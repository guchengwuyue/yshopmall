package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxUserRecharge;
import co.yixiang.modules.shop.service.YxUserRechargeService;
import co.yixiang.modules.shop.service.dto.YxUserRechargeQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author hupeng
* @date 2020-03-02
*/
@Api(tags = "充值管理管理")
@RestController
@RequestMapping("/api/yxUserRecharge")
public class UserRechargeController {

    private final YxUserRechargeService yxUserRechargeService;

    public UserRechargeController(YxUserRechargeService yxUserRechargeService) {
        this.yxUserRechargeService = yxUserRechargeService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('yxUserRecharge:list')")
    public void download(HttpServletResponse response, YxUserRechargeQueryCriteria criteria) throws IOException {
        yxUserRechargeService.download(yxUserRechargeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询充值管理")
    @ApiOperation("查询充值管理")
    @PreAuthorize("@el.check('yxUserRecharge:list')")
    public ResponseEntity<Object> getYxUserRecharges(YxUserRechargeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxUserRechargeService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增充值管理")
    @ApiOperation("新增充值管理")
    @PreAuthorize("@el.check('yxUserRecharge:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxUserRecharge resources){
        return new ResponseEntity<>(yxUserRechargeService.create(resources),HttpStatus.CREATED);
    }



    @Log("删除充值管理")
    @ApiOperation("删除充值管理")
    @PreAuthorize("@el.check('yxUserRecharge:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxUserRechargeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}