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
import org.dromara.gen.domain.veriables.GenVariable;
import org.dromara.gen.mapper.GenTableColumnMapper;
import org.dromara.gen.mapper.GenTableMapper;
import org.dromara.gen.util.template.BaseTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenCodeService {
    private final GenTableMapper tableMapper;
    private final GenTableColumnMapper tableColumnMapper;
    private final IGenTemplateService genTemplateService;

    private record RenderContext<T extends BaseTemplate>(GenTable table, Dict context, List<T> templates) {
    }

    void gen(Long tableId) {
        gen(Set.of(tableId));
    }

    void gen(String[] tableIds) {
        gen(Arrays.stream(tableIds).map(Long::parseLong).collect(Collectors.toSet()));
    }

    void gen(Set<Long> tableIds) {
        List<GenTable> tables = tableMapper.selectByIds(tableIds);
        if (tables == null || tables.size() < tableIds.size()) {
            throw new ServiceException("业务表不存在");
        }
        List<GenTableColumn> columns = tableColumnMapper.lambda()
            .in(GenTableColumn::getTableId, tableIds)
            .orderByAsc(GenTableColumn::getTableId)
            .orderByAsc(GenTableColumn::getSort)
            .list();
        Map<Long, List<GenTableColumn>> columnMap = StreamUtils.groupByKey(columns, GenTableColumn::getTableId);
        tables.forEach(table -> table.setColumns(columnMap.getOrDefault(table.getTableId(), new ArrayList<>())));

        tables.stream().map(this::buildRenderContext);
    }

    private RenderContext<BaseTemplate> buildRenderContext(GenTable table) {
        setMenuId(table);
        setPkColumn(table);
        // 构建变量上下文
        Dict veriableDict = Dict.of("v", new GenVariable(table));
        List<BaseTemplate> templates = genTemplateService.getTemplateList(table.getTplCategory(), table.getDataName(), table.getFrontendType());
        return new RenderContext<>(table, veriableDict, templates);
    }

    // 设置主键列
    private void setPkColumn(GenTable table) {
        if (CollUtil.isEmpty(table.getColumns())) {
            throw new ServiceException("表【" + table.getTableName() + "】字段为空，请检查表结构");
        }
        for (GenTableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (ObjectUtil.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().getFirst());
        }
    }

    // 生成菜单sql主键
    private void setMenuId(GenTable table) {
        List<Long> menuIds = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            menuIds.set(i, IdGeneratorUtil.nextLongId());
        }
        table.setMenuIds(menuIds);
    }

}
