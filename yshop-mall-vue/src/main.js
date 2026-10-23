import { createApp } from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css'

import ElementPlus, { ElMessage, ElMessageBox, ElNotification } from 'element-plus'
import zhCn from 'element-plus/lib/locale/lang/zh-cn'
import 'element-plus/dist/index.css'

// 数据字典
import dict from './components/Dict'

// 权限指令
import permission from './components/Permission'
// 初始化 css（含 Element Plus 主题变量）
import './assets/styles/element-variables.scss'
// global css
import './assets/styles/index.scss'

import 'highlight.js/styles/atom-one-dark.css'

import '@/assets/font_icon/iconfont.css'

import App from './App'
import store from './store'
import router from './router/routers'
import registerIcons from './assets/icons'
import { registerElementIcons } from './utils/element-icons'
import { setupHighlight } from './directive/highlight'
import VueUeditorWrap from 'vue-ueditor-wrap'

import './router/index' // permission control

const app = createApp(App)

registerIcons(app)
registerElementIcons(app)
setupHighlight(app)

app.use(store)
app.use(router)
app.use(permission)
app.use(dict)
app.use(VueUeditorWrap)
const epSize = (() => {
  const s = Cookies.get('size') || 'small'
  if (s === 'mini' || s === 'medium') return 'small'
  return s
})()

app.use(ElementPlus, {
  locale: zhCn,
  size: epSize
})

// Element UI → Plus：兼容页面里大量 this.$message / $notify / $confirm
app.config.globalProperties.$message = ElMessage
app.config.globalProperties.$notify = ElNotification
app.config.globalProperties.$msgbox = ElMessageBox
app.config.globalProperties.$alert = ElMessageBox.alert
app.config.globalProperties.$confirm = ElMessageBox.confirm
app.config.globalProperties.$prompt = ElMessageBox.prompt

app.mount('#app')
