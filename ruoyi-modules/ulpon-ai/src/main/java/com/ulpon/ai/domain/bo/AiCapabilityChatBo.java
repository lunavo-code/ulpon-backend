package com.ulpon.ai.domain.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiCapabilityChatBo {
    private Long capabilityId;
    @NotBlank
    private String userMsg;
}
