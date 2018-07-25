<template>
  <div>
    <chart   theme="wonderland" :options="option"></chart>
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
      type: Array
    }
  },
  computed: {
    option () {
      let legend = []
      let series = []
      let seriesData = {}
      let yAxisData = []
      if (this.cols && this.cols.hasOwnProperty('length')) {
        this.cols.map(row => {
          legend.push(row.label)
          seriesData[row.label] = []
          series.push(
            {
              name: row.label,
              type: 'bar',
              data: seriesData[row.label]
            }
          )
        })
      }
      if (this.data && this.data.length > 0) {
        this.data.map((row, index) => {
          yAxisData.push(row.name)
          this.cols.map(item => {
            seriesData[item.label].push(row[item.prop])
          })
        })
      }
      return {
        title: {
          text: this.title
          // subtext: '数据来自网络'
        },
        tooltip: {
          trigger: 'axis',
          type: 'hideTip',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          x: 900,
          data: legend
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          boundaryGap: [0, 0.01]
        },
        yAxis: {
          type: 'category',
          data: yAxisData
        },
        series: series
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
