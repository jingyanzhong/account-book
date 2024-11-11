<script setup>
import { ref, watch } from 'vue'
import { useModelShowStore } from '@/stores/modelShow';
import { storeToRefs } from 'pinia';
const ModelShowStore = useModelShowStore();
const { isShowModel, isShowMask } = ModelShowStore;
const { showModel, isNew } = storeToRefs(ModelShowStore)
const emit = defineEmits(['update-list', 'edit-list'])
const props = defineProps({
    list: {
        type: Object,
        default: {}
    }
})
watch(() => props.list,
        (item) => {
            cashItem.value = item
    }
)
const cashItem = ref({})

function editItem(){
    isShowMask(false);
    isShowModel(false);
    emit('edit-list', cashItem);
}

function addItem() {
    isShowMask(false);
    isShowModel(false);
    emit('update-list', cashItem);
}
</script>
<template>
    <div class="model" :class="{'active' : showModel}">
    <div class="header">
        <h3>JOURNAL / CASH
            <span v-if="isNew">新增</span>
            <span v-else>編輯</span>
        </h3>
    </div>
    <div class="body">
        <form action="">
            <div class="input_group">
                <div class="input_item">
                    <label>
                        Date <br>
                        <input type="date"  v-model="cashItem.date">
                    </label>
                </div>
                <div class="input_item">
                    <label class="amount_input">
                        Amount <br>
                        <input type="number" placeholder="請輸入金額..." v-model="cashItem.amount">
                    </label>
                </div>
            </div>
            <div class="input_group">
                <div class="input_item">
                    <label>
                        Debit <br>
                        <select name="" id="" v-model="cashItem.debit">
                            <option value="">請選擇...</option>
                            <option value="早餐">早餐</option>
                        </select>
                    </label>
                </div>
                <div class="input_item">
                    <label>
                        Credit <br>
                        <select name="" id="" v-model="cashItem.credit">
                            <option value="">請選擇...</option>
                            <option value="現金">現金</option>
                            <option value="信用卡">信用卡</option>
                        </select>
                    </label>
                </div>
            </div>
            <div class="input_group">
                <div class="input_item">
                    <label>
                        Items <br>
                        <input type="text" placeholder="請輸入項目..." v-model="cashItem.item">
                    </label>
                </div>
                <div class="input_item">
                    <label>
                        Place <br>
                        <input type="text" placeholder="請輸入地點..." v-model="cashItem.place">
                    </label>
                </div>
            </div>
            <div class="input_group">
                <div class="input_item w-100">
                    <label>
                        Account <br>
                        <select name="" id="" v-model="cashItem.account">
                            <option value="">請選擇帳號...</option>
                            <option value="jingyan">Jingyan</option>
                        </select>
                    </label>
                </div>
            </div>
        </form>
    </div>
    <div class="btn-group">
        <button type="button" class="cancel" @click="isShowMask(false); isShowModel(false)">取消</button>
        <button v-if="isNew" type="button" class="add" @click="addItem()">新增</button>
        <button v-else type="button" class="add" @click="editItem()">編輯</button>
    </div>
</div>
</template>