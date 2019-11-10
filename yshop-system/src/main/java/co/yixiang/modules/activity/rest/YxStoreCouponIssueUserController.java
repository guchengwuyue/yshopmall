package co.yixiang.modules.activity.rest;

import co.yixiang.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreCouponIssueUser;
import co.yixiang.modules.activity.service.YxStoreCouponIssueUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserQueryCriteria;
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
* @date 2019-11-09
*/
@Api(tags = "YxStoreCouponIssueUser管理")
@RestController
@RequestMapping("api")
public class YxStoreCouponIssueUserController {

    @Autowired
    private YxStoreCouponIssueUserService yxStoreCouponIssueUserService;

    @Log("查询YxStoreCouponIssueUser")
    @ApiOperation(value = "查询YxStoreCouponIssueUser")
    @GetMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_SELECT')")
    public ResponseEntity getYxStoreCouponIssueUsers(YxStoreCouponIssueUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreCouponIssueUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxStoreCouponIssueUser")
    @ApiOperation(value = "新增YxStoreCouponIssueUser")
    @PostMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCouponIssueUser resources){
        return new ResponseEntity(yxStoreCouponIssueUserService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxStoreCouponIssueUser")
    @ApiOperation(value = "修改YxStoreCouponIssueUser")
    @PutMapping(value = "/yxStoreCouponIssueUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCouponIssueUser resources){
        yxStoreCouponIssueUserService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxStoreCouponIssueUser")
    @ApiOperation(value = "删除YxStoreCouponIssueUser")
    @DeleteMapping(value = "/yxStoreCouponIssueUser/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECOUPONISSUEUSER_ALL','YXSTORECOUPONISSUEUSER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxStoreCouponIssueUserService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}