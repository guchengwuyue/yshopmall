<template>
  <view class="personal-data">
    <view class="wrapper">
		<view class="wrapList">
			<view class="item acea-row row-between-wrapper on">
				<view class="picTxt acea-row row-between-wrapper">
					<view class="pictrue" @tap="chooseImage">
					  <div class="pictrue">
						<img :src="avatar" />
					  </div>
					  <image :src="`${$VUE_APP_RESOURCES_URL}/images/alter.png`" class="alter" />
					</view>
					<view class="text">
					  <view class="name line1">{{ userInfo.nickname }}</view>
					  <view class="phone">
						绑定手机号：
						<text v-if="userInfo.phone">{{ userInfo.phone }}</text>
						<text v-else>未绑定</text>
					  </view>
					</view>
				</view>
			</view>
		</view>
	</view>
	<view class="list">
		<view class="item acea-row row-between-wrapper">
			<view>昵称</view>
			<view class="input">
			  <input type="text" v-model="userInfo.nickname" />
			</view>
		</view>
		<view class="item acea-row row-between-wrapper">
			<view>ID号</view>
			<view class="input acea-row row-between-wrapper">
			  <input type="text" :value="userInfo.uid" disabled class="id" />
			  <text class="iconfont icon-suozi"></text>
			</view>
		</view>
		<view class="item acea-row row-between-wrapper">
			<view>手机号</view>
			<view class="input">
				<!-- <input type="text" v-model="userInfo.phone" placeholder="未绑定"/> -->
			  <input type="text" v-if="userInfo.phone" v-model="userInfo.phone" placeholder="未绑定"/>
			  <input type="text" v-else value="未绑定" disabled class="id" />
			</view>
		</view>
        <view class="item acea-row row-between-wrapper" @click="goChangePassword()">
          <view>密码</view>
          <view class="input acea-row row-between-wrapper">
            <text>点击修改密码</text>
            <text class="iconfont icon-suozi"></text>
          </view>
        </view>
    </view>
    <view class="modifyBnt bg-color-red" @click="submit">保存修改</view>

    <view
      class="logOut cart-color acea-row row-center-wrapper"
      @click="logout"
    >退出登录</view>

  </view>
</template>
<script>
import { mapGetters } from "vuex";
import { trim, isWeixin, chooseImage } from "@/utils";
import { VUE_APP_API_URL } from "@/config";
import {
  postUserEdit,
  getLogout,
  switchH5Login,
  getUserInfo,
} from "@/api/user";
import cookie from "@/utils/store/cookie";
import store from "@//store";
import dayjs from "dayjs";

export default {
  name: "PersonalData",
  components: {
    // VueCoreImageUpload
  },
  data: function () {
    return {
      avatar: "",
      isWeixin: false,
      currentAccounts: 0,
      switchUserInfo: [],
      userIndex: 0,
    };
  },
  computed: mapGetters(["userInfo"]),
  mounted: function () {
    this.avatar = this.userInfo.avatar;
    this.isWeixin = isWeixin();
    this.getUserInfo();
  },
  methods: {
    goChangePassword() {
      this.$yrouter.push("/pages/user/ChangePassword/index");
    },
    switchAccounts: function (index) {
      let that = this;
      this.userIndex = index;
      let userInfo = this.switchUserInfo[this.userIndex];
      if (this.switchUserInfo.length <= 1) return true;
      if (userInfo === undefined) {
        uni.showToast({
          title: "切换的账号不存在",
          icon: "none",
          duration: 2000,
        });
        return;
      }
      if (userInfo.user_type === "h5") {
        switchH5Login()
          .then(({ data }) => {
            uni.hideLoading();
            const expires_time = dayjs(data.expires_time);
            store.commit("login", data.token, expires_time);
            that.$emit("changeswitch", false);
            location.reload();
          })
          .catch((err) => {
            uni.hideLoading();
            uni.showToast({
              title:
                err.msg || err.response.data.msg || err.response.data.message,
              icon: "none",
              duration: 2000,
            });
          });
      } else {
        cookie.set("loginType", "wechat", 60);
        uni.hideLoading();
        this.$store.commit("logout");
        this.$emit("changeswitch", false);
      }
    },
    getUserInfo: function () {
      let that = this;
      getUserInfo().then((res) => {
        // let switchUserInfo = res.data.switchUserInfo;
        // for (let i = 0; i < switchUserInfo.length; i++) {
        //   if (switchUserInfo[i].uid == that.userInfo.uid) that.userIndex = i;
        //   if (
        //     !that.isWeixin &&
        //     switchUserInfo[i].user_type != "h5" &&
        //     switchUserInfo[i].phone === ""
        //   )
        //     switchUserInfo.splice(i, 1);
        // }
        // that.$set(this, "switchUserInfo", switchUserInfo);
      });
    },
    chooseImage() {
      chooseImage((img) => {
        this.avatar = img;
      });
    },

    submit: function () {
      let userInfo = this.userInfo;
      postUserEdit({
        nickname: trim(this.userInfo.nickname ? this.userInfo.nickname : ''),
        avatar: this.avatar,
      }).then(
        (res) => {
          this.$store.dispatch("userInfo", true);
          uni.showToast({
            title: res.msg,
            icon: "none",
            duration: 2000,
          });
          this.$yrouter.back();
        },
        (err) => {
          uni.showToast({
            title:
              err.msg || err.response.data.msg || err.response.data.message,
            icon: "none",
            duration: 2000,
          });
        }
      );
    },
    logout: function () {
      uni.showModal({
        title: "提示",
        content: "确认退出登录?",
        success: (res) => {
          if (res.confirm) {
            getLogout()
              .then((res) => {
                this.$store.commit("logout");
                this.$yrouter.replace({
                  path: "/pages/user/Login/index",
                  query: {},
                });
              })
              .catch((err) => {});
          } else if (res.cancel) {
            // console.log("用户点击取消");
          }
        },
      });
    },
  },
};
</script>
