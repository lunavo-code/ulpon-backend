<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
<template>
    <div class="search-wrap">
        <el-card shadow="hover" class="search-panel" :class="{ 'is-collapsed': !showSearch }">
            <template slot="header">
                <div class="panel-heading search-panel-toggle" @click.stop="toggleSearch">
                    <div><h3>筛选条件</h3></div>
                </div>
            </template>
            <el-form ref="queryFormRef" :model="queryParams" :inline="true" class="query-form">

                <#list v.column.columns?filter(col -> col.query) as column>
                    <#if column.htmlType == "input" || column.htmlType == "textarea">
                        <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                            <el-input v-model="queryParams.${column.javaField}" placeholder="请输入${column.columnLabel}" clearable @keyup.enter="handleQuery"/>
                        </el-form-item>
                    <#elseif column.htmlType == "inputNumber">
                        <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                            <el-input-number v-model="queryParams.${column.javaField}" controls-position="right"/>
                        </el-form-item>
                    <#elseif (column.htmlType == "select" || column.htmlType == "radio") && column.dictType?has_content>
                        <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                            <el-select v-model="queryParams.${column.javaField}" placeholder="请选择${column.columnLabel}" clearable>
                                <el-option v-for="dict in ${column.dictType}" :key="dict.value" :label="dict.label" :value="dict.value"/>
                            </el-select>
                        </el-form-item>
                    <#elseif column.htmlType == "switch" && column.dictType?has_content>
                        <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                            <el-select v-model="queryParams.${column.javaField}" placeholder="请选择${column.columnLabel}" clearable>
                                <el-option v-for="dict in ${column.dictType}" :key="dict.value" :label="dict.label" :value="dict.value"/>
                            </el-select>
                        </el-form-item>
                    <#elseif column.htmlType == "switch">
                        <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                            <el-select v-model="queryParams.${column.javaField}" placeholder="请选择${column.columnLabel}" clearable>
                                <#if column.javaType == "Boolean">
                                    <el-option label="是" :value="true"/>
                                    <el-option label="否" :value="false"/>
                                <#elseif column.javaType == "Integer" || column.javaType == "Long">
                                    <el-option label="开启" :value="0"/>
                                    <el-option label="关闭" :value="1"/>
                                <#else>
                                    <el-option label="开启" value="0"/>
                                    <el-option label="关闭" value="1"/>
                                </#if>
                            </el-select>
                        </el-form-item>
                    <#elseif (column.htmlType == "select" || column.htmlType == "radio") && !(column.dictType?has_content)>
                        <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                            <el-select v-model="queryParams.${column.javaField}" placeholder="请选择${column.columnLabel}" clearable>
                                <el-option label="请选择字典生成" value=""/>
                            </el-select>
                        </el-form-item>
                    <#elseif column.htmlType == "datetime" && column.queryType != "BETWEEN">
                        <el-form-item label="${column.columnLabel}" prop="${column.javaField}">
                            <el-date-picker clearable
                                            v-model="queryParams.${column.javaField}"
                                            type="date"
                                            value-format="YYYY-MM-DD"
                                            placeholder="请选择${column.columnLabel}"
                            />
                        </el-form-item>
                    <#elseif column.htmlType == "datetime" && column.queryType == "BETWEEN" && column.query>
                        <el-form-item label="${column.columnLabel}" style="width: 308px">
                            <el-date-picker
                                v-model="dateRange${column.capJavaField}"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                type="daterange"
                                range-separator="-"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                                :default-time="[new Date(2000, 1, 1, 0, 0, 0), new Date(2000, 1, 1, 23, 59, 59)]"
                            />
                        </el-form-item>
                    </#if>
                </#list>
                <el-form-item>
                    <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                    <el-button icon="Refresh" @click="resetQuery">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script setup name="${v.base.businessNameUpper}SearchPanel" lang="ts">
    import {ElForm} from 'element-plus';
    import {${v.base.businessNameUpper}Query} from '@/api/${v.base.moduleName}/${v.base.businessNameLower}/types';
    import {useSearchReset} from '@/hooks/form/useSearchReset';

    type ElFormInstance = InstanceType < typeof ElForm >;

    const props = defineProps({
        showSearch: {
            type: Boolean,
            default: true
        },
        fields: {
            type: Array as() => string[],
    default: () => [
        <#list v.column.columns?filter(col -> col.query) as column>
        '${column.javaField}',
        </#list>
    ]
    }
    });

    const emit = defineEmits(['update:showSearch', 'search']);

    const queryFormRef = ref < ElFormInstance > ();
    const queryParams = ref < ${v.base.businessNameUpper}Query > ({
        pageNum: 1,
        pageSize: 10,
        <#list v.column.columns?filter(col -> col.query && col.htmlType != "datetime" || col.queryType != "BETWEEN") as column>
        ${column.javaField}: undefined,
        </#list>
    });

    const allFields = [
        <#list v.column.columns as column>
        {prop: '${column.javaField}', label: '${column.columnLabel}', index: ${column?index}},
        </#list>
    ];

    const activeFields = computed(() => {
        return props.fields
            .map(name => allFields.find(field => field.prop === name))
            .filter((field): field is typeof allFields[number] => !!field);
    });

    /** 搜索按钮操作 */
    const handleQuery = () => {
        emit('search', {...queryParams.value});
    };

    const {resetQuery} = useSearchReset({
        queryFormRef,
        queryParams,
        afterReset: handleQuery
    });

    const toggleSearch = () => {
        emit('update:showSearch', !props.showSearch);
    };
</script>
