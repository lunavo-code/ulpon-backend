package org.dromara.gen.domain.veriables;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.gen.constant.GenConstants;
import org.dromara.gen.domain.GenTable;
import org.dromara.gen.domain.GenTableColumn;

import java.util.List;

/**
 * 代码生成选项变量。
 * <p>用于存储代码生成过程中根据表配置解析得到的各种功能开关、
 * <p>
 * 状态字段、唯一约束以及排序字段等选项信息。</p>
 *
 * @author ulpon
 */
@Data
public class GenOptionVariable {

    private static final String DEFAULT_PARENT_MENU_ID = "1761400000000000003";

    /**
     * 父级菜单 ID。
     * <p>表示当前业务菜单所属的父级菜单，
     * <p>
     * 用于生成系统菜单配置。</p>
     */
    private String parentMenuId;
    /**
     * 是否启用数据导出。
     * <p>用于控制是否生成 Excel 等数据导出相关代码。</p>
     */
    private Boolean enableExport;
    /**
     * 是否启用状态字段。
     * <p>用于控制当前业务是否存在状态字段，
     * <p>
     * 并决定是否生成状态查询、状态修改等相关代码。</p>
     */
    private Boolean enableStatus;
    /**
     * 状态字段信息。
     * <p>表示当前配置中指定的状态字段完整信息，
     * <p>
     * 用于获取字段类型、Java 字段名以及状态值等信息。</p>
     */
    private GenTableColumn statusColumn;
    /**
     * 状态字段 Java 属性名。
     * <p>表示状态字段对应的 Java 属性名称，
     * <p>
     * 用于生成实体类、查询条件以及业务代码。</p>
     */
    private String statusField;
    /**
     * 状态启用值。
     * <p>表示状态字段代表“启用”状态时使用的数据库值。</p>
     */
    private String statusActiveValue;
    /**
     * 状态停用值。
     * <p>表示状态字段代表“停用”状态时使用的数据库值。</p>
     */
    private String statusInactiveValue;
    /**
     * 是否启用唯一性校验。
     * <p>用于控制是否生成数据唯一性校验相关代码。</p>
     */
    private Boolean enableUnique;
    /**
     * 唯一性校验字段集合。
     * <p>保存参与唯一性校验的字段，
     * <p>
     * 用于生成新增、修改时的数据重复校验逻辑。</p>
     */
    private List<GenTableColumn> uniqueColumns;
    /**
     * 是否启用排序。
     * <p>用于控制当前业务是否生成排序相关字段和处理逻辑。</p>
     */
    private Boolean enableSort;
    /**
     * 排序字段信息。
     * <p>表示当前配置指定的排序字段完整信息，
     * <p>
     * 用于获取排序字段的数据类型以及数据库字段信息。</p>
     */
    private GenTableColumn sortColumn;
    /**
     * 排序字段 Java 属性名。
     * <p>表示排序字段对应的 Java 属性名称，
     * <p>
     * 用于生成排序相关的实体、查询以及业务代码。</p>
     */
    private String sortField;

    public GenOptionVariable(GenTable table) {
        Dict options = JsonUtils.parseMap(table.getOptions());
        // ==================== 菜单 ====================
        this.parentMenuId = getParentMenuIdOption(options);
        // ==================== 导出 ====================
        this.enableExport = VariableUtils.getBooleanOption(options, GenConstants.ENABLE_EXPORT, true);
        // ==================== 状态 ====================
        this.enableStatus = VariableUtils.getBooleanOption(options, GenConstants.ENABLE_STATUS, false);
        this.statusColumn = VariableUtils.getOptionColumn(table, options.getStr(GenConstants.STATUS_FIELD));
        this.statusField = ObjectUtil.isNotNull(this.statusColumn) ? this.statusColumn.getJavaField() : StringUtils.EMPTY;
        this.statusActiveValue = ObjectUtil.isNotNull(this.statusColumn) ? this.statusColumn.getSwitchActiveValue() : StringUtils.EMPTY;
        this.statusInactiveValue = ObjectUtil.isNotNull(this.statusColumn) ? this.statusColumn.getSwitchInactiveValue() : StringUtils.EMPTY;
        // ==================== 唯一性 ====================
        this.enableUnique =VariableUtils.getBooleanOption(options, GenConstants.ENABLE_UNIQUE, false);
        this.uniqueColumns = VariableUtils.getOptionColumns(table, options.get(GenConstants.UNIQUE_FIELDS));
        // ==================== 排序 ====================
        this.enableSort = VariableUtils.getBooleanOption(options, GenConstants.ENABLE_SORT, false);
        this.sortColumn = VariableUtils.getOptionColumn(table, options.getStr(GenConstants.SORT_FIELD));
        this.sortField = ObjectUtil.isNotNull(this.sortColumn) ? this.sortColumn.getJavaField() : StringUtils.EMPTY;
    }

    private static String getParentMenuIdOption(Dict paramsObj) {
        if (CollUtil.isNotEmpty(paramsObj) && paramsObj.containsKey(GenConstants.PARENT_MENU_ID)
            && StringUtils.isNotEmpty(paramsObj.getStr(GenConstants.PARENT_MENU_ID))) {
            return paramsObj.getStr(GenConstants.PARENT_MENU_ID);
        }
        return DEFAULT_PARENT_MENU_ID;
    }
}
