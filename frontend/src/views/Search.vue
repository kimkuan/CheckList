<template>
  <div id="search">
    <div class="searchTab">
      <!-- <searchFilter /> -->
      <ul class="nav nav-tabs nav-justified mb-3" id="tab" role="tablist">
        <li class="nav-item" role="presentation" v-bind:class="{active : tab === selectedTab}">
          <a
            class="nav-link active"
            id="checkPick-tab"
            data-toggle="tab"
            href="#checkPick"
            role="tab"
            aria-selected="true"
            >체크 Pick</a
          >
        </li>
        <li class="nav-item" role="presentation" v-bind:class="{active : tab === selectedTab}">
          <a
            class="nav-link"
            id="filter-tab"
            data-toggle="tab"
            href="#filter"
            role="tab"
            aria-selected="false"
            >직접 탐색</a
          >
        </li>
      </ul>
    </div>
    <div class="searchList">
      <div>

      </div>
      <SearchList v-for="product in products" :product="product" :key="product.id" />
    </div>
  </div>
</template>

<style scoped>

</style>

<script>
// import SearchFilter from "../components/search/SearchFilter.vue";
import SearchList from "../components/search/SearchList.vue";
// import SearchPick from "../components/search/SearchPick.vue";

import {computed} from 'vue';
import {useStore} from "vuex";

export default {
  name: 'Search',
  components: {
    // SearchFilter,
    SearchList,
    // SearchPick,
  },
  setup(){
    const store = useStore();
    let products = computed(function () {
      return store.state.products
    });
    return {
      products,
      // currentTabComponent: "SearchPick",
      currentId: 1,
            list: [
                { id: 1, label: '체크픽'},
                { id: 2, label: '직접탐색'},
            ]
        }
    },
    computed: {
        current() {
            return this.list.find(el => el.id === this.currentId) || {}
        }
    }
}
</script>
