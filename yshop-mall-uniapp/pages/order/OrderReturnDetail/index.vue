<template>
  <view class="returnList">
    <view class="data bgRed">
      <view v-if="orderDetail.salesState === 0">
        <view class="state" v-if="orderDetail.state === 0">
          已提交申请，请耐心等待卖家处理
        </view>
        <view class="state" v-if="orderDetail.state === 1">
          <view class="state" v-if="orderDetail.serviceType === 0">
            已通过平台审核,正在退款
          </view>
          <view class="state" v-if="orderDetail.serviceType === 1">
            已通过平台审核 ,请填写退货物流
          </view>
        </view>
        <view class="state" v-if="orderDetail.state === 2">
          已发货，等待商家审核
        </view>
        <view class="state" v-if="orderDetail.state === 3">
          售后订单已完成
        </view>
      </view>
      <view v-if="orderDetail.salesState === 1">
        已撤销售后订单
      </view>
      <view v-if="orderDetail.salesState === 2">
        商家已拒绝售后申请
      </view>
    </view>
    <!-- 退款总金额 -->
    <view class="money">
      <view class="top">
        <text>退款总金额</text>
        <text class="colorRed">￥{{ orderDetail.refundAmount || 0 }}</text>
      </view>
      <view class="express" v-if="orderDetail.state === 1 && orderDetail.serviceType === 1 && orderDetail.deliverySn===null">
        <view class="title">商家已同意退货申请，请尽快发货。</view>
        <view class="info people"> 收货人： {{ orderDetail.consignee || '' }} </view>
        <view class="info address"> 收货地址： {{ orderDetail.address || '' }} </view>
        <view class="info phone"> 收货电话： {{ orderDetail.phoneNumber || '' }} </view>
      </view>
      <!-- 退款信息 -->
      <view class="tips" v-if="orderDetail.state === 0 && orderDetail.salesState !== 1 && orderDetail.salesState !== 2 ">
        <text class="title">您已成功发起退款申请，请耐心等待商家处理</text>
        <view class="content">
          <view>· 卖家同意或超时未处理，系统将退款给您</view>
          <view>· 如果卖家拒绝，您可以修改退款申请后再次发起，卖家会重新处理</view>
        </view>
      </view>
        <!-- 物流信息 -->
        <view class="express" v-if="orderDetail.deliverySn && orderDetail.state !== 3">
            <text class="title">您已发送快递，请耐心等待商家处理</text>
            <view class="info people"> 快递公司： {{ orderDetail.deliveryName || '' }}</view>
            <view class="info address"> 快递单号： {{ orderDetail.deliverySn || '' }}</view>
            <view class="info phone"> 发货时间： {{ orderDetail.deliveryTime || '' }}</view>
        </view>
      <!-- 撤销 -->
      <!-- <view class="tips" v-if="orderDetail.serviceType === 1 && orderDetail.state  >= 2">
				<text class="title">您已撤销售后申请</text>
				<view class="content">
					<view>· </view>
					<view>· </view>
				</view>
			</view> -->
      <!-- 商家拒接 -->
      <!-- <view class="tips" v-if="orderDetail.serviceType === 2 && orderDetail.state  >= 2">
				<text class="title">卖家已拒绝</text>
				<view class="content">
					<view>· </view>
					<view>· </view>
				</view>
			</view> -->
      <view class="bottom">
        <view class="btns redBtn" v-if="orderDetail.salesState === 0 && orderDetail.serviceType === 1 && orderDetail.state === 1" @click="toExpress">填写物流</view>
          <view class="btns def" v-if="orderDetail.deliverySn" @click="goLogistics(orderDetail)">查看物流</view>
          <view class="btns def" v-if="orderDetail.salesState === 0 && orderDetail.state !== 2 && orderDetail.state !== 3" @click="cancelReq">撤销申请</view>
        <!-- <view class="btns redBtn" @click="editReq">修改申请</view>-->
      </view>
    </view>
    <!-- 退款信息 -->
    <view class="rebackInfo">
      <view class="top">
        退款信息
      </view>
      <view class="rebackItem" v-for="item in orderDetail.cartInfo" :key="item.id">
        <image class="img" :src="item.productInfo.image" mode=""></image>
        <view class="info">
          <view class="productName">
            {{ item.productInfo.storeName }}
          </view>
          <view class="sku">
            {{ item.productInfo.attrInfo.sku }}
          </view>
        </view>
      </view>
      <view class="bottom">
        <view class="reason">
          <view class="left">
            退款原因:
          </view>
          <view class="right">
            {{ orderDetail.reasons || '' }}
          </view>
        </view>
        <view class="reason">
          <view class="left">
            退款金额:
          </view>
          <view class="right"> ￥{{ orderDetail.refundAmount || 0 }} </view>
        </view>
        <view class="reason">
          <view class="left">
            退款编号:
          </view>
          <view class="right">
            {{ orderDetail.id || '' }}
          </view>
        </view>
        <view class="reason">
          <view class="left">
            申请时间:
          </view>
          <view class="right">
            {{ orderDetail.createTime || '' }}
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import { getAfterSealsDetail, rebackAfterSeals } from '@/api/aftersales.js'
export default {
  data() {
    return {
      orderId: '',
      id: '',
      orderInfo: {},
      orderDetail: {},
    }
  },
  mounted() {
    this.orderId = this.$yroute.query.key
    this.id = this.$yroute.query.id
    this.getReturnDetail()
  },
  methods: {
      goLogistics(order) {
          this.$yrouter.push({
              path: '/pages/order/Logistics/index',
              query: {id: order.orderCode},
          })
      },
    /** state售后状态 0已提交等待平台审核 1平台已审核 等待用户发货/退款 2 用户已发货 3已完成 */
    async getReturnDetail() {
      getAfterSealsDetail(this.orderId, this.id)
        .then(res => {
          this.orderDetail = res.data
        })
        .catch(err => {
          uni.showToast({
            title: '订单异常',
            icon: 'none',
            duration: 2000,
          })
        })
    },
    toExpress() {
      this.$yrouter.push({
        path: '/pages/order/submitExpress/index',
        query: {
          orderCode: this.orderId,
          id: this.id,
        },
      })
    },
    // 撤销申请
    async cancelReq() {
      rebackAfterSeals(this.orderId, this.id)
        .then(res => {
          uni.showToast({
            title: '已撤销',
            icon: 'none',
            duration: 2000,
          })
          setTimeout(() => {
            this.$yrouter.back()
          }, 1500)
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || '撤销失败',
            icon: 'none',
            duration: 2000,
          })
        })
    },
    // 修改申请
    editReq() {},
  },
}
</script>

<style scoped lang="scss">
$red: #eb3729;
.returnList {
  .colorRed {
    color: $red;
  }
  .bgRed {
    background-color: $red;
  }
  .data {
    width: 100%;
    height: 150rpx;
    line-height: 150rpx;
    padding-left: 30rpx;
    color: #fff;
  }
  .money {
    background-color: #fff;
    .top {
      padding: 20rpx 30rpx;
      font-size: 30rpx;
      display: flex;
      justify-content: space-between;
      border-bottom: 2rpx solid #f3f4f5;
    }
    .express {
      padding: 20rpx 30rpx;
      font-size: 26rpx;
      .title {
        color: #333333;
      }
      .info {
        color: #999999;
      }
    }
    .tips {
      height: 160rpx;
      padding: 20rpx 30rpx;
      color: #999999;
      border-bottom: 2rpx solid #f3f4f5;
      font-size: 26rpx;
      .title {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      .content {
        font-size: 20rpx;
      }
    }
    .bottom {
      padding: 20rpx 30rpx;
      display: flex;
      justify-content: flex-end;
      .btns {
        width: 160rpx;
        height: 58rpx;
        margin: 0 10rpx;
        font-size: 24rpx;
        text-align: center;
        line-height: 58rpx;
        border-radius: 180rpx;
      }
      .def {
        color: #dddddd;
        border: 2rpx solid #dddddd;
      }
      .redBtn {
        color: #fff;
        background-color: $red;
      }
    }
  }
  .rebackInfo {
    padding: 0 30rpx;
    background-color: #fff;
    .top {
      height: 80rpx;
      margin: 20rpx 0;
      line-height: 80rpx;
      font-size: 30rpx;
      font-weight: bold;
      color: #333333;
      border-bottom: 2rpx solid #f3f4f5;
    }
    .rebackItem {
      padding: 20rpx 0;
      font-size: 28rpx;
      display: flex;
      border-bottom: 2rpx solid #f3f4f5;
      .img {
        width: 120rpx;
        height: 120rpx;
      }
      .info {
        margin-left: 16rpx;
        flex: 1;
        .productName {
          width: 100%;
          height: 80rpx;
          overflow: hidden;
          text-overflow: ellipsis;
          color: #333333;
        }
        .sku {
          font-size: 24rpx;
          color: #cccccc;
        }
      }
    }
    .bottom {
      padding: 20rpx 0;
      .reason {
        font-size: 28rpx;
        color: #333333;
        display: flex;
        justify-content: space-between;
        .right {
          color: #aaaaaa;
        }
      }
    }
  }
}
</style>
