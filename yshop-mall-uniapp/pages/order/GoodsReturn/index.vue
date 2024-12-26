<!-- 售后提交 -->
<template>
	<view class="apply-return">
		<!-- 申请售后 -->
		<view class="container" v-if="selected">
			<view class="goodsStyle acea-row row-between"
				v-for="cart in selectProduct"
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
			<view class="list">
				<view v-if="serviceType === 1" class="item acea-row row-between-wrapper">
					<view>退货件数</view>
					<view class="num">{{ totalNum || 0  }}</view>
				</view>
				<view class="item acea-row row-between-wrapper">
					<view>退款金额</view>
					<view class="num">￥{{ totalMoney || 0 }}</view>
				</view>
				<picker :value="reason" :range="reasonList" @change="changeReason">
					<view class="item acea-row row-between-wrapper">
						<view>退款原因</view>
						<view class="num">{{reason}}</view>
						<text class="iconfont icon-jiantou"></text>
					</view>
				</picker>
				<view class="item textarea acea-row row-between">
					<view>备注说明</view>
					<textarea class="num"
						placeholder="填写备注信息，100字以内"
						v-model="refund_reason_wap_explain"
					></textarea>
				</view>
			</view>
			<view v-if="serviceType === 0"
				class="returnBnt bg-color-red"
				@click="submit"
			>申请退款</view>
			<view v-if="serviceType === 1"
				class="returnBnt bg-color-red"
				@click="submit"
			>申请退货</view>
		</view>
		<!-- 选择商品 -->
		<view class="selectProduct" v-else>
			<view class="selectContainer"
				v-for="(cart, index) in selectProduct"
				:key="cart.id"
			>
				<view class="checkbox-wrapper">
				  <checkbox-group @change="switchSelect(index)">
				    <label class="well-check">
				      <checkbox color="#eb3729" value :checked="cart.checked"></checkbox>
				    </label>
				  </checkbox-group>
				</view>
				<view class="pictrue">
					<image :src="cart.productInfo.image" class="image" />
				</view>
				<view class="content">
					<view class="title">{{ cart.productInfo.storeName || '' }}</view>
					<view class="skus">
						<view class="sku">
							{{ cart.productInfo.attrInfo.sku || '' }}
						</view>
						<view class="num">x{{ cart.cartNum || 0 }}</view>
					</view>
					<view class="money">
						￥<view>
							{{
							cart.productInfo.attrInfo
							? cart.productInfo.attrInfo.price
							: cart.productInfo.price
							}}
						</view>
					</view>
				</view>
			</view>
			<view class="choose">
				<view class="checkTotal">
					<checkbox-group @change="allChecked">
					  <label class="well-check">
					    <checkbox
							color="#eb3729"
							value="allSelect"
							:checked="isAllSelect"
						></checkbox>
					    <text class="checkAll">全选 ({{ cartCount || 0 }})</text>
					  </label>
					</checkbox-group>
					<view class="total">
						<view class="proTotal">
							{{ orderInfo.totalNum || 0 }}件商品
						</view>
						<view class="moneyTotal">
							合计 ￥ {{ orderInfo.totalPrice || 0 }}
						</view>
					</view>
				</view>
				<view class="typeBtn">
					<view class="button def" @click="chooseType(0)">仅退款</view>
					<view class="button redBtn" @click="chooseType(1)" v-if="orderInfo.status > 0">退货退款</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
import {
	orderDetail,
	getRefundReason,
	postOrderRefund
} from "@/api/order";
import {
	submitAfterSealsOrder,
	getRefundAmount
} from '@/api/aftersales.js';
import {
	trim
} from "@/utils";
import {
	VUE_APP_API_URL
} from "@/config";

export default {
	name: "goodsReturn",
	components: {
		// VueCoreImageUpload
	},
	data() {
		return {
			url: `${VUE_APP_API_URL}/upload/image`,
			headers: {
				Authorization: "Bearer " + this.$store.state.token
			},
			id: 0,
			selected: false,
			orderInfo: {},
			rebackList: [],
			reasonList: [],
			reason: "",
			refund_reason_wap_explain: '',
			refund_reason_wap_img: [],
			serviceType: null,
			// 选中售后商品
			selectProduct: [],
			productParamList: [],
			totalNum: 0,
			totalMoney: 0,
			isAllSelect: false
		};
	},
	mounted() {
		this.id = this.$yroute.query.id || 0;
		this.getOrderDetail();
		this.getRefundReason();
		
	},
	methods: {
		changeReason(e) {
			this.reason = this.reasonList[e.mp.detail.value];
		},
		imageuploaded(res) {
			if (res.status !== 200) {
				uni.showToast({
					title: err.msg || err.response.data.msg|| err.response.data.message,
					icon: 'none',
					duration: 2000
				});
				return
			}
			this.refund_reason_wap_img.push(res.data.url);
		},
		getAfterSalesMoney() {
			getRefundAmount({
				orderCode: this.orderInfo.orderId,
				productParamList: this.productParamList
			}).then(
				res => {
					this.totalMoney = res.data
					console.log('res.data:',res.data)
				}
			)
		},
		getOrderDetail() {
			uni.showLoading({
				title: '正在查询商品',
				duration: 2000,
			})
			orderDetail(this.id)
				.then(res => {
					this.orderInfo = res.data;
					
					this.selectProduct = res.data.cartInfo.map(item => {
						item.checked = false
						return item
					})
					if (res.data.cartInfo.length === 0) {
						uni.showToast({
							title: "订单无可售后商品",
							icon: 'none',
							duration: 2000
						});
						return
					}
					uni.hideLoading()
				})
				.catch(err => {
					uni.hideLoading()
					// uni.showToast({
					// 	title: err.msg || err.response.data.msg|| err.response.data.message,
					// 	icon: 'none',
					// 	duration: 2000
					// });
				});
		},
		getRefundReason() {
			getRefundReason().then(res => {
				this.reasonList = res.data;
			});
		},
		// 选择退款商品
		switchSelect (index) {
			let isAll = true
			this.selectProduct[index].checked = !this.selectProduct[index].checked
			this.selectProduct.forEach(item => {
				if (!item.checked) {
					isAll = false
				}
			})
			this.isAllSelect = isAll
		},
		// 全选
		allChecked () {
			this.isAllSelect = !this.isAllSelect
			this.selectProduct.forEach(item => {
				item.checked = this.isAllSelect
			})
		},
		// 选择退款类型
		chooseType (val) {
			
			this.selectProduct = this.selectProduct.map(item => {
				if (item.checked) {
					this.productParamList.push({
						productId: item.productId
					})
					console.log(item.costPrice)
					//this.totalMoney += parseFloat(item.truePrice)
					this.totalNum += 1
					return item
				}
			}).filter(r => r)
			this.selected = true;
			this.serviceType = val;
			
			this.getAfterSalesMoney();
		},
		submit() {
			const refund_reason_wap_explain = trim(this.refund_reason_wap_explain);
			if (!this.reason) {
				uni.showToast({
					title: "请选择退款原因",
					icon: 'none',
					duration: 2000
				});
				return
			}
			submitAfterSealsOrder({
				orderCode: this.orderInfo.orderId,
				serviceType: this.serviceType,
				reasonForApplication: this.reason,
				applicationInstructions: refund_reason_wap_explain,
				applicationDescriptionPicture: this.refund_reason_wap_img.join(","),
				productParamList: this.productParamList
			}).then(res => {
				if (res.status === 200) {
					uni.showToast({
						title: res.msg,
						icon: "success",
						duration: 2000
					});
					setTimeout(() => {
						this.$yrouter.push({ path: '/pages/order/ReturnList/index' });
					}, 1500)
				}
			})
			.catch(err => {
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

<style lang="scss" scoped>
.apply-return{
	.selectProduct{
		margin-bottom: 180rpx;
		.selectContainer{
			padding: 20rpx 32rpx;
			background-color: #FFF;
			display: flex;
			align-items: center;
			.pictrue{
				.image{
					width: 180rpx;
					height: 180rpx;
				}
			}
			.content{
				width: 100%;
				height: 100%;
				margin-left: 16rpx;
				font-size: 24rpx;
				display: flex;
				flex-direction: column;
				justify-content: space-between;
				.title{
					width: 100%;
					height: 80rpx;
					text-overflow: ellipsis;
					font-size: 28rpx;
				}
				.skus{
					display: flex;
					justify-content: space-between;
					color: #999999;
				}
				.money{
					margin-top: 10rpx;
					display: flex;
					view{
						color: #EB3729;
					}
				}
			}
		}
		.choose{
			width: 100%;
			padding: 20rpx 30rpx;
			background-color: #FFF;
			position: fixed;
			bottom: 0;
			height: 180rpx;
			.checkTotal{
				height: 80rpx;
				font-size: 28rpx;
				border-bottom: 2rpx solid #F3F4F5;
				display: flex;
				justify-content: space-between;
				align-items: center;
				.total{
					display: flex;
					.proTotal{
						color: #666;
					}
					.moneyTotal{
						color: #333;
						margin-left: 30rpx;
					}
				}
			}
			.typeBtn{
				margin-top: 10rpx;
				display: flex;
				justify-content: flex-end;
				align-items: center;
				.button{
					width: 160rpx;
					height: 60rpx;
					margin: 0 10rpx;
					line-height: 60rpx;
					text-align: center;
					font-size: 24rpx;
					border-radius: 180rpx;
				}
				.def{
					color: #AAAAAA;
					border: 2rpx solid #DDDDDD;
				}
				.redBtn{
					color: #FFF;
					background-color: #EB3729;
				}
			}
		}
	}
}
</style>
