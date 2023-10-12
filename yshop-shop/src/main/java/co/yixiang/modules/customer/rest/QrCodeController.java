package co.yixiang.modules.customer.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.annotation.AnonymousAccess;
import co.yixiang.constant.ShopConstants;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.mp.config.WxMpConfiguration;
import co.yixiang.utils.RecodeUtil;
import co.yixiang.utils.RedisUtil;
import co.yixiang.utils.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author lioncity
 * @date 2020-03-21
 */
@Slf4j
@Api(tags = "微信")
@Controller
@RequestMapping("/api/wxmp")
@AllArgsConstructor
public class QrCodeController {

    private final RedisUtils redisUtils;
    /**
     * 生成微信图片二维码
     *
     * @param request
     * @param response
     * @
     */
    @AnonymousAccess
    @GetMapping("/qrcode")
    public void qrcode(HttpServletRequest request, HttpServletResponse response, @RequestParam("key") String key) {
        String adminApiUrl = redisUtils.getY(ShopConstants.ADMIN_API_URL);
        if(StrUtil.isBlank(adminApiUrl)){
           throw new BadRequestException("请配置后台-->商城配置-->商城系统配置-->后台Api地址");
        }
        final WxMpService wxService = WxMpConfiguration.getWxMpService();
        if (wxService == null) {
            throw new IllegalArgumentException("未找到对应配置的服务，请核实！");
        }
        String state = key;
        String url = adminApiUrl + "/api/wxmp/userInfo";
        String redirectURL = wxService.getOAuth2Service().buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(state));
        log.info("【微信网页授权】获取code,redirectURL={}", redirectURL);
        //调用工具类，生成二维码
        //180为图片高度和宽度
        RecodeUtil.creatRrCode(redirectURL, 180, 180, response);
    }

    @AnonymousAccess
    @GetMapping("/wechatCode")
    public ResponseEntity wechatCode() {
        String wechatFollowImg = redisUtils.getY(ShopConstants.WECHAT_FOLLOW_IMG);
        if(StrUtil.isBlank(wechatFollowImg)){
            throw new BadRequestException("请配置后台-->微信管理-->公众号配置->关注二维码");
        }
        return ResponseEntity.ok(wechatFollowImg);
    }

    @AnonymousAccess
    @ResponseBody
    @GetMapping("/userInfo")
    public void userInfo(HttpServletRequest request, @RequestParam("code") String code,
                         @RequestParam("state") String key) throws Exception {
        log.info("【微信网页授权】code={}", code);
        log.info("【微信网页授权】state={}", key);
        final WxMpService wxService = WxMpConfiguration.getWxMpService();
        if (wxService == null) {
            throw new IllegalArgumentException("未找到对应配置的服务，请核实！");
        }
        try {
            WxOAuth2AccessToken wxOAuth2AccessToken = wxService.getOAuth2Service().getAccessToken(code);
            WxOAuth2UserInfo wxOAuth2UserInfo = wxService.getOAuth2Service().getUserInfo(wxOAuth2AccessToken, "zh_CN");
            RedisUtil.set("qrCode:" + key, wxOAuth2UserInfo.getOpenid() + ":" + wxOAuth2UserInfo.getNickname());
            log.info("【微信网页授权】wxMpUser={}", wxOAuth2UserInfo);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}", e);
            throw new Exception(e.getError().getErrorMsg());
        }
    }

    @ResponseBody
    @GetMapping("/getOpenId")
    public ResponseEntity userInfo(HttpServletRequest request, @RequestParam("key") String key) {
        String openId = RedisUtil.get("qrCode:" + key);
        if (openId != null) {
            String[] str = openId.split(":");
            JSONObject json = new JSONObject();
            json.put("openId", str[0]);
            json.put("nickName", str[1]);
            return new ResponseEntity(json, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
