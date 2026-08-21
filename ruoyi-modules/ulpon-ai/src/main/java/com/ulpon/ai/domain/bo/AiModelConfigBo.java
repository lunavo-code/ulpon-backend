package com.ulpon.ai.domain.bo;

import com.ulpon.ai.domain.AiModelConfig;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 大模型配置业务对象 ai_model_config
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@AutoMapper(target = AiModelConfig.class, reverseConvertGenerate = false)
public class AiModelConfigBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @NotNull(message = "主键 ID不能为空", groups = { EditGroup.class })
    private Long modelConfigId;

    /**
     * 提供商 ID
     */
    @NotNull(message = "提供商 ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long providerId;

    /**
     * 配置别名/名称
     */
    @NotBlank(message = "配置别名/名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String configName;

    /**
     * 目标模型名称
     */
    @NotBlank(message = "目标模型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String modelName;

    /**
     * API 接口覆盖地址
     */
    private String baseUrl;

    /**
     * API 密钥 (Script Key)
     */
    @NotBlank(message = "API 密钥 (Script Key)不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "启用状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Boolean status;

    /**
     * 是否为默认模型（Y是 N否）
     */
    @NotNull(message = "是否为默认模型（Y是 N否）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Boolean isDefault;

    /**
     * 备注
     */
    private String remark;


}
