<template>
  <div class="base-dialog">
    <el-dialog v-if="dialog" :title="dialog.title" :visible.sync="dialog.visible" :width="dialog.width" :before-close="dialogClose">
      <el-form size="mini" :label-width="dialog.labelWidth" :model="dialog.data" :class="dialog.type === 'get'?'form-get':''" label-position="right" :rules="dialog.rules" ref="ruleForm">
        <div v-for="item in dialogCols" :key="item.prop" :class="item.size === 'half' && item.clearFloat ? 'clearfix':''">
          <el-form-item :class="!item.size || item.size === 'whole' ? `base-dialog-form-whole` : `base-dialog-form-half` " :label="item.label" :prop="item.prop">
            <el-input v-if="!item.type || item.type === 'text' || item.type==='number'" :type="item.type" v-model="dialog.data[item.prop]" :disabled="dialog.type === 'get'" clearable size="mini"></el-input>
            <el-select v-else-if="item.type==='select'" style="width: 100%" v-model="dialog.data[item.prop]" size="mini" :disabled="dialog.type === 'get'" clearable>
              <el-option v-for="(option, index, key) in item.option" v-if="index < 50" :key="key" :label="option.dict_value" :value="option.dict_code">
              </el-option>
            </el-select>
            <el-date-picker v-else-if="item.type==='date' || item.type==='datetime'" :type="item.type" v-model="dialog.data[item.prop]" style="width: 100%;" :disabled="dialog.type === 'get'" placeholder="选择日期" size="mini">
            </el-date-picker>
          </el-form-item>
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
import { dialogClose } from '../assets/common/common'
export default {
  name: 'BaseDialog', // 表单自动化-增删改查
  props: {
    /**
     * dialog
     * 表单
     * {object},
      default: {
        title: '',
        type: '',   // post、put、get
        visible: false,
        width: '600px',
        labelWidth: `100px`,
        apiModule: '',
        apiMethod: '',
        data: {},
        rules: {}
      }
    */
    dialog: {
      type: Object
    },
    /**
     * dialogCols
     * 表单输入框数据
     * [array]
     * e.g.
     * [
     *    { label: '股权名称', prop: 'equity_name', size: 'half', clearFloat: true, type: 'select', option: this.dictStatus }
     * ]
     * @param label 名称
     * @param prop 输入框绑定的值
     * @param size  名称加输入框的大小   whole---整行、half---半行  默认为whole
     * @param clearFloat boolean 是否清除浮动  size为half时起效 默认为false
     * @param type 输入框类型   text---文本、number---数字、select---选择框、date---日期选择器、datetime---带时间日期选择器  默认为text
     * @param option [array] 选择框选项 type值为select时有效 [lable:'是',value:'1']
    */
    dialogCols: {
      type: Array,
      required: true,
      default: []
    },
    submitCallback: {
      type: Function
    }
  },
  created () {
  },
  computed: {
    // dictStatus () {
    //   return this.$store.getters.sysDict('reg_status')
    // },
    // dialogCols () {
    //   return [
    //     { label: '股权代码', prop: 'equity_code', minwidth: '100px', size: 'half' },
    //     { label: '股权名称', prop: 'equity_name', minwidth: '120px', size: 'half', clearFloat: true, type: 'select', option: this.dictStatus },
    //     { label: '股权简称', prop: 'short_name', minwidth: '100px', type: 'date' },
    //     { label: '发行人', prop: 'issuer_cust_id', minwidth: '100px' },
    //     { label: '总股本', prop: 'contribute_capital', minwidth: '100px', formatter: this.formatterNumber },
    //     { label: '每股净资产', prop: 'asset_per_value', minwidth: '120px' },
    //     { label: '结算币种', prop: 'currency', formatter: this.formatterDictCurrency, minwidth: '100px' },
    //     { label: '状态', prop: 'equity_status', formatter: this.formatterDictStatus, minwidth: '80px' },
    //     { label: '委托下限', prop: 'entrustment_lower_limit', minwidth: '100px' },
    //     { label: '交易基数', prop: 'transaction_base', minwidth: '100px' },
    //     { label: '股权性质', prop: 'property', formatter: this.formatterEquityProperty, minwidth: '100px' },
    //     { label: '出资日期', prop: 'contribute_date', formatter: this.formatterTime, minwidth: '150px' },
    //     { label: '托管日期', prop: 'deposit_date', formatter: this.formatterTime, minwidth: '150px' },
    //     { label: '备注', prop: 'remarks', minwidth: '100px' },
    //     { label: '创建时间', prop: 'created_time', formatter: this.formatterTime, minwidth: '150px', display: false },
    //     { label: '修改时间', prop: 'modified_time', formatter: this.formatterTime, minwidth: '150px', display: false }
    //   ]
    // }
  },
  data () {
    return {
      // dialog: {
      //   title: '测试',
      //   type: 'put',
      //   visible: false,
      //   width: `600px`,
      //   labelWidth: `120px`,
      //   apiModule: 'bossInfoManagement',
      //   apiMethod: 'add',
      //   data: {
      //     equity_name: null,
      //     short_name: null
      //   },
      //   rules: {
      //     equity_code: [
      //       { required: true, message: '请输入客户编号', trigger: 'blur' }
      //     ]
      //   }
      // }
    }
  },
  methods: {
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    submitForm (form) {
      let vm = this
      vm.$refs[form].validate((valid) => {
        if (valid) {
          this.$store.state.http.auto(this.dialog.apiModule, this.dialog.apiMethod, { data: this.dialog.data }).then((res) => {
            this.$message.success(res.statusText)
            this.$refs[form].resetFields()
            this.dialog.visible = false
            this.dialog.data = {}
            if (this.submitCallback) {
              this.submitCallback()
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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>

<style lang="scss">
.base-dialog {
  .el-dialog__header {
    text-align: center;
  }
  .base-dialog-form-whole {
    width: 100%;
  }
  .base-dialog-form-half {
    width: 50%;
    float: left;
  }
  .clearfix {
    zoom: 1;
    &::after {
      content: ".";
      clear: both;
      display: block;
      overflow: hidden;
      font-size: 0;
      height: 0;
    }
  }
}
</style>