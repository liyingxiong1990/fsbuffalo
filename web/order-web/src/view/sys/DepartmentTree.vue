<template>
<div class="permission">
  <div class="permission-top">
    <span style="float:left;margin-left:10px;font-weight: 700;font-size:16px">角色名称：{{role.name}}</span>
    <label style="float:right;"><input type="checkbox" v-model="checkAll"/>全选</label>
  </div>
  <el-tree
    :data="treeData"
    :render-content="renderContent"
    default-expand-all
    :expand-on-click-node="false"
    :filter-node-method="filterNode"
    ref="tree">
  </el-tree>
  <div style="float:right">
    <input type="button" value="确定" style="width:50px" @click="submit"/>
  </div>
  <div style="clear: both; padding-bottom: 10px;"></div>
</div>
</template>
<script>
export default {
  props: {
    role: {
      type: Object
    }
  },
  data () {
    return {
      treeData: [],
      permissionData: [],
      filterText: '',
      checkAll: false,
      data: {
        role_id: null,
        role_permissions: []
      }
    }
  },
  created () {
    this.initData()
    this.$store.state.http.auto('sysDepartment', 'getAll').then(res => {
      this.permissionData = res.data
    }).catch(error => {
      console.log(error)
    })
  },
  mounted () {
  },
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val)
    },
    checkAll (val) {
      if (val) {
        this.data.role_permissions = []
        this.permissionData.map(obj => {
          this.data.role_permissions.push(obj.permission_id)
        })
        for (let i = 0; i < document.getElementsByClassName('input').length; i++) {
          document.getElementsByClassName('input')[i].checked = true
        }
        for (let i = 0; i < document.getElementsByClassName('inputRoot').length; i++) {
          document.getElementsByClassName('inputRoot')[i].checked = true
        }
      } else {
        this.data.role_permissions = []
        for (let i = 0; i < document.getElementsByClassName('input').length; i++) {
          document.getElementsByClassName('input')[i].checked = false
        }
        for (let i = 0; i < document.getElementsByClassName('inputRoot').length; i++) {
          document.getElementsByClassName('inputRoot')[i].checked = false
        }
      }
    }
  },
  methods: {
    initData () {
      let vm = this
      this.$store.state.http.get('/sys/department', 'getAll').then(res => {
        let departmentData = [...res.data]
        // 格式化成树形结构
        this.treeData = this.formatTreeData(departmentData, 1)
        console(this.treeDate)
        // 部门排序
        let sortFn = (data1) => {
          data1.sort((a, b) => {
            return Number(a.dept_id) - Number(b.dept_id)
          })
          data1.map(obj => {
            if (obj.hasOwnProperty('children') && obj.children.length) {
              sortFn(obj.children)
            }
          })
        }
        sortFn(this.treeData)
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
      // 获取当前角色已拥有的权限
      this.$store.state.http.auto('sysRolePermission', 'getListByRoleId', { params: this.role }).then(res => {
        this.data.role_id = this.role.role_id
        this.data.role_permissions = []
        res.data.map(obj => {
          this.data.role_permissions.push(obj.permission_id)
        })
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    formatTreeData (data, pid) {
      let children = []
      for (let node of data) {
        if (node.hasOwnProperty('pid') && node.pid === pid) {
          node.label = node.dept_name
          node.children = this.formatTreeData(data, node.dept_id)
          children.push(node)
        }
      }
      return children
    },
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    submit () {
      let vm = this
      this.$store.state.http.auto('sysRolePermission', 'update', { data: this.data }).then((res) => {
        this.$message.success(res.statusText)
        // 响应closeMenuPermission事件，并在Role.vue监听该事件，关闭该页面
        this.$emit('closeMenuPermission', false)
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    sync (permissionObj, value) {
      if (value === true) {
        this.data.role_permissions.push(permissionObj.permission_id)
      } else {
        for (let i = 0; i < this.data.role_permissions.length; i++) {
          if (this.data.role_permissions[i] === permissionObj.permission_id) {
            this.data.role_permissions.splice(i, 1)
            break
          }
        }
      }
    },
    // 点击父部门checkbox
    dataFn (data, value) {
      data.children.map(obj => {
        // 判断是否还有子目录
        if (obj.children) {
          // 改变当前部门和当前部门下的所有父级部门的checkbox
          for (let item of document.getElementsByName(obj.menu_id)) {
            item.checked = value
          }
          this.dataFn(obj, value)
        } else {
          // 循环所有权限列表
          for (let i = 0; i < this.permissionData.length; i++) {
            // 判断menu_id是否为当前点击的checkbox的子部门menu_id
            if (obj.menu_id === this.permissionData[i].menu_id) {
              let flag = true
              // 循环当前角色的权限列表
              for (let w = 0; w < this.data.role_permissions.length; w++) {
                // 判断角色的权限列表是否已存在当前权限
                if (this.data.role_permissions[w] === this.permissionData[i].permission_id) {
                  flag = false
                  // 角色的权限列表存在且点击checkbox为false
                  if (!value) {
                    this.data.role_permissions.splice(w, 1)
                    let domList = document.getElementsByName(this.permissionData[i].menu_id)
                    for (let index = 0; index < domList.length; index++) {
                      document.getElementsByName(this.permissionData[i].menu_id)[index].checked = false
                    }
                  }
                  break
                }
              }
              // 角色的权限列表不存在且点击checkbox为true
              if (flag && value) {
                this.data.role_permissions.push(this.permissionData[i].permission_id)
                let domList = document.getElementsByName(this.permissionData[i].menu_id)
                for (let index = 0; index < domList.length; index++) {
                  document.getElementsByName(this.permissionData[i].menu_id)[index].checked = true
                }
              }
            }
          }
        }
      })
    },
    rootMenu (data, value) {
      if (data.children) {
        this.dataFn(data, value)
      }
    },
    renderContent (h, { node, data, store }) {
      let arr = ['1']
      let vm = this
      return (
        <span style="flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 8px;">
          <span>
            <span>{node.label}</span>
          </span>
          {
            data.children && data.children.length
              ? <span style="float:right">
                {
                  !data.children
                    ? ''
                    // : data.children.map(menuObj => {
                    //   let falg = false
                    //   vm.permissionData.map(obj => {
                    //     if (obj.menu_id === menuObj.menu_id) {
                    //       falg = true
                    //     }
                    //   })
                    //   if (falg) {
                    : arr.map(str => {
                      return h(
                        'label',
                        [
                          h('input', {
                            attrs: {
                              name: data.dept_id,
                              type: 'checkbox',
                              class: 'inputRoot'
                            },
                            on: {
                              change: (e) => { this.rootMenu(data, e.target.checked) }
                            },
                            ref: 'inputRoot1'
                          },
                            this.$slots.default)
                        ]
                      )
                    })
                  //   }
                  // })
                }
              </span>
              : <span style="float:right">
                {
                  vm.permissionData.map(obj => {
                    if (obj.menu_id === data.menu_id) {
                      let name = obj.request_type
                      let flag = false
                      for (let i = 0; i < vm.data.role_permissions.length; i++) {
                        if (vm.data.role_permissions[i] === obj.permission_id) {
                          flag = true
                          break
                        }
                      }
                      if (flag) {
                        return h(
                          'label',
                          [
                            h('input', {
                              attrs: {
                                name: obj.menu_id,
                                type: 'checkbox',
                                checked: 'checked',
                                class: 'input'
                              },
                              on: {
                                change: (e) => { vm.sync(obj, e.target.checked) }
                              },
                              ref: 'input'
                            }), name
                          ]
                        )
                      } else {
                        return h(
                          'label',
                          [
                            h('input', {
                              attrs: {
                                name: obj.menu_id,
                                type: 'checkbox',
                                class: 'input'
                              },
                              on: {
                                change: (e) => { vm.sync(obj, e.target.checked) }
                              },
                              ref: 'input'
                            }), name
                          ]
                        )
                      }
                    }
                  })
                }
              </span>
          }
        </span>
      )
    }
  }
}
</script>

<style lang="scss" scoped>
.permission {
  .permission-top {
    width: 100%;
    height: 30px;
    margin-bottom: 10px;
  }
}
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>