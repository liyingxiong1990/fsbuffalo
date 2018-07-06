<template>
  <div class="order_log">
    <!-- <base-table :tableData="table.data"  :tableCols="table.cols"></base-table> -->
    <base-table :pageQuery="table.pageQuery" :addTableRowClick="addAlertClick" :rowContextdblClick="rowContextdblClick" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
  </div>
</template>

<script>
import { tableColumnFormatterTool, dateFormatterTool } from '../../assets/common/common'
export default {
  name: 'order_log',
  created () {
    // this.initData()
  },
  computed: {
  },
  data () {
    return {
      table: {
        pageQuery: {
          apiModule: 'sdcOperateLog',
          apiMethod: 'getAll',
          reload: true
        },
        cols: [
          { label: '操作类型', prop: 'operate_type', minwidth: '100px' },
          { label: '操作人', prop: 'operator_user_name', minwidth: '100px' },
          { label: '操作内容', prop: 'operate_content', minwidth: '200px' },
          { label: '操作时间', prop: 'created_time', minwidth: '150px', formatter: this.formatterTime }
        ],
        contextMenuData: [
        ],
        data: [],
        showFilter: false
      }
    }
  },
  methods: {
    initData () {
      let vm = this
      this.$store.state.http.auto('sdcOperateLog', 'getAll').then(res => {
        this.table.data = res.data
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    formatterDictType (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictType, cellValue)
    },
    formatterTime (row, column, cellValue) {
      return dateFormatterTool(cellValue, 'yyyy-MM-dd hh:mm:ss')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
