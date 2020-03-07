package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxStoreCouponIssueUser;
import co.yixiang.modules.activity.service.YxStoreCouponIssueUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserQueryCriteria;
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
* @date 2019-11-09
*/
@Api(tags = "商城:优惠券前台用户领取记录管理")
@RestController
@RequestMapping("api")
public class StoreCouponIssueUserController {

    private final YxStoreCouponIssueUserService yxStoreCouponIssueUserService;

    public StoreCouponIssueUserController(YxStoreCouponIssueUserService yxStoreCouponIssueUserService) {
        this.yxStoreCouponIssueUserService = yxStoreCouponIssueUserService;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_SELECT')")
    public ResponseEntity getYxStoreCouponIssueUsers(YxStoreCouponIssueUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreCouponIssueUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增")
    @ApiOperation(value = "新增")
    @PostMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCouponIssueUser resources){
        return new ResponseEntity(yxStoreCouponIssueUserService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改")
    @ApiOperation(value = "修改")
    @PutMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCouponIssueUser resources){
        yxStoreCouponIssueUserService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreCouponIssueUser/{id}")
    @PreAuthorize("@el.check('admin','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxStoreCouponIssueUserService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}