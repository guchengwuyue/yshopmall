

import request from "@/utils/request";

/**
 * 查询所有直播间
 */
export function yxWechatLive(data) {
    return request.get("/yxWechatLive", data, { login: true });
}

/**
 * 获取直播回放
 */
export function getLiveReplay(id, data) {
    return request.get("/yxWechatLive/getLiveReplay/" + id, data, { login: false });
}
