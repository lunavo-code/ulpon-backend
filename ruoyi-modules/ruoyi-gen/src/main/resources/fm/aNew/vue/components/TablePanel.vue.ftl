<#-- @ftlvariable name="v" type="org.dromara.gen.domain.GenVariable" -->
<template>
    <el-card shadow="hover" class="table-panel">
        <template #header>
            <div class="toolbar-shell">
                <div class="table-heading">
                    <h3>测试单列表</h3>
                </div>
                <div class="toolbar-actions">
                    <el-button v-hasPermi="['${v.moduleName}:${v.businessNameLower}:add']" type="primary" plain icon="Plus" @click="handleAdd">
                        新增
                    </el-button>
                    <el-button v-hasPermi="['${v.moduleName}:${v.businessNameLower}:edit']" type="success" plain icon="Edit" :disabled="single"
                               @click="handleUpdate()">
                        修改
                    </el-button>
                    <el-button v-hasPermi="['${v.moduleName}:${v.businessNameLower}:remove']" type="danger" plain icon="Delete" :disabled="multiple"
                               @click="handleDelete()">
                        删除
                    </el-button>
                    <el-button v-hasPermi="['${v.moduleName}:${v.businessNameLower}:export']" type="warning" plain icon="Download" @click="handleExport">
                        导出
                    </el-button>
                    <right-toolbar :show-search="showSearch" :columns="columns" :search="false"
                                   @update:show-search="handleToggleSearch"
                                   @query-table="getList"></right-toolbar>
                </div>
            </div>
        </template>

        <el-table v-loading="loading" border class="data-table" :data="${v.businessNameLower}List" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column v-for="col in visibleColumnsList" :key="col.prop" :label="col.label" :prop="col.prop" align="center" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template #default="scope">
                    <el-tooltip content="修改" placement="top">
                        <el-button v-hasPermi="['${v.moduleName}:${v.businessNameLower}:edit']" link type="primary" icon="Edit"
                                   @click="handleUpdate(scope.row)"></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="top">
                        <el-button v-hasPermi="['${v.moduleName}:${v.businessNameLower}:remove']" link type="primary" icon="Delete"
                                   @click="handleDelete(scope.row)"></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" :total="total"
                    @pagination="getList" />
    </el-card>
</template>

<script setup name="${v.businessNameUpper}TablePanel" lang="ts">
    import { ref, reactive, computed, onMounted, watch } from 'vue';
    import { list${v.businessNameUpper}, del${v.businessNameUpper} } from '@/api/${v.businessNameLower}/${v.businessNameLower}';
    import { ${v.businessNameUpper}VO, ${v.businessNameUpper}Query } from '@/api/${v.businessNameLower}/${v.businessNameLower}/types';
    import { useLoading } from '@/hooks/async/useLoading';
    import { useTableSelection } from '@/hooks/table/useTableSelection';
    import modal from '@/plugins/modal';
    import { download as requestDownload } from '@/utils/request';

    // 扩展字段属性接口
    interface TableFieldOption extends FieldOption {
        prop: string;
    }

    const props = defineProps({
        showSearch: {
            type: Boolean,
            default: true
        },
        visibleColumns: {
            type: Array as () => string[],
    default: () => ['id', 'deptId', 'userId', 'orderNum', 'testKey', 'value']
    }
    });

    const emit = defineEmits([
        'update:showSearch',
        'add',
        'edit'
    ]);

    const ${v.businessNameLower}List = ref<${v.businessNameUpper}VO[]>([]);
    const total = ref(0);
    const { loading, withLoading } = useLoading(true);

    const allColumnsMeta = [
        { prop: 'id', label: '主键' },
        { prop: 'deptId', label: '部门id' },
        { prop: 'userId', label: '用户id' },
        { prop: 'orderNum', label: '排序号' },
        { prop: 'testKey', label: 'key键' },
        { prop: 'value', label: '值' }
    ];

    // 用于 right-toolbar 显隐列勾选框的数据源
    const columns = ref<TableFieldOption[]>([]);

    // 监听可见列配置的变化，动态生成列显隐信息及其显示顺序
    watch(
        () => props.visibleColumns,
        (newVal) => {
            columns.value = newVal
                .map((name, index) => {
                    const meta = allColumnsMeta.find(col => col.prop === name);
                    return meta ? { key: index, label: meta.label, visible: true, prop: meta.prop } : null;
                })
                .filter((col): col is TableFieldOption => !!col);
        },
        { immediate: true }
    );

    // 过滤出当前显示的可视列
    const visibleColumnsList = computed(() => {
        return columns.value.filter(col => col.visible);
    });

    const queryParams = reactive<${v.businessNameUpper}Query>({
        pageNum: 1,
        pageSize: 10,
        deptId: undefined,
        userId: undefined,
        orderNum: undefined,
        testKey: undefined,
        value: undefined
    });



    const { ids, single, multiple, handleSelectionChange } = useTableSelection<${v.businessNameUpper}VO>(item => item.id);

    /** 查询测试单列表 */
    const getList = async () => {
        await withLoading(async () => {
            const res = await list${v.businessNameUpper}(queryParams);
            ${v.businessNameLower}List.value = res.data?.rows || [];
            total.value = res.data?.total || 0;
        });
    };

    /** 外部应用查询过滤条件 */
    const applyFilters = (filters: Partial<${v.businessNameUpper}Query>) => {
        queryParams.pageNum = 1;
        // 清理先前的过滤参数，防止属性残留污染
        queryParams.deptId = undefined;
        queryParams.userId = undefined;
        queryParams.orderNum = undefined;
        queryParams.testKey = undefined;
        queryParams.value = undefined;

        // 动态合并最新的过滤条件
        Object.assign(queryParams, filters);
        getList();
    };

    /** 外部指示刷新列表 */
    const refresh = () => {
        getList();
    };

    const handleToggleSearch = (val: boolean) => {
        emit('update:showSearch', val);
    };

    const handleAdd = () => {
        emit('add');
    };

    const handleUpdate = (row?: any) => {
        const id = row?.id || ids.value[0];
        emit('edit', id);
    };

    /** 删除按钮操作 */
    const handleDelete = async (row?: any) => {
        const targetIds = row?.id ? [row.id] : ids.value;
        await modal.confirm('是否确认删除测试单编号为"' + targetIds + '"的数据项？');
        await withLoading(async () => {
            await del${v.businessNameUpper}(targetIds);
        });
        modal.msgSuccess('删除成功');
        await getList();
    };

    /** 导出按钮操作 */
    const handleExport = () => {
        requestDownload(
            '${v.businessNameLower}/${v.businessNameLower}/export',
            {
                ...queryParams
            },
            `${v.businessNameLower}_${new Date().getTime()}.xlsx`
        );
    };

    onMounted(() => {
        getList();
    });

    defineExpose({
        applyFilters,
        refresh
    });
</script>
