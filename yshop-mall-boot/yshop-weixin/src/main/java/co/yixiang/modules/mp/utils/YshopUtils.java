/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.mp.utils;


import co.yixiang.modules.user.service.dto.WechatUserDto;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName YshopUtils
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/27
 **/
public class YshopUtils {
    public static WechatUserDto getWechtUser(String str) {
        return JSONObject.parseObject(str,WechatUserDto.class);
    }
}
