<template>
	<view class="not-defined">
		<image :src="`${$VUE_APP_RESOURCES_URL}/images/404.png`" />
		<view class="content">
			<h3 class="title">页面未找到</h3>
			<text>抱歉！您访问的页面不存在，请返回上一级或点击下方按钮返回首页...</text>
		</view>
		<view class="btn" @click="homeGo()">
			返回首页
		</view>
	</view>
</template>

<script>
	export default {
		name: "NotDefined",
		methods: {
			homeGo() {
				this.$yrouter.switchTab('/pages/home/index');
			},
		},
	};
</script>

<style scoped lang="less">
	.not-defined image{
		width: 100%;
		margin-top: 18%;
	}

	.content {
		padding: 0 1*100rpx;
		text-align: center;
		color: #44405e;
		font-size: 15px;
	}

	.title {
		margin-bottom: 0.6*100rpx;
		color: #302c48;
		font-size: 20px;
	}

	.btn {
		color: #fff;
		background-color: #ef4c4c;
		font-size: 16px;
		padding: 0.16*100rpx;
		border-radius: 25px;
		text-align: center;
		width: 2.4*100rpx;
		margin: 0 auto;
		margin-top: 1*100rpx;
	}
</style>
