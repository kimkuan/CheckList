<template>
  <div>
    <div
      class="category"
      v-bind:key="index"
      v-for="(category, index) in state.categoryList"
    >
      <div>
        <img class="category-img" :src="category.img" />
      </div>
      <div>
        {{ category.name }}
      </div>

    </div>
  </div>
</template>

<script>
// import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { reactive } from "vue";
export default {
  name: "MainCategory",
  props: {},
  setup() {
    const store = useStore();
    const state = reactive({
      categoryList: [
        {
          name: "모니터",
          img: require("@/assets/images/category/monitor.png"),
          visible: true,
        },
        {
          name: "에어프라이어",
          img: require("../../assets/images/category/airfryer.png"),
          visible: true,
        },
        {
          name: "공기청정기",
          img: require("../../assets/images/category/air-cleaner.png"),
          visible: true,
        },
        {
          name: "커피머신",
          img: require("../../assets/images/category/coffee-machine.png"),
          visible: true,
        },
        {
          name: "음식물처리기",
          img: require("../../assets/images/category/food-processor.png"),
          visible: true,
        },
      ],
    });
    // image 크기 120

    return { store, state };
  },
  data() {
    return { }
  }
};
</script>

<style scoped>
.category {
  display: inline-block;
  width: 120px;
  height: 170px;
}

.category-img {
  width: 80px;
}
</style>
