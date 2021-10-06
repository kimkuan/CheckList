<template>
    <div class="searchPick">
        <div class="description">
          <p>스펙을 원하는 대로 선택해서</p>
          <p>상품을 찾을 수 있어요</p>
        </div>

        <div class="searchCategory">
          <SearchCategory :categories="categories" @selectCategory="selectCategory" />
        </div>

        <div class="filter">
          <SearchFilter :filterTitles="filterTitles" :filters="filters"/>
        </div>

        <div class="result">
          <span style="color:#CF000F; font-size:25px;"><strong>{{totalProducts}}</strong></span><span>개의 검색 결과</span>
        </div>

        <div class="searchList">
          <SearchList v-for="product in products" :product="product" :key="product.pcode" />
        </div>
    </div>
</template>

<script>
import SearchCategory from "./SearchCategory.vue";
import SearchFilter from "./SearchFilter.vue";
import SearchList from "./SearchList.vue";

import { requestFilteringInfo, requestProducts } from "@/store/actions";
import { useStore } from 'vuex';
import { reactive, onMounted, toRefs } from 'vue';

export default {
  name: 'Search',
  components: {
    SearchCategory,
    SearchFilter,
    SearchList,
  },
  setup(){
    const store = useStore();
    const state = reactive({
      filterTitles: [],
      filters: [],
      page: 1,
      totalProducts: 0,
      products: [],
    });

    const getFilterInfo = () => {
      requestFilteringInfo(store.getters["root/getSelectCategoryName"])
      .then( res => {
        let arrayTitles = [];
        let arrayfilters = [];

        let string = JSON.parse(res.data.condition);
        console.log(string);
        for (let s in string) {
          console.log(s);
          arrayTitles.push(s);
          console.log(string[s]);
          arrayfilters.push(string[s]);
        }
        console.log(arrayTitles);
        state.filterTitles = arrayTitles;
        state.filters = arrayfilters;
      })
      .catch(err => {
        console.log(err);
      })
    }

    const postProductInfo = () => {
      requestProducts(store.getters["root/getSelectCategoryName"] + "/filters?page=" + state.page)
        .then( res => {
          console.log(res);
          state.products = res.data.content;
          state.totalProducts = res.data.totalElements;
        })
        .catch(err => {
          console.log(err);
      });
    }
    onMounted(() => {
      getFilterInfo();
      postProductInfo();
      console.log(state.products);
    })

    return {
      categories : [
        {
          id : 'aircleaner',
          name : "공기청정기"
        },
        {
          id : 'monitor',
          name : "모니터"
        },
        {
          id : 'airfryer',
          name : "에어프라이어"
        },
        {
          id : 'foodprocessor',
          name : "음식물처리기"
        },
        {
          id : 'coffeemachine',
          name : "커피머신"
        },
      ],

      getFilterInfo,
      postProductInfo,
      ...toRefs(state),
    };
  },
}
</script>

<style scoped>
.description {
  background-color: #FFF5F5;
  height: 100px;
  line-height: 40px;
  margin: auto;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 6px -1px, rgba(0, 0, 0, 0.06) 0px 2px 4px -1px;
  border: none;
  text-align: center;
  padding: 20px;
}
.searchList {
  margin-top: 20px;
}
p {
  margin:0px;
  height: 25px;
}
.searchCategory {
  margin-top: 10px;
}

.result {
  border-bottom: 0.5px solid rgb(196, 196, 196);
  padding: 20px;
  margin-left: 50px;
  font-size: 20px;
}
</style>