import Inventory from './Inventory'
import CheckinOrder from './CheckinOrder'

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
  }
]
