// #ifdef H5
// h5端
import Fly from 'flyio/dist/npm/fly'
// #endif

// #ifdef APP-PLUS
// app端
import Fly from 'flyio/dist/npm/wx'
// #endif

// #ifdef MP-WEIXIN
import Fly from 'flyio/dist/npm/wx'
// #endif

import { handleLoginFailure } from '@/utils/auth'
import { VUE_APP_API_URL } from '@/config'
import cookie from '@/utils/store/cookie'

/**
 * 请求层约定：
 * - 默认 login: true，会带 Authorization；公开接口请显式传 { login: false }
 * - 超时统一 15s；网络异常 / 超时自动 toast，不登出
 * - toast: true 时业务错误也会 toast；silent: true 抑制一切自动 toast
 */
const fly = new Fly()
fly.config.baseURL = VUE_APP_API_URL
fly.config.timeout = 15000

function isDev() {
  // #ifdef H5
  return process.env.NODE_ENV === 'development'
  // #endif
  // #ifndef H5
  return false
  // #endif
}

function debugLog(...args) {
  if (isDev()) {
    console.log(...args)
  }
}

function showErrorToast(title) {
  if (!title) return
  uni.showToast({
    title: String(title),
    icon: 'none',
    duration: 2000,
  })
}

fly.interceptors.response.use(
  response => {
    return response
  },
  error => {
    debugLog(error)
    // 断网 / 网络异常：仅提示，不登出
    if (error && (error.message === 'Network Error' || String(error) === 'Error: Network Error')) {
      showErrorToast('网络异常')
      return Promise.reject({ msg: '网络异常', networkError: true })
    }
    // 超时
    if (error && (error.message === 'timeout' || (error.message && error.message.indexOf('timeout') !== -1))) {
      showErrorToast('请求超时')
      return Promise.reject({ msg: '请求超时', timeout: true })
    }
    // HTTP 401：登录失效才登出跳转
    if (error.status == 401) {
      debugLog('登录失效 401', error)
      handleLoginFailure()
      return Promise.reject({ msg: '未登录', toLogin: true })
    }
    if (error.response && error.response.data && error.response.data.status == 5109) {
      showErrorToast(error.response.data.msg)
    }
    return Promise.reject(error)
  }
)

const defaultOpt = { login: true, silent: false, toast: false }

function baseRequest(options) {
  // 从缓存中获取 token 防止 token 失效后还会继续请求的情况
  const token = cookie.get('login_status')

  debugLog(options)

  options.headers = {
    ...options.headers,
  }

  // 默认需要登录态；公开接口传 login: false
  if (options.login === true) {
    options.headers = {
      ...options.headers,
      Authorization: 'Bearer ' + token,
    }
  }

  const { url, params, data, login, silent, toast, ...option } = options
  const shouldToastBiz = toast === true && silent !== true

  return fly
    .request(url, params || data, {
      ...option,
    })
    .then(res => {
      const data = res.data || {}
      if (res.status !== 200) {
        return Promise.reject({ msg: '请求失败', res, data })
      }
      debugLog(data)
      // 业务 status 401 / 403：登出跳转
      if ([401, 403].indexOf(data.status) !== -1) {
        handleLoginFailure()
        return Promise.reject({ msg: res.data.msg, res, data, toLogin: true })
      } else if (data.status === 200) {
        return Promise.resolve(data, res)
      } else if (data.status == 5101) {
        if (shouldToastBiz) {
          showErrorToast(res.data.msg)
        }
        return Promise.reject({ msg: res.data.msg, res, data })
      } else {
        if (shouldToastBiz && res.data && res.data.msg) {
          showErrorToast(res.data.msg)
        }
        return Promise.reject({ msg: res.data.msg, res, data })
      }
    })
}

/**
 * http 请求基础类
 * 参考文档 https://www.kancloud.cn/yunye/axios/234845
 *
 * options:
 * - login {boolean} 默认 true，是否附带 Bearer token；公开接口传 false
 * - toast {boolean} 默认 false，业务错误是否自动 toast
 * - silent {boolean} 默认 false，为 true 时抑制自动 toast（含网络提示）
 */
const request = ['post', 'put', 'patch'].reduce((request, method) => {
  request[method] = (url, data = {}, options = {}) => {
    return baseRequest(Object.assign({ url, data, method }, defaultOpt, options))
  }
  return request
}, {});

['get', 'delete', 'head'].forEach(method => {
  request[method] = (url, params = {}, options = {}) => {
    return baseRequest(Object.assign({ url, params, method }, defaultOpt, options))
  }
})

export default request
