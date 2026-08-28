<!-- @suppress EmptyTag -->
<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
<el-table v-loading="loading" border class="data-table" :data="${v.base.businessNameLower}List" @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55" align="center"/>
    <el-table-column label="序号" align="center" type="index" width="60"/>
    <#list v.column.columns as column>
        <#if column.isPk && column.isList>
            <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}"/>
        <#elseif column.javaField == v.option.statusField && v.option.enableStatus>
            <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}">
                <template #default="scope">
                    <el-switch
                        v-model="scope.row.${column.javaField}"
                        :active-value="${statusField}ActiveValue"
                        :inactive-value="${statusField}InactiveValue"
                        @change="handleStatusChange(scope.row)"
                    />
                </template>
            </el-table-column>
        </#if>
    <#-- 文本框 input-->
    <#-- 数字输入 inputNumber-->

    <#-- 文本域 textarea-->

    <#-- 下拉框 select-->

    <#-- 单选框 radio-->

    <#-- 复选框 checkbox-->

    <#-- 开关 switch-->

    <#-- 日期控件 datetime-->

    <#-- 图片上传 imageUpload-->

    <#-- 文件上传 fileUpload-->

    <#-- 富文本控件 editor-->

    </#list>
</el-table>
