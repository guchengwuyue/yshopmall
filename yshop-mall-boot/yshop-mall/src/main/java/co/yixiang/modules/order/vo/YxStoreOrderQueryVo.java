package co.yixiang.modules.order.vo;


import cn.hutool.core.util.StrUtil;
import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import co.yixiang.modules.order.service.dto.StatusDto;
import co.yixiang.modules.product.vo.YxSystemStoreQueryVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@Schema(description = "YxStoreOrderQueryVo对象")
public class YxStoreOrderQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单ID")
    private Long id;

    @Schema(description = "订单号")
    private String orderId;


    private String extendOrderId;

    @Schema(description = "用户id")
    private Long uid;

    @Schema(description = "用户姓名")
    private String realName;

    @Schema(description = "用户电话")
    private String userPhone;

    @Schema(description = "详细地址")
    private String userAddress;

    @Schema(description = "购物车id")
    private String cartId;

    private List<YxStoreCartQueryVo> cartInfo;

    private StatusDto _status;

    @Schema(description = "运费金额")
    private BigDecimal freightPrice;

    @Schema(description = "订单商品总数")
    private Integer totalNum;

    @Schema(description = "订单总价")
    private BigDecimal totalPrice;

    @Schema(description = "邮费")
    private BigDecimal totalPostage;

    @Schema(description = "实际支付金额")
    private BigDecimal payPrice;

    @Schema(description = "实际支付积分")
    private BigDecimal payIntegral;

    @Schema(description = "支付邮费")
    private BigDecimal payPostage;

    @Schema(description = "抵扣金额")
    private BigDecimal deductionPrice;

    @Schema(description = "优惠券id")
    private Integer couponId;

    @Schema(description = "优惠券金额")
    private BigDecimal couponPrice;

    @Schema(description = "支付状态")
    private Integer paid;

    @Schema(description = "支付时间")
    private Date payTime;

    @Schema(description = "支付方式")
    private String payType;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @Schema(description = "订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：待评价；-1：已退款）")
    private Integer status;

    @Schema(description = "0 未退款 1 申请中 2 已退款")
    private Integer refundStatus;

    @Schema(description = "退款图片")
    private String refundReasonWapImg;

    @Schema(description = "退款用户说明")
    private String refundReasonWapExplain;

    @Schema(description = "退款时间")
    private Date refundReasonTime;

    @Schema(description = "前台退款原因")
    private String refundReasonWap;

    @Schema(description = "不退款的理由")
    private String refundReason;

    @Schema(description = "退款金额")
    private BigDecimal refundPrice;

    @Schema(description = "快递名称/送货人姓名")
    private String deliveryName;

    private String deliverySn;

    @Schema(description = "发货类型")
    private String deliveryType;

    public String getDeliveryType() {
        if(StrUtil.isBlank(deliveryType)) {
            return "express";
        }
        return deliveryType;
    }


    @Schema(description = "快递单号/手机号")
    private String deliveryId;

    @Schema(description = "消费赚取积分")
    private BigDecimal gainIntegral;

    @Schema(description = "使用积分")
    private BigDecimal useIntegral;

    @Schema(description = "给用户退了多少积分")
    private BigDecimal backIntegral;

    @Schema(description = "备注")
    private String mark;

    @Schema(description = "唯一id(md5加密)类似id")
    private String unique;

    @Schema(description = "管理员备注")
    private String remark;

    @Schema(description = "商户ID")
    private Integer merId;


    @Schema(description = "拼团产品id0一般产品")
    private Long combinationId;

    @Schema(description = "拼团id 0没有拼团")
    private Long pinkId;

    @Schema(description = "成本价")
    private BigDecimal cost;

    @Schema(description = "秒杀产品ID")
    private Long seckillId;


    @Schema(description = "核销码")
    private String verifyCode;

    @Schema(description = "门店id")
    private Integer storeId;

    @Schema(description = "配送方式 1=快递 ，2=门店自提")
    private Integer shippingType;


    @Schema(description = "支付渠道(0微信公众号1微信小程序)")
    private Integer isChannel;

    private Integer isSystemDel;

    @Schema(description = "门店信息与二维码链接")
    private String code;

    @Schema(description = "腾讯地图key")
    private String mapKey;

    @Schema(description = "门店信息")
    private YxSystemStoreQueryVo systemStore;

}
