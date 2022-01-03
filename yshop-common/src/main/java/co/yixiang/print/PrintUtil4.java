/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.print;

import co.yixiang.constant.ShopConstants;
import co.yixiang.utils.DateUtils;
import co.yixiang.utils.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("static-access")
public class PrintUtil4 {
    public static final String URL = "http://api.feieyun.cn/Api/Open/";//不需要修改

    public static final String USER = RedisUtil.get(ShopConstants.YSHOP_FEI_E_USER);//*必填*：账号名
    public static final String UKEY = RedisUtil.get(ShopConstants.YSHOP_FEI_E_UKEY);//*必填*: 飞鹅云后台注册账号后生成的UKEY 【备注：这不是填打印机的KEY】
    public static final String SN = "918502791";//*必填*：打印机编号，必须要在管理后台里添加打印机或调用API接口添加之后，才能调用API

    /**
     * 保存文件到磁盘
     *
     * @param path    保存路径
     * @param content 打印内容
     *                writeFile("E:/retlog.txt", result);
     */
    public void writeFile(String path, String content) {
        content = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()) + ",保存的订单日志信息为: " + content;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path, true);
            fos.write(content.getBytes());
            fos.write("\r<BR>".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        try {
            System.out.println(new String(addPrintEquip("918502791", "nvz8kw5n", "测试", "").getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加打印机
     *
     * @param sn     编号
     * @param key    密钥
     * @param remark 备注
     * @param phone  流量卡号码
     * @return 返回打印结果
     */
    public static String addPrintEquip(String sn, String key, String remark, String phone) {
        String printerContent = sn + " # " + key + " # " + remark + " # " + phone;
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", USER));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
        nvps.add(new BasicNameValuePair("apiname", "Open_printerAddlist"));
        nvps.add(new BasicNameValuePair("printerContent", printerContent));// 固定值,不需要修改
        return sendHttpRequest(nvps);
    }

    /**
     * 编辑打印机信息
     *
     * @param sn     编号
     * @param remark 备注
     * @param phone  电话
     * @return 结果
     */
    public static String editPrintEquip(String sn, String remark, String phone) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", USER));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
        nvps.add(new BasicNameValuePair("apiname", "Open_printerEdit"));
        nvps.add(new BasicNameValuePair("sn", sn));
        nvps.add(new BasicNameValuePair("name", remark));
        nvps.add(new BasicNameValuePair("phonenum", phone));
        return sendHttpRequest(nvps);
    }

    /**
     * 打印订单数据
     *
     * @param sn           打印机编号
     * @param contentTitle 打印标题
     * @param orderList    订单数据
     * @param order
     * @return
     */
    public static String printOrder(String sn, String contentTitle, List<OrderPrint> orderList, PrintOrderDataVO order, int lian) {
        String content = getPrintContent(contentTitle, orderList, 14, 6, 3, 6, order, lian + 1);//orderList为数组 b1代表名称列占用（14个字节）  b2单价列（6个字节） b3数量列（3个字节） b4金额列（6个字节）-->这里的字节数可按自己需求自由改写，14+6+3+6再加上代码写的3个空格就是32了，58mm打印机一行总占32字节
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("user", USER));
//        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
//        nvps.add(new BasicNameValuePair("stime", STIME));
//        nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
//        nvps.add(new BasicNameValuePair("apiname", "Open_printMsg"));// 固定值,不需要修改
//        nvps.add(new BasicNameValuePair("sn", sn));
//        nvps.add(new BasicNameValuePair("content", content));
//        nvps.add(new BasicNameValuePair("times", "1"));// 打印联数
//        return sendHttpRequest(nvps);
        return print(sn, content);
    }


    /**
     * 打印预养订单
     *
     * @param orderList 订单数据
     * @param title     标题
     * @param mobile    联系方式
     * @param name      用户姓名
     * @param payPrice  实际支付
     * @return
     */
    public static String printOrderByAdvance(List<OrderPrint> orderList, String title, String mobile, String name, String payPrice) {
        String content = getOrderByAdvance(orderList, 14, 6, 3, 6, title, mobile, name, payPrice);//orderList为数组 b1代表名称列占用（14个字节）  b2单价列（6个字节） b3数量列（3个字节） b4金额列（6个字节）-->这里的字节数可按自己需求自由改写，14+6+3+6再加上代码写的3个空格就是32了，58mm打印机一行总占32字节
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("user", USER));
//        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
//        nvps.add(new BasicNameValuePair("stime", STIME));
//        nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
//        nvps.add(new BasicNameValuePair("apiname", "Open_printMsg"));// 固定值,不需要修改
//        nvps.add(new BasicNameValuePair("sn", SN));
//        nvps.add(new BasicNameValuePair("content", content));
//        nvps.add(new BasicNameValuePair("times", "1"));// 打印联数
//        return sendHttpRequest(nvps);
        return print(SN, content);
    }

    /**
     * 打印订单数据
     *
     * @param contentTitle 打印标题
     * @param orderList    订单数据
     * @return
     */
    public static String printContentByStore(String contentTitle, List<OrderPrint> orderList, int b1, int b3, PrintStoreOrderVO printStoreOrder, int lian) {
        String content = getPrintContentByStore(contentTitle, orderList, b1, b3, printStoreOrder, lian + 1);//orderList为数组 b1代表名称列占用（14个字节）  b2单价列（6个字节） b3数量列（3个字节） b4金额列（6个字节）-->这里的字节数可按自己需求自由改写，14+6+3+6再加上代码写的3个空格就是32了，58mm打印机一行总占32字节
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("user", USER));
//        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
//        nvps.add(new BasicNameValuePair("stime", STIME));
//        nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
//        nvps.add(new BasicNameValuePair("apiname", "Open_printMsg"));// 固定值,不需要修改
//        nvps.add(new BasicNameValuePair("sn", sn));
//        nvps.add(new BasicNameValuePair("content", content));
//        nvps.add(new BasicNameValuePair("times", "1"));// 打印联数
//        return sendHttpRequest(nvps);
        return print(printStoreOrder.getDriverNo(), content);
    }

    /**
     * 打印订单数据
     *
     * @param sn      打印机编号
     * @param content 打印内容
     * @return
     */
    public static String print(String sn, String content) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", USER));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
        nvps.add(new BasicNameValuePair("apiname", "Open_printMsg"));// 固定值,不需要修改
        nvps.add(new BasicNameValuePair("sn", sn));
        nvps.add(new BasicNameValuePair("content", content));
        nvps.add(new BasicNameValuePair("times", "1"));// 打印联数
        return sendHttpRequest(nvps);
    }


    /**
     * 获取预养订单文本信息
     *
     * @return
     */
    public static String getOrderByAdvance(List<OrderPrint> orderList, int b1, int b2, int b3, int b4, String headTitle, String mobile, String name, String payPrice) {
        String orderInfo = "<CB>" + headTitle + "</CB><BR>";
        orderInfo += "名称           单价  数量 金额<BR>";
        orderInfo += "--------------------------------<BR>";
        double totals = 0.0;
        for (int i = 0; i < orderList.size(); i++) {
            String title = orderList.get(i).getTitle();
            String price = orderList.get(i).getPrice();
            String num = orderList.get(i).getNum();
            String total = "" + Double.valueOf(price) * Integer.parseInt(num);
            totals += Double.parseDouble(total);
            price = addSpace(price, b2);
            num = addSpace(num, b3);
            total = addSpace(total, b4);
            String otherStr = " " + price + num + " " + total;

            int tl = 0;
            try {
                tl = title.getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int spaceNum = (tl / b1 + 1) * b1 - tl;
            if (tl < b1) {
                for (int k = 0; k < spaceNum; k++) {
                    title += " ";
                }
                title += otherStr;
            } else if (tl == b1) {
                title += otherStr;
            } else {
                List<String> list = null;
                if (isEn(title)) {
                    list = getStrList(title, b1);
                } else {
                    list = getStrList(title, b1 / 2);
                }
                String s0 = titleAddSpace(list.get(0));
                title = s0 + otherStr + "<BR>";// 添加 单价 数量 总额
                String s = "";
                for (int k = 1; k < list.size(); k++) {
                    s += list.get(k);
                }
                try {
                    s = getStringByEnter(b1, s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                title += s;
            }
            orderInfo += title + "<BR>";
        }
        orderInfo += "--------------------------------<BR>";

        orderInfo += "合计：" + totals + "元<BR>";
        orderInfo += "实付：" + payPrice + "元<BR>";
        //orderInfo += "送货地点：广州市南沙区xx路xx号<BR>";
        orderInfo += "联系人：" + name + "<BR>";
        orderInfo += "联系电话：" + mobile + "<BR>";
        orderInfo += "下单时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "<BR>";
        //orderInfo += "<QR>https://admin.jidanguo10.com/weixin?id=2</QR>";
        return orderInfo;
    }


    /**
     * 打印各门店订单数据信息
     *
     * @param contentTitle
     * @param orderList
     * @param b1
     * @param b3
     * @param lian
     * @return
     */
    public static String getPrintContentByStore(String contentTitle, List<OrderPrint> orderList, int b1, int b3, PrintStoreOrderVO printStoreOrder, int lian) {
        String orderInfo = "<CB>----第" + lian + "联----</CB><BR>";
        orderInfo += "<CB>" + contentTitle + "</CB><BR>";
        orderInfo += "名称                      数量<BR>";
        orderInfo += "--------------------------------<BR>";
        for (int i = 0; i < orderList.size(); i++) {
            String title = orderList.get(i).getTitle();
            String num = orderList.get(i).getNum();
            num = addSpace(num, b3);
            String otherStr = "  " + num;

            int tl = 0;
            try {
                tl = title.getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int spaceNum = (tl / b1 + 1) * b1 - tl;
            if (tl < b1) {
                for (int k = 0; k < spaceNum; k++) {
                    title += " ";
                }
                title += otherStr;
            } else if (tl == b1) {
                title += otherStr;
            } else {
                List<String> list = null;
                if (isEn(title)) {
                    list = getStrList(title, b1);
                } else {
                    list = getStrList(title, b1 / 2);
                }
                String s0 = titleAddSpace(list.get(0));
                title = s0 + otherStr + "<BR>";// 添加 单价 数量 总额
                String s = "";
                for (int k = 1; k < list.size(); k++) {
                    s += list.get(k);
                }
                try {
                    s = getStringByEnter(b1, s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                title += s;
            }
            orderInfo += title + "<BR>";
        }
        orderInfo += "--------------------------------<BR>";
        orderInfo += "社区门店：" + contentTitle + "<BR>";
        orderInfo += "开始时间：" + printStoreOrder.getStartTime() + "<BR>";
        orderInfo += "截止时间：" + printStoreOrder.getEndTime() + "<BR>";
        orderInfo += "打印时间：" + DateUtils.getTime() + "<BR>";
        //orderInfo += "<QR>https://admin.jidanguo10.com/weixin?id=2</QR>";
        return orderInfo;
    }


    //orderList为数组  b1代表名称列占用字节  b2单价列 b3数量列 b4金额列-->这里的字节数可按自己需求自由改写，详细往上看112行调用实际例子运用

    /**
     * 获取打印订单的文本内容
     *
     * @param contentTitle 打印内容标题
     * @param orderList    订单数据
     * @param b1
     * @param b2
     * @param b3
     * @param b4
     * @param order
     * @return
     */
    public static String getPrintContent(String contentTitle, List<OrderPrint> orderList, int b1, int b2, int b3, int b4, PrintOrderDataVO order, int lian) {
        String orderInfo = "<CB>----第" + lian + "联----</CB><BR>";
        orderInfo += "<CB>" + contentTitle + "</CB><BR>";
        orderInfo += "名称           单价  数量 金额<BR>";
        orderInfo += "--------------------------------<BR>";
        double totals = 0.0;
        for (int i = 0; i < orderList.size(); i++) {
            String title = orderList.get(i).getTitle();
            String price = orderList.get(i).getPrice();
            String num = orderList.get(i).getNum();
            String total = "" + Double.valueOf(price) * Integer.parseInt(num);
            totals += Double.parseDouble(total);
            price = addSpace(price, b2);
            num = addSpace(num, b3);
            total = addSpace(total, b4);
            String otherStr = " " + price + num + " " + total;

            int tl = 0;
            try {
                tl = title.getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int spaceNum = (tl / b1 + 1) * b1 - tl;
            if (tl < b1) {
                for (int k = 0; k < spaceNum; k++) {
                    title += " ";
                }
                title += otherStr;
            } else if (tl == b1) {
                title += otherStr;
            } else {
                List<String> list = null;
                if (isEn(title)) {
                    list = getStrList(title, b1);
                } else {
                    list = getStrList(title, b1 / 2);
                }
                String s0 = titleAddSpace(list.get(0));
                title = s0 + otherStr + "<BR>";// 添加 单价 数量 总额
                String s = "";
                for (int k = 1; k < list.size(); k++) {
                    s += list.get(k);
                }
                try {
                    s = getStringByEnter(b1, s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                title += s;
            }
            orderInfo += title + "<BR>";
        }
        orderInfo += "--------------------------------<BR>";
        orderInfo += "社区门店：" + order.getStoreName() + "<BR>";
        orderInfo += "订单编号：" + order.getOrderId() + "<BR>";
        orderInfo += "合计：" + totals + "元<BR>";
        orderInfo += "实付：" + order.getPayPrice() + "元<BR>";
        //orderInfo += "送货地点：广州市南沙区xx路xx号<BR>";
        orderInfo += "联系人：" + order.getRealName() + "<BR>";
        orderInfo += "详细地址：" + order.getUserAddress() + "<BR>";
        orderInfo += "联系电话：" + order.getUserPhone() + "<BR>";
        orderInfo += "下单时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "<BR>";
        orderInfo += "备注：" + order.getMark() + "<BR>";
        //orderInfo += "<QR>https://admin.jidanguo10.com/weixin?id=2</QR>";
        return orderInfo;
    }


    /**
     * 发送打印请求
     *
     * @param nvps
     * @return
     */
    public static String sendHttpRequest(List<NameValuePair> nvps) {
        // 通过POST请求，发送打印信息到服务器
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)// 读取超时
                .setConnectTimeout(30000)// 连接超时
                .build();

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

        HttpPost post = new HttpPost(URL);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = httpClient.execute(post);
            int statecode = response.getStatusLine().getStatusCode();
            if (statecode == 200) {
                HttpEntity httpentity = response.getEntity();
                if (httpentity != null) {
                    // 服务器返回的JSON字符串，建议要当做日志记录起来
                    result = EntityUtils.toString(httpentity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(response, post, httpClient);
        }
        return result;
    }

    /**
     * 关闭流
     *
     * @param response
     * @param post
     * @param httpClient
     */
    public static void close(CloseableHttpResponse response, HttpPost post, CloseableHttpClient httpClient) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            post.abort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String titleAddSpace(String str) {
        int k = 0;
        int b = 14;
        try {
            k = str.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < b - k; i++) {
            str += " ";
        }
        return str;
    }

    public static String getStringByEnter(int length, String string) throws Exception {
        for (int i = 1; i <= string.length(); i++) {
            if (string.substring(0, i).getBytes("GBK").length > length) {
                return string.substring(0, i - 1) + "<BR>" + getStringByEnter(length, string.substring(i - 1));
            }
        }
        return string;
    }

    /**
     * 添加空格,文本对齐
     *
     * @param str
     * @param size
     * @return
     */
    public static String addSpace(String str, int size) {
        int len = str.length();
        if (len < size) {
            for (int i = 0; i < size - len; i++) {
                str += " ";
            }
        }
        return str;
    }

    public static Boolean isEn(String str) {
        Boolean b = false;
        try {
            b = str.getBytes("GBK").length == str.length();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    public static List<String> getStrList(String inputString, int length, int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length, (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 字符串切割
     *
     * @param str
     * @param f
     * @param t
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length()) {
            return null;
        }
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

    /**
     * 签名
     *
     * @param USER  用户
     * @param UKEY  UKEY
     * @param STIME 时间戳
     * @return {@link String}
     */
    private static String signature(String USER, String UKEY, String STIME) {
        return DigestUtils.sha1Hex(USER + UKEY + STIME);
    }
}
