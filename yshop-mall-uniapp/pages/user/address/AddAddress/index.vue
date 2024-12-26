<template>
  <view class="addAddress absolute">
    <view class="list">
      <view class="item acea-row row-between-wrapper">
        <view class="name">姓名</view>
        <input type="text" placeholder="请输入姓名" v-model="userAddress.realName" required />
      </view>
      <view class="item acea-row row-between-wrapper">
        <view class="name">联系电话</view>
        <input type="text" placeholder="请输入联系电话" v-model="userAddress.phone" required />
      </view>
      <view class="item acea-row row-between-wrapper">
        <view class="name">所在地区</view>
        <view class="picker acea-row row-between-wrapper select-value form-control">
          <view class="address">
            <CitySelect ref="cityselect" :defaultValue="defaultAddress" @callback="result" :items="district"></CitySelect>
          </view>
          <view class="iconfont icon-dizhi font-color-red"></view>
        </view>
      </view>
      <view class="item acea-row row-between-wrapper">
        <view class="name">详细地址</view>
        <input type="text" placeholder="请填写具体地址" v-model="userAddress.detail" required />
      </view>
    </view>
    <view class="default acea-row row-middle">
      <view class="select-btn">
        <view class="checkbox-wrapper">
          <checkbox-group @change="ChangeIsDefault">
            <label class="well-check">
              <checkbox :value="userAddress.isDefault == 1 ? 'checked' : ''" :checked="userAddress.isDefault ? true : false"></checkbox>
              <text class="def">设置为默认地址</text>
            </label>
          </checkbox-group>
        </view>
      </view>
    </view>
    <view></view>
    <view class="keepBnt bg-color-red" @tap="submit">立即保存</view>
    <view class="wechatAddress" v-if="isWechat && !id" @click="getAddress">导入微信地址</view>
  </view>
</template>

<script type="text/babel">
import CitySelect from '@/components/CitySelect'
import { getAddress, postAddress, getCity } from '@/api/user'
import attrs, { required, chs_phone } from '@/utils/validate'
import { validatorDefaultCatch } from '@/utils/dialog'
import { openAddress } from "@/libs/wechat";
import { isWeixin } from '@/utils'

export default {
  name: 'AddAddress',
  components: {
    CitySelect,
  },
  data() {
    return {
      district: [],
      id: 0,
      userAddress: { isDefault: 0 },
      address: {},
      isWechat: isWeixin(),
      defaultAddress: {},
      addressText: '',
    }
  },
  mounted: function() {
    let id = this.$yroute.query.id
    this.id = id
    this.getUserAddress()
    this.getCityList()
  },
  watch: {
    addressText(nextModel2) {
      console.log(nextModel2, 8585858585)
    },
  },
  methods: {
    getCityList: function() {
      let that = this
      getCity()
        .then(res => {
          that.district = res.data
          that.ready = true
        })
        .catch(err => {
          uni.showToast({
            title: err.msg,
            icon: 'none',
            duration: 2000,
          })
        })
    },
    getUserAddress: function() {
      if (!this.id) return false
      let that = this
      getAddress(that.id).then(res => {
        that.userAddress = res.data
        that.defaultAddress = {
          province: {
            n: res.data.province,
          },
          city: {
            n: res.data.city,
          },
          district: {
            n: res.data.district,
          },
        }
        that.addressText = res.data.province + ' ' + res.data.city + ' ' + res.data.district
        that.address.province = res.data.province
        that.address.city = res.data.city
        that.address.district = res.data.district
      })
    },
    getAddress() {
		console.log('aaaaaaaaaa')
		openAddress().then(userInfo => {
		  uni.showLoading({ title: "加载中" });
		  postAddress({
		    real_name: userInfo.userName,
		    phone: userInfo.telNumber,
		    address: {
		      province: userInfo.provinceName,
		      city: userInfo.cityName,
		      district: userInfo.countryName
		    },
		    detail: userInfo.detailInfo,
		    post_code: userInfo.postalCode,
		    wx_export: 1
		  })
		    .then(() => {
		      this.page = 1;
		      this.loading = false;
		      this.loadend = false;
		      this.addressList = [];
		      this.AddressList();
		      uni.hideLoading();
				uni.showToast({
					title: "添加成功",
					icon: 'success',
					duration: 2000
				});
			})
		    .catch(err => {
		      uni.hideLoading();
				uni.showToast({
					title: err.msg || err.response.data.msg|| err.response.data.message,
					icon: 'none',
					duration: 2000
				});
		    });
		});
	},
    async submit() {
      let name = this.userAddress.realName,
        phone = this.userAddress.phone,
        addressText = this.addressText,
        detail = this.userAddress.detail,
        isDefault = this.userAddress.isDefault
      try {
        await this.$validator({
          name: [required(required.message('姓名')), attrs.range([2, 16], attrs.range.message('姓名'))],
          phone: [required(required.message('联系电话')), chs_phone(chs_phone.message())],
          addressText: [required('请选择地址')],
          detail: [required(required.message('具体地址'))],
        }).validate({ name, phone, addressText, detail })
      } catch (e) {
        return validatorDefaultCatch(e)
      }
      try {
        let that = this,
          data = {
            id: that.id,
            real_name: name,
            phone: phone,
            address: this.address,
            detail: detail,
            is_default: isDefault ? true : false,
            post_code: '',
          }
        postAddress(data).then(function() {
          if (that.id) {
            uni.showToast({
              title: '修改成功',
              icon: 'none',
              duration: 2000,
            })
          } else {
            uni.showToast({
              title: '操作成功',
              icon: 'none',
              duration: 2000,
            })
            // uni.showToast({
            //   title: "已取消绑定",
            //   icon: "none",
            //   duration: 2000
            // });
            // that.$yrouter.replace({
            //   path: "/pages/user/PersonalData/index"
            // });
          }
          that.$yrouter.back()
        })
      } catch (err) {
        uni.showToast({
          title: err.msg || err.response.data.msg || err.response.data.message,
          icon: 'none',
          duration: 2000,
        })
      }
    },
    ChangeIsDefault: function() {
      this.userAddress.isDefault = !this.userAddress.isDefault
    },
    result(values) {
      console.log(this)
      console.log(values)
      this.address = {
        province: values.province.name || '',
        city: values.city.name || '',
        district: values.district.name || '',
        city_id: values.city.id,
      }
      this.addressText = `${this.address.province}${this.address.city}${this.address.district}`
      // this.addressText =
      //   this.address.province + this.address.city + this.address.district;
    },
  },
}
</script>

<style lang="less">
.address {
  text {
    width: 100%;
    display: block;
  }
}
</style>
