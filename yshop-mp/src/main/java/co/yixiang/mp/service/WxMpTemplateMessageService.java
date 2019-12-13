package co.yixiang.mp.service;

import java.util.Map;

public interface WxMpTemplateMessageService {

    String sendWxMpTemplateMessage(String openId, String templateId, String url, Map<String, String> map);
}
