package org.dromara.gen.domain.veriables;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.gen.constant.GenConstants;
import org.dromara.gen.domain.GenTable;
import org.dromara.gen.domain.GenTableColumn;
import org.dromara.gen.util.TemplateEngineUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class VariableUtils {
    protected static boolean getBooleanOption(Dict paramsObj, String key, boolean defaultValue) {
        if (CollUtil.isEmpty(paramsObj) || !paramsObj.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toBool(paramsObj.get(key), defaultValue);
    }

    protected static GenTableColumn getOptionColumn(GenTable genTable, String field) {
        if (StringUtils.isBlank(field) || CollUtil.isEmpty(genTable.getColumns())) {
            return null;
        }
        for (GenTableColumn column : genTable.getColumns()) {
            if (StringUtils.equalsAny(field, column.getColumnName(), column.getJavaField())) {
                return column;
            }
        }
        return null;
    }

    protected static List<GenTableColumn> getOptionColumns(GenTable genTable, Object fieldValues) {
        List<String> fields = new ArrayList<>();
        if (fieldValues instanceof Collection<?> collection) {
            collection.stream().map(Convert::toStr).forEach(fields::add);
        } else if (ObjectUtil.isNotNull(fieldValues)) {
            fields.addAll(StringUtils.str2List(Convert.toStr(fieldValues), StringUtils.SEPARATOR, true, true));
        }
        List<GenTableColumn> columns = new ArrayList<>();
        for (String field : fields) {
            GenTableColumn column = getOptionColumn(genTable, field);
            if (ObjectUtil.isNotNull(column)) {
                columns.add(column);
            }
        }
        return columns;
    }

    protected static String getTreeCodeOption(Map<String, Object> paramsObj) {
        return TemplateEngineUtils.getTreeCode(paramsObj);
    }

    protected static String getTreeNameOption(Dict paramsObj) {
        return TemplateEngineUtils.getTreeName(paramsObj);
    }

    protected static String getTreeParentCodeOption(Dict paramsObj) {
        return TemplateEngineUtils.getTreeParentCode(paramsObj);
    }

    protected static String getJavaLiteralOption(GenTableColumn column, String value) {
        if (ObjectUtil.isNull(column) || StringUtils.isBlank(value)) {
            return "null";
        }
        if (StringUtils.equals(column.getJavaType(), GenConstants.TYPE_LONG)) {
            return value + "L";
        }
        if (StringUtils.equals(column.getJavaType(), GenConstants.TYPE_FLOAT)) {
            return value + "F";
        }
        if (StringUtils.equals(column.getJavaType(), GenConstants.TYPE_DOUBLE)) {
            return value + "D";
        }
        if (StringUtils.equals(column.getJavaType(), GenConstants.TYPE_BIGDECIMAL)) {
            return "new java.math.BigDecimal(\"" + value + "\")";
        }
        if (StringUtils.equals(column.getJavaType(), GenConstants.TYPE_INTEGER)) {
            return value;
        }
        return "\"" + value + "\"";
    }

    protected static String getTsLiteralOption(GenTableColumn column, String value) {
        if (ObjectUtil.isNull(column) || StringUtils.isBlank(value)) {
            return "undefined";
        }
        if (StringUtils.equalsAny(column.getJavaType(), GenConstants.TYPE_LONG, GenConstants.TYPE_INTEGER,
            GenConstants.TYPE_DOUBLE, GenConstants.TYPE_FLOAT, GenConstants.TYPE_BIGDECIMAL)) {
            return value;
        }
        return "'" + value + "'";
    }

    protected static String getTreeRootValueOption(Dict paramsObj, GenTableColumn treeParentColumn) {
        return TemplateEngineUtils.getTreeRootValue(paramsObj, treeParentColumn);
    }
}
