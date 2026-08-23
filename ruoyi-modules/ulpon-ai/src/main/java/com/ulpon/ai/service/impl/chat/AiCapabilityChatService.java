package com.ulpon.ai.service.impl.chat;

import com.ulpon.ai.common.ModelFactory;
import com.ulpon.ai.domain.AiCapability;
import com.ulpon.ai.domain.AiCapabilityTask;
import com.ulpon.ai.domain.bo.AiCapabilityChatBo;
import com.ulpon.ai.domain.vo.AiChatVo;
import com.ulpon.ai.enums.MsgTypeEnum;
import com.ulpon.ai.exceptions.AiException;
import com.ulpon.ai.handler.AiChatVoResponseHandler;
import com.ulpon.ai.service.IAiCapabilityService;
import com.ulpon.ai.service.IAiCapabilityTaskService;
import com.ulpon.ai.util.AiResUtils;
import dev.langchain4j.data.message.*;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.mybatis.utils.IdGeneratorUtil;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * 定义对话服务，非agent
 */
@Component
@AllArgsConstructor
@Slf4j
public class AiCapabilityChatService {
    private final ModelFactory modelFactory;
    private final IAiCapabilityService capabilityService;
    private final IAiCapabilityTaskService capabilityTaskService;

    public enum UserMsgType {
        TEXT, IMAGE, AUDIO, VIDEO, PDF
    }

    public record UserMsg(UserMsgType msgType, String content, String mimeType) {
        public UserMsg {
            if (msgType == null) {
                throw new AiException("消息类型不能为空");
            }
            if (content == null || content.isBlank()) {
                throw new AiException("消息内容不能为空");
            }
            if (msgType != UserMsgType.TEXT && mimeType == null) {
                throw new AiException("消息类型为非文本时，mimeType不能为空");
            }
        }

        public UserMsg(UserMsgType msgType, String content) {
            this(msgType, content, null);
        }

        public UserMsg(String content) {
            this(UserMsgType.TEXT, content, null);
        }
    }

    public String chat(AiCapabilityChatBo bo) {
        return chat(bo.getCapabilityId(), bo.getUserMsg());
    }

    public String chat(Long capabilityId, String userMsg) {
        return AiResUtils.streamToObj(stream(capabilityId, userMsg)).getC();
    }

    public Flux<AiChatVo> stream(AiCapabilityChatBo bo) {
        return stream(bo.getCapabilityId(), bo.getUserMsg());
    }

    public Flux<AiChatVo> stream(Long capabilityId, String userMsg) {
        AiCapability aiCapability = capabilityService.getById(capabilityId);
        UserMsg um = new UserMsg(userMsg);
        Long modelId = aiCapability.getModelConfigId();
        ModelFactory.ChatModel model = modelId == null ? modelFactory.getChatModel() : modelFactory.getChatModel(modelId);
        OpenAiStreamingChatModel streamingChatModel = model.streamingChatModel();
        ChatRequest chatRequest = buildChatRequest(aiCapability.getSystemPrompt(), um);
        Flux<AiChatVo> chatVoFlux = Flux.create(sink -> streamingChatModel.chat(chatRequest, new AiChatVoResponseHandler(sink)));
        StringBuilder content = new StringBuilder();
        return chatVoFlux.doOnNext(c -> {
                if (MsgTypeEnum.CONTENT.equals(c.getT()) && c.getC() != null) {
                    content.append(c.getC());
                }
            })
            .doOnComplete(() -> saveCapabilityTask(capabilityId, userMsg, content.toString()))
            .doOnError(e -> {
                content.append("\n");
                content.append(e.getMessage());
                saveCapabilityTask(capabilityId, userMsg, content.toString());
            });
    }

    private void saveCapabilityTask(Long capabilityId, String userMsg, String content) {
        capabilityTaskService.save(new AiCapabilityTask(IdGeneratorUtil.nextLongId(), capabilityId, userMsg, content, 0, false));
    }

    private ChatRequest buildChatRequest(String systemMsg, UserMsg... userMsgList) {
        if (userMsgList == null || userMsgList.length == 0) throw new AiException("用户消息不能为空");
        List<ChatMessage> list = new ArrayList<>();
        if (systemMsg != null && !systemMsg.isEmpty()) {
            list.add(SystemMessage.from(systemMsg));
        }
        List<Content> userMsgContentList = buildContents(userMsgList);
        UserMessage userMessage = UserMessage.from(userMsgContentList);
        list.add(userMessage);
        return ChatRequest.builder().messages(list).build();
    }

    private static @NonNull List<Content> buildContents(UserMsg[] userMsgList) {
        return Arrays.stream(userMsgList).map(item -> switch (item.msgType) {
            case TEXT -> TextContent.from(item.content); // 文本内容
            case IMAGE -> ImageContent.from(item.content); // 图片，url或base64
            case AUDIO -> AudioContent.from(item.content); // 音频，url或base64
            case VIDEO -> VideoContent.from(item.content); // 视频，url或base64
            case PDF -> PdfFileContent.from(item.content); // pdf，url或base64
        }).toList();
    }

    private void toBase64Str(byte[] bytes) {
        Base64.getEncoder().encodeToString(bytes);

    }
}
