<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
<el-form ref="${v.businessNameLower}FormRef" :model="form" :rules="rules" label-width="80px">
    <#list v.columns?filter(col -> (col.insert || col.edit) && !col.pk) as column>
        <#if column.htmlType == "input">
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-input v-model="form.${column.javaField}" placeholder="请输入${column.columnLabel}" />
            </el-form-item>
        <#elseif column.htmlType == "inputNumber">
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-input-number v-model="form.${column.javaField}" controls-position="right" />
            </el-form-item>
        <#elseif column.htmlType == "imageUpload">
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <image-upload v-model="form.${column.javaField}"/>
            </el-form-item>
        <#elseif column.htmlType == "fileUpload">
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <file-upload v-model="form.${column.javaField}"/>
            </el-form-item>
        <#elseif column.htmlType == "editor">
            <el-form-item label="${column.columnLabel}">
                <editor v-model="form.${column.javaField}" :min-height="192"/>
            </el-form-item>
        <#elseif column.htmlType == "select" && column.dictType?has_content>
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-select v-model="form.${column.javaField}" placeholder="请选择${column.columnLabel}">
                    <el-option
                        v-for="dict in ${column.dictType}"
                        :key="dict.value"
                        :label="dict.label"
                        <#if column.javaType == "Integer" || column.javaType == "Long">
                            :value="parseInt(dict.value)"
                        <#else>
                            :value="dict.value"
                        </#if>
                    ></el-option>
                </el-select>
            </el-form-item>
        <#elseif column.htmlType == "select" && !(column.dictType?has_content)>
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-select v-model="form.${column.javaField}" placeholder="请选择${column.columnLabel}">
                    <el-option label="请选择字典生成" value=""></el-option>
                </el-select>
            </el-form-item>
        <#elseif column.htmlType == "checkbox" && column.dictType?has_content>
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-checkbox-group v-model="form.${column.javaField}">
                    <el-checkbox
                        v-for="dict in ${column.dictType}"
                        :key="dict.value"
                        :label="dict.value">
                        {{dict.label}}
                    </el-checkbox>
                </el-checkbox-group>
            </el-form-item>
        <#elseif column.htmlType == "checkbox" && !(column.dictType?has_content)>
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-checkbox-group v-model="form.${column.javaField}">
                    <el-checkbox>请选择字典生成</el-checkbox>
                </el-checkbox-group>
            </el-form-item>
        <#elseif column.htmlType == "radio" && column.dictType?has_content>
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-radio-group v-model="form.${column.javaField}">
                    <el-radio
                        v-for="dict in ${column.dictType}"
                        :key="dict.value"
                        <#if column.javaType == "Integer" || column.javaType == "Long">
                            :value="parseInt(dict.value)"
                        <#else>
                            :value="dict.value"
                        </#if>
                    >{{dict.label}}</el-radio>
                </el-radio-group>
            </el-form-item>
        <#elseif column.htmlType == "radio" && !(column.dictType?has_content)>
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-radio-group v-model="form.${column.javaField}">
                    <el-radio value="1">请选择字典生成</el-radio>
                </el-radio-group>
            </el-form-item>
        <#elseif column.htmlType == "switch">
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-switch
                    v-model="form.${column.javaField}"
                    <#if column.javaType == "Boolean">
                        :active-value="true"
                        :inactive-value="false"
                    <#elseif column.javaType == "Integer" || column.javaType == "Long">
                        :active-value="0"
                        :inactive-value="1"
                    <#else>
                        active-value="0"
                        inactive-value="1"
                    </#if>
                />
            </el-form-item>
        <#elseif column.htmlType == "datetime">
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-date-picker clearable
                                v-model="form.${column.javaField}"
                                type="datetime"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="请选择${column.columnLabel}">
                </el-date-picker>
            </el-form-item>
        <#elseif column.htmlType == "textarea">
            <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                <el-input v-model="form.${column.javaField}" type="textarea" placeholder="请输入内容" />
            </el-form-item>
        </#if>
    </#list>
</el-form>
<template slot="footer">
    <div class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
    </div>
</template>
