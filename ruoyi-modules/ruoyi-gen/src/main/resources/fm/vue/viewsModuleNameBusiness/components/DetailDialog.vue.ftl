<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
<template>
    <el-dialog v-model="dialog.visible" :title="dialog.title" width="500px" append-to-body>
        <el-form ref="${v.base.businessNameLower}FormRef" :model="form" :rules="rules" label-width="80px">
            <#list v.column.columns?filter(col -> (col.insert || col.edit) && !col.pk) as column>
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
        <template #footer>
            <div class="dialog-footer">
                <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup name="${v.base.businessNameUpper}DetailDialog" lang="ts">
    import {
        get${v.base.businessNameUpper},
        add${v.base.businessNameUpper},
        update${v.base.businessNameUpper}
    } from '@/api/${v.base.moduleName}/${v.base.businessNameLower}';

    import { ElForm } from 'element-plus';
    import { ${v.base.businessNameUpper}Form } from '@/api/${v.base.moduleName}/${v.base.businessNameLower}/types';
    import { useFormDialog } from '@/hooks/dialog/useFormDialog';
    import modal from '@/plugins/modal';
    <#if v.column.needDict>
    import { useDict } from '@/utils/dict';
    </#if>

    type ElFormInstance = InstanceType<typeof ElForm>;

    const { ${v.column.dictsNoSymbol} } = toRefs<any>(useDict(${v.column.dicts}));

    const emit = defineEmits(['success']);

    const buttonLoading = ref(false);
    const ${v.base.businessNameLower}FormRef = ref<ElFormInstance>();

    const initFormData: ${v.base.businessNameUpper}Form = {
        <#list v.column.columns?filter(col -> col.insert || col.edit) as column>
        <#if column.htmlType == "checkbox">
        ${column.javaField}: [],
        <#else>
        ${column.javaField}: undefined,
        </#if>
        </#list>
    };

    const data = reactive<PageData<${v.base.businessNameUpper}Form, any>>({
        form: { ...initFormData },
        queryParams: {},
        rules: {
            <#list v.column.columns?filter(col -> col.insert || col.edit) as column>
            <#if column.required>
            ${column.javaField}: [{ required: true, message: '${column.columnLabel}不能为空', trigger: <#if column.htmlType == "select" || column.htmlType == "radio" || column.htmlType == "switch" || column.htmlType == "inputNumber">"change"<#else>"blur"</#if> }],
            </#if>
            </#list>
        }
    });

    const { form, rules } = toRefs(data);

    const {
        dialog,
        resetForm: reset,
        showDialog,
        closeDialog
    } = useFormDialog({
        form,
        formRef: ${v.base.businessNameLower}FormRef,
        initialFormData: initFormData
    });

    /** 打开弹窗并根据是否有ID加载数据 */
    const open = async (id?: number | string) => {
        reset();
        if (id) {
            const res = await get${v.base.businessNameUpper}(id);
            Object.assign(form.value, res.data);
            showDialog('修改测试单');
        } else {
            showDialog('添加测试单');
        }
    };

    /** 取消按钮 */
    const cancel = () => {
        reset();
        closeDialog();
    };

    /** 提交表单 */
    const submitForm = () => {
        ${v.base.businessNameLower}FormRef.value?.validate(async (valid: boolean) => {
            if (!valid) return;
            buttonLoading.value = true;
            try {
                const isEdit = !!form.value.${v.column.pkColumn.javaField};
                isEdit ? await update${v.base.businessNameUpper}(form.value) : await add${v.base.businessNameUpper}(form.value);
                modal.msgSuccess(isEdit ? '修改成功' : '新增成功');
                closeDialog();
                emit('success');
            } finally {
                buttonLoading.value = false;
            }
        });
    };

    defineExpose({
        open
    });
</script>
