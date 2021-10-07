<template>
    <div class="searchPick">
        <div class="description">
          <p>체크리스트에서 분석한 내용을 바탕으로</p>
          <p>상품을 추천 받으세요</p>
        </div>
        <div class = "searchCategory">
          <select class="category-select round-3" id="selectCategory" v-model="state.curCategory" @change="changeCategory">
            <option selected disabled>카테고리</option>
            <option class="option-category" id="monitor" value="monitor">모니터</option>
            <option class="option-category" id="coffeemachine" value="coffeemachine">커피머신</option>
            <option class="option-category" id="aircleaner" value="aircleaner">공기청정기</option>
            <option class="option-category" id="airfryer" value="airfryer">에어프라이어</option>
            <option class="option-category" id="foodprocessor" value="foodprocessor">음식물처리기</option>
          </select>
        </div>
        <div class="searchList">
          <SearchList v-for="product in state.checkPickproducts" :product="product" :key="product.id" @click="clickProductDetail(product)" />
        </div>
    </div>
</template>

<script>
import SearchCategory from "./SearchCategory.vue";
import SearchFilter from "./SearchFilter.vue";
import SearchList from "./SearchList.vue";

import { requestCheckPickProducts } from "@/store/actions";
import { useStore } from 'vuex';
import { reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router';

export default {
  name: 'Search',
  components: {
    SearchCategory,
    SearchFilter,
    SearchList,
  },
  setup(){
    const route = useRouter()
    const store = useStore();
    const state = reactive({
      price : [],
      checkPickproducts: [],
      curCategory: store.getters["root/getSelectCategoryName"]
    });

    const clickProductDetail = function(product){
      console.log(product)
      route.push({ name: "Product", params: { category : state.curCategory, pcode: product.pcode }});
    };

    const getCheckPickInfo = () => {
      console.log(getCheckPickInfo);
      requestCheckPickProducts(store.getters["root/getSelectCategoryName"] + "/checkpick")
        .then( res => {
          console.log(res.data);
          state.checkPickproducts = res.data;
          state.price = res.data.price;
        })
      .catch(err => {
        console.log(err);
      });
    }

    const changeCategory = function () {
      store.commit("root/setSelectCategoryName", state.curCategory);
      route.go({ name: "Search" });
    };

    onMounted(() => {
        getCheckPickInfo();
    })

    return {
      changeCategory,
      clickProductDetail,
      getCheckPickInfo,
      route, store, state
    };
  },
}
</script>

<style scoped>
.description {
  background-color: #FFF5F5;
  height: 110px;
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
.searchCategory {
  margin-top: 20px;
  margin-bottom: 20px;
  text-align: center;
  font-size: 16px;
}
#selectCategory{
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 6px -1px, rgba(0, 0, 0, 0.06) 0px 2px 4px -1px;
}
p {
  margin:0px;
  height: 25px;
}
.category-select {
  border: none;
  outline:none;
  background-color: #fff3f3;
  padding: 5px;
}

.result {
  border-bottom: 0.5px solid rgb(196, 196, 196);
  padding: 20px;
  margin-left: 50px;
  font-size: 20px;
}

h3.title {
  text-align: right;
  width: 15%;
  font-size: 20px;
}

h3.content {
  text-align: left;
  width: 40%;
  font-size: 20px;
}
</style>