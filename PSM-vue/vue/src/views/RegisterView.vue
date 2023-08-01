<template>
  <div class="home" style="width: 100%;padding: 10px">
    <div class="register">
      <h2 class="register-title">管理员注册</h2>
      <el-form-item label="账号">
        <el-input class="register-input" type="text" v-model="username" placeholder="在此输入用户名"></el-input><br />
      </el-form-item>
      <el-form-item label="密码">
        <el-input class="register-input" type="password" v-model="password" placeholder="在此输入用户密码"></el-input>
      </el-form-item>
      <el-form-item class="register-control">
        <el-button type="primary" @click="register()">注册</el-button>
        <el-button type="default" @click="goLogin()">登录</el-button>
      </el-form-item>
    </div>
  </div>
</template>

<style>
.register-title {
  text-align: center;
  margin-bottom: 50px;
}

.register-input {
  display: block;
  width: 280px !important;
}

.register{
  width: 350px;
  margin: 0 auto;
}
.register-control button{
  margin: 0 auto;
}
</style>

<script>
import { request } from '@/utils/request';

export default {
  name: 'RegisterView',
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
      goLogin() {
        this.$router.push('/login');
      },
      register(){
        request.post("/admin/register", {
          username: this.username,
          password: this.password
        }).then(res => {
          if (res.code == 200) {
            this.$message({
              message: '注册成功，一秒后跳转到登录界面',
              type: 'success'
            });
            setTimeout(() => {
              this.$router.push('/login');
            }, 1000);
          } else {
            this.$message({
              message: res.msg,
              type: 'error'
            });
          }
        });
      }
  }
}
</script>
