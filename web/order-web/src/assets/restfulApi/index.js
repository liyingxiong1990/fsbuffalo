'use strict'
// 导入JAVA restful 服务端
import FASTDFS_SERVICE from './fastdfs-service.config'
import GATEWAY_SERVICE from './gateway-service.config'
import SYS_SERVICE from './sys-service.config'
import ORDER_SERVICE from './order-service.config'
import STAT_SERVICE from './statistic-service.config'

// 合并所有服务对象
export default Object.assign(
  FASTDFS_SERVICE,
  GATEWAY_SERVICE,
  SYS_SERVICE,
  ORDER_SERVICE,
  STAT_SERVICE)
