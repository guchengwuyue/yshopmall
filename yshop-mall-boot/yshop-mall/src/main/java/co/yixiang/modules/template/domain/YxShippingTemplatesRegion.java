/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.template.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2020-06-29
*/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("yx_shipping_templates_region")
public class YxShippingTemplatesRegion implements Serializable {

    /** 编号 */
    @TableId
    private Integer id;


    /** 省ID */
    @NotNull
    private Integer provinceId;


    /** 模板ID */
    @NotNull
    private Integer tempId;


    /** 城市ID */
    @NotNull
    private Integer cityId;


    /** 首件 */
    @NotNull
    private BigDecimal first;


    /** 首件运费 */
    @NotNull
    private BigDecimal firstPrice;


    /** 续件 */
    @NotNull
    private BigDecimal continues;


    /** 续件运费 */
    @NotNull
    private BigDecimal continuePrice;


    /** 计费方式 */
    @NotNull
    private Integer type;


    /** 分组唯一值 */
    @NotBlank
    private String uniqid;


    public void copy(YxShippingTemplatesRegion source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
