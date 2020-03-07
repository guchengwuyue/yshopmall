package co.yixiang.modules.activity.rest;

import co.yixiang.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserQueryCriteria;
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
* @date 2019-11-10
*/
@Api(tags = "商城:优惠券发放记录管理")
@RestController
@RequestMapping("api")
public class StoreCouponUserController {

    private final YxStoreCouponUserService yxStoreCouponUserService;

    public StoreCouponUserController(YxStoreCouponUserService yxStoreCouponUserService) {
        this.yxStoreCouponUserService = yxStoreCouponUserService;
    }

    @Log("查询Y")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxStoreCouponUser")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONUSER_ALL','YXSTORECOUPONUSER_SELECT')")
    public ResponseEntity getYxStoreCouponUsers(YxStoreCouponUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreCouponUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }


}