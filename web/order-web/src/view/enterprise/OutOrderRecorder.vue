<template>
  <div class="enterprise-outOrderRecorder">
    <base-table tableHandLeftPlaceholder="请输入关键字搜索" :pageQuery="table.pageQuery" :addTableRowClick="addAlertOpen" :rowContextdblClick="getAlertOpen" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    <outOrderRecorder-dialog :dialog="dialog" :submitCallback="submitCallback"></outOrderRecorder-dialog>
  </div>
</template>

<script>
import { dateFormatterTool } from 'gdotc@common/assets/common/common'
import outOrderRecorderDialog from './dialog/OutOrderRecorderDialog'
export default {
  name: 'outOrderRecorder',
  components: {
    outOrderRecorderDialog
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
          apiModule: 'outOrderRecorder',
          apiMethod: 'getAll',
          reload: true
        },
        cols: [
          { label: '姓名', prop: 'name', minwidth: '90px' }
        ],
        contextMenuData: [
          {
            name: `查看`,
            icon: `el-icon-search`,
            fnEvent: this.getAlertOpen,
            entitlement: true
          },
          {
            name: `新增`,
            icon: `el-icon-circle-plus-outline`,
            fnEvent: this.addAlertOpen,
            entitlement: true
          },
          {
            name: `修改`,
            icon: `el-icon-edit`,
            fnEvent: this.putAlertOpen,
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
    addAlertOpen (row) {
      this.dialog.type = 'post'
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
        this.$store.state.http.auto('outOrderRecorder', 'delete', { data: row }).then((response) => {
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