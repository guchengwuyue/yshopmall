package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName StoreOrderCartInfo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/14
 **/


@Data
public class StoreOrderCartInfoDTO {


    private Integer id;


    private Integer oid;


    private Integer cartId;


    private String cartInfo;


    private String unique;

    private Map<String,Object> cartInfoMap;


}
