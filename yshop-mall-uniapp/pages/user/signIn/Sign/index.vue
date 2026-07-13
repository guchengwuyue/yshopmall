<template>
	<view class="sign">
		<view class="header bg-color-red">
			<view class="headerCon acea-row row-between-wrapper">
				<view class="left acea-row row-between-wrapper">
					<view class="pictrue">
						<image :src="userInfo.avatar" />
					</view>
					<view class="text">
						<view class="line1">{{ userInfo.nickname || '' }}</view>
						<view class="integral acea-row">
							<text>积分: {{ userInfo.integral || 0 }}</text>
						</view>
					</view>
				</view>
				<view @click="goSignRecord()" class="right acea-row row-middle">
					<view class="iconfont icon-caidan"></view>
					<view>明细</view>
				</view>
			</view>
		</view>
		<view class="wrapper">
			<view class="list acea-row row-between-wrapper">
				<view class="item" v-for="(item, signSystemListIndex) in signSystemList" :key="signSystemListIndex">
					<view :class="signSystemListIndex + 1 === signSystemList.length ? 'rewardTxt' : ''">{{ item.day }}</view>
					<view class="venus" :class=" (signSystemListIndex + 1 === signSystemList.length ? 'reward' : '') +' ' + (sign_index >= signSystemListIndex + 1 ? 'venusSelect' : '')"></view>
					<view class="num" :class="sign_index >= signSystemListIndex + 1 ? 'on' : ''">+{{ item.sign_num }}</view>
				</view>
			</view>
			<!--加在 but 上 on 为已签到-->
			<view class="but bg-color-red" :class="userInfo.is_day_sgin ? 'on' : ''" @click="goSign">{{ userInfo.isDaySign ? "已签到" : "立即签到" }}</view>
			<view class="lock"></view>
		</view>
		<view class="wrapper wrapper2">
			<view class="tip">已累计签到</view>
			<view class="list2 acea-row row-center row-bottom">
				<view class="item" v-for="(item, signCountIndex) in signCount" :key="signCountIndex">{{ item || 0 }}</view>
				<view class="data">天</view>
			</view>
			<view class="tip2">据说连续签到第{{ day }}天可获得超额积分，一定要坚持签到哦~~~</view>
			<view class="list3">
				<view class="item acea-row row-between-wrapper" v-for="(item, signListIndex) in signList" :key="signListIndex">
					<view>
						<view class="name line1">{{ item.title }}</view>
						<view class="data">{{ item.addTime }}</view>
					</view>
					<view class="num font-color-red">+{{ item.number }}</view>
				</view>
				<view @click="goSignRecord()" class="Loads acea-row row-center-wrapper" v-if="signList.length > 0">
					点击加载更多
					<view class="iconfont icon-xiangyou acea-row row-center-wrapper"></view>
				</view>
			</view>
		</view>
		<view class="signTip acea-row row-center-wrapper" :class="active === true ? 'on' : ''">
			<view class="signTipLight loadingpic">
				<image :src="`${$VUE_APP_RESOURCES_URL}/images/light.png`" />
			</view>
			<view class="signTipCon">
				<view class="state">签到成功</view>
				<view class="integral">获得{{ integral }}积分</view>
				<view class="signTipBnt" @click="close">好的</view>
			</view>
		</view>
		<view class="mask" @touchmove.prevent :hidden="active === false"></view>
	</view>
</template>
<style scoped lang="less">
	.Loads .iconfont {
		font-size: 0.25*100rpx;
		margin: 0.02*100rpx 0 0 0.1*100rpx;
	}
</style>
<script>
	import {
		postSignUser,
		getSignConfig,
		postSignIntegral,
		getSignList
	} from "@/api/user";
	import {
		add
	} from "@/utils/bc";

	export default {
		name: "Sign",
		components: {},
		props: {},
		data: function() {
			return {
				userInfo: {},
				integral: 0,
				signCount: [],
				sign_index: 0,
				signSystemList: [],
				signList: [],
				page: 1,
				limit: 3,
				active: false,
				day: ""
			};
		},
		mounted: function() {
			uni.showLoading({ title: "加载中", mask: true });
			this.signUser();
			this.signConfig();
			this.getSignList();
		},
		methods: {
			goSignRecord() {
				this.$yrouter.push("/pages/user/signIn/SignRecord/index");
			},
			// js给数字补0；num：需要补0的数字，length：长度（补到多少位）；
			PrefixInteger: function(num, length) {
				return (Array(length).join("0") + num).slice(-length).split("");
			},
			//数字转中文
			Rp: function(n) {
				var cnum = ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九"];
				var s = "";
				n = "" + n; // 数字转为字符串
				for (var i = 0; i < n.length; i++) {
					s += cnum[parseInt(n.charAt(i))];
				}
				return s;
			},
			// 获取用户信息
			signUser: function() {
				let that = this;
				postSignUser({
					sign: 1
				}).then(res => {
					uni.hideLoading();
					res.data.integral = parseInt(res.data.integral);
					var sumSginDay = res.data.sumSignDay;
					that.userInfo = res.data;
					that.signCount = that.PrefixInteger(sumSginDay, 4);
					that.sign_index = parseInt(res.data.signNum);
				});
			},
			// 签到配置
			signConfig: function() {
				let that = this;
				getSignConfig().then(res => {
					that.signSystemList = res.data;
					that.day = that.Rp(that.signSystemList.length);
				});
			},
			//  用户签到
			goSign: function() {
				let that = this,
					sumSginDay = that.userInfo.sumSignDay;
				if (that.userInfo.is_day_sgin) {
					uni.showToast({
						title: "您今日已签到!",
						icon: "none",
						duration: 2000
					});
					return
				}
				postSignIntegral().then(res => {
					that.active = true;
					that.integral = res.data.integral;
					let sign_index = parseInt(that.sign_index + 1);
					that.sign_index =
						sign_index > that.signSystemList.length ? 1 : sign_index;
					that.signCount = that.PrefixInteger(sumSginDay + 1, 4);
					that.userInfo.is_day_sgin = true;
					that.userInfo.integral = add(that.userInfo.integral, res.data.integral);
					that.getSignList();
				});
			},
			//  获取签到列表;
			getSignList: function() {
				let that = this;
				getSignList(that.page, that.limit).then(res => {
					that.signList = res.data;
				});
			},
			close: function() {
				this.active = false;
			}
		}
	};
</script>
