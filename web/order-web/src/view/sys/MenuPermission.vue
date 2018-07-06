<template>
  <div class="menu_permission">
    <div class="menu_tree" ref="menuTree">
      <div>
        <el-input placeholder="输入关键字进行过滤" v-model="filterText" style="width: 100%;" size="mini"></el-input>
        <div class="table-hand-right" style="margin:5px 0 0 10px; cursor: pointer;">
          <div class="table-hand-right-button" style="float:right" @click="allMenuAlert">
            <i  class="el-icon-menu"></i>
            <span>管理</span>
          </div>
          <div class="table-hand-right-button" style="float:right;margin:0 10px 0 0" @click="addAlertClick">
            <i  class="el-icon-circle-plus-outline" ></i>
            <span>添加</span>
          </div>
        </div>
        <div style="clear: both;"></div>
      </div>
      <div style="clear: both;"></div>
    <el-tree style="margin-top:5px;" :data="tree.data" :render-content="renderContent" accordion highlight-current node-key="menu_id" :default-expanded-keys="tree.defaulExpandedKeys"
      :expand-on-click-node="false" :filter-node-method="filterNode" ref="tree" :props="tree.defaultProps"
      @node-click="handleNodeClick">
    </el-tree>
    <div style="clear: both;"></div>
    </div>
    <div class="permission">
      <permission ref="permission" :menu="menu"></permission>
    </div>
    <div style="clear:both"></div>
    <el-dialog :title="dialog.title" :visible.sync="dialog.visibleAllMenu" fullscreen width="100%" :before-close="dialogClose">
      <menu1 @initData="initData" v-if="dialog.visibleAllMenu" :tree="tree" :menuAll="dialog.menuAll"></menu1>
      <div style="clear:both"></div>
    </el-dialog>
    <menu-dialog :tree="tree" :dialog="dialog" :submitCallback="submitCallback"></menu-dialog>
  </div>
</template>
<script>
import Permission from './Permission'
import Menu1 from './Menu'
import MenuDialog from './dialog/MenuDialog.vue'
import { dialogClose } from '../../assets/common/common'
export default {
  data () {
    return {
      tree: {
        data: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        defaulExpandedKeys: []
      },
      dialog: {
        title: ``,
        visibleAllMenu: false,
        visible: false,
        disabled: false,
        type: '',
        data: {},
        menuAll: [],
        parentIdData: [],
        parentIdCurrentData: [],
        loading: false,
        remarksRows: 3
      },
      filterText: '',
      menu: {},
      timer: null,
      text: ''
    }
  },
  computed: {
  },
  created () {
    this.initData()
  },
  mounted () {
    window.onresize = this.changeHeight()
  },
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val)
    }
  },
  methods: {
    changeHeight () {
      this.$refs.menuTree.style.height = window.document.body.clientHeight - 35 - 2 - 46 + 'px'
    },
    formatTreeData (data, parentId, inputLabel) {
      let children = []
      for (let node of data) {
        if (node.hasOwnProperty('parent_id') && node.parent_id === parentId) {
          node.label = node.name
          let text = inputLabel
          text += `>${node.name}`
          node.inputLabel = node.type === 'SYS' ? node.name : text
          node.children = this.formatTreeData(data, node.menu_id, node.inputLabel)
          children.push(node)
        }
      }
      return children
    },
    initData () {
      let vm = this
      this.$store.state.http.get('/sys/menu', 'getAll').then(res => {
        this.dialog.menuAll = [...res.data]
        let menuData = [...res.data]
        // 格式化成树形结构
        this.tree.data = this.formatTreeData(menuData, 'SYS')
        // 菜单排序
        let sortFn = (data1) => {
          data1.sort((a, b) => {
            return Number(a.menu_order) - Number(b.menu_order)
          })
          data1.map(obj => {
            if (obj.hasOwnProperty('children') && obj.children.length) {
              sortFn(obj.children)
            }
          })
        }
        sortFn(this.tree.data)
        if (!this.menu.hasOwnProperty('menu_id')) {
          let fn = (data1) => {
            if (!data1.hasOwnProperty('children') || !data1.children.length) {
              this.tree.defaulExpandedKeys.push(data1.menu_id)
              this.menu = data1
            } else {
              fn(data1.children[0])
            }
          }
          fn(this.tree.data[0])
        } else {
          this.tree.defaulExpandedKeys = []
          this.tree.defaulExpandedKeys.push(this.menu.menu_id)
        }
        this.getCurrentKey()
        this.handleNodeClick(this.menu)
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    getCurrentKey () {
      let vm = this
      this.timer = setInterval(() => {
        if (vm.menu.hasOwnProperty('menu_id')) {
          vm.$refs.tree.setCurrentKey(vm.menu.menu_id)
          clearInterval(vm.timer)
        }
      }, 20)
    },
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    handleNodeClick (data) {
      this.menu = data
      this.$refs.permission.initData(data)
    },
    menuTypeChangeEvent (val) {
      this.dialog.data.parent_id = ''
      if (val === 'SYS') {
        this.dialog.data.parent_id = 'SYS'
        this.dialog.parentIdData = this.dialog.parentIdCurrentData = []
        return
      }
      let list = []
      this.dialog.menuAll.map(obj => {
        if (val === 'PARENT' && obj.type === 'ROOT') {
          list.push(obj)
        } else if (val === 'NODE' && obj.type === 'PARENT') {
          list.push(obj)
        } else if (val === 'ROOT' && obj.type === 'SYS') {
          list.push(obj)
        }
      })
      this.dialog.parentIdData = this.dialog.parentIdCurrentData = list
      if (this.dialog.type === 'post') {
        this.dialog.data.parent_id = this.dialog.parentIdData[0].menu_id
        this.parentIdChangeEvent(this.dialog.data.parent_id)
      }
    },
    parentIdChangeEvent (val) {
      let length = 0
      if (val === 'SYS') {
        length = this.tree.data[this.tree.data.length - 1].menu_order
      } else {
        let fn = (data) => {
          for (let i = 0; i < data.length; i++) {
            if (val === data[i].parent_id) {
              length = data[data.length - 1].menu_order
              return
            } else if (data[i].hasOwnProperty('children') && data[i].children) {
              fn(data[i].children)
            }
          }
        }
        fn(this.tree.data)
      }
      this.dialog.data.menu_order = Number(length) + 1
    },
    allMenuAlert () {
      this.dialog.title = '管理菜单'
      this.dialog.visibleAllMenu = true
    },
    addAlertClick (data) {
      let parentId = 'SYS'
      let type = 'SYS'
      this.dialog.disabled = false
      if (data.menu_id) {
        parentId = data.menu_id
        if (data.type === 'ROOT') {
          type = 'PARENT'
        } else if (data.type === 'PARENT') {
          type = 'NODE'
        } else if (data.type === 'SYS') {
          type = 'ROOT'
        }
        this.dialog.disabled = true
      }
      this.menuTypeChangeEvent(type)
      this.dialog.data = {
        type: type,
        parent_id: parentId
      }
      this.parentIdChangeEvent(this.dialog.data.parent_id)
      this.dialog.visible = true
      this.dialog.title = '添加菜单'
      this.dialog.type = 'post'
    },
    putAlertClick (data) {
      this.dialog.visible = true
      this.dialog.title = '编辑菜单'
      this.dialog.type = 'put'
      this.menuTypeChangeEvent(data.type)
      this.dialog.data = {
        parent_id: ''
      }
      for (var key in data) {
        this.dialog.data[key] = `${data[key] === 'null' || data[key] === null ? '' : data[key]}`
      }
      if (data.type === 'SYS') {
        this.dialog.data.parent_id = 'SYS'
      }
    },
    remove (data) {
      let objects = data
      if (data.children && data.children.length) {
        this.$message.error('此菜单存在子菜单，禁止删除')
        return
      }
      this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.state.http.auto('sysMenu', 'delete', { params: objects }).then((response) => {
          this.$message.success(response.statusText)
          this.initData()
        })
      })
    },
    submitCallback () {
      this.initData()
    },
    dialogClose: dialogClose,
    renderContent (h, { node, data, store }) {
      return (
        <span on-dblclick={() => this.putAlertClick(data)} style="flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 8px;">
          <span>{node.label}</span>
          <span style="float:right">
            {
              data.type === 'NODE' ? '' : <span class="table-hand-right-button" on-click={() => this.addAlertClick(data)}>
                <i class="el-icon-circle-plus-outline" ></i>
              </span>
            }
          </span>
        </span >
      )
    }
  },
  components: {
    Permission,
    Menu1,
    MenuDialog
  }
}
</script>

<style lang="scss" scoped>
.menu_permission {
  height: 100%;
  .menu_tree {
    padding: 10px;
    width: 345px;
    height: 100%;
    overflow: auto;
    border: 1px solid #dcdfe6;
    border-radius: 4px;
    box-sizing: border-box;
    float: left;
  }
  .permission {
    margin-left: 10px;
    width: calc(100% - 355px);
    float: left;
  }
}
</style>