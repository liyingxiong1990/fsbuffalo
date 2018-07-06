import Vue from 'vue'
import Vuex from 'vuex'
import * as getters from './getters'
import * as actions from './actions'
import mutations from './mutations'
import http from '../assets/common/http.js'
import sysDict from './modules/sysDict'
import sysPermission from './modules/sysPermission'

Vue.use(Vuex)

const state = {
  userid: null,
  username: null, // 用户名
  nickname: null, // 用户昵称
  permissionList: null, // 用户权限
  menuList: null, // 目录
  http: http, // http请求对象
  tableCurrentRow: null, // 表格当前选中行数据
  tableData: [] // 表格数据
}

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations,
  modules: {
    sysDict,
    sysPermission
  }
})
