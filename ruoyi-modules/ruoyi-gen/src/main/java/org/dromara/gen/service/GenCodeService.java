package org.dromara.gen.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StreamUtils;
import org.dromara.common.mybatis.utils.IdGeneratorUtil;
import org.dromara.gen.domain.GenTable;
import org.dromara.gen.domain.GenTableColumn;
import org.dromara.gen.domain.RenderContext;
import org.dromara.gen.domain.veriables.GenVariable;
import org.dromara.gen.enums.TemplateCategoryEnum;
import org.dromara.gen.mapper.GenTableColumnMapper;
import org.dromara.gen.mapper.GenTableMapper;
import org.dromara.gen.util.template.BaseTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenCodeService {
    private final GenTableMapper tableMapper;
    private final GenTableColumnMapper tableColumnMapper;

    private final Map<TemplateCategoryEnum, List<BaseTemplate>> templateMapperCache;

    public GenTable getGenTable(Long tableId) {
        return getGenTable(Set.of(tableId)).getFirst();
    }

    public List<GenTable> getGenTable(Set<Long> tableIds) {
        List<GenTable> tables = tableMapper.selectByIds(tableIds);
        if (tables == null || tables.size() < tableIds.size()) throw new ServiceException("业务表不存在");
        List<GenTableColumn> columns = tableColumnMapper.lambda()
            .in(GenTableColumn::getTableId, tableIds)
            .orderByAsc(GenTableColumn::getTableId)
            .orderByAsc(GenTableColumn::getSort)
            .list();
        Map<Long, List<GenTableColumn>> columnMap = StreamUtils.groupByKey(columns, GenTableColumn::getTableId);
        tables.forEach(table -> table.setColumns(columnMap.getOrDefault(table.getTableId(), new ArrayList<>())));
        return tables;
    }

    public RenderContext<BaseTemplate> gen(Long tableId) {
        GenTable table = getGenTable(tableId);
        return buildRenderContext(table);
    }

    private RenderContext<BaseTemplate> buildRenderContext(GenTable table) {
        // 生成菜单sql主键
        setMenuId(table);
        // 设置主键列
        setPkColumn(table);
        // 构建变量上下文
        Dict veriableDict = Dict.of("v", new GenVariable(table));
        // 获取模板列表
        List<BaseTemplate> templates = getTemplateList(table.getTplCategory(), table.getDataName(), table.getFrontendType());
        return new RenderContext<>(table, veriableDict, templates);
    }

    /**
     * 设置主键列
     */
    private void setPkColumn(GenTable table) {
        if (CollUtil.isEmpty(table.getColumns())) throw new ServiceException("表【" + table.getTableName() + "】字段为空，请检查表结构");
        for (GenTableColumn column : table.getColumns()) {
            if (!column.isPk()) continue;
            table.setPkColumn(column);
            break;
        }
        if (ObjectUtil.isNull(table.getPkColumn())) table.setPkColumn(table.getColumns().getFirst());
    }

    /**
     * 生成菜单sql主键
     */
    private void setMenuId(GenTable table) {
        List<Long> menuIds = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            menuIds.set(i, IdGeneratorUtil.nextLongId());
        }
        table.setMenuIds(menuIds);
    }

    /**
     * 获取模板列表
     */
    private List<BaseTemplate> getTemplateList(String tplCategory, String dsName, String frontendType) {
        List<BaseTemplate> templates = new ArrayList<>();
        // 后端源码模板
        templates.addAll(templateMapperCache.get(TemplateCategoryEnum.java));
        // MyBatis MapperXML 模板
        templates.addAll(templateMapperCache.get(TemplateCategoryEnum.xml));
        // 前端 API 与类型模板
        templates.addAll(templateMapperCache.get(TemplateCategoryEnum.vue));
        templates.addAll(templateMapperCache.get(TemplateCategoryEnum.react));
        templates.addAll(templateMapperCache.get(TemplateCategoryEnum.vue_tree));
        templates.addAll(templateMapperCache.get(TemplateCategoryEnum.react_tree));
        // 数据库模板
        switch (dsName) {
            case "postgresql" -> templates.addAll(templateMapperCache.get(TemplateCategoryEnum.sql_postgre));
            case "oracle" -> templates.addAll(templateMapperCache.get(TemplateCategoryEnum.sql_oracle));
            case "sqlserver" -> templates.addAll(templateMapperCache.get(TemplateCategoryEnum.sql_sqlserver));
            default -> templates.addAll(templateMapperCache.get(TemplateCategoryEnum.sql_mysql));
        }

        switch (tplCategory + ":" + frontendType) {
            case "TPL_CRUD:react" -> templates.addAll(templateMapperCache.get(TemplateCategoryEnum.react));
            case "TPL_TREE:react" -> templates.addAll(templateMapperCache.get(TemplateCategoryEnum.react_tree));
            case "TPL_TREE:vue" -> templates.addAll(templateMapperCache.get(TemplateCategoryEnum.vue_tree));
            default -> templates.addAll(templateMapperCache.get(TemplateCategoryEnum.vue));
        }
        return templates;
    }
}
