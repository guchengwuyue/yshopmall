/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.product.domain.YxStoreProductReply;
import co.yixiang.modules.product.vo.YxStoreProductReplyQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
public interface StoreProductReplyMapper extends CoreMapper<YxStoreProductReply> {

    @Select("select A.product_score as productScore,A.service_score as serviceScore," +
            "A.comment,A.merchant_reply_content as merchantReplyContent," +
            "A.merchant_reply_time as merchantReplyTime,A.pics as pictures,A.create_Time as createTime," +
            "B.nickname,B.avatar,C.cart_info as cartInfo" +
            " from yx_store_product_reply A left join yx_user B " +
            "on A.uid = B.uid left join yx_store_order_cart_info C on A.`unique` = C.`unique`" +
            " where A.product_id=#{productId} and A.is_del=0 " +
            "order by A.create_Time DESC limit 1")
    YxStoreProductReplyQueryVo getReply(long productId);

    @Select("<script>select A.product_score as productScore,A.service_score as serviceScore," +
            "A.comment,A.merchant_reply_content as merchantReplyContent," +
            "A.merchant_reply_time as merchantReplyTime,A.pics as pictures,A.create_Time as createTime," +
            "B.nickname,B.avatar,C.cart_info as cartInfo" +
            " from yx_store_product_reply A left join yx_user B " +
            "on A.uid = B.uid left join yx_store_order_cart_info C on A.`unique` = C.`unique`" +
            " where A.product_id=#{productId} and A.is_del=0 " +
            "<if test='type == 1'>and A.product_score = 5</if>" +
            "<if test='type == 2'>and A.product_score &lt; 5 and A.product_score &gt; 2</if>" +
            "<if test='type == 3'>and A.product_score &lt; 2</if>"+
            " order by A.create_Time DESC</script>")
    List<YxStoreProductReplyQueryVo> selectReplyList(Page page, @Param("productId") long productId, @Param("type") int type);

}
