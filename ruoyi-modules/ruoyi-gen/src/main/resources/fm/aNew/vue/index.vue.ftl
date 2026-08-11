<#-- @ftlvariable name="v" type="org.dromara.gen.domain.GenVariable" -->
<template>
    <div class="p-2 app-container ${v.moduleName}-${v.businessName}-page">
        <!-- 筛选组件（自定义展示字段与显示顺序） -->
        <${v.classNameUppercase}SearchPanel v-model:showSearch="showSearch" :fields="['value', 'testKey']" @search="handleSearch" />

        <!-- 列表及分页组件（自定义展示列与展示顺序） -->
        <${v.classNameUppercase}TablePanel ref="tablePanelRef" v-model:showSearch="showSearch" @add="handleEdit()" @edit="handleEdit" />

        <!-- 新增/修改表单弹窗 -->
        <${v.classNameUppercase}DetailDialog ref="detailDialogRef" @success="handleSaveSuccess" />
    </div>
</template>

<script setup name="${v.classNameUppercase}" lang="ts">
    import { ref } from 'vue';
    import ${v.classNameUppercase}SearchPanel from './components/${v.classNameUppercase}SearchPanel.vue';
    import ${v.classNameUppercase}TablePanel from './components/${v.classNameUppercase}TablePanel.vue';
    import ${v.classNameUppercase}DetailDialog from './components/${v.classNameUppercase}DetailDialog.vue';

    const tablePanelRef = ref<InstanceType<typeof TablePanel>>();
    const detailDialogRef = ref<InstanceType<typeof DetailDialog>>();
    const showSearch = ref(true);

    /** 筛选查询 */
    const handleSearch = (filters: any) => {
        tablePanelRef.value?.applyFilters(filters);
    };

    /** 新增/修改按钮操作 */
    const handleEdit = (id?: number | string) => {
        detailDialogRef.value?.open(id);
    };

    /** 保存成功后刷新列表 */
    const handleSaveSuccess = () => {
        tablePanelRef.value?.refresh();
    };
</script>
