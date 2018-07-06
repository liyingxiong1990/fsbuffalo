import * as types from '../mutation-types'

const state = {
  permissionAllList: [],
  permissionAllObj: {}, // {menu_id:{request_type:false}}
  menuPermissionAll: {},
  menuPermission: {}
}
const getters = {
  permissionList: state => state.menuPermission
}

const mutations = {
  [types.SYS_GET_PERMISSION] (state) {
    state.menuPermission = {}
    if (!state.menuPermissionAll.hasOwnProperty(window.location.hash.slice(1))) {
      if (state.permissionAllList && state.permissionAllList.hasOwnProperty('length')) {
        // 格式化所有权限  {menu_id: {request_type：false}}
        state.permissionAllObj = {}
        state.permissionAllList.map(obj => {
          if (obj) {
            if (state.permissionAllObj.hasOwnProperty(obj.menu_id)) {
              state.permissionAllObj[obj.menu_id][obj.request_type] = false
            } else {
              state.permissionAllObj[obj.menu_id] = {}
              state.permissionAllObj[obj.menu_id][obj.request_type] = false
            }
          }
        })
        // 格式化当前用户拥有权限 {menu_id: {request_type：true}}
        let menuPermissionObj = {}
        if (this.state.permissionList && this.state.permissionList.hasOwnProperty('length')) {
          this.state.permissionList.map(obj => {
            if (obj) {
              if (menuPermissionObj.hasOwnProperty(obj.menu_id)) {
                menuPermissionObj[obj.menu_id][obj.request_type] = true
              } else {
                menuPermissionObj[obj.menu_id] = {}
                menuPermissionObj[obj.menu_id][obj.request_type] = true
              }
            }
          })
          // 所有权限与当前用户拥有权限合并 {menu_id: {request_type：boolean}}
          for (let key in state.permissionAllObj) {
            if (menuPermissionObj.hasOwnProperty(key)) {
              for (let requestType in state.permissionAllObj[key]) {
                if (menuPermissionObj[key].hasOwnProperty(requestType)) {
                  state.permissionAllObj[key][requestType] = true
                }
              }
            }
          }
          // 格式化菜单数据 {route：menu_id}
          let menuObj = {}
          this.state.menuList.map(obj => {
            if (obj && obj.route) {
              menuObj[obj.route] = obj.menu_id
            }
          })
          // 权限合并的对象的key变成对应的route {route：{request_type：boolean}}
          let permissionAllObj = {}
          for (let key in menuObj) {
            if (state.permissionAllObj.hasOwnProperty(menuObj[key])) {
              permissionAllObj[key] = state.permissionAllObj[menuObj[key]]
            }
          }
          state.menuPermissionAll = permissionAllObj
          console.log(state.menuPermissionAll)
          state.menuPermission = state.menuPermissionAll[window.location.hash.slice(1)]
        }
      }
    } else {
      console.log(state.menuPermissionAll)
      state.menuPermission = state.menuPermissionAll[window.location.hash.slice(1)]
    }
    console.log(state.menuPermission)
  }
}

const actions = {
  SYS_GET_PERMISSION ({ state, commit, dispatch }) {
    this.state.http.auto('auth', 'info', { params: { 'username': window.localStorage.getItem('username') } }).then((res) => {
      this.state.userid = res.data.user.user_id
      this.state.nickname = res.data.user.nickname
      this.state.permissionList = res.data.permissionList
      this.state.menuList = res.data.menuList
      this.state.http.auto('sysPermission', 'getAll').then(res => {
        state.permissionAllList = res.data
        commit(types.SYS_GET_PERMISSION)
      })
    })
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
