<template>
  <view :class="[posterImageStatus ? 'noscroll product-con' : 'product-con']" v-show="domStatus">
    <!-- 商品轮播 -->
    <product-con-swiper :imgUrls="imgUrls"></product-con-swiper>

    <view class="tui-pro-detail">
      <view class="tui-product-title tui-border-radius">
        <view class="tui-price__box">
          <view class="tui-pro-pricebox tui-padding">
            <view class="tui-pro-price">
              <view>
                <text>￥</text>
                <text class="tui-price">{{ formatPrice(storeInfo.price, 0) }}</text>
                <text>.{{ formatPrice(storeInfo.price, 1) }}</text>
              </view>
              <!-- <view class="tui-original-price tui-white__gray">￥199.00</view> -->
            </view>
            <view class="tui-sold tui-white__gray">
              <view class="tui-price-tag">{{ storeInfo.people }}人团</view>
              <text>已拼{{ storeInfo.sales }}{{ storeInfo.unitName }}</text>
            </view>
          </view>
          <view class="tui-right__box">库存{{ storeInfo.stock }}{{ storeInfo.unitName }}</view>
        </view>
        <view class="tui-pro-titbox">
          <view class="tui-pro-title">{{ storeInfo.title }}</view>
        </view>
      </view>
    </view>

    <!-- 规格 -->
    <view class="attribute acea-row row-between-wrapper" @click="selecAttrTap">
      <view>
        <text>{{ attrTxt }}：</text>
        <text class="atterTxt">{{ attrValue }}</text>
      </view>
      <view class="iconfont icon-jiantou"></view>
    </view>

    <!-- 拼团信息 -->
    <view class="notice acea-row row-middle">
      <view class="num font-color-red">
        <text class="iconfont icon-laba"></text>
        已拼{{ storeInfo.sales }}{{ storeInfo.unitName }}
        <text class="line">|</text>
      </view>
      <view class="swiper-no-swiping swiper">
        <swiper class="swiper-wrapper" :options="swiperTip" :autoplay="true" :interval="3000">
          <block v-for="(item, itemNewIndex) in itemNew" :key="itemNewIndex">
            <swiper-item>
              <view class="line1">{{ item }}</view>
            </swiper-item>
          </block>
        </swiper>
      </view>
    </view>

    <!-- 拼团人数，头像 -->
    <view class="assemble">
      <view v-for="(item, groupListindex) in groupList" :key="groupListindex">
        <view class="item acea-row row-between-wrapper" v-if="groupListindex < groupListCount">
          <view class="pictxt acea-row row-between-wrapper">
            <view class="pictrue">
              <image :src="item.avatar" class="image" />
            </view>
            <view class="text line1" v-text="item.nickname"></view>
          </view>
          <view class="right acea-row row-middle">
            <view>
              <view class="lack">
                <text>还差</text>
                <text class="font-color-red" v-text="item.count"></text>
                <text>人成团</text>
              </view>
              <count-down :isDay="true" :tipText="'剩余 '" :dayText="' 天 '" :hourText="' : '" :minuteText="' : '" :secondText="' '" :datatime="item.stopTime"></count-down>
            </view>
            <view class="spellBnt" @click="groupRule(item.id)">
              去拼单
              <text class="iconfont icon-jiantou"></text>
            </view>
          </view>
        </view>
      </view>
      <view class="more" v-if="groupList.length > groupListCount" @click="setGroupListCount">
        查看更多
        <text class="iconfont icon-xiangxia"></text>
      </view>
    </view>

    <!-- 拼团规则 -->
    <view class="playWay">
      <view class="title acea-row row-between-wrapper">
        <view>拼团玩法</view>
      </view>
      <view class="way acea-row row-middle">
        <view class="item"> <text class="num">①</text>开团/参团 </view>
        <view class="iconfont icon-arrow"></view>
        <view class="item"> <text class="num">②</text>邀请好友 </view>
        <view class="iconfont icon-arrow"></view>
        <view class="item">
          <view> <text class="num">③</text>满员发货 </view>
        </view>
      </view>
    </view>

    <!-- 用户评价 -->
    <view class="userEvaluation">
      <view class="title acea-row row-between-wrapper">
        <view v-text="'用户评价(' + replyCount + ')'"></view>
        <view class="praise" @click="goReply">
          <text class="font-color-red" v-text="replyChance + '%'"></text>好评率
          <text class="iconfont icon-jiantou"></text>
        </view>
      </view>
      <UserEvaluation :reply="reply"></UserEvaluation>
    </view>

    <!-- 产品介绍 -->
    <view class="product-intro">
      <text class="title"><text>产品介绍</text></text>
      <view class="conter" v-html="storeInfo.description"></view>
      <!-- <view class="conter" v-html=""></view> -->
    </view>

    <!-- 操作栏 -->
    <view style="height: 160rpx"></view>

    <!--底部操作栏-->
    <view class="tui-operation">
      <view class="tui-operation-left tui-col-5">
        <!-- #ifdef MP-WEIXIN -->
        <button class="tui-operation-item" hover-class="tui-opcity" :hover-stay-time="150">
          <view class="iconfont icon-kefu"></view>
          <view ><button open-type="contact" class="contacButton tui-operation-text tui-scale-small">客服</button></view>
        </button>
        <!-- #endif -->

        <view class="tui-operation-item" hover-class="tui-opcity" :hover-stay-time="150" @click="setCollect" v-if="userCollect">
          <view class="iconfont icon-shoucang1"></view>
          <view class="tui-operation-text tui-scale-small">收藏</view>
        </view>
        <view class="tui-operation-item" hover-class="tui-opcity" :hover-stay-time="150" @click="setCollect" v-if="!userCollect">
          <view class="iconfont icon-shoucang"></view>
          <view class="tui-operation-text tui-scale-small">收藏</view>
        </view>
      </view>

      <view class="tui-operation-right tui-right-flex tui-col-7 tui-btnbox-4">
        <view class="tui-flex-1">
          <tui-button height="100rpx" :size="26" type="warning" shape="rightAngle" @click="openAlone">
            <view class="tui-btn__box">
              <view>单独购买</view>
              <view class="tui-flex-end">
                <view class="tui-size-26">￥</view>
                <view class="tui-size-36">{{ formatPrice(storeInfo.productPrice, 0) }}</view>
                <view class="tui-size-26">.{{ formatPrice(storeInfo.productPrice, 1) }}</view>
              </view>
            </view>
          </tui-button>
        </view>
        <view class="tui-flex-1">
          <tui-button height="100rpx" :size="26" type="danger" shape="rightAngle" @click="openTeam">
            <view class="tui-btn__box">
              <view>发起拼团</view>
              <view class="tui-flex-end">
                <view class="tui-size-28">￥</view>
                <view class="tui-price-large tui-size-36">{{ formatPrice(storeInfo.price, 0) }}</view>
                <view class="tui-size-28">.{{ formatPrice(storeInfo.price, 1) }}</view>
              </view>
            </view>
          </tui-button>
        </view>
      </view>
    </view>

    <!-- 商品信息弹窗 -->
    <ProductWindow v-if="cartNum" v-on:changeFun="changeFun" :attr="attr" :cartNum="cartNum"></ProductWindow>
    <StorePoster v-on:setPosterImageStatus="setPosterImageStatus" :posterImageStatus="posterImageStatus" :posterData="posterData"></StorePoster>
  </view>
</template>

<script>
// import { swiper, swiperSlide } from "vue-awesome-swiper";

import ProductConSwiper from '@/components/ProductConSwiper'
import CountDown from '@/components/CountDown'
import UserEvaluation from '@/components/UserEvaluation'
import ProductWindow from '@/components/ProductWindow'
import StorePoster from '@/components/StorePoster'
import { getCombinationDetail } from '@/api/activity'
import { postCartAdd } from '@/api/store'
import { imageBase64 } from '@/api/public'
import { getCoupon, getCollectAdd, getCollectDel, getUserInfo } from '@/api/user'
const NAME = 'GroupDetails'

export default {
  name: 'GroupDetails',
  components: {
    ProductConSwiper,
    CountDown,
    UserEvaluation,
    // swiper,
    // swiperSlide,
    ProductWindow,
    StorePoster,
  },
  props: {},
  data: function() {
    return {
      domStatus: false,
      posterData: {
        image: '',
        title: '',
        price: '',
        code: '',
      },
      posterImageStatus: false,
      reply: [],
      replyCount: 0,
      replyChance: 0,
      imgUrls: [],
      storeInfo: {},
      itemNew: {},
      groupListCount: 2,
      groupList: {},
      attrTxt: '请选择',
      productValue: [],
      attrValue: '',
      swiperTip: {
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
      attr: {
        cartAttr: false,
        productAttr: [],
        productSelect: {},
      },
      cartNum: 1,
      userCollect: false,
    }
  },
  computed: {},
  watch: {
    $yroute: function(n) {
      var that = this
      if (n.name === NAME) {
        that.mountedStart()
      }
    },
  },
  onShow: function() {
    this.mountedStart()
  },
  methods: {
    formatPrice(price, index) {
      // console.log(price)
      if (price) {
        return price.split('.')[index]
      }
      return ''
    },
    onShareAppMessage: function() {
      return {
        title: this.storeInfo.title,
        imageUrl: this.storeInfo.image,
        path: 'pages/activity/GroupDetails/index?id=' + this.storeInfo.id + '&spread=' + uni.getStorageSync('uid') + '&pageType=good&codeType=routine',
        success(res) {
          uni.showToast({
            title: '分享成功',
          })
        },
        fail(res) {
          uni.showToast({
            title: '分享失败',
            icon: 'none',
          })
        },
      }
    },
    //收藏商品
    setCollect: function() {
      let that = this,
        id = that.storeInfo.id,
        category = 'combination',
        type = 'collect'
      if (that.userCollect) {
        getCollectDel(id, category,type).then(function() {
          that.userCollect = !that.userCollect
        })
      } else {
        getCollectAdd(id, category,type).then(function() {
          that.userCollect = !that.userCollect
        })
      }
    },
    mountedStart: function() {
      var that = this
      let id = that.$yroute.query.id
      getCombinationDetail(id).then(res => {
        that.userCollect = res.data.userCollect
        res.data.storeInfo.description = res.data.storeInfo.description.replace(/\<img/gi, '<img style="max-width:100%;height:auto;"')
        that.$set(that.attr, 'productAttr', res.data.productAttr)
        that.$set(that, 'productValue', res.data.productValue)
        that.$set(that, 'storeInfo', res.data.storeInfo)
        that.$set(that, 'imgUrls', res.data.storeInfo.sliderImageArr)
        that.$set(that, 'itemNew', res.data.pinkOkList)
        that.$set(that, 'groupList', res.data.pink)
        that.$set(that, 'reply', [res.data.reply])
        that.$set(that, 'replyCount', res.data.replyCount)
        that.$set(that, 'replyChance', res.data.replyChance)
        that.posterData.image = that.storeInfo.image
        if (that.storeInfo.title.length > 30) {
          that.posterData.title = that.storeInfo.title.substring(0, 30) + '...'
        } else {
          that.posterData.title = that.storeInfo.title
        }
        that.posterData.price = that.storeInfo.pinkPrice
        that.posterData.code = that.storeInfo.code_base
        that.domStatus = true
        console.log(this.storeInfo)
        that.DefaultSelect()
      })
    },
    DefaultSelect: function() {
      let productAttr = this.attr.productAttr
      let value = []
      for (let i = 0; i < productAttr.length; i++) {
        this.$set(productAttr[i], 'index', 0)
        value.push(productAttr[i].attrValueArr[0])
      }
      //sort();排序函数:数字-英文-汉字；
      let productSelect = this.productValue[value.sort().join(',')]
      console.log(productSelect)
      if (productSelect && productAttr.length) {
        this.$set(this.attr.productSelect, 'store_name', this.storeInfo.title)
        this.$set(this.attr.productSelect, 'image', productSelect.image)
        this.$set(this.attr.productSelect, 'price', productSelect.pinkPrice)
        this.$set(this.attr.productSelect, 'stock', productSelect.pinkStock)
        this.$set(this.attr.productSelect, 'unique', productSelect.unique)
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', value.sort().join(','))
        this.$set(this, 'attrTxt', '已选择')
      } else if (!productSelect && productAttr.length) {
        this.$set(this.attr.productSelect, 'store_name', this.storeInfo.title)
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.pinkPrice)
        this.$set(this.attr.productSelect, 'stock', 0)
        this.$set(this.attr.productSelect, 'unique', '')
        this.$set(this.attr.productSelect, 'cart_num', 0)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      } else if (!productSelect && !productAttr.length) {
        this.$set(this.attr.productSelect, 'store_name', this.storeInfo.title)
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.pinkPrice)
        this.$set(this.attr.productSelect, 'stock', this.storeInfo.pinkStock)
        this.$set(this.attr.productSelect, 'unique', this.storeInfo.unique || '')
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      }
    },
    getImageBase64: function() {
      let that = this
      imageBase64(this.posterData.image, that.posterData.code).then(res => {
        that.posterData.image = res.data.image
        that.posterData.code = res.data.code
      })
    },
    setPosterImageStatus: function() {
      // var sTop = document.body || document.documentElement;
      // sTop.scrollTop = 0;
      this.posterImageStatus = !this.posterImageStatus
    },
    groupRule: function(id) {
      var that = this
      that.$yrouter.push({
        path: '/pages/activity/GroupRule/index',
        query: {
          id,
        },
      })
    },
    goReply: function() {
      var that = this
      that.$yrouter.push({
        path: '/pages/shop/EvaluateList/index',
        query: {
          id: that.storeInfo.product_id,
        },
      })
    },
    setGroupListCount: function() {
      this.groupListCount = this.groupListCount + 2
    },
    //将父级向子集多次传送的函数合二为一；
    changeFun: function(opt) {
      if (typeof opt !== 'object') opt = {}
      let action = opt.action || ''
      let value = opt.value === undefined ? '' : opt.value
      this[action] && this[action](value)
    },
    changeattr: function(res) {
      var that = this
      that.attr.cartAttr = res
    },
    ChangeCartNum: function(res) {
      var that = this
      that.attr.productSelect.cart_num = 1
      that.cartNum = 1
      uni.showToast({
        title: '每人每次限购1' + that.storeInfo.unitName,
        icon: 'none',
        duration: 2000,
      })
    },
    //选择属性；
    ChangeAttr: function(res) {
      // 修改了规格
      let productSelect = this.productValue[res.value]
      if (productSelect) {
        this.attr.productAttr[res.indexw].index = res.indexn
        this.$set(this.attr.productSelect, 'image', productSelect.image)
        this.$set(this.attr.productSelect, 'price', productSelect.pinkPrice)
        this.$set(this.attr.productSelect, 'stock', productSelect.pinkStock)
        this.$set(this.attr.productSelect, 'unique', productSelect.unique)
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', res.value)
        this.$set(this, 'attrTxt', '已选择')
      } else {
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.pinkPrice)
        this.$set(this.attr.productSelect, 'stock', 0)
        this.$set(this.attr.productSelect, 'unique', '')
        this.$set(this.attr.productSelect, 'cart_num', 0)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      }
    },
	// 单独购买
	openAlone: function() {
	  this.$yrouter.push({
	    path: '/pages/shop/GoodsCon/index',
	    query: {
	      id: this.storeInfo.productId,
	    },
	  })
	  // this.$yrouter.replace({ path: "/detail/" + this.storeInfo.productId });
	},
	// 发起拼团
    openTeam: function() {
      var that = this
	  console.log(this.attr)
      if (that.attr.cartAttr == false) { // 展示弹框
	    console.log(this.attr.cartAttr)
        that.attr.cartAttr = !this.attr.cartAttr
		// 设置拼团价格
		that.attr.productSelect.price = this.storeInfo.price
      } else { // 已有弹框——初始化商品信息，下单请求
        var data = {}
        data.productId = that.storeInfo.productId
		data.price = that.storeInfo.price
        data.cartNum = that.attr.productSelect.cart_num
        data.uniqueId = that.attr.productSelect.unique
        data.combinationId = that.storeInfo.id
        data.new = 1
		console.log(data)
        postCartAdd(data)
          .then(res => {
            that.$yrouter.push({
              path: '/pages/order/OrderSubmission/index',
              query: {
                // id: res.data.cartId,
				pinkId: res.data.cartId,
              },
            })
          })
          .catch(err => {
            uni.showToast({
              title: err.msg || err.response.data.msg || err.response.data.message,
              icon: 'none',
              duration: 2000,
            })
          })
      }
    },
    //打开属性插件；
    selecAttrTap: function() {
      this.attr.cartAttr = true
      this.isOpen = true
    },
  },
}
</script>
<style scoped lang="less">
.product-con .wrapper {
  padding-bottom: 0.26 * 100rpx;
}

.noscroll {
  height: 100%;
  overflow: hidden;
}

.product-con .footer-group .bnt {
  // flex:1;
  width: 43%;
}

.footer-group {
  button {
    border: 0;
    background: none;
  }
}

.product-con .footer-group .bnt.bg-color-violet {
  background-color: #fa8013;
}

/*内容 部分*/

.tui-padding {
  padding: 0 30rpx;
  box-sizing: border-box;
}

.tui-ml-auto {
  margin-left: auto;
}

/* #ifdef H5 */
.tui-ptop {
  padding-top: 44px;
}

/* #endif */

.tui-size {
  font-size: 24rpx;
  line-height: 24rpx;
}

.tui-gray {
  color: #999;
}

.tui-white__gray {
  color: rgba(255, 255, 255, 0.8);
  font-weight: normal;
  font-size: 26rpx;
}

.tui-color-red {
  color: #eb0909;
}

.tui-border-radius {
  border-bottom-left-radius: 24rpx;
  border-bottom-right-radius: 24rpx;
  overflow: hidden;
}

.tui-radius-all {
  border-radius: 24rpx;
  overflow: hidden;
}

.tui-mtop {
  margin-top: 26rpx;
}

.tui-pro-detail {
  box-sizing: border-box;
  color: #333;
}

.tui-product-title {
  background: #fff;
  padding-bottom: 30rpx;
}

.tui-price__box {
  width: 100%;
  height: 130rpx;
  display: flex;
}

.tui-pro-pricebox {
  width: 540rpx;
  height: 130rpx;
  display: flex;
  flex-direction: column;
  justify-content: center;
  color: #ffffff;
  font-size: 26rpx;
  line-height: 26rpx;
  background: linear-gradient(-30deg, #ff1f2e, #f52c6c);
  flex-shrink: 0;
}

.tui-pro-price {
  display: flex;
  align-items: flex-end;
}

.tui-price {
  font-size: 44rpx;
  line-height: 42rpx;
}

.tui-sold {
  width: 100%;
  height: 44rpx;
  padding-left: 4rpx;
  display: flex;
  align-items: center;
  padding-top: 10rpx;
}

.tui-price-tag {
  height: 38rpx;
  border: 1rpx solid #fff;
  border-radius: 6rpx;
  white-space: nowrap;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24rpx;
  line-height: 24rpx;
  transform: scale(0.8);
  transform-origin: 0 center;
  border-radius: 6rpx;
  padding: 0 8rpx;
}

.tui-original-price {
  font-size: 26rpx;
  line-height: 26rpx;
  padding: 0 20rpx;
  box-sizing: border-box;
  text-decoration: line-through;
}

.tui-right__box {
  flex: 1;
  background-color: #ffe5e5;
  font-size: 28rpx;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #eb0909;
}

.tui-pro-titbox {
  font-size: 32rpx;
  font-weight: 500;
  position: relative;
  padding: 0 150rpx 0 30rpx;
  box-sizing: border-box;
}

.tui-pro-title {
  padding-top: 20rpx;
}

.tui-share-btn {
  display: block;
  background: transparent;
  margin: 0;
  padding: 0;
  border-radius: 0;
  border: 0;
}

.tui-share-btn::after {
  border: 0;
}

.tui-share-box {
  display: flex;
  align-items: center;
}

.tui-share-position {
  position: absolute;
  right: 0;
  top: 30rpx;
}

.tui-share-text {
  padding-left: 8rpx;
}

.tui-sub-title {
  padding: 20rpx 0;
  line-height: 32rpx;
}

.tui-guarantee__box {
  display: flex;
  padding: 12rpx 88rpx 0;
  box-sizing: border-box;
  justify-content: space-between;
  align-items: center;
  background: #fff;
}

.tui-gt-item {
  font-size: 28rpx;
  display: flex;
  align-items: center;
}

.tui-gt-item text {
  transform: scale(0.8);
  transform-origin: center center;
}

.tui-gt-img {
  width: 24rpx;
  height: 26rpx;
  flex-shrink: 0;
}

.tui-group-text {
  width: 100%;
  display: flex;
  align-items: center;
}

.tui-group-title {
  font-size: 30rpx;
  line-height: 30rpx;
  font-weight: bold;
  padding-left: 16rpx;
  border-left: 2px solid #eb0909;
  box-sizing: border-box;
}

.tui-sub__title {
  font-size: 26rpx;
  padding-right: 30rpx;
}

.tui-step__box {
  width: 100%;
  height: 210rpx;
  background: #fff;
  padding: 0 60rpx;
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-sizing: border-box;
}

.tui-step-item {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  font-size: 26rpx;
  color: #666666;
}

.tui-step-item image {
  width: 64rpx;
  height: 55rpx;
  flex-shrink: 0;
}

.tui-step-item image:first-child {
  width: 60rpx !important;
}

.tui-step-arrow {
  height: 90rpx;
}

.tui-step-arrow image {
  width: 11rpx;
  height: 20rpx;
  flex-shrink: 0;
}

.tui-step-text {
  line-height: 26rpx;
  padding-top: 24rpx;
}

.tui-discount-box {
  background: #fff;
}

.tui-list__cell {
  width: 100%;
  position: relative;
  display: flex;
  align-items: center;
  font-size: 26rpx;
  line-height: 26rpx;
  padding: 36rpx 30rpx;
  box-sizing: border-box;
}

.tui-right {
  position: absolute;
  right: 30rpx;
  top: 30rpx;
}

.tui-top40 {
  top: 40rpx !important;
}

.tui-bold {
  font-weight: bold;
}

.tui-list__cell::after {
  content: '';
  position: absolute;
  border-bottom: 1rpx solid #eaeef1;
  -webkit-transform: scaleY(0.5);
  transform: scaleY(0.5);
  bottom: 0;
  right: 0;
  left: 126rpx;
}

.tui-last::after {
  border-bottom: 0 !important;
}

.tui-flex-center {
  display: flex;
  align-items: center;
}

.tui-cell-title {
  width: 66rpx;
  padding-right: 30rpx;
  flex-shrink: 0;
}

.tui-promotion-box {
  display: flex;
  align-items: center;
  padding: 10rpx 0;
  width: 80%;
}

.tui-promotion-box text {
  width: 70%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.tui-basic-info {
  background: #fff;
}

.tui-addr-box {
  width: 76%;
}

.tui-addr-item {
  padding: 10rpx;
  line-height: 34rpx;
}

.tui-guarantee {
  background: #fdfdfd;
  display: flex;
  flex-wrap: wrap;
  padding: 20rpx 30rpx 30rpx 30rpx;
  font-size: 24rpx;
}

.tui-guarantee-item {
  color: #999;
  padding-right: 30rpx;
  padding-top: 10rpx;
}

.tui-pl {
  padding-left: 4rpx;
}

.tui-cmt-box {
  background: #fff;
}

.tui-between {
  justify-content: space-between !important;
}

.tui-cmt-all {
  color: #ff201f;
  padding-right: 8rpx;
}

.tui-cmt-content {
  font-size: 26rpx;
}

.tui-cmt-user {
  display: flex;
  align-items: center;
}

.tui-acatar {
  width: 60rpx;
  height: 60rpx;
  border-radius: 30rpx;
  display: block;
  margin-right: 16rpx;
}

.tui-cmt {
  padding: 14rpx 0;
}

.tui-attr {
  font-size: 24rpx;
  color: #999;
  padding: 6rpx 0;
}

.tui-cmt-btn {
  padding: 50rpx 0 30rpx 0;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
}

.tui-nomore-box {
  padding-top: 10rpx;
}

.tui-product-img {
  display: flex;
  flex-direction: column;
  transform: translateZ(0);
}

.tui-product-img image {
  width: 100%;
  display: block;
}

/*底部操作栏*/

.tui-col-7 {
  width: 58.33333333%;
}

.tui-col-5 {
  width: 41.66666667%;
}

.tui-operation {
  width: 100%;
  height: 160rpx;
  background: rgba(255, 255, 255, 0.98);
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: space-between;
  z-index: 100;
  bottom: 0;
  left: 0;
  padding-bottom: env(safe-area-inset-bottom);
}

.tui-safearea-bottom {
  width: 100%;
  height: env(safe-area-inset-bottom);
}

.tui-operation::before {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  left: 0;
  border-top: 1rpx solid #eaeef1;
  -webkit-transform: scaleY(0.5);
  transform: scaleY(0.5);
}

.tui-operation-left {
  display: flex;
  align-items: center;
}

.tui-operation-item {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  position: relative;
  background: none;
  padding: 0;

  border: 0;
  line-height: 1em;
  &:after {
    display: none;
  }
  .tui-operation-text {
    margin-top: 9rpx;
    line-height: 1em;
  }
}

.tui-operation-text {
  font-size: 22rpx;
  color: #333;
}

.tui-opacity {
  opacity: 0.5;
}

.tui-scale-small {
  transform: scale(0.9);
  transform-origin: center center;
}

.tui-operation-right {
  height: 100rpx;
  padding-top: 0;
}

.tui-right-flex {
  display: flex;
  align-items: center;
  justify-content: center;
}
.tui-flex-1 {
  flex: 1;
}

.tui-img__clock {
  width: 30rpx;
  height: 30rpx;
  margin-right: 8rpx;
}

/*底部操作栏*/

/*底部选择弹层*/

.tui-popup-class {
  border-top-left-radius: 24rpx;
  border-top-right-radius: 24rpx;
  padding-bottom: env(safe-area-inset-bottom);
}

.tui-popup-box {
  position: relative;
  padding: 30rpx 0 100rpx 0;
}

.tui-popup-btn {
  width: 100%;
  position: absolute;
  left: 0;
  bottom: 0;
}

.tui-product-box {
  display: flex;
  align-items: flex-end;
  font-size: 24rpx;
  padding-bottom: 30rpx;
}

.tui-popup-img {
  height: 200rpx;
  width: 200rpx;
  border-radius: 24rpx;
  display: block;
}

.tui-popup-price {
  padding-left: 20rpx;
  padding-bottom: 8rpx;
}

.tui-amount {
  color: #ff201f;
  font-size: 36rpx;
}

.tui-number {
  font-size: 24rpx;
  line-height: 24rpx;
  padding-top: 12rpx;
  color: #999;
}

.tui-popup-scroll {
  height: 600rpx;
  font-size: 26rpx;
}

.tui-scrollview-box {
  padding: 0 30rpx 60rpx 30rpx;
  box-sizing: border-box;
}

.tui-attr-title {
  padding: 10rpx 0;
  color: #333;
}

.tui-attr-box {
  font-size: 0;
  padding: 20rpx 0;
}

.tui-attr-item {
  max-width: 100%;
  min-width: 200rpx;
  height: 64rpx;
  display: -webkit-inline-flex;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  background: #f7f7f7;
  padding: 0 26rpx;
  box-sizing: border-box;
  border-radius: 32rpx;
  margin-right: 20rpx;
  margin-bottom: 20rpx;
  font-size: 26rpx;
}

.tui-attr-active {
  background: #fcedea !important;
  color: #e41f19;
  font-weight: bold;
  position: relative;
}

.tui-attr-active::after {
  content: '';
  position: absolute;
  border: 1rpx solid #e41f19;
  width: 100%;
  height: 100%;
  border-radius: 40rpx;
  left: 0;
  top: 0;
}

.tui-number-box {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20rpx 0 30rpx 0;
  box-sizing: border-box;
}

/*底部选择弹层*/

/*拼团玩法介绍 modal*/
.tui-modal__title {
  text-align: center;
  font-weight: bold;
  padding-bottom: 8rpx;
}
.tui-modal__p {
  font-size: 26rpx;
  color: #888;
  padding-top: 20rpx;
}
.tui-modal__btn {
  width: 100%;
  padding: 60rpx 0 20rpx;
  display: flex;
  justify-content: center;
}

/*正在拼团*/
.tui-group-swiper {
  width: 100%;
  background-color: #fff;
}
.tui-group-user {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 35rpx 40rpx;
  box-sizing: border-box;
}

.tui-user-left {
  font-size: 30rpx;
  display: flex;
  align-items: center;
}

.tui-user-left image {
  height: 80rpx;
  width: 80rpx;
  flex-shrink: 0;
  border-radius: 50%;
  margin-right: 16rpx;
}

.tui-user-right {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.tui-user-anme {
  max-width: 160rpx;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.tui-group-num {
  font-size: 26rpx;
  line-height: 26rpx;
  padding-bottom: 12rpx;
}

.tui-user-countdown {
  padding-right: 18rpx;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.tui-sub-title {
  font-size: 28rpx;
  padding-right: 30rpx;
}
.tui-group-countdown {
  display: flex;
  align-items: center;
  font-size: 24rpx;
  color: #666666;
  white-space: nowrap;
}

.tui-countdown-right {
  padding-right: 6rpx;
}

.tui-countdown-left {
  padding-left: 6rpx;
}

.tui-btn__box {
  height: 98rpx;
  font-size: 26rpx;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: column;
  line-height: 28rpx;
  padding: 18rpx 0 14rpx 0;
  box-sizing: border-box;
}

.tui-size-26 {
  font-size: 26rpx;
  line-height: 26rpx;
  padding-top: 4rpx;
}

.tui-size-36 {
  font-size: 36rpx;
  line-height: 34rpx;
  font-weight: 500;
}
.tui-flex-end {
  display: flex;
  align-items: flex-end;
}
</style>
