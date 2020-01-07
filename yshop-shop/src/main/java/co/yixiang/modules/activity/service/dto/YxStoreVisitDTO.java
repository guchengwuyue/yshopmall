package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author hupeng
* @date 2019-11-18
*/
@Data
public class YxStoreVisitDTO implements Serializable {

    private Integer id;

    // 产品ID
    private Integer productId;

    // 产品类型
    private String productType;

    // 产品分类ID
    private Integer cateId;

    // 产品类型
    private String type;

    // 用户ID
    private Integer uid;

    // 访问次数
    private Integer count;

    // 备注描述
    private String content;

    // 添加时间
    private Integer addTime;
}