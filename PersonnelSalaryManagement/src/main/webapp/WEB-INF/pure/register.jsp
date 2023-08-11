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

    .main {
        width: 100%;
        height: 100%;
        padding: 20px;
    }

    .main h3 {
        text-align: center;
        margin-bottom: 20px;
    }

    .main form {
        width: 300px;
        margin: 0 auto;
    }
</style>
<div class="head">
    <h1>人员薪资管理系统</h1>
</div>
<div class="main">
    <h3>用户注册</h3>
    <form action="/pure/register" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <input type="submit" value="注册">
                    <input type="reset" value="重置">
                    <input type="button" value="登录" onclick="goLogin()">
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
    window.onload = function () {
        <%
            if (session.getAttribute("success")!=null&&(session.getAttribute("success")).equals("1")) {
        %>
        alert("<%=session.getAttribute("info")%>");
        setTimeout(function () {
            location.href = "/pure/login";
        }, 1000);
        <% } %>
        <%
            if (session.getAttribute("success")!=null&&(session.getAttribute("success")).equals("0")) {
        %>
        alert("<%=session.getAttribute("info")%>");
        <% } %>
        <%session.setAttribute("success",null);%>
    }

    function goLogin() {
        location.href = "/pure/login";
    }
</script>


</body>
</html>
