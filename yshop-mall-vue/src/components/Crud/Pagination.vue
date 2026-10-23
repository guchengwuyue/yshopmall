<!--分页-->
<template>
  <el-pagination
    v-model:page-size="page.size"
    v-model:current-page="page.page"
    :total="page.total"
    style="margin-top: 8px;"
    layout="total, prev, pager, next, sizes"
    @size-change="crud.sizeChangeHandler($event)"
    @current-change="crud.pageChangeHandler"
  />
</template>
<script>
import { pagination } from '@crud/crud'
export default {
  mixins: [pagination()]
}
</script>
