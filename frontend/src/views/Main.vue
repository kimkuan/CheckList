<template>
  <div class="main-wrapper" id="header">
    <main-header :height="`70px`"></main-header>

    <!-- 최근 본 상품 -->
    <transition name="slide-fade">
      <main-recent-productList v-show="show"></main-recent-productList>
    </transition>

    <div class="main-container">
      <router-view></router-view>
    </div>

    <!-- 플로팅 버튼 (최근 본 상품) -->
    <div class="floating-button-list-wrapper">
      <div class="floating-button-wrapper">
        <div class="floating-button" id="recent-product-btn" @click="show = !show">
        최근 본 상품
        </div>
      </div>

      <!-- 플로팅 버튼 (최상단 이동)  -->
      <div class="floating-button-wrapper">
        <a href="#" class="floating-button" @click="scrollToTop">
          <img src="@/assets/images/top-button.png" id="top-button-img"/>
        </a>
      </div>
    </div>

    <main-footer :height="`85px`"></main-footer>
  </div>
</template>

<script>
import MainHeader from "../components/MainHeader.vue";
import MainFooter from "../components/MainFooter.vue";
import MainRecentProductList from "../components/MainRecentProductList.vue";
import { useRouter } from 'vue-router';

export default {
  name: "Main",
  components: {
    MainHeader,
    MainFooter,
    MainRecentProductList,
  },
  data() {
    return {
      show : false,
    }
  },
  setup() {

    const route = useRouter()

    const scrollToTop = function(){
      window.scrollTo(0, 0);
    }

    return {scrollToTop, route};
  },
  methods(){
  },
};
</script>

<style>
@import "../css/Common.css";
@import "../css/Main.css";
</style>
