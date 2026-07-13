<template>
  <view class="sharing-packets" :class="state === true ? 'on' : ''">
    <view
      class="iconfont icon-guanbi acea-row row-center-wrapper"
      @click="closeShare"
    ></view>
    <view class="line"></view>
    <view class="sharing-con" @click="goShare">
      <image :src="`${$VUE_APP_RESOURCES_URL}/images/red-packets.png`" class="image" />
      <view class="text font-color-red">
        <view>会员分享返</view>
        <view class="money"><text class="label">￥</text>{{ priceName }}</view>
        <view class="tip">下单即返佣金</view>
        <view class="shareBut">立即分享</view>
      </view>
    </view>
  </view>
</template>
<script>
export default {
  name: "ShareRedPackets",
  props: {
    priceName: {
      type: [String, Number],
      default: ""
    }
  },
  data: function() {
    return {
      state: false
    };
  },
  mounted: function() {},
  methods: {
    goShare: function() {
      this.$emit("changeFun", { action: "shareCode", value: false });
    },
    closeShare: function() {
      this.state = true;
    }
  }
};
</script>
