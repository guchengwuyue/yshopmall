<script>
import { h } from 'vue'

export default {
  created() {
    const { params, query } = this.$route
    const path = params.path
    const target = Array.isArray(path) ? path.join('/') : (path || '')
    this.$router.replace({ path: '/' + target, query })
  },
  render() {
    return h() // avoid warning message
  }
}
</script>
