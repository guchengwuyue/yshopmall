package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.constant.ShopConstants;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxSystemConfig;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.dto.YxSystemConfigQueryCriteria;
import co.yixiang.mp.config.WxMpConfiguration;
import co.yixiang.mp.config.WxPayConfiguration;
import co.yixiang.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("@el.check('admin','YXSYSTEMCONFIG_ALL','YXSYSTEMCONFIG_SELECT')")
    public ResponseEntity getYxSystemConfigs(YxSystemConfigQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxSystemConfigService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增或修改")
    @ApiOperation(value = "新增或修改")
    @PostMapping(value = "/yxSystemConfig")
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @PreAuthorize("@el.check('admin','YXSYSTEMCONFIG_ALL','YXSYSTEMCONFIG_CREATE')")
    public ResponseEntity create(@RequestBody String jsonStr){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        jsonObject.forEach(
                (key,value)->{
                    YxSystemConfig yxSystemConfig = yxSystemConfigService.findByKey(key);
                    YxSystemConfig yxSystemConfigModel = new YxSystemConfig();
                    yxSystemConfigModel.setMenuName(key);
                    yxSystemConfigModel.setValue(value.toString());
                    //重新配置微信相关
                    if(key.equals("wechat_appid")){
                        WxMpConfiguration.removeWxMpService();
                        WxPayConfiguration.removeWxPayService();
                    }
                    if(key.equals("wxpay_mchId") || key.equals("wxapp_appId")){
                        WxPayConfiguration.removeWxPayService();
                    }
                    RedisUtil.set(key,value.toString(),0);
                    if(ObjectUtil.isNull(yxSystemConfig)){
                        yxSystemConfigService.create(yxSystemConfigModel);
                    }else{
                        yxSystemConfigModel.setId(yxSystemConfig.getId());
                        yxSystemConfigService.update(yxSystemConfigModel);
                    }
                }
        );

        return new ResponseEntity(HttpStatus.CREATED);
    }



}