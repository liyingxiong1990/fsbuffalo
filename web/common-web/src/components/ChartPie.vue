<template>
  <div>
    <div v-show="classifyData">
      <el-select v-model="selectValue" slot="prepend" size="small" style="width: 120px; float: left; padding-left: 5px; margin-bottom: -32px; z-index: 99999;"  placeholder="请选择">
        <el-option :label="item.name" v-for="(item, index) of classifyData"  :key="index" :value="item.name"></el-option>
      </el-select>
    </div>
    <chart style="width: 100%;" theme="wonderland" :options="options"></chart>
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
      default: '股权结构'
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
      type: Object
    },
    /**
     * @augments classifyData
     * 按不同维度分类
     * e.g.
     * classifyData: [
          {
            name: '总股本',
            colsProp: [
              {
                label: '总股本',
                prop: 'all'
              },
              {
                label: '质押总股本',
                prop: 'pledge'
              }
            ]
          },
          {
            name: '法人总股本',
            colsProp: [
              {
                label: '法人总股本',
                prop: 'frAll'
              },
              {
                label: '法人质押股本',
                prop: 'frPledge'
              }
            ]
          }
        ]
     */
    classifyData: {
      type: Array
    },
    /**
     * @augments select
     * 分类饼图初始化选中项
     */
    select: {
      type: String,
      default: null
    }
  },
  computed: {
    options () {
      let legendData = []
      let seriesData = []
      if (this.classifyData && this.classifyData.hasOwnProperty('length') && this.classifyData.length > 0) {
        for (let row of this.classifyData) {
          if (row.name === this.selectValue) {
            this.defaultCols = row.colsProp
          }
        }
      }
      if (this.cols && this.cols.hasOwnProperty('length')) {
        this.defaultCols = this.cols
      }
      this.defaultCols.map(row => {
        legendData.push(row.label)
        let value = 0
        if (this.data) {
          value = this.data[row.prop]
        }
        seriesData.push(
          {
            name: row.label,
            value: value
          }
        )
      })
      return {
        title: {
          text: this.title,
          subtext: '',
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          y: 38,
          orient: 'vertical',
          left: 'left',
          data: legendData
        },
        series: [
          {
            name: '',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: seriesData,
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
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
      defaultCols: [],
      selectValue: '',
      option: {
        title: {
          text: '股权结构',
          subtext: '',
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        series: [
          {
            name: '',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
              { value: 335, name: '直接访问' },
              { value: 310, name: '邮件营销' },
              { value: 234, name: '联盟广告' },
              { value: 135, name: '视频广告' },
              { value: 1548, name: '搜索引擎' }
            ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
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
