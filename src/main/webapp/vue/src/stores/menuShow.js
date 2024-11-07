import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useMenuShowStore = defineStore('menu-show', () => {
  const showMenu = ref(false)
  function menuToggle () {
    showMenu.value = !showMenu.value
  }
  function menuClose () {
    showMenu.value = false
  }
  return { showMenu, menuToggle, menuClose }
})
