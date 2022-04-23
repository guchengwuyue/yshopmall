/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreSeckill;
import co.yixiang.modules.activity.service.YxStoreSeckillService;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillQueryCriteria;
import co.yixiang.modules.aop.NoRepeatSubmit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author hupeng
 * @date 2019-12-14
 */
@Api(tags = "商城:秒杀管理")
@RestController
@RequestMapping("api")
public class StoreSeckillController {

    private final YxStoreSeckillService yxStoreSeckillService;

    public StoreSeckillController(YxStoreSeckillService yxStoreSeckillService) {
        this.yxStoreSeckillService = yxStoreSeckillService;
    }

    @Log("列表")
    @ApiOperation(value = "列表")
    @GetMapping(value = "/yxStoreSeckill")
    @PreAuthorize("hasAnyRole('admin','YXSTORESECKILL_ALL','YXSTORESECKILL_SELECT')")
    public ResponseEntity getYxStoreSeckills(YxStoreSeckillQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(yxStoreSeckillService.queryAll(criteria, pageable), HttpStatus.OK);
    }


    @Log("发布")
    @ApiOperation(value = "发布")
    @PutMapping(value = "/yxStoreSeckill")
    @PreAuthorize("hasAnyRole('admin','YXSTORESECKILL_ALL','YXSTORESECKILL_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreSeckill resources) {
        if (ObjectUtil.isNull(resources.getId())) {
            return new ResponseEntity(yxStoreSeckillService.save(resources), HttpStatus.CREATED);
        } else {
            yxStoreSeckillService.saveOrUpdate(resources);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @NoRepeatSubmit
    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreSeckill/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTORESECKILL_ALL','YXSTORESECKILL_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id) {
        yxStoreSeckillService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
