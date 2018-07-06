// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import store from './store/index'
import ElementUI from 'element-ui'
import './assets/css'
import './assets/bootstrap/css/bootstrap.css'

import App from './App'
import router from './router'
import ECharts from 'vue-echarts'
import echarts from 'echarts'
import { BaseHeader, BaseBar, BaseNav, BaseTable, BaseFolder, BaseMap, BasePlanar, BaseDialog } from '@/components'
// echarts map
import MainCity from '@/assets/config/china-main-city'
for (let key in MainCity) {
  echarts.registerMap(key, MainCity[key])
}

Vue.config.productionTip = false
Vue.prototype.$echarts = echarts
Vue.use(ElementUI)
Vue.component('chart', ECharts)

// 基础组件
Vue.component('BaseHeader', BaseHeader)
Vue.component('BaseNav', BaseNav)
Vue.component('BaseTable', BaseTable)
Vue.component('BaseBar', BaseBar)
Vue.component('BaseFolder', BaseFolder)
Vue.component('BaseMap', BaseMap)
Vue.component('BasePlanar', BasePlanar)
Vue.component('BaseDialog', BaseDialog)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App }
})
