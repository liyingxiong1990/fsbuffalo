<template>
  <div class="sys_role">
    <div class="sys_role_left" style="float:left;width:340px">
      <base-table ref="baseTable" :tableData="table.data" pageLayout="total, sizes, prev, pager, next" :addTableRowClick="addRole" :rowContextdblClick="rowContextdblClick" :rowContextClick="rowContextClick" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    </div>
    <div class="sys_role_right"  style="float:right;width:calc(100% - 350px); padding-left: 10px;">
      <role-user ref="roleUser"></role-user>
    </div>
    <div style="clear: both"></div>
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="500px"  :before-close="dialogClose">
      <el-form label-width="78px" :model="dialog.data" label-position="right" :rules="dialog.rules" ref="ruleForm" size="mini" > 
        <el-form-item label="角色名称" prop='name'>
          <el-input v-model="dialog.data.name" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop='remarks'>
          <el-input type="textarea" :rows="dialog.remarkRows" v-model="dialog.data.remarks" size="mini" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')" size="mini" >取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" size="mini" >确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="dialog.title" :visible.sync="dialog.visibleMenuPermission" width="600px" :before-close="dialogClose">
      <permission-menu :role="role" v-if="dialog.visibleMenuPermission" @closeMenuPermission="closeMenuPermission"></permission-menu>
    </el-dialog>
  </div>
</template>

<script>
import PermissionMenu from './PermissionMenu'
import RoleUser from './RoleUser'
import { tableColumnFormatterTool, dateFormatterTool, dialogClose } from '../../assets/common/common'
export default {
  name: 'sys_role',
  created () {
    this.getAll()
  },
  computed: {
  },
  data () {
    return {
      role: null,
      table: {
        cols: [
          { label: '角色名称', prop: 'name', minwidth: '100px' },
          { label: '备注', prop: 'remarks', minwidth: '100px', display: false },
          { label: '创建时间', prop: 'created_time', formatter: this.formatterTime, minwidth: '150px', display: false },
          { label: '修改时间', prop: 'modified_time', formatter: this.formatterTime, minwidth: '150px', display: false }
        ],
        contextMenuData: [
          {
            name: `添加`,
            icon: `el-icon-plus`,
            fnEvent: this.addRole,
            entitlement: true
          },
          {
            name: `编辑`,
            icon: `el-icon-edit`,
            fnEvent: this.putRole,
            entitlement: true
          },
          {
            name: `权限`,
            icon: `el-icon-setting`,
            fnEvent: this.bindMenuPermission,
            entitlement: true
          },
          {
            name: `删除`,
            icon: `el-icon-delete`,
            fnEvent: this.deleteRole,
            entitlement: true
          }
        ],
        data: [],
        setCurrentRowIndex: 1
      },
      dialog: {
        title: ``,
        visible: false,
        visibleMenuPermission: false,
        type: '',
        data: {},
        remarkRows: 3,
        rules: {
          name: [
            { required: true, message: '请输入角色名称', trigger: 'blur' },
            { max: 10, message: '长度不能超过 10 ', trigger: 'blur' },
            { validator: this.validateName, trigger: 'blur' }
          ]
        }
      }
    }
  },
  mounted () {
  },
  methods: {
    getAll () {
      let vm = this
      this.$store.state.http.auto('sysRole', 'getAll').then(res => {
        this.table.data = res.data
        if (res.data.length > 0) {
          this.$refs.baseTable.setCurrentRow(0)
          this.$refs.roleUser.roleNameClick(this.table.data[0])
        } else {
          this.$refs.roleUser.roleNameClick()
        }
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    addRole () {
      this.dialog.visible = true
      this.dialog.title = '添加角色'
      this.dialog.type = 'post'
      this.dialog.data = {}
    },
    putAlert (row) {
      this.dialog.type = 'put'
      this.dialog.visible = true
      this.dialog.title = '编辑角色'
      this.dialog.data = {}
      for (var key in row) {
        this.dialog.data[key] = `${row[key] === 'null' || row[key] === null ? '' : row[key]}`
      }
    },
    bindMenuPermission () {
      this.role = this.$store.state.tableCurrentRow
      this.dialog.title = '绑定权限'
      this.dialog.visibleMenuPermission = true
    },
    closeMenuPermission (data) {
      this.dialog.visibleMenuPermission = data
    },
    putRole () {
      this.putAlert(this.$store.state.tableCurrentRow)
    },
    deleteRole () {
      let obj = this.$store.state.tableCurrentRow
      this.$confirm('确定删除该数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.state.http.auto('sysRole', 'delete', { params: obj }).then((response) => {
          this.getAll()
          this.$message.success('删除成功!')
        })
      })
    },
    rowContextClick (row) {
      let data = !row ? this.$store.state.tableCurrentRow : row
      if (data) {
        this.$refs.roleUser.roleNameClick(data)
      }
    },
    rowContextdblClick (row) {
      this.putAlert(row)
    },
    formatterDictType (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictType, cellValue)
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
          let successMessage = null
          let errorMessage = null
          let requestMethod = null
          if (this.dialog.type === 'post') {
            successMessage = '角色添加成功'
            errorMessage = '角色添加失败'
            requestMethod = 'add'
          }
          if (this.dialog.type === 'put') {
            successMessage = '角色修改成功'
            errorMessage = '角色修改失败'
            requestMethod = 'update'
          }
          this.$store.state.http.auto('sysRole', requestMethod, { data: this.dialog.data }).then((res) => {
            this.$message.success(successMessage)
            this.$refs[from].resetFields()
            this.dialog.visible = false
            this.dialog.type = null
            this.dialog.data = {}
            this.getAll()
          }).catch(function (error) {
            vm.$message.error(errorMessage)
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    validateName (rule, value, callback) {
      if (this.dialog.type === 'post') {
        let flag = false
        for (let i = 0; i < this.table.data.length; i++) {
          if (this.table.data[i].name === value) {
            flag = true
            break
          }
        }
        if (flag) {
          callback(new Error(`角色名称已存在`))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
  },
  components: {
    PermissionMenu,
    RoleUser
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
