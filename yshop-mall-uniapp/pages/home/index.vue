<template>
  <view class="index">
    <view v-for="(item, index) in homeData" :key="index">
      <view class="head_box" v-if="item.type == 'header'" :style="{ background: bgcolor }" :class="{ active: bgcolor }">
        <cu-custom :isBack="true" :bgColor="bgcolor">
          <block slot="backText">
            <text class="nav-title shopro-selector-rect">{{ item.componentContent.title }}</text>
          </block>
        </cu-custom>
      </view>
      <view class="header header-search acea-row row-center-wrapper" v-if="item.type == 'search'" :style="{ background: bgcolor }">
        <view @click="goGoodSearch()" class="search acea-row row-middle">
          <text class="iconfont icon-xiazai5"></text>
          搜索商品
        </view>
        <!-- #ifndef H5 -->
        <view class="qr" @click="startQr()" v-if="$deviceType !== 'weixin'">
          <image :src="`${$VUE_APP_RESOURCES_URL}/images/qr.png`" />
        </view>
        <!-- #endif -->
      </view>
      <Banner v-if="item.type == 'banner'"
	  :detail="item.componentContent.bannerData"
	  @getbgcolor="getbgcolor"></Banner>
      <uni-notice-bar
	  v-if="item.type == 'noticeBar'"
	  scrollable="true"
	  @click="goRoll(item.componentContent.roll[0])"
	  single="true" :speed="10"
	  showIcon="true"
	  :text="item.componentContent.roll[0].info"></uni-notice-bar>
      <view class="content_box home_content_box"
		v-if="item.type == 'menu' && item.componentContent.menus">
        <!-- 菜单 -->
        <Menu :list="item.componentContent.menus"></Menu>
      </view>
      <!-- 滚动新闻 -->
      <!-- 广告 -->
      <Adv v-if="item.type == 'adv' && item.componentContent.detail"
	  :detail="item.componentContent.detail" />
      <!-- 热门榜单 -->
      <HotCommodity v-if="item.type == 'hotCommodity'"
	  :detail="likeInfo" />
      <!-- 超值拼团 -->
      <Groupon v-if="item.type == 'groupon'" :detail="combinationList" />
      <!-- 首发新品->秒杀 -->
      <FirstNewProduct v-if="item.type == 'firstNewProduct'"
	  :detail="firstList" />
      <!-- 精品推荐 -->
      <ProductsRecommended v-if="item.type == 'productsRecommended'"
	  :detail="bastList" />
      <!-- 促销单品 -->
      <PromoteProduct v-if="item.type == 'promoteProduct'"
	  :detail="benefit" />
      <!-- 直播 -->
      <!-- #ifdef MP-WEIXIN -->
      <Live v-if="item.type == 'live'" :detail="live" />
      <!-- #endif -->
      <!-- 为您推荐 -->
      <PromotionGood v-if="item.type == 'promotionGood'" :benefit="benefit" />
      <Coupon-window
	  :coupon-list="couponList"
	  v-if="showCoupon"
	  @checked="couponClose" @close="couponClose" />
    </view>
	<!-- #ifdef H5 -->
	<view class="bottomSpace" style='line-height:100rpx'>正在使用H5方式浏览</view>
	<!-- #endif -->
  </view>
</template>
<script>
import { mapState, mapMutations, mapActions } from 'vuex'
import GoodList from '@/components/GoodList'
import PromotionGood from '@/components/PromotionGood'
import CouponWindow from '@/components/CouponWindow'
import Menu from '@/components/Menu'
import UniNoticeBar from '@/components/uni-notice-bar/uni-notice-bar'
import Adv from '@/components/sh-adv'
import Groupon from '@/components/sh-groupon.vue'

import Banner from './components/Banner'
import HotCommodity from './components/HotCommodity'
import FirstNewProduct from './components/FirstNewProduct'
import ProductsRecommended from './components/ProductsRecommended'
import Live from './components/Live'

import { getHomeData, getShare, getCanvas } from '@/api/public'
import cookie from '@/utils/store/cookie'
import { isWeixin, handleUrlParam } from '@/utils/index'

import { openShareAll } from '@/libs/wechat'

const HAS_COUPON_WINDOW = 'has_coupon_window'

export default {
  name: 'Index',
  components: {
    // swiper,
    // swiperSlide,
    UniNoticeBar,
    GoodList,
    PromotionGood,
    CouponWindow,
    Menu,
    Adv,
    Groupon,
    Banner,
    HotCommodity,
    FirstNewProduct,
    ProductsRecommended,
    Live,
  },
  props: {},
  data: function() {
    return {
      homeData: [],
      CustomBar: this.CustomBar,
      StatusBar: this.StatusBar,
      formatMenus: [],
      categoryCurrent: 0,
      menuNum: 4,
      bgcolor: '',
      bgColor: '',
      swiperCurrent: 0, //轮播下标
      webviewId: 0,
      showCoupon: false,
      logoUrl: '',
      banner: [],
      menus: [],
      combinationList: [],
      roll: [],
      activity: [],
      activityOne: {},
      bastList: [],
      firstList: [],
      info: {
        fastList: [],
        bastBanner: [],

        bastList: [],
      },
      likeInfo: [],
      live: [],
      lovely: [],
      benefit: [],
      couponList: [],
      swiperOption: {
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
        },
        autoplay: {
          disableOnInteraction: false,
          delay: 2000,
        },
        loop: true,
        speed: 1000,
        observer: true,
        observeParents: true,
      },
      swiperRoll: {
        direction: 'vertical',
        autoplay: {
          disableOnInteraction: false,
          delay: 2000,
        },
        loop: true,
        speed: 1000,
        observer: true,
        observeParents: true,
      },
      swiperScroll: {
        freeMode: true,
        freeModeMomentum: false,
        slidesPerView: 'auto',
        observer: true,
        observeParents: true,
      },
      swiperBoutique: {
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
        },
        autoplay: {
          disableOnInteraction: false,
          delay: 2000,
        },
        loop: true,
        speed: 1000,
        observer: true,
        observeParents: true,
      },
      swiperProducts: {
        freeMode: true,
        freeModeMomentum: false,
        slidesPerView: 'auto',
        observer: true,
        observeParents: true,
      },
      bgImage: '',
	  // indexTitle: false,
    }
  },
  computed: {
    singNew() {
      return this.roll.length > 0 ? this.roll[0] : '你还没添加通知哦！'
    },
    customStyle() {
      var bgImage = this.bgImage
      // var style = `height:${this.CustomBar}px;padding-top:${0}px;background: ${this.bgcolor}`;
      var style = `height:${this.CustomBar}px;padding-top:${this.StatusBar}px;background: ${this.bgcolor}`
      if (this.bgImage) {
        style = `${style}background-image:url(${bgImage});`
      }
      return style
    },
  },
  onLoad() {
    this.getLocation()
    // uni.showLoading({
    //   title: "加载中",
    // });
    getCanvas()
      .then(res => {
		  console.log('223',res)
	  })
      .catch(error => {
		if (!error) {
		  return
		}
        this.homeData = JSON.parse(error.data.json)
		console.log('225',this.homeData)
      })
    getHomeData().then(res => {
      this.logoUrl = res.data.logoUrl
      res.data.banner.forEach(item => (item.bgcolor = item.color || ''))
	  this.info = res.data.info
	  console.log('239', res.data.info)
	  console.log('239',this.info)
      this.$set(this, 'info', res.data.info)
      this.$set(this, 'firstList', res.data.firstList)
      this.$set(this, 'bastList', res.data.bastList)
      this.$set(this, 'likeInfo', res.data.likeInfo)
      this.$set(this, 'live', res.data.liveList)
      this.$set(this, 'lovely', res.data.lovely)
      this.$set(this, 'benefit', res.data.benefit)
      this.$set(this, 'couponList', res.data.couponList)
      this.$set(this, 'combinationList', res.data.combinationList)
      uni.hideLoading()
      this.setOpenShare()
      // this.doColorThief()
    })
  },
  methods: {
    ...mapActions(['getLocation']),
    onShareTimeline: function() {
      return {
        title: this.miniHomeRemark,
        imageUrl: this.miniHomeImg,
        path: 'pages/home/index?spread=' + uni.getStorageSync('uid'),
      }
    },
    onShareAppMessage: function() {
      return {
        title: this.miniHomeRemark,
        imageUrl: this.miniHomeImg,
        path: 'pages/home/index?spread=' + uni.getStorageSync('uid'),
      }
    },
    goRoll(item) {
      if (item.uniapp_url) {
        this.$yrouter.push(item.uniapp_url)
      }
    },
    goGoodSearch() {
      // this.$yrouter.push('/pages/shop/GoodsEvaluate/index');
      this.$yrouter.push('/pages/shop/GoodSearch/index')
    },
    goWxappUrl(item) {
      this.$yrouter.push(item.uniapp_url)
    },
    goHotNewGoods(type) {
      this.$yrouter.push({
        path: '/pages/shop/HotNewGoods/index',
        query: {
          type,
        },
      })
    },
    goGoodsCon(item) {
      this.$yrouter.push({
        path: '/pages/shop/GoodsCon/index',
        query: {
          id: item.id,
        },
      })
    },
    goGoodsPromotion() {
      this.$yrouter.push('/pages/shop/GoodsPromotion/index')
    },
    setOpenShare: function() {
      if (this.$deviceType == 'weixin') {
        getShare().then(res => {
          var data = res.data.data
          var configAppMessage = {
            desc: data.synopsis,
            title: data.title,
            link: location.href,
            imgUrl: data.img,
          }
          openShareAll(configAppMessage)
        })
      }
    },
    startQr: function() {
      uni.scanCode({
        success: res => {
			let option = handleUrlParam(res.result)
			console.log(option)
			switch (option.pageType) {
				case 'good':
				  // 跳转商品详情
				  this.$yrouter.push({
					path: '/pages/shop/GoodsCon/index',
					query: {
					  q: res.result,
					},
				  })
				break
				case 'group':
				  // 跳转团购
				  this.$yrouter.push({
					path: '/pages/activity/GroupRule/index',
					query: {
					  q: res.result,
					},
				  })
				break
				case 'dargain':
				  // 跳转砍价
				  this.$yrouter.push({
					path: '/pages/activity/DargainDetails/index',
					query: {
					  q: res.result,
					},
				  })
				break
				default:
				  // 跳转分销
				  this.$yrouter.push({
					path: '/pages/Loading/index',
					query: {},
				  })
				break
			}
        },
      })
    },
    getbgcolor(e) {
      this.bgcolor = e
    },
  },
  created: async function() {
    // await this.doColorThief();
  },
}
</script>
<style scoped lang="less">
.content_box {
  background: #f6f6f6;
}

.index {
  background-color: #f6f6f6;
}

.swiper-item {
  height: 100%;
}

.fixed-header {
  position: fixed;
  z-index: 99;
  // #ifdef H5
  top: 88rpx;
  // #endif

  // #ifndef H5
  top: 0;
  // #endif
  left: 0;
  right: 0;
  background: #fff;
  box-shadow: 0 0 20rpx -10rpx #aaa;

  & + .fixed-header-box {
    height: 98rpx;
  }
}

.head_box {
  width: 750rpx;
  // background: #fff;
  transition: all linear 0.3s;

  /deep/.cuIcon-back {
    display: none;
  }

  .nav-title {
    font-size: 38rpx;
    font-family: PingFang SC;
    font-weight: 500;
    color: #fff;
  }
}

.cu-bar.fixed {
  position: fixed;
  width: 100%;
  top: 0;
  z-index: 1024;
  // box-shadow: 0 1upx 6upx rgba(0, 0, 0, 0.1);
}

.cu-bar {
  box-sizing: border-box;

  .index .header {
    height: 64rpx;
    // width: 100%;
    // padding: 0 30rpx;
    // box-sizing: border-box;
  }
}

.header-search {
  transition: all linear 0.3s;
}

.cu-bar .action {
  display: -webkit-box;
  display: -webkit-flex;
  display: flex;
  align-items: center;
  height: 100%;
  max-height: 100%;

  &:first-child {
    margin-left: 15px;
    font-size: 15px;
  }
}

.home_content_box {
  // margin-top: -20rpx;
}

.head_box {
}

.nav-title {
  margin-left: 20rpx;
  line-height: 40px;
}
.index{
.uni-noticebar{
  margin-bottom:0;
}
}
</style>
