/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
* @author hupeng
* @date 2020-05-12
*/

@Getter
@Setter
public class YxSystemGroupDataVo{

    /** 组合数据详情ID */

    private Integer id;


    /** 对应的数据名称 */
    private String groupName;


    /** 数据组对应的数据值（json数据） */
    private JSONObject value;


    /** 数据排序 */
    private Integer sort;


    /** 状态（1：开启；2：关闭；） */
    private Integer status;


}
