<template>
  <div class="dialog-print">
    <div v-if="visible" id="dialog_print_main">
      <div id="dialog-print-title">
        {{dialog.title?dialog.title:''}}凭证
        <br><br>
      </div>
      <div id="dialog_print_content"></div>
      <div id="dialog_print_footer">
        <br>
        <p class="print-footer"> 公司（公章）</p>
        <p class="print-footer-time">{{dateFormatterTool(null, 'yyyy年MM月dd日')}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { dateFormatterTool } from 'gdotc@common/assets/common/common'
export default {
  name: 'DialogPrint', // 打印
  props: {
    dialog: {
      type: Object,
      default: {
        title: '',
        visible: false
      }
    },
    /**
     * @prop domId 元素ID
     * 根据ID获取整个DOM
    */
    domId: {
      type: String
    },
    /**
     * @prop fromId 表单id
     * 1.元素从<div id="start"></div>开始截取，截取至元素<div id="end"></div>
     * 2.嵌入当前模板上下文中
    */
    fromId: {
      type: String
    },
    // 是否显示模板
    visible: {
      type: Boolean
    }
  },
  created () {
  },
  computed: {
  },
  watch: {
    // 如果 `question` 发生改变，这个函数就会运行
    visible: function (newQuestion, oldQuestion) {
      if (newQuestion) {
        this.dialogOpen()
      }
    }
  },
  data () {
    return {
      formData: {}
    }
  },
  methods: {
    dateFormatterTool: dateFormatterTool,
    dialogOpen () {
      setTimeout(() => {
        if (this.fromId != null && this.fromId !== '') {
          let printEle = document.createElement('div')
          printEle.id = 'print_view'
          let contentEle = document.getElementById('dialog_print_content')
          let parentNotes = document.getElementById(this.fromId).childNodes
          let isAppend = false
          for (let note of parentNotes) {
            if (note.id === 'start') {
              isAppend = true
              continue
            }
            if (note.id === 'end') {
              isAppend = null
              break
            }
            if (isAppend) {
              contentEle.appendChild(note.cloneNode(true))
            }
          }
          printEle.appendChild(document.getElementById('dialog_print_main').cloneNode(true))
          document.body.appendChild(printEle)
          window.print()
          document.body.removeChild(printEle)
          printEle = null
          this.dialog.visible = false
        } else if (this.domId != null && this.domId !== '') {
          let printEle = document.createElement('div')
          printEle.id = 'print_view'
          let node = document.getElementById(this.domId).cloneNode(true)
          printEle.appendChild(node)
          document.body.appendChild(printEle)
          window.print()
          document.body.removeChild(printEle)
          printEle = null
          this.dialog.visible = false
        }
      }, 0)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
</style>

<style lang="scss">
.dialog-print {
  .el-dialog__header {
    text-align: center;
  }
}
#dialog_print_main {
  #dialog-print-title {
    text-align: center;
    font-weight: 600;
    font-size: 16px;
  }
  #dialog_print_content {
    input {
      background-color: #fff;
      border-color: #fff;
      color: #000;
    }
    textarea {
      background-color: #fff;
      color: #000;
    }
    .form-title-name {
      font-size: 14px;
    }
  }
  #dialog_print_footer {
    .print-footer,.print-footer-time {
      text-align: right;
      font-size: 12px;
    }
  }
  .el-select__caret {
    color: #fff;
  }
}
</style>

