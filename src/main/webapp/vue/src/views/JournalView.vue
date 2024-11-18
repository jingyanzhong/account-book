<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import Model from '@/components/modelComponent.vue'
import DelModel from '@/components/DeleteModel.vue'
import { useModelShowStore } from '@/stores/modelShow'
import { storeToRefs } from 'pinia'
const ModelShowStore = useModelShowStore()
const { isShowModel, isShowMask, isShowDelModel } = ModelShowStore
const { showMask, isNew } = storeToRefs(ModelShowStore)

const searchData = ref({
  debit: '',
  credit: '',
  memo: '',
  date: '',
})
const debitList = ref([])
function getDebitData() {
  const url = 'http://localhost:8080/account-book/api/subject/list'
  axios.get(url).then((res) => {
    debitList.value = res.data
    console.log(debitList.value)
  })
}

const data = ref({})
function getData() {
  let url = ''
  // axios.get(url).then((res) => {
  //   console.log(res.data)
  // })
}

onMounted(() => {
  getData()
  getDebitData()
})

const tempList = ref({})
function openModel(bool) {
  isNew.value = bool
  if (bool) {
    tempList.value = {}
  } else {
    tempList.value = {
      id: 1001,
      account: 'jingyan',
      amount: 10,
      credit: '現金',
      date: '2024-11-14',
      debit: '早餐',
      item: '123',
      place: '123',
    }
  }
  isShowMask(true)
  isShowModel(true)
}

function openDelModel() {
  isShowMask(true)
  isShowDelModel(true)
}

function updateList(item) {
  let url = ''
  let itemArr = item
  console.log('add', itemArr)
  // axios.post(url, itemArr)
  // .then((res) => {
  //     console.log(res);
  // })
  // .catch((err) => {
  //     console.log(err);
  // })
}

function editList(item) {
  let itemArr = item
  console.log('e', itemArr)
}

function delList() {
  console.log('del')
}

function search(item) {
  console.log(item)

  console.log(searchData.value.debit)
}
</script>
<template>
  <div class="bg-gray">
    <div class="container">
      <div class="title">
        <h2>
          JOURNAL / CASH
          <span> | 日記帳 / 現金帳</span>
        </h2>
        <button type="button" @click="openModel(true)">Add</button>
      </div>
      <div class="cash_content">
        <table class="cash_list">
          <thead>
            <tr>
              <th>ID</th>
              <th>Date</th>
              <th>Debit</th>
              <th>Credit</th>
              <th>Amount</th>
              <th>MEMO</th>
              <th>
                Time<br />
                Modify
              </th>
              <th>operate</th>
            </tr>
            <tr class="search">
              <td>
                <div class="reset-btn">
                  <button type="button">
                    <span class="material-symbols-outlined">restart_alt</span>
                  </button>
                </div>
              </td>
              <td>
                <input type="date" />
              </td>
              <td>
                <select @change="search('debit')" v-model="searchData.debit">
                  <option value="">請選擇...</option>
                  <option v-for="item in debitList" :value="item.code" :key="item.code">
                    {{ item.name }}
                  </option>
                </select>
              </td>
              <td>
                <select name="" id="">
                  <option value="">請選擇...</option>
                </select>
              </td>
              <td></td>
              <td>
                <input type="text" placeholder="ex:飯..." />
              </td>
              <td class="pad-width-m"></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1001</td>
              <td>2024-11-07</td>
              <td>早餐</td>
              <td>現金</td>
              <td>30</td>
              <td>7-11茶葉蛋</td>
              <td>
                2024-11-07 <br />
                17:57
              </td>
              <td>
                <div class="btn-group">
                  <button type="button" class="edit" @click="openModel(false)">
                    <span class="material-symbols-outlined">edit</span>
                  </button>
                  <button type="button" class="del" @click="openDelModel">
                    <span class="material-symbols-outlined">delete</span>
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <nav class="page navigation">
        <ul class="pagination">
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
  <div class="mask" :class="{ active: showMask }"></div>
  <Model
    :list="tempList"
    :debitData="debitList"
    @update-list="updateList"
    @edit-list="editList"
  ></Model>
  <DelModel @del-list="delList"></DelModel>
</template>
