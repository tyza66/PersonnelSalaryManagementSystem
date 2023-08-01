<template>
  <div class="home" style="width: 100%;padding: 10px">
    <div class="login">
      <h2 class="login-title">管理员登录</h2>
      <el-form-item label="账号">
        <el-input class="login-input" type="text" v-model="username" placeholder="在此输入用户名"></el-input><br />
      </el-form-item>
      <el-form-item label="密码">
        <el-input class="login-input" type="password" v-model="password" placeholder="在此输入用户密码"></el-input>
      </el-form-item>
      <el-form-item class="login-control">
        <el-button type="primary" @click="login()">登录</el-button>
        <el-button type="default" @click="goRegister()">注册</el-button>
      </el-form-item>
    </div>
  </div>
</template>

<style>
.login-title {
  text-align: center;
  margin-bottom: 50px;
}

.login-input {
  display: block;
  width: 280px !important;
}

.login{
  width: 350px;
  margin: 0 auto;
}
.login-control button{
  margin: 0 auto;
}
</style>

<script>
import { request } from '@/utils/request';

export default {
  name: 'LoginView',
  components: {

  },
  data() {
    return {
      username: '',
      password: ''
    }
  },
  created() {

  },
  methods: {
    goRegister() {
      this.$router.push('/register');
    },
    setCookie(key, value) {
      // 构建新的cookie字符串  
      var cookie = key + '=' + value + ';';
      // 将新cookie字符串添加到现有的cookie字符串中  
      var existingCookies = document.cookie.split(';');
      for (var i = 0; i < existingCookies.length; i++) {
        if (existingCookies[i].trim().indexOf(key) === 0) {
          // 如果找到指定的cookie键，则用新的值替换它  
          existingCookies[i] = cookie;
          break;
        }
      }
      //如果不存在就在后面直接拼接
      if (i == existingCookies.length) {
        existingCookies.push(cookie)
      }
      // 将修改后的cookie字符串重新组合并设置回document.cookie属性  
      document.cookie = existingCookies.join('');
    }, getCookie(key) {
      // 将所有cookie键和值存储在一个数组中  
      var cookies = document.cookie.split(';');
      // 遍历数组，查找指定键的cookie  
      for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        // 检查cookie是否以键值对的形式存在  
        if (cookie.indexOf('=')) {
          var cookieParts = cookie.split('=');
          // 如果cookie的第一个部分等于指定的键，则返回cookie的值  
          if (cookieParts[0] === key) {
            return cookieParts[1];
          }
        }
      }
      // 如果没有找到指定的cookie键，则返回null或任何你指定的默认值  
      return null;
    },
    login(){
      request.post("/admin/login", {
        username: this.username,
        password: this.password
      }).then(res => {
        if (res.code == 200) {
          this.setCookie("satoken", res.token);
          this.$message({
            message: '登录成功，一秒后跳转到管理界面',
            type: 'success'
          });
          setTimeout(() => {
            this.$router.push('/manage');
          }, 1000)
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
        }
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>
