'use strict'
module.exports = {
  /**
   * @property CURRENT_CONFIG 当前装载的配置
  */
  CURRENT_CONFIG: 'DEV',
  /**
   * @property DEFUALT_API
   * 默认装载的API
  */
  DEFUALT_API: 'boss',
  /** 
   * @property DEV 
   * DEV环境，API资源配置
  */
  DEV: {
    fastDFS: 'http://192.168.16.27:8087/',
    boss: 'http://192.168.16.27:8900/'
  },
  /** 
   * @property LOCAL 
   * LOCAL环境，API资源配置
  */
  LOCAL: {
    sdc: 'http://192.168.16.27:8000/',
    fastDFS: 'http://192.168.16.27:8087/'
  },
  /** 
   * @property PROD 
   * PROD环境，API资源配置
  */
  PROD: {
    sdc: 'http://192.168.16.27:8000/',
    fastDFS: 'http://192.168.16.27:8087/'
  }
}
