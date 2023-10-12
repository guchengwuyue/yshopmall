package co.yixiang.modules.user.vo;


import co.yixiang.modules.order.vo.UserOrderCountVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "YxUserQueryVo对象", description = "用户表查询参数")
public class YxUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long uid;

    @ApiModelProperty(value = "用户账户(跟accout一样)")
    private String username;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "优惠券数量")
    private Long couponCount = 0L;

    @ApiModelProperty(value = "订单详情数据")
    private UserOrderCountVo orderStatusNum;


    private Integer statu;

    @ApiModelProperty(value = "总的签到天数")
    private Long sumSignDay;

    @ApiModelProperty(value = "当天是否签到")
    private Boolean isDaySign;

    @ApiModelProperty(value = "昨天是否签到")
    private Boolean isYesterDaySign;

    @ApiModelProperty(value = "核销权限")
    private Boolean checkStatus;



    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "生日")
    private Integer birthday;

    @ApiModelProperty(value = "身份证号码")
    @JsonIgnore
    private String cardId;

    @ApiModelProperty(value = "用户备注")
    private String mark;


    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "添加ip")
    private String addIp;

    @ApiModelProperty(value = "最后一次登录ip")
    private String lastIp;

    @ApiModelProperty(value = "用户余额")
    private BigDecimal nowMoney;

    @ApiModelProperty(value = "佣金金额")
    private BigDecimal brokeragePrice;

    @ApiModelProperty(value = "用户剩余积分")
    private BigDecimal integral;

    @ApiModelProperty(value = "连续签到天数")
    private Integer signNum;

    @ApiModelProperty(value = "1为正常，0为禁止")
    private Boolean status;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "推广元id")
    private Long spreadUid;

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "是否为推广员")
    private Integer isPromoter;

    @ApiModelProperty(value = "用户购买次数")
    private Integer payCount;

    @ApiModelProperty(value = "下级人数")
    private Integer spreadCount;

    @ApiModelProperty(value = "详细地址")
    private String addres;

    @ApiModelProperty(value = "管理员编号 ")
    private Integer adminid;

    @ApiModelProperty(value = "用户登陆类型，h5,wechat,routine")
    private String loginType;

    @ApiModelProperty(value = "是否会员")
    private Boolean vip;

    @ApiModelProperty(value = "会员ID")
    private Integer vipId;

    @ApiModelProperty(value = "会员图标")
    private String vipIcon;

    @ApiModelProperty(value = "会员名称")
    private String vipName;

}
