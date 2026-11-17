import stringify from '@/utils/querystring'

export function parseQuery() {
  let res = {}

  // #ifdef H5
  const query = (location.href.split('?')[1] || '').trim().replace(/^(\?|#|&)/, '')

  if (!query) {
    return res
  }

  query.split('&').forEach(param => {
    const parts = param.replace(/\+/g, ' ').split('=')
    const key = decodeURIComponent(parts.shift())
    const val = parts.length > 0 ? decodeURIComponent(parts.join('=')) : null

    if (res[key] === undefined) {
      res[key] = val
    } else if (Array.isArray(res[key])) {
      res[key].push(val)
    } else {
      res[key] = [res[key], val]
    }
  })
  // #endif
  // #ifndef H5
  var pages = getCurrentPages()
  var currentPage = pages[pages.length - 1]
  res = currentPage.options
  // #endif

  return res
}

/*获取当前页url*/
export function getCurrentPageUrl() {
  var pages = getCurrentPages()
  var currentPage = pages[pages.length - 1]
  var url = currentPage.route
  return url
}

/*获取当前页带参数的url*/
export function getCurrentPageUrlWithArgs() {
  var pages = getCurrentPages()
  var currentPage = pages[pages.length - 1]
  var url = currentPage.route
  var options = currentPage.options
  var urlWithArgs = url + '?'
  for (var key in options) {
    var value = options[key]
    urlWithArgs += key + '=' + value + '&'
  }
  urlWithArgs = urlWithArgs.substring(0, urlWithArgs.length - 1)
  return urlWithArgs
}

export function parseUrl(location) {
  if (typeof location === 'string') return location
  const { path, query } = location

  const queryStr = stringify(query)

  if (!queryStr) {
    return path
  }

  return `${path}?${queryStr}`
}

export function parseRoute($mp) {
  const _$mp = $mp || {}
  const path = _$mp.page && _$mp.page.route
  return {
    path: `/${path}`,
    params: {},
    query: _$mp.query || _$mp.page.options,
    hash: '',
    fullPath: parseUrl({
      path: `/${path}`,
      query: _$mp.query || _$mp.page.options,
    }),
    name: path && path.replace(/\/(\w)/g, ($0, $1) => $1.toUpperCase()),
  }
}

export const handleLoginStatus = (location, complete, fail, success) => {
  // 从 location 中获取当前url，location typeof string || object
  let path = ''
  if (typeof location === 'string') {
    path = location
  } else {
    path = location.path
  }
  return new Promise((resolve, reject) => {
    resolve({
      url: parseUrl(location),
      complete,
      fail,
      success,
    })
  }).catch(error => {
    console.log(error)
  })
}

export function push(location, complete, fail, success) {
  handleLoginStatus(location, complete, fail, success)
    .then(params => {
      uni.navigateTo(params)
    })
    .catch(error => {
      // 没有权限
    })
}

export function replace(location, complete, fail, success) {
  handleLoginStatus(location, complete, fail, success)
    .then(params => {
      uni.redirectTo(params)
    })
    .catch(error => {
      // 没有权限
    })
}

export function reLaunch(location, complete, fail, success) {
  handleLoginStatus(location, complete, fail, success)
    .then(params => {
      uni.reLaunch(params)
    })
    .catch(error => {
      console.log(error)
    })
}

export function go(delta) {
  uni.navigateBack({
    delta,
  })
}

export function back() {
  uni.navigateBack({
    delta: 1,
    success: function (e) {},
    fail: function (e) {},
  })
}

export function switchTab(location, complete, fail, success) {
  handleLoginStatus(location, complete, fail, success)
    .then(params => {
      uni.switchTab(params)
    })
    .catch(error => {
      // 没有权限
    })
}

export const _router = {
  mode: 'history',
  switchTab,
  push,
  replace,
  go,
  back,
  reLaunch,
}
