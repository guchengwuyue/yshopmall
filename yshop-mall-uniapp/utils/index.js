/**
 * utils 入口：按职责拆分到 format / router / auth，此处统一 re-export，
 * 现有 `import { xxx } from '@/utils'` 无需改动。
 */
export * from '@/utils/format'
export * from '@/utils/router'
export * from '@/utils/auth'
