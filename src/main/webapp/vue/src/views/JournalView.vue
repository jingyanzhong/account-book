<script setup>
import { ref } from 'vue'
import Model from '@/components/modelComponent.vue'
import DelModel from '@/components/DeleteModel.vue'
import { useModelShowStore } from '@/stores/modelShow';
import { storeToRefs } from 'pinia';
const ModelShowStore = useModelShowStore();
const { isShowModel, isShowMask, isShowDelModel } = ModelShowStore;
const { showMask } = storeToRefs(ModelShowStore)

const isNew = ref(true)
function openModel(bool) {
    if(bool){
        isNew.value = bool
    }else {
        isNew.value = bool
    }
    isShowMask(true); 
    isShowModel(true)
}

function openDelModel() {
    isShowMask(true); 
    isShowDelModel(true);
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
                        <th>Date</th>
                        <th>Debit</th>
                        <th>Credit</th>
                        <th>Reduce /<br>Increase</th>
                        <th>Amount</th>
                        <th>Info<br>(item / place)</th>
                        <th>Account</th>
                        <th>Time<br> Modify</th>
                        <th>operate</th>
                    </tr>
                    <tr class="search">
                        <td>
                            <input type="date" name="" id="">
                            <br>
                            <div class="reset-btn">
                                <button type="button">
                                    <span class="material-symbols-outlined">restart_alt</span>
                                </button>
                            </div>
                        </td>
                        <td>
                            <select name="" id="">
                                <option value="">請選擇...</option>
                            </select>
                        </td>
                        <td>
                            <select name="" id="">
                                <option value="">請選擇...</option>
                            </select>
                        </td>
                        <td></td>
                        <td></td>
                        <td class="pad-width-m">
                            <input type="text" placeholder="ex:飯...">
                            <br>
                            <input type="text" placeholder="請輸入地點...">
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
            <tbody>
                <tr>
                    <td>2024-11-07</td>
                    <td>早餐</td>
                    <td>現金</td>
                    <td>-</td>
                    <td>30</td>
                    <td>茶葉蛋 <br> 7-11</td>
                    <td>Jingyan</td>
                    <td>2024-11-07 <br> 17:57</td>
                    <td>
                        <button class="edit"@click="openModel(false)">
                            <span class="material-symbols-outlined">edit</span>
                        </button>
                        <button class="del" @click="openDelModel">
                            <span class="material-symbols-outlined">delete</span>
                        </button>
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
<div class="mask" :class="{'active' : showMask}"></div>
<Model />
<DelModel />
</template>
