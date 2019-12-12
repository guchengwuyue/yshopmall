package co.yixiang.modules.activity.rest;

import co.yixiang.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-11-10
*/
@Api(tags = "优惠券发放记录管理")
@RestController
@RequestMapping("api")
public class YxStoreCouponUserController {

    @Autowired
    private YxStoreCouponUserService yxStoreCouponUserService;

    @Log("查询Y")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxStoreCouponUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_SELECT')")
    public ResponseEntity getYxStoreCouponUsers(YxStoreCouponUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreCouponUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxStoreCouponUser")
    @ApiOperation(value = "新增YxStoreCouponUser")
    @PostMapping(value = "/yxStoreCouponUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCouponUser resources){
        return new ResponseEntity(yxStoreCouponUserService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxStoreCouponUser")
    @ApiOperation(value = "修改YxStoreCouponUser")
    @PutMapping(value = "/yxStoreCouponUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCouponUser resources){
        yxStoreCouponUserService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxStoreCouponUser")
    @ApiOperation(value = "删除YxStoreCouponUser")
    @DeleteMapping(value = "/yxStoreCouponUser/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxStoreCouponUserService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}