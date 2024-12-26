/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.rest;

import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.shop.domain.YxSystemStore;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreQueryCriteria;
import co.yixiang.utils.location.GetTencentLocationVO;
import co.yixiang.utils.location.LocationUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
* @author hupeng
* @date 2020-03-03
*/
@Api(tags = "门店管理")
@RestController
@RequestMapping("/api/yxSystemStore")
public class SystemStoreController {

    private final YxSystemStoreService yxSystemStoreService;
    private final IGenerator generator;
    public SystemStoreController(YxSystemStoreService yxSystemStoreService, IGenerator generator) {
        this.yxSystemStoreService = yxSystemStoreService;
        this.generator = generator;
    }


    @Log("所有门店")
    @ApiOperation("所有门店")
    @GetMapping(value = "/all")
    @PreAuthorize("@el.check('yxSystemStore:list')")
    public ResponseEntity<Object>  getAll(YxSystemStoreQueryCriteria criteria) {
        return new ResponseEntity<>(yxSystemStoreService.queryAll(criteria),HttpStatus.OK);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('yxSystemStore:list')")
    public void download(HttpServletResponse response, YxSystemStoreQueryCriteria criteria) throws IOException {
        yxSystemStoreService.download(generator.convert(yxSystemStoreService.queryAll(criteria), YxSystemStoreDto.class), response);
    }

    @GetMapping
    @Log("查询门店")
    @ApiOperation("查询门店")
    @PreAuthorize("@el.check('yxSystemStore:list')")
    public ResponseEntity<Object> getYxSystemStores(YxSystemStoreQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxSystemStoreService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping(value = "/getL")
    @Log("获取经纬度")
    @ApiOperation("获取经纬度")
    @PreAuthorize("@el.check('yxSystemStore:getl')")
    public ResponseEntity<GetTencentLocationVO> create(@Validated @RequestBody String jsonStr) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String addr = jsonObject.getString("addr");
        GetTencentLocationVO locationVO = LocationUtils.getLocation(addr);
        if(locationVO.getStatus()!=0){
            throw new BadRequestException(locationVO.getMessage());
        }
        return new ResponseEntity<>(locationVO, HttpStatus.CREATED);
    }

    @ForbidSubmit
    @PostMapping
    @Log("新增门店")
    @ApiOperation("新增门店")
    @PreAuthorize("@el.check('yxSystemStore:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxSystemStore resources){
        return new ResponseEntity<>(yxSystemStoreService.save(resources),HttpStatus.CREATED);
    }

    @ForbidSubmit
    @PutMapping
    @Log("修改门店")
    @ApiOperation("修改门店")
    @PreAuthorize("@el.check('yxSystemStore:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxSystemStore resources){
        yxSystemStoreService.saveOrUpdate(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除门店")
    @ApiOperation("删除门店")
    @PreAuthorize("@el.check('yxSystemStore:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        yxSystemStoreService.removeByIds(new ArrayList<>(Arrays.asList(ids)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
