package co.yixiang.modules.customer.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单通知用户表 接收订单通知的用户 查询结果对象
 * </p>
 *
 * @author LionCity
 * @date 2020-04-02
 */
@Data
@Schema(description = "订单通知用户表")
public class YzCustomerVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "openId")
    private String openId;

    @Schema(description = "备注")
    private String remark;


    @Schema(description = "创建时间 创建时间")
    private Date createTime;

    @Schema(description = "更新时间 最后更新时间")
    private Date updateTime;

}