<template>
  <view class="order-index" ref="container">
    <image :src="`${$VUE_APP_RESOURCES_URL}/images/orderIndex.png`" mode="widthFix" class="image" />
    <view class="header acea-row">
      <view class="item" @click="goAdminOrderList(0)">
        <view class="num">{{ census.orderCount.unpaidCount }}</view>
        <view>待付款</view>
      </view>
      <view class="item" @click="goAdminOrderList(1)">
        <view class="num">{{ census.orderCount.unshippedCount }}</view>
        <view>待发货</view>
      </view>
      <view class="item" @click="goAdminOrderList(2)">
        <view class="num">{{ census.orderCount.receivedCount }}</view>
        <view>待收货</view>
      </view>
      <view class="item" @click="goAdminOrderList(3)">
        <view class="num">{{ census.orderCount.evaluatedCount }}</view>
        <view>待评价</view>
      </view>
      <view class="item" @click="goAdminOrderList(4)">
        <view class="num">{{ census.orderCount.refundCount }}</view>
        <view>退款</view>
      </view>
    </view>
    <view class="wrapper">
      <view class="title">
        <text class="iconfont icon-shujutongji"></text>数据统计
      </view>
      <view class="list acea-row">
        <view class="item" @click="goStatistics({type:'price',time:'today'})">
          <view class="num">{{ census.orderTimeCount.todayPrice }}</view>
          <view>今日成交额</view>
        </view>
        <view class="item" @click="goStatistics({type:'price',time:'yesterday'})">
          <view class="num">{{ census.orderTimeCount.proPrice }}</view>
          <view>昨日成交额</view>
        </view>
        <view class="item" @click="goStatistics({type:'price',time:'month'})">
          <view class="num">{{ census.orderTimeCount.monthPrice }}</view>
          <view>本月成交额</view>
        </view>
        <view class="item" @click="goStatistics({type:'order',time:'today'})">
          <view class="num">{{ census.orderTimeCount.todayCount }}</view>
          <view>今日订单数</view>
        </view>
        <view class="item" @click="goStatistics({type:'order',time:'yesterday'})">
          <view class="num">{{ census.orderTimeCount.proCount }}</view>
          <view>昨日订单数</view>
        </view>
        <view class="item" @click="goStatistics({type:'order',time:'month'})">
          <view class="num">{{ census.orderTimeCount.monthCount }}</view>
          <view>本月订单数</view>
        </view>
      </view>
    </view>
    <view class="public-wrapper">
      <view class="title">
        <text class="iconfont icon-xiangxishuju"></text>详细数据
      </view>
      <view class="nav acea-row row-between-wrapper">
        <view class="data">日期</view>
        <view class="browse">订单数</view>
        <view class="turnover">成交额</view>
      </view>
      <view class="conter">
        <view
          class="item acea-row row-between-wrapper"
          v-for="(item, orderListIndex) in list"
          :key="orderListIndex"
        >
          <view class="data">{{ item.time }}</view>
          <view class="browse">{{ item.count }}</view>
          <view class="turnover">{{ item.price }}</view>
        </view>
      </view>
    </view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
  </view>
</template>
<script>
import { getStatisticsInfo, getStatisticsMonth } from "@/api/admin";
import Loading from "@/components/Loading";
export default {
  name: "OrderIndex",
  components: {
    Loading
  },
  props: {},
  data: function() {
    return {
      census: {
        orderCount: {},
        orderTimeCount: {}
      },
      list: [],
      where: {
        page: 1,
        limit: 15
      },
      loaded: false,
      loading: false
    };
  },
  mounted: function() {
    this.getIndex();
    this.getList();
  },
  onReachBottom() {
    !this.loading && this.getList();
  },
  methods: {
    goStatistics(query) {
      this.$yrouter.push({
        path: "/pages/orderAdmin/Statistics/index",
        query
      });
    },
    goAdminOrderList(types) {
      this.$yrouter.push({
        path: "/pages/orderAdmin/AdminOrderList/index",
        query: { types }
      });
    },
    getIndex: function() {
      var that = this;
      getStatisticsInfo().then(
        res => {
          that.census = res.data;
          that.census.unpaidCount = that.census.orderCount.unpaidCount;
        },
        err => {
          uni.showToast({
            title:
              err.msg || err.response.data.msg || err.response.data.message,
            icon: "none",
            duration: 2000
          });
        }
      );
    },
    getList: function() {
      var that = this;
      if (that.loading || that.loaded) return;
      that.loading = true;
      getStatisticsMonth(that.where).then(
        res => {
          that.loading = false;
          that.loaded = res.data.length < that.where.limit;
          that.list.push.apply(that.list, res.data);
          that.where.page = that.where.page + 1;
        },
        err => {
          uni.showToast({
            title:
              err.msg || err.response.data.msg || err.response.data.message,
            icon: "none",
            duration: 2000
          });
        },
        300
      );
    }
  }
};
</script>
