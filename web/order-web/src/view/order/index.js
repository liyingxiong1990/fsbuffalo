import Inventory from './Inventory'
import CheckinOrder from './CheckinOrder'
import DelivererOrder from './DelivererOrder'
import WarehouseOrder from './WarehouseOrder'

export default [
  {
    path: '/order/inventory',
    name: 'Inventory',
    component: Inventory
  },
  {
    path: '/order/checkinOrder',
    name: 'CheckinOrder',
    component: CheckinOrder
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
