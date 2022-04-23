/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.rest;

import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.tools.service.LocalStorageService;
import co.yixiang.modules.tools.service.dto.LocalStorageDto;
import co.yixiang.modules.tools.service.dto.LocalStorageQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@AllArgsConstructor
@Api(tags = "文件管理")
@RestController
@RequestMapping("/api/localStorage")
public class LocalStorageController {

    private final LocalStorageService localStorageService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','localStorage:list')")
    public void download(HttpServletResponse response, LocalStorageQueryCriteria criteria) throws IOException {
        localStorageService.download(generator.convert(localStorageService.queryAll(criteria), LocalStorageDto.class), response);
    }

    @GetMapping
    @Log("查询文件")
    @ApiOperation("查询文件")
    @PreAuthorize("@el.check('admin','localStorage:list')")
    public ResponseEntity<Object> getLocalStorages(LocalStorageQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(localStorageService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增文件")
    @ApiOperation("新增文件")
    @PreAuthorize("@el.check('admin','localStorage:add')")
    public ResponseEntity<Object> create(@RequestParam String name, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(localStorageService.create(name, file), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改文件")
    @ApiOperation("修改文件")
    @PreAuthorize("@el.check('admin','localStorage:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody LocalStorageDto resources) {
        localStorageService.updateLocalStorage(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除文件")
    @ApiOperation("删除文件")
    @PreAuthorize("@el.check('admin','localStorage:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        Arrays.asList(ids).forEach(id -> {
            localStorageService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
