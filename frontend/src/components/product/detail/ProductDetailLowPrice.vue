<template>
  <!-- font 크기 수정 예정 -->
  <div class="low-price">
     <div class="title">
        <div><h2>최저가 사러가기</h2></div>
      </div>
      <div class="site-list" v-for="(item, index) in state.lowPriceInfoList" v-bind:key="index">
        <div class="site">
          <span class="name">{{ item.site }}</span>
          <span class="price">{{ $filters.convertPrice(item.price) }}원</span>
          <span class="fee">무료배송</span>
          <button>사러가기</button>
        </div>
      </div>
  </div>
</template>

<style scoped>
.low-price {
  width: 100%;
  min-height: 150px;
}
.low-price .title {
  margin: 10px 10px;
}
.low-price .title div {
  display: inline-block;
  vertical-align: middle;
}
.site-list {
  text-align: center;
}
.site {
  display: table;
  table-layout: fixed;
  width: 50%;
  max-width: 500px;
  margin: 0 auto;
  margin-top: 20px;
}
.site > span {
  display: table-cell;
  vertical-align: middle;
}
.site > button {
  display: inline-block;
  width: 100px;
  height: 30px;
  background: #fff3f3;
  border-radius: 15px;
  border: none;
  filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.25));
}
.site > .name {
  font-weight: 600;
}
.site > .price {
  color: #cf000f;
  font-weight: 600;
}
.site > .fee {
  font-size: 0.875rem;
}

</style>

<script>
import { computed, reactive } from '@vue/reactivity';
import { useStore } from "vuex";

export default {
  name: 'ProductLowPrice',
  props:{

  },
  setup(){
    const store = useStore()

    const state = reactive({
      lowPriceInfoList : computed(() => store.getters["root/getProductInfo"].lowPriceInfoList),
    })

    return { state }
  }
}
</script>
