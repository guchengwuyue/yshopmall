<template>
  <view>
    <view class="priceChange" :class="change === true ? 'on' : ''">
      <view class="priceTitle">
        <text v-if="status==0">
          <text v-if="orderInfo.refundStatus == 1">立即退款</text>
          <text v-if="orderInfo.refundStatus != 1">一键改价</text>
        </text>
        <text v-if="status!=0">订单备注</text>
        <text class="iconfont icon-guanbi" @click="close"></text>
      </view>
      <view class="listChange" v-if="status == 0">
        <view class="item acea-row row-between-wrapper" v-if="orderInfo.refundStatus === 0">
          <view>商品总价(¥)</view>
          <view class="money">
            {{ orderInfo.totalPrice }}
            <text class="iconfont icon-suozi"></text>
          </view>
        </view>
        <view class="item acea-row row-between-wrapper" v-if="orderInfo.refundStatus === 0">
          <view>原始邮费(¥)</view>
          <view class="money">
            {{ orderInfo.payPostage }}
            <text class="iconfont icon-suozi"></text>
          </view>
        </view>
        <view class="item acea-row row-between-wrapper" v-if="orderInfo.refundStatus === 0">
          <view>实际支付(¥)</view>
          <view class="money">
            <input
              type="text"
              v-model="price"
              :class="focus === true ? 'on' : ''"
              @focus="priceChange"
            />
          </view>
        </view>
        <view class="item acea-row row-between-wrapper" v-if="orderInfo.refundStatus === 1">
          <view>实际支付(¥)</view>
          <view class="money">
            {{ orderInfo.payPrice }}
            <text class="iconfont icon-suozi"></text>
          </view>
        </view>
        <view class="item acea-row row-between-wrapper" v-if="orderInfo.refundStatus === 1">
          <view>退款金额(¥)</view>
          <view class="money">
            <input
              type="text"
              v-model="refund_price"
              :class="focus === true ? 'on' : ''"
              @focus="priceChange"
            />
          </view>
        </view>
      </view>
      <view class="listChange" v-else>
        <textarea
          :placeholder="'请填写备注信息...'"
          v-model="remark"
        ></textarea>
      </view>
      <view class="modify" @click="save">{{ orderInfo.refundStatus === 0 ? "立即修改" : "确认退款" }}</view>
      <view class="modify1" @click="refuse" v-if="orderInfo.refundStatus === 1">拒绝退款</view>
    </view>
    <view class="mask" @touchmove.prevent v-show="change === true"></view>
  </view>
</template>
<style scoped lang="less" >
.priceChange .listChange textarea {
  border: 1px solid #eee;
  width: 100%;
  height: 200rpx;
  margin-top: 50rpx;
  border-radius: 10rpx;
  color: #333;
  padding: 20rpx;
}
</style>
<script>
export default {
  name: "PriceChange",
  components: {},
  props: {
    change: Boolean,
    orderInfo: Object,
    status: String
  },
  data: function() {
    return {
      focus: false,
      price: 0,
      refund_price: 0,
      remark: ""
    };
  },
  watch: {
    orderInfo: function() {
      this.price = this.orderInfo.payPrice;
      this.refund_price = this.orderInfo.payPrice;
      this.remark = "";
    }
  },
  mounted: function() {},
  methods: {
    priceChange: function() {
      this.focus = true;
    },
    close: function() {
      this.price = this.orderInfo.payPrice;
      this.$emit("closechange", false);
    },
    save: function() {
      let that = this;
      that.$emit("savePrice", {
        price: that.price,
        refund_price: that.refund_price,
        type: 1,
        remark: that.remark
      });
    },
    refuse: function() {
      let that = this;
      that.$emit("savePrice", {
        price: that.price,
        refund_price: that.refund_price,
        type: 2,
        remark: that.remark
      });
    }
  }
};
</script>
