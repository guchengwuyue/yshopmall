<template>
  <view class="distribution-posters">
    <view class="slider-banner banner">
      <swiper indicatorDots="true">
        <block v-for="(item, infoIndex) in info" :key="infoIndex">
          <swiper-item>
            <image class="slide-image" :src="item.wap_poster" mode="widthFix" show-menu-by-longpress />
          </swiper-item>
        </block>
      </swiper>
    </view>
    <view class="keep bg-color-red" @click="saveImg">保存海报</view>
  </view>
</template>
<script>
// import { swiper, swiperSlide } from "vue-awesome-swiper";
import { getSpreadImg } from '@/api/user'

export default {
  name: 'Poster',
  components: {
    // swiper,
    // swiperSlide
  },
  props: {},
  data: function () {
    return {
      swiperPosters: {
        speed: 1000,
        effect: 'coverflow',
        slidesPerView: 'auto',
        centeredSlides: true,
        coverflowEffect: {
          rotate: 0, // 旋转的角度
          stretch: -20, // 拉伸   图片间左右的间距和密集度
          depth: 100, // 深度   切换图片间上下的间距和密集度
          modifier: 3, // 修正值 该值越大前面的效果越明显
          slideShadows: false, // 页面阴影效果
        },
        observer: true,
        observeParents: true,
      },
      info: [],
      activeIndex: 0,
    }
  },
  mounted: function () {
    this.getIndex()
    let that = this
    // this.swiper.on("slideChange", function() {
    //   that.activeIndex = that.swiper.activeIndex;
    // });
  },
  computed: {
    swiper() {
      // return this.$refs.mySwiper.swiper;
    },
  },
  methods: {
    getIndex: function () {
      let that = this
      getSpreadImg({
        from: this.$deviceType == 'weixin' || this.$deviceType == 'weixinh5' ? 'uniappH5' : this.$deviceType,
      }).then(
        res => {
          that.info = res.data
        },
        err => {
          uni.showToast({
            title: err.msg || err.response.data.msg || err.response.data.message,
            icon: 'none',
            duration: 2000,
          })
        }
      )
    },
    downloadIamge(imgsrc, name) {
      var that = this
      this.isDown = true
      var downloadUrl = imgsrc

      that.downloadFile(downloadUrl)
    },
    saveImg: function () {
      this.downloadIamge(this.info[this.activeIndex].wap_poster, 'poster' + this.activeIndex)
    },
    downloadFile(url) {
      uni.downloadFile({
        url,
        fail: function (res) {
          uni.showModal({
            title: '提示',
            content: '保存失败',
          })
        },
        success: function (res) {
          uni.saveImageToPhotosAlbum({
            filePath: res.tempFilePath,
            success: () => {
              uni.showToast({
                title: '保存成功！',
              })
            },
            fail: () => {
              uni.showToast({
                title: '保存失败',
              })
            },
          })
        },
      })
    },
  },
}
</script>

<style lang="less">
page {
  height: 100%;
}

.distribution-posters {
  height: 100%;
}

.banenr {
  height: 100%;
}

.banner swiper {
  height: 100%;
}

.banner .slide-image {
  width: 100%;
  height: auto;
}
.distribution-posters {
  .keep {
    position: absolute;
    bottom: 60rpx;
    left: 60rpx;
    right: 60rpx;
    width: auto;
  }
}
</style>
