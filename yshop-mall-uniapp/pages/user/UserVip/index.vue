<template>
  <view class="member-center">
    <view class="header">
      <view class="slider-banner banner">
        <swiper indicatorDots="true" @change="swiperChange">
          <block v-for="(item, vipListIndex) in vipList" :key="vipListIndex">
            <swiper-item>
              <view class="swiper-slide" :style="{ backgroundImage: 'url(' + item.image + ')' }">
                <!--            <image :src="item.icon" />-->
                <view class="name">{{ item.name }}</view>
                <view class="discount">
                  可享受商品折扣: {{ item.discount / 10 }}折
                  <text class="iconfont icon-zhekou"></text>
                </view>
                <view class="nav acea-row" v-if="item.grade == grade">
                  <view class="item" v-for="(val, indexn) in vipComplete" :key="indexn">
                    <view class="num">{{ val.newNumber }}</view>
                    <view>{{ val.realName }}</view>
                  </view>
                </view>
                <view class="lock" v-if="item.grade > grade">
                  <text class="iconfont icon-quanxianguanlisuozi"></text>该会员等级尚未解锁
                </view>
                <view class="lock" v-if="item.grade < grade">
                  <text class="iconfont icon-xuanzhong1"></text>已解锁更高等级
                </view>
              </view>
            </swiper-item>
          </block>
        </swiper>
      </view>
    </view>
    <view class="wrapper">
      <view class="title acea-row row-between-wrapper">
        <view>
          <text class="iconfont icon-jingyanzhi"></text>会员升级要求
        </view>
        <view class="num">
          <text class="current">{{ taskCount }}</text>
          /{{ vipRequire.length }}
        </view>
      </view>
      <view class="list">
        <view class="item" v-for="(item, vipCompleteIndex) in vipComplete" :key="vipCompleteIndex">
          <view class="top acea-row row-between-wrapper">
            <view class="name">
              {{ item.name}}
              <text class="iconfont icon-wenti" v-if="item.illustrate" @click="showGrow(item)"></text>
            </view>
            <view>{{ item.finish ? "已满足条件" : "未满足条件" }}</view>
          </view>
          <view class="cu-progress">
            <view class="bg-red" :style="{ width: item.speed + '%' }"></view>
          </view>
          <view class="experience acea-row row-between-wrapper">
            <view>{{ item.taskTypeTitle }}</view>
            <view>
              <text class="num">{{ item.newNumber }}</text>
              /{{ item.number }}
            </view>
          </view>
        </view>
      </view>
    </view>
    <Recommend></Recommend>
    <view class="growthValue" :class="growthValue === false ? 'on' : ''">
      <view class="pictrue">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/value.jpg`" />
        <text class="iconfont icon-guanbi3" @click="growthTap"></text>
      </view>
      <view class="conter">{{ illustrate }}</view>
    </view>
    <view class="mask" :hidden="growthValue" @click="growthTap"></view>
  </view>
</template>
<script>
// import { swiper, swiperSlide } from "vue-awesome-swiper";

import Recommend from "@/components/Recommend";
import { getVipInfo, getVipTask, setDetection } from "@/api/user";
export default {
  name: "Poster",
  components: {
    // swiper,
    // swiperSlide,
    Recommend
  },
  props: {},
  data: function() {
    return {
      vipList: [], //等级列表
      vipRequire: [], //等级要求
      vipComplete: [], //完成情况
      taskCount: 0, //任务数
      grade: 0, //当前会员等级
      swiperVip: {
        speed: 1000,
        effect: "coverflow",
        slidesPerView: "auto",
        centeredSlides: true,
        // loop: true,
        coverflowEffect: {
          rotate: 0, // 旋转的角度
          stretch: -20, // 拉伸   图片间左右的间距和密集度
          depth: 100, // 深度   切换图片间上下的间距和密集度
          modifier: 2, // 修正值 该值越大前面的效果越明显
          slideShadows: false // 页面阴影效果
        },
        observer: true,
        observeParents: true
      },
      loading: false,
      growthValue: true,
      illustrate: "",
      activeIndex: 0
    };
  },
  watch: {
    vipList: function() {
      let that = this;
      if (that.vipList.length > 0) {
        that.vipList.forEach(function(item, index) {
          if (item.isClear === false) {
            // that.swiper.slideTo(index);
            that.activeIndex = index;
            that.grade = item.grade;
          }
        });
      }
    }
  },
  computed: {
    swiper() {
      // return this.$refs.mySwiper.swiper;
    }
  },
  mounted: function() {
    let that = this;
    setDetection();
    that.getInfo();
    // that.swiper.on("slideChange", function() {
    //   that.activeIndex = that.swiper.activeIndex;
    //   that.getTask();
    // });
  },

  methods: {
    swiperChange: function(e) {
      let that = this;
      that.activeIndex = e.mp.detail.current;
      that.getTask();
    },
    growthTap: function() {
      this.growthValue = true;
    },
    getInfo: function() {
      let that = this;
      getVipInfo().then(
        res => {
          that.vipList = res.data.list;
          that.vipRequire = res.data.task.list;
          that.vipComplete = res.data.task.task;
          that.taskCount = res.data.task.reachCount;
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
    getTask: function() {
      let that = this;
      getVipTask(that.vipList[that.activeIndex].id).then(
        res => {
          that.vipRequire = res.data.list;
          that.vipComplete = res.data.task;
          that.taskCount = res.data.reachCount;
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
    showGrow: function(item) {
      if (this.illustrate != item.illustrate) this.illustrate = item.illustrate;
      this.growthValue = false;
    }
  }
};
</script>

<style lang="less">
.banner swiper {
  height: 328rpx;
}
.swiper-slide {
  margin: 0 auto;
}
</style>
