/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.quartz.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.quartz.domain.QuartzLog;
import co.yixiang.modules.quartz.service.dto.QuartzLogDto;
import co.yixiang.modules.quartz.service.dto.QuartzLogQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author hupeng
 * @date 2020-05-13
 */
public interface QuartzLogService extends BaseService<QuartzLog> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(QuartzLogQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<QuartzLogDto>
     */
    List<QuartzLog> queryAll(QuartzLogQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<QuartzLogDto> all, HttpServletResponse response) throws IOException;
}
