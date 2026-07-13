<template>
  <view class="integral-details" ref="container">
    <view class="header">
      <view class="currentScore">当前积分</view>
      <view>{{ info.integral }}</view>
      <view class="line"></view>
      <!--<view class="nav acea-row">-->
      <!--<view class="item">-->
      <!--<view class="num">{{ info.sum_integral }}</view>-->
      <!--<view>累计积分</view>-->
      <!--</view>-->
      <!--<view class="item">-->
      <!--<view class="num">{{ info.deduction_integral }}</view>-->
      <!--<view>累计消费</view>-->
      <!--</view>-->
      <!--<view class="item">-->
      <!--<view class="num">{{ info.today_integral }}</view>-->
      <!--<view>今日获得</view>-->
      <!--</view>-->
      <!--</view>-->
    </view>
    <view class="wrapper">
      <view class="nav acea-row">
        <view
          class="item acea-row row-center-wrapper"
          :class="current === navListIndex ? 'on' : ''"
          v-for="(item, navListIndex) in navList"
          :key="navListIndex"
          @click="nav(navListIndex)"
        >
          <text class="iconfont" :class="item.icon"></text>
          {{ item.name }}
        </view>
      </view>
      <view class="list" :hidden="current !== 0">
        <!--<view class="tip acea-row row-middle">-->
        <!--<text class="iconfont icon-shuoming"><text-->
        <!--&gt;提示：积分数值的高低会直接影响您的会员等级-->
        <!--</view>-->
        <view
          class="item acea-row row-between-wrapper"
          v-for="(item, listIndex) in list"
          :key="listIndex"
        >
          <view>
            <text class="state">{{ item.title }}</text>
            <div>{{ item.createTime }}</div>
          </view>
          <text class="num" v-if="item.pm == 1">+{{ item.number }}</text>
          <text class="num font-color-red" v-if="item.pm == 0">-{{ item.number }}</text>
        </view>
      </view>
      <!--<view class="list2" :hidden="current !== 1">-->
      <!--<view class="item acea-row row-between-wrapper" @click="goHome()">-->
      <!--<view class="pictrue"><image :src="`${$VUE_APP_RESOURCES_URL}/images/score.png`" /></view>-->
      <!--<view class="name">购买商品可获得积分奖励</view>-->
      <!--<view class="earn">赚积分</view>-->
      <!--</view>-->
      <!--<view-->
      <!--class="item acea-row row-between-wrapper"-->
      <!--@click="goSignIn()"-->
      <!--&gt;-->
      <!--<view class="pictrue"><image :src="`${$VUE_APP_RESOURCES_URL}/images/score.png`" /></view>-->
      <!--<view class="name">每日签到可获得积分奖励</view>-->
      <!--<view class="earn">赚积分</view>-->
      <!--</view>-->
      <!--</view>-->
    </view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
  </view>
</template>
<script>
import { getIntegralList, postSignUser } from "@/api/user";
import Loading from "@/components/Loading";
import { dataFormat } from "@/utils";

export default {
  name: "Integral",
  components: {
    Loading,
  },
  props: {},
  data: function() {
    return {
      navList: [
        { name: "分值明细", icon: "icon-mingxi" }
        // { name: "分值提升", icon: "icon-tishengfenzhi" }
      ],
      current: 0,
      where: {
        page: 1,
        limit: 15
      },
      data: {
        sign: 1,
        integral: 1,
        all: 1
      },
      list: [],
      info: [],
      loaded: false,
      loading: false
    };
  },
  mounted: function() {
    this.getIntegral();
    this.getInfo();
  },
  onReachBottom() {
    !this.loading && this.getInfo();
  },
  methods: {
    dataFormat,
    goSignIn() {
      this.$yrouter.push("/pages/user/signIn/Sign/index");
    },
    goHome() {
      this.$yrouter.switchTab("/pages/home/index");
    },
    nav: function(index) {
      this.current = index;
    },
    getInfo: function() {
      let that = this;
      if (that.loaded == true || that.loading == true) return;
      that.loading = true;
      getIntegralList(that.where).then(
        res => {
          that.loading = false;
          that.loaded = res.data.length < that.where.limit;
          that.loadTitle = that.loaded ? "人家是有底线的" : "上拉加载更多";
          that.where.page = that.where.page + 1;
          that.list.push.apply(that.list, res.data);
        },
        err => {
          uni.showToast({
				title: err.msg || err.response.data.msg|| err.response.data.message,
				icon: 'none',
				duration: 2000
			});
        }
      );
    },
    getIntegral: function() {
      let that = this;
      postSignUser(that.data).then(
        res => {
          that.info = res.data;
        },
        err => {
          uni.showToast({
				title: err.msg || err.response.data.msg|| err.response.data.message,
				icon: 'none',
				duration: 2000
			});
        }
      );
    }
  }
};
</script>
<style lang="less">
.list {
}
</style>
