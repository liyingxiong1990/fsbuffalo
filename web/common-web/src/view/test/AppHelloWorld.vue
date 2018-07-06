<template>
  <div class="hello">
    <base-table :tableData="tableData" :tableCols="tableCols" :tablePower="tablePower" :contextMenuData="contextMenuData"></base-table>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'hello',
  computed: {
    ...mapGetters([
      'dict'
    ])
  },
  methods: {
    handleClick (row) {
      console.log(row)
    },
    addEvent () {
      this.tableData = [this.tableData[0], this.tableData[0]]
      console.log(this.dict)
    },
    fn () {
    }
  },
  data () {
    return {
      index: 1,
      tablePower: 2,
      isadd: false,
      tableCols: [
        { label: '日期', prop: 'date', columnFilter: false },
        { label: '姓名', prop: 'name', columnFilter: true },
        { label: '省份', prop: 'province', columnFilter: true },
        { label: '市区', prop: 'city', columnFilter: true },
        { label: '地址', prop: 'address', columnFilter: true },
        { label: '邮编', prop: 'zip', columnFilter: true }],
      contextMenuData: [
        {
          name: `编辑`,
          icon: `glyphicon glyphicon-ok`,
          fnEvent: this.addEvent,
          entitlement: true
        },
        {
          name: `查询`,
          icon: `glyphicon glyphicon-zoom-in`,
          fnEvent: this.fn,
          entitlement: true
        },
        {
          name: `删除`,
          icon: `glyphicon glyphicon-time`,
          fnEvent (event) {
          },
          entitlement: true
        }
      ],
      tableData: [{
        date: '2016-05-01',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200343
      }, {
        date: '2016-05-02',
        name: '赵楠',
        province: '北京',
        city: '普陀区',
        address: '北京市普陀区金沙江路 1518 弄',
        zip: 200383
      }, {
        date: '2016-05-03',
        name: '李四',
        province: '广州',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200353
      }, {
        date: '2016-05-04',
        name: '张珊',
        province: '深圳',
        city: '普陀区',
        address: '上海市普陀区绿沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-05',
        name: '李贵',
        province: '厦门',
        city: '雅鹿区',
        address: '厦门市雅鹿区金沙江路 18 号',
        zip: 200333
      }, {
        date: '2016-05-06',
        name: '赵楠',
        province: '贵港',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-07',
        name: '赵楠',
        province: '东京',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-08',
        name: '李贵',
        province: '西藏',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-09',
        name: '王小虎',
        province: '佛山',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-10',
        name: '王小虎',
        province: '南京',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-11',
        name: '田七',
        province: '大理',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-12',
        name: '赵六',
        province: '桂林',
        city: '平南区',
        address: '桂林市平南区金沙江路 199 号',
        zip: 200333
      }, {
        date: '2016-05-13',
        name: '王小虎',
        province: '汕头',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-14',
        name: '王小虎',
        province: '桂林',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-15',
        name: '王小虎',
        province: '桂林',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-16',
        name: '王小虎',
        province: '桂林',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-17',
        name: '王小虎',
        province: '佛山',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-18',
        name: '王小虎',
        province: '佛山',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-19',
        name: '王小虎',
        province: '南京',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }]
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
