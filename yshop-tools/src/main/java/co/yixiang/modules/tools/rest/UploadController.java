/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.annotation.AnonymousAccess;
import co.yixiang.modules.tools.domain.QiniuContent;
import co.yixiang.modules.tools.service.LocalStorageService;
import co.yixiang.modules.tools.service.QiNiuService;
import co.yixiang.modules.tools.service.dto.LocalStorageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hupeng
 * @date 2020-01-09
 */
@Api(tags = "上传统一管理")
@RestController
@RequestMapping("/api/upload")
@SuppressWarnings("unchecked")
public class UploadController {

    @Value("${file.localUrl}")
    private String localUrl;

    private final LocalStorageService localStorageService;

    private final QiNiuService qiNiuService;

    public UploadController(LocalStorageService localStorageService, QiNiuService qiNiuService) {
        this.localStorageService = localStorageService;
        this.qiNiuService = qiNiuService;
    }


    @ApiOperation("上传文件")
    @PostMapping
    @AnonymousAccess
    public ResponseEntity<Object> create(@RequestParam(defaultValue = "") String name, @RequestParam("file") MultipartFile[] files) {
        StringBuilder url = new StringBuilder();
        if (StrUtil.isNotEmpty(localUrl)) { //存在走本地
            for (MultipartFile file : files) {
                LocalStorageDto localStorageDTO = localStorageService.create(name, file);
                if ("".equals(url.toString())) {
                    url = url.append(localUrl + "/file/" + localStorageDTO.getType() + "/" + localStorageDTO.getRealName());
                } else {
                    url = url.append("," + localUrl + "/file/" + localStorageDTO.getType() + "/" + localStorageDTO.getRealName());
                }
            }
        } else {//走七牛云
            for (MultipartFile file : files) {
                QiniuContent qiniuContent = qiNiuService.upload(file, qiNiuService.find());
                if ("".equals(url.toString())) {
                    url = url.append(qiniuContent.getUrl());
                } else {
                    url = url.append("," + qiniuContent.getUrl());
                }
            }
        }

        Map<String, Object> map = new HashMap<>(2);
        map.put("errno", 0);
        map.put("link", url);
        return new ResponseEntity(map, HttpStatus.CREATED);
    }


}
