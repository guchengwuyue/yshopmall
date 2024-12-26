<template>
	<view class="banner-swiper-box">
		<canvas canvas-id="colorThief" class="hide-canvas"></canvas>
		<swiper class="banner-carousel Shop-selector-rect" circular @change="swiperChange" :autoplay="true">
			<swiper-item v-for="(item, index) in detail" :key="index" class="carousel-item">
				<image class="swiper-image " :src="item.pic" @click="goRoll(item)" mode="widthFix" lazy-load>
				</image>
			</swiper-item>
		</swiper>
		<view class="banner-swiper-dots">
			<text :class="swiperCurrent === index ? 'banner-dot-active' : 'banner-dot'" v-for="(dot, index) in detail.length"
			 :key="index"></text>
		</view>
	</view>
</template>

<script>
	import colorThief from 'miniapp-color-thief';

	export default {
		data() {
			return {
				swiperCurrent: 0, //轮播下标
				webviewId: 0,
			};
		},
		props: {
			detail: {
				type: Array,
				default: []
			}
		},
		created: async function() {
			await this.doColorThief();
		},
		computed: {},
		methods: {
			async doColorThief() {
				let that = this;
				// 获取轮播图
				let item = this.detail[this.swiperCurrent];
				// 获取轮播图颜色
				let bgcolor = item.color;
				// 颜色不存在
				if (!bgcolor) {
					that.$set(item, 'bgcolor', '#c40414');
					that.$emit('getbgcolor', '#c40414');
				} else {
					that.$set(item, 'bgcolor', bgcolor);
					that.$emit('getbgcolor', bgcolor);
				}

			},
			swiperChange(e) {
				this.swiperCurrent = e.detail.current;
				this.doColorThief();
				let bgcolor = this.detail[this.swiperCurrent].bgcolor;
				this.$emit('getbgcolor', bgcolor);
			},

			// 路由跳转
			goRoll(item) {
				if (item.uniapp_url) {
					this.$yrouter.push(item.uniapp_url)
				}
			},
		}

	}
</script>

<style lang="less">
	// 轮播
	.banner-swiper-box {
		background: #fff;
	}

	.banner-swiper-box,
	.banner-carousel {
		width: 750rpx;
		height: 350upx;
		position: relative;

		.carousel-item {
			width: 100%;
			height: 100%;
			// padding: 0 28upx;
			overflow: hidden;
		}

		.swiper-image {
			width: 100%;
			height: 100%;
			// border-radius: 10upx;
			// background: #ccc;
		}
	}

	.banner-swiper-dots {
		display: flex;
		position: absolute;
		left: 50%;
		transform: translateX(-50%);
		bottom: 20rpx;
		z-index: 5;

		.banner-dot {
			width: 14rpx;
			height: 14rpx;
			background: rgba(255, 255, 255, 1);
			border-radius: 50%;
			margin-right: 10rpx;
		}

		.banner-dot-active {
			width: 14rpx;
			height: 14rpx;
			background: #a8700d;
			border-radius: 50%;
			margin-right: 10rpx;
		}
	}


	.hide-canvas {
		position: fixed !important;
		top: -99999upx;
		left: -99999upx;
		z-index: -99999;
	}
</style>
