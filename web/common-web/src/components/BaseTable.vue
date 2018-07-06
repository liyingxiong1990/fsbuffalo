<template>
  <div class="BaseTable" ref="BaseTable">
    <div style="margin-button: 5px; overflow: auto; padding: 10px 10px 10px 10px;">
      <div style="float: left;" ref="tableHandLeft">
        <el-input size="mini" :placeholder="tableHandLeftPlaceholder?tableHandLeftPlaceholder:'请输入查询内容'" suffix-icon="el-icon-search" class="input-with-select" v-model="filterValue" @change="globalSearch">
          <template slot="prepend" v-if="!(this.pageQuery && this.pageQuery.apiModule && this.pageQuery.apiMethod)">
            <el-select size="mini" style="width: 70px;" v-model="filterSelected" placeholder="">
              <el-option label="全部" value="all"></el-option>
              <el-option v-for="(item, key) in tableCols" :label="item.label" :key="key" :value="item.prop"></el-option>
            </el-select>
          </template>
        </el-input>
      </div>
      <div class="table-hand-right" ref="tableHandRight">
        <div class="table-hand-right-button" v-for="(item, index) in handRightBotton" :key="index" v-if="item.entitlement" @click="item.fn">
          <i :class="item.icon"></i>
          <span>{{item.name}}</span>
        </div>
        <div class="table-hand-right-button" v-if="typeof addTableRowClick === 'function'" @click="addTableRowClickEvent">
          <i class="el-icon-circle-plus-outline"></i>
          <span>添加</span>
        </div>
        <el-popover ref="popoverExport" placement="bottom-end" width="200" trigger="click" v-model="popoverVisible">
          <el-form ref="exportForm" :model="exportForm" label-width="80px">
            <el-form-item label="导出数据">
              <el-radio v-model="exportForm.data" label="all">所有</el-radio>
              <el-radio v-model="exportForm.data" label="show" style="margin-left: 6px;">显示</el-radio>
            </el-form-item>
            <el-form-item label="文件类型">
              <el-select v-model="exportForm.type" placeholder="请选择文件类型" size="mini">
                <el-option label="xlsx" value="xlsx"></el-option>
                <el-option label="xlsm" value="xlsm"></el-option>
                <el-option label="xlml" value="xlml"></el-option>
                <el-option label="ods" value="ods"></el-option>
                <el-option label="fods" value="fods"></el-option>
                <el-option label="csv" value="csv"></el-option>
                <el-option label="html" value="html"></el-option>
                <el-option label="txt" value="txt"></el-option>
              </el-select>
            </el-form-item>
            <div style="float: right">
              <el-button type="primary" size="small" @click="submitExport">确 定</el-button>
            </div>
          </el-form>
        </el-popover>
        <div class="table-hand-right-button" v-popover:popoverExport @click="popoverVisible=!popoverVisible">
          <i class="el-icon-download"></i>
          <span>导出</span>
        </div>
        <div class="table-hand-right-button">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link table-hand-right-button">
              <i class="el-icon-view"></i>
              <span>筛选</span>
            </span>
            <el-dropdown-menu slot="dropdown">
              <li class="dropdown-column-li-main dropdown-column-li">
                <el-checkbox @change="showAllColumnsChange">全选</el-checkbox>
              </li>
              <el-checkbox-group v-model="showColumns">
                <li class="dropdown-column-li" v-for="item in tableCols" :key="item.label">
                  <el-checkbox :label="item.prop">{{item.label}}</el-checkbox>
                </li>
              </el-checkbox-group>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <el-table ref="singleTable" v-loading="tableLoadingData" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)" :height="tableHeight" border highlight-current-row @current-change="handleCurrentRowChange" @row-contextmenu="rowContextMenuShowEvent" @row-dblclick="rowContextdblClickEvent" @row-click="rowContextMenuCloseEvent" :data="curPage" style="width: auto; height: 100%; ">
      <el-table-column v-for="(item, key) in tableCols" :width="item.width" :min-width="item.minwidth" :formatter="item.formatter" v-if="showColumns.indexOf(item.prop)>=0" :key="key" :prop="item.prop" :label="item.label" sortable header-align="center" style="padding: 0 0;">
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="55" v-if="operationCols && operationCols.length > 0">
        <template slot-scope="scope">
          <el-button v-for="(item, index) in operationCols" :key="index" @click="item.click(scope.row)" type="text" size="small">{{item.label}}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :page-size="pagesize" :total="total" :page-sizes="[5, 10, 20, 50]" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :layout="pageLayout" :page-count='pageCount'>
    </el-pagination>
    <div class="baseTablerowContextMenu" :style="{ left: rowContextMenuLeft + ' !important', top: rowContextMenuTop + ' !important', 'z-index': '9999' }" v-show="rowContextMenuShow" ref="baseTablerowContextMenu">
      <ul>
        <li v-for="(item, index) in contextMenuData" v-if="item.entitlement" :key="index" @click="baseTablerowContextMenuClick(item.fnEvent)">
          <i :class="item.icon"></i>{{ item.name }}</li>
      </ul>
    </div>
    <div v-show="rowContextMenuShow" @contextmenu="rowContextMenuScreenClick" @click="rowContextMenuShow = false" style="z-index: 9998; position: fixed; left: 0; top: 0; width: 100%; height: 100%;"></div>
  </div>
</template>

<script>
import { fileExport } from '../assets/common/uploadCommon'
import { mouseRightKeyStop } from '../assets/common/common'
export default {
  name: 'BaseTable',
  props: {
    /**
     * @augments tableLoading
     * 表格加载提示
     */
    tableLoading: {
      type: Boolean,
      default: false
    },
    /**
     * @augments operationCols 附加操作按钮
     * e.g.
     * [
     *    { label: '撤单', click: this.cancelOrder }
     * ]
     * @param label 显示名称
     * @param click 单击事件
     */
    operationCols: {
      type: Array
    },
    /**
     * tableData
     * 表格所有数据
     * [array]
    */
    tableData: {
      required: false
    },
    /**
     * tableCols
     * 表头列
     * {object}
     * e.g. {
     *        label: 'colName', // 显示列名
     *        prop: 'dataKey', // 数据列名
     *        columnFilter: boolean, // 是否可筛选
     *        formatter: Function, // 格式化数据方法
     *        width: number/String, // 列宽度，默认自适应
     *        winwidth: number/String, // 列最小宽度，默认自适应
     *        display: boolean // 默认为true
     *      }
     */
    tableCols: {
      type: Array,
      required: true
    },
    /**
     * contextMenuData
     * 行单击鼠标右键激活功能目录
     * [array]
     * e.g. [{
     *        name: string, // 显示名称
     *        icon: stinrg[Glyphicons icon], // 显示按钮
     *        fn: function, // 单击事件
     *        entitlement: boolean //是否显示
     *      }]
     * this.$store.state.tableCurrentRow // 执行fn方法时，获取当前行数据
     */
    contextMenuData: {
      type: Array
    },
    /**
     * rowContextdblClick
     * 行双击事件
     * {Function}
     * (row) 当前行数据
     */
    rowContextdblClick: {
      type: Function
    },
    /**
     * rowContextdblClick
     * 行单击事件
     * {Function}
     * (row) 当前行数据
     */
    rowContextClick: {
      type: Function
    },
    /**
     * addTableRowClick
     * 创建新数据单击事件
     * {Function}
    */
    addTableRowClick: {
      type: Function
    },
    // 表格高度按像素微调
    heightNegative: {
      type: Number,
      default: 177
    },
    /**
     * handRightBotton
     * 头部按钮
     * [array]
     * e.g. [{
     *        name: string, // 显示名称
     *        icon: stinrg[Glyphicons icon], // 显示按钮
     *        fn: function, // 单击事件
     *        entitlement: boolean //是否显示
     *      }]
     */
    handRightBotton: {
      type: Array
    },
    pageLayout: {
      type: String,
      default: 'total, sizes, prev, pager, next, jumper'
    },
    /**
     * rowContextMenuFn
     * 改变右键功能名称
     * {Function}
    */
    rowContextMenuFn: {
      type: Function
    },
    /**
     * pageQuery 后台分页查询
      default: {
        apiModule: null, // API模块名称
        apiMethod: null, // API方法名称
        reload: true, // 重新加载数据
        autoLoadingQuery: false // 自动初始化数据，默认为ture
      }
    */
    pageQuery: {
      type: Object
    },
    tableHandLeftPlaceholder: {
      type: String
    }
  },
  data () {
    return {
      tableLoadingData: this.tableLoading,
      isSearch: true,
      curPageData: [],
      queryData: {},
      total: 0,
      // 当前页数
      currentPage: 1,
      // 一页显示的数据
      pagesize: 10,
      // 总页数
      pageCount: 0,
      popoverVisible: false,
      exportForm: {
        data: 'all',
        type: 'xlsx'
      },
      data: [],
      tableColsSearch: [],
      rowContextMenuShow: false,
      rowContextMenuStyle: {},
      rowContextMenuLeft: '-1000px',
      rowContextMenuTop: '',
      filterSelected: 'all',
      filterValue: '',
      dropdownHideOnClick: false,
      showColumns: [],
      showColumn: true,
      tableHeight: window.document.body.clientHeight - this.heightNegative,
      tableIf: true,
      currentRow: null,
      dialogData: this.dialog
    }
  },
  created () {
    // if cols.display === false, 不显示该列
    this.showColumns = []
    this.tableCols.map(col => {
      if (col.display === false) {
      } else {
        this.showColumns.push(col.prop)
      }
    })
    this.$store.commit('SYS_GET_PERMISSION')
  },
  mounted () {
    this.tableHeightResize()
    window.onresize = this.tableHeightResize
  },
  computed: {
    curPage: {
      get () {
        if (this.pageQuery && this.pageQuery.apiModule && this.pageQuery.apiMethod) {
          if (this.currentPage !== this.queryData.pageNum || this.pagesize !== this.queryData.pageSize || this.pageQuery.reload || this.isSearch) {
            this.pageQuery.reload = false
            this.isSearch = false
            let paramsData = {
              keyword: this.filterValue,
              pageNum: this.currentPage,
              pageSize: this.pagesize
            }
            if (this.pageQuery.params) {
              Object.assign(paramsData, this.pageQuery.params)
            }
            if (this.pageQuery.hasOwnProperty('autoLoadingQuery') && this.pageQuery.autoLoadingQuery === false) {
              this.pageQuery.autoLoadingQuery = true
              this.currentPage = 0
              this.pagesize = 10
              this.total = 0
              this.queryData = []
              this.curPage = []
              this.curPageData = []
              this.tableLoadingData = false
              return []
            }
            this.$store.state.http.auto(this.pageQuery.apiModule, this.pageQuery.apiMethod, { params: paramsData }).then(res => {
              const data = res.data
              if (res) {
                this.currentPage = data.pageNum
                this.pagesize = data.pageSize
                this.total = data.total
                this.queryData = data
                this.curPage = data.list
                this.curPageData = data.list
                this.tableLoadingData = false
                return data.list
              }
            }).catch(error => {
              this.tableLoadingData = false
              if (error) {
                // this.$message.error(error.statusText)
                console.log(error)
              }
            })
          }
          return this.curPageData
        } else {
          let curData = this.curData
          if (curData && curData.hasOwnProperty('length')) {
            this.total = this.curData.length
            // this.pageCount = Math.ceil(this.curData.length / this.pagesize)
            return this.curData.slice((this.currentPage - 1) * this.pagesize, this.currentPage * this.pagesize)
          } else {
            this.total = 0
            return []
          }
        }
      },
      set (val) {
        this.curPageData = val
      }
    },
    curData () {
      return this.globalSearch()
    }
  },
  watch: {
    tableLoading () {
      this.tableLoadingData = this.tableLoading
    }
  },
  methods: {
    handleCurrentRowChange (current) {
      this.currentRow = current
    },
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
    },
    handleSizeChange (size) {
      this.pagesize = size
    },
    globalSearch () {
      if (this.pageQuery && this.pageQuery.apiModule && this.pageQuery.apiMethod) {
        this.isSearch = true
      } else {
        let filterColumns = [] // 过滤列
        let formatterColumnsFn = {} // 格式化列
        filterColumns = filterColumns.concat(this.filterSelected)
        if (typeof filterColumns !== 'object' || filterColumns.indexOf('all') > -1) {
          filterColumns = []
          this.tableCols.map(key => {
            filterColumns.push(key.prop)
            if (key.hasOwnProperty('formatter')) {
              if (key.formatter) {
                formatterColumnsFn[key.prop] = key.formatter
              }
            }
          })
        }
        return this.filter(formatterColumnsFn, this.filterValue, this.tableData ? this.tableData : this.queryData.list, filterColumns)
      }
    },
    filter (formatterColumnsFn, value, data, columns) {
      let rows = []
      if (value === null || value === '') {
        return data
      }
      data.map((row, index, array) => {
        for (let key of columns) {
          let val = row
          if (key !== null && key !== '') {
            // 解析嵌套数据
            key.split('.').map(keyName => {
              val = val[keyName]
            })
          } else {
            continue
          }
          if (val === null || val === '') {
            continue
          }
          if (formatterColumnsFn.hasOwnProperty(key)) {
            val = formatterColumnsFn[key](row, key, val)
          }
          if ((val + '').indexOf(value) > -1) {
            rows.push(row)
            break
          }
        }
      })
      return rows
    },
    rowContextMenuShowEvent (row) {
      this.mouseRightKeyStop()
      if (this.rowContextMenuFn) {
        this.rowContextMenuFn(row)
      }
      if (this.rowContextClick) {
        this.rowContextClick(row)
      }
      this.rowContextMenuShow = true
      let obj = this.getMousePosition(this.$refs.baseTablerowContextMenu)
      this.rowContextMenuTop = obj.y + 'px'
      this.rowContextMenuLeft = obj.x + 'px'
      this.$store.state.tableCurrentRow = {}
      Object.assign(this.$store.state.tableCurrentRow, row)
    },
    getMousePosition (element, parentElement) {
      if (!parentElement) {
        parentElement = document.documentElement || document.body
      }
      let parentWidth = parentElement.clientWidth
      let parentHeight = parentElement.clientHeight
      let x = parentWidth - window.event.clientX > element.clientWidth ? window.event.clientX : window.event.clientX - element.clientWidth
      let y = parentHeight - window.event.clientY > element.clientHeight ? window.event.clientY : window.event.clientY - element.clientHeight
      return {
        'x': x,
        'y': y
      }
    },
    rowContextMenuCloseEvent (row) {
      this.$store.state.tableCurrentRow = row
      if (this.rowContextClick) {
        this.rowContextClick(row)
      }
      this.rowContextMenuShow = false
    },
    showAllColumnsChange (checked) {
      if (checked) {
        this.showColumns = []
        this.tableCols.map(key => {
          this.showColumns = this.showColumns.concat(key.prop)
        })
      } else {
        this.showColumns = []
      }
    },
    baseTablerowContextMenuClick (fn) {
      fn()
      this.rowContextMenuCloseEvent()
    },
    rowContextdblClickEvent (row) {
      if (this.rowContextdblClick) {
        this.rowContextdblClick(row)
      }
    },
    addTableRowClickEvent () {
      if (this.addTableRowClick) {
        this.addTableRowClick()
      }
    },
    tableHeightResize () {
      if (this.$refs && this.$refs.BaseTable && (this.$refs.BaseTable.clientWidth < this.$refs.tableHandRight.clientWidth + this.$refs.tableHandLeft.clientWidth)) {
        this.tableHeight = window.document.body.clientHeight - this.heightNegative - this.$refs.tableHandRight.offsetHeight + 2
      } else {
        this.tableHeight = window.document.body.clientHeight - this.heightNegative
      }
    },
    fileExport: fileExport,
    submitExport () {
      // let data = this.exportForm
      if (this.exportForm.data === 'all') {
        if (this.tableData) {
          this.fileExportByData(this.tableData)
        } else {
          if (this.pageQuery && this.pageQuery.apiModule && this.pageQuery.apiMethod) {
            let paramsData = {
              keyword: this.filterValue,
              pageSize: this.total
            }
            if (this.pageQuery.params) {
              Object.assign(paramsData, this.pageQuery.params)
            }
            this.$store.state.http.auto(this.pageQuery.apiModule, this.pageQuery.apiMethod, { params: paramsData }).then(res => {
              const data = res.data
              if (res) {
                this.fileExportByData(data.list)
              }
            }).catch(error => {
              if (error) {
                console.log(error)
              }
            })
          }
        }
      } else {
        this.fileExportByData(this.curPage)
      }
    },
    fileExportByData (data) {
      if (!(data.hasOwnProperty('length') && data.length > 0)) {
        this.$message.error('对不起，当前没有数据！！！导出失败')
        return
      }
      let jsonResult = []
      data.map((row, key) => {
        let json = {}
        this.tableCols.map(item => {
          let value = row
          if (item.prop !== null && item.prop !== '') {
            // 解析嵌套数据
            item.prop.split('.').map(keyName => {
              value = value[keyName]
            })
            if (item.formatter) {
              value = item.formatter(row, item.prop, value)
            }
          } else {
            value = ''
          }

          json[item.label] = value
        })
        jsonResult.push(json)
      })
      let exportName = this.$route.path.split('/')
      if (exportName.length >= 1) {
        exportName = exportName[exportName.length - 1]
      }
      this.fileExport(jsonResult, exportName, this.exportForm.type)
    },
    rowContextMenuScreenClick (event) {
      this.mouseRightKeyStop()
      this.rowContextMenuShow = false
    },
    mouseRightKeyStop: mouseRightKeyStop
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.table-select-all {
  padding-left: 14px;
  padding-bottom: 7px;
  border-bottom: 1px dashed #dcdfe6;
}

.dropdown-column-li {
  list-style: none;
  padding-left: 5px;
  padding-right: 5px;
  padding-bottom: 5px;
}

.dropdown-column-li:last-child {
  padding-bottom: 0;
}

.BaseTable {
  box-sizing: border-box;
  border: 1px solid rgba(160, 154, 154, 0.308);
  border-radius: 4px;
  .table-hand-right {
    float: right;
    line-height: 28px;
    .table-hand-right-button {
      display: inline-block;
      padding-left: 5px;
      cursor: pointer;
      i {
        font-size: 20px;
        vertical-align: middle;
      }
      span {
        vertical-align: middle;
      }
    }
  }
  .el-pagination {
    margin-top: 10px;
  }
  width: 100%;
  .baseTablerowContextMenu {
    position: fixed;
    top: 0px;
    background: #e6e6e6;
    box-shadow: 0 0 3px #cab7b7;
    border-radius: 3px;
    ul {
      margin: 0;
      padding: 0;
      li {
        text-align: left;
        list-style: none;
        padding: 5px;
        cursor: pointer;
        i {
          padding-left: 3px;
          padding-right: 5px;
        }
      }
    }
  }
  .base-table-row-ul {
    padding: 0;
    margin: 0;
    li {
      float: left;
      padding-right: 3px;
      list-style: none;
    }
  }
}
</style>

<style lang="scss">
.BaseTable {
  .el-table th {
    padding: 0;
  }
  .el-table td {
    padding: 1px 0;
  }

  .el-table .cell {
    white-space: nowrap;
    color: #626262;
  }
  .el-table th > .cell {
    white-space: inherit;
  }
  .table-hand-right-button {
    color: #909399;
  }
  .el-pagination__editor.el-input .el-input__inner {
    height: 22px;
  }
  .el-pagination .el-select .el-input .el-input__inner {
    height: 22px;
  }
  .el-pagination {
    margin-top: 5px;
    padding-bottom: 5px;
  }
}
</style>
