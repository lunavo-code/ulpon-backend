package com.ulpon.ai.domain.vo;

import com.ulpon.ai.domain.AiCapabilityTask;
import org.apache.fesod.sheet.annotation.ExcelIgnoreUnannotated;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * AI能力任务视图对象 ai_capability_task
 *
 * @author Ulpon
 * @date 2026-08-23 01:03:38
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AiCapabilityTask.class)
public class AiCapabilityTaskVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @ExcelProperty(value = "主键 ID")
    private Long taskId;

    /**
     * 能力 ID
     */
    @ExcelProperty(value = "能力 ID")
    private Long capabilityId;

    /**
     * 用户消息
     */
    @ExcelProperty(value = "用户消息")
    private String userContent;

    /**
     * 反馈消息
     */
    @ExcelProperty(value = "反馈消息")
    private String resContent;

    /**
     * Token 消耗数
     */
    @ExcelProperty(value = "Token 消耗数")
    private Integer tokenCount;


}
