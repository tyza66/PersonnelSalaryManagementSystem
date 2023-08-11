<%--
  Created by IntelliJ IDEA.
  User: tyza66
  Date: 2023/8/10
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员薪资管理系统-纯jsp</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    .head {
        width: 100%;
        height: 50px;
        background-color: #00a0e9;
        box-shadow: 0 0 10px #00a0e9;
    }

    .head h1 {
        text-align: center;
        line-height: 50px;
        color: #fff;
    }
    .main{
        width: 100%;
        height: 100%;
        background-color: #f5f5f5;
    }
</style>
<div class="head">
    <h1>人员薪资管理系统</h1>
</div>

<div class="main">

</div>

<script>
    window.onload = function(){
        <%if(session.getAttribute("username")==null){%>
        alert("您还未登录，请先登录，一秒后将跳回登录界面")
        setTimeout(function(){
            window.location.href = "/pure/login"
        },1000)
        <%}%>
    }
</script>

</body>
</html>
