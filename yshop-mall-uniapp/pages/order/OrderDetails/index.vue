<template>
  <view class="order-details">
    <!-- 给header上与data上加on为退款订单-->
    <view class="header bg-color-red acea-row row-middle" :class="refundOrder ? 'on' : ''">
      <view class="data" :class="refundOrder ? 'on' : ''">
        <view class="state">{{ orderInfo._status._msg }}</view>
        <view>{{ orderInfo.createTime ||  '' }}</view>
      </view>
    </view>
    <template v-if="!refundOrder">
      <view class="nav">
        <view class="navCon acea-row row-between-wrapper">
          <view :class="{ on: status.type === 0 || status.type === 9 }">待付款</view>
          <view :class="{ on: status.type === 1 }" v-if="orderInfo.shippingType === 2">待核销</view>
          <view :class="{ on: status.type === 1 }" v-else>待发货</view>
          <view :class="{ on: status.type === 2 }" v-if="orderInfo.shippingType === 1">待收货</view>
          <view :class="{ on: status.type === 3 }">待评价</view>
          <view :class="{ on: status.type === 4 }">已完成</view>
        </view>
        <view class="progress acea-row row-between-wrapper">
          <view class="iconfont" :class="[
              status.type === 0 || status.type === 9
                ? 'icon-webicon318'
                : 'icon-yuandianxiao',
              status.type >= 0 ? 'font-color-red' : ''
            ]"></view>
          <view class="line" :class="{ 
			  'bg-color-red': status.type > 0 && status.type != 9 
		  }"></view>
          <view class="iconfont" :class="[
              status.type === 1 ? 'icon-webicon318' : 'icon-yuandianxiao',
              status.type >= 1 && status.type != 6 && status.type != 9
                ? 'font-color-red'
                : ''
            ]"></view>
          <view class="line" :class="{
			  'bg-color-red':status.type > 1 && status.type != 6 && status.type != 9
			}"
            v-if="orderInfo.shippingType === 1"></view>
          <view class="iconfont"
            :class="[status.type === 2 ? 'icon-webicon318' : 'icon-yuandianxiao',status.type >= 2 && status.type != 6 && status.type != 9? 'font-color-red': '']"
            v-if="orderInfo.shippingType === 1"></view>
          <view class="line" :class="{
              'bg-color-red':
                status.type > 2 && status.type != 6 && status.type != 9
            }"></view>
          <view class="iconfont" :class="[
              status.type === 3 ? 'icon-webicon318' : 'icon-yuandianxiao',
              status.type >= 3 && status.type != 6 && status.type != 9
                ? 'font-color-red'
                : ''
            ]"></view>
          <view class="line" :class="{
              'bg-color-red': status.type > 3 && status.type != 6 && status.type != 9
            }"></view>
          <view class="iconfont" :class="[
              status.type == 4 ? 'icon-webicon318' : 'icon-yuandianxiao',
              status.type >= 4 && status.type != 6 && status.type != 9
                ? 'font-color-red'
                : ''
            ]"></view>
        </view>
      </view>
      <div class="writeOff" v-if="orderInfo.shippingType === 2 && orderInfo.paid === 1">
        <div class="title">核销信息</div>
        <div class="grayBg">
          <div class="pictrue">
            <img :src="orderInfo.code" />
          </div>
        </div>
        <div class="gear">
          <img :src="`${$VUE_APP_RESOURCES_URL}/images/writeOff.jpg`" />
        </div>
        <div class="num">{{ orderInfo.verifyCode }}</div>
        <div class="rules">
          <div class="item">
            <div class="rulesTitle acea-row row-middle">
              <span class="iconfont icon-shijian"></span>核销时间
            </div>
            <div class="info">
              每日：
              <span class="time">{{ system_store.dayTime }}</span>
            </div>
          </div>
          <div class="item">
            <div class="rulesTitle acea-row row-middle">
              <span class="iconfont icon-shuoming1"></span>使用说明
            </div>
            <div class="info">可将二维码出示给店员扫描或提供数字核销码</div>
          </div>
        </div>
      </div>
      <div class="map acea-row row-between-wrapper" v-if="orderInfo.shippingType === 2 && orderInfo.paid === 1">
        <div>自提地址信息</div>
        <div class="place cart-color acea-row row-center-wrapper" @click="showChang(orderInfo.systemStore)">
          <span class="iconfont icon-weizhi"></span>查看位置
        </div>
      </div>
      <view class="address" v-if="orderInfo.shippingType === 1">
        <view class="name">
          {{ orderInfo.realName }}
          <text class="phone">{{ orderInfo.userPhone }}</text>
          <text
			  @click="telPhone(orderInfo.userPhone)"
			  class="iconfont icon-tonghua font-color-red"
		  ></text>
        </view>
        <view>{{ orderInfo.userAddress }}</view>
      </view>
      <div class="address" v-else>
        <div class="name">
          {{ system_store.name}}
          <span class="phone">{{ system_store.phone }}</span>
          <span @click="telPhone(system_store.phone)"
			class="iconfont icon-tonghua font-color-red"
			:href="'tel:' + system_store.phone"></span>
        </div>
        <div>{{ system_store.address }}</div>
      </div>
      <view class="line" v-if="orderInfo.shippingType === 1">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/line.jpg`" />
      </view>
    </template>
    <OrderGoods
		:evaluate="status.type || 0"
		:isIntegral="isIntegral"
		:cartInfo="orderInfo.cartInfo || []"
	></OrderGoods>
    <view class="wrapper">
      <view class="item acea-row row-between">
        <view>订单编号：</view>
        <view class="conter acea-row row-middle row-right">
          {{ orderInfo.orderId || '' }}
          <!-- #ifndef H5 -->
		  <text class="copy copy-data" @click="copyClipboard(orderInfo.orderId)">复制</text>
		  <!-- #endif -->
        </view>
      </view>
      <view class="item acea-row row-between">
        <view>下单时间：</view>
        <view class="conter">{{ orderInfo.createTime || '' }}</view>
      </view>
      <view class="item acea-row row-between">
        <view>订单类型：</view>
        <view class="conter">{{ orderTypeName || '' }}</view>
      </view>
      <view class="item acea-row row-between">
        <view>支付状态：</view>
        <view class="conter">{{ orderInfo.paid ? "已支付" : "未支付" }}</view>
      </view>
      <view class="item acea-row row-between">
        <view>支付方式：</view>
        <view class="conter">{{ orderInfo._status._payType || '' }}</view>
      </view>
      <view class="item acea-row row-between" v-if="orderInfo.mark">
        <view>买家留言：</view>
        <view class="conter">{{ orderInfo.mark || '' }}</view>
      </view>
    </view>

    <view v-if="orderInfo.status != 0">
      <view class="wrapper" v-if="orderInfo.deliveryType === 'express'">
        <view class="item acea-row row-between">
          <view>配送方式：</view>
          <view class="conter">发货</view>
        </view>
        <view class="item acea-row row-between">
          <view>快递公司：</view>
          <view class="conter">{{ orderInfo.deliveryName || "" }}</view>
        </view>
        <view class="item acea-row row-between">
          <view>快递号：</view>
          <view class="conter">{{ orderInfo.deliveryId || "" }}</view>
        </view>
      </view>

      <view class="wrapper" v-else>
        <view class="item acea-row row-between">
          <view>配送方式：</view>
          <view class="conter">送货</view>
        </view>
        <view class="item acea-row row-between">
          <view>配送人：</view>
          <view class="conter">{{ orderInfo.deliveryName || "" }}</view>
        </view>
        <view class="item acea-row row-between">
          <view>配送电话：</view>
          <view class="conter acea-row row-middle row-right">
            {{ orderInfo.deliveryId || "" }}
            <text class="copy">拨打</text>
          </view>
        </view>
      </view>
    </view>
    <!--     退款订单详情 -->
    <view class="wrapper" v-if="refundOrder">
      <view class="item acea-row row-between">
        <view>收货人：</view>
        <view class="conter">{{ orderInfo.realName || '' }}</view>
      </view>
      <view class="item acea-row row-between">
        <view>联系电话：</view>
        <view class="conter">{{ orderInfo.userPhone || '' }}</view>
      </view>
      <view class="item acea-row row-between">
        <view>收货地址：</view>
        <view class="conter">{{ orderInfo.userAddress || '' }}</view>
      </view>
    </view>
    <view class="wrapper">
      <view class="item acea-row row-between" v-if="!isIntegral">
        <view>支付金额：</view>
        <view class="conter">￥{{ orderInfo.totalPrice || 0 }}</view>
      </view>
      <view class="item acea-row row-between" v-if="isIntegral">
        <view>支付积分：</view>
        <view class="conter">{{ orderInfo.payIntegral || 0 }}积分</view>
      </view>
      <view class="item acea-row row-between" v-if="orderInfo.couponPrice > 0">
        <view>优惠券抵扣：</view>
        <view class="conter">-￥{{ orderInfo.couponPrice ||0 }}</view>
      </view>
      <view class="item acea-row row-between" v-if="orderInfo.useIntegral > 0">
        <view>积分抵扣：</view>
        <view class="conter">-￥{{ orderInfo.deductionPrice || 0 }}</view>
      </view>
      <view class="item acea-row row-between" v-if="orderInfo.payPostage > 0">
        <view>运费：</view>
        <view class="conter">￥{{ orderInfo.payPostage || 0 }}</view>
      </view>
      <view class="actualPay acea-row row-right">
        实付款：
        <text class="money font-color-red" v-if="!isIntegral">￥{{ orderInfo.payPrice || 0 }}</text>
        <text class="money font-color-red" v-if="isIntegral">{{ orderInfo.payIntegral || 0 }}积分</text>
      </view>
    </view>
    <view style="height:100rpx;" v-if="!refundOrder && offlineStatus"></view>
    <view class="footer acea-row row-right row-middle" v-if="!refundOrder && offlineStatus">
      <template v-if="status.type == 0">
        <view class="bnt cancel" @click="cancelOrder">取消订单</view>
        <view class="bnt bg-color-red" @click="pay = true">立即付款</view>
      </template>
      <template v-if="orderInfo.refundStatus === 0 && orderInfo.paid === 1">
        <view class="bnt cancel" @click="goGoodsReturn(orderInfo)">申请售后</view>
      </template>
      <!--  -->
      <template v-if="orderInfo.shippingType == 1 && status.type == 2">
        <view class="bnt default"
          @click="$yrouter.push({
			  path: '/pages/order/Logistics/index',
			  query:{
				  id:orderInfo.orderId
			  }
		  })"
		>查看物流</view>
        <view class="bnt bg-color-red" @click="takeOrder">确认收货</view>
      </template>
      <template v-if="orderInfo.shippingType == 1 && status.type == 3 && orderInfo.deliveryType == 'express'">
        <view class="bnt default"
          @click="$yrouter.push({ path: '/pages/order/Logistics/index' ,query:{id:orderInfo.orderId }})">查看物流</view>
      </template>
      <template v-if="orderInfo.shippingType == 1 && status.type == 4">
        <view class="bnt cancel" @click="delOrder">删除订单</view>
        <view class="bnt default" @click="
        $yrouter.push({ path: '/pages/order/Logistics/index' ,query:{id:orderInfo.orderId }})
        ">查看物流</view>
      </template>
      <template v-if="status.type == 6">
        <view class="bnt bg-color-red" @click="goGroupRule(orderInfo)">查看拼团</view>
      </template>
    </view>
    <Payment
		v-model="pay"
		:types="payType"
		@checked="toPay"
		:balance="userInfo.nowMoney"
	></Payment>
    <view class="geoPage" v-if="mapShow">
      <iframe
		width="100%"
		height="100%"
		frameborder="0"
		scrolling="no"
		:src="'https://apis.map.qq.com/uri/v1/geocoder?coord=' + system_store.latitude + ',' +system_store.longitude +'&referer=' +mapKey"
		></iframe>
    </view>
  </view>
</template>

<script>
  import OrderGoods from "@/components/OrderGoods";
  import {
    orderDetail
  } from "@/api/order";
  import Payment from "@/components/Payment";
  import {
    isWeixin,
    copyClipboard
  } from "@/utils";
  import {
    mapGetters
  } from "vuex";
  import {
    cancelOrderHandle,
    takeOrderHandle,
    delOrderHandle,
    payOrderHandle,
  } from "@/libs/order";
  // import { wechatEvevt } from "@/libs/wechat";

  const NAME = "OrderDetails";

  export default {
    name: NAME,
    components: {
      OrderGoods,
      Payment,
    },
    data: function () {
      return {
        offlinePayStatus: 2,
        orderTypeName: "普通订单",
        orderTypeNameStatus: true,
        offlineStatus: true,
        id: "",
        orderInfo: {
          _status: {},
        },
        status: {},
        pay: false,
        payType: ["yue", "weixin"],
        from: this.$deviceType,
        system_store: {},
        mapKay: "",
        mapShow: false,
        isIntegral: false
      };
    },
    computed: {
      refundOrder() {
        return this.orderInfo.refund_status > 0;
      },
      ...mapGetters(["userInfo"]),
    },
    onShow() {
      this.id = this.$yroute.query.id;
      this.getDetail();
    },
    mounted: function () {
      this.id = this.$yroute.query.id;
      // this.getDetail();
    },
    methods: {
      copyClipboard,
	  // 申请退款
      goGoodsReturn(orderInfo) {
        this.$yrouter.push({
          path: "/pages/order/GoodsReturn/index",
          query: {
            id: orderInfo.orderId,
          },
        });
      },
      goGroupRule(orderInfo) {
        this.$yrouter.push({
          path: "/pages/activity/GroupRule/index",
          query: {
            id: orderInfo.pinkId,
          },
        });
      },
      //拨打电话
      telPhone(phoneNumber) {
        uni.makePhoneCall({
          phoneNumber: phoneNumber,
          fail() {
            console.log("取消拨打");
          }
        });
      },
      showChang: function (data) {
        // 这里判断是不是微信小程序
        this.$yrouter.push({
          path: "/pages/map/index",
          query: data,
        });
        // if (isWeixin()) {
        //   let config = {
        //     latitude: this.system_store.latitude,
        //     longitude: this.system_store.longitude,
        //     name: this.system_store.name,
        //     address: this.system_store.address
        //   };
        // } else {
        //   if (!this.mapKey)
        //     uni.showToast({
        //       title: "暂无法使用查看地图，请配置您的腾讯地图key",
        //       icon: "none",
        //       duration: 2000
        //     });
        //   return;
        //   this.mapShow = true;
        // }
      },
      goBack() {
        if (this.name === "MyOrder") {
          this.$yrouter.back();
          return;
        } else {
          console.log(this);
          this.$yrouter.replace({
            path: "/pages/order/MyOrder/index",
          });
          return;
        }
      },
      cancelOrder() {
        cancelOrderHandle(this.orderInfo.orderId)
          .then(() => {
            setTimeout(() => this.goBack(), 300);
          })
          .catch(() => {
            this.getDetail();
          });
      },
      takeOrder() {
        takeOrderHandle(this.orderInfo.orderId).finally(() => {
          this.getDetail();
        });
      },
      delOrder() {
        delOrderHandle(this.orderInfo.orderId).then(() => {
          setTimeout(() => this.goBack(), 300);
        });
      },
      setOfflinePayStatus: function (status) {
        var that = this;
        that.offlinePayStatus = status;
        if (status === 1 && that.orderTypeNameStatus === true) {
          that.payType.push("offline");
        }
      },
      getOrderStatus: function () {
        let orderInfo = this.orderInfo || {},
          _status = orderInfo._status || {
            _type: 0
          },
          status = {};
        let type = parseInt(_status._type),
          delivery_type = orderInfo.deliveryType,
          seckill_id = orderInfo.seckillId ? parseInt(orderInfo.seckillId) : 0,
          combination_id = orderInfo.combinationId ?
          parseInt(orderInfo.combinationId) :
          0;
        status = {
          type: type,
          class_status: 0,
        };
        if (type == 1 && combination_id > 0) {
          status.type = 6;
          status.class_status = 1;
        } //查看拼团
        if (type == 2 && delivery_type == "express") status.class_status = 2; //查看物流
        if (type == 2) status.class_status = 3; //确认收货
        if (type == 4 || type === 0) status.class_status = 4; //删除订单
        if (
          !seckill_id &&
          !combination_id &&
          (type == 3 || type == 4)
        )
          status.class_status = 5; //再次购买
        if (type == 9) {
          //线下付款
          status.class_status = 0;
          this.offlineStatus = false;
        }
        this.status = status;
      },
      getDetail() {
        const id = this.id;
        if (!id) {
          uni.showToast({
            title: "订单不存在",
            icon: "none",
            duration: 2000,
          });
          return;
        }
        orderDetail(id)
          .then((res) => {
            this.orderInfo = res.data;
            this.getOrderStatus();
            if (this.orderInfo.combinationId > 0) {
              this.orderTypeName = "拼团订单";
              this.orderTypeNameStatus = false;
            }else if (this.orderInfo.seckillId > 0) {
              this.orderTypeName = "秒杀订单";
              this.orderTypeNameStatus = false;
            }
            this.isIntegral = res.data.payType == 'integral'
            if (this.isIntegral) {
              this.orderTypeName = "积分兑换订单";
              this.orderTypeNameStatus = false;
            }

            this.system_store = res.data.systemStore || {};
            this.mapKey = res.data.mapKay;
            this.setOfflinePayStatus(this.orderInfo.offlinePayStatus);
          })
          .catch((err) => {
            uni.showToast({
              title: err.response.data.msg,
              icon: "none",
              duration: 2000,
            });
          });
      },
      async toPay(type) {
        var that = this;
        await payOrderHandle(this.orderInfo.orderId, type, that.from);
        that.getDetail();
      },
    },
  };
</script>

<style scoped lang="less">
  .geoPage {
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    z-index: 10000;
  }

  .order-details .writeOff {
    background-color: #fff;
    margin-top: 0.13 * 100rpx;
    padding-bottom: 0.3 * 100rpx;
  }

  .order-details .writeOff .title {
    font-size: 0.3 * 100rpx;
    color: #282828;
    height: 0.87 * 100rpx;
    border-bottom: 1px solid #f0f0f0;
    padding: 0 0.3 * 100rpx;
    line-height: 0.87 * 100rpx;
  }

  .order-details .writeOff .grayBg {
    background-color: #f2f5f7;
    width: 5.9 * 100rpx;
    height: 3.84 * 100rpx;
    border-radius: 0.2 * 100rpx 0.2 * 100rpx 0 0;
    margin: 0.5 * 100rpx auto 0 auto;
    padding-top: 0.55 * 100rpx;
  }

  .order-details .writeOff .grayBg .pictrue {
    width: 2.9 * 100rpx;
    height: 2.9 * 100rpx;
    margin: 0 auto;
  }

  .order-details .writeOff .grayBg .pictrue img {
    width: 100%;
    height: 100%;
    display: block;
  }

  .order-details .writeOff .gear {
    width: 5.9 * 100rpx;
    height: 0.3 * 100rpx;
    margin: 0 auto;
  }

  .order-details .writeOff .gear img {
    width: 100%;
    height: 100%;
    display: block;
  }

  .order-details .writeOff .num {
    background-color: #f0c34c;
    width: 5.9 * 100rpx;
    height: 0.84 * 100rpx;
    color: #282828;
    font-size: 0.48 * 100rpx;
    margin: 0 auto;
    border-radius: 0 0 0.2 * 100rpx 0.2 * 100rpx;
    text-align: center;
    padding-top: 0.04 * 100rpx;
  }

  .order-details .writeOff .rules {
    margin: 0.46 * 100rpx 0.3 * 100rpx 0 0.3 * 100rpx;
    border-top: 0.01 * 100rpx solid #f0f0f0;
    padding-top: 0.1 * 100rpx;
  }

  .order-details .writeOff .rules .item {
    margin-top: 0.15 * 100rpx;
  }

  .order-details .writeOff .rules .item .rulesTitle {
    font-size: 0.28 * 100rpx;
    color: #282828;
  }

  .order-details .writeOff .rules .item .rulesTitle .iconfont {
    font-size: 0.3 * 100rpx;
    color: #333;
    margin-right: 0.08 * 100rpx;
    margin-top: 0.05 * 100rpx;
  }

  .order-details .writeOff .rules .item .info {
    font-size: 0.28 * 100rpx;
    color: #999;
    margin-top: 0.05 * 100rpx;
  }

  .order-details .writeOff .rules .item .info .time {
    margin-left: 0.2 * 100rpx;
  }

  .order-details .map {
    height: 0.86 * 100rpx;
    font-size: 0.3 * 100rpx;
    color: #282828;
    line-height: 0.86 * 100rpx;
    border-bottom: 0.01 * 100rpx solid #f0f0f0;
    margin-top: 0.13 * 100rpx;
    background-color: #fff;
    padding: 0 0.3 * 100rpx;
  }

  .order-details .map .place {
    font-size: 0.26 * 100rpx;
    width: 1.76 * 100rpx;
    height: 0.5 * 100rpx;
    border-radius: 0.25 * 100rpx;
    line-height: 0.5 * 100rpx;
    text-align: center;
  }

  .order-details .map .place .iconfont {
    font-size: 0.27 * 100rpx;
    height: 0.27 * 100rpx;
    line-height: 0.27 * 100rpx;
    margin: 0.02 * 100rpx 0.03 * 100rpx 0 0;
  }

  .order-details .address .name .iconfont {
    font-size: 0.34 * 100rpx;
    margin-left: 0.1 * 100rpx;
  }
</style>
