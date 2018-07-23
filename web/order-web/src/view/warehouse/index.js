import Inventory from './Inventory'
import CheckinOrder from './CheckinOrder'
import WarehouseOrder from './WarehouseOrder'

export default [
  {
    path: '/warehouse/inventory',
    name: 'Inventory',
    component: Inventory
  },
  {
    path: '/warehouse/checkinOrder',
    name: 'CheckinOrder',
    component: CheckinOrder
  },
  {
    path: '/warehouse/warehouseOrder',
    name: 'WarehouseOrder',
    component: WarehouseOrder
  }
]
