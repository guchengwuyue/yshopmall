<template>
  <view class="user">
    <view v-if="$store.getters.token || userInfo.uid">
      <view
        class="getUserBaseData header bg-color-red acea-row row-between-wrapper"
        v-if="!userInfo.uid"
      >
        <button
          class="userDataBtn"
          v-if="canIUseGetUserProfile"
          @tap="getUserInfoProfile"
        >授权并查看用户信息</button>
        <button
          class="userDataBtn"
          v-else
          @getuserinfo="getUserInfo"
          open-type="getUserInfo"
        >授权并查看用户信息</button>
      </view>
      <view
        class="header bg-color-red acea-row row-between-wrapper"
        v-else
      >
        <view class="picTxt acea-row row-between-wrapper">
          <view class="pictrue">
            <image :src="userInfo.avatar" />
          </view>
          <view class="text">
            <view class="acea-row row-middle">
              <view class="name line1">{{ userInfo.nickname }}</view>
              <view
                class="member acea-row row-middle"
                v-if="userInfo.vip"
              >
                <image :src="userInfo.vipIcon" />
                <text>{{ userInfo.vipName }}</text>
              </view>
            </view>
            <view
              @click="goPersonalData()"
              class="id"
              v-if="userInfo.phone"
            >
              <text>ID：{{ userInfo.uid || 0 }}</text>
              <text class="iconfont icon-bianji1"></text>
            </view>
            <!-- #ifdef MP-WEIXIN -->
            <!-- <button open-type="getPhoneNumber" @getphonenumber="getPhoneNumber" class="binding" v-else>
              <text>绑定手机号</text>
            </button> -->
            <!-- #endif -->

            <!-- #ifndef MP-WEIXIN -->
            <!-- <button class="binding" @click="goBindPhone()" v-else>
              <text>绑定手机号</text>
            </button> -->
            <!-- #endif -->
          </view>
        </view>
        <text
          class="iconfont icon-shezhi"
          @click="goPersonalData()"
        ></text>
      </view>
      <view class="wrapper">
        <view class="nav acea-row row-middle">
          <view
            @click="goUserAccount()"
            class="item"
          >
            <text>我的余额</text>
            <text class="num">{{ userInfo.nowMoney || 0 }}</text>
          </view>
          <view
            @click="goUserPromotion()"
            class="item"
            v-if="userInfo.isPromoter === 1 || userInfo.statu === 2"
          >
            <text>当前佣金</text>
            <text class="num">{{ userInfo.brokeragePrice || 0 }}</text>
          </view>
          <view
            @click="goIntegral()"
            class="item"
            v-else
          >
            <text>当前积分</text>
            <text class="num">{{ userInfo.integral || 0 }}</text>
          </view>
          <view
            @click="goUserCoupon()"
            class="item"
          >
            <text>优惠券</text>
            <text class="num">{{ userInfo.couponCount || 0 }}</text>
          </view>
        </view>
        <view class="myOrder">
          <view class="title acea-row row-between-wrapper">
            <text>我的订单</text>
            <text
              @click="goMyOrder()"
              class="allOrder"
            >
              <text>全部订单</text>
              <text class="iconfont icon-jiantou"></text>
            </text>
          </view>
          <view
            class="orderState acea-row row-middle"
            v-if="userInfo.orderStatusNum !== undefined || userInfo.orderStatusNum !== null"
          >
            <view
              @click="goMyOrder(0)"
              class="item"
            >
              <view class="pictrue">
                <image :src="`${$VUE_APP_RESOURCES_URL}/images/dfk.png`" />
                <text
                  class="order-status-num"
                  v-if="userInfo.orderStatusNum.unpaidCount > 0"
                >{{ userInfo.orderStatusNum.unpaidCount }}</text>
              </view>
              <view>待付款</view>
            </view>
            <view
              @click="goMyOrder(1)"
              class="item"
            >
              <view class="pictrue">
                <image :src="`${$VUE_APP_RESOURCES_URL}/images/dfh.png`" />
                <text
                  class="order-status-num"
                  v-if="userInfo.orderStatusNum.unshippedCount > 0"
                >{{ userInfo.orderStatusNum.unshippedCount }}</text>
              </view>
              <view>待发货</view>
            </view>
            <view
              @click="goMyOrder(2)"
              class="item"
            >
              <view class="pictrue">
                <image :src="`${$VUE_APP_RESOURCES_URL}/images/dsh.png`" />
                <text
                  class="order-status-num"
                  v-if="userInfo.orderStatusNum.receivedCount > 0"
                >{{ userInfo.orderStatusNum.receivedCount }}</text>
              </view>
              <text>待收货</text>
            </view>
            <view
              @click="goMyOrder(3)"
              class="item"
            >
              <view class="pictrue">
                <image :src="`${$VUE_APP_RESOURCES_URL}/images/dpj.png`" />
                <text
                  class="order-status-num"
                  v-if="userInfo.orderStatusNum.evaluatedCount > 0"
                >{{ userInfo.orderStatusNum.evaluatedCount }}</text>
              </view>
              <text>待评价</text>
            </view>
            <view
              @click="goReturnList()"
              class="item"
            >
              <view class="pictrue">
                <image :src="`${$VUE_APP_RESOURCES_URL}/images/sh.png`" />
                <text
                  class="order-status-num"
                  v-if="userInfo.orderStatusNum.refundCount > 0"
                >{{ userInfo.orderStatusNum.refundCount }}</text>
              </view>
              <text>售后/退款</text>
            </view>
          </view>
        </view>
        <view class="myService">
          <view class="serviceList acea-row row-middle">
            <template v-for="(item, MyMenusIndex) in MyMenus">
              <view
                class="item"
                :key="MyMenusIndex"
                @click="goPages(MyMenusIndex)"
              >
                <view class="pictrue">
                  <image :src="item.pic" />
                </view>
                <view class="cell">{{ item.name }}</view>
                <text class="iconfont icon-jiantou"></text>
              </view>
            </template>
          </view>
        </view>
      </view>
      <view class="by">
        <view>
          <text class="by-text">www.yixiang.co提供技术支持</text>
        </view>
      </view>
      <!-- <SwitchWindow
        v-on:changeswitch="changeswitch"
        :switchActive="switchActive"
        :login_type="userInfo.login_type"
      ></SwitchWindow>-->
    </view>
    <Authorization
      v-else
      ref="authorization"
    />
  </view>
</template>
<script>
import { mapState, mapGetters, mapMutations, mapActions } from 'vuex'
import { getUserInfo, getMenuUser, wxappAuth, bindingPhone, wxappBindingPhone, wxappGetUserInfo } from '@/api/user'
import { isWeixin, VUE_APP_RESOURCES_URL, parseQuery, getProvider } from '@/utils'
import SwitchWindow from '@/components/SwitchWindow'
import Authorization from '@/pages/authorization/index'
import cookie from '@/utils/store/cookie'

const NAME = 'User'

export default {
  name: NAME,
  components: {
    SwitchWindow,
    Authorization,
  },
  props: {},
  data: function () {
    return {
      canIUseGetUserProfile: false,
      MyMenus: [],
      switchActive: false,
      isWeixin: false,
    }
  },
  computed: mapGetters(['userInfo']),
  onLoad() {
    if (wx.getUserProfile) {
      this.canIUseGetUserProfile = true
    }
  },
  methods: {
    ...mapMutations(['updateAuthorizationPage']),
    toLogin() {
      this.$yrouter.push('/pages/user/Login/index')
    },
    goReturnList() {
      this.$yrouter.push('/pages/order/ReturnList/index')
    },
    goMyOrder(type) {
      this.$yrouter.push({
        path: '/pages/order/MyOrder/index',
        query: {
          type,
        },
      })
    },
    goBindPhone() {
      this.$yrouter.push('/pages/user/BindingPhone/index')
    },
    goUserCoupon() {
      this.$yrouter.push('/pages/user/coupon/UserCoupon/index')
    },
    goIntegral() {
      this.$yrouter.push('/pages/user/signIn/Integral/index')
    },
    goUserPromotion() {
      this.$yrouter.push('/pages/user/promotion/UserPromotion/index')
    },
    goUserAccount() {
      this.$yrouter.push('/pages/user/UserAccount/index')
    },
    goPersonalData() {
      this.$yrouter.push('/pages/user/PersonalData/index')
    },
    getPhoneNumber(e) {
      // 判断一下这里是不是小程序 如果是小程序，走获取微信手机号进行绑定
      if (e.mp.detail.errMsg == 'getPhoneNumber:ok') {
        uni.showLoading({
          title: '绑定中',
        })
        wxappBindingPhone({
          encryptedData: e.mp.detail.encryptedData,
          iv: e.mp.detail.iv,
        })
          .then(res => {
            // this.User();
            this.$store.dispatch('userInfo', true)
            uni.hideLoading()
            uni.showToast({
              title: res.msg,
              icon: 'success',
              duration: 2000,
            })
          })
          .catch(error => {
            uni.hideLoading()
            this.$store.dispatch('userInfo', true)
            console.log(error)
            uni.showToast({
              title: error.msg || error.response.data.msg || error.response.data.message,
              icon: 'none',
              duration: 2000,
            })
          })
        // // 获取当前环境的服务商
        // uni.getProvider({
        //   service: "oauth",
        //   success: function (res) {
        //     // 此处可以排除h5
        //     if (res.provider) {
        //       uni.login({
        //         success: loginRes => {
        //           bindingPhone({
        //               code: loginRes.code,
        //               encryptedData: e.mp.detail.encryptedData,
        //               iv: e.mp.detail.iv
        //             })
        //             .then(res => {
        //               // this.User();
        //               this.$store.dispatch("userInfo", true);
        //               uni.hideLoading();
        //               uni.showToast({
        //                 title: res.msg,
        //                 icon: "success",
        //                 duration: 2000
        //               });
        //             })
        //             .catch(error => {
        //               uni.hideLoading();
        //               this.$store.dispatch("userInfo", true);
        //               console.log(error);
        //               uni.showToast({
        //                 title: error.msg ||
        //                   error.response.data.msg ||
        //                   error.response.data.message,
        //                 icon: "none",
        //                 duration: 2000
        //               });
        //             });
        //         },
        //         fail() {
        //           reject("绑定失败");
        //         }
        //       });
        //     }
        //   },
        //   fail() {
        //     reject("获取环境服务商失败");
        //   }
        // });
      } else {
        uni.showToast({
          title: '已拒绝授权',
          icon: 'none',
          duration: 2000,
        })
      }
    },
    // 获取用户授权，读取头像、昵称
    getUserInfo(data) {
      if (data.detail.errMsg == 'getUserInfo:fail auth deny') {
        uni.showToast({
          title: '取消授权',
          icon: 'none',
          duration: 2000,
        })
        return
      }
    },
    // 申请获取用户信息
    getUserInfoProfile(data) {
      wx.getUserProfile({
        lang: 'zh_CN',
        desc: '需要获取您的信息用来展示',
        success: res => {
          uni.showLoading({
            title: '正在更新信息...',
            duration: 2000,
          })
          getProvider().then(provider => {
            // 环境提供商
            if (!provider) {
              reject()
            }
            // 获取开发code
            uni.login({
              provider: provider,
              success: async loginRes => {
                wxappGetUserInfo({
                  encryptedData: res.encryptedData,
                  iv: res.iv,
                  code: loginRes.code, // 开发code
                }).then(res => {
                  if (res.status === 200) {
                    this.userInfo.avatar = res.data.avatar
                    this.userInfo.nickname = res.data.nickname
                  } else {
                    uni.showLoading({
                      title: res.msg,
                      duration: 2000,
                    })
                  }
                })
              },
            })
          })
        },
      })
    },
    changeswitch(data) {
      this.switchActive = data
    },
    // 获取用户信息
    MenuUser() {
      getMenuUser()
        .then(res => {
          uni.hideLoading()
          this.MyMenus = res.data.routine_my_menus
        })
        .catch(error => {
          uni.hideLoading()
          console.log(error)
        })
    },
    goPages(index) {
      let url = this.MyMenus[index].uniapp_url
      if (url === '/pages/user/promotion/UserPromotion/index' && this.userInfo.statu === 1) {
        if (!this.userInfo.isPromoter) {
          uni.showToast({
            title: '您还没有推广权限！！',
            icon: 'none',
            duration: 2000,
          })
          return
        }
      }

      if (url === '/pages/orderAdmin/OrderIndex/index' && !this.userInfo.adminid) {
        uni.showToast({
          title: '您还不是管理员！！',
          icon: 'none',
          duration: 2000,
        })
        return
      }
      console.log(this.userInfo)
      if (url === '/pages/orderAdmin/OrderCancellation/index' && !this.userInfo.checkStatus) {
        uni.showToast({
          title: '您没有核销权限,请后台店员设置！！',
          icon: 'none',
          duration: 2000,
        })
        return
      }

      this.$yrouter.push({
        path: this.MyMenus[index].uniapp_url,
      })
    },
    goPages2() {
      this.$yrouter.push({
        path: '/pages/shop/GoodsList/index',
        query: {
          // id: 0,
          title: '积分商城',
          isIntegral: true,
        },
      })
    },
  },
  watch: {
    userInfo() {
      this.MenuUser()
    },
  },
  onShow() {
    if (!this.$store.getters.token && !this.userInfo.uid) {
      this.$refs.authorization.getLoginCode()
    }
    if (this.$store.getters.token) {
		console.log('userInfo11:',this.$store.getters.token)
      //
      uni.showLoading({
        title: '加载中',
      })
      this.$store.dispatch('getUser', true)
      this.MenuUser()
      this.isWeixin = isWeixin()
    }
  },
  onHide() {
    console.log('离开用户中心')
    this.updateAuthorizationPage(false)
  },
}
</script>

<style lang="less">
.getUserBaseData {
  .userDataBtn {
    width: 80%;
    height: 80rpx;
    background: linear-gradient(to right, #f35447 0%, #ff8e3c 100%);
    background: -moz-linear-gradient(to right, #f35447 0%, #ff8e3c 100%);
    border-radius: 40rpx;
    font-size: 30rpx;
    font-family: PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
  }
}

.footer-line-height {
  height: 1 * 100rpx;
}

.order-status-num {
  min-width: 0.33 * 100rpx;
  background-color: #fff;
  color: #eb3729;
  border-radius: 15px;
  position: absolute;
  right: -0.14 * 100rpx;
  top: -0.15 * 100rpx;
  font-size: 0.2 * 100rpx;
  padding: 0 0.08 * 100rpx;
  border: 1px solid #eb3729;
}

.pictrue {
  position: relative;
}

.switch-h5 {
  margin-left: 0.2 * 100rpx;
}

.binding {
  margin-top: 0.1 * 100rpx;
  display: inline-block;
  padding: 0.05 * 100rpx 0.2 * 100rpx;
  background-color: #ca1f10;
  border-radius: 50px;
  font-size: 0.22 * 100rpx;
  line-height: 1.5;
  border: 1px solid #e8695e;
  color: #ffffff;
}

.by {
  text-align: center;
  padding: 30rpx 0;
}

.by-text {
  text-align: center;
  font-size: 24rpx;
}
</style>
