package org.dromara.gen.domain.veriables;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.gen.constant.GenConstants;
import org.dromara.gen.domain.GenTable;
import org.dromara.gen.domain.GenTableColumn;

/**
 * 树形表代码生成变量。
 *
 * <p>用于存储树形表代码生成过程中所需的树结构相关变量，
 * 供后端 Java、前端 Vue/TypeScript 等代码模板使用。</p>
 *
 * @author ulpon
 */
@Data
public class GenTreeVariable {
    /**
     * 树节点编码字段。
     *
     * <p>表示树形结构中用于唯一标识当前节点的数据库字段，
     * 通常对应表中的主键或业务编码字段。</p>
     */
    private String treeCode;
    /**
     * 树父节点编码字段。
     *
     * <p>表示当前节点对应的父节点字段，用于建立树形结构中的
     * 父子节点关系。</p>
     */
    private String treeParentCode;
    /**
     * 树节点名称字段。
     *
     * <p>表示树形结构中用于展示节点名称的字段，
     * 例如部门名称、分类名称等。</p>
     */
    private String treeName;
    /**
     * 树父节点字段信息。
     *
     * <p>对应 {@link #treeParentCode} 的完整字段定义，
     * 用于获取父节点字段的数据类型、Java 类型等元数据信息。</p>
     */
    private GenTableColumn treeParentColumn;
    /**
     * 树祖先节点字段名。
     *
     * <p>用于保存当前节点的祖先节点路径，例如：
     * {@code 0,100,101}。</p>
     *
     * <p>如果数据表未配置祖先节点字段，则该值通常为空。</p>
     */
    private String treeAncestorsField;
    /**
     * 树排序字段名。
     *
     * <p>表示用于控制同级树节点显示顺序的 Java 字段名。</p>
     */
    private String treeOrderField;
    /**
     * 树排序字段信息。
     *
     * <p>对应 {@link #treeOrderField} 的完整字段定义，
     * 用于获取排序字段的数据类型及数据库字段信息。</p>
     */
    private GenTableColumn treeOrderColumn;
    /**
     * 树根节点值。
     *
     * <p>表示树形结构根节点对应的父节点值，
     * 用于判断某个节点是否属于根节点下的一级节点。</p>
     */
    private String treeRootValue;
    /**
     * 树根节点值的 Java 字面量。
     *
     * <p>根据父节点字段的数据类型，将 {@link #treeRootValue}
     * 转换为可直接写入 Java 源代码的字面量形式。</p>
     *
     * <p>例如字符串类型可能生成 {@code "0"}，
     * 数字类型可能生成 {@code 0}。</p>
     */
    private String treeRootValueJavaLiteral;
    /**
     * 树根节点值的 TypeScript 字面量。
     *
     * <p>根据父节点字段的数据类型，将 {@link #treeRootValue}
     * 转换为可直接写入 TypeScript 源代码的字面量形式。</p>
     */
    private String treeRootValueTsLiteral;
    /**
     * 树父节点字段首字母大写名称。
     *
     * <p>用于生成 Java 方法名、变量名或其他需要使用
     * 首字母大写字段名称的模板代码。</p>
     */
    private String treeParentCap;
    /**
     * 树祖先字段首字母大写名称。
     *
     * <p>用于生成 Java 方法名、变量名等需要使用
     * 首字母大写字段名称的模板代码。</p>
     */
    private String treeAncestorsCap;
    /**
     * 树排序字段首字母大写名称。
     *
     * <p>用于生成 Java 方法名、变量名等需要使用
     * 首字母大写字段名称的模板代码。</p>
     */
    private String treeOrderCap;
    /**
     * 树节点展开字段所在的列索引。
     *
     * <p>用于前端树形列表生成，表示树名称字段在列表字段中的位置，
     * 用于确定前端表格中树形展开节点对应的列。</p>
     *
     * <p>默认值为 {@code 0}。</p>
     */
    private Integer expandColumn = 0;
    /**
     * 树父节点配置值。
     *
     * <p>用于兼容现有代码生成模板中的 {@code tree_parent_code}
     * 变量名称，保存树父节点字段对应的原始配置值。</p>
     *
     * <p>该字段采用下划线命名是为了保持与现有模板变量的兼容性。</p>
     */
    private Object tree_parent_code;
    /**
     * 树节点名称配置值。
     *
     * <p>用于兼容现有代码生成模板中的 {@code tree_name}
     * 变量名称，保存树节点名称字段对应的原始配置值。</p>
     *
     * <p>该字段采用下划线命名是为了保持与现有模板变量的兼容性。</p>
     */
    private Object tree_name;

    public GenTreeVariable(GenTable table) {
        if (!GenConstants.TPL_TREE.equals(table.getTplCategory())) {
            return;
        }

        Dict options = JsonUtils.parseMap(table.getOptions());
        this.treeCode = VariableUtils.getTreeCodeOption(options);
        this.treeParentCode = VariableUtils.getTreeParentCodeOption(options);
        this.treeName = VariableUtils.getTreeNameOption(options);
        // ==================== 父节点 ====================
        this.treeParentColumn = VariableUtils.getOptionColumn(table, options.getStr(GenConstants.TREE_PARENT_CODE));
        // ==================== 排序 ====================
        this.treeOrderColumn = VariableUtils.getOptionColumn(table, options.getStr(GenConstants.TREE_ORDER_FIELD));
        this.treeOrderField = ObjectUtil.isNotNull(this.treeOrderColumn) ? this.treeOrderColumn.getJavaField() : StringUtils.EMPTY;
        // ==================== 根节点 ====================
        this.treeRootValue = VariableUtils.getTreeRootValueOption(options, this.treeParentColumn);
        this.treeRootValueJavaLiteral = VariableUtils.getJavaLiteralOption(this.treeParentColumn, this.treeRootValue);
        this.treeRootValueTsLiteral = VariableUtils.getTsLiteralOption(this.treeParentColumn, this.treeRootValue);
        // ==================== 祖先节点 ====================
        GenTableColumn treeAncestorsColumn = VariableUtils.getOptionColumn(table, options.getStr(GenConstants.TREE_ANCESTORS));
        this.treeAncestorsField = ObjectUtil.isNotNull(treeAncestorsColumn) ? treeAncestorsColumn.getJavaField() : StringUtils.EMPTY;
        // ==================== 首字母大写 ====================
        this.treeParentCap = StringUtils.capitalize(this.treeParentCode);
        this.treeAncestorsCap = StringUtils.capitalize(ObjectUtil.isNotNull(treeAncestorsColumn) ? treeAncestorsColumn.getJavaField() : StringUtils.EMPTY);
        this.treeOrderCap = StringUtils.capitalize(ObjectUtil.isNotNull(this.treeOrderColumn) ? this.treeOrderColumn.getJavaField() : StringUtils.EMPTY);
        // ==================== 树节点展开列 ====================
        String expandTreeName = options.getStr(GenConstants.TREE_NAME);
        for (GenTableColumn column : table.getColumns()) {
            if (!column.isList()) continue;
            this.expandColumn++;
            if (column.getColumnName().equals(expandTreeName)) break;
        }
        // ==================== 模板兼容变量 ====================
        if (options.containsKey(GenConstants.TREE_PARENT_CODE)) {
            this.tree_parent_code = options.get(GenConstants.TREE_PARENT_CODE);
        }
        if (options.containsKey(GenConstants.TREE_NAME)) {
            this.tree_name = options.get(GenConstants.TREE_NAME);
        }
    }
}
