/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.common.util;


import co.yixiang.utils.RedisUtils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName 阿里云短信
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/26
 **/
@Slf4j
@Configuration(proxyBeanMethods = false)
public class SmsUtils {

    private static RedisUtils redisUtils;

    @Autowired
    public SmsUtils(RedisUtils redisUtils){
        SmsUtils.redisUtils = redisUtils;
    }
    /**
     * 发送短信
     * @param phoneNumbers 手机号
     * @param templateParam 短信模板变量对应的实际值，JSON格式
     */
    public static void sendSms(String phoneNumbers, String templateParam) throws ClientException {
        String regionId = redisUtils.getY("sms_region");
        String accessKeyId = redisUtils.getY("sms_access_key");
        String accessKeySecret = redisUtils.getY("sms_access_secret");
        String sign = redisUtils.getY("sms_sign");
        String templateId = redisUtils.getY("sms_templateId");
        DefaultProfile profile = DefaultProfile.getProfile(
                regionId,
                accessKeyId,
                accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();

        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", sign);
        request.putQueryParameter("TemplateCode", templateId);
        request.putQueryParameter("TemplateParam", templateParam);
        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());
    }
}
