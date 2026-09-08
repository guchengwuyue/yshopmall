package co.yixiang.modules.mp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "YxArticleQueryVo对象")
public class YxArticleQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "文章管理ID")
    private Integer id;

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章作者")
    private String author;

    @Schema(description = "文章图片")
    private String imageInput;

    @Schema(description = "文章简介")
    private String synopsis;

    @Schema(description = "文章分享标题")
    private String shareTitle;

    @Schema(description = "文章分享简介")
    private String shareSynopsis;

    @Schema(description = "浏览次数")
    private String visit;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "原文链接")
    private String url;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonProperty(value = "addTime")
    private String createTime;

    @Schema(description = "文章内容")
    private String content;

}
