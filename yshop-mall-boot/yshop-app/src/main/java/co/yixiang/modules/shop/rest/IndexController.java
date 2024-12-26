/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.rest;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.resource.ClassPathResource;
import co.yixiang.api.ApiResult;
import co.yixiang.api.YshopException;
import co.yixiang.constant.ShopConstants;
import co.yixiang.enums.ProductEnum;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.YxStoreSeckillService;
import co.yixiang.modules.canvas.domain.StoreCanvas;
import co.yixiang.modules.canvas.service.StoreCanvasService;
import co.yixiang.modules.mp.service.YxWechatLiveService;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.product.vo.YxSystemStoreQueryVo;
import co.yixiang.modules.shop.domain.YxAppVersion;
import co.yixiang.modules.shop.param.YxSystemStoreQueryParam;
import co.yixiang.modules.shop.service.YxAppVersionService;
import co.yixiang.modules.shop.service.YxSystemGroupDataService;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.vo.AppCheckVersion;
import co.yixiang.modules.shop.vo.IndexVo;
import co.yixiang.modules.shop.vo.YxAppVersionVo;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.RedisUtil;
import co.yixiang.utils.ShopKeyUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IndexController
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/19
 **/
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "首页模块", tags = "商城:首页模块")
public class IndexController {
    private final YxAppVersionService appVersionService;
    private final YxSystemGroupDataService systemGroupDataService;
    private final YxStoreProductService storeProductService;
    private final YxSystemStoreService systemStoreService;
    private final YxStoreCombinationService storeCombinationService;
    private final YxStoreSeckillService storeSeckillService;
    private final YxWechatLiveService wechatLiveService;

    private final StoreCanvasService storeCanvasService;

    @GetMapping("/getCanvas")
    @ApiOperation(value = "读取画布数据")
    public ResponseEntity<StoreCanvas> getCanvas(StoreCanvas storeCanvas){
        StoreCanvas canvas = storeCanvasService.getOne(new LambdaQueryWrapper<StoreCanvas>()
                .eq(StoreCanvas::getTerminal, storeCanvas.getTerminal())
                .orderByDesc(StoreCanvas::getCanvasId).last("limit 1"));
        return new ResponseEntity<>(canvas, HttpStatus.OK);
    }

    @Cacheable(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY)
    @GetMapping("/index")
    @ApiOperation(value = "首页数据",notes = "首页数据")
    public ApiResult<IndexVo> index(){
        IndexVo indexVo = IndexVo.builder()
                .banner(systemGroupDataService.getDatas(ShopConstants.YSHOP_HOME_BANNER))
                .bastList(storeProductService.getList(1,6, ProductEnum.TYPE_1.getValue()))
                .benefit(storeProductService.getList(1,10,ProductEnum.TYPE_4.getValue()))
                .combinationList(storeCombinationService.getList(1,8).getStoreCombinationQueryVos())
                .firstList(storeProductService.getList(1,6,ProductEnum.TYPE_3.getValue()))
                .likeInfo(storeProductService.getList(1,8,ProductEnum.TYPE_2.getValue()))
                .mapKey(RedisUtil.get(ShopKeyUtils.getTengXunMapKey()))
                .menus(systemGroupDataService.getDatas(ShopConstants.YSHOP_HOME_MENUS))
                .roll(systemGroupDataService.getDatas(ShopConstants.YSHOP_HOME_ROLL_NEWS))
                .seckillList(storeSeckillService.getList(1, 4))
                .liveList(wechatLiveService.getList(1,4,0))
                .build();
        return ApiResult.ok(indexVo);
    }

    @GetMapping("/search/keyword")
    @ApiOperation(value = "热门搜索关键字获取",notes = "热门搜索关键字获取")
    public ApiResult<List<String>> search(){
        List<JSONObject> list = systemGroupDataService.getDatas(ShopConstants.YSHOP_HOT_SEARCH);
        List<String>  stringList = new ArrayList<>();
        for (JSONObject object : list) {
            stringList.add(object.getString("title"));
        }
        return ApiResult.ok(stringList);
    }


    @PostMapping("/image_base64")
    @ApiOperation(value = "获取图片base64",notes = "获取图片base64")
    @Deprecated
    public ApiResult<List<String>> imageBase64(){
        return ApiResult.ok(null);
    }


    @GetMapping("/citys")
    @ApiOperation(value = "获取城市json",notes = "获取城市json")
    public ApiResult<JSONObject> cityJson(){
        String path = "city.json";
        String name = "city.json";
        try {
            File file = FileUtil.inputStreamToFile(new ClassPathResource(path).getStream(), name);
            FileReader fileReader = new FileReader(file,"UTF-8");
            String string = fileReader.readString();
            JSONObject jsonObject = JSON.parseObject(string);
            return ApiResult.ok(jsonObject);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new YshopException("无数据");
        }

    }


    @GetMapping("/store_list")
    @ApiOperation(value = "获取门店列表",notes = "获取门店列表")
    public ApiResult<Map<String,Object>> storeList( YxSystemStoreQueryParam param){
        Map<String,Object> map = new LinkedHashMap<>();
        List<YxSystemStoreQueryVo> lists = systemStoreService.getStoreList(
                param.getLatitude(),
                param.getLongitude(),
                param.getPage(),param.getLimit());
        map.put("list",lists);
        return ApiResult.ok(map);
    }


    @GetMapping("/version")
    @ApiOperation(value = "获取app版本信息",notes = "获取app版本信息")
    public ApiResult<YxAppVersionVo> storeList(AppCheckVersion param){
        YxAppVersion appVersion= appVersionService.lambdaQuery().orderByDesc(YxAppVersion::getCreateTime).one();
        YxAppVersionVo appVersionVo=new  YxAppVersionVo();
        appVersionVo.setVersionCode(appVersion.getVersionCode());
        appVersionVo.setVersionInfo(appVersion.getVersionInfo());
        appVersionVo.setVersionName(appVersion.getVersionName());
        appVersionVo.setDownloadUrl("1101".equals(param.getType())?appVersion.getAndroidUrl():appVersion.getIosUrl());
        appVersionVo.setForceUpdate(appVersion.getForceUpdate()==0);
        if (!param.getVersionName().equals(appVersion.getVersionName())){
            return ApiResult.ok(appVersionVo);
        }
        return ApiResult.ok(new YxAppVersionVo());
    }

}
