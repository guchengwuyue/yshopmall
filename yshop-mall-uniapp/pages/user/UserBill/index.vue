<template>
  <view class="bill-details" ref="container">
    <view class="nav acea-row">
      <view class="item" :class="types == 0 ? 'on' : ''" @click="changeTypes(0)">全部</view>
      <view class="item" :class="types == 1 ? 'on' : ''" @click="changeTypes(1)">消费</view>
      <view class="item" :class="types == 2 ? 'on' : ''" @click="changeTypes(2)">充值</view>
    </view>
    <view class="sign-record">
      <view class="list">
        <view class="item" v-for="(item, listIndex) in list" :key="listIndex">
          <view class="data">{{ item.time }}</view>
          <view class="listn" v-for="(val, key) in item.list" :key="key">
            <view class="itemn acea-row row-between-wrapper">
              <view>
                <view class="name line1">{{ val.title }}</view>
                <view>{{ val.addTime }}</view>
              </view>
              <view
                class="num"
                :class="val.pm == 0 ? 'font-color-red' : ''"
              >{{ val.pm == 0 ? "-" : "+" }}{{ val.number }}</view>
            </view>
          </view>
        </view>
      </view>
    </view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
  </view>
</template>
<script>
import { getCommissionInfo } from "@/api/user";
import Loading from "@/components/Loading";
export default {
  name: "UserBill",
  components: {
    Loading
  },
  props: {},
  data: function() {
    return {
      types: "",
      where: {
        page: 1,
        limit: 5
      },
      list: [],
      loaded: false,
      loading: false
    };
  },
  watch: {
    "$yroute.query.types": function(newVal) {
      let that = this;
      if (newVal != undefined) {
        that.types = newVal;
        that.list = [];
        that.where.page = 1;
        that.loaded = false;
        that.loading = false;
        that.getIndex();
      }
    },
    types: function() {
      this.getIndex();
    }
  },
  mounted: function() {
    let that = this;
    that.types = that.$yroute.query.types;
    that.getIndex();
  },
  onReachBottom() {
    !this.loading && this.getIndex();
  },
  methods: {
    code: function() {
      this.sendCode();
    },
    changeTypes: function(val) {
      if (val != this.types) {
        this.types = val;
        this.list = [];
        this.where.page = 1;
        this.loaded = false;
        this.loading = false;
      }
    },
    getIndex: function() {
      let that = this;
      if (that.loaded == true || that.loading == true) return;
      that.loading = true;
      getCommissionInfo(that.where, that.types).then(
        res => {
          that.loading = false;
          that.loaded = res.data.length < that.where.limit;
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
    }
  }
};
</script>
