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
          <div class="row g-0">
            <div class="line" v-for="(title, i) in state.filterTitles" :key=i>
              <div class="name col-md-2">{{title}}</div>
              <div class="contents" v-for="(filter, j) in state.filters[i]" :key=j>
                <input type="checkbox" :id="title+':'+filter" class="check" @click="getCheckboxValue">
                <label :for="title+':'+filter" class="check-label"><span>{{filter}}</span></label>
              </div>
            </div>
          </div>
        </div>

        <div class="result">
          <span style="color:#CF000F; font-size:25px;"><strong>{{state.totalProducts}}</strong></span><span>개의 검색 결과</span>
        </div>

        <div class="searchList" id="searchList">
          <SearchList v-for="product in state.products" :product="product" :key="product.pcode" />
          <InfiniteLoading @infinite="infiniteHandler"/>
        </div>
    </div>
</template>

<script>
import SearchCategory from "./SearchCategory.vue";
import SearchFilter from "./SearchFilter.vue";
import SearchList from "./SearchList.vue";
import InfiniteLoading from "v3-infinite-loading";
import "v3-infinite-loading/lib/style.css";

import { requestFilteringInfo } from "@/store/actions";
import { useStore } from 'vuex';
import { reactive, onMounted, computed } from 'vue';

export default {
  name: 'Search',
  components: {
    SearchCategory,
    SearchFilter,
    SearchList,
    InfiniteLoading,
  },
  setup(){
    const store = useStore();
    const state = reactive({
      filterTitles: [],
      filters: [],
      query: {},
      pageValue: 0,
      totalProducts: 0,
      products: [],
      json: {},
      selectedFilter: computed(() => {
        return store.getters["root/getSelectedFilter"];
      }),
    });
    
    const getFilterInfo = () => {
      requestFilteringInfo(store.getters["root/getSelectCategoryName"])
      .then( res => {
        let arrayTitles = [];
        let arrayfilters = [];

        let string = JSON.parse(res.data.condition);
        console.log(string);
        for (let s in string) {
          arrayTitles.push(s);
          arrayfilters.push(string[s]);
        }
        state.filterTitles = arrayTitles;
        state.filters = arrayfilters;
      })
      .catch(err => {
        console.log(err);
      })
    }


    function getCheckboxValue()  {
      // 선택된 목록 가져오기
      const check = 'input[type="checkbox"]:checked';
      const selectedEls = document.querySelectorAll(check);

      // 선택된 목록에서 value 찾기
      let filterList = [];
      state.json = {};
      selectedEls.forEach((el) => {
        // 선택된 필터 목록 저장
        filterList.push(el.id);
        // 만원 붙은 경우 카테고리별로 검색이 안되는 경우가 있음..! => 만원 제거, key, value 분리
        let map = el.id.replace('만원', '').split(':');
        let key = map[0];
        let value = map[1];


        // 키가 있는 경우 기존 배열에 값 추가후 key에 넣어줌
        if (!state.json.hasOwnProperty(key)) {
          state.json[key] = [];
        }
        state.json[key].push(value);
        console.log(state.json[key]);
        store.commit("root/setSelectedFilter", filterList);
      });
      console.log(filterList);
      state.selectedFilter = filterList;
      infiniteHandler();
    }

    onMounted(() => {
      getFilterInfo();
    })

    const infiniteHandler = ($state) => {
      console.log('Call infiniteHandler Method');
      store.dispatch("root/requestPostFilteringProducts", {
        category : store.getters["root/getSelectCategoryName"],
        filters : state.json,
        pageValue : state.pageValue+1,
      })
      .then(function(result){
        setTimeout(() => {
          console.log(result.data);
          if(result.data.length < 10) {
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

      getCheckboxValue,
      infiniteHandler,
      getFilterInfo,
      store, state
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
.filter {
  box-shadow: rgba(0, 0, 0, 0.1) 0px 5px 6px -5px, rgba(0, 0, 0, 0.06) 0px 4px 6px -5px;
  margin-bottom: 50px;
}
.line {
    border-bottom: 0.5px solid rgb(196, 196, 196);
}
.name {
    background-color: #FFF5F5;
    display: inline-block;
    font-size: 18px;
    padding: 10px;
    padding-left: 30px;
}
.contents {
    display: inline-block;
    margin-left: 3px;
    text-align: center;
}

.check {
    display: none;
}

.check-label {
    display: inline-block;
    min-width: 55px;
    height: 30px;
    margin-left: 10px;
    border-radius: 50px;
    padding: 3px;
}

input:checked ~ .check-label {
    background-color: #CF000F;
    color: white;
}
</style>