<template>
  <div class="login">
    <div class="login-main">
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="50px" class="login-form">
      <div class="login-title">
        Gdotc UI
      </div>
      <el-form-item label="账号" prop="username">
        <el-input type="text" v-model="ruleForm.username" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 100%;" @click="submitForm('ruleForm')">登录</el-button>
      </el-form-item>
    </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      ruleForm: {},
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
  },
  methods: {
    submitForm (from) {
      let vm = this
      vm.$refs[from].validate((valid) => {
        if (valid) {
          vm.ruleForm.client_id = 'sdc'
          vm.ruleForm.client_secret = 'sdc'
          vm.ruleForm.grant_type = 'password'
          vm.$store.state.http.auto('auth', 'login', { params: vm.ruleForm }).then((res) => {
            window.localStorage.setItem('username', vm.ruleForm.username)
            window.localStorage.setItem('token', res.token_type + ' ' + res.access_token)
            window.localStorage.setItem('TOKEN_TIME', new Date().getTime() + 360000 * 1000)
            vm.$store.dispatch('SYS_INITIAL')
            vm.$router.push('/home')
          }).catch(function (error) {
            vm.$message.error(error.statusText)
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
      return false
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.login {
  .login-form {
    width: 300px;
    border-radius: 3px;
    background: white;
    padding: 35px 40px 40px 30px;
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    margin: auto;
    height: 223px;
    box-shadow: 0 0 5px #bcc2c3;
  }
  .login-title {
    font-family: 微软雅黑;
    font-weight: bold;
    font-size: 26px;
    text-align: center;
    padding-bottom: 25px;
    padding-left: 24px;
  }
}
</style>

<style lang="scss">
.login {
  background-position: center top;
  background-size: cover;
  width: 100%;
  height: 100%;
  input:-webkit-autofill {
    -webkit-box-shadow: 0 0 0px 1000px rgba(255, 255, 255, 1) inset !important;
  }
}
</style>



