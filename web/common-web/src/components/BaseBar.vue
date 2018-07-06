<template>
  <div class="BaseBar base-bar-color">
    <el-header>
      <span>{{modelName}}</span>
      <div v-on:click="addEvent" v-if="showAdd">
        <i class="el-icon-circle-plus"></i>
        新增
      </div>
    </el-header>
  </div>
</template>

<script>
export default {
  name: 'BaseBar', // 标题菜单条
  props: {
    modelName: {
      type: String,
      required: true
    },
    showAdd: {
      type: Boolean
    },
    addEvent: Function
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.BaseBar {
  padding-bottom: 10px;
  .el-header {
    border: 1px solid #e6e6e6;
    border-radius: 3px;
    // box-shadow: 0px 0px 1px #82807E;
  }
  span, div {
    float: left;
    margin-top: 20px;
  }
  span {
    width: 300px;
    text-align: left;
    font-weight: bold;
  }
  div {
    width: calc(100% - 300px);
    text-align: right;
    cursor: pointer;
  }
}

</style>
