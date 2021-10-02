<template>
  <div class="searchHistoryList">
    <div class="noHistory" v-if="!state.searchHistory.length">
      최근 검색어 내역이 없습니다.
    </div>
    <div class="historyDiv" v-if="state.searchHistory.length">
      <div class="historyTitle">
        <div style="display: inline-block; width: 85%"><b>최근 검색어</b></div>
        <div class="totalDelte" @click="deleteAll">전체 삭제</div>
      </div>
    </div>
    <ul class="search-history" v-if="state.searchHistory.length">
      <li
        class="searchHistory__item"
        v-bind:key="index"
        v-for="(word, index) in state.searchHistory"
        @mouseover="turnColor"
      >
        <button class="btnSearchWord" @click="searchWord(word)">
          {{ word }}
        </button>
        <button class="searchHistory__item-content" @click="deleteWord(index)">
          <i class="fa fa-times"></i>
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";
export default {
  name: "MainHeaderSearchHistory",
  setup() {
    const store = useStore();
    const state = reactive({
      searchHistory: computed(() => {
        console.log(store.getters["root/getSearchHistory"]);
        return store.getters["root/getSearchHistory"];
      }),
    });

    const deleteAll = function () {
      store.commit("root/deleteHistoryAll");
    };

    const deleteWord = function (index) {
      console.log(index);
      store.commit("root/deleteWord", index);
    };

    const searchWord = function (word) {
      console.log(word);
      store.commit("root/setSearchWord", word);
      console.log(store.getters["root/getSearchWord"]);
      // 검색 결과 화면으로 이동
    };

    return { store, state, deleteAll, deleteWord, searchWord };
  },
};
</script>
<style scoped>
a {
  cursor: pointer;
}

button:focus {
  outline: none;
}

.historyDiv {
  padding: 10px 10px 0px 32px;
}
.totalDelte {
  display: inline-block;
  width: 15%;
  text-align: center;
}

.search-history {
  padding-top: 3px;
  list-style: none;
  min-height: 150px;
  max-height: 200px;
  overflow: auto;
}

.search-history::-webkit-scrollbar {
  margin-right: 20px;
  width: 10px;
}
.search-history::-webkit-scrollbar-thumb {
  background-color: #ff5757;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}

.noHistory {
  opacity: 0.6;
  padding-top: 15%;
  padding-bottom: 15%;
  text-align: center;
}
.searchHistoryList {
  width: 98%;
  background-color: white;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 10px;
  margin-top: 50px;
  padding: 10px 0px 10px 0px;
}

.btnSearchWord:hover {
  background: whitesmoke;
}

.searchHistory__item {
  line-height: 28px;
}

.searchHistory__item-content {
  background-color: white;
  border: none;
  color: #ff5757;
}

.btnSearchWord {
  text-align: left;
  width: 90%;
  background: white;
  border: none;
}
</style>
