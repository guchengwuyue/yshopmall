/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.sales.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import co.yixiang.annotation.Query;

/**
* @author gzlv
* @date 2021-06-30
*/
@Data
public class YxStoreAfterSalesQueryCriteria{

    /**
     * 订单号
     */
    private String orderCode;

    /** 售后状态-0正常1用户取消2商家拒绝 */
    private Integer salesState;

    private List<Timestamp> time;

    /** 状态 0已提交等待平台审核 1平台已审核 等待用户发货/退款 2 用户已发货 3已完成 */
    private Integer state;

    /** 服务类型0仅退款1退货退款 */
    private Integer serviceType;
}
