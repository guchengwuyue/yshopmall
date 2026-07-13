<template>
	<view v-if="couponList.length > 0">
		<view class="coupon-window" :class="value ? 'on' : ''">
			<view class="couponWinList">
				<view class="item acea-row row-between-wrapper" v-for="(item, couponwindiwIndex) in couponList" :key="couponwindiwIndex">
					<view class="money font-color-red">
						￥<text class="num">{{ item.coupon_price }}</text>
					</view>
					<view class="text">
						<view class="name">
							购物买{{ item.use_min_price }}减{{ item.coupon_price }}
						</view>
						<view v-if="item.end_time">
							{{ item.start_time }}-{{ item.end_time }}
						</view>
					</view>
				</view>
				<view style="height:120rpx"></view>
			</view>
			<view class="lid">
				<view class="bnt font-color-red" @click="checked">立即领取</view>
				<view class="iconfont icon-guanbi3" @click="close"></view>
			</view>
		</view>
		<view class="mask" @touchmove.prevent :hidden="!value"></view>
	</view>
</template>
<script>
	import {
		mapGetters
	} from "vuex";
	import {handleLoginFailure} from "@/utils";
	import {
		couponReceiveBatch
	} from "@/api/user";

	export default {
		name: "CouponWindow",
		props: {
			couponList: {
				type: Array,
				default: () => []
			}
		},
		computed: mapGetters(["isLogin"]),
		data: function() {
			return {
				value: true
			};
		},
		mounted: function() {},
		methods: {
			checked() {
				const isLogin = this.isLogin;
				if (!isLogin) return handleLoginFailure();

				const ids = this.couponList.reduce((initial, coupon) => {
					initial.push(coupon.id);
					return initial;
				}, []);
				couponReceiveBatch(ids)
					.then(() => {
						this.$emit("success");
						uni.showToast({
							title: '领取成功',
							icon: 'success',
							duration: 2000
						});
					})
					.catch(() => {
						uni.showToast({
							title: '已领取',
							icon: 'none',
							duration: 2000
						});
					});
				if (isLogin) {
					this.value = false;
					this.$emit("checked");
				}
			},
			close: function() {
				this.value = false;
				this.$emit("close");
			}
		}
	};
</script>
