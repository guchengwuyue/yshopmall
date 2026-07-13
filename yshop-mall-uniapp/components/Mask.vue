<template>
  <div
    :class="show?'yd-mask g-fix-ios-overflow-scrolling-bug':'yd-mask' "
    ref="scrollView"
    :style="styles"
  >
    <slot></slot>
  </div>
</template>

<script type="text/babel">
export default {
  name: "yd-mask",
  data() {
    return {
      show: this.value
    };
  },
  props: {
    value: {
      type: Boolean,
      default: false
    },
    bgcolor: {
      type: String,
      default: "#000"
    },
    zindex: {
      default: 1500
    },
    opacity: {
      default: 0.5
    },
    animated: {
      type: Boolean,
      default: true
    }
  },
  computed: {
    styles() {
      const style = {
        "z-index": this.zindex,
        "background-color": this.bgcolor
      };
      if (this.show) {
        style["opacity"] = this.opacity;
        style["pointer-events"] = "auto";
      }
      return style;
    }
  },
  mounted() {}
};
</script>

<style lang="less">
@css-prefix: yd;

.@{css-prefix} {
  &-mask {
    position: fixed;
    bottom: 0;
    right: 0;
    left: 0;
    top: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    pointer-events: none;
    transition: opacity 0.2s ease-in;
    opacity: 0;
  }
}
</style>
