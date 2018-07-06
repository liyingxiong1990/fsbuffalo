import py from '../config/pinyin'
/**
 * 根据数据字典代码返回对应的值
 * @param {any} dict 数据字典
 * @param {any} cellValue dict_code
 * @returns {String} dict_value
 */
export function tableColumnFormatterTool (dict, cellValue) {
  let value = cellValue + ''
  for (var key in dict) {
    let code = dict[key].dict_code + ''
    let val = dict[key].dict_value
    if (code === value) {
      return val
    }
  }
  return cellValue
}

/**
 * Formatter Date
 * @export
 * @param {Number} time milliseconds
 * @param {String} fmt e.g.：'yyyy-MM-dd hh:mm:ss' keys:yyyy MM dd hh mm ss qq SSS
 * @returns {String} formatter date value
 */
export function dateFormatterTool (time, fmt) {
  let date
  if (time) {
    date = new Date(time)
  } else {
    return time
  }
  if (fmt == null) {
    fmt = 'yyyy-MM-dd hh:mm:ss'
  }
  var o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds(),
    'q+': Math.floor((date.getMonth() + 3) / 3),
    'S': date.getMilliseconds()
  }
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  for (var k in o) {
    if (new RegExp('(' + k + ')').test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
  }
  return fmt
}

function arraySearch (str, type) {
  for (let name in py.pinyin) {
    if (py.pinyin[name].indexOf(str) !== -1) {
      return ucfirst(name, type)
    }
  }
  return false
}

function ucfirst (str, type) {
  if (str.length > 0) {
    let first = str.substr(0, 1).toUpperCase()
    if (!type) {
      return first
    }
    if (type === 'initial') {
      return first
    } else if (type === 'all') {
      let spare = str.substr(1, str.length)
      return first + spare
    }
  }
}

/**
 * 根据文字返回对应的拼音
 * @param {String} name1 文字
 * @param {String} type  默认值为initial   initial === 只返回大写的拼音首字母   all === 返回完整的拼音且大写首字母
 * @returns {String} 拼音
 */
export function convertPinyinTool (name1, type) {
  if (name1) {
    let str = ''
    let reg = new RegExp('[a-zA-Z0-9-]')
    for (let i = 0; i < name1.length; i++) {
      let val = name1.substr(i, 1)
      let name = arraySearch(val, type)
      if (reg.test(val)) {
        str += val
      } else if (name !== false) {
        str += name
      }
    }
    str = str.replace(/ /g, '-')
    while (str.indexOf('--') > 0) {
      str = str.replace('--', '-')
    }
    return str
  }
}

export function dialogClose (done) {
  let vm = this
  if (this.dialog.type === 'post' || this.dialog.type === 'put') {
    this.$confirm('是否关闭' + this.dialog.title + '窗口', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(function () {
      for (var key in vm.$refs) {
        if (vm.$refs[key + ''].hasOwnProperty('resetFields')) {
          vm.$refs[key + ''].resetFields()
        }
      }
      done()
    })
  } else {
    done()
  }
}

/**
 * mouseRightKeyStop 阻止鼠标右键菜单
 */
export function mouseRightKeyStop () {
  var e = window.evt || window.event
  if (e.preventDefault) {
    e.preventDefault()
  }

  if (e.stopPropagation) {
    e.stopPropagation()
  } else {
    e.returnValue = false // 解决IE8右键弹出
    e.cancelBubble = true
  }
}

/**
 * priceFormatter 价格格式化
 * @param {*} number 价格
 * @param {*} decimals 保留几位小数
 * @param {*} decimalPoint 小数点符号
 * @param {*} thousandsSep 千分位符号
 */
export function priceFormatter (number, decimals, decimalPoint, thousandsSep) {
  number = (number + '').replace(/[^0-9+-Ee.]/g, '')
  let n = !isFinite(+number) ? 0 : +number
  let prec = !isFinite(+decimals) ? 0 : Math.abs(decimals)
  let sep = (typeof thousandsSep === 'undefined') ? ',' : thousandsSep
  let dec = (typeof decimalPoint === 'undefined') ? '.' : decimalPoint
  let s = ''
  var toFixedFix = function (n, prec) {
    var k = Math.pow(10, prec)
    return '' + Math.ceil(n * k) / k
  }

  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.')
  var re = /(-?\d+)(\d{3})/
  while (re.test(s[0])) {
    s[0] = s[0].replace(re, '$1' + sep + '$2')
  }

  if ((s[1] || '').length < prec) {
    s[1] = s[1] || ''
    s[1] += new Array(prec - s[1].length + 1).join('0')
  }
  return s.join(dec)
}
