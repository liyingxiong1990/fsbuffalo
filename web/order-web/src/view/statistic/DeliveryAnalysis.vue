<template>
  <div class="stats-province-overview">
    <div class="planar">
      <div class="planar-box">
        <div class="planar-box-title">
          外线今天销量
        </div>
        <div class="planar-box-content">
          {{data.todaySalesDelivery}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          外线本月销量
        </div>
        <div class="planar-box-content">
          {{data.thisMonthSalesDelivery}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          外线本年销量
        </div>
        <div class="planar-box-content">
          {{data.thisYearSalesDelivery}}
        </div>
      </div>
      <div style="clear:both"></div>
    </div>

    <div style="clear:both"></div>

    <div class="manage-scale">
      <div class="manage-scale-row">
        <chart-histogram :height="600" :title="data.deliverySalesThisMonthData.title" :grid="data.deliverySalesThisMonthData.grid" :rotate="data.deliverySalesThisMonthData.rotate" :cols="data.deliverySalesThisMonthData.cols" :classifyData="data.deliverySalesThisMonthData.classifyData" :select="data.deliverySalesThisMonthData.select"></chart-histogram>
      </div>
      <div class="manage-scale-row">
        <chart-pie :title="data.deliveryProductsThisMonthData.title" :cols="data.deliveryProductsThisMonthData.cols" :data="data.deliveryProductsThisMonthData.data"></chart-pie>
      </div>
      <div class="manage-scale-row">
        <chart-pie :title="data.deliveryAreaThisMonthData.title" :cols="data.deliveryAreaThisMonthData.cols" :data="data.deliveryAreaThisMonthData.data"></chart-pie>
      </div>
    </div>
    <div class="manage-scale">
      <div class="manage-scale-row">
        <chart-histogram :height="600" :title="data.deliverySalesThisYearData.title" :grid="data.deliverySalesThisYearData.grid" :rotate="data.deliverySalesThisYearData.rotate" :cols="data.deliverySalesThisYearData.cols" :classifyData="data.deliverySalesThisYearData.classifyData" :select="data.deliverySalesThisYearData.select"></chart-histogram>
      </div>
      <div class="manage-scale-row">
        <chart-pie :title="data.deliveryProductsThisYearData.title" :cols="data.deliveryProductsThisYearData.cols" :data="data.deliveryProductsThisYearData.data"></chart-pie>
      </div>
      <div class="manage-scale-row">
        <chart-pie :title="data.deliveryAreaThisYearData.title" :cols="data.deliveryAreaThisYearData.cols" :data="data.deliveryAreaThisYearData.data"></chart-pie>
      </div>
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
        todaySalesDelivery: 0,
        thisMonthSalesDelivery: 0,
        thisYearSalesDelivery: 0,
        deliverySalesThisMonthData: {
          title: '外线本月销量',
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
        },
        deliveryProductsThisMonthData: {
          title: '外线本月产品分析',
          cols: [],
          data: {}
        },
        deliveryAreaThisMonthData: {
          title: '外线本月地区分析',
          cols: [],
          data: {}
        },
        deliverySalesThisYearData: {
          title: '外线本年销量',
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
        },
        deliveryProductsThisYearData: {
          title: '外线本年产品分析',
          cols: [],
          data: {}
        },
        deliveryAreaThisYearData: {
          title: '外线本年地区分析',
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
      this.deliverySalesThisMonth()
      this.deliveryProductsThisMonth()
      this.deliverySalesThisYear()
      this.deliveryProductsThisYear()
    },
    todaySales () {
      this.$store.state.http.auto('statistic', 'todaySales', {}).then(res => {
        for (var item in res.data) {
          if (res.data[item].type === 'deliver') {
            this.data.todaySalesDelivery = res.data[item].quantity
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
        for (var item in res.data) {
          if (res.data[item].type === 'deliver') {
            this.data.thisMonthSalesDelivery = res.data[item].quantity
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
        for (var item in res.data) {
          if (res.data[item].type === 'deliver') {
            this.data.thisYearSalesDelivery = res.data[item].quantity
          }
        }
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    deliverySalesThisMonth () {
      let vm = this
      this.$store.state.http.auto('statistic', 'deliverySalesThisMonth', {}).then(res => {
        vm.data.deliverySalesThisMonthData.classifyData[0].data = []
        for (let item of res.data) {
          vm.data.deliverySalesThisMonthData.classifyData[0].data.push({
            quantity: Number(item.quantity),
            name: item.name
          })

          vm.data.deliveryAreaThisMonthData.cols.push({
            label: item.delivery_lines,
            prop: item.delivery_lines
          })
          vm.data.deliveryAreaThisMonthData.data[item.delivery_lines] = Number(item.quantity)
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    deliveryProductsThisMonth () {
      let vm = this
      this.$store.state.http.auto('statistic', 'deliveryProductsThisMonth', {}).then(res => {
        for (let item of res.data) {
          vm.data.deliveryProductsThisMonthData.cols.push({
            label: item.name,
            prop: item.name
          })
          vm.data.deliveryProductsThisMonthData.data[item.name] = Number(item.quantity)
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    deliverySalesThisYear () {
      let vm = this
      this.$store.state.http.auto('statistic', 'deliverySalesThisYear', {}).then(res => {
        vm.data.deliverySalesThisYearData.classifyData[0].data = []
        for (let item of res.data) {
          if (item.name !== '专卖店') {
            vm.data.deliverySalesThisYearData.classifyData[0].data.push({
              quantity: Number(item.quantity),
              name: item.name
            })

            vm.data.deliveryAreaThisYearData.cols.push({
              label: item.delivery_lines,
              prop: item.delivery_lines
            })
            vm.data.deliveryAreaThisYearData.data[item.delivery_lines] = Number(item.quantity)
          }
        }
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    deliveryProductsThisYear () {
      let vm = this
      this.$store.state.http.auto('statistic', 'deliveryProductsThisYear', {}).then(res => {
        for (let item of res.data) {
          vm.data.deliveryProductsThisYearData.cols.push({
            label: item.name,
            prop: item.name
          })
          vm.data.deliveryProductsThisYearData.data[item.name] = Number(item.quantity)
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