/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.tools.domain.Picture;
import co.yixiang.modules.tools.service.PictureService;
import co.yixiang.modules.tools.service.dto.PictureDto;
import co.yixiang.modules.tools.service.dto.PictureQueryCriteria;
import co.yixiang.modules.tools.service.mapper.PictureMapper;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.TranslatorUtil;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.utils.YshopConstant;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Service
//@AllArgsConstructor
//@CacheConfig(cacheNames = "picture")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PictureServiceImpl extends BaseServiceImpl<PictureMapper, Picture> implements PictureService {

    private final IGenerator generator;

    @Value("${smms.token}")
    private String token;


    private static final String SUCCESS = "success";

    private static final String CODE = "code";

    private static final String MSG = "message";

    public PictureServiceImpl(IGenerator generator) {
        this.generator = generator;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(PictureQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<Picture> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), PictureDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<Picture> queryAll(PictureQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(Picture.class, criteria));
    }


    @Override
    public void download(List<PictureDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PictureDto picture : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("上传日期", picture.getCreateTime());
            map.put("删除的URL", picture.getDeleteUrl());
            map.put("图片名称", picture.getFilename());
            map.put("图片高度", picture.getHeight());
            map.put("图片大小", picture.getSize());
            map.put("图片地址", picture.getUrl());
            map.put("用户名称", picture.getUsername());
            map.put("图片宽度", picture.getWidth());
            map.put("文件的MD5值", picture.getMd5code());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Picture upload(MultipartFile multipartFile, String username) {
        File file = FileUtil.toFile(multipartFile);
        // 验证是否重复上传
        Picture picture = this.getOne(new LambdaQueryWrapper<Picture>().eq(Picture::getMd5code, FileUtil.getMd5(file)));
        if (picture != null) {
            return picture;
        }
        HashMap<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("smfile", file);
        // 上传文件
        String result = HttpRequest.post(YshopConstant.Url.SM_MS_URL + "/v2/upload")
                .header("Authorization", token)
                .form(paramMap)
                .timeout(20000)
                .execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (!jsonObject.get(CODE).toString().equals(SUCCESS)) {
            throw new BadRequestException(TranslatorUtil.translate(jsonObject.get(MSG).toString()));
        }
        picture = JSON.parseObject(jsonObject.get("data").toString(), Picture.class);
        picture.setSize(FileUtil.getSize(Integer.parseInt(picture.getSize())));
        picture.setUsername(username);
        picture.setMd5code(FileUtil.getMd5(file));
        picture.setFilename(FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) + "." + FileUtil.getExtensionName(multipartFile.getOriginalFilename()));
        this.save(picture);
        //删除临时文件
        FileUtil.del(file);
        return picture;

    }

    @Override
    public Picture findById(Long id) {
        Picture picture = this.getById(id);
        ValidationUtil.isNull(picture.getId(), "Picture", "id", id);
        return picture;
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            Picture picture = findById(id);
            try {
                HttpUtil.get(picture.getDeleteUrl());
                this.removeById(id);
            } catch (Exception e) {
                this.removeById(id);
            }
        }
    }

    @Override
    public void synchronize() {
        //链式构建请求
        String result = HttpRequest.get(YshopConstant.Url.SM_MS_URL + "/v2/upload_history")
                //头信息，多个头信息多次调用此方法即可
                .header("Authorization", token)
                .timeout(20000)
                .execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        List<Picture> pictures = JSON.parseArray(jsonObject.get("data").toString(), Picture.class);
        for (Picture picture : pictures) {
            if (this.getOne(new LambdaQueryWrapper<Picture>().eq(Picture::getUrl, picture.getUrl())) == null) {
                picture.setSize(FileUtil.getSize(Integer.parseInt(picture.getSize())));
                picture.setUsername("System Sync");
                picture.setMd5code(null);
                this.save(picture);
            }
        }
    }
}
