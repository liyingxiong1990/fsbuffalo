<template>
  <div class="sys-menu-dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" append-to-body  width="500px" style="text-align:center" :before-close="dialogClose">
      <el-form label-width="80px" :model="dialog.data"  label-position="right" :rules="dialog.rules" ref="ruleForm" size="mini">
        <el-form-item label="菜单名称" prop='name'>
          <el-input v-model="dialog.data.name" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="菜单类型" prop='type'>
          <el-select style="width: 100%" v-model="dialog.data.type" @change="menuTypeChangeEvent"
           :disabled="dialog.type === 'put' || dialog.disabled" placeholder="请选择菜单类型"  size="mini">
            <el-option v-for="item in dictSysMenuType" :key="item.dict_code" :label="item.dict_value" :value="item.dict_code"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上级菜单" prop='parent_id'>
          <el-select style="width: 100%" v-model="dialog.data.parent_id" :loading="dialog.loading" :remote-method="parentIdRemoteMethod" @change="parentIdChangeEvent"
            placeholder="请选择上级菜单" filterable remote reserve-keyword size="mini">
            <el-option label="父菜单" value="SYS" v-if="dialog.data.type === 'SYS' || !dialog.data.type"></el-option>
            <el-option v-for="item in dialog.parentIdCurrentData" :key="item.menu_id" :label="item.inputLabel" :value="item.menu_id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="路由" prop='route'>
          <el-input v-model="dialog.data.route" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="图标" prop='icon'>
          <el-input v-model="dialog.data.icon" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="排序" prop='menu_order'>
          <el-input  v-model="dialog.data.menu_order" :disabled="dialog.type === 'post'" placeholder="自动填充"  size="mini"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop='remarks'>
          <el-input type="textarea" :rows="dialog.remarksRows" v-model="dialog.data.remarks" size="mini"></el-input>
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
import { dialogClose } from '../../../assets/common/common'
export default {
  name: 'sys-menu-dialog', // 菜单弹出框
  props: {
    tree: {
      type: Object,
      default: {
        data: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        defaulExpandedKeys: []
      }
    },
    dialog: {
      type: Object,
      default: {
        title: ``,
        visible: false,
        disabled: false,
        type: '',
        data: {},
        parentIdData: [],
        parentIdCurrentData: [],
        loading: false,
        remarksRows: 3
      }
    },
    submitCallback: {
      type: Function
    }
  },
  created () {
  },
  computed: {
    dictSysMenuType () {
      return this.$store.getters.sysDict('sys_menu_type')
    }
  },
  data () {
    return {
    }
  },
  mounted () {
    this.dialog.rules = {
      name: [
        { required: true, message: '请输入菜单名称', trigger: 'blur' }
      ],
      type: [
        { required: true, message: '请选择菜单类型', trigger: 'change' }
      ],
      parent_id: [
        { required: true, message: '请选择上级菜单', trigger: 'change' }
      ],
      menu_order: [
        { required: true, message: '请输入排序', trigger: 'blur' }
      ]
    }
  },
  methods: {
    menuTypeChangeEvent (val) {
      this.dialog.data.parent_id = ''
      if (val === 'SYS') {
        this.dialog.data.parent_id = 'SYS'
        this.dialog.parentIdData = this.dialog.parentIdCurrentData = []
        this.parentIdChangeEvent(this.dialog.data.parent_id)
        return
      }
      let list = []
      this.dialog.menuAll.map(obj => {
        if (val === 'PARENT' && obj.type === 'ROOT') {
          list.push(obj)
        } else if (val === 'NODE' && obj.type === 'PARENT') {
          list.push(obj)
        } else if (val === 'ROOT' && obj.type === 'SYS') {
          list.push(obj)
        }
      })
      this.dialog.parentIdData = this.dialog.parentIdCurrentData = list
      if (this.dialog.type === 'post') {
        this.dialog.data.parent_id = this.dialog.parentIdData[0].menu_id
        this.parentIdChangeEvent(this.dialog.data.parent_id)
      }
    },
    parentIdChangeEvent (val) {
      let length = 0
      if (val === 'SYS') {
        length = this.tree.data[this.tree.data.length - 1].menu_order
      } else {
        let fn = (data) => {
          for (let i = 0; i < data.length; i++) {
            if (val === data[i].parent_id) {
              length = data[data.length - 1].menu_order
              return
            } else if (data[i].hasOwnProperty('children') && data[i].children) {
              fn(data[i].children)
            }
          }
        }
        fn(this.tree.data)
      }
      this.dialog.data.menu_order = Number(length) + 1
    },
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    submitForm (form) {
      let vm = this
      this.$refs[form].validate((valid) => {
        if (valid) {
          let requestMethod = null
          if (this.dialog.type === 'post') {
            requestMethod = 'add'
          }
          if (this.dialog.type === 'put') {
            requestMethod = 'update'
          }
          this.$store.state.http.auto('sysMenu', requestMethod, { data: this.dialog.data }).then((res) => {
            this.$message.success(res.statusText)
            this.$refs[form].resetFields()
            this.dialog.visible = false
            this.dialog.data = {}
            if (vm.submitCallback) {
              vm.submitCallback(res.data)
            }
          }).catch(function (error) {
            vm.$message.error(error.statusText)
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    parentIdRemoteMethod (query) {
      if (query !== '') {
        this.dialog.loading = true
        setTimeout(() => {
          this.dialog.loading = false
          this.dialog.parentIdCurrentData = this.dialog.parentIdData.filter(item => {
            return (item.name.toLowerCase())
              .indexOf(query.toLowerCase()) > -1
          })
        }, 200)
      } else {
        this.dialog.parentIdCurrentData = this.dialog.parentIdData
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

</style>

<style lang="scss">
.sys-menu-dialog {
}
</style>