<template>
  <view v-show="iShidden === false">
    <view class="WriteOff">
      <view class="num acea-row row-center-wrapper">
        {{ orderInfo.orderId }}
      </view>
      <view class="tip">确定要核销此订单吗？</view>
      <view class="sure" @click="confirm">确定核销</view>
      <view class="sure cancel" @click="cancel">取消</view>
    </view>
    <view class="mask" @touchmove.prevent></view>
  </view>
</template>
<style scoped lang="less">
.WriteOff {
  width: 5.6*100rpx;
  height: 5*100rpx;
  background-color: #fff;
  border-radius: 0.2*100rpx;
  position: fixed;
  top: 50%;
  left: 50%;
  margin-top: -4*100rpx;
  margin-left: -2.8*100rpx;
  padding-top: 0.55*100rpx;
  z-index: 99999;
}
.WriteOff .pictrue {
  width: 3.4*100rpx;
  height: 3.4*100rpx;
  margin: 0 auto;
}
.WriteOff .pictrue image{
  width: 100%;
  height: 100%;
  display: block;
  border-radius: 0.1*100rpx;
}
.WriteOff .num {
  font-size: 0.3*100rpx;
  color: #666;
  margin: 0.28*100rpx 0 0.3*100rpx 0;
}
.WriteOff .num .see {
  font-size: 0.16*100rpx;
  color: #fff;
  border-radius: 0.04*100rpx;
  background-color: #c68937;
  padding-left: 0.05*100rpx;
  margin-left: 0.12*100rpx;
}
.WriteOff .num .see .iconfont {
  font-size: 0.15*100rpx;
}
.WriteOff .tip {
  font-size: 0.36*100rpx;
  color: #282828;
  text-align: center;
  border-top: 1px dashed #ccc;
  padding-top: 0.4*100rpx;
  position: relative;
}
.WriteOff .tip:after {
  content: "";
  position: absolute;
  width: 0.25*100rpx;
  height: 0.25*100rpx;
  border-radius: 50%;
  background-color: #7f7f7f;
  right: -0.125*100rpx;
  top: -0.125*100rpx;
}
.WriteOff .tip:before {
  content: "";
  position: absolute;
  width: 0.25*100rpx;
  height: 0.25*100rpx;
  border-radius: 50%;
  background-color: #7f7f7f;
  left: -0.125*100rpx;
  top: -0.125*100rpx;
}
.WriteOff .sure {
  font-size: 0.32*100rpx;
  color: #fff;
  text-align: center;
  line-height: 0.82*100rpx;
  height: 0.82*100rpx;
  width: 4.6*100rpx;
  border-radius: 0.41*100rpx;
  margin: 0.4*100rpx auto 0 auto;
  background-image: linear-gradient(to right, #f67a38 0%, #f11b09 100%);
  background-image: -webkit-linear-gradient(to right, #f67a38 0%, #f11b09 100%);
  background-image: -moz-linear-gradient(to right, #f67a38 0%, #f11b09 100%);
}
.WriteOff .sure.cancel {
  background-image: none;
  color: #999;
  margin-top: 0.1*100rpx;
}
</style>
<script>
export default {
  name: "WriteOff",
  props: {
    iShidden: {
      type: Boolean,
      default: true
    },
    orderInfo: {
      type: Object
    }
  },
  data: function() {
    return {};
  },
  mounted: function() {},
  methods: {
    cancel: function() {
      this.$emit("cancel", true);
    },
    confirm: function() {
      this.$emit("confirm", true);
    }
  }
};
</script>
