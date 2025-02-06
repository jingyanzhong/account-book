import { ref, onMounted } from 'vue'
import { defineStore, storeToRefs } from 'pinia'
import { useJournalStore } from '@/stores/journal'
import { useApiServeStore } from './apiServe'

export const usePaginationStore = defineStore('paginationStore', () => {
  const apiServeStore = useApiServeStore()
  const { findData } = apiServeStore

  const JournalStore = useJournalStore()
  const { sortData } = storeToRefs(JournalStore)

  const pageTotal = ref(1)
  const currPage = ref(1)

  async function initPage(e) {
    try {
      pageTotal.value = 1
      currPage.value = 1
      if (e !== undefined) {
        await findData(e) // 等待資料獲取完成
      } else {
        await findData()
      }
      pageTotal.value = Math.ceil(sortData.value.journalData.length / 10)
    } catch (error) {
      console.error('資料獲取失敗', error)
    }
  }

  onMounted(() => {
    initPage()
  })
  return { pageTotal, currPage, initPage }
})
