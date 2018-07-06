<template>
  <div class="cert_customer">
    <base-table :tableData="table.data" :addTableRowClick="addAlertClick" :rowContextdblClick="rowContextdblClick" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="500px" :before-close="dialogClose">
      <el-form label-width="110px" :model="dialog.data" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="操作名称" prop='request_type'>
          <el-input v-model="dialog.data.request_type" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="请求类型" prop='operation'>
          <el-select v-model="dialog.data.operation" placeholder="请选择请求类型" style="width: 100%" size="mini" >
            <el-option v-for="(item, key) in dictSysOperation" :key="key" :label="item.dict_code" :value="item.dict_code">
              <span style="float: left">{{ item.dict_value }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.dict_code }}</span>
            </el-option>
          </el-select> 
        </el-form-item>
        <el-form-item label="API请求路径" prop='api_url'>
          <el-input v-model="dialog.data.api_url" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop='remarks'>
          <el-input type="textarea" :rows="dialog.remarkRows" v-model="dialog.data.remarks" size="mini"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')" size="mini">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { tableColumnFormatterTool, dateFormatterTool, dialogClose } from '../../assets/common/common'
export default {
  name: 'cert_customer',
  props: {
    menu: {
      type: Object
    }
  },
  created () {
    this.initData(this.menu)
  },
  computed: {
    dictSysOperation () {
      return this.$store.getters.sysDict('sys_operation')
    }
  },
  data () {
    return {
      table: {
        cols: [
          { label: '操作名称', prop: 'request_type', minwidth: '100px' },
          { label: '请求类型', prop: 'operation', minwidth: '100px' },
          { label: 'API请求路径', prop: 'api_url', minwidth: '130px' },
          { label: '备注', prop: 'remarks', minwidth: '100px' },
          { label: '创建日期', prop: 'created_time', formatter: this.formatterTime, minwidth: '150px', display: false },
          { label: '修改时间', prop: 'modified_time', minwidth: '150px', formatter: this.formatterTime, display: false }
        ],
        contextMenuData: [
          {
            name: `添加`,
            icon: `el-icon-plus`,
            fnEvent: this.addAlertClick,
            entitlement: true
          },
          {
            name: `编辑`,
            icon: `el-icon-edit`,
            fnEvent: this.putAlertClick,
            entitlement: true
          },
          {
            name: `删除`,
            icon: `el-icon-delete`,
            fnEvent: this.deleteAlertClick,
            entitlement: true
          }
        ],
        data: []
      },
      dialog: {
        title: ``,
        visible: false,
        type: '',
        data: {},
        menuIdLoading: false,
        remarkRows: 2,
        rules: {
          request_type: [
            { required: true, message: '请选择操作名称', trigger: 'blur' }
          ]
          // operation: [
          //   { required: true, message: '请选择请求类型', trigger: 'blur' }
          // ],
          // api_url: [
          //   { required: true, message: '请输入API请求路径', trigger: 'blur' }
          // ]
        }
      }
    }
  },
  mounted () {
  },
  methods: {
    initData (obj) {
      if (obj && obj.hasOwnProperty('menu_id')) {
        let vm = this
        this.$store.state.http.auto('sysPermission', 'getByMenuId', { params: { menu_id: obj.menu_id } }).then(res => {
          this.table.data = res.data
        }).catch(function (error) {
          vm.$message.error(error.statusText)
          console.log(error)
        })
      }
    },
    addAlertClick () {
      if (this.menu.children.length) {
        this.$message.error('此目录禁止添加权限')
      } else {
        this.dialog.data = {
          operation: 'GET'
        }
        this.dialog.visible = true
        this.dialog.title = `添加${this.menu.name}权限`
        this.dialog.type = 'post'
      }
    },
    putAlert (row) {
      this.dialog.visible = true
      this.dialog.title = `编辑${this.menu.name}权限`
      this.dialog.type = 'put'
      this.dialog.data = {
        operation: null
      }
      for (var key in row) {
        this.dialog.data[key] = `${row[key] === 'null' || row[key] === null ? '' : row[key]}`
      }
    },
    deleteAlertClick () {
      let obj = this.$store.state.tableCurrentRow
      this.$confirm('此操作将删除该条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.state.http.auto('sysPermission', 'delete', { params: obj }).then((response) => {
          this.$message.success('删除成功')
          this.initData(this.menu)
        })
      })
    },
    putAlertClick () {
      this.putAlert(this.$store.state.tableCurrentRow)
    },
    rowContextdblClick (row) {
      this.putAlert(row)
    },
    formatterDictSysOperation (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictSysOperation, cellValue)
    },
    formatterTime (row, column, cellValue) {
      return dateFormatterTool(cellValue, 'yyyy-MM-dd')
    },
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    submitForm (from) {
      let vm = this
      this.$refs[from].validate((valid) => {
        if (valid) {
          let requestMethod = null
          if (this.dialog.type === 'post') {
            requestMethod = 'add'
            this.dialog.data.menu_id = this.menu.menu_id
          }
          if (this.dialog.type === 'put') {
            requestMethod = 'update'
          }
          this.$store.state.http.auto('sysPermission', requestMethod, { data: this.dialog.data }).then((res) => {
            this.$message.success(res.statusText)
            this.$refs[from].resetFields()
            this.dialog.visible = false
            this.dialog.type = null
            this.dialog.data = {}
            this.initData(this.menu)
          }).catch(function (error) {
            vm.$message.error(error.statusText)
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>