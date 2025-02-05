<script setup>
import { ref, watch, onMounted } from 'vue'
import { useModelShowStore } from '@/stores/modelShow'
import { storeToRefs } from 'pinia'
const ModelShowStore = useModelShowStore()
const { isShowDelModel, isShowMask } = ModelShowStore
const { showDelModel } = storeToRefs(ModelShowStore)
const props = defineProps({
  delKey: {
    type: Number,
    default: null,
  },
})

watch(
  () => props.delKey,
  (item) => {
    delKey.value = item
  },
)
const emit = defineEmits(['del-list'])
const delKey = ref()
function delFn() {
  isShowMask(false)
  isShowDelModel(false)
  emit('del-list', delKey)
}
</script>
<template>
  <div class="model delModel" :class="{ active: showDelModel }">
    <div class="header">
      <h3>JOURNAL / CASH <span>刪除項目</span></h3>
    </div>
    <div class="body">
      <p class="color-danger">請問是否確定刪除 ID為{{ delKey }} 的項目?</p>
      <p>(刪除後無法復原，請謹慎思考)</p>
    </div>
    <div class="btn-group">
      <button type="button" class="cancel" @click="isShowMask(false), isShowDelModel(false)">
        取消
      </button>
      <button type="button" class="del" @click="delFn">確定刪除</button>
    </div>
  </div>
</template>
