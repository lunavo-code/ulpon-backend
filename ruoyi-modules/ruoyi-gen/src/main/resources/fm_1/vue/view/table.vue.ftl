<#--<!-- @suppress EmptyTag &ndash;&gt;-->
<#--&lt;#&ndash; @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" &ndash;&gt;-->
<#--<el-table v-loading="loading" border class="data-table" :data="${v.base.businessNameLower}List" @selection-change="handleSelectionChange">-->
<#--    <el-table-column type="selection" width="55" align="center"/>-->
<#--    <el-table-column label="序号" align="center" type="index" width="60"/>-->
<#--    <#list v.column.columns as column>-->
<#--        <#if column.isPk && column.isList>-->
<#--            <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}"/>-->
<#--        <#elseif column.javaField == v.option.statusField && v.option.enableStatus>-->
<#--            <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}">-->
<#--                <template #default="scope">-->
<#--                    <el-switch-->
<#--                        v-model="scope.row.${column.javaField}"-->
<#--                        :active-value="${statusField}ActiveValue"-->
<#--                        :inactive-value="${statusField}InactiveValue"-->
<#--                        @change="handleStatusChange(scope.row)"-->
<#--                    />-->
<#--                </template>-->
<#--            </el-table-column>-->
<#--        </#if>-->
<#--    &lt;#&ndash; 文本框 input&ndash;&gt;-->
<#--    &lt;#&ndash; 数字输入 inputNumber&ndash;&gt;-->

<#--    &lt;#&ndash; 文本域 textarea&ndash;&gt;-->

<#--    &lt;#&ndash; 下拉框 select&ndash;&gt;-->

<#--    &lt;#&ndash; 单选框 radio&ndash;&gt;-->

<#--    &lt;#&ndash; 复选框 checkbox&ndash;&gt;-->

<#--    &lt;#&ndash; 开关 switch&ndash;&gt;-->

<#--    &lt;#&ndash; 日期控件 datetime&ndash;&gt;-->

<#--    &lt;#&ndash; 图片上传 imageUpload&ndash;&gt;-->

<#--    &lt;#&ndash; 文件上传 fileUpload&ndash;&gt;-->

<#--    &lt;#&ndash; 富文本控件 editor&ndash;&gt;-->

<#--    </#list>-->
<#--</el-table>-->
