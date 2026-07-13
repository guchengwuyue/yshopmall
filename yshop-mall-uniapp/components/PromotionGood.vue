<template>
  <view>
    <view class="sh-title-card mb10">
      <view class="title-box">
        <image class="title-bg" :src="`${$VUE_APP_RESOURCES_URL}/images/title1.png`" mode="aspectFill" />
        <view class="title-text">为你推荐</view>
        <!-- <view class="title-text" :style="{ color: detail.color }">为你推荐</view> -->
      </view>
    </view>
    <view class="hot-goods mx20 mb10" v-if="benefit.length">
      <view class="goods-list x-f">
        <view class="goods-item" v-for="(item, promotionGoodIndex) in benefit" :key="promotionGoodIndex">
          <view class="goods-box" @tap="routerGo(item)">
            <view class="img-box">
              <!-- <image class="tag-img" :src="item.image" mode=""></image> -->
              <image class="img" :src="item.image" lazy-load mode="aspectFill"></image>
            </view>
            <view class="tip one-t">{{ item.storeName }}</view>
            <view class="title more-t">{{ item.storeName }}</view>
            <view class="price-box">
              <view class="flex x-bc align-end">
                <view class="current">{{ item.price }} </view>
                <view class="sales miso-font">仅剩：{{ item.stock }}{{ item.unitName }}</view>
              </view>
              <view class="x-f tag-box">
                <!-- <view class="discount">新人礼</view>
					<view class="discount">满100减60</view> -->
              </view>
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>
<script>
export default {
  name: 'PromotionGood',
  props: ['benefit'],
  data: function() {
    return {}
  },
  methods: {
    routerGo(item) {
      this.$yrouter.push({
        path: '/pages/shop/GoodsCon/index',
        query: {
          id: item.id,
        },
      })
    },
  },
  mounted() {},
}
</script>

<style lang="scss">
.sh-title-card {
  width: 750rpx;
}

.title-box {
  width: 710rpx;
  height: 88rpx;
  margin: 0 auto;
  position: relative;
  border-radius: 30rpx;

  .title-bg {
    width: 100%;
    height: 100%;
  }

  .title-text {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-weight: bold;
  }
}

.goods-box {
  width: 345rpx;
  background: #fff;
  padding-bottom: 20rpx;
  border-radius: 20rpx;
  overflow: hidden;

  .img-box {
    width: 345rpx;
    height: 345rpx;
    overflow: hidden;
    position: relative;

    .tag-img {
      position: absolute;
      left: 0;
      top: 0;
      z-index: 2;
      width: 80rpx;
      height: 40rpx;
    }

    .img {
      width: 345rpx;
      height: 345rpx;
      background-color: #ccc;
    }
  }

  .tip {
    width: 346rpx;
    line-height: 56rpx;
    background: rgba(246, 242, 234, 1);
    font-size: 22rpx;
    font-family: PingFang SC;
    font-weight: 400;
    color: rgba(168, 112, 13, 1);
    padding: 0 20rpx;
  }

  .title {
    font-size: 24rpx;
    font-family: PingFang SC;
    font-weight: 500;
    line-height: 36rpx;
    height: 72rpx;
    margin: 20rpx 20rpx 10rpx;
  }

  .price-box {
    padding: 10rpx 20rpx 0;
    width: 344rpx;
    box-sizing: border-box;

    .sales {
      font-size: 20rpx;
      font-family: PingFang SC;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      line-height: 20rpx;
      margin-bottom: 20rpx;
    }

    .current {
      font-size: 30rpx;
      font-weight: 500;
      color: rgba(225, 33, 43, 1);
      line-height: 30rpx;
      margin-bottom: 20rpx;

      &:before {
        content: '￥';
        font-size: 26rpx;
      }
    }

    .original {
      font-size: 22rpx;
      font-weight: 400;
      text-decoration: line-through;
      color: rgba(153, 153, 153, 1);
      margin-left: 14rpx;
      line-height: 22rpx;
      margin-bottom: 10rpx;

      &:before {
        content: '￥';
        font-size: 20rpx;
      }
    }

    .tag-box {
      .discount {
        line-height: 28rpx;
        border: 1rpx solid rgba(225, 33, 43, 1);
        border-radius: 8rpx;
        font-size: 18rpx;
        font-family: PingFang SC;
        font-weight: 500;
        color: rgba(225, 33, 43, 1);
        padding: 0 8rpx;
        margin-right: 10rpx;
      }
    }
  }
}

// 为你推荐
.hot-goods {
  // background: linear-gradient(#fff 200rpx, #f6f6f6 500rpx, #f6f6f6);
  // border-radius: 20rpx;

  .goods-list {
    flex-wrap: wrap;
    width: 710rpx;

    .goods-item {
      margin-right: 20rpx;
      margin-bottom: 20rpx;
      width: 345rpx;
      box-shadow: 0px 0px 10rpx 4rpx rgba(199, 199, 199, 0.22);
      border-radius: 20rpx;

      &:nth-child(2n) {
        margin-right: 0;
      }
    }
  }
}
</style>
