package co.yixiang.modules.system.rest;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.aop.log.Log;
import co.yixiang.modules.system.domain.Permission;
import co.yixiang.modules.system.service.PermissionService;
import co.yixiang.modules.system.service.dto.PermissionDTO;
import co.yixiang.modules.system.service.dto.PermissionQueryCriteria;
import co.yixiang.modules.system.service.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-12-03
 */
@RestController
@RequestMapping("api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMapper permissionMapper;

    private static final String ENTITY_NAME = "permission";

    /**
     * 返回全部的权限，新增角色时下拉选择
     * @return
     */
    @GetMapping(value = "/permissions/tree")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE','PERMISSION_EDIT','ROLES_SELECT','ROLES_ALL')")
    public ResponseEntity getTree(){
        return new ResponseEntity(permissionService.getPermissionTree(permissionService.findByPid(0L)),HttpStatus.OK);
    }

    @Log("查询权限")
    @GetMapping(value = "/permissions")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
    public ResponseEntity getPermissions(PermissionQueryCriteria criteria){
        List<PermissionDTO> permissionDTOS = permissionService.queryAll(criteria);
        return new ResponseEntity(permissionService.buildTree(permissionDTOS),HttpStatus.OK);
    }

    @Log("新增权限")
    @PostMapping(value = "/permissions")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Permission resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(permissionService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改权限")
    @PutMapping(value = "/permissions")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_EDIT')")
    public ResponseEntity update(@Validated(Permission.Update.class) @RequestBody Permission resources){
        //if(ObjectUtil.isNotNull(resources)) throw new BadRequestException("演示环境禁止操作");
        permissionService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除权限")
    @DeleteMapping(value = "/permissions/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        //if(id > 0) throw new BadRequestException("演示环境禁止操作");
        List<Permission> permissions = permissionService.findByPid(id);
        Set<Permission> permissionSet = new HashSet<>();
        permissionSet.add(permissionMapper.toEntity(permissionService.findById(id)));
        permissionSet = permissionService.getDeletePermission(permissions, permissionSet);
        permissionService.delete(permissionSet);
        return new ResponseEntity(HttpStatus.OK);
    }
}
