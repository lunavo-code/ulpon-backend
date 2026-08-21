package com.ulpon.ai.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ulpon.ai.domain.AiChatMessage;
import com.ulpon.ai.domain.vo.AiChatMessageVo;
import com.ulpon.ai.service.IAiChatMessageService;
import dev.langchain4j.data.message.*;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.utils.IdGeneratorUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DbChatMemoryStore implements ChatMemoryStore {
    private final IAiChatMessageService messageService; // 消息数据库操作类

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        Long sessionId = Long.valueOf(memoryId.toString());
        // 1. 查询该 sessionId 下所有正常状态的消息，按时间升序排列
        List<AiChatMessageVo> dbMessages = messageService.queryBySessionId(sessionId);
        if(dbMessages == null || dbMessages.isEmpty()) return List.of();
        // 2. 将数据库实体转换为 LangChain4j 标准的 ChatMessage 对象（UserMessage, AiMessage, SystemMessage 等）
        return dbMessages.stream().map(msg -> switch (msg.getRole()) {
            case "user" -> UserMessage.from(msg.getContent());
            case "assistant" -> AiMessage.from(msg.getContent());
            case "system" -> SystemMessage.from(msg.getContent());
            // 可扩展 ToolMessage
            default -> UserMessage.from(msg.getContent());
        }).collect(Collectors.toList());
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        if (messages == null || messages.isEmpty()) return;
        Long sessionId = Long.valueOf(memoryId.toString());

        // 1. 获取数据库中已有的消息数，做增量保存，防止重复插入
        LambdaQueryWrapper<AiChatMessage> countWrapper = Wrappers.lambdaQuery();
        countWrapper.eq(AiChatMessage::getSessionId, sessionId);
        long existingCount = messageService.count(countWrapper);
        if (messages.size() <= existingCount) return;

        // 2. 映射并保存新增的消息
        List<AiChatMessage> list = messages.stream().skip(existingCount) // 过滤掉已经落库的旧消息
            .map(item -> {
                // 规范化 Role 字符串，与 getMessages 的 switch-case 保持一致
                String role = switch (item.type()) {
                    case SYSTEM -> "system";
                    case USER -> "user";
                    case AI -> "assistant";
                    case TOOL_EXECUTION_RESULT -> "tool";
                    case CUSTOM -> "custom";
                };
                // 安全提取消息文本内容 (兼容 Java 21)
                String content = switch (item) {
                    case SystemMessage systemMessage -> systemMessage.text();
                    case UserMessage userMessage -> userMessage.singleText();
                    case AiMessage aiMessage -> aiMessage.text();
                    case ToolExecutionResultMessage toolMessage -> toolMessage.text();
                    case CustomMessage customMessage -> customMessage.toString();
                    default -> "";
                };
                return new AiChatMessage(
                    IdGeneratorUtil.nextLongId(),
                    sessionId,
                    role,
                    content,
                    0,
                    false
                );
            }).filter(i -> StringUtils.isNotBlank(i.getContent())).toList();
        for (AiChatMessage aiChatMessage : list) {
            messageService.save(aiChatMessage);
        }
//        messageService.saveBatch(list);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        Long sessionId = Long.valueOf(memoryId.toString());
        messageService.deleteBySessionId(sessionId);
    }
}
