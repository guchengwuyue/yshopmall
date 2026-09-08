package co.yixiang.modules.user.vo;


import co.yixiang.modules.order.vo.UserOrderCountVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-16
 */
@Data
@Schema(description = "YxUserQueryVo对象")
public class YxUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Long uid;

    @Schema(description = "用户账户(跟accout一样)")
    private String username;

    @Schema(description = "用户账号")
    private String account;

    @Schema(description = "优惠券数量")
    private Long couponCount = 0L;

    @Schema(description = "订单详情数据")
    private UserOrderCountVo orderStatusNum;


    private Integer statu;

    @Schema(description = "总的签到天数")
    private Long sumSignDay;

    @Schema(description = "当天是否签到")
    private Boolean isDaySign;

    @Schema(description = "昨天是否签到")
    private Boolean isYesterDaySign;

    @Schema(description = "核销权限")
    private Boolean checkStatus;



    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "生日")
    private Integer birthday;

    @Schema(description = "身份证号码")
    @JsonIgnore
    private String cardId;

    @Schema(description = "用户备注")
    private String mark;


    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "添加ip")
    private String addIp;

    @Schema(description = "最后一次登录ip")
    private String lastIp;

    @Schema(description = "用户余额")
    private BigDecimal nowMoney;

    @Schema(description = "佣金金额")
    private BigDecimal brokeragePrice;

    @Schema(description = "用户剩余积分")
    private BigDecimal integral;

    @Schema(description = "连续签到天数")
    private Integer signNum;

    @Schema(description = "1为正常，0为禁止")
    private Boolean status;

    @Schema(description = "等级")
    private Integer level;

    @Schema(description = "推广元id")
    private Long spreadUid;

    @Schema(description = "用户类型")
    private String userType;

    @Schema(description = "是否为推广员")
    private Integer isPromoter;

    @Schema(description = "用户购买次数")
    private Integer payCount;

    @Schema(description = "下级人数")
    private Integer spreadCount;

    @Schema(description = "详细地址")
    private String addres;

    @Schema(description = "管理员编号 ")
    private Integer adminid;

    @Schema(description = "用户登陆类型，h5,wechat,routine")
    private String loginType;

    @Schema(description = "是否会员")
    private Boolean vip;

    @Schema(description = "会员ID")
    private Integer vipId;

    @Schema(description = "会员图标")
    private String vipIcon;

    @Schema(description = "会员名称")
    private String vipName;

}
