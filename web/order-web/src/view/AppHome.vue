<template>
  <div class="home">
    <base-planar :menuData="menuData" v-if="menuData.data.length"></base-planar>
  </div>
</template>

<script>
export default {
  name: 'home',
  created () {
    let timer = null
    let vm = this
    timer = setInterval(() => {
      if (vm.$store.state.menuList && vm.$store.state.menuList.length) {
        this.menuData.data = vm.$store.state.menuList
        clearInterval(timer)
      }
    }, 100)
  },
  computed: {
  },
  data () {
    return {
      menuData: {
        data: [],
        parentId: `0d441330-43d9-11e8-a09d-00505695d0f0`
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.home {
  text-align: left;
  padding-left: 20px;
}
</style>
