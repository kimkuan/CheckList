<template>
  <nav class="navbar navbar-light" :style="{ height: height }">
    <div class="container-fluid">
      <!-- 로고 -->
      <a class="one" @click="clickLogo">
        <img src="../assets/images/logo.png" alt="" width="150" height="50" />
      </a>
      <!-- 검색바 -->
      <div id="searchBar" class="search-box two">
          <select class="category-select" id="selectCategory" v-model="state.searchCategory">
            <option selected disabled>카테고리</option>
            <option class="option-category" id="monitor" value="monitor">모니터</option>
            <option class="option-category" id="coffeemachine" value="coffeemachine">커피머신</option>
            <option class="option-category" id="aircleaner" value="aircleaner">공기청정기</option>
            <option class="option-category" id="airfryer" value="airfryer">에어프라이어</option>
            <option class="option-category" id="foodprocessor" value="foodprocessor">음식물처리기</option>
          </select>
        <input
          type="text"
          class="search-txt"
          name=""
          placeholder="Search"
          v-model="state.searchWord"
          @keyup.enter="clickSearchBtn"
          @click="clickSearchBar"
        />
        <a class="search-btn" @click="clickSearchBtn">
          <i class="fas fa-search"></i>
        </a>
        <main-header-search-history
          v-show="state.searchHistoryView"
          style="z-index: 3; position: relative"
        />
      </div>
      <div class="three"></div>
    </div>
  </nav>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { reactive, computed, onMounted } from "vue";
import MainHeaderSearchHistory from "./header/MainHeaderSearchHistory.vue";

export default {
  name: "main-header",
  components: {
    MainHeaderSearchHistory,
  },
  props: {
    height: {
      type: String,
      default: "70px",
    },
  },
  setup() {
    const router = useRouter();
    const store = useStore();
    const state = reactive({
      searchWord: "",
      searchWordList: [],
      searchHistoryView: computed(() => {
        return store.getters["root/getSearchHistoryView"];
      }),
      searchCategory: "카테고리",
    });

    onMounted({
    })

    // 로고 클릭
    const clickLogo = function () {
      router.push({ name: "Main" }); // vue-router.js 밑에 정의해둔 메인페이지 경로로 이동
    };

    // 돋보기 버튼 or 엔터키 입력
    const clickSearchBtn = function () {
      if (!state.searchWord) return;
      console.log(state.searchWord);
      console.log(state.searchCategory);
      if(state.searchCategory == "카테고리") {
        alert("카테고리 입력해");
        return;
      }
      var obj = {};
      if(state.searchCategory == "airfryer") {
        obj.key = "airfryer";
        obj.word = "에어프라이어";
      } else if(state.searchCategory == "aircleaner") {
        obj.key = "aircleaner";
        obj.word = "공기청정기";
      } else if(state.searchCategory == "coffeemachine") {
        obj.key = "coffeemachine";
        obj.word = "커피머신";
      } else if(state.searchCategory == "monitor") {
        obj.key = "monitor";
        obj.word = "모니터";
      } else {
        obj.key = "foodprocessor";
        obj.word = "음식물처리기";
      }
      store.commit("root/setSearchCategory", obj);
      store.commit("root/setSearchWord", state.searchWord);

      console.log(store.getters["root/getSearchHistory"]);
      state.searchWord = "";
      state.searchCategory = "카테고리";
      state.searchHistoryView = false;
      // 검색 결과 화면으로 이동
      router.push({ name: "SearchProduct" });
    };

    // 검색바 클릭
    const clickSearchBar = function () {
      store.commit("root/setSearchHistoryView", !state.searchHistoryView);
      console.log(state.searchHistoryView)
    };

    // // 검색바에서 마우스 떠날 때
    // const changeDisplay  = function (status) {
    //   console.log(status)
    //   if (status == "over")
    //     state.searchHistoryView = true;
    //   else
    //     state.searchHistoryView = false;
    // }

    return { router, store, state, onMounted, clickLogo, clickSearchBtn, clickSearchBar };
  },
  data() {
    return {
      // searchWord: "",
    };
  },
  computed() {},
  methods: {

  },
};
</script>

<style scoped>
.main-header {
  z-index: 100; /* 헤더 맨위 고정 */
  position: sticky;
  top: 0;
  left: 0;
  width: 100%;
  text-align: center;
  overflow: visible;
}

a {
  cursor: pointer;
}
.category-select {
  border: none;
  outline:none;
  background-color: #fff3f3;
}

option:disabled {
}

.option-category:hover {
  background-color: #ff5757;
}
.search-box {
  padding-left: 10px;
  height: 40px;
  background-color: #fff3f3;
  border-radius: 30px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.search-btn {
  padding-right: 5px;
  text-decoration: none;
  float: right;
  width: 40px;
  height: 40px;
  background-color: #fff3f3;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #ff5757;
}
.search-txt {
  margin-left: 3px;
  width: calc(100% - 165px);
  align-self: center;
  border: none;
  background: none;
  outline: none;
  line-height: 40px;
}

.search-txt::placeholder {
  opacity: 0.5;
}

.one,
.three {
  width: 25%;
}

.two {
  width: 50%;
}

.navbar {
  padding-top: 1rem;
  padding-bottom: 1rem;
}
</style>
