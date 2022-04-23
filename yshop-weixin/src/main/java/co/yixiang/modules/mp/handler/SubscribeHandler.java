/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.handler;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.modules.mp.domain.YxWechatReply;
import co.yixiang.modules.mp.service.YxWechatReplyService;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private YxWechatReplyService yxWechatReplyService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {


        String str = "你好，欢迎关注yshop!";
        YxWechatReply wechatReply = yxWechatReplyService.isExist("subscribe");
        if (!ObjectUtil.isNull(wechatReply)) {
            str = JSONObject.parseObject(wechatReply.getData()).getString("content");
        }

        try {
            WxMpXmlOutMessage msg = WxMpXmlOutMessage.TEXT()
                    .content(str)
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .build();
            return msg;
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }


        return null;
    }


}
