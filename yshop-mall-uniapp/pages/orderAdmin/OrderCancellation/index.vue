<template>
  <view class="OrderCancellation">
    <view class="header">
      <image mode="aspectFit" :src="`${$VUE_APP_RESOURCES_URL}/images/writeOffBg.jpg`" @click="openQRCode" />
    </view>
    <view class="whiteBg">
      <view class="input">
        <input type="number" placeholder="0" v-model="verify_code" />
      </view>
      <view class="bnt" @click="storeCancellation">立即核销</view>
      <view class="bnt" v-if="$deviceType !== 'weixin'" @click="openQRCode">扫码核销</view>
    </view>
    <!-- #ifndef H5 -->
    <!-- <view class="scan" v-if="iswechat">
			<image :src="`${$VUE_APP_RESOURCES_URL}/images/scan.gif`" @click="openQRCode" />
		</view> -->
    <!-- #endif -->
    <WriteOff :iShidden="iShidden" :orderInfo="orderInfo" @cancel="cancel" @confirm="confirm"></WriteOff>
  </view>
</template>

<script>
import WriteOff from '@/components/WriteOff'
import { isWeixin } from '@/utils'
// import { wechatEvevt } from "@/libs/wechat";
import { orderVerific } from '@/api/order'
const NAME = 'OrderCancellation'

export default {
  name: NAME,
  components: {
    WriteOff,
  },
  props: {},
  data: function() {
    return {
      iShidden: true,
      orderInfo: {},
      verify_code: '',
    }
  },
  mounted: function() {},
  methods: {
    cancel: function(res) {
      this.iShidden = res
    },
    confirm: function() {
      orderVerific(this.verify_code, 1)
        .then(res => {
          this.iShidden = true
          this.verify_code = ''
          uni.showToast({
            title: res.msg,
            icon: 'none',
            duration: 2000,
          })
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    storeCancellation: function() {
      let ref = /[0-9]{12}/
      if (!this.verify_code) {
        uni.showToast({
          title: '请输入核销码',
          icon: 'none',
          duration: 2000,
        })
        return
      }
      if (!ref.test(this.verify_code)) {
        uni.showToast({
          title: '请输入正确的核销码',
          icon: 'none',
          duration: 2000,
        })
        return
      }
      uni.showLoading({
        title: '查询中',
      })
      orderVerific(this.verify_code, 0)
        .then(res => {
          uni.hideLoading()
          this.orderInfo = res.data
          this.iShidden = false
          uni.showToast({
            title: res.msg,
            icon: 'success',
            duration: 2000,
          })
        })
        .catch(error => {
          uni.hideLoading()
          uni.showToast({
            title: error.msg || error.response.data.msg || error.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    openQRCode: function() {
      let that = this
      // 这里需要调用扫码功能
      uni.scanCode({
        success: res => {
          var result = res.result
          if (result) {
            that.verify_code = res.result
            that.storeCancellation()
          } else {
            uni.showToast({
              title: '没有扫描到什么！',
              icon: 'none',
              duration: 2000,
            })
          }
        },
      })
    },
  },
}
</script>

<style scoped lang="less">
.OrderCancellation .header {
  width: 100%;
  height: 3 * 100rpx;
  background-size: 100% 100%;

  image {
    width: 100%;
    height: 3 * 100rpx;
  }
}

.OrderCancellation .whiteBg {
  width: 6.9 * 100rpx;
  background-color: #fff;
  margin: -0.93 * 100rpx auto 0 auto;
  padding-top: 0.8 * 100rpx;
  padding-bottom: 30rpx;
  z-index: 20;
  position: relative;
  border-radius: 0.06 * 100rpx 0.06 * 100rpx 0 0;
}

.OrderCancellation .whiteBg .input {
  width: 5.8 * 100rpx;
  margin: 0 auto;
  border-bottom: 0.01 * 100rpx solid #eee;
}

.OrderCancellation .whiteBg .input input {
  padding-bottom: 0.25 * 100rpx;
  font-size: 0.6 * 100rpx;
  height: auto;
  color: #282828;
  width: 100%;
  text-align: center;
}

.OrderCancellation .whiteBg .bnt {
  font-size: 0.32 * 100rpx;
  color: #fff;
  width: 5.8 * 100rpx;
  height: 0.86 * 100rpx;
  border-radius: 0.43 * 100rpx;
  background-image: linear-gradient(to right, #eb3729 0%, #eb3729 100%);
  background-image: -webkit-linear-gradient(to right, #eb3729 0%, #eb3729 100%);
  background-image: -moz-linear-gradient(to right, #eb3729 0%, #eb3729 100%);
  text-align: center;
  line-height: 0.86 * 100rpx;
  margin: 0.55 * 100rpx auto 0 auto;
}

.OrderCancellation .scan {
  width: 3 * 100rpx;
  height: 3 * 100rpx;
  margin: 1.1 * 100rpx auto 0 auto;
}

.OrderCancellation .scan image {
  width: 100%;
  height: 100%;
  display: block;
}
</style>
