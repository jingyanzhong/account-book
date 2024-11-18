<script setup>
import { ref } from 'vue'
const subject = ref('debit')
const debitItem = ref({})
const creditItem = ref({})

function addDebitItem(values, { resetForm }) {
  console.log('d', values)
  resetForm()
}

function addCreditItem(values, { resetForm }) {
  console.log('c', values)
  resetForm()
}
</script>

<template>
  <div class="bg-gray">
    <div class="container container-subject">
      <div class="title">
        <h2>
          SUBJECT
          <span> | 會計科目</span>
        </h2>
      </div>
      <div class="btn-group">
        <button
          class="subject-btn"
          :class="{ active: subject === 'debit' }"
          @click="subject = 'debit'"
        >
          Debit
        </button>
        <button
          class="subject-btn"
          :class="{ active: subject === 'credit' }"
          @click="subject = 'credit'"
        >
          Credit
        </button>
      </div>
      <div class="subject_content debit" v-if="subject === 'debit'">
        <div class="add_subject">
          <h3>新增Debit</h3>
          <v-form id="form" ref="dform" v-slot="{ errors, meta, resetForm }" @submit="addDebitItem">
            <div class="input_item">
              <label>
                代號 <br />
                <v-field
                  type="number"
                  name="代號"
                  rules="required|max:4|min:4"
                  placeholder="請輸入4位數字代號"
                  v-model="debitItem.CODE"
                  :class="{ 'is-invalid': errors['代號'] }"
                ></v-field>
                <error-message name="代號" class="invalid-feedback"></error-message>
              </label>
            </div>
            <div class="input_item">
              <label for="">
                名稱 <br />
                <v-field
                  type="text"
                  placeholder="請輸入名稱"
                  name="名稱"
                  rules="required"
                  :class="{ 'is-invalid': errors['名稱'] }"
                  v-model="debitItem.NAME"
                ></v-field>
                <error-message name="名稱" class="invalid-feedback"></error-message>
              </label>
            </div>
            <div class="btn-group">
              <button type="button" class="cancel" @click="resetForm">取消</button>
              <button type="subimt" class="add" :disabled="!meta.valid">新增</button>
            </div>
          </v-form>
        </div>
        <div class="subject_table">
          <table>
            <thead>
              <tr>
                <th width="15%">代號</th>
                <th width="30%">名稱</th>
                <th width="23%">建立時間</th>
                <th width="32%"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1001</td>
                <td>薪水</td>
                <td>2024-11-12 22:07:00</td>
                <td>
                  <div class="btn-group">
                    <button class="edit">
                      <span class="material-symbols-outlined">edit</span>
                    </button>
                    <button class="del">
                      <span class="material-symbols-outlined">delete</span>
                    </button>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="subject_content credit" v-else>
        <div class="add_subject">
          <h3>新增Credit</h3>
          <v-form
            id="form"
            ref="Cform"
            v-slot="{ errors, meta, resetForm }"
            @submit="addCreditItem"
          >
            <div class="input_item">
              <label>
                代號 <br />
                <v-field
                  type="number"
                  name="Credit代號"
                  rules="required|max:4|min:4"
                  placeholder="請輸入4位數字代號"
                  v-model="creditItem.CODE"
                  :class="{ 'is-invalid': errors['Credit代號'] }"
                ></v-field>
                <error-message name="Credit代號" class="invalid-feedback"></error-message>
              </label>
            </div>
            <div class="input_item">
              <label for="">
                名稱 <br />
                <v-field
                  type="text"
                  placeholder="請輸入名稱"
                  name="Credit名稱"
                  rules="required"
                  :class="{ 'is-invalid': errors['Credit名稱'] }"
                  v-model="creditItem.NAME"
                ></v-field>
                <error-message name="Credit名稱" class="invalid-feedback"></error-message>
              </label>
            </div>
            <div class="btn-group">
              <button type="button" class="cancel" @click="resetForm">取消</button>
              <button type="subimt" class="add" :disabled="!meta.valid">新增</button>
            </div>
          </v-form>
        </div>
        <div class="subject_table">
          <table>
            <thead>
              <tr>
                <th width="15%">代號</th>
                <th width="30%">名稱</th>
                <th width="23%">建立時間</th>
                <th width="32%"></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1001</td>
                <td>現金</td>
                <td>2024-11-12 22:07:00</td>
                <td>
                  <div class="btn-group">
                    <button class="edit">
                      <span class="material-symbols-outlined">edit</span>
                    </button>
                    <button class="del">
                      <span class="material-symbols-outlined">delete</span>
                    </button>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
