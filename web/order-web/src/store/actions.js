import * as types from './mutation-types'

export const SYS_INITIAL = ({ state, commit, dispatch }) => {
  commit(types.SYS_INITIAL)
  dispatch(types.SYS_DICT)
  dispatch(types.SYS_GET_PERMISSION)
}
