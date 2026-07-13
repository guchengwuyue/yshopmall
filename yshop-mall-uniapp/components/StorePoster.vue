<template>
  <view v-if="posterImageStatus" class="poster-first">
    <div class="posterCanvasWarp">
      <canvas class="posterCanvas" canvas-id="myCanvas"></canvas>
    </div>
    <!-- <view class="poster-pop" v-show="!canvasStatus">
      <image
        :src="`${$VUE_APP_RESOURCES_URL}/images/poster-close.png`"
        class="close"
        @click="posterImageClose"
      />
      <view class="canvas" ref="poster">
        <image class="image" :src="posterData.image" alt="商品图片" />
        <view class="text black">
          <text v-text="posterData.title"></text>
        </view>
        <view class="text rad">
          <text v-text="'￥' + posterData.price"></text>
        </view>
        <view class="code">
          <view class="code-img">
            <image :src="posterData.code" show-menu-by-longpress mode="widthFix" alt="二维码" />
          </view>
          <view class="code-text">
            <text>长按识别二维码 立即购买</text>
          </view>
        </view>
      </view>
      <view class="save-poster" @click="savePosterPath">生成图片</view>
    </view>-->
    <view class="poster-pop" v-show="canvasStatus">
      <img :src="`${$VUE_APP_RESOURCES_URL}/images/poster-close.png`" class="close" @click="posterImageClose" mode="widthFix" />
      <image :src="posterImage" alt="tp" class="poster-image" show-menu-by-longpress mode="widthFix" />
      <view class="save-poster" @click="saveImg">保存海报</view>
    </view>
    <view class="mask"></view>
  </view>
</template>

<script>
// import html2canvas from "html2canvas";
import { PosterCanvas } from '@/utils'
import { getProductPoster } from '@/api/store'

export default {
  name: 'StorePoster',
  props: {
    posterImageStatus: Boolean,
    posterData: Object,
    goodId: String,
  },
  data: function () {
    return {
      canvasStatus: false,
      posterImage: '',
    }
  },
  watch: {
    posterImageStatus: function () {
      var that = this
      if (that.posterImageStatus === true) {
        that.$nextTick(function () {
          that.savePosterPath()
        })
      }
    },
  },
  mounted: function () {},
  methods: {
    posterImageClose: function () {
      this.posterImageStatus = false
      this.canvasStatus = false
      this.$emit('setPosterImageStatus')
    },
    saveImg: function () {
      this.downloadFile(this.posterImage)
    },
    downloadFile(url) {
      uni.authorize({
        scope: 'scope.writePhotosAlbum',
        success() {
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
                success: function () {
                  uni.showModal({
                    title: '提示',
                    content: '保存成功',
                  })
                },
                fail: function () {
                  uni.showModal({
                    title: '提示',
                    content: '保存失败',
                  })
                },
              })
              console.log(res)
            },
          })
        },
      })
    },
    savePosterPath: function () {
      const that = this

      uni.showLoading({
        title: '海报生成中',
        mask: true,
      })
      getProductPoster(this.goodId, {
        from: this.$deviceType == 'weixin' || this.$deviceType == 'weixinh5' ? 'uniappH5' : this.$deviceType,
      })
        .then(res => {
          this.canvasStatus = true
          this.posterImage = res.data
        })
        .finally(() => {
          uni.hideLoading()
        })
      // return;
      // //清空图片重新生成
      // that.posterImage = "";
      // uni.showLoading({ title: "海报生成中", mask: true });
      // console.log(this);
      // var prodId = that.$yrouter.currentRoute.query.id;
      // uni.downloadFile({
      //   url:
      //     this.$VUE_APP_API_URL +
      //     "/shareImg/" +
      //     prodId +
      //     "?shareImgName=" +
      //     this.posterData.code,
      //   fail: function(res) {},
      //   success: function(res) {
      //     that.canvasStatus = true;
      //     that.posterImage = res.tempFilePath;
      //     uni.hideLoading();
      //   }
      // });
    },
  },
}
</script>

<style scoped lang="less" lang="less">
.poster-first {
  overscroll-behavior: contain;
}

.poster-pop {
  width: 4.5 * 100rpx;
  height: 8 * 100rpx;
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  z-index: 99;
  top: 50%;
  margin-top: -4.6 * 100rpx;
}

.poster-pop .canvas {
  background-color: #ffffff;
  height: 8 * 100rpx;
}

.poster-pop .poster-image {
  width: 100%;
  height: auto;
}

.poster-pop .canvas .image {
  width: 4.5 * 100rpx;
  height: 4.5 * 100rpx;
  display: block;
}

.poster-pop .canvas .text {
  text-align: center;
  color: #000000;
  margin-top: 0.32 * 100rpx;
}

.poster-pop .canvas .text.black {
  height: 0.68 * 100rpx;
}

.poster-pop .canvas .text.rad {
  color: #ff0000;
}

.poster-pop .canvas .code {
  height: 1.4 * 100rpx;
  display: flex;
}

.poster-pop .canvas .code .code-img {
  width: 33%;
  padding: 0.06 * 100rpx;
}

.poster-pop .canvas .code .code-img image {
  width: 100%;
}

.poster-pop .canvas .code .code-text {
  width: 60%;
  font-size: 0.12 * 100rpx;
  line-height: 1.64 * 100rpx;
}

.poster-pop .close {
  width: 0.46 * 100rpx;
  height: 0.75 * 100rpx;
  position: fixed;
  right: 0;
  top: -0.73 * 100rpx;
  display: block;
}

.poster-pop .save-poster {
  background-color: #df2d0a;
  font-size: 0.22 * 100rpx;
  color: #fff;
  text-align: center;
  height: 0.76 * 100rpx;
  line-height: 0.76 * 100rpx;
  width: 100%;
  margin-top: -0.1 * 100rpx;
  border-radius: 0 0 10rpx 10rpx;
}

.poster-pop .keep {
  color: #fff;
  text-align: center;
  font-size: 0.25 * 100rpx;
  margin-top: 0.1 * 100rpx;
}

.mask {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.6);
  z-index: 9;
}
</style>
