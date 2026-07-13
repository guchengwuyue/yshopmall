package co.yixiang.modules.services;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import co.yixiang.exception.ErrorRequestException;
import co.yixiang.modules.mp.service.dto.YxArticleDto;
import co.yixiang.modules.mp.config.WxMpConfiguration;
import co.yixiang.modules.mp.utils.URLUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpNewsArticle;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @ClassName WechatArticleService
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/7/2
 **/
@Service
@Slf4j
public class WechatArticleService {

    @Value("${file.path}")
    private String uploadDirStr;

    /**
     * 发布微信图文
     * @param wxNewsArticleItem YxArticleDto
     * @throws WxErrorException
     */
    public void publish(YxArticleDto wxNewsArticleItem) throws WxErrorException {
        WxMpService wxMpService = WxMpConfiguration.getWxMpService();

        WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();


        WxMpNewsArticle article = new WxMpNewsArticle();

        WxMpMaterialUploadResult wxMpMaterialUploadResult = uploadPhotoToWx( wxMpService,
                wxNewsArticleItem.getImageInput() );
        wxNewsArticleItem.setThumbMediaId( wxMpMaterialUploadResult.getMediaId() );

        article.setAuthor( wxNewsArticleItem.getAuthor() );


        //处理content
        String content = processContent(wxMpService, wxNewsArticleItem.getContent());
        System.out.println(content);
        article.setContent( content );
        article.setContentSourceUrl( wxNewsArticleItem.getUrl() );
        article.setDigest( wxNewsArticleItem.getSynopsis() );
        article.setShowCoverPic( true );
        article.setThumbMediaId( wxNewsArticleItem.getThumbMediaId() );
        article.setTitle( wxNewsArticleItem.getTitle() );
        //TODO 暂时注释掉，测试号没有留言权限
        //article.setNeedOpenComment( wxNewsArticleItem );
        //article.setOnlyFansCanComment( wxNewsArticleItem );
        wxMpMaterialNews.addArticle( article );

        log.info( "wxMpMaterialNews : {}", JSONUtil.toJsonStr( wxMpMaterialNews ) );

        WxMpMaterialUploadResult wxMpMaterialUploadResult1 = wxMpService.getMaterialService()
                .materialNewsUpload( wxMpMaterialNews );

        //推送开始
        WxMpMassTagMessage massMessage = new WxMpMassTagMessage();
        massMessage.setMsgType(WxConsts.MassMsgType.MPNEWS);
        massMessage.setMediaId(wxMpMaterialUploadResult1.getMediaId());
        massMessage.setSendAll(true);

        WxMpMassSendResult massResult = wxMpService.getMassMessageService()
                .massGroupMessageSend(massMessage);
        if(!"0".equals(massResult.getErrorCode())) {
            log.info("error:"+massResult.getErrorMsg());
            throw new ErrorRequestException("发送失败");
        }

        log.info( "massResult : {}", JSONUtil.toJsonStr( massResult ) );

        log.info( "wxMpMaterialUploadResult : {}", JSONUtil.toJsonStr( wxMpMaterialUploadResult1 ) );
    }


    /**
     * 上传素材
     * @param wxMpService WxMpService
     * @param picPath 图片路径
     * @return WxMpMaterialUploadResult
     * @throws WxErrorException
     */
    private WxMpMaterialUploadResult uploadPhotoToWx(WxMpService wxMpService, String picPath) throws WxErrorException {
        WxMpMaterial wxMpMaterial = new WxMpMaterial();

        String filename = String.valueOf( (int)System.currentTimeMillis() ) + ".png";
        String downloadPath = uploadDirStr + filename;
        long size = HttpUtil.downloadFile(picPath, cn.hutool.core.io.FileUtil.file(downloadPath));
        picPath = downloadPath;
        File picFile = new File( picPath );
        wxMpMaterial.setFile( picFile );
        wxMpMaterial.setName( picFile.getName() );
        log.info( "picFile name : {}", picFile.getName() );
        WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpService.getMaterialService().materialFileUpload( WxConsts.MediaFileType.IMAGE, wxMpMaterial );
        log.info( "wxMpMaterialUploadResult : {}", JSONUtil.toJsonStr( wxMpMaterialUploadResult ) );
        return wxMpMaterialUploadResult;
    }

    /**
     * 处理内容
     * @param wxMpService WxMpService
     * @param content 内容
     * @return String
     * @throws WxErrorException
     */
    private String processContent(WxMpService wxMpService,String content) throws WxErrorException {
        if(StringUtils.isBlank( content )){
            return content;
        }
        String imgReg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        List<String> imgList = ReUtil.findAllGroup1( imgReg,content);
        for (int j = 0; j < imgList.size(); j++) {
            String imgSrc = imgList.get( j );
            String filepath = URLUtils.getParam( imgSrc,"filepath" );

            if(StringUtils.isBlank( filepath )){//网络图片URL，需下载到本地
                String filename = System.currentTimeMillis() + ".png";
                String downloadPath = uploadDirStr + filename;
                long size = HttpUtil.downloadFile(imgSrc, cn.hutool.core.io.FileUtil.file(downloadPath));
                filepath = downloadPath;
            }
            WxMediaImgUploadResult wxMediaImgUploadResult = wxMpService.getMaterialService().mediaImgUpload( new File(filepath) );
            content = StringUtils.replace( content,imgList.get( j ),wxMediaImgUploadResult.getUrl());
        }
        return content;
    }
}
