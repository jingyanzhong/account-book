import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/project',
      name: 'project',
      component: () => import('../views/ProjectView.vue')
    },
    {
      path: '/report',
      name: 'report',
      component: () => import('../views/ReportView.vue')
    },
    {
      path: '/journal_cash',
      name: 'journal_cash',
      component: () => import('../views/JournalView.vue')
    },
    {
      path: '/subjects',
      name: 'subjects',
      component: () => import('../views/SubjectsView.vue')
    },
    {
      path: '/account',
      name: 'account',
      component: () => import('../views/AccountView.vue')
    },
    {
      path: '/member',
      name: 'member',
      component: () => import('../views/MemberView.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue')
    }
  ]
})

export default router
