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
          <SearchFilter :filters="filters" :contents="contents"/>
        </div>

        <div class="result">
          <span style="color:#CF000F; font-size:25px;"><strong>{{totalProducts}}</strong></span><span>개의 검색 결과</span>
        </div>

        <div class="searchList">
          <SearchList v-for="product in products" :product="product" :key="product.pcode" />
        </div>

        <div class="page">
          <SearchPaging :totalPages="totalPages" @movePage="movePage"/>
        </div>
    </div>
</template>

<script>
import SearchCategory from "./SearchCategory.vue";
import SearchFilter from "./SearchFilter.vue";
import SearchList from "./SearchList.vue";
import SearchPaging from "./SearchPaging.vue";

import { requestProducts } from "@/store/actions";
import { useStore } from 'vuex';
import { reactive, onMounted, toRefs } from 'vue';

let filters = [
  {"가격": '~10만, 10~20만, 20-30만, 30만~'},
  {'사용목적': '침실용, 작은 거실용, 거실용, 큰 거실용'},
  {'면적': '~17(5평), 17~33(5~10평), 33~50(10~15평), 50~66(15~20평), 66~83(20~25평), 83~99(25~30평)',},
  {'필터등급': 'E11 등급, E12 등급, H13 등급, H14 등급',}
]
let contents = [
  {"가격": '~10만, 10~20만, 20-30만, 30만~'},
  {'사용목적': '침실용, 작은 거실용, 거실용, 큰 거실용'},
  {'면적': '~17(5평), 17~33(5~10평), 33~50(10~15평), 50~66(15~20평), 66~83(20~25평), 83~99(25~30평)',},
  {'필터등급': 'E11 등급, E12 등급, H13 등급, H14 등급',}
]

export default {
  name: 'Search',
  components: {
    SearchCategory,
    SearchFilter,
    SearchList,
    SearchPaging,
  },
  setup(){
    const store = useStore();
    const state = reactive({
      filters,
      contents,
      totalPages: 0,
      totalProducts: 0,
      pageSize: 0,
      products: [],
    });

    const getProductInfo = (page) => {
      requestProducts(store.getters["root/getSelectCategoryName"] + "/?pageNumber="+page)
        .then( res => {
          console.log(res);
          state.products = res.data.content;
          state.totalPages = res.data.totalPages;
          state.totalProducts = res.data.totalElements;
        })
        .catch(err => {
          console.log(err);
      });
    }
    
    const movePage = (page) => {
      this.getProductInfo(page+1);
      console.log("moving page: " + page+1);
    }


    onMounted(() => {
      getProductInfo(1);
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
      
      movePage,
      getProductInfo,
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