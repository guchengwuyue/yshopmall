<template>
  <view>
    <view class="coupon-list-window" :class="coupon.coupon === true ? 'on' : ''">
      <view class="title">
        优惠券
        <text class="iconfont icon-guanbi" @click="close"></text>
      </view>
      <view class="coupon-list" v-if="coupon.list.length > 0">
        <view
          class="item acea-row row-center-wrapper"
          v-for="(item, couponpopIndex) in coupon.list"
          :key="couponpopIndex"
          @click="getCouponUser(couponpopIndex, item.id)"
        >
          <view class="money">
            ￥
            <text class="num">{{ item.couponPrice }}</text>
          </view>
          <view class="text">
            <view class="condition line1">购物满{{ item.useMinPrice }}元可用</view>
            <view class="data acea-row row-between-wrapper">
              <view v-if="item.end_time === 0">不限时</view>
              <view v-else>{{ item.startTime }}-{{ item.endTime }}</view>
              <view
                class="bnt acea-row row-center-wrapper"
                :class="!item.isUse ? 'bg-color-red' : 'gray'"
              >{{ !item.isUse ? "立即领取" : "已领取" }}</view>
            </view>
          </view>
        </view>
      </view>
      <!--无优惠券-->
      <view class="pictrue" v-else>
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noCoupon.png`" class="image" />
      </view>
    </view>
    <view class="mask" @touchmove.prevent :hidden="coupon.coupon === false" @click="close"></view>
  </view>
</template>
<script>
import { getCouponReceive } from "@/api/user";
export default {
  name: "CouponPop",
  props: {
    coupon: {
      type: Object,
      default: () => {}
    }
  },
  data: function() {
    return {};
  },
  mounted: function() {},
  methods: {
    close: function() {
      this.$emit("changeFun", { action: "changecoupon", value: false }); //$emit():注册事件；
    },
    getCouponUser: function(index, id) {
      let that = this,
        list = that.coupon.list;
      if (list[index].is_use === true) return;
      getCouponReceive(id).then(function() {
        uni.showToast({
          title: "已领取",
          icon: "none",
          duration: 2000
        });
        that.$set(list[index], "is_use", true);
        that.$emit("changefun", { action: "currentcoupon", value: index });
        that.$emit("changeFun", { action: "changecoupon", value: false });
      });
    }
  }
};
</script>
