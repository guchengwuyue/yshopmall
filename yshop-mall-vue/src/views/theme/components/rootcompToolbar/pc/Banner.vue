/**
  轮播图编辑
 */
<template>
  <div class="editorBannerBox">
      <h2>轮播图</h2>
      <ul class="editorList">
        <draggable v-model="activeComponent.componentContent.bannerList" :item-key="dragItemKey">
            <template #item="{ element: item, index: index }">
              <li
        class="item"
        >
        <DraggableSlot :obj="item" :index="index" @deleteIt="deleteIt">
            <div class="addContent">
                <ToolSingleImg
                v-if="item.pic !== undefined && item.pic !== null"
                v-model:imageUrl="item.pic" />
                <div class="titleBox">{{ item.title }}</div>
                <el-input
                    placeholder="描述"
                    resize="none"
                    v-model="item.des"
                />
                <div class="titleBox">{{ '跳转地址' }}</div>
                <el-input
                    placeholder="请输入新的内容"
                    resize="none"
                    v-model="item.linkTo"
                />
            </div>
            <div @click="deleteItem(item, index)" class="deleteItem">
                <span class="iconfont">&#xe633;</span>删除内容
            </div>
        </DraggableSlot>
        </li>
            </template>
          </draggable>
        <div
        class="addImgBtn"
        @click="addImgText(activeComponent.componentContent.bannerList.length)">
          <span class="iconfont">&#xe64a;</span>添加图文
        </div>
      </ul>
      <el-dialog
      title="提示"
      v-model="dialogVisible"
      width="30%"
      :before-close="deleteItem"
      >
        <span>点击确定删除此项</span>
        <template #footer>
<span class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false"
            >确 定</el-button
          >
        </span>
</template>
      </el-dialog>
      <el-dialog v-model="dialogImageVisible">
        <img width="100%" :src="dialogImageUrl" alt="" />
      </el-dialog>
  </div>
</template>

<script>
import Draggable from 'vuedraggable'
import { dragItemKey } from '@/utils/drag-key'
import { toolMixin } from '@/mixins/tool.js'
import ToolSingleImg from '@/views/theme/components/rootcompToolbar/toolModule/tool-single-img.vue'
import DraggableSlot from '@/views/theme/components/rootcompToolbar/pc/components/draggableSlot.vue'
export default {
  name: 'PcBanner',
  mixins: [toolMixin],
  components: {Draggable, ToolSingleImg, DraggableSlot},
  data () {
    return {
      dialogVisible: false,
      dialogImageVisible: false,
      dialogImageUrl: null
    }
  },
  methods: {
    dragItemKey,
    // 添加图文
    addImgText (index) {
      console.log(index)
      this.activeComponent.componentContent.bannerList.push({
        title: `轮播${index + 1}`,
        des: '',
        pic: '',
        url: '',
        linkTo: ''
      })
    },
    deleteItem (item, index) {
        this.$confirm('确定删除此项？')
        .then((_) => {
            this.activeComponent.componentContent.bannerList.splice(index, 1)
        })
        .catch((_) => {})
    },
    deleteIt (index) {
      this.activeComponent.componentContent.bannerList.splice(index, 1)
    }
  }
}
</script>

<style lang="scss">
.editorBannerBox{
  padding: 8px 12px;
  .editorList{
    .item {
        border: 1px solid #e8eaec;
        border-radius: 4px;
        margin: 10px 0;
    }
    .addImgBtn {
      border-radius: 4px;
      background: $mainColor;
      text-align: center;
      height: 36px;
      color: #ffffff;
      font-size: 14px;
      display: flex;
      align-items: center;
      justify-content: center;
      cursor: pointer;
      margin-bottom: 30px;
      span {
        font-size: 20px;
        margin-right: 5px;
      }
    }
  }
}
</style>
