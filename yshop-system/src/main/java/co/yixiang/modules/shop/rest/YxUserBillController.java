package co.yixiang.modules.shop.rest;

import co.yixiang.aop.log.Log;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.dto.YxUserBillQueryCriteria;
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
* @date 2019-11-06
*/
@Api(tags = "YxUserBill管理")
@RestController
@RequestMapping("api")
public class YxUserBillController {

    @Autowired
    private YxUserBillService yxUserBillService;

    @Log("查询YxUserBill")
    @ApiOperation(value = "查询YxUserBill")
    @GetMapping(value = "/yxUserBill")
    @PreAuthorize("hasAnyRole('ADMIN','YXUSERBILL_ALL','YXUSERBILL_SELECT')")
    public ResponseEntity getYxUserBills(YxUserBillQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxUserBillService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxUserBill")
    @ApiOperation(value = "新增YxUserBill")
    @PostMapping(value = "/yxUserBill")
    @PreAuthorize("hasAnyRole('ADMIN','YXUSERBILL_ALL','YXUSERBILL_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxUserBill resources){
        return new ResponseEntity(yxUserBillService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxUserBill")
    @ApiOperation(value = "修改YxUserBill")
    @PutMapping(value = "/yxUserBill")
    @PreAuthorize("hasAnyRole('ADMIN','YXUSERBILL_ALL','YXUSERBILL_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxUserBill resources){
        yxUserBillService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxUserBill")
    @ApiOperation(value = "删除YxUserBill")
    @DeleteMapping(value = "/yxUserBill/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXUSERBILL_ALL','YXUSERBILL_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxUserBillService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}