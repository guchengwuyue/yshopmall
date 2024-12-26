<template>
  <view class="lottie-bg">
    <view id="lottie">
      <image
        src="@/static/live-logo.gif"
        rel="preload"
        mode="widthFix"
        style="width: 100%"
      />
    </view>
  </view>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex'
// 组件
// import request from "@//api/request";
import { wxappAuth } from '@/api/user'
import dayjs from 'dayjs'
import store from '@/store'
import cookie from '@/utils/store/cookie'
import { parseQuery, login, handleQrCode, getCurrentPageUrl, handleUrlParam, getCurrentPageUrlWithArgs } from '@/utils'

export default {
  name: 'Loading',
  data() {
    return {}
  },
  onShow() {
    console.log('getUser')
    var url = handleQrCode()
    if (!url) {
      url = handleUrlParam(getCurrentPageUrlWithArgs())
    }
    console.log(url)
    console.log('判断是否是分销')
    // 判断是否是分销
    if (url) {
      let urlSpread = parseInt(url.spread)
      if (urlSpread) {
        cookie.set('spread', urlSpread)
      }
    }
    if (this.$deviceType == 'app' || this.$deviceType == 'weixinh5') {
      this.$yrouter.switchTab({
        path: '/pages/home/index',
      })
      return
    }
    if (this.$store.getters.token) {
      // 如果token存在，直接进行进页面
      console.log('登录状态存在，直接进页面')
      this.toLaunch()
      return
    }
    console.log('进行登录操作')
    login().finally(() => {
      this.changeAuthorization(false)
      this.$yrouter.switchTab({
        path: '/pages/home/index',
      })
    })
  },
  methods: {
    ...mapActions(['changeAuthorization', 'setUserInfo', 'getUser']),
    toLaunch() {
      console.log('loading home')
      this.changeAuthorization(false)
      let redirect = cookie.get('redirect').replace(/\ /g, '')
      cookie.remove('redirect')
      if (redirect && redirect.indexOf('/pages') != -1) {
        // uni.showToast({
        //   title: '/pages' + redirect.split('/pages')[1],
        //   icon: 'none',
        //   duration: 2000,
        // })
		//此处跳转tab 得判断
		let pathUrl = '/pages' + redirect.split('/pages')[1]
		if(pathUrl ==  '/pages/home/index' || pathUrl ==  '/pages/shop/GoodsClass/index' || pathUrl ==  '/pages/shop/ShoppingCart/index' || pathUrl ==  '/pages/user/User/index'){
			this.$yrouter.switchTab({
			  path: pathUrl,
			})
		}else{
			this.$yrouter.replace({
			  path: pathUrl,
			})
		}
       
      } else {
        this.$yrouter.switchTab({
          path: '/pages/home/index',
        })
      }
    },
  },
}
</script>

<style scoped lang="less">
.lottie-bg {
  position: fixed;
  left: 0;
  top: 0;
  background-color: #fff;
  width: 100%;
  height: 100%;
  z-index: 999;
  display: -webkit-flex;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  justify-content: center;
}

#lottie {
  width: 35%;
  display: block;
  overflow: hidden;
  transform: translate3d(0, 0, 0);
  margin: auto;
}
</style>
