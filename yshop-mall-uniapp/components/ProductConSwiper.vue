<template>
  <view class="slider-banner product-bg">
    <swiper
      class="swiper-wrapper"
      @change="handleChange"
      v-if="imgUrls.length > 0"
    >
      <block v-for="(item, imgUrlsIndex) in imgUrls" :key="imgUrlsIndex">
        <swiper-item>
          <image :src="item" @tap="previewImage(imgUrlsIndex)" class="slide-image" />
        </swiper-item>
      </block>
    </swiper>
    <!-- <swiper class="swiper-wrapper" :options="ProductConSwiper" v-if="imgUrls.length > 0">
      <swiperSlide class="swiper-slide" v-for="item in imgUrls" :key="item" ref="goodSwiper">
        <image :src="item" class="slide-image" />
      </swiperSlide>
    </swiper>-->
    <view class="pages">{{ currents || 1 }}/{{ imgUrls.length || 1 }}</view>
  </view>
</template>
<script>
// import { swiper, swiperSlide } from "vue-awesome-swiper";

export default {
  name: "ProductConSwiper",
  components: {
    // swiper,
    // swiperSlide
  },
  props: {
    imgUrls: {
      type: Array,
      default: () => [],
    },
  },
  data: function () {
    let that = this;
    return {
      currents: 1,
      ProductConSwiper: {
        autoplay: {
          disableOnInteraction: false,
          delay: 2000,
        },
        loop: true,
        speed: 1000,
        observer: true,
        observeParents: true,
        on: {
          slideChangeTransitionStart: function () {
            that.currents = this.realIndex + 1;
          },
        },
      },
    };
  },
  mounted: function () {},
  methods: {
    handleChange(event) {
      this.currents = event.mp.detail.current + 1;
    },
    previewImage(current) {
      uni.previewImage({
        current,
        urls: this.imgUrls,
      });
    },
  },
};
</script>
