<template>
  <view class="order-details pos-order-details">
    <view class="header acea-row row-middle">
      <view class="state">{{ title }}</view>
      <view class="data">
        <view class="order-num">订单：{{ orderInfo.orderId }}</view>
        <view>
          <text class="time">
            <data-format :date="orderInfo.addTime"></data-format>
          </text>
        </view>
      </view>
    </view>
    <view class="orderingUser acea-row row-middle">{{ orderInfo.nickname }}</view>
    <view class="address">
      <view class="name">
        {{ orderInfo.realName }}
        <text class="phone">{{ orderInfo.userPhone }}</text>
      </view>
      <view>{{ orderInfo.userAddress }}</view>
    </view>
    <view class="line">
      <image :src="`${$VUE_APP_RESOURCES_URL}/images/line.jpg`" />
    </view>
    <view class="pos-order-goods">
      <view class="goods acea-row row-between-wrapper" v-for="(item, orderInfoIndex) in orderInfo.cartInfo" :key="orderInfoIndex">
        <view class="picTxt acea-row row-between-wrapper">
          <view class="pictrue">
            <image :src="item.productInfo.image" />
          </view>
          <view class="text acea-row row-between row-column">
            <view class="info line2">{{ item.productInfo.storeName }}</view>
            <view class="attr">{{ item.productInfo.sku }}</view>
          </view>
        </view>
        <view class="money">
          <view class="x-money">￥{{ item.productInfo.price }}</view>
          <view class="num">x{{ item.cartNum }}</view>
          <view class="y-money">￥{{ item.productInfo.otPrice ? item.productInfo.otPrice : 0 }}</view>
        </view>
      </view>
    </view>
    <view class="public-total">
      共{{ orderInfo.totalNum }}件商品，应支付
      <text class="money">￥{{ orderInfo.payPrice }}</text>
      ( 邮费 ¥{{ orderInfo.payPostage }})
    </view>
    <view class="wrapper">
      <view class="item acea-row row-between">
        <view>订单编号：</view>
        <view class="conter acea-row row-middle row-right">
          {{ orderInfo.orderId }}
          <!-- #ifndef H5 -->
          <text class="copy copy-data" @click="copyClipboard(orderInfo.orderId)">复制</text>
          <!-- #endif -->
        </view>
      </view>
      <view class="item acea-row row-between">
        <view>下单时间：</view>
        <!-- <view class="conter"> -->
        <view class="conter">{{ orderInfo.createTime }}</view>
        <!-- <data-format :date="orderInfo.addTime"></data-format> -->
        <!-- </view> -->
      </view>
      <view class="item acea-row row-between">
        <view>支付状态：</view>
        <view class="conter">{{ orderInfo.paid == 1 ? '已支付' : '未支付' }}</view>
      </view>
      <view class="item acea-row row-between">
        <view>支付方式：</view>
        <view class="conter">{{ payType }}</view>
      </view>
      <view class="item acea-row row-between">
        <view>买家留言：</view>
        <view class="conter">{{ orderInfo.mark }}</view>
      </view>
    </view>
    <view class="wrapper">
      <view class="item acea-row row-between">
        <view>支付金额：</view>
        <view class="conter">￥{{ orderInfo.totalPrice }}</view>
      </view>
      <view class="item acea-row row-between">
        <view>优惠券抵扣：</view>
        <view class="conter">-￥{{ orderInfo.couponPrice }}</view>
      </view>
      <view class="actualPay acea-row row-right">
        实付款：
        <text class="money font-color-red">￥{{ orderInfo.payPrice }}</text>
      </view>
    </view>
    <view class="wrapper" v-if="orderInfo.delivery_type != 'fictitious' && orderInfo._status._type === 2">
      <view class="item acea-row row-between">
        <view>配送方式：</view>
        <view class="conter" v-if="orderInfo.delivery_type === 'express'">快递</view>
        <view class="conter" v-if="orderInfo.delivery_type === 'send'">送货</view>
      </view>
      <view class="item acea-row row-between">
        <view v-if="orderInfo.delivery_type === 'express'">快递公司：</view>
        <view v-if="orderInfo.delivery_type === 'send'">送货人：</view>
        <view class="conter">{{ orderInfo.delivery_name }}</view>
      </view>
      <view class="item acea-row row-between">
        <view v-if="orderInfo.delivery_type === 'express'">快递单号：</view>
        <view v-if="orderInfo.delivery_type === 'send'">送货人电话：</view>
        <view class="conter">
          {{ orderInfo.delivery_id }}
          <!-- #ifndef H5 -->
          <text class="copy copy-data" @click="copyClipboard(orderInfo.delivery_id)">复制</text>
          <!-- #endif -->
        </view>
      </view>
    </view>
    <view style="height:100rpx;"></view>
    <view class="footer acea-row row-right row-middle">
      <view class="more"></view>
      <view class="bnt cancel" @click="modify(0)" v-if="types == 0">一键改价</view>
      <view class="bnt cancel" @click="modify(0)" v-if="types == -1">立即退款</view>
      <view class="bnt cancel" v-if="orderInfo.pay_type === 'offline' && orderInfo.paid === 0" @click="offlinePay">确认付款</view>
      <view class="bnt delivery" v-if="title == '未发货' && types == 1" @click="goGoodsDeliver(orderInfo)">去发货</view>
      <view class="bnt quick" v-if="title == '待核销' && types == 1" @click="storeCancellation(0)">快速核销</view>
      <view class="bnt delivery" v-if="title == '待核销' && types == 1" @click="storeCancellation(1)">立即核销</view>
    </view>
    <PriceChange :change="change" :orderInfo="orderInfo" v-on:closechange="changeclose($event)" v-on:savePrice="savePrice" :status="status"></PriceChange>
  </view>
</template>
<script>
import PriceChange from '@/components/PriceChange'
import DataFormat from '@/components/DataFormat'
import { getAdminOrderDetail, setAdminOrderPrice, setAdminOrderRemark, setOfflinePay, setOrderRefund } from '@/api/admin'
import { orderVerific } from '@/api/order'
import { required, num } from '@/utils/validate'
import { validatorDefaultCatch } from '@/utils/dialog'
import { copyClipboard } from '@/utils'

export default {
  name: 'AdminOrder',
  components: {
    PriceChange,
    DataFormat,
  },
  props: {},
  data: function() {
    return {
      order: false,
      change: false,
      order_id: '',
      orderInfo: {
        _status: {},
      },
      status: '',
      title: '',
      payType: '',
      types: '',
    }
  },
  watch: {
    '$yroute.query.oid': function(newVal) {
      let that = this
      if (newVal != undefined) {
        that.order_id = newVal
        that.getIndex()
      }
    },
  },
  onShow: function() {
    this.order_id = this.$yroute.query.oid
    this.getIndex()
  },
  methods: {
    goGoodsDeliver(orderInfo) {
      this.$yrouter.push({
        path: '/pages/orderAdmin/GoodsDeliver/index',
        query: { oid: orderInfo.orderId },
      })
    },
    copyClipboard,
    more: function() {
      this.order = !this.order
    },
    modify: function(status) {
      this.change = true
      this.status = status
    },
    changeclose: function(msg) {
      this.change = msg
    },
    getIndex: function() {
      let that = this
      getAdminOrderDetail(that.order_id).then(
        res => {
          that.orderInfo = res.data
          that.types = res.data._status._type
          that.title = res.data._status._title
          that.payType = res.data._status._payType
        },
        err => {
          uni.showToast({
            title: res.msg,
            icon: 'none',
            duration: 2000,
          })
        }
      )
    },
    async savePrice(opt) {
      let that = this,
        data = {},
        price = opt.price,
        remark = opt.remark,
        refund_price = opt.refund_price.toString()
      data.orderId = that.orderInfo.orderId
      if (that.status == 0 && that.orderInfo.refundStatus === 0) {
        try {
          await this.$validator({
            price: [required(required.message('金额')), num(num.message('金额'))],
          }).validate({ price })
        } catch (e) {
          return validatorDefaultCatch(e)
        }
        data.price = price
        setAdminOrderPrice(data).then(
          function() {
            that.change = false
            uni.showToast({
              title: '改价成功',
              icon: 'success',
              duration: 2000,
            })
            that.getIndex()
          },
          function() {
            that.change = false
            uni.showToast({
              title: '改价失败',
              icon: 'none',
              duration: 2000,
            })
          }
        )
      } else if (that.status == 0 && that.orderInfo.refundStatus === 1) {
        try {
          await this.$validator({
            refund_price: [required(required.message('金额')), num(num.message('金额'))],
          }).validate({ refund_price })
        } catch (e) {
          return validatorDefaultCatch(e)
        }
        data.price = refund_price
        data.type = opt.type
        setOrderRefund(data).then(
          res => {
            that.change = false
            uni.showToast({
              title: res.msg,
              icon: 'none',
              duration: 2000,
            })
            that.getIndex()
          },
          err => {
            that.change = false
            uni.showToast({
              title: res.msg,
              icon: 'none',
              duration: 2000,
            })
            that.getIndex()
          }
        )
      } else {
        try {
          await this.$validator({
            remark: [required(required.message('备注'))],
          }).validate({ remark })
        } catch (e) {
          return validatorDefaultCatch(e)
        }
        data.remark = remark
        setAdminOrderRemark(data).then(
          res => {
            that.change = false
            uni.showToast({
              title: res.msg,
              icon: 'none',
              duration: 2000,
            })
            that.getIndex()
          },
          err => {
            that.change = false
            uni.showToast({
              title: res.msg,
              icon: 'none',
              duration: 2000,
            })
          }
        )
      }
    },
    offlinePay: function() {
      setOfflinePay({ order_id: this.orderInfo.order_id }).then(
        res => {
          uni.showToast({
            title: res.msg,
            icon: 'none',
            duration: 2000,
          })
          this.getIndex()
        },
        err => {
          uni.showToast({
            title: err.msg,
            icon: 'none',
            duration: 2000,
          })
        }
      )
    },
    storeCancellation(index) {
      const that = this
      that.check = true
      if (index == 0) {
        uni.showModal({
          title: '确定核销订单?',
          content: '注意:请务必核对核销码的与客户正确性',
          success(res) {
            if (res.confirm) {
              uni.showLoading({
                title: '查询中',
              })
              orderVerific(that.orderInfo.verifyCode, 1)
                .then(res => {
                  console.log(res)
                  uni.hideLoading()
                  that.iShidden = false
                  uni.showToast({
                    title: res.msg,
                    icon: 'none',
                    duration: 1000,
                  })
                  //最后就是返回上一个页面。
                  setTimeout(function() {
                    uni.navigateBack({
                      delta: 1, // 返回上一级页面。
                      success: function() {
                        console.log('成功！')
                      },
                    })
                  }, 1000)
                })
                .catch(err => {
                  console.log(err)
                  uni.hideLoading()
                  uni.showToast({
                    title: err.data.msg,
                    icon: 'none',
                    duration: 2000,
                  })
                })
            }
          },
        })
      } else {
        that.$yrouter.push({
          path: '/pages/orderAdmin/OrderCancellation/index',
        })
      }
    },
  },
}
</script>
<style lang="less">
.quick {
  background: #f25555;
}
</style>
