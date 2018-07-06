<template>
  <div class="menu">
    <base-table :tableData="table.data" :addTableRowClick="addAlertClick" :rowContextdblClick="rowContextdblClick" :tableCols="table.cols" :contextMenuData="table.contextMenuData"></base-table>
    <menu-dialog :dialog="dialog" :tree="tree" :submitCallback="submitCallback"></menu-dialog>
  </div>
</template> 

<script>
import { tableColumnFormatterTool, dateFormatterTool } from '../../assets/common/common'
import MenuDialog from './dialog/MenuDialog.vue'
export default {
  components: {
    MenuDialog
  },
  props: {
    tree: {
      type: Object,
      default: {
        data: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        defaulExpandedKeys: []
      }
    },
    menuAll: {
      type: Array
    }
  },
  data () {
    return {
      table: {
        data: [],
        cols: [
          { label: '菜单名', prop: 'name', minwidth: '100px' },
          { label: '父菜单', prop: 'parent_id', formatter: this.formatterParent, minwidth: '100px' },
          { label: '路由', prop: 'route', minwidth: '120px' },
          { label: '节点类型', prop: 'type', minwidth: '100px' },
          { label: '图标', prop: 'icon', minwidth: '120px' },
          { label: '排序', prop: 'menu_order', minwidth: '80px' },
          { label: '备注', prop: 'remarks', minwidth: '100px' },
          { label: '创建时间', prop: 'created_time', formatter: this.formatterTime, minwidth: '150px', display: false },
          { label: '修改时间', prop: 'modified_time', formatter: this.formatterTime, minwidth: '150px', display: false }
        ],
        contextMenuData: [
          {
            name: `添加`,
            icon: `el-icon-plus`,
            fnEvent: this.addAlertClick,
            entitlement: true
          },
          {
            name: `编辑`,
            icon: `el-icon-edit`,
            fnEvent: this.putAlertClick,
            entitlement: true
          },
          {
            name: `删除`,
            icon: `el-icon-delete`,
            fnEvent: this.remove,
            entitlement: true
          }
        ]
      },
      dialog: {
        title: ``,
        visible: false,
        loading: false,
        type: '',
        data: {},
        parentIdData: [],
        parentIdCurrentData: [],
        remarksRows: 3,
        menuAll: []
      }
    }
  },
  computed: {
  },
  created () {
    this.initData()
    this.dialog.menuAll = this.menuAll
  },
  methods: {
    initData () {
      let vm = this
      this.$store.state.http.get('/sys/menu', 'getAll').then(res => {
        this.table.data = res.data
      }).catch(function (error) {
        vm.$message.error(error.statusText)
        console.log(error)
      })
    },
    submitCallback () {
      this.$emit('initData')
      this.initData()
    },
    menuTypeChangeEvent (val) {
      if (this.dialog.type === 'post') {
        this.dialog.data.parent_id = ''
      }
      if (val === 'SYS') {
        this.dialog.data.parent_id = 'SYS'
        this.dialog.parentIdData = this.dialog.parentIdCurrentData = []
        return
      }
      let list = []
      this.table.data.map(obj => {
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
          if (length) {
            return
          }
          for (let i = 0; i < data.length; i++) {
            if (val === data[i].parent_id) {
              length = data[data.length - 1].menu_order
            } else if (data[i].hasOwnProperty('children') && data[i].children) {
              fn(data[i].children)
            }
          }
        }
        fn(this.tree.data)
      }
      this.dialog.data.menu_order = Number(length) + 1
    },
    addAlertClick () {
      let parentId = 'SYS'
      this.dialog.data = {
        type: 'SYS',
        parent_id: parentId
      }
      this.parentIdChangeEvent(this.dialog.data.type)
      this.dialog.visible = true
      this.dialog.title = '添加菜单'
      this.dialog.type = 'post'
    },
    putAlert (row) {
      this.dialog.visible = true
      this.dialog.title = '编辑菜单'
      this.dialog.type = 'put'
      this.dialog.data = {
        parent_id: null
      }
      for (var key in row) {
        this.dialog.data[key] = `${row[key] === 'null' || row[key] === null ? '' : row[key]}`
      }
      this.menuTypeChangeEvent(this.dialog.data.type)
    },
    putAlertClick () {
      this.putAlert(this.$store.state.tableCurrentRow)
    },
    remove () {
      let flag = true
      for (let i = 0; i < this.table.data.length; i++) {
        if (this.$store.state.tableCurrentRow.menu_id === this.table.data[i].parent_id) {
          flag = false
          break
        }
      }
      if (flag) {
        let objects = this.$store.state.tableCurrentRow
        this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.state.http.auto('sysMenu', 'delete', { params: objects }).then((response) => {
            this.$message.success('删除成功!')
            this.$emit('initData')
            this.initData()
          })
        })
      } else {
        this.$message.error('此菜单存在子菜单，禁止删除')
      }
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
      return dateFormatterTool(cellValue, 'yyyy-MM-dd hh:mm:ss')
    },
    formatterParent (row, column, cellValue) {
      for (const item of this.table.data) {
        if (item.menu_id === row.parent_id) {
          return item.name
        }
      }
      return '父菜单'
    }
  }
}
</script>