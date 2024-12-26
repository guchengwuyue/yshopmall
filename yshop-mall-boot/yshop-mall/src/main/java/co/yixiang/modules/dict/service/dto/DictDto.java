/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.dict.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @author hupeng
* @date 2020-05-14
*/
@Data
public class DictDto implements Serializable {

    /** 字典ID */
    private Long id;

    /** 字典名称 */
    private String name;

    private List<DictDetailDto> dictDetails;

    /** 描述 */
    private String remark;

    /** 创建日期 */
    private Timestamp createTime;
}
