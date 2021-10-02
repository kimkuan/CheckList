<template>
  <div class="category-total">
    <div class="noCategory" v-if="!state.isItem">
      <h2><b>서비스 준비중입니다. 🤐</b></h2>
    </div>
    <div
      class="category-wrap"
      v-bind:key="index"
      v-for="(category, index) in state.categoryList"
    >
      <div class="category" v-if="category.visible" @click="clickCategory(category.name)">
        <div class="category-img-div">
          <img class="category-img" :src="category.img" />
        </div>
        <div class="category-name">
          <b> {{ category.name }} </b>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { reactive, computed } from "vue";
export default {
  name: "MainCategory",
  props: {
    visible: Array(),
  },
  setup(props) {
    const router = useRouter();
    const store = useStore();
    const state = reactive({
      isItem: computed(() => {
        return props.visible[5];
      }),
      categoryList: computed(() => {
        return [
          {
            name: "모니터",
            img: require("@/assets/images/category/monitor.png"),
            visible: props.visible[0],
          },
          {
            name: "에어프라이어",
            img: require("../../assets/images/category/airfryer.png"),
            visible: props.visible[1],
          },
          {
            name: "공기청정기",
            img: require("../../assets/images/category/air-cleaner.png"),
            visible: props.visible[2],
          },
          {
            name: "커피머신",
            img: require("../../assets/images/category/coffee-machine.png"),
            visible: props.visible[3],
          },
          {
            name: "음식물처리기",
            img: require("../../assets/images/category/food-processor.png"),
            visible: props.visible[4],
          },
        ];
      }),
    });

    const clickCategory = function (name) {
      console.log("Main >> 선택한 카테고리 >> " + name);
      store.commit("root/setSelectCategoryName", name);
      // Search.vue로 이동
      router.push({ name: "Search"});
    };

    return { router, store, state, clickCategory };
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
.category-total {
  width: 100%;
  display: inline-block;
  height: 200px;
  margin-bottom: 30px;
}
.noCategory {
  height: 100%;
  padding-top: 50px;
  text-align: center;
  opacity: 0.7;
}

.category-wrap {
  display: inline-block;
}
.category {
  height: 100%;
  display: inline-block;
  justify-content: center;
  margin-top: 30px;
  align-content: center;
  text-align: center;
  width: 180px;
  height: 170px;
}

.category-img-div {
  width: 120px;
  height: 120px;
  border-radius: 80px;
  margin-left: 30px;
  text-align: center;
  background: #fff5f5;
  vertical-align: middle;
}
.category-img {
  width: 80px;
  opacity: 0.5;
  margin-top: 20px;
}

.category-name {
  margin-top: 5px;
}
</style>
