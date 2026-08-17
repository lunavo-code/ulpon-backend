<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
<template>
    <el-card shadow="hover" class="table-panel">
        <template slot="header">
            <div class="toolbar-shell">
                <div class="table-heading"><h3>${v.base.functionName}列表</h3></div>
                <div class="toolbar-actions">
                    <el-button v-hasPermi="['${v.base.moduleName}:${v.base.businessNameLower}:add']" type="primary" plain icon="Plus" @click="handleAdd">
                        新增
                    </el-button>
                    <el-button v-hasPermi="['${v.base.moduleName}:${v.base.businessNameLower}:edit']" type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()">
                        修改
                    </el-button>
                    <el-button v-hasPermi="['${v.base.moduleName}:${v.base.businessNameLower}:remove']" type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()">
                        删除
                    </el-button>
                    <el-button v-hasPermi="['${v.base.moduleName}:${v.base.businessNameLower}:export']" type="warning" plain icon="Download" @click="handleExport">
                        导出
                    </el-button>
                    <right-toolbar :show-search="showSearch" :columns="columns" :search="false" @update:show-search="handleToggleSearch" @query-table="getList"/>
                </div>
            </div>
        </template>

        <el-table v-loading="loading" border class="data-table" :data="${v.base.businessNameLower}List" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center"/>
            <#list v.column.columns as column>
                <#if column.pk && column.list>
                    <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}"/>
                <#elseif v.option.enableStatus && v.option.statusField == column.javaField>
                    <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}">
                        <template #default="scope">
                            <el-switch
                                v-model="scope.row.${column.javaField}"
                                :active-value="${v.option.statusField}ActiveValue"
                                :inactive-value="${v.option.statusField}InactiveValue"
                                @change="handleStatusChange(scope.row)"
                            />
                        </template>
                    </el-table-column>
                <#elseif v.option.enableSort && v.option.sortField == column.javaField>
                    <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}" width="160">
                        <template #default="scope">
                            <#if column.javaType == "LocalDateTime">
                                <el-date-picker
                                    v-model="scope.row.${column.javaField}"
                                    type="datetime"
                                    value-format="YYYY-MM-DD HH:mm:ss"
                                    placeholder="选择${column.columnLabel}"
                                    @change="handleSortChange(scope.row)"
                                />
                            <#else>
                                <el-input-number v-model="scope.row.${column.javaField}" controls-position="right" :min="0" @change="handleSortChange(scope.row)"/>
                            </#if>
                        </template>
                    </el-table-column>
                <#elseif column.list && column.htmlType == "switch">
                    <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}" width="120">
                        <template #default="scope">
                            <el-switch
                                v-model="scope.row.${column.javaField}"
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
                                disabled
                            />
                        </template>
                    </el-table-column>
                <#elseif column.list && column.htmlType == "datetime">
                    <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}" width="180">
                        <template #default="scope">
                            <span>{{ parseTime(scope.row.${column.javaField}, '{y}-{m}-{d}') }}</span>
                        </template>
                    </el-table-column>
                <#elseif column.list && column.htmlType == "imageUpload">
                    <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}Url" width="100">
                        <template #default="scope">
                            <image-preview :src="scope.row.${column.javaField}Url" :width="50" :height="50"/>
                        </template>
                    </el-table-column>
                <#elseif column.list && column.dictColumn>
                    <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}">
                        <template #default="scope">
                            <#if column.htmlType == "checkbox">
                                <dict-tag :options="${column.dictType}" :value="scope.row.${column.javaField} ? scope.row.${column.javaField}.split(',') : []"/>
                            <#else>
                                <dict-tag :options="${column.dictType}" :value="scope.row.${column.javaField}"/>
                            </#if>
                        </template>
                    </el-table-column>
                <#elseif column.list && "" != column.javaField>
                    <el-table-column label="${column.columnLabel}" align="center" prop="${column.javaField}"/>
                </#if>
            </#list>
            <#if v.option.enableStatus && !v.option.statusColumn.list>
                <el-table-column label="${v.option.statusColumn.columnComment}" align="center" prop="${v.option.statusField}">
                    <template #default="scope">
                        <el-switch
                            v-model="scope.row.${v.option.statusField}"
                            <#if v.option.statusColumn.javaType == "Boolean">
                                :active-value="true"
                                :inactive-value="false"
                            <#elseif v.option.statusColumn.javaType == "Integer" || v.option.statusColumn.javaType == "Long">
                                :active-value="0"
                                :inactive-value="1"
                            <#else>
                                active-value="0"
                                inactive-value="1"
                            </#if>
                            @change="handleStatusChange(scope.row)"
                        />
                    </template>
                </el-table-column>
            </#if>
            <#if v.option.enableSort && !v.option.sortColumn.list>
                <el-table-column label="${v.option.sortColumn.columnComment}" align="center" prop="${v.option.sortField}" width="160">
                    <template #default="scope">
                        <#if v.option.sortColumn.javaType == "LocalDateTime">
                            <el-date-picker
                                v-model="scope.row.${v.option.sortField}"
                                type="datetime"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="选择${v.option.sortColumn.columnComment}"
                                @change="handleSortChange(scope.row)"
                            />
                        <#else>
                            <el-input-number v-model="scope.row.${v.option.sortField}" controls-position="right" :min="0" @change="handleSortChange(scope.row)"/>
                        </#if>
                    </template>
                </el-table-column>
            </#if>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template #default="scope">
                    <el-tooltip content="修改" placement="top">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['${v.base.moduleName}:${v.base.businessNameLower}:edit']"></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="top">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['${v.base.moduleName}:${v.base.businessNameLower}:remove']"></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList"/>
    </el-card>
</template>

<script setup name="${v.base.businessNameUpper}TablePanel" lang="ts">
    import {list${v.base.businessNameUpper}, del${v.base.businessNameUpper}} from '@/api/${v.base.moduleName}/${v.base.businessNameLower}';
    import {${v.base.businessNameUpper}VO, ${v.base.businessNameUpper}Query} from '@/api/${v.base.moduleName}/${v.base.businessNameLower}/types';
    import {useLoading} from '@/hooks/async/useLoading';
    import {useTableSelection} from '@/hooks/table/useTableSelection';
    import modal from '@/plugins/modal';
    import {download as requestDownload} from '@/utils/request';

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
            type: Array as() => string[],
    default: () => [
        <#list v.column.columns as column>
        <#if column.list>
        '${column.javaField}',
        </#if>
        </#list>
    ]
    }
    });

    const emit = defineEmits([
        'update:showSearch',
        'add',
        'edit'
    ]);

    const ${v.base.businessNameLower}List = ref<${v.base.businessNameUpper}VO[]> ([]);
    const total = ref(0);
    const {loading, withLoading} = useLoading(true);

    const allColumnsMeta = [
        <#list v.column.columns as column>
        {prop: '${column.javaField}', label: '${column.columnLabel}', index: ${column?index}},
        </#list>
    ];

    // 用于 right-toolbar 显隐列勾选框的数据源
    const columns = ref<TableFieldOption[]> ([]);

    // 监听可见列配置的变化，动态生成列显隐信息及其显示顺序
    watch(
        () => props.visibleColumns,
        (newVal) => {
            columns.value = newVal
                .map((name, index) => {
                    const meta = allColumnsMeta.find(col => col.prop === name);
                    return meta ? {key: index, label: meta.label, visible: true, prop: meta.prop} : null;
                })
                .filter((col): col is TableFieldOption => !!col);
        },
        {immediate: true}
    );

    // 过滤出当前显示的可视列
    const visibleColumnsList = computed(() => {
        return columns.value.filter(col => col.visible);
    });

    const queryParams = reactive < ${v.base.businessNameUpper}Query > ({
        pageNum: 1,
        pageSize: 10,
        <#list v.column.columns?filter(col -> col.query && col.htmlType != "datetime" || col.queryType != "BETWEEN") as column>
        ${column.javaField}: undefined,
        </#list>
    });


    const {ids, single, multiple, handleSelectionChange} = useTableSelection < ${v.base.businessNameUpper}VO > (item => item.${v.column.pkColumn.javaField});

    /** 查询${v.base.functionName}列表 */
    const getList = async () => {
        await withLoading(async () => {
            const res = await list${v.base.businessNameUpper}(queryParams);
            ${v.base.businessNameLower}List.value = res.data?.rows || [];
            total.value = res.data?.total || 0;
        });


        await withLoading(async () => {
            <#if v.column.needAddDateRange>
            let params = queryParams.value;
            <#list v.column.columns as column>
            <#if column.htmlType == "datetime" && column.queryType == "BETWEEN" && column.query>
            params = apply${column.capJavaField}DateRange(params);
            </#if>
            </#list>
            </#if>
            const res = await list${v.base.businessNameUpper}(params);
            ${v.base.businessNameLower}List.value = res.data?.rows || [];
            total.value = res.data?.total || 0;
        });
    };

    /** 外部应用查询过滤条件 */
    const applyFilters = (filters: Partial<${v.base.businessNameUpper}Query>) => {
        queryParams.pageNum = 1;
        // 清理先前的过滤参数，防止属性残留污染
        <#list v.column.columns?filter(col -> col.query) as column>
        queryParams.${column.javaField} = undefined;
        </#list>
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
        const _${v.column.pkColumn.javaField}s = row?.id ? [row.id] : ids.value;
        await modal.confirm('是否确认删除测试单编号为"' + targetIds + '"的数据项？');
        await withLoading(async () => {
            await del${v.base.businessNameUpper}(targetIds);
        });
        modal.msgSuccess('删除成功');
        await getList();
    };

    /** 导出按钮操作 */
    const handleExport = () => {
        requestDownload(
            '${v.base.businessNameLower}/${v.base.businessNameLower}/export',
            {
                ...queryParams
            },
            `${v.base.functionName}_${v.base.businessNameUpper}_${r'${new Date().getTime()}'}.xlsx`
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
