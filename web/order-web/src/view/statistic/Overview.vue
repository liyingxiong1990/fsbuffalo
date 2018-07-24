<template>
  <div class="stats-province-overview">
    <div class="planar">
      <div class="planar-box">
        <div class="planar-box-title">
          今天销量
        </div>
        <div class="planar-box-content">
          {{data.todaySales}}<br/> 外线：{{data.todaySalesDelivery}} / 专卖店：{{data.todaySalesDriver}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          本月销量
        </div>
        <div class="planar-box-content">
          {{data.thisMonthSales}}<br/> 外线：{{data.thisMonthSalesDelivery}} / 专卖店：{{data.thisMonthSalesDriver}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          本年销量
        </div>
        <div class="planar-box-content">
          {{data.thisYearSales}}<br/> 外线：{{data.thisYearSalesDelivery}} / 专卖店：{{data.thisYearSalesDriver}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          今天进仓量
        </div>
        <div class="planar-box-content">
          {{data.todayCheckin}}
        </div>
      </div>
      <div style="clear:both"></div>
    </div>

    <div style="clear:both"></div>

    <div class="manage-scale-row">
      <chart-line :height="600" :width="800" :title="data.salesEveryMonthData.title" :cols="data.salesEveryMonthData.cols" :data="data.salesEveryMonthData.data"></chart-line>
    </div>

    <div class="manage-scale-row">
      <chart-bar-horizontal :height="1600" :width="2000" :title="data.productSalesThisMonthData.title" :cols="data.productSalesThisMonthData.cols" :data="data.productSalesThisMonthData.data"></chart-bar-horizontal>
    </div>
  </div>

</template>

<script>
export default {
  name: 'statsOverview', // 概况
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
        todaySales: 0,
        todaySalesDelivery: 0,
        todaySalesDriver: 0,
        thisMonthSales: 0,
        thisMonthSalesDelivery: 0,
        thisMonthSalesDriver: 0,
        thisYearSales: 0,
        thisYearSalesDelivery: 0,
        thisYearSalesDriver: 0,
        todayCheckin: 0,
        productSalesThisMonthData: {
          title: '本月产品销量',
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
          title: '销量走势',
          legend: [],
          cols: [],
          data: []
        }

      }
    }
  },
  methods: {
    initData () {
      this.todaySales()
      this.thisMonthSales()
      this.thisYearSales()
      this.todayCheckin()
      this.productSalesThisMonth()
      this.salesEveryMonth()
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
    todayCheckin () {
      this.$store.state.http.auto('statistic', 'todayCheckin', {}).then(res => {
        this.data.todayCheckin = res.data[0].quantity
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    productSalesThisMonth () {
      let vm = this
      this.$store.state.http.auto('statistic', 'productSalesThisMonth', {}).then(res => {
        var salesArray = []
        var productArray = []
        for (let item of res.data) {
          salesArray.push(Number(item.quantity))
          productArray.push(item.name)
        }
        salesArray.reverse()
        productArray.reverse()
        vm.data.productSalesThisMonthData.data = {}
        vm.data.productSalesThisMonthData.data['销量'] = salesArray
        vm.data.productSalesThisMonthData.cols = productArray
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    salesEveryMonth () {
      let vm = this
      this.$store.state.http.auto('statistic', 'salesEveryMonth', {}).then(res => {
        debugger
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
        debugger
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
</style>

<style lang="scss">
</style>