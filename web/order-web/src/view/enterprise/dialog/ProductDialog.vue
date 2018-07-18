<template>
  <div class="product-dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="380px" @open="dialogOpen" :before-close="dialogClose">
      <el-form label-width="120px" :model="dialog.data"  :class="dialog.type === 'get'?'form-get':''" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="名称" prop='name'>
          <el-input v-model="dialog.data.name" :disabled="dialog.type === 'get'" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="规格" prop='scale'>
          <el-input v-model="dialog.data.scale" :disabled="dialog.type === 'get'" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="排序" prop='product_index'>
          <el-input v-model="dialog.data.product_index" :disabled="dialog.type === 'get'" clearable size="mini"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')" size="small">取 消</el-button>
        <el-button v-if="dialog.type === 'post' || dialog.type === 'put'" type="primary" @click="submitForm('ruleForm')" size="small">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { dialogClose } from 'gdotc@common/assets/common/common'
export default {
  name: 'productDialog',
  created () {
  },
  computed: {
  },
  props: {
    dialog: {
      type: Object,
      default: {
        title: ``,
        visible: false,
        type: '',
        data: {},
        loading: true
      }
    },
    submitCallback: {
      type: Function
    }
  },
  data () {
    return {
      custCurrentData: []
    }
  },
  mounted () {
    this.dialog.rules = {
    }
  },
  methods: {
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    dialogOpen () {
      this.dialog.data = {
        product_name: '',
        delivery_line: '',
        phone: ''
      }
      switch (this.dialog.type) {
        case 'post':
          this.dialog.title = '新增产品'
          break
        case 'put':
          Object.assign(this.dialog.data, this.dialog.currentRow)
          this.dialog.title = '修改产品'
          break
        case 'get':
          this.dialog.data = this.dialog.currentRow
          this.dialog.title = '查看产品'
          break
      }
    },
    submitForm (form) {
      let vm = this
      vm.$refs[form].validate((valid) => {
        if (valid) {
          let successMessage = null
          let errorMessage = null
          let requestMethod = null
          if (vm.dialog.type === 'post') {
            successMessage = '产品新增成功！'
            errorMessage = '产品新增失败！'
            requestMethod = 'add'
          }
          if (vm.dialog.type === 'put') {
            successMessage = '产品修改成功！'
            errorMessage = '产品修改失败！'
            requestMethod = 'update'
          }
          this.$store.state.http.auto('product', requestMethod, { data: this.dialog.data }).then((res) => {
            this.$message.success(successMessage)
            this.$refs[form].resetFields()
            this.dialog.visible = false
            this.dialog.type = null
            this.$message.success(successMessage)
            if (this.submitCallback) {
              this.submitCallback(res)
            }
            this.dialog.data = {}
          }).catch(function (error) {
            vm.$message.error(errorMessage)
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

