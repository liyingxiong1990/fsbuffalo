import * as types from '../mutation-types'

const state = {
  dict: []
}
/**
 * 获取字典类型
 * @param {String} type 字典类型
 * @returns {Array} 字典类型下的所有子类型
 */
const getters = {
  sysDict: state => type => {
    return state.dict[type]
  }
}

const mutations = {
  // 格式化数据字典并保存
  [types.SYS_DICT] (state, res) {
    let data = res.data
    let dictMap = {}
    if (typeof data === 'object') {
      data.map((row) => {
        if (!dictMap[row.dict_type]) {
          dictMap[row.dict_type] = []
        }
        dictMap[row.dict_type].push(row)
      })
    }
    state.dict = dictMap
  }
}

const actions = {
  // 首次访问获取数据字典
  SYS_DICT ({
    commit
  }, res) {
    this.getters.http.auto('sysDict', 'getAll').then(res => {
      commit(types.SYS_DICT, res)
    })
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
