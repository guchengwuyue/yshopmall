package co.yixiang.mp.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-10-07
*/
@Entity
@Data
@Table(name="yx_article")
public class YxArticle implements Serializable {

    // 文章管理ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 分类id
    @Column(name = "cid")
    private String cid;

    // 文章标题
    @Column(name = "title",nullable = false)
    private String title;

    // 文章作者
    @Column(name = "author")
    private String author;

    @Column(name = "content")
    private String content;

    // 文章图片
    @Column(name = "image_input",nullable = false)
    private String imageInput;

    // 文章简介
    @Column(name = "synopsis")
    private String synopsis;

    // 文章分享标题
    @Column(name = "share_title")
    private String shareTitle;

    // 文章分享简介
    @Column(name = "share_synopsis")
    private String shareSynopsis;

    // 浏览次数
    @Column(name = "visit")
    private String visit;

    // 排序
    @Column(name = "sort",nullable = false)
    private Integer sort;

    // 原文链接
    @Column(name = "url")
    private String url;

    // 状态
    @Column(name = "status",nullable = false)
    private Integer status;

    // 添加时间
    @Column(name = "add_time",nullable = false)
    private String addTime;

    // 是否隐藏
    @Column(name = "hide",nullable = false)
    private Integer hide;

    // 管理员id
    @Column(name = "admin_id",nullable = false)
    private Integer adminId;

    // 商户id
    @Column(name = "mer_id")
    private Integer merId;

    // 产品关联id
    @Column(name = "product_id",nullable = false)
    private Integer productId;

    // 是否热门(小程序)
    @Column(name = "is_hot",nullable = false)
    private Integer isHot;

    // 是否轮播图(小程序)
    @Column(name = "is_banner",nullable = false)
    private Integer isBanner;

    public void copy(YxArticle source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}