<template>
  <view class="shoppingCart">
    <view v-if="$store.getters.token||userInfo.uid">
      <view class="labelNav acea-row row-around row-middle">
        <view class="item">
          <text class="iconfont icon-xuanzhong"></text>100%正品保证
        </view>
        <view class="item">
          <text class="iconfont icon-xuanzhong"></text>所有商品精挑细选
        </view>
        <view class="item">
          <text class="iconfont icon-xuanzhong"></text>售后无忧
        </view>
      </view>
      <view class="nav acea-row row-between-wrapper">
        <view>
          购物数量
          <text class="num font-color-red">{{ count }}</text>
        </view>
        <view v-if="cartList.valid.length > 0" class="administrate acea-row row-center-wrapper" @click="manage">
          {{ footerswitch ? '取消' : '管理' }}</view>
      </view>
      <view v-if="validList.length > 0 || cartList.invalid.length > 0">
        <view class="list">
          <view class="item acea-row row-between-wrapper" v-for="(item, cartListValidIndex) in validList"
            :key="cartListValidIndex">
            <view class="select-btn">
              <view class="checkbox-wrapper">
                <checkbox-group @change="switchSelect(cartListValidIndex)">
                  <label class="well-check">
                    <checkbox color="#eb3729" value :checked="item.checked"></checkbox>
                  </label>
                </checkbox-group>
              </view>
            </view>
            <view class="picTxt acea-row row-between-wrapper">
              <view class="pictrue" @click="goGoodsCon(item)">
                <image :src="item.productInfo.attrInfo.image" v-if="item.productInfo.attrInfo" />
                <image :src="item.productInfo.image" v-else />
              </view>
              <view class="text">
                <view class="line1">{{ item.productInfo.storeName }}</view>
                <view class="infor line1" v-if="item.productInfo.attrInfo">属性：{{ item.productInfo.attrInfo.sku }}</view>
                <view class="money">￥{{ item.truePrice }}</view>
              </view>
              <view class="carnum acea-row row-center-wrapper">
                <view class="reduce" :class="validList[cartListValidIndex].cartNum <= 1 ? 'on' : ''"
                  @click.prevent="reduce(cartListValidIndex)">-</view>
                <view class="num">{{ item.cartNum }}</view>
                <view class="plus" v-if="validList[cartListValidIndex].attrInfo"
                  :class="validList[cartListValidIndex].cartNum >= validList[cartListValidIndex].attrInfo.stock ? 'on' : ''"
                  @click.prevent="plus(cartListValidIndex)">+</view>
                <view class="plus" v-else
                  :class="validList[cartListValidIndex].cartNum >= validList[cartListValidIndex].stock ? 'on' : ''"
                  @click.prevent="plus(cartListValidIndex)">+</view>
              </view>
            </view>
          </view>
        </view>
        <view class="invalidGoods" v-if="cartList.invalid.length > 0">
          <view class="goodsNav acea-row row-between-wrapper">
            <view @click="goodsOpen">
              <text class="iconfont" :class="goodsHidden === true ? 'icon-xiangyou' : 'icon-xiangxia'"></text>失效商品
            </view>
            <view class="del" @click="delInvalidGoods">
              <text class="iconfont icon-shanchu1"></text>清空
            </view>
          </view>
          <view class="goodsList" :hidden="goodsHidden">
            <view v-for="(item, cartListinvalidIndex) in cartList.invalid" :key="cartListinvalidIndex">
              <view @click="goGoodsCon(item)" class="item acea-row row-between-wrapper" v-if="item.productInfo">
                <view class="invalid acea-row row-center-wrapper">失效</view>
                <view class="pictrue">
                  <image :src="item.productInfo.attrInfo.image" v-if="item.productInfo.attrInfo" />
                  <image :src="item.productInfo.image" v-else />
                </view>
                <view class="text acea-row row-column-between">
                  <view class="line1">{{ item.productInfo.storeName }}</view>
                  <view class="infor line1" v-if="item.productInfo.attrInfo">属性：{{ item.productInfo.attrInfo.sku }}
                  </view>
                  <view class="acea-row row-between-wrapper">
                    <view class="end">该商品已下架</view>
                  </view>
                </view>
              </view>
            </view>
          </view>
        </view>
      </view>
      <!--购物车暂无商品-->
      <view class="noCart" v-if="cartList.valid.length === 0 && cartList.invalid.length === 0">
        <view class="pictrue">
          <image :src="`${$VUE_APP_RESOURCES_URL}/images/noCart.png`" />
        </view>
        <Recommend></Recommend>
      </view>
      <view style="height:210rpx"></view>
      <view :class="{'footer acea-row row-between-wrapper':true,'footer-h5':isH5}" v-if="cartList.valid.length > 0">
        <view>
          <view class="select-btn">
            <view class="checkbox-wrapper">
              <!-- <label class="well-check">
              <input
                type="checkbox"
                name
                value
                :checked="isAllSelect && cartCount > 0"
                @click="allChecked"
              />
              <i class="icon"></i>
              <text class="checkAll">全选 ({{ cartCount }})</text>
              </label>-->

              <checkbox-group @change="allChecked">
                <label class="well-check">
                  <checkbox color="#eb3729" value="allSelect" :checked="isAllSelect && cartCount > 0"></checkbox>
                  <text class="checkAll">全选 ({{ cartCount }})</text>
                </label>
              </checkbox-group>
            </view>
          </view>
        </view>
        <view class="money acea-row row-middle" v-if="footerswitch === false">
          <text class="font-color-red">￥{{ countmoney }}</text>
          <view class="placeOrder bg-color-red" @click="placeOrder">立即下单</view>
        </view>
        <view class="button acea-row row-middle" v-else>
          <!-- <view class="bnt cart-color" @click="collectAll">收藏</view> -->
          <view class="bnt" @click="delgoods">删除</view>
        </view>
      </view>
    </view>
    <Authorization v-else ref="authorization" />
  </view>
</template>
<script>
  import Recommend from "@/components/Recommend";
  import Authorization from "@/pages/authorization/index";
  import {
    mapGetters
  } from "vuex";

  import {
    getCartList,
    postCartDel,
    changeCartNum,
    getCartCount
  } from "@/api/store";
  import {
    postCollectAll
  } from "@/api/user";
  import {
    mul,
    add
  } from "@/utils/bc";
  import cookie from "@/utils/store/cookie";

  const CHECKED_IDS = "cart_checked";

  export default {
    name: "ShoppingCart",
    components: {
      Recommend,
      Authorization
    },
    props: {},
    data: function () {
      return {
        cartList: {
          invalid: [],
          valid: []
        },
        isH5: false,
        validList: [],
        isAllSelect: false,
        cartCount: 0,
        countmoney: 0,
        goodsHidden: true,
        footerswitch: false,
        count: 0,
        checkedIds: [],
        loaded: false
      };
    },
    computed: mapGetters(["userInfo", "token"]),
    //   watch: {
    //     $yroute(n) {
    //       if (n.name === "ShoppingCart") {
    //         this.carnum();
    //         this.countMoney();
    //         this.getCartList();
    //         this.gainCount();
    //         this.goodsHidden = true;
    //         this.footerswitch = false;
    //       }
    //     },
    //     cartList(list) {
    //       this.validList = list.valid;
    //     }
    //   },
	onLoad() {
	  // if (wx.getUserProfile) {
	  //   this.canIUseGetUserProfile = true
	  // }
	  if (!this.$store.getters.token && !this.userInfo.uid) {
	    this.$refs.authorization.getLoginCode()
	  }
	},
    watch: {
      userInfo(user) {
        if (user.uid) {
          this.carnum();
          this.countMoney();
          this.getCartList();
          this.gainCount();
        }
      },
      token(token) {
        if (this.userInfo.uid) {
          this.carnum();
          this.countMoney();
          this.getCartList();
          this.gainCount();
        }
      },
      cartList(list) {
        this.validList = list.valid;
      }
    },
    onShow: function () {
      // #ifdef H5
      this.isH5 = true
      // #endif
      console.log(this.userInfo)
      if (this.userInfo.uid) {
        this.carnum();
        this.countMoney();
        this.getCartList();
        this.gainCount();
      }
    },
    methods: {
      goGoodsCon(item) {
        this.$yrouter.push({
          path: "/pages/shop/GoodsCon/index",
          query: {
            id: item.productId
          }
        });
      },
      getCartList: function () {
        let that = this;
        getCartList().then(res => {
          that.cartList = res.data;
          let checkedIds = cookie.get(CHECKED_IDS) || [];
          if (!Array.isArray(checkedIds)) checkedIds = [];
          this.cartList.valid.forEach(cart => {
            if (checkedIds.indexOf(cart.id) !== -1) cart.checked = true;
          });
          if (checkedIds.length) {
            that.checkedIds = checkedIds;
            that.isAllSelect = checkedIds.length === this.cartList.valid.length;
            that.carnum();
            that.countMoney();
          }
          this.loaded = true;
        });
      },
      //删除商品；
      delgoods: function () {
        let that = this,
          id = [],
          valid = [],
          list = that.cartList.valid;
        list.forEach(function (val) {
          if (val.checked === true) {
            id.push(val.id);
          }
        });
        if (id.length === 0) {
          uni.showToast({
            title: "请选择产品",
            icon: "none",
            duration: 2000
          });
          return;
        }
        postCartDel(id).then(function () {
          list.forEach(function (val, i) {
            if (val.checked === false || val.checked === undefined)
              valid.push(list[i]);
          });
          that.$set(that.cartList, "valid", valid);
          that.carnum();
          that.countMoney();
          that.gainCount();
          that.getCartList();
        });
      },
      // //获取数量
      gainCount: function () {
        let that = this;
        getCartCount().then(res => {
          that.count = res.data.count;
        });
      },
      //清除失效产品；
      delInvalidGoods: function () {
        let that = this,
          id = [],
          list = that.cartList.invalid;
        list.forEach(function (val) {
          id.push(val.id);
        });
        postCartDel(id).then(function () {
          list.splice(0, list.length);
          that.gainCount();
          that.getCartList();
        });
      },
      //批量收藏;
      collectAll: function () {
        let that = this,
          data = {
            id: [],
            category: ""
          },
          list = that.cartList.valid;
        list.forEach(function (val) {
          if (val.checked === true) {
            data.id.push(val.product_id);
            data.category = val.type;
          }
        });
        if (data.id.length === 0) {
          uni.showToast({
            title: "请选择产品",
            icon: "none",
            duration: 2000
          });
          return;
        }
        postCollectAll(data).then(function () {
          uni.showToast({
            title: "收藏成功!",
            icon: "none",
            duration: 2000
          });
        });
      },
      //立即下单；
      placeOrder: function () {
        let that = this,
          list = that.cartList.valid,
          id = [];
        list.forEach(function (val) {
          if (val.checked === true) {
            id.push(val.id);
          }
        });
        if (id.length === 0) {
          uni.showToast({
            title: "请选择产品",
            icon: "none",
            duration: 2000
          });
          return;
        }
        this.$yrouter.push({
          path: "/pages/order/OrderSubmission/index",
          query: {
            id: id.join(",")
          }
        });
      },
      manage: function () {
        let that = this;
        that.footerswitch = !that.footerswitch;
      },
      goodsOpen: function () {
        let that = this;
        that.goodsHidden = !that.goodsHidden;
      },
      //加
      plus: function (index) {
        let that = this;
        let list = that.cartList.valid[index];
        list.cartNum++;
        if (list.attrInfo) {
          if (list.cartNum >= list.attrInfo.stock) {
            that.$set(list, "cart_num", list.attrInfo.stock);
          }
        } else {
          if (list.cartNum >= list.stock) {
            that.$set(list, "cart_num", list.stock);
          }
        }
        that.carnum();
        that.countMoney();
        that.syncCartNum(list);
      },
      //减
      reduce: function (index) {
        let that = this;
        let list = that.cartList.valid[index];
        if (list.cartNum <= 1) {
          uni.showToast({
            title: "已经是底线啦!",
            icon: "none",
            duration: 2000
          });
          return;
        }
        list.cartNum--;
        if (list.cartNum < 1) {
          that.$set(list, "cart_num", 1);
        }
        that.carnum();
        that.countMoney();
        that.syncCartNum(list);
      },
      syncCartNum(cart) {
        if (!cart.sync) {
          changeCartNum(cart.id, Math.max(cart.cartNum, 1) || 1)
            .then(res => {
              this.getCartList();
              this.gainCount();
            })
            .catch(error => {
              this.gainCount();
              uni.showToast({
                title: error.response.data.msg,
                icon: "none",
                duration: 2000
              });
            });
        }
      },
      //单选
      switchSelect: function (index) {
        let that = this,
          cart = that.cartList.valid[index],
          i = this.checkedIds.indexOf(cart.id);
        cart.checked = !cart.checked;

        if (i !== -1) this.checkedIds.splice(i, 1);
        if (cart.checked) {
          this.checkedIds.push(cart.id);
        }
        let len = that.cartList.valid.length;
        let selectnum = [];
        for (let i = 0; i < len; i++) {
          if (that.cartList.valid[i].checked === true) {
            selectnum.push(true);
          }
        }
        that.isAllSelect = selectnum.length === len;
        that.$set(that, "cartList", that.cartList);
        that.$set(that, "isAllSelect", that.isAllSelect);
        cookie.set(CHECKED_IDS, that.checkedIds);
        that.carnum();
        that.gainCount();
        that.countMoney();
      },
      //全选
      allChecked: function (e) {
        console.log(e);
        let that = this;
        let selectAllStatus = e.mp.detail.value[0] == "allSelect" ? true : false;
        console.log(selectAllStatus);
        // let selectAllStatus = that.isAllSelect;
        let checkedIds = [];
        // for (let i = 0; i < array.length; i++) {
        //   array[i].checked = selectAllStatus;
        //   checked.push()
        // }
        that.cartList.valid.forEach(cart => {
          cart.checked = selectAllStatus;
          if (selectAllStatus) {
            checkedIds.push(cart.id);
          }
        });
        let cartList = {
          ...that.cartList
        };
        that.cartList = [];
        that.cartList = cartList;
        console.log(this.cartList);
        this.$set(this, "cartList", this.cartList);
        this.$set(this, "isAllSelect", selectAllStatus);
        this.checkedIds = checkedIds;
        cookie.set(CHECKED_IDS, checkedIds);
        that.carnum();
        that.countMoney();
        this.$forceUpdate();
      },
      //数量
      carnum: function () {
        let that = this;
        var carnum = 0;
        var array = that.cartList.valid;
        for (let i = 0; i < array.length; i++) {
          if (array[i].checked === true) {
            carnum += parseInt(array[i].cartNum);
          }
        }
        that.$set(that, "cartCount", carnum);
      },
      //总共价钱；
      countMoney: function () {
        let that = this;
        let carmoney = 0;
        let array = that.cartList.valid;
        for (let i = 0; i < array.length; i++) {
          if (array[i].checked === true) {
            carmoney = add(carmoney, mul(array[i].cartNum, array[i].truePrice));
          }
        }
        that.countmoney = carmoney;
      }
    }
  };
</script>

<style lang="less">
  .footer-h5 {
    bottom: 50px
  }
  .shoppingCart{
	  position: relative;
	  .list{
		  margin-top: 150rpx;
		  padding-top: 15rpx;
	  }
  }
</style>
