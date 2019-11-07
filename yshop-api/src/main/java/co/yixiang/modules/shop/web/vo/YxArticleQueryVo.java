package co.yixiang.modules.shop.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 文章管理表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-02
 */
@Data
@ApiModel(value="YxArticleQueryVo对象", description="文章管理表查询参数")
public class YxArticleQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "文章管理ID")
private Integer id;

@ApiModelProperty(value = "分类id")
private String cid;

@ApiModelProperty(value = "文章标题")
private String title;

@ApiModelProperty(value = "文章作者")
private String author;

@ApiModelProperty(value = "文章图片")
private String imageInput;

@ApiModelProperty(value = "文章简介")
private String synopsis;

@ApiModelProperty(value = "文章分享标题")
private String shareTitle;

@ApiModelProperty(value = "文章分享简介")
private String shareSynopsis;

@ApiModelProperty(value = "浏览次数")
private String visit;

@ApiModelProperty(value = "排序")
private Integer sort;

@ApiModelProperty(value = "原文链接")
private String url;

@ApiModelProperty(value = "状态")
private Boolean status;

@ApiModelProperty(value = "添加时间")
private String addTime;

@ApiModelProperty(value = "是否隐藏")
private Boolean hide;

@ApiModelProperty(value = "管理员id")
private Integer adminId;

@ApiModelProperty(value = "商户id")
private Integer merId;

@ApiModelProperty(value = "产品关联id")
private Integer productId;

@ApiModelProperty(value = "是否热门(小程序)")
private Boolean isHot;

@ApiModelProperty(value = "是否轮播图(小程序)")
private Boolean isBanner;

}