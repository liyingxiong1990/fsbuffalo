<template>
  <div class="store-dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="500px" @open="dialogOpen" :before-close="dialogClose">
      <el-form label-width="120px" :model="dialog.data" :class="dialog.type === 'get'?'form-get':''" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="店名" prop='store_name'>
          <el-input v-model="dialog.data.store_name" :disabled="dialog.type === 'get'" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="店主" prop='store_holder'>
          <el-input v-model="dialog.data.store_holder" :disabled="dialog.type === 'get'" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop='address'>
          <el-input v-model="dialog.data.address" :disabled="dialog.type === 'get'" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop='phone'>
          <el-input v-model="dialog.data.phone" :disabled="dialog.type === 'get'" clearable size="mini"></el-input>
        </el-form-item>

        <el-form-item label="路线" prop='line_id'>
          <el-select style="width: 100%" v-model="dialog.data.line_id"  size="mini"  :disabled="dialog.type !== 'post'" clearable>
            <el-option v-for="item in lineList" :key="item.id" :label="item.name" :value="item.id">
              <span style="float: left">{{ item.name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.id }}</span>
            </el-option>
          </el-select>
        </el-form-item>

        <div class="dialog-cust-from">
          <div class="dialog-cust-from-row">
            <div class="dialog-cust-from">
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>产品</p>
                </div>
              </div>
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>单价</p>
                </div>
              </div>

              <div style="clear: both;"></div>
            </div>
          </div>
          <div v-for="item of this.dialog.data.priceList" :key="item.id" class="dialog-cust-from-row">
            <div class="dialog-cust-from">
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.product_name}}</p>
                </div>
              </div>
              <div v-if="dialog.type === 'post' || dialog.type === 'put'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <el-input type="number" style="width: 100%; resize:none;" v-model="item.unit_price"></el-input>
                </div>
              </div>
              <div v-if="dialog.type === 'get'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.unit_price}}</p>
                </div>
              </div>

              <div style="clear: both;"></div>
            </div>
          </div>
        </div>

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
  name: 'storeDialog',
  created () {
    this.getProductList()
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
      custCurrentData: [],
      productList: [],
      lineList: []
    }
  },
  mounted () {
    this.dialog.rules = {
    }
  },
  methods: {
    getProductList () {
      this.$store.state.http.auto('product', 'getProductList').then((res) => {
        this.productList = res.data
      })
    },
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    lineRemoteMethod () {
      this.dialog.loading = true
      let vm = this
      vm.$store.state.http.auto('line', 'getLineList').then(res => {
        this.lineList = res.data
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    dialogOpen () {
      this.lineRemoteMethod()
      this.dialog.data = {
        store_name: '',
        delivery_line: '',
        phone: '',
        line_id: ''
      }
      switch (this.dialog.type) {
        case 'post':
          this.dialog.data = {}
          this.dialog.title = '新增专卖店'
          this.dialog.data.priceList = []
          for (var i = 0; i < this.productList.length; i++) {
            this.dialog.data.priceList.push({
              product_id: this.productList[i].id,
              product_name: this.productList[i].name
            })
          }
          break
        case 'put':
          this.dialog.data = {}
          Object.assign(this.dialog.data, this.dialog.currentRow)
          this.dialog.title = '修改专卖店'
          break
        case 'get':
          this.dialog.data = {}
          this.dialog.data = this.dialog.currentRow
          this.dialog.title = '查看专卖店'
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
            successMessage = '专卖店新增成功！'
            errorMessage = '专卖店新增失败！'
            requestMethod = 'add'
          }
          if (vm.dialog.type === 'put') {
            successMessage = '专卖店修改成功！'
            errorMessage = '专卖店修改失败！'
            requestMethod = 'update'
          }
          this.$store.state.http.auto('store', requestMethod, { data: this.dialog.data }).then((res) => {
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
<style lang="scss" scoped>
.dialog-cust-from {
  border: 1px #dcdee6 solid;
  border-radius: 4px;
  padding: 9px 9px 9px 4px;
  margin-bottom: 3px;
  .dialog-cust-from-row {
    .dialog-cust-from-row-column {
      width: calc(100% / 3 - 5px);
      float: left;
      padding-left: 5px;
    }
  }
}
</style>
