<template>
	<view class="recommend" ref="container">
		<view class="title acea-row row-center-wrapper">
			<text class="iconfont icon-zhuangshixian"></text>
			<text class="name">为你推荐</text>
			<text class="iconfont icon-zhuangshixian lefticon"></text>
		</view>
		<view class="recommendList acea-row row-between-wrapper">
			<view @click="routerGo(item)" class="item" v-for="(item, recommendIndex) in hostProduct"
				:key="recommendIndex">
				<view class="pictrue">
					<image :src="item.image" class="image" />
				</view>
				<view class="name line1">{{ item.storeName }}</view>
				<view class="money font-color-red">
					￥
					<text class="num">{{ item.price }}</text>
				</view>
			</view>
		</view>
		<Loading :loaded="loadend" :loading="loading"></Loading>
	</view>
</template>
<script>
	import {
		getHostProducts
	} from '@/api/store';
	import Loading from '@/components/Loading';
	export default {
		name: 'Recommend',
		props: {
			recommendLoading: Boolean
		},
		components: {
			Loading
		},
		watch: {
			recommendLoading(nextLoading) {
				if (nextLoading) {
					this.hostProducts()
				}
			}
		},
		data: function () {
			return {
				hostProduct: [],
				page: 1,
				limit: 20,
				loadTitle: '',
				loading: false,
				loadend: false
			};
		},
		mounted: function () {
			this.hostProducts();
		},
		methods: {
			routerGo(item) {
				this.$yrouter.push({
					path: '/pages/shop/GoodsCon/index',
					query: {
						id: item.id
					}
				});
			},
			hostProducts: function () {
				let that = this;
				if (that.loading) return; //阻止下次请求（false可以进行请求）；
				if (that.loadend) return; //阻止结束当前请求（false可以进行请求）；
				that.loading = true;
				getHostProducts(that.page, that.limit).then(res => {
					that.loading = false;
					//apply();js将一个数组插入另一个数组;
					that.hostProduct.push.apply(that.hostProduct, res.data);
					that.loadend = res.data.length < that.limit; //判断所有数据是否加载完成；
					that.page = that.page + 1;
					this.$emit('changeRecommendLoading', false)
				});
			}
		},
	};
</script>
