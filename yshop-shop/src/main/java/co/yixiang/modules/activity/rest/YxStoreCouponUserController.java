package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("@el.check('admin','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_SELECT')")
    public ResponseEntity getYxStoreCouponUsers(YxStoreCouponUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreCouponUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxStoreCouponUser")
    @ApiOperation(value = "新增YxStoreCouponUser")
    @PostMapping(value = "/yxStoreCouponUser")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCouponUser resources){
        return new ResponseEntity(yxStoreCouponUserService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxStoreCouponUser")
    @ApiOperation(value = "修改YxStoreCouponUser")
    @PutMapping(value = "/yxStoreCouponUser")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCouponUser resources){
        yxStoreCouponUserService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxStoreCouponUser")
    @ApiOperation(value = "删除YxStoreCouponUser")
    @DeleteMapping(value = "/yxStoreCouponUser/{id}")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxStoreCouponUserService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}