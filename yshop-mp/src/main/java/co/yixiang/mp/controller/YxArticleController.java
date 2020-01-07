package co.yixiang.mp.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.mp.domain.YxArticle;
import co.yixiang.mp.service.YxArticleService;
import co.yixiang.mp.service.dto.YxArticleDTO;
import co.yixiang.mp.service.dto.YxArticleQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "查询YxArticle")
    @GetMapping(value = "/yxArticle")
    @PreAuthorize("@el.check('admin','YXARTICLE_ALL','YXARTICLE_SELECT')")
    public ResponseEntity getYxArticles(YxArticleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxArticleService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    @ApiOperation(value = "新增YxArticle")
    @PostMapping(value = "/yxArticle")
    @PreAuthorize("@el.check('admin','YXARTICLE_ALL','YXARTICLE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxArticle resources){
        resources.setAddTime(DateUtil.now());
        return new ResponseEntity(yxArticleService.create(resources),HttpStatus.CREATED);
    }


    @ApiOperation(value = "修改YxArticle")
    @PutMapping(value = "/yxArticle")
    @PreAuthorize("@el.check('admin','YXARTICLE_ALL','YXARTICLE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxArticle resources){
        yxArticleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "删除YxArticle")
    @DeleteMapping(value = "/yxArticle/{id}")
    @PreAuthorize("@el.check('admin','YXARTICLE_ALL','YXARTICLE_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxArticleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "发布文章")
    @GetMapping(value = "/yxArticle/publish/{id}")
    @PreAuthorize("@el.check('admin','YXARTICLE_ALL','YXARTICLE_DELETE')")
    public ResponseEntity publish(@PathVariable Integer id)  throws Exception{
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        YxArticleDTO yxArticleDTO= yxArticleService.findById(id);
        yxArticleService.uploadNews(yxArticleDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}