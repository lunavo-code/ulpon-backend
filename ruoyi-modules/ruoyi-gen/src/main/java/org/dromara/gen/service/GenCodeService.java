package org.dromara.gen.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StreamUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.utils.IdGeneratorUtil;
import org.dromara.gen.constant.GenConstants;
import org.dromara.gen.domain.GenTable;
import org.dromara.gen.domain.GenTableColumn;
import org.dromara.gen.domain.RenderContext;
import org.dromara.gen.domain.veriables.GenVariable;
import org.dromara.gen.enums.TemplateCategoryEnum;
import org.dromara.gen.mapper.GenTableColumnMapper;
import org.dromara.gen.mapper.GenTableMapper;
import org.dromara.gen.util.template.BaseTemplate;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GenCodeService {
    private final GenTableMapper tableMapper;
    private final GenTableColumnMapper tableColumnMapper;

    private final Map<TemplateCategoryEnum, List<BaseTemplate>> templateMapperCache;

    private static final String BASE_BACKEND_WITH_DIR = "backend/src/main";

    private static final String BASE_FRONT_WITH_DIR = "frontend/src";

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
        GenVariable genVariable = new GenVariable(table);
        Dict veriableDict = Dict.of("v", genVariable);
        // 获取模板列表
        List<BaseTemplate> templates = getTemplateList(genVariable);
        return new RenderContext<>(table, veriableDict, templates);
    }

    /**
     * 设置主键列
     */
    private void setPkColumn(GenTable table) {
        if (CollUtil.isEmpty(table.getColumns()))
            throw new ServiceException("表【" + table.getTableName() + "】字段为空，请检查表结构");
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
        List<Long> menuIds = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            menuIds.add(IdGeneratorUtil.nextLongId());
        }
        table.setMenuIds(menuIds);
    }

    /**
     * 获取模板列表
     */
    private List<BaseTemplate> getTemplateList(GenVariable genVariable) {
        String tplCategory = genVariable.getBase().getTplCategory();
        String frontendType = genVariable.getBase().getFrontendType();
        String classNameUpper = genVariable.getBase().getClassNameUpper();
        String businessNameLower = genVariable.getBase().getBusinessNameLower();

        String packageName = genVariable.getBase().getPackageName().replace(".", "/");
        String moduleName = genVariable.getBase().getModuleName();

        List<BaseTemplate> templates = new ArrayList<>();
        // 后端源码模板
        templates.addAll(getTemplates(TemplateCategoryEnum.java, BASE_BACKEND_WITH_DIR + "/java/" + packageName, classNameUpper));
        // MyBatis MapperXML 模板
        templates.addAll(getTemplates(TemplateCategoryEnum.xml, BASE_BACKEND_WITH_DIR + "/resources/mapper", classNameUpper));
        // 数据库模板
        templates.addAll(getTemplates(TemplateCategoryEnum.sql, BASE_BACKEND_WITH_DIR + "/resources/sql", classNameUpper));
        // 前端 API 与类型模板

        // 前端页面
        switch (tplCategory + ":" + frontendType) {
            case "TPL_CRUD:react" -> templates.addAll(getTemplates(TemplateCategoryEnum.react, BASE_FRONT_WITH_DIR, "/" + moduleName + "/" + businessNameLower));
            case "TPL_TREE:react" -> templates.addAll(getTemplates(TemplateCategoryEnum.react_tree, BASE_FRONT_WITH_DIR, "/" + moduleName + "/" + businessNameLower));
            case "TPL_TREE:vue" -> templates.addAll(getTemplates(TemplateCategoryEnum.vue_tree, BASE_FRONT_WITH_DIR, "/" + moduleName + "/" + businessNameLower));
            default -> templates.addAll(getTemplates(TemplateCategoryEnum.vue, BASE_FRONT_WITH_DIR, "/" + moduleName + "/" + businessNameLower));
        }
        return templates;
    }

    private List<BaseTemplate> getTemplates(TemplateCategoryEnum templateCategoryEnum, String withDir, String business) {
        List<BaseTemplate> list = templateMapperCache.get(templateCategoryEnum);
        String s = GenConstants.TEMPLATE_ROOT_PATH + templateCategoryEnum;
        list.forEach(i -> {
            String pathName = i.getPathName();
            pathName = pathName.replace("ModuleNameBusiness", business).replace("business", business).replace(".ftl", StringUtils.EMPTY);
            i.setExportFilePath(withDir + pathName.substring(s.length() + 1));
        });
        return list;
    }
}
