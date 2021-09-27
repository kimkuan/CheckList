<template>
  <div id="search">
    <div class="searchFilter">
      <searchFilter />
    </div>
    <div class="productCard">
      <SearchList v-for="product in products" :product="product" :key="product.id" />
      <ProductCard v-for="product in products" :product="product" :key="product.id" />
    </div>
  </div>
</template>

<style scoped>

</style>

<script>
import SearchFilter from "../components/search/SearchFilter.vue";
import SearchList from "../components/search/SearchList.vue";
import ProductCard from "../components/product/ProductCard.vue";
import {computed} from 'vue';
import {useStore} from "vuex";
export default {
  name: 'Search',
  components: {
    SearchFilter,
    SearchList,
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
