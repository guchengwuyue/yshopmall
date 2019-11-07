package co.yixiang.modules.shop.mapper;

import co.yixiang.modules.shop.entity.YxArticle;
import co.yixiang.modules.shop.web.param.YxArticleQueryParam;
import co.yixiang.modules.shop.web.vo.YxArticleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 文章管理表 Mapper 接口
 * </p>
 *
 * @author hupeng
 * @since 2019-10-02
 */
@Repository
public interface YxArticleMapper extends BaseMapper<YxArticle> {

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    YxArticleQueryVo getYxArticleById(Serializable id);

    /**
     * 获取分页对象
     * @param page
     * @param yxArticleQueryParam
     * @return
     */
    IPage<YxArticleQueryVo> getYxArticlePageList(@Param("page") Page page, @Param("param") YxArticleQueryParam yxArticleQueryParam);

}
