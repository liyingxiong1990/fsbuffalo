<template>
  <div class="user">
    <h3 >
      test
    </h3>
    <el-upload
  class="upload-demo"
  drag
  action="http://192.168.16.164:8000/fastdfs/upload/"
  multiple>
  <i class="el-icon-upload"></i>
  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
  <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload>
  </div>
</template>

<script>
export default {
  name: 'user',
  data () {
    return {
      datas: []
    }
  },
  methods: {
    getAll () {
      this.$store.state.http.get('/mongo/file/').then((response) => {
        this.datas = response
      }).catch((error) => {
        this.datas = error
        console.log(error)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.user {
  text-align: left;
  padding-left: 20px;
}
</style>
