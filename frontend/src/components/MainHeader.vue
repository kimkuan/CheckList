<template>
  <nav class="navbar navbar-light bg-light" :style="{ height: height }">
    <div class="container-fluid">
      <a class="one" @click="clickLogo">
        <img src="../assets/images/logo.png" alt="" width="150" height="50" />
      </a>
      <div class="search-box two">
        <input type="text" class="search-txt" name="" placeholder="Search">
        <a class="search-btn" href="#">
          <i class="fas fa-search"></i>
        </a>
      </div>
      <div class="three" style="width : 100px"></div>
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

<style>
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
.one {
  width: 20%;
}

.two {
  width: 60%;
}

.three {
  width: 20%;
}

.search-box {
  padding-left: 10px;
  width: 400px;
  height: 30px;
  background-color: #FFF3F3;
  border-radius: 30px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.search-btn {
  padding-right: 5px;
  text-decoration: none;
  float: right;
  width: 30px;
  height: 30px;
  background-color: #FFF3F3;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: red;
}
.search-txt{
  display: flex;
  padding: 0;
  width: 240px;
  border:none;
  background: none;
  outline: none;
  float: left;
  font-size: 1rem;
  line-height: 30px;
}
.search-txt::placeholder {
  opacity: .5;
}
</style>
