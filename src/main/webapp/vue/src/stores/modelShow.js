import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useModelShowStore = defineStore('model-show', () => {
  const isNew = ref(true)
  // show 新增 model
  const showModel = ref(false)
  function isShowModel (bool) {
    showModel.value = bool
  }

  //   show 刪除項目 model
  const showDelModel = ref(false)
  function isShowDelModel (bool) {
    showDelModel.value = bool
  }

  //   show model 黑色遮罩
  const showMask = ref(false)
  function isShowMask (bool) {
    if (bool) {
      document.querySelector('body').setAttribute('style', 'overflow: hidden')
    } else if (!bool) {
      document.querySelector('body').removeAttribute('style')
    }
    showMask.value = bool
  }

  return { showModel, showMask, isShowModel, isShowMask, showDelModel, isShowDelModel, isNew }
})
