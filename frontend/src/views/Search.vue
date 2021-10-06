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
        <SearchPick />
      </div>
      <div class="tab-pane fade" id="nav-filter" role="tabpanel" aria-labelledby="nav-filter-tab">
        <SearchConditions />
      </div>
    </div>
  </div>
</template>

<script>
import SearchPick from "../components/search/SearchPick.vue";
import SearchConditions from "../components/search/SearchConditions.vue";

export default {
  name: 'Search',
  components: {
    SearchPick,
    SearchConditions,
  },
  setup(){
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
  background-color: #fff;
  border-color: #fff #fff #cf000f;
  border-top: 0.5px solid rgb(196, 196, 196);
  border-bottom: 1px solid rgb(196, 196, 196);
  font-size: 15px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 6px -1px, rgba(0, 0, 0, 0.06) 0px 2px 4px -1px;
}
.tab-content {
  height: 25%;
}
.nav-link.active {
  color: rgba(207, 0, 15, 1);
  font-weight: bold;
  background-color: #fff;
  border-bottom-color: #cf000f;
  border-bottom-width: 3px;
}
.nav-link:hover {
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}
.nav-link {
  color: #6d6d6d;
}
</style>