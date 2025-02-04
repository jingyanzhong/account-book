import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useJournalStore = defineStore('journalCRUD', () => {
  const journalData = ref()
  // 日記帳data資料按照 日期 時間 重新排序
  function journalDataSort(d) {
    journalData.value = d.value.sort((a, b) => {
      // 首先按照日期排序
      if (a.txTime.date < b.txTime.date) return 1 // 日期較早的排在後面
      if (a.txTime.date > b.txTime.date) return -1 // 日期較晚的排在前面

      // 如果日期相同，則按照時間排序
      if (a.txTime.timePoint < b.txTime.timePoint) return 1 // 時間較早的排在後面
      if (a.txTime.timePoint > b.txTime.timePoint) return -1 // 時間較晚的排在前面

      return 0 // 如果日期和時間都相同，則不變
    })
    dataRender()
  }

  const allDate = ref()
  function allDateSort(d) {
    allDate.value = d.value.sort((a, b) => {
      if (a.year !== b.year) {
        return b.year - a.year
      }
      return b.month - a.month
    })
  }

  const currData = ref([])
  function dataRender(page = 1) {
    const start = page * 10 - 10
    const end = page * 10
    currData.value = journalData.value.filter((item, index) => {
      return index >= start && index < end
    })
  }

  return {
    allDate,
    dataRender,
    currData,
    journalDataSort,
    journalData,
    allDateSort,
  }
})
