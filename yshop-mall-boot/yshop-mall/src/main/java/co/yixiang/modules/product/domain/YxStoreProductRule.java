/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.product.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author hupeng
* @date 2020-06-28
*/
@Data
@TableName(value = "yx_store_product_rule",autoResultMap = true)
public class YxStoreProductRule extends BaseDomain {

    @TableId
    private Integer id;


    /** 规格名称 */
    @NotBlank(message = "请输入规则名称")
    private String ruleName;


    /** 规格值 */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    @NotNull(message = "规格名称/值必填")
    private JSONArray ruleValue;



    public void copy(YxStoreProductRule source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
