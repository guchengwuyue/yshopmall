/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.dict.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* @author hupeng
* @date 2020-05-14
*/
@Data
@TableName("dict_detail")
public class DictDetail extends BaseDomain {

    /** 字典详细 */
    @TableId
    private Long id;


    /** 字典标签 */
    private String label;


    /** 字典值 */
    private String value;


    /** 排序 */
    private String sort;


    /** 字典id */
    private Long dictId;

    @TableField(exist = false)
    private Dict dict;




    public void copy(DictDetail source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
