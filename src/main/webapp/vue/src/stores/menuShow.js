import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useMenuShowStore = defineStore('menu-show', () => {
  const showMenu = ref(false)
  const showMask = ref(false)
  function menuToggle () {
    showMenu.value = !showMenu.value
  }
  function menuClose () {
    showMenu.value = false
    showMask.value = false
    document.querySelector('body').removeAttribute('style')
  }
  function maskToggle () {
    showMask.value = !showMask.value
    if (showMask.value) {
      document.querySelector('body').setAttribute('style', 'overflow: hidden')
    } else if (!showMask.value) {
      document.querySelector('body').removeAttribute('style')
    }
  }
  return { showMenu, menuToggle, menuClose, maskToggle, showMask }
})
