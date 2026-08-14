package org.dromara.gen.domain;

import cn.hutool.core.lang.Dict;
import org.dromara.gen.util.template.BaseTemplate;

import java.util.List;

/**
 * 模板渲染上下文。
 *
 * @param table     生成表信息
 * @param context   模板上下文
 * @param templates 待渲染模板
 */
public record RenderContext<T extends BaseTemplate>(GenTable table, Dict context, List<T> templates) {
}
