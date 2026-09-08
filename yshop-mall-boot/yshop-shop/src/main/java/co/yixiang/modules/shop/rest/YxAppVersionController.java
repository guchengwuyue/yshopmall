/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.shop.rest;

import co.yixiang.domain.PageResult;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.shop.domain.YxAppVersion;
import co.yixiang.modules.shop.service.YxAppVersionService;
import co.yixiang.modules.shop.service.dto.YxAppVersionDto;
import co.yixiang.modules.shop.service.dto.YxAppVersionQueryCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
* @author lioncity
* @date 2020-12-09
*/
@AllArgsConstructor
@Tag(name = "app版本控制管理")
@RestController
@RequestMapping("/api/yxAppVersion")
public class YxAppVersionController {

    private final YxAppVersionService yxAppVersionService;
    private final IGenerator generator;


    @Log("导出数据")
    @Operation(summary = "导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','yxAppVersion:list')")
    public void download(HttpServletResponse response, YxAppVersionQueryCriteria criteria) throws IOException {
        yxAppVersionService.download(generator.convert(yxAppVersionService.queryAll(criteria), YxAppVersionDto.class), response);
    }

    @GetMapping
    @Log("查询app版本控制")
    @Operation(summary = "查询app版本控制")
    @PreAuthorize("@el.check('admin','yxAppVersion:list')")
    public ResponseEntity<PageResult<YxAppVersionDto>> getYxAppVersions(YxAppVersionQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxAppVersionService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ForbidSubmit
    @PostMapping
    @Log("新增app版本控制")
    @Operation(summary = "新增app版本控制")
    @PreAuthorize("@el.check('admin','yxAppVersion:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxAppVersion resources){
        return new ResponseEntity<>(yxAppVersionService.save(resources),HttpStatus.CREATED);
    }

    @ForbidSubmit
    @PutMapping
    @Log("修改app版本控制")
    @Operation(summary = "修改app版本控制")
    @PreAuthorize("@el.check('admin','yxAppVersion:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxAppVersion resources){
        yxAppVersionService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除app版本控制")
    @Operation(summary = "删除app版本控制")
    @PreAuthorize("@el.check('admin','yxAppVersion:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            yxAppVersionService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
