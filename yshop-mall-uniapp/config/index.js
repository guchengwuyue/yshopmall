// 正式环境 API（HTTPS）
const PROD_API_URL = 'https://wxapi.yixiang.co/api'
// H5 / 本地调试
const DEV_API_URL = 'http://127.0.0.1:8008/api'

// #ifdef H5
export const VUE_APP_API_URL = process.env.NODE_ENV === 'development'
  ? DEV_API_URL
  : PROD_API_URL
// #endif

// #ifndef H5
// 小程序 / App：正式包走 HTTPS；本地联调可临时改为局域网 IP
export const VUE_APP_API_URL = PROD_API_URL
// #endif

export const VUE_APP_RESOURCES_URL = 'https://h5.yixiang.co/static'
