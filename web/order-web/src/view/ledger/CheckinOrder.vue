<template>
  <div class="enterprise-checkinOrder">
    <base-table tableHandLeftPlaceholder="库存日期" :pageQuery="table.pageQuery" :handRightBotton="table.handRightBotton" :rowContextdblClick="getAlertOpen" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    <checkinOrder-dialog :dialog="dialog" :submitCallback="submitCallback"></checkinOrder-dialog>
  </div>
</template>

<script>
import { dateFormatterTool } from 'gdotc@common/assets/common/common'
import checkinOrderDialog from './dialog/checkinOrderDialog'
export default {
  name: 'checkinOrder',
  components: {
    checkinOrderDialog
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
          apiModule: 'checkinOrder',
          apiMethod: 'getAll',
          reload: true
        },
        handRightBotton: [
          {
            name: '新增进仓单',
            icon: 'el-icon-circle-plus-outline',
            fn: this.addAlertOpen,
            entitlement: true
          },
          {
            name: '统计',
            icon: 'el-icon-circle-plus-outline',
            fn: this.showStatistic,
            entitlement: true
          }
        ],
        cols: [
          { label: '进仓单号', prop: 'id', minwidth: '90px' },
          { label: '进仓日期', prop: 'checkin_date', minwidth: '90px', formatter: this.formatterTime },
          { label: '仓管', prop: 'in_order_recorder', minwidth: '90px' },
          { label: '缴仓', prop: 'carrier', minwidth: '90px' }
        ],
        contextMenuData: [
          {
            name: `查看`,
            icon: `el-icon-search`,
            fnEvent: this.getAlertOpen,
            entitlement: true
          },
          {
            name: `新增进仓单`,
            icon: `el-icon-circle-plus-outline`,
            fnEvent: this.addAlertOpen,
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
      window.open(`${urlPath}/enterprise.html#/${row.id}/checkin_order`)
    },
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
        this.$store.state.http.auto('checkinOrder', 'delete', { data: row }).then((response) => {
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