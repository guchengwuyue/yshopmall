/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.wechat.rest.controller;

import cn.binarywang.wx.miniapp.bean.live.WxMaLiveResult;
import co.yixiang.api.ApiResult;
import co.yixiang.modules.mp.service.YxWechatLiveService;
import co.yixiang.modules.mp.service.dto.YxWechatLiveQueryCriteria;
import co.yixiang.modules.mp.vo.WechatLiveVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author hupeng
* @date 2020-08-10
*/
@AllArgsConstructor
@Api(tags = "wxlive管理")
@RestController
@RequestMapping
public class WechatLiveController {

    private final YxWechatLiveService yxWechatLiveService;


    @GetMapping("yxWechatLive")
    @ApiOperation("查询所有直播间")
    public ApiResult<WechatLiveVo> getYxWechatLives(YxWechatLiveQueryCriteria criteria, Pageable pageable){
        return ApiResult.ok(yxWechatLiveService.queryAll(criteria,pageable));
    }
    @GetMapping("yxWechatLive/getLiveReplay/{id}")
    @ApiOperation("获取直播回放")
    public ApiResult<List<WxMaLiveResult.LiveReplay>>  getLiveReplay(@PathVariable Integer id){
        return ApiResult.ok(yxWechatLiveService.getLiveReplay(id));
    }
}
