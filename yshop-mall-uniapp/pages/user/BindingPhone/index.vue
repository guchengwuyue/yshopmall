<template>
  <view class="ChangePassword">
    <view class="list">
      <view class="item">
        <input type="number" placeholder="填写手机号码" v-model="phone" />
      </view>
      <view class="item acea-row row-between-wrapper">
        <input type="text" placeholder="填写验证码" class="codeIput" v-model="captcha" />
        <button class="code font-color-red" :disabled="disabled" :class="disabled === true ? 'on' : ''" @click="code">{{ text }}</button>
      </view>
    </view>
    <view class="confirmBnt bg-color-red" @click="confirm">确认绑定</view>
  </view>
</template>
<script>
import { mapGetters } from 'vuex'
import sendVerifyCode from '@/mixins/SendVerifyCode'
import { required, alpha_num, chs_phone } from '@/utils/validate'
import { validatorDefaultCatch } from '@/utils/dialog'
import { registerVerify, wxappBindingPhone } from '@/api/user'

export default {
  name: 'BindingPhone',
  components: {},
  props: {},
  data: function () {
    return {
      captcha: '',
      phone: '', //手机号
    }
  },
  mixins: [sendVerifyCode],
  computed: mapGetters(['userInfo']),
  mounted: function () {},
  methods: {
    async confirm() {
      let that = this
      const { phone, captcha } = that
      try {
        await that
          .$validator({
            phone: [chs_phone(chs_phone.message('手机号码')), alpha_num(alpha_num.message())],
            captcha: [required(required.message('验证码')), alpha_num(alpha_num.message('验证码'))],
          })
          .validate({ phone, captcha })
      } catch (e) {
        return validatorDefaultCatch(e)
      }
      wxappBindingPhone({
        phone: this.phone,
        captcha: this.captcha,
      })
        .then(res => {
          if (res.data !== undefined && res.data.is_bind) {
            uni.showModal({
              title: '提示',
              content: '确认绑定?',
              success: function (res) {
                if (res.confirm) {
                  wxappBindingPhone({
                    phone: this.phone,
                    captcha: this.captcha,
                    step: 1,
                  })
                    .then(res => {
                      uni.showToast({
                        title: res.msg,
                        icon: 'none',
                        duration: 2000,
                      })
                      that.$yrouter.replace({
                        path: '/pages/user/PersonalData/index',
                      })
                    })
                    .catch(res => {
                      uni.showToast({
                        title: res.msg,
                        icon: 'none',
                        duration: 2000,
                      })
                      that.$yrouter.replace({
                        path: '/pages/user/PersonalData/index',
                      })
                    })
                } else if (res.cancel) {
                  uni.showToast({
                    title: '已取消绑定',
                    icon: 'none',
                    duration: 2000,
                  })
                  that.$yrouter.replace({
                    path: '/pages/user/PersonalData/index',
                  })
                }
              },
            })
          } else {
            uni.showToast({
              title: res.msg,
              icon: 'none',
              duration: 2000,
            })
            that.$yrouter.replace({ path: '/pages/user/PersonalData/index' })
          }
        })
        .catch(res => {
          uni.showToast({
            title: res.msg,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    async code() {
      let that = this
      const { phone } = that
      try {
        await that
          .$validator({
            phone: [required(required.message('手机号码')), chs_phone(chs_phone.message())],
          })
          .validate({ phone })
      } catch (e) {
        return validatorDefaultCatch(e)
      }

      registerVerify({ phone: phone })
        .then(res => {
          uni.showToast({
            title: res.msg,
            icon: 'none',
            duration: 2000,
          })
          that.sendCode()
        })
        .catch(res => {
          uni.showToast({
            title: res.msg,
            icon: 'none',
            duration: 2000,
          })
        })
    },
  },
}
</script>

<style lang=""></style>
