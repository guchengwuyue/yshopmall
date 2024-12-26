package co.yixiang.modules.sales.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : gzlv 2021/6/27 15:49
 */
@Data
@TableName("yx_store_after_sales_item")
public class StoreAfterSalesItem {

    /** 主键id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 售后id */
    private Long storeAfterSalesId;

    /** 商品id */
    private Long productId;

    /** 退货东西的详情信息 */
    private String cartInfo;

    /** 逻辑删除 */
    private Integer isDel;
}
