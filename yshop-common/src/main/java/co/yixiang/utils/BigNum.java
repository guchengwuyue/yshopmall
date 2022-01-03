/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigNum {
    public static final String BIG_NUM_FMT_COMMA = "#,###,###,###,###,###,##0.00";//千位分隔符 方便查看金额具体大小
    public static final String BIG_NUM_FMT = "##################0.00";//不带千位分隔符
    public static final String BIG_NUM_HUNDRED = "100";//100常量
    public static final int BIG_NUM_SCALE = 2;//保留两位小数


    // 除法运算默认精度
    private static final int DEF_DIV_SCALE = 10;


    /**
     * 精确加法
     */
    public static BigDecimal add(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return new BigDecimal(b1.add(b2).toString());
    }

    /**
     * 精确加法
     */
    public static BigDecimal add(Object value1, Object value2) {
        BigDecimal b1 = new BigDecimal(value1.toString());
        BigDecimal b2 = new BigDecimal(value2.toString());
        return new BigDecimal(b1.add(b2).toString());
    }

    /**
     * 精确减法
     */
    public static BigDecimal sub(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return new BigDecimal(b1.subtract(b2).toString());
    }

    /**
     * 精确减法
     */
    public static BigDecimal sub(Object value1, Object value2) {
        BigDecimal b1 = new BigDecimal(value1.toString());
        BigDecimal b2 = new BigDecimal(value2.toString());
        return new BigDecimal(b1.subtract(b2).toString());
    }

    /**
     * 精确乘法
     */
    public static BigDecimal mul(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return new BigDecimal(b1.multiply(b2).toString());
    }

    /**
     * 精确乘法
     */
    public static BigDecimal mul(Object value1, Object value2) {
        BigDecimal b1 = new BigDecimal(value1.toString());
        BigDecimal b2 = new BigDecimal(value2.toString());
        return new BigDecimal(b1.multiply(b2).toString());
    }

    /**
     * 精确除法 使用默认精度
     */
    public static BigDecimal div(double value1, double value2) throws IllegalAccessException {
        return div(value1, value2, DEF_DIV_SCALE);
    }

    /**
     * 精确除法 使用默认精度
     */
    public static BigDecimal div(String value1, String value2) throws IllegalAccessException {
        return div(value1, value2, DEF_DIV_SCALE);
    }

    /**
     * 精确除法
     *
     * @param scale
     *            精度
     */
    public static BigDecimal div(double value1, double value2, int scale) throws IllegalAccessException {
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        // return b1.divide(b2, scale).doubleValue();
        return new BigDecimal(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString());
    }

    /**
     * 精确除法
     *
     * @param scale
     *            精度
     */
    public static BigDecimal div(String value1, String value2, int scale) throws IllegalAccessException {
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        // return b1.divide(b2, scale).doubleValue();
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 四舍五入
     *
     * @param scale
     *            小数点后保留几位
     */
    public static BigDecimal round(double v, int scale) throws IllegalAccessException {
        return div(v, 1, scale);
    }

    /**
     * 四舍五入
     *
     * @param scale
     *            小数点后保留几位
     */
    public static BigDecimal round(String v, int scale) throws IllegalAccessException {
        return div(v, "1", scale);
    }

    /**
     * 比较大小
     */
    public static boolean equalTo(BigDecimal b1, BigDecimal b2) {
        if (b1 == null || b2 == null) {
            return false;
        }
        return 0 == b1.compareTo(b2);
    }


    /**
     * 分转换成元
     * @param v
     * @return
     */
    public static BigDecimal penny2dollar(String v) {
        BigDecimal s = new BigDecimal("0.00");//保留两位小数
        try {
            s = div(v, "100", 2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 元转换成分
     * @param v
     * @return
     */
    public static BigDecimal dollar2penny(String v) {
        return mul(v, "100");
    }

    /**
     * 格式化金额
     * 千位分隔符 方便查看金额具体大小 BIG_NUM_FMT = "#,###,###,###,###,###,##0.00"
     * 精确两位小数 .99 -> 0.99
     *             1111111.985 -> 1,111,111.99
     * @param v
     * @return
     */
    public static String formatNumber(String v) {
        return formatNumber(v, BIG_NUM_FMT_COMMA);
    }

    /**
     * 格式化金额
     * @param v
     * @param pattern BigNum类中的常量 BIG_NUM_FMT_COMMA,BIG_NUM_FMT
     * @return
     */
    public static String formatNumber(String v, String pattern) {
        return new DecimalFormat(pattern).format(new BigDecimal(v));
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal("0.00").toString());
    }
}
