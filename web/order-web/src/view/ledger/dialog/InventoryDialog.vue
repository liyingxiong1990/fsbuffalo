<template>
  <div class="inventory-dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="600px" @open="dialogOpen" :before-close="dialogClose">
      <el-form label-width="120px" :model="dialog.data" :class="dialog.type === 'get'?'form-get':''" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="库存日期" prop='inventory_date'>
          <el-date-picker v-model="dialog.data.inventory_date" :disabled="dialog.type === 'get'" clearable size="mini" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="上一库存日期" prop='last_date'>
          <el-date-picker v-model="dialog.data.last_date" :disabled="dialog.type === 'get'" clearable size="mini" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>

        <div v-if="this.dialog.type ===  'get' || this.dialog.type ===  'post_blank'" class="dialog-cust-from">
          <div class="dialog-cust-from-row">
            <div class="dialog-cust-from">
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>产品</p>
                </div>
              </div>
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>规格</p>
                </div>
              </div>
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>库存量</p>
                </div>
              </div>

              <div style="clear: both;"></div>
            </div>
          </div>
          <div v-for="item of this.dialog.data.itemList" :key="item.id" class="dialog-cust-from-row">
            <div class="dialog-cust-from">
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.product_name}}</p>
                </div>
              </div>
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.product_scale}}</p>
                </div>
              </div>
              <div v-if="dialog.type === 'get'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.quantity}}</p>
                </div>
              </div>
              <div v-if="dialog.type === 'post_blank'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <el-input type="number" style="width: 100%; resize:none;" v-model="item.quantity"></el-input>
                </div>
              </div>
              <div style="clear: both;"></div>
            </div>
          </div>
        </div>

      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')" size="small">取 消</el-button>
        <el-button v-if="dialog.type === 'post' || dialog.type === 'post_blank'" type="primary" @click="submitForm('ruleForm')" size="small">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { dialogClose } from 'gdotc@common/assets/common/common'
export default {
  name: 'inventoryDialog',
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
      productList: []
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
    dialogOpen () {
      this.dialog.data = {
        inventory_name: '',
        delivery_line: '',
        phone: ''
      }
      switch (this.dialog.type) {
        case 'post':
          this.dialog.data = {}
          this.dialog.title = '新增库存记录'
          break
        case 'post_blank':
          this.dialog.data = {}
          this.dialog.data.itemList = []
          for (var i = 0; i < this.productList.length; i++) {
            this.dialog.data.itemList.push({
              product_id: this.productList[i].id,
              product_scale: this.productList[i].scale,
              product_name: this.productList[i].name,
              quantity: 0
            })
          }
          this.dialog.title = '新增空白库存记录'
          break
        case 'put':
          this.dialog.data = {}
          Object.assign(this.dialog.data, this.dialog.currentRow)
          this.dialog.title = '修改库存记录'
          break
        case 'get':
          this.dialog.data = {}
          this.inventoryRemoteMethod(this.dialog.currentRow.id)
          this.dialog.title = '查看库存记录'
          break
      }
    },
    inventoryRemoteMethod (query) {
      this.$store.state.http
        .auto('inventory', 'getById', {
          params: { id: query }
        })
        .then(res => {
          if (res && res.data) {
            this.dialog.data = {}
            Object.assign(this.dialog.data, res.data)
            this.dialog.data.id = query
            this.dialog.currentRow = res.data
            this.dialog.visible = true
          }
        })
        .catch(function (error) {
          this.$message.error('查询失败!')
          console.log(error)
        })
    },
    submitForm (form) {
      let vm = this
      vm.$refs[form].validate((valid) => {
        if (valid) {
          let successMessage = null
          let requestMethod = null
          if (vm.dialog.type === 'post') {
            successMessage = '库存记录新增成功！'
            requestMethod = 'add'
          }
          if (vm.dialog.type === 'post_blank') {
            successMessage = '空白库存记录新增成功！'
            requestMethod = 'add_blank'
          }
          if (vm.dialog.type === 'put') {
            successMessage = '库存记录修改成功！'
            requestMethod = 'update'
          }
          this.$store.state.http.auto('inventory', requestMethod, { data: this.dialog.data }).then((res) => {
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
            vm.$message.error(error.statusText)
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
