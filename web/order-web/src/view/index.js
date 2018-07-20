import ENTERPRISE from './enterprise'
import ORDER from './order'
import SYS from './sys'
import STAT from './statistic'
import LEDGER from './ledger'

// import ENTERPRISE from './enterprise'

import AppHome from './AppHome'
import Login from './Login'
export default [
  {
    path: '/home',
    name: 'AppHome',
    component: AppHome
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
].concat(ENTERPRISE, SYS, ORDER, STAT, LEDGER)
