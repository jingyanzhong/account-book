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
            <h3>年收入</h3>
            <div class="pie_chart1" id="pie_chart1"></div>
        </div>
        <div class="expenses">
            <h3>年支出</h3>
            <div class="pie_chart2" id="pie_chart2"></div>
        </div>
    </div>
</div>
</template>
<script setup>
import { onMounted } from 'vue'
onMounted(() => {
    getData();
})
const data = []
function getData(){
    let url = ''
    // axios.get(url)
    // .then((res) => {
    //     console.log(res);   
    // })
    // .catch((err) => {
    //     console.log(err); 
    // })
    console.log('取得資料');
}
 // Load the Visualization API and the corechart package.
 google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(resize);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function resize() {
    let width = window.innerWidth;
    setWidth(width);
}
function setWidth(w) {
    let width = null;
    let height = null;
    if(w < 768) {
        width = w - 40 ;
        height = 200;
    }else if (w > 767 && w < 821) {
        width = (w - 72) / 2 ;
        height = 250;
    } else {
        if(w - 272 > 1480) {
            width = (1480 - 32) / 2;
            height = 400;
        } else {
            width = (w - 272) / 2;
            height = 400;
        }
    }

    drawPieChart1(width, height);
    drawPieChart2(width, height);
}
function drawPieChart1(w, h) {
    
  // Create the data table.
  let data = new google.visualization.DataTable();
  data.addColumn('string', 'Topping');
  data.addColumn('number', 'Slices');
  data.addRows([
    ['Mushrooms', 3],
    ['Onions', 1],
    ['Olives', 1],
    ['Zucchini', 1],
    ['Pepperoni', 2]
  ]);

  // Set chart options
  let options = {width: w, height: h, 'legend': {
    textStyle: {color: '#000', fontSize: 16}},  colors:['#72BFFF','#EC9D4E', '#1E6FA9', '#AD6ED2', '#F0CC00'],chartArea: {width: '100%',height: '100%',left: 0,top: 0}};

  // Instantiate and draw our chart, passing in some options.
  let chart = new google.visualization.PieChart(document.getElementById('pie_chart1'));
  chart.draw(data, options);
}

function drawPieChart2(w, h) {
// Create the data table.
let data = new google.visualization.DataTable();
data.addColumn('string', 'Topping');
data.addColumn('number', 'Slices');
data.addRows([
  ['Mushrooms', 3],
  ['Onions', 1],
  ['Olives', 1],
  ['Zucchini', 1],
  ['Pepperoni', 2]
]);

// Set chart options
let options = {width: w, height: h, 'legend': {
    textStyle: {color: '#000', fontSize: 16}},  colors:['#72BFFF','#EC9D4E', '#1E6FA9', '#AD6ED2', '#F0CC00'],chartArea: {width: '100%',height: '100%',left: 0,top: 0}};

// Instantiate and draw our chart, passing in some options.
let chart = new google.visualization.PieChart(document.getElementById('pie_chart2'));
chart.draw(data, options);
}

window.addEventListener('resize',resize)
</script>