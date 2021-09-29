<template>
  <div class="main-wrapper">
    <main-header :height="`70px`"></main-header>
    <div class="main-container">
      <router-view></router-view>
    </div>
    <main-footer :height="`70px`"></main-footer>
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
