import hljs from 'highlight.js/lib/core'
import java from 'highlight.js/lib/languages/java'
import javascript from 'highlight.js/lib/languages/javascript'
import xml from 'highlight.js/lib/languages/xml'
import json from 'highlight.js/lib/languages/json'

hljs.registerLanguage('java', java)
hljs.registerLanguage('javascript', javascript)
hljs.registerLanguage('xml', xml)
hljs.registerLanguage('json', json)

function highlightEl(el, binding) {
  const targets = el.querySelectorAll('code')
  const codeNodes = targets.length ? targets : [el]
  codeNodes.forEach((block) => {
    if (binding.value != null) {
      block.textContent = binding.value
    }
    hljs.highlightElement(block)
  })
}

export default {
  mounted(el, binding) {
    highlightEl(el, binding)
  },
  updated(el, binding) {
    highlightEl(el, binding)
  }
}

export function setupHighlight(app) {
  app.directive('highlightjs', {
    mounted(el, binding) {
      highlightEl(el, binding)
    },
    updated(el, binding) {
      highlightEl(el, binding)
    }
  })
}
