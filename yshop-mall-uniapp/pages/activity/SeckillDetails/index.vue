<template>
  <view :class="[posterImageStatus ? 'noscroll product-con' : 'product-con']" v-show="domStatus">
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
              <view class="tui-original-price tui-white__gray" v-text="'￥' + storeInfo.otPrice"></view>
            </view>
            <view class="tui-sold tui-white__gray">
              <text>已抢{{ storeInfo.sales }}件</text>
              <tui-tag v-if="seckillStatus != 2" type="white" plain size="24rpx" padding="8rpx" :scaleMultiple="0.94">限时秒杀</tui-tag>
            </view>
          </view>
          <view class="tui-right__box">
            <image v-if="seckillStatus == 2" src="https://www.thorui.cn/images/mall/img_seckillgood_seckill.png" class="tui-seckill__img" mode="widthFix"></image>
            <tui-button v-if="seckillStatus == 2" type="danger" disabled shape="circle" width="140rpx" height="42rpx" :size="24">{{ seckillStatus == 1 ? '活动已结束' : '即将开抢' }}</tui-button>
            <text v-if="seckillStatus != 2" class="tui-color-red">距结束还剩:</text>
            <count-down :isDay="false" :tipText="' '" :dayText="' '" :hourText="' : '" :minuteText="' : '" :secondText="' '" :datatime="datatime"></count-down>
          </view>
        </view>
        <view class="tui-pro-titbox">
          <view class="tui-pro-title">{{ storeInfo.title }}</view>
        </view>
        <view class="tui-padding">
          <view class="tui-sale-info tui-size tui-gray">
            <view>库存：{{ storeInfo.stock }}</view>
            <view>月销{{ storeInfo.sales }}</view>
          </view>
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
    <view class="product-intro">
      <text class="title"><text>产品介绍</text></text>
      <view class="conter" v-html="storeInfo.description"></view>
    </view>

    <!-- 操作栏 -->
    <view style="height: 160rpx"></view>

    <!--底部操作栏-->
    <view class="tui-operation">
      <view class="tui-operation-left tui-col-5">
        <!-- #ifdef MP-WEIXIN -->
        <button class="tui-operation-item" hover-class="tui-opcity" :hover-stay-time="150">
          <view class="iconfont icon-kefu"></view>
          <view class="tui-operation-text tui-scale-small">客服</view>
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

      
      <view class="tui-operation-right tui-right-flex tui-col-7 tui-btnbox-4" v-if="seckillStatus == 1 && storeInfo.num > 0 && storeInfo.stock > 0">
        <view class="tui-flex-1">
          <tui-button height="68rpx" :size="26" type="warning" shape="circle" @click="openAlone">单独购买</tui-button>
        </view>
        <view class="tui-flex-1">
          <tui-button height="68rpx" :size="26" type="danger" shape="circle" @click="tapBuy">立即购买</tui-button>
        </view>
      </view>
      <view class="tui-operation-right tui-right-flex tui-col-7 tui-btnbox-4" v-if="seckillStatus == 1 && storeInfo.num <= 0 && storeInfo.stock <= 0">
        <view class="tui-flex-1">
          <tui-button height="68rpx" :size="26" type="warning" shape="circle" disabled>已售罄</tui-button>
        </view>
        <view class="tui-flex-1">
          <tui-button height="68rpx" :size="26" type="danger" shape="circle" @click="openAlone">原价购买</tui-button>
        </view>
      </view>
      <view class="tui-operation-right tui-right-flex tui-col-7 tui-btnbox-4" v-if="seckillStatus == 2">
        <view class="tui-flex-1">
          <tui-button height="68rpx" :size="26" type="warning" shape="circle" disabled>即将开始</tui-button>
        </view>
        <view class="tui-flex-1">
          <tui-button height="68rpx" :size="26" type="danger" shape="circle" @click="openAlone">原价购买</tui-button>
        </view>
      </view>
      <view class="tui-operation-right tui-right-flex tui-col-7 tui-btnbox-4" v-if="seckillStatus == 0">
        <view class="tui-flex-1">
          <tui-button height="68rpx" :size="26" type="warning" shape="circle" disabled>活动已结束</tui-button>
        </view>
        <view class="tui-flex-1">
          <tui-button height="68rpx" :size="26" type="danger" shape="circle" @click="openAlone">原价购买</tui-button>
        </view>
      </view>
    </view>

    <ProductWindow v-on:changeFun="changeFun" :attr="attr" :cartNum="cartNum"></ProductWindow>
    <StorePoster v-on:setPosterImageStatus="setPosterImageStatus" :posterImageStatus="posterImageStatus" :posterData="posterData"></StorePoster>
  </view>
</template>
<style scoped lang="less">
.noscroll {
  height: 100%;
  overflow: hidden;
}
</style>
<script>
import ProductConSwiper from '@/components/ProductConSwiper'
import CountDown from '@/components/CountDown'
import ProductWindow from '@/components/ProductWindow'
import StorePoster from '@/components/StorePoster'
import { getSeckillDetail } from '@/api/activity'
import { postCartAdd } from '@/api/store'
import { imageBase64 } from '@/api/public'
import { getCoupon, getCollectAdd, getCollectDel, getUserInfo } from '@/api/user'
const NAME = 'SeckillDetails'

export default {
  name: 'SeckillDetails',
  components: {
    ProductConSwiper,
    CountDown,
    ProductWindow,
    StorePoster,
  },
  props: {},
  data: function() {
    return {
      seckillStatus: '',
      domStatus: false,
      posterData: {
        image: '',
        title: '',
        price: '',
        code: '',
      },
      posterImageStatus: false,
      action: '',
      imgUrls: [],
      storeInfo: [],
      replyCount: 0,
      reply: [],
      cartNum: 1,
      attrTxt: '请选择',
      productValue: [],
      attrValue: '',
      attr: {
        cartAttr: false,
        productAttr: [],
        productSelect: {},
      },
      datatime: 0,
      userCollect: false,
    }
  },
  onShow: function() {
    this.mountedStart()
  },
  methods: {
    formatPrice(price, index) {
      console.log(price)
      if (price) {
        return price.split('.')[index]
      }
      return ''
    },
    onShareAppMessage: function() {
      return {
        title: this.storeInfo.title,
        imageUrl: this.storeInfo.image,
        path: 'pages/activity/GoodsSeckill/index?id=' + this.storeInfo.id + '&spread=' + uni.getStorageSync('uid') + '&pageType=good&codeType=routine',
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
    openAlone: function() {
      this.$yrouter.push({
        path: '/pages/shop/GoodsCon/index',
        query: {
          id: this.storeInfo.productId,
        },
      })
      // this.$yrouter.replace({ path: "/detail/" + this.storeInfo.productId });
    },
    routerGo(item) {
      this.$yrouter.push({
        path: '/pages/user/CustomerList/index',
      })
    },
    //收藏商品
    setCollect: function() {
      let that = this,
        id = that.storeInfo.id,
        category = 'seckill',
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
      console.log(this)
      let id = that.$yroute.query.id
      this.seckillStatus = that.$yroute.query.status
      that.datatime = parseInt(that.$yroute.query.time)
      getSeckillDetail(id).then(res => {
        that.userCollect = res.data.userCollect
        res.data.storeInfo.description = res.data.storeInfo.description.replace(/\<img/gi, '<img style="max-width:100%;height:auto;"')
        that.$set(that.attr, 'productAttr', res.data.productAttr)
        that.$set(that, 'productValue', res.data.productValue)
        that.$set(that, 'storeInfo', res.data.storeInfo)
        that.$set(that, 'imgUrls', res.data.storeInfo.sliderImageArr)
        that.$set(that, 'replyCount', res.data.replyCount)
        that.$set(that, 'reply', res.data.reply)
        that.posterData.image = that.storeInfo.image_base
        that.updateTitle()
        if (that.storeInfo.title.length > 30) {
          that.posterData.title = that.storeInfo.title.substring(0, 30) + '...'
        } else {
          that.posterData.title = that.storeInfo.title
        }
        that.posterData.price = that.storeInfo.price
        that.posterData.code = that.storeInfo.code_base
        // that.setProductSelect();
        that.domStatus = true
        that.DefaultSelect()
      })
    },
    updateTitle() {
      // document.title = this.storeInfo.title || this.$yroute.meta.title;
    },
    setPosterImageStatus: function() {
      // var sTop = document.body || document.documentElement;
      // sTop.scrollTop = 0;
      this.posterImageStatus = !this.posterImageStatus
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
        this.$set(this.attr.productSelect, 'price', productSelect.seckillPrice)
        this.$set(this.attr.productSelect, 'stock', productSelect.seckillStock)
        this.$set(this.attr.productSelect, 'unique', productSelect.unique)
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', value.sort().join(','))
        this.$set(this, 'attrTxt', '已选择')
      } else if (!productSelect && productAttr.length) {
        this.$set(this.attr.productSelect, 'store_name', this.storeInfo.title)
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.seckillPrice)
        this.$set(this.attr.productSelect, 'stock', 0)
        this.$set(this.attr.productSelect, 'unique', '')
        this.$set(this.attr.productSelect, 'cart_num', 0)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      } else if (!productSelect && !productAttr.length) {
        this.$set(this.attr.productSelect, 'store_name', this.storeInfo.title)
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.seckillPrice)
        this.$set(this.attr.productSelect, 'stock', this.storeInfo.seckillStock)
        this.$set(this.attr.productSelect, 'unique', this.storeInfo.unique || '')
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      }
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
    // ChangeCartNum: function (res) {
    //   var that = this;
    //   if (res) {
    //     if (that.attr.productSelect.cart_num < that.storeInfo.seckillStock) {
    //       that.attr.productSelect.cart_num += 1;
    //       this.cartNum += 1;
    //       console.log(this.cartNum)
    //     }
    //   } else {
    //     if (that.attr.productSelect.cart_num > 1) {
    //       that.attr.productSelect.cart_num -= 1;
    //       this.cartNum -= 1;
    //     }
    //   }
    // },
    ChangeCartNum: function(changeValue) {
      //changeValue:是否 加|减
      //获取当前变动属性
      let productSelect = this.productValue[this.attrValue]
      //如果没有属性,赋值给商品默认库存
      if (productSelect === undefined && !this.attr.productAttr.length) {
        productSelect = this.attr.productSelect
      }
      //无属性值即库存为0；不存在加减；
      if (productSelect === undefined) return
      let stock = productSelect.stock || 0
      let num = this.attr.productSelect
      if (changeValue) {
        if (num) num.cart_num++
        if (num.cart_num > this.storeInfo.num) {
          num.cart_num = this.storeInfo.num
          uni.showToast({
            title: '限购' + this.storeInfo.num,
            icon: 'none',
            duration: 2000,
          })
        }
        if (num.cart_num > stock) {
          this.$set(this.attr.productSelect, 'cart_num', stock)
          this.$set(this, 'cartNum', stock)
        } else {
          this.$set(this.attr.productSelect, 'cart_num', num.cart_num)
          this.$set(this, 'cartNum', num.cart_num)
        }
      } else {
        num.cart_num--
        if (num.cart_num < 1) {
          this.$set(this.attr.productSelect, 'cart_num', 1)
          this.$set(this, 'cartNum', 1)
        } else {
          this.$set(this.attr.productSelect, 'cart_num', num.cart_num)
          this.$set(this, 'cartNum', num.cart_num)
        }
      }
    },
    //选择属性；
    ChangeAttr: function(res) {
      // 修改了规格
      let productSelect = this.productValue[res.value]
      if (productSelect) {
        this.attr.productAttr[res.indexw].index = res.indexn
        this.$set(this.attr.productSelect, 'image', productSelect.image)
        this.$set(this.attr.productSelect, 'price', productSelect.seckillPrice)
        this.$set(this.attr.productSelect, 'stock', productSelect.seckillStock)
        this.$set(this.attr.productSelect, 'unique', productSelect.unique)
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', res.value)
        this.$set(this, 'attrTxt', '已选择')
      } else {
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.seckillPrice)
        this.$set(this.attr.productSelect, 'stock', 0)
        this.$set(this.attr.productSelect, 'unique', '')
        this.$set(this.attr.productSelect, 'cart_num', 0)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      }
    },
    selecAttrTap: function() {
      this.attr.cartAttr = true
      this.isOpen = true
    },
    tapBuy: function() {
      var that = this
      if (that.attr.cartAttr == false) {
        that.attr.cartAttr = !this.attr.attrcartAttr
      } else {
        var data = {}
        data.productId = that.storeInfo.productId
        data.cartNum = that.attr.productSelect.cart_num
        data.uniqueId = that.attr.productSelect.unique
        data.secKillId = that.storeInfo.id
        data.new = 1
        postCartAdd(data)
          .then(res => {
            that.$yrouter.push({
              path: '/pages/order/OrderSubmission/index',
              query: {
                id: res.data.cartId,
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
  },
}
</script>
<style scoped lang="less">
.product-con .nav {
  padding: 0 0.2 * 100rpx;
}

.product-con .footer .bnt .buy.bg-color-hui {
  background: #ccc;
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
  align-items: flex-end;
  justify-content: space-between;
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
  font-size: 24rpx;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.tui-right__box text {
  text-align: center;
  padding-bottom: 12rpx;
}

.tui-seckill__img {
  width: 146rpx;
  height: 26rpx;
  margin-bottom: 16rpx;
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

.tui-sale-info {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-top: 30rpx;
}

.tui-discount-box {
  background: #fff;
}

.tui-list-cell {
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

.tui-list-cell::after {
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
  padding: 0 12rpx;
  box-sizing: border-box;
}

.tui-right-flex {
  display: flex;
  align-items: center;
  justify-content: center;
}

.tui-flex-1 {
  flex: 1;
  padding: 6rpx;
}

.tui-img__clock {
  width: 30rpx;
  height: 30rpx;
  margin-right: 8rpx;
}
</style>
