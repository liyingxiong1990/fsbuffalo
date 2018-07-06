import axios from 'axios'
import ApiConfig from '../restfulApi'
import ServiceConfig from '@config/service.config'

// axios.defaults.baseURL = 'http://192.168.16.25:8000/'
// axios.defaults.baseURL = 'http://localhost:8000/'
// axios.defaults.headers.common['Authorization'] = 'AUTH_TOKEN'
axios.defaults.headers.common['Access-Control-Allow-Headers'] = '*'
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*'
// axios.defaults.headers.common['Cache-Control'] = 'no-cache'
// axios.defaults.headers.common['Pragma'] = 'no-cache'
// axios.defaults.headers.common['Cache-Control'] = 'must-revalidate'
// axios.defaults.headers.common['Cache-Control'] = 'no-store'
// axios.defaults.headers.common['Expires'] = '0'
// 'application/x-www-form-urlencoded'
axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.defaults.headers.put['Content-Type'] = 'application/json'
axios.defaults.headers.get['Content-Type'] = 'application/json'

// 装载API资源配置
if (ServiceConfig) {
  let configType = ServiceConfig.CURRENT_CONFIG
  let defualtApiType = ServiceConfig.DEFUALT_API
  axios.defaults.baseURL = ServiceConfig[configType][defualtApiType]
} else {
  axios.defaults.baseURL = 'http://192.168.16.25:8000/'
}

// 请求拦截器
axios.interceptors.request.use(function (config) {
  if (config.hasOwnProperty('DEFUALT_API')) {
    let configType = ServiceConfig.CURRENT_CONFIG
    let defualtApiType = config['DEFUALT_API']
    config.baseURL = ServiceConfig[configType][defualtApiType]
  }
  let token = window.localStorage.getItem('token')
  if (token) {
    if (config.hasOwnProperty('headers')) {
      config.headers.Authorization = token
      config.headers.userid = window.localStorage.getItem('userid')
    } else {
      config.headers = {
        Authorization: token,
        userid: window.localStorage.getItem('userid')
      }
    }
  } else {
    if (window.location.hash.indexOf('login') >= 0) {
      return config
    }
    let urlPath = window.location.origin
    let paths = window.location.pathname.split('/')
    for (let pathName of paths) {
      if (pathName !== '' && pathName != null && pathName.indexOf('html') < 0) {
        urlPath += '/' + pathName
      }
    }
    urlPath += '#/login'
    window.location.replace(urlPath)
  }
  return config
}, function (error) {
  return Promise.reject(error)
})

// 响应拦截器
axios.interceptors.response.use(function (response) {
  return response
}, function (error) {
  if (window.location.hash.indexOf('login') >= 0) {
    if (error.response && error.response.status === 400 && error.response.data.error === 'invalid_grant') {
      error = {
        error: error,
        statusText: '对不起，账号或密码错误！'
      }
      return Promise.reject(error)
    } else {
      error = {
        error: error,
        statusText: '请稍后再试，系统正在维护！'
      }
      return Promise.reject(error)
    }
  }
  let TOKEN_TIME = localStorage.getItem('TOKEN_TIME')
  if (!TOKEN_TIME || Number(TOKEN_TIME) < new Date().getTime()) {
    window.localStorage.removeItem('TOKEN_TIME')
    window.localStorage.removeItem('token')
    let urlPath = window.location.origin
    let paths = window.location.pathname.split('/')
    for (let pathName of paths) {
      if (pathName !== '' && pathName != null && pathName.indexOf('html') < 0) {
        urlPath += '/' + pathName
      }
    }
    urlPath += '#/login'
    window.location.replace(urlPath)
  }
  if (!error.hasOwnProperty('statusText')) {
    error = {
      error: error,
      statusText: '对不起，服务器正忙请稍后再试！'
    }
  }
  return Promise.reject(error)
})

/**
 * Replace Url Params
 * Replace the url/{key}/ with params value.
 * @param {any} url string
 * @param {any} params object
 * @returns string
 */
function replaceUrlParams (url, params) {
  if (typeof url === 'string') {
    let urlArray = url.split(`{`)
    if (urlArray.length <= 1 && typeof params === 'object' && params.length < 1) {
      return url
    }
    return urlArray.reduce((res, cur, index, data) => {
      let key = cur.split(`}`)[0]
      return res + params[key] + '/'
    })
  }
}

// 封装axios的请求
export function http (method, url, params) {
  return new Promise((resolve, reject) => {
    if (params && params.params) {
      url = replaceUrlParams(url, params.params)
      // params.params = {}
    }
    let config = { method: method, url: url }
    config = Object.assign(config, params)
    axios(config).then(response => {
      if (response.data && Number(response.data.status) >= 400) {
        reject(response.data)
      }
      resolve(response.data)
    }).catch((error) => {
      reject(error)
    })
  })
}

export default {
  post (url, params) {
    return http('post', url, params)
  },
  get (url, params) {
    return http('get', url, params)
  },
  delete (url, params) {
    return http('delete', url, params)
  },
  put (url, params) {
    return http('put', url, params)
  },
  /**
   * Auto HTTP Request
   * By apiModule and apiMethod get url and request method.
   * @param {any} apiModule string
   * @param {any} apiMethod string
   * @param {any} params reference url #Request Config: https://github.com/axios/axios
   * @returns Promise
   */
  auto (apiModule, apiMethod, params) {
    let config = ApiConfig
    if (!config || !config[apiModule] || !config[apiModule][apiMethod]) {
      if (console.error) {
        console.error(`HTTP Auto: Can not find the config by apiModule and apiMethod. You can check the service-api.config.js file.`)
      }
      return
    }
    if (config[apiModule].DEFUALT_API) {
      if (params) {
        params['DEFUALT_API'] = config[apiModule].DEFUALT_API
      } else {
        params = {
          DEFUALT_API: config[apiModule].DEFUALT_API
        }
      }
    }
    // 替换请求API服务器
    if (params && !params.hasOwnProperty('DEFUALT_API')) {
      if (config[apiModule].hasOwnProperty('DEFUALT_API')) {
        params['DEFUALT_API'] = config[apiModule]['DEFUALT_API']
      }
    }
    let url = config[apiModule][apiMethod].url
    let method = config[apiModule][apiMethod].method
    return http(method, url, params)
  },
  /**
   * getApiConfig
   * 获取API配置
   * @param {any} apiModule string
   * @param {any} apiMethod string
   * @returns Object
   */
  getApiConfig (apiModule, apiMethod) {
    let config = ApiConfig
    if (!config || !config[apiModule] || !config[apiModule][apiMethod]) {
      if (console.error) {
        console.error(`HTTP Auto: Can not find the config by apiModule and apiMethod. You can check the service-api.config.js file.`)
      }
    }
    let apiConfigs = config[apiModule]
    if (apiConfigs.hasOwnProperty('DEFUALT_API')) {
      let configType = ServiceConfig.CURRENT_CONFIG
      let defualtApiType = apiConfigs['DEFUALT_API']
      let apiUrl = ServiceConfig[configType][defualtApiType] + apiConfigs[apiMethod].url.substr(1, apiConfigs[apiMethod].url.length)
      return apiUrl
    } else {
      return axios.defaults.baseURL + apiConfigs[apiModule][apiMethod]
    }
  }
}
