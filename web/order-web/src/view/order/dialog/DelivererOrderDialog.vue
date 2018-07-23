<template>
  <div class="delivererOrder-dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="600px" @open="dialogOpen" :before-close="dialogClose">
      <el-form label-width="120px" :model="dialog.data" :class="dialog.type === 'get'?'form-get':''" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="开单日期" prop='order_date'>
          <el-date-picker @change="selectOrderDate" v-model="dialog.data.order_date" :disabled="dialog.type === 'get'" clearable size="mini" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>

        <el-form-item label="送货日期" prop='delivery_date'>
          <el-date-picker v-model="dialog.data.delivery_date" :disabled="dialog.type === 'get'" clearable size="mini" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>

        <el-form-item label="路线" prop='line_id'>
          <el-select v-model="dialog.data.line_id" placeholder="请选择" size="mini" :disabled="dialog.type !== 'post'" @change="chooseLine">
            <el-option v-for="item in lineList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="专卖店" prop='store_id'>
          <el-select v-model="dialog.data.store_id" placeholder="请选择" size="mini" :disabled="dialog.type !== 'post'">
            <el-option v-for="item in storeList" :key="item.id" :label="item.store_name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="司机" prop='driver_id'>
          <el-select v-model="dialog.data.driver_id" placeholder="请选择" size="mini" :disabled="dialog.type === 'get'">
            <el-option v-for="(item, index) in this.driverList" :key="index" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="开单人" prop='order_recorder_id'>
          <el-select v-model="dialog.data.order_recorder_id" placeholder="请选择" size="mini" :disabled="dialog.type !== 'post'">
            <el-option v-for="(item, index) in this.outOrderRecorderList" :key="index" :label="item.name" :value="item.id"></el-option>
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
                  <p>规格</p>
                </div>
              </div>
              <div class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>数量</p>
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
              <div v-if="dialog.type === 'post' || dialog.type === 'put'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <el-input type="number" style="width: 100%; resize:none;" v-model="item.quantity" @blur="handleBlur(item)" :disabled="dialog.data.is_out === 1"></el-input>
                </div>
              </div>
              <div style="clear: both;"></div>
            </div>
          </div>
        </div>
      </el-form>
      <div slot="footer">
        <el-button v-if="dialog.type === 'get'" @click="printFrom()" size="small">打印</el-button>
        <el-button @click="cancelForm('ruleForm')" size="small">取 消</el-button>
        <el-button v-if="dialog.type === 'post' || dialog.type === 'put'" type="primary" @click="submitForm('ruleForm')" size="small">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { dialogClose } from 'gdotc@common/assets/common/common'
export default {
  name: 'delivererOrderDialog',
  created () {
    this.getProductList()
    this.getOutOrderRecorderList()
    this.getDriverList()
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
      driverList: [],
      lineList: [],
      storeList: [],
      inventory: null
    }
  },
  mounted () {
    this.dialog.rules = {
      order_date: [
        { required: true, message: '请选择出单日期', trigger: 'change' }
      ],
      line_id: [
        { required: true, message: '请选择路线', trigger: 'change' }
      ],
      store_id: [
        { required: true, message: '请选择专卖店', trigger: 'change' }
      ],
      driver_id: [
        { required: true, message: '请选择司机', trigger: 'change' }
      ],
      order_recorder_id: [
        { required: true, message: '请选择开单人', trigger: 'change' }
      ]
    }
  },
  methods: {
    printFrom () {
      let urlPath = window.location.origin
      let paths = window.location.pathname.split('/')
      for (let pathName of paths) {
        if (pathName !== '' && pathName != null && pathName.indexOf('html') < 0) {
          urlPath += '/' + pathName
        }
      }
      window.open(`${urlPath}/enterprise.html#/${this.dialog.data.id}/deliverer_order`)
    },
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
      if (!Number.isInteger(Number(item.quantity)) || Number(item.quantity) < 0) {
        alert('请输入正整数')
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
    getDriverList () {
      this.$store.state.http.auto('driver', 'getDriverList').then((res) => {
        this.driverList = res.data
      })
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
      // this.dialog.data.store_id = ''
      this.dialog.loading = true
      let vm = this
      vm.$store.state.http.auto('store', 'getStoresByLine', { params: { line_id: val } }).then(res => {
        this.storeList = res.data
      }).catch(error => {
        vm.$message.error(error.statusText)
        vm.dialog.loading = false
        console.log(error)
      })
    },
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    dialogOpen () {
      this.lineRemoteMethod()
      this.dialog.data = {
        phone: ''
      }
      switch (this.dialog.type) {
        case 'post':
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
          this.dialog.title = '新增送货单'
          break
        case 'put':
          this.chooseLine(this.dialog.currentRow.line_id)
          this.dialog.data = {}
          this.dialog.data = this.dialog.currentRow
          this.dialog.title = '编辑送货单'
          this.selectOrderDate(this.dialog.data.order_date)
          break
        case 'get':
          this.chooseLine(this.dialog.currentRow.line_id)
          this.dialog.data = {}
          this.dialog.data = this.dialog.currentRow
          this.dialog.title = '查看送货单'
          break
      }
    },
    submitForm (form) {
      let vm = this
      vm.$refs[form].validate((valid) => {
        if (valid) {
          let successMessage = null
          let requestMethod = null
          if (vm.dialog.type === 'post') {
            successMessage = '送货单新增成功！'
            requestMethod = 'add'
          }
          if (vm.dialog.type === 'put') {
            successMessage = '送货单修改成功！'
            requestMethod = 'update'
          }
          this.$store.state.http.auto('delivererOrder', requestMethod, { data: this.dialog.data }).then((res) => {
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
