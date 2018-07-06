<template>
  <chart style="margin-left: calc( 50% - 300px)" theme="wonderland" :options="option"></chart>
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
    return {
      option: {
        title: {
          text: '基础雷达图'
        },
        tooltip: {},
        legend: {
          data: ['预算分配（Allocated Budget）', '实际开销（Actual Spending）']
        },
        radar: {
          // shape: 'circle',
          name: {
            textStyle: {
              color: '#fff',
              backgroundColor: '#999',
              borderRadius: 3,
              padding: [3, 5]
            }
          },
          indicator: [
            { name: '销售（sales）', max: 10000 },
            { name: '管理（Administration）', max: 10000 },
            { name: '信息技术（Information Techology）', max: 10000 },
            { name: '客服（Customer Support）', max: 10000 },
            { name: '研发（Development）', max: 10000 },
            { name: '市场（Marketing）', max: 10000 }
          ]
        },
        series: [{
          name: '预算 vs 开销（Budget vs spending）',
          type: 'radar',
          // areaStyle: {normal: {}},
          data: [
            {
              value: [10000, 10000, 10000, 10000, 10000, 10000],
              name: '预算分配（Allocated Budget）'
            },
            {
              value: [5000, 14000, 13000, 14000, 14000, 13000],
              name: '实际开销（Actual Spending）'
            }
          ]
        }]
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

</style>
