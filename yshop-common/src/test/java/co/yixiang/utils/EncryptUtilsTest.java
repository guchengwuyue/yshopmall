/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.utils;

import org.junit.Assert;
import org.junit.Test;

public class EncryptUtilsTest {

    /**
     * 对称加密
     */
    @Test
    public void testDesEncrypt() {
        try {
            Assert.assertEquals("7772841DC6099402", EncryptUtils.desEncrypt("123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对称解密
     */
    @Test
    public void testDesDecrypt() {
        try {
            Assert.assertEquals("123456", EncryptUtils.desDecrypt("7772841DC6099402"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
