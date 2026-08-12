package org.dromara.gen.service;

import com.baomidou.mybatisplus.spring.service.IService;
import org.dromara.gen.domain.GenTemplate;
import org.dromara.gen.util.template.DBTemplate;

import java.util.List;

/**
 * 业务 服务层
 *
 * @author Lion Li
 */
public interface IGenTemplateService extends IService<GenTemplate> {
    List<DBTemplate> getTemplateList(String tplCategory, String dsName, String frontendType);
}
