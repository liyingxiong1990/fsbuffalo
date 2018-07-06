<template>
  <div>
    <chart style="width: 100%; height: 600px;"  theme="wonderland"  :options="option"></chart>
  </div>
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
    var data2 = {
      'name': '顺德农商行',
      'children': [
        {
          'name': '广东美涂士建材股份有限公司',
          'value': 2105,
          'children': [
            { 'name': '周伟彬', 'value': 1316 },
            { 'name': '王曼娜', 'value': 3151 },
            { 'name': '王曼娜', 'value': 3151 }
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
    }
    return {
      option: {
        height: '550px',
        title: {
          text: '股东关联关系'
          // subtext: '数据来自网络'
        },
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: [
          {
            type: 'tree',
            name: 'tree2',
            data: [data2],

            top: '1%',
            left: '7%',
            bottom: '1%',
            right: '7%',

            symbolSize: 9,

            label: {
              normal: {
                position: 'left',
                verticalAlign: 'middle',
                align: 'right',
                fontSize: 9,
                fontWeight: 600
              }
            },

            leaves: {
              label: {
                normal: {
                  position: 'right',
                  verticalAlign: 'middle',
                  align: 'left'
                }
              }
            },

            expandAndCollapse: true,
            animationDuration: 550,
            animationDurationUpdate: 750
          }
        ]
      }
    }
  }
}
</script>

<!-- Add 'scoped' attribute to limit CSS to this component only -->
<style lang='scss' scoped>

</style>
