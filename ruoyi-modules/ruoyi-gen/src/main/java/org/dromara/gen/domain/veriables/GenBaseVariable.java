package org.dromara.gen.domain.veriables;

import lombok.Data;
import org.dromara.common.core.utils.DateUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.gen.constant.GenConstants;
import org.dromara.gen.domain.GenTable;
import org.dromara.gen.domain.GenTableColumn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 代码生成基础变量。
 * <p>用于存储代码生成过程中与模块、包名、类名、表名、
 * <p>
 * 模板类型以及权限相关的基础信息，供各类代码模板使用。</p>
 *
 * @author ulpon
 */
@Data
public class GenBaseVariable {

    /**
     * 基础包名。
     * <p>表示项目的基础 Java 包路径，例如：
     * <p>
     * {@code org.dromara.system}。</p>
     */
    private String basePackage;

    /**
     * 完整 Java 包名。
     * <p>表示当前生成代码所属的完整 Java 包路径。</p>
     */
    private String packageName;

    /**
     * 模块名称。
     * <p>表示当前代码所属的业务模块名称，
     * <p>
     * 通常用于生成 Controller、Service、Mapper 等类所在的包路径。</p>
     */
    private String moduleName;

    /**
     * 类名，首字母大写。
     * <p>根据数据库表名转换得到的 Java 类名，
     * <p>
     * 例如 {@code sys_user} 转换为 {@code SysUser}。</p>
     */
    private String classNameUpper;

    /**
     * 类名，首字母小写。
     * <p>用于生成 Java Bean 名称、前端变量名称等场景，
     * <p>
     * 例如 {@code SysUser} 对应 {@code sysUser}。</p>
     */

    private String classNameLower;
    /**
     * 业务名称，首字母大写。
     * <p>表示业务名称的首字母大写形式，
     * <p>
     * 主要用于生成类名、方法名以及前端组件名称等。</p>
     */
    private String businessNameUpper;

    /**
     * 业务名称，首字母小写。
     * <p>表示业务名称的首字母小写形式，
     * <p>
     * 主要用于生成变量名、权限标识以及前端业务标识。</p>
     */
    private String businessNameLower;

    /**
     * 模板类型。
     * <p>表示当前代码生成所使用的模板类别，
     * <p>
     * 例如单表、树表、主子表等。</p>
     */
    private String tplCategory;

    /**
     * 前端代码类型。
     * <p>表示前端代码生成类型，用于确定生成 Vue、
     * <p>
     * TypeScript 等前端代码时所采用的模板。</p>
     */
    private String frontendType;

    /**
     * 功能名称。
     * <p>表示当前业务功能的名称，通常来源于代码生成配置，
     * <p>
     * 用于生成类注释、菜单名称以及页面标题等。</p>
     */
    private String functionName;

    /**
     * 数据库表名。
     * <p>表示当前代码生成对应的数据表名称。</p>
     */
    private String tableName;

    /**
     * 作者名称。
     * <p>表示生成代码中的作者信息，
     * <p>
     * 通常用于生成 Java 类的 {@code @author} 注释。</p>
     */
    private String author;

    /**
     * 代码生成时间。
     * <p>表示本次代码生成的时间，
     * <p>
     * 通常用于生成文件头部的时间注释。</p>
     */

    private String datetime;
    /**
     * 权限标识前缀。
     * <p>用于生成后端接口权限标识，
     * <p>
     * 通常由模块名称和业务名称组合而成。</p>
     * <p>例如：{@code system:user}。</p>
     */
    private String permissionPrefix;

    /**
     * 导入包集合。
     * <p>保存当前代码模板所需要导入的 Java 类，
     * <p>
     * 用于动态生成目标 Java 文件中的 {@code import} 语句。</p>
     */
    private Set<String> importList;

    /**
     * 父级菜单 ID。
     * <p>表示生成菜单时使用的父级菜单标识，
     * <p>
     * 用于确定当前业务菜单在系统菜单树中的位置。</p>
     */
    private String parentMenuId;

    public GenBaseVariable(GenTable table) {
        this.tplCategory = table.getTplCategory();
        this.frontendType = table.getFrontendType();
        this.tableName = table.getTableName();
        this.functionName = table.getFunctionName();
        this.classNameUpper = table.getClassName();
        this.classNameLower = StringUtils.uncapitalize(this.classNameUpper);
        this.moduleName = table.getModuleName();
        this.businessNameLower = table.getBusinessName();
        this.businessNameUpper = StringUtils.capitalize(this.businessNameLower);
        this.packageName = table.getPackageName();
        int lastIndex = this.packageName.lastIndexOf(".");
        this.basePackage = StringUtils.substring(this.packageName, 0, lastIndex);
        this.author = table.getFunctionAuthor();
        this.datetime = DateUtils.now();
        this.importList = bulidImportList(table);
        this.permissionPrefix = "%s:%s".formatted(this.moduleName, this.businessNameLower);
    }

    private static HashSet<String> bulidImportList(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();
        HashSet<String> importList = new HashSet<>();
        for (GenTableColumn column : columns) {
            boolean needImportColumn = !column.isSuperColumn() || column.isQuery();
            if (needImportColumn && GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.time.LocalDateTime");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            } else if (needImportColumn && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }
}
