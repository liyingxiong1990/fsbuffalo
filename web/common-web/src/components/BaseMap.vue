<template>
  <div class="user">
    <div id="main" style="width: 100%;height:100%;min-width:500px;min-height:400px;">
    </div>
    <!-- <chart :options="pie" ref="map" id="map">
    </chart> -->
    <el-button type="info" @click="gd()" id="back" size="mini" round v-if="show">上一级</el-button>
  </div>
</template>

<script>
import GD from '../assets/config/guangdong'
import { convertPinyinTool } from '../assets/common/common'
export default {
  name: 'user',
  props: {
    mapDatas: {
      type: Array,
      required: true
    }
  },
  created () {
    this.base()
  },
  mounted () {
    this.initData()
  },
  data () {
    return {
      myChart: null,
      citybase: [],
      show: true,
      pie: {
        title: {
          text: '广东省',
          subtext: '挂牌企业分布'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b}<br/>挂牌企业：{c}家'
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center',
          feature: {
            dataView: { readOnly: false },
            // restore: {},
            saveAsImage: {}
          }
        },
        visualMap: {
          min: 0,
          max: 0,
          text: ['高', '低'],
          realtime: false,
          calculable: true,
          inRange: {
            color: ['yellow', 'orange']
          }
        },
        series: [
          {
            name: '广东省',
            type: 'map',
            roam: true,
            zoom: '1.2',
            mapType: 'GD', // 自定义扩展图表类型
            label: {
              fontSize: 9,
              show: true,
              fontWeight: 'bold'
            },
            itemStyle: {
              label: {
                fontSize: 8,
                show: true
              },
              emphasis: { label: { show: true, fontSize: 8 } },
              borderWidth: 0,
              shadowColor: 'rgba(0, 0, 0, 0.2)',
              shadowBlur: 1
            },
            data: []
          }
        ]
      }
    }
  },
  computed: {
  },
  watch: {
    mapDatas (val) {
      this.pie.series[0].data = this.citybase
      for (let i of this.pie.series[0].data) {
        for (let j of val) {
          if (i.name === j.name) {
            this.pie.visualMap.min = this.pie.visualMap.min < j.value ? this.pie.visualMap.min : j.value
            this.pie.visualMap.max = this.pie.visualMap.max > j.value ? this.pie.visualMap.max : j.value
            i.value = j.value
          }
        }
      }
      this.myChart.setOption(this.pie)
    }
  },
  methods: {
    base () {
      this.citybase = []
      for (let item of GD.citys) {
        this.citybase.push({
          name: item.name,
          value: 0
        })
        for (let row of item.area) {
          this.citybase.push({
            name: row,
            value: 0
          })
        }
      }
    },
    gd () {
      if (this.pie.series[0].mapType !== 'GD') {
        this.pie.series[0].mapType = 'GD'
        this.pie.title.text = '广东省'
        this.show = false
        this.myChart.setOption(this.pie)
      } else {
        this.$message.warning('没有上一级地区了')
      }
    },
    initData () {
      this.show = false
      let vm = this
      this.myChart = this.$echarts.init(document.getElementById('main'))
      this.myChart.setOption(this.pie)
      this.myChart.on('click', function (params) {
        vm.show = true
        let cityname = convertPinyinTool(params.name, 'initial')
        if (vm.pie.title.text === '广东省') {
          vm.pie.series[0].mapType = cityname
          vm.pie.title.text += ` > ${params.name}`
          vm.myChart.setOption(vm.pie)
        } else {
          vm.$message.warning('没有下一级地区了')
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.user {
  text-align: left;
  padding-left: 20px;
  position: relative;
}
#map {
  position: relative;
}
#back {
  position: absolute;
  top: 10px;
  right: 10px;
}
</style>
