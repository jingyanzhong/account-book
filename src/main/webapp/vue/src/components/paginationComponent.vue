<script setup>
import { storeToRefs } from 'pinia'
import { usePaginationStore } from '@/stores/pagination'
const PaginationStore = usePaginationStore()
// const {  } = PaginationStore
const { pageTotal, currPage } = storeToRefs(PaginationStore)

import { useJournalStore } from '@/stores/journal'
const JournalStore = useJournalStore()
const { dataRander } = JournalStore

function changePage(page) {
  currPage.value = page
  dataRander(page)
}

function showCurrPage() {
  currPage.value = currPage.value - 1
  dataRander(currPage.value)
}

function showNextPage() {
  currPage.value = currPage.value + 1
  dataRander(currPage.value)
}
</script>
<template>
  <nav class="page navigation">
    <ul class="pagination">
      <li class="page-item" :class="{ disabled: currPage === 1 }" @click.prevent="showCurrPage">
        <a class="page-link" href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li class="page-item">
        <a
          class="page-link"
          href="#"
          @click.prevent="changePage(1)"
          :class="{ active: currPage == 1 }"
          >1</a
        >
      </li>
      <li v-show="currPage >= 3">...</li>
      <li class="page-item" v-show="pageTotal >= 2 && currPage < 3">
        <a
          class="page-link"
          href="#"
          @click.prevent="changePage(2)"
          :class="{ active: currPage == 2 }"
          >2</a
        >
      </li>
      <li class="page-item" v-show="pageTotal >= 3 && currPage < 3">
        <a
          class="page-link"
          href="#"
          @click.prevent="changePage(3)"
          :class="{ active: currPage == 3 }"
          >3</a
        >
      </li>
      <li class="page-item" v-show="currPage == pageTotal">
        <a
          class="page-link"
          href="#"
          @click.prevent="changePage(currPage - 2)"
          :class="{ active: currPage == currPage - 2 }"
          >{{ currPage - 2 }}</a
        >
      </li>
      <li class="page-item" v-show="currPage > 2">
        <a
          class="page-link"
          href="#"
          @click.prevent="changePage(currPage - 1)"
          :class="{ active: currPage == currPage - 1 }"
          >{{ currPage - 1 }}</a
        >
      </li>
      <li class="page-item" v-show="currPage > 2">
        <a
          class="page-link"
          href="#"
          @click.prevent="changePage(currPage)"
          :class="{ active: currPage == currPage }"
          >{{ currPage }}</a
        >
      </li>
      <li class="page-item" v-show="currPage > 2 && currPage + 1 < pageTotal">
        <a
          class="page-link"
          href="#"
          @click.prevent="changePage(currPage + 1)"
          :class="{ active: currPage == currPage + 1 }"
          >{{ currPage + 1 }}</a
        >
      </li>
      <li v-show="pageTotal > 3 && currPage < pageTotal - 2">...</li>
      <li class="page-item" v-show="pageTotal > 3 && currPage < pageTotal">
        <a
          class="page-link"
          href="#"
          @click.prevent="changePage(pageTotal)"
          :class="{ active: currPage == pageTotal }"
          >{{ pageTotal }}</a
        >
      </li>
      <li
        class="page-item"
        @click.prevent="showNextPage"
        :class="{ disabled: currPage === pageTotal }"
      >
        <a class="page-link" href="#" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>
