package co.yixiang.modules.mp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文章管理表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-02
 */
@Data
@ApiModel(value = "YxArticleQueryVo对象", description = "文章管理表查询参数")
public class YxArticleQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章管理ID")
    private Integer id;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonProperty(value = "addTime")
    private String createTime;

    @ApiModelProperty(value = "文章内容")
    private String content;

}
