<template>
  <view class="group-con">
    <view class="tui-goods-item" v-if="storeCombination">
      <image :src="storeCombination.image" class="tui-goods-img"></image>
      <view class="tui-goods-center">
        <view class="tui-goods-name">{{ storeCombination.title }}</view>
        <view class="tui-price__box">
          <view class="tui-goods-price">
            <view class="tui-size-24">￥</view>
            <view class="tui-price-large">{{ storeCombination.price.split('.')[0] || 0 }}</view>
            <view class="tui-size-24">.{{ storeCombination.price.split('.')[1] || 0 }}</view>
            <!-- <text>已拼2020件</text> -->
          </view>
          <view class="tui-price-tag">{{ storeCombination.people || 0 }}人团</view>
        </view>
      </view>
    </view>

    <view class="tui-group__box tui-mtop__20">
      <tui-divider backgroundColor="#fff" width="70%" gradual>
        <view class="tui-divider__content">
          <text v-if="pinkBool == 0">进行中</text>
          <text v-if="pinkBool == 1">拼团成功</text>
          <text v-if="pinkBool == -1">拼团失败</text>
        </view>
      </tui-divider>
      <view class="tui-group__title" v-if="pinkBool == 0">
        <text>还差</text>
        <text class="tui-color__red">{{ count }}</text>
        <text>人，赶快邀请好友来拼团吧</text>
      </view>
      <view class="tui-group-countdown" v-if="pinkBool == 0">
        <view class="tui-countdown-right">剩余</view>
        <count-down :isDay="true" :tipText="'倒计时 '" :dayText="' 天 '" :hourText="' 时 '" :minuteText="' 分 '" :secondText="' 秒'" :datatime="pinkT.stopTime"></count-down>
        <view class="tui-countdown-left">结束</view>
      </view>
      <view class="tui-user__box">
        <view class="tui-user__item">
          <view class="tui-avatar__box tui-size">
            <image class="tui-size" :src="pinkT.avatar"></image>
            <view class="tui-team__leader">团长</view>
          </view>
          <!-- <view class="tui-nickname">不许人间见白头</view> -->
        </view>
        <view class="tui-user__item" v-for="(item, index) in Array(storeCombination.people-1)" :key="index">
          <view class="tui-avatar__box tui-size" v-if="pinkAll[index]"><image class="tui-size" :src="pinkAll[index].avatar"></image></view>
          <view class="tui-avatar__box tui-user__none" v-else><image class="tui-size" :src="`${$VUE_APP_RESOURCES_URL}/images/vacancy.png`"></image></view>
          <!-- <view class="tui-nickname">小可爱本人</view> -->
        </view>
      </view>
      <view class="tui-btn__box">
        <tui-button type="danger" height="88rpx" shadow shape="circle" v-if="userBool === 1 && isOk == 0 && pinkBool === 0" @click="goPoster">邀请好友参团</tui-button>
        <tui-button type="warning" height="88rpx" shadow shape="circle" v-else-if="userBool === 0 && pinkBool === 0 && count > 0" @click="pay">我要参团</tui-button>
        <tui-button type="danger" height="88rpx" shadow shape="circle" v-if="pinkBool === 1 || pinkBool === -1" @click="goDetail(storeCombination.id)">再次开团</tui-button>
        <tui-button type="warning" height="88rpx" shadow shape="circle" @click="getCombinationRemove" v-if="userBool === 1 && pinkBool === 0">取消开团</tui-button>
        <tui-button type="danger" height="88rpx" shadow shape="circle" v-if="pinkBool === 1" @click="goOrder">查看订单信息</tui-button>
      </view>
    </view>
  </view>
</template>
<script>
import CountDown from '@/components/CountDown'
import { getCombinationPink, getCombinationRemove } from '@/api/activity'
import { postCartAdd } from '@/api/store'
import { isWeixin, parseQuery, handleQrCode } from '@/utils/index'

const NAME = 'GroupRule'
export default {
  name: NAME,
  components: {
    CountDown,
  },
  props: {},
  data: function() {
    return {
      currentPinkOrder: '', //当前拼团订单
      isOk: 0, //判断拼团是否完成
      pinkBool: 0, //判断拼团是否成功|0=失败,1=成功
      userBool: 0, //判断当前用户是否在团内|0=未在,1=在
      pinkAll: [], //团员
      pinkT: {}, //团长信息
      storeCombination: {}, //拼团产品
      pinkId: 0,
      uniqueId: '',
      count: 0, //拼团剩余人数
      iShidden: false,
      userInfo: {},
    }
  },
  watch: {
    $yroute(n) {
      var that = this
      if (n.name === NAME) {
        that.pinkId = that.$yroute.query.id
        that.getCombinationPink()
      }
    },
  },
  mounted() {
    let url = handleQrCode()
    if (url) {
      this.pinkId = url.pinkId
    } else {
      this.pinkId = this.$yroute.query.id
    }
    this.getCombinationPink()
  },
  methods: {
    pay: function() {
      var that = this
      var data = {}
      data.productId = that.storeCombination.productId
      data.cartNum = that.pinkT.totalNum
      data.uniqueId = that.uniqueId
      data.combinationId = that.storeCombination.id
      data.new = 1
      postCartAdd(data)
        .then(res => {
          that.$yrouter.push({
            path: '/pages/order/OrderSubmission/index',
            query: {
              id: res.data.cartId,
              pinkid: that.pinkId,
            },
          })
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    goPoster: function() {
      var that = this
      this.$yrouter.push({
        path: '/pages/activity/Poster/index',
        query: {
          id: that.pinkId,
          type: 1,
        },
      })
    },
    goOrder: function() {
      var that = this
      this.$yrouter.push({
        path: '/pages/order/OrderDetails/index',
        query: {
          id: that.currentPinkOrder,
        },
      })
    },
    //拼团列表
    goList: function() {
      this.$yrouter.push({
        path: '/pages/activity/GoodsGroup/index',
      })
    },
    //拼团详情
    goDetail: function(id) {
      this.$yrouter.push({
        path: '/pages/activity/GroupDetails/index',
        query: {
          id,
        },
      })
    },
    //拼团信息
    getCombinationPink: function() {
      var that = this
      getCombinationPink(that.pinkId).then(res => {
        that.$set(that, 'storeCombination', res.data.storeCombination)
        that.$set(that, 'pinkT', res.data.pinkT)
        that.$set(that, 'pinkAll', res.data.pinkAll)
        that.$set(that, 'count', res.data.count)
        that.$set(that, 'userBool', res.data.userBool)
        that.$set(that, 'pinkBool', res.data.pinkBool)
        that.$set(that, 'isOk', res.data.isOk)
        that.$set(that, 'currentPinkOrder', res.data.currentPinkOrder)
        that.$set(that, 'uniqueId', res.data.uniqueId)
        that.$set(that, 'userInfo', res.data.userInfo)
      })
    },
    //拼团取消
    getCombinationRemove: function() {
      var that = this
      getCombinationRemove({
        id: that.pinkId,
        cid: that.storeCombination.id,
      })
        .then(res => {
          uni.showToast({
            title: res.msg,
            icon: 'none',
            duration: 2000,
          })
        })
        .catch(res => {
          uni.showToast({
            title: res.msg,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    lookAll: function() {
      this.iShidden = !this.iShidden
    },
  },
}
</script>

<style lang="less">
.tips-warp {
  text-align: center;
  margin-top: 20rpx;
}

.tui-goods-item {
  width: 100%;
  padding: 20rpx 25rpx;
  box-sizing: border-box;
  display: flex;
  justify-content: space-between;
  background-color: #fff;
}

.tui-goods-img {
  width: 180rpx;
  height: 180rpx;
  display: block;
  flex-shrink: 0;
}

.tui-goods-center {
  flex: 1;
  padding: 12rpx 12rpx;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.tui-goods-name {
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  font-size: 26rpx;
  line-height: 32rpx;
}

.tui-price__box {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.tui-goods-price {
  width: 100%;
  display: flex;
  align-items: flex-end;
  font-size: 24rpx;
  color: #eb0909;
}
.tui-goods-price text {
  font-size: 24rpx;
  line-height: 24rpx;
  color: #999;
  padding-left: 20rpx;
}

.tui-size-24 {
  font-size: 24rpx;
  line-height: 24rpx;
}

.tui-price-large {
  font-size: 32rpx;
  line-height: 30rpx;
  font-weight: 500;
}

.tui-price-tag {
  height: 38rpx;
  border: 1rpx solid #eb0909;
  border-radius: 6rpx;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24rpx;
  line-height: 24rpx;
  transform: scale(0.8);
  transform-origin: 100% center;
  border-radius: 6rpx;
  padding: 0 8rpx;
  color: #eb0909;
  flex-shrink: 0;
}
.tui-mtop__20 {
  margin-top: 20rpx;
}
.tui-divider__content {
  display: flex;
  align-items: center;
}
.tui-divider__content image {
  width: 36rpx;
  height: 36rpx;
  margin-right: 20rpx;
}
.tui-divider__content text {
  font-size: 34rpx;
  line-height: 34rpx;
  color: #000000;
  font-weight: bold;
}
.tui-group__time {
  font-size: 24rpx;
  font-weight: 400;
  color: #999;
  text-align: center;
}
.tui-group__box {
  width: 100%;
  padding: 50rpx 25rpx;
  box-sizing: border-box;
  background-color: #fff;
}
.tui-group__title {
  width: 100%;
  font-size: 34rpx;
  line-height: 34rpx;
  font-weight: 500;
  text-align: center;
}
.tui-color__red {
  color: #eb0909;
}
.tui-group-countdown {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24rpx;
  color: #666666;
  padding-top: 20rpx;
}

.tui-countdown-right {
  padding-right: 6rpx;
}

.tui-countdown-left {
  padding-left: 6rpx;
}
.tui-user__box {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-top: 60rpx;
}
.tui-user__item {
  max-width: 128rpx;
  margin: 0 40rpx;
}
.tui-size {
  width: 100rpx;
  height: 100rpx;
  border-radius: 50%;
}
.tui-avatar__box {
  position: relative;
  border: 4rpx solid #eb0909;
}
.tui-user__none {
  width: 108rpx;
  height: 108rpx;
  border: 0;
}
.tui-avatar__box image {
  display: block;
  width: 100%;
  height: 100%;
}
.tui-team__leader {
  position: absolute;
  width: 64rpx;
  height: 28rpx;
  font-size: 24rpx;
  background-color: #eb0909;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 30px;
  left: 50%;
  top: -14rpx;
  transform: translateX(-50%);
  z-index: 10;
}
.tui-nickname {
  font-size: 24rpx;
  color: #999;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: center;
  padding-top: 12rpx;
}
.tui-btn__box {
  padding-top: 60rpx;
}

.tni-cell {
  height: 20rpx;
}

.tui-group__text {
  width: 100%;
  display: flex;
  align-items: center;
}

.tui-group-title {
  font-size: 30rpx;
  line-height: 30rpx;
  padding-left: 16rpx;
  border-left: 2px solid #eb0909;
  box-sizing: border-box;
}

.tui-sub__info {
  font-size: 26rpx;
  padding-right: 30rpx;
}

.tui-group__start .tui-group-title {
  border-left: 0;
  padding-left: 0;
}
.tui-group__start .tui-sub__info {
  padding-right: 0;
}
.tui-step__box {
  width: 100%;
  height: 210rpx;
  background: #fff;
  padding: 0 60rpx;
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-sizing: border-box;
}

.tui-step-item {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  font-size: 26rpx;
  color: #666666;
}

.tui-step-item image {
  width: 64rpx;
  height: 55rpx;
  flex-shrink: 0;
}

.tui-step-item image:first-child {
  width: 60rpx !important;
}

.tui-step-arrow {
  height: 90rpx;
}

.tui-step-arrow image {
  width: 11rpx;
  height: 20rpx;
  flex-shrink: 0;
}

.tui-step-text {
  line-height: 26rpx;
  padding-top: 24rpx;
}
.tui-between {
  justify-content: space-between;
}
.tui-btn__box .tui-btn {
  margin-bottom: 30rpx !important;
}
/*拼团玩法介绍 modal*/
.tui-modal__title {
  text-align: center;
  font-weight: bold;
  padding-bottom: 8rpx;
}
.tui-modal__p {
  font-size: 26rpx;
  color: #888;
  padding-top: 20rpx;
}
.tui-modal__btn {
  width: 100%;
  padding: 60rpx 0 20rpx;
  display: flex;
  justify-content: center;
}
.tui-hot__title {
  font-size: 30rpx;
  line-height: 30rpx;
  color: #333;
  font-weight: bold;
}

/*========商品 start======*/

.tui-product__box {
  width: 100%;
  padding: 0 25rpx 60rpx 25rpx;
  box-sizing: border-box;
}

.tui-product-list {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  flex-wrap: wrap;
  box-sizing: border-box;
}

.tui-product-container {
  flex: 1;
  margin-right: 10rpx;
}

.tui-product-container:last-child {
  margin-right: 0;
}

.tui-pro-item {
  width: 100%;
  margin-bottom: 10rpx;
  background: #fff;
  box-sizing: border-box;
  border-radius: 12rpx;
  overflow: hidden;
  transition: all 0.15s ease-in-out;
}

.tui-flex-list {
  display: flex;
  margin-bottom: 1rpx !important;
}

.tui-pro-img {
  width: 100%;
  display: block;
  flex-shrink: 0;
  background-color: #f5f5f5;
}

.tui-proimg-list {
  width: 280rpx;
  height: 280rpx !important;
  flex-shrink: 0;
  border-radius: 12rpx;
}

.tui-pro-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 20rpx;
}

.tui-pro-tit {
  color: #2e2e2e;
  font-size: 26rpx;
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
}

.tui-price__box {
  width: 100%;
  display: flex;
  align-items: center;
  padding-top: 12rpx;
}

.tui-price {
  display: flex;
  align-items: flex-end;
  color: #eb0909;
}

.tui-price__small {
  font-size: 24rpx;
  line-height: 24rpx;
}

.tui-price__large {
  font-size: 34rpx;
  line-height: 32rpx;
  font-weight: 600;
}

.tui-price__original {
  font-size: 24rpx;
  line-height: 24rpx;
  text-decoration: line-through;
  color: #999;
  padding-top: 10rpx;
  padding-left: 12rpx;
}

.tui-group-btn {
  max-width: 312rpx;
  height: 48rpx;
  border-radius: 6rpx;
  background: #eb0909;
  display: flex;
  align-items: center;
  padding: 4rpx;
  margin-top: 10rpx;
  box-sizing: border-box;
}

.tui-flex-btn {
  height: 100%;
  flex: 1;
  text-align: center;
  font-size: 26rpx;
  line-height: 26rpx;
  font-weight: 400;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
}

.tui-flex-btn:first-child {
  background: #fff;
}

.tui-group-text {
  font-size: 25rpx;
  line-height: 25rpx;
  transform: scale(0.8);
  transform-origin: 0 center;
  padding-top: 30rpx;
  color: #999;
}

.tui-color-red {
  color: #eb0909;
}

/*======商品======= end*/
</style>
