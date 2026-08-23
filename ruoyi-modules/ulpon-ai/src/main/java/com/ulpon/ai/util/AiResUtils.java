package com.ulpon.ai.util;

import com.ulpon.ai.domain.vo.AiChatVo;
import com.ulpon.ai.enums.MsgTypeEnum;
import reactor.core.publisher.Flux;

public class AiResUtils {
    public static AiChatVo streamToObj(Flux<AiChatVo> s) {
        return s.filter(i -> MsgTypeEnum.CONTENT.equals(i.getT()))
            .collectList()
            .map(l -> {
                StringBuilder builder = new StringBuilder();
                Long sid = l.getFirst().getSId();
                l.forEach(i -> builder.append(i.getC()));
                return new AiChatVo(sid, MsgTypeEnum.CONTENT, builder.toString());
            })
            .block();
    }
}
