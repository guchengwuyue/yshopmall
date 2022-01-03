/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import co.yixiang.enums.ShopCommonEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName OrderUtil
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/13
 **/
public class OrderUtil {


    /**
     * 返回活动状态
     * @param starTime 开始时间
     * @param endTime 结束时间
     * @param status  0-关闭 其他表示相反
     * @return String
     */
    public static String checkActivityStatus(Date starTime, Date endTime, int status) {
        Date nowTime = new Date();

        if(ShopCommonEnum.IS_STATUS_0.getValue().equals(status)) {
            return "关闭";
        }

        if(DateUtil.compare(starTime,nowTime) > 0){
            return "活动未开始";
        }else if(DateUtil.compare(endTime,nowTime) < 0){
            return "活动已结束";
        }else if(DateUtil.compare(endTime,nowTime) > 0 && DateUtil.compare(starTime,nowTime) < 0){
            return "正在进行中";
        }

        return "未知";

    }

    /**
     * 生成邀请码
     *
     * @return
     */
    public static String createShareCode() {
        int maxNum = 36;
        int i;
        int count = 0;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < 10) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 获取俩个数之间的随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static Double randomNumber(double min, double max) {
        return NumberUtil.add(min,
                NumberUtil.mul(Math.random(),
                        NumberUtil.sub(max, min)));
    }

    /**
     * 时间戳订单号
     *
     * @return
     */
    public static String orderSn() {
        Date date = DateUtil.date();
        return DateUtil.format(date, "yyyyMMddHHmmssSSS");
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s) * 1000;
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为date
     */
    public static Date stampToDateObj(String s) {
        long lt = new Long(s) * 1000;
        Date date = new Date(lt);
        return date;
    }


    /**
     * 获取精确到秒的时间戳
     *
     * @return
     **/
    public static int getSecondTimestampTwo() {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        return Integer.valueOf(timestamp);
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @return
     **/
    public static int dateToTimestamp(Date date) {
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.valueOf(timestamp);
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @return
     **/
    public static int dateToTimestampT(DateTime date) {
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.valueOf(timestamp);
    }

    public static long dateToSecond(String str) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(str);
        long ts = date.getTime();
        return ts / 1000;
    }

    /**
     * 获取订单状态名称
     *
     * @param paid
     * @param status
     * @param shipping_type
     * @param refund_status
     * @return
     */
    public static String orderStatusStr(int paid, int status,
                                        int shipping_type, int refund_status) {
        String statusName = "";
        if (paid == 0 && status == 0) {
            statusName = "未支付";
        } else if (paid == 1 && status == 0 && shipping_type == 1 && refund_status == 0) {
            statusName = "未发货";
        } else if (paid == 1 && status == 0 && shipping_type == 2 && refund_status == 0) {
            statusName = "未核销";
        } else if (paid == 1 && status == 1 && shipping_type == 1 && refund_status == 0) {
            statusName = "待收货";
        } else if (paid == 1 && status == 1 && shipping_type == 2 && refund_status == 0) {
            statusName = "未核销";
        } else if (paid == 1 && status == 2 && refund_status == 0) {
            statusName = "待评价";
        } else if (paid == 1 && status == 3 && refund_status == 0) {
            statusName = "已完成";
        } else if (paid == 1 && refund_status == 1) {
            statusName = "退款中";
        } else if (paid == 1 && refund_status == 2) {
            statusName = "已退款";
        }

        return statusName;
    }


    /**
     * 获取状态数值
     *
     * @param paid
     * @param status
     * @param refund_status
     * @return
     */
    public static int orderStatus(int paid, int status, int refund_status) {
        //todo  1-未付款 2-未发货 3-退款中 4-待收货 5-待评价 6-已完成 7-已退款
        int _status = 0;

        if (paid == 0 && status == 0 && refund_status == 0) {
            _status = 1;
        } else if (paid == 1 && status == 0 && refund_status == 0) {
            _status = 2;
        } else if (paid == 1 && refund_status == 1) {
            _status = 3;
        } else if (paid == 1 && status == 1 && refund_status == 0) {
            _status = 4;
        } else if (paid == 1 && status == 2 && refund_status == 0) {
            _status = 5;
        } else if (paid == 1 && status == 3 && refund_status == 0) {
            _status = 6;
        } else if (paid == 1 && refund_status == 2) {
            _status = 7;
        }

        return _status;

    }


    /**
     * 支付方式
     *
     * @param pay_type
     * @param paid
     * @return
     */
    public static String payTypeName(String pay_type, int paid) {
        String payTypeName = "";
        if (paid == 1) {
            switch (pay_type) {
                case "weixin":
                    payTypeName = "微信支付";
                    break;
                case "yue":
                    payTypeName = "余额支付";
                    break;
                case "offline":
                    payTypeName = "线下支付";
                    break;
                default:
                    payTypeName = "其他支付";
                    break;
            }
        } else {
            switch (pay_type) {
                default:
                    payTypeName = "未支付";
                    break;
                case "offline":
                    payTypeName = "线下支付";
                    break;
            }
        }
        return payTypeName;
    }

    /**
     * 支付渠道(0微信公众号1微信小程序)
     *
     * @return
     */
    public static String payChannel(Integer pay_channel) {
        if (pay_channel.equals(1)) {
            return "微信小程序";
        } else {
            return "微信公众号";
        }
    }

    //todo 订单类型
    public static String orderType(int pinkId) {
        return "普通订单";
    }


}
