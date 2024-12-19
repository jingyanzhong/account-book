import { defineStore } from 'pinia'

export const usethousandthsFormatStore = defineStore('thousandthsFormatStore', () => {
  function thousandthsFormat(val) {
    const regex = /\B(?=(\d{3})+(?!\d))/g
    val = val.toString()
    return val.replace(regex, ',')
  }
  return { thousandthsFormat }
})
