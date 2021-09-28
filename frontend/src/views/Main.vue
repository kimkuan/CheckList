<template>
  <div class="main-content">
    <main-header></main-header>
    <main-footer></main-footer>
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
