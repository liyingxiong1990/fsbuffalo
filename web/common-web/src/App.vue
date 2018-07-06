<template>
  <div id="app">
    <base-header v-if="$route.path!=='/login'" v-bind:username="$store.state.nickname?$store.state.nickname:''"  v-on:exitSystemEvent="exitSystem"></base-header>
    <base-nav :navDIYData="navDIYData" v-if="$route.path!=='/login'"></base-nav>
    <router-view v-if="$route.path==='/login'"/>
  </div>
</template>
<script>
import NAV_CONFIG from './assets/config/nav-menu.config'
export default {
  name: 'app',
  data () {
    return {
      ok: '',
      username: 'admin',
      navDIYData: NAV_CONFIG
    }
  },
  created () {
    if (this.$route.path !== '/login') {
      this.$store.dispatch('SYS_INITIAL')
    }
  },
  computed: {
  },
  methods: {
    exitSystem () {
      window.localStorage.removeItem('token')
      this.$router.push('/login')
    }
  }
}
</script>

<style>
html,
body,
#app {
  height: 100%;
}
#app {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;
  padding: 0;
}
body {
  margin: 0;
  padding: 0;
}
</style>
