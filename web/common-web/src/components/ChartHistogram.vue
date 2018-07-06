<template>
  <div>
    <div v-show="classifyData">
      <el-select v-if="classifyData !=null && classifyData.length > 1" v-model="selectValue" slot="prepend" size="mini" style="width: 120px; float: right; padding-left: 5px; margin-bottom: -32px; z-index: 99999;" placeholder="请选择">
        <el-option :label="item.name" v-for="(item, index) of classifyData" :key="index" :value="item.name"></el-option>
      </el-select>
    </div>
    <chart style="width: 100%;" :style="{ height: height + 'px'}" theme="wonderland" :options="option"></chart>
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
      default: '主要股东持股排名'
    },
    /**
     * @augments cols 显示列
     * e.g. [
     *  {
     *    label: '数量',
     *    prop: 'quantity',
     *    type: 'line' // e.g.{'line', 'bar'}
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
    },
    /**
     * @augments classifyData
     * 按不同维度分类
     * e.g.
     * [{
        name: `企业分类`,
        data: [
          {
            name: `注册`,
            quantity: 1678
          },
          {
            name: `主板`,
            quantity: 1202
          }
        ]
      },{
        name: `业务分类`,
        data: [
          {
            name: `挂牌`,
            quantity: 1678
          },
          {
            name: `托管`,
            quantity: 1202
          }
        ]
      }]
     */
    classifyData: {
      type: Array
    },
    /**
     * @augments select
     * 分类初始化选中项
     */
    select: {
      type: String,
      default: null
    },
    grid: { // 控制图的大小，调整下面这些值就可以，
      type: Object
      // x: 50, // 左侧宽度
      // x2: 50, // 右侧宽度
      // y2: 150 // x轴标题高度
    },
    rotate: { // x轴标题角度
      type: Number
    },
    height: {
      type: Number,
      default: 400
    }
  },
  computed: {
    option () {
      let legend = []
      let series = []
      let seriesData = {}
      let xAxisData = []
      if (this.cols && this.cols.hasOwnProperty('length')) {
        this.cols.map(row => {
          legend.push(row.label)
          seriesData[row.label] = []
          series.push(
            {
              name: row.label,
              type: row.type,
              yAxisIndex: row.type === 'line' ? 1 : 0,
              data: seriesData[row.label]
            }
          )
        })
      }
      let data = this.data
      if (this.classifyData) {
        for (let item of this.classifyData) {
          if (item.name === this.selectValue) {
            data = item.data
          }
        }
      }
      if (data && data.length > 0) {
        data.map((row, index) => {
          xAxisData.push(row.name)
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
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          show: this.data,
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: legend
        },
        grid: this.grid,
        xAxis: [
          {
            axisLabel: {
              rotate: this.rotate,
              textStyle: {
                // color: '#000',
                // shadowColor: '#000',
                // fontWeight: 'bold'
              }
            },
            type: 'category',
            data: xAxisData,
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '数量',
            min: 0,
            axisLabel: {
              formatter: '{value}'
            }
          },
          {
            type: 'value',
            name: '占比',
            min: 0,
            max: 100,
            interval: 20,
            axisLabel: {
              formatter: '{value} %'
            }
          }
        ],
        series: series
      }
    }
  },
  created () {
    if (this.select) {
      this.selectValue = this.select
    }
  },
  data () {
    return {
      selectValue: ``,
      options: {
        title: {
          text: '世界人口总量'
          // subtext: '数据来自网络'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ['蒸发量', '降水量', '平均温度']
        },
        xAxis: [
          {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '水量',
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
              formatter: '{value} ml'
            }
          },
          {
            type: 'value',
            name: '温度',
            min: 0,
            max: 25,
            interval: 5,
            axisLabel: {
              formatter: '{value} °C'
            }
          }
        ],
        series: [
          {
            name: '蒸发量',
            type: 'bar',
            data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
          },
          {
            name: '降水量',
            type: 'bar',
            data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
          },
          {
            name: '平均温度',
            type: 'line',
            yAxisIndex: 1,
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          }
        ]
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>
