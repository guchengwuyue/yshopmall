<template>
  <view>
    <view class="payment" :class="value === true ? 'on' : ''">
      <view class="title acea-row row-center-wrapper">
        选择付款方式<text class="iconfont icon-guanbi" @click="close"></text>
      </view>
      <view
        class="item acea-row row-between-wrapper"
        v-if="types.indexOf('weixin') !== -1"
        @click="checked('weixin')"
      >
        <view class="left acea-row row-between-wrapper">
          <view class="iconfont icon-weixinzhifu"></view>
          <view class="text">
            <view class="name">微信支付</view>
            <view class="info">使用微信快捷支付</view>
          </view>
        </view>
        <view class="iconfont icon-xiangyou"></view>
      </view>
      <!-- <view
        class="item acea-row row-between-wrapper"
        v-if="types.indexOf('alipay') !== -1"
        @click="checked('alipay')"
      >
        <view class="left acea-row row-between-wrapper">
          <view class="iconfont icon-zhifubao"></view>
          <view class="text">
            <view class="name">支付宝支付</view>
            <view class="info">使用线上支付宝支付</view>
          </view>
        </view>
        <view class="iconfont icon-xiangyou"></view>
      </view> -->
      <view
        class="item acea-row row-between-wrapper"
        v-if="types.indexOf('yue') !== -1"
        @click="checked('yue')"
      >
        <view class="left acea-row row-between-wrapper">
          <view class="iconfont icon-yuezhifu"></view>
          <view class="text">
            <view class="name">余额支付</view>
            <view class="info">
              当前可用余额：<text class="money">{{ balance }}</text>
            </view>
          </view>
        </view>
        <view class="iconfont icon-xiangyou"></view>
      </view>
      <!-- <view
        class="item acea-row row-between-wrapper"
        v-if="types.indexOf('offline') !== -1"
        @click="checked('offline')"
      >
        <view class="left acea-row row-between-wrapper">
          <view class="iconfont icon-yuezhifu1"></view>
          <view class="text">
            <view class="name">线下支付</view>
            <view class="info">选择线下付款方式</view>
          </view>
        </view>
        <view class="iconfont icon-xiangyou"></view>
      </view> -->
    </view>
    <view class="mask" v-show="value" @click="close"></view>
  </view>
</template>
<script>
export default {
  name: "Payment",
  props: {
    value: {
      type: Boolean,
      default: false
    },
    balance: {
      type: [Number, String],
      default: 0
    },
    types: {
      type: Array,
      default: () => ["weixin", "alipay", "yue", "offline"]
    }
  },
  data: function() {
    return {};
  },
  mounted: function() {},
  methods: {
    checked: function(type) {
      this.$emit("checked", type);
      this.close();
    },
    close: function() {
      this.$emit("input", false);
    }
  }
};
</script>
<style scoped lang="less" lang="less">
.payment {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  border-radius: 0.16*100rpx 0.16*100rpx 0 0;
  background-color: #fff;
  padding-bottom: 0.6*100rpx;
  z-index: 99;
  transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
  -webkit-transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
  -moz-transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
  -o-transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
  transform: translate3d(0, 100%, 0);
  -webkit-transform: translate3d(0, 100%, 0);
  -ms-transform: translate3d(0, 100%, 0);
  -moz-transform: translate3d(0, 100%, 0);
  -o-transform: translate3d(0, 100%, 0);
}

.payment.on {
  transform: translate3d(0, 0, 0);
  -webkit-transform: translate3d(0, 0, 0);
  -ms-transform: translate3d(0, 0, 0);
  -moz-transform: translate3d(0, 0, 0);
  -o-transform: translate3d(0, 0, 0);
}

.payment .title {
  text-align: center;
  height: 1.23*100rpx;
  font-size: 0.32*100rpx;
  color: #282828;
  font-weight: bold;
  padding-right: 0.3*100rpx;
  margin-left: 0.3*100rpx;
  position: relative;
  border-bottom: 0.01*100rpx solid #eee;
}

.payment .title .iconfont {
  position: absolute;
  right: 0.3*100rpx;
  top: 50%;
  transform: translateY(-50%);
  font-size: 0.43*100rpx;
  color: #8a8a8a;
  font-weight: normal;
}

.payment .item {
  border-bottom: 0.01*100rpx solid #eee;
  height: 1.3*100rpx;
  margin-left: 0.3*100rpx;
  padding-right: 0.3*100rpx;
}

.payment .item .left {
  width: 6.1*100rpx;
}

.payment .item .left .text {
  width: 5.4*100rpx;
}

.payment .item .left .text .name {
  font-size: 0.32*100rpx;
  color: #282828;
}

.payment .item .left .text .info {
  font-size: 0.24*100rpx;
  color: #999;
}

.payment .item .left .text .info .money {
  color: #ff9900;
}

.payment .item .left .iconfont {
  font-size: 0.45*100rpx;
  color: #09bb07;
}

.payment .item .left .iconfont.icon-zhifubao {
  color: #00aaea;
}

.payment .item .left .iconfont.icon-yuezhifu {
  color: #ff9900;
}

.payment .item .left .iconfont.icon-yuezhifu1 {
  color: #eb6623;
}

.payment .item .iconfont {
  font-size: 0.3*100rpx;
  color: #999;
}
</style>
