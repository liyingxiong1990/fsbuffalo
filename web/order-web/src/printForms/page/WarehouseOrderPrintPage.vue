<template>
  <div class="page-base-info" :class="flag?'flag' : ''">
    <h1>百富露水牛奶有限公司</h1>
    <table border="0" border-spacing="0" border-collapse="collapse" cellspacing="0" style="width: 100%; border-color: grey;margin: auto;">
      <tr>
        <td width="20%" class="td">单号</td>
        <td width="30%">{{data.id}}</td>
        <td width="20%" class="td">开单日期</td>
        <td width="30%">{{formatterTime(null,null,data.order_date)}}</td>
      </tr>
      <tr>
        <td width="20%" class="td">司机</td>
        <td width="30%">{{data.deliverer}}</td>
        <td width="20%" class="td">送货日期</td>
        <td width="30%">{{formatterTime(null,null,data.deliver_date)}}</td>
        <!-- <td width="20%" class="td">开单人</td>
        <td width="30%">{{data.out_order_recorder}}</td> -->
      </tr>
    </table>

    <table border="0" border-spacing="0" border-collapse="collapse" cellspacing="0" style="width: 100%; border-color: grey;margin: auto;">
      <tr>
        <td width="20%" class="td">产品</td>
        <td width="20%" class="td">规格</td>
        <td width="20%" class="td">数量</td>
        <td width="20%" class="td">箱数</td>
        <td width="20%" class="td">零头</td>
        <td width="20%" class="td">备注</td>
      </tr>
      <tr v-if="data.itemList[0]">
        <td width="20%" class="td">产品</td>
        <td width="20%" class="td">规格</td>
        <td width="20%" class="td">数量</td>
        <td width="20%" class="td">箱数</td>
        <td width="20%" class="td">零头</td>
        <td width="20%" class="td">备注</td>
      </tr>
      <!-- <tr v-for="item of data.itemList" :key="item.id">
        <td width="20%" >{{item.product_name}}</td>
        <td width="20%" >1*{{item.product_scale}}</td>
        <td width="20%" >{{item.quantity}}</td>
        <td width="20%" >{{item.number_of_boxes}}</td>
        <td width="20%" >{{item.remainder}}</td>
        <td width="20%" ></td>
      </tr> -->
    </table>
  </div>
</template>

<script>
import { dateFormatterTool, tableColumnFormatterTool } from 'gdotc@common/assets/common/common'
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
    dictIsOnStock () {
      return this.$store.getters.sysDict('base_enterprise_info_is_on_stock')
    },
    dictOrgType () {
      return this.$store.getters.sysDict('ent_org_type')
    },
    dictEntProvince () {
      return this.$store.getters.sysDict('ent_province')
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
    shareholderTableRowContextdblClickEvent (row) {
      this.shareholderDialog.currentRow = row
      this.shareholderDialog.type = 'get'
      this.shareholderDialog.visible = true
    },
    managementTableRowContextdblClickEvent (row) {
      this.managementDialog.currentRow = row
      this.managementDialog.type = 'get'
      this.managementDialog.visible = true
    },
    changeTableRowContextdblClickEvent (row) {
      this.changeDialog.currentRow = row
      this.changeDialog.type = 'get'
      this.changeDialog.visible = true
    },
    formatterOrgType (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictOrgType, cellValue)
    },
    formatterIsOnStock (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictIsOnStock, cellValue)
    },
    formatterEntProvince (row, column, cellValue) {
      return tableColumnFormatterTool(this.dictEntProvince, cellValue)
    },
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
  width: 100%;
  td {
    padding: 12px 10px 12px 10px;
    border: #e4eef6 1px solid;
    font-size: 14px;
    line-height: 19px;
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
