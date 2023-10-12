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
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author hupeng
* @date 2020-06-29
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("yx_shipping_templates")
public class YxShippingTemplates extends BaseDomain {

    /** 模板ID */
    @TableId
    private Integer id;


    /** 模板名称 */
    private String name;


    /** 计费方式 */
    private Integer type;


    /** 地域以及费用 */
    private String regionInfo;


    /** 指定包邮开关 */
    private Integer appoint;


    /** 指定包邮内容 */
    private String appointInfo;




    /** 排序 */
    private Integer sort;


    public void copy(YxShippingTemplates source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
