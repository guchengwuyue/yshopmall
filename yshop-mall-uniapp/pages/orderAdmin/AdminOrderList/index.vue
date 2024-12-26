<template>
	<view class="pos-order-list" ref="container">
		<view class="nav acea-row row-around row-middle">
			<view class="item" :class="where.status == 0 ? 'on' : ''" @click="changeStatus(0)">待付款</view>
			<view class="item" :class="where.status == 1 ? 'on' : ''" @click="changeStatus(1)">待发货</view>
			<view class="item" :class="where.status == 2 ? 'on' : ''" @click="changeStatus(2)">待收货</view>
			<view class="item" :class="where.status == 3 ? 'on' : ''" @click="changeStatus(3)">待评价</view>
			<view class="item" :class="where.status == 4 ? 'on' : ''" @click="changeStatus(4)">已完成</view>
			<view class="item" :class="where.status == -3 ? 'on' : ''" @click="changeStatus(-3)">退款</view>
		</view>
		<view class="list">
			<view class="item" v-for="(item, listIndex) in list" :key="listIndex">
				<view class="order-num acea-row row-middle" @click="toDetail(item)">
					<text>订单号：{{ item.orderId }}</text>
					<text class="time acea-row row-between">
						<text>下单时间：</text>
						<text class="conter">{{ item.createTime }}</text>
					</text>
				</view>
				<view class="pos-order-goods" v-for="(val, key) in item.cartInfo" :key="key">
					<view class="goods acea-row row-between-wrapper" @click="toDetail(item)">
						<view class="picTxt acea-row row-between-wrapper">
							<view class="pictrue">
								<image :src="val.productInfo.image" />
							</view>
							<view class="text acea-row row-between row-column">
								<view class="info line2">{{ val.productInfo.storeName }}</view>
								<view class="attr" v-if="val.productInfo.sku">{{ val.productInfo.sku }}</view>
							</view>
						</view>
						<view class="money">
							<view class="x-money">￥{{ val.productInfo.price }}</view>
							<view class="num">x{{ val.cartNum }}</view>
							<view class="y-money" v-if="val.productInfo.otPrice">￥{{ val.productInfo.otPrice }}</view>
						</view>
					</view>
				</view>
				<view class="public-total">
					共{{ item.totalNum }}件商品，应支付
					<text class="money">￥{{ item.payPrice }}</text>
					( 邮费 ¥{{item.totalPostage}})
				</view>
				<view class="operation acea-row row-between-wrapper">
					<view class="more">
						<!--            <view class="iconfont icon-gengduo" @click="more(index)"></view>-->
						<!--            <view class="order" v-show="current === index">-->
						<!--              <view class="items">-->
						<!--                {{ where.status > 0 ? "删除" : "取消" }}订单-->
						<!--              </view>-->
						<!--              <view class="arrow"></view>-->
						<!--            </view>-->
					</view>
					<view class="acea-row row-middle">
						<view class="bnt" @click="modify(item, 0)" v-if="where.status == 0">一键改价</view>
						<view class="bnt" @click="modify(item, 0)" v-if="where.status == -3 && item.refundStatus === 1">立即退款</view>
						<view class="bnt cancel" v-if="item.pay_type === 'offline' && item.paid === 0" @click="offlinePay(item)">确认付款</view>
						<view class="bnt" v-if="where.status == 1 && item._status._title=='未发货'" @click="goGoodsDeliver(item)">去发货</view>
            <view class="bnt cancel" v-if="item._status._title=='待核销' && where.status == 1" @click="storeCancellation(0,item.verifyCode)">快速核销</view>
            <view class="bnt" v-if="item._status._title=='待核销' && where.status == 1" @click="storeCancellation(1,item.verifyCode)">立即核销</view>
					</view>
				</view>
			</view>
		</view>
		<Loading :loaded="loaded" :loading="loading"></Loading>
		<PriceChange
		:change="change"
		:orderInfo="orderInfo"
		v-on:closechange="changeclose($event)"
		v-on:savePrice="savePrice"
		:status="status"></PriceChange>
	</view>
</template>
<script>
	import PriceChange from "@/components/PriceChange";
	import Loading from "@/components/Loading";
	import DataFormat from "@/components/DataFormat";
	import {
		getAdminOrderList,
		setAdminOrderPrice,
		setAdminOrderRemark,
		setOfflinePay,
		setOrderRefund
	} from "@/api/admin";
  import {orderVerific} from "@/api/order";
	import {
		required,
		num
	} from "@/utils/validate";
	import {
		validatorDefaultCatch
	} from "@/utils/dialog";
	export default {
		name: "AdminOrderList",
		components: {
			PriceChange,
			Loading,
			DataFormat
		},
		props: {},
		data: function() {
			return {
				current: "",
				change: false,
				types: 0,
				where: {
					page: 1,
					limit: 5,
					status: 0
				},
				list: [],
				loaded: false,
				loading: false,
				orderInfo: {},
				status: ""
			};
		},
		watch: {
			"$yroute.query.types": function(newVal) {
				let that = this;
				if (newVal != undefined) {
					that.where.status = newVal;
					that.init();
				}
			},
			types: function() {
				this.getIndex();
			}
		},
    onShow: function() {
			let that = this;
			that.where.status = that.$yroute.query.types;
			that.current = "";
			that.getIndex();
		},
		onReachBottom() {
			!this.loading && this.getIndex();
		},
		methods: {
			goGoodsDeliver(item) {
				this.$yrouter.push({
					path: "/pages/orderAdmin/GoodsDeliver/index",
					query: {
						oid: item.orderId
					}
				});
			},
			more: function(index) {
				if (this.current === index) this.current = "";
				else this.current = index;
			},
			modify: function(item, status) {
				this.change = true;
				this.orderInfo = item;
				this.status = status;
			},
			changeclose: function(msg) {
				this.change = msg;
			},
			async savePrice(opt) {
				let that = this,
					data = {},
					price = opt.price.toString(),
					refund_price = opt.refund_price.toString(),
					refund_status = that.orderInfo.refundStatus,
					remark = opt.remark;
				data.orderId = that.orderInfo.orderId;
				if (that.status == 0 && refund_status === 0) {
					try {
						await this.$validator({
							price: [
								required(required.message("金额")),
								num(num.message("金额"))
							]
						}).validate({
							price
						});
					} catch (e) {
						return validatorDefaultCatch(e);
					}
					data.price = price;
					setAdminOrderPrice(data).then(
						function() {
							that.change = false;
							uni.showToast({
								title: "改价成功",
								icon: "success",
								duration: 2000
							});
							that.init();
						},
						function() {
							that.change = false;
							uni.showToast({
								title: "改价失败",
								icon: "none",
								duration: 2000
							});
						}
					);
				} else if (that.status == 0 && refund_status === 1) {
					try {
						await this.$validator({
							refund_price: [
								required(required.message("金额")),
								num(num.message("金额"))
							]
						}).validate({
							refund_price
						});
					} catch (e) {
						return validatorDefaultCatch(e);
					}
					data.price = refund_price;
					data.type = opt.type;
					setOrderRefund(data).then(
						res => {
							that.change = false;
							uni.showToast({
								title: res.msg,
								icon: "none",
								duration: 2000
							});
							that.init();
						},
						err => {
							that.change = false;
							uni.showToast({
								title: res.msg,
								icon: "none",
								duration: 2000
							});
						}
					);
				} else {
					try {
						await this.$validator({
							remark: [required(required.message("备注"))]
						}).validate({
							remark
						});
					} catch (e) {
						return validatorDefaultCatch(e);
					}
					data.remark = remark;
					setAdminOrderRemark(data).then(
						res => {
							that.change = false;
							uni.showToast({
								title: res.msg,
								icon: "none",
								duration: 2000
							});
							that.init();
						},
						err => {
							that.change = false;
							uni.showToast({
								title: res.msg,
								icon: "none",
								duration: 2000
							});
						}
					);
				}
			},
			init: function() {
				this.list = [];
				this.where.page = 1;
				this.loaded = false;
				this.loading = false;
				this.getIndex();
				this.current = "";
			},
			getIndex: function() {
				let that = this;
				if (that.loading || that.loaded) return;
				that.loading = true;
				getAdminOrderList(that.where).then(
					res => {
						that.loading = false;
						that.loaded = res.data.length < that.where.limit;
						that.list.push.apply(that.list, res.data);
						that.where.page = that.where.page + 1;
					},
					err => {
						uni.showToast({
							title: res.msg,
							icon: "none",
							duration: 2000
						});
					}
				);
			},
			changeStatus: function(val) {
				if (this.where.status != val) {
					this.where.status = val;
					this.init();
				}
			},
			toDetail: function(item) {
				this.$yrouter.push({
					path: "/pages/orderAdmin/AdminOrder/index",
					query: {
						oid: item.orderId
					}
				});
			},
			offlinePay: function(item) {
				setOfflinePay({
					order_id: item.order_id
				}).then(
					res => {
						uni.showToast({
							title: res.msg,
							icon: "none",
							duration: 2000
						});
						this.init();
					},
					error => {
						uni.showToast({
							title: error.msg,
							icon: 'none',
							duration: 2000
						});
					}
				);
			},
      storeCancellation(index,verifyCode) {
        const that = this;
        that.check = true;
        if (index == 0) {
          uni.showModal({
            title: "确定核销订单?",
            content: "注意:请务必核对核销码的与客户正确性",
            success(res) {
              if (res.confirm) {
                uni.showLoading({
                  title: "查询中"
                });
                orderVerific(verifyCode, 1)
                    .then(res => {
                      console.log(res)
                      uni.hideLoading();
                      that.iShidden = false;
                      uni.showToast({
                        title: res.msg,
                        icon: 'none',
                        duration: 1000
                      });
                      //最后就是返回上一个页面。
                      setTimeout(function() {
                        uni.navigateBack({
                          delta: 1, // 返回上一级页面。
                          success: function() {
                            console.log('成功！')
                          }
                        })
                      }, 1000);
                    })
                    .catch((err) => {
                      console.log(err)
                      uni.hideLoading();
                      uni.showToast({
                        title: err.data.msg,
                        icon: "none",
                        duration: 2000
                      });
                    });
              }
            }
          });

        } else {
          that.$yrouter.push({
            path: '/pages/orderAdmin/OrderCancellation/index'
          })
        }
      }
		}
	};
</script>

<style lang="less">
.quick {
  background: #F25555;
}
</style>
