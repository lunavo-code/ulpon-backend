<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
<template>
    <el-select
        v-model="model"
        filterable
        remote
        reserve-keyword
        :popper-class="uniqueClass"
        :placeholder="placeholder"
        :clearable="clearable"
        :disabled="disabled"
        :loading="loading"
        :remote-method="handleRemoteSearch"
        @visible-change="handleVisibleChange"
        @change="handleChange"
    >
        <!-- 粘性表头：仅在有选项且开启了多列展示时呈现 -->
        <div v-if="${v.getBase().getBusinessNameLower()}Options.length > 0 && displayColumns.length > 1" class="${v.getBase().getBusinessNameLower()}-select-header">
      <span
          v-for="(col, index) in displayColumns"
          :key="col"
          class="header-cell"
          :style="{ flex: 1, marginRight: index < displayColumns.length - 1 ? '16px' : '0' }"
      >
        {{ getColumnLabel(col) }}
      </span>
        </div>

        <!-- 数据选项列表 -->
        <el-option
            v-for="item in ${v.getBase().getBusinessNameLower()}Options"
            :key="item.id"
            :label="String(item[displayColumns[0] as keyof ${v.getBase().getBusinessNameUpper()}VO] ?? '')"
            :value="item[valueKey as keyof ${v.getBase().getBusinessNameUpper()}VO]"
        >
            <div class="${v.getBase().getBusinessNameLower()}-select-option-row">
        <span
            v-for="(col, index) in displayColumns"
            :key="col"
            class="option-cell"
            :style="{ flex: 1, marginRight: index < displayColumns.length - 1 ? '16px' : '0' }"
        >
          {{ item[col as keyof ${v.getBase().getBusinessNameUpper()}VO] }}
        </span>
            </div>
        </el-option>

        <!-- 局部加载状态项，保证下拉框高度稳定且无闪烁 -->
        <el-option
            v-if="appendLoading"
            disabled
            label="正在加载下一页..."
            value="-1"
            style="text-align: center; color: var(--el-text-color-secondary);"
        />
    </el-select>
</template>

<script setup name="${v.getBase().getBusinessNameUpper()}Select" lang="ts">
    import { PropType } from 'vue';
    import { useDebounceFn } from '@vueuse/core';
    import { list${v.getBase().getBusinessNameUpper()} } from '@/api/${v.base.moduleName}/${v.base.businessNameLower}';
    import { ${v.getBase().getBusinessNameUpper()}VO } from '@/api/${v.base.moduleName}/${v.base.businessNameLower}/types';

    // 生成实例级唯一的 popper 样式类，防止多个下拉框事件冲突
    const uniqueClass = `${v.getBase().getBusinessNameLower()}-select-popper-${r'${Math.random().toString(36).substring(2, 9)}'}`;

    // 双向绑定绑定值 (Vue 3.4+ defineModel)
    const model = defineModel<string | number | undefined>();

    const props = defineProps({
        placeholder: {
            type: String,
            default: '请输入关键字搜索'
        },
        clearable: {
            type: Boolean,
            default: true
        },
        disabled: {
            type: Boolean,
            default: false
        },
        // 要展示的列属性列表（首个属性对应为输入框选中的回显文字）
        displayColumns: {
            type: Array as PropType<string[]>,
            default: () => ['value', 'testKey']
        },
        // 选中后提交给 v-model 的字段属性
        valueKey: {
            type: String,
            default: 'id'
        }
    });

    const emit = defineEmits(['change']);

    // 运行中下拉框里展示的列表数据
    const ${v.getBase().getBusinessNameLower()}Options = ref<${v.getBase().getBusinessNameUpper()}VO[]>([]);
    const loading = ref(false);
    const appendLoading = ref(false); // 局部滚动加载状态

    // 分页状态管理
    const pageNum = ref(1);
    const total = ref(0);
    const pageSize = 50; // 分页大小
    const currentQuery = ref('');

    // ==================== 双轨制数据缓存 ====================
    // 默认数据轨（持久缓存所有已加载的默认列表数据）
    const defaultOptions = ref<${v.getBase().getBusinessNameUpper()}VO[]>([]);
    const defaultPageNum = ref(1);
    const defaultTotal = ref(0);
    // ========================================================

    // 选项列名称映射配置
    const columnLabelsMap: Record<string, string> = {
        id: '主键',
        deptId: '部门ID',
        userId: '用户ID',
        orderNum: '排序号',
        testKey: 'key键',
        value: '值'
    };

    const getColumnLabel = (col: string) => columnLabelsMap[col] || col;

    const hasMore = computed(() => ${v.getBase().getBusinessNameLower()}Options.value.length < total.value);

    /** 异步加载下拉框选项 */
    const loadOptions = async (query?: string, isAppend: boolean = false) => {
        const isSearch = !!query;

        if (!isSearch) {
            // ------------------ 默认数据轨 (无搜索词) ------------------
            if (!isAppend) {
                // 命中缓存：非滚动翻页时，若已有已加载的默认列表缓存，则同步瞬间恢复，不发接口
                if (defaultOptions.value.length > 0) {
                    ${v.getBase().getBusinessNameLower()}Options.value = [...defaultOptions.value];
                    pageNum.value = defaultPageNum.value;
                    total.value = defaultTotal.value;
                    return;
                }
                pageNum.value = 1;
                ${v.getBase().getBusinessNameLower()}Options.value = [];
                loading.value = true;
            } else {
                appendLoading.value = true;
            }

            try {
                const res = await list${v.getBase().getBusinessNameUpper()}({
                    pageNum: pageNum.value,
                    pageSize: pageSize,
                    value: undefined
                });
                const rows = res.data?.rows || [];
                if (isAppend) {
                    defaultOptions.value.push(...rows);
                    defaultPageNum.value = pageNum.value;
                } else {
                    defaultOptions.value = rows;
                    defaultPageNum.value = 1;
                }
                defaultTotal.value = res.data?.total || 0;

                // 同步当前视图变量
                ${v.getBase().getBusinessNameLower()}Options.value = [...defaultOptions.value];
                total.value = defaultTotal.value;

                // 数据更新后绑定监听器，防止首次拉取时 DOM 节点不存在的竞态问题
                bindScrollListener();
            } catch (error) {
                console.error('加载测试单默认数据失败:', error);
                if (isAppend) {
                    pageNum.value--; // 请求失败时回滚页码
                }
            } finally {
                loading.value = false;
                appendLoading.value = false;
            }
        } else {
            // ------------------ 搜索数据轨 (有搜索词) ------------------
            if (!isAppend) {
                pageNum.value = 1;
                ${v.getBase().getBusinessNameLower()}Options.value = [];
                loading.value = true;
            } else {
                appendLoading.value = true;
            }

            try {
                const res = await list${v.getBase().getBusinessNameUpper()}({
                    pageNum: pageNum.value,
                    pageSize: pageSize,
                    value: query
                });
                const rows = res.data?.rows || [];
                if (isAppend) {
                    ${v.getBase().getBusinessNameLower()}Options.value.push(...rows);
                } else {
                    ${v.getBase().getBusinessNameLower()}Options.value = rows;
                }
                total.value = res.data?.total || 0;

                // 搜索数据更新后绑定监听器
                bindScrollListener();
            } catch (error) {
                console.error('加载测试单搜索数据失败:', error);
                if (isAppend) {
                    pageNum.value--; // 请求失败时回滚页码
                }
            } finally {
                loading.value = false;
                appendLoading.value = false;
            }
        }
    };

    /** 加载下一页数据 */
    const loadMore = async () => {
        if (loading.value || appendLoading.value || !hasMore.value) return;
        pageNum.value++;
        await loadOptions(currentQuery.value, true);
    };

    /** 监听下拉容器的滚动事件 */
    const handleScroll = (e: Event) => {
        if (loading.value || appendLoading.value || !hasMore.value) return;

        const target = e.target as HTMLElement;
        // 动态获取当前渲染的首个选项元素的高度，以自动适配任何 CSS/字体/缩放样式变化
        const firstOption = target.querySelector('.el-select-dropdown__item') as HTMLElement;
        const itemHeight = firstOption && firstOption.offsetHeight > 0 ? firstOption.offsetHeight : 34; // 兜底值为 34

        const scrolledItems = Math.floor(target.scrollTop / itemHeight);
        // 基于 pageSize 计算动态翻页阈值系数（当前设定为 0.5，即可剩余一半数据时触发拉取）
        const thresholdOffset = Math.floor(pageSize * 0.5);

        if (scrolledItems >= ${v.getBase().getBusinessNameLower()}Options.value.length - thresholdOffset) {
            loadMore();
        }
    };

    // 获取下拉滚动容器 DOM
    const getScrollWrap = () => document.querySelector(${r'`.${uniqueClass}'} .el-select-dropdown__wrap`);

    /** 动态绑定滚动监听器（解决懒加载 DOM 竞态问题，防重复监听） */
    const bindScrollListener = () => {
        nextTick(() => {
            const wrap = getScrollWrap();
            if (wrap) {
                wrap.removeEventListener('scroll', handleScroll);
                wrap.addEventListener('scroll', handleScroll);
            }
        });
    };

    /** 动态解绑滚动监听器 */
    const unbindScrollListener = () => {
        getScrollWrap()?.removeEventListener('scroll', handleScroll);
    };

    /** 下拉框显隐事件（控制懒加载及滚动监听） */
    const handleVisibleChange = (visible: boolean) => {
        if (visible) {
            // 懒加载：只有在当前没有数据时才进行首次拉取
            if (${v.getBase().getBusinessNameLower()}Options.value.length === 0) {
                loadOptions();
            }
            bindScrollListener();
        } else {
            unbindScrollListener();
        }
    };

    /** 远程搜索防抖处理 */
    const handleRemoteSearch = useDebounceFn((query: string) => {
        currentQuery.value = query;
        loadOptions(query);
    }, 300);

    const handleChange = (val: string | number | undefined) => emit('change', val);

    onMounted(() => {
        // 回显模式：只有在初始绑定了值的情况下，才在首屏自动拉取以翻译 label
        if (model.value) {
            loadOptions();
        }
    });

    onBeforeUnmount(() => {
        // 组件销毁前彻底清理事件监听器
        unbindScrollListener();
    });
</script>

<style scoped>
    /* 粘性表头样式 */
    .${v.getBase().getBusinessNameLower()}-select-header {
        position: sticky;
        top: 0;
        z-index: 10;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 8px 20px;
        background-color: var(--el-fill-color-light);
        border-bottom: 1px solid var(--el-border-color-lighter);
        font-weight: bold;
        color: var(--el-text-color-secondary);
        font-size: 13px;
        user-select: none;
    }

    /* 表头单元格 */
    .header-cell {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        text-align: left;
    }

    /* 选项行样式 */
    .${v.getBase().getBusinessNameLower()}-select-option-row {
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%;
    }

    /* 选项单元格 */
    .option-cell {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        text-align: left;
    }
</style>
