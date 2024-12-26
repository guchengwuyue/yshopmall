<template>
  <view>
    <text class="uni-input" @tap="open">{{ value }}</text>
    <uni-popup ref="popup" type="bottom">
      <view class="cityselect">
        <view class="cityselect-header">
          <view class="cityselect-title">
            <text>请选择地址</text>
          </view>
          <view class="cityselect-nav">
            <view class="item" v-if="provinceActive" @tap="changeNav(0)">
              <text>{{ provinceActive.n }}</text>
            </view>
            <view class="item" v-if="cityActive" @tap="changeNav(1)">
              <text>{{ cityActive.n }}</text>
            </view>
            <view class="item" v-if="districtActive" @tap="changeNav(2)">
              <text>{{ districtActive.n }}</text>
            </view>
            <view class="item active" v-else>
              <text>请选择</text>
            </view>
          </view>
        </view>
        <view class="cityselect-content">
          <swiper class="swiper" disable-touch="true" touchable="false" :current="current">
            <swiper-item>
              <scroll-view scroll-y class="cityScroll">
                <view>
                  <view class="cityselect-item" v-for="(item, index) in province" :key="index" @tap="selectProvince(index)">
                    <view class="cityselect-item-box" :class="{ active: isProvinceActive(item) }">
                      <text>{{ item.n }}</text>
                    </view>
                  </view>
                </view>
              </scroll-view>
            </swiper-item>
            <swiper-item>
              <scroll-view scroll-y class="cityScroll">
                <view>
                  <view class="cityselect-item" v-for="(item, index) in city" :key="index" @tap="selectCity(index)">
                    <view class="cityselect-item-box" :class="{ active: isCityActive(item)}">
                      <text>{{ item.n }}</text>
                    </view>
                  </view>
                </view>
              </scroll-view>
            </swiper-item>
            <swiper-item>
              <scroll-view scroll-y class="cityScroll">
                <view>
                  <view class="cityselect-item" v-for="(item, index) in district" :key="index" @tap="selectDistrict(index)">
                    <view class="cityselect-item-box" :class="{ active: isDistrictActive(item) }">
                      <text>{{ item.n }}</text>
                    </view>
                  </view>
                </view>
              </scroll-view>
            </swiper-item>
          </swiper>
        </view>
      </view>
    </uni-popup>
  </view>
</template>

<script type="text/babel">
import uniPopup from './uni-popup/uni-popup.vue'
import uniPopupMessage from './uni-popup/uni-popup-message.vue'
import uniPopupDialog from './uni-popup/uni-popup-dialog.vue'

export default {
  name: 'CitySelect',
  components: {
    uniPopup,
    uniPopupMessage,
    uniPopupDialog,
  },
  props: ['callback', 'items', 'defaultValue'],
  data() {
    return {
      value: '请选择',
      show: this.value,
      province: [],
      provinceActive: null,
      city: [],
      cityActive: null,
      district: [],
      districtActive: null,
      current: 0,
    }
  },
  watch: {
    items(next) {
      this.province = next
    },
    defaultValue(next) {
      this.value = `${next.province.n} ${next.city.n} ${next.district.n}`
      this.setDefaultValue(this.items, next)
    },
  },
  
  mounted() {
    console.log(this)
    if (this.value) {
      this.value = this.value
    }
    this.province = this.items
  },
  methods: {
     isProvinceActive(item) {
      return this.provinceActive && item.v == this.provinceActive.v
    },
    isCityActive(item) {
      return this.cityActive && item.v == this.cityActive.v
    },
    isDistrictActive(item) {
      return this.districtActive && item.v == this.districtActive.v
    },
    setDefaultValue(items, value) {
      if (!items || !value) {
        return
      }
      this.province = items
      items.map(province => {
        if (province.n == value.province.n) {
          this.city = province.c
          this.provinceActive = {
            v: province.v,
            n: value.province.n,
          }
          province.c.map(city => {
            if (city.n == value.city.n) {
              this.district = city.c
              this.cityActive = {
                v: city.v,
                n: value.city.n,
              }
              city.c.map(district => {
                if (district.n == value.district.n) {
                  this.districtActive = {
                    v: city.v,
                    n: value.district.n,
                  }
                }
              })
            }
          })
        }
      })
      console.log(this.provinceActive, this.cityActive, this.districtActive)
      console.log(this)
    },
    open() {
      this.province = this.items
      this.provinceActive = null
      this.cityActive = null
      this.districtActive = null
      this.city = []
      this.district = []
      this.current = 0
      this.$refs.popup.open()

      this.setDefaultValue(this.items, this.defaultValue)
    },
    changeNav(index) {
      if (index == 0) {
        this.provinceActive = null
      }
      if (index == 1) {
        this.cityActive = null
        this.districtActive = null
      }
      if (index == 2) {
        this.districtActive = null
      }
      this.current = index
    },
    selectProvince(index) {
      this.provinceActive = this.province[index]
      this.city = this.province[index].c
      this.current = 1
    },
    selectCity(index) {
      this.cityActive = this.city[index]
      this.district = this.city[index].c
      this.current = 2
    },
    selectDistrict(index) {
      this.districtActive = this.district[index]
      this.value = `${this.provinceActive.n} ${this.cityActive.n} ${this.districtActive.n}`
      // this.callback({
      //   province: {
      //     id: this.provinceActive.v,
      //     name: this.provinceActive.n
      //   },
      //   city: {
      //     id: this.cityActive.v,
      //     name: this.cityActive.n
      //   },
      //   district: {
      //     id: this.districtActive.v,
      //     name: this.districtActive.n
      //   }
      // });
      this.$emit('callback', {
        province: {
          id: this.provinceActive.v,
          name: this.provinceActive.n,
        },
        city: {
          id: this.cityActive.v,
          name: this.cityActive.n,
        },
        district: {
          id: this.districtActive.v,
          name: this.districtActive.n,
        },
      })
      this.$refs.popup.close()
    },
  },
}
</script>

<style lang="less">
.cityselect {
  width: 100%;
  height: 75%;
  background-color: #fff;
  z-index: 1502;
  position: relative;
  padding-bottom: 0;
  padding-bottom: constant(safe-area-inset-bottom);
  padding-bottom: env(safe-area-inset-bottom);
  .cityScroll {
    height: 100%;
  }
  .swiper {
    height: 800rpx;
  }
}
.cityselect-header {
  width: 100%;
  z-index: 1;
}
.cityselect-title {
  width: 100%;
  font-size: 30rpx;
  text-align: center;
  height: 95rpx;
  line-height: 95rpx;
  position: relative;
  &:cityselect-title:after {
    height: 1px;
    position: absolute;
    z-index: 0;
    bottom: 0;
    left: 0;
    content: '';
    width: 100%;
    background-image: linear-gradient(0deg, #ececec 50%, transparent 0);
  }
}
.cityselect-nav {
  width: 100%;
  padding-left: 20rpx;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  .item {
    font-size: 26rpx;
    color: #222;
    display: block;
    height: 80rpx;
    line-height: 92rpx;
    padding: 0 16rpx;
    position: relative;
    margin-right: 30rpx;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: 40%;
    &.active {
      color: #f23030 !important;
      border-bottom: 1rpx solid #f23030;
    }
  }
}
.cityselect-content {
  height: 100%;
  width: 100%;
}
.cityselect-item {
  .cityselect-item-box {
    display: block;
    padding: 0 40rpx;
    position: relative;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-break: break-all;
    text-overflow: ellipsis;
    line-height: 64rpx;
    max-height: 65rpx;
    font-size: 26rpx;
    color: #333;
    &.active{
      color:#f23030 !important;
    }
    &:after {
      content: '';
      height: 1rpx;
      position: absolute;
      z-index: 0;
      bottom: 0;
      left: 0;
      width: 100%;
      background-image: linear-gradient(0deg, #ececec 50%, transparent 0);
    }
  }
}
</style>
