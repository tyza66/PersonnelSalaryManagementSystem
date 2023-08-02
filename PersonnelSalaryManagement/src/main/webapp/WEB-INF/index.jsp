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
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script src="js/vue.js"></script>
    <script src="js/eindex.js"></script>
    <script src="js/axios.min.js"></script>
    <link rel="stylesheet" href="css/eindex.css">
    <style>
        *{
            margin: 0;
            padding: 0;
        }

        .header{
            width: 100%;
            height: 100px;
            background-color: #00a0e9;
        }
    </style>
</head>
<body>
<div id="app">
    <div class="header"></div>
</div>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!'
        },created(){

        },methods:{

        }
    })
</script>
</body>
</html>
