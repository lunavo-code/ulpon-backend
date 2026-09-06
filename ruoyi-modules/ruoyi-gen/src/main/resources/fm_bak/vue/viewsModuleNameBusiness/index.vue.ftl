<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
<template>
    <div class="p-2 app-container ${v.base.moduleName}-${v.base.businessNameLower}-page">
        <!-- 筛选组件（自定义展示字段与显示顺序） -->
        <SearchPanel v-model:showSearch="showSearch" :fields="['value']" @search="handleSearch" />

        <!-- 列表及分页组件（自定义展示列与展示顺序） -->
        <TablePanel ref="tablePanelRef" v-model:showSearch="showSearch" :visible-columns="['value']"
                    @add="handleEdit()" @edit="handleEdit" />

        <!-- 新增/修改表单弹窗 -->
        <DetailDialog ref="detailDialogRef" @success="handleSaveSuccess" />
    </div>
</template>

<script setup name="${v.base.businessNameUpper}" lang="ts">
    import { ref } from 'vue';
    import SearchPanel from './components/SearchPanel.vue';
    import TablePanel from './components/TablePanel.vue';
    import DetailDialog from './components/DetailDialog.vue';

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
