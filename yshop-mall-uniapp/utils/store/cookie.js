const CACHE_KEY = 'clear_0.0.1'

/**
 * 解析过期时间，返回毫秒时间戳；无过期则返回 null
 * 支持：dayjs/Date、unix 秒、毫秒时间戳、TTL 秒数
 */
function resolveExpire(time) {
  if (time === undefined || time === null || time === '') {
    return null
  }
  if (typeof time === 'object' && typeof time.valueOf === 'function') {
    const ms = time.valueOf()
    return Number.isFinite(ms) ? ms : null
  }
  if (typeof time === 'number') {
    if (time > 1e12) {
      // 毫秒时间戳
      return time
    }
    if (time > 1e9) {
      // unix 秒
      return time * 1000
    }
    // TTL 秒
    return Date.now() + time * 1000
  }
  if (typeof time === 'string') {
    const parsed = Date.parse(time)
    return Number.isNaN(parsed) ? null : parsed
  }
  return null
}

function isWrapped(raw) {
  return (
    raw &&
    typeof raw === 'object' &&
    Object.prototype.hasOwnProperty.call(raw, 'value') &&
    Object.prototype.hasOwnProperty.call(raw, 'expire')
  )
}

function get(key) {
  if (!key) {
    return
  }
  const raw = uni.getStorageSync(key)
  if (raw === '' || raw === undefined || raw === null) {
    return raw
  }
  // 新格式：{ value, expire }
  if (isWrapped(raw)) {
    if (raw.expire && Date.now() > raw.expire) {
      uni.removeStorageSync(key)
      return null
    }
    return raw.value
  }
  // 兼容旧数据：直接存的原始值
  return raw
}

function all() {
  return uni.getStorageInfoSync()
}

function set(key, data, time) {
  if (!key) {
    return
  }
  const expire = resolveExpire(time)
  if (expire) {
    uni.setStorageSync(key, { value: data, expire })
  } else {
    // 无 TTL：仍写入包装结构，expire 为 0 表示不过期，便于统一读取
    uni.setStorageSync(key, { value: data, expire: 0 })
  }
}

function remove(key) {
  if (!key) {
    return
  }
  try {
    uni.removeStorageSync(key)
  } catch (e) {
    // ignore
  }
}

function clearAll() {
  const res = uni.getStorageInfoSync()
  res.keys.map(item => {
    if (item == 'redirect' || item == 'spread' || item == CACHE_KEY) {
      return
    }
    remove(item)
  })
}

function _has(key) {
  if (!key) {
    return false
  }
  const value = get(key)
  return value !== undefined && value !== null && value !== ''
}

export default {
  get,
  all,
  set,
  remove,
  clearAll,
  has: _has,
  CACHE_KEY,
}
