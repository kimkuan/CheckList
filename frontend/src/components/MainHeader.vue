<template>
  <nav class="navbar navbar-light" :style="{ height: height }">
    <div class="container-fluid">
      <!-- 로고 -->
      <a class="one" @click="clickLogo">
        <img src="../assets/images/logo.png" alt="" width="150" height="50" />
      </a>
      <!-- 검색바 -->
      <div id="searchBar" class="search-box two">
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
import { reactive, onMounted } from "vue";
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
      searchHistoryView: false,
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

      store.commit("root/setSearchWord", state.searchWord);
      console.log(store.getters["root/getSearchHistory"]);
      state.searchWord = "";
      // 검색 결과 화면으로 이동
    };

    // 검색바 클릭
    const clickSearchBar = function () {
      state.searchHistoryView = !state.searchHistoryView;
      console.log(state.searchHistoryView)
    };

    // 검색바에서 마우스 떠날 때
    const changeDisplay  = function (status) {
      console.log(status)
      if (status == "over")
        state.searchHistoryView = true;
      else
        state.searchHistoryView = false;
    }

    return { router, store, state, onMounted, clickLogo, clickSearchBtn, clickSearchBar, changeDisplay };
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
  width: calc(100% - 45px);
  margin-left: 5px;
  align-self: center;
  display: flex;
  border: none;
  background: none;
  outline: none;
  float: left;
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
