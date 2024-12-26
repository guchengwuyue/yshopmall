package co.yixiang.modules.sales.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author : gzlv 2021/6/27 15:42
 */
@Data
@TableName("yx_store_after_sales")
public class StoreAfterSales {

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 订单号 */
    private String orderCode;

    /** 退款金额 */
    private BigDecimal refundAmount;

    /** 服务类型0仅退款1退货退款 */
    private Integer serviceType;

    /** 申请原因 */
    private String reasons;

    /** 说明 */
    private String explains;

    /** 说明图片->多个用逗号分割 */
    private String explainImg;

    @ApiModelProperty(value = "快递公司编码")
    private String shipperCode;

    /** 物流单号 */
    private String deliverySn;

    /** 物流名称 */
    private String deliveryName;

    /** 状态 0已提交等待平台审核 1平台已审核 等待用户发货/退款 2 用户已发货 3已完成 */
    private Integer state;

    /** 售后状态-0正常1用户取消2商家拒绝 */
    private Integer salesState;

    /** 添加时间 */
    private Timestamp createTime;

    /** 逻辑删除 */
    private Integer isDel;

    /** 用户id */
    private Long userId;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 地址
     */
    private String address;
}
