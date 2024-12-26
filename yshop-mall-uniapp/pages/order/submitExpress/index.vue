<!-- 提交物流 -->
<template>
  <view class="submitExpress">
    <!-- 填写物流 -->
    <view class="form">
      <picker class="picker" :value="express" :range="expressPicker" @change="changeExpress">
        <view class="item acea-row row-between-wrapper">
          <view class="title">
            <view class="point">
              *
            </view>
            物流公司
          </view>
          <view class="num">{{ express }}</view>
          <text class="iconfont icon-jiantou"></text>
        </view>
      </picker>
      <view class="item">
        <view class="title">
          <view class="point">
            *
          </view>
          物流单号:
        </view>
        <input class="input" v-model="expressQuery.postalCode" placeholder="请输入物流单号" />
      </view>
    </view>
    <!-- 提交按钮 -->
    <view class="btnContainer">
      <view class="addBtn" @click="addExpress">
        提交
      </view>
    </view>
  </view>
</template>

<script>
import { getExpressData, addExpressData } from '@/api/aftersales.js'
export default {
  data() {
    return {
      expressQuery: {
        code: '', // 快递公司编码
        name: '', // 快递公司名称
        postalCode: '', // 快递编号
        orderCode: '', // 订单编号
        id: '', // 售后订单id
      },
      expressList: [],
      expressPicker: [],
      express: '',
    }
  },
  mounted() {
    this.expressQuery.orderCode = this.$yroute.query.orderCode
    this.expressQuery.id = this.$yroute.query.id
    this.getExpress()
  },
  methods: {
    async getExpress() {
      const res = await getExpressData()
      console.log(res)
      this.expressList = res.data.map(item => {
        return {
          id: item.id,
          name: item.name,
          code: item.code,
        }
      })
      this.expressPicker = res.data.map(item => {
        return item.name
      })
    },
    changeExpress(e) {
      this.express = this.expressPicker[e.mp.detail.value]
      this.expressQuery.code = this.expressList[e.mp.detail.value].code
      this.expressQuery.name = this.expressList[e.mp.detail.value].name
    },
    async addExpress() {
      let errMsg = '请选择'
      if (!this.expressQuery.code || !this.expressQuery.code) {
        errMsg += '快递公司 '
          uni.showToast({
              title: errMsg,
              icon: 'none',
              duration: 2000,
          })
          return
      }
      if (!this.expressQuery.postalCode) {
          uni.showToast({
              title: '请填写快递单号',
              icon: 'none',
              duration: 2000,
          })
          return
      }
      addExpressData(this.expressQuery)
        .then(res => {
          uni.showToast({
            title: '物流填写成功！',
            icon: 'none',
            duration: 2000,
          })
          this.$yrouter.push({
            path: '/pages/order/ReturnList/index',
          })
        })
        .catch(err => {
          uni.showToast({
            title: '提交物流信息失败！',
            icon: 'none',
            duration: 2000,
          })
        })
    },
  },
}
</script>

<style lang="scss" scoped>
.submitExpress {
  .form {
    margin-bottom: 170rpx;
    .picker {
      margin: 0;
      padding: 0;
      font-size: 28rpx;
      color: #999999;
    }
    .item {
      width: 100%;
      height: 80rpx;
      padding: 0 32rpx;
      line-height: 80rpx;
      background-color: #fff;
      display: flex;
      .title {
        font-size: 28rpx;
        color: #333333;
        display: flex;
        .point {
          color: #ea3729;
        }
      }
      .input {
        height: 80rpx;
        line-height: 80rpx;
        font-size: 28rpx;
        color: #999999;
      }
    }
  }
  .btnContainer {
    width: 100%;
    height: 170rpx;
    background-color: #fff;
    box-shadow: 0px 0px 0px rgba(197, 202, 207, 0.5);
    position: fixed;
    bottom: 0;
    .addBtn {
      height: 90rpx;
      margin: 40rpx 30rpx;
      line-height: 90rpx;
      text-align: center;
      border-radius: 50rpx;
      font-size: 28rpx;
      color: #fff;
      background: linear-gradient(90deg, #e93526 0%, #ff8e3c 100%);
    }
  }
}
</style>
