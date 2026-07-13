import { subscribeMessage } from '@/libs/order'
import { getProvider } from '@/utils'
import WechatJSSDK from 'wechat-jssdk/dist/client.umd'
import { getWechatConfig, wechatAuth } from '@/api/public'
import { parseQuery } from '@/utils'
import cookie from '@/utils/store/cookie'
import store from '@/store'
import dayjs from 'dayjs'

// 支付模块
export const weappPay = option => {
  return new Promise((resolve, reject) => {
    if (store.state.$deviceType == 'weixinh5') {
      setTimeout(() => {
        location.href = option.mweb_url
      }, 100)
      resolve()
      return
    }
    if (store.state.$deviceType == 'weixin') {
      pay(option)
        .then(() => {
          uni.showToast({
            title: '支付成功',
            icon: 'success',
            duration: 5000,
          })
          resolve()
        })
        .finally(res => {
          //if(typeof(res) == "undefined") return
        })
        .catch(function() {
          uni.showToast({ title: '支付失败', icon: 'none', duration: 5000 })
          reject()
        })
      return
    }
    // 吊起微信支付
    // getProvider('payment').then(provider => {
    let orderInfo = {
      appid: option.appid,
      noncestr: option.noncestr,
      package: option.package,
      partnerid: option.partnerid,
      prepayid: option.prepayid,
      sign: option.sign,
      timestamp: option.timestamp + '',
    }
    // 调用只接口
    uni.requestPayment({
      provider: 'wxpay',
      ...option,
      timestamp: orderInfo.timestamp,
      orderInfo,
      success: success => {
        console.log(success)
        uni.showToast({
          title: '支付成功',
          icon: 'success',
          duration: 5000,
        })
        let time = setTimeout(() => {
          clearTimeout(time)
          resolve(success)
        }, 3000)
        // #ifdef MP-WEIXIN
        subscribeMessage()
        // #endif
      },
      fail: error => {
        console.log(error)
        if (error.errMsg == 'requestPayment:fail cancel') {
          uni.showToast({ title: '已取消支付', icon: 'none', duration: 5000 })
        } else {
          uni.showToast({ title: error || error.msg, icon: 'none', duration: 5000 })
        }
        reject(error)
      },
    })
    // })
  })
}

const STATE_KEY = 'wx_authorize_state'
const WX_AUTH = 'wx_auth'
const BACK_URL = 'login_back_url'
const LOGINTYPE = 'loginType'
let instance
let wechatObj
let appId
let wechatLoading = false

export function wechat() {
  console.log('初始化微信配置')
  wechatLoading = false
  return new Promise((resolve, reject) => {
    if (instance) return resolve(instance)
    getWechatConfig()
      .then(res => {
        console.log(res.data)
        const _wx = WechatJSSDK(res.data)
        console.log(_wx)
        appId = res.data.appId
        wechatObj = _wx
        _wx
          .initialize()
          .then(() => {
            instance = _wx.wx
            instance.initConfig = res.data
            resolve(instance)
          })
          .catch(error => {
            console.log(error)
            uni.showToast({
              title: error,
              icon: 'none',
              duration: 2000,
            })
            reject()
          })
      })
      .catch(err => {
        console.log(err)
        reject()
      })
  })
}

export function clearAuthStatus() {
  cookie.remove(WX_AUTH)
  cookie.remove(STATE_KEY)
}

export async function oAuth() {
  console.log('处理微信授权')
  console.log(store)
  console.log(store.state)
  return new Promise((resolve, reject) => {
    // if (cookie.has(WX_AUTH)) {
    if (cookie.has(WX_AUTH) && store.state.token) {
      reject()
      return
    }
    const { code } = parseQuery()
    if (!code) {
      toAuth()
      return
    } else {
      auth(code)
    }
    resolve()
  }).catch(error => {
    console.log(error)
  })
}

export async function auth(code) {
  console.log('获取微信授权')
  return new Promise((resolve, reject) => {
    let loginType = cookie.get(LOGINTYPE)
    let spread = cookie.get('spread')
    console.log('微信授权登录前获取spread', spread)
    wechatAuth(code, spread, loginType)
      .then(({ data }) => {
        console.log(data)
        const expires_time = dayjs(data.expires_time)
        const newTime = Math.round(new Date() / 1000)
        store.commit('login', data.token, expires_time - newTime)
        cookie.set(WX_AUTH, code, expires_time)
        cookie.remove(STATE_KEY)
        loginType && cookie.remove(LOGINTYPE)
        console.log('微信公众号授权登录，获取用户信息')
        store.dispatch('getUser').finally(() => {
          resolve()
        })
      })
      .catch(reject)
  }).catch(error => {
    console.log(error)
  })
}

export async function toAuth() {
  if (wechatLoading) {
    return
  }
  wechatLoading = true
  wechat().then(wx => {
    location.href = getAuthUrl(appId)
  })
}

function getAuthUrl(appId) {
  // const redirect_uri = encodeURIComponent(window.location.href);
  // const redirect_uri = encodeURIComponent(`${location.origin}/pages/Loading/index`);

  // #ifdef H5
  // #endif
  cookie.set('redirect', window.location.href)
  const redirect_uri = encodeURIComponent(`${location.origin}/pages/Loading/index`)
  // const redirect_uri = encodeURIComponent(`${location.origin}/pages/Loading/index?path=${encodeURIComponent(window.location.href)}`);
  // const redirect_uri = encodeURIComponent(`${window.location.origin}${window.location.pathname}`)
  // const redirect_uri = encodeURIComponent(`${location.origin}`)
  cookie.remove(BACK_URL)
  const state = 'STATE'
  // const state = encodeURIComponent(("" + Math.random()).split(".")[1] + "authorizestate");
  cookie.set(STATE_KEY, state)
  return `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=${redirect_uri}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect`
}

function toPromise(fn, config = {}) {
  return new Promise((resolve, reject) => {
    fn({
      ...config,
      success(res) {
        resolve(res)
      },
      fail(err) {
        reject(err)
      },
      complete(err) {
        reject(err)
      },
      cancel(err) {
        reject(err)
      },
    })
  })
}

export function pay(config) {
  console.log(instance)
  return toPromise(instance.chooseWXPay, config)
}

export function openAddress() {
  return new Promise((resolve, reject) => {
    wechatEvevt('openAddress', {})
      .then(res => {
        resolve(res)
      })
      .catch(res => {
        if (res.is_ready) {
          res.wx.openAddress({
            fail(res) {
              reject(res)
            },
            success(res) {
              resolve(res)
            },
          })
        } else {
          reject(res)
        }
      })
  })
}

export function openShareAll(config) {
  config || {}
  config.type = config.type == undefined ? 'link' : config.type
  return new Promise(resolve => {
    getWechatConfig().then(res => {
      wechatObj.signSignature({
        nonceStr: res.data.nonceStr,
        signature: res.data.signature,
        timestamp: res.data.timestamp,
      })
      instance = wechatObj.getOriginalWx()
      instance.ready(() => {
        instance.updateAppMessageShareData(config)
        instance.updateTimelineShareData(config)
        resolve()
      })
    })
  })
}

export function openShareAppMessage(config) {
  instance.updateAppMessageShareData(config)
  instance.onMenuShareAppMessage && instance.onMenuShareAppMessage(config)
}

export function openShareTimeline(config) {
  instance.updateTimelineShareData(config)
  instance.onMenuShareTimeline && instance.onMenuShareTimeline(config)
}

export function wechatEvevt(name, config) {
  return new Promise((resolve, reject) => {
    let wx
    let configDefault = {
      fail(res) {
        if (wx) return reject({ is_ready: true, wx: wx })
        getWechatConfig().then(res => {
          wechatObj.signSignature({
            nonceStr: res.data.nonceStr,
            signature: res.data.signature,
            timestamp: res.data.timestamp,
          })
          wx = wechatObj.getOriginalWx()
          reject({ is_ready: true, wx: wx })
        })
      },
      success(res) {
        resolve(res)
      },
    }
    Object.assign(configDefault, config)
    if (typeof instance !== 'undefined') {
      instance.ready(() => {
        if (typeof name === 'object') {
          name.forEach(item => {
            instance[item] && instance[item](configDefault)
          })
        } else instance[name] && instance[name](configDefault)
      })
    } else {
      getWechatConfig().then(res => {
        const _wx = WechatJSSDK(res.data)
        _wx.initialize().then(() => {
          instance = _wx.getOriginalWx()
          instance.ready(() => {
            if (typeof name === 'object') {
              name.forEach(item => {
                instance[item] && instance[item](configDefault)
              })
            } else instance[name] && instance[name](configDefault)
          })
        })
      })
    }
  })
}

export function ready() {
  return new Promise(resolve => {
    if (typeof instance !== 'undefined') {
      instance.ready(() => {
        resolve(instance)
      })
    } else {
      getWechatConfig().then(res => {
        const _wx = WechatJSSDK(res.data)
        _wx.initialize().then(() => {
          instance = _wx.wx
          instance.ready(() => {
            resolve(instance)
          })
        })
      })
    }
  })
}

export function wxShowLocation() {
  return new Promise(() => {
    wechatEvevt('getLocation', { type: 'wgs84' })
      .then(res => {
        let latitude = res.latitude // 纬度
        let longitude = res.longitude // 经度
        cookie.set(LATITUDE, latitude)
        cookie.set(LONGITUDE, longitude)
      })
      .catch(res => {
        if (res.is_ready) {
          res.wx.getLocation({
            success(res) {
              let latitude = res.latitude // 纬度
              let longitude = res.longitude // 经度
              cookie.set(LATITUDE, latitude)
              cookie.set(LONGITUDE, longitude)
            },
            cancel() {
              cookie.remove(LATITUDE)
              cookie.remove(LONGITUDE)
              uni.showToast({
                title: '取消获取位置',
                icon: 'none',
                duration: 2000,
              })
            },
            fail() {
              cookie.remove(LATITUDE)
              cookie.remove(LONGITUDE)
              uni.showToast({
                title: '授权失败',
                icon: 'none',
                duration: 2000,
              })
            },
          })
        }
      })
  })
}
