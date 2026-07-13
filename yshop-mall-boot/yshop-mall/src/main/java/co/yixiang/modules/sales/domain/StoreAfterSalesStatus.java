package co.yixiang.modules.sales.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author : gzlv 2021/6/27 15:51
 */
@Data
@TableName("yx_store_after_sales_status")
public class StoreAfterSalesStatus {

    /** Id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 售后id */
    private Long storeAfterSalesId;

    /** 操作类型 0售后订单生成 1后台审核成功 2用户发货 3打款 4审核失败 5用户撤销*/
    private Integer changeType;

    /** 操作备注 */
    private String changeMessage;

    /** 操作时间 */
    private Timestamp changeTime;

    /** 操作人 */
    private String operator;
}
