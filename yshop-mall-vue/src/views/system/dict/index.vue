<template>
  <div class="app-container">
    <!--表单组件-->
    <el-dialog append-to-body :close-on-click-modal="false" :before-close="cancel" v-model="dialog" :title="getFormTitle()" width="500px">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
        <el-form-item label="字典名称" prop="name">
          <el-input v-model="form.name" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.remark" style="width: 370px;" />
        </el-form-item>
      </el-form>
      <template #footer>
<div class="dialog-footer">
        <el-button type="text" @click="cancel">取消</el-button>
        <el-button :loading="loading" type="primary" @click="submitMethod">确认</el-button>
      </div>
</template>
    </el-dialog>
    <!-- 字典列表 -->
    <el-row :gutter="10">
      <el-col :xs="24" :sm="24" :md="10" :lg="10" :xl="10" style="margin-bottom: 10px">
        <el-card class="box-card">
          <template #header>
<div class="clearfix">
            <span>字典列表</span>
            <!-- 新增 -->
            <el-button
              v-permission="['admin','dict:add']"
              class="filter-item"
              size="small"
              style="float: right;padding: 4px 10px"
              type="primary"
              :icon="Plus"
              @click="showAddFormDialog"
            >新增</el-button>
          </div>
</template>
          <!--工具栏-->
          <div class="head-container">
            <!-- 搜索 -->
            <el-input v-model="query.blurry" clearable size="small" placeholder="输入名称或者描述搜索" style="width: 200px;" class="filter-item" @keyup.enter="toQuery" />
            <el-button class="filter-item" size="small" type="success" :icon="Search" @click="toQuery">搜索</el-button>
            <!-- 导出 -->
            <el-button
              :loading="downloadLoading"
              size="small"
              class="filter-item"
              type="warning"
              :icon="Download"
              @click="downloadMethod"
            >导出</el-button>
          </div>
          <!--表格渲染-->
          <el-table v-loading="loading" :data="data" highlight-current-row style="width: 100%;" @current-change="handleCurrentChange">
            <el-table-column :show-overflow-tooltip="true" prop="name" label="名称" />
            <el-table-column :show-overflow-tooltip="true" prop="remark" label="描述" />
            <el-table-column v-if="checkPermission(['admin','dict:edit','dict:del'])" label="操作" width="130px" align="center" fixed="right">
              <template #default="scope">
                <el-button v-permission="['admin','dict:edit']" size="small" type="primary" :icon="Edit" @click="showEditFormDialog(scope.row)" />
                <el-popover
                  :ref="scope.row.id"
                  v-permission="['admin','dict:del']"
                  placement="top"
                  width="180"
                >
                  <p>此操作将删除字典与对应的字典详情，确定要删除吗？</p>
                  <div style="text-align: right; margin: 0">
                    <el-button size="small" type="text" @click="$refs[scope.row.id].doClose()">取消</el-button>
                    <el-button :loading="delLoading" type="primary" size="small" @click="delMethod(scope.row.id)">确定</el-button>
                  </div>
                  <template #reference>
<el-button type="danger" :icon="Delete" size="small" />
</template>
                </el-popover>
              </template>
            </el-table-column>
          </el-table>
          <!--分页组件-->
          <el-pagination
            :total="total"
            :current-page="page + 1"
            style="margin-top: 8px;"
            layout="total, prev, pager, next, sizes"
            @size-change="sizeChange"
            @current-change="pageChange"
          />
        </el-card>
      </el-col>
      <!-- 字典详情列表 -->
      <el-col :xs="24" :sm="24" :md="14" :lg="14" :xl="14">
        <el-card class="box-card">
          <template #header>
<div class="clearfix">
            <span>字典详情</span>
            <el-button
              v-if="checkPermission(['admin','dict:add']) && this.$refs.dictDetail && this.$refs.dictDetail.dictName"
              class="filter-item"
              size="small"
              style="float: right;padding: 4px 10px"
              type="primary"
              :icon="Plus"
              @click="$refs.dictDetail.showAddFormDialog"
            >新增</el-button>
          </div>
</template>
          <dictDetail ref="dictDetail" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import crud from '@/mixins/crud'
import dictDetail from './dictDetail'
import crudDict from '@/api/system/dict'
export default {
  name: 'Dict',
  components: { dictDetail },
  mixins: [crud],
  data() {
    return {
      title: '字典',
      crudMethod: { ...crudDict },
      queryTypeOptions: [
        { key: 'name', display_name: '字典名称' },
        { key: 'remark', display_name: '描述' }
      ],
      form: { id: null, name: null, remark: null },
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.$nextTick(() => {
      this.init()
    })
  },
  methods: {
    // 获取数据前设置好接口地址
    beforeInit() {
      this.url = 'api/dict'
      if (this.$refs.dictDetail) {
        this.$refs.dictDetail.data = []
        this.$refs.dictDetail.dictName = ''
      }
      return true
    },
    // 选中字典后，设置字典详情数据
    handleCurrentChange(val) {
      if (val) {
        this.$refs.dictDetail.dictName = val.name
        this.$refs.dictDetail.form.dict.id = val.id
        this.$refs.dictDetail.init()
      }
    }
  }
}
</script>

<style scoped>
</style>
