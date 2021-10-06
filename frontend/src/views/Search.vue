<template>
  <div id="search">
    <div class="searchTab">
      <nav>
        <div class="nav nav-tabs nav-fill nav-tabs-category" id="nav-category-tab" role="tablist">
          <button class="nav-link active category-button" id="nav-check-tab" data-bs-toggle="tab" data-bs-target="#nav-check" type="button" role="tab" aria-controls="nav-check" aria-selected="true">체크픽</button>
          <button class="nav-link category-button" id="nav-filter-tab" data-bs-toggle="tab" data-bs-target="#nav-filter" type="button" role="tab" aria-controls="nav-filter" aria-selected="false">직접탐색</button>
        </div>
      </nav>
    </div>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-check" role="tabpanel" aria-labelledby="nav-check-tab">
        
      </div>
      <div class="tab-pane fade" id="nav-filter" role="tabpanel" aria-labelledby="nav-filter-tab">
        
      </div>
    </div>
    <tabs v-model="selectedTab">
      <tab
        v-for="(tab, i) in tabs"
        :key="`t${i}`"
        :val="tab.value"
        indicator="true"
      >
        <div
          :style="{'border-color': getActive(tab.value) ? tab.color : '', color: getActive(tab.value) ? tab.color : ''}"
          :class="{'active-tab': getActive(tab.value)}"
          class="tab"
        >
          {{ tab.label }}
        </div>
      </tab>
    </tabs>
    <tab-panels
      v-model="selectedTab"
      :animate="true"
      :swipeable="false"
      class="tab-panels"
    >
      <tab-panel class="tab-panel" :val="tabs[0].value">
        <div class="description">
          <p>{{ tabs[0].description1 }}</p>
          <p>{{ tabs[0].description2 }}</p>
        </div>

        <div class="searchCategory">
          <SearchCategory :categories="categories" />
        </div>

        <div class="searchList">
          <SearchList v-for="product in products" :product="product" :key="product.id" />
        </div>
      </tab-panel>

      <tab-panel class="tab-panel" :val="tabs[1].value">
        <div class="description">
          <p>{{ tabs[1].description1 }}</p>
          <p>{{ tabs[1].description2 }}</p>
        </div>

        <div class="searchCategory">
          <SearchCategory :categories="categories" />
        </div>

        <div class="filter">
          <SearchFilter :filters="filters" :contents="contents"/>
        </div>

        <div class="result">
          <span style="color:#CF000F; font-size:25px;"><strong>500</strong></span><span>개의 검색 결과</span>
        </div>

        <div class="searchList">
          <SearchList v-for="product in products" :product="product" :key="product.pcode" />
        </div>

        <div class="page">
        </div>
      </tab-panel>


    </tab-panels>
  </div>
</template>

<script>
import SearchCategory from "../components/search/SearchCategory.vue";
import SearchFilter from "../components/search/SearchFilter.vue";
import SearchList from "../components/search/SearchList.vue";

import { requestProducts } from "../store/actions";
import { Tabs, Tab, TabPanels, TabPanel } from 'vue3-tabs';
// import { useRouter } from "vue-router";
import { useStore } from 'vuex';
import { reactive, toRefs, onMounted } from 'vue';

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

// let obj = JSON.parse(filters);
// console.log(obj);

const tabs = [{
  label: '체크픽',
  value: 1,
  description1: "체크리스트에서 분석한 내용을 바탕으로",
  description2: "상품을 추천 받으세요",
  color: '#CF000F'
}, {
  label: '직접탐색',
  value: 2,
  description1: "스펙을 원하는 대로 선택해서",
  description2: "상품을 찾을 수 있어요",
  color: '#CF000F'
}];

export default {
  name: 'Search',
  components: {
    Tabs,
    Tab,
    TabPanels,
    TabPanel,
    SearchCategory,
    SearchFilter,
    SearchList,
  },
  setup(){
    // const router = useRouter();
    const store = useStore();
    const state = reactive({
      totalPages: 0,
      totalProducts: 0,
      pageSize: 0,
      products: [],
      selectedTab: tabs[0].value
    });

    const getProductInfo = (page) => {
      requestProducts(store.getters["root/getSelectCategoryName"] + "/?pageNumber="+page)
        .then( res => {
          console.log(res);
          state.products = res.data.content;
          state.totalPages = res.data.totalPages;
          state.totalProducts = res.data.totalProducts;
          console.log(state.products);
        })
        .catch(err => {
          console.log(err);
      });
    }

    const getActive = (tab) => {
      return state.selectedTab === tab;
    }

    // for (let filter in filters) {
      // Object.keys(filter).forEach(function(k){
        // console.log(filter);
        // console.log('키값 : '+k + ', 데이터값 : ' + filter[k]);
      // });
    // }

    onMounted(() => {
      getProductInfo(1);
      console.log(state.products);
    })

    return {
      categories : [
        {
          id : 'Aircleaner',
          name : "공기청정기"
        },
        {
          id : 'Monitor',
          name : "모니터"
        },
        {
          id : 'Airfryer',
          name : "에어프라이어"
        },
        {
          id : 'Foodprocessor',
          name : "음식물처리기"
        },
        {
          id : 'Coffeemachine',
          name : "커피머신"
        },
      ],
      state,
      tabs,
      filters,
      contents,
      getActive,
      getProductInfo,
      ...toRefs(state),
    };
  },
}
</script>

<style scoped>
button {
  border: none;
  background: none;
}

button:focus {
  outline: none;
}
.searchTab {
  background-color: #FFF5F5;
  text-align: center;
  justify-content: center;
  border-top: 0.5px solid rgb(196, 196, 196);
  border-bottom: 1px solid rgb(196, 196, 196);
  font-size: 15px;
}
.tab-content {
  height: 25%;
}

.nav-link.active {
  color: rgba(207, 0, 15, 1);
  font-weight: bold;
  background-color: #FFF5F5;
  border-bottom-color: #cf000f;
  border-bottom-width: 3px;
}

.nav-link {
  color: #6d6d6d;
}
.tab {
  padding: 1px 10px;
  margin: 5px 100px;
}

/* .tab-panel {
  padding: 0px;
  box-sizing: border-box;
}
.tabs {
  background-color: #FFF5F5;
  text-align: center;
  justify-content: center;
  border-top: 0.5px solid rgb(196, 196, 196);
  border-bottom: 0.5px solid rgb(196, 196, 196);
  
} */

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
