/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.wechat.rest;

import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.mp.domain.YxWechatLive;
import co.yixiang.modules.mp.service.YxWechatLiveService;
import co.yixiang.modules.mp.service.dto.UpdateGoodsDto;
import co.yixiang.modules.mp.service.dto.YxWechatLiveDto;
import co.yixiang.modules.mp.service.dto.YxWechatLiveQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
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
* @date 2020-08-10
*/
@AllArgsConstructor
@Api(tags = "wxlive管理")
@RestController
@RequestMapping("/api/yxWechatLive")
public class YxWechatLiveController {

    private final YxWechatLiveService yxWechatLiveService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','yxWechatLive:list')")
    public void download(HttpServletResponse response, YxWechatLiveQueryCriteria criteria) throws IOException {
        yxWechatLiveService.download(generator.convert(yxWechatLiveService.queryAll(criteria), YxWechatLiveDto.class), response);
    }

    @GetMapping
    @Log("查询wxlive")
    @ApiOperation("查询wxlive")
    @PreAuthorize("@el.check('admin','yxWechatLive:list')")
    public ResponseEntity<Object> getYxWechatLives(YxWechatLiveQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxWechatLiveService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @ForbidSubmit
    @PostMapping
    @Log("新增wxlive")
    @ApiOperation("新增wxlive")
    @PreAuthorize("@el.check('admin','yxWechatLive:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxWechatLive resources){
        return new ResponseEntity<>(yxWechatLiveService.saveLive(resources),HttpStatus.CREATED);
    }


    @ForbidSubmit
    @PostMapping("/addGoods")
    @Log("添加商品")
    @ApiOperation("添加商品")
    @PreAuthorize("@el.check('admin','yxWechatLive:add')")
    public ResponseEntity<Object> addGoods(@Validated @RequestBody UpdateGoodsDto resources){
        return new ResponseEntity<>(yxWechatLiveService.addGoods(resources),HttpStatus.CREATED);
    }

    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @ForbidSubmit
    @PutMapping
    @Log("修改wxlive")
    @ApiOperation("修改wxlive")
    @PreAuthorize("@el.check('admin','yxWechatLive:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxWechatLive resources){
        yxWechatLiveService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @ForbidSubmit
    @Log("删除wxlive")
    @ApiOperation("删除wxlive")
    @PreAuthorize("@el.check('admin','yxWechatLive:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        Arrays.asList(ids).forEach(id->{
            yxWechatLiveService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @ApiOperation("同步数据")
    @GetMapping("/synchro")
    public ResponseEntity<Object> synchroWxOlLive() {
        yxWechatLiveService.synchroWxOlLive();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
