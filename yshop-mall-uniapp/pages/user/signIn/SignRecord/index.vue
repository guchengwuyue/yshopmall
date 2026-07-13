<template>
  <view class="sign-record" ref="container">
    <view class="list">
      <view class="item" v-for="(item, signListIndex) in signList" :key="signListIndex">
        <view class="data">{{ item.time }}</view>
        <view class="listn">
          <view
            class="itemn acea-row row-between-wrapper"
            v-for="(itemn, indexn) in item.list"
            :key="indexn"
          >
            <view>
              <view class="name line1">{{ itemn.title }}</view>
              <view>{{ itemn.addTime }}</view>
            </view>
            <view class="num font-color-red">+{{ itemn.number }}</view>
          </view>
        </view>
      </view>
    </view>
    <Loading :loaded="loadend" :loading="loading"></Loading>
  </view>
</template>
<script>
import { getSignMonth } from "@/api/user";
import Loading from "@/components/Loading";
export default {
  name: "SignRecord",
  components: {
    Loading
  },
  props: {},
  data: function() {
    return {
      page: 1,
      limit: 3,
      signList: [],
      loading: false,
      loadend: false,
      active: false
    };
  },
  mounted: function() {
    this.signListTap();
  },
    onReachBottom() {
    !this.loading && this.signListTap();
  },
  methods: {
    signListTap: function() {
      let that = this;
      if (that.loading) return; //阻止下次请求（false可以进行请求）；
      if (that.loadend) return; //阻止结束当前请求（false可以进行请求）；
      that.loading = true;
      getSignMonth(that.page, that.limit).then(res => {
        that.loading = false;
        //apply();js将一个数组插入另一个数组;
        that.signList.push.apply(that.signList, res.data.list);
        that.loadend = res.data.length < that.limit; //判断所有数据是否加载完成；
        that.page = that.page + 1;
      });
    }
  }
};
</script>
