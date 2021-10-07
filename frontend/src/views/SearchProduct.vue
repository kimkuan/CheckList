<template>
  <div id="searchProduct">
    <div class="productList">
      <div class="result">
        <h3 style="display:inline-block">
          <strong>
          '{{ state.searchWord.word }}'
          </strong>
        </h3>
        <span> 검색 결과 </span>
        <span style="color:#CF000F; font-size:25px;">
          <strong v-if="state.products.length>0">
            {{ state.products[0].totalResultCount }}
          </strong>
          <strong v-else>
            0
          </strong>
        </span>
        <span>
          건
        </span>
      </div>
      <ProductCard v-for="product in state.products" :product="product" :category="state.category" :key="product.pcode" @click="clickProduct(product)"/>
      <InfiniteLoading @infinite="infiniteHandler"/>
    </div>
  </div>
</template>

<script>
import { computed, reactive, watch, watchEffect } from 'vue';
import { useStore } from "vuex";
import { useRouter } from "vue-router";
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
    const router = useRouter();
    const store = useStore();
    const state = reactive({
      searchWord: computed(() => {
        return store.getters["root/getSearchHistory"][0];
      }),
      products: computed(() => {
        return store.getters["root/getSearchProductListInfo"];
      }),
      category : computed(() => {
        return state.searchWord.category.key;
      }),
      // category : "coffeemachine",
      keywordValue : computed(() => {
        return router.params.keyword;
      }),
      pageValue: 0,
    })

    console.log("뷰엑스에저장된searchWord :")
    console.log(state.searchWord)

    const infiniteHandler = function($state){
      console.log('Call infiniteHandler Method');
      store.dispatch("root/requestGetSearchProducts", {
        category : state.category,
        keywordValue : state.searchWord.word,
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
      store.dispatch("root/requestGetSearchProducts", {
        category : category,
        keywordValue : searchWord,
        pageValue : pageValue,
      })
      .then(function(result){
        store.commit("root/setSearchProductListInfo", result.data);
      })
      .catch(function(err){
        console.log(err)
      })
    }

    const clickProduct = function(product){
      console.log("click!")
       store
        .dispatch("root/requestProductInfo", {
          category: state.category,
          pcode: product.pcode,
        })
        .then(function(result) {
          console.log(result.data)
          store.commit("root/setProductInfo", result.data);
          store.commit("root/setProductId", store.getters["root/getProductInfo"].pcode);
          store.commit("root/setMainCheckPickCategory", "airfryer");

          console.log(result.data.pcode)
          router.push({name: "Product", params: {category: state.category, pcode:result.data.pcode}});
        })
        .catch(function(err) {
          console.log(err)
        });
    }

    setProducts(state.category, state.searchWord.word, state.pageValue);

    watch(() => state.searchWord,
      (searchWord, prevSearchWord) => {
        state.pageValue = 0;
        setProducts(state.category, state.searchWord.word, state.pageValue);
      }
    )

    return {
      store, state, setProducts, infiniteHandler, clickProduct
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

