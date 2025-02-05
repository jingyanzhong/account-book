import { ref, onMounted } from 'vue'
import axios from 'axios'
import { toast } from 'vue3-toastify'
import { defineStore } from 'pinia'
import { usePaginationStore } from './pagination'
import { useJournalStore } from '@/stores/journal'

export const useApiServeStore = defineStore('apiServeStore', () => {
  const BASE_API_URL = 'http://localhost:8080/account-book/api'
  // get debit所有項目
  const debitList = ref([])
  function getDebitData() {
    const url = `${BASE_API_URL}/subject/list`
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

  async function findData(e) {
    try {
      if (e !== undefined) {
        year.value = e.target[optionSort.value].dataset.year
        month.value = e.target[optionSort.value].dataset.month
      }

      const url = `${BASE_API_URL}/journalRecord/findByYearMonth`

      const data = {
        year: year.value,
        month: month.value,
      }

      const response = await axios.post(url, data, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;' },
      })

      const result = response.data.data

      const JournalStore = useJournalStore()
      JournalStore.journalDataSort(ref(result))
    } catch (error) {
      console.error(error)
      toast.error('查詢失敗！')
    }
  }
  // 查詢日記帳 年月資料
  function findDate() {
    const url = `${BASE_API_URL}/journalRecord/list/yearMonth`
    const data = ref()
    axios
      .post(url)
      .then((res) => {
        data.value = res.data.data
        const JournalStore = useJournalStore()
        JournalStore.allDateSort(data)
      })
      .catch((res) => {
        console.log(res)
      })
  }
  onMounted(() => {
    findDate()
  })
  // 取得MEMO項目
  const memoData = ref()
  function getMemoData() {
    const url = `${BASE_API_URL}/journalRecord/list/memo`

    axios
      .post(url)
      .then((res) => {
        memoData.value = res.data.data
      })
      .catch((res) => {
        console.log(res)
      })
  }
  onMounted(() => {
    getMemoData()
  })

  // 新增資料
  function updateList(item) {
    const PaginationStore = usePaginationStore()
    const { initPage } = PaginationStore
    const url = `${BASE_API_URL}/journalRecord/add`

    const data = {
      txTime: `${item.txTime.date} ${item.txTime.timePoint}`,
      debit: item.debit.code,
      credit: item.credit.code,
      amount: item.amount,
      memo: item.memo,
      remark: item.remark,
    }
    axios
      .post(url, data, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;' },
      })
      .then((res) => {
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
    const url = `${BASE_API_URL}/journalRecord/modify`

    const params = new URLSearchParams()
    params.append('txTime', `${item.txTime.date} ${item.txTime.timePoint}`)
    params.append('debit', item.debit.code)
    params.append('credit', item.credit.code)
    params.append('amount', item.amount)
    params.append('memo', item.memo)
    params.append('remark', item.remark)
    params.append('key', item.key)

    axios
      .post(url, params, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;' },
      })
      .then((res) => {
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
    const url = `${BASE_API_URL}/journalRecord/remove`
    const params = new URLSearchParams()
    params.append('key', key.value)
    axios
      .post(url, params)
      .then((res) => {
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

  return {
    debitList,
    optionSort,
    findData,
    updateList,
    editList,
    delList,
    getMemoData,
    memoData,
  }
})
