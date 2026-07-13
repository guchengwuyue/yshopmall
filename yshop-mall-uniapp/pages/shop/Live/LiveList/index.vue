<template>
  <view class="page_box">
    <view class="head_box">
      <view class="live-tab">
        <view class="live-tab__item" v-for="tab in liveTab" :key="tab.title" @tap="selTab(tab)">
          <view class="live-tab__item-name">{{ tab.name }}</view>
          <text class="live-tab__item--link" :class="{ 'live-tab__item--active': tabCur == tab.title }"></text>
        </view>
      </view>
    </view>
    <view class="content_box">
      <scroll-view scroll-y="true" @scrolltolower="loadMore" class="scroll-box">
        <view class="list-box">
          <block v-for="live in liveList" :key="live.roomId">
            <shop-live-card :detail="live"></shop-live-card>
          </block>
        </view>
        <view v-if="liveList.length" class="cu-load text-gray" :class="loadStatus"></view>
      </scroll-view>
    </view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
  </view>
</template>

<script>
  import {
    yxWechatLive
  } from "@/api/live";
  import ShopLiveCard from '@/components/ShopLiveCard.vue'
  import Loading from "@/components/Loading";

  export default {
    components: {
      ShopLiveCard,
      Loading
    },
    data() {
      return {
        tabCur: 'all',
        liveStatus: '',
        loaded: false,
        loading: false,
        liveTab: [{
            title: 'all',
            name: '全部',
            code: ''
          },
          {
            title: 'prevue',
            name: '预告',
            code: '102'

          },
          {
            title: 'living',
            name: '直播中',
            code: '101'

          },
          {
            title: 'lived',
            name: '已结束',
            code: '103'

          }
        ],
        liveList: [],
        loadStatus: '', //loading,over
        currentPage: 0,
        size: 10,
        lastPage: 0
      };
    },
    computed: {},
    onLoad() {
      this.getLiveList();
    },
    onHide() {},
    methods: {
      // 切换tab
      selTab(tab) {
        console.log(tab)
        this.tabCur = tab.title;
        this.liveStatus = tab.code;
        this.liveList = [];
        this.loaded=false;
        this.loading=false;
        this.getLiveListTab();
      },

      // 直播列表
      getLiveListTab() {
        let that = this;
        yxWechatLive({
          liveStatus: that.liveStatus,
          page: 0,
          size: that.size
        }).then(res => {
          that.liveList = [...that.liveList, ...res.data.content];
          that.lastPage = res.data.lastPage;
          this.loaded = res.data.content.length < that.size;
          this.loading = false;
        });
      },
      // 直播列表
      getLiveList() {
        let that = this;
        if (this.loading || this.loaded) return;
        this.loading = true;
        yxWechatLive({
          liveStatus: this.liveStatus,
          page: this.currentPage,
          size: this.size
        }).then(res => {
          that.liveList = that.liveList.concat(res.data.content)
          this.currentPage++;
          this.loaded = res.data.content.length < that.size;
          this.loading = false;
        });
      }
    },
    onReachBottom() {
      !this.loading && this.getLiveList();
    }
  };
</script>

<style lang="scss">
  // tab
  .live-tab {
    width: 100%;
    height: 96rpx;
    background: #fff;
    display: flex;
    align-items: center;

    &__item {
      flex: 1;
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
    }

    &__item-name {
      font-size: 28rpx;
      font-family: PingFang SC;
      font-weight: bold;
      color: rgba(51, 51, 51, 1);
      flex: 1;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    &__item--link {
      width: 68rpx;
      height: 4rpx;
      background: transparent;
      border-radius: 2rpx;
    }

    &__item--active {
      width: 68rpx;
      height: 4rpx;
      background: rgba(213, 166, 90, 1);
      border-radius: 2rpx;
    }
  }

  // 瀑布流 list
  .scroll-box {
    .list-box {
      width: 100%;
      -moz-column-count: 2;
      -webkit-column-count: 2;
      column-count: 2;
      padding: 25rpx;
    }
  }
</style>
