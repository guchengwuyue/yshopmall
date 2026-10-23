<template>
  <!-- 站内/外链导航均由 el-menu @select 处理，此处只透传内容，避免 Vue3 下 router-link 包裹 li 点击无效 -->
  <slot />
</template>

<script>
export default {
  props: {
    to: {
      type: String,
      required: true
    }
  }
}
</script>
