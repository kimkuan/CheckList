<template>
  <div>{{ state.category }}</div>
</template>

<script>
import { useStore } from "vuex";
import { reactive, computed, onMounted } from "vue";

export default {
  name: "MainCheckPick",
  setup() {
    const store = useStore();
    const state = reactive({
      category: computed(() => {
        console.log(store.getters["root/setMainCheckPickCategory"]);
        return store.getters["root/setMainCheckPickCategory"];
      }),
      productList: [],
    });

    console.log(state.category);

    store.dispatch("root/requestCheckPick", state.category)
      .then(function(result) {
        console.log(result.data);
      })
      .catch(function(err){
        console.log(err)
      });
    // console.log("과연'>>");
    // console.log(state.category);
    // onMounted(() => {
    //   console.log(props.category);
    //   store.dispatch("root/requestCheckPick", {
    //       category: props.category,
    //     })      // props.category
    //     .then(function (result) {
    //       console.log(result.data)
    //       store.commit("root/setProductInfo", result.data);
    //     })
    //     .catch(function(err){
    //       console.log(err)
    //     })
    // });
    return { store, state, onMounted };
  },
}
</script>

<style scoped>

</style>
