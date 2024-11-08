<script setup>
import { useModelShowStore } from '@/stores/modelShow';
import { storeToRefs } from 'pinia';
const ModelShowStore = useModelShowStore();
const { isShowModel, isShowMask } = ModelShowStore;
const { showModel } = storeToRefs(ModelShowStore)

</script>
<template>
    <div class="model" :class="{'active' : showModel}">
    <div class="header">
        <h3>JOURNAL / CASH
            <span>新增</span></h3>
    </div>
    <div class="body">
        <form action="">
            <div class="input_group">
                <div class="input_item">
                    <label>
                        Date <br>
                        <input type="date">
                    </label>
                </div>
                <div class="input_item">
                    <label class="amount_input">
                        Amount <br>
                        <input type="number" placeholder="請輸入金額..." >
                    </label>
                </div>
            </div>
            <div class="input_group">
                <div class="input_item">
                    <label>
                        Debit <br>
                        <select name="" id="">
                            <option value="">請選擇...</option>
                        </select>
                    </label>
                </div>
                <div class="input_item">
                    <label>
                        Credit <br>
                        <select name="" id="">
                            <option value="">請選擇...</option>
                        </select>
                    </label>
                </div>
            </div>
            <div class="input_group">
                <div class="input_item">
                    <label>
                        Items <br>
                        <input type="text" placeholder="請輸入項目...">
                    </label>
                </div>
                <div class="input_item">
                    <label>
                        Place <br>
                        <input type="text" placeholder="請輸入地點...">
                    </label>
                </div>
            </div>
            <div class="input_group">
                <div class="input_item w-100">
                    <label>
                        Account <br>
                        <select name="" id="">
                            <option value="">請選擇帳號...</option>
                        </select>
                    </label>
                </div>
            </div>
        </form>
    </div>
    <div class="btn-group">
        <button type="button" class="cancel" @click="isShowMask(false); isShowModel(false)">取消</button>
        <button type="button" class="add" @click="isShowMask(false); isShowModel(false)">新增</button>
    </div>
</div>
</template>