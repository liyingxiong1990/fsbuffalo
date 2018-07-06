<template>
  <div class="dataTool">
    <el-row type="flex" class="row-bg">
      <el-col :span="6">
        <el-select v-model="importData.module" placeholder="请选择模块">
          <el-option v-for="(item, key) in apiConfig" :key="key" :label="key" :value="key">
          </el-option>
        </el-select>
        <el-input placeholder="列分割符" v-model="importData.separator" style="width: 120px"></el-input>
        <el-button type="primary">
          <i class="el-icon-refresh"></i>刷新
        </el-button>
        <el-button type="primary">
          <i class="el-icon-refresh"></i>提交
        </el-button>
        <div id="log">
          <ul>
            <li>asd</li>
            <li>asd</li>
            <li>asd</li>
          </ul>
        </div>
        <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或
            <em>点击上传</em>
          </div>
          <div class="el-upload__tip" slot="tip">只能上传txt/csv文件，且不超过500kb</div>
        </el-upload>
      </el-col>
      <el-col :span="12">
        <table>
          <tbody>
            <td>as</td>
            <td>as</td>
          </tbody>
          <tr>
            <td>aa</td>
            <td>aa</td>
          </tr>
          <tr>
            <td>aa</td>
            <td>aa</td>
          </tr>
        </table>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import apiConfig from '../../assets/config/srevice-api.config'
export default {
  name: 'dataTool',
  created () {
    for (let key in apiConfig) {
      console.log(key + apiConfig[key])
    }
  },
  computed: {
  },
  methods: {
  },
  data () {
    return {
      apiConfig: apiConfig,
      importData: {
        module: '',
        separator: '',
        defaultColumn: {}
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
