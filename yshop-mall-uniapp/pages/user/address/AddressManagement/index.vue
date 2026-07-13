<template>
  <view
    class="address-management"
    :class="addressList.length < 1 && page > 1 ? 'on' : ''"
    ref="container"
  >
    <view class="line" v-if="addressList.length > 0">
      <image :src="`${$VUE_APP_RESOURCES_URL}/images/line.jpg`" />
    </view>
    <view class="item" v-for="(item, addressListIndex) in addressList" :key="addressListIndex">
      <view class="address">
        <view class="consignee">
          收货人：{{ item.realName }}
          <text class="phone">{{ item.phone }}</text>
        </view>
        <view>
          收货地址：{{ item.province }}{{ item.city }}{{ item.district
          }}{{ item.detail }}
        </view>
      </view>
      <view class="operation acea-row row-between-wrapper">
        <view class="select-btn">
          <view class="checkbox-wrapper">
            <checkbox-group @change="radioChange(item.id)">
              <label class="well-check">
                <checkbox :value="item.isDefault==1?'checked':''" :checked="item.isDefault||item.isDefault=='1' ? true : false"></checkbox>
                <text class="default">设为默认</text>
              </label>
            </checkbox-group>
            <!-- <label class="well-check">
              <input
                type="radio"
                name="default"
                value
                :checked="item.isDefault ? true : false"
                @click="radioChange(addressListIndex)"
              />
              <i class="icon"></i>
              <text class="default">设为默认</text>
            </label>-->
          </view>
        </view>
        <view class="acea-row row-middle">
          <view @click="editAddress(addressListIndex)">
            <text class="iconfont icon-bianji"></text>编辑
          </view>
          <view @click="delAddress(addressListIndex)">
            <text class="iconfont icon-shanchu"></text>删除
          </view>
        </view>
      </view>
    </view>
    <Loading :loaded="loadend" :loading="loading"></Loading>
    <view class="noCommodity" v-if="addressList.length < 1 && page > 1">
      <view class="noPictrue">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noAddress.png`" class="image" />
      </view>
    </view>
    <view style="height:100rpx;"></view>
    <view class="footer acea-row row-between-wrapper">
      <view class="addressBnt bg-color-red" v-if="isWechat" @click="addAddress">
        <text class="iconfont icon-tianjiadizhi"></text>添加新地址
      </view>
      <view class="addressBnt on bg-color-red" v-else @click="addAddress">
        <text class="iconfont icon-tianjiadizhi"></text>添加新地址
      </view>
      <!--<view class="addressBnt wxbnt" v-if="isWechat" @click="getAddress">-->
      <!--<text class="iconfont icon-weixin2"></text>导入微信地址-->
      <!--</view>-->
    </view>
  </view>
</template>
<style scoped lang="less">
.address-management.on {
  background-color: #fff;
  height: 100vh;
}
</style>
<script type="text/babel">
import {
  getAddressList,
  getAddressRemove,
  getAddressDefaultSet,
  postAddress
} from "@/api/user";
import Loading from "@/components/Loading";
import { isWeixin } from "@/utils";
// import { openAddress } from "@/libs/wechat";

export default {
  components: {
    Loading
  },
  data() {
    return {
      page: 1,
      limit: 20,
      addressList: [],
      loadTitle: "",
      loading: false,
      loadend: false,
      isWechat: isWeixin()
    };
  },
  mounted: function() {
    this.AddressList();
  },
  onReachBottom() {
    !this.loading && this.AddressList();
  },
  onShow: function() {
    this.refresh();
  },
  methods: {
    refresh: function() {
      this.addressList = [];
      this.page = 1;
      this.loadend = false;
      this.AddressList();
    },
    /**
     * 获取地址列表
     *
     */
    AddressList: function() {
      let that = this;
      if (that.loading) return; //阻止下次请求（false可以进行请求）；
      if (that.loadend) return; //阻止结束当前请求（false可以进行请求）；
      that.loading = true;
      getAddressList({ page: that.page, limit: that.limit }).then(res => {
        that.loading = false;
        //apply();js将一个数组插入另一个数组;
        that.addressList.push.apply(that.addressList, res.data);
        that.loadend = res.data.length < that.limit; //判断所有数据是否加载完成；
        that.page = that.page + 1;
      });
    },
    /**
     * 编辑地址
     */
    editAddress: function(index) {
      this.$yrouter.push({
        path: "/pages/user/address/AddAddress/index",
        query: { id: this.addressList[index].id }
      });
    },
    /**
     * 删除地址
     */
    delAddress: function(index) {
      let that = this;
      let address = this.addressList[index];
      let id = address.id;
      getAddressRemove(id).then(function() {
        uni.showToast({
          title: "删除成功!",
          icon:"success",
          duration: 2000,
          complete: () => {
            that.addressList.splice(index, 1);
            that.$set(that, "addressList", that.addressList);
          }
        });
      });
    },
    /**
     * 设置默认地址
     */
    radioChange: function(id) {
      getAddressDefaultSet(id).then(res => {
        this.refresh();
        uni.showToast({ title: res.msg, icon: "none", duration: 2000 });
      });
    },
    /**
     * 新增地址
     */
    addAddress: function() {
      this.$yrouter.push({
        path: "/pages/user/address/AddAddress/index"
      });
    },
    getAddress() {
      // openAddress().then(userInfo => {
      //   uni.showLoading({ title: "加载中" });
      //   postAddress({
      //     real_name: userInfo.userName,
      //     phone: userInfo.telNumber,
      //     address: {
      //       province: userInfo.provinceName,
      //       city: userInfo.cityName,
      //       district: userInfo.countryName
      //     },
      //     detail: userInfo.detailInfo,
      //     post_code: userInfo.postalCode,
      //     wx_export: 1
      //   })
      //     .then(() => {
      //       this.page = 1;
      //       this.loading = false;
      //       this.loadend = false;
      //       this.addressList = [];
      //       this.AddressList();
      //       uni.hideLoading();
      // uni.showToast({
      // 											title: "添加成功",
      // 											icon: 'success',
      // 											duration: 2000
      // 										});
      //     })
      //     .catch(err => {
      //       uni.hideLoading();
      // uni.showToast({
      // 	title: err.msg || err.response.data.msg|| err.response.data.message,
      // 	icon: 'none',
      // 	duration: 2000
      // });
      //     });
      // });
    }
  }
};
</script>
