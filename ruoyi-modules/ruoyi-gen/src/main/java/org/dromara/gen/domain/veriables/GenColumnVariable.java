package org.dromara.gen.domain.veriables;

import lombok.Data;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.gen.constant.GenConstants;
import org.dromara.gen.domain.GenTable;
import org.dromara.gen.domain.GenTableColumn;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 代码生成字段变量。
 * <p>用于存储数据库表、字段以及根据字段配置分析得到的
 * <p>
 * 各类生成能力标识，供后端和前端代码模板判断字段应采用的处理方式。</p>
 *
 * @author ulpon
 */
@Data
public class GenColumnVariable {
    /**
     * 当前代码生成的数据表信息。
     * <p>包含表名、表注释、生成配置以及字段集合等完整的表级元数据。</p>
     */
    private GenTable table;
    /**
     * 数据表字段集合。
     * <p>保存当前数据表参与代码生成的全部字段信息，
     * <p>
     * 用于模板遍历生成实体类、表单、列表等代码。</p>
     */
    private List<GenTableColumn> columns;
    /**
     * 主键字段。
     * <p>表示当前数据表的主键字段，
     * <p>
     * 用于生成实体类主键、查询条件以及增删改等相关代码。</p>
     */
    private GenTableColumn pkColumn;
    /**
     * 字典类型集合。
     * <p>保存当前表字段中使用到的字典类型，
     * <p>
     * 用于生成字典导入、字典转换以及前端字典选择器等代码。</p>
     */
    private String dicts;
    /**
     * 去除符号后的字典类型集合。
     * <p>与 {@link #dicts} 对应，但去除了模板生成过程中不需要的引号等符号，
     * <p>
     * 用于部分前端或脚本模板直接使用。</p>
     */
    private String dictsNoSymbol;
    /**
     * 是否存在 BETWEEN 查询条件。
     * <p>表示当前表的查询字段中是否存在时间范围或数值范围查询，
     * <p>
     * 用于决定是否生成 BETWEEN 查询相关代码。</p>
     */
    private Boolean hasBetween = false;
    /**
     * 是否需要添加日期范围查询。
     * <p>表示当前表是否存在需要转换为日期范围查询的字段。</p>
     */
    private Boolean needAddDateRange = false;
    /**
     * 是否需要日期范围查询组件。
     * <p>表示前端查询条件中是否需要生成日期范围选择组件。</p>
     */
    private Boolean needDateRange = false;
    /**
     * 是否需要字典组件。
     * <p>表示当前表字段是否使用字典，
     * <p>
     * 用于决定前端是否需要生成字典相关组件或数据。</p>
     */
    private Boolean needDict = false;
    /**
     * 是否需要图片预览。
     * <p>表示列表中是否存在图片字段，
     * <p>
     * 用于决定前端列表是否生成图片预览相关代码。</p>
     */
    private Boolean needImagePreview = false;
    /**
     * 是否需要图片上传组件。
     * <p>表示表单中是否存在图片上传字段，
     * <p>
     * 用于决定前端是否生成图片上传组件。</p>
     */
    private Boolean needImageUpload = false;
    /**
     * 是否需要文件上传组件。
     * <p>表示表单中是否存在文件上传字段，
     * <p>
     * 用于决定前端是否生成文件上传组件。</p>
     */
    private Boolean needFileUpload = false;
    /**
     * 是否需要富文本编辑器。
     * <p>表示表单中是否存在富文本字段，
     * <p>
     * 用于决定前端是否生成富文本编辑器。</p>
     */
    private Boolean needEditor = false;
    /**
     * 是否需要复选框组件。
     * <p>表示表单中是否存在复选框类型字段。</p>
     */
    private Boolean needCheckbox = false;
    /**
     * 是否需要选择类组件。
     * <p>表示表单或查询条件中是否存在下拉选择、
     * <p>
     * 单选、开关等需要选择数据的字段。</p>
     */
    private Boolean needSelect = false;
    /**
     * 是否需要文本域组件。
     * <p>表示表单中是否存在多行文本字段，
     * <p>
     * 用于决定前端是否生成 textarea 组件。</p>
     */
    private Boolean needTextArea = false;
    /**
     * 是否需要数字输入组件。
     * <p>表示表单中是否存在数字类型输入字段，
     * <p>
     * 用于决定前端是否生成数字输入组件。</p>
     */
    private Boolean needDigit = false;
    /**
     * 是否需要日期字段处理。
     * <p>表示表单中是否存在日期时间类型字段，
     * <p>
     * 用于决定前端是否生成日期时间选择组件。</p>
     */
    private Boolean needDateField = false;
    /**
     * 是否需要开关组件。
     * <p>表示当前表单或列表中是否存在开关类型字段。</p>
     */
    private Boolean needSwitchField = false;
    /**
     * 是否需要时间解析。
     * <p>表示列表中是否存在日期时间字段，
     * <p>
     * 用于决定前端是否需要进行日期时间格式化处理。</p>
     */
    private Boolean needParseTime = false;
    /**
     * 第一个树形列表字段。
     * <p>表示列表字段中第一个参与树形展示的字段，
     * <p>
     * 用于树形表模板确定默认的树节点展示字段。</p>
     */
    private String firstTreeListField;

    public GenColumnVariable(GenTable table) {
        this.table = table;
        this.columns = table.getColumns();
        this.pkColumn = table.getPkColumn();
        // ==================== 字典 ====================
        String dictStr = this.columns.stream().filter(column -> !column.isSuperColumn() && StringUtils.isNotEmpty(column.getDictType()) && StringUtils.equalsAny(column.getHtmlType(), GenConstants.HTML_SELECT, GenConstants.HTML_RADIO, GenConstants.HTML_CHECKBOX, GenConstants.HTML_SWITCH)).map(column -> "'%s'".formatted(column.getDictType())).collect(Collectors.joining(", "));
        this.dicts = dictStr;
        this.dictsNoSymbol = StringUtils.replace(dictStr, "'", StringUtils.EMPTY);
        // ==================== 字段能力分析 ====================
        for (GenTableColumn column : this.columns) {
            boolean writable = column.isInsert() || column.isEdit();
            // BETWEEN 查询
            this.hasBetween = this.hasBetween || column.isQuery() && StringUtils.equals(column.getQueryType(), GenConstants.QUERY_BETWEEN);
            // 日期范围
            this.needAddDateRange = this.needAddDateRange || column.isDateRangeQuery();
            this.needDateRange = this.needAddDateRange;
            // 字典
            this.needDict = StringUtils.isNotBlank(this.dicts);
            // 图片预览
            this.needImagePreview = this.needImagePreview || column.isList() && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_IMAGE_UPLOAD);
            // 图片上传
            this.needImageUpload = this.needImageUpload || writable && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_IMAGE_UPLOAD);
            // 文件上传
            this.needFileUpload = this.needFileUpload || writable && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_FILE_UPLOAD);
            // 富文本编辑器
            this.needEditor = this.needEditor || writable && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_EDITOR);
            // 复选框
            this.needCheckbox = this.needCheckbox || writable && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_CHECKBOX);
            // 下拉、单选、开关
            this.needSelect = this.needSelect || (writable || column.isQuery()) && StringUtils.equalsAny(column.getHtmlType(), GenConstants.HTML_SELECT, GenConstants.HTML_RADIO, GenConstants.HTML_SWITCH);
            // 文本域
            this.needTextArea = this.needTextArea || writable && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_TEXTAREA);
            // 数字输入
            this.needDigit = this.needDigit || writable && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_INPUT_NUMBER);
            // 日期时间
            this.needDateField = this.needDateField || writable && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_DATETIME);
            // 开关
            this.needSwitchField = this.needSwitchField || (writable || column.isList()) && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_SWITCH);
            // 时间解析
            this.needParseTime = this.needParseTime || column.isList() && StringUtils.equals(column.getHtmlType(), GenConstants.HTML_DATETIME);
            // 第一个列表字段
            if (StringUtils.isBlank(this.firstTreeListField) && column.isList()) {
                this.firstTreeListField = column.getJavaField();
            }
        }
    }
}
