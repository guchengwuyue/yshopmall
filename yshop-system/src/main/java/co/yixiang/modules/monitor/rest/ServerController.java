package co.yixiang.modules.monitor.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.monitor.domain.Server;
import co.yixiang.modules.monitor.service.ServerService;
import co.yixiang.modules.monitor.service.dto.ServerQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
* @author Zhang houying
* @date 2019-11-03
*/
@Api(tags = "服务监控管理")
@RestController
@RequestMapping("/api/server")
public class ServerController {

    private final ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping
    @Log("查询服务监控")
    @ApiOperation("查询服务监控")
    @PreAuthorize("@el.check('admin','server:list')")
    public ResponseEntity<Object> getServers(ServerQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(serverService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增服务监控")
    @ApiOperation("新增服务监控")
    @PreAuthorize("@el.check('admin','server:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Server resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        return new ResponseEntity<>(serverService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改服务监控")
    @ApiOperation("修改服务监控")
    @PreAuthorize("@el.check('admin','server:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Server resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        serverService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除服务监控")
    @ApiOperation("删除服务监控")
    @PreAuthorize("@el.check('admin','server:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Integer> ids){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        serverService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
