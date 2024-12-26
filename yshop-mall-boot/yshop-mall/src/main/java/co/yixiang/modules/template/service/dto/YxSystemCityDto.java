/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.template.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
* @author hupeng
* @date 2020-06-29
*/
@Data
public class YxSystemCityDto implements Serializable {

    private Integer id;

    /** 城市id */
    private Integer cityId;

    /** 省市级别 */
    private Integer level;

    /** 父级id */
    private Integer parentId;

    /** 区号 */
    private String areaCode;

    /** 名称 */
    private String name;

    /** 合并名称 */
    private String mergerName;

    /** 经度 */
    private String lng;

    /** 纬度 */
    private String lat;

    /** 是否展示 */
    private Integer isShow;
}
