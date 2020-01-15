package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2019-10-14
*/
@Entity
@Data
@Table(name="yx_store_order")
public class YxStoreOrder implements Serializable {

    // 订单ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 订单号
    @Column(name = "order_id",nullable = false)
    private String orderId;

    // 订单号
    @Column(name = "extend_order_id",nullable = true)
    private String extendOrderId;


    // 用户id
    @Column(name = "uid",nullable = false)
    private Integer uid;

    // 用户姓名
    @Column(name = "real_name",nullable = false)
    private String realName;

    // 用户电话
    @Column(name = "user_phone",nullable = false)
    private String userPhone;

    // 详细地址
    @Column(name = "user_address",nullable = false)
    private String userAddress;

    // 购物车id
    @Column(name = "cart_id",nullable = false)
    private String cartId;

    // 运费金额
    @Column(name = "freight_price",nullable = false)
    private BigDecimal freightPrice;

    // 订单商品总数
    @Column(name = "total_num",nullable = false)
    private Integer totalNum;

    // 订单总价
    @Column(name = "total_price",nullable = false)
    private BigDecimal totalPrice;

    // 邮费
    @Column(name = "total_postage",nullable = false)
    private BigDecimal totalPostage;

    // 实际支付金额
    @Column(name = "pay_price",nullable = false)
    private BigDecimal payPrice;

    // 支付邮费
    @Column(name = "pay_postage",nullable = false)
    private BigDecimal payPostage;

    // 抵扣金额
    @Column(name = "deduction_price",nullable = false)
    private BigDecimal deductionPrice;

    // 优惠券id
    @Column(name = "coupon_id",nullable = false)
    private Integer couponId;

    // 优惠券金额
    @Column(name = "coupon_price",nullable = false)
    private BigDecimal couponPrice;

    // 支付状态
    @Column(name = "paid",nullable = false)
    private Integer paid;

    // 支付时间
    @Column(name = "pay_time")
    private Integer payTime;

    // 支付方式
    @Column(name = "pay_type",nullable = false)
    private String payType;

    // 创建时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    // 订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：待评价；-1：已退款）
    @Column(name = "status",nullable = false)
    private Integer status;

    // 0 未退款 1 申请中 2 已退款
    @Column(name = "refund_status",nullable = false)
    private Integer refundStatus;

    // 退款图片
    @Column(name = "refund_reason_wap_img")
    private String refundReasonWapImg;

    // 退款用户说明
    @Column(name = "refund_reason_wap_explain")
    private String refundReasonWapExplain;

    // 退款时间
    @Column(name = "refund_reason_time")
    private Integer refundReasonTime;

    // 前台退款原因
    @Column(name = "refund_reason_wap")
    private String refundReasonWap;

    // 不退款的理由
    @Column(name = "refund_reason")
    private String refundReason;

    // 退款金额
    @Column(name = "refund_price",nullable = false)
    private BigDecimal refundPrice;

    // 快递名称/送货人姓名
    @Column(name = "delivery_name")
    //@NotBlank(message = "请选择快递公司")
    private String deliveryName;

    @Column(name = "delivery_sn")
    private String deliverySn;


    // 发货类型
    @Column(name = "delivery_type")
    private String deliveryType;

    // 快递单号/手机号
    @Column(name = "delivery_id")
    //@NotBlank(message = "快递单号不能为空")
    private String deliveryId;

    // 消费赚取积分
    @Column(name = "gain_integral",nullable = false)
    private BigDecimal gainIntegral;

    // 使用积分
    @Column(name = "use_integral",nullable = false)
    private BigDecimal useIntegral;

    // 给用户退了多少积分
    @Column(name = "back_integral")
    private BigDecimal backIntegral;

    // 备注
    @Column(name = "mark",nullable = false)
    private String mark;

    // 是否删除
    @Column(name = "is_del",nullable = false)
    private Integer isDel;

    // 唯一id(md5加密)类似id
    @Column(name = "`unique`",unique = true,nullable = false)
    private String unique;

    // 管理员备注
    @Column(name = "remark")
    private String remark;

    // 商户ID
    @Column(name = "mer_id",nullable = false)
    private Integer merId;

    @Column(name = "is_mer_check",nullable = false)
    private Integer isMerCheck;

    // 拼团产品id0一般产品
    @Column(name = "combination_id")
    private Integer combinationId;

    // 拼团id 0没有拼团
    @Column(name = "pink_id",nullable = false)
    private Integer pinkId;

    // 成本价
    @Column(name = "cost",nullable = false)
    private BigDecimal cost;

    // 秒杀产品ID
    @Column(name = "seckill_id",nullable = false)
    private Integer seckillId;

    // 砍价id
    @Column(name = "bargain_id")
    private Integer bargainId;

    // 核销码
    @Column(name = "verify_code",nullable = false)
    private String verifyCode;

    // 门店id
    @Column(name = "store_id",nullable = false)
    private Integer storeId;

    // 配送方式 1=快递 ，2=门店自提
    @Column(name = "shipping_type",nullable = false)
    private Integer shippingType;

    // 支付渠道(0微信公众号1微信小程序)
    @Column(name = "is_channel")
    private Integer isChannel;

    @Column(name = "is_remind")
    private Integer isRemind;

    @Column(name = "is_system_del")
    private Integer isSystemDel;

    public void copy(YxStoreOrder source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}