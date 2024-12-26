<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="150px">
      <el-form-item label="移动端H5地址">
        <el-input v-model="form.site_url" style="width: 370px;" />
      </el-form-item>
      <el-form-item label="uniapp-H5地址">
        <el-input v-model="form.uni_site_url" style="width: 370px;" />
        <span style="color: red">主要用于兼容单独h5</span>
      </el-form-item>
      <el-form-item label="移动端API地址">
        <el-input v-model="form.api_url" style="width: 370px;" />
      </el-form-item>
      <el-form-item label="后台API地址">
        <el-input v-model="form.admin_api_url" style="width: 370px;" />
      </el-form-item>
      <el-form-item label="文件存储方式">
        <el-radio v-model="form.file_store_mode" :label="1">本地存储</el-radio>
        <el-radio v-model="form.file_store_mode" :label="2">云存储</el-radio>
      </el-form-item>
      <el-form-item label="包邮金额">
        <el-input v-model="form.store_free_postage" style="width: 370px;" />
        <p style="color: red">如果设置满包邮0 表示全局包邮，如果设置大于0表示满这价格包邮，否则走运费模板算法</p>
      </el-form-item>
      <el-form-item label="隐藏充值按钮">
        <el-radio v-model="form.yshop_show_recharge" :label="0">显示</el-radio>
        <el-radio v-model="form.yshop_show_recharge" :label="1">隐藏</el-radio>
      </el-form-item>
      <el-form-item label="微信发货信息管理">
        <div class="detail-centent">
          根据《商家自营类小程序运营规范》,特定类型的小程序需要在平台完成发货信息录入及确认收货流程后方可进行资金结算。符合以下情形的小程序，需接入微信开放平台订单发货管理功能：<br />
          1. 小程序内提供珠宝玉石、3C数码、盲盒等商品在线销售及配送服务；<br />

          2.小程序的账号主体为近一年内新成立的企业或个体户主体；<br />

          3. 小程序的账号管理员、运营者等角色，与其它高风险小程序存在关联；<br />

          4. 小程序内经营预售商品。<br />
          <br />
          在小程序mp后台菜单出现一个发货信息管理菜单<el-link :underline="false" type="primary"
        >（图例）<el-image
          class="images"
          :src="require('@/assets/images/shipping.png')"
          :preview-src-list="[require('@/assets/images/shipping.png')]"
        ></el-image></el-link
        >，<span class="textE93323"
        >只有该类小程序需要开启发货配置开关；如果没有这个菜单，则无需开启，以免出错；</span
        >之前已产生的订单不会去主动触发，需要微信开放平台手动发货才可提现。<br />
          快递物流：资金将于订单发货后的第7天，系统自动确认收货后结算；<br />
          <div class="acea-row">
            详情见<a
            target="_blank"
            style="color: blue; cursor: pointer; text-decoration: underline;"
            href="https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/order-shipping/order-shipping.html#一、发货信息录入接口"
          >《小程序发货信息管理服务》</a
          >
            <a
              target="_blank"
              style="color: blue; cursor: pointer; text-decoration: underline;"
              href="https://developers.weixin.qq.com/miniprogram/product/jiaoyilei/yunyingguifan.html#_1-2-商品规范"
            >《交易类小程序运营规范》</a
            >
          </div>
        </div>
        <el-radio v-model="form.yshop_wechat_routine_shipping" :label="1">开启</el-radio>
        <el-radio v-model="form.yshop_wechat_routine_shipping" :label="0">关闭</el-radio>
      </el-form-item>
      <el-form-item label="">
        <el-button type="primary" @click="doSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import checkPermission from '@/utils/permission'
import initData from '@/mixins/crud'
import { del, add, get } from '@/api/yxSystemConfig'
import eForm from './form'
import picUpload from '@/components/pic-upload'
import { Message } from 'element-ui'
export default {
  components: { eForm, picUpload },
  mixins: [initData],
  data() {
    return {
      delLoading: false,
      form: {
        yshop_show_recharge: 1,
        file_store_mode: 2,
        site_url: '',
        api_url: '',
        uni_site_url: '',
        admin_api_url: '',
        store_free_postage: '',
        yshop_wechat_routine_shipping: 0
      },
      rules: {
      }
    }
  },
  created() {
    get().then(rese => {
      const that = this;
      rese.content.map(function(key, value) {
        const keyName = key.menuName
        const newValue = key.value
        if(keyName in that.form){
          that.form[keyName] = newValue
        }
      })

      this.form.file_store_mode = parseInt(this.form.file_store_mode)
      this.form.yshop_show_recharge = parseInt(this.form.yshop_show_recharge)
      this.form.yshop_wechat_routine_shipping = parseInt(this.form.yshop_wechat_routine_shipping)
    })
  },
  methods: {
    checkPermission,
    doSubmit() {
      add(this.form).then(res => {
        Message({ message: '设置成功', type: 'success' })
      }).catch(err => {
        // this.loading = false
        console.log(err.response.data.message)
      })
    }

  }
}
</script>

<style scoped lang="scss">
.images {
  opacity: 0;
  position: absolute;
  width: 54px;
  height: 27px;
  left: 3px;
}
.detail-centent {
  line-height: 30px;
  font-size: 14px;
}
a {
  color: var(--prev-color-primary);
}
.textE93323 {
  color: #e93323 !important;
}
</style>
