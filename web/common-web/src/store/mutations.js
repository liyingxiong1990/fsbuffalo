import * as types from './mutation-types'

export default {
  /**
   * 初始化系统信息
   * SYS_INITIAL
   * @param {any} state 数据源
   * @param {any} res 初始化参数
   */
  [types.SYS_INITIAL] (state, res) {
    state.userid = null
    state.nickname = null
    state.permissionList = null
    state.menuList = null
    state.http.auto('auth', 'info', { params: { 'username': window.localStorage.getItem('username') } }).then((res) => {
      window.localStorage.setItem('userid', res.data.user.user_id)
      state.userid = res.data.user.user_id
      state.nickname = res.data.user.nickname
      state.permissionList = res.data.permissionList
      state.menuList = res.data.menuList
    })
  },
  /**
 * 退出系统
 * SYS_EXIT
 * @param {any} state 数据源
 */
  [types.SYS_EXIT] (state, res) {
    window.localStorage.removeItem('userid')
    window.localStorage.removeItem('token')
    window.localStorage.removeItem('username')
    window.localStorage.removeItem('TOKEN_TIME')
    state.userid = null
    state.nickname = null
    state.permissionList = null
    state.menuList = null
    this.$router.push('/login')
  }
}
