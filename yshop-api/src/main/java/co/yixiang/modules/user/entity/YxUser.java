package co.yixiang.modules.user.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import co.yixiang.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hupeng
 * @since 2019-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="YxUser对象", description="用户表")
public class YxUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "用户id")
@TableId(value = "uid", type = IdType.AUTO)
private Integer uid;

@ApiModelProperty(value = "用户账户(跟accout一样)")
private String username;

@ApiModelProperty(value = "用户账号")
private String account;

@ApiModelProperty(value = "用户密码（跟pwd）")
private String password;

@ApiModelProperty(value = "用户密码")
private String pwd;

@ApiModelProperty(value = "真实姓名")
private String realName;

@ApiModelProperty(value = "生日")
private Integer birthday;

@ApiModelProperty(value = "身份证号码")
private String cardId;

@ApiModelProperty(value = "用户备注")
private String mark;

@ApiModelProperty(value = "合伙人id")
private Integer partnerId;

@ApiModelProperty(value = "用户分组id")
private Integer groupId;

@ApiModelProperty(value = "用户昵称")
private String nickname;

@ApiModelProperty(value = "用户头像")
private String avatar;

@ApiModelProperty(value = "手机号码")
private String phone;

@ApiModelProperty(value = "添加时间")
private Integer addTime;

@ApiModelProperty(value = "添加ip")
private String addIp;

@ApiModelProperty(value = "最后一次登录时间")
private Integer lastTime;

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
private Integer spreadUid;

@ApiModelProperty(value = "推广员关联时间")
private Integer spreadTime;

@ApiModelProperty(value = "用户类型")
private String userType;

@ApiModelProperty(value = "是否为推广员")
private Boolean isPromoter;

@ApiModelProperty(value = "用户购买次数")
private Integer payCount;

@ApiModelProperty(value = "下级人数")
private Integer spreadCount;

@ApiModelProperty(value = "清理会员时间")
private Integer cleanTime;

@ApiModelProperty(value = "详细地址")
private String addres;

@ApiModelProperty(value = "管理员编号 ")
private Integer adminid;

@ApiModelProperty(value = "用户登陆类型，h5,wechat,routine")
private String loginType;

}
