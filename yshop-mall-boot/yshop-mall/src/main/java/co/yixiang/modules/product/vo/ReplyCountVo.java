package co.yixiang.modules.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName ReplyCount
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/4
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyCountVo implements Serializable {

    @Schema(description = "总的评论数")
    private Long sumCount;

    @Schema(description = "好评数")
    private Long goodCount;

    @Schema(description = "中评数")
    private Long inCount;

    @Schema(description = "差评数")
    private Long poorCount;

    @Schema(description = "好评率")
    private String replyChance;

    @Schema(description = "好评星星数")
    private String replySstar;

}
