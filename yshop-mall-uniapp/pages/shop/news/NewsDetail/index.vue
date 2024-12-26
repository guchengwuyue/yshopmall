<template>
  <view class="newsDetail" v-if="isShow">
    <view class="conter" v-html="articleInfo.content"></view>
  </view>
  <view class="newsDetail" v-else>
    <view class="title">{{ articleInfo.title }}</view>
    <view class="list acea-row row-middle">
      <view class="label cart-color line1">新闻专区</view>
      <view class="item"> <text class="iconfont icon-shenhezhong"></text>{{ articleInfo.addTime }} </view>
      <view class="item"> <text class="iconfont icon-liulan"></text>{{ articleInfo.visit }} </view>
    </view>
    <view class="conter" v-html="articleInfo.content"></view>
  </view>
</template>
<style scoped lang="less">
.newsDetail .picTxt {
  width: 6.9 * 100rpx;
  height: 2 * 100rpx;
  border-radius: 0.2 * 100rpx;
  border: 1px solid #e1e1e1;
  position: relative;
  margin: 0.3 * 100rpx auto 0 auto;
}
.newsDetail .picTxt .pictrue {
  width: 2 * 100rpx;
  height: 2 * 100rpx;
}
.newsDetail .picTxt .pictrue image {
  width: 100%;
  height: 100%;
  border-radius: 0.2 * 100rpx 0 0 0.2 * 100rpx;
  display: block;
}
.newsDetail .picTxt .text {
  width: 4.6 * 100rpx;
}
.newsDetail .picTxt .text .name {
  font-size: 0.3 * 100rpx;
  color: #282828;
}
.newsDetail .picTxt .text .money {
  font-size: 0.24 * 100rpx;
  margin-top: 0.4 * 100rpx;
  font-weight: bold;
}
.newsDetail .picTxt .text .money .num {
  font-size: 0.36 * 100rpx;
}
.newsDetail .picTxt .text .y_money {
  font-size: 0.26 * 100rpx;
  color: #999;
  text-decoration: line-through;
}
.newsDetail .picTxt .label {
  position: absolute;
  background-color: #303131;
  width: 1.6 * 100rpx;
  height: 0.5 * 100rpx;
  right: -0.07 * 100rpx;
  border-radius: 0.25 * 100rpx 0 0.06 * 100rpx 0.25 * 100rpx;
  text-align: center;
  line-height: 0.5 * 100rpx;
  bottom: 0.24 * 100rpx;
}
.newsDetail .picTxt .label .span {
  background-image: linear-gradient(to right, #fff71e 0%, #f9b513 100%);
  background-image: -webkit-linear-gradient(to right, #fff71e 0%, #f9b513 100%);
  background-image: -moz-linear-gradient(to right, #fff71e 0%, #f9b513 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
.newsDetail .picTxt .label:after {
  content: ' ';
  position: absolute;
  width: 0;
  height: 0;
  border-bottom: 0.08 * 100rpx solid #303131;
  border-right: 0.08 * 100rpx solid transparent;
  top: -0.08 * 100rpx;
  right: 0;
}
.newsDetail .bnt {
  color: #fff;
  font-size: 0.3 * 100rpx;
  width: 6.9 * 100rpx;
  height: 0.9 * 100rpx;
  border-radius: 0.45 * 100rpx;
  margin: 0.48 * 100rpx auto 0 auto;
  text-align: center;
  line-height: 0.9 * 100rpx;
}
</style>
<script>
import { getArticleDetails } from '@/api/public'
export default {
  name: 'NewsDetail',
  components: {},
  props: {},
  data: function() {
    return {
      articleInfo: {},
	  isShow: false
    }
  },
  onLoad(option) {
  	if (option.name) {
		this.isShow = true
  		uni.setNavigationBarTitle({
  			title:option.name
  		});
  	}
  },
  watch: {
    $yroute(to) {
      if (to.name === 'NewsDetail') this.articleDetails()
    },
  },
  mounted: function() {
    this.articleDetails()
  },
  methods: {
    updateTitle() {
      // document.title = this.articleInfo.title || this.$yroute.meta.title;
    },
    articleDetails: function() {
      let that = this,
        id = this.$yroute.query.id
      getArticleDetails(id).then(res => {
        res.data.content = res.data.content.replace(/\<img/gi, '<img style="max-width:100% !important; height:auto"')
        that.articleInfo = res.data
        console.log(that.articleInfo)
        that.updateTitle()
      })
    },
  },
}
</script>
