<template>
  <text>{{time}}</text>
</template>
<script>
import { dateFormatT } from "@/utils";

export default {
  name: "DataFormatT",
  props: ["date"],
  data: function() {
    return {
      time: ""
    };
  },
  mounted() {
    this.time = dateFormatT(this.date);
  },
  watch: {
    "$props.date"(props) {
      this.time = dateFormatT(this.date);
    }
  }
};
</script>
