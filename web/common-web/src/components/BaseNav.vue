<template>
  <div class="BaseNav">
    <el-container>
      <div id="nav-left" style="border-right: 1px solid #e6e6e6;">
        <!-- 根据权限加载菜单 -->
        <el-menu v-if="!navDIYData" :default-active="$route.path" unique-opened router style="overflow: auto;" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
          <el-submenu :index="itemFrist.id" v-for="(itemFrist, index) in navTreeData" v-if="itemFrist" :key="index">
            <template slot="title">
              <i :class="itemFrist.icon"></i>
              <span slot="title">{{ itemFrist.name }}</span>
            </template>
            <i v-for="(itemSecond, key) in itemFrist.nodes" v-if="itemSecond" :key="key">
              <el-menu-item :index="itemSecond.router" v-if="itemSecond.nodes.length === 0 && itemSecond.router != null && itemSecond.router != ''" @click="navClickEvent(itemSecond.router, itemSecond.name)">{{ itemSecond.name }}</el-menu-item>
              <el-submenu :index="itemSecond.id" v-if="itemSecond.nodes.length > 0">
                <template slot="title">{{ itemSecond.name }}</template>
                <el-menu-item :index="itemThird.router" v-for="(itemThird) in itemSecond.nodes" v-if="itemThird  && itemThird.router != null && itemThird.router != ''" :key="itemThird.id" @click="navClickEvent(itemThird.router, itemThird.name)">{{ itemThird.name }}</el-menu-item>
              </el-submenu>
            </i>
          </el-submenu>
        </el-menu>
        <!-- 根据目录资源加载菜单 -->
        <el-menu v-else :default-active="$route.path" unique-opened router class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
          <el-submenu :index="index" v-for="(itemFrist, index) in navTreeData" :key="itemFrist.id">
            <template slot="title">
              <i :class="itemFrist.icon"></i>
              <span slot="title">{{ itemFrist.name }}</span>
            </template>
            <i v-for="(itemSecond) in itemFrist.nodes" :key="itemSecond.id">
              <el-menu-item :index="itemSecond.router" v-if="itemSecond.router!=''" @click="navClickEvent(itemSecond.router, itemSecond.name)">{{ itemSecond.name }}</el-menu-item>
              <el-menu-item-group v-else>
                <span slot="title" :v-if="false">{{ itemSecond.name }}</span>
                <el-menu-item :index="itemThird.router" v-for="(itemThird) in itemSecond.nodes" :key="itemThird.id" @click="navClickEvent(itemThird.router, itemThird.name)">{{ itemThird.name }}</el-menu-item>
              </el-menu-item-group>
            </i>
          </el-submenu>
        </el-menu>
        <i class="el-icon-d-arrow-left el-munu-arrow-left" v-on:click="munuCollapseChange" v-show="!isCollapse"></i>
        <i class="el-icon-d-arrow-right el-munu-arrow-right" v-on:click="munuCollapseChange" v-show="isCollapse"></i>
      </div>
      <el-main>
        <el-tabs v-model="activeTab" style="margin-top: -5px;" type="card" closable @tab-click="tabClick" v-if="tabOptions.length" @tab-remove="tabRemove">
          <el-tab-pane :key="item.name" v-for="(item) in tabOptions" :name="item.route">
            <span slot="label" @contextmenu="tabPaneContextMenu(item)">{{item.name}}</span>
          </el-tab-pane>
          <!-- <keep-alive> -->
          <router-view/>
          <!-- </keep-alive> -->
        </el-tabs>
      </el-main>
    </el-container>
    <div class="tabsContextMenu" v-show="tabsContextMenuShow" :style="{ left: contextMenuLeft + 'px !important', top: contextMenuTop + 'px !important' }" ref="tabsContextMenu">
      <ul v-for="(item, key) in tabsContextMenu" :key="key">
        <li @click="item.fn" :class="{'forbidClick': !item.tabsContextMenuClick}">{{item.name}}</li>
      </ul>
    </div>
    <div v-show="tabsContextMenuShow" @contextmenu="rowContextMenuScreenClick" @click="tabsContextMenuShow = false" style="z-index: 10000; position: fixed; left: 0; top: 0; width: 100%; height: 100%;"></div>
  </div>
</template>

<script>
import { mouseRightKeyStop } from '../assets/common/common'
export default {
  name: 'BaseNav',
  props: {
    navDIYData: {
      type: Object
    },
    // 当前系统菜单ID
    parentId: {
      type: String
    },
    // 传入navDIYData时，才传该值
    parentName: {
      type: String
    }
  },
  data () {
    return {
      activeTab: '/home',
      isCollapse: false,
      // 当前路由地址
      // 限制切换标签数量，为0不限制
      tabOptionCounts: 0,
      navTree: [],
      tabOptions: [
        {
          name: '首页',
          route: '/home'
        }
      ],
      currentTab: {},
      tabsContextMenuShow: true,
      contextMenuTop: 0,
      contextMenuLeft: 10000,
      firstLoadItem: true,
      system: ''
    }
  },
  created () {
    if (this.parentId) {
      this.system = this.parentId
    } else if (this.navDIYData && this.parentName) {
      this.system = this.parentName
    }
    if (this.navDIYData) {
      this.initializeDIYData(this.navDIYData)
    }
  },
  watch: {
    routePath (now, old) {
    }
  },
  computed: {
    routePath () {
      this.navClickEvent(this.$route.path)
      return this.$route.path
    },
    navTreeData () {
      if (this.navDIYData) {
        return this.navDIYData
      }
      if (this.navTree && this.navTree.length > 0) {
        return this.navTree
      }
      let menuList = this.$store.state.menuList
      if (!(menuList && menuList.hasOwnProperty('length') && menuList.length > 1)) {
        return []
      }
      let tree = {}
      let fristNotes = {}
      let secondNotes = {}
      for (let row of menuList) {
        if (row === null || !row.hasOwnProperty('type')) {
          continue
        }
        if (row.type === 'ROOT') {
          if (row.parent_id === this.parentId) {
            tree[row.menu_id] = {
              'id': row.menu_id,
              'name': row.name,
              'icon': row.icon,
              'order': row.menu_order,
              'nodes': []
            }
          }
        } else {
          let notes = secondNotes
          if (row.type === 'PARENT') {
            notes = fristNotes
          }
          let node = {
            'id': row.menu_id,
            'name': row.name,
            'router': row.route,
            'order': row.menu_order,
            'nodes': []
          }
          if (notes.hasOwnProperty(row.parent_id)) {
            notes[row.parent_id][row.menu_id] = node
          } else {
            notes[row.parent_id] = {}
            notes[row.parent_id][row.menu_id] = node
          }
          // 如果路由存在跳转到路由地址
          if (this.firstLoadItem && this.$route.path === row.route) {
            this.navClickEvent(row.route, row.name)
            this.firstLoadItem = false
          }
        }
      }
      let treeArray = []
      for (let key in fristNotes) {
        // 该节点是否存在父节点
        if (tree.hasOwnProperty(key)) {
          // 父节点下的所有子节点
          let fristItems = fristNotes[key]
          for (let fristKey in fristItems) {
            let fristItem = fristItems[fristKey]

            if (secondNotes.hasOwnProperty(fristKey)) {
              let secondItems = secondNotes[fristKey]
              for (let secondKey in secondItems) {
                let secondItem = secondItems[secondKey]
                // 保存所有第二级子菜单
                if (secondItem.hasOwnProperty('order') && secondItem.order) {
                  if (fristItem.nodes.hasOwnProperty(secondItem.order)) {
                    fristItem.nodes.splice(secondItem.order, 0, secondItem)
                  } else {
                    fristItem.nodes[secondItem.order] = secondItem
                  }
                }
              }
            }
            // 保存所有第一级子菜单
            if (fristItem.hasOwnProperty('order') && fristItem.order) {
              if (tree[key].nodes.hasOwnProperty(fristItem.order)) {
                tree[key].nodes.splice(fristItem.order, 0, fristItem)
              } else {
                tree[key].nodes[fristItem.order] = fristItem
              }
            }
          }
          // 保存所有父节点
          if (tree[key].hasOwnProperty('order') && tree[key].order) {
            if (treeArray.hasOwnProperty(tree[key].order)) {
              treeArray.splice(tree[key].order, 0, tree[key])
            } else {
              treeArray[tree[key].order] = tree[key]
            }
          }
        }
      }
      this.navTree = treeArray
      return treeArray
    },
    tabsContextMenu () {
      let arr = [
        {
          name: '关闭标签页',
          fn: this.closeCurrentTab,
          tabsContextMenuClick: true
        },
        {
          name: '关闭其他标签页',
          fn: this.closeOtherTab,
          tabsContextMenuClick: true
        },
        {
          name: '关闭右侧标签页',
          fn: this.closeRightTab,
          tabsContextMenuClick: true
        }
      ]
      if (this.currentTab.route === this.tabOptions[0].route) {
        arr[0].tabsContextMenuClick = false
      }
      if (this.tabOptions.length <= 1) {
        arr[1].tabsContextMenuClick = false
      }
      if (this.currentTab.route === this.tabOptions[this.tabOptions.length - 1].route) {
        arr[2].tabsContextMenuClick = false
      }
      return arr
    }
  },
  methods: {
    changRecordsData (data) {
      let flag = true
      let recordsData = []
      let localStorageKey = `${window.localStorage.getItem(`userid`)} ${this.system} menuList`
      if (window.localStorage.getItem(localStorageKey)) {
        recordsData = [...JSON.parse(window.localStorage.getItem(localStorageKey))]
        for (let [index, item] of recordsData.entries()) {
          if (item && item.hasOwnProperty(`clicks`) && item.route === data.route) {
            item.clicks++
            flag = false
            recordsData.splice(index, 1)
            recordsData.unshift(item)
            break
          }
        }
        recordsData.sort((a, b) => {
          return Number(b.clicks) - Number(a.clicks)
        })
      }
      data.clicks = 1
      if (flag) {
        recordsData.push(data)
      }
      let jsonStr = JSON.stringify([...recordsData])
      window.localStorage.setItem(localStorageKey, jsonStr)
    },
    tabPaneContextMenu (currentTab) {
      mouseRightKeyStop()
      this.currentTab = currentTab
      let obj = this.getMousePosition(this.$refs.tabsContextMenu)
      this.contextMenuTop = obj.y
      this.contextMenuLeft = obj.x
      this.tabsContextMenuShow = true
    },
    closeCurrentTab () {
      mouseRightKeyStop()
      if (this.tabsContextMenu[0].tabsContextMenuClick === true) {
        if (this.currentTab.hasOwnProperty(`route`)) {
          this.tabOptions = this.tabOptions.reduce((res, cur, index, arr) => {
            if (arr[index].route === this.currentTab.route) {
              if (arr.splice) {
                arr.splice(index, 1)
                let currentTab = arr[(index - 1)]
                this.activeTab = currentTab.route
                this.$router.push({ path: currentTab.route })
              }
            }
            return arr
          })
          this.tabsContextMenuShow = false
        }
      }
    },
    closeOtherTab () {
      mouseRightKeyStop()
      if (this.tabsContextMenu[1].tabsContextMenuClick === true) {
        if (this.currentTab.hasOwnProperty(`route`)) {
          let arr = []
          arr.push(this.tabOptions[0])
          for (let item of this.tabOptions) {
            if (item.route === this.currentTab.route) {
              this.activeTab = item.route
              this.$router.push({ path: item.route })
              if (item.route !== this.tabOptions[0].route) {
                arr.push(item)
              }
              this.tabOptions = [...arr]
              break
            }
          }
          this.tabsContextMenuShow = false
        }
      }
    },
    closeRightTab () {
      mouseRightKeyStop()
      if (this.tabsContextMenu[2].tabsContextMenuClick === true) {
        if (this.currentTab.hasOwnProperty(`route`)) {
          let arr = []
          for (let item of this.tabOptions) {
            arr.push(item)
            if (item.route === this.currentTab.route) {
              this.activeTab = item.route
              this.$router.push({ path: item.route })
              this.tabOptions = [...arr]
              break
            }
          }
          this.tabsContextMenuShow = false
        }
      }
    },
    getMousePosition (element, parentElement) {
      if (!parentElement) {
        parentElement = document.documentElement || document.body
      }
      const e = event || window.event
      const parentWidth = parentElement.clientWidth
      const parentHeight = parentElement.clientHeight
      const x = parentWidth - e.clientX > 126 ? e.clientX : parentWidth - 126
      const y = parentHeight - e.clientY > 120 ? e.clientY : parentWidth - 120
      return {
        'x': x,
        'y': y
      }
    },
    rowContextMenuScreenClick (event) {
      mouseRightKeyStop()
      this.tabsContextMenuShow = false
    },
    handleOpen (key, keyPath) {
      // console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      // console.log(key, keyPath)
    },
    munuCollapseChange () {
      this.isCollapse = !this.isCollapse
    },
    tabClick (name) {
      if (name.name !== '/home') {
        this.changRecordsData({
          name: name.$slots.label[0].children[0].text,
          route: name.name
        })
      }
      this.activeTab = name.name
      this.$router.push({ path: name.name })
    },
    tabRemove (targetName) {
      if (this.tabOptions.length <= 1) {
        return
      }
      this.tabOptions = this.tabOptions.reduce((res, cur, index, arr) => {
        if (arr[index].route === targetName) {
          if (arr.splice) {
            arr.splice(index, 1)
            let currentTab = arr[(index - 1)]
            this.activeTab = currentTab.route
            this.$router.push({ path: currentTab.route })
          }
        }
        return arr
      })
    },
    navClickEvent (router, name) {
      if (this.activeTab !== router && name != null && name !== '') {
        this.changRecordsData({
          name: name,
          route: router
        })
      }
      this.activeTab = router
      if (name === '' || name == null) {
        if (this.navDIYData) {
          let fristNotes = (notes) => {
            for (let key in notes) {
              let note = notes[key]
              if (note.hasOwnProperty('router')) {
                if (note.router === router) {
                  name = note.name
                  return
                }
                continue
              } else if (note.hasOwnProperty('nodes')) {
                fristNotes(note.nodes)
              }
            }
          }
          fristNotes(this.navDIYData)
        } else if (this.navTree.length) {
          let fristNotes = (notes) => {
            for (let note of notes) {
              if (note) {
                if (note.hasOwnProperty('router')) {
                  if (note.router === router) {
                    name = note.name
                    return
                  }
                  continue
                } else if (note.hasOwnProperty('nodes') && note.nodes.length) {
                  fristNotes(note.nodes)
                }
              }
            }
          }
          fristNotes(this.navTree)
        }
      }
      if (name === '' || name == null) {
        return
      }
      for (let row of this.tabOptions) {
        if (row.route === router) {
          return
        }
      }
      if (this.tabOptionCounts !== 0 && this.tabOptionCounts === this.tabOptions.length) {
        if (this.tabOptions.splice) {
          this.tabOptions.splice(1, 1)
        }
      }
      this.tabOptions.push({
        name: name,
        route: router
      })
    },
    initializeDIYData (navData) {
      if (this.$route.path === '/' || this.$route.path === '' || typeof this.$route.path !== 'string') {
        return
      }
      // 判断路由格式
      let pathNames = this.$route.path.split('/')
      if (pathNames.length < 3) {
        return
      }
      // 导航配置
      for (let key in navData) {
        // 判断路由根名称
        if (key === pathNames[1]) {
          let parent = navData[key].nodes
          // 递归所有子节点
          let fn = (parent) => {
            for (let key in parent) {
              // 判断子节点名称
              if (key === pathNames[2]) {
                return parent[key]
              }
              fn(parent[key].parent)
            }
          }
          let curRouter = fn(parent)
          // 新增标签页
          // this.tabOptions.push({
          //   name: curRouter.name,
          //   route: curRouter.router
          // })
          this.activeTab = curRouter.router
          return
        }
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.BaseNav {
  overflow: auto;
  height: calc(100% - 38px);
  margin-top: 2px;
  .el-container {
    height: 100%;
    div {
      height: 100%;
    }
  }
  .tabsContextMenu {
    position: fixed;
    top: 0px;
    padding: 5px 0;
    background-color: #ffffff;
    box-shadow: 0 0 1px #cab7b7;
    border: 1px solid #e4e7ed;
    border-radius: 3px;
    box-sizing: border-box;
    font-size: 12px;
    z-index: 10001;
    ul {
      margin: 0;
      padding: 0;
      li {
        text-align: left;
        list-style: none;
        padding: 10px 20px;
        cursor: pointer;
        box-sizing: border-box;
      }
      li:hover {
        background-color: rgba(0, 0, 0, 0.1);
      }
      .forbidClick {
        color: #babbbd;
        cursor: not-allowed;
      }
    }
  }
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 180px;
  height: 100%;
  border-right: 0;
}

@keyframes myfirst {
  50% {
    color: #727171;
    left: 180px;
  }
  100% {
    color: #000000;
    left: 182px;
    font-size: 10px;
    top: calc(50% + 2px);
  }
}

@-webkit-keyframes myfirst {
  50% {
    color: #727171;
    left: 180px;
  }
  100% {
    color: #000000;
    left: 182px;
    font-size: 10px;
    top: calc(50% + 2px);
  }
}

.el-munu-arrow-left {
  left: 180px;
  animation: myfirst 1s linear 0s infinite alternate;
  -webkit-animation: myfirst 1s linear 0s infinite alternate;
}

.el-munu-arrow-left,
.el-munu-arrow-right {
  font-size: 12px;
  color: #727171;
  cursor: pointer;
  position: fixed;
  top: 50%;
}

@keyframes arrowRightAnimate {
  50% {
    color: #727171;
    left: 64px;
  }
  100% {
    color: #000000;
    left: 66px;
    font-size: 10px;
    top: calc(50% + 2px);
  }
}

@-webkit-keyframes arrowRightAnimate {
  50% {
    color: #727171;
    left: 64px;
  }
  100% {
    color: #000000;
    left: 66px;
    font-size: 10px;
    top: calc(50% + 2px);
  }
}

.el-munu-arrow-right {
  left: 64px;
  animation: arrowRightAnimate 1s linear 0s infinite alternate;
  -webkit-animation: arrowRightAnimate 1s linear 0s infinite alternate;
}
.el-tabs__content {
  height: 100%;
}
.el-main {
  padding: 3px 10px 0 10px;
}
.el-submenu .el-menu-item {
  overflow: hidden;
  text-overflow: ellipsis;
  padding-left: 0px !important;
  padding-right: 0 !important;
  width: 100%;
  text-align: center;
  min-width: 0;
}
</style>

<style lang="scss" >
.BaseNav {
  .el-tabs__header {
    margin: 0 0 10px;
  }
  .el-tabs__item {
    height: 28px;
    line-height: 28px;
  }
  .el-submenu__title {
    span {
      vertical-align: -2px;
      font-weight: bold;
      color: #626262;
    }
  }
}
</style>
