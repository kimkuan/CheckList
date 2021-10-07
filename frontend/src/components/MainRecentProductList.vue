<template>
  <div class="recent-product-list-wrapper">
      <div class="title">
        <h4>최근 본 상품</h4>
      </div>
      <hr/>
      <div class="recent-product" v-for="(item, index) in state.productHistory" :key = index>
        <img class="recent-image" @mouseover="turnColor" :src="item.productInfo.img" @click="showRecentProduct(item)"  />
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
      console.log(item)

      // 현재 가져온 상품을 최근 본 상품에 등록
      store.commit("root/setProductHistory", item);
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
  height: 450px;
  margin-right:0px;
  padding: 5px;
  border-radius: 30px 0px 0px 30px;
  background-color:white;
  color:black;
  box-shadow: 0px 4px 4px rgb(0 0 0 / 25%);
}
.recent-product{
  text-align: center;
  margin: 0 auto;
}
hr{
  margin: 0 !important;
  margin-bottom: 0.5rem !important;
  color: #CFCFCF;
}
.title h4{
  text-align: center;
}
.recent-image {
  width: 90px;
  margin-bottom: 10px;
}
.recent-image:hover{
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.2);
  cursor: pointer;
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
