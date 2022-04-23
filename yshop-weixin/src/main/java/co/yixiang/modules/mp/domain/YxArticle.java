/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Data
@TableName("yx_article")
public class YxArticle extends BaseDomain {

    /** 文章管理ID */
    @TableId
    private Integer id;


    /** 分类id */
    private String cid;


    /** 文章标题 */
    @NotBlank(message = "请输入文章标题")
    private String title;


    /** 文章作者 */
    @NotBlank(message = "请输入文章作者")
    private String author;


    /** 文章图片 */
    @NotBlank(message = "请上传文章图片")
    private String imageInput;


    /** 文章简介 */
    @NotBlank(message = "请填写文章简介")
    private String synopsis;


    @NotBlank(message = "请填写文章详情")
    private String content;


    /** 文章分享标题 */
    private String shareTitle;


    /** 文章分享简介 */
    private String shareSynopsis;


    /** 浏览次数 */
    private String visit;


    /** 排序 */
    private Integer sort;


    /** 原文链接 */
    private String url;


    /** 状态 */
    private Integer status;



    public void copy(YxArticle source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
