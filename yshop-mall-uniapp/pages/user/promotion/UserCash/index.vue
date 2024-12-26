<template>
  <view class="cash-withdrawal">
    <!-- <view class="nav acea-row">
      <view v-for="(item, navListIndex) in navList" class="item font-color-red" @click="swichNav(navListIndex, item)"
        :key="navListIndex">
        <view class="line bg-color-red" :class="currentTab === navListIndex ? 'on' : ''"></view>
        <view class="iconfont" :class="item.icon + ' ' + (currentTab === navListIndex ? 'on' : '')"></view>
        <view>{{ item.name }}</view>
      </view>
    </view> -->

    <div class="pos-order-list">
      <view class="nav acea-row row-around row-middle">
        <view v-for="(item, navListIndex) in navList" class="item" :class="currentTab === navListIndex ? 'on' : ''"
          @click="swichNav(navListIndex, item)" :key="navListIndex">{{item.name}}</view>
      </view>
    </div>
    <view class="wrapper">
      <view :hidden="currentTab !== 0" class="list">
        <view class="item acea-row row-between-wrapper">
          <view class="name">微信号</view>
          <view class="input">
            <input placeholder="请输入微信号" v-model="post.weixin" />
          </view>
        </view>
        <view class="item acea-row row-between-wrapper">
          <view class="name">提现</view>
          <view class="input">
            <input :placeholder="'最低提现金额' + minPrice" v-model="post.money" />
          </view>
        </view>
        <view class="tip">当前可提现金额: {{ commissionCount }}</view>
        <view class="bnt bg-color-red" @click="submitted">提现</view>
      </view>
      <view :hidden="currentTab !== 1" class="list">
        <view class="item acea-row row-between-wrapper">
          <view class="name">用户名</view>
          <view class="input">
            <input placeholder="请填写您的支付宝用户名" v-model="post.name" />
          </view>
        </view>
        <view class="item acea-row row-between-wrapper">
          <view class="name">账号</view>
          <view class="input">
            <input placeholder="请填写您的支付宝账号" v-model="post.alipay_code" />
          </view>
        </view>
        <view class="item acea-row row-between-wrapper">
          <view class="name">提现</view>
          <view class="input">
            <input :placeholder="'最低提现金额' + minPrice" v-model="post.money" />
          </view>
        </view>
        <view class="tip">当前可提现金额: {{ commissionCount }}</view>
        <view class="bnt bg-color-red" @click="submitted">提现</view>
      </view>
    </view>
  </view>
</template>
<script>
  import {
    getBank,
    postCashInfo
  } from "@/api/user";
  import {
    required
  } from "@/utils/validate";
  import {
    validatorDefaultCatch
  } from "@/utils/dialog";

  export default {
    name: "UserCash",
    components: {},
    props: {},
    data: function () {
      return {
        navList: [{
            name: "微信",
            type: "weixin",
            icon: "icon-weixin2"
          },
          {
            name: "支付宝",
            type: "alipay",
            icon: "icon-icon34"
          }
        ],
        post: {
          extract_type: "weixin",
          alipay_code: "",
          money: "",
          name: "",
          bankname: "",
          cardnum: "",
          weixin: ""
        },
        currentTab: 0,
        minPrice: 0,
        banks: [],
        commissionCount: 0
      };
    },
    mounted: function () {
      this.getBank();
    },
    methods: {
      swichNav: function (index, item) {
        console.log(item);
        this.currentTab = index;
        this.post.extract_type = item.type;
      },
      getBank: function () {
        let that = this;
        getBank().then(
          res => {
            that.banks = res.data.extractBank;
            that.minPrice = res.data.minPrice;
            that.commissionCount = res.data.commissionCount;
          },
          function (err) {
            uni.showToast({
              title: err.msg || err.response.data.msg || err.response.data.message,
              icon: "none",
              duration: 2000
            });
          }
        );
      },
      async submitted() {
        let bankname = this.post.bankname,
          alipay_code = this.post.alipay_code,
          money = this.post.money,
          name = this.post.name,
          cardnum = this.post.cardnum,
          weixin = this.post.weixin,
          that = this;
        // console.log(parseFloat(money))
        if (
          parseFloat(money) > parseFloat(that.commissionCount) ||
          parseFloat(that.commissionCount) == 0
        ) {
          uni.showToast({
            title: "余额不足",
            icon: "none",
            duration: 2000
          });
          return
        }
        if (parseFloat(money) < parseFloat(that.minPrice)) {
          uni.showToast({
            title: "最低提现金额" + that.minPrice,
            icon: "none",
            duration: 2000
          });
          return
        }
        //console.log(that.post.extract_type)
        switch (that.post.extract_type) {
          case "alipay":
            // if (!name) {
            //   uni.showToast({
            //     title: "请输入支付宝用户名",
            //     icon: "none",
            //     duration: 2000
            //   });
            //   return;
            // }
            // if (!alipay_code) {
            //   uni.showToast({
            //     title: "请输入支付宝账号",
            //     icon: "none",
            //     duration: 2000
            //   });
            //   return;
            // }
            // if (!money) {
            //   uni.showToast({
            //     title: "请输入提现金额",
            //     icon: "none",
            //     duration: 2000
            //   });
            //   return;
            // }
            try {
              await this.$validator({
                name: [required(required.message("支付宝用户名"))],
                alipay_code: [required(required.message("支付宝账号"))],
                money: [required(required.message("提现金额"))]
              }).validate({
                name,
                alipay_code,
                money
              });
              let save = {
                extractType: that.post.extract_type,
                alipayCode: alipay_code,
                name: name,
                money: money
              };
              that.save(save);
            } catch (e) {
              return validatorDefaultCatch(e);
            }
            break;
          case "weixin":
            try {
              await this.$validator({
                weixin: [required(required.message("提现微信号"))],
                money: [required(required.message("提现金额"))]
              }).validate({
                weixin,
                money
              });
              let save = {
                extractType: that.post.extract_type,
                weixin: weixin,
                money: money
              };
              that.save(save);
            } catch (e) {
              return validatorDefaultCatch(e);
            }
            break;
        }
      },
      save: function (info) {
        postCashInfo(info).then(
          res => {
            uni.showToast({
              title: res.msg,
              icon: "none",
              duration: 2000
            });
            this.$yrouter.push({
              path: "/pages/user/promotion/CashAudit/index"
            });
          },
          err => {
            uni.showToast({
              title: err.msg || err.response.data.msg || err.response.data.message,
              icon: "none",
              duration: 2000
            });
          }
        );
      }
    }
  };
</script>
<style lang="less">
  .cash-withdrawal .pos-order-list .nav .item.on {
    color: #eb3729
  }
</style>
