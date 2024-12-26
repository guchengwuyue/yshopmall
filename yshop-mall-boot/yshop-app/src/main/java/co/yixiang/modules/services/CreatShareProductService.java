/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.services;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.http.HttpUtil;
import co.yixiang.api.YshopException;
import co.yixiang.constant.ShopConstants;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.enums.AppFromEnum;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.order.vo.YxStoreOrderQueryVo;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.shop.domain.YxSystemAttachment;
import co.yixiang.modules.shop.service.YxSystemAttachmentService;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.utils.OrderUtil;
import co.yixiang.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static co.yixiang.utils.FileUtil.transformStyle;

/**
 * @ClassName 二维码相关服务
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/22
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreatShareProductService {

    private final YxSystemAttachmentService systemAttachmentService;
    private final YxStorePinkService storePinkService;
    private final YxStoreCombinationService storeCombinationService;
    private final YxSystemStoreService systemStoreService;
    private final YxSystemConfigService systemConfigService;

    /**
     * 返回门店信息与二维码
     * @param storeOrder 订单
     * @return YxStoreOrderQueryVo
     */
    public YxStoreOrderQueryVo handleQrcode(YxStoreOrderQueryVo storeOrder,String path){
        //门店
        if(OrderInfoEnum.SHIPPIING_TYPE_2.getValue().equals(storeOrder.getShippingType())){
            String mapKey = RedisUtil.get(SystemConfigConstants.TENGXUN_MAP_KEY);
            if(StrUtil.isBlank(mapKey)) {
                throw new YshopException("请配置腾讯地图key");
            }
            String apiUrl = systemConfigService.getData(SystemConfigConstants.API_URL);
            if(StrUtil.isEmpty(apiUrl)){
                throw new YshopException("未配置api地址");
            }
            //生成二维码
            String name = storeOrder.getVerifyCode()+"_yshop.jpg";
            YxSystemAttachment attachment = systemAttachmentService.getInfo(name);
            String fileDir = path+"qrcode"+ File.separator;
            String qrcodeUrl = "";
            if(ObjectUtil.isNull(attachment)){
                //生成二维码
                File file = FileUtil.mkdir(new File(fileDir));
                QrCodeUtil.generate(storeOrder.getVerifyCode(), 180, 180,
                        FileUtil.file(fileDir+name));
                systemAttachmentService.attachmentAdd(name,String.valueOf(FileUtil.size(file)),
                        fileDir+name,"qrcode/"+name);
                qrcodeUrl = apiUrl + "/api/file/qrcode/"+name;
            }else{
                qrcodeUrl = apiUrl + "/api/file/" + attachment.getSattDir();
            }
            storeOrder.setCode(qrcodeUrl);
            storeOrder.setMapKey(mapKey);
            storeOrder.setSystemStore(systemStoreService.getYxSystemStoreById(storeOrder.getStoreId()));
        }

        return storeOrder;
    }

    /**
     * 获取分销二维码url
     * @param from 来源
     * @param userInfo 用户
     * @param siteUrl h5地址
     * @param apiUrl api地址
     * @param path 本地图片路径
     * @return url
     */
    public String getSpreadUrl(String from,YxUser userInfo,String siteUrl,
                               String apiUrl,String path){
        String spreadUrl = "";
        Long uid = userInfo.getUid();

        File newFile = new File("fx.jpg");
        File newFileT = new File("simsunb.ttf");
        try {
            if(!newFile.exists()){
                InputStream stream =  getClass().getClassLoader().getResourceAsStream("fx.jpg");
                FileUtils.copyInputStreamToFile(stream, newFile);
            }
            if(!newFileT.exists()){
                InputStream streamT =  getClass().getClassLoader()
                        .getResourceAsStream("simsunb.ttf");
                FileUtils.copyInputStreamToFile(streamT, newFileT);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new YshopException(e.getMessage());
        }

        if(StrUtil.isNotBlank(from) && AppFromEnum.APP.getValue().equals(from)){
            String spreadPicName = uid + "_"+from+"_user_spread.jpg";
            String fileDir = path+"qrcode"+File.separator;
            String spreadPicPath = fileDir+spreadPicName;

            YxSystemAttachment attachmentT = systemAttachmentService.getInfo(spreadPicName);
            if(ObjectUtil.isNull(attachmentT)){
                try {
                    Font font =  Font.createFont(Font.TRUETYPE_FONT, newFileT);
                    Font f= font.deriveFont(Font.PLAIN,20);
                    ImgUtil.pressText(//
                            newFile,
                            FileUtil.file(spreadPicPath),
                            userInfo.getNickname()+"邀您加入",
                            Color.BLACK,
                            f, //字体
                            0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                            300, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                            0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
                    );

                    String inviteCode =  OrderUtil.createShareCode();
                    ImgUtil.pressText(
                            FileUtil.file(spreadPicPath),
                            FileUtil.file(spreadPicPath),
                            "邀您码:"+ inviteCode,
                            Color.RED,
                            f, //字体
                            0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                            340, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                            0.8f
                    );

                    systemAttachmentService.newAttachmentAdd(spreadPicName,
                            String.valueOf(FileUtil.size(new File(spreadPicPath))),
                            spreadPicPath,"qrcode/"+spreadPicName,uid,inviteCode);

                    spreadUrl = apiUrl + "/api/file/qrcode/"+spreadPicName;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                spreadUrl = apiUrl + "/api/file/" + attachmentT.getSattDir();
            }
        }
        else{//其他
            if(StrUtil.isBlank(from)) {
                from = AppFromEnum.H5.getValue();
            }

            String name = uid + "_"+from+"_user_wap.jpg";

            YxSystemAttachment attachment = systemAttachmentService.getInfo(name);
            String fileDir = path+"qrcode"+File.separator;
            String qrcodeUrl = "";
            if(ObjectUtil.isNull(attachment)){
                //生成二维码
                //判断用户是否小程序,注意小程序二维码生成路径要与H5不一样 不然会导致都跳转到小程序问题
                if(from.equals(AppFromEnum.ROUNTINE.getValue())){
                    siteUrl = siteUrl+"/distribution/";
                }else if(AppFromEnum.UNIAPPH5.getValue().equals(from)){
                    String uniUrl = systemConfigService.getData(SystemConfigConstants.UNI_SITE_URL);
                    siteUrl =  StrUtil.isNotBlank(uniUrl) ? uniUrl :  ShopConstants.DEFAULT_UNI_H5_URL;
                    siteUrl = siteUrl+"/pages/Loading/index";
                }
                File file = FileUtil.mkdir(new File(fileDir));
                QrCodeUtil.generate(siteUrl+"?spread="+uid, 180, 180,
                        FileUtil.file(fileDir+name));

                systemAttachmentService.attachmentAdd(name,String.valueOf(FileUtil.size(file)),
                        fileDir+name,"qrcode/"+name);

                qrcodeUrl = fileDir+name;
            }else{
                qrcodeUrl = attachment.getAttDir();
            }

            String spreadPicName = uid + "_"+from+"_user_spread.jpg";
            String spreadPicPath = fileDir+spreadPicName;

            YxSystemAttachment attachmentT = systemAttachmentService.getInfo(spreadPicName);
            if(ObjectUtil.isNull(attachmentT)){
                try {

                    Font font =  Font.createFont(Font.TRUETYPE_FONT, newFileT);
                    Font f= font.deriveFont(Font.PLAIN,20);
                    //font.
                    ImgUtil.pressText(//
                            newFile,
                            FileUtil.file(spreadPicPath),
                            userInfo.getNickname()+"邀您加入",
                            Color.BLACK,
                            f, //字体
                            50, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                            300, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                            0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
                    );

                    ImgUtil.pressImage(
                            FileUtil.file(spreadPicPath),
                            FileUtil.file(spreadPicPath),
                            ImgUtil.read(FileUtil.file(qrcodeUrl)), //水印图片
                            -150, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                            340, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                            0.8f
                    );

                    systemAttachmentService.attachmentAdd(spreadPicName,
                            String.valueOf(FileUtil.size(new File(spreadPicPath))),
                            spreadPicPath,"qrcode/"+spreadPicName);

                    spreadUrl = apiUrl + "/api/file/qrcode/"+spreadPicName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                spreadUrl = apiUrl + "/api/file/" + attachmentT.getSattDir();
            }

        }

        return spreadUrl;
    }



    /**
     * 获取拼团海报
     * @param pinkId 拼团id
     * @param userInfo 用户
     * @param siteUrl h5地址
     * @param apiUrl api地址
     * @param path 本地图片路径
     * @return url
     */
    public String getPinkPosterUrl(Long pinkId, YxUser userInfo,String siteUrl,
                                      String apiUrl,String path,String from){
        Long uid = userInfo.getUid();
        YxStorePink storePink = storePinkService.getById(pinkId);
        if(ObjectUtil.isNull(storePink)) {
            throw new YshopException("拼团不存在");
        }
        YxStoreCombination storeCombination = storeCombinationService.getById(storePink.getCid());
        if(ObjectUtil.isNull(storeCombination)) {
            throw new YshopException("拼团产品不存在");
        }


        String name = pinkId+"_"+uid + "_"+from+"_pink_share_wap.jpg";
        YxSystemAttachment attachment = systemAttachmentService.getInfo(name);
        String fileDir = path+"qrcode"+ File.separator;
        String qrcodeUrl = "";
        if(ObjectUtil.isNull(attachment)){
            //生成二维码
            File file = FileUtil.mkdir(new File(fileDir));
            if(AppFromEnum.ROUNTINE.getValue().equals(from)){
                siteUrl = siteUrl+"/pink/";
                QrCodeUtil.generate(siteUrl+"?pinkId="+pinkId+"&spread="+uid+"&pageType=group&codeType="+AppFromEnum.ROUNTINE.getValue(), 180, 180,
                        FileUtil.file(fileDir+name));
            }
            else if(AppFromEnum.APP.getValue().equals(from)){
                siteUrl = siteUrl+"/pink/";
                QrCodeUtil.generate(siteUrl+"?pinkId="+pinkId+"&spread="+uid+"&pageType=group&codeType="+AppFromEnum.ROUNTINE.getValue(), 180, 180,
                        FileUtil.file(fileDir+name));
            }
            else if(AppFromEnum.H5.getValue().equals(from)){
                QrCodeUtil.generate(siteUrl+"/activity/group_rule/"+pinkId+"?spread="+uid, 180, 180,
                        FileUtil.file(fileDir+name));
            }else {
                String uniUrl = systemConfigService.getData(SystemConfigConstants.UNI_SITE_URL);
                siteUrl =  StrUtil.isNotBlank(uniUrl) ? uniUrl :  ShopConstants.DEFAULT_UNI_H5_URL;
                QrCodeUtil.generate(siteUrl+"/pages/activity/GroupRule/index?id="+pinkId+"&spread="+uid, 180, 180,
                        FileUtil.file(fileDir+name));
            }


            systemAttachmentService.attachmentAdd(name,String.valueOf(FileUtil.size(file)),
                    fileDir+name,"qrcode/"+name);

            qrcodeUrl = fileDir+name;
        }else{
            qrcodeUrl = attachment.getAttDir();
        }

        String spreadPicName = pinkId+"_"+uid + "_"+from+"_pink_user_spread.jpg";
        String spreadPicPath = fileDir+spreadPicName;

        YxSystemAttachment attachmentT = systemAttachmentService.getInfo(spreadPicName);
        String spreadUrl = "";
//        InputStream stream =  getClass().getClassLoader().getResourceAsStream("poster.jpg");
//        InputStream streamT =  getClass().getClassLoader()
//                .getResourceAsStream("simsunb.ttf");
        File newFile = new File("poster.jpg");
        File newFileT = new File("simsunb.ttf");
        try {
            if(!newFileT.exists()){
                InputStream streamT =  getClass().getClassLoader()
                        .getResourceAsStream("simsunb.ttf");
                FileUtils.copyInputStreamToFile(streamT, newFileT);
            }
            if(!newFile.exists()){
                InputStream stream =  getClass().getClassLoader().getResourceAsStream("poster.jpg");
                FileUtils.copyInputStreamToFile(stream, newFile);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new YshopException(e.getMessage());
        }
        if(ObjectUtil.isNull(attachmentT)){
            try {

                //第一步标题
                Font font =  Font.createFont(Font.TRUETYPE_FONT, newFileT);
                Font f= font.deriveFont(Font.PLAIN,40);
                //font.
                ImgUtil.pressText(//
                        newFile,
                        FileUtil.file(spreadPicPath),
                        storeCombination.getTitle(),
                        Color.BLACK,
                        f, //字体
                        0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                        -480, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                        0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
                );

                Font f2= font.deriveFont(Font.PLAIN,45);
                //第2步价格
                ImgUtil.pressText(//
                        FileUtil.file(spreadPicPath),
                        FileUtil.file(spreadPicPath),
                        storePink.getTotalPrice().toString(),
                        Color.RED,
                        f2, //字体
                        -160, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                        -350, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                        0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
                );

                Font f3= font.deriveFont(Font.PLAIN,30);
                //第3步几人团
                ImgUtil.pressText(//
                        FileUtil.file(spreadPicPath),
                        FileUtil.file(spreadPicPath),
                        storePink.getPeople()+"人团",
                        Color.WHITE,
                        f3, //字体
                        90, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                        -370, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                        0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
                );

                //第4步介绍
                String pro = "原价￥"+storeCombination.getProductPrice()+" 还差"
                        +storePinkService.surplusPeople(storePink)+"人拼团成功";
                ImgUtil.pressText(//
                        FileUtil.file(spreadPicPath),
                        FileUtil.file(spreadPicPath),
                        pro,
                        Color.BLACK,
                        f3, //字体
                        -50, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                        -300, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                        0.7f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
                );

                //第5步商品图片
                //下载图片
//                String picImage = fileDir+pinkId+"_pink_image.jpg";
//                HttpUtil.downloadFile(storeCombination.getImage(),
//                        FileUtil.file(picImage));

                String image = storeCombination.getImage();
                String ext = image.substring(image.lastIndexOf("."));
                String picImage = fileDir + "pink_product_" + pinkId + ext;
                if(!new File(picImage).exists()){
                    //下载商品图
                    HttpUtil.downloadFile(image, FileUtil.file(picImage));
                    //只缩放一次防止图片持续缩放导致图片没了
                    ImgUtil.scale(
                            FileUtil.file(picImage),
                            FileUtil.file(picImage),
                            0.5f//缩放比例
                    );
                }


                ImgUtil.pressImage(
                        FileUtil.file(spreadPicPath),
                        FileUtil.file(spreadPicPath),
                        ImgUtil.read(FileUtil.file(picImage)), //水印图片
                        0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                        0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                        0.8f
                );

                ImgUtil.pressImage(
                        FileUtil.file(spreadPicPath),
                        FileUtil.file(spreadPicPath),
                        ImgUtil.read(FileUtil.file(qrcodeUrl)), //水印图片
                        0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                        390, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                        0.8f
                );

                systemAttachmentService.attachmentAdd(spreadPicName,
                        String.valueOf(FileUtil.size(new File(spreadPicPath))),
                        spreadPicPath,"qrcode/"+spreadPicName);

                spreadUrl = apiUrl + "/api/file/qrcode/"+spreadPicName;

            } catch (Exception e) {
                log.error(e.getMessage());
                throw new YshopException(e.getMessage());
            }
        }else{
            spreadUrl = apiUrl + "/api/file/" + attachmentT.getSattDir();
        }

        return  spreadUrl;
    }


    /**
     * 普通组合海报
     * @param productDTO 商品
     * @param shareCode 二维码
     * @param spreadPicName 海报图片名称
     * @param spreadPicPath 路径
     * @param apiUrl api地址
     * @return String
     * @throws IOException
     * @throws FontFormatException
     */
    public  String creatProductPic(YxStoreProduct productDTO, String shareCode, String spreadPicName, String spreadPicPath, String apiUrl) throws IOException, FontFormatException {
        YxSystemAttachment attachmentT = systemAttachmentService.getInfo(spreadPicName);
        String spreadUrl = "";
        if(ObjectUtil.isNull(attachmentT)){
            //创建图片
            BufferedImage img = new BufferedImage(750, 1334, BufferedImage.TYPE_INT_RGB);
            //开启画图
            Graphics g = img.getGraphics();
            //背景 -- 读取互联网图片
            InputStream stream =  getClass().getClassLoader().getResourceAsStream("background.png");
            ImageInputStream background = ImageIO.createImageInputStream(stream);
            BufferedImage back = ImageIO.read(background);

            g.drawImage(back.getScaledInstance(750, 1334, Image.SCALE_DEFAULT), 0, 0, null); // 绘制缩小后的图
            //商品  banner图
            //读取互联网图片
            BufferedImage priductUrl = null;
            try {
                priductUrl = ImageIO.read(new URL(transformStyle(productDTO.getImage())));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(priductUrl.getScaledInstance(750,590,Image.SCALE_DEFAULT),0,0,null);

            File newFileT = new File("simsunb.ttf");
            if(!newFileT.exists()){
                InputStream streamT =  getClass().getClassLoader()
                        .getResourceAsStream("simsunb.ttf");
                FileUtils.copyInputStreamToFile(streamT, newFileT);
            }
            Font font =  Font.createFont(Font.TRUETYPE_FONT, newFileT);
            //文案标题
            g.setFont(font.deriveFont(Font.BOLD,34));
            g.setColor(new Color(29,29,29));
            int fontlenb = getWatermarkLength(productDTO.getStoreName(), g);
            //文字长度相对于图片宽度应该有多少行
            int lineb = fontlenb / (back.getWidth() +200);
            //高度
            int yb = back.getHeight() - (lineb + 1) * 30 + 100;
            //文字叠加,自动换行叠加
            int tempXb = 32;
            int tempYb = yb;
            //单字符长度
            int tempCharLenb = 0;
            //单行字符总长度临时计算
            int tempLineLenb = 0;
            StringBuffer sbb =new StringBuffer();
            for(int i=0; i < productDTO.getStoreName().length(); i++) {
                char tempChar = productDTO.getStoreName().charAt(i);
                tempCharLenb = getCharLen(tempChar, g);
                tempLineLenb += tempCharLenb;
                if(tempLineLenb >= (back.getWidth()+220)) {
                    //长度已经满一行,进行文字叠加
                    g.drawString(sbb.toString(), tempXb, tempYb + 50);
                    //清空内容,重新追加
                    sbb.delete(0, sbb.length());
                    //每行文字间距50
                    tempYb += 50;
                    tempLineLenb =0;
                }
                //追加字符
                sbb.append(tempChar);
            }
            g.drawString(sbb.toString(), tempXb, tempYb + 50);

            //------------------------------------------------文案-----------------------
            //文案
            g.setFont(font.deriveFont(Font.PLAIN,30));
            g.setColor(new Color(47,47,47));
            int fontlen = getWatermarkLength(productDTO.getStoreInfo(), g);
            //文字长度相对于图片宽度应该有多少行
            int line = fontlen / (back.getWidth() - 90);
            //高度
            int y = tempYb + 50 - (line + 1) * 30 + 100;
            //文字叠加,自动换行叠加
            int tempX = 32;
            int tempY = y;
            //单字符长度
            int tempCharLen = 0;
            //单行字符总长度临时计算
            int tempLineLen = 0;
            StringBuffer sb =new StringBuffer();

            for(int i=0; i < productDTO.getStoreInfo().length(); i++) {
                char tempChar = productDTO.getStoreInfo().charAt(i);
                tempCharLen = getCharLen(tempChar, g);
                tempLineLen += tempCharLen;
                if(tempLineLen >= (back.getWidth()-90)) {
                    //长度已经满一行,进行文字叠加
                    g.drawString(sb.toString(), tempX, tempY + 50);
                    //清空内容,重新追加
                    sb.delete(0, sb.length());
                    //每行文字间距50
                    tempY += 50;
                    tempLineLen =0;
                }
                //追加字符
                sb.append(tempChar);
            }
            //最后叠加余下的文字
            g.drawString(sb.toString(), tempX, tempY + 50);

            //价格背景
            //读取互联网图片
            BufferedImage bground  = null;
            InputStream redStream =  getClass().getClassLoader().getResourceAsStream("red.jpg");
            try {
                ImageInputStream red = ImageIO.createImageInputStream(redStream);
                bground = ImageIO.read(red);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 绘制缩小后的图
            g.drawImage(bground.getScaledInstance(160, 40, Image.SCALE_DEFAULT), 30, 1053, null);

            //限时促销价
            g.setFont(font.deriveFont(Font.PLAIN,24));
            g.setColor(new Color(255,255,255));
            g.drawString("限时促销价", 50, 1080);

            //价格
            g.setFont(font.deriveFont(Font.PLAIN,50));
            g.setColor(new Color(249,64,64));
            g.drawString("¥" +productDTO.getPrice(), 29, 1162);

            //原价
            g.setFont(font.deriveFont(Font.PLAIN,36));
            g.setColor(new Color(171,171,171));
            String price = "¥" + productDTO.getOtPrice();
            g.drawString(price, 260, 1160);
            g.drawLine(250,1148,260+150,1148);

//            //商品名称
//            g.setFont(font.deriveFont(Font.PLAIN,32));
//            g.setColor(new Color(29,29,29));
//            g.drawString(productDTO.getStoreName(), 30, 1229);

            //生成二维码返回链接
            String url = shareCode;
            //读取互联网图片
            BufferedImage qrCode  = null;
            try {
                qrCode = ImageIO.read(new URL(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 绘制缩小后的图
            g.drawImage(qrCode.getScaledInstance(174, 174, Image.SCALE_DEFAULT), 536, 1057, null);

            //二维码字体
            g.setFont(font.deriveFont(Font.PLAIN,25));
            g.setColor(new Color(171,171,171));
            //绘制文字
            g.drawString("扫描或长按小程序码", 515, 1260);

            g.dispose();
            //先将画好的海报写到本地
            File file = new File(spreadPicPath);
            try {
                ImageIO.write(img, "jpg",file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            systemAttachmentService.attachmentAdd(spreadPicName,
                    String.valueOf(FileUtil.size(new File(spreadPicPath))),
                    spreadPicPath,"qrcode/"+spreadPicName);
            spreadUrl = apiUrl + "/api/file/qrcode/"+spreadPicName;
            //保存到本地 生成文件名字
        }else {
            spreadUrl = apiUrl + "/api/file/" + attachmentT.getSattDir();
        }

        return spreadUrl;
    }


    /**
     * 获取水印文字总长度
     *@paramwaterMarkContent水印的文字
     *@paramg
     *@return水印文字总长度
     */
    public static int getWatermarkLength(String waterMarkContent, Graphics g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(),0, waterMarkContent.length());
    }
    public static int getCharLen(char c, Graphics g) {
        return g.getFontMetrics(g.getFont()).charWidth(c);
    }
}
