import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useRoute } from 'vue-router'

export const useIsActiveStore = defineStore('isActive', () => {
  // 當前menu選單名稱
  const isActive = ref('')
  const route = useRoute()
  function navActive (active) {
    isActive.value = active
  }

  // 判斷當前路由位置
  function getPath () {
    const curPath = route.path
    switch (curPath) {
      case '/':
        isActive.value = 'home'
        break
      case '/project':
        isActive.value = 'project'
        break
      case '/report':
        isActive.value = 'report'
        break
      case '/journal_cash':
        isActive.value = 'journal_cash'
        break
      case '/subjects':
        isActive.value = 'subjects'
        break
      case '/account':
        isActive.value = 'account'
        break
      case '/member':
        isActive.value = 'member'
        break
      case '/profile':
        isActive.value = 'profile'
        break
    }
  }
  return { isActive, navActive, getPath }
})
