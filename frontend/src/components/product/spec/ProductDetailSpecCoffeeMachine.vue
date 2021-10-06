<template>
  <div class="spec-info">
    <div class="title">
      <h2>스펙 분석</h2>
    </div>
    <div class="body">
      <!-- 가격 관련 분석 -->
      <div class="price spec">
        <div class="sub-title">
          <div class="circle circle-margin">1</div>
          <h3>가격 | {{ state.priceWord.word }}</h3>
        </div>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div
            :class="state.priceWord.class"
            :style="{ width: state.priceWord.width + '%' }"
          >
            &nbsp;
          </div>
          <ul class="chart-desc">
            <li class="desc">매우 비쌈</li>
            <li class="desc">비쌈</li>
            <li class="desc">보통</li>
            <li class="desc">저렴</li>
            <li class="desc">매우 저렴</li>
          </ul>
        </div>
        <div class="content">
          해당 상품의 최저가는 {{ state.productLowPrice }}만원으로
          {{ state.priceWord.content }} 수준입니다.<br />
        </div>
        <div class="modal-button">
          <button
            data-bs-toggle="modal"
            data-bs-target="#productDetailSpecCoffeeMachineModal"
          >
            커피머신 가격 어느 정도일까? 🤔
          </button>
          <product-detail-spec-coffee-machine-modal
            :id="productDetailSpecCoffeeMachineModal"
            :name="priceModal"
          ></product-detail-spec-coffee-machine-modal>
        </div>
      </div>

      <!-- 추출 성능 -->
      <div class="brewing spec">
        <div class="sub-title">
          <div class="circle circle-margin">2</div>
          <h3>추출성능 | {{ state.brewingWord.word }}</h3>
        </div>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div
            :class="state.brewingWord.class"
            :style="{ width: state.brewingWord.width + '%' }"
          >
            &nbsp;
          </div>
          <ul class="chart-desc">
            <li class="desc">매우 부족</li>
            <li class="desc">부족</li>
            <li class="desc">보통</li>
            <li class="desc">우수</li>
            <li class="desc">매우 우수</li>
          </ul>
        </div>
        <div class="content">
          표기되어 있는 청정 면적은 최대 세기 작동 기준입니다.<br />
          팬 소음으로 인해 항상 최대 세기로 작동 시키기 어려우므로 표기된 면적의
          70% 수준 정도 공간에 사용하기에 적합합니다.<br />
          따라서 사용할 공간의 실면적의 1.5배로 선택하는 것을 추천합니다.
        </div>
        <div class="modal-button">
          <button
            data-bs-toggle="modal"
            data-bs-target="#productDetailSpecCoffeeMachineModal"
          >
            펌프압력이란? 🤓
          </button>
          <product-detail-spec-coffee-machine-modal
            :id="productDetailSpecCoffeeMachineModal"
            :name="priceModal"
          ></product-detail-spec-coffee-machine-modal>
        </div>
      </div>

      <!-- 물통 용량 -->
      <div class="water spec">
        <div class="sub-title">
          <div class="circle circle-margin">2</div>
          <h3>물통용량 | {{ state.waterWord.word }}</h3>
        </div>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div
            :class="state.waterWord.class"
            :style="{ width: state.waterWord.width + '%' }"
          >
          &nbsp;
          </div>
          <ul class="chart-desc">
            <li class="desc">매우 작음</li>
            <li class="desc">작음</li>
            <li class="desc">보통</li>
            <li class="desc">큼</li>
            <li class="desc">매우 큼</li>
          </ul>
        </div>
        <div class="content">
          표기되어 있는 청정 면적은 최대 세기 작동 기준입니다.<br />
          팬 소음으로 인해 항상 최대 세기로 작동 시키기 어려우므로 표기된 면적의
          70% 수준 정도 공간에 사용하기에 적합합니다.<br />
          따라서 사용할 공간의 실면적의 1.5배로 선택하는 것을 추천합니다.
        </div>
      </div>

      <!-- 편의기능 성능 분석 -->
      <div class="conference spec">
        <div class="sub-title">
          <div class="circle circle-margin">2</div>
          <h3>사용편의 | {{ state.conferWord.word }}</h3>
        </div>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div
            :class="state.conferWord.class"
            :style="{ width: state.conferWord.width + '%' }"
          >
            &nbsp;
          </div>
          <ul class="chart-desc">
            <li class="desc">매우 부족</li>
            <li class="desc">부족</li>
            <li class="desc">보통</li>
            <li class="desc">다양</li>
            <li class="desc">매우 다양</li>
          </ul>
        </div>
        <div class="content">
          표기되어 있는 청정 면적은 최대 세기 작동 기준입니다.<br />
          팬 소음으로 인해 항상 최대 세기로 작동 시키기 어려우므로 표기된 면적의
          70% 수준 정도 공간에 사용하기에 적합합니다.<br />
          따라서 사용할 공간의 실면적의 1.5배로 선택하는 것을 추천합니다.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductDetailSpecCoffeeMachineModal from "./ProductDetailSpecCoffeeMachineModal.vue";
import { reactive, computed } from "vue";
import { useStore } from "vuex";
// import { useStore } from "vuex";
export default {
  name: "ProductDetailSpecCoffeeMachine",
  components: {
    ProductDetailSpecCoffeeMachineModal,
  },
  props: {
    product: Object,
  },
  setup() {
    const store = useStore();
    const state = reactive({
      product: computed(() => {
        return store.getters["root/getProdctSpec"];
      }),
      productLowPrice: computed(() => {
        // return store.getters["root/getProductLowPrice"][0];
        return "20";
      }),
      priceWord: computed(() => {
        let price = 100;
        if (price < 20) {
          return {
            word: "매우 비쌈",
            content: "커피머신 중 매우 비싼",
            class: "chart-low",
            width: price,
          };
        } else if (price < 40) {
          return {
            word: "비쌈",
            content: "커피머신 중 비싼",
            class: "chart-low",
            width: price,
          };
        } else if (price < 60) {
          return {
            word: "보통",
            content: "보통",
            class: "chart-low",
            width: price,
          };
        } else if (price < 80) {
          return {
            word: "저렴",
            content: "저렴한",
            class: "chart-middle",
            width: price,
          };
        } else if (price < 100) {
          return {
            word: "저렴",
            content: "저렴한",
            class: "chart-high",
            width: price,
          };
        } else {
          return {
            word: "매우 저렴",
            content: "커피머신 중 매우 저렴한😋",
            class: "chart-highest",
            width: price,
          };
        }
      }),
      brewingWord: computed(() => {
        let brewingScore = 20;
        if (brewingScore < 20) {
          return {
            word: "매우 부족",
            content: "커피머신 중 매우 비싼",
            class: "chart-low",
            width: brewingScore,
          };
        } else if (brewingScore < 40) {
          return {
            word: "부족",
            content: "커피머신 중 비싼",
            class: "chart-low",
            width: brewingScore,
          };
        } else if (brewingScore < 60) {
          return {
            word: "보통",
            content: "보통",
            class: "chart-low",
            width: brewingScore,
          };
        } else if (brewingScore < 80) {
          return {
            word: "우수",
            content: "저렴한",
            class: "chart-middle",
            width: brewingScore,
          };
        } else if (brewingScore < 100) {
          return {
            word: "우수",
            content: "저렴한",
            class: "chart-high",
            width: brewingScore,
          };
        } else {
          return {
            word: "매우 우수",
            content: "커피머신 중 매우 저렴한😋",
            class: "chart-highest",
            width: brewingScore,
          };
        }
      }),
      waterWord: computed(() => {
        let waterScore = 60;
        if (waterScore < 20) {
          return {
            word: "매우 작음",
            content: "커피머신 중 매우 비싼",
            class: "chart-low",
            width: waterScore,
          };
        } else if (waterScore < 40) {
          return {
            word: "작음",
            content: "커피머신 중 비싼",
            class: "chart-low",
            width: waterScore,
          };
        } else if (waterScore < 60) {
          return {
            word: "보통",
            content: "보통",
            class: "chart-low",
            width: waterScore,
          };
        } else if (waterScore < 80) {
          return {
            word: "큼",
            content: "저렴한",
            class: "chart-middle",
            width: waterScore,
          };
        } else if (waterScore < 100) {
          return {
            word: "큼",
            content: "저렴한",
            class: "chart-high",
            width: waterScore,
          };
        } else {
          return {
            word: "매우 큼",
            content: "커피머신 중 매우 저렴한😋",
            class: "chart-highest",
            width: waterScore,
          };
        }
      }),
      conferWord: computed(() => {
        let confScore = 90;
        if (confScore < 20) {
          return {
            word: "매우 부족",
            content: "커피머신 중 매우 비싼",
            class: "chart-low",
            width: confScore,
          };
        } else if (confScore < 40) {
          return {
            word: "부족",
            content: "커피머신 중 비싼",
            class: "chart-low",
            width: confScore,
          };
        } else if (confScore < 60) {
          return {
            word: "보통",
            content: "보통",
            class: "chart-low",
            width: confScore,
          };
        } else if (confScore < 80) {
          return {
            word: "다양",
            content: "저렴한",
            class: "chart-middle",
            width: confScore,
          };
        } else if (confScore < 100) {
          return {
            word: "다양",
            content: "저렴한",
            class: "chart-high",
            width: confScore,
          };
        } else {
          return {
            word: "매우 다양",
            content: "커피머신 중 매우 저렴한😋",
            class: "chart-highest",
            width: confScore,
          };
        }
      }),
    });

    return { store, state };
  },
};
</script>

<style scoped>
h2 {
  margin: 0px 0px;
  font-family: "SpoqaHanSansNeo-Bold";
}

h3 {
  display: inline-block;
}
.title {
  text-align: center;
  margin: 10px 0px 10px 0px;
}
.body {
  width: 90%; /* 변경 가능 */
  margin: 0 auto;
  margin-top: 30px;
}
.spec {
  margin-bottom: 100px;
  width: 100%;
}
.sub-title {
  margin-bottom: 10px;
}
.circle {
  display: inline-block;
  text-align: center;
  vertical-align: middle;
  width: 25px;
  height: 25px;
  background-color: black;
  color: white;
  border-radius: 25px;
}
.circle-margin {
  margin-right: 10px;
}
.chart {
  position: relative;
  height: 60px;
  width: 100%;
}
.chart > div {
  position: absolute;
  display: block;
  line-height: 22px;
  border-radius: 15px;
  margin: 10px 0;
}
.chart-back {
  background-color: #f5f5f5;
  width: 100%;
}

/* 1 ~ 3점 */
.chart-low {
  background-color: #ff8946;
  width: 50%; /* width 값을 이용하여 그래프 조정 */
}

/* 3 ~ 4점 */
.chart-middle {
  background-color: #ffd480;
  width: 70%;
}

/* 4 ~ 5점*/
.chart-high {
  background-color: #9eed9c;
  width: 90%;
}

/* 5점 */
.chart-highest {
  background-color: #7bd8ff;
  width: 90%;
}

.chart-desc {
  position: relative;
  display: table;
  table-layout: fixed;
}
.chart-desc > desc {
  display: table-cell;
}
.chart-desc > li {
  display: inline-block;
  list-style: none;
  width: 130px;  /* 이게 맞는지는 모르겠음 */
  text-align: right;
  padding-top: 40px;
  opacity: 0.5; /* 텍스트 투명도 조절 */
}

.content {
  margin-top: 30px;
  line-height: 30px;
}
.modal-button {
  margin: 0 auto;
  width: 100%;
  text-align: center;
}
.modal-button > button {
  outline: none;
  width: 20%;
  min-width: 300px;
  height: 60px;
  background-color: white;
  border: none;
  /* filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.25)); */
  box-shadow: 0px 9px 18px rgba(0, 0, 0, 0.25);
  border-radius: 50px;
  margin-top: 40px;
  font-size: 18px;
}
</style>
