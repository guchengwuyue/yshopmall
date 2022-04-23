/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.gen.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.gen.domain.GenConfig;
import co.yixiang.modules.gen.service.GenConfigService;
import co.yixiang.modules.gen.service.mapper.GenConfigMapper;
import co.yixiang.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Zheng Jie
 * @date 2019-01-14
 */
@Service
//@CacheConfig(cacheNames = "genConfig")
public class GenConfigServiceImpl extends BaseServiceImpl<GenConfigMapper, GenConfig> implements GenConfigService {

    @Override
//    @Cacheable(key = "#p0")
    public GenConfig find(String tableName) {
        GenConfig genConfig = this.getOne(new LambdaQueryWrapper<GenConfig>().eq(GenConfig::getTableName, tableName));
        if (genConfig == null) {
            return new GenConfig(tableName);
        }
        return genConfig;
    }

    @Override
//    @CachePut(key = "#p0")
    public GenConfig update(String tableName, GenConfig genConfig) {
        // 如果 api 路径为空，则自动生成路径
        if (StringUtils.isBlank(genConfig.getApiPath())) {
            String separator = File.separator;
            String[] paths;
            String symbol = "\\";
            if (symbol.equals(separator)) {
                paths = genConfig.getPath().split("\\\\");
            } else {
                paths = genConfig.getPath().split(File.separator);
            }
            StringBuilder api = new StringBuilder();
            for (String path : paths) {
                api.append(path);
                api.append(separator);
                if ("src".equals(path)) {
                    api.append("api");
                    break;
                }
            }
            genConfig.setApiPath(api.toString());
        }
        this.saveOrUpdate(genConfig);
        return genConfig;
    }
}
