<template>
  <div class="enterprise-warehouseOrder">
    <base-table tableHandLeftPlaceholder="" :pageQuery="table.pageQuery" :handRightBotton="table.handRightBotton" :rowContextdblClick="getAlertOpen" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    <warehouseOrder-dialog :dialog="dialog" :submitCallback="submitCallback"></warehouseOrder-dialog>
  </div>
</template>

<script>
import { dateFormatterTool } from 'gdotc@common/assets/common/common'
import warehouseOrderDialog from './dialog/warehouseOrderDialog'
export default {
  name: 'warehouseOrder',
  components: {
    warehouseOrderDialog
  },
  props: {
  },
  created () {
  },
  computed: {
  },
  data () {
    return {
      table: {
        pageQuery: {
          apiModule: 'warehouseOrder',
          apiMethod: 'getAll',
          reload: true
        },
        handRightBotton: [
          {
            name: '新增外县市出仓单',
            icon: 'el-icon-circle-plus-outline',
            fn: this.addDeliverOpen,
            entitlement: true
          },
          {
            name: `新增专卖店出仓单`,
            icon: `el-icon-circle-plus-outline`,
            fn: this.addDriverOpen,
            entitlement: true
          }
        ],
        cols: [
          { label: '出仓单号', prop: 'id', minwidth: '90px' },
          { label: '出单日期', prop: 'order_date', minwidth: '90px', formatter: this.formatterTime },
          { label: '司机', prop: 'deliverer', minwidth: '90px' },
          { label: '路线', prop: 'line_name', minwidth: '90px' },
          { label: '开单人', prop: 'out_order_recorder', minwidth: '90px' }
        ],
        contextMenuData: [
          {
            name: `查看`,
            icon: `el-icon-search`,
            fnEvent: this.getAlertOpen,
            entitlement: true
          },
          {
            name: `新增外县市出仓单`,
            icon: `el-icon-circle-plus-outline`,
            fnEvent: this.addDeliverOpen,
            entitlement: true
          },
          {
            name: `新增专卖店出仓单`,
            icon: `el-icon-circle-plus-outline`,
            fnEvent: this.addDriverOpen,
            entitlement: true
          },
          {
            name: `删除`,
            icon: `el-icon-delete`,
            fnEvent: this.deleteAlertOpen,
            entitlement: true
          }
        ]
      },
      dialog: {
        title: ``,
        visible: false,
        type: '',
        data: {},
        loading: false
      }
    }
  },
  methods: {
    formatterTime (row, column, cellValue) {
      return dateFormatterTool(cellValue, 'yyyy-MM-dd')
    },
    rowContextdblClick (row) {
      // this.putAlert(row)
    },
    getAlertOpen (row) {
      if (!row) {
        row = this.$store.state.tableCurrentRow
      }
      this.dialog.currentRow = row
      this.dialog.type = 'get'
      this.dialog.visible = true
    },
    addDeliverOpen (row) {
      this.dialog.type = 'post_deliver'
      this.dialog.visible = true
    },
    addDriverOpen (row) {
      this.dialog.type = 'post_driver'
      this.dialog.visible = true
    },
    showStatistic (row) {
      this.dialog.type = 'statistic'
      this.dialog.visible = true
    },
    putAlertOpen (row) {
      if (!row) {
        row = this.$store.state.tableCurrentRow
      }
      this.dialog.currentRow = row
      this.dialog.type = 'put'
      this.dialog.visible = true
    },
    deleteAlertOpen (row) {
      if (!row) {
        row = this.$store.state.tableCurrentRow
      }
      this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.state.http.auto('warehouseOrder', 'delete', { data: row }).then((response) => {
          this.$message.success('删除成功!')
          this.table.pageQuery.reload = true
        })
      })
    },
    submitCallback (res) {
      this.table.pageQuery.reload = true
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="sass" scoped>
</style>

<style lang="sass">
</style>