<script setup>
import { ref, reactive } from 'vue'
import Model from '@/components/modelComponent.vue'
import DelModel from '@/components/DeleteModel.vue'
import pagination from '@/components/paginationComponent.vue'
import { storeToRefs } from 'pinia'
import { useModelShowStore } from '@/stores/modelShow'
const ModelShowStore = useModelShowStore()
const { isShowModel, isShowMask, isShowDelModel, openModel, openDelModel } = ModelShowStore
const { showMask, isNew, tempList, delKey } = storeToRefs(ModelShowStore)

import { useJournalStore } from '@/stores/journal'
const JournalStore = useJournalStore()
const { journalData, currData, allDate } = storeToRefs(JournalStore)

import { useApiServeStore } from '@/stores/apiServe'
const apiServeStore = useApiServeStore()
const { updateList, editList, delList } = apiServeStore
const { debitList, optionSort, memoData } = storeToRefs(apiServeStore)

import { usePaginationStore } from '@/stores/pagination'
const PaginationStore = usePaginationStore()
const { initPage } = PaginationStore

import { usethousandthsFormatStore } from '@/stores/thousandthsFormat'
const thousandthsFormatStore = usethousandthsFormatStore()
const { thousandthsFormat } = thousandthsFormatStore
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
              <!-- <th>ID</th> -->
              <th width="12%">Date</th>
              <th width="10%">Debit</th>
              <th width="10%">Credit</th>
              <th width="13%">Amount</th>
              <th width="12%">MEMO</th>
              <th width="15%">reMark</th>
              <th width="15%">
                Time<br />
                Modify
              </th>
              <th width="23%">operate</th>
            </tr>
            <tr class="search">
              <td>
                <select name="date" id="date" @change="initPage($event)" v-model="optionSort">
                  <option
                    v-for="(date, index) in allDate"
                    :value="index"
                    :key="index"
                    :data-year="date.year"
                    :data-month="date.month"
                  >
                    {{ date.year }}年{{ date.month }}月
                  </option>
                </select>
              </td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td class="pad-width-m"></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in currData" :key="item.key">
              <td>{{ item.txTime.date }}</td>
              <td>{{ item.debit.name }}</td>
              <td>{{ item.credit.name }}</td>
              <td>{{ thousandthsFormat(item.amount) }}</td>
              <td>{{ item.memo }}</td>
              <td>{{ item.remark }}</td>
              <td>{{ item.txTime.timePoint }}</td>
              <td>
                <div class="btn-group">
                  <button type="button" class="edit" @click="openModel(false, item)">
                    <span class="material-symbols-outlined">edit</span>
                  </button>
                  <button type="button" class="del" @click="openDelModel(item.key)">
                    <span class="material-symbols-outlined">delete</span>
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <pagination></pagination>
    </div>
  </div>
  <div class="mask" :class="{ active: showMask }"></div>
  <Model
    :list="tempList"
    :debitData="debitList"
    :memoData="memoData"
    @update-list="updateList"
    @edit-list="editList"
  ></Model>
  <DelModel :del-key="delKey" @del-list="delList"></DelModel>
</template>
