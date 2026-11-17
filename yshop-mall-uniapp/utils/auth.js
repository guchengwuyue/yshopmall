import Vue from 'vue'
import { wxappAuth, getUserInfo } from '@/api/user'
import store from '@/store'
import dayjs from 'dayjs'
import cookie from '@/utils/store/cookie'
import { wechat, auth, oAuth, toAuth } from '@/libs/wechat'
import {
  parseQuery,
  getCurrentPageUrl,
  getCurrentPageUrlWithArgs,
  parseUrl,
  push,
  replace,
  reLaunch,
  switchTab,
} from '@/utils/router'

export const getProvider = service => {
  return new Promise((resolve, reject) => {
    uni.getProvider({
      service: service || 'oauth',
      success: function (res) {
        if (res.provider) {
          resolve(res.provider[0])
        }
      },
      fail() {
        reject('获取环境服务商失败')
      },
    })
  }).catch(error => {
    console.log('167', error)
  })
}

export const authorize = authorizeStr => {
  return new Promise((resolve, reject) => {
    console.log('检验授权', `scope.${authorizeStr}`)
    uni.getSetting({
      success(res) {
        console.log(res.authSetting)
        if (res.authSetting[`scope.${authorizeStr}`]) {
          resolve(true)
        } else {
          resolve(false)
        }
      },
      fail() {
        resolve(false)
      },
    })
  })
}

export const login = loginInfo => {
  return new Promise((resolve, reject) => {
    if (Vue.prototype.$deviceType == 'weixin') {
      const { code } = parseQuery()
      if (code) {
        auth(code)
          .then(() => {
            let redirect = cookie.get('redirect').replace(/\ /g, '')
            console.log('199', redirect)
            if (redirect) {
              redirect = redirect.split('/pages')[1]
              if (!redirect) {
                redirect = '/Loading/index'
              }
              reLaunch({
                path: '/pages' + redirect,
              })
              cookie.remove('redirect')
            } else {
              reLaunch({
                path: '/pages/home/index',
              })
            }
          })
          .catch(() => {
            reject('当前运行环境为微信浏览器')
            reLaunch({
              path: '/pages/home/index',
            })
          })
      } else {
      }
      return
    }
    if (Vue.prototype.$deviceType == 'weixinh5') {
      console.log('当前运行环境为H5')
      reject('当前运行环境为H5')
      return
    }
    if (Vue.prototype.$deviceType == 'app') {
      console.log('当前运行环境为app')
      reject('当前运行环境为app')
      return
    }
    console.log('开始登录')
    getProvider()
      .then(async provider => {
        console.log(provider)
        if (!provider) {
          reject()
        }
        let authorizeRes = await authorize('userInfo')
        if (!wx.getUserProfile && !authorizeRes) {
          reject('用户未授权')
          return
        }
        uni.login({
          provider: provider,
          success: async function (loginRes) {
            let code = loginRes.code
            cookie.set('wxLoginCode', loginRes.code)

            if (wx.getUserProfile) {
              if (loginInfo) {
                wxappAuth({
                  encryptedData: loginInfo.encryptedData,
                  iv: loginInfo.iv,
                  code: code,
                  spread: cookie.get('spread'),
                })
                  .then(({ data }) => {
                    uni.hideLoading()
                    store.commit('login', {
                      token: data.token,
                      expiresTime: dayjs(data.expires_time),
                    })
                    store.dispatch('userInfo', true)
                    getUserInfo()
                      .then(user => {
                        uni.setStorageSync('uid', user.data.uid)
                        store.dispatch('setUserInfo', user.data)
                        resolve(user)
                      })
                      .catch(error => {
                        reject('获取用户信息失败')
                      })
                  })
                  .catch(error => {
                    console.log(error)
                    reject('登录接口调用失败')
                  })
              } else {
                reject('用户未授权')
                return
              }
            } else {
              uni.getUserInfo({
                provider: provider,
                success: function (user) {
                  wxappAuth({
                    encryptedData: user.encryptedData,
                    iv: user.iv,
                    code: code,
                    spread: cookie.get('spread'),
                  })
                    .then(({ data }) => {
                      uni.hideLoading()
                      store.commit('login', {
                        token: data.token,
                        expiresTime: dayjs(data.expires_time),
                      })
                      store.dispatch('userInfo', true)
                      getUserInfo()
                        .then(user => {
                          uni.setStorageSync('uid', user.data.uid)
                          store.dispatch('setUserInfo', user.data)
                          resolve(user)
                        })
                        .catch(error => {
                          reject('获取用户信息失败')
                        })
                    })
                    .catch(error => {
                      console.log(error)
                      reject('登录接口调用失败')
                    })
                },
                fail() {
                  reject('获取用户信息失败')
                },
              })
            }
          },
          fail() {
            reject('调用登录接口失败')
          },
        })
      })
      .catch(error => {
        reject('获取环境服务商失败')
      })
  })
}

export const handleGetUserInfo = () => {
  getUserInfo().then(res => {
    store.dispatch('setUserInfo', res.data)
    let redirect = cookie.get('redirect').replace(/\ /g, '')
    console.log('redirect:', redirect)
    if (redirect) {
      reLaunch({
        path: redirect,
      })
      return
    }
    reLaunch({
      path: '/pages/home/index',
    })
  })
}

export function handleAuth() {
  if (cookie.get('login_status')) {
    return true
  }
  return false
}

export function routerPermissions(url, type) {
  console.log('routerPermissions', url)
  let path = url
  if (!path) {
    path = '/' + getCurrentPageUrlWithArgs()
  }
  console.log(Vue.prototype.$deviceType)
  if (Vue.prototype.$deviceType == 'routine') {
    authorize('userInfo')
      .then(() => {
        login()
          .then(res => {
            store.commit('updateAuthorizationPage', false)
            if (path == '/pages/shop/ShoppingCart/index' || path == '/pages/user/User/index') {
              return
            }
            if (type == 'reLaunch') {
              reLaunch({
                path,
              })
              return
            }
            if (type == 'replace') {
              replace({
                path,
              })
              return
            }
            push({
              path,
            })
          })
          .catch(error => {
            uni.showToast({
              title: error,
              icon: 'none',
              duration: 2000,
            })
            reLaunch({
              path: '/pages/authorization/index',
            })
            cookie.set('redirect', path)
          })
      })
      .catch(error => {
        if (path == '/pages/shop/ShoppingCart/index' || path == '/pages/user/User/index') {
          switchTab({
            path,
          })
          store.commit('updateAuthorizationPage', false)
          return
        }
        reLaunch({
          path: '/pages/authorization/index',
        })
        cookie.set('redirect', path)
      })
  } else if (Vue.prototype.$deviceType == 'weixin') {
    wechat().then(() => oAuth())
    if (!type) {
      push({
        path: url,
      })
    }
  } else {
    console.log(path)
    cookie.set('redirect', path)
    push({
      path: '/pages/user/Login/index',
    })
  }
}

export function handleQrCode() {
  var urlSpread = parseQuery()['q']
  if (urlSpread) {
    if (urlSpread.indexOf('%3F') != -1) {
      urlSpread = urlSpread
        .split('%3F')[1]
        .replace(/%3D/g, ':')
        .replace(/%26/g, ',')
        .split(',')
        .map((item, index) => {
          item = item.split(':')
          return `"${item[0]}":"${item[1]}"`
        })
        .join(',')
      urlSpread = JSON.parse('{' + urlSpread + '}')
      return urlSpread
    } else {
      return handleUrlParam(urlSpread)
    }
  }
  return null
}

export function handleUrlParam(path) {
  var theRequest = new Object()
  if (path.includes('?')) {
    var url = path.split('?')[1]
    let strs = url.split('&')
    for (var i = 0; i < strs.length; i++) {
      theRequest[strs[i].split('=')[0]] = unescape(strs[i].split('=')[1])
    }
  }
  return theRequest
}

export const handleLoginFailure = () => {
  console.log('退出登录，标记当前页面为授权页面，防止多次跳转')

  store.commit('logout')
  store.commit('updateAuthorization', false)

  let currentPageUrl = getCurrentPageUrl()
  console.log('store.state.$deviceType:', store.state.$deviceType)
  if (store.state.$deviceType == 'weixin') {
    if (!store.getters.isAuthorizationPage) {
      store.commit('updateAuthorizationPage', true)
      toAuth()
    }
    return
  } else {
    if (store.getters.isAuthorizationPage || currentPageUrl == '/pages/user/Login/index') {
      console.log(store.getters.isAuthorizationPage, currentPageUrl, '已经是登录页面或者授权页面，跳出方法')
      return
    }
  }

  store.commit('updateAuthorizationPage', true)

  let path = '/' + getCurrentPageUrlWithArgs()

  let qrCode = handleQrCode()

  if (qrCode) {
    if (currentPageUrl == 'pages/activity/GroupRule/index') {
      if (qrCode.pinkId) {
        path = parseUrl({
          path: `/ ${currentPageUrl} `,
          query: {
            id: qrCode.pinkId,
          },
        })
        if (qrCode.spread) {
          cookie.set('spread', qrCode.spread || 0)
        }
      } else {
        handleNoParameters()
      }
    }

    if (currentPageUrl == 'pages/shop/GoodsCon/index') {
      if (qrCode.productId) {
        path = parseUrl({
          path: `/ ${currentPageUrl} `,
          query: {
            id: qrCode.productId,
          },
        })
        if (qrCode.spread) {
          cookie.set('spread', qrCode.spread || 0)
        }
      } else {
        handleNoParameters()
      }
    }
  }

  console.log(path, '重定向页面地址')
  routerPermissions(path, 'reLaunch')
}

const handleNoParameters = () => {
  uni.showToast({
    title: '未获取到必要参数，即将跳转首页',
    icon: 'success',
    duration: 2000,
  })
  setTimeout(() => {
    clearTimeout()
    switchTab({
      path: '/pages/home/index',
    })
  }, 1500)
}
