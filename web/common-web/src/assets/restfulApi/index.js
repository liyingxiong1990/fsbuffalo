'use strict'
// 导入JAVA restful 服务端
import ACCT_SERVICE from './acct-service.config'
import FASTDFS_SERVICE from './fastdfs-service.config'
import GATEWAY_SERVICE from './gateway-service.config'
import NSH_SERVICE from './nsh-service.config'
import PPJH_SERVICE from './ppjh-service.config'
import SDC_SERVICE from './sdc-service.config'
import SYS_SERVICE from './sys-service.config'
import BOSS_SERVICE from './boss-service.config'

// 合并所有服务对象
export default Object.assign(
  ACCT_SERVICE,
  FASTDFS_SERVICE,
  GATEWAY_SERVICE,
  NSH_SERVICE,
  PPJH_SERVICE,
  SDC_SERVICE,
  SYS_SERVICE,
  BOSS_SERVICE)
