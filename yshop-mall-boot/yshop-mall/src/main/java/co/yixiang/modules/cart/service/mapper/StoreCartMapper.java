/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.cart.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.cart.domain.YxStoreCart;
import co.yixiang.modules.order.service.dto.CountDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author hupeng
* @date 2020-05-12
*/
@Repository
public interface StoreCartMapper extends CoreMapper<YxStoreCart> {

    @Select("select IFNULL(sum(cart_num),0) from yx_store_cart " +
            "where is_pay=0 and is_del=0 and is_new=0 and uid=#{uid}")
    int cartSum(@Param("uid") Long uid);


    @Select("SELECT t.cate_name as catename from yx_store_cart c  " +
            "LEFT JOIN yx_store_product p on c.product_id = p.id  " +
            "LEFT JOIN yx_store_category t on p.cate_id = t.id " +
            "WHERE c.is_pay = 1")
    List<CountDto> findCateName();
}
