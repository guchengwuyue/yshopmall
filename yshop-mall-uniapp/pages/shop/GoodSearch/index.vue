<template>
  <view>
    <view class="searchGood">
      <view class="search acea-row row-between-wrapper">
        <view class="input acea-row row-between-wrapper">
          <text class="iconfont icon-sousuo2"></text>
          <!-- <form @submit.prevent="submit"></form> -->
          <input type="text" placeholder="点击搜索商品" v-model="search" />
        </view>
        <view class="bnt" @click="submit">搜索</view>
      </view>
      <view v-if="keywords.length">
        <view class="title">热门搜索</view>
        <view class="list acea-row">
          <view
            class="item"
            v-for="keywordsKey of keywords"
            :key="keywordsKey"
            @click="toSearch(keywordsKey)"
          >{{ keywordsKey }}</view>
        </view>
      </view>
      <view class="line"></view>
      <!--      <GoodList></GoodList>-->
    </view>
    <!--<view class="noCommodity">-->
    <!--<view class="noPictrue">-->
    <!--<image :src="`${$VUE_APP_RESOURCES_URL}/images/noSearch.png`" class="image" />-->
    <!--</view>-->
    <!--<recommend></recommend>-->
    <!--</view>-->
  </view>
</template>
<script>
// import GoodList from "@/components/GoodList";
import { getSearchKeyword } from "@/api/store";
import { trim } from "@/utils";
// import Recommend from "@/components/Recommend";
export default {
  name: "GoodSearch",
  components: {
    // Recommend,
    // GoodList
  },
  props: {},
  data: function() {
    return {
      keywords: [],
      search: ""
    };
  },
  mounted: function() {
    this.getData();
  },
  methods: {
    submit() {
      const search = trim(this.search) || "";
      if (!search) return;
      this.toSearch(search);
    },
    toSearch(s) {
      this.$yrouter.push({ path: "/pages/shop/GoodsList/index", query: { s } });
    },
    getData() {
      getSearchKeyword().then(res => {
        this.keywords = res.data;
      });
    }
  }
};
</script>
<style >

.noCommodity {
  border-top: 0.05*100rpx solid #f5f5f5;
}
</style>
