<script setup>
import { ref, watch } from 'vue'
import { useModelShowStore } from '@/stores/modelShow'
import { storeToRefs } from 'pinia'
const ModelShowStore = useModelShowStore()
const { isShowModel, isShowMask } = ModelShowStore
const { showModel, isNew } = storeToRefs(ModelShowStore)
const emit = defineEmits(['update-list', 'edit-list'])
const props = defineProps({
  list: {
    type: Object,
    default: {},
  },
  debitData: {
    type: Object,
    default: {},
  },
})
watch(
  () => props.list,
  (item) => {
    cashItem.value = JSON.parse(JSON.stringify(item))
    initItem.value = JSON.parse(JSON.stringify(item))
  },
)
watch(
  () => props.debitData,
  (item) => {
    debitItem.value = item
  },
)
const initItem = ref()
const cashItem = ref({
  key: '',
  amount: '',
  credit: { code: '', name: '' },
  debit: { code: '', name: '' },
  memo: '',
  txTime: { date: '', timePoint: '' },
})

const debitItem = ref({})
function updateItem(val, { resetForm }) {
  isShowMask(false)
  isShowModel(false)

  if (isNew.value) {
    emit('update-list', cashItem.value)
  } else {
    emit('edit-list', cashItem.value)
  }
  resetForm()
}

function cancel(resetForm) {
  isShowMask(false)
  isShowModel(false)
  resetForm()
  cashItem.value = JSON.parse(JSON.stringify(initItem.value))
}
</script>
<template>
  <div class="model" :class="{ active: showModel }">
    <div class="header">
      <h3>
        JOURNAL / CASH
        <span v-if="isNew">新增</span>
        <span v-else>編輯</span>
      </h3>
    </div>
    <div class="body">
      <v-form v-slot="{ errors, meta, resetForm }" @submit="updateItem" ref="form">
        <div class="input_group">
          <div class="input_item">
            <label>
              Date <br />
              <v-field
                type="date"
                rules="required"
                name="日期"
                v-model="cashItem.txTime.date"
                :disabled="!isNew"
                :class="{ 'is-invalid': errors['日期'] }"
              ></v-field>
              <error-message name="日期" class="invalid-feedback"></error-message>
            </label>
          </div>
          <div class="input_item">
            <label class="amount_input">
              Amount <br />
              <v-field
                type="number"
                rules="required|min:1"
                name="金額"
                placeholder="請輸入金額..."
                v-model="cashItem.amount"
                :class="{ 'is-invalid': errors['金額'] }"
              ></v-field>
              <error-message name="金額" class="invalid-feedback"></error-message>
            </label>
          </div>
        </div>
        <div class="input_group">
          <div class="input_item">
            <label>
              Debit <br />
              <v-field
                as="select"
                name="debit"
                rules="required"
                v-model="cashItem.debit.code"
                :class="{ 'is-invalid': errors.debit }"
              >
                <option value="">請選擇...</option>
                <option v-for="item in debitItem" :value="item.code" :key="item.code">
                  {{ item.name }}
                </option>
              </v-field>
              <error-message name="debit" class="invalid-feedback"></error-message>
            </label>
          </div>
          <div class="input_item">
            <label>
              Credit <br />
              <v-field
                as="select"
                name="credit"
                rules="required"
                v-model="cashItem.credit.code"
                :class="{ 'is-invalid': errors.credit }"
              >
                <option value="">請選擇...</option>
                <option v-for="item in debitItem" :value="item.code" :key="item.code">
                  {{ item.name }}
                </option>
              </v-field>
              <error-message name="credit" class="invalid-feedback"></error-message>
            </label>
          </div>
        </div>
        <div class="input_group">
          <div class="input_item">
            <label>
              MEMO <br />
              <v-field
                type="text"
                name="MEMO"
                rules="required|min:1"
                placeholder="請輸入摘要..."
                v-model="cashItem.memo"
                :class="{ 'is-invalid': errors['MEMO'] }"
              ></v-field>
              <error-message name="MEMO" class="invalid-feedback"></error-message>
            </label>
          </div>
          <div class="input_item">
            <label for="">
              ID
              <v-field
                type="number"
                name="id"
                rules="min:1|max:4"
                placeholder="無須填寫，系統自動帶入"
                v-model="cashItem.key"
                :class="{ 'is-invalid': errors.id }"
                disabled
              ></v-field>
              <error-message name="id" class="invalid-feedback"></error-message>
            </label>
          </div>
        </div>
        <div class="btn-group">
          <button type="button" class="cancel" @click="cancel(resetForm)">取消</button>
          <button v-if="isNew" type="submit" class="add" :disabled="!meta.valid">新增</button>
          <button v-else type="submit" class="add" :disabled="!meta.valid">編輯</button>
        </div>
      </v-form>
    </div>
  </div>
</template>
