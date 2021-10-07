<template>
  <div class="card" v-if="state.checkPickList">
    <div v-bind:key="index"
      v-for="(checkPick, index) in state.checkPickList"
      @mouseover="turnColor"
    >
      <div class="card-body" v-if="index < 3" @click="readDetail(checkPick)">
        <div class="content img">
          <span class="card-rate" v-if="index == 0" style="font-size:45px;">🥇</span>
          <span class="card-rate" v-if="index == 1" style="font-size:45px;">🥈</span>
          <span class="card-rate" v-if="index == 2" style="font-size:45px;">🥉</span>
          <img :src="checkPick.img" class="card-img-left check-img"/>
        </div>
        <div class="content info">
          <div class="brand">
            <h5>{{checkPick.brand}}</h5>
          </div>
          <div class="name">
            <h4>{{checkPick.name}}</h4>
          </div>
          <div>

          </div>
        </div>
        <div class="content price">
          <span style="color:red; font-size:20px;">최저가 &nbsp;&nbsp;&nbsp;</span><span style="font-size:25px;" > <b>  {{ $filters.convertPrice(checkPick.price)}}원</b></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { reactive, computed, onMounted } from "vue";

export default {
  name: "MainCheckPick",
  props: {
    categoryName: String,
  },
  setup() {
    const router = useRouter();
    const store = useStore();
    const state = reactive({
      category: computed(() => {
        console.log("체크픽 카테고리")
        console.log(store.getters["root/getMainCheckPickCategory"]);
        return store.getters["root/getMainCheckPickCategory"];
      }),
      checkPickList: computed(() => {
        console.log("체크픽 페이지에서 리스트를 보자 ")
        console.log(store.getters["root/getCheckPickList"])
        return store.getters["root/getCheckPickList"];
      }),
    });

    const readDetail = function (product) {
      console.log(product);
      store.commit("root/setProductInfo", {
        productInfo: product,
      });

      router.push({name: "Product", params: {category: state.category, pcode: product.pcode }});
    };

    // 가기 전에 state.selectCategory 초기화
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
    return { store, state, onMounted, readDetail };
  },
}
</script>

<style scoped>
div{
  cursor: pointer;
}

.card {
  border: none;
}
.check-img {
  width: 65%;
}

.card-rate {
  position:absolute;
}

.card-body {
  border-radius: 25px ;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
  margin: 10px 0 10px 0;
}
.card-body > .content {
  display: inline-block;
}

.content.img {
  text-align: center;
  width:25%;
}

.content.info {
  position: relative;
  bottom: 40px;
  width:50%;
}

.content.price {
  width:25%;
  padding-right: 15px;
  text-align: right;
  position: relative;
  top: 80px;
}
</style>
