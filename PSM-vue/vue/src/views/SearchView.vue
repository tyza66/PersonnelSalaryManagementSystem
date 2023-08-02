<template>
  <div class="home" style="width: 100%;padding: 10px">
    <div class="search">
      <h2 class="search-title">员工薪资查询</h2>
      <h4></h4>
      <el-form-item label="姓名">

        <el-input class="search-input" type="text" v-model="name" placeholder="在此输入用户名"></el-input><br />
      </el-form-item>
      <el-form-item label="年份">
        <el-input class="search-input" type="number" v-model="year" placeholder="在此输入要查找的年份" :disabled="!year_open"></el-input>
        <el-checkbox v-model="year_open"></el-checkbox>
      </el-form-item>
      <el-form-item label="月份">
        <el-input class="search-input" type="number" v-model="month" placeholder="在此输入要查找的月份" :disabled="!month_open"></el-input>
        <el-checkbox v-model="month_open"></el-checkbox>
      </el-form-item>
      <el-form-item class="search-control">
        <el-button type="default" @click="search()">查找</el-button>
      </el-form-item>
    </div>
    <div class="search-out">{{ out }}</div>
  </div>
</template>

<style>
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
</style>

<script>
import { request } from '@/utils/request';

export default {
  name: 'SearchView',
  components: {

  },
  data() {
    return {
      name: '',
      year: '',
      month: '',
      out: "未查找，您可以通过姓名搭配年，月，或年月一起查找",
      year_open: false,
      month_open: false
    }
  },
  created() {

  },
  methods: {
    goLogin() {
      this.$router.push('/login');
    }
  }
}
</script>
