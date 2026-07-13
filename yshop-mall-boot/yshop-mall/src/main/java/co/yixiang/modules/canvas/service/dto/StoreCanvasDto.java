/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.canvas.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author yshop
* @date 2021-02-01
*/
@Data
public class StoreCanvasDto implements Serializable {

    /** 画布id */
    private Long canvasId;

    /** 终端 1-小程序 2-H5 3-APP 4-PC */
    private Integer terminal;

    /** 画布json数据 */
    private String json;

    /** 类型 1-系统画布 2-自定义页面 3-商家店铺装修 */
    private Integer type;

    /** 名称 */
    private String name;

    /** 店铺id，当type=3的时候，值为具体的店铺id，其它情况为0 */
    private Long shopId;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;
}
