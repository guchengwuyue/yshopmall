package co.yixiang.modules.order.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @ClassName ProductReplyParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/23
 **/
@Getter
@Setter
public class ProductReplyParam {

    @NotBlank(message = "评论不能为空")
    @Size(min = 1, max = 200,message = "长度超过了限制")
    @Schema(description = "商品评论内容")
    private String comment;

    @Schema(description = "商品评论图片地址")
    private String pics;

    @NotBlank(message = "请为商品评分")
    @Schema(description = "商品评分")
    private String productScore;

    @NotBlank(message = "请为商品评分")
    @Schema(description = "服务评分")
    private String serviceScore;

    @NotBlank(message = "参数有误")
    @Schema(description = "订单唯一值")
    private String unique;
}
