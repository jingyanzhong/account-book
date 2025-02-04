<script setup>
import { reactive, watch } from 'vue'
import { useModelShowStore } from '@/stores/modelShow'
import { storeToRefs } from 'pinia'
const ModelShowStore = useModelShowStore()
const { isShowModel, isShowMask } = ModelShowStore
const { showModel, isNew } = storeToRefs(ModelShowStore)
const emit = defineEmits(['update-list', 'edit-list'])
const props = defineProps({
  list: {
    type: Object,
    default: () => ({}),
  },
  debitData: {
    type: Object,
    default: () => ({}),
  },
  memoData: {
    type: Object,
    default: () => ({}),
  },
})
watch(
  () => props.list,
  (item) => {
    Object.assign(cashItem, JSON.parse(JSON.stringify(item)))
  },
  { deep: true },
)
watch(
  () => props.debitData,
  (item) => {
    Object.assign(debitItem, item)
  },
)
watch(
  () => props.memoData,
  (item) => {
    Object.assign(memoItem, item)
  },
)
const cashItem = reactive({
  key: '',
  amount: '',
  credit: { code: '', name: '' },
  debit: { code: '', name: '' },
  memo: '',
  remark: '',
  txTime: { date: '', timePoint: '' },
})
const memoItem = reactive({})
const debitItem = reactive({})

// 判斷如果debit.code等於以下代號,則設定為disabled
const disabledCodes = ['1000', '2000', '3000', '4000', '5000', '6000', '7000']

const isDisabled = (code) => {
  return disabledCodes.includes(code)
}

function updateItem(val, { resetForm }) {
  isShowMask(false)
  isShowModel(false)
  if (isNew.value) {
    emit('update-list', cashItem)
  } else {
    emit('edit-list', cashItem)
  }
  resetForm()
}

function cancel(resetForm) {
  isShowMask(false)
  isShowModel(false)
  resetForm()
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
            <label>
              Time <br />
              <v-field name="time" rules="required" v-model="cashItem.txTime.timePoint">
                <div class="time-picker">
                  <el-time-picker
                    v-model="cashItem.txTime.timePoint"
                    format="HH:mm:ss"
                    value-format="HH:mm:ss"
                    placeholder="請選擇時間"
                    :class="{ 'is-invalid': meta.touched && meta.invalid }"
                    :disabled="!isNew"
                  />
                </div>
              </v-field>
              <error-message name="time" class="invalid-feedback"></error-message>
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
                <option
                  v-for="item in debitItem"
                  :key="item.code"
                  :value="item.code"
                  :disabled="isDisabled(item.code)"
                >
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
                <option
                  v-for="item in debitItem"
                  :value="item.code"
                  :key="item.code"
                  :disabled="isDisabled(item.code)"
                >
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
                as="select"
                name="MEMO"
                rules="required"
                v-model="cashItem.memo"
                :class="{ 'is-invalid': errors['MEMO'] }"
              >
                <option value="">請選擇...</option>
                <option v-for="item in memoItem" :value="item.name" :key="item.code">
                  {{ item.name }}
                </option>
              </v-field>
              <error-message name="MEMO" class="invalid-feedback"></error-message>
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
          <div class="input_item w-100">
            <label for="">
              reMark
              <v-field
                type="text"
                name="remark"
                rules="required|min:1"
                placeholder="請輸入備註..."
                v-model="cashItem.remark"
                :class="{ 'is-invalid': errors.id }"
              ></v-field>
              <error-message name="remark" class="invalid-feedback"></error-message>
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
