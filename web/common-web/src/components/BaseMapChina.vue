<template>
  <div class="user">
    <div id="main1" style="width: 100%;height:100%;min-width:500px;min-height:400px;">
    </div>
    <!-- <chart :options="pie" ref="map" id="map">
    </chart> -->
  </div>
</template>

<script>
import china from '../assets/config/provinces'
export default {
  name: 'BaseMapChina',
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
          text: '全国',
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
            name: '全国',
            type: 'map',
            roam: true,
            zoom: '1.2',
            mapType: 'china', // 自定义扩展图表类型
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
      this.citybase = [
        {
          name: '新疆',
          value: 0
        },
        {
          name: '西藏',
          value: 0
        },
        {
          name: '内蒙古',
          value: 0
        },
        {
          name: '宁夏',
          value: 0
        },
        {
          name: '北京',
          value: 0
        },
        {
          name: '重庆',
          value: 0
        },
        {
          name: '广西',
          value: 0
        },
        {
          name: '天津',
          value: 0
        },
        {
          name: '南海诸岛',
          value: 0
        },
        {
          name: '上海',
          value: 0
        }
      ]
      for (let item of china.provinces) {
        this.citybase.push({
          name: item.provinceName.split('省')[0],
          value: 0
        })
      }
    },
    initData () {
      this.show = false
      this.myChart = this.$echarts.init(document.getElementById('main1'))
      this.myChart.setOption(this.pie)
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
