import Vue from 'vue'
import Router from 'vue-router'
import WarehouseOrderPrintPage from './WarehouseOrderPrintPage'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/:id/warehouse_order',
      name: 'WarehouseOrderPrintPage',
      component: WarehouseOrderPrintPage
    }
  ]
})
