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
      <div style="clear:both"></div>
    </div>

    <div style="clear:both"></div>

    <div class="manage-scale-row">
      <chart-bar-horizontal :height="1600" :width="2000" :title="data.productSalesTodayData.title" :cols="data.productSalesTodayData.cols" :data="data.productSalesTodayData.data"></chart-bar-horizontal>
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
        productSalesTodayData: {
          title: '当天产品销量',
          cols: [
            {
              label: '销量',
              type: 'bar',
              prop: 'all'
            }
          ],
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
      this.productSalesToday()
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
    productSalesToday () {
      let vm = this
      this.$store.state.http.auto('statistic', 'productSalesToday', {}).then(res => {
        var salesArray = []
        var productArray = []
        for (let item of res.data) {
          salesArray.push(Number(item.quantity))
          productArray.push(item.name)
        }
        salesArray.reverse()
        productArray.reverse()
        vm.data.productSalesTodayData.data = {}
        vm.data.productSalesTodayData.data['销量'] = salesArray
        vm.data.productSalesTodayData.cols = productArray
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