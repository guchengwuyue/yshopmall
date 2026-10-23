/**
 * Element UI `el-icon-*` → Element Plus icons mapping.
 * Icons are registered as global components and on globalProperties
 * so templates can use `:icon="Search"` / `<Search />`.
 */
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

/** Explicit overrides where Element Plus renamed or dropped Element UI icons */
const ICON_ALIAS = {
  'el-icon-s-finance': 'Money',
  'el-icon-s-grid': 'Grid',
  'el-icon-s-operation': 'Operation',
  'el-icon-s-order': 'List',
  'el-icon-s-promotion': 'Promotion',
  'el-icon-s-ticket': 'Ticket',
  'el-icon-s-tools': 'Tools',
  'el-icon-notebook-1': 'Notebook',
  'el-icon-shopping-bag-1': 'ShoppingBag',
  'el-icon-picture-outline': 'Picture',
  'el-icon-success': 'SuccessFilled',
  'el-icon-error': 'CircleCloseFilled',
  'el-icon-info': 'InfoFilled',
  'el-icon-warning': 'WarningFilled',
  'el-icon-bank-card': 'CreditCard',
  'el-icon-truck': 'Van',
  'el-icon-goods': 'Goods',
  'el-icon-finished': 'Finished',
  'el-icon-refrigerator': 'Refrigerator',
  'el-icon-tickets': 'Tickets',
  'el-icon-printer': 'Printer',
  'el-icon-document': 'Document',
  'el-icon-view': 'View',
  'el-icon-back': 'Back',
  'el-icon-right': 'Right',
  'el-icon-loading': 'Loading',
  'el-icon-circle-check': 'CircleCheck',
  'el-icon-circle-close': 'CircleClose',
  'el-icon-caret-bottom': 'CaretBottom',
  'el-icon-caret-top': 'CaretTop',
  'el-icon-arrow-down': 'ArrowDown',
  'el-icon-arrow-up': 'ArrowUp',
  'el-icon-arrow-left': 'ArrowLeft',
  'el-icon-arrow-right': 'ArrowRight',
  'el-icon-search': 'Search',
  'el-icon-edit': 'Edit',
  'el-icon-delete': 'Delete',
  'el-icon-plus': 'Plus',
  'el-icon-minus': 'Minus',
  'el-icon-close': 'Close',
  'el-icon-check': 'Check',
  'el-icon-refresh': 'Refresh',
  'el-icon-refresh-left': 'RefreshLeft',
  'el-icon-download': 'Download',
  'el-icon-upload': 'Upload',
  'el-icon-user': 'User',
  'el-icon-postcard': 'Postcard',
  'el-icon-office-building': 'OfficeBuilding'
}

function kebabToPascal(name) {
  return name
    .replace(/^el-icon-/, '')
    .split('-')
    .filter(Boolean)
    .map(s => s.charAt(0).toUpperCase() + s.slice(1))
    .join('')
}

/**
 * Resolve Element UI class / icon prop to an Element Plus icon component.
 * @param {string} name e.g. 'el-icon-search' or 'Search'
 */
export function resolveElIcon(name) {
  if (!name) return null
  if (ElementPlusIconsVue[name]) return ElementPlusIconsVue[name]
  const aliased = ICON_ALIAS[name]
  if (aliased && ElementPlusIconsVue[aliased]) return ElementPlusIconsVue[aliased]
  const pascal = kebabToPascal(name)
  return ElementPlusIconsVue[pascal] || null
}

/** Map el-icon-* string → PascalCase component name used in templates */
export function elIconToComponentName(name) {
  if (!name) return null
  if (ICON_ALIAS[name]) return ICON_ALIAS[name]
  if (ElementPlusIconsVue[name]) return name
  const pascal = kebabToPascal(name)
  return ElementPlusIconsVue[pascal] ? pascal : null
}

export function registerElementIcons(app) {
  for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
    // Expose on instance so Options API templates can use `:icon="Search"`
    app.config.globalProperties[key] = component
  }
  app.config.globalProperties.$elIcon = resolveElIcon
}

export { ElementPlusIconsVue, ICON_ALIAS }
