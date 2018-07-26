<template>
  <div class="stats-province-overview">
    <div class="planar">
      <div class="planar-box">
        <div class="planar-box-title">
          今天销量
        </div>
        <div class="planar-box-content">
          {{data.todaySalesDriver}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          本月销量
        </div>
        <div class="planar-box-content">
          {{data.thisMonthSalesDriver}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          本年销量
        </div>
        <div class="planar-box-content">
          {{data.thisYearSalesDriver}}
        </div>
      </div>
      <div style="clear:both"></div>
    </div>

    <div style="clear:both"></div>

    <div class="manage-scale">
      <div class="manage-scale-row">
        <chart-bar-horizontal :height="1600" :width="2000" :title="data.storeSalesThisMonthData.title" :cols="data.storeSalesThisMonthData.cols" :data="data.storeSalesThisMonthData.data"></chart-bar-horizontal>
      </div>
      <div class="manage-scale-row">
        <chart-pie :title="data.storeProductSalesThisMonthData.title" :cols="data.storeProductSalesThisMonthData.cols" :data="data.storeProductSalesThisMonthData.data"></chart-pie>
      </div>
      <div class="manage-scale-row">
        <chart-bar-horizontal :height="1600" :width="2000" :title="data.storeSalesThisYearData.title" :cols="data.storeSalesThisYearData.cols" :data="data.storeSalesThisYearData.data"></chart-bar-horizontal>
      </div>
      <div class="manage-scale-row">
        <chart-pie :title="data.storeProductSalesThisYearData.title" :cols="data.storeProductSalesThisYearData.cols" :data="data.storeProductSalesThisYearData.data"></chart-pie>
      </div>
      <div class="manage-scale-row">
        <chart-pie :title="data.storeLineSalesThisYearData.title" :cols="data.storeLineSalesThisYearData.cols" :data="data.storeLineSalesThisYearData.data"></chart-pie>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'statsOverview', // 专卖店分析
  props: {
  },
  created () {
    this.initData()
  },
  computed: {
    dictType () {
      return this.$store.getters.sysDict('statistic_role_type')
    }
  },
  data () {
    return {
      data: {
        todaySalesDriver: 0,
        thisMonthSalesDriver: 0,
        thisYearSalesDriver: 0,
        storeSalesThisMonthData: {
          title: '本月各专卖店销量',
          cols: [
            {
              label: '销量',
              type: 'bar',
              prop: 'all'
            }
          ],
          data: []
        },
        storeSalesThisYearData: {
          title: '今年各专卖店销量',
          cols: [
            {
              label: '销量',
              type: 'bar',
              prop: 'all'
            }
          ],
          data: []
        },
        salesEveryMonthData: {
          title: '今年销量走势',
          legend: [],
          cols: [],
          data: []
        },
        salesEveryDayData: {
          title: '本月销量走势',
          legend: [],
          cols: [],
          data: []
        },
        storeProductSalesThisMonthData: {
          title: '本月专卖店产品销量',
          cols: [],
          data: {}
        },
        storeProductSalesThisYearData: {
          title: '今年专卖店产品销量',
          cols: [],
          data: {}
        },
        storeLineSalesThisYearData: {
          title: '今年专卖店线路分析',
          cols: [],
          data: {}
        }
      }
    }
  },
  methods: {
    initData () {
      this.todaySales()
      this.thisMonthSales()
      this.thisYearSales()
      this.storeSalesThisMonth()
      this.storeProductSalesThisMonth()
      this.storeSalesThisYear()
      this.storeProductSalesThisYear()
      this.storeLineSalesThisYear()
    },
    todaySales () {
      this.$store.state.http.auto('statistic', 'todaySales', {}).then(res => {
        this.data.todaySales = 0
        for (var item in res.data) {
          if (res.data[item].type === 'deliver') {
            this.data.todaySales += res.data[item].quantity
            this.data.todaySalesDelivery = res.data[item].quantity
          } else if (res.data[item].type === 'driver') {
            this.data.todaySales += res.data[item].quantity
            this.data.todaySalesDriver = res.data[item].quantity
          }
        }
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    thisMonthSales () {
      this.$store.state.http.auto('statistic', 'thisMonthSales', {}).then(res => {
        this.data.thisMonthSales = 0
        for (var item in res.data) {
          if (res.data[item].type === 'deliver') {
            this.data.thisMonthSales += res.data[item].quantity
            this.data.thisMonthSalesDelivery = res.data[item].quantity
          } else if (res.data[item].type === 'driver') {
            this.data.thisMonthSales += res.data[item].quantity
            this.data.thisMonthSalesDriver = res.data[item].quantity
          }
        }
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    thisYearSales () {
      this.$store.state.http.auto('statistic', 'thisYearSales', {}).then(res => {
        this.data.thisYearSales = 0
        for (var item in res.data) {
          if (res.data[item].type === 'deliver') {
            this.data.thisYearSales += res.data[item].quantity
            this.data.thisYearSalesDelivery = res.data[item].quantity
          } else if (res.data[item].type === 'driver') {
            this.data.thisYearSales += res.data[item].quantity
            this.data.thisYearSalesDriver = res.data[item].quantity
          }
        }
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    storeSalesThisMonth () {
      let vm = this
      this.$store.state.http.auto('statistic', 'storeSalesThisMonth', {}).then(res => {
        var salesArray = []
        var storeArray = []
        for (var i = 0; i < res.data.length; i++) {
          storeArray.push(res.data[i].store_name)
          salesArray.push(res.data[i].quantity)
        }
        storeArray.reverse()
        salesArray.reverse()
        vm.data.storeSalesThisMonthData.data = []
        vm.data.storeSalesThisMonthData.data.push({
          name: '销量',
          type: 'bar',
          label: {
            normal: {
              show: true,
              position: 'right'
            }
          },
          data: salesArray
        })
        vm.data.storeSalesThisMonthData.legend = {
          data: ['销量']
        }
        vm.data.storeSalesThisMonthData.cols = storeArray
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    storeSalesThisYear () {
      let vm = this
      this.$store.state.http.auto('statistic', 'storeSalesThisYear', {}).then(res => {
        var salesArray = []
        var storeArray = []
        for (var i = 0; i < res.data.length; i++) {
          storeArray.push(res.data[i].store_name)
          salesArray.push(res.data[i].quantity)
        }
        storeArray.reverse()
        salesArray.reverse()
        vm.data.storeSalesThisYearData.data = []
        vm.data.storeSalesThisYearData.data.push({
          name: '销量',
          type: 'bar',
          label: {
            normal: {
              show: true,
              position: 'right'
            }
          },
          data: salesArray
        })
        vm.data.storeSalesThisYearData.legend = {
          data: ['销量']
        }
        vm.data.storeSalesThisYearData.cols = storeArray
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    salesEveryMonth () {
      let vm = this
      this.$store.state.http.auto('statistic', 'salesEveryMonth', {}).then(res => {
        var deliverSalesArray = []
        var driverSalesArray = []
        var totalSalesArray = []
        var monthArray = []
        for (var i = 0; i < res.data.length; i++) {
          if (i % 2 === 0) {
            monthArray.push(res.data[i].month)
            deliverSalesArray.push(Number(res.data[i].quantity))
            totalSalesArray.push(Number(res.data[i].quantity) + Number(res.data[i + 1].quantity))
          } else {
            driverSalesArray.push(Number(res.data[i].quantity))
          }
        }
        vm.data.salesEveryMonthData.legend = ['外线', '专卖店', '总量']
        vm.data.salesEveryMonthData.data = {}
        vm.data.salesEveryMonthData.data['外线'] = deliverSalesArray
        vm.data.salesEveryMonthData.data['专卖店'] = driverSalesArray
        vm.data.salesEveryMonthData.data['总量'] = totalSalesArray
        vm.data.salesEveryMonthData.cols = monthArray
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    salesEveryDay () {
      let vm = this
      this.$store.state.http.auto('statistic', 'salesEveryDay', {}).then(res => {
        var deliverSalesArray = []
        var driverSalesArray = []
        var totalSalesArray = []
        var dayArray = []
        for (var i = 0; i < res.data.length; i++) {
          if (i % 2 === 0) {
            dayArray.push(res.data[i].day)
            deliverSalesArray.push(Number(res.data[i].quantity))
            totalSalesArray.push(Number(res.data[i].quantity) + Number(res.data[i + 1].quantity))
          } else {
            driverSalesArray.push(Number(res.data[i].quantity))
          }
        }
        vm.data.salesEveryDayData.legend = ['外线', '专卖店', '总量']
        vm.data.salesEveryDayData.data = {}
        vm.data.salesEveryDayData.data['外线'] = deliverSalesArray
        vm.data.salesEveryDayData.data['专卖店'] = driverSalesArray
        vm.data.salesEveryDayData.data['总量'] = totalSalesArray
        vm.data.salesEveryDayData.cols = dayArray
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    storeProductSalesThisMonth () {
      let vm = this
      this.$store.state.http.auto('statistic', 'storeProductSalesThisMonth', {}).then(res => {
        for (let item of res.data) {
          vm.data.storeProductSalesThisMonthData.cols.push({
            label: item.name,
            prop: item.name
          })
          vm.data.storeProductSalesThisMonthData.data[item.name] = Number(item.quantity)
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    storeProductSalesThisYear () {
      let vm = this
      this.$store.state.http.auto('statistic', 'storeProductSalesThisYear', {}).then(res => {
        for (let item of res.data) {
          vm.data.storeProductSalesThisYearData.cols.push({
            label: item.name,
            prop: item.name
          })
          vm.data.storeProductSalesThisYearData.data[item.name] = Number(item.quantity)
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    storeLineSalesThisYear () {
      let vm = this
      this.$store.state.http.auto('statistic', 'storeLineSalesThisYear', {}).then(res => {
        for (let item of res.data) {
          vm.data.storeLineSalesThisYearData.cols.push({
            label: item.remarks,
            prop: item.remarks
          })
          vm.data.storeLineSalesThisYearData.data[item.remarks] = Number(item.quantity)
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
.stats-province-overview {
  width: 100%;
  padding: 20px 50px;
  box-sizing: border-box;
  .planar {
    width: 100%;
    box-sizing: border-box;
    .planar-box {
      float: left;
      width: 260px;
      margin: 0 30px 10px;
      border-bottom: 1px solid #e4e7ed;
      .planar-box-title {
        margin-bottom: 10px;
        font-weight: 700;
      }
      .planar-box-content {
        margin-bottom: 10px;
        color: #9c9ea2;
      }
    }
  }
}

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

<style lang="scss">
</style>