<template>
  <div class="cert_customer">
    <base-table :tableData="table.data" :addTableRowClick="addAlertClick" :rowContextdblClick="rowContextdblClick" :tableCols="table.cols"
      :contextMenuData="table.contextMenuData"></base-table>
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="500px"  :before-close="dialogClose">
      <el-form label-width="78px" :model="dialog.data" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="登录名" prop='login_name'>
          <el-input v-model="dialog.data.login_name" clearable></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop='nickname'>
          <el-input v-model="dialog.data.nickname" clearable></el-input>
        </el-form-item>
        <el-form-item label="失效日期" prop='expired_date'>
          <el-date-picker v-model="dialog.data.expired_date" type="date"  placeholder="选择日期" style="width: 100%"></el-date-picker>
        </el-form-item>
        <el-form-item label="邮件地址" prop='email'>
          <el-input v-model="dialog.data.email" clearable></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop='mobile'>
          <el-input v-model="dialog.data.mobile" clearable></el-input>
        </el-form-item>
        <el-form-item label="所属部门" prop='dept_id'>
          <el-select style="width: 100%" v-model="dialog.data.dept_id">
            <el-option v-for="(item, key) in deptData" v-if="item.pid === 1" :key="key" :label="item.dept_name" :value="item.dept_id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="dialog.title" :visible.sync="dialogFormVisible2"  width="500px" :before-close="dialogClose">
      <el-form :model="form" label-width="78px" :rules="dialog.rules" ref="pwdForm">
        <el-form-item label="重置密码"  prop='secret_id'>
          <el-input  type="password" v-model="form.secret_id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop='secret2_id'>
          <el-input type="password" v-model="form.secret2_id" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelPwd('pwdForm')">取 消</el-button>
        <el-button type="primary" @click="submitForm('pwdForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { tableColumnFormatterTool, dateFormatterTool, dialogClose } from '../../assets/common/common'
export default {
  name: 'cert_customer',
  created () {
    this.initData()
    this.initDepartmentData()
  },
  computed: {
    dictStatus () {
      return this.$store.getters.sysDict('acct_sys_department_status')
    }
  },
  data () {
    return {
      deptData: [],
      dialogFormVisible2: false,
      form: {
        secret_id: null,
        secret2_id: null
      },
      table: {
        cols: [
          { label: '登录名', prop: 'login_name', minwidth: '120px' },
          { label: '昵称', prop: 'nickname', minwidth: '120px' },
          { label: '邮件地址', prop: 'email', minwidth: '120px' },
          { label: '手机号码', prop: 'mobile', minwidth: '120px' },
          { label: '失效日期', prop: 'expired_date', formatter: this.formatterTime, minwidth: '150px' },
          { label: '所属部门', prop: 'dept_id', formatter: this.formatterDept, minwidth: '120px' },
          { label: '用户状态', prop: 'user_status', formatter: this.formatterDictStatus, minwidth: '100px' },
          { label: '备注', prop: 'remarks', display: false, minwidth: '100px' },
          { label: '创建时间', prop: 'created_time', formatter: this.formatterTime, minwidth: '150px', display: false },
          { label: '修改时间', prop: 'modified_time', formatter: this.formatterTime, minwidth: '150px', display: false }
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
          },
          {
            name: `密码`,
            icon: `el-icon-edit-outline`,
            fnEvent: this.secret,
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
        rules: {
          login_name: [
            { required: true, message: '请输入登录名', trigger: 'blur' }
          ],
          expired_date: [
            { required: true, message: '请选择失效日期', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '请输入昵称', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '请输入手机号码', trigger: 'blur' },
            { pattern: /^1[34578]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { pattern: /^[\d,a-zA-Z]([\w.-]+)@([a-zA-Z0-9-]+).([a-zA-Z.]+[a-zA-Z])$/, message: '请输入正确的邮箱格式，例如: xxxx@xx.xxx , xxxx@xx.xx.xxx', trigger: 'blur' },
            { max: 128, message: '长度不能超过128', trigger: 'blur' }
          ],
          dept_id: [
            { required: true, message: '请选择所属部门', trigger: 'blur' }
          ],
          secret_id: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          secret2_id: [
            { required: true, message: '请再次输入密码', trigger: 'blur' },
            { validator: this.validatePwd, trigger: 'blur' }
          ]
        }
      }
    }
  },
  methods: {
    initData () {
      let vm = this
      this.$store.state.http.get('/sys/user', 'getAll').then(res => {
        this.table.data = res.data
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    initDepartmentData () {
      let vm = this
      this.$store.state.http.get('/sys/department').then((response) => {
        vm.deptData = response.data
      }).catch(error => {
        console.log(error)
      })
    },
    addAlertClick () {
      this.dialog.visible = true
      this.dialog.title = '添加用户'
      this.dialog.type = 'post'
      this.dialog.data = {
        user_status: 1,
        login_name: null,
        dept_id: null,
        expired_date: null
      }
    },
    putAlert (row) {
      this.dialog.visible = true
      this.dialog.title = '编辑用户'
      this.dialog.type = 'put'
      this.dialog.data = {
        login_name: null,
        dept_id: null,
        expired_date: null
      }
      Object.assign(this.dialog.data, row)
      this.dialog.data.acct_type = this.dialog.data.acct_type + ''
      // this.dialog.data.user_status = this.dialog.data.user_status + ''
    },
    putAlertClick () {
      this.putAlert(this.$store.state.tableCurrentRow)
    },
    rowContextdblClick (row) {
      this.putAlert(row)
    },
    formatterDept (row, column, cellValue) {
      let dept = this.deptData
      for (let key in dept) {
        if (dept[key].dept_id === cellValue) {
          return dept[key].dept_name
        }
      }
      return cellValue
    },
    formatterDictStatus (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictStatus, cellValue)
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
          if (from === 'ruleForm') {
            let successMessage = null
            let errorMessage = null
            let requestMethod = null
            if (this.dialog.type === 'post') {
              successMessage = '用户创建成功'
              errorMessage = '用户创建失败'
              requestMethod = 'add'
            }
            if (this.dialog.type === 'put') {
              successMessage = '用户修改成功'
              errorMessage = '用户修改失败'
              requestMethod = 'update'
            }
            this.$store.state.http.auto('sysUser', requestMethod, { data: this.dialog.data }).then((res) => {
              this.$message.success(successMessage)
              this.$refs[from].resetFields()
              this.dialog.visible = false
              this.dialog.type = null
              this.dialog.data = {}
              this.initData()
            }).catch(function (error) {
              vm.$message.error(errorMessage)
              console.log(error)
            })
          } else {
            this.dialog.data.pwd = this.form.secret_id
            this.$store.state.http.auto('sysUser', 'updatePwd', { data: this.dialog.data }).then((res) => {
              this.$message.success(res.statusText)
              this.cancelPwd(from)
              this.initData()
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
      objects = this.$store.state.tableCurrentRow.user_id
      this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.state.http.auto('sysUser', 'delete', { params: { user_id: objects } }).then((response) => {
          this.datas = response
          this.initData()
        })
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    recover () {
      this.$store.state.tableCurrentRow.user_status = 1
      this.$store.state.http.auto('sysUser', 'update', { data: this.$store.state.tableCurrentRow }).then((res) => {
        this.$message.success(res.statusText)
        this.initData()
      })
    },
    cancel () {
      this.$store.state.tableCurrentRow.user_status = 2
      this.$store.state.http.auto('sysUser', 'update', { data: this.$store.state.tableCurrentRow }).then((res) => {
        this.$message.success(res.statusText)
        this.initData()
      })
    },
    secret () {
      this.dialogFormVisible2 = true
      this.dialog.type = 'put'
      this.dialog.title = '修改密码'
      this.dialog.data.user_id = this.$store.state.tableCurrentRow.user_id
      this.form = {
        secret_id: null,
        secret2_id: null
      }
    },
    cancelPwd (form) {
      this.dialogFormVisible2 = false
      this.$refs[form].resetFields()
      this.form = {
        secret_id: null,
        secret2_id: null
      }
    },
    validatePwd (rule, value, callback) {
      if (value !== this.form.secret_id) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
