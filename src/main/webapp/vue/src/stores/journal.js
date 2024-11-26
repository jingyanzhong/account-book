import { ref, onMounted } from 'vue'
import axios from 'axios'
import { toast } from 'vue3-toastify'
import { defineStore } from 'pinia'
import { usePaginationStore } from './pagination'

export const useJournalStore = defineStore('journalCRUD', () => {
  // get debit所有項目
  const debitList = ref([])
  function getDebitData() {
    const url = 'http://localhost:8080/account-book/api/subject/list'
    axios.get(url).then((res) => {
      debitList.value = res.data
    })
  }
  onMounted(() => {
    getDebitData()
  })

  // 查詢某年月 日記帳資料 並渲染到畫面上
  const date = new Date()
  const year = ref(date.getFullYear())
  const month = ref(date.getMonth() + 1)
  const optionSort = ref(0)
  const journalData = ref()

  function findData(e) {
    return new Promise((resolve, reject) => {
      if (e !== undefined) {
        year.value = e.target[optionSort.value].dataset.year
        month.value = e.target[optionSort.value].dataset.month
      }

      const url = '//localhost:8080/account-book/api/journalRecord/findByYearMonth'
      const params = new URLSearchParams()
      params.append('year', year.value)
      params.append('month', month.value)

      const data = ref()
      axios
        .post(url, params, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;',
          },
        })
        .then((res) => {
          data.value = res.data.data
          journalDataSort(data)
          resolve()
        })
        .catch((res) => {
          console.log(res)
          toast.success('查詢失敗！')
          reject(res)
        })
    })
  }
  // 日記帳data資料按照 key值 重新排序
  function journalDataSort(d) {
    journalData.value = d.value.sort((a, b) => {
      return a.txTime.date < b.txTime.date ? 1 : -1
    })
    console.log(journalData.value)
    dataRander()
  }

  // 查詢日記帳 年月資料
  const allDate = ref()
  function findDate() {
    const url = '//localhost:8080/account-book/api/journalRecord/list/yearMonth'
    const data = ref()
    axios
      .post(url)
      .then((res) => {
        data.value = res.data.data
        allDateSort(data)
      })
      .catch((res) => {
        console.log(res)
      })
  }

  function allDateSort(d) {
    allDate.value = d.value.sort((a, b) => {
      if (a.year !== b.year) {
        return b.year - a.year
      }
      return b.month - a.month
    })
  }
  onMounted(() => {
    findData()
    findDate()
  })

  // 新增資料
  function updateList(item) {
    const PaginationStore = usePaginationStore()
    const { initPage } = PaginationStore
    const url = '//localhost:8080/account-book/api/journalRecord/add'
    const date = new Date()
    const hours = date.getHours()
    const min = date.getMinutes()
    const sec = date.getSeconds()
    const time = `${hours < 10 ? '0' + hours : hours}:${min < 10 ? '0' + min : min}:${sec < 10 ? '0' + sec : sec}`

    const params = new URLSearchParams()
    params.append('txTime', `${item.txTime.date} ${time}`)
    params.append('debit', item.debit.code)
    params.append('credit', item.credit.code)
    params.append('amount', item.amount)
    params.append('memo', item.memo)

    axios
      .post(url, params, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;',
        },
      })
      .then((res) => {
        console.log(res)
        if (res.data.message === '成功') {
          toast.success('新增成功！')
        } else {
          toast.success('新增異常！' + res.data.data)
        }
        initPage()
      })
      .catch((res) => {
        console.log(res)
        toast.error('新增失敗！')
      })
  }

  // 編輯資料
  function editList(item) {
    const PaginationStore = usePaginationStore()
    const { initPage } = PaginationStore
    const url = '//localhost:8080/account-book/api/journalRecord/modify'

    const params = new URLSearchParams()
    params.append('txTime', `${item.txTime.date} ${item.txTime.timePoint}`)
    params.append('debit', item.debit.code)
    params.append('credit', item.credit.code)
    params.append('amount', item.amount)
    params.append('memo', item.memo)
    params.append('key', item.key)

    axios
      .post(url, params, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;',
        },
      })
      .then((res) => {
        console.log(res)
        if (res.data.message === '成功') {
          toast.success('編輯成功！')
        } else {
          toast.success('編輯異常！' + res.data.data)
        }
        initPage()
      })
      .catch((res) => {
        console.log(res)
        toast.error('編輯失敗！')
      })
  }

  // 刪除資料
  function delList(key) {
    const PaginationStore = usePaginationStore()
    const { initPage } = PaginationStore
    const url = '//localhost:8080/account-book/api/journalRecord/remove'
    const params = new URLSearchParams()
    params.append('key', key.value)
    axios
      .post(url, params, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;',
        },
      })
      .then((res) => {
        console.log(res)
        if (res.data.message === '成功') {
          toast.success('已刪除該筆資料！')
        } else {
          toast.success('刪除異常！' + res.data.data)
        }
        initPage()
      })
      .catch((res) => {
        console.log(res)
      })
  }

  const currData = ref([])
  function dataRander(page = 1) {
    const start = page * 10 - 10
    const end = page * 10
    currData.value = journalData.value.filter((item, index) => {
      return index >= start && index < end
    })

    console.log(currData.value)
  }

  return {
    debitList,
    optionSort,
    journalData,
    allDate,
    findData,
    updateList,
    editList,
    delList,
    dataRander,
    currData,
  }
})
