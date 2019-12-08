package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxSystemUserTask;
import co.yixiang.modules.shop.service.YxSystemUserTaskService;
import co.yixiang.modules.shop.service.dto.YxSystemUserTaskQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-12-04
*/
@Api(tags = "YxSystemUserTask管理")
@RestController
@RequestMapping("api")
public class YxSystemUserTaskController {

    @Autowired
    private YxSystemUserTaskService yxSystemUserTaskService;

    @Log("查询YxSystemUserTask")
    @ApiOperation(value = "查询YxSystemUserTask")
    @GetMapping(value = "/yxSystemUserTask")
    @PreAuthorize("hasAnyRole('ADMIN','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_SELECT')")
    public ResponseEntity getYxSystemUserTasks(YxSystemUserTaskQueryCriteria criteria,
                                               Pageable pageable){
        Sort sort = new Sort(Sort.Direction.ASC, "levelId");
        Pageable pageableT = new PageRequest(pageable.getPageNumber(),
                pageable.getPageSize(),
                sort);
        return new ResponseEntity(yxSystemUserTaskService.queryAll(criteria,pageableT),
                HttpStatus.OK);
    }

    @Log("新增YxSystemUserTask")
    @ApiOperation(value = "新增YxSystemUserTask")
    @PostMapping(value = "/yxSystemUserTask")
    @PreAuthorize("hasAnyRole('ADMIN','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxSystemUserTask resources){
        return new ResponseEntity(yxSystemUserTaskService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxSystemUserTask")
    @ApiOperation(value = "修改YxSystemUserTask")
    @PutMapping(value = "/yxSystemUserTask")
    @PreAuthorize("hasAnyRole('ADMIN','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxSystemUserTask resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxSystemUserTaskService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxSystemUserTask")
    @ApiOperation(value = "删除YxSystemUserTask")
    @DeleteMapping(value = "/yxSystemUserTask/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxSystemUserTaskService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}