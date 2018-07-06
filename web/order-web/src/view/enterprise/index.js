import Driver from './Driver'
import Deliverer from './Deliverer'
import OutOrderRecorder from './OutOrderRecorder'
import InOrderRecorder from './InOrderRecorder'
import Carrier from './Carrier'
import Product from './Product'
import Store from './Store'

export default [
  {
    path: '/enterprise/driver',
    name: 'Driver',
    component: Driver
  },
  {
    path: '/enterprise/deliverer',
    name: 'Deliverer',
    component: Deliverer
  },
  {
    path: '/enterprise/outOrderRecorder',
    name: 'OutOrderRecorder',
    component: OutOrderRecorder
  },
  {
    path: '/enterprise/inOrderRecorder',
    name: 'InOrderRecorder',
    component: InOrderRecorder
  },
  {
    path: '/enterprise/carrier',
    name: 'Carrier',
    component: Carrier
  },
  {
    path: '/enterprise/product',
    name: 'Product',
    component: Product
  },
  {
    path: '/enterprise/store',
    name: 'Store',
    component: Store
  }
]
