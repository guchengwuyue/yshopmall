package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
* @author hupeng
* @date 2019-10-04
*/
@Data
public class YxStoreProductSmallDTO implements Serializable {

    // 商品id
    private Integer id;

    // 商品图片
    private String image;


    // 商品名称
    private String storeName;


}