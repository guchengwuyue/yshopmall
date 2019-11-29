package co.yixiang.modules.shop.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.common.web.vo.Paging;
import co.yixiang.modules.shop.entity.YxArticle;
import co.yixiang.modules.shop.web.param.YxArticleQueryParam;
import co.yixiang.modules.shop.web.vo.YxArticleQueryVo;

import java.io.Serializable;

/**
 * <p>
 * 文章管理表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-10-02
 */
public interface ArticleService extends BaseService<YxArticle> {

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    YxArticleQueryVo getYxArticleById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     * @param yxArticleQueryParam
     * @return
     */
    Paging<YxArticleQueryVo> getYxArticlePageList(YxArticleQueryParam yxArticleQueryParam) throws Exception;

}
