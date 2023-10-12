package co.yixiang.modules.activity.domain;


import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * <p>
 * 砍价用户帮助表
 * </p>
 *
 * @author hupeng
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "YxStoreBargainUserHelp对象", description = "砍价用户帮助表")
public class YxStoreBargainUserHelp extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "砍价用户帮助表ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帮助的用户id")
    private Long uid;

    @ApiModelProperty(value = "砍价产品ID")
    private Long bargainId;

    @ApiModelProperty(value = "用户参与砍价表id")
    private Long bargainUserId;

    @ApiModelProperty(value = "帮助砍价多少金额")
    private BigDecimal price;

}
