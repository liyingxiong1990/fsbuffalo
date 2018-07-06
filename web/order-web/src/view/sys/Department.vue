<template>
  <div class="sys_department">
    <base-table :tableData="table.data" :addTableRowClick="addAlertClick" :rowContextdblClick="rowContextdblClick" :tableCols="table.cols"
      :contextMenuData="table.contextMenuData"></base-table>
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="400px"  :before-close="dialogClose">
      <el-form label-width="98px" :model="dialog.data" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="部门名称" prop='dept_name'>
          <el-input v-model="dialog.data.dept_name" size="mini" clearable></el-input>
        </el-form-item>
        <el-form-item label="上级机构" prop="pid">
        <el-select style="width: 100% !important" size="mini" v-model="dialog.data.pid" placeholder="请选择" >
        <el-option
          label="组织机构"
          value="1" style="width:100%">
        </el-option>
        <el-option
          v-for="(item, key) in table.data"
          v-if="item.pid === 1"
          :key="key"
          :label="item.dept_name"
          :value="item.dept_id+''" style="width:100%">
        </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="备注" size="mini" prop='remarks'>
          <el-input v-model="dialog.data.remarks" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')" size="small">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" size="small">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { tableColumnFormatterTool, dateFormatterTool, dialogClose } from '../../assets/common/common'
export default {
  name: 'sys_department',
  created () {
    this.initData()
  },
  computed: {
    dictStatus () {
      return this.$store.getters.sysDict('acct_sys_department_status')
    }
  },
  data () {
    return {
      arr: [],
      dialogFormVisible2: false,
      table: {
        cols: [
          { label: '部门名称', prop: 'dept_name' },
          { label: '所属机构', prop: 'pid', width: '150px', formatter: this.formatterPid },
          { label: '部门状态', prop: 'dept_status', formatter: this.formatterDictStatus },
          { label: '备注', prop: 'remarks' },
          { label: '创建时间', prop: 'created_time', formatter: this.formatterTime },
          { label: '修改时间', prop: 'modified_time', formatter: this.formatterTime, width: '150px', display: false }
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
            fnEvent: this.remove,
            entitlement: true
          },
          {
            name: `恢复`,
            icon: `el-icon-refresh`,
            fnEvent: this.recover,
            entitlement: true
          },
          {
            name: `注销`,
            icon: `el-icon-remove-outline`,
            fnEvent: this.cancel,
            entitlement: true
          }
        ],
        data: []
      },
      dialog: {
        title: ``,
        visible: false,
        type: '',
        data: {
        },
        rules: {
          dept_name: [
            { required: true, message: '请输入部门名称', trigger: 'blur' }
          ]
        }
      }
    }
  },
  methods: {
    initData () {
      let vm = this
      this.$store.state.http.get('/sys/department').then((response) => {
        this.table.data = response.data
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    addAlertClick () {
      this.dialog.visible = true
      this.dialog.title = '添加组织机构'
      this.dialog.type = 'post'
      let obj = {}
      this.form = obj
      this.dialog.data = {
        dept_name: null,
        pid: '1',
        remarks: null
      }
    },
    putAlert (row) {
      this.dialog.visible = true
      this.dialog.title = '编辑组织机构'
      this.dialog.type = 'put'
      this.dialog.data = {
        pid: null
      }
      Object.assign(this.dialog.data, row)
      this.dialog.data.pid = this.dialog.data.pid + ''
    },
    putAlertClick () {
      this.putAlert(this.$store.state.tableCurrentRow)
    },
    rowContextdblClick (row) {
      this.putAlert(row)
    },
    formatterDept (row, column, cellValue) {
      let dept = this.arr
      for (let key in dept) {
        if (dept[key].id === cellValue) {
          return dept[key].dept_name
        }
      }
      return cellValue
    },
    formatterDictStatus (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictStatus, cellValue)
    },
    formatterTime (row, column, cellValue) {
      return dateFormatterTool(cellValue, 'yyyy-MM-dd hh:mm:ss')
    },
    formatterPid (row, column, cellValue) {
      let data = this.table.data
      if (cellValue + '' === '1') {
        return '组织机构'
      }
      for (let row of data) {
        if (row.dept_id + '' === cellValue + '') {
          return row.dept_name
        }
      }
      return cellValue
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
          if (from === 'ruleForm') {
            let successMessage = null
            let errorMessage = null
            let requestMethod = null
            if (this.dialog.type === 'post') {
              vm.dialog.data.dept_status = 1
              successMessage = '部门创建成功'
              errorMessage = '部门创建失败'
              requestMethod = 'add'
            }
            if (this.dialog.type === 'put') {
              successMessage = '部门修改成功'
              errorMessage = '部门修改失败'
              requestMethod = 'update'
            }
            vm.$store.state.http.auto('sysDepartment', requestMethod, { data: vm.dialog.data }).then((res) => {
              this.$refs[from].resetFields()
              this.$message.success(successMessage)
              this.dialog.visible = false
              this.dialog.type = null
              this.dialog.data = {}
              this.initData()
            }).catch(function (error) {
              this.$message.error(errorMessage)
              console.log(error)
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    remove () {
      let objects = {}
      objects = this.$store.state.tableCurrentRow
      this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.state.http.auto('sysDepartment', 'delete', { params: { dept_id: objects.dept_id } }).then((response) => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.initData()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    recover () {
      this.$store.state.tableCurrentRow.dept_status = 1
      this.$store.state.http.auto('sysDepartment', 'update', { data: this.$store.state.tableCurrentRow }).then((res) => {
        this.$message.success(res.statusText)
        this.initData()
      })
    },
    cancel () {
      this.$store.state.tableCurrentRow.dept_status = 2
      this.$store.state.http.auto('sysDepartment', 'update', { data: this.$store.state.tableCurrentRow }).then((res) => {
        this.$message.success(res.statusText)
        this.initData()
      })
    }
  }
}
</script>

<style scoped>

</style>
