/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author hupeng
* @date 2020-05-14
*/
@Data
public class UserAvatarDto implements Serializable {

    private Long id;

    /** 真实文件名 */
    private String realName;

    /** 路径 */
    private String path;

    /** 大小 */
    private String size;

    /** 创建时间 */
    private Timestamp createTime;
}
