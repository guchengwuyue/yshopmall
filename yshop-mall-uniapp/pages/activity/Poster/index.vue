<template>
  <view class="poster-poster" v-if="status === false">
    <view class="tip">
		<!-- #ifndef H5 -->
		 <text class="iconfont icon-shuoming"></text>提示：长按图片保存至手机相册
		 <!-- #endif -->
    </view>
    <view class="poster">
		<!-- #ifndef APP-PLUS -->
		<image :src="image" mode="widthFix" show-menu-by-longpress />
		<!-- #endif -->
		<!-- #ifdef APP-PLUS -->
		<image :src="image" mode="widthFix" @longtap="longtap"/>
		<!-- #endif -->
    </view>
  </view>
</template>
<script>
  import {
    getCombinationPoster
  } from "@/api/activity";

  export default {
    name: "Poster",
    components: {},
    props: {},
    data () {
      return {
        status: true,
        id: 0,
        image: ""
      };
    },
    mounted () {
      var id = this.$yroute.query.id;
      var type = this.$yroute.query.type;
      this.id = id;
      if (type == 2) {
      } else {
        this.getCombinationPoster();
      }
    },
    methods: {
		// 拼团海报
		getCombinationPoster: function () {
			var that = this;
			console.log(this.$deviceType)
			let from = this.$deviceType
			if (from == 'weixin' || this.$deviceType == 'weixinh5') {
			  from = 'uniappH5'
			}
			console.log(from)
			getCombinationPoster({
				id: that.id,
				from
			  })
			  .then(res => {
				that.image = res.data.url;
				that.status = false;
			  })
			  .catch(res => {
				uni.showToast({
				  title: res.msg,
				  icon: "none",
				  duration: 2000
				});
			  });
		},
		// app端长按保存
		longtap () {
			// 先下载图片
			uni.downloadFile({
				url: this.img,
				success: (res) => {
				  // 获取到图片本地地址后再保存图片到相册（因为此方法不支持远程地址）
				  uni.saveImageToPhotosAlbum({
					filePath: res.tempFilePath,
					success: () => {
					  uni.showToast({
						title: "保存成功！",
					  });
					},
					fail: () => {
					  uni.showToast({
						title: "保存失败",
					  });
					},
				  });
				},
			});
	    }
    }
  };
</script>

<style scoped lang="less">
  page {
    background-color: #eb3729;
  }

  .poster-poster {
    height: unset !important;
  }
</style>
