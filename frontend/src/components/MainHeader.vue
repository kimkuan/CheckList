<template>
  <nav class="navbar navbar-light" :style="{ height: height }">
    <div class="container-fluid">
      <a class="one" @click="clickLogo">
        <img src="../assets/images/logo.png" alt="" width="150" height="50" />
      </a>
      <div class="search-box two">
        <input type="text" class="search-txt" name="" placeholder="Search">
        <a class="search-btn" href="#" @click="searchProduct">
          <i class="fas fa-search"></i>
        </a>
      </div>
      <div class="three"></div>
    </div>
  </nav>
</template>

<script>
import { useRouter } from "vue-router";

export default {
  name: "main-header",
  components: {},
  props: {
    height: {
      type: String,
      default: "70px",
    },
  },
  setup() {
    const router = useRouter();
    const clickLogo = function () {
      router.push({ name: "Main" }); // vue-router.js 밑에 정의해둔 메인페이지 경로로 이동
    };

    const searchProduct = function() {
      router.push({ name: "SearchProduct" });
    };

    return { router, clickLogo, searchProduct };
  },
  data() {
    return {};
  },
  computed() {},
  method() {},
};
</script>

<style scoped>
.main-header {
  z-index: 100; /* 헤더 맨위 고정 */
  position: sticky;
  top: 0;
  left: 0;
  width: 100%;
  text-align: center;
  overflow: visible;
}
a {
  cursor: pointer;
}

.search-box {
  padding-left: 10px;
  width: 100%;
  height: 40px;
  background-color: #fff3f3;
  border-radius: 30px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.search-btn {
  padding-right: 5px;
  text-decoration: none;
  float: right;
  width: 35px;
  height: 40px;
  background-color: #fff3f3;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: red;
}
.search-txt {
  align-self: center;
  display: flex;
  border: none;
  background: none;
  outline: none;
  float: left;
  line-height: 40px;
}
.search-txt::placeholder {
  opacity: 0.5;
}

.one,
.three {
  width: 25%;
}

.two {
  width: 50%;
}

.navbar {
  padding-top: 1rem;
  padding-bottom: 1rem;
}
</style>
