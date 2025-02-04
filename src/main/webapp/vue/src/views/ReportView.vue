<template>
  <div class="container">
    <div class="title">
      <h2>
        REPORT
        <span> | 財務報表</span>
      </h2>
    </div>
    <div class="dateSelect">
      <select name="" id="year" v-model="changeYear">
        <option value="2025">2025年</option>
        <option value="2024">2024年</option>
      </select>
      <select name="" id="month" v-model="changeMonth">
        <option value="1">01月</option>
        <option value="2">02月</option>
        <option value="3">03月</option>
        <option value="4">04月</option>
        <option value="5">05月</option>
        <option value="6">06月</option>
        <option value="7">07月</option>
        <option value="8">08月</option>
        <option value="9">09月</option>
        <option value="10">10月</option>
        <option value="11">11月</option>
        <option value="12">12月</option>
      </select>
      <button type="button" @click="getData(changeYear, changeMonth)">查詢</button>
    </div>
    <div class="report_content">
      <div class="income">
        <h3>
          月收入
          <span>{{ thousandthsFormat(sumCredit) }}元</span>
        </h3>
        <div class="pie_chart1" id="pie_chart1"></div>
      </div>
      <div class="expenses">
        <h3>
          月支出
          <span>{{ thousandthsFormat(sumDebit) }}元</span>
        </h3>
        <div class="pie_chart2" id="pie_chart2"></div>
      </div>
    </div>
    <div class="table" v-if="data !== undefined">
      <table>
        <thead>
          <tr>
            <th colspan="12">{{ data.month }}月份收支總覽</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th colspan="12">收入</th>
          </tr>
          <tr>
            <td v-for="item in data.credits">{{ item.subject.name }}</td>
          </tr>
          <tr>
            <td v-for="item in data.credits">{{ thousandthsFormat(item.totalAmount) }}</td>
          </tr>
          <tr>
            <th colspan="12">支出</th>
          </tr>
          <tr>
            <td v-for="item in data.debits">{{ item.subject.name }}</td>
          </tr>
          <tr>
            <td v-for="item in data.debits">{{ thousandthsFormat(item.totalAmount) }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios'
import { ref, onMounted } from 'vue'
import { usethousandthsFormatStore } from '@/stores/thousandthsFormat'
const thousandthsFormatStore = usethousandthsFormatStore()
const { thousandthsFormat } = thousandthsFormatStore
// 取得一整年的日記帳資料
onMounted(() => {
  getData()
})
const data = ref()
const sumCredit = ref(0)
const sumDebit = ref(0)
const date = new Date()
const year = date.getFullYear()
const month = date.getMonth() + 1
const changeYear = ref(year)
const changeMonth = ref(month)
const BASE_API_URL = '//localhost:8080/account-book/api'
function getData(y = year, m = month) {
  const url = `${BASE_API_URL}/journalReport/monthly`
  const year = y
  const month = m
  const urlData = {
    year: year,
    month: month,
  }
  axios
    .post(url, urlData, {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;' },
    })
    .then(async (res) => {
      data.value = res.data.data
      sumCredit.value = res.data.data.sumCredit
      sumDebit.value = res.data.data.sumDebit
      console.log(data.value)
      await getChartsData()
    })
    .catch((err) => {
      console.log(err)
    })
}

const creditsData = ref([])
const debitsData = ref([])
function getChartsData() {
  creditsData.value = []
  debitsData.value = []
  if (data.value.credits !== undefined) {
    data.value.credits.map((item) => {
      creditsData.value.push([item.subject.name, item.totalAmount])
    })
  }
  if (data.value.debits !== undefined) {
    data.value.debits.map((item) => {
      debitsData.value.push([item.subject.name, item.totalAmount])
    })
  }

  initCharts()
}

function initCharts() {
  google.charts.load('current', { packages: ['corechart'] })
  google.charts.setOnLoadCallback(resize)
}

function resize() {
  let width = window.innerWidth
  setWidth(width)
}
function setWidth(w) {
  let width = null
  let height = null
  if (w < 768) {
    width = w - 40
    height = 200
  } else if (w > 767 && w < 821) {
    width = (w - 72) / 2
    height = 250
  } else {
    if (w - 272 > 1480) {
      width = (1480 - 32) / 2
      height = 400
    } else {
      width = (w - 272) / 2
      height = 400
    }
  }

  drawPieChart1(width, height)
  drawPieChart2(width, height)
}
function drawPieChart1(w, h) {
  let data = new google.visualization.DataTable()
  data.addColumn('string', 'Topping')
  data.addColumn('number', 'Slices')
  data.addRows(creditsData.value)

  let options = {
    width: w,
    height: h,
    legend: {
      textStyle: { color: '#000', fontSize: 16 },
    },
    colors: ['#72BFFF', '#EC9D4E', '#1E6FA9', '#AD6ED2', '#F0CC00'],
    chartArea: { width: '100%', height: '100%', left: 0, top: 0 },
  }

  let chart = new google.visualization.PieChart(document.getElementById('pie_chart1'))
  chart.draw(data, options)
}

function drawPieChart2(w, h) {
  let data = new google.visualization.DataTable()
  data.addColumn('string', 'Topping')
  data.addColumn('number', 'Slices')
  data.addRows(debitsData.value)

  let options = {
    width: w,
    height: h,
    legend: {
      textStyle: { color: '#000', fontSize: 16 },
    },
    colors: ['#72BFFF', '#EC9D4E', '#1E6FA9', '#AD6ED2', '#F0CC00'],
    chartArea: { width: '100%', height: '100%', left: 0, top: 0 },
  }

  let chart = new google.visualization.PieChart(document.getElementById('pie_chart2'))
  chart.draw(data, options)
}

window.addEventListener('resize', resize)
</script>
