<%@ page import="com.tyza66.personnelsalarymanagement.pojo.UserSalary" %>
<%@ page import="java.util.List" %><%--
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
        background-color: #f5f5f5;
        border-top: 1px;
        overflow: auto;
    }

    .menu {
        position: fixed;
        top: 100px;
        width: 100px;
        height: 300px;
        background-color: #00a0e9;
    }

    .menu h4 {
        text-align: center;
        line-height: 50px;
        color: #fff;
    }

    .table-context {
        width: 700px;
        background-color: #8c939d;
        padding: 10px;
        margin: 0 auto;
        margin-top: 50px;
    }

    .menu2 {
        position: fixed;
        top: 100px;
        right: 0;
        width: 100px;
        height: 300px;
        background-color: #00a0e9;
        display: none;
    }

    .menu2 h4 {
        text-align: center;
        line-height: 50px;
        color: #fff;
    }
</style>
<div class="head">
    <h1>人员薪资管理系统</h1>
</div>

<div class="main">
    <div class="menu">
        <h4>功能菜单</h4>
        <ul style="list-style: none;">
            <form action="/pure/manage?mode=1" method="post">
                <li><input style="width: 100%;" type="text" hidden/></li>
                <li><input style="width: 100%;" type="text" placeholder="姓名" name="xm"/></li>
                <li><input style="width: 100%;" type="text" placeholder="年日期" name="nrq"/></li>
                <li><input style="width: 100%;" type="text" placeholder="月日期" name="yrq"/></li>
                <li><input style="width: 100%;" type="text" placeholder="薪资" name="xz"/></li>
                <li>
                    <button style="width: 100%;">添加薪资信息</button>
                </li>
            </form>
            <li><input style="width: 100%;" type="text" placeholder="搜索信息" id="key"/></li>
            <li>
                <button style="width: 100%;" onclick="searchByID()">按ID查找</button>
                <button style="width: 100%;" onclick="clearSearch()">清除搜索</button>
            </li>
            <li></li>
        </ul>
    </div>
    <div class="menu2">
        <h4>编辑菜单</h4>
        <ul style="list-style: none;">
            <form action="/pure/manage?mode=2" method="post">
                <li><input style="width: 100%;" type="text" disabled value="" placeholder="待修改ID" name="id1"/></li>
                <li><input style="width: 100%;" type="text" hidden name="id" value="" placeholder="待修改ID"/></li>
                <li><input style="width: 100%;" type="text" placeholder="姓名" name="xm"/></li>
                <li><input style="width: 100%;" type="text" placeholder="年日期" name="nrq"/></li>
                <li><input style="width: 100%;" type="text" placeholder="月日期" name="yrq"/></li>
                <li><input style="width: 100%;" type="text" placeholder="薪资" name="xz"/></li>
                <li>
                    <button style="width: 100%;">提交修改</button>
                </li>
            </form>
        </ul>
    </div>

    <div class="table-context">
        <table border="1" align="center" style="text-align: center;width: 600px;margin: 0 auto;">
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>年日期</th>
                <th>月日期</th>
                <th>薪资</th>
                <th>操作</th>
            </tr>
            <%-- <tr>
                 <td>1</td>
                 <td>张三</td>
                 <td>2021</td>
                 <td>8</td>
                 <td>
                     <button>编辑</button>
                     <button>删除</button>
                 </td>
             </tr>--%>
            <%
                if (session.getAttribute("userSalary") != null) {
                    List<UserSalary> salaries = (List<UserSalary>) session.getAttribute("userSalary");
                    for (UserSalary salary : salaries) {
            %>
            <tr>
                <td><%=salary.getId()%>
                </td>
                <td><%=salary.getName()%>
                </td>
                <td><%=salary.getYear()%>
                </td>
                <td><%=salary.getMonth()%>
                </td>
                <td><%=salary.getSalary()%>
                </td>
                <td>
                    <button onclick="edit1('<%=salary.getId()%>','<%=salary.getName()%>','<%=salary.getYear()%>','<%=salary.getMonth()%>','<%=salary.getSalary()%>')">
                        编辑
                    </button>
                    <button onclick="delete1(<%=salary.getId()%>)">删除</button>
                </td>
            </tr>
            <%
                    }
                }
            %>

        </table>
    </div>
</div>

<script>
    window.onload = function () {
        <%if(session.getAttribute("username")==null){%>
        alert("您还未登录，请先登录，一秒后将跳回登录界面")
        setTimeout(function () {
            window.location.href = "/pure/login"
        }, 1000)
        <%}%>

        <%
        if(session.getAttribute("add")!=null){
        %>

        alert("<%=session.getAttribute("info")%>")
        <%}%>

        <%
        session.removeAttribute("add");
        %>

        <%
        if(session.getAttribute("delete")!=null){
        %>

        alert("<%=session.getAttribute("info")%>")
        clearSearch();
        <%}%>

        <%
        session.removeAttribute("delete");
        %>

        <%
        if(session.getAttribute("edit")!=null){
        %>

        alert("<%=session.getAttribute("info")%>")
        clearSearch();
        <%}%>

        <%
        session.removeAttribute("edit");
        %>

    }

    function searchByID() {
        var id = document.getElementById("key").value;
        if (id == null || id == "") {
            alert("请输入要查找的ID")
        } else {
            window.location.href = "/pure/manage?mode=1&key=" + id
        }
    }

    function delete1(id) {
        var b = confirm("确定要删除吗？");
        if (b) {
            window.location.href = "/pure/manage?mode=2&key=" + id
        }
    }

    function clearSearch() {
        window.location.href = "/pure/manage"
    }

    function edit1(id, name, year, month, salary) {
        document.getElementsByName("id1")[0].value = id;
        document.getElementsByName("id")[0].value = id;
        document.getElementsByName("xm")[1].value = name;
        document.getElementsByName("nrq")[1].value = year;
        document.getElementsByName("yrq")[1].value = month;
        document.getElementsByName("xz")[1].value = salary;

        //点击编辑的时候才让编辑菜单显示出来
        document.getElementsByClassName("menu2")[0].style.display = "block";
    }
</script>

</body>
</html>
