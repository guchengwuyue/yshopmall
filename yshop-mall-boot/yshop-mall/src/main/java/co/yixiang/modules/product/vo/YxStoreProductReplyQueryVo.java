package co.yixiang.modules.product.vo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-23
 */
@Data
@ApiModel(value = "YxStoreProductReplyQueryVo对象", description = "评论表查询参数")
public class YxStoreProductReplyQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    private Long id;

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "某种商品类型(普通商品、秒杀商品）")
    private String replyType;

    @ApiModelProperty(value = "商品分数")
    private Integer productScore;

    @ApiModelProperty(value = "服务分数")
    private Integer serviceScore;

    @ApiModelProperty(value = "评论内容")
    private String comment;

    @ApiModelProperty(value = "评论图片")
    private String[] pics;

    private String pictures;

    private String[] picturesArr;

    public String[] getPicturesArr() {
        if(StrUtil.isNotEmpty(pictures)){
            return pictures.split(",");
        }else{
            return new String[]{};
        }

    }


    @ApiModelProperty(value = "管理员回复内容")
    private String merchantReplyContent;

    @ApiModelProperty(value = "管理员回复时间")
    private Date merchantReplyTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "评价星星数")
    private String star;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "商品sku")
    private String sku;

    @JsonIgnore
    private String cartInfo;


}
