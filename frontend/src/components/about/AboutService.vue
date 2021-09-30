<template>
  <div class="content-wrapper">
  </div>

</template>

<script>
import { useRouter } from "vue-router";

export default {
  name: "about-service",
  setup() {
    const router = useRouter();

    return { router };
  },
  data() {
    return {};
  },
  computed() {},
  method() {},
};
</script>

<style scoped>
.content-wrapper {
  width: 100%;
  height: 500px;
  background-color: pink;
}
</style>
