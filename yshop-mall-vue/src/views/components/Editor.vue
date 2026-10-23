<template>
  <div class="wang-editor-wrap" style="border: 1px solid #ccc; margin: 5px; width: 700px;">
    <Toolbar
      :editor="editor"
      :default-config="toolbarConfig"
      mode="default"
      style="border-bottom: 1px solid #ccc"
    />
    <Editor
      v-model="html"
      :default-config="editorConfig"
      mode="default"
      style="height: 400px; overflow-y: hidden; text-align: left;"
      @onCreated="onCreated"
      @onChange="onChange"
    />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { getToken } from '@/utils/auth'
import '@wangeditor/editor/dist/css/style.css'

export default {
  name: 'WangEditor',
  components: { Editor, Toolbar },
  props: {
    value: {
      type: String,
      default: ''
    },
    modelValue: {
      type: String,
      default: undefined
    }
  },
  emits: ['update:modelValue', 'input', 'change'],
  data() {
    return {
      editor: null,
      html: '',
      toolbarConfig: {},
      syncing: false
    }
  },
  computed: {
    ...mapGetters([
      'uploadApi'
    ]),
    content() {
      return this.modelValue !== undefined ? this.modelValue : this.value
    },
    editorConfig() {
      const headers = { Authorization: getToken() }
      const uploadApi = this.uploadApi
      return {
        placeholder: '请输入内容...',
        MENU_CONF: {
          uploadImage: {
            server: uploadApi,
            fieldName: 'file',
            headers,
            customInsert(res, insertFn) {
              const url = res && (res.link || res.url || (res.data && res.data.url))
              if (url) insertFn(url, '', url)
            }
          }
        }
      }
    }
  },
  watch: {
    content: {
      immediate: true,
      handler(val) {
        const next = val || ''
        if (next === this.html) return
        this.syncing = true
        this.html = next
        this.$nextTick(() => {
          this.syncing = false
        })
      }
    }
  },
  beforeUnmount() {
    const editor = this.editor
    if (editor == null) return
    editor.destroy()
    this.editor = null
  },
  methods: {
    onCreated(editor) {
      this.editor = Object.seal(editor)
    },
    onChange(editor) {
      if (this.syncing) return
      const html = editor.getHtml()
      this.html = html
      this.$emit('update:modelValue', html)
      this.$emit('input', html)
      this.$emit('change', html)
    }
  }
}
</script>
