<template>
  <div class="home">
    <base-planar :fileData="fileData"></base-planar>
  </div>
</template>

<script>
import NAV_CONFIG from '../assets/config/nav-menu.config'
export default {
  name: 'Home', // 首页
  props: {
  },
  created () {
  },
  computed: {
  },
  data () {
    return {
      fileData: NAV_CONFIG
    }
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

</style>

<style lang="scss">

</style>