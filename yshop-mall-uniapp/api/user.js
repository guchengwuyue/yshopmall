import request from '@/utils/request'

/**
 * 省市区
 */
export function getCity(data) {
  return request.get('/city_list', data, {
    // return request.get("/citys", data, {
    login: false,
  })
}

export function district(data) {
  // return request.get("/city_list", data, {
  return request.get('/citys', data, {
    login: false,
  })
}

/**
 * 用户登录
 * @param data object 用户账号密码
 */
export function login(data) {
  return request.post('/login', data, {
    login: false,
  })
}

/**
 * 用户手机号登录
 * @param data object 用户手机号 也只能
 */
export function loginMobile(data) {
  return request.post('/login/mobile', data, {
    login: false,
  })
}

/**
 * 用户发送验证码
 * @param data object 用户手机号
 */
export function registerVerify(data) {
  return request.post('/register/verify', data, {
    login: false,
  })
}

/**
 * 用户手机号注册
 * @param data object 用户手机号 验证码 密码
 */
export function register(data) {
  return request.post('/register', data, {
    login: false,
  })
}

/**
 * 用户手机号修改密码
 * @param data object 用户手机号 验证码 密码
 */
export function registerReset(data) {
  return request.post('/register/reset', data, {
    login: true,
  })
}

/*
 * 领取优惠券列表
 * */
export function getCoupon(q) {
  return request.get('/coupons', q, {
    login: true,
  })
}

/*
 * 点击领取优惠券
 * */
export function getCouponReceive(id) {
  return request.post(
    '/coupon/receive',
    {
      couponId: id,
    },
    {
      login: true,
    }
  )
}

/*
 * 批量领取优惠券
 * */
export function couponReceiveBatch(couponId) {
  return request.post('/coupon/receive/batch', {
    couponId,
  })
}

/*
 * 我的优惠券
 * */
export function getCouponsUser(type) {
  return request.get('/coupons/user/' + type)
}

/*
 * 个人中心
 * */
export function getUser() {
  return request.get('/user')
}

/*
 * 用户信息
 * */
export function getUserInfo() {
  return request.get('/userinfo', {
    login: true,
  })
}

/*
 * 小程序登陆
 * */
export function wxappAuth(data) {
  return request.post('/wxapp/auth', data, {
    login: false,
  })
}

/*
 * 个人中心(功能列表)
 * */
export function getMenuUser() {
  return request.get('/menu/user')
}

/*
 * 地址列表
 * */
export function getAddressList(data) {
  return request.get('/address/list', data || {})
}

/*
 * 删除地址
 * */
export function getAddressRemove(id) {
  return request.post('/address/del', {
    id: id,
  })
}

/*
 * 设置默认地址
 * */
export function getAddressDefaultSet(id) {
  return request.post('/address/default/set', {
    id: id,
  })
}

/*
 * 获取默认地址
 * */
export function getAddressDefault() {
  return request.get('/address/default')
}

/*
 * 获取单个地址
 * */
export function getAddress(id) {
  return request.get('/address/detail/' + id)
}

/*
 * 修改 添加地址
 * */
export function postAddress(data) {
  return request.post('/address/edit', data)
}

/*
 * 获取收藏产品
 * */
export function getCollectUser(page, limit, type) {
  return request.get('/collect/user', {
    page: page,
    limit: limit,
    type,
  })
}

/*
 * 删除收藏产品
 * */
export function getCollectDel(id, category,type) {
  return request.post('/collect/del', {
    id: id,
    category: category,
	type: type
  })
}

/*
 * 批量收藏产品
 * */
export function postCollectAll(data) {
  return request.post('/collect/all', data)
}

/*
 * 添加收藏产品
 * */
export function getCollectAdd(id, category,type) {
  return request.post('collect/add', {
    id: id,
    category: category,
	type: type
  })
}

/*
 * 签到配置
 * */
export function getSignConfig() {
  return request.get('/sign/config')
}

/*
 * 签到里的签到列表
 * */
export function getSignList(page, limit) {
  return request.get('/sign/list', {
    page: page,
    limit: limit,
  })
}

/*
 * 签到列表
 * */
export function getSignMonth(page, limit) {
  return request.get('/sign/month', {
    page: page,
    limit: limit,
  })
}

/*
 * 签到用户信息
 * */
export function postSignUser(sign) {
  return request.post('/sign/user', sign)
}

/*
 * 签到
 * */
export function postSignIntegral(sign) {
  return request.post('/sign/integral', sign)
}

/*
 * 推广数据
 * */
export function getSpreadInfo() {
  return request.get('/commission')
}

/*
 * 推广人列表
 * */
export function getSpreadUser(screen) {
  return request.post('/spread/people', screen)
}

/*
 * 推广人订单
 * */
export function getSpreadOrder(where) {
  return request.post('/spread/order', where)
}

/*
 * 资金明细（types|0=全部,1=消费,2=充值,3=返佣,4=提现）
 * */
export function getCommissionInfo(q, types) {
  return request.get('/spread/commission/' + types, q)
}

/*
 * 积分记录
 * */
export function getIntegralList(q) {
  return request.get('/integral/list', q)
}

/*
 * 提现银行
 * */
export function getBank() {
  return request.get('/extract/bank')
}

/*
 * 提现申请
 * */
export function postCashInfo(cash) {
  return request.post('/extract/cash', cash)
}

/*
 * 会员中心
 * */
export function getVipInfo() {
  return request.get('/user/level/grade')
}

/*
 * 会员等级任务
 * */
export function getVipTask(id) {
  return request.get('/user/level/task/' + id)
}

/*
 * 资金统计
 * */
export function getBalance() {
  return request.get('/user/balance')
}

/*
 * 活动状态
 * */
export function getActivityStatus() {
  return request.get(
    '/user/activity',
    {},
    {
      login: false,
    }
  )
}

/*
 * 活动状态
 * */
export function getSpreadImg(data) {
  return request.get('/spread/banner', data)
}

/*
 * 用户修改信息
 * */
export function postUserEdit(data) {
  return request.post('/user/edit', data)
}

/*
 * 用户修改信息
 * */
export function getChatRecord(to_uid, data) {
  return request.get('user/service/record/' + to_uid, data)
}

/*
 * 用户修改信息
 * */
export function serviceList() {
  return request.get('user/service/list')
}

/*
 * 公众号充值
 * */
export function rechargeWechat(data) {
  return request.post('/recharge/wechat', data)
}

/*
 * 退出登录
 * */
export function getLogout() {
  return request.post('/auth/logout')
}

/*
 * 小程序绑定手机号
 * */
export function bindingPhone(data) {
  return request.post('/binding', data)
}

/*
 * 绑定手机号
 * */
export function wxappBindingPhone(data) {
  return request.post('wxapp/binding', data)
}

/**
 * 小程序授权
 */
export function wxappGetUserInfo (data) {
	return request.post('/wxapp/loginAuth', data)
	//return request.get('/wechat/auth', data)
}

/*
 * h5切换公众号登陆
 * */
export function switchH5Login() {
  return request.post('switch_h5', {
    from: 'wechat',
  })
}
/*
 * 获取推广人排行
 * */
export function getRankList(q) {
  return request.get('rank', q)
}
/*
 * 获取佣金排名
 * */
export function getBrokerageRank(q) {
  return request.get('brokerage_rank', q)
}

/**
 * 检测会员等级
 */
export function setDetection() {
  return request.get('user/level/detection')
}

export function getRechargeApi() {
  return request.get('recharge/index')
}
