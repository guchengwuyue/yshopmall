<template>
  <view class="register absolute">
    <view class="shading">
      <view class="pictrue acea-row row-center-wrapper">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/logo.png`" />
      </view>
    </view>
    <view class="whiteBg">
      <view class="title">注册账号</view>
      <view class="list">
        <view class="item">
          <view>
            <!-- <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-phone_" />
            </svg> -->
            <input name="phone" type="text" placeholder="输入手机号码" />
          </view>
        </view>
        <view class="item">
          <view class="align-left">
            <!-- <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-code_1" />
            </svg> -->
            <input name="" type="text" placeholder="填写验证码" class="codeIput" />
            <button class="verifyCode" :disabled="disabled" :class="disabled === true ? 'on' : ''"
              @click="code">{{ text }}</button>
          </view>
        </view>
        <view class="item">
          <view>
            <!-- <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-code_" />
            </svg> -->
            <input type="text" placeholder="填写您的登录密码" />
          </view>
        </view>
      </view>
      <view class="logon">注册</view>
      <view class="tip">
        已有账号?
        <text @click="goLogin()" class="font-color-red">立即登录</text>
      </view>
    </view>
    <view class="bottom"></view>
  </view>
</template>

<script>
  import sendVerifyCode from "@/mixins/SendVerifyCode";

  export default {
    name: "Register",
    mixins: [sendVerifyCode],
    methods: {
      code: function () {
        this.sendCode();
      },
      goLogin() {
        this.$yrouter.push({
          path: "/pages/user/Login/index"
        });
      }
    }
  };
</script>
