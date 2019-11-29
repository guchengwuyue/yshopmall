package co.yixiang.modules.shop.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.web.vo.Paging;
import co.yixiang.modules.shop.entity.YxArticle;
import co.yixiang.modules.shop.mapper.YxArticleMapper;
import co.yixiang.modules.shop.service.ArticleService;
import co.yixiang.modules.shop.web.param.YxArticleQueryParam;
import co.yixiang.modules.shop.web.vo.YxArticleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


/**
 * <p>
 * 文章管理表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-10-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl extends BaseServiceImpl<YxArticleMapper, YxArticle> implements ArticleService {

    @Autowired
    private YxArticleMapper yxArticleMapper;

    @Override
    public YxArticleQueryVo getYxArticleById(Serializable id) throws Exception{
        return yxArticleMapper.getYxArticleById(id);
    }

    @Override
    public Paging<YxArticleQueryVo> getYxArticlePageList(YxArticleQueryParam yxArticleQueryParam) throws Exception{
        Page page = setPageParam(yxArticleQueryParam,OrderItem.desc("create_time"));
        IPage<YxArticleQueryVo> iPage = yxArticleMapper.getYxArticlePageList(page,yxArticleQueryParam);
        return new Paging(iPage);
    }

}
