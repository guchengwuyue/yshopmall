<template>
	<view class="min-goods" @tap="jump('/pages/activity/GroupDetails/index', { id: detail.id })">
		<view class="img-box">
			<view class="tag" >{{ detail.people}}人团</view>
			<image class="img" :src="detail.image" mode="widthFix"></image>
		</view>
		<view class="price-box">
			<view class="y-f">
				<text class="seckill-current">￥{{  detail.price  }}</text>
				<text class="original">销量{{ detail.sales }}{{detail.unitName}}</text>
			</view>
		</view>
		<view class="title"><slot name="titleText"></slot></view>
	</view>
</template>

<script>
export default {
	components: {},
	data() {
		return {};
	},
	props: {
		detail: Object
	},
	computed: {},
	methods: {
		// 路由跳转
		jump(path, query) {
			this.$yrouter.push({
					path,
					query
				});
		}
	}
};
</script>

<style lang="scss">
</style>
