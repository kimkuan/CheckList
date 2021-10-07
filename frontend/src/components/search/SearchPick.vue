<template>
    <div class="searchPick">
        <div class="description">
          <p>체크리스트에서 분석한 내용을 바탕으로</p>
          <p>상품을 추천 받으세요</p>
        </div>

        <div class="searchCategory">
          <SearchCategory :categories="categories" />
        </div>

        <div class="searchList">
          <SearchList v-for="product in checkPickproducts" :product="product" :key="product.id" />
        </div>
    </div>
</template>

<script>
import SearchCategory from "./SearchCategory.vue";
import SearchFilter from "./SearchFilter.vue";
import SearchList from "./SearchList.vue";

import { requestCheckPickProducts } from "@/store/actions";
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
        checkPickproducts: [],
    });

    const getCheckPickInfo = () => {
        requestCheckPickProducts(store.getters["root/getSelectCategoryName"] + "/checkpick")
            .then( res => {
                console.log(res.data);
                state.checkPickproducts = res.data;
            })
        .catch(err => {
            console.log(err);
        });
    }

    onMounted(() => {
        getCheckPickInfo();
        console.log(state.checkPickproducts);
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
      
      getCheckPickInfo,
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