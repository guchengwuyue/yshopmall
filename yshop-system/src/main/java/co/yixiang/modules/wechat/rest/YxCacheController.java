package co.yixiang.modules.wechat.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.wechat.service.YxCacheService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import co.yixiang.aop.log.Log;
import co.yixiang.modules.wechat.domain.YxCache;
import co.yixiang.utils.OrderUtil;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-10-06
*/
@Api(tags = "YxCache管理")
@RestController
@RequestMapping("api")
public class YxCacheController {

    @Autowired
    private YxCacheService yxCacheService;

    @Autowired
    private  WxMpService wxService;

    @Log("查询菜单")
    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "/yxCache")
    @PreAuthorize("hasAnyRole('ADMIN','YXCACHE_ALL','YXCACHE_SELECT')")
    public ResponseEntity getYxCaches(){
        return new ResponseEntity(yxCacheService.findById("wechat_menus"),HttpStatus.OK);
    }

    @Log("创建菜单")
    @ApiOperation(value = "创建菜单")
    @PostMapping(value = "/yxCache")
    @PreAuthorize("hasAnyRole('ADMIN','YXCACHE_ALL','YXCACHE_CREATE')")
    public ResponseEntity create( @RequestBody String jsonStr){
        //if(StrUtil.isNotEmpty(jsonStr)) throw new BadRequestException("演示环境禁止操作");
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String jsonButton = jsonObject.get("buttons").toString();
        YxCache yxCache = new YxCache();
        Boolean isExist = yxCacheService.isExist("wechat_menus");
        WxMenu menu = JSONObject.parseObject(jsonStr,WxMenu.class);
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
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }




}