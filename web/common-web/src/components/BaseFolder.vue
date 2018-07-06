<template>
  <div class="base-folder">
    <!-- <el-dialog title="上传文件" :visible.sync="dialog.visible" width="830px"> -->
    <div class="block" style="padding-left: 10px;" v-if="!(dialog.visibleButton === false)">
      <el-button type="primary" plain size="mini" style="float: left; margin-right: 20px;" @click="folderUpload.visible = true">上传文件</el-button>
      <!-- <el-pagination :current-page="1" :page-sizes="[100, 200, 300, 400]" :page-size="100" layout="total, sizes, prev, pager, next, jumper"
                    :total="400" style="float: left;">
                </el-pagination> -->
      <div style="clear: both;"></div>
    </div>
    <div class="base-folder-image" v-if="item" v-for="(item, index) of dialog.fileList" :key="index">
      <el-card :body-style="{ padding: '0px' }">
        <img v-if="item.type.indexOf('image') >= 0" :src="item.storePath" class="image" @click="folderDetailOpen(item.key)">
        <img v-else src="../assets/image/pictureWarning.png" class="image" @click="folderDetailOpen(item.key)">
        <div style="padding: 0 14px; text-align: left;">
          <span>{{item.name}}</span>
          <div class="bottom clearfix" style="height: 24px;">
            <time class="time" style="float: left; font-size: 12px; line-height: 20px; color: #A59F9F;">{{dateFormatterTool(item.dateTime,'yyyy-MM-dd hh:mm:ss')}}</time>
            <el-button type="text" class="button" style="height: 20px; padding: 0; float: right; padding-left: 5px;" @click="deleteFile(item.key)" v-if="!(dialog.visibleButton === false)">删除</el-button>
            <el-button type="text" class="button" style="height: 20px; padding: 0; float: right;" @click="download(item.storePath)">下载</el-button>
            <div style="clear: both;"></div>
          </div>
        </div>
      </el-card>
    </div>
    <div v-if="dialog.fileList.length === 0">
      暂无文件
    </div>
    <div style="clear: both;"></div>
    <base-folder-upload :dialog='folderUpload' :uploadSuccessCallback="uploadSuccessCallback"></base-folder-upload>
    <base-folder-detail :dialog="folderDetail"></base-folder-detail>
    <!-- </el-dialog> -->
  </div>
</template>

<script>
import BaseFolderUpload from './BaseFolderUpload'
import BaseFolderDetail from './BaseFolderDetail'
import { dateFormatterTool } from '@assets/common/common'
export default {
  name: 'BaseUpload',
  components: {
    BaseFolderUpload,
    BaseFolderDetail
  },
  props: {
    /**
     * @prop {Object} dialog
     * @augments Object dialog.attachment 附件对象
     * @augments String dialog.attachment.order_id 查询标识编号
     * @augments String dialog.attachment.order_type 查询标识类型
     * @augments String dialog.attachment.key_array 附件keys 数据格式:{key,key,key}
     * ---- 1.查询后返回
     * ---- 2.修改需更新该对象
     * @augments Boolean dialog.loading 是否装载附件
     * ---- 1.该值修改为true，调用loadFolderUpload()方法，系统会监听该值触发重载方法。
     * ---- 2.该值为false或为null,需自己传入dialog.attachment和dialog.fileList对象
     * @augments Array dialog.fileList 附件对象集合
     * ---- fastDFS接口 查询后返回
     * @augments Boolean dialog.visibleButton 是否显示上传按钮和删除按钮,默认为true
    */
    dialog: {
      type: Object,
      default: {
        attachment: {
          order_id: '标识编号',
          order_type: '唯一标识类型',
          key_array: '附件keys' // 根据order_id和order_type查询后返回。用于记录附件keys
        },
        fileList: [],
        loading: false,
        visibleButton: true
      },
      required: true
    },
    /**
     * @function uploadCallback 上传成功回调函数
     * @param res 上传文件的ID
    */
    uploadCallback: {
      type: Function
    },
    // 限制文件上传类型
    fileTypeLimit: {
      type: Array
    },
    // 限制文件上传大小,单位为字节
    fileSizeLimit: {
      type: Number
    },
    // 限制上传文件数量
    uploadFileCountLimit: {
      type: Number
    }
  },
  watch: {
    loading (val, old) {
    }
  },
  computed: {
    loading () {
      if (this.dialog.loading === true) {
        this.loadFolderUpload()
        this.dialog.loading = false
      }
      return this.dialog.loading
    }
  },
  data () {
    return {
      time: new Date(),
      folderUpload: {
        visible: false,
        fileList: [],
        fileTypeLimit: this.fileTypeLimit, // 限制文件类型
        fileSizeLimit: this.fileSizeLimit, // 限制文件大小,单位为字节
        uploadFileCountLimit: this.uploadFileCountLimit // 限制上传文件数量

      },
      folderDetail: {
        visible: false,
        subtitle: `文件信息`,
        fileList: []
      }
    }
  },
  created () {
  },
  methods: {
    loadFolderUpload () {
      let dialog = this.dialog
      dialog.attachment.key_array = ''
      this.$store.state.http.auto('fastDFSAttachment', 'getByIdAndType', { params: dialog.attachment }).then((res) => {
        let data = JSON.parse(res.data)
        dialog.attachment = data.attaInfo
        dialog.fileList = data.fileList
        for (let index = 0; index < data.fileList.length; index++) {
          const element = data.fileList[index]
          if (!element) {
            data.fileList.splice(index, 1)
          }
        }
      }).catch(res => {
        this.$store.state.http.auto('fastDFSAttachment', 'add', { data: dialog.attachment }).then((res) => {
        })
      })
    },
    updateUploadFileDefault (res) {
      let attachment = this.dialog.attachment
      if (attachment.hasOwnProperty('key_array')) {
        let index = attachment.key_array.indexOf(res)
        if (index > 0) {
          attachment.key_array = attachment.key_array.replace(',' + res, '')
        } else if (index === 0) {
          attachment.key_array = attachment.key_array.replace(res, '')
        } else {
          attachment.key_array = attachment.key_array + ',' + res
        }
      }
      this.$store.state.http.auto('fastDFSAttachment', 'update', { data: attachment }).then((res) => {
      })
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    uploadSuccessCallback (res) {
      let vm = this
      vm.$store.state.http.auto('fastDFS', 'getByKey', { params: { key: res } }).then(res => {
        vm.dialog.fileList.unshift(res.data)
      }).catch(error => {
        vm.$message.error(error.statusText)
        console.log(error)
      })
      if (this.uploadCallback) {
        this.uploadCallback(res)
      } else {
        this.updateUploadFileDefault(res)
      }
    },
    download (url) {
      var link = document.createElement('a')
      link.download = ''
      link.href = url
      link.click()
    },
    dateFormatterTool: dateFormatterTool,
    folderDetailOpen (key) {
      this.folderDetail.fileList = this.dialog.fileList
      this.folderDetail.key = key
      this.folderDetail.visible = true
    },
    deleteFile (key) {
      let vm = this
      this.$store.state.http.auto('fastDFS', 'delete', { params: { key: key } }).then(res => {
        let fileLsit = vm.dialog.fileList
        for (let index = 0; index < fileLsit.length; index++) {
          if (fileLsit[index] && fileLsit[index].key === key) {
            fileLsit.splice(index, 1)
          }
        }
        this.uploadCallback(key)
      }).catch(error => {
        if (error) {
          // vm.$message.error(error)
          console.log(error)
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.base-folder-image {
  width: 250px;
  padding: 10px 10px 5px 10px;
  float: left;
  img {
    width: 248px;
    height: 248px;
  }
}
</style>

<style lang="scss">
.base-folder {
  .el-dialog__body {
    padding: 0;
    padding-left: 10px;
    padding-bottom: 10px;
  }
}
</style>
