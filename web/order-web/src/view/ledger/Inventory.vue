<template>
  <div class="enterprise-inventory">
    <base-table tableHandLeftPlaceholder="库存日期" :pageQuery="table.pageQuery" :handRightBotton="table.handRightBotton" :rowContextdblClick="getAlertOpen" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    <inventory-dialog :dialog="dialog" :submitCallback="submitCallback"></inventory-dialog>
  </div>
</template>

<script>
import { dateFormatterTool } from 'gdotc@common/assets/common/common'
import inventoryDialog from './dialog/inventoryDialog'
export default {
  name: 'inventory',
  components: {
    inventoryDialog
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
          apiModule: 'inventory',
          apiMethod: 'getAll',
          reload: true
        },
        handRightBotton: [
          // {
          //   name: '新增库存记录',
          //   icon: 'el-icon-circle-plus-outline',
          //   fn: this.addAlertOpen,
          //   entitlement: true
          // },
          // {
          //   name: '新增空白库存记录',
          //   icon: 'el-icon-circle-plus-outline',
          //   fn: this.addBlankAlertOpen,
          //   entitlement: true
          // }
        ],
        cols: [
          { label: '库存日期', prop: 'inventory_date', minwidth: '90px', formatter: this.formatterTime },
          { label: '上一库存日期', prop: 'last_date', minwidth: '90px', formatter: this.formatterTime }
        ],
        contextMenuData: [
          {
            name: `查看`,
            icon: `el-icon-search`,
            fnEvent: this.getAlertOpen,
            entitlement: true
          },
          {
            name: `删除`,
            icon: `el-icon-delete`,
            fnEvent: this.deleteAlertOpen,
            entitlement: true
          }
          // {
          //   name: `新增库存记录`,
          //   icon: `el-icon-circle-plus-outline`,
          //   fnEvent: this.addAlertOpen,
          //   entitlement: true
          // },
          // {
          //   name: `新增空白库存记录`,
          //   icon: `el-icon-circle-plus-outline`,
          //   fnEvent: this.addBlankAlertOpen,
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
    addBlankAlertOpen (row) {
      this.dialog.type = 'post_blank'
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
        this.$store.state.http.auto('inventory', 'delete', { data: row }).then((response) => {
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