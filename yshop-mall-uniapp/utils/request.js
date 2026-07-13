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

import store from '../store'
import { handleLoginFailure } from '@/utils'
import { VUE_APP_API_URL } from '@/config'
import cookie from '@/utils/store/cookie'

const fly = new Fly()
fly.config.baseURL = VUE_APP_API_URL

// 小程序测试请求域名
// #ifdef MP-WEIXIN
// fly.config.baseURL = 'http://29e25012l6.qicp.vip/api'
// #endif

// #ifdef APP-PLUS
// app端
// fly.config.baseURL = 'http://29e25012l6.qicp.vip/api'
// #endif

fly.interceptors.response.use(
  response => {
    // console.log(response)
    // 定时刷新access-token
    return response
  },
  error => {
	console.log(error)
    if (error.toString() == 'Error: Network Error') {
      console.log('————————')
      console.log('请求失败', error)
      console.log('————————')
      handleLoginFailure()
      return Promise.reject({ msg: '未登录', toLogin: true })
    }
    if (error.status == 401) {
      console.log('————————')
      console.log('登录失效 401', error)
      console.log('————————')
      handleLoginFailure()
      return Promise.reject({ msg: '未登录', toLogin: true })
    }
    if (error.response.data.status == 5109) {
      uni.showToast({
        title: error.response.data.msg,
        icon: 'none',
        duration: 2000,
      })
    }
    return Promise.reject(error)
  }
)

const defaultOpt = { login: true }

function baseRequest(options) {
  // 从缓存中获取 token 防止 token 失效后还会继续请求的情况
  const token = cookie.get('login_status')

  // 合并传参过来的 headers
  // 如果接口需要登录，携带 token 去请求
console.log(options)

  options.headers = {
    ...options.headers,
	// hahah: 'Bearer2 ',
  }
  // if(options.method=='post'){
	//   options.headers.hahah='Bearer2 '
  // }

  if (options.login === true) {
    options.headers = {
      ...options.headers,
      Authorization: 'Bearer ' + token,
    }
  }

  // // 如果需要登录才可访问的接口没有拿到 token 视为登录失效
  // if (options.login === true && !token) {
  //   // 跳转到登录或授权页面
  //   handleLoginFailure()
  //   // 提示错误信息
  //   return Promise.reject({ msg: '未登录', toLogin: true })
  // }
  // 结构请求需要的参数
  const { url, params, data, login, ...option } = options

  // 发起请求
  return fly
    .request(url, params || data, {
      ...option,
    })
    .then(res => {
      const data = res.data || {}
      if (res.status !== 200) {
        return Promise.reject({ msg: '请求失败', res, data })
      }
      console.log(data)
      if ([401, 403].indexOf(data.status) !== -1) {
        handleLoginFailure()
        return Promise.reject({ msg: res.data.msg, res, data, toLogin: true })
      } else if (data.status === 200) {
        return Promise.resolve(data, res)
      } else if (data.status == 5101) {
        return Promise.reject({ msg: res.data.msg, res, data })
      } else {
        return Promise.reject({ msg: res.data.msg, res, data })
      }
    })
}

/**
 * http 请求基础类
 * 参考文档 https://www.kancloud.cn/yunye/axios/234845
 *
 */
const request = ['post', 'put', 'patch'].reduce((request, method) => {
  /**
   *
   * @param url string 接口地址
   * @param data object get参数
   * @param options object axios 配置项
   * @returns {AxiosPromise}
   */
  request[method] = (url, data = {}, options = {}) => {
    return baseRequest(Object.assign({ url, data, method }, defaultOpt, options))
  }
  return request
}, {});

['get', 'delete', 'head'].forEach(method => {
  /**
   *
   * @param url string 接口地址
   * @param params object get参数
   * @param options object axios 配置项
   * @returns {AxiosPromise}
   */
  request[method] = (url, params = {}, options = {}) => {
    return baseRequest(Object.assign({ url, params, method }, defaultOpt, options))
  }
})

export default request
