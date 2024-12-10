<template>
  <div class="container">
    <div class="title">
      <h2>
        REPORT
        <span> | 財務報表</span>
      </h2>
    </div>
    <div class="report_content">
      <div class="income">
        <h3>
          年收入 <br />
          <span>{{ incomeTotal }}元</span>
        </h3>
        <div class="pie_chart1" id="pie_chart1"></div>
      </div>
      <div class="expenses">
        <h3>
          年支出 <br />
          <span>{{ expensesTotal }}元</span>
        </h3>
        <div class="pie_chart2" id="pie_chart2"></div>
      </div>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios'
import { ref, onMounted } from 'vue'

// 取得一整年的日記帳資料
onMounted(() => {
  getData()
})
const data = ref()
function getData() {
  let url = '//localhost:8080/account-book/api/journalRecord/findAll'
  axios
    .post(url)
    .then((res) => {
      data.value = res.data.data
      console.log(data.value)
      filterData()
    })
    .catch((err) => {
      console.log(err)
    })
}

// 篩選出收入 / 支出data
const incomeData = ref()
const expensesData = ref()
function filterData() {
  incomeData.value = data.value.filter((item, index) => {
    return item.credit.code === '1000'
  })
  console.log(incomeData.value)
  expensesData.value = data.value.filter((item, index) => {
    return item.credit.code !== '1000'
  })
  console.log(expensesData.value)
  chartsData()
}

// 組出收入的chart data 和計算出總收入
const incomeTotal = ref(0)
function incomeChartDataFn() {
  const allDebit = ref([])
  const filterdata = ref([])
  const data = ref([])
  incomeData.value.map((item, index) => {
    allDebit.value.push(item.debit.name)
    incomeTotal.value += parseInt(item.amount)
  })

  allDebit.value.filter((item, index, arr) => {
    if (arr.indexOf(item) === index) {
      filterdata.value.push({ name: item, amount: 0 })
    }
  })

  incomeData.value.map((item, index) => {
    for (let i = 0; i < filterdata.value.length; i++) {
      item.debit.name === filterdata.value[i].name
        ? (filterdata.value[i].amount += parseInt(item.amount))
        : ''
    }
  })

  filterdata.value.map((item, index) => {
    data.value.push([item.name, item.amount])
  })
  return data.value
}

// 組出支出的 chart data和計算出總支出
const expensesTotal = ref(0)
function expensesChartDataFn() {
  const allDebit = ref([])
  const filterdata = ref([])
  const data = ref([])
  expensesData.value.map((item, index) => {
    allDebit.value.push(item.debit.name)
    expensesTotal.value += parseInt(item.amount)
  })
  allDebit.value.filter((item, index, arr) => {
    if (arr.indexOf(item) === index) {
      filterdata.value.push({ name: item, amount: 0 })
    }
  })
  expensesData.value.map((item, index) => {
    for (let i = 0; i < filterdata.value.length; i++) {
      item.debit.name === filterdata.value[i].name
        ? (filterdata.value[i].amount += parseInt(item.amount))
        : ''
    }
  })
  filterdata.value.map((item, index) => {
    data.value.push([item.name, item.amount])
  })
  return data.value
}

const incomeChartData = ref([])
const expensesChartData = ref([])
function chartsData() {
  incomeChartData.value = incomeChartDataFn()
  expensesChartData.value = expensesChartDataFn()
  initCharts()
}

function initCharts() {
  // Load the Visualization API and the corechart package.
  google.charts.load('current', { packages: ['corechart'] })

  // Set a callback to run when the Google Visualization API is loaded.
  google.charts.setOnLoadCallback(resize)
}

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
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
  // Create the data table.
  let data = new google.visualization.DataTable()
  data.addColumn('string', 'Topping')
  data.addColumn('number', 'Slices')
  data.addRows(incomeChartData.value)

  // Set chart options
  let options = {
    width: w,
    height: h,
    legend: {
      textStyle: { color: '#000', fontSize: 16 },
    },
    colors: ['#72BFFF', '#EC9D4E', '#1E6FA9', '#AD6ED2', '#F0CC00'],
    chartArea: { width: '100%', height: '100%', left: 0, top: 0 },
  }

  // Instantiate and draw our chart, passing in some options.
  let chart = new google.visualization.PieChart(document.getElementById('pie_chart1'))
  chart.draw(data, options)
}

function drawPieChart2(w, h) {
  // Create the data table.
  let data = new google.visualization.DataTable()
  data.addColumn('string', 'Topping')
  data.addColumn('number', 'Slices')
  data.addRows(expensesChartData.value)

  // Set chart options
  let options = {
    width: w,
    height: h,
    legend: {
      textStyle: { color: '#000', fontSize: 16 },
    },
    colors: ['#72BFFF', '#EC9D4E', '#1E6FA9', '#AD6ED2', '#F0CC00'],
    chartArea: { width: '100%', height: '100%', left: 0, top: 0 },
  }

  // Instantiate and draw our chart, passing in some options.
  let chart = new google.visualization.PieChart(document.getElementById('pie_chart2'))
  chart.draw(data, options)
}

window.addEventListener('resize', resize)
</script>
