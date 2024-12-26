/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.common.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 手机号码工具类
 * @author hupeng
 * @date 2020-04-30
 */
@Slf4j
public class PhoneUtil {

    /**
     * 手机号码长度
     */
    private static final int PHONE_LENGTH = 11;

    /**
     * 脱敏*号
     */
    private static final String ASTERISK = "****";

    /**
     * 手机号码脱敏
     * 截取手机号码前三位，后4为，中间4位使用*号代替
     * 18812345678
     * 188****5678
     *
     * @param phone
     * @return
     */
    public static String desensitize(String phone) {
        // 校验手机号码
        if (StringUtils.isBlank(phone)) {
            return null;
        }
        if (phone.length() != PHONE_LENGTH) {
            log.error("手机号码不合法：" + phone);
            return phone;
        }

        String before = phone.substring(0,3);
        String after = phone.substring(7,11);
        String desensitizePhone = before + "****" + after;
        return desensitizePhone;
    }



}
