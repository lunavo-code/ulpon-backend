package com.ulpon.ai.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 大模型提供商对象 ai_model_provider
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_model_provider")
public class AiModelProvider extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "provider_id")
    private Long providerId;

    /**
     * 提供商名称
     */
    private String providerName;

    /**
     * 提供商唯一标识键
     */
    private String providerCode;

    /**
     * 默认 API 接口地址
     */
    private String defaultBaseUrl;

    /**
     * 启用状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 备注
     */
    private String remark;


}
