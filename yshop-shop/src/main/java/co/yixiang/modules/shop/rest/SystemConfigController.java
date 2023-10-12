/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.constant.ShopConstants;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.shop.domain.YxSystemConfig;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.dto.YxSystemConfigQueryCriteria;
import co.yixiang.modules.mp.config.WxMpConfiguration;
import co.yixiang.modules.mp.config.WxPayConfiguration;
import co.yixiang.modules.mp.config.WxMaConfiguration;
import co.yixiang.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author hupeng
* @date 2019-10-10
*/
@Api(tags = "商城:配置管理")
@RestController
@RequestMapping("api")
public class SystemConfigController {

    private final YxSystemConfigService yxSystemConfigService;

    public SystemConfigController(YxSystemConfigService yxSystemConfigService) {
        this.yxSystemConfigService = yxSystemConfigService;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxSystemConfig")
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMCONFIG_ALL','YXSYSTEMCONFIG_SELECT')")
    public ResponseEntity getYxSystemConfigs(YxSystemConfigQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxSystemConfigService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ForbidSubmit
    @Log("新增或修改")
    @ApiOperation(value = "新增或修改")
    @PostMapping(value = "/yxSystemConfig")
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @PreAuthorize("hasAnyRole('admin','YXSYSTEMCONFIG_ALL','YXSYSTEMCONFIG_CREATE')")
    public ResponseEntity create(@RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        jsonObject.forEach(
                (key,value)->{
                    YxSystemConfig yxSystemConfig = yxSystemConfigService.getOne(new LambdaQueryWrapper<YxSystemConfig>()
                            .eq(YxSystemConfig::getMenuName,key));
                    YxSystemConfig yxSystemConfigModel = new YxSystemConfig();
                    yxSystemConfigModel.setMenuName(key);
                    yxSystemConfigModel.setValue(value.toString());
                    //重新配置微信相关
                    if(SystemConfigConstants.WECHAT_APPID.equals(key)){
                        WxMpConfiguration.removeWxMpService();
                        WxPayConfiguration.removeWxPayService();
                        WxMaConfiguration.removeWxMaService();
                    }
                    if(SystemConfigConstants.WXPAY_MCHID.equals(key) || SystemConfigConstants.WXAPP_APPID.equals(key)){
                        WxPayConfiguration.removeWxPayService();
                    }
                    if(SystemConfigConstants.EXP_APPID.equals(key)){
                        RedisUtil.del(ShopConstants.YSHOP_EXPRESS_SERVICE);
                    }
                    RedisUtil.set(key,value.toString(),0);
                    if(ObjectUtil.isNull(yxSystemConfig)){
                        yxSystemConfigService.save(yxSystemConfigModel);
                    }else{
                        yxSystemConfigModel.setId(yxSystemConfig.getId());
                        yxSystemConfigService.saveOrUpdate(yxSystemConfigModel);
                    }
                }
        );

        return new ResponseEntity(HttpStatus.CREATED);
    }



}
