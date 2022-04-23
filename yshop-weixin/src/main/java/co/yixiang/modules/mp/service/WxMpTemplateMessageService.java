/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.service;

import java.util.Map;

public interface WxMpTemplateMessageService {

    String sendWxMpTemplateMessage(String openId, String templateId, String url, Map<String, String> map);
}
