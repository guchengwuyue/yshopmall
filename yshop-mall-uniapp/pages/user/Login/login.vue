<template>
  <view class="register absolute">
    <view class="whiteBg" v-if="formItem === 1">
      <view class="title acea-row">
        <view class="item" :class="current === index ? 'on' : ''" v-for="(item, index) in navList"
          @click="navTap(index)" :key="index">{{ item }}</view>
      </view>
      <view class="list" :hidden="current !== 0">
        <form @submit.prevent="submit">
          <view class="item">
            <view class="acea-row row-between-wrapper">
              <!-- <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-phone_" />
              </svg>-->
              <input type="text" placeholder="输入手机号码" v-model="account" required />
            </view>
          </view>
          <view class="item">
            <view class="acea-row row-between-wrapper">
              <!-- <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-code_" />
              </svg>-->
              <input type="password" placeholder="填写登录密码" v-model="password" required />
            </view>
          </view>
        </form>
      </view>
      <view class="list" :hidden="current !== 1">
        <view class="item">
          <view class="acea-row row-between-wrapper">
            <!-- <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-phone_" />
            </svg>-->
            <input type="text" placeholder="输入手机号码" v-model="account" />
          </view>
        </view>
        <view class="item">
          <view class="align-left">
            <!-- <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-code_1" />
            </svg>-->
            <input type="text" placeholder="填写验证码" class="codeIput" v-model="captcha" />
            <button class="code" :disabled="disabled" :class="disabled === true ? 'on' : ''"
              @click="code">{{ text }}</button>
          </view>
        </view>
      </view>
      <view class="logon" @click="loginMobile" :hidden="current !== 1">登录</view>
      <view class="logon" @click="submit" :hidden="current === 1">登录</view>
      <view class="tip">
        没有账号?
        <text @click="formItem = 2" class="font-color-red">立即注册</text>
      </view>
    </view>
	<!-- 注册 -->
    <view class="whiteBg" v-else>
      <view class="title acea-row row-between-wrapper">
        <view class="item on">注册账号</view>
      </view>
      <view class="list">
        <view class="item">
          <view>
            <!-- <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-phone_" />
            </svg>-->
            <input name="account" type="text" placeholder="输入手机号码" v-model="account" />
          </view>
        </view>
        <view class="item">
          <view class="align-left">
            <!-- <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-code_1" />
            </svg>-->
            <input name="verifyCode" type="text" placeholder="填写验证码" class="codeIput" v-model="captcha" />
            <button class="code" :disabled="disabled" :class="disabled === true ? 'on' : ''"
              @click="code">{{ text }}</button>
          </view>
        </view>
        <view class="item">
          <view>
            <!-- <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-code_" />
            </svg>-->
            <input name="password" type="password" placeholder="填写您的登录密码" v-model="password" />
          </view>
        </view>
        <!-- #ifndef H5 -->
        <view class="item">
          <view>
            <!-- <svg class="icon" aria-hidden="true">
				  <use xlink:href="#icon-phone_" />
            </svg>-->
            <input name="inviteCode" type="text" placeholder="输入邀请码" v-model="inviteCode" />
          </view>
        </view>
        <!-- #endif -->

      </view>
      <view class="logon" @click="register">注册</view>
      <view class="tip">
        已有账号?
        <text @click="formItem = 1" class="font-color-red">立即登录</text>
      </view>
    </view>
  </view>
</template>
<script>
  import sendVerifyCode from "@/mixins/SendVerifyCode";
  import {
    login,
    loginMobile,
    registerVerify,
    register
  } from "@/api/user";
  import attrs, {
    required,
    alpha_num,
    chs_phone
  } from "@/utils/validate";
  import {
    validatorDefaultCatch
  } from "@/utils/dialog";
  import dayjs from "dayjs";
  import cookie from "@/utils/store/cookie";

  import {
    handleGetUserInfo
  } from "@/utils";

  const BACK_URL = "login_back_url";

  export default {
    name: "Login",
    mixins: [sendVerifyCode],
    data: function () {
      return {
        navList: ["账号登录", "修改密码"],
        current: 0,
        account: "",
        password: "",
        captcha: "",
        inviteCode: "",
        formItem: 1,
        type: "login"
      };
    },
    methods: {
		async loginMobile() {
			var that = this;
			const {
			  account,
			  captcha
			} = that;
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
				  ]
				})
				.validate({
				  account,
				  captcha
				});
			} catch (e) {
			  return validatorDefaultCatch(e);
			}
			loginMobile({
				phone: that.account,
				captcha: that.captcha,
				spread: cookie.get("spread")
			  })
			  .then(res => {
				var data = res.data;
				that.$store.commit("login", data.token, dayjs(data.expires_time));
				handleGetUserInfo();
			  })
			  .catch(err => {
				uni.showToast({
				  title: err.msg || err.response.data.msg || err.response.data.message,
				  icon: "none",
				  duration: 2000
				});
			  });
		},
		async register() {
			var that = this;
			const {
			  account,
			  captcha,
			  password
			} = that;
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
				.validate({
				  account,
				  captcha,
				  password
				});
			} catch (e) {
			  return validatorDefaultCatch(e);
			}
			register({
				account: that.account,
				captcha: that.captcha,
				password: that.password,
				inviteCode: that.inviteCode,
				spread: cookie.get("spread")
			  })
			  .then(res => {
				uni.showToast({
				  title: res.msg,
				  icon: "success",
				  duration: 2000
				});
				that.formItem = 1;
			  })
			  .catch(err => {
				uni.showToast({
				  title: err.msg || err.response.data.msg || err.response.data.message,
				  icon: "none",
				  duration: 2000
				});
			  });
		},
		async code() {
			var that = this;
			const {
			  account
			} = that;
			try {
			  await that
				.$validator({
				  account: [
					required(required.message("手机号码")),
					chs_phone(chs_phone.message())
				  ]
				})
				.validate({
				  account
				});
			} catch (e) {
			  return validatorDefaultCatch(e);
			}
			if (that.formItem == 2) that.type = "register";
			await registerVerify({
				phone: that.account,
				type: that.type
			  })
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
				  title: err.msg || err.response.data.msg || err.response.data.message,
				  icon: "none",
				  duration: 2000
				});
			  });
		},
		navTap: function (index) {
			this.current = index;
		},
		async submit() {
			const {
			  account,
			  password
			} = this;
			try {
			  await this.$validator({
				account: [
				  required(required.message("账号")),
				  attrs.range([5, 16], attrs.range.message("账号")),
				  alpha_num(alpha_num.message("账号"))
				],
				password: [
				  required(required.message("密码")),
				  attrs.range([6, 16], attrs.range.message("密码")),
				  alpha_num(alpha_num.message("密码"))
				]
			  }).validate({
				account,
				password
			  });
			} catch (e) {
			  return validatorDefaultCatch(e);
			}
			login({
				username: account,
				password,
				spread: cookie.get("spread")
			})
			.then(({ data }) => {
				this.$store.commit("login", data.token, dayjs(data.expires_time));
				handleGetUserInfo();
				// let replace=this.$yroute.query.replace
				// if(replace){

				// }
				// this.$yrouter.replace({
				// 	path: this.$yroute.query.replace || '/pages/home/index'
				// });
			})
			.catch(err => {
				console.log(err);
				uni.showToast({
				  title: err.msg || err.response.data.msg || err.response.data.message,
				  icon: "none",
				  duration: 2000
				});
			});
		}
    }
  };
</script>
