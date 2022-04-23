/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.rest;

import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.NoRepeatSubmit;
import co.yixiang.modules.shop.domain.YxSystemStore;
import co.yixiang.modules.shop.domain.YxSystemStoreStaff;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.service.YxSystemStoreStaffService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffQueryCriteria;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author hupeng
 * @date 2020-03-22
 */
@Api(tags = "门店店员管理")
@RestController
@RequestMapping("/api/yxSystemStoreStaff")
public class SystemStoreStaffController {

    private final YxSystemStoreStaffService yxSystemStoreStaffService;
    private final YxSystemStoreService yxSystemStoreService;

    private final IGenerator generator;

    public SystemStoreStaffController(YxSystemStoreService yxSystemStoreService, YxSystemStoreStaffService yxSystemStoreStaffService, IGenerator generator) {
        this.yxSystemStoreService = yxSystemStoreService;
        this.yxSystemStoreStaffService = yxSystemStoreStaffService;
        this.generator = generator;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('yxSystemStoreStaff:list')")
    public void download(HttpServletResponse response, YxSystemStoreStaffQueryCriteria criteria) throws IOException {
        yxSystemStoreStaffService.download(generator.convert(yxSystemStoreStaffService.queryAll(criteria), YxSystemStoreStaffDto.class), response);
    }

    @GetMapping
    @Log("查询门店店员")
    @ApiOperation("查询门店店员")
    @PreAuthorize("@el.check('yxSystemStoreStaff:list')")
    public ResponseEntity<Object> getYxSystemStoreStaffs(YxSystemStoreStaffQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(yxSystemStoreStaffService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @NoRepeatSubmit
    @PostMapping
    @Log("新增门店店员")
    @ApiOperation("新增门店店员")
    @PreAuthorize("@el.check('yxSystemStoreStaff:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxSystemStoreStaff resources) {
        YxSystemStore systemStore = yxSystemStoreService.getOne(Wrappers.<YxSystemStore>lambdaQuery()
                .eq(YxSystemStore::getId, resources.getStoreId()));
        resources.setStoreName(systemStore.getName());
        return new ResponseEntity<>(yxSystemStoreStaffService.save(resources), HttpStatus.CREATED);
    }

    @NoRepeatSubmit
    @PutMapping
    @Log("修改门店店员")
    @ApiOperation("修改门店店员")
    @PreAuthorize("@el.check('yxSystemStoreStaff:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxSystemStoreStaff resources) {
        YxSystemStore systemStore = yxSystemStoreService.getOne(Wrappers.<YxSystemStore>lambdaQuery()
                .eq(YxSystemStore::getId, resources.getStoreId()));
        resources.setStoreName(systemStore.getName());
        yxSystemStoreStaffService.saveOrUpdate(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @NoRepeatSubmit
    @Log("删除门店店员")
    @ApiOperation("删除门店店员")
    @PreAuthorize("@el.check('yxSystemStoreStaff:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {

        yxSystemStoreStaffService.removeByIds(new ArrayList<>(Arrays.asList(ids)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
