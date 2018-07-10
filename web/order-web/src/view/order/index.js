import Inventory from './Inventory'
import CheckinOrder from './CheckinOrder'
import DelivererOrder from './DelivererOrder'

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
  }
]
