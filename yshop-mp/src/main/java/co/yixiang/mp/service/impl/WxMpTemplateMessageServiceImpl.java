package co.yixiang.mp.service.impl;

import cn.hutool.core.util.StrUtil;
import co.yixiang.mp.config.WxMpConfiguration;
import co.yixiang.mp.service.WxMpTemplateMessageService;
import co.yixiang.utils.RedisUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WxMpTemplateMessageServiceImpl implements WxMpTemplateMessageService {

    @Override
    public String sendWxMpTemplateMessage(String openId, String templateId, String url, Map<String,String> map){
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                .url(url)
                .build();
        map.forEach( (k,v)-> { templateMessage.addData(new WxMpTemplateData(k, v, "#000000"));} );
        String msgId = null;
        WxMpService wxService = WxMpConfiguration.getWxMpService();
        try {
            msgId =   wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return msgId;
    }
}
