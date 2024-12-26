<template>
  <view class="statistical-page" ref="container">
    <view class="navs">
      <view class="list">
        <view class="item" :class="time == 'today' ? 'on' : ''" @click="setTime('today')">今天</view>
        <view class="item" :class="time == 'yesterday' ? 'on' : ''" @click="setTime('yesterday')">昨天</view>
        <view class="item" :class="time == 'seven' ? 'on' : ''" @click="setTime('seven')">最近7天</view>
        <view class="item" :class="time == 'month' ? 'on' : ''" @click="setTime('month')">本月</view>
      </view>
    </view>
    <view class="wrapper">
      <view class="title">{{ title }}{{ this.where.type == 1 ? "营业额（元）" : "订单量（份）" }}</view>
      <view class="money">{{ time_price }}</view>
    </view>
    <!-- <view class="chart">
      <view class="company">{{ where.type === 1 ? "单位（元）" : "单位（份）" }}</view>
      <ECharts :options="polar"></ECharts>
    </view>-->
    <view class="public-wrapper">
      <view class="title">
        <text class="iconfont icon-xiangxishuju"></text>详细数据
      </view>
      <view class="nav acea-row row-between-wrapper">
        <view class="data">日期</view>
        <view class="browse">订单量</view>
        <view class="turnover">成交额</view>
      </view>
      <view class="conter">
        <view
          class="item acea-row row-between-wrapper"
          v-for="(item, statisticalIndex) in list"
          :key="statisticalIndex"
        >
          <view class="data">{{ item.time }}</view>
          <view class="browse">{{ item.count }}</view>
          <view class="turnover">{{ item.price }}</view>
        </view>
      </view>
    </view>
    <!-- <view class="calendar-wrapper" :class="current === true ? 'on' : ''">
      <view class="calendar">
        <Calendar
          :clean="clean"
          :lunar="lunar"
          ref="calendar"
          :range="isrange"
          :multi="ismulti"
          @select="select"
          @next="next"
          @prev="prev"
          :value="value"
          :weekSwitch="weekSwitch"
          :monthRange="monthRange"
          rangeMonthFormat="yyyy年MM月"
          monFirst
          responsive
          :begin="[1992, 5, 20]"
          :end="[2049, 5, 20]"
        />
      </view>
    </view>-->
    <view class="mask" @touchmove.prevent v-show="current === true" @click="close"></view>
    <Loading :loaded="loaded" :loading="loading"></Loading>
  </view>
</template>
<script>
// import ECharts from "vue-echarts";
// import "echarts/lib/chart/line";
// import "echarts/lib/component/polar";
// import Calendar from "mpvue-calendar";
// import "mpvue-calendar/src/browser-style.css";
import { getStatisticsMonth, getStatisticsTime } from "@/api/admin";
import Loading from "@/components/Loading";
const year = new Date().getFullYear();
const month = new Date().getMonth() + 1;
const day = new Date().getDate();
export default {
  name: "Statistics",
  components: {
    // ECharts,
    // Calendar,
    Loading
  },
  props: {},
  data: function() {
    return {
      polar: {
        tooltip: {
          trigger: "axis"
        },
        legend: {
          data: [""]
        },
        toolbox: {
          show: false,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line"] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
            splitLine: {
              show: false
            },
            axisLine: {
              lineStyle: {
                color: "#999",
                width: 1 //这里是为了突出显示加上的
              }
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            splitLine: {
              show: true,
              lineStyle: {
                color: ["#f5f5f5"],
                width: 1,
                type: "solid"
              }
            },
            axisLine: {
              lineStyle: {
                color: "#999",
                width: 1 //这里是为了突出显示加上的
              }
            }
          }
        ],
        series: [
          {
            name: "邮件营销",
            type: "line",
            stack: "总量",
            itemStyle: {
              normal: {
                color: "#2291f8", //折点颜色
                lineStyle: {
                  color: "#2291f8" //折线颜色
                }
              }
            },
            data: [120, 132.5, 101, 134, 90, 150, 30]
          }
        ],
        grid: {
          x: 30,
          x2: 10,
          y: 20,
          y2: 110
        },
        animationDuration: 2000
      },
      value: [
        [year, month, day - 1],
        [year, month, day]
      ],
      isrange: true,
      weekSwitch: false,
      ismulti: false,
      monFirst: true,
      clean: false, //简洁模式
      lunar: false, //显示农历
      renderValues: [],
      monthRange: [],
      current: false,
      where: {
        start: "",
        stop: "",
        type: "",
        cate: 1
      },
      types: "", //类型|order=订单数|price=营业额
      time: "", //时间|today=今天|yesterday=昨天|month=本月
      title: "", //时间|today=今天|yesterday=昨天|month=本月
      growth_rate: "", //增长率
      increase_time: "", //增长率
      increase_time_status: "", //增长率
      time_price: "", //增长率
      loaded: false,
      loading: false,
      filter: {
        page: 1,
        limit: 10
      },
      list: []
    };
  },
  watch: {
    "$yroute.params": function(newVal) {
      var that = this;
      if (newVal != undefined) {
        that.setType(newVal.type);
        that.setTime(newVal.time);
        that.getIndex();
      }
    }
  },
  mounted: function() {
    this.handelRenderValues();
    this.setTime(this.$yroute.query.time);
    this.setType(this.$yroute.query.type);
    // this.getIndex();
    this.getInfo();
  },
  onReachBottom() {
    !this.loading && this.getInfo();
  },
  computed: {
    monthRangeText() {
      return this.monthRange.length ? "固定" : "指定范围";
    }
  },
  methods: {
    getIndex: function() {
      var that = this;
      getStatisticsTime(that.where).then(
        res => {
          var _info = res.data.chart,
            day = [],
            num = [];
          _info.forEach(function(item) {
            day.push(item.time);
            num.push(item.num);
          });
          that.polar.xAxis[0].data = day;
          that.polar.series[0].data = num;
          that.growth_rate = res.data.growth_rate;
          that.increase_time = res.data.increase_time;
          that.increase_time_status = res.data.increase_time_status;
          that.time_price = res.data.time;
        },
        error => {
          uni.showToast({
            title: error.msg,
            icon: "none",
            duration: 2000
          });
        }
      );
    },
    setTime: function(time) {
      this.time = time;
      var year = new Date().getFullYear(),
        month = new Date().getMonth() + 1,
        day = new Date().getDate();
      switch (time) {
        case "today":
          this.where.cate = 1;
          this.title = "今日";
          this.getIndex();
          break;
        case "yesterday":
          this.where.cate = 2;
          this.title = "昨日";
          this.getIndex();
          break;
        case "month":
          this.where.cate = 4;
          this.title = "本月";
          this.getIndex();
          break;
        case "seven":
          this.where.cate = 3;
          this.title = "七日";
          this.getIndex();
          break;
      }
    },
    setType: function(type) {
      switch (type) {
        case "price":
          this.where.type = 1;
          break;
        case "order":
          this.where.type = 2;
          break;
      }
    },
    clickSomeThing(data) {
      this.value = [
        [2019, 4, 1],
        [2019, 4, 8]
      ];
    },
    setMonthRange() {
      this.monthRange = this.monthRange.length ? [] : ["2019-4", "2020-1"];
    },
    switchMode() {
      this.weekSwitch = !this.weekSwitch;
      setTimeout(() => {
        // this.$refs.calendar.renderer(2019, 1); //渲染2018年8月份
      }, 0);
    },
    handelRenderValues(data) {
      if (this.ismulti) {
        this.renderValues = this.value.map(v => v.join("-"));
      } else if (this.isrange) {
        const values = [];
        data || this.value;
        this.value.forEach((v, i) => {
          values.push(v.join("-"));
          if (!i) {
            values.push("~");
          }
        });
        this.renderValues = values;
      } else {
        this.renderValues = [this.value.join("-")];
      }
    },
    multiMode() {
      this.ismulti = true;
      this.isrange = false;
      this.value = [
        [year, month, 16],
        [year, month, 18]
      ];
      this.handelRenderValues();
      // this.$refs.calendar.renderer(year, month);
    },
    rangeMode() {
      this.ismulti = false;
      this.isrange = true;
      this.value = [
        [year, month, 16],
        [year, month, 22]
      ];
      this.handelRenderValues();
      // this.$refs.calendar.renderer(year, month);
    },
    valueMode() {
      this.ismulti = false;
      this.isrange = false;
      this.value = [year, month, 16];
      this.handelRenderValues();
      // this.$refs.calendar.renderer(year, month);
    },
    selectMonth(month, year) {},
    prev(y, m, w) {},
    next(year, month, week) {},
    selectYear(year) {},
    setToday() {
      // this.$refs.calendar.setToday();
    },
    dateInfo() {
      // const info = this.$refs.calendar.dateInfo(2018, 8, 23);
    },
    renderer() {
      if (this.monthRange.length) {
        this.monthRange = ["2018-8", "2018-8"];
      }
      // this.$refs.calendar.renderer(2018, 8); //渲染2018年8月份
    },
    select(val, val2) {
      if (this.isrange) {
        this.handelRenderValues([val, val2]);
      } else if (this.ismulti) {
        this.handelRenderValues(val);
      } else {
        this.handelRenderValues([val]);
      }
      if (
        this.where.start != new Date(val) / 1000 ||
        this.where.stop != new Date(val2) / 1000 + 24 * 60 * 60 - 1
      ) {
        this.time = "date";
        this.title = "";
        this.where.start =
          new Date(Date.parse(val[0] + "/" + val[1] + "/" + val[2])).getTime() /
          1000;
        this.where.stop =
          new Date(
            Date.parse(val2[0] + "/" + val2[1] + "/" + val2[2])
          ).getTime() /
            1000 +
          24 * 60 * 60 -
          1;
        this.getIndex();
      }
    },
    dateTitle: function() {
      this.current = true;
    },
    close: function() {
      this.current = false;
    },
    getInfo: function() {
      var that = this;
      if (that.loading || that.loaded) return;
      that.loading = true;
      getStatisticsMonth(that.filter).then(
        res => {
          that.loading = false;
          that.loaded = res.data.length < that.filter.limit;
          // that.list.push.apply(that.list, res.data);
          that.list = res.data;
          that.filter.page = that.filter.page + 1;
        },
        err => {
          uni.showToast({
            title:
              err.msg || err.response.data.msg || err.response.data.message,
            icon: "none",
            duration: 2000
          });
        }
      );
    }
  }
};
</script>
<style scoped lang="less">
.echarts {
  width: 100%;
  height: 5.5 * 100rpx;
}
.calendar-wrapper {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  z-index: 66;
  transform: translate3d(0, 100%, 0);
  transition: all 0.3s cubic-bezier(0.25, 0.5, 0.5, 0.9);
}
.calendar-wrapper.on {
  transform: translate3d(0, 0, 0);
}
.statistical-page .wrapper .increase {
  font-size: 0.26 * 100rpx;
}
.statistical-page .wrapper .increase .iconfont {
  margin-left: 0;
}
</style>
