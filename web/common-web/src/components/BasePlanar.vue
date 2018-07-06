<template>
  <div class="base-planar">
    <div v-for="(item,key) of list" :key="key">
      <ul>
        <li class="box-title">
          <i :class="item.icon" style="margin-right: 5px;"></i>{{item.name}}</li>
        <li class="box-content" v-for="(childrenItem, key) of item.children" :key="key" @click="changeRouterPath(childrenItem)">{{childrenItem.name}}</li>
        <div style="clear:both"></div>
      </ul>
      <div style="clear:both"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BasePlanar',
  props: {
    /**
     * menuData
     * 请求获取数据
     * {object}
     * e.g. {
     *   data：[],  // 数据
     *   parentId:''  // 根目录menu_id
     * }
    */
    menuData: {
      type: Object
    },
    /**
     * fileData
     * 目录资源数据
     * {object}
    */
    fileData: {
      type: Object
    },
    // 传入fileData时，才传该值
    parentNmae: {
      type: String
    },
    /**
     * maxNumber
     * 展示列表第一个的最大个数(默认为10个)
     * Number
    */
    maxNumber: {
      type: Number,
      default: 10
    }
  },
  created () {
    if (this.menuData.parentId) {
      this.system = this.menuData.parentId
    } else if (this.fileData && this.parentNmae) {
      this.system = this.parentNmae
    }
    // 获取浏览器缓存的用户对应的menuList
    this.localStorageKey = `${window.localStorage.getItem(`userid`)} ${this.system} menuList`
    if (window.localStorage.getItem(this.localStorageKey)) {
      let localMenuList = JSON.parse(window.localStorage.getItem(this.localStorageKey))
      let obj = {}
      // 获取拥有权限的菜单
      if (this.menuData && this.menuData.hasOwnProperty(`data`)) {
        for (let item of this.menuData.data) {
          if (item.route !== '' || item.route !== null) {
            obj[item.route] = item
          }
        }
      } else if (this.fileData) {
        let fn = data => {
          for (let key in data) {
            for (let nodesKey in data[key].nodes) {
              if (data[key].nodes[nodesKey].hasOwnProperty(`router`)) {
                obj[data[key].nodes[nodesKey].router] = data[key].nodes[nodesKey]
              }
            }
            if (data[key].nodes.hasOwnProperty(`name`)) {
              fn(data[key].nodes)
            }
          }
        }
        fn(this.fileData)
      }
      // 过滤localStorage中的菜单列表，保留拥有权限的菜单，并更新到localStorage
      localMenuList = localMenuList.filter(item => {
        return obj.hasOwnProperty(item.route)
      })
      let jsonStr = JSON.stringify(localMenuList)
      window.localStorage.setItem(this.localStorageKey, jsonStr)
    }
    if (this.menuData && this.menuData.hasOwnProperty(`data`)) {
      this.initMenuData()
    } else if (this.fileData) {
      this.initFileData()
    }
  },
  computed: {
  },
  data () {
    return {
      list: [],
      localMenuList: [],
      system: '',
      localStorageKey: ''
    }
  },
  methods: {
    // 格式化请求获取的数据成树形结构
    formatTreeData (data, parentId) {
      let children = []
      for (let node of data) {
        if (node.hasOwnProperty('parent_id') && node.parent_id === parentId) {
          node.children = this.formatTreeData(data, node.menu_id)
          children.push(node)
        }
      }
      // 菜单按menu_order由大到小排序
      let sortFn = (data) => {
        data.sort((a, b) => {
          return Number(a.menu_order) - Number(b.menu_order)
        })
        data.map(obj => {
          if (obj.hasOwnProperty('children') && obj.children.length) {
            sortFn(obj.children)
          }
        })
      }
      sortFn(children)
      return children
    },
    // 格式化目录资源数据
    formatFileData (fileData) {
      let obj = Object.assign({}, fileData)
      let list = []
      let fn = data => {
        for (let key in data) {
          data[key].children = []
          for (let nodesKey in data[key].nodes) {
            data[key].nodes[nodesKey].route = data[key].nodes[nodesKey].router
            data[key].children.push(data[key].nodes[nodesKey])
          }
          if (data[key].nodes.hasOwnProperty(`name`)) {
            fn(data[key].nodes)
          }
        }
      }
      fn(obj)
      for (let key in obj) {
        list.push(obj[key])
      }
      return list
    },
    changeRouterPath (data) {
      let menuList = JSON.parse(window.localStorage.getItem(this.localStorageKey))
      let obj = { name: data.name, route: data.route }
      if (menuList) {
        let flag = true
        // 为localStorage存在的菜单的点击次数+1
        for (let [index, item] of menuList.entries()) {
          if (item && item.hasOwnProperty(`clicks`) && item.route === obj.route) {
            item.clicks++
            flag = false
            menuList.splice(index, 1)
            menuList.unshift(item)
            break
          }
        }
        // 按点击次数由多到少排序
        menuList.sort((a, b) => {
          return Number(b.clicks) - Number(a.clicks)
        })
        obj.clicks = 1
        if (flag) {
          menuList.push(obj)
        }
      } else {
        obj.clicks = 1
        menuList = []
        menuList.push(obj)
      }
      // 将改变后的菜单列表存到localStorage
      let jsonStr = JSON.stringify(menuList)
      window.localStorage.setItem(this.localStorageKey, jsonStr)
      this.$router.push(data.route)
    },
    // 初始化请求获取的数据
    initMenuData () {
      let menuData = [...this.menuData.data]
      menuData = this.formatTreeData(menuData, 'SYS')
      if (window.localStorage.getItem(this.localStorageKey)) {
        let menuList = JSON.parse(window.localStorage.getItem(this.localStorageKey))
        if (menuList.length) {
          // 保留最多maxNumber个展示出来
          menuList = JSON.parse(window.localStorage.getItem(this.localStorageKey)).slice(0, this.maxNumber)
          this.list.push({
            name: `最近访问`,
            icon: `glyphicon glyphicon-heart`,
            children: menuList
          })
        }
      }
      for (let SysObj of menuData) {
        if (SysObj.menu_id === this.menuData.parentId) {
          for (let RootObj of SysObj.children) {
            let arr = []
            let fn = (data) => {
              for (let item of data) {
                if (item.children.length) {
                  fn(item.children)
                } else {
                  arr.push(item)
                }
              }
            }
            fn(RootObj.children)
            RootObj.children = arr
          }
          this.list = [...this.list, ...SysObj.children]
        }
      }
    },
    // 初始化目录资源数据
    initFileData () {
      if (window.localStorage.getItem(this.localStorageKey)) {
        let menuList = JSON.parse(window.localStorage.getItem(this.localStorageKey))
        if (menuList.length) {
          // 保留最多maxNumber个展示出来
          menuList = JSON.parse(window.localStorage.getItem(this.localStorageKey)).slice(0, this.maxNumber)
          this.list.push({
            name: `最近访问`,
            icon: `glyphicon glyphicon-heart`,
            children: menuList
          })
        }
      }
      this.list = [...this.list, ...this.formatFileData(this.fileData)]
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.base-planar {
  width: 100%;
  min-width: 280px;
  ul {
    width: 70%;
    max-width: 980px;
    min-width: 280px;
    margin: auto;
    padding: 20px 0;
    border-bottom: 1px solid #e4e7ed;
    box-sizing: border-box;
    overflow: auto;
    li {
      list-style-type: none;
      box-sizing: border-box;
      cursor: pointer;
    }
    .box-title {
      min-width: 140px;
      text-align: left;
      padding: 20px 0;
      font-weight: 700;
      color: #524a4a;
      cursor: default;
    }
    .box-content {
      float: left;
      // padding: 0 0 20px 0;
      width: 140px;
      line-height: 40px;
      text-align: left;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      &:hover {
        color: #000;
        font-weight: 600;
      }
    }
  }
}
</style>

<style lang="scss">
</style>