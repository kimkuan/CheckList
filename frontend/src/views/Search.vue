<template>
  <div id="search">
    <div class="productCard">
      <ProductCard v-for="product in products" :product="product" :key="product.id" />
    </div>
  </div>
</template>

<style scoped>

</style>

<script>
import ProductCard from "../components/product/ProductCard.vue";
import {computed} from 'vue';
import {useStore} from "vuex";
export default {
  name: 'Search',
  components: {
    ProductCard,
  },
  setup(){
    const store = useStore();
    let products = computed(function () {
      return store.state.products
    });
    return {
      products,
    }
  }
  
}
</script>
