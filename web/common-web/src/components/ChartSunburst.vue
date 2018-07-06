<template>
  <chart style="margin-left: calc( 50% - 300px)" :options="option"></chart>
</template>

<script>
export default {
  props: {
    showAdd: {
      type: Boolean
    },
    addEvent: Function
  },
  data () {
    var data2 = [
      {
        'name': '广东美涂士建材股份有限公司',
        'value': 2105,
        'children': [
          { 'name': '周伟彬', 'value': 400 },
          { 'name': '王曼娜', 'value': 600 },
          { 'name': '王曼娜', 'value': 1105 }
        ]
      },
      {
        'name': '广东弘诚集团有限公司',
        'value': 1316,
        'children': [
          { 'name': '蔡伟斌', 'value': 1316 }
        ]
      },
      {
        'name': '广东顺控城网建设投资有限公司',
        'value': 1316,
        'children': [
          { 'name': '佛山市顺德区诚顺资产管理有限公司 ', 'value': 1316 }
        ]
      },
      {
        'name': '佛山市顺德区德美投资有限公司',
        'value': 1316,
        'children': [
          { 'name': '广东德美精细化工集团股份有限公司  ', 'value': 1316 }
        ]
      },
      {
        'name': '佛山市依达塑胶化工有限公司',
        'value': 1316,
        'children': [
          { 'name': '佛山市星展投资有限公司  ', 'value': 1316 }
        ]
      },
      {
        'name': '广东东菱凯琴集团有限公司',
        'value': 1316,
        'children': [
          { 'name': '郭建刚', 'value': 1316 },
          { 'name': '郭建强', 'value': 1316 }
        ]
      }
    ]
    return {
      option: {
        visualMap: {
          type: 'continuous',
          min: 0,
          max: 10,
          inRange: {
            color: ['#2D5F73', '#538EA6', '#F2D1B3', '#F2B8A2', '#F28C8C']
          }
        },
        series: {
          type: 'sunburst',
          data: data2,
          radius: [0, '90%'],
          label: {
            rotate: 'radial'
          }
        }
      }
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang='scss' scoped>

</style>
