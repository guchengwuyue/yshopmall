package co.yixiang.mp.controller;


import cn.hutool.core.util.StrUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.mp.config.WxMpConfiguration;
import co.yixiang.mp.domain.YxCache;
import co.yixiang.mp.service.YxCacheService;
import co.yixiang.utils.OrderUtil;
import co.yixiang.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
* @author hupeng
* @date 2019-10-06
*/
@Api(tags = "商城:微信菜單")
@RestController
@RequestMapping("api")
@SuppressWarnings("unchecked")
public class WechatMenuController {

    private final YxCacheService yxCacheService;

    public WechatMenuController(YxCacheService yxCacheService) {
        this.yxCacheService = yxCacheService;
    }

    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "/yxCache")
    @PreAuthorize("@el.check('admin','YXCACHE_ALL','YXCACHE_SELECT')")
    public ResponseEntity getYxCaches(){
        return new ResponseEntity(yxCacheService.findById("wechat_menus"),HttpStatus.OK);
    }


    @ApiOperation(value = "创建菜单")
    @PostMapping(value = "/yxCache")
    @PreAuthorize("@el.check('admin','YXCACHE_ALL','YXCACHE_CREATE')")
    public ResponseEntity create( @RequestBody String jsonStr){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String jsonButton = jsonObject.get("buttons").toString();
        YxCache yxCache = new YxCache();
        Boolean isExist = yxCacheService.isExist("wechat_menus");
        WxMenu menu = JSONObject.parseObject(jsonStr,WxMenu.class);

        WxMpService wxService = WxMpConfiguration.getWxMpService();
        if(isExist){
            yxCache.setKey("wechat_menus");
            yxCache.setResult(jsonButton);
            yxCacheService.update(yxCache);
        }else {
            yxCache.setKey("wechat_menus");
            yxCache.setResult(jsonButton);
            yxCache.setAddTime(OrderUtil.getSecondTimestampTwo());
            yxCacheService.create(yxCache);
        }


        //创建菜单
        try {
            wxService.getMenuService().menuDelete();
            wxService.getMenuService().menuCreate(menu);
        } catch (WxErrorException e) {
            throw new BadRequestException(e.getMessage());
           // e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }




}