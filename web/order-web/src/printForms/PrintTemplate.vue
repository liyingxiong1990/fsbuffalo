<template>
  <div class="enterprise-home">
    <div class="content">
      <div class="base-navigaion">
        <el-tabs type="border-card">
          <el-tab-pane></el-tab-pane>
          <keep-alive>
            <router-view/>
          </keep-alive>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PrintWarehosueOrder',
  components: {
  },
  props: {
  },
  created () {
    this.$store.dispatch('SYS_INITIAL')
  },
  computed: {
  },
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.content {
  padding: 30px calc(50% - 600px) 30px calc(50% - 600px);
}
</style>

<style>
body {
  margin: 0;
  padding: 0;
  background: white;
}
</style>