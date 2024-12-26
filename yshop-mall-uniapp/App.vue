<script>
import Vue from 'vue'
// #ifdef H5
var VConsole = require('@/utils/vconsole.min.js')
//  #endif
export default {
  onLaunch: function () {
    const updateManager = uni.getUpdateManager()
    updateManager.onCheckForUpdate(function (res) {
      // 请求完新版本信息的回调
      console.log(res.hasUpdate)
    })
    // 下载新版本
    updateManager.onUpdateReady(function () {
      uni.showModal({
        title: '更新提示',
        content: '新版本已经准备好，是否重启应用？',
        success(res) {
          if (res.confirm) {
            // 重启应用
            updateManager.applyUpdate()
          }
        },
      })
    })
    // 新版本下载失败
    updateManager.onUpdateFailed(function (res) {
      // 新的版本下载失败
      uni.showModal({
        title: '已经有新版本了哟~',
        content: '新版本已经上线啦~，请您删除当前小程序，重新搜索打开哟~',
      })
    })
  },
  onShow: function () {
    console.log('App Show')
  },
  onHide: function () {
    console.log('App Hide')
  },
  mounted() {
    // #ifdef H5
    //var vConsole = new VConsole()
    console.log('开启调试')
    //  #endif
    this.setAppInfo()
  },
  methods: {
    // 获取系统栏高度
    async setAppInfo() {
      let that = this
      return new Promise((resolve, reject) => {
        uni.getSystemInfo({
          success: function (e) {
            Vue.prototype.StatusBar = e.statusBarHeight
            // #ifdef H5
            Vue.prototype.CustomBar = e.statusBarHeight + 45
            // #endif

            // #ifdef APP-PLUS
            if (e.platform == 'android') {
              Vue.prototype.CustomBar = e.statusBarHeight + 50
            } else {
              Vue.prototype.CustomBar = e.statusBarHeight + 45
            }
            // #endif

            // #ifdef MP-WEIXIN
            let custom = wx.getMenuButtonBoundingClientRect()
            Vue.prototype.Custom = custom
            Vue.prototype.CustomBar = custom.bottom + custom.top - e.statusBarHeight
            // #endif
          },
        })
      })
    },

    // 自动登录
    async autoLogin(data) {},
  },
}
</script>

<style lang="less">
/*每个页面公共css */
@import 'animate.css';
@import './assets/iconfont/iconfont.css';
@import './assets/css/base.less';
@import './assets/css/reset.less';
@import './assets/css/style.less';
</style>
