package com.ulpon.ai.domain.vo;

import com.ulpon.ai.domain.AiChatSession;
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
 * 对话会话视图对象 ai_chat_session
 *
 * @author Ulpon
 * @date 2026-08-21 11:01:03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AiChatSession.class)
public class AiChatSessionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @ExcelProperty(value = "主键 ID")
    private Long sessionId;

    /**
     * 会话名称
     */
    @ExcelProperty(value = "会话名称")
    private String sessionName;

    /**
     * 关联智能体 ID
     */
    @ExcelProperty(value = "关联智能体 ID")
    private Long agentId;

    /**
     * 会话所有者 ID
     */
    @ExcelProperty(value = "会话所有者 ID")
    private Long userId;


}
