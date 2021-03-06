import Inventory from './Inventory'
import DelivererOrder from './DelivererOrder'
import WarehouseOrder from './WarehouseOrder'

export default [
  {
    path: '/order/inventory',
    name: 'Inventory',
    component: Inventory
  },
  {
    path: '/order/delivererOrder',
    name: 'DelivererOrder',
    component: DelivererOrder
  },
  {
    path: '/order/warehouseOrder',
    name: 'WarehouseOrder',
    component: WarehouseOrder
  }
]
