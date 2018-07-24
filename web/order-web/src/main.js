// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import store from './store/index'
import ElementUI from 'element-ui'
import '@/assets/css/element-variables.scss'
// 引入bootstarap图标、字体
import 'gdotc@common/assets/bootstrap/css/bootstrap.css'
import App from './App'
import router from './router'
import ECharts from 'vue-echarts'
import echarts from 'echarts'
import { BaseHeader, BaseBar, BaseNav, BaseTable, BaseFolder, BaseMap, ChartBar, ChartBarHorizontal, ChartHistogram, ChartPie, ChartRadar, ChartSunburst, ChartTable, ChartTree, BasePlanar, BaseDialog, BaseMapChina } from 'gdotc@common/components'
// echarts map
import MainCity from 'gdotc@common/assets/config/china-main-city'
import $ from 'jquery'
window.$ = $
for (let key in MainCity) {
  echarts.registerMap(key, MainCity[key])
}

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
Vue.component('ChartBar', ChartBar)
Vue.component('ChartBarHorizontal', ChartBarHorizontal)
Vue.component('ChartHistogram', ChartHistogram)
Vue.component('ChartPie', ChartPie)
Vue.component('ChartRadar', ChartRadar)
Vue.component('ChartSunburst', ChartSunburst)
Vue.component('ChartTable', ChartTable)
Vue.component('ChartTree', ChartTree)
Vue.component('BasePlanar', BasePlanar)
Vue.component('BaseDialog', BaseDialog)
Vue.component('BaseMapChina', BaseMapChina)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App }
})
