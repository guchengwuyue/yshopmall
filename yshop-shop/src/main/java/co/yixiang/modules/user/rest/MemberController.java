/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.dto.UserMoneyDto;
import co.yixiang.modules.user.service.dto.YxUserQueryCriteria;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author hupeng
* @date 2019-10-06
*/
@Api(tags = "商城:会员管理")
@RestController
@RequestMapping("api")
public class MemberController {

    private final YxUserService yxUserService;

    public MemberController(YxUserService yxUserService) {
        this.yxUserService = yxUserService;
    }

    @Log("查看下级")
    @ApiOperation(value = "查看下级")
    @PostMapping(value = "/yxUser/spread")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_EDIT')")
    public ResponseEntity getSpread(@RequestBody YxUserQueryCriteria criteria){
        return new ResponseEntity<>(yxUserService.querySpread(criteria.getUid(),criteria.getGrade()),
                HttpStatus.OK);
    }

    @Log("查询用户")
    @ApiOperation(value = "查询用户")
    @GetMapping(value = "/yxUser")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_SELECT')")
    public ResponseEntity getYxUsers(YxUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    @Log("修改用户")
    @ApiOperation(value = "修改用户")
    @PutMapping(value = "/yxUser")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxUser resources){
        yxUserService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除用户")
    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/yxUser/{uid}")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer uid){
        yxUserService.removeById(uid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ForbidSubmit
    @ApiOperation(value = "用户禁用启用")
    @PostMapping(value = "/yxUser/onStatus/{id}")
    public ResponseEntity onStatus(@PathVariable Long id,@RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Integer status = jsonObject.getInteger("status");
        yxUserService.onStatus(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "修改余额")
    @PostMapping(value = "/yxUser/money")
    @PreAuthorize("hasAnyRole('admin','YXUSER_ALL','YXUSER_EDIT')")
    public ResponseEntity updatePrice(@Validated @RequestBody UserMoneyDto param){
        yxUserService.updateMoney(param);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
