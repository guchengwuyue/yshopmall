<template>
  <view ref="container">
    <div class="coupon-list" v-if="couponsList.length > 0">
      <div class="item acea-row row-center-wrapper" v-cloak v-for="(item, index) in couponsList" :key="index">
        <div class="money" :class="item._type === 0 ? 'moneyGray' : ''">
          <div>
            ￥<span class="num">{{ item.couponPrice }}</span>
          </div>
          <div class="pic-num">满{{ item.useMinPrice }}元可用</div>
        </div>
        <div class="text">
          <div class="condition line1">
            {{ item.couponTitle }}
          </div>
          <div class="data acea-row row-between-wrapper">
            <div v-if="item.endTime === 0">不限时</div>
            <div v-else>{{ item.createTime }}-{{ item.endTime }}</div>
            <div class="bnt gray" v-if="item._type === 0">{{ item._msg }}</div>
            <div class="bnt bg-color-red" v-else>{{ item._msg }}</div>
          </div>
        </div>
      </div>
    </div>
    <!--暂无优惠券-->
    <view class="noCommodity" v-if="couponsList.length === 0 && loading === true">
      <view class="noPictrue">
        <image :src="`${$VUE_APP_RESOURCES_URL}/images/noCoupon.png`" class="image" />
      </view>
    </view>
  </view>
</template>
<script>
import { getCouponsUser } from '@/api/user'
import DataFormatT from '@/components/DataFormatT'

const NAME = 'UserCoupon'

export default {
  name: 'UserCoupon',
  components: {
    DataFormatT,
  },
  props: {},
  data: function() {
    return {
      couponsList: [],
      loading: false,
    }
  },
  watch: {
    $yroute: function(n) {
      var that = this
      if (n.name === NAME) {
        that.getUseCoupons()
      }
    },
  },
  mounted: function() {
    this.getUseCoupons()
  },
  methods: {
    getUseCoupons: function() {
      let that = this,
        type = 0
      getCouponsUser(type).then(res => {
        that.couponsList = res.data
        that.loading = true
      })
    },
  },
}
</script>
