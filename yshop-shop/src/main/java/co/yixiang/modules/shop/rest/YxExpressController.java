package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxExpress;
import co.yixiang.modules.shop.service.YxExpressService;
import co.yixiang.modules.shop.service.dto.YxExpressQueryCriteria;
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
* @date 2019-12-12
*/
@Api(tags = "YxExpress管理")
@RestController
@RequestMapping("api")
public class YxExpressController {

    @Autowired
    private YxExpressService yxExpressService;

    @Log("查询YxExpress")
    @ApiOperation(value = "查询YxExpress")
    @GetMapping(value = "/yxExpress")
    @PreAuthorize("hasAnyRole('ADMIN','YXEXPRESS_ALL','YXEXPRESS_SELECT')")
    public ResponseEntity getYxExpresss(YxExpressQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxExpressService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxExpress")
    @ApiOperation(value = "新增YxExpress")
    @PostMapping(value = "/yxExpress")
    @PreAuthorize("hasAnyRole('ADMIN','YXEXPRESS_ALL','YXEXPRESS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxExpress resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        return new ResponseEntity(yxExpressService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxExpress")
    @ApiOperation(value = "修改YxExpress")
    @PutMapping(value = "/yxExpress")
    @PreAuthorize("hasAnyRole('ADMIN','YXEXPRESS_ALL','YXEXPRESS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxExpress resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxExpressService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxExpress")
    @ApiOperation(value = "删除YxExpress")
    @DeleteMapping(value = "/yxExpress/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXEXPRESS_ALL','YXEXPRESS_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxExpressService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}