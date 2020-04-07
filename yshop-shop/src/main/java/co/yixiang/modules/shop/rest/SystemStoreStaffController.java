package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxSystemStoreStaff;
import co.yixiang.modules.shop.service.YxSystemStoreStaffService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffQueryCriteria;
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
* @date 2020-03-22
*/
@Api(tags = "门店店员管理")
@RestController
@RequestMapping("/api/yxSystemStoreStaff")
public class SystemStoreStaffController {

    private final YxSystemStoreStaffService yxSystemStoreStaffService;

    public SystemStoreStaffController(YxSystemStoreStaffService yxSystemStoreStaffService) {
        this.yxSystemStoreStaffService = yxSystemStoreStaffService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('yxSystemStoreStaff:list')")
    public void download(HttpServletResponse response, YxSystemStoreStaffQueryCriteria criteria) throws IOException {
        yxSystemStoreStaffService.download(yxSystemStoreStaffService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询门店店员")
    @ApiOperation("查询门店店员")
    @PreAuthorize("@el.check('yxSystemStoreStaff:list')")
    public ResponseEntity<Object> getYxSystemStoreStaffs(YxSystemStoreStaffQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxSystemStoreStaffService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增门店店员")
    @ApiOperation("新增门店店员")
    @PreAuthorize("@el.check('yxSystemStoreStaff:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxSystemStoreStaff resources){
        return new ResponseEntity<>(yxSystemStoreStaffService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改门店店员")
    @ApiOperation("修改门店店员")
    @PreAuthorize("@el.check('yxSystemStoreStaff:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxSystemStoreStaff resources){
        yxSystemStoreStaffService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除门店店员")
    @ApiOperation("删除门店店员")
    @PreAuthorize("@el.check('yxSystemStoreStaff:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxSystemStoreStaffService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}