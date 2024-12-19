import '@/assets/css/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import Vue3Toastify, { toast } from 'vue3-toastify'
import 'vue3-toastify/dist/index.css'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import { Field, Form, ErrorMessage, defineRule, configure } from 'vee-validate'
import { all as rules } from '@vee-validate/rules'
import { localize, setLocale } from '@vee-validate/i18n'
import zhTW from '@vee-validate/i18n/dist/locale/zh_TW.json'

import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'

Object.keys(rules).forEach((rule) => {
  defineRule(rule, rules[rule])
})

configure({
  generateMessage: localize({ zh_TW: zhTW }),
  validateOnInput: true,
})
setLocale('zh_TW')

const app = createApp(App)
app.use(VueAxios, axios)
app.use(createPinia())
app.use(router)
app.use(Vue3Toastify, {
  autoClose: 3000, // 自動關閉時間
  position: toast.POSITION.TOP_CENTER, // 提示窗位置
})
app.use(ElementPlus)
app.component('VField', Field)
app.component('VForm', Form)
app.component('ErrorMessage', ErrorMessage)

app.mount('#app')
