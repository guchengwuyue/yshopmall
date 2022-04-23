/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.NoRepeatSubmit;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.UserMoneyDto;
import co.yixiang.modules.shop.service.dto.YxUserQueryCriteria;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author hupeng
 * @date 2019-10-06
 */
@Api(tags = "商城:会员管理")
@RestController
@RequestMapping("api")
public class MemberController {

    private final YxUserService yxUserService;
    private final YxSystemConfigService yxSystemConfigService;

    public MemberController(YxUserService yxUserService, YxSystemConfigService yxSystemConfigService) {
        this.yxUserService = yxUserService;
        this.yxSystemConfigService = yxSystemConfigService;
    }

    @Log("查询用户")
    @ApiOperation(value = "查询用户")
    @GetMapping(value = "/yxUser")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_SELECT')")
    public ResponseEntity getYxUsers(YxUserQueryCriteria criteria, Pageable pageable) {
        if (ObjectUtil.isNotNull(criteria.getIsPromoter())) {
            if (criteria.getIsPromoter() == 1) {
                String key = yxSystemConfigService.findByKey(SystemConfigConstants.STORE_BROKERAGE_STATU)
                        .getValue();
                if (Integer.valueOf(key) == 2) {
                    return new ResponseEntity(null, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(yxUserService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @NoRepeatSubmit
    @Log("新增用户")
    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/yxUser")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxUser resources) {
        return new ResponseEntity(yxUserService.save(resources), HttpStatus.CREATED);
    }

    @NoRepeatSubmit
    @Log("修改用户")
    @ApiOperation(value = "修改用户")
    @PutMapping(value = "/yxUser")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxUser resources) {
        yxUserService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @NoRepeatSubmit
    @Log("删除用户")
    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/yxUser/{uid}")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer uid) {

        yxUserService.removeById(uid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "用户禁用启用")
    @PostMapping(value = "/yxUser/onStatus/{id}")
    public ResponseEntity onStatus(@PathVariable Integer id, @RequestBody String jsonStr) {

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        int status = Integer.valueOf(jsonObject.get("status").toString());
        yxUserService.onStatus(id, status);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "修改余额")
    @PostMapping(value = "/yxUser/money")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_EDIT')")
    public ResponseEntity updatePrice(@Validated @RequestBody UserMoneyDto param) {
        yxUserService.updateMoney(param);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
