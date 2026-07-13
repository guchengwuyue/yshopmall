<template>
  <view class="return-list" ref="container">
	<!-- 分类查询 -->
	<view class="nav">
	  <view class="item" :class="{ on: listQuery.type === 0 }" @click="changeType(0)">
	    <view>全部</view>
	  </view>
	  <view class="item" :class="{ on: listQuery.type === 1 }" @click="changeType(1)">
	    <view>售后中</view>
	  </view>
	  <view class="item" :class="{ on: listQuery.type === 2 }" @click="changeType(2)">
	    <view>已完成</view>
	  </view>
	</view>
	<!-- 商品列表 -->
	<view class="list">
		<ListItem
			v-for="(item,index) in orderList"
			:key="index"
			:item="item"
		/>
	</view>
    <view class="noCart" v-if="orderList.length === 0 && page > 1">
      <view class="pictrue">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noOrder.png`" />
      </view>
    </view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
  </view>
</template>

<script>
import { getOrderList } from "@/api/order";
import { getAfterSealsList } from "@/api/aftersales.js";
import Loading from "@/components/Loading";
import ListItem from './listItem.vue'

export default {
  name: "ReturnList",
  components: {
    Loading,
	ListItem
  },
  data() {
    return {
		orderList: [],
		listQuery: {
		  page: 1,
		  limit: 5,
		  type: 0
		},
		type: 0,
		loading: false,
		loaded: false
    };
  },
  mounted() {
    this.getOrderList();
  },
  onReachBottom() {
    !this.loading && this.getOrderList();
  },
  methods: {
	  // 售后详情
    getOrderList() {
      if ((this.loading || this.loaded) && (this.type === this.listQuery.type)) return;
      this.loading = true;
	  // 售后接口
	  getAfterSealsList(this.listQuery).then(res => {
		  // console.log(res)
		  if (this.type === this.listQuery.type) {
			this.orderList = [...this.orderList, ...res.data];
		  } else {
			  this.orderList = res.data;
			  this.type = this.listQuery.type
		  }
		  this.listQuery.page++;
		  // 加载组件
		  this.loading = false;
		  this.loaded = res.data.length < this.listQuery.limit;// 查询到末尾
	  })
    },
	// 分类查询
	changeType (type) {
		this.listQuery.type = type
		this.listQuery.page = 1;
		this.getOrderList()
	}
  }
};
</script>

<style scoped lang="scss">
.return-list{
	.nav{
		background-color: #FFF;
		display: flex;
		justify-content: space-around;
		.item{
			height: 80rpx;
			line-height: 80rpx;
			font-size: 14px;
			font-family: PingFang SC;
			color: #333333;
		}
		.on{
			border-bottom: 6rpx solid #EB3729;
		}
	}
}
</style>
