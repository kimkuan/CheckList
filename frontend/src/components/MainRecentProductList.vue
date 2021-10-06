<template>
  <div class="recent-product-list-wrapper">
      <h4>최근 본 상품</h4>
      <div class="recent-product" v-for="(item, index) in state.productHistory" :key = index>
        <img class="recent-image" :src="item.productInfo.img" @click="showRecentProduct(item)"  />
        {{ item.pcode }}
      </div>
  </div>
</template>

<script>
import { computed, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  components: {
  },
  setup() {
    const route = useRouter()
    const store = useStore()
    const state = reactive({
        productHistory : computed(() => {
          return store.getters["root/getProductHistory"];
        })
    })


    const showRecentProduct = function(item){
      console.log("선택한 상품 >> ")
      console.log(item)
      store.commit("root/setProductInfo", item.productInfo);
      route.push({ name: "Product", params: { category : item.category, pcode: item.productInfo.pcode }});
    };

    return {state, route, showRecentProduct};
  },
  methods(){
  },
};
</script>

<style scoped>
.recent-product-list-wrapper {
  position: fixed;
  right: 0px;
  top: 94px;
  width: 170px;
  height: 440px;
  margin-right:0px;
  border-radius: 30px 0px 0px 30px;
  background-color:white;
  color:black;
  box-shadow: 0px 4px 4px rgb(0 0 0 / 25%);
}
.recent-image {
  width: 100px;
}

.slide-fade-enter-active, .slide-fade-leave-active {
  transition: all 1s cubic-bezier(0, .50, 0, .50);
}
.slide-fade-leave-to {
  transition: all 1s cubic-bezier(0, .50, 0, .50);
  transform: translateX(190px);
}
.slide-fade-enter-to {
  transition: all 1s cubic-bezier(0, .50, 0, .50);
  transform: translateX(0px);
}
</style>
