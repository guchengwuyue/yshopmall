<template>
  <view class="my-order" ref="container">
    <view class="header bg-color-red">
      <view class="picTxt acea-row row-between-wrapper">
        <view class="text">
          <view class="name">订单信息</view>
          <view>累计订单：{{ orderData.orderCount || 0 }} 总消费：￥{{ orderData.sumPrice || 0 }}</view>
        </view>
      </view>
    </view>
    <view class="nav acea-row row-around">
      <view class="item" :class="{ on: type === 0 }" @click="changeType(0)">
        <view>待付款</view>
        <view class="num">{{ orderData.unpaidCount || 0 }}</view>
      </view>
      <view class="item" :class="{ on: type === 1 }" @click="changeType(1)">
        <view>待发货</view>
        <view class="num">{{ orderData.unshippedCount || 0 }}</view>
      </view>
      <view class="item" :class="{ on: type === 2 }" @click="changeType(2)">
        <view>待收货</view>
        <view class="num">{{ orderData.receivedCount || 0 }}</view>
      </view>
      <view class="item" :class="{ on: type === 3 }" @click="changeType(3)">
        <view>待评价</view>
        <view class="num">{{ orderData.evaluatedCount || 0 }}</view>
      </view>
      <view class="item" :class="{ on: type === 4 }" @click="changeType(4)">
        <view>已完成</view>
        <view class="num">{{ orderData.completeCount || 0 }}</view>
      </view>
    </view>
    <view class="list">
      <view class="item" v-for="(order, orderListIndex) in orderList" :key="orderListIndex">
        <view class="title acea-row row-between-wrapper">
          <view class="acea-row row-middle">
            <span class="sign cart-color acea-row row-center-wrapper" v-if="order.combinationId > 0">拼团</span>
            <span class="sign cart-color acea-row row-center-wrapper" v-if="order.seckillId > 0">秒杀</span>
            <span class="sign cart-color acea-row row-center-wrapper" v-if="order.storeId > 0">门店</span>
            {{ order.createTime }}
          </view>
          <view class="font-color-red">{{ getStatus(order) }}</view>
        </view>
        <view @click="goOrderDetails(order)">
          <view class="item-info acea-row row-between row-top" v-for="(cart, cartInfoIndex) in order.cartInfo" :key="cartInfoIndex">
            <view class="pictrue">
              <image :src="cart.productInfo.image" @click.stop="$yrouter.push({ path: '/pages/shop/GoodsCon/index', query: { id: cart.productInfo.id } })" v-if="cart.combinationId === 0 && cart.seckillId === 0" />
              <image
                :src="cart.productInfo.image"
                @click.stop="
                  $yrouter.push({
                    path: '/pages/activity/GroupDetails/index',
                    query: { id: cart.combinationId },
                  })
                "
                v-else-if="cart.combinationId > 0"
              />
              <image
                :src="cart.productInfo.image"
                @click.stop="
                  $yrouter.push({
                    path: '/pages/activity/SeckillDetails/index',
                    query: { id: cart.seckillId },
                  })
                "
                v-else-if="cart.seckillId > 0"
              />
            </view>
            <view class="text acea-row row-between">
              <view class="name line2">{{ cart.productInfo.storeName }}</view>
              <view class="money">
                <view v-if="order.payType != 'integral'"> ￥{{ cart.productInfo.attrInfo ? cart.productInfo.attrInfo.price : cart.productInfo.price }} </view>
                <view v-if="order.payType == 'integral'"> {{ order.payIntegral }}积分 </view>
                <view>x{{ cart.cartNum }}</view>
              </view>
            </view>
          </view>
        </view>
        <view class="totalPrice">
          共{{ order.cartInfo.length || 0 }}件商品，总金额
          <text class="money font-color-red" v-if="order.payType != 'integral'">￥{{ order.payPrice }}</text>
          <text class="money font-color-red" v-if="order.payType == 'integral'">{{ order.payIntegral }}积分</text>
        </view>
        <view class="bottom acea-row row-right row-middle">
          <template v-if="order._status._type == 0">
            <view class="bnt cancelBnt" @click="cancelOrder(order)">取消订单</view>
            <view class="bnt bg-color-red" @click="goOrderDetails(order)">立即付款</view>
          </template>
          <template v-if="order._status._type == 1 || order._status._type == 9">
            <view class="bnt bg-color-red" @click="goOrderDetails(order)">查看详情</view>
          </template>
          <template v-if="order._status._type == 2">
            <view class="bnt default" @click="goLogistics(order)">查看物流</view>
            <view class="bnt bg-color-red" @click="takeOrder(order)">确认收货</view>
          </template>
          <template v-if="order._status._type == 3">
            <!--<view-->
            <!--class="bnt default"-->
            <!--@click="-->
            <!--$yrouter.push({ path: '/pages/order/Logistics/index',query:{id:order.orderId}})-->
            <!--"-->
            <!--v-if="order.deliveryType == 'express'"-->
            <!--&gt;-->
            <!--查看物流-->
            <!--</view>-->
            <view class="bnt bg-color-red" @click="goOrderDetails(order)">去评价</view>
          </template>
          <template v-if="order._status._type === 4">
            <view class="bnt bg-color-red" @click="goOrderDetails(order)">查看订单</view>
          </template>
        </view>
      </view>
    </view>
    <view class="noCart" v-if="orderList.length === 0 && page > 1">
      <view class="pictrue">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noOrder.png`" />
      </view>
    </view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
    <Payment v-model="pay" :types="payType" @checked="toPay" :balance="userInfo.nowMoney"></Payment>
  </view>
</template>
<script>
import { getOrderData, getOrderList } from '@/api/order'
import { cancelOrderHandle, payOrderHandle, takeOrderHandle } from '@/libs/order'
import Loading from '@/components/Loading'
import Payment from '@/components/Payment'
import DataFormat from '@/components/DataFormat'
import { mapGetters } from 'vuex'
import { isWeixin, dataFormat } from '@/utils'

const STATUS = ['待付款', '待发货', '待收货', '待评价', '已完成', '', '', '', '', '待付款']

const NAME = 'MyOrder'

export default {
  name: NAME,
  data() {
    return {
      offlinePayStatus: 2,
      orderData: {},
      type: '',
      page: 1,
      limit: 20,
      loaded: false,
      loading: false,
      orderList: [],
      pay: false,
      payType: ['yue', 'weixin'],
      from: this.$deviceType,
    }
  },
  components: {
    Loading,
    Payment,
    DataFormat,
  },
  computed: mapGetters(['userInfo']),
  onShow: function() {
    console.log(this)
    this.type = parseInt(this.$yroute.query.type) || 0
    this.changeType(this.type)
    this.getOrderData()
    this.getOrderList()
  },
  onHide: function() {
    this.orderList = []
    this.page = 1
    this.limit = 20
    this.loaded = false
    this.loading = false
  },
  methods: {
    goLogistics(order) {
      this.$yrouter.push({
        path: '/pages/order/Logistics/index',
        query: { id: order.orderId },
      })
    },
    goOrderDetails(order) {
      this.$yrouter.push({
        path: '/pages/order/OrderDetails/index',
        query: { id: order.orderId },
      })
    },
    dataFormat,
    setOfflinePayStatus: function(status) {
      var that = this
      that.offlinePayStatus = status
      if (status === 1) {
        if (that.payType.indexOf('offline') < 0) {
          that.payType.push('offline')
        }
      }
    },
    getOrderData() {
      getOrderData().then(res => {
        this.orderData = res.data
      })
    },
    takeOrder(order) {
      takeOrderHandle(order.orderId).finally(() => {
        this.reload()
        this.getOrderData()
      })
    },
    reload() {
      this.changeType(this.type)
    },
    changeType(type) {
      this.type = type
      this.orderList = []
      this.page = 1
      this.loaded = false
      this.loading = false
      this.getOrderList()
    },
    getOrderList() {
      if (this.loading || this.loaded) return
      this.loading = true
      const { page, limit, type } = this
      getOrderList({
        page,
        limit,
        type,
      }).then(res => {
        this.orderList = this.orderList.concat(res.data)
        this.page++
        this.loaded = res.data.length < this.limit
        this.loading = false
      })
    },
    getStatus(order) {
      return STATUS[order._status._type]
    },
    cancelOrder(order) {
      cancelOrderHandle(order.orderId)
        .then(() => {
          this.getOrderData()
          this.orderList.splice(this.orderList.indexOf(order), 1)
        })
        .catch(() => {
          this.reload()
        })
    },
    paymentTap: function(order) {
      var that = this
      if (!(order.combinationId > 0 || order.seckillId > 0)) {
        that.setOfflinePayStatus(order.offlinePayStatus)
      }
      this.pay = true
      this.toPay = type => {
        payOrderHandle(order.orderId, type, that.from)
          .then(() => {
            const type = parseInt(this.$yroute.query.type) || 0
            that.changeType(type)
            that.getOrderData()
          })
          .catch(() => {
            const type = parseInt(that.$yroute.query.type) || 0
            that.changeType(type)
            that.getOrderData()
          })
      }
    },
    toPay() {},
  },
  mounted() {},
  onReachBottom() {
    !this.loading && this.getOrderList()
  },
}
</script>

<style scoped lang="less">
.noCart {
  margin-top: 0.17 * 100rpx;
  padding-top: 0.1 * 100rpx;
}

.noCart .pictrue {
  width: 4 * 100rpx;
  height: 3 * 100rpx;
  overflow: hidden;
  margin: 0.7 * 100rpx auto 0.5 * 100rpx auto;
}

.noCart .pictrue image {
  width: 4 * 100rpx;
  height: 3 * 100rpx;
}
</style>
