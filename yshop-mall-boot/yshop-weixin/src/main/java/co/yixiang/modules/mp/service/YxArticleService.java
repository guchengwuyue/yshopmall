/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.mp.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.mp.service.dto.YxArticleDto;
import co.yixiang.modules.mp.service.dto.YxArticleQueryCriteria;
import co.yixiang.modules.mp.domain.YxArticle;
import co.yixiang.modules.mp.vo.YxArticleQueryVo;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxArticleService  extends BaseService<YxArticle>{

    /**
     * 获取文章列表
     * @param page 页码
     * @param limit 条数
     * @return List
     */
    List<YxArticleQueryVo> getList(int page, int limit);

    /**
     * 获取文章详情
     * @param id id
     * @return YxArticleQueryVo
     */
    YxArticleQueryVo getDetail(int id);

    void incVisitNum(int id);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxArticleQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxArticleDto>
    */
    List<YxArticle> queryAll(YxArticleQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxArticleDto> all, HttpServletResponse response) throws IOException;


}
