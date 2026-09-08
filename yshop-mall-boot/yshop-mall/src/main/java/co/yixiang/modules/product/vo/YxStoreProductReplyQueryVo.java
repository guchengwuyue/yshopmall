package co.yixiang.modules.product.vo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "YxStoreProductReplyQueryVo对象")
public class YxStoreProductReplyQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "评论ID")
    private Long id;

    @Schema(description = "产品id")
    private Long productId;

    @Schema(description = "某种商品类型(普通商品、秒杀商品）")
    private String replyType;

    @Schema(description = "商品分数")
    private Integer productScore;

    @Schema(description = "服务分数")
    private Integer serviceScore;

    @Schema(description = "评论内容")
    private String comment;

    @Schema(description = "评论图片")
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


    @Schema(description = "管理员回复内容")
    private String merchantReplyContent;

    @Schema(description = "管理员回复时间")
    private Date merchantReplyTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @Schema(description = "评价星星数")
    private String star;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "商品sku")
    private String sku;

    @JsonIgnore
    private String cartInfo;


}
