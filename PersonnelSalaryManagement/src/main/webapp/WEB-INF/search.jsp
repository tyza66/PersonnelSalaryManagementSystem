<%--
  Created by IntelliJ IDEA.
  User: tyza66
  Date: 2023/8/2
  Time: 11:27
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

        .search-title {
            text-align: center;
            margin-bottom: 50px;
        }

        .search-input {
            display: block;
            width: 280px !important;
        }

        .search {
            width: 350px;
            margin: 0 auto;
        }

        .search-control button {
            margin: 0 auto;
        }

        .search-out {
            text-align: center;
            margin: 0 auto;
            margin-top: 50px;
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
<div class="app">
    <div style="height:50px;line-height:50px;border-bottom:1px solid #ccc;display:flex;">
        <div style="width:200px;margin-left:30px;font-weight:bold;color:#559EFF;">人员薪资管理</div>
        <div style="flex:1;"></div>
        <div style="width:110px; margin-top:8px;margin-right:25px;">
        </div>
    </div>
    <div class="home" style="width: 100%;padding: 10px">
        <div class="search">
            <h2 class="search-title">员工薪资查询</h2>
            <h4></h4>
            <el-form>
                <el-form-item label="姓名">
                    <el-input class="search-input" type="text" v-model="name" placeholder="在此输入用户名"></el-input>
                    <br/>
                </el-form-item>
                <el-form-item label="年份">
                    <el-checkbox v-model="year_open">启用</el-checkbox>
                    <el-input class="search-input" type="number" v-model="year" placeholder="在此输入要查找的年份"
                              :disabled="!year_open"></el-input>
                </el-form-item>
                <el-form-item label="月份">
                    <el-checkbox v-model="month_open">启用</el-checkbox>
                    <el-input class="search-input" type="number" v-model="month" placeholder="在此输入要查找的月份"
                              :disabled="!month_open"></el-input>
                </el-form-item>
                <el-form-item class="search-control">
                    <el-button type="default" @click="search()">查找</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="search-out">{{ out }}</div>
    </div>
</div>
<script>
    var app = new Vue({
        el: '.app',
        data: {
            name: '',
            year: '',
            month: '',
            out: "未查找，您可以通过姓名搭配年，月，或年月一起查找",
            year_open: false,
            month_open: false
        },
        methods: {
            search(){
                if(!this.year_open && !this.month_open){
                    // 只有姓名
                    axios.get("/salary/searchByName",{
                        params:{
                            "username":this.name
                        }
                    }).then(res=>{
                        this.out = "您被统计过的全部工资总和有"+res.data.data+"元";
                    }).catch(err=>{
                        console.log(err);
                    })
                }else if(this.year_open && !this.month_open){
                    // 姓名+年份
                    axios.get("/salary/searchByNameAndYear",{
                        params:{
                            "username":this.name,
                            "year":this.year
                        }
                    }).then(res=>{
                        this.out = "您"+this.year+"年被统计过的全部工资总和有"+res.data.data+"元";
                    }).catch(err=>{
                        console.log(err);
                    })
                }else if(!this.year_open && this.month_open){
                    // 姓名+月份
                    axios.get("/salary/searchByNameAndMonth",{
                        params:{
                            "username":this.name,
                            "month":this.month
                        }
                    }).then(res=>{
                        this.out = "您每年的"+this.month+"月被统计过的全部工资总和有"+res.data.data+"元";
                    }).catch(err=>{
                        console.log(err);
                    })
                }else if(this.year_open && this.month_open){
                    // 姓名+年份+月份
                    axios.get("/salary/searchByNameAndYearAndMonth",{
                        params:{
                            "username":this.name,
                            "year":this.year,
                            "month":this.month
                        }
                    }).then(res=>{
                        this.out = "您"+this.year+"年的"+this.month+"月被统计过的全部工资总和有"+res.data.data+"元";
                    }).catch(err=>{
                        console.log(err);
                    })
                }
            }
        }
    })
</script>
</body>
</html>
