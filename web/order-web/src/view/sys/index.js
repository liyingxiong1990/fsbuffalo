import Dict from './Dict'
import Department from './Department'
import Menu from './Menu'
import Role from './Role'
import SdcOperateLog from './SdcOperateLog'
import BossOperateLog from './BossOperateLog'
import RequestLog from './RequestLog'
import Permission from './Permission'
import MenuPermission from './MenuPermission'
import Check from './Check'
import User from './User'

export default [

  {
    path: '/system/dict',
    name: 'Dict',
    component: Dict
  },
  {
    path: '/system/department',
    name: 'Department',
    component: Department
  },
  {
    path: '/system/menu',
    name: 'Menu',
    component: Menu
  },
  {
    path: '/system/role',
    name: 'Role',
    component: Role
  },
  {
    path: '/system/permission',
    name: 'Permission',
    component: Permission
  },
  {
    path: '/system/menu_permission',
    name: 'MenuPermission',
    component: MenuPermission
  },
  {
    path: '/system/sdc_operate_log',
    name: 'SdcOperateLog',
    component: SdcOperateLog
  },
  {
    path: '/system/boss_operate_log',
    name: 'BossOperateLog',
    component: BossOperateLog
  },
  {
    path: '/system/request_log',
    name: 'RequestLog',
    component: RequestLog
  },
  {
    path: '/system/Check',
    name: 'Check',
    component: Check
  },
  {
    path: '/system/user',
    name: 'User',
    component: User
  }
]
