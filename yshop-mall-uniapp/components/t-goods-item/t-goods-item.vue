<template>
  <view class="tui-goods__item" :class="{ 'tui-full__item': isList }">
    <view class="tui-image__box" :class="{ 'tui-full__imgbox': isList }">
      <image class="tui-goods__img" :class="{ 'tui-full__img': isList }" :src="item.image" mode="widthFix"></image>
    </view>
    <view class="tui-goods__content" :class="{ 'tui-full__content': isList }">
      <view class="tui-goods__title">{{ item.title || '' }}</view>
      <view class="progress cart-color">
        <view class="bg-red" :style="{ width: loading ? item.percent + '%' : '' }"></view>
        <view class="piece font-color-red" v-text="'仅剩' + item.stock + '件'"></view>
      </view>
      <view class="tui-tag__box"><tui-tag plain size="24rpx" type="red" padding="8rpx 12rpx">限时价</tui-tag></view>
      <view class="tui-box__bottom">
        <view class="tui-price__box">
          <view class="tui-price">
            <view class="tui-price__small">￥</view>
            <view class="tui-price__large">{{ item.price || '' }}</view>
            <!-- <view class="tui-price__small">.{{ decimalPrice }}</view> -->
          </view>
          <!-- <view class="tui-price__original">￥{{ item.factory || '0.00' }}</view> -->
        </view>
        <view>
          <!-- <tui-button :width="status == 3 ? '146rpx' : '144rpx'" :height="status == 3 ? '60rpx' : '50rpx'" :size="status == 3 ? 26 : 24" :type="status == 1 ? 'gray' : 'danger'" :disabled="status == 1" :plain="status == 3">
            {{ status | getBtnText(item.subscribe) }}
          </tui-button> -->
          <view class="grab bg-color-red" v-if="timeList[active].status === 1 && item.stock > 0" @click="goDetail">马上抢</view>
          <view class="grab" v-if="timeList[active].status === 1 && item.stock <= 0">已售磬</view>
          <view class="grab bg-color-red" v-if="timeList[active].status === 2" >即将开始</view>
          <view class="grab bg-color-red" v-if="timeList[active].status === 0" >已结束</view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  name: 'tGoodsItem',
  props: {
    item: {
      type: Object,
      default() {
        return {}
      },
    },
    //是否为列表展示
    isList: {
      type: Boolean,
      default: false,
    },
    //status：1-已结束，2-正在进行，3-即将开枪
    status: {
      type: Number,
      default: 2,
    },
    timeList: {
      type: Array,
      default: [],
    },
    active: {
      type: Number,
      default: 0,
    },
  },
  filters: {
    getBtnText(status, subscribe) {
      status = status || 1
      let text = ['活动已结束', '立即抢购', '立即预约'][status - 1]
      if (status == 3 && subscribe) {
        text = '取消预约'
      }
      return text
    },
  },
  computed: {
    integerPrice: function() {
      let price = this.item.sale || '0.00'
      if (~price.indexOf('.')) {
        return price.split('.')[0]
      }
      return price
    },
    decimalPrice: function() {
      let price = this.item.sale || '0.00'
      if (~price.indexOf('.')) {
        return price.split('.')[1]
      }
      return '00'
    },
  },
  data() {
    return {}
  },
  methods: {
    goDetail: function() {
      this.$emit('goDetail', this.item)
    },
  },
}
</script>

<style scoped>
.tui-goods__item {
  width: 100%;
  padding: 20rpx 20rpx 36rpx;
  box-sizing: border-box;
  border-radius: 12rpx;
  background-color: #fff;
  margin-bottom: 4%;
  position: relative;
}
.tui-full__item {
  display: flex;
  margin-bottom: 20rpx !important;
  padding: 20rpx !important;
}
.tui-img__newguest {
  position: absolute;
  width: 96rpx;
  height: 32rpx;
  left: 0;
  top: 8rpx;
}
.tui-image__box {
  width: 100%;
  height: 300rpx;
}
.tui-full__imgbox {
  width: 240rpx !important;
  height: 240rpx !important;
  margin-right: 20rpx;
}
.tui-goods__img {
  max-width: 100%;
  max-height: 300rpx;
  display: block;
  border-radius: 8rpx;
}
.tui-full__img {
  max-height: 240rpx !important;
}
.tui-goods__content {
  width: 100%;
  padding-top: 16rpx;
}
.tui-full__content {
  height: 240rpx;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-top: 0 !important;
}
.tui-goods__title {
  font-size: 26rpx;
  font-weight: 400;
  color: #333;
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  margin-bottom: 20rpx;
}
.tui-tag__box {
  display: flex;
  padding-top: 25rpx;
  padding-bottom: 25rpx;
}
.tui-box__bottom {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.tui-price__box {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
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
}
</style>
