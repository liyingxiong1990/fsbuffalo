<template>
  <div class="request_log">
    <base-table :tableData="table.data"  :tableCols="table.cols"></base-table>
  </div>
</template>

<script>
import { tableColumnFormatterTool, dateFormatterTool } from '../../assets/common/common'
export default {
  name: 'request_log',
  created () {
    this.initData()
  },
  computed: {
  },
  data () {
    return {
      table: {
        cols: [
          { label: '请求ip', prop: 'user_ip', minwidth: '100px' },
          { label: '请求url', prop: 'request_url', minwidth: '120px' },
          { label: '请求方法', prop: 'request_method', minwidth: '100px' },
          { label: '请求参数', prop: 'request_params', minwidth: '100px' },
          { label: '响应状态', prop: 'response_status', minwidth: '100px' },
          { label: '响应内容', prop: 'response_content', minwidth: '150px' },
          { label: '请求时间', prop: 'created_time', minwidth: '150px', formatter: this.formatterTime }
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
      this.$store.state.http.auto('requestLog', 'getAll').then(res => {
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
