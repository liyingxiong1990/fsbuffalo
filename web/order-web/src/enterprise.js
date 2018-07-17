// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import store from './store/index'
import ElementUI from 'element-ui'
import './assets/css/element-variables.scss'
import './assets/bootstrap/css/bootstrap.css'
import PrintTemplate from '@/printForms/PrintTemplate'
import router from './printForms/page'
import { BaseHeader, BaseBar, BaseNav, BaseTable, BaseFolder } from 'gdotc@common/components'

// import ECharts from 'vue-echarts/components/ECharts.vue'
// import echarts from 'echarts'
// Vue.config.productionTip = false
Vue.use(ElementUI)
// Vue.component('chart', ECharts)

// 基础组件
Vue.component('BaseHeader', BaseHeader)
Vue.component('BaseNav', BaseNav)
Vue.component('BaseTable', BaseTable)
Vue.component('BaseBar', BaseBar)
Vue.component('BaseFolder', BaseFolder)

/* eslint-disable no-new */

new Vue({
  el: '#app',
  store,
  router,
  template: '<PrintTemplate/>',
  components: { PrintTemplate }
})
