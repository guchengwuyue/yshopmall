package co.yixiang.modules.wechat.rest;

import co.yixiang.modules.wechat.service.dto.YxArticleQueryCriteria;
import co.yixiang.aop.log.Log;
import co.yixiang.modules.wechat.domain.YxArticle;
import co.yixiang.modules.wechat.service.YxArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-10-07
*/
@Api(tags = "YxArticle管理")
@RestController
@RequestMapping("api")
public class YxArticleController {

    @Autowired
    private YxArticleService yxArticleService;

    @Log("查询YxArticle")
    @ApiOperation(value = "查询YxArticle")
    @GetMapping(value = "/yxArticle")
    @PreAuthorize("hasAnyRole('ADMIN','YXARTICLE_ALL','YXARTICLE_SELECT')")
    public ResponseEntity getYxArticles(YxArticleQueryCriteria criteria, Pageable pageable){
        System.out.println(yxArticleService.queryAll(criteria,pageable));
        return new ResponseEntity(yxArticleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxArticle")
    @ApiOperation(value = "新增YxArticle")
    @PostMapping(value = "/yxArticle")
    @PreAuthorize("hasAnyRole('ADMIN','YXARTICLE_ALL','YXARTICLE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxArticle resources){
        return new ResponseEntity(yxArticleService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxArticle")
    @ApiOperation(value = "修改YxArticle")
    @PutMapping(value = "/yxArticle")
    @PreAuthorize("hasAnyRole('ADMIN','YXARTICLE_ALL','YXARTICLE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxArticle resources){
        yxArticleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxArticle")
    @ApiOperation(value = "删除YxArticle")
    @DeleteMapping(value = "/yxArticle/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXARTICLE_ALL','YXARTICLE_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxArticleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "发布文章")
    @GetMapping(value = "/yxArticle/publish/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXARTICLE_ALL','YXARTICLE_DELETE')")
    public ResponseEntity publish(@PathVariable Integer id){
        //todo
        return new ResponseEntity(HttpStatus.OK);
    }

}