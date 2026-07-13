<template>
  <view>
    <view class="storeBox" ref="container">
      <view
        class="storeBox-box"
        v-for="(item, index) in storeList"
        :key="index"
        @click="checked(item)"
      >
        <view class="store-img">
          <img :src="item.image" lazy-load="true" />
        </view>
        <view class="store-cent-left">
          <text class="store-name">{{ item.name }}</text>
          <text class="store-address line1">{{ item.address }}</text>
        </view>
        <view class="row-right">
          <view>
            <a class="store-phone" @click="telPhone(item.phone)">
              <text class="iconfont icon-dadianhua01"></text>
            </a>
          </view>
          <view class="store-distance" @click="showMaoLocation(item)">
            <text class="addressTxt" v-if="item.distance">距离{{ item.distance }}千米</text>
            <text class="addressTxt" v-else>查看地图</text>
            <text class="iconfont icon-youjian"></text>
          </view>
        </view>
      </view>
      <Loading :loaded="loaded" :loading="loading"></Loading>
    </view>
  </view>
</template>

<script>
import Loading from "@/components/Loading";
import { storeListApi } from "@/api/store";
import { isWeixin,tel } from "@/utils/index";
import { wechatEvevt, wxShowLocation } from "@/libs/wechat";
import { mapGetters } from "vuex";
import cookie from "@/utils/store/cookie";
const LONGITUDE = "user_longitude";
const LATITUDE = "user_latitude";
const MAPKEY = "mapKey";
export default {
  name: "storeList",
  components: { Loading },
  computed: mapGetters(["location", "goName"]),
  data() {
    return {
      page: 1,
      limit: 20,
      loaded: false,
      loading: false,
      storeList: [],
      mapShow: false,
      system_store: {},
      mapKey: cookie.get(MAPKEY),
      locationShow: false
    };
  },
  mounted() {
    this.getList();
  },
  onReachBottom() {
    !this.loading && this.getOrderList();
  },
  methods: {
    showMaoLocation(data) {
      this.$yrouter.push({
        path: "/pages/map/index",
        query: data
      });
    },
    // 选中门店
    checked(e) {
      if (this.goName === "orders") {
        this.$store.commit("get_store", e);
        this.$yrouter.back();
      }
    },
    //拨打电话
    telPhone(phoneNumber) {
      uni.makePhoneCall({
        phoneNumber: phoneNumber,
        fail() {
          console.log("取消拨打");
        }
      });
    },
    // 获取门店列表数据
    getList: function() {
      if (this.loading || this.loaded) return;
      this.loading = true;
      let data = {
        latitude: this.location.latitude, //纬度
        longitude: this.location.longitude, //经度
        page: this.page,
        limit: this.limit
      };
      storeListApi(data)
        .then(res => {
          this.loading = false;
          this.loaded = res.data.list.length < this.limit;
          this.storeList.push.apply(this.storeList, res.data.list);
          this.page = this.page + 1;
          this.mapKey = res.data.mapKey;
        })
        .catch(err => {
          uni.showToast({
					title: err.msg,
					icon: "none",
					duration: 2000,
				});
        });
    }
  }
};
</script>

<style scoped lang="less">
.geoPage {
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  z-index: 10000;
}
.storeBox {
  background-color: #fff;
  padding: 0 0.3 * 100rpx;
}
.storeBox-box {
  width: 100%;
  height: auto;
  display: flex;
  align-items: center;
  padding: 0.23 * 100rpx 0;
  justify-content: space-between;
  border-bottom: 1px solid #eee;
}
.store-cent {
  display: flex;
  align-items: center;
  width: 80%;
}
.store-cent-left {
  width: 45%;
  text {
    display: block;
  }
}
.store-img {
  width: 1.2 * 100rpx;
  height: 1.2 * 100rpx;
  border-radius: 0.06 * 100rpx;
  margin-right: 0.22 * 100rpx;
}
.store-img img {
  width: 100%;
  height: 100%;
}
.store-name {
  color: #282828;
  font-size: 0.3 * 100rpx;
  margin-bottom: 0.22 * 100rpx;
  font-weight: 800;
}
.store-address {
  color: #666666;
  font-size: 0.24 * 100rpx;
}
.store-phone {
  width: 0.5 * 100rpx;
  height: 0.5 * 100rpx;
  color: #fff;
  border-radius: 50%;
  display: block;
  text-align: center;
  line-height: 0.5 * 100rpx;
  background-color: #e83323;
  margin-bottom: 0.22 * 100rpx;
}
.store-distance {
  font-size: 0.22 * 100rpx;
  color: #e83323;
}
.iconfont {
  font-size: 0.2 * 100rpx;
}
.row-right {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  width: 33.5%;
}
</style>
