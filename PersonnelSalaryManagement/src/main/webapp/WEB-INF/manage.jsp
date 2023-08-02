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

        .manage-table {
            width: 930px;
            margin-top: 45px;
        }

        .manage-control {
            width: 400px;
            height: 460px;
            background-color: #efefef;
            position: fixed;
            right: 0;
            top: 100px;
            border-radius: 10px 0 0 10px;
            display: flex;
            flex-direction: column;
        }

        .manage-control div {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .manage-control div button {
            margin-left: 0 !important;
            margin: 5px 10px;
            width: 80%;
        }

        .dialog-footer {
            display: flex;
            justify-content: center;
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
        <el-dialog title="编辑" :visible.sync="editDialogVisible" width="30%" :before-close="handleClose">
            <div>
                <el-form>
                    <el-form-item label="姓名">
                        <el-input v-model="editForm.name" placeholder="姓名"></el-input>
                    </el-form-item>
                    <el-form-item label="年份">
                        <el-input v-model="editForm.year" placeholder="年时间" type="number"></el-input>
                    </el-form-item>
                    <el-form-item label="月份">
                        <el-input v-model="editForm.month" placeholder="月时间" type="number"></el-input>
                    </el-form-item>
                    <el-form-item label="薪资">
                        <el-input v-model="editForm.salary" placeholder="薪资" type="number"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateSalary()">确 定</el-button>
      </span>
        </el-dialog>
        <el-dialog title="添加" :visible.sync="addDialogVisible" width="30%" :before-close="handleClose">
            <div>
                <el-form>
                    <el-form-item label="姓名">
                        <el-input v-model="addForm.name" placeholder="姓名"></el-input>
                    </el-form-item>
                    <el-form-item label="年份">
                        <el-input v-model="addForm.year" placeholder="年时间" type="number"></el-input>
                    </el-form-item>
                    <el-form-item label="月份">
                        <el-input v-model="addForm.month" placeholder="月时间" type="number"></el-input>
                    </el-form-item>
                    <el-form-item label="薪资">
                        <el-input v-model="addForm.salary" placeholder="薪资" type="number"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addSalary()">确 定</el-button>
      </span>
        </el-dialog>
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
                        <template v-slot="scope">
                            <el-button type="text" size="small" @click="openEdit(scope.row)">编辑</el-button>
                            <el-button type="text" size="small" @click="delete1(scope.row.id)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="manage-control">
                <h2 style="text-align:center;margin-top:10px;margin-buttom:10px;">可执行的操作</h2>
                <div>
                    <h3 style="text-align:center;">分页操作</h3>
                    <el-button type="default" @click="prePage()">上一页</el-button>
                    <span>共{{ pageCount }}页，当前第{{ now }}页</span>
                    <el-button type="default" @click="nextPage()">下一页</el-button>
                </div>
                <div>
                    <h3 style="text-align:center;">表单操作</h3>
                    <el-button type="primary" @click="getFirstPage()">刷新表单</el-button>
                    <el-button type="primary" @click="addOpen()">添加信息</el-button>
                    <el-input style="margin-top: 20px;" v-model="search" placeholder="在此输入搜索条件"></el-input>
                    <el-button type="primary" @click="searchByName()">按姓名查询</el-button>
                    <el-button type="primary" @click="searchByID()">按ID查询</el-button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var app = new Vue({
        el: '.app',
        data: {
            tableData: [{
                "id": "1",
                "name": "1",
                "year": "1",
                "month": "1",
                "salary": "1",
            }],
            pageCount: 0,
            now: 1,
            search: '',
            editForm: {
                "id": "",
                "name": "",
                "year": "",
                "month": "",
                "salary": "",
            }, addForm: {
                "name": "",
                "year": "",
                "month": "",
                "salary": ""
            },
            editDialogVisible: false,
            addDialogVisible: false
        },
        created() {
            this.checkLogin();
            this.getFirstPage();
        },
        methods: {
            checkLogin() {
                axios.get("/admin/check").then(res => {
                    if (res.data.code == 201) {
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
                //如果没有找到指定的cookie键，则返回null或任何你指定的默认值
                return null;
            }, getFirstPage() {
                this.now = 1
                axios.get("/salary/list", {
                    params: {
                        "page": 1,
                        "limit": 10
                    },
                    headers: {
                        "satoken": this.getCookie("satoken")
                    }
                }).then(res => {
                    this.tableData = res.data.data
                    this.pageCount = res.data.count
                }).catch(err => {
                    console.log(err)
                })
            }, getPage(page, limit) {
                axios.get("/salary/list", {
                    params: {
                        "page": page,
                        "limit": limit
                    },
                    headers: {
                        "satoken": this.getCookie("satoken")
                    }
                }).then(res => {
                    this.tableData = res.data.data
                    this.pageCount = res.data.count
                }).catch(err => {
                    console.log(err)
                })
            }, nextPage() {
                if (this.now < this.pageCount) {
                    this.now++
                    this.getPage(this.now, 10)
                } else {
                    this.$message({
                        message: '已经是最后一页了',
                        type: 'warning'
                    });
                }
            }, prePage() {
                if (this.now > 1) {
                    this.now--
                    this.getPage(this.now, 10)
                } else {
                    this.$message({
                        message: '已经是第一页了',
                        type: 'warning'
                    });
                }
            }, searchByName() {
                axios.get("/salary/getByUsername", {
                    params: {
                        "username": this.search
                    },
                    headers: {
                        "satoken": this.getCookie("satoken")
                    }
                }).then(res => {
                    this.tableData = res.data.data
                    this.pageCount = 1
                    this.now = 1
                }).catch(err => {
                    console.log(err)
                })
            }, searchByID() {
                axios.get("/salary/getById", {
                    params: {
                        "id": this.search
                    },
                    headers: {
                        "satoken": this.getCookie("satoken")
                    }
                }).then(res => {
                    this.tableData = []
                    this.tableData[0] = res.data.data
                    this.pageCount = 1
                    this.now = 1
                }).catch(err => {
                    console.log(err)
                })
            }, delete1(id) {
                console.log(id)
                this.$confirm(
                    '确定要删除吗?',
                    '删除',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
                )
                    .then(() => {
                        axios.post("/salary/delete", {
                            "id": id
                        }, {
                            headers: {
                                "satoken": this.getCookie("satoken")
                            }
                        }).then(res => {
                            this.$message({
                                type: 'success',
                                message: '删除成功!',
                            })
                            this.getFirstPage()
                        }).catch(err => {
                            console.log(err)
                        })
                    })
                    .catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除',
                        })
                    })
            }, handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            openEdit(row) {
                this.editDialogVisible = true
                this.editForm.id = row.id
                this.editForm.name = row.name
                this.editForm.year = row.year
                this.editForm.month = row.month
                this.editForm.salary = row.salary
            }, updateSalary() {
                axios.post("/salary/update", this.editForm, {
                    headers: {
                        "satoken": this.getCookie("satoken")
                    }
                }).then(res => {
                    if (res.data.code == 200) {
                        this.$message({
                            type: 'success',
                            message: '修改成功!',
                        })
                        this.getFirstPage()
                        this.editDialogVisible = false
                    } else {
                        this.$message({
                            type: 'error',
                            message: '修改失败!',
                        })
                    }
                }).catch(err => {
                    console.log(err)
                })
            }, addOpen() {
                this.addDialogVisible = true
            }, addSalary() {
                axios.post("/salary/add", this.addForm, {
                    headers: {
                        "satoken": this.getCookie("satoken")
                    }
                }).then(res => {
                    if (res.data.code == 200) {
                        this.$message({
                            type: 'success',
                            message: '添加成功!',
                        })
                        this.getFirstPage()
                        this.addDialogVisible = false
                    } else {
                        this.$message({
                            type: 'error',
                            message: '添加失败!',
                        })
                    }
                }).catch(err => {
                    console.log(err)
                })
                this.addForm.name = ""
                this.addForm.year = ""
                this.addForm.month = ""
                this.addForm.salary = ""
            }
        }
    })
</script>
</body>
</html>
