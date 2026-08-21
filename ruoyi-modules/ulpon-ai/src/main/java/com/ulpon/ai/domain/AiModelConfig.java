package com.ulpon.ai.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 大模型配置对象 ai_model_config
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_model_config")
public class AiModelConfig extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "model_config_id")
    private Long modelConfigId;

    /**
     * 提供商 ID
     */
    private Long providerId;

    /**
     * 配置别名/名称
     */
    private String configName;

    /**
     * 目标模型名称
     */
    private String modelName;

    /**
     * API 接口覆盖地址
     */
    private String baseUrl;

    /**
     * API 密钥 (Script Key)
     */
    private String apiKey;

    /**
     * 最大生成 Token 数
     */
    private Integer maxTokens;

    /**
     * 温度参数 (0.0 ~ 2.0)
     */
    private Double temperature;

    /**
     * 启用状态（0正常 1停用）
     */
    private Boolean status;

    /**
     * 是否为默认模型（Y是 N否）
     */
    private Boolean isDefault;

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
