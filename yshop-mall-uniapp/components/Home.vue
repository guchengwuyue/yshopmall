<template>
	<view class="home" :style="{ top: top + 'px' }" style="position:fixed;" id="right-nav" @touchmove="touchmove($event)">
		<view class="homeCon bg-color-red1" :class="homeActive === true ? 'on' : ''">
			<view @click="homeGo()" class="iconfont icon-shouye-xianxing " style="color: green;"></view>
			<view @click="shoppingCartGo()" class="iconfont icon-caigou-xianxing" style="color: green;"></view>
			<!--<view @click="userGo()" class="iconfont icon-yonghu1"></view>-->
		</view>
	</view>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
	name: 'Home',
	props: {},
	data: function() {
		return {
			top: '',
			homeActive: true
		};
	},
	computed: mapGetters(['homeActive']),
	methods: {
		userGo() {
			this.$yrouter.push('/pages/user/User/index');
		},
		homeGo() {
			this.$yrouter.switchTab('/pages/home/index');
		},
		shoppingCartGo() {
			this.$yrouter.switchTab('/pages/shop/ShoppingCart/index');
		},
		touchmove(event) {
			// event.preventDefault();
			// let top =
			//   event.touches[0].pageY -
			//   (document.documentElement.scrollTop || document.body.scrollTop) -
			//   this.$el.clientHeight;

			// if (top > 390) top = 390;
			// else if (top < 55) top = 55;
			this.top = 55;
		},
	}
};
</script>
<style scoped lang="less">
.mystyl {
	display: inline-block;
	width: 64rpx;
	height: 64rpx;
	margin-top:12rpx;
	box-sizing: border-box;
	border: 1px solid #e1e1e1;
	border-radius: 50%;
	background-size: 124rpx auto;
	background-repeat: no-repeat;
	background-color: rgba(255, 255, 255, 0.9);
}
</style>
