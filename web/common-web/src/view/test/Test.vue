<template>
  <div class="user">
    <!-- <h3 >
      test
    </h3>
    
    <button @click="getAll">查询所有</button>
    <button @click="getById">查询</button>
    <button @click="add">新增</button>
    <button @click="update">修改</button>
    <button @click="deleteObj">删除</button>
    <p>
      {{ datas }}
    </p>

    <h3 >
      dict
    </h3>
    <button @click="getDictAll">查询所有</button> -->
     <base-map :mapDatas="mapData"></base-map>
  </div>
</template>

<script>
export default {
  name: 'user',
  data () {
    return {
      datas: [],
      // { value: 挂牌企业数， name：'市名或区镇名'}
      // 实例
      mapData: [
        { value: 300, name: '广州市' },
        { value: 500, name: '佛山市' },
        { value: 150, name: '肇庆市' },
        { value: 200, name: '东莞市' },
        { value: 400, name: '深圳市' },
        { value: 200, name: '三水区' },
        { value: 150, name: '南海区' },
        { value: 400, name: '禅城区' },
        { value: 100, name: '高明区' },
        { value: 400, name: '顺德区' }
      ]
    }
  },
  methods: {
    getAll () {
      this.$store.state.http.get('/sys/department').then((response) => {
        this.datas = response
      }).catch((error) => {
        this.datas = error
        console.log(error)
      })
    },
    getById () {
      // this.$store.state.http.get('/sys/department/{id}', { params: { id: '1' } }).then((response) => {
      //   this.datas = response
      // })
      this.$store.state.http.auto('sysDict', 'getListByDictType', { params: { dict_type: 'bank_ent_flag' } }).then((response) => {
        this.datas = response
      })
    },
    add () {
      // let user = `{ "id": 1, "code": "0003", "d_name": "股权交易中心测试测试侧sad", "pid": 0, "d_state": 2, "remark": "asdasdasd", "create_time": 1515117652000 }`
      let dict = `{ "dict_type": "bank_ent_flag", "dict_type_desc": "是否开通银企", "dict_code": "2", "dict_value": "未开通", "sort": 1, "remark": "测试测测测" }`
      this.$store.state.http.auto('sysDict', 'add', { data: dict }).then((response) => {
        this.datas = response
      })
    },
    update () {
      // let user = `{ "id": 6, "code": "0001", "d_name": "股权交易中心试试阿萨德", "pid": 0, "d_state": 1, "remark": "ASDASDASD", "create_time": 1515117652000 }`
      let dict = `{ "id": 3, "dict_type": "bank_ent_flag", "dict_type_desc": "是否开通银企", "dict_code": "2", "dict_value": "未开通", "sort": 0, "remark": "测试啊啊啊" }`
      this.$store.state.http.auto('sysDict', 'update', { data: dict }).then((response) => {
        this.datas = response
      })
    },
    deleteObj () {
      this.$store.state.http.auto('sysDict', 'delete', { params: { id: '3' } }).then((response) => {
        this.datas = response
      })
    },
    getDictAll () {
      this.$store.state.http.get('/sys/dict').then((response) => {
        this.datas = response
      }).catch((error) => {
        this.datas = error
        console.log(error)
      })
    },
    delUser () {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.user {
  text-align: left;
  padding-left: 20px;
}
</style>
