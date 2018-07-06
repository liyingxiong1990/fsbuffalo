/**
 * @function wordExport
 * 导出DOC文件
 * @export
 * @param {any} fileName 文件名称
 * @param {any} fileId 导出区域根元素ID
 */
export function wordExport (fileName, fileId) {
  fileId = typeof fileId !== 'undefined' ? fileId : 'jQuery-Word-Export'
  var globalValue = {
    mhtml: {
      top: `Mime-Version: 1.0\nContent-Base: ` + location.href + `\nContent-Type: Multipart/related; boundary="NEXT.ITEM-BOUNDARY";type="text/html"\n\n--NEXT.ITEM-BOUNDARY\nContent-Type: text/html; charset="utf-8"\nContent-Location: " + location.href + "\n\n<!DOCTYPE html>\n<html>\n_html_</html>`,
      head: `<head>\n<meta http-equiv="Content-Type" content="text/html; charset=utf-8">\n<style>\n_styles_\n</style>\n</head>\n`,
      body: `<body>_body_</body>`
    }
  }
  var options = {
    maxWidth: 624
  }
  // Clone selected element before manipulating it
  var markup = window.$('#' + fileId).clone()

  // Remove hidden elements from the output
  markup.each(function () {
    var self = window.$(this)
    self.find('textarea').parent().css('background', '#fff')
    self.find('.hide-ele').remove()
    self.find('.el-input__inner').each(function () {
      let ele = window.$(this)
      let value = ele.val()
      // If the input DOM root equal to 'TD' and replaces the element DOM
      let fn = function (el) {
        let parent = el.parent()
        if (parent && parent.length > 0 && parent[0].tagName.toUpperCase() === 'TD') {
          parent.html(value)
        } else {
          if (parent && parent.length > 0) {
            fn(parent)
          }
        }
      }
      fn(ele)
    })
    if (self.is(':hidden')) {
      self.remove()
    }
  })

  // Embed all images using Data URLs
  var images = []
  var img = markup.find('img')
  for (let i = 0; i < img.length; i++) {
    // Calculate dimensions of output image
    var w = Math.min(img[i].width, options.maxWidth)
    var h = img[i].height * (w / img[i].width)
    // Create canvas for converting image to data URL
    var canvas = document.createElement('CANVAS')
    canvas.width = w
    canvas.height = h
    // Draw image to canvas
    var context = canvas.getContext('2d')
    context.drawImage(img[i], 0, 0, w, h)
    // Get data URL encoding of image
    var uri = canvas.toDataURL('image/png')
    window.$(img[i]).attr('src', img[i].src)
    img[i].width = w
    img[i].height = h
    // Save encoded image to array
    images[i] = {
      type: uri.substring(uri.indexOf(':') + 1, uri.indexOf(';')),
      encoding: uri.substring(uri.indexOf(';') + 1, uri.indexOf(',')),
      location: window.$(img[i]).attr('src'),
      data: uri.substring(uri.indexOf(',') + 1)
    }
  }

  // Prepare bottom of mhtml file with image data
  var mhtmlBottom = '\n'
  for (let i = 0; i < images.length; i++) {
    mhtmlBottom += '--NEXT.ITEM-BOUNDARY\n'
    mhtmlBottom += 'Content-Location: ' + images[i].location + '\n'
    mhtmlBottom += 'Content-Type: ' + images[i].type + '\n'
    mhtmlBottom += 'Content-Transfer-Encoding: ' + images[i].encoding + '\n\n'
    mhtmlBottom += images[i].data + '\n\n'
  }
  mhtmlBottom += '--NEXT.ITEM-BOUNDARY--'

  // TODO: load css from included stylesheet
  var styles = ''

  // Aggregate parts of the file together
  var fileContent = globalValue.mhtml.top.replace('_html_', globalValue.mhtml.head.replace('_styles_', styles) + globalValue.mhtml.body.replace('_body_', markup.html())) + mhtmlBottom

  // Create a Blob with the file contents
  var blob = new Blob([fileContent], {
    type: 'application/msword;charset=utf-8'
  })
  // Auto download
  var url = URL.createObjectURL(blob)
  var a = document.createElement('a')
  a.href = url
  a.download = fileName + '.doc'
  a.click()
}

/**
 * @function tableEditInit
 * 表格编辑初始化
 * 依赖jquery
 * @export
 */
export function tableEditInit () {
  let $ = window.$
  // 1.显示编辑框
  // 2.删除class=‘text-note’的元素
  let fn = function () {
    let main = this.childNodes
    for (let node of main) {
      if (node.type === 'textarea') {
        $(node).removeClass('hide-ele')
        $(node).select()
      }
      if (node.className === 'text-note') {
        $(node).remove()
      }
    }
  }
  // 重新绑定单击事件
  let tdEle = $('td')
  tdEle.unbind('click')
  tdEle.click(fn)
  let divEle = $(`div [class='row-edit']`)
  divEle.unbind('click')
  divEle.click(fn)
  // 编辑框
  let textareaEle = $('textarea')
  // 设置可编辑区域颜色
  textareaEle.each(function () {
    let self = $(this)
    self.parent().css('background', '#eaeaea')
  })
  textareaEle.unbind('blur')
  // 1.隐藏编辑框
  // 2.展示文本框
  textareaEle.blur(function () {
    let self = $(this)
    self.addClass('hide-ele')
    let textNote = document.createElement('p')
    textNote.style.width = 'inherit'
    textNote.style.wordBreak = 'break-all'
    textNote.className = 'text-note'
    let selfParent = self.parent()
    if (selfParent[0].className === 'row-edit') {
      textNote.style.display = 'inline'
    }
    selfParent.append($(textNote).append(self.val().replace(/\n/g, '<br>')))
  })
}
