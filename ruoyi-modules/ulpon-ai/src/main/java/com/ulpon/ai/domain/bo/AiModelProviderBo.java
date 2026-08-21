package com.ulpon.ai.domain.bo;

import com.ulpon.ai.domain.AiModelProvider;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 大模型提供商业务对象 ai_model_provider
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@AutoMapper(target = AiModelProvider.class, reverseConvertGenerate = false)
public class AiModelProviderBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @NotNull(message = "主键 ID不能为空", groups = { EditGroup.class })
    private Long providerId;

    /**
     * 提供商名称
     */
    @NotBlank(message = "提供商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String providerName;

    /**
     * 提供商唯一标识键
     */
    @NotBlank(message = "提供商唯一标识键不能为空", groups = { AddGroup.class, EditGroup.class })
    private String providerCode;

    /**
     * 默认 API 接口地址
     */
    private String defaultBaseUrl;

    /**
     * 启用状态（0正常 1停用）
     */
    @NotNull(message = "启用状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Boolean status;

    /**
     * 备注
     */
    private String remark;


}
