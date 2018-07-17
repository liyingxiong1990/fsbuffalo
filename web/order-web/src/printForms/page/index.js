import Vue from 'vue'
import Router from 'vue-router'
import WarehouseOrderPrintPage from './WarehouseOrderPrintPage'
import CheckinOrderPrintPage from './CheckinOrderPrintPage'
import DelivererOrderPrintPage from './DelivererOrderPrintPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/:id/warehouse_order',
      name: 'WarehouseOrderPrintPage',
      component: WarehouseOrderPrintPage
    },
    {
      path: '/:id/checkin_order',
      name: 'CheckinOrderPrintPage',
      component: CheckinOrderPrintPage
    },
    {
      path: '/:id/deliverer_order',
      name: 'DelivererOrderPrintPage',
      component: DelivererOrderPrintPage
    }
  ]
})
