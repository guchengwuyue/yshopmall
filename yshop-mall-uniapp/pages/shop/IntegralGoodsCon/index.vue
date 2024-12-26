<template>
  <view :class="productConClass">
    <view v-if="storeInfo.id">
      <!-- 轮播图 -->
      <product-con-swiper :img-urls="storeInfo.sliderImageArr"></product-con-swiper>

      <!-- 商品信息描述 -->
      <view class="wrapper">
        <view class="share acea-row row-between row-bottom">
          <view class="money font-color-red">
            <text class="num">{{ attr.productSelect.integral || storeInfo.integral }}积分</text>
          </view>
        </view>
        <view class="introduce">{{ storeInfo.storeName }}</view>
        <view class="label acea-row row-between-wrapper">
          <text>库存:{{ storeInfo.stock }}{{ storeInfo.unitName }}</text>
          <text>销量:{{ storeInfo.sales }}{{ storeInfo.unitName }}</text>
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

      <!-- 用户评价 -->
      <view class="userEvaluation" v-if="replyCount">
        <view class="title acea-row row-between-wrapper">
          <view>用户评价({{ replyCount }})</view>
          <text @click="goEvaluateList(id)" class="praise">
            <text class="font-color-red">{{ replyChance }}%</text>好评率
            <text class="iconfont icon-jiantou"></text>
          </text>
        </view>
        <user-evaluation :reply="reply"></user-evaluation>
      </view>

      <!-- 商品详情 -->
      <view class="product-intro">
        <text class="title"><text>产品介绍</text></text>
        <view class="conter" v-html="storeInfo.description"></view>
      </view>

      <view style="height: 100rpx"></view>
      <!-- 操作栏 -->
      <view class="footer acea-row row-between-wrapper">
        <view class="item" @click="goHome">
          <view class="iconfont icon-shouye-xianxing"></view>
          <text>首页</text>
        </view>
        <view class="bnt in-bnt acea-row">
          <view class="buy" @click="tapBuy">
            <text>立即兑换</text>
          </view>
        </view>
      </view>

      <!-- 商品规格弹窗 -->
      <ProductWindow :isIntegral="isIntegral" v-on:changeFun="changeFun" :attr="attr" :cartNum="cart_num"></ProductWindow>
      <!-- 分享海报 -->
      <StorePoster v-on:setPosterImageStatus="setPosterImageStatus" :posterImageStatus="posterImageStatus" :posterData="posterData" :goodId="id"></StorePoster>
    </view>
  </view>
</template>

<script>
// import { swiper, swiperSlide } from "vue-awesome-swiper";

import ProductConSwiper from '@/components/ProductConSwiper'
import UserEvaluation from '@/components/UserEvaluation'
import CouponPop from '@/components/CouponPop'
import ProductWindow from '@/components/ProductWindow'
import StorePoster from '@/components/StorePoster'
import ShareInfo from '@/components/ShareInfo'
import { getProductDetail, postCartAdd, getCartCount, getProductCode } from '@/api/store'
import { getCoupon, getCollectAdd, getCollectDel, getUserInfo } from '@/api/user'
import cookie from '@/utils/store/cookie'
import { isWeixin, PosterCanvas, handleQrCode, handleUrlParam, getCurrentPageUrlWithArgs } from '@/utils'
import { wechatEvevt } from '@/libs/wechat'
import { imageBase64 } from '@/api/public'
import { mapGetters } from 'vuex'

export default {
  name: 'GoodsCon',
  components: {
    // swiper,
    // swiperSlide,
    ProductConSwiper,
    UserEvaluation,
    CouponPop,
    ProductWindow,
    StorePoster,
    ShareInfo,
  },
  data: function() {
    return {
      shareInfoStatus: false,
      weixinStatus: false,
      mapShow: false,
      mapKey: '',
      posterData: {
        image: '',
        title: '',
        price: '',
        code: '',
      },
      posterImageStatus: false,
      animated: false,
      coupon: {
        coupon: false,
        list: [],
      },
      attr: {
        cartAttr: false,
        productAttr: [],
        productSelect: {},
      },
      isOpen: false, //是否打开属性组件
      productValue: [],
      id: 0,
      storeInfo: {},
      couponList: [],
      attrTxt: '请选择',
      attrValue: '',
      cart_num: 1, //购买数量
      replyCount: '',
      replyChance: '',
      reply: [],
      priceName: 0,
      CartCount: 0,
      posters: false,
      banner: [{}, {}],
      swiperRecommend: {
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
        },
        autoplay: false,
        loop: false,
        speed: 1000,
        observer: true,
        observeParents: true,
      },
      goodList: [],
      systemStore: {},
      qqmapsdk: null,
      productConClass: 'product-con',
      tempName: '全国包邮',
      isIntegral: false,
    }
  },
  computed: mapGetters(['isLogin', 'location']),
  mounted: function() {
    this.$store.commit('get_to', 'goodcon')
    let url = handleQrCode()
    if (!url) {
      url = handleUrlParam(getCurrentPageUrlWithArgs())
    }
    this.coupons()
    if (url && url.id) {
      this.id = url.id
      let urlSpread = parseInt(url.spread)
      if (urlSpread) {
        cookie.set('spread', urlSpread)
      }
    } else {
      this.id = this._route.query.id
    }
    this.isIntegral = url.isIntegral == 'true'
    this.productCon()
    this.setOpenShare()
  },
  watch: {
    posterImageStatus(status) {
      console.log(status)
      if (status) {
        this.productConClass = 'noscroll product-con'
      } else {
        this.productConClass = 'product-con'
      }
    },
  },
  methods: {
    onShareAppMessage: function() {
      return {
        title: this.storeInfo.storeName,
        imageUrl: this.storeInfo.image,
        path: 'pages/shop/GoodsCon/index?id=' + this.storeInfo.id + '&spread=' + uni.getStorageSync('uid') + '&pageType=good&codeType=routine',
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
    goHome() {
      this.$yrouter.switchTab('/pages/home/index')
    },
    goShoppingCart() {
      this.$yrouter.switchTab('/pages/shop/ShoppingCart/index')
    },
    goCustomerList() {
      this.$yrouter.push({
        path: '/pages/user/CustomerList/index',
      })
    },
    goStoreList() {
      this.$yrouter.push({
        path: '/pages/shop/StoreList/index',
      })
    },
    goEvaluateList(id) {
      this.$yrouter.push({
        path: '/pages/shop/EvaluateList/index',
        query: {
          id,
        },
      })
    },
    telPhone(phoneNumber) {
      uni.makePhoneCall({
        phoneNumber: phoneNumber,
        fail() {
          console.log('取消拨打')
        },
      })
    },
    showChang: function(data) {
      this.$yrouter.push({
        path: '/pages/map/index',
        query: data,
      })
    },
    updateTitle() {
      // document.title = this.storeInfo.storeName || this.$yroute.meta.title;
    },
    setShareInfoStatus: function() {
      this.shareInfoStatus = !this.shareInfoStatus
      this.posters = false
    },
    shareCode: function() {
      var that = this
      getProductCode(that.id).then(res => {
        that.posterData.code = res.data.code
        that.listenerActionSheet()
      })
    },
    setPosterImageStatus: function() {
      this.posterImageStatus = !this.posterImageStatus
      this.posters = false
    },
    //产品详情接口；
    productCon: function() {
      let that = this
      let from = this.location
      if (this.$deviceType == 'app') {
        from.from = 'app'
      }
      uni.showLoading({
        title: '加载中',
        mask: true,
      })
      getProductDetail(that.id, from)
        .then(res => {
          res.data.storeInfo.description = res.data.storeInfo.description.replace(/\<img/gi, '<img style="max-width:100%;height:auto;"')
          that.$set(that, 'storeInfo', res.data.storeInfo)
          // 给 attr 赋值，将请求回来的规格赋值给 attr
          that.$set(that.attr, 'productAttr', res.data.productAttr)
          that.$set(that, 'productValue', res.data.productValue)
          that.$set(that, 'replyCount', res.data.replyCount)
          that.$set(that, 'replyChance', res.data.replyChance)
          that.reply = res.data.reply ? [res.data.reply] : []
          that.$set(that, 'reply', that.reply)
          that.$set(that, 'priceName', res.data.priceName)
          that.$set(that, 'tempName', res.data.tempName)
          that.posterData.image = that.storeInfo.image
          if (that.storeInfo.storeName.length > 30) {
            that.posterData.title = that.storeInfo.storeName.substring(0, 30) + '...'
          } else {
            that.posterData.title = that.storeInfo.storeName
          }
          that.posterData.price = that.storeInfo.price
          that.posterData.code = that.storeInfo.codeBase
          that.systemStore = res.data.systemStore
          let good_list = res.data.goodList || []
          let goodArray = []
          let count = Math.ceil(good_list.length / 6)
          for (let i = 0; i < count; i++) {
            var list = good_list.slice(i * 6, 6)
            if (list.length)
              goodArray.push({
                list: list,
              })
          }
          that.mapKay = res.data.mapKay
          that.$set(that, 'goodList', goodArray)
          that.updateTitle()
          that.DefaultSelect()
          that.getCartCount()
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
        .finally(() => {
          uni.hideLoading()
        })
    },
    //默认选中属性；
    DefaultSelect: function() {
      let productAttr = this.attr.productAttr
      let value = []
      for (let i = 0; i < productAttr.length; i++) {
        this.$set(productAttr[i], 'index', 0)
        value.push(productAttr[i].attrValueArr[0])
      }
      //sort();排序函数:数字-英文-汉字；
      let productSelect = this.productValue[value.sort().join(',')]
      if (productSelect && productAttr.length) {
        this.$set(this.attr.productSelect, 'store_name', this.storeInfo.storeName)
        this.$set(this.attr.productSelect, 'image', productSelect.image)
        this.$set(this.attr.productSelect, 'price', productSelect.price)
        this.$set(this.attr.productSelect, 'stock', productSelect.stock)
        this.$set(this.attr.productSelect, 'unique', productSelect.unique)
        this.$set(this.attr.productSelect, 'integral', productSelect.integral)
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', value.sort().join(','))
        this.$set(this, 'attrTxt', '已选择')
      } else if (!productSelect && productAttr.length) {
        this.$set(this.attr.productSelect, 'store_name', this.storeInfo.storeName)
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.price)
        this.$set(this.attr.productSelect, 'integral', this.storeInfo.integral)
        this.$set(this.attr.productSelect, 'stock', 0)
        this.$set(this.attr.productSelect, 'unique', '')
        this.$set(this.attr.productSelect, 'cart_num', 0)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      } else if (!productSelect && !productAttr.length) {
        this.$set(this.attr.productSelect, 'store_name', this.storeInfo.storeName)
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.price)
        this.$set(this.attr.productSelect, 'stock', this.storeInfo.stock)
        this.$set(this.attr.productSelect, 'integral', this.storeInfo.integral)
        this.$set(this.attr.productSelect, 'unique', this.storeInfo.unique || '')
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      }
    },
    //购物车；
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
        num.cart_num++
        if (num.cart_num > stock) {
          this.$set(this.attr.productSelect, 'cart_num', stock)
          this.$set(this, 'cart_num', stock)
        } else {
          this.$set(this.attr.productSelect, 'cart_num', num.cart_num)
          this.$set(this, 'cart_num', num.cart_num)
        }
      } else {
        num.cart_num--
        if (num.cart_num < 1) {
          this.$set(this.attr.productSelect, 'cart_num', 1)
          this.$set(this, 'cart_num', 1)
        } else {
          this.$set(this.attr.productSelect, 'cart_num', num.cart_num)
          this.$set(this, 'cart_num', num.cart_num)
        }
      }
    },
    //将父级向子集多次传送的函数合二为一；
    changeFun: function(opt) {
      if (typeof opt !== 'object') opt = {}
      let action = opt.action || ''
      let value = opt.value === undefined ? '' : opt.value
      this[action] && this[action](value)
    },
    //打开优惠券插件；
    couponTap: function() {
      let that = this
      that.coupons()
      that.coupon.coupon = true
    },
    changecoupon: function(msg) {
      this.coupon.coupon = msg
      this.coupons()
    },
    currentcoupon: function(res) {
      let that = this
      that.coupon.coupon = false
      that.$set(that.coupon.list[res], 'is_use', true)
    },
    //可领取优惠券接口；
    coupons: function() {
      let that = this,
        q = {
          page: 1,
          limit: 20,
        }
      getCoupon(q).then(res => {
        that.$set(that, 'couponList', res.data || [])
        that.$set(that.coupon, 'list', res.data)
      })
    },
    //打开属性插件；
    selecAttrTap: function() {
      this.attr.cartAttr = true
      this.isOpen = true
    },
    changeattr: function(msg) {
      // 修改了规格
      console.log(msg)
      this.attr.cartAttr = msg
      this.isOpen = false
    },
    //选择属性；
    ChangeAttr: function(res) {
      // 修改了规格

      let productSelect = this.productValue[res.value]
      console.log(productSelect)
      if (productSelect) {
        this.attr.productAttr[res.indexw].index = res.indexn
        this.$set(this.attr.productSelect, 'image', productSelect.image)
        this.$set(this.attr.productSelect, 'price', productSelect.price)
        this.$set(this.attr.productSelect, 'stock', productSelect.stock)
        this.$set(this.attr.productSelect, 'unique', productSelect.unique)
        this.$set(this.attr.productSelect, 'integral', productSelect.integral)
        this.$set(this.attr.productSelect, 'cart_num', 1)
        this.$set(this, 'attrValue', res.value)
        this.$set(this, 'attrTxt', '已选择')
      } else {
        this.$set(this.attr.productSelect, 'image', this.storeInfo.image)
        this.$set(this.attr.productSelect, 'price', this.storeInfo.price)
        this.$set(this.attr.productSelect, 'integral', this.storeInfo.price)
        this.$set(this.attr.productSelect, 'stock', 0)
        this.$set(this.attr.productSelect, 'unique', '')
        this.$set(this.attr.productSelect, 'cart_num', 0)
        this.$set(this, 'attrValue', '')
        this.$set(this, 'attrTxt', '请选择')
      }
    },
    //收藏商品
    setCollect: function() {
      let that = this,
        id = that.storeInfo.id,
        category = 'collect'
      if (that.storeInfo.userCollect) {
        getCollectDel(id, category).then(function() {
          that.storeInfo.userCollect = !that.storeInfo.userCollect
        })
      } else {
        getCollectAdd(id, category).then(function() {
          that.storeInfo.userCollect = !that.storeInfo.userCollect
        })
      }
    },
    //  点击加入购物车按钮
    joinCart: function() {
      //0=加入购物车
      this.goCat(0)
    },
    // 加入购物车；
    goCat: function(news) {
      let that = this,
        productSelect = that.productValue[this.attrValue]
      //打开属性
      if (that.attrValue) {
        //默认选中了属性，但是没有打开过属性弹窗还是自动打开让用户查看默认选中的属性
        that.attr.cartAttr = !that.isOpen ? true : false
      } else {
        if (that.isOpen) that.attr.cartAttr = true
        else that.attr.cartAttr = !that.attr.cartAttr
      }
      //只有关闭属性弹窗时进行加入购物车
      if (that.attr.cartAttr === true && that.isOpen === false) return (that.isOpen = true)
      //如果有属性,没有选择,提示用户选择
      if (that.attr.productAttr.length && productSelect === undefined && that.isOpen === true) {
        uni.showToast({
          title: '产品库存不足，请选择其它',
          icon: 'none',
          duration: 2000,
        })
        return
      }
      let q = {
        productId: that.id,
        cartNum: that.attr.productSelect.cart_num,
        new: news,
        uniqueId: that.attr.productSelect !== undefined ? that.attr.productSelect.unique : '',
      }
      postCartAdd(q)
        .then(function(res) {
          console.log(res)
          that.isOpen = false
          that.attr.cartAttr = false
          if (news) {
            if (!res.data) {
              uni.showToast({
                title: res.msg || res.data.msg || res.data.message,
                icon: 'none',
                duration: 2000,
              })
              return
            }
            that.$yrouter.push({
              path: '/pages/order/OrderSubmission/index',
              query: {
                id: res.data.cartId,
                isIntegral: that.isIntegral,
              },
            })
          } else {
            uni.showToast({
              title: '添加购物车成功',
              icon: 'success',
              duration: 2000,
              complete: () => {
                that.getCartCount(true)
              },
            })
          }
        })
        .catch(error => {
          console.log(error)
          that.isOpen = false
          uni.showToast({
            title: error.msg || error.response.data.msg || error.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    //获取购物车数量
    getCartCount: function(isAnima) {
      let that = this
      const isLogin = that.isLogin
      if (isLogin) {
        getCartCount({
          numType: 0,
        }).then(res => {
          that.CartCount = res.data.count
          //加入购物车后重置属性
          if (isAnima) {
            that.animated = true
            setTimeout(function() {
              that.animated = false
            }, 500)
          }
        })
      }
    },
    //立即购买；
    tapBuy: function() {
      //  1=直接购买
      this.goCat(1)
    },
    listenerActionSheet: function() {
      if (isWeixin() === true) {
        this.weixinStatus = true
      }
      this.posters = true
    },
    listenerActionClose: function() {
      this.posters = false
    },
    setOpenShare: function() {
      var data = this.storeInfo
      var href = this.location.href
      if (this.$deviceType == 'weixin') {
        if (this.isLogin) {
          getUserInfo().then(res => {
            href = href.indexOf('?') === -1 ? href + '?spread=' + res.data.uid : href + '&spread=' + res.data.uid
            var configAppMessage = {
              desc: data.storeInfo,
              title: data.storeName,
              link: href,
              imgUrl: data.image,
            }
            wechatEvevt(['updateAppMessageShareData', 'updateTimelineShareData'], configAppMessage)
              .then(res => {})
              .catch(res => {
                if (res.is_ready) {
                  res.wx.updateAppMessageShareData(configAppMessage)
                  res.wx.updateTimelineShareData(configAppMessage)
                }
              })
          })
        } else {
          var configAppMessage = {
            desc: data.storeInfo,
            title: data.storeName,
            link: href,
            imgUrl: data.image,
          }
          wechatEvevt(['updateAppMessageShareData', 'updateTimelineShareData'], configAppMessage)
            .then(res => {})
            .catch(res => {
              if (res.is_ready) {
                res.wx.updateAppMessageShareData(configAppMessage)
                res.wx.updateTimelineShareData(configAppMessage)
              }
            })
        }
      }
    },
  },
}
</script>

<style scoped lang="less">
.geoPage {
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  z-index: 10000;
}

.product-con .store-info {
  margin-top: 0.2 * 100rpx;
  background-color: #fff;
}

.product-con .store-info .title {
  padding: 0 0.3 * 100rpx;
  font-size: 0.28 * 100rpx;
  color: #282828;
  height: 0.8 * 100rpx;
  line-height: 0.8 * 100rpx;
  border-bottom: 0.01 * 100rpx solid #f5f5f5;
}

.product-con .store-info .info {
  padding: 0 0.3 * 100rpx;
  height: 1.26 * 100rpx;
}

.product-con .store-info .info .picTxt {
  width: 100%;
  display: flex;
  align-items: center;
}

.product-con .store-info .info .picTxt .pictrue {
  width: 0.76 * 100rpx;
  height: 0.76 * 100rpx;
  margin-right: 0.2 * 100rpx;
}

.product-con .store-info .info .picTxt .pictrue image {
  width: 100%;
  height: 100%;
  border-radius: 0.06 * 100rpx;
}

.product-con .store-info .info .picTxt .text {
  flex: 1;
}

.product-con .store-info .info .picTxt .text .name {
  font-size: 0.3 * 100rpx;
  color: #282828;
}

.product-con .store-info .info .picTxt .text .address {
  font-size: 0.24 * 100rpx;
  color: #666;
  margin-top: 0.03 * 100rpx;
}

.product-con .store-info .info .picTxt .text .address .iconfont {
  color: #707070;
  font-size: 0.18 * 100rpx;
  margin-left: 0.1 * 100rpx;
}

.product-con .store-info .info .picTxt .addressBox {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.product-con .store-info .info .picTxt .addressBox .iconfont {
  font-size: 0.4 * 100rpx;
}

.product-con .store-info .info .picTxt .addressBox .addressTxt {
  font-size: 0.24 * 100rpx;
  color: #eb3729;
}

.product-con .store-info .praise {
  font-size: 0.28 * 100rpx;
  color: #808080;
}

.product-con .store-info .praise .iconfont {
  font-size: 0.28 * 100rpx;
}

.product-con .superior {
  background-color: #fff;
  margin-top: 0.2 * 100rpx;
}

.product-con .superior .title {
  height: 0.98 * 100rpx;
}

.product-con .superior .title image {
  width: 0.3 * 100rpx;
  height: 0.3 * 100rpx;
}

.product-con .superior .title .titleTxt {
  margin: 0 0.2 * 100rpx;
  font-size: 0.3 * 100rpx;
  background-image: linear-gradient(to right, #f57a37 0%, #f21b07 100%);
  background-image: -webkit-linear-gradient(to right, #f57a37 0%, #f21b07 100%);
  background-image: -moz-linear-gradient(to right, #f57a37 0%, #f21b07 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.product-con .superior .slider-banner {
  width: 6.9 * 100rpx;
  margin: 0 auto;
  padding-bottom: 0.2 * 100rpx;
}

.product-con .superior .slider-banner .list {
  width: 100%;
  padding-bottom: 0.2 * 100rpx;
}

.product-con .superior .slider-banner .list .item {
  width: 2.15 * 100rpx;
  margin: 0 0.22 * 100rpx 0.3 * 100rpx 0;
  font-size: 0.26 * 100rpx;
}

.product-con .superior .slider-banner .list .item:nth-of-type(3n) {
  margin-right: 0;
}

.product-con .superior .slider-banner .list .item .pictrue {
  width: 100%;
  height: 2.15 * 100rpx;
}

.product-con .superior .slider-banner .list .item .pictrue image {
  width: 100%;
  height: 100%;
  border-radius: 0.06 * 100rpx;
}

.product-con .superior .slider-banner .list .item .name {
  color: #282828;
  margin-top: 0.12 * 100rpx;
}

.product-con .superior .slider-banner .swiper-pagination-bullet {
  background-color: #999;
}

.product-con .superior .slider-banner .swiper-pagination-bullet-active {
  background-color: #e93323;
}

.mask {
  -webkit-filter: blur(2px);
  -moz-filter: blur(2px);
  -ms-filter: blur(2px);
  filter: blur(2px);
}

.product-con .product-intro .conter view {
  width: 100% !important;
}

.generate-posters {
  width: 100%;
  height: 1.7 * 100rpx;
  background-color: #fff;
  position: fixed;
  left: 0;
  bottom: 0;
  z-index: 99;
  transform: translate3d(0, 100%, 0);
  -webkit-transform: translate3d(0, 100%, 0);
  -ms-transform: translate3d(0, 100%, 0);
  -moz-transform: translate3d(0, 100%, 0);
  -o-transform: translate3d(0, 100%, 0);
  transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
  -webkit-transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
  -moz-transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
  -o-transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
}

.generate-posters.on {
  transform: translate3d(0, 0, 0);
  -webkit-transform: translate3d(0, 0, 0);
  -ms-transform: translate3d(0, 0, 0);
  -moz-transform: translate3d(0, 0, 0);
  -o-transform: translate3d(0, 0, 0);
}

.generate-posters .item {
  flex: 50%;
  -webkit-flex: 50%;
  -ms-flex: 50%;
  text-align: center;
}

.generate-posters .item .iconfont {
  font-size: 0.8 * 100rpx;
  color: #5eae72;
}

.generate-posters .item .iconfont.icon-haibao {
  color: #5391f1;
}

.noscroll {
  height: 100%;
  overflow: hidden;
}
.product-con .footer {
  .in-bnt {
    width: auto;
    flex: 1;
    margin-left: 30rpx;
    & > view {
      width: 100%;
      border-radius: 50rpx;
    }
  }
}
</style>
