<template>
    <div class="section" id="core-spec" tabindex="-1">
      <div class="core-spec">
        <div class="title">
          <div><h2 style="width: 50%">주요 스펙</h2></div>
          <div style="text-align: right"><button data-bs-toggle="modal" data-bs-target="#productSpecModal">스펙전체보기</button></div>
        </div>

        <div class="content">
          <div class="spec-item"><div><p style="color: #7E7E7E;">가격대</p><p>데이터</p></div></div>
          <div class="spec-item"><div><p>가격대</p><p>데이터</p></div></div>
          <div class="spec-item"><div><p>가격대</p><p>데이터</p></div></div>
          <div class="spec-item"><div><p>가격대</p><p>데이터</p></div></div>
          <div class="spec-item"><div><p>가격대</p><p>데이터</p></div></div>
          <div class="spec-item"><div><p>가격대</p><p>데이터</p></div></div>
          <div class="spec-item"><div><p>가격대</p><p>데이터</p></div></div>
        </div>
      </div>
    </div>

    <hr class="division-line" />

    <div class="section" id="score">
      <div class="score">
        <h2>상품 점수</h2>
        <div class="score-chart">
          <div class="score-group">
            <h3 class="title">가격</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart">
              <div class="circle-middle" />
              <div class="circle-middle" />
              <div class="circle-middle" />
              <div class="circle" />
              <div class="circle" />
            </div>
            <h3 class="content">3점 <span style="color: #C7C7C7;">|</span> 100~120만원</h3>
          </div>
          <div class="score-group">
            <h3 class="title">용량</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart">
              <div class="circle-high" />
              <div class="circle-high" />
              <div class="circle-high" />
              <div class="circle-high" />
              <div class="circle" />
            </div>
            <h3 class="content">4.5점 <span style="color: #C7C7C7;">|</span> 875리터</h3>
          </div>
          <div class="score-group">
            <h3 class="title">가격</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart">
              <div class="circle-middle" />
              <div class="circle-middle" />
              <div class="circle-middle" />
              <div class="circle" />
              <div class="circle" />
            </div>
            <h3 class="content">3점 <span style="color: #C7C7C7;">|</span> 100~120만원</h3>
          </div>
          <div class="score-group">
            <h3 class="title">냉각성능</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart">
              <div class="circle-highest" />
              <div class="circle-highest" />
              <div class="circle-highest" />
              <div class="circle-highest" />
              <div class="circle-highest" />
            </div>
            <h3 class="content">5점 <span style="color: #C7C7C7;">|</span> 다양한편</h3>
          </div>
          <div class="score-group">
            <h3 class="title">가격</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart">
              <div class="circle-middle" />
              <div class="circle-middle" />
              <div class="circle-middle" />
              <div class="circle" />
              <div class="circle" />
            </div>
            <h3 class="content">3점 <span style="color: #C7C7C7;">|</span> 100~120만원</h3>
          </div>
        </div>
      </div>
    </div>

    <!-- 동일한 구조로 카테고리에 맞게 작성하시면 됩니다! -->
    <hr class="division-line" />
    <div class="section" id="detail-spec">
      <div class="spec-info">
        <div class="title">
          <h2>스펙 분석</h2>
        </div>
        <div class="body">
          <!-- 가격 -->
          <div class="price spec">
            <div class="sub-title">
              <div class="circle circle-margin">1</div>
              <h3>가격 | {{ state.priceWord.word }}</h3>
            </div>
            <hr>
            <div class="chart">
              <div class="chart-back">&nbsp;</div>
              <div class="chart-low"
              :class="state.priceWord.class"
              :style="{ width: state.priceWord.width + '%' }"
              >&nbsp;</div>
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
          </div>
          <!-- 용량 -->
          <div class="size spec">
            <div class="sub-title">
              <div class="circle circle-margin">2</div>
              <h3>용량 | {{ state.brewingWord.word }}</h3>
            </div>
            <hr>
            <div class="chart">
              <div class="chart-back">&nbsp;</div>
              <div
                :class="state.brewingWord.class"
                :style="{ width: state.brewingWord.width + '%' }">&nbsp;</div>
              <ul class="chart-desc">
                <li class="desc">매우 부족</li>
                <li class="desc">부족</li>
                <li class="desc">보통</li>
                <li class="desc">우수</li>
                <li class="desc">매우 우수</li>
              </ul>
            </div>
            <div class="content">
              <div class="content-func">
                <h4>용량: 9L</h4>
                <div>스펙 상 용량은 9L이며, 에어프라이어 중에서는 보통 정도로 2~3인이 사용하기에 적합한 제품입니다.</div>
              </div>
            </div>
            <div class="modal-button">
              <button data-bs-toggle="modal" data-bs-target="#exampleModal" @click="clickModal('size')">에어프라이기 용량은 어느정도가 적당할까?🤔</button>
            </div>

            <product-detail-spec-air-fryer-modal id="exampleModal"></product-detail-spec-air-fryer-modal>
          </div>

          <!-- 조리 성능 -->
          <div class="cook spec">
            <div class="sub-title">
              <div class="circle circle-margin">3</div>
              <h3>조리성능 | 좋음</h3>
              <hr>
            </div>
            <div class="chart">
              <div class="chart-back">&nbsp;</div>
              <div class="chart-middle">&nbsp;</div>
            </div>
            <div class="content">
              <div class="content-func">
                <h4>소비전력(W): 1800</h4>
                <div>소비전력이 1800W이며, 온도범위/팬 구조 등을 고려하면 조리속도가 조금 빠를 것으로 예상되는 제품입니다.</div>
              </div>
              <div class="content-func">
                <h4>용량: 9L</h4>
                <div>스펙 상 용량은 9L이며, 에어프라이어 중에서는 보통 정도로 2~3인이 사용하기에 적합한 제품입니다.</div>
              </div>
            </div>
            <div class="modal-button">
              <button data-bs-toggle="modal" data-bs-target="#exampleModal" @click="clickModal('cook')">성능 측정?!</button>
            </div>
            <product-detail-spec-air-fryer-modal desc="cook" id="exampleModal"></product-detail-spec-air-fryer-modal>
          </div>

          <!-- 세척 편의 -->
          <div class="cook spec">
            <div class="sub-title">
              <div class="circle circle-margin">4</div>
              <h3>세척편의 | 매우 편리함</h3>
              <hr>
            </div>
            <div class="chart">
              <div class="chart-back">&nbsp;</div>
              <div class="chart-high">&nbsp;</div>
            </div>
            <div class="content">
              <div class="content-func">
                <h4>분리형 용기</h4>
                <div>바스켓과 내솥(내부팬)이 분리가 가능한 방식으로 깨끗하고 조금 더 간편하게 세척할 수 있습니다.</div>
              </div>
              <div class="content-func">
                <h4>분리형 핸들</h4>
                <div>바스켓과 손잡이가 분리되기 때문에 손잡이는 분리하고 바스켓만 세척할 수 있어 조금 더 편리합니다.</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

</template>

<style scoped>
/* 공통 CSS */
h2 {
  margin: 0px 0px;
  font-family: SpoqaHanSansNeo-Bold;
}
h3 {
  display: inline-block;
}
.title {
  text-align: center;
  margin: 10px 0px 10px 0px;
}
.body {
  width: 70%; /* 변경 가능 */
  margin: 0 auto;
  margin-top: 30px;
}
.spec {
  margin-bottom: 100px;
  width: 100%;
}
.section {
  margin: 0 auto;
  display: flex;
  justify-content: center;
  padding-top: 50px;
}

/* ProductDetail에서 가져온 CSS */
hr.division-line {
  /* width: 1205px; */
  /* width: 85%; */
  /* max-width: 1205px; */
  border: 1px solid #D8D8D8;;
  margin-top: 100px;
}

.core-spec {
  /* display: inline-block; */
  /* position: absolute; */
  /* width: 55%; */
  width: 1048px;
  height: 292px;
  /* left: 437px; */
  /* margin: 0px auto; */
  /* top: 733px; */
  /* background-color: lightgray; */
  text-align: center;
  justify-content: left;
}

.core-spec .title {
  margin: 10px 10px;
  line-height: 15%;
}

.core-spec .title div {
  width: 50%;
  display: inline-block;
  text-align: left;
  vertical-align: middle;
}

.core-spec .title button {
  width: 25%;
  height: 40px;
  border-radius: 15px;
  /* margin-right: 5px; */
  background-color: #FFFFFF;
  border: 0.5px solid #000000;
}

.core-spec .content {
  display: inline-block;
  text-align: left;
  width: 100%;
}

.spec-item {
  display: inline-block;
  width: 18%;
  height: 89px;
  background-color: #fff3f3;
  border-radius: 15px;
  margin: 10px 10px;
  filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.25));
  text-align: center;
  vertical-align: middle;
  justify-content: center;
}

.spec-item div {
  width: 100%;
  vertical-align: middle;
  margin-top: 10%;
  align-items: center;
}

.spec-item p {
  width: 100%;
  margin: 5px auto;
}

.score {
  /* position: absolute; */
  /* width: 40%; */
  width: 646px;
  min-height: 200px;
  /* height: 487px; */
  /* left: 637px; */
  /* top: 1115px; */
  text-align: center;
}

.score-chart {
  margin: 57px auto;
}

.score-group {
  margin: 15px auto;
}

.score-chart h3 {
  display: inline-block;
  margin: 0 10px;
}

.score-chart .score-group h3 {
  display: inline-block;
  margin: 0 10px;
}

h3.title {
  text-align: right;
  width: 15%;
  font-size: 20px;
}

h3.content {
  text-align: left;
  width: 40%;
  font-size: 20px;
}

.score-chart .score-group .chart {
  display: inline-block;
  width: 30%;
}

.score .circle {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #ECECEC;
}

.score .circle-low {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #FF8946;
}

.score .circle-middle {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #FFD480;
}

.score .circle-high {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #9EED9C;
}

.score .circle-highest {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #7BD8FF;
}

h1 {
  font-family: "SpoqaHanSansNeo-Bold";
  margin: 0px 0px;
  display: inline-block;
  width: 80%;
}

h2 {
  font-family: "SpoqaHanSansNeo-Bold";
  margin: 0px 0px;
}

h4 {
  font-family: "SpoqaHanSansNeo-Bold";
  margin: 0px 0px;
}

/* 스펙 타이틀 CSS */
.sub-title {
  margin-bottom: 10px;
}
.spec .circle {
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

/* 막대그래프 CSS */
.spec-info .chart {
  position: relative;
  height: 60px;
  width: 100%;
}
.spec-info .chart > div {
  position: absolute;
  display: block;
  line-height: 22px;
  border-radius: 15px;
  margin: 10px 0;
}
.chart-back {
  background-color: #F5F5F5;
  width: 100%;
}

/* 1 ~ 3점 */
.chart-llow {
  background-color: #FF8946;
  width: 20%; ; /* width 값을 이용하여 그래프 조정 */
}

/* 3 ~ 4점 */
.chart-low {
  background-color: #FFD480;
  width: 50%;
}

/* 4 ~ 5점*/
.chart-middle {
  background-color: #9EED9C;
  width: 70%;
}

/* 5점 */
.chart-high {
  background-color: #7BD8FF;
  width: 90%
}
.chart-desc {
  position: relative;
  display: table;
  width: inherit;
  table-layout: fixed;
}
.chart-desc > desc {
  display: table-cell;
}
.chart-desc > li {
  display: inline-block;
  list-style: none;
  width: 20%;/* 이게 맞는지는 모르겠음 */
  text-align: right;
  padding-top: 40px;
  opacity: 0.5; /* 텍스트 투명도 조절 */
}

/* 성능 상세 설명 CSS */
.content {
  margin-top: 30px;
  line-height: 30px;
}
.content > .content-func {
  margin-bottom: 30px;
}

/* 모달 버튼 CSS */
.modal-button {
  margin: 0 auto;
  width: 100%;
  text-align: center;
}
.modal-button > button {
  display: block;
  min-width: 300px;
  width: 20%;
  height: 50px;
  margin: auto;

  background-color: white;
  border: none ;
  border-radius: 50px;
  filter: drop-shadow(0px 5px 5px rgba(0, 0, 0, 0.2));
  font-size: 16px;
}
</style>

<script>
import ProductDetailSpecAirFryerModal from './ProductDetailSpecAirFryerModal.vue';
import { reactive, computed } from "vue";
import { useStore } from "vuex";

export default {
  name: 'ProductDetailSpecAirFryer',
  components: {
    ProductDetailSpecAirFryerModal,
  },
  setup(){
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
          }
        }
      })
  });

    function clickModal(desc) {
      store.commit("root/setModal", desc)
      console.log("모달 ㄱ밧 :" + store.getters["root/getModal"])
    }

    return { clickModal, state }
  }
}
</script>
