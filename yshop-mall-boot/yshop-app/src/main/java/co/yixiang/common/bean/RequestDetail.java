/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.common.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 请求详情信息
 * @author hupeng
 * @date 2020-04-30
 */
@Data
@Accessors(chain = true)
public class RequestDetail implements Serializable {
	private static final long serialVersionUID = 2543641512850125440L;

	/**
     * 请求ip地址
     */
    private String ip;

    /**
     * 请求路径
     */
    private String path;

}
