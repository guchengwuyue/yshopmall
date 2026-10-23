let seq = 0

/**
 * Stable key for vuedraggable@4 item-key when list items lack a unique id.
 */
export function dragItemKey(el) {
  if (el == null || typeof el !== 'object') {
    return String(el)
  }
  if (el.__dk == null) {
    Object.defineProperty(el, '__dk', {
      value: `dk-${++seq}`,
      enumerable: false,
      configurable: true
    })
  }
  return el.__dk
}
