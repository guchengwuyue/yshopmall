<!-- 售后列表内容 -->
<template>
	<view class="productItem">
		<view class="title">
			<view class="shopName">
				订单号：{{ item.orderCode }}
			</view>
		</view>
		<view class="content" v-for="(i, index) in item.cartInfo">
			<image class="img"
				:src="i.productInfo.image"
				mode=""
				@click.stop="goGoodsCon(i)"
			></image>
			<view class="info">
				<view class="infoTitle">
					{{ i.productInfo.storeName }}
				</view>
				<view class="infoSku">
					<view class="sku">
						{{ i.productInfo.attrInfo.sku || '' }}
					</view>
					<view class="num">
						x {{ 1 }}
					</view>
				</view>
				<view class="infoMoney">
					退款：<view class="red">
						￥{{ i.productInfo.price }}
					</view>
				</view>
			</view>
		</view>
		<view class="money">
			<view class="status">
				<view v-if="item.salesState === 0">
					<view v-if="item.state === 0">商家处理中</view>
					<view v-if="item.state === 1">售后中</view>
					<view v-if="item.state === 2">等待商家收货</view>
					<view v-if="item.state === 3">已完成</view>
				</view>
				<view v-if="item.salesState === 1">
					已撤销
				</view>
				<view v-if="item.salesState === 2">
					商家拒绝
				</view>
			</view>
			<view class="refundMoney">
				退款：<view class="red">
					￥{{ item.refundAmount }}
				</view>
			</view>
		</view>
		<view class="btns">
			<view class="button def"
				@click="toDetail"
				v-if="item.state === 2"
			>删除记录</view>
			<view class="button redBtn" @click="toDetail(item)">查看详情</view>
		</view>
	</view>
</template>

<script>
import {
	deleteAfterSeals
} from '@/api/aftersales.js'
export default {
	props: {
		item: {
			type: Object,
			default: () => ({})
		}
	},
	methods:{
		// 跳转商品
		goGoodsCon(item) {
		  this.$yrouter.push({
		    path: "/pages/shop/GoodsCon/index",
		    query: { id: item.productInfo.id }
		  });
		},
		// 跳转售后详情
		toDetail (item) {
			console.log(item)
			this.$yrouter.push({
				path: "/pages/order/OrderReturnDetail/index",
				query: {
					key: item.orderCode,
					id: item.id
				}
			});
		}
	}
}
</script>

<style lang="scss" scoped>
.productItem{
	margin: 10rpx 0;
	background-color: #FFF;
	.title{
		height: 80rpx;
		padding: 0 30rpx;
		color: #333;
		font-size: 28rpx;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.content{
		padding: 20rpx 30rpx;
		border: 2rpx solid #F3F4F5;
		display: flex;
		.img{
			width: 180rpx;
			height: 180rpx;
		}
		.info{
			flex: 1;
			margin-left: 16rpx;
			display: flex;
			flex-direction: column;
			justify-content: space-around;
			.infoTitle{
				width: 100%;
				height: 80rpx;
				font-size: 28rpx;
				color: #333333;
				overflow: hidden;
				text-overflow: ellipsis;
			}
			.infoSku{
				font-size: 24rpx;
				color: #999999;
				display: flex;
				justify-content: space-between;
			}
			.infoMoney{
				font-size: 24rpx;
			}
		}
	}
	.money{
		height: 80rpx;
		padding: 0 30rpx;
		font-size: 28rpx;
		font-family: PingFang SC;
		color: #333333;
		border: 2rpx solid #F3F4F5;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.btns{
		padding: 20rpx 30rpx;
		display: flex;
		justify-content: flex-end;
		.button{
			width: 160rpx;
			height: 58rpx;
			margin: 0 10rpx;
			border-radius: 180rpx;
			font-size: 24rpx;
			line-height: 54rpx;
			text-align: center;
		}
		.def{
			color: #DDD;
			border: 2rpx solid #DDDDDD;
		}
		.redBtn{
			color: #FFF;
			background-color: #EB3729;
			border: 2rpx solid #EB3729;
		}
	}
	.red{
		display: inline-block;
		color: #EB3729;
	}
}
</style>
