import store from '@/store'
import { VUE_APP_API_URL } from '@/config'

export function dataFormat(time, option) {
  time = +time * 1000
  const d = new Date(time)
  const now = new Date().getTime()

  const diff = (now - d) / 1000
  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) {
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    return '1天前'
  }
  if (option) {
    // return parseTime(time, option);
  } else {
    let timeStr = d.getFullYear() + '年' + (d.getMonth() + 1) + '月' + d.getDate() + '日' + d.getHours() + '时' + d.getMinutes() + '分'
    return timeStr
  }
}

// "YYYY-mm-dd HH:MM"
export function dateFormatL(fmt, date) {
  let ret
  const opt = {
    'Y+': date.getFullYear().toString(),
    'm+': (date.getMonth() + 1).toString(),
    'd+': date.getDate().toString(),
    'H+': date.getHours().toString(),
    'M+': date.getMinutes().toString(),
    'S+': date.getSeconds().toString(),
  }
  for (let k in opt) {
    ret = new RegExp('(' + k + ')').exec(fmt)
    if (ret) {
      fmt = fmt.replace(ret[1], ret[1].length == 1 ? opt[k] : opt[k].padStart(ret[1].length, '0'))
    }
  }
  return fmt
}

export function dateFormatT(time) {
  time = +time * 1000
  const d = new Date(time)
  return d.getFullYear() + '/' + (d.getMonth() + parseInt(1)) + '/' + d.getDate()
}

export function trim(str) {
  return String.prototype.trim.call(str)
}

export function isType(arg, type) {
  return Object.prototype.toString.call(arg) === '[object ' + type + ']'
}

export function isWeixin() {
  if (navigator && navigator.userAgent && navigator.userAgent.toLowerCase().indexOf('micromessenger') !== -1) {
    return true
  }
  return false
}

export const copyClipboard = data => {
  uni.setClipboardData({
    data: data,
    success: res => {
      uni.showToast({
        title: '复制成功',
        icon: 'success',
        duration: 2000,
      })
    },
  })
}

const getImageInfo = images => {
  return new Promise((resolve, reject) => {
    let imageAry = {}
    images.map((item, index) => {
      uni.getImageInfo({
        src: item,
        fail: function (res) {
          imageAry[index] = null
          if (imageAry.length == images.length) {
            resolve(imageAry)
          }
        },
        success: function (res) {
          imageAry[index] = res
          if (Object.keys(imageAry).length == images.length) {
            resolve(imageAry)
          }
        },
      })
    })
  })
}

/**
 * 获取分享海报
 */
export const PosterCanvas = (storeData, successCallBack) => {
  uni.showLoading({
    title: '海报生成中',
    mask: true,
  })
  getImageInfo([storeData.image, storeData.code]).then(res => {
    let contentHh = 48 * 1.3
    const ctx = uni.createCanvasContext('myCanvas')
    ctx.clearRect(0, 0, 0, 0)
    const WIDTH = 747
    const HEIGHT = 1326
    ctx.fillStyle = '#FFFFFF'
    ctx.fillRect(0, 0, WIDTH, HEIGHT)
    ctx.drawImage(res[0].path, 0, 0, WIDTH, WIDTH)
    ctx.drawImage(res[1].path, 40, 1064, 200, 200)
    ctx.save()
    let r = 90
    let cx = 40
    let cy = 990
    ctx.arc(cx + r, cy + r, r, 0, 2 * Math.PI)
    ctx.clip()
    ctx.restore()
    ctx.setTextAlign('center')
    ctx.setFontSize(48)
    ctx.setFillStyle('#000')
    ctx.fillText(storeData.title, WIDTH / 2, 810 + contentHh)
    ctx.setTextAlign('center')
    ctx.setFontSize(32)
    ctx.setFillStyle('red')
    ctx.fillText('￥' + storeData.price, WIDTH / 2, 985)
    ctx.setTextAlign('center')
    ctx.setFontSize(22)
    ctx.setFillStyle('#333333')
    ctx.fillText('长按识别二维码立即购买', WIDTH / 2, 1167)
    ctx.save()
    ctx.draw(true, () => {
      uni.canvasToTempFilePath({
        canvasId: 'myCanvas',
        fileType: 'png',
        destWidth: WIDTH,
        destHeight: HEIGHT,
        success: function (res) {
          uni.hideLoading()
          successCallBack && successCallBack(res.tempFilePath)
        },
        fail: function (error) {
          console.log(error)
        },
      })
    })
  })
}

export function chooseImage(callback) {
  uni.chooseImage({
    count: 1,
    sourceType: ['album'],
    success: res => {
      uni.getImageInfo({
        src: res.tempFilePaths[0],
        success: image => {
          uni.showLoading({ title: '图片上传中', mask: true })
          uni.uploadFile({
            url: `${VUE_APP_API_URL}/api/upload`,
            file: image,
            filePath: image.path,
            header: {
              Authorization: 'Bearer ' + store.getters.token,
            },
            name: 'file',
            success: res => {
              if (callback) {
                callback(JSON.parse(res.data).link)
              }
            },
            fail: err => {
              uni.showToast({
                title: '上传图片失败',
                icon: 'none',
                duration: 2000,
              })
            },
            complete: res => {
              uni.hideLoading()
            },
          })
        },
        fail: err => {
          uni.showToast({
            title: '获取图片信息失败',
            icon: 'none',
            duration: 2000,
          })
        },
      })
    },
  })
}

export function handleErrorMessage(err) {
  console.log(err)
  uni.hideLoading()
  uni.showToast({
    title: err.msg || err.response.data.msg || err.response.data.message || '创建订单失败',
    icon: 'none',
    duration: 2000,
  })
}
