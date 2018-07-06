<template>
  <div>
    
    
  <div>
    <div style="width: 50%; float: left; min-width: 600px;">
      <!-- <div>
      <el-select v-model="select" slot="prepend" size="small" style="width: 120px; float: left; padding-left: 5px; margin-bottom: -32px; z-index: 99999;"  placeholder="请选择">
        <el-option :label="item.name" v-for="(item, index) of classifyData"  :key="index" :value="item.name"></el-option>
      </el-select>
    </div> -->
      <chart-histogram :title="title" :cols="histogramMainPersonData.cols" :data="histogramMainPersonData.data"></chart-histogram>
    </div>
    <div style="width: 50%;height: 360px; float: left; min-width: 600px;" class="chart-table">
      <div class="arrow-buttom">
        <p class="el-icon-d-arrow-left"></p>
      </div>
      <div class="table-middle" style="border: 1px solid #EBEEF5; padding-left: 5px; padding-right: 5px;">
        <el-table height="360px" :data="tableData" style="width: 100%"
        :row-class-name="tableRowClassName">
          <el-table-column class-name="chart-column" v-for="(item, key) in cols" :width="item.width" :min-width="item.minwidth" :formatter="item.formatter" :key="key"
            :prop="item.prop" :label="item.label" header-align="center" style="padding: 0 0;">
          </el-table-column>
        </el-table>
      </div>
      <div class="arrow-buttom">
        <p class="el-icon-d-arrow-right"></p>
      </div>
      <div style="clear: both;"></div>
    </div>
      <div style="clear: both;"></div>
  </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String
    },
    cols: {
      type: Array
    },
    tableData: {
      type: Array
    }
  },
  computed: {
  },
  data () {
    return {
      histogramMainPersonData: {
        title: '',
        cols: [
          {
            label: '持股',
            type: 'bar',
            prop: 'quantity'
          },
          {
            label: '质押',
            type: 'bar',
            prop: 'pledge'
          },
          {
            label: '冻结',
            type: 'bar',
            prop: 'freeze'
          },
          {
            label: '质押占比',
            type: 'line',
            prop: 'proportion'
          }
        ],
        data: [
          {
            name: '一季度',
            quantity: '1080',
            pledge: '160',
            freeze: '150',
            proportion: '24.12'
          },
          {
            name: '二季度',
            quantity: '1020',
            pledge: '150',
            freeze: '110',
            proportion: '21.45'
          },
          {
            name: '三季度',
            quantity: '1120',
            pledge: '120',
            freeze: '170',
            proportion: '20.04'
          },
          {
            name: '四季度',
            quantity: '980',
            pledge: '140',
            freeze: '200',
            proportion: '23.14'
          }
        ]
      }
    }
  },
  methods: {
    tableRowClassName ({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row'
      }
      return ''
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.arrow-buttom {
  float: left;
  width: 20px;
  padding-top: 151px;
  padding-bottom: 151px;
  cursor: pointer;
}
.arrow-buttom :hover {
  color: black;
  font-weight: bold;
}
.table-middle {
  float: left;
  width: calc(100% - 54px);
}
.chart-column {
  line-height: 40px;
}

</style>
