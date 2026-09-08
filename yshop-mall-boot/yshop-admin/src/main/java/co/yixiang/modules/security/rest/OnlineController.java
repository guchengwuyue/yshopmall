/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.security.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.security.service.OnlineUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author hupeng
 */
@RestController
@RequestMapping("/auth/online")
@Tag(name = "系统：在线用户管理")
public class OnlineController {

    private final OnlineUserService onlineUserService;

    public OnlineController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @Operation(summary = "查询在线用户")
    @GetMapping
    @PreAuthorize("@el.check('auth_online')")
    public ResponseEntity<Object> getAll(@RequestParam(value = "filter",defaultValue = "") String filter,
                                         @RequestParam(value = "type",defaultValue = "0") int type,
                                         Pageable pageable){
        return new ResponseEntity<>(onlineUserService.getAll(filter, type,pageable),HttpStatus.OK);
    }

    @Log("导出数据")
    @Operation(summary = "导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check()")
    public void download(HttpServletResponse response,
                         @RequestParam(value = "filter",defaultValue = "") String filter,
                         @RequestParam(value = "type",defaultValue = "0") int type) throws IOException {
        onlineUserService.download(onlineUserService.getAll(filter,type), response);
    }

    @ForbidSubmit
    @Operation(summary = "踢出用户")
    @DeleteMapping
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> delete(@RequestBody Set<String> keys) throws Exception {
        for (String key : keys) {
            onlineUserService.kickOut(key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ForbidSubmit
    @Operation(summary = "踢出移动端用户")
    @PostMapping("/delete" )
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> deletet(@RequestBody Set<String> keys) throws Exception {
        for (String key : keys) {
            onlineUserService.kickOutT(key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
