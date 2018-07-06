import XLSX from 'xlsx'
import { dateFormatterTool } from './common'
/**
 * CSV文件转换为JSON
 * @export
 * @param {File} file CSV文件
 * @param {Object} titlesMap e.g.{'文件标题名称': '映射名称'}
 * @param {String} encoding e.g. 'gb2312'、'gbk'、'utf-8'.默认为'gb2312'
 * @param {String} splitRow 分隔行符;默认'\r'
 * @param {String} splitColumn 分割列符;默认','
 * @returns {primise} resolve(JSON)
 */
export function csvConvertToJSON (file, titlesMap, encoding, splitRow, splitColumn) {
  if (!encoding) {
    encoding = 'gb2312'
  }
  if (!splitRow) {
    splitRow = '\r'
  }
  if (!splitColumn) {
    splitColumn = ','
  }
  var reader = new FileReader()
  reader.readAsText(file, encoding) // 以文本格式读取
  return new Promise(function (resolve, reject) {
    reader.onload = function (evt) {
      var data = evt.target.result // 读到的数据
      let rows = data.split(splitRow) // 分行
      if (rows.length < 2) {
        reject(new Error('Length is less than 2.'))
      }
      let titles = rows[0].split(splitColumn) // 标题
      if (titles.length < 1) {
        reject(new Error('Titles is less than 1.'))
      }
      if (titlesMap) {
        titles.map(function (val, index) {
          if (titlesMap[val]) {
            titles[index] = titlesMap[val]
          }
        })
      }
      let result = rows.reduce(function (resVal, currentVal, index) {
        let columns = currentVal.replace('\n', '').split(splitColumn)
        if (titles.length === columns.length) {
          let row = {}
          titles.map(function (title, index) {
            row[title] = columns[index]
          })
          if (!resVal.push) {
            resVal = []
          }
          resVal.push(row)
          return resVal
        }
        return resVal
      })
      resolve(result).catch(function (error) {
        reject(error)
      })
    }
  })
}

/**
 * Excel文件转换为JSON对象
 * @param {File} file
 * @param {object} titlesMap e.g. {'JSON键的名称':'替换键的名称'}
 * @returns {Promise} resolve(json)
 */
export function fileConvertToJson (file, titlesMap) {
  var reader = new FileReader()
  reader.readAsArrayBuffer(file)
  return new Promise(function (resolve, reject) {
    reader.onload = function (evt) {
      let data = evt.target.result
      let workbook = XLSX.read(data, {
        type: 'buffer'
      }) // 以二进制流方式读取得到整份excel表格对象
      let result = []
      for (var sheet in workbook.Sheets) {
        if (workbook.Sheets.hasOwnProperty(sheet)) {
          result = result.concat(XLSX.utils.sheet_to_json(workbook.Sheets[sheet]))
          break
        }
      }
      if (titlesMap) {
        result = replaceJsonKeyName(result, titlesMap)
      }
      resolve(result)
    }
  })
}

/**
 * 替换JSON键名称
 * @param {object} json
 * @param {object} map e.g. {'JSON键的名称':'替换键的名称'}
 * @returns {object} json 替换后的JSON对象
 */
export function replaceJsonKeyName (json, map) {
  let convertKeyName = function (row) { // 替换object对象的键名称
    for (let key in row) {
      if (row.hasOwnProperty(key) && map.hasOwnProperty(key)) {
        row[map[key]] = row[key]
        delete row[key]
      }
    }
    return row
  }
  if (json.hasOwnProperty('length')) { // 判断是否为数组对象
    for (let key in json) {
      convertKeyName(json[key])
    }
  } else {
    return convertKeyName(json)
  }
  return json
}
/**
 * fileExport 文件导出
 * @export
 * @param {Object} json JSON数据
 * @param {String} name 文件名称
 * @param {String} fileType 文件类型 e.g.{'xlsx','xlml','csv','html'.'txt'}
 */
export function fileExport (json, name, fileType) {
  let fileName = name + '-' + dateFormatterTool(new Date().getTime(), 'yyyyMMddhhmmss') + '.' + fileType
  let sheet = XLSX.utils.json_to_sheet(json)
  let wb = {
    SheetNames: ['Sheet1'],
    Sheets: {
      'Sheet1': sheet
    }
  }
  XLSX.writeFile(wb, fileName)
}
