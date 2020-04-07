package co.yixiang.modules.shop.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
* @author hupeng
* @date 2019-10-03
*/
@Data
public class YxStoreCategorySmallDTO implements Serializable {

    // 商品分类表ID
    private Integer id;


    // 分类名称
    private String cateName;



}