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
传过来的信息：<%=(String) request.getAttribute("info")%>
<form action="/pure/temp" method="post">
    用户名：<input type="text" name="username" /><br/>
    <input type="submit" value="提交">
</form>


</body>
</html>
