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
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2020-06-29
*/
@Data
public class YxShippingTemplatesRegionDto implements Serializable {

    /** 编号 */
    private Integer id;

    /** 省ID */
    private Integer provinceId;

    /** 模板ID */
    private Integer tempId;

    /** 城市ID */
    private Integer cityId;

    /** 首件 */
    private BigDecimal first;

    /** 首件运费 */
    private BigDecimal firstPrice;

    /** 续件 */
    private BigDecimal continues;

    /** 续件运费 */
    private BigDecimal continuePrice;

    /** 计费方式 */
    private Integer type;

    /** 分组唯一值 */
    private String uniqid;
}
