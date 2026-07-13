<template>
  <view>
    <view class="address-window" :class="value === true ? 'on' : ''">
      <view class="title">
        选择地址
        <text class="iconfont icon-guanbi" @click="closeAddress"></text>
      </view>
      <view class="list" v-if="addressList.length">
        <view
          class="item acea-row row-between-wrapper"
          :class="item.id === checked ? 'font-color-red' : ''"
          v-for="(item, addressIndex) in addressList"
          @click="tapAddress(addressIndex)"
          :key="addressIndex"
        >
          <text class="iconfont icon-ditu" :class="item.id === checked ? 'font-color-red' : ''"></text>
          <view class="addressTxt">
            <view class="name" :class="item.id === checked ? 'font-color-red' : ''">
              {{ item.realName }}
              <text class="phone">{{ item.phone }}</text>
            </view>
            <view class="line1">
              {{ item.province }}{{ item.city }}{{ item.district
              }}{{ item.detail }}
            </view>
          </view>
          <text class="iconfont icon-complete" :class="item.id === checked ? 'font-color-red' : ''"></text>
        </view>
      </view>
      <view class="pictrue" v-if="addressList.length < 1">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noAddress.png`" class="image" />
      </view>
      <view class="addressBnt bg-color-red" @click="goAddressPages">新加地址</view>
    </view>
    <view class="mask" @touchmove.prevent :hidden="value === false" @click="closeAddress"></view>
  </view>
</template>
<script>
import { getAddressList } from "@/api/user";

export default {
  name: "AddressWindow",
  props: {
    value: Boolean,
    checked: Number
  },
  data: function() {
    return {
      addressList: [],
      current: 0,
      cartId: 0,
      pinkId: 0,
      couponId: 0
    };
  },
  mounted: function() {},
  methods: {
    getAddressList: function() {
      let that = this;
      getAddressList().then(res => {
        that.addressList = res.data;
      });
    },
    closeAddress() {
      this.$emit("input", false);
    },
    goAddressPages: function() {
      this.$yrouter.push({ path: "/pages/user/address/AddAddress/index" });
      this.$emit("redirect");
    },
    tapAddress: function(index) {
      this.$emit("checked", this.addressList[index]);
      this.$emit("input", false);
    }
  }
};
</script>
