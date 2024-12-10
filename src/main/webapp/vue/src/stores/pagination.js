import { ref, onMounted } from 'vue'
import { defineStore, storeToRefs } from 'pinia'
import { useJournalStore } from './journal'

export const usePaginationStore = defineStore('paginationStore', () => {
  const JournalStore = useJournalStore()
  const { findData } = JournalStore
  const { journalData } = storeToRefs(JournalStore) // 取得反應式的 journalData
  const pageTotal = ref(1)
  const currPage = ref(1)
  const data = ref([])

  async function initPage(e) {
    try {
      pageTotal.value = 1
      currPage.value = 1
      if (e !== undefined) {
        await findData(e) // 等待資料獲取完成
      } else {
        await findData()
      }
      data.value = journalData.value // 使用 .value 取得實際值
      pageTotal.value = Math.ceil(data.value.length / 10)
    } catch (error) {
      console.error('資料獲取失敗', error)
    }
  }

  onMounted(() => {
    initPage()
  })
  return { pageTotal, currPage, initPage }
})
