<template>
  <view>
    <view class="switchWindow" :class="switchActive === true ? 'on' : ''">
      <view class="pictrue">
        
      </view>
      <!-- 是否选择切换到小程序账户？ -->
      <view class="info">
        <text>是否选择切换到</text>
        <text class="font-color" v-if="login_type === 'h5'">微信账号</text>
        <text class="font-color" v-else>手机用户</text>
        <text>？</text>
      </view>
      <view class="switchBnt" @click="switchH5">
        <text>切换</text>
      </view>
      <view class="switchBnt cancelBnt" @click="switchClose">
        <text>取消</text>
      </view>
    </view>
    <view class="mask" @touchmove.prevent v-show="switchActive === true" @click="switchClose"></view>
  </view>
</template>
<style lang="less">
.switchWindow {
  width: 5.6*100rpx;
  border-radius: 0.2*100rpx;
  -webkit-border-radius: 0.2*100rpx;
  background-color: #fff;
  position: fixed;
  top: 50%;
  left: 50%;
  margin-left: -2.8*100rpx;
  margin-top: -3*100rpx;
  z-index: 99;
  padding: 0.5*100rpx 0.3*100rpx 0.4*100rpx 0.3*100rpx;
  text-align: center;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  transition: all 0.3s ease-in-out 0s;
  -webkit-transition: all 0.3s ease-in-out 0s;
  -moz-transition: all 0.3s ease-in-out 0s;
  -o-transition: all 0.3s ease-in-out 0s;
  opacity: 0;
  transform: scale(0);
}

.switchWindow.on {
  opacity: 1;
  transform: scale(1);
  -webkit-transform: scale(1);
  -ms-transform: scale(1);
  -moz-transform: scale(1);
  -o-transform: scale(1);
}

.switchWindow .pictrue {
  width: 2.36*100rpx;
  height: 2.36*100rpx;
  margin: 0 auto;
}

.switchWindow .pictrue image {
  width: 100%;
  height: 100%;
  display: block;
}

.switchWindow .info {
  font-size: 0.32*100rpx;
  color: #282828;
  margin-top: 0.44*100rpx;
  font-weight: bold;
}

.switchWindow .switchBnt {
  font-size: 0.32*100rpx;
  color: #fff;
  width: 3.6*100rpx;
  height: 0.82*100rpx;
  border-radius: 0.41*100rpx;
  -webkit-border-radius: 0.41*100rpx;
  margin: 0.57*100rpx auto 0 auto;
  line-height: 0.82*100rpx;
  background-image: linear-gradient(to right, #f67a38 0%, #f11b09 100%);
  background-image: -webkit-linear-gradient(to right, #f67a38 0%, #f11b09 100%);
  background-image: -moz-linear-gradient(to right, #f67a38 0%, #f11b09 100%);
}

.switchWindow .switchBnt.cancelBnt {
  background-color: #fff;
  color: #999;
  background-image: none;
  margin-top: 0.1*100rpx;
}
</style>
<script>
import { switchH5Login } from "@/api/user";
import cookie from "@/utils/store/cookie";
import store from "@//store";
import dayjs from "dayjs";

export default {
  name: "SwitchWindow",
  props: {
    switchActive: {
      type: Boolean,
      default: false
    },
    login_type: {
      type: String,
      default: ""
    }
  },
  data: function() {
    return {};
  },
  mounted: function() {},
  methods: {
    switchClose: function() {
      this.$emit("changeswitch", false); //$emit():注册事件；
    },
    switchH5() {
      let that = this;
      uni.showLoading({
        title: "正在切换中"
      });
      if (that.login_type === "h5") {
        cookie.set("loginType", "wechat", 60);
        uni.hideLoading();
        this.$store.commit("logout");
        this.$emit("changeswitch", false);
        location.reload();
      } else {
        switchH5Login()
          .then(({ data }) => {
            uni.hideLoading();
            const expires_time = dayjs(data.expires_time);
            store.commit("login", data.token, expires_time);
            this.$emit("changeswitch", false);
            location.reload();
          })
          .catch(err => {
            uni.hideLoading();
            uni.showToast({
              title: err.msg || err.response.data.msg|| err.response.data.message,
              icon: "none",
              duration: 2000
            });
          });
      }
    }
  }
};
</script>
