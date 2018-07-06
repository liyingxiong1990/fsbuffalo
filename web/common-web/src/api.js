// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import store from './store/index'

import ElementUI from 'element-ui'
import './assets/css/index.scss'
import './assets/bootstrap/css/bootstrap.css'

import Api from './view/test/Api.vue'
import router from './router'

Vue.use(ElementUI)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<Api/>',
  components: { Api }
})
