<template>
	<view class="productList">
		<view class="goodsStyle acea-row row-between"
			v-for="cart in list"
			:key="cart.id"
		>
			<view class="pictrue">
				<image :src="cart.productInfo.image" class="image" />
			</view>
			<view class="text acea-row row-between">
				<view class="name line2">{{ cart.productInfo.storeName }}</view>
				<view class="money">
					<view>
						￥{{
			cart.productInfo.attrInfo
			? cart.productInfo.attrInfo.price
			: cart.productInfo.price
			}}
					</view>
					<view class="num">x{{ cart.cartNum }}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	props: {
		list: {
			type: Array,
			default: () => []
		}
	}
}
</script>

<style scoped lang="less">
</style>
