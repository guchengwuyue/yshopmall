<template>
  <view v-if="shareInfoStatus" class="poster-first">
    <view class="mask-share">
      <!-- <image :src="`${$VUE_APP_RESOURCES_URL}/images/share-info.png`" @click="shareInfoClose" /> -->
    </view>
  </view>
</template>
<style scoped lang="less">
.poster-first {
  overscroll-behavior: contain;
}
.mask-share {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 999;
}
.mask-share image{
  width: 100%;
}
</style>
<script>
export default {
  name: "ShareInfo",
  props: {
    shareInfoStatus: Boolean
  },
  data: function() {
    return {};
  },
  mounted: function() {},
  methods: {
    shareInfoClose: function() {
      this.$emit("setShareInfoStatus");
    }
  }
};
</script>
