/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.NoRepeatSubmit;
import co.yixiang.modules.shop.domain.YxSystemStore;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreQueryCriteria;
import co.yixiang.modules.mp.config.ShopKeyUtils;
import co.yixiang.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    @PreAuthorize("hasAnyRole('yxSystemStore:list')")
    public ResponseEntity<Object> getAll(YxSystemStoreQueryCriteria criteria) {
        return new ResponseEntity<>(yxSystemStoreService.queryAll(criteria), HttpStatus.OK);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("hasAnyRole('yxSystemStore:list')")
    public void download(HttpServletResponse response, YxSystemStoreQueryCriteria criteria) throws IOException {
        yxSystemStoreService.download(generator.convert(yxSystemStoreService.queryAll(criteria), YxSystemStoreDto.class), response);
    }

    @GetMapping
    @Log("查询门店")
    @ApiOperation("查询门店")
    @PreAuthorize("hasAnyRole('yxSystemStore:list')")
    public ResponseEntity<Object> getYxSystemStores(YxSystemStoreQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(yxSystemStoreService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/getL")
    @Log("获取经纬度")
    @ApiOperation("获取经纬度")
    @PreAuthorize("hasAnyRole('yxSystemStore:getl')")
    public ResponseEntity<Object> create(@Validated @RequestBody String jsonStr) {
        String key = RedisUtil.get(ShopKeyUtils.getTengXunMapKey());
        if (StrUtil.isBlank(key)) {
            throw new BadRequestException("请先配置腾讯地图key");
        }
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String addr = jsonObject.getString("addr");
        String url = StrUtil.format("?address={}&key={}", addr, key);
        String json = HttpUtil.get(ShopConstants.QQ_MAP_URL + url);
        return new ResponseEntity<>(json, HttpStatus.CREATED);
    }

    @NoRepeatSubmit
    @PostMapping
    @Log("新增门店")
    @ApiOperation("新增门店")
    @PreAuthorize("hasAnyRole('yxSystemStore:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxSystemStore resources) {
        return new ResponseEntity<>(yxSystemStoreService.save(resources), HttpStatus.CREATED);
    }

    @NoRepeatSubmit
    @PutMapping
    @Log("修改门店")
    @ApiOperation("修改门店")
    @PreAuthorize("hasAnyRole('yxSystemStore:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxSystemStore resources) {

        yxSystemStoreService.saveOrUpdate(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @NoRepeatSubmit
    @Log("删除门店")
    @ApiOperation("删除门店")
    @PreAuthorize("hasAnyRole('yxSystemStore:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {

        yxSystemStoreService.removeByIds(new ArrayList<>(Arrays.asList(ids)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
