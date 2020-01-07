package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.activity.service.dto.YxStorePinkQueryCriteria;
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
* @date 2019-11-18
*/
@Api(tags = "拼团记录管理")
@RestController
@RequestMapping("api")
public class YxStorePinkController {

    @Autowired
    private YxStorePinkService yxStorePinkService;

    @Log("查询记录")
    @ApiOperation(value = "查询记录")
    @GetMapping(value = "/yxStorePink")
    @PreAuthorize("@el.check('admin','YXSTOREPINK_ALL','YXSTOREPINK_SELECT')")
    public ResponseEntity getYxStorePinks(YxStorePinkQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStorePinkService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxStorePink")
    @ApiOperation(value = "新增YxStorePink")
    @PostMapping(value = "/yxStorePink")
    @PreAuthorize("@el.check('admin','YXSTOREPINK_ALL','YXSTOREPINK_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStorePink resources){
        return new ResponseEntity(yxStorePinkService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxStorePink")
    @ApiOperation(value = "修改YxStorePink")
    @PutMapping(value = "/yxStorePink")
    @PreAuthorize("@el.check('admin','YXSTOREPINK_ALL','YXSTOREPINK_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStorePink resources){
        yxStorePinkService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxStorePink")
    @ApiOperation(value = "删除YxStorePink")
    @DeleteMapping(value = "/yxStorePink/{id}")
    @PreAuthorize("@el.check('admin','YXSTOREPINK_ALL','YXSTOREPINK_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxStorePinkService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}