/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.customer.service.dto;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;

/**
* @author Bug
* @date 2020-12-10
*/
@Data
public class YxStoreCustomerDto implements Serializable {

    /** id */
    private Long id;

    /** 用户昵称 */
    private String nickName;

    /** openId */
    private String openId;

    /** 备注 */
    private String remark;

    /** 添加时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    private Integer isDel;

    /** 是否启用 */
    private Integer isEnable;
}
