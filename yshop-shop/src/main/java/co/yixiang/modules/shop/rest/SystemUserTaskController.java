package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxSystemUserTask;
import co.yixiang.modules.shop.service.YxSystemUserTaskService;
import co.yixiang.modules.shop.service.dto.YxSystemUserTaskQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author hupeng
* @date 2019-12-04
*/
@Api(tags = "商城:用户任务管理")
@RestController
@RequestMapping("api")
public class SystemUserTaskController {

    private final YxSystemUserTaskService yxSystemUserTaskService;

    public SystemUserTaskController(YxSystemUserTaskService yxSystemUserTaskService) {
        this.yxSystemUserTaskService = yxSystemUserTaskService;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxSystemUserTask")
    @PreAuthorize("@el.check('admin','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_SELECT')")
    public ResponseEntity getYxSystemUserTasks(YxSystemUserTaskQueryCriteria criteria,
                                               Pageable pageable){
        Sort sort = new Sort(Sort.Direction.ASC, "levelId");
        Pageable pageableT = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                sort);
        return new ResponseEntity(yxSystemUserTaskService.queryAll(criteria,pageableT),
                HttpStatus.OK);
    }

    @Log("新增")
    @ApiOperation(value = "新增")
    @PostMapping(value = "/yxSystemUserTask")
    @PreAuthorize("@el.check('admin','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxSystemUserTask resources){
        return new ResponseEntity(yxSystemUserTaskService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改")
    @ApiOperation(value = "修改")
    @PutMapping(value = "/yxSystemUserTask")
    @PreAuthorize("@el.check('admin','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxSystemUserTask resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxSystemUserTaskService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxSystemUserTask/{id}")
    @PreAuthorize("@el.check('admin','YXSYSTEMUSERTASK_ALL','YXSYSTEMUSERTASK_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxSystemUserTaskService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}