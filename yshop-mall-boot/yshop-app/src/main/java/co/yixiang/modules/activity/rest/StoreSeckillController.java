/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.rest;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.ApiResult;
import co.yixiang.api.YshopException;
import co.yixiang.enums.ProductTypeEnum;
import co.yixiang.modules.logging.aop.log.AppLog;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.constant.ShopConstants;
import co.yixiang.modules.activity.service.YxStoreSeckillService;
import co.yixiang.modules.activity.service.dto.SeckillTimeDto;
import co.yixiang.modules.activity.vo.SeckillConfigVo;
import co.yixiang.modules.activity.vo.StoreSeckillVo;
import co.yixiang.modules.activity.vo.YxStoreSeckillQueryVo;
import co.yixiang.modules.product.service.YxStoreProductRelationService;
import co.yixiang.modules.shop.domain.YxSystemGroupData;
import co.yixiang.modules.shop.service.YxSystemGroupDataService;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataQueryCriteria;
import co.yixiang.utils.OrderUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>
 * 商品秒杀产品前端控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-12-14
 */
@Slf4j
@RestController
@RequestMapping
@Api(value = "商品秒杀", tags = "营销:商品秒杀")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreSeckillController {

    private final YxStoreSeckillService yxStoreSeckillService;
    private final YxSystemGroupDataService yxSystemGroupDataService;
    private final YxStoreProductRelationService relationService;

    /**
     * 秒杀产品列表
     */
    @GetMapping("/seckill/list/{time}")
    @ApiOperation(value = "秒杀产品列表", notes = "秒杀产品列表")
    public ApiResult<List<YxStoreSeckillQueryVo>> getYxStoreSeckillPageList(@PathVariable String time,
                                                       @RequestParam(value = "page",defaultValue = "1") int page,
                                                       @RequestParam(value = "limit",defaultValue = "10") int limit){
        if (StrUtil.isBlank(time) || !NumberUtil.isNumber(time)){
            throw new YshopException("参数错误");
        }
        return ApiResult.resultPage(yxStoreSeckillService.getList(page, limit, Integer.valueOf(time)),limit);

    }


    /**
     * 根据id获取商品秒杀产品详情
     */
    @AppLog(value = "根据id获取商品秒杀产品详情", type = 1)
    @AuthCheck
    @GetMapping("/seckill/detail/{id}")
    @ApiOperation(value = "秒杀产品详情", notes = "秒杀产品详情")
    public ApiResult<StoreSeckillVo> getYxStoreSeckill(@PathVariable Long id){
        Long uid = LocalUser.getUser().getUid();
        StoreSeckillVo storeSeckillVo = yxStoreSeckillService.getDetail(id);
        storeSeckillVo.setUserCollect(relationService
                .isProductRelation(id,uid, ProductTypeEnum.SECKILL.getValue()));
        return ApiResult.ok(storeSeckillVo);
    }


    /**
     * 秒杀产品时间区间
     */
    @GetMapping("/seckill/index")
    @ApiOperation(value = "秒杀产品时间区间", notes = "秒杀产品时间区间")
    public ApiResult<SeckillConfigVo> getYxStoreSeckillIndex() {
        //获取秒杀配置
        AtomicInteger seckillTimeIndex = new AtomicInteger();
        SeckillConfigVo seckillConfigVo = new SeckillConfigVo();

        YxSystemGroupDataQueryCriteria queryCriteria = new YxSystemGroupDataQueryCriteria();
        queryCriteria.setGroupName(ShopConstants.YSHOP_SECKILL_TIME);
        queryCriteria.setStatus(1);
        List<YxSystemGroupData> yxSystemGroupDataList = yxSystemGroupDataService.queryAll(queryCriteria);

        List<SeckillTimeDto> list = new ArrayList<>();
        int today = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(new Date()));
        yxSystemGroupDataList.forEach(i -> {
            String jsonStr = i.getValue();
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            int time = Integer.valueOf(jsonObject.get("time").toString());//时间 5
            int continued = Integer.valueOf(jsonObject.get("continued").toString());//活动持续事件  3
            SimpleDateFormat sdf = new SimpleDateFormat("HH");
            String nowTime = sdf.format(new Date());
            String index = nowTime.substring(0, 1);
            int currentHour = "0".equals(index) ? Integer.valueOf(nowTime.substring(1, 2)) : Integer.valueOf(nowTime);
            SeckillTimeDto seckillTimeDto = new SeckillTimeDto();
            seckillTimeDto.setId(i.getId());
            //活动结束时间
            int activityEndHour = time + continued;
            if (activityEndHour > 24) {
                seckillTimeDto.setState("即将开始");
                seckillTimeDto.setTime(jsonObject.get("time").toString().length() > 1 ? jsonObject.get("time").toString() + ":00" : "0" + jsonObject.get("time").toString() + ":00");
                seckillTimeDto.setStatus(2);
                seckillTimeDto.setStop(today + activityEndHour * 3600);
            } else {
                if (currentHour >= time && currentHour < activityEndHour) {
                    seckillTimeDto.setState("抢购中");
                    seckillTimeDto.setTime(jsonObject.get("time").toString().length() > 1 ? jsonObject.get("time").toString() + ":00" : "0" + jsonObject.get("time").toString() + ":00");
                    seckillTimeDto.setStatus(1);
                    seckillTimeDto.setStop(today + activityEndHour * 3600);
                    seckillTimeIndex.set(yxSystemGroupDataList.indexOf(i));
                } else if (currentHour < time) {
                    seckillTimeDto.setState("即将开始");
                    seckillTimeDto.setTime(jsonObject.get("time").toString().length() > 1 ? jsonObject.get("time").toString() + ":00" : "0" + jsonObject.get("time").toString() + ":00");
                    seckillTimeDto.setStatus(2);
                    seckillTimeDto.setStop(today + time * 3600);
                } else {
                    seckillTimeDto.setState("已结束");
                    seckillTimeDto.setTime(jsonObject.get("time").toString().length() > 1 ? jsonObject.get("time").toString() + ":00" : "0" + jsonObject.get("time").toString() + ":00");
                    seckillTimeDto.setStatus(0);
                    seckillTimeDto.setStop(today + activityEndHour * 3600);
                }
            }
            list.add(seckillTimeDto);
        });
        List<SeckillTimeDto> seckillTimeDtoList = list.stream()
                .sorted(Comparator.comparing(SeckillTimeDto::getTime))
                .collect(Collectors.toList());
        OptionalInt optionalInt = IntStream.range(0, seckillTimeDtoList.size())
                .filter(i -> seckillTimeDtoList.get(i).getStatus().equals(1))
                .findFirst();
        seckillConfigVo.setSeckillTimeIndex(optionalInt.isPresent() ? optionalInt.getAsInt() : 0);
        seckillConfigVo.setSeckillTime(seckillTimeDtoList);
        return ApiResult.ok(seckillConfigVo);
    }
}

