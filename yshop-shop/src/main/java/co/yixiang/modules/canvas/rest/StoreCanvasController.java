/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.canvas.rest;
import java.util.Arrays;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.constant.ShopConstants;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.tools.service.LocalStorageService;
import co.yixiang.modules.tools.service.dto.LocalStorageDto;
import co.yixiang.oss.config.OssProperties;
import co.yixiang.oss.service.OssTemplate;
import co.yixiang.utils.RedisUtils;
import lombok.AllArgsConstructor;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.canvas.domain.StoreCanvas;
import co.yixiang.modules.canvas.service.StoreCanvasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
* @author yshop
* @date 2021-02-01
*/
@AllArgsConstructor
@Api(tags = "画布管理")
@RestController
@RequestMapping("/api/canvas")
public class StoreCanvasController {

    private final StoreCanvasService storeCanvasService;
    private final LocalStorageService localStorageService;
    private final RedisUtils redisUtils;
    private final OssProperties ossProperties;
    private final OssTemplate ossTemplate;

    @ForbidSubmit
    @PostMapping("/saveCanvas")
    @Log("新增或修改画布")
    @ApiOperation("新增或修改画布")
    public ResponseEntity<Object> create(@Validated @RequestBody StoreCanvas resources){
        return new ResponseEntity<>(storeCanvasService.saveOrUpdate(resources),HttpStatus.CREATED);
    }


    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public ResponseEntity<Object> create(@RequestParam(defaultValue = "") String name,
                                         @RequestParam(defaultValue = "") String type,
                                         @RequestParam("file") MultipartFile file) {

        String localUrl = redisUtils.getY(ShopConstants.ADMIN_API_URL);
        if(StrUtil.isBlank(type)){
            localUrl = redisUtils.getY(SystemConfigConstants.API_URL) + "/api";
        }
        String mode = redisUtils.getY(SystemConfigConstants.FILE_STORE_MODE);
        StringBuilder url = new StringBuilder();
        if (ShopCommonEnum.STORE_MODE_1.getValue().toString().equals(mode)) {
            // 存在走本地
            if(StrUtil.isBlank(localUrl)){
                throw new YshopException("本地上传,请先登陆系统配置后台/移动端API地址");
            }
            LocalStorageDto localStorageDTO = localStorageService.create(name, file);
            if ("".equals(url.toString())) {
                url = url.append(localUrl + "/file/" + localStorageDTO.getType() + "/" + localStorageDTO.getRealName());
            } else {
                url = url.append(","+localUrl + "/file/" + localStorageDTO.getType() + "/" + localStorageDTO.getRealName());
            }
        } else {
            // 走oss存储
            String [] originalFilename = file.getOriginalFilename().split("\\.");
            String fileName = ossProperties.getBucketName()+"/file/"+originalFilename[0] + "-" + IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
            try {
                ossTemplate.putObject(ossProperties.getBucketName(), fileName, file.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String fileUrl = String.format(ossProperties.getCustomDomain(), fileName);
            if ("".equals(url.toString())) {
                url = url.append(fileUrl);
            }else{
                url = url.append(","+fileUrl);
            }
        }

        Map<String, Object> map = new HashMap<>(2);
        map.put("errno", 0);
        map.put("link", url);
        return new ResponseEntity(map, HttpStatus.CREATED);
    }


    @GetMapping("/getCanvas")
    @ApiOperation(value = "读取画布数据")
    public ResponseEntity<StoreCanvas> getCanvas(StoreCanvas storeCanvas){
        StoreCanvas canvas = storeCanvasService.lambdaQuery().eq(StoreCanvas::getTerminal, storeCanvas.getTerminal()).one();
        return new ResponseEntity<>(canvas,HttpStatus.OK);
    }

    @Log("删除画布")
    @ApiOperation("删除画布")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeCanvasService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
