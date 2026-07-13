<template>
  <map
    id="map"
    :longitude="map.longitude"
    :latitude="map.latitude"
    :markers="mapConfig"
    scale="15"
    show-location
    style="width: 100%; height: 100%;"
  ></map>
</template>
<script>
export default {
  name: "Index",
  components: {},
  props: {},
  data: function() {
    return {
      mapConfig: {
        latitude: "",
        longitude: "",
        name: "",
        address: ""
      },
      map: {
        latitude: "",
        longitude: ""
      }
    };
  },
  onShow: function() {
    this.map = {
      latitude: this.$yroute.query.latitude,
      longitude: this.$yroute.query.longitude
    };
    this.mapConfig = [{
      id: 1,
      width: 50,
      height: 50,
      iconPath: this.$yroute.query.image,
      latitude: this.$yroute.query.latitude,
      longitude: this.$yroute.query.longitude,
      title: this.$yroute.query.name,
      name: this.$yroute.query.name,
      address: this.$yroute.query.detailedAddress
    }];
    console.log(this.mapConfig);
  },
  methods: {}
};
</script>
<style  lang="less">
page {
  width: 100%;
  height: 100%;
}
</style>
