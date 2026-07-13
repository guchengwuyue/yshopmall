<template>
  <view class="page">
    <view :class="tabtarIndex==0?'page':''" v-show="tabtarIndex==0">
      <home></home>
    </view>
    <view :class="tabtarIndex==1?'page':''" v-show="tabtarIndex==1">
      <goods-class></goods-class>
    </view>
    <view :class="tabtarIndex==2?'page':''" v-if="tabtarIndex==2">
      <shopping-cart></shopping-cart>
    </view>
    <view :class="tabtarIndex==3?'page':''" v-if="tabtarIndex==3">
      <user></user>
    </view>
    <footer-components></footer-components>
  </view>
</template>

<script>
import Home from "@/pages/home/index";
import GoodsClass from "@/pages/shop/GoodsClass/index";
import ShoppingCart from "@/pages/shop/ShoppingCart/index";
import User from "@/pages/user/User/index";
import FooterComponents from "@/components/Footer";
import { mapState, mapMutations, mapActions } from "vuex";


export default {
  name: "Launch",
  data() {
    return {
      history: [],
    };
  },
  components: {
    Home,
    GoodsClass,
    ShoppingCart,
    User,
    FooterComponents
  },
  computed: {
    ...mapState(["tabtarIndex"])
  },
  methods: {
  },
  mounted() {
    let type = this.$yroute.query.type;
    if (type) {
    }
	
    // if (!this.tabtarIndex) {
    // }
  },
  onHide() {
  }
};
</script>

<style scoped lang="less">
.lottie-bg {
  position: fixed;
  left: 0;
  top: 0;
  background-color: #fff;
  width: 100%;
  height: 100%;
  z-index: 999;
  display: -webkit-flex;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  justify-content: center;
}

#lottie {
  width: 35%;
  display: block;
  overflow: hidden;
  transform: translate3d(0, 0, 0);
  margin: auto;
}

.iphonex {
  height: 68rpx;
}
</style>
