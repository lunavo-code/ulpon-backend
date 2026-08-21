package com.ulpon.ai.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ulpon.ai.domain.bo.AiChatBo;
import com.ulpon.ai.domain.vo.AiChatVo;
import com.ulpon.ai.service.impl.AiChatService;
import lombok.AllArgsConstructor;
import org.dromara.common.core.domain.R;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("ai/chat")
@AllArgsConstructor
@SaIgnore
public class AiChatController {
    private final AiChatService chatService;

    @PostMapping("s")
    public R<AiChatVo> chat(@RequestBody AiChatBo bo) {
        return R.ok(chatService.chat(bo));
    }

    @PostMapping(value = "a", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AiChatVo> stream(@RequestBody AiChatBo bo) {
        return chatService.stream(bo);
    }

}
