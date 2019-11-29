package co.yixiang.modules.shop.web.controller;

import co.yixiang.common.api.ApiResult;
import co.yixiang.common.web.controller.BaseController;
import co.yixiang.common.web.param.IdParam;
import co.yixiang.common.web.vo.Paging;
import co.yixiang.modules.shop.entity.YxArticle;
import co.yixiang.modules.shop.service.ArticleService;
import co.yixiang.modules.shop.web.param.YxArticleQueryParam;
import co.yixiang.modules.shop.web.vo.YxArticleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 文章管理表 前端控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-10-02
 */
@Slf4j
@RestController
@RequestMapping("/yxArticle")
@Api("文章管理表 API")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService yxArticleService;

    /**
    * 添加文章管理表
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加YxArticle对象",notes = "添加文章管理表",response = ApiResult.class)
    public ApiResult<Boolean> addYxArticle(@Valid @RequestBody YxArticle yxArticle) throws Exception{
        boolean flag = yxArticleService.save(yxArticle);
        return ApiResult.result(flag);
    }

    /**
    * 修改文章管理表
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改YxArticle对象",notes = "修改文章管理表",response = ApiResult.class)
    public ApiResult<Boolean> updateYxArticle(@Valid @RequestBody YxArticle yxArticle) throws Exception{
        boolean flag = yxArticleService.updateById(yxArticle);
        return ApiResult.result(flag);
    }

    /**
    * 删除文章管理表
    */
    @PostMapping("/delete")
    @ApiOperation(value = "删除YxArticle对象",notes = "删除文章管理表",response = ApiResult.class)
    public ApiResult<Boolean> deleteYxArticle(@Valid @RequestBody IdParam idParam) throws Exception{
        boolean flag = yxArticleService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
    * 获取文章管理表
    */
    @PostMapping("/info")
    @ApiOperation(value = "获取YxArticle对象详情",notes = "查看文章管理表",response = YxArticleQueryVo.class)
    public ApiResult<YxArticleQueryVo> getYxArticle(@Valid @RequestBody IdParam idParam) throws Exception{
        YxArticleQueryVo yxArticleQueryVo = yxArticleService.getYxArticleById(idParam.getId());
        return ApiResult.ok(yxArticleQueryVo);
    }

    /**
     * 文章管理表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取YxArticle分页列表",notes = "文章管理表分页列表",response = YxArticleQueryVo.class)
    public ApiResult<Paging<YxArticleQueryVo>> getYxArticlePageList(@Valid @RequestBody(required = false) YxArticleQueryParam yxArticleQueryParam) throws Exception{
        Paging<YxArticleQueryVo> paging = yxArticleService.getYxArticlePageList(yxArticleQueryParam);
        return ApiResult.ok(paging);
    }

}

