<template>
  <div class="page-base-info" :class="flag?'flag' : ''">
    <h1>进仓单{{data.id}}</h1>
    <table border="0" border-spacing="0" border-collapse="collapse" cellspacing="0" style="width: 87%; border-color: grey;margin: auto;">
      <tr>
        <td width="20%" class="td">单号</td>
        <td width="30%">{{data.id}}</td>
        <td width="20%" class="td">进仓日期</td>
        <td width="30%">{{formatterTime(null,null,data.checkin_date)}}</td>
      </tr>
      <tr>
        <td width="20%" class="td">仓管</td>
        <td width="30%">{{data.carrier}}</td>
        <td width="20%" class="td">缴仓</td>
        <td width="30%">{{data.in_order_recorder}}</td>
      </tr>
    </table>

    <table border="0" border-spacing="0" border-collapse="collapse" cellspacing="0" style="width: 87%; border-color: grey;margin: auto;">
      <tr>
        <td width="22%" class="td">产品</td>
        <td width="15%" class="td">规格</td>
        <td width="15%" class="td">数量</td>
        <td width="15%" class="td">备注</td>
      </tr>
      <tr v-if="data.itemList[0] && data.itemList[0].quantity>0">
        <td width="22%">{{data.itemList[0].product_name}}</td>
        <td width="15%">1*{{data.itemList[0].product_scale}}</td>
        <td width="15%">{{data.itemList[0].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[1] && data.itemList[1].quantity>0">
        <td width="22%">{{data.itemList[1].product_name}}</td>
        <td width="15%">1*{{data.itemList[1].product_scale}}</td>
        <td width="15%">{{data.itemList[1].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[2] && data.itemList[2].quantity>0">
        <td width="22%">{{data.itemList[2].product_name}}</td>
        <td width="15%">1*{{data.itemList[2].product_scale}}</td>
        <td width="15%">{{data.itemList[2].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[3] && data.itemList[3].quantity>0">
        <td width="22%">{{data.itemList[3].product_name}}</td>
        <td width="15%">1*{{data.itemList[3].product_scale}}</td>
        <td width="15%">{{data.itemList[3].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[14] && data.itemList[14].quantity>0">
        <td width="22%">{{data.itemList[14].product_name}}</td>
        <td width="15%">1*{{data.itemList[14].product_scale}}</td>
        <td width="15%">{{data.itemList[14].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[21] && data.itemList[21].quantity>0">
        <td width="22%">{{data.itemList[21].product_name}}</td>
        <td width="15%">1*{{data.itemList[21].product_scale}}</td>
        <td width="15%">{{data.itemList[21].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[4] && data.itemList[4].quantity>0">
        <td width="22%">{{data.itemList[4].product_name}}</td>
        <td width="15%">1*{{data.itemList[4].product_scale}}</td>
        <td width="15%">{{data.itemList[4].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[5] && data.itemList[5].quantity>0">
        <td width="22%">{{data.itemList[5].product_name}}</td>
        <td width="15%">1*{{data.itemList[5].product_scale}}</td>
        <td width="15%">{{data.itemList[5].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[6] && data.itemList[6].quantity>0">
        <td width="22%">{{data.itemList[6].product_name}}</td>
        <td width="15%">1*{{data.itemList[6].product_scale}}</td>
        <td width="15%">{{data.itemList[6].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[7] && data.itemList[7].quantity>0">
        <td width="22%">{{data.itemList[7].product_name}}</td>
        <td width="15%">1*{{data.itemList[7].product_scale}}</td>
        <td width="15%">{{data.itemList[7].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[8] && data.itemList[8].quantity>0">
        <td width="22%">{{data.itemList[8].product_name}}</td>
        <td width="15%">1*{{data.itemList[8].product_scale}}</td>
        <td width="15%">{{data.itemList[8].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[9] && data.itemList[9].quantity>0">
        <td width="22%">{{data.itemList[9].product_name}}</td>
        <td width="15%">1*{{data.itemList[9].product_scale}}</td>
        <td width="15%">{{data.itemList[9].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[10] && data.itemList[10].quantity>0">
        <td width="22%">{{data.itemList[10].product_name}}</td>
        <td width="15%">1*{{data.itemList[10].product_scale}}</td>
        <td width="15%">{{data.itemList[10].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[11] && data.itemList[11].quantity>0">
        <td width="22%">{{data.itemList[11].product_name}}</td>
        <td width="15%">1*{{data.itemList[11].product_scale}}</td>
        <td width="15%">{{data.itemList[11].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[12] && data.itemList[12].quantity>0">
        <td width="22%">{{data.itemList[12].product_name}}</td>
        <td width="15%">1*{{data.itemList[12].product_scale}}</td>
        <td width="15%">{{data.itemList[12].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[13] && data.itemList[13].quantity>0">
        <td width="22%">{{data.itemList[13].product_name}}</td>
        <td width="15%">1*{{data.itemList[13].product_scale}}</td>
        <td width="15%">{{data.itemList[13].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[15] && data.itemList[15].quantity>0">
        <td width="22%">{{data.itemList[15].product_name}}</td>
        <td width="15%">1*{{data.itemList[15].product_scale}}</td>
        <td width="15%">{{data.itemList[15].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[16] && data.itemList[16].quantity>0">
        <td width="22%">{{data.itemList[16].product_name}}</td>
        <td width="15%">1*{{data.itemList[16].product_scale}}</td>
        <td width="15%">{{data.itemList[16].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[17] && data.itemList[17].quantity>0">
        <td width="22%">{{data.itemList[17].product_name}}</td>
        <td width="15%">1*{{data.itemList[17].product_scale}}</td>
        <td width="15%">{{data.itemList[17].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[18] && data.itemList[18].quantity>0">
        <td width="22%">{{data.itemList[18].product_name}}</td>
        <td width="15%">1*{{data.itemList[18].product_scale}}</td>
        <td width="15%">{{data.itemList[18].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[19] && data.itemList[19].quantity>0">
        <td width="22%">{{data.itemList[19].product_name}}</td>
        <td width="15%">1*{{data.itemList[19].product_scale}}</td>
        <td width="15%">{{data.itemList[19].quantity}}</td>
        <td width="15%"></td>
      </tr>
      <tr v-if="data.itemList[20] && data.itemList[20].quantity>0">
        <td width="22%">{{data.itemList[20].product_name}}</td>
        <td width="15%">1*{{data.itemList[20].product_scale}}</td>
        <td width="15%">{{data.itemList[20].quantity}}</td>
        <td width="15%"></td>
      </tr>
    </table>
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
      this.$store.state.http.auto('checkinOrder', 'getById', { params: { id: this.$route.params.id } }).then((res) => {
        this.data = res.data
        console.log(this.data)
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
  },
  data () {
    return {
      flag: true,
      data: {
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
  width: 87%;
  td {
    padding: 10px 10px 10px 10px;
    border: #000000 1px solid;
    font-size: 17px;
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
