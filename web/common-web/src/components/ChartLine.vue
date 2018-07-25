<template>
  <div>
    <chart theme="wonderland"  :options="option"></chart>
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
    legend: {
      type: Array
    },
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
      let series = []
      if (this.data) {
        for (var key in this.data) {
          series.push({
            name: key,
            type: 'line',
            data: this.data[key]
          }
          )
        }
      }
      return {
        title: {
          text: this.title
          // subtext: '数据来自网络'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          // data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
          data: this.legend
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            // saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.cols
          // data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
          type: 'value'
        },
        series: series
        // series: [
        //   {
        //     name: '邮件营销',
        //     type: 'line',
        //     stack: '总量',
        //     data: [120, 132, 101, 134, 90, 230, 210]
        //   },
        //   {
        //     name: '联盟广告',
        //     type: 'line',
        //     stack: '总量',
        //     data: [220, 182, 191, 234, 290, 330, 310]
        //   },
        //   {
        //     name: '视频广告',
        //     type: 'line',
        //     stack: '总量',
        //     data: [150, 232, 201, 154, 190, 330, 410]
        //   },
        //   {
        //     name: '直接访问',
        //     type: 'line',
        //     stack: '总量',
        //     data: [320, 332, 301, 334, 390, 330, 320]
        //   },
        //   {
        //     name: '搜索引擎',
        //     type: 'line',
        //     stack: '总量',
        //     data: [820, 932, 901, 934, 1290, 1330, 1320]
        //   }
        // ]
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
