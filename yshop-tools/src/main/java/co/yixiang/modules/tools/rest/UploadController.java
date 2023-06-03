/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.rest;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.oss.config.OssProperties;
import co.yixiang.oss.service.OssTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    private final OssProperties ossProperties;
    private final OssTemplate ossTemplate;
    public UploadController(OssProperties ossProperties, OssTemplate ossTemplate) {
        this.ossProperties = ossProperties;
        this.ossTemplate = ossTemplate;
    }


    @ApiOperation("上传文件")
    @PostMapping
    public ResponseEntity<Object> create(@RequestParam("file") MultipartFile file)  {
        String [] originalFilename = file.getOriginalFilename().split("\\.");
        String fileName = ossProperties.getBucketName()+"/file/"+originalFilename[0] + "-" +IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
        try {
            ossTemplate.putObject(ossProperties.getBucketName(), fileName, file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileUrl = String.format(ossProperties.getCustomDomain(), fileName);

        Map<String, Object> map = new HashMap<>(2);
        map.put("errno", 0);
        map.put("link", fileUrl);
        return new ResponseEntity(map, HttpStatus.CREATED);
    }


}
