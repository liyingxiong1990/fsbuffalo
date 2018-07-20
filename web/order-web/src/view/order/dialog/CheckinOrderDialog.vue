<template>
  <div class="checkinOrder-dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="600px" @open="dialogOpen" :before-close="dialogClose">
      <el-form label-width="120px" :model="dialog.data" :class="dialog.type === 'get'?'form-get':''" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <el-form-item label="进仓日期" prop='checkin_date'>
          <el-date-picker @change="getSTime" v-model="dialog.data.checkin_date" :disabled="dialog.type === 'get'" clearable size="mini" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>

        <el-form-item v-if="this.dialog.type != 'statistic'" label="仓管" prop='in_order_recorder_id'>
          <el-select v-model="dialog.data.in_order_recorder_id" placeholder="请选择" size="mini" :disabled="dialog.type === 'get'">
            <el-option v-for="(item, index) in this.inOrderRecorderList" :key="index" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item v-if="this.dialog.type != 'statistic'" label="缴仓" prop='carrier_id'>
          <el-select v-model="dialog.data.carrier_id" placeholder="请选择" size="mini" :disabled="dialog.type === 'get'">
            <el-option v-for="(item, index) in this.carrierList" :key="index" :label="item.name" :value="item.id"></el-option>
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
              <div v-if="dialog.type === 'get' || dialog.type === 'statistic'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <p>{{item.quantity}}</p>
                </div>
              </div>
              <div v-if="dialog.type === 'post'" class="dialog-cust-from-row-column">
                <div classs="dialog-cust-from-row-column-context">
                  <el-input type="number" style="width: 100%; resize:none;" v-model="item.quantity" @blur="handleBlur(item)"></el-input>
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
        <el-button v-if="dialog.type === 'post'" type="primary" @click="submitForm('ruleForm')" size="small">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { dialogClose } from 'gdotc@common/assets/common/common'
export default {
  name: 'checkinOrderDialog',
  created () {
    this.getProductList()
    this.getInOrderRecorderList()
    this.getCarrierList()
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
      inOrderRecorderList: [],
      carrierList: []
    }
  },
  mounted () {
    this.dialog.rules = {
      checkin_date: [
        { required: true, message: '请选择进仓日期', trigger: 'change' }
      ],
      in_order_recorder_id: [
        { required: true, message: '请选择仓管', trigger: 'change' }
      ],
      carrier_id: [
        { required: true, message: '请选择缴仓', trigger: 'change' }
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
      window.open(`${urlPath}/enterprise.html#/${this.dialog.data.id}/checkin_order`)
    },
    getProductList () {
      this.$store.state.http.auto('product', 'getProductList').then((res) => {
        this.productList = res.data
      })
    },
    getInOrderRecorderList () {
      this.$store.state.http.auto('inOrderRecorder', 'getInOrderRecorderList').then((res) => {
        this.inOrderRecorderList = res.data
      })
    },
    getCarrierList () {
      this.$store.state.http.auto('carrier', 'getCarrierList').then((res) => {
        this.carrierList = res.data
      })
    },
    getSTime (val) {
      var vm = this
      this.$store.state.http.auto('checkinOrder', 'statistic', { data: { checkin_date: val } }).then((res) => {
        if (this.dialog.type === 'statistic') {
          if (res.data.itemList) {
            vm.dialog.data.itemList = []
            Object.assign(vm.dialog.data, res.data)
          }
        }
      })
    },
    handleBlur (item) {
      if (!Number.isInteger(Number(item.quantity))) {
        alert('请输入整数')
      }
    },
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    dialogOpen () {
      this.dialog.data = {
        checkinOrder_name: '',
        delivery_line: '',
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
          this.dialog.title = '新增进仓单'
          break
        case 'statistic':
          this.dialog.title = '按日期统计'
          break
        case 'get':
          this.dialog.data = {}
          this.dialog.data = this.dialog.currentRow
          this.dialog.title = '查看进仓单'
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
            successMessage = '进仓单新增成功！'
            requestMethod = 'add'
          }
          if (vm.dialog.type === 'post_blank') {
            successMessage = '空白进仓单新增成功！'
            requestMethod = 'add_blank'
          }
          if (vm.dialog.type === 'put') {
            successMessage = '进仓单修改成功！'
            requestMethod = 'update'
          }
          this.$store.state.http.auto('checkinOrder', requestMethod, { data: this.dialog.data }).then((res) => {
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
