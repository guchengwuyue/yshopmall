<template>
  <view class="page_box">
    <view class="content_box">
      <scroll-view class="scroll-box" scroll-y enable-back-to-top scroll-with-animation @scrolltolower="loadMore">
        <view class="group-wrap" :style="{ background: background }">
          <view class="group-head x-bc">
            <text class="group-head__title">爆款拼团</text>
            <text class="group-head__notice">省钱省心限时拼</text>
          </view>
          <view class="group-box">
            <view class="goods-item" v-for="(groupon, index) in grouponList" :key="groupon.id">
              <activity-card
			  :cid="groupon.id" :title="groupon.title" :info="groupon.info" :img="groupon.image" :price="groupon.price" :productPrice="groupon.productPrice">
                <block slot="tag">
                  <view class="tag" v-if="index < 3">TOP{{ index + 1 }}</view>
                </block>
                <block slot="sell">
                  <view class="x-f">
                    <view class="sell-box">
                      <text class="cuIcon-hotfill"></text>
                      <text class="sell-num">已拼{{ groupon.sales }}件</text>
                    </view>
                    <text class="group-num">{{ groupon.people || 0 }}人团</text>
                  </view>
                </block>
                <block slot="btn">
					<button class="cu-btn buy-btn"
				@tap.stop="jump('/pages/activity/GroupDetails/index', { id: groupon.id })">马上拼</button></block>
              </activity-card>
            </view>
          </view>
        </view>
      </scroll-view>
    </view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
  </view>
</template>

<script>
import activityCard from './children/activity-card.vue'
import Loading from '@/components/Loading'

import { getCombinationList } from '@/api/activity'
export default {
  components: {
    activityCard,
    Loading,
  },
  data() {
    return {
      emptyData: {
        img: '/static/imgs/empty/empty_goods.png',
        tip: '还没有拼团商品噢，去首页看看吧~',
        path: '/pages/index/index',
        pathText: '去首页逛逛',
      },
      loaded: false,
      loading: false,
      lastPage: 0,
      currentPage: 1,
      limit: 10,
      grouponList: [],
    }
  },
  onLoad() {
    this.getGrouponList()
  },
  onPullDownRefresh() {},
  computed: {
    background() {
      return `url('${this.$VUE_APP_RESOURCES_URL}/images/index-bg.png') no-repeat`
    },
  },
  methods: {
    // 路由跳转
    jump(path, parmas) {
      this.$yrouter.push({
        path: path,
        query: parmas,
      })
    },
    // 加载更多
    loadMore() {},
    // 拼团列表
    getGrouponList() {
      let that = this
      if (this.loading || this.loaded) return
      this.loading = true
      getCombinationList({
        page: that.currentPage,
        limit: this.limit,
      }).then(res => {
        that.grouponList = that.grouponList.concat(res.data.storeCombinationQueryVos)
        this.currentPage++
        this.loaded = res.data.storeCombinationQueryVos.length < this.limit
        this.loading = false
      })

      // that.$api('goods.grouponList', {
      //   page: that.currentPage
      // }).then(res => {
      //   if (res.code === 1) {
      //     that.isLoading = false;
      //     that.grouponList = [...that.grouponList, ...res.data.data];
      //     // that.grouponList=[];
      //     that.lastPage = res.data.last_page;
      //     if (that.currentPage < res.data.last_page) {
      //       that.loadStatus = '';
      //     } else {
      //       that.loadStatus = 'over';
      //     }
      //   }
      // });
    },
  },
  onReachBottom() {
    !this.loading && this.getGrouponList()
  },
}
</script>

<style lang="scss">
.group-wrap {
  background-size: 100% 374rpx;
}

.group-head {
  padding: 0 25rpx;
  height: 100rpx;

  .group-head__title {
    font-size: 32rpx;
    font-family: PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
  }

  .group-head__notice {
    font-size: 26rpx;
    font-family: PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
  }
}

.group-box {
  width: 710rpx;
  background: linear-gradient(#fff, #f5f5f5);
  border-radius: 20rpx;
  margin: 0 auto;
  min-height: 1000rpx;

  .goods-item {
    border-radius: 20rpx;
    overflow: hidden;
    position: relative;
    margin-bottom: 20rpx;

    .tag {
      position: absolute;
      left: 0;
      top: 10rpx;
      z-index: 2;
      line-height: 35rpx;
      background: linear-gradient(132deg, rgba(255, 153, 93, 1), rgba(255, 99, 97, 1));
      border-radius: 0px 18rpx 18rpx 0px;
      padding: 0 10rpx;
      font-size: 24rpx;
      font-family: PingFang SC;
      font-weight: bold;
      color: rgba(255, 255, 255, 0.8);
    }

    /deep/.goods-right {
      width: 460rpx;

      .title {
        width: 460rpx;
      }

      .tip {
        width: 460rpx;
      }
    }

    .buy-btn {
      position: absolute;
      right: 0;
      bottom: -10rpx;
      width: 160rpx;
      height: 60rpx;
      background: linear-gradient(90deg, rgba(254, 131, 42, 1), rgba(255, 102, 0, 1));
      box-shadow: 0px 7rpx 6rpx 0px rgba(255, 104, 4, 0.22);
      border-radius: 30rpx;
      font-size: 28rpx;
      font-family: PingFang SC;
      font-weight: 500;
      color: #fff;
      padding: 0;
    }

    .group-num {
      font-size: 20rpx;
      font-family: PingFang SC;
      font-weight: 500;
      color: rgba(153, 153, 153, 1);
      margin-left: 20rpx;
    }

    .sell-box {
      background: rgba(255, 224, 226, 0.3);
      border-radius: 16rpx;
      line-height: 32rpx;
      padding: 0 10rpx;

      .sell-num {
        font-size: 20rpx;
        font-family: PingFang SC;
        font-weight: 400;
        color: rgba(247, 151, 156, 1);
      }

      .cuIcon-hotfill {
        font-size: 26rpx;
        color: #e1212b;
        margin-right: 8rpx;
      }
    }
  }
}
</style>
