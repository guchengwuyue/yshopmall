/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.customer.rest;
import java.util.Arrays;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.aop.ForbidSubmit;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.customer.domain.YxStoreCustomer;
import co.yixiang.modules.customer.service.YxStoreCustomerService;
import co.yixiang.modules.customer.service.dto.YxStoreCustomerQueryCriteria;
import co.yixiang.modules.customer.service.dto.YxStoreCustomerDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import co.yixiang.domain.PageResult;
/**
* @author Bug
* @date 2020-12-10
*/
@AllArgsConstructor
@Api(tags = "customer管理")
@RestController
@RequestMapping("/api/yxStoreCustomer")
public class YxStoreCustomerController {

    private final YxStoreCustomerService yxStoreCustomerService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','yxStoreCustomer:list')")
    public void download(HttpServletResponse response, YxStoreCustomerQueryCriteria criteria) throws IOException {
        yxStoreCustomerService.download(generator.convert(yxStoreCustomerService.queryAll(criteria), YxStoreCustomerDto.class), response);
    }

    @GetMapping
    @Log("查询customer")
    @ApiOperation("查询customer")
    @PreAuthorize("@el.check('admin','yxStoreCustomer:list')")
    public ResponseEntity<PageResult<YxStoreCustomerDto>> getYxStoreCustomers(YxStoreCustomerQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxStoreCustomerService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ForbidSubmit
    @PostMapping
    @Log("新增customer")
    @ApiOperation("新增customer")
    @PreAuthorize("@el.check('admin','yxStoreCustomer:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxStoreCustomer resources){
        Long count = yxStoreCustomerService.count(new LambdaQueryWrapper<YxStoreCustomer>().eq(YxStoreCustomer::getOpenId, resources.getOpenId()));
        if (count > 0) {
            throw new BadRequestException("当前用户已存在，请勿重复提交");
        }
        return new ResponseEntity<>(yxStoreCustomerService.save(resources),HttpStatus.CREATED);
    }

    @ForbidSubmit
    @PutMapping
    @Log("修改customer")
    @ApiOperation("修改customer")
    @PreAuthorize("@el.check('admin','yxStoreCustomer:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxStoreCustomer resources){
        yxStoreCustomerService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除customer")
    @ApiOperation("删除customer")
    @PreAuthorize("@el.check('admin','yxStoreCustomer:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        Arrays.asList(ids).forEach(id->{
            yxStoreCustomerService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
