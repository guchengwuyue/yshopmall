<template>
	<view class="quality-recommend">
		<!-- <view class="slider-banner swiper">
			<swiper indicatorDots="true" v-if="banner.length > 0">
				<block v-for="(item, imgUrlsIndex) in imgUrls" :key="imgUrlsIndex">
					<swiper-item>
						<image :src="item.img" class="slide-image" />
					</swiper-item>
				</block>
			</swiper>
		</view> -->
		<view class="title acea-row row-center-wrapper">
			<view class="line"></view>
			<view class="name">
				<text class="iconfont icon-cuxiaoguanli"></text>促销单品
			</view>
			<view class="line"></view>
		</view>
		<Promotion-good :benefit="goodsList"></Promotion-good>
	</view>
</template>
<script>
	// import { swiper, swiperSlide } from "vue-awesome-swiper";

	import PromotionGood from "@/components/PromotionGood";
	import {
		getGroomList
	} from "@/api/store";
	export default {
		name: "GoodsPromotion",
		components: {
			// swiper,
			// swiperSlide,
			PromotionGood
		},
		props: {},
		data: function() {
			return {
				imgUrls: [],
				goodsList: [],
				RecommendSwiper: {
					pagination: {
						el: ".swiper-pagination",
						clickable: true
					},
					autoplay: {
						disableOnInteraction: false,
						delay: 2000
					},
					loop: true,
					speed: 1000,
					observer: true,
					observeParents: true
				}
			};
		},
		mounted: function() {
			this.getIndexGroomList();
		},
		methods: {
			getIndexGroomList: function() {
				let that = this;
				getGroomList(4)
					.then(res => {
						that.imgUrls = res.data.banner;
						that.goodsList = res.data.list;
					})
					.catch((err) => {
						uni.showToast({
							title: err.msg || err.response.data.msg|| err.response.data.message,
							icon: 'none',
							duration: 2000
						});
					});
			}
		}
	};
</script>
