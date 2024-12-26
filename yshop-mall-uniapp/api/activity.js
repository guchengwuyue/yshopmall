import request from "@/utils/request";

/**
 * 拼团列表
 */
export function getCombinationList(data) {
  return request.get("/combination/list", data, { login: false });
}

/**
 * 拼团产品详情
 * @param {*} id
 */
export function getCombinationDetail(id) {
  return request.get("/combination/detail/" + id, {}, { login: true });
}

/**
 * 拼团 开团
 * @param {*} id
 */
export function getCombinationPink(id) {
  return request.get("/combination/pink/" + id);
}

/**
 * 拼团 取消开团
 */
export function getCombinationRemove(data) {
  return request.post("/combination/remove", data);
}

/**
 * 拼团海报
 * @param {*} id
 */
export function getCombinationPoster(data) {
  return request.post("/combination/poster", data);
}

/**
 * 秒杀列表配置
 */
export function getSeckillConfig() {
  return request.get("/seckill/index", {}, { login: false });
}

/**
 * 秒杀列表
 */
export function getSeckillList(time, data) {
  return request.get("/seckill/list/" + time, data, { login: false });
}

/**
 * 秒杀产品详情
 */
export function getSeckillDetail(id) {
  return request.get("/seckill/detail/" + id, {}, { login: true });
}



