<template>
  <div class="manage-scale">
    <!-- <div class="manage-scale-row">
      <div class="manage-scale-row-half">
        <base-map-china :mapDatas="mapDataChina"></base-map-china>
      </div>
      <div class="manage-scale-row-half">
        <base-map :mapDatas="mapData"></base-map>
      </div>
      <div style="clear:both"></div>
    </div>
    <div class="manage-scale-row">
      <div class="manage-scale-row-half">
        <chart-pie :title="pieQuotedTypeData.title" :cols="pieQuotedTypeData.cols" :data="pieQuotedTypeData.data"></chart-pie>
      </div>
      <div class="manage-scale-row-half">
        <chart-histogram :title="histogramIndustryData.title" :grid="histogramIndustryData.grid" :rotate="histogramIndustryData.rotate" :cols="histogramIndustryData.cols" :classifyData="histogramIndustryData.classifyData" :select="histogramIndustryData.select"></chart-histogram>
      </div>
      <div style="clear:both"></div>
    </div>
    <div class="manage-scale-row">
      <div class="manage-scale-row-half">
        <chart-pie :title="pieOperatingCenterData.title" :cols="pieOperatingCenterData.cols" :data="pieOperatingCenterData.data"></chart-pie>
      </div>
      <div class="manage-scale-row-half">
        <chart-pie :title="pieCompanyTypeData.title" :cols="pieCompanyTypeData.cols" :data="pieCompanyTypeData.data"></chart-pie>
      </div>
      <div style="clear:both"></div>
    </div> -->
    <div class="manage-scale-row">
      <!-- <div class="block">
        <span class="demonstration">开始</span>
        <el-date-picker @change="storeSales" v-model="storeSalesPeriodStart" type="month" placeholder="选择月"></el-date-picker>
        <span class="demonstration">结束</span>
        <el-date-picker @change="storeSales" v-model="storeSalesPeriodEnd" type="month" placeholder="选择月"></el-date-picker>
      </div> -->
      <div class="block">
        <span class="demonstration">请选择时间范围</span>
        <el-date-picker @change="storeSales" v-model="timePeriod" type="daterange" align="right" unlink-panels range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions2"></el-date-picker>
      </div>
      <chart-histogram :height="600" :title="histogramStoreData.title" :grid="histogramStoreData.grid" :rotate="histogramStoreData.rotate" :cols="histogramStoreData.cols" :classifyData="histogramStoreData.classifyData" :select="histogramStoreData.select"></chart-histogram>
    </div>
    <div style="clear:both"></div>
  </div>
</template>

<script>
import {
  tableColumnFormatterTool
} from 'gdotc@common/assets/common/common'
export default {
  name: 'StoreAnalysis', // 企业分析
  props: {
  },
  created () {
    this.initData()
    // this.provinceAnalysis()
    // this.cityAnalysis()
    // this.companyTypeAnalysis()
    // this.quotedTypeAnalysis()
    // this.industryAnalysis()
    // this.operatingCenterAnalysis()
    this.storeSales()
  },
  computed: {
    dictProvince () {
      return this.$store.getters.sysDict('ent_province')
    }
  },
  data () {
    return {
      timePeriod: '',
      storeSalesPeriodStart: '',
      storeSalesPeriodEnd: '',
      pickerOptions2: {
        shortcuts: [{
          text: '最近一周',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      mapDataChina: [],
      mapData: [],
      pieCompanyTypeData: {
        title: '企业类型分析',
        cols: [
          {
            label: '注册',
            prop: '00'
          },
          {
            label: '纯托管',
            prop: '01'
          },
          {
            label: '纯挂牌',
            prop: '02'
          },
          {
            label: '挂牌托管',
            prop: '03'
          },
          {
            label: '托管注册',
            prop: '04'
          }
        ],
        data: {
          '00': 0,
          '01': 0,
          '02': 0,
          '03': 0,
          '04': 0
        }
      },
      pieQuotedTypeData: {
        title: '挂牌板块分析',
        cols: [
          {
            label: '主板',
            prop: '00'
          },
          {
            label: '科技版',
            prop: '01'
          },
          {
            label: '华侨版展示层',
            prop: '02'
          },
          {
            label: '华侨版交易层',
            prop: '03'
          }
        ],
        data: {
          '00': 0,
          '01': 0,
          '02': 0,
          '03': 0
        }
      },
      histogramIndustryData: {
        title: '企业行业分析',
        select: `行业`,
        grid: { // 控制图的大小，调整下面这些值就可以，
          x: 50, // 左侧宽度
          x2: 50, // 右侧宽度
          y2: 170 // x轴标题高度
        },
        rotate: 75,
        cols: [
          {
            label: '数量',
            type: 'bar',
            prop: 'quantity'
          }
        ],
        classifyData: [
          {
            name: `行业`,
            data: []
          }
        ]
      },
      histogramRegisterData: {
        title: '注册企业',
        select: `地域`,
        cols: [
          {
            label: '数量',
            type: 'bar',
            prop: 'quantity'
          }
        ],
        classifyData: [
          {
            name: `地域`,
            data: [
              {
                name: `佛山市`,
                quantity: 187891
              },
              {
                name: `珠海市`,
                quantity: 135747
              },
              {
                name: `中山市`,
                quantity: 84561
              },
              {
                name: `江门市`,
                quantity: 53274
              }
            ]
          }
        ]
      },
      pieOperatingCenterData: {
        title: '运营中心',
        cols: [
          {
            label: '佛山运营中心',
            prop: 'fs'
          },
          {
            label: '广州运营中心',
            prop: 'gz'
          }
        ],
        data: {
          fs: 0,
          gz: 0
        }
      },
      histogramStoreData: {
        title: '专卖店销量Top20',
        select: `销量`,
        grid: { // 控制图的大小，调整下面这些值就可以，
          y2: 210 // x轴标题高度
        },
        rotate: 15,
        cols: [
          {
            label: '销量',
            type: 'bar',
            prop: 'quantity'
          }
        ],
        classifyData: [
          {
            name: `销量`,
            data: []
          }
        ]
      }
    }
  },
  methods: {
    initData () {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
      this.timePeriod = []
      this.timePeriod.push(start)
      this.timePeriod.push(end)
    },
    formatterProvince (cellValue) {
      return tableColumnFormatterTool(this.dictProvince, cellValue)
    },
    provinceAnalysis () {
      let vm = this
      let dp = this.dictProvince
      this.$store.state.http.auto('bossStatistic', 'provinceAnalysis', {}).then(res => {
        for (let item of res.data) {
          vm.mapDataChina.push({
            value: Number(item.count),
            name: tableColumnFormatterTool(dp, item.province).split('省')[0]
          })
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    cityAnalysis () {
      let vm = this
      this.$store.state.http.auto('bossStatistic', 'cityAnalysis', {}).then(res => {
        for (let item of res.data) {
          vm.mapData.push({
            value: Number(item.count),
            name: item.place
          })
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    companyTypeAnalysis () {
      let vm = this
      this.$store.state.http.auto('bossStatistic', 'companyTypeAnalysis', {}).then(res => {
        for (let item of res.data) {
          vm.pieCompanyTypeData.data[item.company_type] = Number(item.count)
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    quotedTypeAnalysis () {
      let vm = this
      this.$store.state.http.auto('bossStatistic', 'quotedTypeAnalysis', {}).then(res => {
        for (let item of res.data) {
          vm.pieQuotedTypeData.data[item.quoted_type] = Number(item.count)
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    industryAnalysis () {
      let vm = this
      this.$store.state.http.auto('bossStatistic', 'industryAnalysis', {}).then(res => {
        for (let item of res.data) {
          vm.histogramIndustryData.classifyData[0].data.push({
            quantity: Number(item.count),
            name: item.industry
          })
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    operatingCenterAnalysis () {
      let vm = this
      this.$store.state.http.auto('bossStatistic', 'operatingCenterAnalysis', {}).then(res => {
        for (let item of res.data) {
          vm.pieOperatingCenterData.data[item.operating_center] = Number(item.count)
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    storeSales () {
      let vm = this
      this.$store.state.http.auto('statistic', 'storeSales', { params: { startTime: vm.timePeriod[0], endTime: vm.timePeriod[1] } }).then(res => {
        vm.histogramStoreData.classifyData[0].data = []
        for (let item of res.data) {
          vm.histogramStoreData.classifyData[0].data.push({
            quantity: Number(item.quantity),
            name: item.store_name
          })
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.manage-scale-row {
  padding-bottom: 30px;
}
</style>

<style lang="scss">
.manage-scale {
  padding-top: 15px;
  width: 100%;
  .manage-scale-row {
    width: 100%;
    .manage-scale-row-half {
      width: calc(50% - 8px);
      float: left;
      &:nth-child(2n + 1) {
        margin-right: 8px;
      }
      &:nth-child(2n) {
        margin-left: 8px;
      }
    }
  }
}
</style>