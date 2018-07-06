<template>
  <div id="app">
    <base-header titleName="业务运营支撑系统" subName="(Business & Operation Support System)" v-if="$route.path!=='/login'" v-bind:username="$store.state.nickname?$store.state.nickname:''"  v-on:exitSystemEvent="exitSystem"></base-header>
    <base-nav parentId="0d441330-43d9-11e8-a09d-00505695d0f0" v-if="$route.path!=='/login'"></base-nav>
    <router-view v-if="$route.path==='/login'"/>
  </div>
</template>
<script>

export default {
  name: 'app',
  data () {
    return {
      ok: '',
      username: 'admin'
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
