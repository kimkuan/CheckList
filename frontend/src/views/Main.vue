<template>
  <div class="main-wrapper" id="header">
    <main-header :height="`70px`"></main-header>
    <div class="main-container">
      <router-view></router-view>
    </div>

    <!-- 플로팅 버튼 -->
    <div class="floating-button-list-wrapper">
      <div class="floating-button-wrapper">
        <router-link to="/recentProductList" class="floating-button" id="recent-product">
        최근 본 상품
        </router-link>
      </div>
      <div class="floating-button-wrapper">
        <a href="#" class="floating-button" id="go-to-top">
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

export default {
  name: "Main",
  components: {
    MainHeader,
    MainFooter,
  },
};
</script>

<style>
@import "../css/Common.css";
@import "../css/Main.css";
</style>
