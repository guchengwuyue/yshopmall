<template>
  <div class="app-container">
    <p class="warn-content">
      Markdown 基于
      <el-link type="primary" href="https://github.com/imzbf/md-editor-v3" target="_blank">md-editor-v3</el-link>
      ，图片上传使用 <el-link type="primary" href="https://sm.ms/" target="_blank">SM.MS</el-link>
    </p>
    <MdEditor
      v-model="text"
      :style="{ height }"
      @onUploadImg="onUploadImg"
    />
  </div>
</template>

<script>
import { MdEditor } from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
import { upload } from '@/utils/upload'
import { mapGetters } from 'vuex'

export default {
  name: 'Markdown',
  components: { MdEditor },
  data() {
    return {
      text: '',
      height: document.documentElement.clientHeight - 200 + 'px'
    }
  },
  computed: {
    ...mapGetters([
      'imagesUploadApi'
    ])
  },
  mounted() {
    const that = this
    window.onresize = function temp() {
      that.height = document.documentElement.clientHeight - 200 + 'px'
    }
  },
  methods: {
    onUploadImg(files, callback) {
      Promise.all(
        files.map(file =>
          upload(this.imagesUploadApi, file).then(data => {
            const url = (data && data.data && data.data.url) || (data && data.url) || ''
            return url
          })
        )
      ).then(urls => {
        callback(urls.filter(Boolean))
      })
    }
  }
}
</script>

<style scoped>
</style>
