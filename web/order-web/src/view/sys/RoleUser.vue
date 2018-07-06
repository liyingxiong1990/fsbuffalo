<template>
  <div class="sys-role-user">
    <base-table :tableData="table.data"  :handRightBotton="table.handRightBotton" :rowContextdblClick="rowContextdblClick" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="700px" :before-close="dialogClose">
      <div class="transfer">
        <div class="transfer_left">
          <el-input placeholder="请输入昵称/登录名" prefix-icon="el-icon-search" style="margin-bottom: 10px;" @focus="transferSearchFocus('transferContentLeft')" v-model="searchValueLeft"></el-input>
          <div class="transfer_content" v-for="(item, index) in dialog.transferData" :key="item.key" ref="transferContentLeft" 
          @click="!$event.ctrlKey && transferClick(index, 'transferContentLeft')"
          @dblclick="transferDblclick(index, 'transferContentLeft')" 
          @click.ctrl="transferClickCtrl(index, 'transferContentLeft')"
          >
            <span class="transfer_content_left">{{item.label}}</span>
            <span class="transfer_content_right">{{item.loginName}}</span>
          </div>
        </div>
        <div class="transfer_centre">
          <div class="transfer_centre_div"></div>
          <div class="transfer_centre_div">
            <div :class="dialog.transferData.length ? 'global-background-color':'prohibit'" @click="transferBindAll('left')">
              <span>全部></span>
            </div>
          </div>
          <div class="transfer_centre_div">
            <div :class="dialog.transferValue.length ? 'global-background-color':'prohibit'" @click="transferBindAll('right')">
              <span><全部</span>
            </div>
          </div>
          <div class="transfer_centre_div">
            <div :class="dialog.transferIndexLeft.length ? 'global-background-color':'prohibit'" @click="transferBind">
              <span>绑定></span>
            </div>
          </div>
          <div class="transfer_centre_div">
            <div :class="dialog.transferIndexRight.length ? 'global-background-color':'prohibit'" @click="transferBind">
              <span><绑定</span>
            </div>
          </div>
          <div class="transfer_centre_div"></div>
        </div>
        <div class="transfer_right">
          <el-input placeholder="请输入昵称/登录名" prefix-icon="el-icon-search" style="margin-bottom: 10px;" @focus="transferSearchFocus('transferContentRight')" v-model="searchValueRight"></el-input>
          <div class="transfer_content" v-for="(item, index) in dialog.transferValue" :key="item.key"  ref="transferContentRight"
          @click="!$event.ctrlKey && transferClick(index, 'transferContentRight')"
          @dblclick="transferDblclick(index, 'transferContentRight')"
          @click.ctrl="transferClickCtrl(index, 'transferContentRight')"
          >
            <span class="transfer_content_left">{{item.label}}</span>
            <span class="transfer_content_right">{{item.loginName}}</span>
          </div>
        </div>
      </div>
      <div slot="footer">
        <el-button @click="cancelForm('ruleForm')" size="mini">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" size="mini">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="dialog.title" :visible.sync="dialog.visibleUserForm" width="500px" :before-close="dialogClose">
      <el-form label-width="78px" :model="dialog.data" label-position="right" ref="UserForm">
        <el-form-item label="登录名" prop='login_name'>
          <el-input v-model="dialog.data.login_name"  :disabled="dialog.type === 'put'"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop='nickname'>
          <el-input v-model="dialog.data.nickname" :disabled="dialog.type === 'put'"></el-input>
        </el-form-item>
        <el-form-item label="失效日期" prop='expired_date'>
          <el-date-picker v-model="dialog.data.expired_date" type="date"  placeholder="选择日期" style="width: 100%" :disabled="dialog.type === 'put'"></el-date-picker>
        </el-form-item>
        <el-form-item label="邮件地址" prop='email'>
          <el-input v-model="dialog.data.email" :disabled="dialog.type === 'put'"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop='mobile'>
          <el-input v-model="dialog.data.mobile" :disabled="dialog.type === 'put'"></el-input>
        </el-form-item>
        <el-form-item label="所属部门" prop='dept_id'>
          <el-select style="width: 100%" v-model="dialog.data.dept_id" :disabled="dialog.type === 'put'">
            <!-- <el-option v-for="(item, key) in arr" :key="key" :label="item.dept_name" :value="item.id">
            </el-option> -->
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialog.visibleUserForm = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { tableColumnFormatterTool, dateFormatterTool, dialogClose } from '../../assets/common/common'
export default {
  name: 'sys-role-user',
  data () {
    return {
      table: {
        cols: [
          { label: '登录名', prop: 'login_name', minwidth: '120px' },
          { label: '昵称', prop: 'nickname', minwidth: '120px' },
          { label: '备注', prop: 'remarks', minwidth: '150px', display: false },
          { label: '创建时间', prop: 'created_time', formatter: this.formatterTime, minwidth: '150px', display: false },
          { label: '修改时间', prop: 'modified_time', formatter: this.formatterTime, minwidth: '150px', display: false }
        ],
        handRightBotton: [
          {
            name: `绑定用户`,
            icon: `el-icon-circle-plus-outline`,
            fn: this.addRoleUser,
            entitlement: true
          }
        ],
        contextMenuData: [
          {
            name: `查看`,
            icon: `el-icon-edit`,
            fnEvent: this.putRoleUser,
            entitlement: true
          },
          {
            name: `删除`,
            icon: `el-icon-delete`,
            fnEvent: this.deleteRoleUser,
            entitlement: true
          }
        ],
        data: []
      },
      dialog: {
        title: ``,
        visible: false,
        visibleUserForm: false,
        type: '',
        data: {},
        transferValue: [],
        transferData: [],
        transferData1: [],
        transferIndexLeft: [],
        transferIndexRight: [],
        custData: null,
        custCurrentData: null,
        custLoading: false,
        remarkRows: 3
      },
      searchValueLeft: null,
      searchValueRight: null,
      roleCurrentClick: null,
      timer: null
    }
  },
  created () {
  },
  mounted () {
    this.$store.state.http.auto('sysUser', 'getAll').then(res => {
      res.data.map(userObj => {
        this.dialog.transferData1.push({
          key: userObj.user_id,
          label: userObj.nickname,
          loginName: userObj.login_name
        })
      })
      this.dialog.transferData = this.dialog.transferData1
    })
  },
  computed: {
  },
  watch: {
    searchValueLeft (val) {
      if (val) {
        this.dialog.transferData = [].concat(this.transferSearch(this.initTransfer(this.dialog.transferData, this.dialog.transferValue), val))
      } else {
        this.dialog.transferData = this.initTransfer(this.dialog.transferData, this.dialog.transferValue)
      }
    },
    searchValueRight (val) {
      if (val) {
        this.dialog.transferValue = [].concat(this.transferSearch(this.initTransfer(this.dialog.transferValue, this.dialog.transferData), val))
      } else {
        this.dialog.transferValue = this.initTransfer(this.dialog.transferValue, this.dialog.transferData)
      }
    }
  },
  methods: {
    initTransfer (data1, data2) {
      data1 = [].concat(this.dialog.transferData1)
      if (data2.length) {
        let arr = []
        for (let i = 0; i < data1.length; i++) {
          data2.map(roleUser => {
            if (data1[i].key === roleUser.key) {
              arr.push(i)
            }
          })
        }
        if (arr.length) {
          arr.reverse()
          arr.map(index => {
            data1.splice(index, 1)
          })
        }
      }
      return data1
    },
    transferClick (index, ref) {
      this.timer && clearTimeout(this.timer)
      let vm = this
      this.timer = setTimeout(function () {
        vm.$refs[ref].map(obj => {
          obj.className = 'transfer_content'
        })
        if (ref === 'transferContentLeft') {
          vm.dialog.transferIndexLeft = []
          vm.dialog.transferIndexLeft.push(index)
        } else if (ref === 'transferContentRight') {
          vm.dialog.transferIndexRight = []
          vm.dialog.transferIndexRight.push(index)
        }
        vm.$refs[ref][index].className += ' click'
      }, 200)
    },
    transferDblclick (index, ref) {
      this.timer && clearTimeout(this.timer)
      if (ref === 'transferContentLeft') {
        this.dialog.transferIndexLeft = []
        let arr = this.dialog.transferData.splice(index, 1)
        this.dialog.transferValue.push(arr[0])
      } else if (ref === 'transferContentRight') {
        this.dialog.transferIndexRight = []
        let arr = this.dialog.transferValue.splice(index, 1)
        this.dialog.transferData.push(arr[0])
      }
      this.$refs[ref].map(obj => {
        obj.className = 'transfer_content'
      })
    },
    transferBindAll (ref) {
      this.searchValueLeft = null
      this.searchValueRight = null
      this.dialog.transferIndexLeft = []
      this.dialog.transferIndexRight = []
      if (ref === 'left') {
        this.dialog.transferValue = this.dialog.transferValue.concat(this.dialog.transferData)
        this.dialog.transferData = []
        this.$refs.transferContentLeft.map(obj => {
          obj.className = 'transfer_content'
        })
      } else if (ref === 'right') {
        this.dialog.transferData = this.dialog.transferData.concat(this.dialog.transferValue)
        this.dialog.transferValue = []
        this.$refs.transferContentRight.map(obj => {
          obj.className = 'transfer_content'
        })
      }
    },
    transferBind () {
      if (this.dialog.transferIndexLeft.length) {
        this.dialog.transferIndexLeft.sort((a, b) => {
          return b - a
        })
        this.dialog.transferIndexLeft.map(index => {
          let arr = this.dialog.transferData.splice(index, 1)
          this.dialog.transferValue.push(arr[0])
        })
        this.dialog.transferIndexLeft = []
      } else if (this.dialog.transferIndexRight.length) {
        this.dialog.transferIndexRight.sort((a, b) => {
          return b - a
        })
        this.dialog.transferIndexRight.map(index => {
          let arr = this.dialog.transferValue.splice(index, 1)
          this.dialog.transferData.push(arr[0])
        })
        this.dialog.transferIndexRight = []
      }
    },
    transferClickCtrl (index, ref) {
      let transferIndex = []
      if (ref === 'transferContentRight') {
        transferIndex = this.dialog.transferIndexRight
      } else if (ref === 'transferContentLeft') {
        transferIndex = this.dialog.transferIndexLeft
      }
      if (!transferIndex.length) {
        this.$refs[ref].map(obj => {
          obj.className = 'transfer_content'
        })
      }
      let flag = true
      let number = null
      for (let i = 0; i < transferIndex.length; i++) {
        if (transferIndex[i] === index) {
          flag = false
          number = i
          break
        }
      }
      if (flag) {
        transferIndex.push(index)
        this.$refs[ref][index].className += ' click'
      } else {
        transferIndex.splice(number, 1)
        this.$refs[ref][index].className = 'transfer_content'
      }
      if (ref === 'transferContentRight') {
        this.dialog.transferIndexRight = transferIndex
      } else if (ref === 'transferContentLeft') {
        this.dialog.transferIndexLeft = transferIndex
      }
    },
    transferSearchFocus (ref) {
      this.$refs[ref].map(obj => {
        obj.className = 'transfer_content'
      })
      if (ref === 'transferContentRight') {
        this.dialog.transferIndexRight = []
      } else {
        this.dialog.transferIndexLeft = []
      }
    },
    transferSearch (data, value, type) {
      let arr = []
      data.map(obj => {
        if (obj.label.indexOf(value) > -1 || obj.loginName.indexOf(value) > -1) {
          arr.push(obj)
        }
      })
      return arr
    },
    roleNameClick (row) {
      this.roleCurrentClick = null
      this.table.data = []
      if (row && row.hasOwnProperty('role_id')) {
        this.roleCurrentClick = row
        this.$store.state.http.auto('sysRoleUser', 'getByRoleId', { params: { role_id: row.role_id } }).then(res => {
          this.table.data = res.data
        })
      }
    },
    addRoleUser () {
      if (this.roleCurrentClick && this.roleCurrentClick.hasOwnProperty('name')) {
        this.dialog.type = 'post'
        this.dialog.visible = true
        this.dialog.title = `${this.roleCurrentClick.name}角色绑定`
        this.dialog.transferIndexLeft = []
        this.dialog.transferIndexRight = []
        this.dialog.transferValue = []
        this.table.data.map(roleUser => {
          this.dialog.transferValue.push({
            key: roleUser.user_id,
            label: roleUser.nickname,
            loginName: roleUser.login_name
          })
        })
        if (this.$refs.transferContentRight) {
          this.$refs.transferContentRight.map(obj => {
            obj.className = 'transfer_content'
          })
        }
        if (this.$refs.transferContentLeft) {
          this.$refs.transferContentLeft.map(obj => {
            obj.className = 'transfer_content'
          })
        }
        this.dialog.transferData = this.initTransfer(this.dialog.transferData, this.dialog.transferValue)
      } else {
        this.$message.error('请选择角色')
      }
    },
    putAlert (row) {
      this.dialog.visibleUserForm = true
      this.dialog.title = '查看用户属性'
      this.dialog.type = 'put'
      this.dialog.data = {
      }
      for (var key in row) {
        this.dialog.data[key] = `${row[key] === 'null' || row[key] === null ? '' : row[key]}`
      }
      this.dialog.data.expired_date = row.expired_date
    },
    putRoleUser () {
      this.putAlert(this.$store.state.tableCurrentRow)
    },
    deleteRoleUser () {
      let obj = this.$store.state.tableCurrentRow
      this.$confirm('确定删除该数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.state.http.auto('sysRoleUser', 'delete', { params: { role_user_id: obj.role_user_id } }).then((response) => {
          this.roleNameClick(this.roleCurrentClick)
          this.$message.success('删除成功!')
        })
      })
    },
    rowContextdblClick (row) {
      this.putAlert(row)
    },
    formatterDictType (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictType, cellValue)
    },
    formatterDictStatus (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictStatus, cellValue)
    },
    formatterTime (row, column, cellValue) {
      return dateFormatterTool(cellValue, 'yyyy-MM-dd')
    },
    dialogClose: dialogClose,
    cancelForm (form) {
      this.dialog.visible = false
      if (this.$refs.transferContentRight) {
        this.$refs.transferContentRight.map(obj => {
          obj.className = 'transfer_content'
        })
      }
      if (this.$refs.transferContentLeft) {
        this.$refs.transferContentLeft.map(obj => {
          obj.className = 'transfer_content'
        })
      }
    },
    submitForm (from) {
      let vm = this
      let userIdList = []
      this.dialog.transferValue.map(obj => {
        userIdList.push(obj.key)
      })
      let list = { role_id: this.roleCurrentClick.role_id, userIdList: userIdList }
      this.$store.state.http.auto('sysRoleUser', 'add', { data: list }).then((res) => {
        this.roleNameClick(this.roleCurrentClick)
        this.$message.success(res.statusText)
        this.dialog.visible = false
        this.dialog.type = null
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error.statusText)
      })
    },
    custRemoteMethod (query) {
      if (query !== '') {
        this.dialog.custLoading = true
        setTimeout(() => {
          this.dialog.custLoading = false
          this.dialog.custCurrentData = this.dialog.custData.filter(item => {
            return ((item.nickname + '' + item.user_id).toLowerCase())
              .indexOf(query.toLowerCase()) > -1
          })
        }, 200)
      } else {
        this.dialog.custCurrentData = this.dialog.custData
      }
    }
  },
  components: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
div.el-dialog__wrapper {
  .transfer {
    padding: 10px 0 10px 10px;
    width: 100%;
    height: 400px;
    box-sizing: border-box;
    .transfer_left,
    .transfer_right {
      padding: 10px;
      float: left;
      width: calc((100% - 100px) / 2);
      height: 100%;
      border: 1px solid #e4e7ed;
      box-sizing: border-box;
      overflow: auto;
      .transfer_content {
        padding: 0 10px;
        width: 100%;
        height: 24px;
        box-sizing: border-box;
        text-align: 24px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        user-select: none;
        cursor: pointer;
        .transfer_content_left {
          float: left;
        }
        .transfer_content_right {
          float: right;
        }
      }
    }
    .transfer_centre {
      float: left;
      width: 100px;
      height: 100%;
      box-sizing: border-box;
      .transfer_centre_div {
        padding: 16px 12px;
        width: 100%;
        height: calc(100% / 6);
        box-sizing: border-box;
        cursor: pointer;
        .prohibit {
          background-color: #dcdfe6;
          color: #909399;
          cursor: not-allowed;
        }
        div {
          width: 100%;
          height: 100%;
          color: #fff;
          border-radius: 10px;
          box-sizing: border-box;
          line-height: 31px;
          span {
            font-size: 16px;
          }
        }
      }
    }
  }
}
.el-transfer-panel {
  width: 300px !important;
}
</style>