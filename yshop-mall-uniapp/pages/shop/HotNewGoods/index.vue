<template>
  <view class="quality-recommend">
    <view class="title acea-row row-center-wrapper">
      <view class="line"></view>
      <view class="name">
        <text class="iconfont" :class="icon"></text>{{ name }}
      </view>
      <view class="line"></view>
    </view>
    <GoodList :good-list="goodsList" :is-sort="false"></GoodList>
  </view>
</template>
<script>

import GoodList from "@/components/GoodList";
import { getGroomList } from "@/api/store";
export default {
  name: "HotNewGoods",
  components: {
    GoodList
  },
  props: {},
  data: function() {
    return {
      imgUrls: [],
      goodsList: [],
      name: "",
      icon: "",
      RecommendSwiper: {
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
    this.titleInfo();
    this.getIndexGroomList();
  },
  methods: {
    titleInfo: function() {
      let type = this.$yroute.query.type;
      if (type === "1") {
        this.name = "精品推荐";
        this.icon = "icon-jingpintuijian";
        // document.title = "精品推荐";
      } else if (type === "2") {
        this.name = "热门榜单";
        this.icon = "icon-remen";
        // document.title = "热门榜单";
      } else if (type === "3") {
        this.name = "首发新品";
        this.icon = "icon-xinpin";
        // document.title = "首发新品";
      }
    },
    getIndexGroomList: function() {
      let that = this;
      let type = this.$yroute.query.type;
      getGroomList(type)
        .then(res => {
          that.imgUrls = res.data.banner;
          that.goodsList = res.data.list;
        })
        .catch((err)=> {
			uni.showToast({
							title: err.msg || err.response.data.msg|| err.response.data.message,
							icon: 'none',
							duration: 2000
						});
        });
    }
  }
};
</script>
