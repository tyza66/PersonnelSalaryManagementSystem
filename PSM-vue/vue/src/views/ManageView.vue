<template>
  <div class="home" style="width: 100%;padding: 10px">
    <div>
      <div>
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column fixed prop="id" label="ID" width="150">
          </el-table-column>
          <el-table-column fixed prop="name" label="姓名" width="150">
          </el-table-column>
          <el-table-column fixed prop="year" label="年日期" width="150">
          </el-table-column>
          <el-table-column fixed prop="month" label="月日期" width="150">
          </el-table-column>
          <el-table-column fixed prop="salary" label="薪资" width="150">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button type="text" size="small">查看</el-button>
              <el-button type="text" size="small">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from '@/utils/request';

export default {
  name: 'ManageView',
  components: {

  },
  data() {
    return {
      tableData:[{
        "id":"1",
        "name":"1",
        "year":"1",
        "month":"1",
        "salary":"1",
      }]
    }
  },
  created() {

  },
  methods: {

  }
}
</script>
