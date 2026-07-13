<template>
  <view class="register absolute">
    <view class="shading">
      <view class="pictrue acea-row row-center-wrapper">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/logo.png`" />
      </view>
    </view>
    <view class="whiteBg">
      <view class="title">找回密码</view>
      <view class="list">
        <view class="item">
          <view>
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-phone_" />
            </svg>
            <input type="text" placeholder="输入手机号码" v-model="account" />
          </view>
        </view>
        <view class="item">
          <view class="align-left">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-code_1" />
            </svg>
            <input type="text" placeholder="填写验证码" class="codeIput" v-model="captcha" />
            <button
              class="code"
              :disabled="disabled"
              :class="disabled === true ? 'on' : ''"
              @click="code"
            >{{ text }}</button>
          </view>
        </view>
        <view class="item">
          <view>
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-code_" />
            </svg>
            <input type="password" placeholder="填写您的登录密码" v-model="password" />
          </view>
        </view>
      </view>
      <view class="logon" @click="registerReset">确认</view>
      <view class="tip">
        <text @click="goLogin()" class="font-color-red">立即登录</text>
      </view>
    </view>
    <view class="bottom"></view>
  </view>
</template>

<script>
import sendVerifyCode from "@/mixins/SendVerifyCode";
import { registerVerify, registerReset } from "@/api/user";
import { validatorDefaultCatch } from "@/utils/dialog";
import attrs, { required, alpha_num, chs_phone } from "@/utils/validate";

export default {
  name: "RetrievePassword",
  data: function() {
    return {
      account: "",
      password: "",
      captcha: ""
    };
  },
  mixins: [sendVerifyCode],
  methods: {
    goLogin() {
      this.$yrouter.push({ path: "/pages/user/Login/index" });
    },
    async registerReset() {
      var that = this;
      const { account, captcha, password } = that;
      try {
        await that
          .$validator({
            account: [
              required(required.message("手机号码")),
              chs_phone(chs_phone.message())
            ],
            captcha: [
              required(required.message("验证码")),
              alpha_num(alpha_num.message("验证码"))
            ],
            password: [
              required(required.message("密码")),
              attrs.range([6, 16], attrs.range.message("密码")),
              alpha_num(alpha_num.message("密码"))
            ]
          })
          .validate({ account, captcha, password });
      } catch (e) {
        return validatorDefaultCatch(e);
      }
      registerReset({
        account: that.account,
        captcha: that.captcha,
        password: that.password
      })
        .then(res => {
          uni.showToast({
            title: res.msg,
            icon: "success",
            duration: 2000,
            complete: () => {
              that.$yrouter.push({ name: "Login" });
            }
          });
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg|| err.response.data.message,
            icon: "none",
            duration: 2000
          });
        });
    },
    async code() {
      var that = this;
      const { account } = that;
      try {
        await that
          .$validator({
            account: [
              required(required.message("手机号码")),
              chs_phone(chs_phone.message())
            ]
          })
          .validate({ account });
      } catch (e) {
        return validatorDefaultCatch(e);
      }
      registerVerify({ phone: that.account })
        .then(res => {
          uni.showToast({
            title: res.msg,
            icon: "success",
            duration: 2000
          });
          that.sendCode();
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg|| err.response.data.message,
            icon: "none",
            duration: 2000
          });
        });
    }
  }
};
</script>
