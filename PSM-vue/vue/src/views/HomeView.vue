<template>
  <div class="home" style="width: 100%;padding: 10px">
    <div class="home-title">
      <h2>工资管理系统</h2>
      <div class="home-main">
        在这里可以展示各种公示和主页信息<br/>
        当前登陆状态：{{ status }}<br/>
        <el-button @click="goManage()">前往管理界面</el-button>
        <el-button @click="goLogin()">前往登录界面</el-button>
        <el-button @click="goRegister()">前往注册界面</el-button>
      </div>
    </div>
  </div>
</template>

<style>
.home-title{
  text-align: center;
  margin-bottom: 50px;
}

.home-main{

}
</style>

<script>
import { request } from '@/utils/request';

export default {
  name: 'HomeView',
  components: {

  },
  data() {
    return {
      status: '未登录'
    }
  },
  created() {
    this.checkLogin();
  },
  methods: {
    goManage() {
      this.$router.push('/manage');
    },setCookie(key, value) {
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
    checkLogin() {
      request.get("/admin/check", {
        headers: {
          "satoken": this.getCookie("satoken")
        }
      }).then(res => {
        if (res.code == 200) {
          this.status = "已登录"
        }
      }).catch(err => {
        console.log(err)
      })
    },
    goLogin(){
      this.$router.push('/login');
    },
    goRegister(){
      this.$router.push('/register');
    }
  }
}
</script>
