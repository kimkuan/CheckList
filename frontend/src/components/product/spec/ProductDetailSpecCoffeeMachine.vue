<template>
  <div class="section" id="core-spec" tabindex="-1">
    <div class="core-spec">
      <div class="title">
        <div><h2 style="width: 50%">주요 스펙</h2></div>
        <div style="text-align: right"><button data-bs-toggle="modal" data-bs-target="#productSpecModal">스펙전체보기</button></div>
      </div>

      <div class="content">
        <div class="spec-item"><div><p>가격대</p><p>{{ $filters.convertPriceVer2(state.product.price) }}만원</p></div></div>
        <div class="spec-item"><div><p>크기</p><p>{{ state.size == "null" ? "-" : state.size }}</p></div></div>
        <div class="spec-item"><div><p>자동세척</p><p>{{ state.auto == "null" ? "-" : state.auto }}</p></div></div>
        <div class="spec-item"><div><p>무게</p><p>{{state.weight == "null" ? "-" : state.weight}}</p></div></div>
        <div class="spec-item"><div><p>펌프압력</p><p>{{state.product.pressure == "null" ? "-":state.product.pressure}}</p></div></div>
        <div class="spec-item"><div><p>예열시간</p><p>{{ state.product.heatTime == "null" ? "-" : state.product.heatTime}}</p></div></div>
        <div class="spec-item"><div><p>캡슐수거함</p><p>{{ state.case == "null" ? "-" : state.case}}</p></div></div>
        <div class="spec-item"><div><p>물통용량</p><p>{{ state.product.waterVolume == "null" ? "-" : state.product.waterVolume}}</p></div></div>
        <div class="spec-item"><div><p>물통분리</p><p>{{ state.isSeperate == "null" ? "-" : state.isSeperate}}</p></div></div>
        <div class="spec-item"><div><p>우유스팀노즐</p><p>{{ state.milk == "null" ? "-" : state.milk}}</p></div></div>
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
            <div class="circle" :class="state.priceWord.class" v-for="i in Math.ceil(state.priceWord.score)" v-bind:key="i" />
            <div class="circle" v-for="i in 5 - Math.ceil(state.priceWord.score)" v-bind:key="i" />
          </div>
          <h3 class="content">{{state.priceWord.score}}점 <span style="color: #C7C7C7;">|</span> 100~120만원</h3>
        </div>
        <div class="score-group">
          <h3 class="title">추출성능</h3>
          <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
          <div class="chart">
            <div class="circle" :class="state.brewingWord.class" v-for="i in Math.ceil(state.brewingWord.score)" v-bind:key="i" />
            <div class="circle" v-for="i in 5 - Math.ceil(state.brewingWord.score)" v-bind:key="i" />
          </div>
          <h3 class="content"> {{state.brewingWord.score}}점 <span style="color: #C7C7C7;">|</span> {{state.brewingWord.content}}</h3>
        </div>
        <div class="score-group">
          <h3 class="title">물통용량</h3>
          <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
          <div class="chart">
            <div class="circle" :class="state.waterWord.class" v-for="i in Math.ceil(state.waterWord.score)" v-bind:key="i" />
            <div class="circle" v-for="i in 5 - Math.ceil(state.waterWord.score)" v-bind:key="i" />
          </div>
          <h3 class="content">{{state.waterWord.score}}점<span style="color: #C7C7C7;">|</span> {{getVolumeWord()}}</h3>
        </div>
        <div class="score-group">
          <h3 class="title">사용편의</h3>
          <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
          <div class="chart">
            <div class="circle" :class="state.conferWord.class" v-for="i in Math.ceil(state.conferWord.score)" v-bind:key="i" />
            <div class="circle" v-for="i in 5 - Math.ceil(state.conferWord.score)" v-bind:key="i" />
          </div>
          <h3 class="content"> {{state.conferWord.score}}점<span style="color: #C7C7C7;">|</span> {{ state.conferWord.content}}</h3>
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
            해당 상품의 최저가는 {{ $filters.convertPriceVer2(state.product.price) }}만원으로
            {{ state.priceWord.content }} 수준입니다.<br />
          </div>
          <div class="modal-button">
            <button
              data-bs-toggle="modal"
              data-bs-target="#exampleModal"
              @click="clickModal('price')"
            >
              커피머신 가격 어느 정도일까? 🤔
            </button>
            <product-detail-spec-coffee-machine-modal
              desc="price" id="exampleModal"
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
            <div class="content-func">
              <div class="content-title">
                <h4><b>펌프압력</b></h4>
              </div>
              <div class="content-article" v-if="state.product.pressure != (undefined && 'null')">
                펌프 압력이 {{state.product.pressure}}로 캡슐 커피머신 중에서
                {{
                  state.product.pressure == "8bar" ||
                  state.product.pressure == "3.5bar"
                    ? "낮은"
                    : state.product.pressure == "19bar" ||
                      state.product.pressure == "20bar" ||
                      state.product.pressure == "21bar"
                    ? "높은"
                    : "보통인"
                }} 편입니다.
              </div>
              <div class="contetn-article" v-else>
                정보가 없습니다 😪
              </div>
            </div>
            <div class="content-func">
              <div class="content-title">
                <h4><b>예열시간</b></h4>
              </div>
              <div class="content-article" v-if="state.product.heatTime != 'null'">
                예열 시간이 {{state.product.heatTime}}정도로 캡슐 커피머신 중에서
                {{
                  state.product.heatTime == "3초"
                    ? "매우 빠른"
                    : state.product.heatTime.startsWith("15")
                    ? "빠른"
                    : state.product.heatTime.startsWith("25") ||
                      state.product.heatTime.startsWith("30") ||
                      state.product.heatTime.startsWith("40")
                    ? "보통인"
                    : "느린"
                }} 제품입니다. 캡슐 커피머신의 경우 보통 25 ~ 40초 정도 걸립니다.
              </div>
              <div class="contetn-article" v-else>
                정보가 없습니다 😪
              </div>
            </div>
            <!-- 추출기능 관련 스펙  -->
            <div class="content-func" v-for="(item, index) in state.brewingList" v-bind:key="index">
              <h4>{{ item.key }}</h4>
              <div>{{ answerOfSpec(item.key, item.value) }}</div>
            </div>
          </div>
          <div class="modal-button">
            <button
              data-bs-toggle="modal"
              data-bs-target="#exampleModal"
              @click="clickModal('pressure')"
            >
              펌프압력이란? 🤓
            </button>
            <product-detail-spec-coffee-machine-modal
              desc="pressure" id="exampleModal"
            ></product-detail-spec-coffee-machine-modal>
          </div>
        </div>

        <!-- 물통 용량 -->
        <div class="water spec">
          <div class="sub-title">
            <div class="circle circle-margin">3</div>
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
            <div class="content-title">
              <h4><b>물통용량 : {{ state.product.waterVolume }}</b></h4>
              <div class="content-article" v-if="state.product.waterVolume != 'null'">
                물통 용량이 {{state.product.waterVolume}}로 물 보충 없이 한 번에 에스프레소 약
                {{calculateWater()}}잔 정도를 추출할 수 있습니다. <br>
                <br>
                <br>
                🌞 추출량 조절로 차이가 있을 수 있지만, 기본 세팅 값 40ml를 기준으로 계산했습니다 📊
              </div>
              <div class="contetn-article" v-else>
                정보가 없습니다 😪
              </div>
            </div>
          </div>
        </div>

        <!-- 편의기능 성능 분석 -->
        <div class="conference spec">
          <div class="sub-title">
            <div class="circle circle-margin">4</div>
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
            <!-- 부가기능 관련 스펙  -->
            <div class="content-func" v-for="(item, index) in state.convenienceList" v-bind:key="index">
              <h4>{{ item.key }}</h4>
              <div>{{ answerOfSpec(item.key, item.value) }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductDetailSpecCoffeeMachineModal from "./ProductDetailSpecCoffeeMachineModal.vue";
import { reactive, computed } from "vue";
import { useStore } from "vuex";

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
        return store.getters["root/getProductInfo"];
      }),
      brewingList: [],  // 추출성능 관련
      convenienceList: [], // 편의기능 관련
      size: "null",     // '크기'
      auto: "null",   // 자동세척
      weight: "null",    // 무게
      case: "null",     // 캡슐수거('저장개수' -> 값 그대로 & "캡슐수거함" -> "자동저장" // 캡슐수거함 나올 때 null이면 저장 null 아니면 안저장)
      isSeperate: "null", // 물통분리여부 ('분리세척')
      milk: "null",   // ('우유' 뭐시기)
      pressure:"null",
      heatTime: "null",

      priceWord : { word : "", content : "", class : "", score : 0, width : 0},
      brewingWord : { word : "", content : "", class : "", score : 0, width : 0},
      volumeWord : { word : "", content : "", class : "", score : 0, width : 0},
      waterWord: { word : "", content : "", class : "", score : 0, width : 0},
      conferWord: { word : "", content : "", class : "", score : 0, width : 0},

      priceWord: computed(() => {
        let price = store.getters["root/getProductInfo"].pricePoint;
        if (price < 20) {
          return {
            word: "매우 비쌈",
            content: "커피머신 중 매우 비싼",
            class: "chart-low",
            width: price,
            score: Math.ceil(price / 20),
          };
        } else if (price <= 40) {
          return {
            word: "비쌈",
            content: "커피머신 중 비싼",
            class: "chart-low",
            width: price,
            score: Math.ceil(price / 20),
          };
        } else if (price <= 60) {
          return {
            word: "보통",
            content: "보통",
            class: "chart-low",
            width: price,
            score: Math.ceil(price / 20),
          };
        } else if (price <= 80) {
          return {
            word: "저렴",
            content: "저렴한",
            class: "chart-middle",
            width: price,
            score: Math.ceil(price / 20),
          };
        } else if (price < 100) {
          return {
            word: "저렴",
            content: "저렴한",
            class: "chart-high",
            width: price,
            score: Math.ceil(price / 20),
          };
        } else {
          return {
            word: "매우 저렴",
            content: "커피머신 중 매우 저렴한😋",
            class: "chart-high",
            width: price,
            score: Math.ceil(price / 20),
          };
        }
      }),
      brewingWord: computed(() => {
        // content 바꾸기
        let brewingScore = store.getters["root/getProductInfo"].brewingPoint;
        if (brewingScore < 20) {
          return {
            word: "매우 부족",
            content: "매우 부족한편",
            class: "chart-low",
            width: brewingScore,
            score: Math.ceil(brewingScore/20),
          };
        } else if (brewingScore <= 40) {
          return {
            word: "부족",
            content: "부족한편",
            class: "chart-low",
            width: brewingScore,
            score: Math.ceil(brewingScore/20),
          };
        } else if (brewingScore <= 60) {
          return {
            word: "보통",
            content: "보통인편",
            class: "chart-low",
            width: brewingScore,
            score: Math.ceil(brewingScore/20),
          };
        } else if (brewingScore <= 80) {
          return {
            word: "우수",
            content: "우수한편",
            class: "chart-middle",
            width: brewingScore,
            score: Math.ceil(brewingScore/20),
          };
        } else if (brewingScore < 100) {
          return {
            word: "우수",
            content: "우수한편",
            class: "chart-high",
            width: brewingScore,
            score: Math.ceil(brewingScore/20),
          };
        } else {
          return {
            word: "매우 우수",
            content: "매우 우수한편",
            class: "chart-high",
            width: brewingScore,
            score: Math.ceil(brewingScore/20),
          };
        }
      }),
      waterWord: computed(() => {
        let waterScore = store.getters["root/getProductInfo"].waterVolumePoint;
        if (waterScore < 20) {
          return {
            word: "매우 작음",
            content: "커피머신 중 매우 비싼",
            class: "chart-low",
            width: waterScore,
          };
        } else if (waterScore <= 40) {
          return {
            word: "작음",
            content: "커피머신 중 비싼",
            class: "chart-low",
            width: waterScore,
            score: Math.ceil(waterScore/20),
          };
        } else if (waterScore <= 60) {
          return {
            word: "보통",
            content: "보통",
            class: "chart-low",
            width: waterScore,
            score: Math.ceil(waterScore/20),
          };
        } else if (waterScore <= 80) {
          return {
            word: "큼",
            content: "저렴한",
            class: "chart-middle",
            width: waterScore,
            score: Math.ceil(waterScore / 20),
          };
        } else if (waterScore < 100) {
          return {
            word: "큼",
            content: "저렴한",
            class: "chart-high",
            width: waterScore,
            score: Math.ceil(waterScore/20),
          };
        } else {
          return {
            word: "매우 큼",
            content: "커피머신 중 매우 저렴한😋",
            class: "chart-high",
            width: waterScore,
            score: Math.ceil(waterScore/20),
          };
        }
      }),
      conferWord: computed(() => {
        let confScore = store.getters["root/getProductInfo"].conveniencePoint;
        if (confScore < 20) {
          return {
            word: "매우 부족",
            content: "매우 부족한편",
            class: "chart-low",
            width: confScore,
            score: Math.ceil(confScore/20),
          };
        } else if (confScore <= 40) {
          return {
            word: "부족",
            content: "부족한편",
            class: "chart-low",
            width: confScore,
            score: Math.ceil(confScore/20),
          };
        } else if (confScore <= 60) {
          return {
            word: "보통",
            content: "보통인편",
            class: "chart-low",
            width: confScore,
            score: Math.ceil(confScore/20),
          };
        } else if (confScore <= 80) {
          return {
            word: "다양",
            content: "다양한편",
            class: "chart-middle",
            width: confScore,
            score: Math.ceil(confScore/20),
          };
        } else if (confScore < 100) {
          return {
            word: "다양",
            content: "다양한편",
            class: "chart-high",
            width: confScore,
            score: Math.ceil(confScore/20),
          };
        } else {
          return {
            word: "매우 다양",
            content: "매우 다양한편",
            class: "chart-high",
            width: confScore,
            score: Math.ceil(confScore/20),
          };
        }
      }),
    });


    // 모달 관련
    const clickModal = function(desc) {
      store.commit("root/setModal", desc);
    };

    const getVolumeWord = function() {
      if(state.product.waterVolume == "null") return "정보없음";
      else return state.product.waterVolume;
    };

    const calculateWater = function() {
      var str = state.product.waterVolume.slice(0, -1);
      var ml = str * 1000;
      return Math.floor(ml / 40);
    };

    const answerOfSpec = function(k, value) {
      var key = String(k)

      if(key.startsWith("자동전원") || key.includes("에너지절약"))
        return "미사용 시 자동으로 전원이 꺼지는 기능이 있어 에너지 절약에 도움을 줄 수 있습니다 ⚡"
      else if(key.includes("우유"))
        return "우유 노즐이 있어 간편하게 우유가 들어간 라떼를 만들 수 있습니다 ☕"
      else if(key.includes("추출량"))
        return "추출량 조절이 가능한 제품입니다"
      else if(key.includes("물없음"))
        return "물이 없음을 알려주는 스마트한 제품입니다 🧐"
      else if(key.includes("자동세척"))
        return "자동을 세척하여 관리에 용이한 제품입니다. 귀차니즘이라면 사보세요 😊"
      else if(key.startsWith("저장개수") || key.startsWith("캡슐수거함"))
        return "자동으로 캡슐을 수거해줍니다. 한 번에 저장할 수 있는 개수가 정해져있으니 유의하세요 ❗"
      else if(key.includes("분리세척"))
        return "물통을 분리해서 편하게 씻을 수 있어요. 좀 더 깔끔함을 즐겨보세요 🧼"
      else return key + " 기능을 갖춘 제품입니다"
    }

    const specList = JSON.parse(state.product.spec);
    for (let key in specList) {
      for (let subKey in specList[key]) {
        let json = new Object();
        json.key = subKey;
        json.value = specList[key][subKey];

        if (subKey.startsWith("추출량") || subKey.startsWith("우유거품"))
          state.brewingList.push(json)
        else if(subKey.startsWith("크기") || subKey.startsWith("무게"))
          console.log("필요없지롱")
        else state.convenienceList.push(json);

        if (subKey.startsWith("크기"))
          state.size = specList[key][subKey];
        else if (subKey.startsWith("자동세척"))
          state.auto = specList[key][subKey];
        else if (subKey.startsWith("무게"))
          state.weight = specList[key][subKey];
        else if (subKey.startsWith("저장개수") || (subKey.startsWith("캡슐수거함") && state.case == "null"))
          state.case = specList[key][subKey];
        else if (subKey.startsWith("분리세척"))
          state.isSeperate = specList[key][subKey];
        else if (subKey.startsWith("우유") || subKey.startsWith("밀크"))
          state.milk = "O";
      }
    }

    return { store, state, clickModal, getVolumeWord, calculateWater, answerOfSpec };
  },
};
</script>

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
  background-color: #FF8946 !important;
  width: 20%; ; /* width 값을 이용하여 그래프 조정 */
}

/* 3 ~ 4점 */
.chart-low {
  background-color: #FFD480 !important;
  width: 50%;
}

/* 4 ~ 5점*/
.chart-middle {
  background-color: #9EED9C !important;
  width: 70%;
}

/* 5점 */
.chart-high {
  background-color: #7BD8FF !important;
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

/* 주저리 */
.content-article {
  line-height: 30px;
}
/* 모달 버튼 CSS */
.modal-button {
  margin: 0 auto;
  width: 100%;
  padding : 20px;
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
  font-size: 14px;
}
</style>
