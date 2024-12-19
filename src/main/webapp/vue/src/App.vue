<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { useMenuShowStore } from '@/stores/menuShow'
import { useIsActiveStore } from '@/stores/isActive'
import { storeToRefs } from 'pinia'
import { onMounted } from 'vue'
const menuShowStore = useMenuShowStore()
const { menuToggle, menuClose, maskToggle } = menuShowStore
const { showMenu, showMask } = storeToRefs(menuShowStore)

const isActiveStore = useIsActiveStore()
const { navActive, getPath } = isActiveStore
const { isActive } = storeToRefs(isActiveStore)

// 頁面重新整理時，判斷當前路由，為menu加上active
onMounted(() => {
  setTimeout(() => {
    getPath()
  }, 1000)
})
</script>

<template>
  <header>
    <h1>
      <img src="./assets/img/tree-icon-green.png" alt="logo" />
      Account Book
    </h1>
    <button type="button" id="menuToggle" @click="menuToggle(), maskToggle()"></button>
  </header>
  <div id="menu" :class="{ active: showMenu }">
    <div class="menu-content">
      <ul class="menu-list">
        <li class="menu-item">
          <RouterLink
            to="/"
            class="menu-link"
            @click="navActive('home'), menuClose()"
            :class="{ active: isActive === 'home' }"
            >HOME</RouterLink
          >
        </li>
        <li class="menu-item">
          <RouterLink
            to="/project"
            class="menu-link"
            @click="navActive('project'), menuClose()"
            :class="{ active: isActive === 'project' }"
            >PROJECT</RouterLink
          >
        </li>
        <li class="menu-item">
          <RouterLink
            to="/report"
            class="menu-link"
            @click="navActive('report'), menuClose()"
            :class="{ active: isActive === 'report' }"
            >REPORT</RouterLink
          >
        </li>
        <li class="menu-item">
          <RouterLink
            to="/journal_cash"
            class="menu-link"
            @click="navActive('journal_cash'), menuClose()"
            :class="{ active: isActive === 'journal_cash' }"
            >JOURNAL / CASH</RouterLink
          >
        </li>
        <li class="menu-item">
          <RouterLink
            to="/subjects"
            class="menu-link"
            @click="navActive('subjects'), menuClose()"
            :class="{ active: isActive === 'subjects' }"
            >SUBJECTS</RouterLink
          >
        </li>
        <li class="menu-item">
          <RouterLink
            to="/account"
            class="menu-link"
            @click="navActive('account'), menuClose()"
            :class="{ active: isActive === 'account' }"
            >ACCOUNTS</RouterLink
          >
        </li>
        <li class="menu-item">
          <RouterLink
            to="/member"
            class="menu-link"
            @click="navActive('member'), menuClose()"
            :class="{ active: isActive === 'member' }"
            >MEMBERS</RouterLink
          >
        </li>
        <li class="menu-item">
          <RouterLink
            to="/profile"
            class="menu-link"
            @click="navActive('profile'), menuClose()"
            :class="{ active: isActive === 'profile' }"
            >MY PROFILE</RouterLink
          >
        </li>
      </ul>
    </div>
  </div>
  <div class="content">
    <RouterView />
  </div>
  <div class="menu-mask" :class="{ active: showMask }"></div>
</template>

<style scoped></style>
