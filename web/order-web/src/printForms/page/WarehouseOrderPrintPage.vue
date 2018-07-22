<template>
  <div class="page-base-info" :class="flag?'flag' : ''">
    <h1 style="text-align:center;">出仓单{{data.id}}</h1>
    <table border="0" border-spacing="0" border-collapse="collapse" cellspacing="0" style="width: 80%; border-color: grey;margin: auto;">
      <tr>
        <td width="20%" class="td">开单日期</td>
        <td width="30%">{{formatterTime(null,null,data.order_date)}}</td>
        <td width="20%" class="td">司机</td>
        <td width="30%">{{data.deliverer}}</td>
      </tr>
      <!-- <tr>
        <td width="20%" class="td">送货日期</td>
        <td width="30%">{{formatterTime(null,null,data.deliver_date)}}</td>
      </tr> -->
    </table>

    <table border="0" border-spacing="0" border-collapse="collapse" cellspacing="0" style="width: 80%; border-color: grey;margin: auto;">
      <tr>
        <td width="40%" class="td">产品</td>
        <td width="10%" class="td">规格</td>
        <td width="10%" class="td">数量</td>
        <td width="10%" class="td">箱数</td>
        <td width="10%" class="td">零头</td>
        <td width="10%" class="td">备注</td>
      </tr>

      <tr v-if="data.itemList[0] && data.itemList[0].quantity>0">
        <td width="40%">{{data.itemList[0].product_name}}</td>
        <td width="10%">1*{{data.itemList[0].product_scale}}</td>
        <td width="10%">{{data.itemList[0].quantity}}</td>
        <td width="10%">{{data.itemList[0].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[0].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[1] && data.itemList[1].quantity>0">
        <td width="40%">{{data.itemList[1].product_name}}</td>
        <td width="10%">1*{{data.itemList[1].product_scale}}</td>
        <td width="10%">{{data.itemList[1].quantity}}</td>
        <td width="10%">{{data.itemList[1].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[1].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[2] && data.itemList[2].quantity>0">
        <td width="40%">{{data.itemList[2].product_name}}</td>
        <td width="10%">1*{{data.itemList[2].product_scale}}</td>
        <td width="10%">{{data.itemList[2].quantity}}</td>
        <td width="10%">{{data.itemList[2].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[2].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[3] && data.itemList[3].quantity>0">
        <td width="40%">{{data.itemList[3].product_name}}</td>
        <td width="10%">1*{{data.itemList[3].product_scale}}</td>
        <td width="10%">{{data.itemList[3].quantity}}</td>
        <td width="10%">{{data.itemList[3].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[3].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="subTotal1>0">
        <td width="40%"></td>
        <td width="10%">小计</td>
        <td width="10%">{{subTotal1}}</td>
      </tr>

      <tr v-if="data.itemList[4] && data.itemList[4].quantity>0">
        <td width="40%">{{data.itemList[4].product_name}}</td>
        <td width="10%">1*{{data.itemList[4].product_scale}}</td>
        <td width="10%">{{data.itemList[4].quantity}}</td>
        <td width="10%">{{data.itemList[4].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[4].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[5] && data.itemList[5].quantity>0">
        <td width="40%">{{data.itemList[5].product_name}}</td>
        <td width="10%">1*{{data.itemList[5].product_scale}}</td>
        <td width="10%">{{data.itemList[5].quantity}}</td>
        <td width="10%">{{data.itemList[5].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[5].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="subTotal2>0">
        <td width="40%"></td>
        <td width="10%">小计</td>
        <td width="10%">{{subTotal2}}</td>
      </tr>

      <tr v-if="data.itemList[6] && data.itemList[6].quantity>0">
        <td width="40%">{{data.itemList[6].product_name}}</td>
        <td width="10%">1*{{data.itemList[6].product_scale}}</td>
        <td width="10%">{{data.itemList[6].quantity}}</td>
        <td width="10%">{{data.itemList[6].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[6].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[7] && data.itemList[7].quantity>0">
        <td width="40%">{{data.itemList[7].product_name}}</td>
        <td width="10%">1*{{data.itemList[7].product_scale}}</td>
        <td width="10%">{{data.itemList[7].quantity}}</td>
        <td width="10%">{{data.itemList[7].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[7].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[8] && data.itemList[8].quantity>0">
        <td width="40%">{{data.itemList[8].product_name}}</td>
        <td width="10%">1*{{data.itemList[8].product_scale}}</td>
        <td width="10%">{{data.itemList[8].quantity}}</td>
        <td width="10%">{{data.itemList[8].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[8].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[9] && data.itemList[9].quantity>0">
        <td width="40%">{{data.itemList[9].product_name}}</td>
        <td width="10%">1*{{data.itemList[9].product_scale}}</td>
        <td width="10%">{{data.itemList[9].quantity}}</td>
        <td width="10%">{{data.itemList[9].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[9].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="subTotal3>0">
        <td width="40%"></td>
        <td width="10%">小计</td>
        <td width="10%">{{subTotal3}}</td>
      </tr>

      <tr v-if="data.itemList[10] && data.itemList[10].quantity>0">
        <td width="40%">{{data.itemList[10].product_name}}</td>
        <td width="10%">1*{{data.itemList[10].product_scale}}</td>
        <td width="10%">{{data.itemList[10].quantity}}</td>
        <td width="10%">{{data.itemList[10].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[10].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[11] && data.itemList[11].quantity>0">
        <td width="40%">{{data.itemList[11].product_name}}</td>
        <td width="10%">1*{{data.itemList[11].product_scale}}</td>
        <td width="10%">{{data.itemList[11].quantity}}</td>
        <td width="10%">{{data.itemList[11].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[11].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[12] && data.itemList[12].quantity>0">
        <td width="40%">{{data.itemList[12].product_name}}</td>
        <td width="10%">1*{{data.itemList[12].product_scale}}</td>
        <td width="10%">{{data.itemList[12].quantity}}</td>
        <td width="10%">{{data.itemList[12].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[12].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[13] && data.itemList[13].quantity>0">
        <td width="40%">{{data.itemList[13].product_name}}</td>
        <td width="10%">1*{{data.itemList[13].product_scale}}</td>
        <td width="10%">{{data.itemList[13].quantity}}</td>
        <td width="10%">{{data.itemList[13].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[13].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[14] && data.itemList[14].quantity>0">
        <td width="40%">{{data.itemList[14].product_name}}</td>
        <td width="10%">1*{{data.itemList[14].product_scale}}</td>
        <td width="10%">{{data.itemList[14].quantity}}</td>
        <td width="10%">{{data.itemList[14].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[14].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[15] && data.itemList[15].quantity>0">
        <td width="40%">{{data.itemList[15].product_name}}</td>
        <td width="10%">1*{{data.itemList[15].product_scale}}</td>
        <td width="10%">{{data.itemList[15].quantity}}</td>
        <td width="10%">{{data.itemList[15].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[15].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="subTotal4>0">
        <td width="40%"></td>
        <td width="10%">小计</td>
        <td width="10%">{{subTotal4}}</td>
      </tr>

      
      
      <tr v-if="data.itemList[16] && data.itemList[16].quantity>0">
        <td width="40%">{{data.itemList[16].product_name}}</td>
        <td width="10%">1*{{data.itemList[16].product_scale}}</td>
        <td width="10%">{{data.itemList[16].quantity}}</td>
        <td width="10%">{{data.itemList[16].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[16].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[17] && data.itemList[17].quantity>0">
        <td width="40%">{{data.itemList[17].product_name}}</td>
        <td width="10%">1*{{data.itemList[17].product_scale}}</td>
        <td width="10%">{{data.itemList[17].quantity}}</td>
        <td width="10%">{{data.itemList[17].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[17].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="subTotal5>0">
        <td width="40%"></td>
        <td width="10%">小计</td>
        <td width="10%">{{subTotal5}}</td>
      </tr>

      
      <tr v-if="data.itemList[18] && data.itemList[18].quantity>0">
        <td width="40%">{{data.itemList[18].product_name}}</td>
        <td width="10%">1*{{data.itemList[18].product_scale}}</td>
        <td width="10%">{{data.itemList[18].quantity}}</td>
        <td width="10%">{{data.itemList[18].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[18].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[19] && data.itemList[19].quantity>0">
        <td width="40%">{{data.itemList[19].product_name}}</td>
        <td width="10%">1*{{data.itemList[19].product_scale}}</td>
        <td width="10%">{{data.itemList[19].quantity}}</td>
        <td width="10%">{{data.itemList[19].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[19].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[20] && data.itemList[20].quantity>0">
        <td width="40%">{{data.itemList[20].product_name}}</td>
        <td width="10%">1*{{data.itemList[20].product_scale}}</td>
        <td width="10%">{{data.itemList[20].quantity}}</td>
        <td width="10%">{{data.itemList[20].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[20].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="data.itemList[21] && data.itemList[21].quantity>0">
        <td width="40%">{{data.itemList[21].product_name}}</td>
        <td width="10%">1*{{data.itemList[21].product_scale}}</td>
        <td width="10%">{{data.itemList[21].quantity}}</td>
        <td width="10%">{{data.itemList[21].number_of_boxes}}</td>
        <td width="10%">{{data.itemList[21].remainder}}</td>
        <td width="10%"></td>
      </tr>
      <tr v-if="subTotal6>0">
        <td width="40%"></td>
        <td width="10%">小计</td>
        <td width="10%">{{subTotal6}}</td>
      </tr>
    </table>
    
    <table border="0" border-spacing="0" border-collapse="collapse" cellspacing="0" style="width: 80%; border-color: grey;margin: auto;">
      <tr>
        <td width="16%">开单人</td>
        <td width="16%"></td>
        <td width="16%">领物人</td>
        <td width="16%"></td>
        <td width="16%">仓管</td>
        <td width="16%"></td>
      </tr>
    </table>
    白色：仓库, 红色：会计, 蓝色：司机, 黄色：保管
  </div>
</template>

<script>
import { dateFormatterTool } from 'gdotc@common/assets/common/common'
export default {
  name: 'BaseHeader',
  components: {
  },
  props: {
  },
  created () {
    let vm = this
    if (this.$route.params && this.$route.params.id) {
      this.$store.state.http.auto('warehouseOrder', 'getById', { params: { id: this.$route.params.id } }).then((res) => {
        this.data = res.data
        console.log(this.data.itemList[0])
        this.flag = !this.flag
      }).catch(error => {
        vm.$message.error('页面初始化失败')
        console.log(error)
      })
    } else {
      this.$message.error('页面初始化失败')
    }
  },
  mounted () {
  },
  computed: {
    subTotal1 () {
      return this.data.itemList[0].quantity + this.data.itemList[1].quantity + this.data.itemList[2].quantity + this.data.itemList[3].quantity
    },
    subTotal2 () {
      return this.data.itemList[4].quantity + this.data.itemList[5].quantity
    },
    subTotal3 () {
      return this.data.itemList[6].quantity + this.data.itemList[7].quantity + this.data.itemList[8].quantity + this.data.itemList[9].quantity
    },
    subTotal4 () {
      return this.data.itemList[10].quantity + this.data.itemList[11].quantity + this.data.itemList[12].quantity + this.data.itemList[13].quantity + this.data.itemList[14].quantity + this.data.itemList[15].quantity
    },
    subTotal5 () {
      return this.data.itemList[16].quantity + this.data.itemList[17].quantity
    },
    subTotal6 () {
      return this.data.itemList[18].quantity + this.data.itemList[19].quantity + this.data.itemList[20].quantity + this.data.itemList[21].quantity
    }
  },
  data () {
    return {
      flag: true,
      id: 210001,
      data: {
        changeList: []
      }
    }
  },
  methods: {
    formatterTime (row, column, cellValue) {
      if (cellValue) {
        return dateFormatterTool(cellValue, 'yyyy-MM-dd')
      } else {
        return '——'
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.page-base-info {
  width: 90%;
  td {
    padding: 10px 1px 10px 1px;
    border: #000000 1px solid;
    font-size: 19px;
    font-weight: bold;
    text-align:center;
    line-height: 3px;
    color: #222;
  }
  .td {
    background: #f5f7fa;
  }
}
</style>

<style lang="scss">
.el-table th > .cell {
  color: #000;
}
</style>
