export default {
  auth: {
    login: {
      url: '/oauth/token',
      method: 'post'
    },
    info: {
      url: '/oauth/info/{username}',
      method: 'get'
    }
  }
}
