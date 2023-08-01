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
        <el-button type="primary">登录</el-button>
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
    }
  }
}
</script>
