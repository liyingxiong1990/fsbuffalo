# sdc

> A Vue.js project


## initialize script

```
# install webpack
npm install -global webpack
# install taobao npm tool.
npm install -g cnpm --registry=https://registry.npm.taobao.org
# install package
cnpm install
npm start
```

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

## Glyphicons icon

- [bootstrap](https://v3.bootcss.com/components/) 
- [element](http://element.eleme.io/#/zh-CN/component/icon) 

## software

- vs code
- node.js
- svn

## vs code setting

```
{
    "workbench.startupEditor": "welcomePage",
    "vetur.format.defaultFormatter.html": "js-beautify-html",
    "vetur.format.defaultFormatter.js": "vscode-typescript",
    "editor.quickSuggestions": {
        "strings": true
    },
    "editor.tabSize": 2,
    "eslint.validate": [
        "javascript",
        "javascriptreact",
        "html",
        "vue",
        {
            "language": "html",
            "autoFix": true
        },
        {
          "language": "javascript",
          "autoFix": true
        }
    ],
    "eslint.autoFixOnSave": true,
    "typescript.format.insertSpaceBeforeFunctionParenthesis": true,
    "javascript.format.insertSpaceBeforeFunctionParenthesis": true,
    "git.ignoreMissingGitWarning": true,
    "explorer.sortOrder": "type",
    "emmet.triggerExpansionOnTab": true,
    "emmet.includeLanguages": {
        "vue-html": "html",
        "vue": "html"
    }
}
```

## vs code plugins

- ESLint
- Beautify css
- vetur
- document this

## code style

- indent code：2 spaces
- code style guide：https://cn.vuejs.org/v2/style-guide/
- eslint


For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
http://sheetjs.com/
https://github.com/MrRio/jsPDF