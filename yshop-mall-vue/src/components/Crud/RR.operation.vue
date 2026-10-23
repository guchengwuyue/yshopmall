<!--搜索与重置-->
<template>
  <span>
    <el-button class="filter-item" size="small" type="success" :icon="Search" @click="crud.toQuery">搜索</el-button>
    <el-button class="filter-item" size="small" type="warning" :icon="RefreshLeft" @click="crud.resetQuery()">重置</el-button>
  </span>
</template>
<script>
export default {
  props: {
    crud: {
      type: Object,
      required: true
    },
    itemClass: {
      type: String,
      required: false,
      default: ''
    }
  }
}
</script>
