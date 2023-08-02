<%--
  Created by IntelliJ IDEA.
  User: tyza66
  Date: 2023/8/2
  Time: 9:35
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
        .home-title{
            text-align: center;
            margin-bottom: 50px;
        }

        .home-main{

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
        <div class="home-title">
            <h2>工资管理系统</h2>
            <div class="home-main">
                在这里可以展示各种公示和主页信息<br/>
                当前登陆状态：{{ status }}<br/>

                <br/><br/>
                jsp版本：
                <el-button @click="goManage()">前往管理界面</el-button>
                <el-button @click="goLogin()">前往登录界面</el-button>
                <el-button @click="goRegister()">前往注册界面</el-button>
                <el-button @click="goSearch()">前往员工查工资界面</el-button>


                <br/><br/>
                vue版本：
                <el-button @click="goVueHome()">前往门户导航</el-button>
                <el-button @click="goVueManage()">前往管理界面</el-button>
                <el-button @click="goVueLogin()">前往登录界面</el-button>
                <el-button @click="goVueRegister()">前往注册界面</el-button>
                <el-button @click="goVueSearch()">前往员工查工资界面</el-button>

                <br/><br/>
                其他操作：
                <el-button @click="logout()">退出登录</el-button>

            </div>
        </div>
    </div>
</div>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            status: '未登录'
        }, created() {
            this.checkLogin();
        }, methods: {
            goVueHome(){
                window.location.href = "http://localhost:8080";
            },
            goVueManage(){
                window.location.href = "http://localhost:8080/manage";
            },
            goVueLogin(){
                window.location.href = "http://localhost:8080/login";
            },
            goVueRegister(){
                window.location.href = "http://localhost:8080/register";
            },
            goVueSearch(){
                window.location.href = "http://localhost:8080/search";
            },checkLogin(){
                axios.get("/admin/check").then(res=>{
                    if(res.data.code==200){
                        this.status = "已登录";
                    }else{
                        this.status = "未登录";
                    }
                })
            },goManage(){
                window.location.href = "http://localhost:9090/manage.jsp";
            },goLogin(){
                window.location.href = "http://localhost:9090/login.jsp";
            },goRegister(){
                window.location.href = "http://localhost:9090/register.jsp";
            },goSearch(){
                window.location.href = "http://localhost:9090/search.jsp";
            },logout(){
                axios.get("/admin/logout").then(res=>{
                    if(res.data.code==200){
                        alert("退出成功");
                        window.location.href = "http://localhost:9090";
                    }else{
                        alert("退出失败");
                    }
                })
            }
        }
    })
</script>
</body>
</html>
