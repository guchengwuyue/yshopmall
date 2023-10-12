package co.yixiang.modules.sales.service.vo;

import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author : gzlv 2021/6/28 0:43
 */
@Data
public class StoreAfterSalesVo {

    /** id */
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

    /** 物流单号 */
    private String deliverySn;

    /** 物流名称 */
    private String deliveryName;

    @ApiModelProperty(value = "快递公司编码")
    private String shipperCode;

    /** 状态 0已提交等待平台审核 1平台已审核 等待用户发货/退款 2 用户已发货 3退款成功 */
    private Integer state;

    /** 售后状态-0正常1用户取消2商家拒绝 */
    private Integer salesState;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    /** 逻辑删除 */
    private Integer isDel;

    /** 用户id */
    private Long userId;

    private List<YxStoreCartQueryVo> cartInfo;

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


    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp reviewTime;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp deliveryTime;

    /**
     * 完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp completeTime;

    /**
     * 审核失败时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp auditFailedTime;

    /**
     * 撤销时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp revocationTime;

    /**
     * 关闭售后时间
     */
    private Timestamp closeAfterSaleTime;

}
