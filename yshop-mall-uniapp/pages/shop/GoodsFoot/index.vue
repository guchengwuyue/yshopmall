<template>
  <view ref="container">
    <view class="collectionGoods" v-if="collectProductList.length > 0">
      <view class="item acea-row row-between-wrapper" v-for="(item, collectProductListIndex) in collectProductList" :key="collectProductListIndex" @click="goGoodsCon(item)">
        <view class="pictrue">
          <image :src="item.image" />
        </view>
        <view class="text acea-row row-column-between">
          <view class="infor line1">{{ item.storeName }}</view>
          <view class="acea-row row-between-wrapper">
            <view class="money font-color-red" v-if="isIntegral == 1">{{ item.costPrice }}积分</view>
            <view class="money font-color-red" v-else>￥{{ item.price }}</view>
            <view class="delete" @tap.stop="delCollection(collectProductListIndex)">删除</view>
          </view>
        </view>
      </view>
    </view>
    <Loading :loaded="loadend" :loading="loading"></Loading>
    <view class="noCommodity" style="background-color:#fff;" v-if="collectProductList.length < 1 && page > 1">
      <view class="noPictrue">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noCollection.png`" class="image" />
      </view>
      <Recommend></Recommend>
    </view>
  </view>
</template>
<script>
import Recommend from '@/components/Recommend'
import { getCollectUser, getCollectDel } from '@/api/user'
import Loading from '@/components/Loading'
export default {
  name: 'GoodsFoot',
  components: {
    Recommend,
    Loading,
  },
  props: {},
  data: function() {
    return {
      page: 1,
      limit: 20,
      type: 'foot',
      collectProductList: [],
      loadTitle: '',
      loading: false,
      loadend: false,
    }
  },
  mounted: function() {
    this.get_user_collect_product()
  },
  onReachBottom() {
    !this.loading && this.get_user_collect_product()
  },
  methods: {
    goGoodsCon(item) {
      if (item.isIntegral == 1) {
        this.$yrouter.push({
          path: '/pages/shop/GoodsCon/index',
          query: { id: item.pid },
        })
      } else {
        this.$yrouter.push({
          path: '/pages/shop/GoodsCon/index',
          query: { id: item.pid },
        })
      }
    },
    get_user_collect_product: function() {
      let that = this
      if (that.loading) return //阻止下次请求（false可以进行请求）；
      if (that.loadend) return //阻止结束当前请求（false可以进行请求）；
      that.loading = true
      getCollectUser(that.page, that.limit, that.type).then(res => {
        that.loading = false
        //apply();js将一个数组插入另一个数组;
        that.collectProductList.push.apply(that.collectProductList, res.data)
        that.loadend = res.data.length < that.limit //判断所有数据是否加载完成；
        that.page = that.page + 1
      })
    },
    //删除收藏；
    delCollection: function(index) {
      let that = this,
        id = that.collectProductList[index].pid,
        category = that.collectProductList[index].category
      getCollectDel(id, category).then(function() {
        uni.showToast({
          title: '删除成功',
          icon: 'success',
          duration: 2000,
          complete: () => {
            that.collectProductList.splice(index, 1)
            that.$set(that, 'collectProductList', that.collectProductList)
          },
        })
      })
    },
  },
}
</script>
