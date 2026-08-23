package com.ulpon.ai.domain.bo;

import com.ulpon.ai.domain.AiCapabilityTask;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * AI能力任务业务对象 ai_capability_task
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Data
@AutoMapper(target = AiCapabilityTask.class, reverseConvertGenerate = false)
public class AiCapabilityTaskBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @NotNull(message = "主键 ID不能为空", groups = { EditGroup.class })
    private Long taskId;

    /**
     * 能力 ID
     */
    @NotNull(message = "能力 ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long capabilityId;

    /**
     * 用户消息
     */
    @NotBlank(message = "用户消息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userContent;

    /**
     * 反馈消息
     */
    @NotBlank(message = "反馈消息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String resContent;

    /**
     * Token 消耗数
     */
    private Integer tokenCount;


}
