package co.yixiang.mp.controller;

import cn.hutool.core.util.StrUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.mp.domain.YxWechatTemplate;
import co.yixiang.mp.service.YxWechatTemplateService;
import co.yixiang.mp.service.dto.YxWechatTemplateQueryCriteria;
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
* @author xuwenbo
* @date 2019-12-10
*/
@Api(tags = "商城:微信模板管理")
@RestController
@RequestMapping("api")
public class WechatTemplateController {

    private final YxWechatTemplateService yxWechatTemplateService;

    public WechatTemplateController(YxWechatTemplateService yxWechatTemplateService) {
        this.yxWechatTemplateService = yxWechatTemplateService;
    }

    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxWechatTemplate")
    @PreAuthorize("@el.check('admin','YXWECHATTEMPLATE_ALL','YXWECHATTEMPLATE_SELECT')")
    public ResponseEntity getYxWechatTemplates(YxWechatTemplateQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxWechatTemplateService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ApiOperation(value = "新增")
    @PostMapping(value = "/yxWechatTemplate")
    @PreAuthorize("@el.check('admin','YXWECHATTEMPLATE_ALL','YXWECHATTEMPLATE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxWechatTemplate resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        return new ResponseEntity(yxWechatTemplateService.create(resources),HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/yxWechatTemplate")
    @PreAuthorize("@el.check('admin','YXWECHATTEMPLATE_ALL','YXWECHATTEMPLATE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxWechatTemplate resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxWechatTemplateService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxWechatTemplate/{id}")
    @PreAuthorize("@el.check('admin','YXWECHATTEMPLATE_ALL','YXWECHATTEMPLATE_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxWechatTemplateService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}