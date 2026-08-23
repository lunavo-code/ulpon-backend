package com.ulpon.ai.handler;

import com.ulpon.ai.domain.vo.AiChatVo;
import com.ulpon.ai.enums.MsgTypeEnum;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import lombok.AllArgsConstructor;
import reactor.core.publisher.FluxSink;

@AllArgsConstructor
public class AiChatVoResponseHandler implements StreamingChatResponseHandler {
    private FluxSink<AiChatVo> sink;

    @Override
    public void onPartialResponse(String partialResponse) {
        sink.next(new AiChatVo(null, MsgTypeEnum.CONTENT, partialResponse));
    }

    @Override
    public void onCompleteResponse(ChatResponse completeResponse) {
        sink.complete();
    }

    @Override
    public void onError(Throwable error) {
        sink.error(error);
    }
}
