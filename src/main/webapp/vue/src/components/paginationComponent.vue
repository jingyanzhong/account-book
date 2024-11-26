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
      <li
        class="page-item"
        v-for="page in pageTotal"
        v-show="page > 1"
        @click.prevent="changePage(page)"
      >
        <a class="page-link" href="#" :class="{ active: currPage == page }">{{ page }}</a>
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
