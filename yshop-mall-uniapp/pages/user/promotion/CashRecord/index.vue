<template>
  <view class="commission-details" ref="container">
    <view class="promoterHeader bg-color-red">
      <view class="headerCon acea-row row-between-wrapper">
        <view>
          <view class="name">提现记录</view>
          <view class="money">
            ￥
            <text class="num">{{ commission }}</text>
          </view>
        </view>
        <view class="iconfont icon-jinbi1"></view>
      </view>
    </view>
    <view class="sign-record" ref="content">
      <view class="list">
        <view class="item" v-for="(item, infoIndex) in info" :key="infoIndex">
          <view class="data">{{ item.time }}</view>
          <view class="listn" v-for="(val, indexn) in item.list" :key="indexn">
            <view class="itemn acea-row row-between-wrapper">
              <view>
                <view class="name line1">{{ val.title }}</view>
                <view>{{ val.addTime }}</view>
              </view>
              <view class="num" v-if="val.pm == 1">+{{ val.number }}</view>
              <view class="num font-color-red" v-if="val.pm == 0">-{{ val.number }}</view>
            </view>
          </view>
        </view>
      </view>
    </view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
  </view>
</template>
<script>
import { getCommissionInfo, getSpreadInfo } from "@/api/user";
import Loading from "@/components/Loading";

export default {
  name: "CashRecord",
  components: {
    Loading
  },
  props: {},
  data: function() {
    return {
      info: [],
      commission: 0,
      where: {
        page: 1,
        limit: 3
      },
      types: 4,
      loaded: false,
      loading: false
    };
  },
  mounted: function() {
    this.getCommission();
    this.getIndex();
  },
  onReachBottom() {
    this.loading === false && this.getIndex();
  },
  methods: {
    getIndex: function() {
      let that = this;
      if (that.loading == true || that.loaded == true) return;
      that.loading = true;
      getCommissionInfo(that.where, that.types).then(
        res => {
          that.loading = false;
          that.loaded = res.data.length < that.where.limit;
          that.where.page = that.where.page + 1;
          that.info.push.apply(that.info, res.data);
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
    getCommission: function() {
      let that = this;
      getSpreadInfo().then(
        res => {
          that.commission = res.data.commissionCount;
        },
        err => {
          uni.showToast({
            title: err.msg || err.response.data.msg|| err.response.data.message,
            icon: "none",
            duration: 2000
          });
        }
      );
    }
  }
};
</script>
