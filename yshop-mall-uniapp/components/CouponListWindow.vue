<template>
  <view>
    <view class="coupon-list-window" :class="value === true ? 'on' : ''">
      <view class="title">
        优惠券
        <text class="iconfont icon-guanbi" @click="close"></text>
      </view>
      <view v-if="couponList.length > 0">
        <view class="coupon-list">
          <div
            class="item acea-row row-center-wrapper"
            v-for="coupon in couponList"
            :key="coupon.id"
            @click="click(coupon)"
          >
            <div class="money">
              <div>
                ￥<span class="num">{{ coupon.couponPrice }}</span>
              </div>
              <div class="pic-num">满{{ coupon.useMinPrice }}元可用</div>
            </div>
            <div class="text">
              <div class="condition line1">{{ coupon.couponTitle }}</div>
              <div class="data acea-row row-between-wrapper">
                <div v-if="coupon.endTime === 0">不限时</div>
                <div v-else>截止:{{ coupon.endTime }}</div>
                <div
                  class="iconfont icon-xuanzhong1 font-color-red"
                  v-if="checked === coupon.id"
                ></div>
                <div class="iconfont icon-weixuanzhong" v-else></div>
              </div>
            </div>
          </div>
        </view>
        <view class="couponNo bg-color-red" @click="couponNo">不使用优惠券</view>
      </view>
      <view v-if="!couponList.length && loaded">
        <view class="pictrue">
          <image :src="`${$VUE_APP_RESOURCES_URL}/images/noCoupon.png`" class="image" />
        </view>
      </view>
    </view>
    <view class="mask" @touchmove.prevent :hidden="value === false" @click="close"></view>
  </view>
</template>
<style scoped lang="less">
.coupon-list-window .iconfont {
  font-size: 40rpx;
}
.couponNo {
  font-size: 30rpx;
  font-weight: bold;
  color: #fff;
  width: 690rpx;
  height: 86rpx;
  border-radius: 43rpx;
  text-align: center;
  line-height: 86rpx;
  margin: 60rpx auto;
}
</style>
<script>
import { getOrderCoupon } from "@/api/order";
import DataFormatT from "@/components/DataFormatT";

export default {
  name: "CouponListWindow",
  components: {
    DataFormatT
  },
  props: {
    value: Boolean,
    checked: Number,
    price: {
      type: [Number, String],
      default: undefined
    },
    cartid: {
      type: String,
      default: ""
    }
  },
  data: function() {
    return {
      couponList: [],
      loaded: false
    };
  },
  watch: {
    price(n) {
      if (n === undefined || n == null) return;
      this.getCoupon();
    },
    cartid(n) {
      if (n === undefined || n == null) return;
      this.getCoupon();
    }
  },
  mounted: function() {},
  methods: {
    close: function() {
      this.$emit("input", false);
      this.$emit("close");
    },
    getCoupon() {
      getOrderCoupon(this.cartid).then(res => {
        this.couponList = res.data;
        this.loaded = true;
      });
    },
    click(coupon) {
      this.$emit("checked", coupon);
      this.$emit("input", false);
    },
    couponNo: function() {
      this.$emit("checked", null);
      this.$emit("input", false);
    }
  }
};
</script>
