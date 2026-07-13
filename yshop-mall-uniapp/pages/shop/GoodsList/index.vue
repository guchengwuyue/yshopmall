<template>
  <view class="productList" ref="container">
    <form @submit.prevent="submitForm">
      <view class="search bg-color-red acea-row row-between-wrapper">
        <view class="input acea-row row-between-wrapper">
          <input placeholder="搜索商品信息" v-model="where.keyword" />
          <text class="iconfont icon-sousuo" @click="submitForm"></text>
        </view>
        <view class="iconfont" :class="Switch === true ? 'icon-pailie' : 'icon-tupianpailie'" @click="switchTap"></view>
      </view>
    </form>
    <view class="nav acea-row row-middle">
      <view class="item" :class="title ? 'font-color-red' : ''" @click="set_where(0)">{{ title ? title : '默认' }}</view>
      <view class="item" @click="set_where(1)">
        价格
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/horn.png`" v-if="price === 0" />
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/up.png`" v-if="price === 1" />
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/down.png`" v-if="price === 2" />
      </view>
      <view class="item" @click="set_where(2)">
        销量
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/horn.png`" v-if="stock === 0" />
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/up.png`" v-if="stock === 1" />
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/down.png`" v-if="stock === 2" />
      </view>
      <!-- down -->
      <view class="item" :class="nows ? 'font-color-red' : ''" @click="set_where(3)">新品</view>
    </view>
    <view class="list acea-row row-between-wrapper" :class="Switch === true ? '' : 'on'" ref="container" v-if="!isIntegral">
      <view @click="goGoodsCon(item)" class="item" :class="Switch === true ? '' : 'on'" v-for="(item, productListIndex) in productList" :key="productListIndex" :title="item.storeName">
        <view class="pictrue" :class="Switch === true ? '' : 'on'">
          <image :src="item.image" :class="Switch === true ? '' : 'on'" />
        </view>
        <view class="text" :class="Switch === true ? '' : 'on'">
          <view class="name line1">{{ item.storeName }}</view>
          <view class="money font-color-red" :class="Switch === true ? '' : 'on'">
            ￥
            <text class="num">{{ item.price }}</text>
          </view>
          <view class="vip acea-row row-between-wrapper" :class="Switch === true ? '' : 'on'">
            <view class="vip-money">￥{{ item.otPrice }}</view>
            <view>已售{{ item.sales }}件</view>
          </view>
        </view>
      </view>
    </view>
    <view class="list acea-row row-between-wrapper" :class="Switch === true ? '' : 'on'" ref="container" v-if="isIntegral == 'true'">
      <view @click="goIntegralGoodsCon(item)" class="item" :class="Switch === true ? '' : 'on'" v-for="(item, productListIndex) in productList" :key="productListIndex" :title="item.storeName">
        <view class="pictrue" :class="Switch === true ? '' : 'on'">
          <image :src="item.image" :class="Switch === true ? '' : 'on'" />
        </view>
        <view class="text" :class="Switch === true ? '' : 'on'">
          <view class="name line1">{{ item.storeName }}</view>
          <view class="money font-color-red" :class="Switch === true ? '' : 'on'">
            <text class="num">{{ item.integral }}积分</text>
          </view>
          <view class="vip acea-row row-between-wrapper" :class="Switch === true ? '' : 'on'">
            <!-- <view class="vip-money">￥{{ item.otPrice }}</view> -->
            <view>已售{{ item.sales }}件</view>
          </view>
        </view>
      </view>
    </view>
    <Loading :loaded="loadend" :loading="loading"></Loading>
    <view class="noCommodity" style="background-color: #fff" v-if="productList.length === 0 && where.page > 1">
      <view class="noPictrue">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noGood.png`" class="image" />
      </view>
    </view>
    <Recommend v-if="productList.length === 0 && where.page > 1" :recommendLoading="recommendLoading" @changeRecommendLoading="changeRecommendLoading"></Recommend>
  </view>
</template>
<script>
import Recommend from '@/components/Recommend'
import { getProducts, getProductsIntegral } from '@/api/store'
import Loading from '@/components/Loading'

export default {
  name: 'GoodsList',
  components: {
    Recommend,
    Loading,
  },
  props: {},
  data: function() {
    const s = '',
      id = 0,
      title = ''
    return {
      hostProduct: [],
      productList: [],
      Switch: true,
      where: {
        page: 1,
        limit: 8,
        keyword: s,
        sid: id, //二级分类id
        news: 0,
        priceOrder: '',
        salesOrder: '',
      },
      title: title && id ? title : '',
      loadTitle: '',
      loading: false,
      loadend: false,
      price: 0,
      stock: 0,
      nows: false,
      recommendLoading: false,
      target: false,
      isIntegral: false
    }
  },
  watch: {
    title() {
      this.updateTitle()
    },
    isIntegral() {
      if (this.isIntegral) {
        uni.setNavigationBarTitle({
          title: '积分商品',
        })
      } else {
        uni.setNavigationBarTitle({
          title: '商品列表',
        })
      }
    },
    $yroute(to) {},
  },
  mounted: function() {
    const { s = '', id = 0, title = '', isIntegral = false } = this.$yroute.query

    this.where.keyword = s
    this.where.isIntegral = isIntegral == 'true' ? 1 : 0
    this.isIntegral = isIntegral
    this.updateTitle()
    this.getProductList()
  },
  onReachBottom() {
    this.recommendLoading = true
    !this.loading && this.getProductList()
  },
  onHide() {},
  methods: {
    changeRecommendLoading(recommendLoading) {
      this.recommendLoading = recommendLoading
    },
    goGoodsCon(item) {
      this.$yrouter.push({
        path: '/pages/shop/GoodsCon/index',
        query: {
          id: item.id,
          isIntegral: this.isIntegral,
        },
      })
    },
    goIntegralGoodsCon(item) {
      this.$yrouter.push({
        path: '/pages/shop/IntegralGoodsCon/index',
        query: {
          id: item.id,
          isIntegral: this.isIntegral,
        },
      })
    },
    updateTitle() {
      uni.setNavigationBarTitle({
        title: this.title,
      })
    },
    getProductList() {
      if (this.loading) {
        return
      }
      this.loading = true
      var that = this
      this.setWhere()
      const { s = '', id = 0, title = '' } = this.$yroute.query
      if (s !== this.where.keyword || id !== this.where.sid) {
        this.loadend = false
        this.loading = false
        this.where.page = 1
        this.where.sid = id
        this.title = title && id ? title : ''
        this.nows = false
        this.$set(this, 'productList', [])
        this.price = 0
        this.stock = 0
        // this.getProductList();
      }
      let q = this.where
      let getData = this.isIntegral !== 'true' ? getProducts : getProductsIntegral
      getData(q).then(res => {
        this.loading = false
        if (this.target) {
          this.productList = res.data
		  this.target = false
        } else {
          this.productList.push.apply(this.productList, res.data)
        }
		console.log(this.target)
        this.loadend = res.data.length < this.where.limit //判断所有数据是否加载完成；
        this.where.page = this.where.page + 1
      })
    },
    submitForm: function() {
      this.$set(this, 'productList', [])
      this.where.page = 1
      this.loadend = false
      this.loading = false
      this.getProductList()
    },
    //点击事件处理
    set_where: function(index) {
      const { s = '', id = 0, title = '', isIntegral = false } = this.$yroute.query

      let that = this
      switch (index) {
        case 0:
          this.where.keyword = ''
          break
        case 1:
          if (that.price === 0) that.price = 1
          else if (that.price === 1) that.price = 2
          else if (that.price === 2) that.price = 0
          that.stock = 0
          this.where.keyword = s
          break
        case 2:
          if (that.stock === 0) that.stock = 1
          else if (that.stock === 1) that.stock = 2
          else if (that.stock === 2) that.stock = 0
          that.price = 0
          this.where.keyword = s
          break
        case 3:
          that.nows = !that.nows
          this.where.keyword = s
          break
        default:
          break
      }
      this.productList = []
      that.$set(that, 'productList', [])
      that.where.page = 1
      that.target = true
      that.loadend = false
      that.getProductList()
    },
    //设置where条件
    setWhere() {
      if (this.price === 0) {
        this.where.priceOrder = ''
      } else if (this.price === 1) {
        this.where.priceOrder = 'asc'
      } else if (this.price === 2) {
        this.where.priceOrder = 'desc'
      }
      if (this.stock === 0) {
        this.where.salesOrder = ''
      } else if (this.stock === 1) {
        this.where.salesOrder = 'asc'
      } else if (this.stock === 2) {
        this.where.salesOrder = 'desc'
      }
      this.where.news = this.nows ? '1' : '0'
    },
    switchTap() {
      this.Switch = !this.Switch
    },
  },
}
</script>
<style scoped lang="less">
.noCommodity {
  border-top: 3px solid #f5f5f5;
  padding-bottom: 1px;
}
</style>
