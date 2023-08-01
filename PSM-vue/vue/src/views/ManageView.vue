<template>
  <div class="home" style="width: 100%;padding: 10px">
    <div>
      <div class="manage-table">
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column fixed prop="id" label="ID" width="150">
          </el-table-column>
          <el-table-column fixed prop="name" label="姓名" width="150">
          </el-table-column>
          <el-table-column fixed prop="year" label="年日期" width="150">
          </el-table-column>
          <el-table-column fixed prop="month" label="月日期" width="150">
          </el-table-column>
          <el-table-column fixed prop="salary" label="薪资" width="200">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="132">
            <span slot-scope="scope">
              <el-button type="text" size="small">编辑</el-button>
              <el-button type="text" size="small">删除</el-button>
            </span>
          </el-table-column>
        </el-table>
      </div>
      <div class="manage-control">
        <h2 style="text-align:center;margin-top:10px;margin-buttom:10px;">可执行的操作</h2>
        <div>
          <h3 style="text-align:center;">分页操作</h3>
        </div>
        <div>
          <h3 style="text-align:center;">表单操作</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.manage-table {
  width: 930px;
}

.manage-control {
  width: 400px;
  height: 500px;
  background-color: #efefef;
  position: fixed;
  right: 0;
  top: 100px;
  border-radius: 10px 0 0 10px;
  display: flex;
  flex-direction: column;
}
</style>

<script>
import { request } from '@/utils/request';

export default {
  name: 'ManageView',
  components: {

  },
  data() {
    return {
      tableData: [{
        "id": "1",
        "name": "1",
        "year": "1",
        "month": "1",
        "salary": "1",
      }]
    }
  },
  created() {
    this.checkLogin();
    this.getFirstPage();
  },
  methods: {
    checkLogin() {
      request.get("/admin/check", {
        headers: {
          "satoken": this.getCookie("satoken")
        }
      }).then(res => {
        if (res.code == 201) {
          this.$message({
            message: '您还未登录，一秒后跳转到登录界面',
            type: 'warning'
          });
          setTimeout(() => {
            this.$router.push('/login');
          }, 1000)
        }
      }).catch(err => {
        console.log(err)
      })
    }, setCookie(key, value) {
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
    },getFirstPage(){
      request.get("/salary/list",{
        params:{
          "page":1,
          "limit":10
        },
        headers: {
          "satoken": this.getCookie("satoken")
        }
      }).then(res=>{
        this.tableData = res.data
      }).catch(err=>{
        console.log(err)
      })
    }
  }
}
</script>
