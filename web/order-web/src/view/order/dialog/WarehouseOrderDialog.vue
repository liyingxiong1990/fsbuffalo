<template>
  <div class="warehouseOrder-dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="1000px" @open="dialogOpen" :before-close="dialogClose">
      <el-form label-width="120px" :model="dialog.data" :class="dialog.type === 'get'?'form-get':''" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="开单日期" prop='order_date'>
          <el-date-picker @change="selectOrderDate" v-model="dialog.data.order_date" :disabled="dialog.type === 'get'" clearable size="mini" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>

        <el-form-item label="送货日期" prop='delivery_date'>
          <el-date-picker v-model="dialog.data.delivery_date" :disabled="dialog.type === 'get'" clearable size="mini" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>

        <el-form-item v-if="dialog.type === 'get'" label="司机">{{dialog.data.deliverer}}</el-form-item>
        <el-form-item v-if="dialog.type === 'get'" label="开单人">{{dialog.data.out_order_recorder}}</el-form-item>

        <el-form-item v-if="dialog.type === 'post_deliver' || dialog.type === 'post_driver'" label="司机" prop='deliverer_id'>
          <el-select v-model="dialog.data.deliverer_id" placeholder="请选择" size="mini" :disabled="dialog.type === 'get'">
            <el-option v-for="(item, index) in this.delivererList" :key="index" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item v-if="dialog.type === 'post_driver'" label="路线" prop='line_id'>
          <el-select v-model="dialog.data.line_id" placeholder="请选择" size="mini" :disabled="dialog.type === 'get'" @change="chooseLine">
            <el-option v-for="item in lineList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item v-if="dialog.type === 'post_deliver' || dialog.type === 'post_driver'" label="开单人" prop='order_recorder_id'>
          <el-select v-model="dialog.data.order_recorder_id" placeholder="请选择" size="mini" :disabled="dialog.type === 'get'">
            <el-option v-for="(item, index) in this.outOrderRecorderList" :key="index" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

        <div v-if="dialog.type === 'post_deliver' || dialog.type === 'get'" class="dialog-cust-from">
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
                  <p>数量</p>
                </div>
              </div>
              <div v-if="dialog.type === 'get'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>箱数</p>
                </div>
              </div>
              <div v-if="dialog.type === 'get'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>零头</p>
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
              <div v-if="dialog.type === 'get' || dialog.type === 'post_driver'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.quantity}}</p>
                </div>
              </div>
              <div v-if="dialog.type === 'post_deliver'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <el-input type="number" style="width: 100%; resize:none;" v-model="item.quantity" @blur="handleBlur(item)"></el-input>
                </div>
              </div>
              <div v-if="dialog.type === 'get'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.number_of_boxes}}</p>
                </div>
              </div>
              <div v-if="dialog.type === 'get'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.remainder}}</p>
                </div>
              </div>

              <div style="clear: both;"></div>
            </div>
          </div>
        </div>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')" size="small">取 消</el-button>
        <el-button v-if="dialog.type === 'post_deliver' || dialog.type === 'post_driver'" type="primary" @click="submitForm('ruleForm')" size="small">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { dialogClose } from 'gdotc@common/assets/common/common'
export default {
  name: 'warehouseOrderDialog',
  created () {
    this.getProductList()
    this.getOutOrderRecorderList()
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
      productList: [],
      outOrderRecorderList: [],
      delivererList: [],
      lineList: [],
      inventory: null
    }
  },
  mounted () {
    this.dialog.rules = {
      order_date: [
        { required: true, message: '请选择出单日期', trigger: 'change' }
      ],
      // line_id: [
      //   { required: true, message: '请选择路线', trigger: 'change' }
      // ],
      order_recorder_id: [
        { required: true, message: '请选择开单人', trigger: 'change' }
      ],
      deliverer_id: [
        { required: true, message: '请选择司机', trigger: 'change' }
      ]
    }
  },
  methods: {
    selectOrderDate (val) {
      var vm = this
      this.$store.state.http.auto('inventory', 'getByDate', { data: { inventory_date: val } }).then((res) => {
        if (res.data) {
          var tempInventory = {}
          for (var i = 0; i < res.data.itemList.length; i++) {
            tempInventory[res.data.itemList[i].product_id] = res.data.itemList[i].quantity
          }
          vm.inventory = tempInventory
        } else {
          alert('该日期没有库存呢记录!')
        }
      })
    },
    handleBlur (item) {
      if (!Number.isInteger(Number(item.quantity))) {
        alert('请输入整数')
      }
      var vm = this
      if (vm.inventory[item.product_id] < item.quantity) {
        alert(item.product_name + '库存不足！')
      }
    },
    getProductList () {
      this.$store.state.http.auto('product', 'getProductList').then((res) => {
        this.productList = res.data
      })
    },
    getOutOrderRecorderList () {
      this.$store.state.http.auto('outOrderRecorder', 'getOutOrderRecorderList').then((res) => {
        this.outOrderRecorderList = res.data
      })
    },
    getDelivererList () {
      this.delivererList = []
      if (this.dialog.data.type === 'deliver') {
        this.$store.state.http.auto('deliverer', 'getDelivererList').then((res) => {
          this.delivererList = res.data
        })
      } else if (this.dialog.data.type === 'driver') {
        this.$store.state.http.auto('driver', 'getDriverList').then((res) => {
          this.delivererList = res.data
        })
      }
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
    chooseLine (val) {
      // this.dialog.loading = true
      // let vm = this
      // vm.dialog.data.driver_id = vm.dialog.data.deliverer_id
      // vm.$store.state.http.auto('delivererOrder', 'getByDateDriverLine', { data: vm.dialog.data }).then(res => {
      //   var tempData = this.dialog.data
      //   res.data.order_date = tempData.order_date
      //   res.data.deliverer_id = tempData.deliverer_id
      //   res.data.delivery_date = tempData.delivery_date
      //   res.data.order_recorder_id = tempData.order_recorder_id
      //   res.data.type = tempData.type
      //   Object.assign(vm.dialog.data, res.data)
      //   vm.dialog.data.line_id = val
      // }).catch(error => {
      //   vm.$message.error(error.statusText)
      //   vm.dialog.loading = false
      //   console.log(error)
      // })
    },
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    dialogOpen () {
      this.dialog.data = {
        id: '',
        order_date: '',
        deliverer_id: '',
        delivery_date: '',
        order_recorder_id: '',
        type: '',
        line_id: '',
        deliverer: '',
        out_order_recorder: '',
        line_name: ''
      }
      switch (this.dialog.type) {
        case 'post_deliver':
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
          this.dialog.title = '新增外县市出仓单'
          this.dialog.data.type = 'deliver'
          this.getDelivererList()
          break
        case 'post_driver':
          this.dialog.data = {}
          this.dialog.title = '新增专卖店出仓单'
          this.dialog.data.type = 'driver'
          this.getDelivererList()
          this.lineRemoteMethod()
          break
        case 'get':
          this.warehouseRemoteMethod(this.dialog.currentRow.id)
          this.dialog.title = '查看出仓单'
          break
      }
    },
    warehouseRemoteMethod (query) {
      this.$store.state.http
        .auto('warehouseOrder', 'getById', {
          params: { id: query }
        })
        .then(res => {
          if (res && res.data) {
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
          if (vm.dialog.type === 'post_deliver') {
            successMessage = '外县市出仓单新增成功！'
            requestMethod = 'add'
          }
          if (vm.dialog.type === 'post_driver') {
            successMessage = '专卖店出仓单新增成功！'
            requestMethod = 'add'
          }
          this.$store.state.http.auto('warehouseOrder', requestMethod, { data: this.dialog.data }).then((res) => {
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
      width: calc(100% / 5 - 5px);
      float: left;
      padding-left: 5px;
    }
  }
}
</style>
