import TEST from './test'

import Home from './AppHome'
import Login from './Login'

// 导出所有路由对象
export default [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
    redirect: '/login',
    name: 'login',
    component: Login
  }
].concat(
  TEST
)
