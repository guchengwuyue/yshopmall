/**
    编辑器_专区导航组件
*/
<template>
  <div class="editorNavType">
    <h2>专区导航</h2>
    <ul class="editorList">
      <draggable v-model="activeComponent.componentContent.typeRoute" :item-key="dragItemKey">
            <template #item="{ element: item, index: index }">
              <li
        class="item"
        >
          <DraggableSlot :obj="item" :index="index" @deleteIt="deleteIt">
            <div class="addContent">
                <!-- 文本编辑 -->
                <div class="titleBox">{{ '模块' }}</div>
                <el-input
                    placeholder="请输入新的内容"
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
      <div class="addImgBtn" @click="addImgText">
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
  </div>
</template>

<script>
import Draggable from 'vuedraggable'
import { dragItemKey } from '@/utils/drag-key'
import { toolMixin } from '@/mixins/tool.js'
import DraggableSlot from '@/views/theme/components/rootcompToolbar/pc/components/draggableSlot.vue'
export default {
    name: 'TopTypeNav',
    mixins: [toolMixin],
    components: {Draggable, DraggableSlot},
    data () {
        return {
          dialogVisible: false
        }
    },
    methods: {
    dragItemKey,
      // 添加图文
      addImgText () {
          this.activeComponent.componentContent.typeRoute.push({
              'title': '',
              'des': '',
              'linkTo': '',
              'pic': ''
          })
      },
      deleteItem (item, index) {
          this.$confirm('确定删除此项？')
          .then((_) => {
              this.activeComponent.componentContent.typeRoute.splice(index, 1)
          })
          .catch((_) => {})
      },
      deleteIt (index) {
        this.activeComponent.componentContent.typeRoute.splice(index, 1)
      }
    }
}
</script>

<style lang="scss">
.editorNavType{
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
