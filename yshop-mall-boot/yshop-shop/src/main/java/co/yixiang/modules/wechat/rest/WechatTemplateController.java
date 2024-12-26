/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.wechat.rest;

import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.mp.domain.YxWechatTemplate;
import co.yixiang.modules.mp.service.YxWechatTemplateService;
import co.yixiang.modules.mp.service.dto.YxWechatTemplateDto;
import co.yixiang.modules.mp.service.dto.YxWechatTemplateQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author hupeng
* @date 2019-12-10
*/
@Api(tags = "商城:微信模板管理")
@RestController
@RequestMapping("/api/yxWechatTemplate")
@AllArgsConstructor
public class WechatTemplateController {


    private final YxWechatTemplateService yxWechatTemplateService;
    private final IGenerator generator;


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','yxWechatTemplate:list')")
    public void download(HttpServletResponse response, YxWechatTemplateQueryCriteria criteria) throws IOException {
        yxWechatTemplateService.download(generator.convert(yxWechatTemplateService.queryAll(criteria), YxWechatTemplateDto.class), response);
    }

    @GetMapping
    @ApiOperation("查询微信模板消息")
    @PreAuthorize("@el.check('admin','yxWechatTemplate:list')")
    public ResponseEntity<Object> getYxWechatTemplates(YxWechatTemplateQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxWechatTemplateService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ForbidSubmit
    @PostMapping
    @ApiOperation("新增微信模板消息")
    @PreAuthorize("@el.check('admin','yxWechatTemplate:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxWechatTemplate resources){
        return new ResponseEntity<>(yxWechatTemplateService.save(resources),HttpStatus.CREATED);
    }

    @ForbidSubmit
    @PutMapping
    @ApiOperation("修改微信模板消息")
    @PreAuthorize("@el.check('admin','yxWechatTemplate:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxWechatTemplate resources){
        yxWechatTemplateService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @ApiOperation("删除微信模板消息")
    @PreAuthorize("@el.check('admin','yxWechatTemplate:del')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAll(@PathVariable Integer id) {
        yxWechatTemplateService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
