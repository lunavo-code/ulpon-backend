<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
<template>
    <el-dialog v-model="dialog.visible" :title="dialog.title" width="800px" append-to-body>
        <el-form class="dialog-grid-form" ref="${v.base.businessNameLower}FormRef" :model="form" :rules="rules" label-width="80px">
            <#list v.column.columns as column>
                <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                    <#if column.htmlType == "input"><#--文本框 input-->
                        <el-input v-model="form.${column.javaField}" placeholder="请输入${column.columnLabel}"/>
                    <#elseif column.htmlType == "inputNumber"><#--数字输入 inputNumber-->
                        <el-input-number v-model="form.${column.javaField}" controls-position="right"/>
                    <#elseif column.htmlType == "textarea"><#--文本域 textarea-->
                        <el-input v-model="form.${column.javaField}" type="textarea" placeholder="请输入内容"/>
                    <#elseif column.htmlType == "select"><#--下拉框 select 字典-->
                        <#if column.dictType?has_content>
                            <dict-select v-model="form.${column.javaField}" dict-type="${column.dictType}" placeholder="请选择${column.columnLabel}"/>
                        <#else>
                            <dict-select v-model="form.${column.javaField}" placeholder="请选择字典生成或手动指定元素列表"/>
                        </#if>
                    <#elseif column.htmlType == "radio"><#--单选框 radio 字典-->
                        <#if column.dictType?has_content>
                            <dict-radio v-model="form.${column.javaField}" dict-type="${column.dictType}" placeholder="请选择${column.columnLabel}"/>
                        <#else>
                            <dict-radio v-model="form.${column.javaField}" placeholder="请选择字典生成或手动指定元素列表"/>
                        </#if>
                    <#elseif column.htmlType == "checkbox"><#--复选框 checkbox 字典-->
                        <#if column.dictType?has_content>
                            <dict-checkbox v-model="form.${column.javaField}" dict-type="${column.dictType}" placeholder="请选择${column.columnLabel}"/>
                        <#else>
                            <dict-checkbox v-model="form.${column.javaField}" placeholder="请选择字典生成或手动指定元素列表"/>
                        </#if>
                    <#elseif column.htmlType == "switch"><#--开关 switch 字典-->
                        <el-switch v-model="form.${column.javaField}" <#if column.javaType == "Integer" || column.javaType == "Long">:active-value="0" :inactive-value="1"</#if>/>
                    <#elseif column.htmlType == "datetime"><#--日期控件 datetime-->
                        <el-date-picker v-model="form.${column.javaField}" clearable type="datetime" value-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择${column.columnLabel}">
                        </el-date-picker>
                    <#elseif column.htmlType == "imageUpload"><#--图片上传 imageUpload-->
                        <image-upload v-model="form.${column.javaField}"/>
                    <#elseif column.htmlType == "fileUpload"><#--文件上传 fileUpload-->
                        <file-upload v-model="form.${column.javaField}"/>
                    <#elseif column.htmlType == "editor"><#--富文本控件 editor 这个比较特殊，原模板中没有prop属性-->
                        <editor v-model="form.${column.javaField}" :min-height="192"/>
                    </#if>
                </el-form-item>
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
    import {get${v.base.businessNameUpper}, add${v.base.businessNameUpper}, update${v.base.businessNameUpper}} from '@/api/${v.base.moduleName}/${v.base.businessNameLower}';
    import { ElForm } from 'element-plus';
    import { ${v.base.businessNameUpper}Form } from '@/api/${v.base.moduleName}/${v.base.businessNameLower}/types';
    import { useFormDialog } from '@/hooks/dialog/useFormDialog';
    import modal from '@/plugins/modal';

    type ElFormInstance = InstanceType<typeof ElForm>;

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
