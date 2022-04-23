/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.constant.ShopConstants;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.NoRepeatSubmit;
import co.yixiang.modules.shop.domain.YxSystemGroupData;
import co.yixiang.modules.shop.service.YxSystemGroupDataService;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataQueryCriteria;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author hupeng
 * @date 2019-10-18
 */
@Api(tags = "商城:数据配置管理")
@RestController
@RequestMapping("api")
public class SystemGroupDataController {

    private final YxSystemGroupDataService yxSystemGroupDataService;

    public SystemGroupDataController(YxSystemGroupDataService yxSystemGroupDataService) {
        this.yxSystemGroupDataService = yxSystemGroupDataService;
    }

    @Log("查询数据配置")
    @ApiOperation(value = "查询数据配置")
    @GetMapping(value = "/yxSystemGroupData")
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMGROUPDATA_ALL','YXSYSTEMGROUPDATA_SELECT')")
    public ResponseEntity getYxSystemGroupDatas(YxSystemGroupDataQueryCriteria criteria,
                                                Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, "sort");
        Pageable pageableT = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                sort);
        return new ResponseEntity(yxSystemGroupDataService.queryAll(criteria, pageableT), HttpStatus.OK);
    }

    @NoRepeatSubmit
    @Log("新增数据配置")
    @ApiOperation(value = "新增数据配置")
    @PostMapping(value = "/yxSystemGroupData")
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY, allEntries = true)
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMGROUPDATA_ALL','YXSYSTEMGROUPDATA_CREATE')")
    public ResponseEntity create(@RequestBody String jsonStr) {

        JSONObject jsonObject = JSON.parseObject(jsonStr);

        if (ObjectUtil.isNotNull(jsonObject.get("name"))) {
            if (StrUtil.isEmpty(jsonObject.get("name").toString())) {
                throw new BadRequestException("名称必须填写");
            }
        }

        if (ObjectUtil.isNotNull(jsonObject.get("title"))) {
            if (StrUtil.isEmpty(jsonObject.get("title").toString())) {
                throw new BadRequestException("标题必须填写");
            }
        }

        if (ObjectUtil.isNotNull(jsonObject.get("info"))) {
            if (StrUtil.isEmpty(jsonObject.get("info").toString())) {
                throw new BadRequestException("简介必须填写");
            }
        }

        if (ObjectUtil.isNotNull(jsonObject.get("pic"))) {
            if (StrUtil.isEmpty(jsonObject.get("pic").toString())) {
                throw new BadRequestException("图片必须上传");
            }
        }


        YxSystemGroupData yxSystemGroupData = new YxSystemGroupData();
        yxSystemGroupData.setGroupName(jsonObject.get("groupName").toString());
        jsonObject.remove("groupName");
        yxSystemGroupData.setValue(jsonObject.toJSONString());
        yxSystemGroupData.setStatus(jsonObject.getInteger("status"));
        yxSystemGroupData.setSort(jsonObject.getInteger("sort"));

        return new ResponseEntity(yxSystemGroupDataService.save(yxSystemGroupData), HttpStatus.CREATED);
    }

    @NoRepeatSubmit
    @Log("修改数据配置")
    @ApiOperation(value = "修改数据配置")
    @PutMapping(value = "/yxSystemGroupData")
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY, allEntries = true)
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMGROUPDATA_ALL','YXSYSTEMGROUPDATA_EDIT')")
    public ResponseEntity update(@RequestBody String jsonStr) {

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        if (ObjectUtil.isNotNull(jsonObject.get("name"))) {
            if (StrUtil.isEmpty(jsonObject.get("name").toString())) {
                throw new BadRequestException("名称必须填写");
            }
        }

        if (ObjectUtil.isNotNull(jsonObject.get("title"))) {
            if (StrUtil.isEmpty(jsonObject.get("title").toString())) {
                throw new BadRequestException("标题必须填写");
            }
        }

        if (ObjectUtil.isNotNull(jsonObject.get("pic"))) {
            if (StrUtil.isEmpty(jsonObject.get("pic").toString())) {
                throw new BadRequestException("图片必须上传");
            }
        }

        YxSystemGroupData yxSystemGroupData = new YxSystemGroupData();

        yxSystemGroupData.setGroupName(jsonObject.get("groupName").toString());
        jsonObject.remove("groupName");
        yxSystemGroupData.setValue(jsonObject.toJSONString());
        if (jsonObject.getInteger("status") == null) {
            yxSystemGroupData.setStatus(1);
        } else {
            yxSystemGroupData.setStatus(jsonObject.getInteger("status"));
        }

        if (jsonObject.getInteger("sort") == null) {
            yxSystemGroupData.setSort(0);
        } else {
            yxSystemGroupData.setSort(jsonObject.getInteger("sort"));
        }


        yxSystemGroupData.setId(Integer.valueOf(jsonObject.get("id").toString()));
        yxSystemGroupDataService.saveOrUpdate(yxSystemGroupData);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @NoRepeatSubmit
    @Log("删除数据配置")
    @ApiOperation(value = "删除数据配置")
    @DeleteMapping(value = "/yxSystemGroupData/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMGROUPDATA_ALL','YXSYSTEMGROUPDATA_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id) {

        yxSystemGroupDataService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
