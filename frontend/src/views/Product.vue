<template>
  <div>
    <product-detail></product-detail>
  </div>
</template>

<style scoped>

</style>

<script>
import ProductDetail from '../components/product/ProductDetail.vue';
import {computed} from 'vue';
import {useStore} from "vuex";
export default {
  name: 'Product',
  components: {
    ProductDetail,
  },
  setup(){
    const store = useStore();
    let productId = computed(function () {
      return store.state.productId
    });
    return {
      productId,
    }
  },
}
</script>
