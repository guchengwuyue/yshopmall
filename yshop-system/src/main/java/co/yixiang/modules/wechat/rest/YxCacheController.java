package co.yixiang.modules.wechat.rest;

import co.yixiang.modules.wechat.service.YxCacheService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import co.yixiang.aop.log.Log;
import co.yixiang.modules.wechat.domain.YxCache;
import co.yixiang.utils.OrderUtil;
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
    public ResponseEntity create(@RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String jsonButton = jsonObject.get("button").toString();
        YxCache yxCache = new YxCache();
        Boolean isExist = yxCacheService.isExist("wechat_menus");
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

        return new ResponseEntity(HttpStatus.OK);
    }




}