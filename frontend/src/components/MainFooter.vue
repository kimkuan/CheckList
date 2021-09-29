<template>
  <div class="main-footer" :style="{height : height}">
    <div class="row one">
      <div class="col click" @click="clickLogo">
        <img src="@/assets/images/logo.png" style="height:40px">
      </div>
    </div>
    <div class="row two">
      <div class="col click" @click="clickDeveloper">
        개발자 소개
      </div>
      <div class="col copyright">
        Copyright © SAMSUNG All Rights Reserved.
      </div>
      <div class="col click" @click="clickService">
        서비스 소개
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

export default {
  name: 'main-footer',

  props: {
    height: {
      type: String,
      default: '70px'
    }
  },

  setup() {
    const router = useRouter();
    const state = reactive({});

    const clickLogo = function () {
      router.push({ name: "Main" }); // vue-router.js 밑에 정의해둔 메인페이지 경로로 이동
    };

    const clickDeveloper = function (){
      router.push({ name: "AboutUs" });
    };

    const clickService = function () {
      router.push({ name: "AboutService" });
    };

    return { router, state, clickLogo, clickDeveloper, clickService }
  }
}

</script>

<style scoped>
@import "../css/Common.css";

@media (max-width: 749px) { /* 작은 화면 */
  .main-footer {
    height: 90px;
    background-color: #F2EFED;
  }
  .main-footer .contents {
    /* line-height: 90px; */
    text-align: center;
    margin: 0 auto;
  }
}

.main-footer {
  margin-left: 3%;
  margin-right: 3%;
  border-top: solid 0.5px rgb(161, 161, 161);
  width: 94%;
  bottom: 0;
  background: white;
  text-align: center;
  color: black;
}

.col {
  font-size: 0.001rem;
}

.click {
  cursor: pointer;
}

.one {
  padding-top: 1%;
}

.copyright {
  font-family: SpoqaHanSansNeo-Light;
  font-size: xx-small;
}
</style>
