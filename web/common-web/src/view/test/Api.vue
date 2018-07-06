<template>
  <div class="test-api">
    <div class="toolbar">
      <el-button type="primary" style="background-color:rgba(0,0,0,0);border:0px;color:#000;" size="small" >{{nickname}}</el-button>
      <el-button type="primary" size="small" @click="dialog.visible = true">登 录</el-button>
      <el-button type="primary" size="small" @click="dialogVisible = true">导 入</el-button>
      <el-button type="primary" size="small" style="margin-right:10px;" v-popover:popoverExport  @click="popover.visible=!popover.visible">导 出</el-button>
      <el-popover
            ref="popoverExport"
            placement="bottom-end"
            width="250"
            trigger="click"
            v-model="popover.visible">
             <el-form ref="exportForm" :rules="popover.rules" :model="popover" label-width="80px">
            <el-form-item label="文件名称" prop='name'>
              <el-input  v-model="popover.name" clearable size="mini"></el-input>
            </el-form-item>
          </el-form>
              <el-button type="primary" style="float:right" size="small" @click="submitExport('exportForm')">确 定</el-button>
      </el-popover>
      <el-button type="primary" size="small" @click="submitAll">全部提交</el-button>
      <el-button type="primary" size="small" @click="addRowClick">新 增</el-button>
    </div>
    <div class="box-list">
      <div class="box" v-for="(item, index) in data" :key="index" ref="box">
        <div class="box-row">
          <i class="el-icon-caret-bottom" style="width: 20px;float:left;line-height:28px;test-align:center" v-if="item.show" @click="conceal(index)" ></i>
          <i class="el-icon-caret-right" style="width: 20px;float:left;line-height:28px;test-align:center" v-else @click="show(index)"></i>
          <el-select class="box-row-input" style="width: 150px;float:left;" v-model="item.type" size="mini">
            <el-option v-for="(value, key) in type" :key="key" :label="value" :value="value"></el-option>
          </el-select>
          <el-input class="box-row-input" style="width: calc((100% - 170px) / 2);float:left;" placeholder="例如: 192.168.16.25:8000"
            v-model="item.baseURL" size="mini"></el-input>
          <el-input class="box-row-input" style="width: calc((100% - 170px) / 2);float:left;" placeholder="例如: /sys/dict 或 /sys/dict/{dict_type}"
            v-model="item.url" size="mini"></el-input>
          <div style="clear:both"></div>
        </div>
        <div v-if="item.show">
          <div class="box-request">
            <div class="box-row">
              <div class="box-row-title">Request Headers</div>
              <el-input class="box-row-input" rows="3" placeholder='例如: {"X-Requested-With": "XMLHttpRequest"}' type="textarea" v-model="item.headers"
                size="mini" @blur="format(index, 'headers')"></el-input>
              <div style="clear:both"></div>
            </div>
            <div class="box-row">
              <div class="box-row-title">Request Params</div>
              <el-input class="box-row-input" type="textarea" rows="3" v-model="item.params" size="mini" @blur="format(index, 'params')"></el-input>
              <div style="clear:both"></div>
            </div>
            <div class="box-row" v-if="item.type === 'POST' ||item.type ==='PUT'">
              <div class="box-row-title">Request Data</div>
              <el-input class="box-row-input" type="textarea" rows="3" v-model="item.data" size="mini" @blur="format(index, 'data')"></el-input>
              <div style="clear:both"></div>
            </div>
          </div>
          <div class="box-response" v-if="item.statusText">
            <div class="box-row">
              <div class="box-row-title">Response Body</div>
              <el-input class="box-row-input" type="textarea" rows="3" v-model="item.body" size="mini"></el-input>
              <div style="clear:both"></div>
            </div>
            <div class="box-row">
              <div class="box-row-title">Response Text</div>
              <el-input class="box-row-input" v-model="item.statusText" size="mini"></el-input>
              <div style="clear:both"></div>
            </div>
            <div class="box-row">
              <div class="box-row-title">Response Code</div>
              <el-input class="box-row-input" v-model="item.code" size="mini"></el-input>
              <div style="clear:both"></div>
            </div>
          </div>
          <div class="box-row">
            <div class="box-row-title">Remarks</div>
            <el-input class="box-row-input" v-model="item.remarks" size="mini"></el-input>
            <div style="clear:both"></div>
          </div>
          <div slot="footer" style="float:right">
            <el-button type="primary" size="small" @click="submit(index)">提 交</el-button>
            <el-button type="primary" size="small" @click="reset(index)">重 置</el-button>
            <el-button type="primary" size="small" @click="deleteRowClick(index)">删 除</el-button>
          </div>
          <div style="clear:both"></div>
        </div>
      </div>
    </div>
    <el-dialog title="上传文件" :visible.sync="dialogVisible" width="380px">
      <el-upload action="" :multiple="false" :show-file-list="false" :auto-upload="false" class="upload-demo" drag :on-change="uploadChange">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将csv文件拖到此处，或
          <em>点击上传</em>
        </div>
      </el-upload>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关 闭</el-button>
      </span>
    </el-dialog>
    <el-dialog title="登录" :visible.sync="dialog.visible" width="450px" style="text-align: center">
      <el-form :model="dialog.data" status-icon :rules="dialog.rules" ref="ruleForm" label-width="70px" class="demo-ruleForm">
        <el-form-item label="账号" prop="username">
          <el-input type="text" v-model="dialog.data.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="dialog.data.password" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')" size="small">取 消</el-button>
        <el-button type="primary" size="small" @click="submitForm('ruleForm')">登录</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fileExport, fileConvertToJson } from '../../assets/common/uploadCommon'
export default {
  name: 'test-api',
  created () {
    this.format(0, 'params')
    this.format(0, 'headers')
    this.format(0, 'data')
  },
  computed: {
  },
  data () {
    return {
      data: [
        {
          baseURL: 'http://192.168.16.25:8000',
          headers: '{"X-Requested-With": "XMLHttpRequest","Access-Control-Allow-Headers" : "*","Access-Control-Allow-Origin" : "*","Content-Type" : "application/json"}',
          url: '/sys/dict/{dict_type}',
          type: 'GET',
          params: '{"dict_type": "reg_hold_is_market_right"}',
          data: '',
          statusText: '',
          body: '',
          code: '',
          remarks: '',
          show: true
        }
      ],
      type: ['GET', 'POST', 'PUT', 'DELETE'],
      dialogVisible: false,
      nickname: '',
      dialog: {
        visible: false,
        data: {},
        rules: {
          username: [
            { required: true, message: '请输入账号', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ]
        }
      },
      popover: {
        visible: false,
        name: '',
        rules: {
          name: [
            { required: true, message: '请输入文件名称', trigger: 'blur' }
          ]
        }
      }
    }
  },
  mounted () {
    if (window.localStorage.getItem('username')) {
      this.$store.state.http.auto('auth', 'info', { params: { 'username': window.localStorage.getItem('username') } }).then((res) => {
        this.nickname = res.data.user.nickname
      })
    }
  },
  methods: {
    show (index) {
      this.data[index].show = true
    },
    conceal (index) {
      this.data[index].show = false
    },
    format (index, name) {
      if (this.data[index][name].trim()) {
        this.data[index][name] = JSON.stringify(JSON.parse(this.data[index][name]), null, 4)
      }
    },
    reset (index) {
      for (let key in this.data[index]) {
        this.data[index][key] = ''
      }
      this.data[index].baseURL = 'http://192.168.16.25:8000'
      this.data[index].headers = '{"X-Requested-With": "XMLHttpRequest","Access-Control-Allow-Headers" : "*","Access-Control-Allow-Origin" : "*","Content-Type" : "application/json"}'
      this.data[index].type = 'GET'
      this.data[index].show = true
      this.format(index, 'headers')
      this.$refs.box[index].className = 'box box-success'
    },
    addRowClick () {
      this.data.push({
        baseURL: 'http://192.168.16.25:8000',
        headers: '{"X-Requested-With": "XMLHttpRequest","Access-Control-Allow-Headers" : "*","Access-Control-Allow-Origin" : "*","Content-Type" : "application/json"}',
        url: '',
        type: 'GET',
        params: '',
        data: '',
        statusText: '',
        body: '',
        code: '',
        remarks: '',
        show: true
      })
      this.format(this.data.length - 1, 'headers')
    },
    deleteRowClick (index) {
      if (this.data.length > 1) {
        let next
        if (this.$refs.box[index + 1]) {
          next = this.$refs.box[index + 1].className
        }
        this.data.splice(index, 1)
        if (this.$refs.box[index]) {
          this.$refs.box[index].className = next
        }
      } else {
        this.$message.warning('至少要剩余一个')
      }
    },
    fileExport: fileExport,
    submitExport (form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.fileExport(this.data, this.popover.name, 'xlsx')
          this.popover.visible = false
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    uploadChange (rawFile, fileList) {
      let vm = this
      const raw = rawFile.raw
      let fileNameSplit = (raw.name + '').split('.')
      if (fileNameSplit[(fileNameSplit.length - 1)].toLocaleUpperCase()) {
        fileConvertToJson(raw).then(res => {
          for (let item of res) {
            if (item.show === 'FALSE') {
              item.show = false
            } else if (!item.show || item.show === 'TRUE') {
              item.show = true
            }
          }
          vm.data = res
          vm.$message.success('上传成功!!!')
          vm.dialogVisible = false
        })
      } else {
        this.$message.warning('只可以上传csv文件！！！')
      }
    },
    cancelForm (form) {
      this.dialog.visible = false
      this.$refs[form].resetFields()
    },
    submitForm (from) {
      let vm = this
      this.$refs[from].validate((valid) => {
        if (valid) {
          this.dialog.data.client_id = 'sdc'
          this.dialog.data.client_secret = 'sdc'
          this.dialog.data.grant_type = 'password'
          this.$store.state.http.auto('auth', 'login', { params: this.dialog.data }).then((res) => {
            window.localStorage.setItem('username', this.dialog.data.username)
            window.localStorage.setItem('token', res.token_type + ' ' + res.access_token)
            this.$store.state.http.auto('auth', 'info', { params: { 'username': window.localStorage.getItem('username') } }).then((res1) => {
              this.nickname = res1.data.user.nickname
              this.dialog.visible = false
              this.$refs[from].resetFields()
              this.$message.success('登录成功')
            }).catch(function (error) {
              vm.$message.error(error.statusText)
              console.log(error)
            })
          }).catch(function (error) {
            vm.$message.error(error.statusText)
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
      return false
    },
    submit (index) {
      let vm = this
      let data = this.data[index]
      let type = data.type.toLocaleLowerCase()
      let headers, params, childData
      let arr = []
      data.body = ''
      data.statusText = ''
      data.remarks = ''
      data.code = ''
      if (data.params.trim()) {
        params = JSON.parse(`${data.params}`)
      }
      if (data.headers.trim()) {
        headers = JSON.parse(`${data.headers}`)
      }
      if (data.data.trim()) {
        childData = JSON.parse(`${data.data}`)
      }
      if (!data.baseURL.trim()) {
        this.$message.error('提交失败')
        this.$refs.box[index].className = 'box box-error'
        return
      }
      arr = [data.url, { params: params, data: childData, headers: headers, baseURL: data.baseURL }]
      if (arr.length) {
        this.$store.state.http[type](...arr).then(res => {
          data.body = JSON.stringify(res.data, null, 4)
          data.statusText = res.statusText
          data.code = res.status
          this.$refs.box[index].className = 'box box-success'
          this.$message.success(res.statusText)
        }).catch(function (error) {
          vm.$message.error(error.statusText)
          data.statusText = error.statusText
          data.code = error.status
          vm.$refs.box[index].className = 'box box-error'
        })
      }
    },
    async submitAll () {
      let vm = this
      let [successSum, errorSum] = [0, 0]
      for (let [index, data] of this.data.entries()) {
        let type = data.type.toLocaleLowerCase()
        let headers, params, childData
        let arr = []
        data.body = ''
        data.statusText = ''
        data.remarks = ''
        data.code = ''
        data.show = true
        if (data.params.trim()) {
          params = JSON.parse(`${data.params}`)
        }
        if (data.headers.trim()) {
          headers = JSON.parse(`${data.headers}`)
        }
        if (data.data.trim()) {
          childData = JSON.parse(`${data.data}`)
        }
        if (!data.baseURL.trim()) {
          errorSum++
          this.$refs.box[index].className = 'box box-error'
          if (index === this.data.length - 1) {
            this.$message(`总共${this.data.length}条,成功${successSum}条,失败${errorSum}条,`)
          }
          continue
        }
        arr = [data.url, { params: params, data: childData, headers: headers, baseURL: data.baseURL }]
        if (arr.length) {
          await this.$store.state.http[type](...arr).then(res => {
            successSum++
            data.body = JSON.stringify(res.data, null, 4)
            data.statusText = res.statusText
            data.code = res.status
            this.$refs.box[index].className = 'box box-success'
            if (index === this.data.length - 1) {
              this.$message(`总共${this.data.length}条,成功${successSum}条,失败${errorSum}条,`)
            }
          }).catch(function (error) {
            errorSum++
            if (index === vm.data.length - 1) {
              vm.$message(`总共${vm.data.length}条,成功${successSum}条,失败${errorSum}条,`)
            }
            data.statusText = error.statusText
            data.code = error.status
            vm.$refs.box[index].className = 'box box-error'
          })
        }
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.test-api {
  width: 100%;
  // padding: 10px;
  box-sizing: border-box;
  .toolbar {
    position: fixed;
    top: 0px;
    right: 0px;
    width: 100%;
    text-align: right;
    padding: 10px 20px 10px 0;
    z-index: 50;
    box-sizing: border-box;
    background-color: rgba(0, 0, 0, 0.1);
  }
  .box-list {
    width: 750px;
    margin: 52px auto 0;
    padding: 15px 0;
    box-sizing: border-box;
    background-color: #ebf7f0;
    border: 1px solid #c3e8d1;
    .box-float {
      float: left;
      width: 50%;
      min-width: 560px;
    }
    .box {
      padding: 10px 5px;
      width: 600px;
      margin: 0 auto 20px;
      border: 1px solid #c0c4cc;
      box-sizing: border-box;
      border-radius: 5px;
      background-color: #fff;
      .box-response {
        padding: 10px 0;
        width: 100%;
        box-sizing: border-box;
      }
      .box-row {
        width: 100%;
        margin-bottom: 10px;
        .box-row-title {
          width: 140px;
          float: left;
          line-height: 28px;
          margin-right: 10px;
        }
        .box-row-input {
          float: left;
          width: calc(100% - 150px);
        }
      }
    }
    .box-success {
      border: 1px solid #c0c4cc;
    }
    .box-error {
      border: 1px solid rgb(241, 59, 4);
    }
  }
  .el-dialog {
    padding: 10px;
    .el-dialog__header {
      text-align: center;
    }
    .el-dialog__body {
      padding: 5px 10px 0px 10px;
    }
  }
}
</style>
