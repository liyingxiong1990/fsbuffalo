<template>
    <div >
        <el-dialog class="base-folder-detail" title="" :visible.sync="dialog.visible" :fullscreen="true" :style="{background: 'black'}" :append-to-body="true" @open="openCallback">
            <div :style="{'height': (height - 70) + 'px'}" v-for="(item, index) of dialog.fileList"  v-if="item && item.key === dialog.key" :key="index">
                <div class="detail-left" :style="{'line-height': (height - 60) + 'px' }">
                    <el-card class="box-card">
                        <i class="el-icon-d-arrow-left" :style="{'height': (height - 20) + 'px', 'line-height': (height - 20) + 'px'}" style="left: 11px;" @click="lastFile(index)"></i>
                        <i class="el-icon-d-arrow-right" :style="{'height': (height - 20) + 'px', 'line-height': (height - 20) + 'px'}" style="right: calc(20% + 7px);"  @click="nextFile(index)"></i>
                        <img  v-if="item.type.indexOf('image') >= 0" :src="item.storePath" :style="{'max-height': (height - 60) + 'px', 'max-width': '100%', 'vertical-align': 'middle'}"
                            alt="">
                        <img v-else  :style="{'max-height': (height - 60) + 'px', 'max-width': '100%', 'vertical-align': 'middle'}" src="../assets/image/pictureWarning.png"
                            alt="">
                    </el-card>
                </div>
                <div class="detail-right">
                    <el-card class="box-card" :style="{'height': (height - 20) + 'px' }">
                        <div slot="header" class="clearfix">
                          <span>{{ dialog.subtitle?dialog.subtitle:'' }}</span>
                        </div>
                        <p style="text-align: left;">
                            {{item.name}}
                        </p>
                        <p style="text-align: left;">
                            {{item.detail}}
                        </p>
                        <p style="text-align: left; font-size: 12px; color: #A59F9F;">
                          {{dateFormatterTool(item.dateTime,'yyyy-MM-dd hh:mm:ss')}}
                        </p>
                        <p style="text-align: left; font-size: 12px; color: #A59F9F;">
                          <a :href="item.storePath" :download="item.name">下载</a>
                        </p>
                    </el-card>
                </div>
                <div style="clear: both;"></div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { dateFormatterTool } from '@assets/common/common'
export default {
  name: 'BaseFolderDetail',
  props: {
    dialog: {
      type: Object,
      default: {
        subtitle: `文件信息`, // 标题默认为‘文件信息’,可不传，传入则覆盖
        visible: false, // 是否显示窗口，必须传入
        key: '', // 当前显示文件的key，必须传入
        fileList: [] // 附件对象集合, 必须传入
      }
    }
  },
  data () {
    return {
      time: new Date(),
      visible: true
    }
  },
  created () {
  },
  computed: {
    height () {
      return window.document.body.clientHeight
    }
  },
  methods: {
    openCallback () {
    },
    lastFile (index) {
      if (index === 0) {
        this.$message.warning('当前是第一个文件!!!')
      } else {
        this.dialog.key = this.dialog.fileList[index - 1].key
        this.dialog.fileList.sort()
      }
    },
    nextFile (index) {
      if (index === this.dialog.fileList.length - 1) {
        this.$message.warning('当前是最后一个文件!!!')
      } else {
        this.dialog.key = this.dialog.fileList[index + 1].key
        this.dialog.fileList.sort()
      }
    },
    dateFormatterTool: dateFormatterTool
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.base-folder-detail {
  .detail-left {
    width: 80%;
    float: left;
    height: 100%;
  }
  .detail-right {
    width: calc(20% - 10px);
    margin-left: 10px;
    float: left;
    height: 100%;
  }
}
</style>

<style lang="scss" >
.base-folder-detail {
  .el-dialog__body {
    padding: 10px;
    text-align: center;
  }
  .el-icon-d-arrow-left, .el-icon-d-arrow-right {
    position: absolute;
    top: 11px;
    font-size: 28px;
    font-weight: bold;
    color: #b8b6b6;
    cursor: pointer;
  }
  .el-icon-d-arrow-left:hover, .el-icon-d-arrow-right:hover {
    background: #a7a7a738;
    color: white;
  }
  .el-dialog__header {
    padding: 0;
  }
}
</style>