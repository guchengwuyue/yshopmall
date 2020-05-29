/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.mp.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
@TableName("yx_article")
public class YxArticle implements Serializable {

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


    /** 添加时间 */
    @TableField(fill= FieldFill.INSERT)
    private String addTime;


    /** 是否隐藏 */
    private Integer hide;


    /** 管理员id */
    private Integer adminId;


    /** 商户id */
    private Integer merId;


    /** 产品关联id */
    private Integer productId;


    /** 是否热门(小程序) */
    private Integer isHot;


    /** 是否轮播图(小程序) */
    private Integer isBanner;


    public void copy(YxArticle source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
