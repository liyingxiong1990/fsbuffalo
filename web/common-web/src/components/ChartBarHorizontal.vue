<template>
  <div>
    <chart theme="wonderland" :style="{ height: height + 'px', width: width + 'px'}" :options="option"></chart>
  </div>
</template>

<script>
export default {
  props: {
    /**
     * @augments title 标题
     */
    title: {
      type: String,
      default: ''
    },
    /**
     * @augments cols 显示列
     * e.g. [
     *  {
     *    label: '数量',
     *    prop: 'quantity'
     *  }
     * ]
     */
    cols: {
      type: Array
    },
    /**
     * @augments data 数据
     * json 数据
     */
    data: {
      // type: Object
    },
    legend: {

    },
    height: {
      type: Number,
      default: 400
    },
    width: {
      type: Number,
      default: 400
    }
  },
  computed: {
    option () {
      // let legend = []
      // let series = []
      // let yAxisData = []
      // if (this.cols && this.cols.hasOwnProperty('length')) {
      //   yAxisData = this.cols
      // }
      // if (this.data) {
      //   for (var key in this.data) {
      //     series.push({
      //       name: key,
      //       type: 'bar',
      //       stack: '总量',
      //       label: {
      //         normal: {
      //           show: true
      //         }
      //       },
      //       data: this.data[key]
      //     }
      //     )
      //   }
      // }
      return {
        title: {
          text: this.title
          // subtext: '数据来自网络'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: this.legend,
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value'
        },
        yAxis: [
          {
            type: 'category',
            data: this.cols
          }
        ],
        series: this.data
      }
    }

  },
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>
