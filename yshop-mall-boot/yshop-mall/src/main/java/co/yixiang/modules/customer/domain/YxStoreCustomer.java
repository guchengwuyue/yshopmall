/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.customer.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.validation.constraints.*;
import java.util.Date;
import co.yixiang.domain.BaseDomain;

/**
* @author Bug
* @date 2020-12-10
*/
@Data
@TableName("yx_store_customer")
public class YxStoreCustomer extends BaseDomain {
    /** id */
    @TableId
    private Long id;

    /** 用户昵称 */
    private String nickName;

    /** openId */
    @NotBlank(message = "请用户扫码后提交")
    private String openId;

    /** 备注 */
    private String remark;




    /** 是否启用 */
    private Integer isEnable;


    public void copy(YxStoreCustomer source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
