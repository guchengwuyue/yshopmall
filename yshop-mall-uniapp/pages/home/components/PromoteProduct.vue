<template>
    <view class="group-goods pa20 mx20 mb10" v-if="detail.length>0">
        <view class="title-box x-bc" @tap="$yrouter.push('/pages/shop/GoodsPromotion/index')">
            <text class="title">促销单品</text>
            <view class="group-people x-f">
                <text class="tip">更多</text>
                <text class="cuIcon-right"></text>
            </view>
        </view>
        <view class="goods-box swiper-box x-f">
			<block v-for="(goods, index) in goodsList" :key="index" class="carousel-item">
				<view class="goods-list-box x-f">
					<block v-for="mgoods in goods" :key="mgoods.id">
						<view class="min-goods"
							@tap="$yrouter.push({ path: '/pages/shop/GoodsCon/index', query: { id: item.id } })">
							<view class="img-box">
								<view class="tag">促销</view>
								<image class="img" :src="mgoods.image" mode="widthFix"></image>
							</view>
							<view class="price-box">
								<view class="y-f">
									<text class="seckill-current">日常价：￥{{  mgoods.price  }}</text>
									<text class="original">仅剩：{{ mgoods.stock }}{{ mgoods.unitName }}</text>
								</view>
							</view>
							<view class="title">
								<slot name="titleText"></slot>
							</view>
						</view>
					</block>
				</view>
			</block>
            <view class="swiper-dots" v-if="goodsList.length > 1">
                <text :class="swiperCurrent === index ? 'dot-active' : 'dot'" v-for="(dot, index) in goodsList.length"
                    :key="index"></text>
            </view>
        </view>
    </view>
</template>

<script>
    import shActivityGoods from '@/components/sh-activity-goods.vue';

    export default {
        name: "ProductsRecommended",
        components: {
            shActivityGoods
        },
        data() {
            return {
                goodsList: [],
                swiperCurrent: 0
            };
        },
        props: {
            detail: Array
        },
        computed: {},
        created() {},
        watch: {
            detail(next) {
                this.goodsList = this.sortData(next, 4);
            }
        },
        methods: {
            swiperChange(e) {
                this.swiperCurrent = e.detail.current;
            },
            // 数据分层
            sortData(oArr, length) {
                let arr = [];
                let minArr = [];
                oArr.forEach(c => {
                    if (minArr.length === length) {
                        minArr = [];
                    }
                    if (minArr.length === 0) {
                        arr.push(minArr);
                    }
                    minArr.push(c);
                });

                return arr;
            },
            jump(path, query) {
                this.$yrouter.push({
                    path,
                    query,
                });
            },
        }
    }
</script>


<style lang="scss" scoped>
    .group-goods {
        position: relative;
        z-index: 9;
    }

    .swiper-box,
    .carousel {
        width: 700rpx;
        height: 240upx;
        position: relative;
        border-radius: 20rpx;

        .carousel-item {
            width: 100%;
            height: 100%;
            // padding: 0 28upx;
            overflow: hidden;
        }

        .swiper-image {
            width: 100%;
            height: 100%;
            // border-radius: 10upx;
            background: #ccc;
        }
    }

    .swiper-dots {
        display: flex;
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        bottom: 0rpx;
        z-index: 66;

        .dot {
            width: 45rpx;
            height: 3rpx;
            background: #eee;
            border-radius: 50%;
            margin-right: 10rpx;
        }

        .dot-active {
            width: 45rpx;
            height: 3rpx;
            background: #a8700d;
            border-radius: 50%;
            margin-right: 10rpx;
        }
    }

    // 今日必拼+限时抢购
    .group-goods {
        background: #fff;
        border-radius: 20rpx;
        overflow: hidden;

        .title-box {
            padding-bottom: 20rpx;

            .title {
                font-size: 32rpx;
                font-weight: bold;
            }

            .group-people {
                .time-box {
                    font-size: 26rpx;
                    color: #edbf62;

                    .count-text-box {
                        width: 30rpx;
                        height: 34rpx;
                        background: #edbf62;
                        text-align: center;
                        line-height: 34rpx;
                        font-size: 24rpx;
                        border-radius: 6rpx;
                        color: rgba(#fff, 0.9);
                        margin: 0 8rpx;
                    }
                }

                .head-box {
                    .head-img {
                        width: 40rpx;
                        height: 40rpx;
                        border-radius: 50%;
                        background: #ccc;
                    }
                }

                .tip {
                    font-size: 28rpx;
                    padding-left: 30rpx;
                    color: #666;
                }

                .cuIcon-right {
                    font-size: 30rpx;
                    line-height: 28rpx;
                    color: #666;
                }
            }
        }

        .goods-box {
            .goods-item {
                margin-right: 22rpx;

                &:nth-child(4n) {
                    margin-right: 0;
                }
            }
        }
    }
</style>
