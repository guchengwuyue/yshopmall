<template>
  <view class="Loads acea-row row-center-wrapper" v-if="loading || !loaded" style="margin-top: 20rpx;">
    <template v-if="loading">
      <view class="iconfont icon-jiazai loading acea-row row-center-wrapper"></view>
      正在加载中
    </template>
    <template v-if="!loading">
      上拉加载更多
    </template>
  </view>
</template>

<script>
  export default {
    name: "Loading",
    props: {
      loaded: Boolean,
      loading: Boolean
    }
  };
</script>
