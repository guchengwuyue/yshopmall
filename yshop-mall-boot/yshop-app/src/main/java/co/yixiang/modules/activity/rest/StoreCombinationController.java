/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.ApiResult;
import co.yixiang.api.YshopException;
import co.yixiang.enums.ProductTypeEnum;
import co.yixiang.modules.logging.aop.log.AppLog;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.modules.activity.param.PinkCancelParam;
import co.yixiang.modules.activity.param.PinkShareParam;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.activity.vo.CombinationQueryVo;
import co.yixiang.modules.activity.vo.PinkInfoVo;
import co.yixiang.modules.activity.vo.StoreCombinationVo;
import co.yixiang.modules.product.service.YxStoreProductRelationService;
import co.yixiang.modules.services.CreatShareProductService;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.user.domain.YxUser;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 拼团前端控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-11-19
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "拼团", tags = "营销:拼团")
public class StoreCombinationController {

    private final YxStoreCombinationService storeCombinationService;
    private final YxStorePinkService storePinkService;
    private final YxSystemConfigService systemConfigService;
    private final YxStoreProductRelationService relationService;
    private final CreatShareProductService creatShareProductService;

    @Value("${file.path}")
    private String path;


    /**
     * 拼团产品列表
     */
    @GetMapping("/combination/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码,默认为1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "页大小,默认为10", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "拼团产品列表",notes = "拼团产品列表")
    public ApiResult<CombinationQueryVo> getList(@RequestParam(value = "page",defaultValue = "1") int page,
                                                 @RequestParam(value = "limit",defaultValue = "10") int limit){
        return  ApiResult.ok(storeCombinationService.getList(page, limit));
    }

    /**
     * 拼团产品详情
     */
    @AppLog(value = "查看拼团产品详情", type = 1)
    @AuthCheck
    @GetMapping("/combination/detail/{id}")
    @ApiOperation(value = "拼团产品详情",notes = "拼团产品详情")
    public ApiResult<StoreCombinationVo> detail(@PathVariable Long id){
        if(ObjectUtil.isNull(id)) {
            throw new YshopException("参数错误");
        }
        Long uid = LocalUser.getUser().getUid();
        StoreCombinationVo storeCombinationVo = storeCombinationService.getDetail(id,uid);
        storeCombinationVo.setUserCollect(relationService
                .isProductRelation(id,uid, ProductTypeEnum.COMBINATION.getValue()));
        return ApiResult.ok(storeCombinationVo);
    }

    /**
     * 拼团明细
     */
    @AppLog(value = "查看拼团明细", type = 1)
    @AuthCheck
    @GetMapping("/combination/pink/{id}")
    @ApiOperation(value = "拼团明细",notes = "拼团明细")
    public ApiResult<PinkInfoVo> pink(@PathVariable Long id){
        if(ObjectUtil.isNull(id)) {
            throw new YshopException("参数错误");
        }
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(storePinkService.pinkInfo(id,uid));
    }

    /**
     * 拼团海报
     */
    @AppLog(value = "生成拼团海报", type = 1)
    @AuthCheck
    @PostMapping("/combination/poster")
    @ApiOperation(value = "拼团海报",notes = "拼团海报")
    public ApiResult<Object> poster(@Validated @RequestBody PinkShareParam param){
        String siteUrl = systemConfigService.getData(SystemConfigConstants.SITE_URL);
        if(StrUtil.isEmpty(siteUrl)){
            throw new YshopException("未配置h5地址");
        }
        String apiUrl = systemConfigService.getData(SystemConfigConstants.API_URL);
        if(StrUtil.isEmpty(apiUrl)){
            throw new YshopException("未配置api地址");
        }
        YxUser userInfo = LocalUser.getUser();
        Map<String,Object> map = Maps.newHashMap();
        String spreadUrl = creatShareProductService.getPinkPosterUrl(Long.valueOf(param.getId()),userInfo,
                siteUrl,apiUrl,path,param.getFrom());
        map.put("url",spreadUrl);
        return ApiResult.ok(map);
    }

    /**
     * 取消开团
     */
    @AppLog(value = "取消开团", type = 1)
    @AuthCheck
    @PostMapping("/combination/remove")
    @ApiOperation(value = "取消开团",notes = "取消开团")
    public ApiResult<Boolean> remove(@Validated @RequestBody PinkCancelParam param){
        Long pinkId = Long.valueOf(param.getId());
        Long cId = Long.valueOf(param.getCid());
        Long uid = LocalUser.getUser().getUid();
        storePinkService.removePink(uid,cId,pinkId);
        return ApiResult.ok();
    }




}

