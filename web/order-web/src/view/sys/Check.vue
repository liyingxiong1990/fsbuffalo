<template>
  <div class="all">
    <div class="box">
      <div class="checkword">
        <div class="one" v-for="(item, index) in show2" :key="index" @click="clickword(index, item)" ref="border">{{item}}</div>
      </div>
      <img class="insidejpg" src="http://bpic.ooopic.com/16/10/73/16107312-87c6acb63010a33b6e94594a9b11044a.jpg" ref="photo">
      <div class="el-icon-refresh reload" @click="random">换一个</div>
      <div class="cover"></div>
    </div>
    <div class="smallbox">
      <div class="explain">请依次点击“{{wordexplain}}"</div>
    </div>
    <br>
    <div class="box">
      <div class="copy" ref="cccc">
        <img class="outsidejpg" src="" ref="movephoto">
      </div>
      <div class="remove" ref="dddd"></div>
      <img class="insidejpg" src="" ref="photo">
      <div class="el-icon-refresh reload" @click="reload">换一张</div>
      <div class="cover"></div>
    </div>
    <div class="smallbox" ref="bbbb"  @mousemove.stop="move" @mouseup.stop="up">
      <div class="explain">{{explain}}</div>
      <div class="button" @mousedown.stop="down" ref="aaaa">
        <div class="el-icon-arrow-right" v-if="wrong"></div>
        <div class="el-icon-check" v-if="right"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'check',
  created () {
    this.random()
  },
  mounted () {
    for (var m = 0; m < 4; m++) {
      let num = Math.floor(Math.random() * (50 - 8 + 1) + 8)
      this.$refs.border[m].style.transform = 'rotate(' + num + 'deg)'
    }
    this.reload()
  },
  computed: {
  },
  data () {
    return {
      x: 0,
      falg: false,
      left: 0,
      number: 0,
      explain: '向右滑动完成拼图以验证',
      wordexplain: '',
      wrong: true,
      right: false,
      show: ['进', '退', '自', '如', '力', '挽', '狂', '澜', '我', '你', '它', '他'],
      show2: [],
      show3: [],
      arr2: [],
      brr: [],
      photoarr: [
        'http://bpic.ooopic.com/16/10/73/16107312-87c6acb63010a33b6e94594a9b11044a.jpg',
        'http://up.enterdesk.com/edpic_source/bf/f2/5c/bff25c52adda065475059b31c2214228.jpg',
        'http://pic2.ooopic.com/01/35/57/17b1OOOPICec.jpg']
    }
  },
  methods: {
    reload () {
      let number = Math.floor(Math.random() * (2 - 0 + 1) + 0)
      this.$refs.movephoto.src = this.photoarr[number]
      this.$refs.photo.src = this.photoarr[number]
      this.number = Math.floor(Math.random() * (300 - 110 + 1) + 110)
      this.$refs.dddd.style.marginLeft = this.number + 'px'
      this.$refs.movephoto.style.marginLeft = '-' + this.number + 'px'
      this.upnumber = Math.floor(Math.random() * (80 - 3 + 1) + 3)
      this.$refs.dddd.style.marginTop = this.upnumber + 'px'
      this.$refs.cccc.style.marginTop = this.upnumber + 'px'
      this.$refs.movephoto.style.marginTop = '-' + this.upnumber + 'px'
    },
    random () {
      this.wordexplain = ''
      this.show2 = []
      this.show3 = []
      this.arr2 = []
      this.brr = []
      let arr3 = [...this.show]
      for (var k = 0; k < 4; k++) {
        var random = Math.floor(Math.random() * this.show.length)
        this.show2[k] = arr3[random]
        arr3.splice(random, 1)
      }
      this.show3 = [...this.show2]
      for (var i = 0, len = this.show3.length; i < len; i++) {
        var randomIndex = Math.floor(Math.random() * this.show3.length)
        this.brr[i] = this.show3[randomIndex]
        this.show3.splice(randomIndex, 1)
      }
      for (var j = 0; j < this.brr.length; j++) {
        this.wordexplain += this.brr[j] + ''
      }
      this.wordexplain += '”'
    },
    down (event) {
      this.x = event.clientX
      this.left = this.$refs.aaaa.offsetLeft
      this.falg = true
      this.explain = ''
    },
    move (event) {
      if (!this.falg) {
        return
      }
      let x = event.clientX - (this.x - this.left)
      if (x > 300) {
        this.$refs.aaaa.style.left = 300 + 'px'
        this.$refs.cccc.style.left = 300 + 'px'
      } else if (x < 0) {
        this.$refs.aaaa.style.left = 0 + 'px'
        this.$refs.cccc.style.left = 0 + 'px'
      } else {
        this.$refs.aaaa.style.left = x + 'px'
        this.$refs.cccc.style.left = x + 'px'
      }
    },
    up () {
      let x = event.clientX - (this.x - this.left)
      if (x > this.number - 5 && x < this.number + 5) {
        this.$refs.bbbb.style.backgroundColor = '#9ED18B'
        this.wrong = false
        this.right = true
        this.$message.success('验证成功')
      } else {
        this.$refs.aaaa.style.left = 0 + 'px'
        this.$refs.cccc.style.left = 0 + 'px'
        this.$refs.bbbb.style.backgroundColor = 'rgba(128, 128, 128, 0.212)'
        this.explain = '向右滑动完成拼图以验证'
        this.wrong = true
        this.right = false
        this.$message('验证失败')
      }
      this.falg = false
    },
    clickword (index, item) {
      this.$refs.border[index].style.color = 'white'
      this.arr2.push(item)
      if (this.brr.length === this.arr2.length) {
        let flag = false
        for (let [index, item] of this.brr.entries()) {
          if (this.arr2[index] !== item) {
            flag = true
          }
        }
        if (!flag) {
          this.$message.success('验证成功')
        } else {
          this.$message.error('验证失败')
        }
      }
    }
  }
}
</script>

<style scoped>
.box {
  width: 340px;
  height: 140px;
  position: relative;
}
.word {
  width: 340px;
}
.insidejpg {
  width: 340px;
  height: 140px;
  position: relative;
  float: left;
}
.checkword {
  position: absolute;
  left: 5px;
  top: 50px;
  z-index: 100;
  color: darkblue;
  font-size: 31px;
  opacity: 0.6;
  font-style: italic;
}
.one {
  float: left;
  margin-left: 40px;
  font-style: italic;
  cursor: pointer;
}
.outsidejpg {
  width: 340px;
  height: 140px;
  z-index: 998;
  margin-top: -30px;
}
.smallbox {
  width: 340px;
  height: 38px;
  background-color: rgba(128, 128, 128, 0.212);
  box-sizing: border-box;
  position: relative;
}
.explain {
  position: absolute;
  margin-top: 9px;
  font-size: 14px;
  margin-left: 29%;
}
.button {
  width: 40px;
  height: 38px;
  background-color: whitesmoke;
  position: absolute;
  cursor: pointer;
}
.cover {
  width: 340px;
  height: 140px;
  position: absolute;
  background-color: white;
  opacity: 0.3;
}
.copy {
  width: 40px;
  height: 38px;
  position: absolute;
  margin-top: 30px;
  z-index: 100;
  overflow: hidden;
  box-shadow: 1px 1px 2px;
}
.remove {
  width: 40px;
  height: 38px;
  background-color: white;
  opacity: 0.9;
  position: absolute;
  margin-top: 30px;
  margin-left: 250px;
  z-index: 50;
  box-shadow: 1px 1px 3px inset;
}
.el-icon-arrow-right {
  margin-top: 27%;
}
.el-icon-check {
  margin-top: 27%;
  color: green;
}
.reload {
  position: absolute;
  right: 5px;
  bottom: 5px;
  z-index: 100;
  color: white;
  font-size: 10px;
  cursor: pointer;
}
</style>
