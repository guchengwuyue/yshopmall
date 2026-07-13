<template>
  <text>{{time}}</text>
</template>
<script>
import { dataFormat } from "@/utils";

export default {
  name: "DataFormat",
  props: ["date"],
  data: function() {
    return {
      time: ""
    };
  },
  mounted() {
    this.time = dataFormat(this.date);
  },
  watch: {
    "$props.date"(props) {
      this.time = dataFormat(this.date);
    }
  }
};
</script>
