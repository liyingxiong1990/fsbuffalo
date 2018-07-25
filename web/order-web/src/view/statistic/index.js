import Overview from './Overview'
import StoreAnalysis from './StoreAnalysis'
import DeliveryAnalysis from './DeliveryAnalysis'

export default [
  {
    path: '/stats/overview',
    name: 'Overview',
    component: Overview
  },
  {
    path: '/stats/store',
    name: 'StoreAnalysis',
    component: StoreAnalysis
  },
  {
    path: '/stats/delivery',
    name: 'DeliveryAnalysis',
    component: DeliveryAnalysis
  }
]
