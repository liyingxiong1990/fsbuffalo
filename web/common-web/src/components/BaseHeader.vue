<template>
  <div class="BaseHeader BaseHeaderColor global-background-color">
    <el-header>
      <!-- <img src="../assets/image/reg_trade_logo.png" height="34" /> -->
      <span style="color: white; float: left; font-size: 18px;     font-family: 微软雅黑;font-weight: bold;font-size: 18px; padding-top: 5px;">{{titleName}}<i style="font-size: 14px; font-family: inherit;">{{subName}}</i></span>
      <div>
        欢迎回来{{username}}！
        <span v-on:click="exitSystem" >
          <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
          退出
        </span>
      </div>
    </el-header>
  </div>
</template>

<script>
export default {
  name: 'BaseHeader',
  props: {
    titleName: {
      type: String,
      default: 'Gdotc UI'
    },
    subName: {
      type: String
    },
    username: {
      type: String,
      required: true
    },
    exitSystemEvent: Function
  },
  methods: {
    exitSystem () {
      this.$emit('exitSystemEvent')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.BaseHeader {
  height: 35px;
  img {
    float: left;
  }
  div {
    float: right;
    color: white;
    padding-top: 7px;
    text-align: right;
    vertical-align: middle;
    span {
      cursor: pointer;
    }
  }
  .glyphicon-off {
    font-size: 14px;
  }
}
</style>
