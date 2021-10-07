<template>
  <div id="searchProduct">
    <div class="productList">
      <div class="result">
        <span>검색 결과 </span> <span style="color:#CF000F; font-size:25px;"><strong>{{ state.products[0].totalResultCount }}</strong></span><span>건</span>
        <h3>현재 검색어 : {{ state.searchWord }}</h3>
      </div>
      <ProductCard v-for="product in state.products" :product="product" :key="product.id"/>
      <InfiniteLoading @infinite="infiniteHandler"/>
    </div>
  </div>
</template>

<script>
import { computed, reactive, watch, watchEffect } from 'vue';
import { useStore } from "vuex";
import { useRoute } from "vue-router";
import ProductCard from "../components/product/ProductCard.vue";
import InfiniteLoading from "v3-infinite-loading";
import "v3-infinite-loading/lib/style.css";

export default {
  name: 'SearchProduct',
  components: {
    ProductCard,
    InfiniteLoading,
  },
  setup(){
    const router = useRoute();
    const store = useStore();
    const state = reactive({
      searchWord: computed(() => {
        return store.getters["root/getSearchHistory"][0];
      }),
      products: computed(() => {
        return store.getters["root/getSearchProductListInfo"];
      }),
      // category : computed(() => {
      //   return store.getters["root/getSearchCategory"][0];
      // }),
      category : "coffeemachine",
      keywordValue : computed(() => {
        return router.params.keyword;
      }),
      pageValue: 0,
    })

    console.log("뷰엑스에저장된searchWord :" +state.searchWord)

    const infiniteHandler = function($state){
      console.log('Call infiniteHandler Method');
      store.dispatch("root/requestGetSearchProducts", {
        category : state.category,
        keywordValue : state.searchWord,
        pageValue : state.pageValue+1,
      })
      .then(function(result){
        setTimeout(() => {
          if(result.data.length < 30) {
            console.log("인피니티스크롤 끝")
            $state.complete()
          }
          if(result.data.length){
            $state.loaded();
            state.pageValue++;
            for(var i = 0; i < result.data.length; i++){
              state.products.push(result.data[i])
            }
          } else {
            $state.complete()
          }
        })
      })
      .catch(function(err){
        console.log(err)
      })
    }

    // 1페이지 데이터 불러오기 axios
    const setProducts = function(category, searchWord, pageValue) {
      console.log("79")
      console.log(category)
      console.log(searchWord)
      console.log(pageValue)
      store.dispatch("root/requestGetSearchProducts", {
        category : category,
        keywordValue : searchWord,
        pageValue : pageValue,
      })
      .then(function(result){
        store.commit("root/setSearchProductListInfo", result.data);
        console.log(result.data)
      })
      .catch(function(err){
        console.log(err)
      })
    }

    setProducts(state.category, state.searchWord, state.pageValue);

    watch(() => state.searchWord,
      (searchWord, prevSearchWord) => {
        console.log("watch : 검색어 변경. 변경된 검색어 : "+state.searchWord)
        state.pageValue = 0;
        setProducts(state.category, state.searchWord, state.pageValue);
      }
    )

    return {
      store, state, setProducts, infiniteHandler, 
    };
  },
}
</script>

<style>
.result {
  margin-left: 20px;
}

.container { 
  text-align: -webkit-center;
}
</style>
