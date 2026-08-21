package com.ulpon.ai.domain.vo;

import com.ulpon.ai.domain.AiChatMessage;
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
 * 对话消息视图对象 ai_chat_message
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AiChatMessage.class)
public class AiChatMessageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @ExcelProperty(value = "主键 ID")
    private Long messageId;

    /**
     * 关联会话 ID
     */
    @ExcelProperty(value = "关联会话 ID")
    private Long sessionId;

    /**
     * 角色类型（system, user, assistant, tool）
     */
    @ExcelProperty(value = "角色类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "s=ystem,,u=ser,,a=ssistant,,t=ool")
    private String role;

    /**
     * 消息内容
     */
    @ExcelProperty(value = "消息内容")
    private String content;

    /**
     * Token 消耗数
     */
    @ExcelProperty(value = "Token 消耗数")
    private Integer tokenCount;


}
