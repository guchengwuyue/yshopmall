<template>
  <view class="time">
    {{ tipText }}
    <text class="styleAll" v-if="isDay === true">{{ day }}</text>
    <text class="timeTxt">{{ dayText }}</text>
    <text class="styleAll">{{ hour }}</text>
    <text class="timeTxt">{{ hourText }}</text>
    <text class="styleAll">{{ minute }}</text>
    <text class="timeTxt">{{ minuteText }}</text>
    <text class="styleAll">{{ second }}</text>
    <text class="timeTxt">{{ secondText }}</text>
  </view>
</template>
<script>
export default {
  name: 'CountDown',
  props: {
    //距离开始提示文字
    tipText: {
      type: String,
      default: '倒计时',
    },
    dayText: {
      type: String,
      default: '天',
    },
    hourText: {
      type: String,
      default: '时',
    },
    minuteText: {
      type: String,
      default: '分',
    },
    secondText: {
      type: String,
      default: '秒',
    },
    datatime: {},
    isDay: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
	  timeInterval: null,
      time: this.datatime,
      day: '00',
      hour: '00',
      minute: '00',
      second: '00',
    }
  },
  created() {
    this.show_time()
  },
  watch: {
    datatime(val) {
      clearInterval(this.timeInterval)
      this.time = val
      this.show_time()
    },
  },
  mounted() {
  },
  methods: {
    show_time() {
	console.log(this.datatime)
      if (this.time.toString().length == 13) {
        // 毫秒级
        console.log('毫秒')
        this.time = this.time / 1000
      } else if (this.time.toString().length == 10) {
        console.log('秒')
        // 秒级
      } else {
        // 时间
        console.log('时间')
        this.time = Date.parse(this.time) / 1000
      }
	  this.runTime()
      this.timeInterval = setInterval(this.runTime, 1000)
    },
	runTime() {
		//时间函数
		let intDiff = this.time - Date.parse(new Date()) / 1000 //获取数据中的时间戳的时间差
		let day = 0,
		hour = 0,
		minute = 0,
		second = 0
		if (intDiff > 0) {
		//转换时间
		if (this.isDay === true) {
		  day = Math.floor(intDiff / (60 * 60 * 24))
		} else {
		  day = 0
		}
		hour = Math.floor(intDiff / (60 * 60)) - day * 24
		minute = Math.floor(intDiff / 60) - day * 24 * 60 - hour * 60
		second = Math.floor(intDiff) - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60
		if (hour <= 9) hour = '0' + hour
		if (minute <= 9) minute = '0' + minute
		if (second <= 9) second = '0' + second
			this.day = day
			this.hour = hour
			this.minute = minute
			this.second = second
		} else {
			this.day = '00'
			this.hour = '00'
			this.minute = '00'
			this.second = '00'
		}
	}
  },
  destroyed() {
  	clearTimeout(this.timeInterval)
  }
}
</script>
