<template>
  <view class="container">
    <view class="tui-bg__box">
      <image :src="`${$VUE_APP_RESOURCES_URL}/images/bg_seckill.png`" class="tui-bg__img" mode="widthFix" :style="{ opacity: opacity }"></image>
    </view>
	
    <view class="tui-header__bg">
      <image :src="`${$VUE_APP_RESOURCES_URL}/images/bg_seckill.png`" class="tui-bg__img" mode="widthFix"></image>
      <scroll-view class="tui-time-slot" scroll-x>
        <view class="tui-time__list" :class="{ 'tui-flex__between': timeList.length < 6 }">
          <view class="tui-time__item" :class="[timeList.length < 6 ? 'tui-flex__1' : 'tui-width__min', index == active ? 'tui-time__active' : '']" v-for="(item, index) in timeList" :key="index" @tap="setTime(index)">
            <view class="tui-time">{{ item.time }}</view>
            <view class="tui-status">{{ item.state }}</view>
          </view>
        </view>
      </scroll-view>
    </view>
    <view class="tui-body">
      <block v-for="(item, index) in timeList" :key="index">
        <view class="tui-status__box" v-if="active == index">
          <view class="tui-full__width" v-if="item.status == 0">
            <tui-divider gradual width="80%" backgroundColor="#fff" :height="34">
              <view class="tui-divider__status">
                <image :src="`${$VUE_APP_RESOURCES_URL}/images/img_seckill.png`" mode="widthFix"></image>
                <text class="tui-color__red">{{ item.time }}</text>
                <text>{{ item.state }}</text>
              </view>
            </tui-divider>
          </view>
          <view class="tui-full__width" v-if="item.status == 2">
            <tui-divider gradual width="80%" backgroundColor="#fff" :height="34">
              <view class="tui-divider__status">
                <image :src="`${$VUE_APP_RESOURCES_URL}/images/img_seckill.png`" mode="widthFix"></image>
                <text class="tui-color__red">{{ item.time }}</text>
                <text>{{ item.state }}</text>
              </view>
            </tui-divider>
          </view>
          <view class="tui-countdown__box" v-if="item.status == 1 || item.status == 2">
            <text>距离{{ item.status == 1 ? '结束还剩' : '开始还有' }}</text>
            <CountDown :isDay="true" :tipText="'倒计时 '" :dayText="' 天 '" :hourText="' 时 '" :minuteText="' 分 '" :secondText="' 秒'" :datatime="item.stop"></CountDown>
          </view>
        </view>
      </block>

      <view class="tui-list__goods">
        <view class="tui-goods__left">
          <block v-for="(item, index) in seckillList" :key="index">
            <t-goods-item v-if="index % 2 == 0" :item="item" :timeList="timeList" :active="active" :isList="false" @goDetail="goDetail"></t-goods-item>
          </block>
        </view>
        <view class="tui-goods__right">
          <block v-for="(item, index) in seckillList" :key="index">
            <t-goods-item v-if="index % 2 !== 0" :item="item" :timeList="timeList" :active="active" :isList="false" @goDetail="goDetail"></t-goods-item>
          </block>
        </view>
      </view>
    </view>
  </view>
</template>
<script>
import { getSeckillConfig, getSeckillList } from '@/api/activity'
import CountDown from '@/components/CountDown'

// import { Tab, Tabs } from "vant-weapp";
import Loading from '@/components/Loading'

export default {
  name: 'GoodsSeckill',
  components: {
    CountDown,
  },
  props: {},
  data: function() {
    return {
      headerImg: '',
      timeList: [],
      sticky: false,
      loading: false,
      datatime: 0,
      active: 0,
      seckillList: [],
      status: false, //砍价列表是否获取完成 false 未完成 true 完成
      loadingList: false, //当前接口是否请求完成 false 完成 true 未完成
      page: 1, //页码
      limit: 5, //数量
      title: [],
      opacity: 1,
    }
  },
  mounted: function() {
    this.mountedStart()
  },
  onReachBottom() {
    !this.loadingList && this.getSeckillList()
  },
  filters: {
    getStatusText(status) {
      let text = ['活动已结束', '正在疯抢', '即将开抢'][status - 1]
      return text
    },
  },
  methods: {
    changeTime: function(index) {
      this.active = index
      this.getSeckillList()
    },
    mountedStart: function() {
      var that = this
      uni.showLoading()
      getSeckillConfig().then(res => {
        that.$set(that, 'headerImg', res.data.lovely)
        that.$set(that, 'timeList', res.data.seckillTime)
        that.$set(that, 'active', res.data.seckillTimeIndex)

        let title = []
        title = res.data.seckillTime.map((item, index) => {
          return {
            name: 'div',
            attrs: {
              class: 'timeItem',
            },
            children: [
              {
                name: 'div',
                attrs: {
                  class: 'time',
                },
                children: [
                  {
                    type: 'text',
                    text: item.time,
                  },
                ],
              },
              {
                name: 'div',
                attrs: {
                  class: 'state',
                },
                children: [
                  {
                    type: 'text',
                    text: item.state,
                  },
                ],
              },
            ],
          }
        })
        that.$set(that, 'title', title)
        that.datatime = that.timeList[that.active].stop
        that.getSeckillList()
        that.$nextTick(function() {
          that.sticky = true
          uni.hideLoading()
        })
      })
    },
    setTime: function(index) {
      var that = this
      that.page = 1
      that.loadingList = false
      that.status = false
      that.active = index
      that.datatime = that.timeList[that.active].stop
	  console.log(new Date(that.datatime))
      this.seckillList = []
      that.getSeckillList()
    },
    getSeckillList: function() {
      var that = this
      if (that.loadingList) return
      if (that.status) return
      var time = that.timeList[that.active].id
      getSeckillList(time, {
        page: that.page,
        limit: that.limit,
      }).then(res => {
        that.status = res.data.length < that.limit
        that.seckillList.push.apply(that.seckillList, res.data)
        that.page++
        uni.hideLoading()
      })
    },
    goDetail: function(item) {
      var that = this
      var time = that.timeList[that.active].stop
      this.$yrouter.push({
        path: '/pages/activity/SeckillDetails/index',
        query: {
          id: item.id,
          time,
          status: that.timeList[that.active].status,
        },
      })
    },
    onPageScroll(e) {
      let scrollTop = e.scrollTop
      if (scrollTop <= 2) {
        this.opacity = 1
      } else {
        if (this.opacity <= 0) return
        this.opacity = 1 - scrollTop / 40
      }
    },
  },
}
</script>
<style scoped lang="less">
.tui-bg__box {
  width: 100%;
  height: 210rpx;
  position: fixed;
  left: 0;
  top: 0;
  /* #ifdef H5 */
  top: 80rpx;
  /* #endif */
  z-index: 1;
}

.tui-header__bg {
  width: 100%;
  height: 120rpx;
  position: fixed;
  left: 0;
  top: 0;
  /* #ifdef H5 */
  top: 80rpx;
  /* #endif */
  z-index: 3;
  overflow: hidden;
}

.tui-bg__img {
  width: 100%;
  height: 210rpx;
  display: block;
  transition: opacity 0.1s linear;
}

.tui-body {
  width: 100%;
  position: relative;
  margin-top: 120rpx;
  z-index: 2;
  padding: 0 25rpx;
  box-sizing: border-box;
}

.tui-time-slot {
  width: 100%;
  height: 120rpx;
  position: absolute;
  left: 0;
  top: 0;
}

.tui-time__list {
  min-width: 100%;
  height: 120rpx;
  display: flex;
  align-items: center;
}

.tui-flex__between {
  justify-content: space-between;
}

.tui-time__item {
  flex-shrink: 0;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  color: #ffb2b2;
}

.tui-flex__1 {
  flex: 1 !important;
}

.tui-width__min {
  min-width: 150rpx;
}

.tui-time {
  font-size: 32rpx;
  line-height: 32rpx;
  font-weight: bold;
}

.tui-status {
  font-size: 24rpx;
  line-height: 24rpx;
  font-weight: 500;
  padding-top: 16rpx;
}

.tui-time__active .tui-time {
  color: #fff;
  font-size: 36rpx;
  line-height: 36rpx;
}

.tui-time__active .tui-status {
  color: #fff;
  font-size: 28rpx;
  line-height: 28rpx;
  font-weight: bold;
}

.tui-status__box {
  width: 100%;
  height: 146rpx;
  background: #fff;
  border-radius: 20rpx;
  box-shadow: 0 3rpx 20rpx rgba(183, 183, 183, 0.1);
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.tui-full__width {
  width: 100%;
}

.tui-divider__status {
  display: flex;
  align-items: center;
  font-size: 28rpx;
  color: #333;
  font-weight: bold;
}

.tui-divider__status image {
  width: 30rpx;
  height: 30rpx;
  margin-right: 10rpx;
  flex-shrink: 0;
}

.tui-color__red {
  color: #eb0909;
  padding-right: 6rpx;
  font-size: 32rpx;
  font-weight: 500;
}

.tui-countdown__box {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #333;
  font-size: 24rpx;
  font-weight: 400;
  margin-top: 16rpx;
}

.tui-countdown__box text {
  padding-right: 12rpx;
}

/*======商品列表 start=======*/
.tui-list__goods {
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  margin-top: 20rpx;
  padding-bottom: 30rpx;
}

.tui-goods__left,
.tui-goods__right {
  width: 49%;
}

.tui-full__width {
  width: 100% !important;
}

/*======商品列表 end=======*/

.tui-goods__item {
  width: 100%;
  padding: 20rpx 20rpx 36rpx;
  box-sizing: border-box;
  border-radius: 12rpx;
  background-color: #fff;
  margin-bottom: 4%;
  position: relative;
}
.tui-full__item {
  display: flex;
  margin-bottom: 20rpx !important;
  padding: 20rpx !important;
}
.tui-img__newguest {
  position: absolute;
  width: 96rpx;
  height: 32rpx;
  left: 0;
  top: 8rpx;
}
.tui-image__box {
  width: 100%;
  height: 300rpx;
}
.tui-full__imgbox {
  width: 240rpx !important;
  height: 240rpx !important;
  margin-right: 20rpx;
}
.tui-goods__img {
  max-width: 100%;
  max-height: 300rpx;
  display: block;
  border-radius: 8rpx;
}
.tui-full__img {
  max-height: 240rpx !important;
}
.tui-goods__content {
  width: 100%;
  padding-top: 16rpx;
}
.tui-full__content {
  height: 240rpx;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-top: 0 !important;
}
.tui-goods__title {
  font-size: 26rpx;
  font-weight: 400;
  color: #333;
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  margin-bottom: 20rpx;
}
.tui-tag__box {
  display: flex;
  padding-top: 25rpx;
  padding-bottom: 25rpx;
}
.tui-box__bottom {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.tui-price__box {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.tui-price {
  display: flex;
  align-items: flex-end;
  color: #eb0909;
}
.tui-price__small {
  font-size: 24rpx;
  line-height: 24rpx;
}
.tui-price__large {
  font-size: 34rpx;
  line-height: 32rpx;
  font-weight: 600;
}
.tui-price__original {
  font-size: 24rpx;
  line-height: 24rpx;
  text-decoration: line-through;
  color: #999;
  padding-top: 10rpx;
}
</style>
