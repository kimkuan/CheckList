<template>
  <div class="recent-product-list-wrapper">
      <h4>최근 본 상품</h4>
      <div class="recent-product" v-for="(item, index) in state.productList" :key = index>
          상품 데이터 {{ item }}
      </div>
  </div>
</template>

<script>
import { reactive } from 'vue';

export default {
  components: {
  },
  setup() {
    const state = reactive({
        productList : ['1', '2', '3', '4'],
    })
    return {state};
  },
  methods(){
  },
};
</script>

<style>

</style>
