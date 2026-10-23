import { constantRouterMap } from '@/router/routers'
import Layout from '@/layout/index'

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    GenerateRoutes({ commit }, asyncRouter) {
      commit('SET_ROUTERS', asyncRouter)
    }
  }
}

export const filterAsyncRouter = (routers) => { // 遍历后台传来的路由字符串，转换为组件对象
  return routers.filter(router => {
    if (router.component) {
      if (router.component === 'Layout') { // Layout组件特殊处理
        router.component = Layout
      } else {
        const component = router.component
        router.component = loadView(component)
      }
    }
    if (router.children && router.children.length) {
      router.children = filterAsyncRouter(router.children)
    }
    return true
  })
}

// webpack 5 动态 import 需带 .vue，且用相对路径建立 context，否则会 webpackEmptyContext
const viewModules = require.context('../../views', true, /\.vue$/)

export const loadView = (view) => {
  return () => {
    const path = `./${view}.vue`
    try {
      return Promise.resolve(viewModules(path))
    } catch (e) {
      console.error(`[permission] 找不到页面组件: views/${view}.vue`, e)
      return import('@/views/features/404')
    }
  }
}

export default permission
