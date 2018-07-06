
<template >
  <div>
    <el-dialog :title="dialog.title?dialog.title:'上传文件'" class="base-folder-upload" :visible.sync="dialog.visible" width="450px" :before-close="dialogBeforeClose" :append-to-body="true">
      <el-upload ref="folderUpload" drag :action="uploadUrl" :data="requestData" :auto-upload="false" :on-success="onSuccess" :limit="limit" :on-change="onChange" multiple :before-upload="beforeUpload" :show-file-list="false" :on-exceed="onExceed">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <!-- <div class="el-upload__tip" slot="tip">只能上传jpg/png文件</div> -->
      </el-upload>
      <el-card :shadow="'hover'" v-if="fileDetailsList.length > 0" v-for="(item, index) of fileDetailsList" :key="index" style="margin-top: 10px;">
        <i class="el-icon-error upload-remove-button" @click="removeUploadFile(item.uid)"></i>
        <P style="text-align: left; font-size: 10px; color: #928b8b;">
          文件名称: {{ item.raw.name }}
        </P>
        <div style="float: left;">
          <div class="el-upload-dragger" style="height: 120px; width: 120px;">
            <img id="uploadImg" v-if="item.raw.type.indexOf('image') >= 0" border="0" :src="item.src" style="border: none;" width="120px" height="120px">
            <img id="uploadImg" v-else border="0" src="../assets/image/pictureWarning.png" style="border: none;" width="120px" height="120px">
          </div>
        </div>
        <div style="float: left; margin-left: 10px;">
          <el-form :model="item" label-position="right" label-width="60px" ref="ruleForm">
            <el-form-item label="名称" prop='name'>
              <el-input v-model="item.name" clearable size="mini"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop='detail'>
              <el-input type="textarea" :rows="3" v-model="item.detail" size="mini"></el-input>
            </el-form-item>

          </el-form>
        </div>
        <div style="clear: both;"></div>

      </el-card>
      <div slot="footer" style="">
        <el-button type="primary" size="small" @click="submitFolderUpload">提交</el-button>
      </div>
      <div style="clear: both;"></div>
      <div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'BaseFolderUpload',
  props: {
    dialog: {
      type: Object,
      default: {
        title: ``, // 标题默认为‘上传文件’，可不设置，传入则覆盖
        visible: false, // 是否显示上传窗口
        fileTypeLimit: [], // 限制文件类型
        fileSizeLimit: null, // 限制文件大小,单位为字节
        uploadFileCountLimit: null // 限制上传文件数量
      }
    },
    /**
     * @prop {Function} uploadSuccessCallback 文件上传成功回调函数
     * @param res 返回附件key
    */
    uploadSuccessCallback: {
      type: Function
    }
  },
  data () {
    return {
      time: new Date(),
      requestData: {
        metaDataJson: ''
      },
      fileList: [],
      fileDetailsList: [],
      limit: 5
    }
  },
  created () {
  },
  computed: {
    uploadUrl () {
      return this.$store.state.http.getApiConfig('fastDFS', 'add')
    }
  },
  methods: {
    onSuccess (res) {
      if (this.uploadSuccessCallback) {
        this.uploadSuccessCallback(res)
      }
    },
    onChange (file, fileList) {
      let vm = this
      let fileTypeLimit = vm.dialog.fileTypeLimit
      let fileSizeLimit = vm.dialog.fileSizeLimit
      let uploadFileCountLimit = vm.dialog.uploadFileCountLimit
      if (file.status === 'ready') {
        if (uploadFileCountLimit && !isNaN(uploadFileCountLimit)) {
          if (fileList.length > Number(uploadFileCountLimit)) {
            vm.$message.error(`对不起，装载文件失败！最多上传${uploadFileCountLimit}个文件`)
            vm.removeUploadFile(file.uid)
            return
          }
        }

        if (fileTypeLimit && typeof fileTypeLimit === 'object') {
          let fileType = file.raw.type
          let isPrimise = false
          for (let item of fileTypeLimit) {
            if (fileType.indexOf(item) >= 0) {
              isPrimise = true
              break
            }
          }
          if (!isPrimise) {
            vm.removeUploadFile(file.uid)
            vm.$message.error(`对不起，装载文件失败！只能上传${fileTypeLimit.toString()}类型文件`)
            return
          }
        }

        if (fileSizeLimit && !isNaN(fileSizeLimit)) {
          if (file.size > Number(fileSizeLimit)) {
            vm.removeUploadFile(file.uid)
            vm.$message.error(`对不起，装载文件失败！文件大小不能超过${fileSizeLimit}字节`)
            return
          }
        }

        this.fileList = fileList
        vm.fileDetailsList.push({
          uid: file.uid,
          name: file.name.split('.')[0],
          detail: '',
          src: file.url,
          raw: file.raw
        })
      } else if (file.status === 'success') {
        vm.removeUploadFile(file.uid)
      }
    },
    submitFolderUpload () {
      this.$refs.folderUpload.submit()
      this.dialog.visible = false
    },
    beforeUpload (file) {
      for (const iterator of this.fileDetailsList) {
        if (iterator.uid === file.uid) {
          let obj = {
            name: iterator.name,
            detail: iterator.detail,
            type: file.type,
            dateTime: new Date().getTime()
          }
          this.requestData.metaDataJson = window.JSON.stringify(obj)
        }
      }
    },
    removeUploadFile (uid) {
      // 移除上传文件
      for (let index = 0; index < this.fileList.length; index++) {
        const element = this.fileList[index]
        if (element.uid === uid) {
          this.fileList.splice(index, 1)
          // 移除上传文件映射详情
          for (let index = 0; index < this.fileDetailsList.length; index++) {
            const ele = this.fileDetailsList[index]
            if (ele.uid === uid) {
              this.fileDetailsList.splice(index, 1)
              return
            }
          }
          break
        }
      }
    },
    onExceed () {
      this.$message.error(`对不起，装载文件失败！最多只能上传个${this.limit}文件`)
    },
    dialogBeforeClose (done) {
      this.fileDetailsList = []
      this.fileList = []
      this.$refs.folderUpload.clearFiles()
      done()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.upload-remove-button {
  position: absolute;
  margin-top: -3px;
  margin-left: 170px;
  font-size: 20px;
  color: #909699;
}
.upload-remove-button:hover {
  color: #f56c6c;
}
</style>

<style lang="scss">
.base-folder-upload {
  .el-upload-dragger {
    width: 388px;
  }
  .el-dialog__body {
    padding: 0;
    display: inline-block;
    text-align: center;
    padding-bottom: 10px;
    padding-top: 5px;
    width: 100%;
  }
  .el-card {
    width: 388px;
    display: inline-block;
  }
  .el-card__body {
    padding: 10px;
    .el-form-item {
      margin-bottom: 5px;
    }
  }
}
</style>