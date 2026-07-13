<template>
  <view class="newsList" ref="container">
    <view class="list" v-for="(item, articleListIndex) in articleList" :key="articleListIndex">
      <view @click="goNewsDetail(item)" class="item acea-row row-between-wrapper">
        <view class="text acea-row row-column-between">
          <view class="name line2">{{ item.title }}</view>
          <view>{{ item.addTime }}</view>
        </view>
        <view class="pictrue">
          <image :src="item.imageInput" />
        </view>
      </view>
    </view>

    <!--暂无新闻-->
    <view class="noCommodity" v-if="articleList.length === 0 && page > 1">
      <view class="noPictrue">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noNews.png`" class="image" />
      </view>
    </view>
    <!--</Tab>-->
    <!--</Tabs>-->
  </view>
</template>
<script>
import { getArticleList } from "@/api/public";

export default {
  name: "NewsList",
  components: {},
  props: {},
  data: function() {
    return {
      page: 1,
      limit: 20,
      loadTitle: "",
      loading: false,
      loadend: false,
      imgUrls: [],
      navLsit: [],
      articleList: [],
      active: 0,
      cid: 0,
      swiperNew: {
        pagination: {
          el: ".swiper-pagination",
          clickable: true
        },
        autoplay: {
          disableOnInteraction: false,
          delay: 2000
        },
        loop: true,
        speed: 1000,
        observer: true,
        observeParents: true
      }
    };
  },
  mounted: function() {
    // this.articleBanner();
    //this.articleCategory();
    this.getArticleLists();
    //   this.$scroll(this.$refs.container, () => {
    //     !this.loading && this.getArticleLists();
    //   });
  },
  onReachBottom() {
    !this.loading && this.getArticleLists();
  },
  methods: {
    goNewsDetail(item) {
      this.$yrouter.push({
        path: "/pages/shop/news/NewsDetail/index",
        query: { id: item.id }
      });
    },
    getArticleLists: function() {
      let that = this;
      if (that.loading) return; //阻止下次请求（false可以进行请求）；
      if (that.loadend) return; //阻止结束当前请求（false可以进行请求）；
      that.loading = true;
      let q = {
        page: that.page,
        limit: that.limit
      };
      getArticleList(q).then(res => {
        that.loading = false;
        //apply();js将一个数组插入另一个数组;
        that.articleList.push.apply(that.articleList, res.data);
        that.loadend = res.data.length < that.limit; //判断所有数据是否加载完成；
        that.page = that.page + 1;
      });
    },
    onClick: function(name) {
      if (name === 0) this.articleHotList();
      else {
        this.cid = this.navLsit[name].id;
        this.articleList = [];
        this.page = 1;
        this.loadend = false;
        this.loading = false;
        this.getArticleLists(name);
      }
    }
  }
};
</script>
