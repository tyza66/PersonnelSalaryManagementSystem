<%--
  Created by IntelliJ IDEA.
  User: tyza66
  Date: 2023/8/2
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工薪资管理系统-jsp</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script src="js/vue.js"></script>
    <script src="js/eindex.js"></script>
    <script src="js/axios.min.js"></script>
    <link rel="stylesheet" href="css/eindex.css">
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .register-title {
            text-align: center;
            margin-bottom: 50px;
        }

        .register-input {
            display: block;
            width: 280px !important;
        }

        .register {
            width: 350px;
            margin: 0 auto;
        }

        .register-control button {
            margin: 0 auto;
        }

        .el-form-item {
            margin-bottom: 10px !important;
        }

        .el-form-item__content {
            line-height: 20px !important;
        }
    </style>
</head>
<body>
<div id="app">
    <div style="height:50px;line-height:50px;border-bottom:1px solid #ccc;display:flex;">
        <div style="width:200px;margin-left:30px;font-weight:bold;color:#559EFF;">人员薪资管理</div>
        <div style="flex:1;"></div>
        <div style="width:110px; margin-top:8px;margin-right:25px;">
        </div>
    </div>
    <div class="home" style="width: 100%;padding: 10px">
        <div class="register">
            <h2 class="register-title">管理员注册</h2>
            <el-form>
                <el-form-item label="账号">
                    <el-input class="register-input" type="text" v-model="username"
                              placeholder="在此输入用户名"></el-input>
                    <br/>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input class="register-input" type="password" v-model="password"
                              placeholder="在此输入用户密码"></el-input>
                </el-form-item>
                <el-form-item class="register-control">
                    <el-button type="primary" @click="register()">注册</el-button>
                    <el-button type="default" @click="goLogin()">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</div>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            username: '',
            password: ''
        },
        methods: {
            register() {
                var that = this;
                axios.post('admin/register', {
                    username: this.username,
                    password: this.password
                }).then(function (response) {
                    if (response.data.code === 200) {
                        that.$message({
                            message: "注册成功，一秒后将会跳转到登录页面",
                            type: 'success'
                        })
                        setTimeout(()=>{
                            window.location.href = "http://localhost:9090/login";
                        },1000)
                    } else {
                        that.$message({
                            message: response.data.msg,
                            type: 'error'
                        })
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            goLogin() {
                window.location.href = "http://localhost:9090/login";
            }
        }
    })
</script>
</body>
</html>
