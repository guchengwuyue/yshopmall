<template>
  <view class="evaluateWtapper" v-if="reply&&reply.length>0">
    <view v-for="(item, evaluateWtapperIndex) in reply" :key="evaluateWtapperIndex">
      <view class="evaluateItem" v-if="item">
        <view class="pic-text acea-row row-middle">
          <view class="pictrue">
            <image :src="item.avatar" class="image" />
          </view>
          <view class="acea-row row-middle">
            <view class="name line1">{{ item.nickname }}</view>
            <view class="start" :class="'star' + item.star"></view>
          </view>
        </view>
        <view class="time">{{ item.createTime }} {{ item.sku||'' }}</view>
        <view class="evaluate-infor">{{ item.comment }}</view>
        <view class="imgList acea-row">
          <view class="pictrue" v-for="(itemn, eq) in item.picturesArr" :key="eq">
            <image :src="itemn" class="image" />
          </view>
        </view>
        <view class="reply" v-if="item.merchantReplyContent">
          <span class="font-color-red">yshop店员</span>
          ：{{item.merchantReplyContent}}
        </view>
      </view>
    </view>
  </view>
</template>
<script>
  import {
    dataFormat
  } from "@/utils";

  export default {
    name: "UserEvaluation",
    props: {
      reply: {
        type: Array,
        default: () => []
      }
    },
    data: function () {
      return {};
    },
    mounted: function () {
      console.log(this)
    },
    methods: {
      dataFormat
    }
  };
</script>
