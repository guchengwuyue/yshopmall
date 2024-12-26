<template>
  <view class="order-submission">
    <view class="allAddress" :style="systemStore ? '' : 'padding-top: 0.2*100rpx'">
      <view class="nav acea-row">
        <view class="item font-color-red" :class="shipping_type === 0 ? 'on' : 'on2'" @click="addressType(0)" v-if="systemStore"></view>
        <view  class="item font-color-red" :class="shipping_type === 1 ? 'on' : 'on2'" @click="addressType(1)" v-if="storeSelfMention != 2 && systemStore && !isIntegral"></view>
      </view>
      <view class="address acea-row row-between-wrapper" v-if="shipping_type === 0" @click="addressTap">
        <view class="addressCon" v-if="addressInfo.realName">
          <view class="name">
            {{ addressInfo.realName }}
            <text class="phone">{{ addressInfo.phone }}</text>
          </view>
          <view>
            <text class="default font-color-red" v-if="addressInfo.isDefault">[默认]</text>
            {{ addressInfo.province }}{{ addressInfo.city }}{{ addressInfo.district }}{{ addressInfo.detail }}
          </view>
        </view>
        <view class="addressCon" v-else>
          <view class="setaddress">设置收货地址</view>
        </view>
        <view class="iconfont icon-jiantou"></view>
      </view>
      <view class="address acea-row row-between-wrapper" v-if="shipping_type === 1" @click="showStoreList">
        <view class="addressCon" v-if="storeItems">
          <view class="name">
            {{ storeItems.name }}
            <span class="phone">{{ storeItems.phone }}</span>
          </view>
          <view>{{ storeItems.address }}</view>
        </view>
        <view class="addressCon" v-else>
          <view class="name">
            {{ systemStore.name }}
            <span class="phone">{{ systemStore.phone }}</span>
          </view>
          <view>{{ systemStore.address }}</view>
        </view>
        <view class="iconfont icon-jiantou"></view>
      </view>
      <view class="line">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/line.jpg`" />
      </view>
    </view>
    <OrderGoods :evaluate="0" :isIntegral="isIntegral" :cartInfo="orderGroupInfo.cartInfo"></OrderGoods>
    <view class="wrapper">
      <view class="item acea-row row-between-wrapper" @click="couponTap" v-if="deduction === false && !isIntegral">
        <view>优惠券</view>
        <view class="discount">
          {{ usableCoupon.couponTitle || '请选择' }}
          <text class="iconfont icon-jiantou"></text>
        </view>
      </view>
      <view class="item acea-row row-between-wrapper" v-if="!isIntegral && deduction === false && enableIntegral === true">
        <view>积分抵扣</view>
        <view class="discount">
          <view class="select-btn">
            <view class="checkbox-wrapper">
              <!-- <input type="checkbox" v-model="useIntegral" @click="changeUseIntegral"/> -->
              <checkbox-group @change="changeUseIntegral">
                <label class="well-check">
                  <text class="integral">
                    当前积分
                    <text class="num font-color-red">{{ userInfo.integral || 0 }}</text>
                  </text>
                  <checkbox value="true" :checked="useIntegral ? true : false"></checkbox>
                </label>
              </checkbox-group>
            </view>
          </view>
        </view>
      </view>
      <view class="item acea-row row-between-wrapper" v-if="shipping_type === 0">
        <view>快递费用</view>
        <view class="discount">
          {{ orderPrice.payPostage > 0 ? orderPrice.payPostage : '免运费' }}
        </view>
      </view>
      <view v-if="shipping_type === 1">
        <view class="item acea-row row-between-wrapper">
          <view>联系人</view>
          <view class="discount">
            <input type="text" placeholder="请填写您的联系姓名" v-model="contacts" />
          </view>
        </view>
        <view class="item acea-row row-between-wrapper">
          <view>联系电话</view>
          <view class="discount">
            <input type="text" placeholder="请填写您的联系电话" v-model="contactsTel" />
          </view>
        </view>
      </view>
      <view class="item">
        <view>备注信息（150字以内）</view>
        <textarea v-model="mark"></textarea>
      </view>
    </view>
    <view class="wrapper">
      <view class="item">
        <view>支付方式</view>
        <view class="list">
          <view class="payItem acea-row row-middle" v-if="!isIntegral" :class="active === 'weixin' ? 'on' : ''" @click="payItem('weixin')" v-show="isWeixin">
            <view class="name acea-row row-center-wrapper"> <view class="iconfont icon-weixin2" :class="active === 'weixin' ? 'bounceIn' : ''"></view>微信支付 </view>
            <view class="tip">微信快捷支付</view>
          </view>
          <view class="payItem acea-row row-middle" v-if="!isIntegral" :class="active === 'weixin' ? 'on' : ''" @click="payItem('weixin')" v-show="!isWeixin">
            <view class="name acea-row row-center-wrapper"> <view class="iconfont icon-weixin2" :class="active === 'weixin' ? 'bounceIn' : ''"></view>微信支付 </view>
            <view class="tip">微信快捷支付</view>
          </view>
          <view class="payItem acea-row row-middle" v-if="!isIntegral" :class="active === 'yue' ? 'on' : ''" @click="payItem('yue')">
            <view class="name acea-row row-center-wrapper"> <view class="iconfont icon-icon-test" :class="active === 'yue' ? 'bounceIn' : ''"></view>余额支付 </view>
            <view class="tip">可用余额：{{ userInfo.nowMoney || 0 }}</view>
          </view>
          <view class="payItem acea-row row-middle" v-if="isIntegral" :class="active === 'integral' ? 'on' : ''" @click="payItem('integral')">
            <view class="name acea-row row-center-wrapper"> <view class="iconfont icon-icon-test" :class="active === 'integral' ? 'bounceIn' : ''"></view>积分支付 </view>
            <view class="tip">可用积分：{{ userInfo.integral || 0 }}</view>
          </view>
        </view>
      </view>
    </view>
    <view class="moneyList">
      <view class="item acea-row row-between-wrapper" v-if="orderPrice.totalPrice !== undefined">
        <view>商品总价：</view>
        <view class="money" v-if="!isIntegral">￥{{ orderPrice.totalPrice }}</view>
        <view class="money" v-if="isIntegral">{{ orderPrice.payIntegral }}积分</view>
      </view>
      <view class="item acea-row row-between-wrapper" v-if="orderPrice.payPostage > 0 && !isIntegral">
        <view>运费：</view>
        <view class="money">￥{{ orderPrice.payPostage }}</view>
      </view>
      <view class="item acea-row row-between-wrapper" v-if="orderPrice.couponPrice > 0 && !isIntegral">
        <view>优惠券抵扣：</view>
        <view class="money">-￥{{ orderPrice.couponPrice }}</view>
      </view>
      <view class="item acea-row row-between-wrapper" v-if="orderPrice.deductionPrice > 0 && !isIntegral">
        <view>积分抵扣：</view>
        <view class="money">-￥{{ orderPrice.deductionPrice }}</view>
      </view>
    </view>
    <view style="height: 120rpx"></view>
    <view class="footer acea-row row-between-wrapper">
      <view>
        合计:
        <text class="font-color-red" v-if="!isIntegral">￥{{ orderPrice.payPrice }}</text>
        <text class="font-color-red" v-if="isIntegral">{{ orderPrice.payIntegral }}积分</text>
      </view>
      <view class="settlement" @click="createOrder">立即结算</view>
    </view>
    <CouponListWindow v-on:couponchange="changecoupon($event)" v-model="showCoupon" :price="orderPrice.totalPrice" :checked="usableCoupon.id" @checked="changeCoupon" :cartid="cartid"></CouponListWindow>
    <AddressWindow @checked="changeAddress" @redirect="addressRedirect" v-model="showAddress" :checked="addressInfo.id" ref="mychild"></AddressWindow>
  </view>
</template>
<style scoped lang="less">
.order-submission .wrapper .shipping select {
  color: #999;
  padding-right: 0.15 * 100rpx;
}

.order-submission .wrapper .shipping .iconfont {
  font-size: 0.3 * 100rpx;
  color: #515151;
}

.order-submission .allAddress {
  width: 100%;
  background-image: linear-gradient(to bottom, #eb3729 0%, #eb3729 100%);
  background-image: -webkit-linear-gradient(to bottom, #eb3729 0%, #eb3729 100%);
  background-image: -moz-linear-gradient(to bottom, #eb3729 0%, #eb3729 100%);
  padding-top: 1 * 100rpx;
}

.order-submission .allAddress .nav {
  margin: 0 auto;
  padding: 0 30rpx;
  width: 100%;
  box-sizing: border-box;
}

.order-submission .allAddress .nav .item {
  flex: 1;
  position: relative;
}

.order-submission .allAddress .nav .item.on {
  position: relative;
}

.order-submission .allAddress .nav .item.on:before {
  position: absolute;
  bottom: 0;
  content: '快递配送';
  font-size: 0.28 * 100rpx;
  display: block;
  height: 0;
  left: 0;
  right: 0;
  border-width: 0.4 * 100rpx;
  border-style: solid;
  border-color: #fff;
  z-index: 9;
  text-align: center;
  line-height: 0.14 * 100rpx;
}

.order-submission .allAddress .nav .item:nth-of-type(2).on:before {
  content: '到店自提';
  border-width: 0.4 * 100rpx;
}

.order-submission .allAddress .nav .item.on2 {
  position: relative;
}

.order-submission .allAddress .nav .item.on2:before {
  position: absolute;
  bottom: 0;
  content: '到店自提';
  font-size: 0.28 * 100rpx;
  display: block;
  height: 0;
  left: 0;
  right: 0;
  border-width: 0.4 * 100rpx;
  border-style: solid;
  border-color: #d5e6e6;
  text-align: center;
  line-height: 0.14 * 100rpx;
}

.order-submission .allAddress .nav .item:nth-of-type(1).on2:before {
  content: '快递配送';
  border-width: 0.4 * 100rpx;
}

.order-submission .allAddress .address {
  width: 6.91 * 100rpx;
  height: 1.5 * 100rpx;
  margin: 0 auto;
  box-sizing: border-box;
}

.order-submission .allAddress .line {
  width: 7.1 * 100rpx;
  margin: 0 auto;
}

.order-submission .wrapper .item .discount input::placeholder {
  color: #ccc;
}
</style>
<script>
import OrderGoods from '@/components/OrderGoods'
import CouponListWindow from '@/components/CouponListWindow'
import AddressWindow from '@/components/AddressWindow'
import { postOrderConfirm, postOrderComputed, createOrder } from '@/api/order'
import { mapGetters } from 'vuex'
import { handleOrderPayResults, subscribeMessage } from '@/libs/order'
import { weappPay } from '@/libs/wechat'
import { isWeixin, handleErrorMessage } from '@/utils'

const NAME = 'OrderSubmission',
  _isWeixin = isWeixin()
export default {
  name: NAME,
  components: {
    OrderGoods,
    CouponListWindow,
    AddressWindow,
  },
  props: {},
  data: function() {
    return {
      offlinePayStatus: 2,
      from: this.$deviceType,
      deduction: true,
      enableIntegral: true,
      enableIntegralNum: 0,
      isWeixin: _isWeixin,
      pinkId: 0,
      active: _isWeixin ? 'weixin' : 'yue',
      showCoupon: false,
      showAddress: false,
      addressInfo: {},
      couponId: 0,
      orderGroupInfo: {
        priceGroup: {},
      },
      usableCoupon: {},
      addressLoaded: false,
      useIntegral: false,
      orderPrice: {
        payPrice: '计算中',
      },
      mark: '',
      systemStore: {},
      shipping_type: 0,
      contacts: '',
      contactsTel: '',
      storeSelfMention: 0,
      cartid: '',
      isIntegral: false,
    }
  },
  computed: mapGetters(['userInfo', 'storeItems']),
  watch: {
    useIntegral() {
      this.computedPrice()
    },
    shipping_type() {
      this.computedPrice()
    },
  },
  onLoad: function() {
  // #ifdef MP-WEIXIN
   
   subscribeMessage()
   // #endif
    let that = this
    this.$store.dispatch('getUser', true)
    that.getCartInfo()

    console.log(that.$yroute)
    if (that.$yroute.query.pinkid !== undefined) {
      that.pinkId = that.$yroute.query.pinkid
    }
    this.isIntegral = that.$yroute.query.isIntegral == 'true'
    this.useIntegral = this.isIntegral
    if (this.isIntegral) {
      this.active = 'integral'
    }
    if (that.$yroute.query.id !== undefined) {
      that.cartid = that.$yroute.query.id
      console.log(that.cartid)
    }
  },
  methods: {
    showStoreList() {
      this.$store.commit('get_to', 'orders')
      this.$yrouter.push({
        path: '/pages/shop/StoreList/index',
      })
    },
    addressType: function(index) {
      if (index && !this.systemStore.id) {
        uni.showToast({
          title: '暂无门店信息，您无法选择到店自提！',
          icon: 'none',
          duration: 2000,
        })
        return
      }
      console.log(this)
      this.shipping_type = index
    },
    changeUseIntegral: function(e) {
      // this.computedPrice();
      if (this.isIntegral) {
        return
      }
      this.useIntegral = e.mp.detail.value[0]
    },
	// 计算商品价格
    computedPrice() {
      let shipping_type = this.shipping_type
      postOrderComputed(this.orderGroupInfo.orderKey, {
        addressId: this.addressInfo.id,
        useIntegral: this.useIntegral ? 1 : 0,
        couponId: this.usableCoupon.id || 0,
        shipping_type: parseInt(shipping_type) + 1,
      }).then(res => {
        const data = res.data
        if (data.status === 'EXTEND_ORDER') {
          this.$yrouter.replace({
            path: '/pages/order/OrderDetails/index',
            query: {
              id: data.result.orderId,
            },
          })
        } else {
          this.orderPrice = data.result
        }
      })
    },
    getCartInfo() {
      let cartIds = this.$yroute.query.id
	  // 拼团id
	  if (this.$yroute.query.pinkId) {
		 cartIds = this.$yroute.query.pinkId
	  }
      if (!cartIds) {
        uni.showToast({
          title: '参数有误',
          icon: 'none',
          duration: 2000,
        })
        return this.$yrouter.back()
      }
      postOrderConfirm(cartIds)
        .then(res => {
          this.offlinePayStatus = res.data.offline_pay_status
          this.orderGroupInfo = res.data
          this.deduction = res.data.deduction
          this.usableCoupon = res.data.usableCoupon || {}
          this.addressInfo = res.data.addressInfo || {}
          // 用来显示到店自提的店铺地址
          this.systemStore = res.data.systemStore || {}
          this.storeSelfMention = res.data.storeSelfMention
          this.computedPrice()
        })
        .catch((error) => {
          console.log(error)
          uni.showToast({
            title: '加载订单数据失败',
            icon: 'none',
            duration: 2000,
          })
        })
    },
    addressTap: function() {
      this.showAddress = true
      if (!this.addressLoaded) {
        this.addressLoaded = true
        this.$refs.mychild.getAddressList()
      }
    },
    addressRedirect() {
      this.addressLoaded = false
      this.showAddress = false
    },
    couponTap: function() {
      this.showCoupon = true
    },
    changeCoupon: function(coupon) {
      if (!coupon) {
        this.usableCoupon = {
          couponTitle: '不使用优惠券',
          id: 0,
        }
      } else {
        this.usableCoupon = coupon
      }
      this.computedPrice()
    },
    payItem: function(index) {
      this.active = index
    },
    changeAddress(addressInfo) {
      this.addressInfo = addressInfo
      this.computedPrice()
    },
    createOrder() {
      if (this.isIntegral) {
        // 积分支付
        if (this.userInfo.integral < this.orderPrice.payIntegral) {
          uni.showToast({
            title: '积分不足',
            icon: 'none',
            duration: 2000,
          })
          return
        }
        this.active = 'integral'
      }
      let shipping_type = this.shipping_type
      if (!this.isIntegral && !this.active) {
        uni.showToast({
          title: '请选择支付方式',
          icon: 'none',
          duration: 2000,
        })
        return
      }
      if (!this.addressInfo.id && !this.shipping_type) {
        uni.showToast({
          title: '请选择收货地址',
          icon: 'none',
          duration: 2000,
        })
        return
      }

      if (this.shipping_type) {
        if ((this.contacts === '' || this.contactsTel === '') && this.shipping_type) {
          uni.showToast({
            title: '请填写联系人或联系人电话',
            icon: 'none',
            duration: 2000,
          })
          return
        }

        if (!/^1(3|4|5|7|8|9|6)\d{9}$/.test(this.contactsTel)) {
          uni.showToast({
            title: '请填写正确的手机号',
            icon: 'none',
            duration: 2000,
          })
          return
        }
        if (!/^[\u4e00-\u9fa5\w]{2,16}$/.test(this.contacts)) {
          uni.showToast({
            title: '请填写您的真实姓名',
            icon: 'none',
            duration: 2000,
          })
          return
        }
      }

      uni.showLoading({
        title: '生成订单中',
      })
      let from = {}
      if (this.$deviceType == 'app') {
        from.from = 'app'
      }
      console.log(this.$deviceType)
      // #ifdef MP-WEIXIN
	  
     // subscribeMessage()
      // #endif
      createOrder(this.orderGroupInfo.orderKey, {
        realName: this.contacts,
        phone: this.contactsTel,
        addressId: this.addressInfo.id,
        useIntegral: this.useIntegral ? 1 : 0,
        couponId: this.usableCoupon.id || 0,
        payType: this.active,
        pinkId: this.pinkId,
        seckillId: this.orderGroupInfo.seckill_id,
        combinationId: this.orderGroupInfo.combination_id,
        from: this.from,
        mark: this.mark || '',
        shippingType: parseInt(shipping_type) + 1,
        storeId: this.storeItems ? this.storeItems.id : this.systemStore.id,
        ...from,
      })
        .then(res => {
          uni.hideLoading()
          handleOrderPayResults.call(this, res.data, 'create', this.active)
        })
        .catch(err => {
          handleErrorMessage(err, '创建订单失败')
        })
    },
  },
}
</script>
