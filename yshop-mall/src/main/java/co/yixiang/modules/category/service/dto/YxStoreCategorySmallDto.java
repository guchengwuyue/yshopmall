/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.category.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author hupeng
* @date 2019-10-03
*/
@Data
public class YxStoreCategorySmallDto implements Serializable {

    // 商品分类表ID
    private Integer id;


    // 分类名称
    private String cateName;



}
