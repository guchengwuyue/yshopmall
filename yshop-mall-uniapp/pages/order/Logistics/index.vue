<template>
  <view class="logistics">
    <view class="header acea-row row-between row-top" v-for="cart in cartInfo" :key="cart.id">
      <view class="pictrue">
        <image :src="cart.productInfo.image" />
      </view>
      <view class="text acea-row row-between">
        <view class="name line2">{{ cart.productInfo.store_name }}</view>
        <view class="money">
          <view>￥{{ cart.truePrice }}</view>
          <view>x{{ cart.cart_num }}</view>
        </view>
      </view>
    </view>
    <view class="logisticsCon" style="margin-bottom: 5px">
      <view class="company acea-row row-between-wrapper">
        <view class="picTxt acea-row row-between-wrapper">
          <view class="iconfont icon-wuliu"></view>
          <view class="text">
            <view>
              <text class="name line1">物流公司：</text>
              {{ orderInfo.deliveryName }}
            </view>
            <view class="express line1">
              <text class="name">快递单号：</text>
              {{ orderInfo.deliveryId }}
            </view>
          </view>
        </view>
        <!-- #ifndef H5 -->
        <view class="copy acea-row row-center-wrapper copy-data" @click="copyClipboard(orderInfo.deliveryId)">复制单号</view>
        <!-- #endif -->
      </view>
      <view class="item" v-for="(express, expressListIndex) in expressList" :key="expressListIndex">
        <view class="circular" :class="expressListIndex === 0 ? 'on' : ''"></view>
        <view class="text">
          <view :class="expressListIndex === 0 ? 'font-color-red' : ''">{{ express.status }}</view>
          <view class="data">{{ express.time }}</view>
        </view>
      </view>
    </view>

    <!-- 物流进度条 -->
    <view class="div-bg bg-white" style="font-size:12px; background:#fff;">
      <!--物流跟踪-->
      <view style="margin-bottom:5px;">
        <view class="bg-white" style="width: 92%; margin-left: 4%;margin: auto;padding-left: 15px;padding-right: 15px;padding-top: 10px">
          <view style="font-size: 26rpx;color: #111111; margin: 5px 0">
            物流跟踪
            <!--物流跟踪-->
          </view>
          <view>
            <view class="track-rcol">
              <view class="track-list">
                <view>
                  <view class="track-list-item" v-for="(item, logisticsListindex) in logisticsList" :key="logisticsListindex">
                    <view class="active" v-if="logisticsListindex === 0">
                      <view></view>
                      <i class="node-icon"></i>
                      <text class="txt">{{ item.AcceptStation }}</text>
                      <text class="time">{{ item.AcceptTime }}</text>
                    </view>
                    <view v-if="logisticsListindex > 0 && logisticsListindex !== logisticsList.length - 1">
                      <i class="node-icon"></i>
                      <text class="txt">{{ item.AcceptStation }}</text>
                      <text class="time">{{ item.AcceptTime }}</text>
                    </view>
                    <view v-if="logisticsListindex === logisticsList.length - 1" class="finall">
                      <i class="div-spilander"></i>
                      <i class="node-icon"></i>
                      <text class="txt">{{ item.AcceptStation }}</text>
                      <text class="time">{{ item.AcceptTime }}</text>
                    </view>
                  </view>
                </view>
              </view>
            </view>
          </view>
        </view>
      </view>
    </view>

    <view class="no-express" v-if="loaded && !expressList.length">
      <image :src="`${$VUE_APP_RESOURCES_URL}/images/noExpress.png`" />
    </view>
    <Recommend></Recommend>
  </view>
</template>
<script>
import Recommend from '@/components/Recommend'
import { express, orderDetail } from '@/api/order'
import { copyClipboard } from '@/utils'

const NAME = 'Logistics'

export default {
  name: NAME,
  components: {
    Recommend,
  },
  data: function() {
    return {
      id: '',
      cartInfo: [],
      orderInfo: {},
      expressList: [],
      loaded: false,
      logisticsList: [
        {
          message: '暂无数据',
          messageDate: '',
        },
      ],
    }
  },
  watch: {
    $yroute(n) {
      if (n.name === NAME && this.$yroute.query.id !== this.id) {
        this.id = this.$yroute.query.id
        this.getExpress()
      }
    },
  },
  mounted: function() {
    this.id = this.$yroute.query.id
    this.getExpress()
  },
  methods: {
    copyClipboard,
    getExpressInfo() {
      let params = {
        orderCode: this.id,
        shipperCode: this.orderInfo.deliverySn,
        logisticCode: this.orderInfo.deliveryId,
      }
      express(params)
        .then(res => {
          this.logisticsList = res.data.Traces.reverse();
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    getExpress() {
      if (!this.id) {
        uni.showToast({
          title: err.msg || err.response.data.msg || err.response.data.message,
          icon: 'none',
          duration: 2000,
        })
        return
      }
      this.loaded = false
      orderDetail(this.id)
        .then(res => {
          this.orderInfo = {
            deliveryId: res.data.deliveryId,
            deliveryName: res.data.deliveryName,
            deliverySn: res.data.deliverySn,
          }
          this.getExpressInfo()
          // const result = res.data.express.result || {};
          // this.cartInfo = res.data.order.cartInfo;
          // this.expressList = result.list || [];
          // this.loaded = true;
        })
        .catch(err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        })
    },
  },
}
</script>

<style scoped lang="less">
.no-express {
  margin: 1.5 * 100rpx 0;
}

.no-express image {
  width: 6 * 100rpx;
  margin: 0 auto;
  display: block;
}

.message-text {
  font-family: MicrosoftYaHei;
  font-size: 1 * 100rpx;
  font-weight: normal;
  font-stretch: normal;
  line-height: 3 * 100rpx;
  letter-spacing: 0 * 100rpx;
  color: #333333;
  width: 50%;
}

.fontblack {
  color: #999999;
}

.img2 {
  width: 0.81 * 100rpx;
  height: 0.8 * 100rpx;
  float: right;
}

.addressshow2 {
  height: auto;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  width: 75%;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
  word-wrap: break-word;
  word-break: break-all;
  font-size: 1 * 100rpx;
}

.addressshow1 {
  height: auto;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  width: 75%;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  white-space: normal;
  word-wrap: break-word;
  word-break: break-all;
  font-size: 1 * 100rpx;
}

.orderTitle {
  font-size: 1 * 100rpx;
  color: #333333;
  height: auto;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
  word-wrap: break-word;
  word-break: break-all;
  height: 2.5 * 100rpx;
}

.orderDetail {
  font-size: 0.26 * 100rpx;
  color: #666666;
  text-align: left;
}

.border-ceter {
  width: 92%;
  padding-left: 15px;
  padding-right: 15px;
}

.pay-button {
  width: 88%;
  height: 2.6 * 100rpx;
  position: relative;
  background-color: red;
  color: white;

  margin-left: 6%;
}

ul view {
  list-style: none;
  font-size: 0.24 * 100rpx;
}

ul {
}

.track-rcol {
}

.track-list {
  position: relative;
}

.track-list > view {
  position: relative;
  padding: 0 0 0.5 * 100rpx 20rpx;
}
.track-list .track-list-item {
  position: relative;
  padding: 0 0 0.5 * 100rpx 20rpx;
  line-height: 0.3 * 100rpx;
  border-left: 1px solid #d9d9d9;
  color: #999;
}

.track-list view.first {
  color: red;
  padding-top: 0;
  width: 100%;
  text-align: left;
  border-left: 1px solid #d9d9d9;
}

.track-list view.node-icon {
  position: absolute;
  left: -6.5px;
  border-radius: 50%;
  width: 0.2 * 100rpx;
  height: 0.2 * 100rpx;
  top: 4px;
  background-color: #b2b2b2;
  padding: 0 !important;
}

.track-list view.active .node-icon {
  background-position: 0 -72px;
  background-color: #ea7c0a;
  width: 0.3 * 100rpx;
  z-index: 2;
  height: 0.3 * 100rpx;
  position: absolute;
  left: -0.19 * 100rpx;
  top: 0;
  border-radius: 50%;
}

.track-list view.time {
  margin-right: 20px;
  position: relative;
  top: 4px;
  display: inline-block;
  vertical-align: middle;
  color: #999;
  width: 100%;
  text-align: left;
}

.track-list view.txt {
  position: relative;
  display: inline-block;
  vertical-align: top;
  color: #999;
  left: 0.2 * 100rpx;
  top: 0.04 * 100rpx;
}

.track-list view.first .time {
  text-align: left;
  width: 94%;
  color: red;
}

.track-list view.first .txt {
  color: red;
  text-align: left;
  width: 94%;
}

.track-list view.finall {
  padding: 0px 0 0.5 * 100rpx 5px;
  line-height: 18px;
  border-color: white;
  border-left: 1px solid #ffffff;
  color: #999;
}

.track-list view.finall .div-spilander {
  width: 1px;
  position: absolute;
  left: -1.5px;
  height: 0.5 * 100rpx;
  background-color: #d9d9d9;
}
</style>
