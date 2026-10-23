<template>
  <div v-if="type == 'image'">
    <div v-if="myValue != ''">
      <ul class="el-upload-list el-upload-list--picture-card">
        <li tabindex="0" class="el-upload-list__item is-ready" :style="'width: '+width+'px;height: '+height+'px'">
          <div>
            <img :src="myValue" alt="" class="el-upload-list__item-thumbnail">
            <span class="el-upload-list__item-actions">
              <span class="el-upload-list__item-delete" @click="deleteMaterial">
                <el-icon><Delete /></el-icon>
              </span>
            </span>
          </div>
        </li>
      </ul>
    </div>
    <div v-else tabindex="0" class="el-upload el-upload--picture-card" :style="'width: '+width+'px;height: '+height+'px;'+'line-height:'+height+'px;'" @click="toSeleteMaterial">
      <el-icon><Plus /></el-icon>
    </div>

    <el-dialog
      v-model="dialogVisible"
      append-to-body
      width="35%"
    >
      <img :src="url" alt="" style="width: 100%">
    </el-dialog>

    <el-dialog
      v-model="listDialogVisible"
      title="图片素材库"
      append-to-body
      width="70%"
    >
      <el-container>
        <el-aside width="unset">
          <div style="margin-bottom: 10px">
            <el-button
              :icon="Plus"
              size="small"
              @click="materialgroupAdd()"
            >
              添加分组
            </el-button>
          </div>
          <el-tabs v-model="materialgroupObjId" v-loading="materialgroupLoading" tab-position="left" @tab-click="tabClick">
            <el-tab-pane
              v-for="item in materialgroupList"
              :key="item.id"
              :name="String(item.id)"
            >
              <template #label>
                <span>{{ item.name }}</span>
              </template>
            </el-tab-pane>
          </el-tabs>
        </el-aside>
        <el-main>
          <el-card>
            <template #header>
              <div>
                <el-row>
                  <el-col :span="12">
                    <span>{{ materialgroupObj.name }}</span>
                    <span v-if="materialgroupObj.id != '-1'">
                      <el-button size="small" type="primary" link :icon="Edit" style="margin-left: 10px;" @click="materialgroupEdit(materialgroupObj)">重命名</el-button>
                      <el-button size="small" type="danger" link :icon="Delete" style="margin-left: 10px;" @click="materialgroupDelete(materialgroupObj)">删除</el-button>
                    </span>
                  </el-col>
                  <el-col :span="12" style="text-align: right;">
                    <el-upload
                      :action="uploadApi"
                      :headers="headers"
                      :file-list="[]"
                      :on-progress="handleProgress"
                      :before-upload="beforeUpload"
                      :on-success="handleSuccess"
                      :data="{type: 1}"
                      multiple
                    >
                      <el-button size="small" type="primary">批量上传</el-button>
                    </el-upload>
                  </el-col>
                </el-row>
              </div>
            </template>
            <div v-loading="tableLoading">
              <el-alert
                v-if="tableData.length <= 0"
                title="暂无数据"
                type="info"
                :closable="false"
                center
                show-icon
              />
              <el-checkbox-group v-model="urls" :max="1">
                <el-row :gutter="5">
                  <el-col v-for="(item,index) in tableData" :key="index" :span="4">
                    <el-card :body-style="{ padding: '5px' }">
                      <el-image
                        style="width: 100%;height: 100px"
                        :src="item.url"
                        fit="contain"
                        :preview-src-list="[item.url]"
                        preview-teleported
                        :z-index="9999"
                      />
                      <div>
                        <el-checkbox class="material-name" :value="item.url">
                          选择
                        </el-checkbox>
                        <el-row>
                          <el-col :span="24" class="col-do">
                            <el-button type="primary" link size="small" @click="materialDel(item)">删除</el-button>
                          </el-col>
                        </el-row>
                      </div>
                    </el-card>
                  </el-col>
                </el-row>
              </el-checkbox-group>
              <el-pagination
                v-model:current-page="page.currentPage"
                :page-sizes="[12, 24]"
                :page-size="page.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="page.total"
                class="pagination"
                @size-change="sizeChange"
                @current-change="pageChange"
              />
            </div>
          </el-card>
        </el-main>
      </el-container>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="listDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="sureUrls">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>

</template>

<script>
import { getList as materialgroupPage, addObj as materialgroupAdd, delObj as materialgroupDel, putObj as materialgroupEdit } from '@/api/tools/materialgroup'
import { getPage, addObj, delObj, putObj } from '@/api/tools/material'
import { getToken } from '@/utils/auth'
import { mapGetters } from 'vuex'

export default {
  name: 'MaterialList',
  props: {
    modelValue: {
      type: String,
      default: undefined
    },
    value: {
      type: String,
      default: undefined
    },
    type: {
      type: String
    },
    num: {
      type: Number,
      default() {
        return 5
      }
    },
    width: {
      type: Number,
      default() {
        return 150
      }
    },
    height: {
      type: Number,
      default() {
        return 150
      }
    }
  },
  emits: ['update:modelValue', 'input'],
  data() {
    return {
      headers: {
        Authorization: getToken()
      },
      dialogVisible: false,
      myValue: '',
      url: '',
      listDialogVisible: false,
      materialgroupList: [],
      materialgroupObjId: '',
      materialgroupObj: {},
      materialgroupLoading: false,
      tableData: [],
      resultNumber: 0,
      page: {
        total: 0,
        currentPage: 1,
        pageSize: 12,
        ascs: [],
        descs: 'create_time'
      },
      tableLoading: false,
      groupId: null,
      urls: []
    }
  },
  computed: {
    ...mapGetters([
      'uploadApi'
    ]),
    boundValue() {
      if (this.modelValue !== undefined) return this.modelValue || ''
      return this.value || ''
    }
  },
  watch: {
    boundValue: {
      immediate: true,
      handler(val) {
        this.myValue = val || ''
      }
    }
  },
  methods: {
    emitValue(val) {
      this.myValue = val || ''
      this.$emit('update:modelValue', this.myValue)
      this.$emit('input', this.myValue)
    },
    deleteMaterial() {
      this.$confirm('是否确认删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.emitValue('')
        this.urls = []
      })
    },
    toSeleteMaterial() {
      this.listDialogVisible = true
      if (this.tableData.length <= 0) {
        this.materialgroupPage()
      }
    },
    materialgroupPage() {
      this.materialgroupLoading = true
      materialgroupPage({
        total: 0,
        page: 1,
        size: 100,
        ascs: [],
        sort: 'create_time,desc'
      }).then(response => {
        this.materialgroupLoading = false
        const materialgroupList = Array.isArray(response) ? response : []
        materialgroupList.unshift({
          id: '-1',
          name: '全部分组'
        })
        this.materialgroupList = materialgroupList
        this.selectGroup(0)
      }).catch(() => {
        this.materialgroupLoading = false
      })
    },
    materialgroupDelete(materialgroupObj) {
      this.$confirm('是否确认删除该分组？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        materialgroupDel(materialgroupObj.id)
          .then(() => {
            this.materialgroupList.splice(materialgroupObj.index, 1)
          })
      })
    },
    materialgroupEdit(materialgroupObj) {
      this.$prompt('请输入分组名', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputValue: materialgroupObj.name
      }).then(({ value }) => {
        materialgroupEdit({
          id: materialgroupObj.id,
          name: value
        }).then(() => {
          materialgroupObj.name = value
          if (materialgroupObj.index != null) {
            this.materialgroupList[materialgroupObj.index] = materialgroupObj
          }
        })
      }).catch(() => {})
    },
    materialgroupAdd() {
      this.$prompt('请输入分组名', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        materialgroupAdd({
          name: value
        }).then(() => {
          this.materialgroupPage()
        })
      }).catch(() => {})
    },
    resolveTabIndex(tab) {
      if (tab == null) return 0
      if (typeof tab.index === 'number' || typeof tab.index === 'string') {
        const n = Number(tab.index)
        return Number.isNaN(n) ? 0 : n
      }
      const name = tab.paneName ?? tab.props?.name
      if (name != null) {
        const i = this.materialgroupList.findIndex(g => String(g.id) === String(name))
        return i >= 0 ? i : 0
      }
      return 0
    },
    tabClick(tab) {
      this.selectGroup(this.resolveTabIndex(tab))
    },
    selectGroup(index) {
      this.urls = []
      const materialgroupObj = this.materialgroupList[index]
      if (!materialgroupObj) return
      materialgroupObj.index = index
      this.materialgroupObj = materialgroupObj
      this.materialgroupObjId = String(materialgroupObj.id)
      this.page.currentPage = 1
      this.page.total = 0
      if (String(materialgroupObj.id) !== '-1') {
        this.groupId = materialgroupObj.id
      } else {
        this.groupId = null
      }
      this.getPage(this.page)
    },
    getPage(page, params) {
      this.tableLoading = true
      getPage(Object.assign({
        page: page.currentPage - 1,
        size: page.pageSize,
        descs: this.page.descs,
        ascs: this.page.ascs,
        sort: 'create_time,desc'
      }, {
        groupId: this.groupId
      })).then(response => {
        this.tableData = response.content || []
        this.page.total = response.totalElements || 0
        this.page.currentPage = page.currentPage
        this.page.pageSize = page.pageSize
        this.tableLoading = false
      }).catch(() => {
        this.tableLoading = false
      })
    },
    sizeChange(val) {
      this.page.currentPage = 1
      this.page.pageSize = val
      this.getPage(this.page)
    },
    pageChange(val) {
      this.page.currentPage = val
      this.getPage(this.page)
    },
    materialRename(item) {
      this.$prompt('请输入素材名', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputValue: item.name
      }).then(({ value }) => {
        putObj({
          id: item.id,
          name: value
        }).then(() => {
          this.getPage(this.page)
        })
      }).catch(() => {})
    },
    materialUrl(item) {
      this.$prompt('素材链接', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputValue: item.url
      }).then(() => {}).catch(() => {})
    },
    materialDel(item) {
      this.$confirm('是否确认删除该素材？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delObj(item.id)
          .then(() => {
            this.getPage(this.page)
          })
      })
    },
    handleCommand(command) {
      const s = command.split('-')
      putObj({
        id: s[0],
        groupId: s[1]
      }).then(() => {
        this.getPage(this.page)
      })
    },
    handleProgress() {},
    handleSuccess(response, file, fileList) {
      addObj({
        type: '1',
        groupId: this.groupId !== '-1' ? this.groupId : null,
        name: file.name,
        url: response.link
      }).then(() => {
        this.resultNumber++
        if (fileList.length === this.resultNumber) {
          this.getPage(this.page)
          this.resultNumber = 0
        }
      })
    },
    beforeUpload(file) {
      const isPic =
        file.type === 'image/jpeg' ||
        file.type === 'image/png' ||
        file.type === 'image/gif' ||
        file.type === 'image/jpg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isPic) {
        this.$message.error('上传图片只能是 JPG、JPEG、PNG、GIF 格式!')
        return false
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isPic && isLt2M
    },
    sureUrls() {
      if (this.urls && this.urls.length > 0) {
        this.emitValue(this.urls[0])
      }
      this.urls = []
      this.listDialogVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
  :deep(.el-icon-circle-close) {
    color: red;
  }
  .material-name {
    padding: 8px 0;
  }
  .col-do {
    text-align: center;
  }
  .button-do {
    padding: unset !important;
    font-size: 12px;
  }
  .pagination {
    margin-top: 12px;
    justify-content: flex-end;
  }
</style>
