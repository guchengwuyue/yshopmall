/**
 * 首页装修画布：把失效远程图替换为本地 /static/images 资源
 */
const BROKEN_HOST =
  /https?:\/\/(app2\.yixiang\.co|image\.dayouqiantu\.cn|img\.yixiang\.co|h5\.yixiang\.co|wx\.yixiang\.co|wxapi\.yixiang\.co)[^"'\s]*/gi

const MENU_BY_NAME = {
  全部商品: '/static/images/menu_all.png',
  图文资讯: '/static/images/menu_news.png',
  我的收藏: '/static/images/menu_collect.png',
  优惠券: '/static/images/menu_coupon.png',
  拼团专区: '/static/images/menu_group.png',
  积分签到: '/static/images/menu_sign.png',
  秒杀专区: '/static/images/menu_seckill.png',
  砍价专区: '/static/images/menu_bargain.png',
}

const FILE_FALLBACK = {
  'index001.png': '/static/images/index001.png',
  'index002.png': '/static/images/index002.png',
  'index003.png': '/static/images/index003.png',
  'all.png': '/static/images/menu_all.png',
  'xw.png': '/static/images/menu_news.png',
  'news.png': '/static/images/menu_news.png',
  'colle.png': '/static/images/menu_collect.png',
  'cou.png': '/static/images/menu_coupon.png',
  'coupon.png': '/static/images/menu_coupon.png',
  'bar.png': '/static/images/menu_bargain.png',
  'bargin.png': '/static/images/menu_bargain.png',
  'bargain.png': '/static/images/menu_bargain.png',
  'sign.png': '/static/images/menu_sign.png',
  'sekill.png': '/static/images/menu_seckill.png',
  'pink.png': '/static/images/menu_group.png',
  'group.png': '/static/images/menu_group.png',
}

const BANNER_FALLBACKS = [
  '/static/images/home_banner1.png',
  '/static/images/home_banner2.png',
]

const ADV_FALLBACKS = [
  '/static/images/index001.png',
  '/static/images/index002.png',
  '/static/images/index003.png',
]

function fileName(url) {
  if (!url || typeof url !== 'string') return ''
  const clean = url.split('?')[0]
  const parts = clean.split('/')
  return parts[parts.length - 1] || ''
}

function isBroken(url) {
  if (!url || typeof url !== 'string') return true
  if (!url.trim()) return true
  return /yixiang\.co|dayouqiantu\.cn/i.test(url)
}

function mapRemoteUrl(url, fallback) {
  if (!isBroken(url)) return url
  const name = fileName(url)
  if (name && FILE_FALLBACK[name]) return FILE_FALLBACK[name]
  if (name && /^index00[123]\.png$/i.test(name)) {
    return `/static/images/${name}`
  }
  return fallback || '/static/images/home_banner1.png'
}

export function fixHomeCanvasImages(list) {
  if (!Array.isArray(list)) return list
  let bannerIdx = 0
  let advIdx = 0

  return list.map(item => {
    if (!item || !item.componentContent) return item
    const type = item.type
    const content = { ...item.componentContent }

    if (type === 'banner' && Array.isArray(content.bannerData)) {
      content.bannerData = content.bannerData.map(b => {
        const pic = mapRemoteUrl(
          b.pic,
          BANNER_FALLBACKS[bannerIdx % BANNER_FALLBACKS.length]
        )
        bannerIdx += 1
        return { ...b, pic }
      })
    }

    if (type === 'menu' && Array.isArray(content.menus)) {
      content.menus = content.menus.map(m => {
        let pic = m.pic
        if (isBroken(pic)) {
          pic =
            MENU_BY_NAME[m.name] ||
            mapRemoteUrl(pic, '/static/images/menu_all.png')
        }
        const imageArr = Array.isArray(m.imageArr)
          ? m.imageArr.map(u => (isBroken(u) ? pic : u))
          : m.imageArr
        return { ...m, pic, imageArr }
      })
    }

    if (type === 'adv' && content.detail && Array.isArray(content.detail.list)) {
      content.detail = {
        ...content.detail,
        list: content.detail.list.map(a => {
          const image = mapRemoteUrl(
            a.image,
            ADV_FALLBACKS[advIdx % ADV_FALLBACKS.length]
          )
          advIdx += 1
          return { ...a, image }
        }),
      }
    }

    return { ...item, componentContent: content }
  })
}

/** 可选：整段 JSON 字符串域名替换（兜底） */
export function replaceBrokenHostsInJson(jsonStr) {
  if (!jsonStr || typeof jsonStr !== 'string') return jsonStr
  return jsonStr.replace(BROKEN_HOST, match => mapRemoteUrl(match))
}
