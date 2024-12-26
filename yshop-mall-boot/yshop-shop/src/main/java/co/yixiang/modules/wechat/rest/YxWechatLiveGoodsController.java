/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.wechat.rest;

import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.mp.domain.YxWechatLiveGoods;
import co.yixiang.modules.mp.service.YxWechatLiveGoodsService;
import co.yixiang.modules.mp.service.dto.YxWechatLiveGoodsDto;
import co.yixiang.modules.mp.service.dto.YxWechatLiveGoodsQueryCriteria;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
* @author hupeng
* @date 2020-08-11
*/
@AllArgsConstructor
@Api(tags = "yxWechatLiveGoods管理")
@RestController
@RequestMapping("/api/yxWechatLiveGoods")
public class YxWechatLiveGoodsController {

    private final YxWechatLiveGoodsService yxWechatLiveGoodsService;
    private final IGenerator generator;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','yxWechatLiveGoods:list')")
    public void download(HttpServletResponse response, YxWechatLiveGoodsQueryCriteria criteria) throws IOException {
        yxWechatLiveGoodsService.download(generator.convert(yxWechatLiveGoodsService.queryAll(criteria), YxWechatLiveGoodsDto.class), response);
    }

    @GetMapping
    @Log("查询yxWechatLiveGoods")
    @ApiOperation("查询yxWechatLiveGoods")
    @PreAuthorize("@el.check('admin','yxWechatLiveGoods:list')")
    public ResponseEntity<Object> getYxWechatLiveGoodss(YxWechatLiveGoodsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxWechatLiveGoodsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ForbidSubmit
    @PostMapping
    @Log("新增yxWechatLiveGoods")
    @ApiOperation("新增yxWechatLiveGoods")
    @PreAuthorize("@el.check('admin','yxWechatLiveGoods:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxWechatLiveGoods resources){
        return new ResponseEntity<>(yxWechatLiveGoodsService.saveGoods(resources),HttpStatus.CREATED);
    }

    @ForbidSubmit
    @PutMapping
    @Log("修改yxWechatLiveGoods")
    @ApiOperation("修改yxWechatLiveGoods")
    @PreAuthorize("@el.check('admin','yxWechatLiveGoods:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxWechatLiveGoods resources){
        yxWechatLiveGoodsService.updateGoods(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除yxWechatLiveGoods")
    @ApiOperation("删除yxWechatLiveGoods")
    @PreAuthorize("@el.check('admin','yxWechatLiveGoods:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        Arrays.asList(ids).forEach(id->{
                yxWechatLiveGoodsService.removeGoods(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("同步数据")
    @PostMapping("/synchro")
    public ResponseEntity<Object> synchroWxOlLiveGoods(@RequestBody Integer[] ids) {
        yxWechatLiveGoodsService.synchroWxOlLive(Arrays.asList(ids));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
