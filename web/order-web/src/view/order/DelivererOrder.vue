<template>
  <div class="enterprise-delivererOrder">
    <base-table tableHandLeftPlaceholder="送货单号/出单日期/司机/路线/专卖店" :pageQuery="table.pageQuery" :handRightBotton="table.handRightBotton" :rowContextdblClick="putAlertOpen" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    <delivererOrder-dialog :dialog="dialog" :submitCallback="submitCallback"></delivererOrder-dialog>
  </div>
</template>

<script>
import { dateFormatterTool } from 'gdotc@common/assets/common/common'
import delivererOrderDialog from './dialog/delivererOrderDialog'
export default {
  name: 'delivererOrder',
  components: {
    delivererOrderDialog
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
          apiModule: 'delivererOrder',
          apiMethod: 'today',
          reload: true
        },
        handRightBotton: [
          {
            name: '新增送货单',
            icon: 'el-icon-circle-plus-outline',
            fn: this.addAlertOpen,
            entitlement: true
          }
        ],
        cols: [
          { label: '送货单号', prop: 'id', minwidth: '90px' },
          { label: '出单日期', prop: 'order_date', minwidth: '90px', formatter: this.formatterTime },
          { label: '司机', prop: 'driver', minwidth: '90px' },
          { label: '路线', prop: 'line_name', minwidth: '90px' },
          { label: '专卖店', prop: 'store_name', minwidth: '90px' },
          { label: '店主', prop: 'store_holder', minwidth: '90px' },
          { label: '地址', prop: 'address', minwidth: '90px' },
          { label: '送货日期', prop: 'delivery_date', minwidth: '90px', formatter: this.formatterTime },
          { label: '是否已出仓', prop: 'is_out', minwidth: '90px', formatter: this.formatterIsOut }
        ],
        contextMenuData: [
          {
            name: `查看`,
            icon: `el-icon-search`,
            fnEvent: this.getAlertOpen,
            entitlement: true
          },
          {
            name: `新增送货单`,
            icon: `el-icon-circle-plus-outline`,
            fnEvent: this.addAlertOpen,
            entitlement: true
          },
          {
            name: `编辑送货单`,
            icon: `el-icon-edit`,
            fnEvent: this.putAlertOpen,
            entitlement: true
          },
          {
            name: `打印`,
            icon: `el-icon-printer`,
            fnEvent: this.printFrom,
            entitlement: true
          }
          // {
          //   name: `删除`,
          //   icon: `el-icon-delete`,
          //   fnEvent: this.deleteAlertOpen,
          //   entitlement: true
          // }
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
    printFrom (row) {
      if (!row) {
        row = this.$store.state.tableCurrentRow
      }
      let urlPath = window.location.origin
      let paths = window.location.pathname.split('/')
      for (let pathName of paths) {
        if (pathName !== '' && pathName != null && pathName.indexOf('html') < 0) {
          urlPath += '/' + pathName
        }
      }
      window.open(`${urlPath}/enterprise.html#/${row.id}/deliverer_order`)
    },
    formatterTime (row, column, cellValue) {
      return dateFormatterTool(cellValue, 'yyyy-MM-dd')
    },
    formatterIsOut (row, column, cellValue) {
      if (cellValue === 1) {
        return '是'
      } else if (cellValue === 0) {
        return '否'
      }
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
    addAlertOpen (row) {
      this.dialog.type = 'post'
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
        this.$store.state.http.auto('delivererOrder', 'delete', { data: row }).then((response) => {
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