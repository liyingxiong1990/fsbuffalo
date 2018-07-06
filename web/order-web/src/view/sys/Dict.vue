<template>
  <div class="Dict">
    <base-table :tableData="tableData" :addTableRowClick="showAddDialog" :tableCols="tableCols" :contextMenuData="contextMenuData" :rowContextdblClick="rowContextdblClick"></base-table>
    <el-dialog :title="dialog.title" width="500px" :visible.sync="dialogFormVisible"  :before-close="dialogClose">
      <el-form :model="fromObj" :rules="rules" ref="ruleForm">
        <el-form-item label="字典类型" label-width="110px" prop='dict_type'>
          <el-input v-model="fromObj.dict_type" clearable></el-input>
        </el-form-item>
        <el-form-item label="字典类型说明" label-width="110px" prop='dict_type_desc'>
          <el-input v-model="fromObj.dict_type_desc" clearable></el-input>
        </el-form-item>
        <el-form-item label="字典代码" label-width="110px" prop='dict_code'>
          <el-input type="text" v-model="fromObj.dict_code" :maxlength="codemaxlength" ></el-input>
        </el-form-item>
        <el-form-item label="字典描述" label-width="110px" prop='dict_value'>
          <el-input v-model="fromObj.dict_value" clearable></el-input>
        </el-form-item>
        <el-form-item label="排序" label-width="110px" prop='sort'>
          <el-input type="number" v-model.number="fromObj.sort" :maxlength="codemaxlength"></el-input>
        </el-form-item>
        <el-form-item label="备注" label-width="110px" prop='remark'>
          <el-input v-model="fromObj.remark" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { dateFormatterTool, dialogClose } from '../../assets/common/common'
export default {
  name: 'dict',
  data () {
    return {
      data: [],
      tableCols: [
        { label: '字典类型', prop: 'dict_type', minwidth: '150px' },
        { label: '字典类型说明', prop: 'dict_type_desc', minwidth: '150px' },
        { label: '字典代码', prop: 'dict_code', minwidth: '120px' },
        { label: '字典描述', prop: 'dict_value', minwidth: '110px' },
        { label: '排序', prop: 'sort', minwidth: '80px' },
        { label: '最后修改时间', prop: 'last_modified', minwidth: '150px', formatter: this.formatterTime, display: false },
        { label: '备注', prop: 'remarks', minwidth: '100px' },
        { label: '创建时间', prop: 'created_time', minwidth: '150px', formatter: this.formatterTime, display: false },
        { label: '修改时间', prop: 'modified_time', minwidth: '150px', formatter: this.formatterTime, display: false }
      ],
      tableData: [],
      contextMenuData: [
        {
          name: '添加',
          icon: `el-icon-plus`,
          fnEvent: this.showAddDialog,
          entitlement: true
        },
        {
          name: `编辑`,
          icon: `el-icon-edit`,
          fnEvent: this.showEditDialog,
          entitlement: true
        },
        {
          name: `删除`,
          icon: `el-icon-delete`,
          fnEvent: this.deleteObj,
          entitlement: true
        }],
      statusText: '',
      fromObj: {},
      temporaryData: {},
      dialogFormVisible: false,
      dialogTitle: '添加',
      rules: {
        dict_type: [
          { required: true, message: '请输入字典类型', trigger: 'blur' },
          { max: 60, message: '长度不能超过 60 ', trigger: 'blur' }
        ],
        dict_type_desc: [
          { required: true, message: '请输入字典类型说明', trigger: 'blur' },
          { max: 60, message: '长度不能超过 60 ', trigger: 'blur' }
        ],
        dict_code: [
          { required: true, message: '请输入字典代码', trigger: 'blur' }
        ],
        dict_value: [
          { required: true, message: '请输入字典描述', trigger: 'blur' },
          { max: 60, message: '长度不能超过 60 ', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '请输入排序', trigger: 'blur' },
          { min: 1, type: 'number', message: '必须是正数' }
        ]
      },
      codemaxlength: 36,
      dialog: {
        title: '',
        type: ''
      }
    }
  },
  computed: {
  },
  created () {
    this.getAll()
  },
  methods: {
    getAll () {
      let vm = this
      this.$store.state.http.get('/sys/dict').then((response) => {
        this.data = this.tableData = response.data
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    messageSuccess () {
      this.$message({
        showClose: true,
        message: this.statusText,
        type: 'success'
      })
    },
    messageError () {
      this.$message({
        showClose: true,
        message: this.statusText,
        type: 'error'
      })
    },
    formatterTime (row, column, cellValue) {
      return dateFormatterTool(cellValue, 'yyyy-MM-dd')
    },
    showAddDialog () {
      this.fromObj = {}
      if (this.$store.state.tableCurrentRow) {
        this.fromObj['dict_type'] = this.$store.state.tableCurrentRow['dict_type']
        this.fromObj['dict_type_desc'] = this.$store.state.tableCurrentRow['dict_type_desc']
      }
      this.dialog.title = '添加字典'
      this.dialogFormVisible = true
      this.dialog.type = 'post'
    },
    showEditDialog (row) {
      if (!row) {
        row = this.$store.state.tableCurrentRow
      }
      this.dialog.title = '编辑字典'
      let obj = {}
      for (let item in row) {
        obj[item] = row[item]
        if (item === 'sort') {
          obj[item] = Number(row[item])
        }
      }
      this.fromObj = obj
      this.dialogFormVisible = true
      this.dialog.type = 'put'
    },
    deleteObj () {
      let obj = this.$store.state.tableCurrentRow
      this.$confirm('确定删除该数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.state.http.auto('sysDict', 'delete', { params: obj }).then((response) => {
          this.statusText = response.statusText
          this.messageSuccess()
          this.getAll()
        })
      })
    },
    addObj (form) {
      this.$store.state.http.auto('sysDict', 'add', { data: this.fromObj }).then((response) => {
        this.statusText = response.statusText
        this.fromObj = {}
        this.$refs[form].resetFields()
        this.dialogFormVisible = false
        this.$store.state.tableCurrentRow = null
        this.getAll()
        this.messageSuccess()
      })
    },
    editObj (form) {
      let obj = {}
      for (let item in this.fromObj) {
        obj[item] = this.fromObj[item]
      }
      obj.sort = Math.round(obj.sort)
      this.$store.state.http.auto('sysDict', 'update', { data: obj }).then((response) => {
        this.statusText = response.statusText
        this.$refs[form].resetFields()
        this.dialogFormVisible = false
        this.fromObj = {}
        this.$store.state.tableCurrentRow = null
        this.getAll()
        this.messageSuccess()
      })
    },
    dialogClose: dialogClose,
    cancelForm (form) {
      this.$store.state.tableCurrentRow = null
      this.dialogFormVisible = false
      this.$refs[form].resetFields()
    },
    submitForm (formname) {
      this.$refs[formname].validate((valid) => {
        if (valid) {
          if (this.dialog.type === 'post') {
            this.addObj(formname)
          } else if (this.dialog.type === 'put') {
            this.editObj(formname)
          }
          for (let item in this.$refs.dictType) {
            this.$refs.dictType[item].className = null
          }
        } else {
          this.statusText = 'error submit'
          this.messageError()
          return false
        }
      })
    },
    rowContextdblClick (row) {
      this.showEditDialog(row)
    }
  }
}
</script>

<style lang="scss" scoped>
.Dict {
  width: 100%;
}
</style>