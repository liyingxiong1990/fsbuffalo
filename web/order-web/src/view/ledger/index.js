import Inventory from './Inventory'
import CheckinOrder from './CheckinOrder'
import DelivererOrder from './DelivererOrder'
import WarehouseOrder from './WarehouseOrder'

export default [
  {
    path: '/ledger/inventory',
    name: 'Inventory',
    component: Inventory
  },
  {
    path: '/ledger/checkinOrder',
    name: 'CheckinOrder',
    component: CheckinOrder
  },
  {
    path: '/ledger/delivererOrder',
    name: 'DelivererOrder',
    component: DelivererOrder
  },
  {
    path: '/ledger/warehouseOrder',
    name: 'WarehouseOrder',
    component: WarehouseOrder
  }
]
