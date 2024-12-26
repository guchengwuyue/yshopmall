<template>
  <view class="cash-audit">
    <view class="pictrue">
      <image :src="`${$VUE_APP_RESOURCES_URL}/images/examine.png`" />
    </view>
    <view class="tip">提现申请已提交，等待人工审核</view>
    <view class="time">{{ time }}</view>
    <view class="bnt bg-color-red" @click="goUserPromotion()">好的</view>
  </view>
</template>
<script>
export default {
  name: "CashAudit",
  components: {},
  props: {},
  data: function() {
    return {
      time: ""
    };
  },
  mounted: function() {
    let myData = new Date();
    this.time = myData.toLocaleString();
  },
  methods: {
    goUserPromotion() {
      this.$yrouter.push({ path: "/pages/user/promotion/UserPromotion/index" });
    }
  }
};
</script>
