<template>
  <view class="register absolute">
    <view class="whiteBg">
      <view class="title acea-row">
        <view class="item" :class="current === index ? 'on' : ''" v-for="(item, index) in navList" @click="navTap(index)" :key="index">{{ item }}</view>
      </view>
      <!-- 手机号登录 -->
      <view class="list">
        <form @submit.prevent="submit">
          <view class="item getPhoneCode">
            <view class="phoneInput acea-row row-between-wrapper">
              <input type="text" placeholder="输入手机号码" v-model="account" required />
            </view>
            <view class="phoneCode" @click="sendCode">
              {{ clockText }}
            </view>
          </view>
          <view class="item">
            <view class="acea-row row-between-wrapper">
              <input type="text" placeholder="请输入验证码" v-model="captcha" required />
              <!-- <input type="password" placeholder="填写登录密码" v-model="password" required /> -->
            </view>
          </view>
        </form>
      </view>

      <view class="logon" @click="loginMobile">手机号登录</view>
      <view class="tip">
        未注册的手机号验证通过后会自动注册
      </view>
    </view>
	<view class="hint">
		<radio  :checked="isChecked" @tap.stop="onChange" />
		我已经阅读并遵守
		<text class="link" @tap="goNewsDetail(50,'用户协议')">《用户协议》</text>与
			<text class="link"  @tap="goNewsDetail(49,'隐私政策')">《隐私政策》</text>
			<!--上面协议是固定的 请添加相应id文章-->
	</view>
  </view>
</template>
<script>
import sendVerifyCode from '@/mixins/SendVerifyCode'
import { login, loginMobile, registerVerify, register } from '@/api/user'
import attrs, { required, alpha_num, chs_phone } from '@/utils/validate'
import { validatorDefaultCatch } from '@/utils/dialog'
import dayjs from 'dayjs'
import cookie from '@/utils/store/cookie'

import { handleGetUserInfo } from '@/utils'

const BACK_URL = 'login_back_url'

export default {
  name: 'Login',
  mixins: [sendVerifyCode],
  data: function() {
    return {
      navList: ['手机号登录'],
      // navList: ["手机号登录",],
      current: 0,
      account: '',
      password: '',
      captcha: '',
      inviteCode: '',
      formItem: 1,
      type: 'login',
      timer: null,
      clock: 60,
      clockText: '验证码',
	  isChecked: false
    }
  },
  methods: {
    goNewsDetail(id,name) {
      this.$yrouter.push({
        path: "/pages/shop/news/NewsDetail/index",
        query: { id: id,name:name }
      });
    },
	onChange() {
		console.log('isChecked;',this.isChecked)
		this.isChecked = !this.isChecked
	  },
    async loginMobile() {
	  if(!this.isChecked) {
	  		  uni.showToast({
	  		    title: '请勾选下面协议',
	  		    icon: 'none',
	  		    duration: 2000,
	  		  })
	  		  return
	  }
      var that = this
      const { account, captcha } = that
      try {
        await that
          .$validator({
            account: [required(required.message('手机号码')), chs_phone(chs_phone.message())],
            captcha: [required(required.message('验证码')), alpha_num(alpha_num.message('验证码'))],
          })
          .validate({
            account,
            captcha,
          })
      } catch (e) {
        return validatorDefaultCatch(e)
      }
      loginMobile({
        account: that.account,
        captcha: that.captcha,
        spread: cookie.get('spread'),
      })
        .then(res => {
          this.$store.commit('login', res.data.token, dayjs(res.data.expires_time))
          handleGetUserInfo()
        })
        .catch(err => {
          uni.showToast({
            title: res.msg || res.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
      // .then(res => {
      // 	that.$store.commit("login", res.data.token, dayjs(res.data.expires_time));
      // 	handleGetUserInfo();
      // })
      // .catch(err => {
      // 	console.log(err)
      // 	uni.showToast({
      // 	  title: err.msg || err.response.data.msg || err.response.data.message,
      // 	  icon: "none",
      // 	  duration: 2000
      // 	});
      // });
    },
    async register() {
      var that = this
      const { account, captcha, password } = that
      try {
        await that
          .$validator({
            account: [required(required.message('手机号码')), chs_phone(chs_phone.message())],
            captcha: [required(required.message('验证码')), alpha_num(alpha_num.message('验证码'))],
            password: [required(required.message('密码')), attrs.range([6, 16], attrs.range.message('密码')), alpha_num(alpha_num.message('密码'))],
          })
          .validate({
            account,
            captcha,
            password,
          })
      } catch (e) {
        return validatorDefaultCatch(e)
      }
      register({
        account: that.account,
        captcha: that.captcha,
        password: that.password,
        inviteCode: that.inviteCode,
        spread: cookie.get('spread'),
      })
        .then(res => {
          uni.showToast({
            title: res.msg,
            icon: 'success',
            duration: 2000,
          })
          that.formItem = 1
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    // 获取验证码
    async code() {
      const { account } = this
      try {
        await this.$validator({
          account: [required(required.message('手机号码')), chs_phone(chs_phone.message())],
        }).validate({
          account,
        })
      } catch (e) {
        return validatorDefaultCatch(e)
      }
      if (this.formItem == 2) this.type = 'register'
      this.showCode()
    },
    // 手机号登录获取验证码
    async sendCode() {
      const { account } = this
      try {
        await this.$validator({
          account: [required(required.message('手机号码')), chs_phone(chs_phone.message())],
        }).validate({
          account,
        })
      } catch (e) {
        return validatorDefaultCatch(e)
      }
      // 倒计时
      if (this.clock !== 60) {
        return
      }
      this.clock--
      this.clockText = `${this.clock} 秒`
      this.timer = setInterval(() => {
        if (parseInt(this.clock) === 1) {
          clearTimeout(this.timer)
          this.clock = 60
          this.clockText = '获取验证码'
          return
        }
        this.clock--
        if (this.clock < 10) this.clock = `0${this.clock}`
        this.clockText = `${this.clock} 秒`
      }, 1000)
      this.showCode()
    },
    // 验证码请求
    showCode() {
      return registerVerify({
        phone: this.account,
        type: this.type,
      })
        .then(res => {
          uni.showToast({
            title: res.msg,
            icon: 'success',
            duration: 2000,
          })
          this.sendCode()
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    navTap(index) {
      this.current = index
    },
    async submit() {
      const { account, password } = this
      try {
        await this.$validator({
          account: [required(required.message('账号')), attrs.range([5, 16], attrs.range.message('账号')), alpha_num(alpha_num.message('账号'))],
          password: [required(required.message('密码')), attrs.range([6, 16], attrs.range.message('密码')), alpha_num(alpha_num.message('密码'))],
        }).validate({
          account,
          password,
        })
      } catch (e) {
        return validatorDefaultCatch(e)
      }

      login({
        username: account,
        password,
        spread: cookie.get('spread'),
      })
        .then(res => {
          this.$store.commit('login', res.data.token, dayjs(res.data.expires_time))
          handleGetUserInfo()
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
  },
}
</script>

<style lang="scss">
.hint {
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 20rpx 40rpx;
	font-size: 25rpx;
	color: #000;

	.link {
		color: #ff8e3c;
	}
}
.register .whiteBg .title .item {
  border-bottom: 2px solid;
}
.register .whiteBg .title .item.on {
  border-bottom-color: #f35749;
}
.getPhoneCode {
  display: flex;
  justify-content: space-between;
  .phoneInput {
    width: 70%;
  }
  .phoneCode {
    width: 30%;
    text-align: center;
    color: #ffffff;
    background-color: #f35749;
    padding: 12rpx;
    font-size: 28rpx;
    border-radius: 30rpx;
  }
}
</style>
