<template>
    <div class="section" id="core-spec" tabindex="-1">
      <div class="core-spec">
        <div class="title">
          <div><h2 style="width: 50%">주요 스펙</h2></div>
          <div style="text-align: right"><button data-bs-toggle="modal" data-bs-target="#productSpecModal">스펙전체보기</button></div>
        </div>

        <div class="content">
          <!-- 주요 스펙 중 null인 값은 '-' 처리 -->
          <div class="spec-item"><div><p>가격대</p><p>{{ $filters.convertPriceVer2(productInfo.price) }}만원</p></div></div>
          <div class="spec-item"><div><p>형태</p><p>{{ isNull(productInfo.type) }}</p></div></div>
          <div class="spec-item"><div><p>용량</p><p>{{ isNull(productInfo.volume) }}L</p></div></div>
          <div class="spec-item"><div><p>소비전력(W)</p><p>{{ isNull(productInfo.power) }}</p></div></div>
          <div class="spec-item"><div><p>조작방식</p><p>{{ isNull(state.control) }}</p></div></div>
          <div class="spec-item"><div><p>시간조절</p><p>{{ isNull(state.timeControl) }}</p></div></div>
          <div class="spec-item"><div><p>온도조절</p><p>{{ isNull(state.temperatureControl) }}</p></div></div>
          <div class="spec-item"><div><p>내부투시창 </p><p>{{ isNull(state.showInside) }}</p></div></div>
          <div class="spec-item"><div><p>디지털디스플레이</p><p>{{ isNull(state.digitalDisplay) }}</p></div></div>

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
            <!-- 점수에 따른 circle 개수/색상 다르게 출력 -->
            <div class="chart">
              <div class="circle" :class="state.priceWord.class" v-for="i in Math.ceil(state.priceWord.score)" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - Math.ceil(state.priceWord.score)" v-bind:key="i" />
            </div>
            <h3 class="content">{{ state.priceWord.score }}점 <span style="color: #C7C7C7;">|</span> {{ $filters.convertPriceVer2(productInfo.price) }}만원</h3>
          </div>
          <div class="score-group">
            <h3 class="title">용량</h3>
            <!-- 점수에 따른 circle 개수/색상 다르게 출력 -->
            <div class="chart">
              <div class="circle" :class="state.volumeWord.class" v-for="i in Math.ceil(state.volumeWord.score)" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - Math.ceil(state.volumeWord.score)" v-bind:key="i" />
            </div>
            <h3 class="content">{{ state.volumeWord.score }}점 <span style="color: #C7C7C7;">|</span> {{ productInfo.volume == "null" ? "-" :  productInfo.volume }}L</h3>
          </div>
          <div class="score-group">
            <h3 class="title">조리성능</h3>
            <!-- 점수에 따른 circle 개수/색상 다르게 출력 -->
            <div class="chart">
              <div class="circle" :class="state.cookWord.class" v-for="i in Math.ceil(state.cookWord.score)" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - Math.ceil(state.cookWord.score)" v-bind:key="i" />
            </div>
            <h3 class="content">{{ state.cookWord.score }}점 <span style="color: #C7C7C7;">|</span> {{ state.cookWord.word }}</h3>
          </div>
          <div class="score-group">
            <h3 class="title">세척편의</h3>
            <!-- 점수에 따른 circle 개수/색상 다르게 출력 -->
            <div class="chart">
              <div class="circle" :class="state.managementWord.class" v-for="i in Math.ceil(state.managementWord.score)" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - Math.ceil(state.managementWord.score)" v-bind:key="i" />
            </div>
            <h3 class="content">{{ state.managementWord.score }}점 <span style="color: #C7C7C7;">|</span> {{ state.managementWord.word }}</h3>
          </div>
          <div class="score-group">
            <h3 class="title">편의기능</h3>
            <!-- 점수에 따른 circle 개수/색상 다르게 출력 -->
            <div class="chart">
              <div class="circle" :class="state.convenienceWord.class" v-for="i in Math.ceil(state.convenienceWord.score)" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - Math.ceil(state.convenienceWord.score)" v-bind:key="i" />
            </div>
            <h3 class="content">{{ state.convenienceWord.score }}점 <span style="color: #C7C7C7;">|</span> {{ state.convenienceWord.word }} </h3>
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
               해당 상품의 최저가는 {{ $filters.convertPrice(productInfo.price) }}원으로
               {{ state.priceWord.content }} 수준입니다.<br />
            </div>
          </div>
          <!-- 용량 -->
          <div class="size spec">
            <div class="sub-title">
              <div class="circle circle-margin">2</div>
              <h3>용량 | {{ state.volumeWord.word }}</h3>
            </div>
            <hr>
            <div class="chart">
              <div class="chart-back">&nbsp;</div>
              <div
                :class="state.volumeWord.class"
                :style="{ width: state.volumeWord.width + '%' }">&nbsp;</div>
              <ul class="chart-desc">
                <li class="desc">매우 부족</li>
                <li class="desc">부족</li>
                <li class="desc">보통</li>
                <li class="desc">우수</li>
                <li class="desc">매우 우수</li>
              </ul>
            </div>
            <div class="content">
              <!-- 용량 정보의 존재여부에 따른 설명란 -->
              <div class="content-func">
                <h4>용량: {{ productInfo.volume == "null" ? "-" : productInfo.volume}}L</h4>
                <div v-if="productInfo.volume != 'null'">
                  스펙 상 용량은 {{ productInfo.volume }}L이며, 에어프라이어 중에서는 {{ state.volumeWord.word }} 정도로 {{ state.volumeWord.content }}이 사용하기에 적합한 제품입니다.
                </div>
                <div v-else>
                  정보가 없습니다.
                </div>
              </div>
            </div>
            <div class="modal-button">
              <button data-bs-toggle="modal" data-bs-target="#exampleModal" @click="clickModal('volume')">에어프라이기 용량은 어느정도가 적당할까? 🤔</button>
            </div>

            <product-detail-spec-air-fryer-modal id="exampleModal"></product-detail-spec-air-fryer-modal>
          </div>

          <!-- 조리 성능 -->
          <div class="cook spec">
            <div class="sub-title">
              <div class="circle circle-margin">3</div>
              <h3>조리성능 | {{ state.cookWord.word }}</h3>
              <hr>
            </div>
            <div class="chart">
              <div class="chart-back">&nbsp;</div>
              <div
                :class="state.cookWord.class"
                :style="{ width: state.cookWord.width + '%' }">&nbsp;</div>
               <ul class="chart-desc">
                <li class="desc">매우 부족</li>
                <li class="desc">부족</li>
                <li class="desc">보통</li>
                <li class="desc">우수</li>
                <li class="desc">매우 우수</li>
              </ul>
            </div>
            <div class="content">
              <!-- 소비전력 정보의 존재여부에 따른 설명란 -->
              <div class="content-func">
                <h4>소비전력(W): {{ productInfo.power == "null" ? "-" : productInfo.power}}</h4>
                <div v-if='productInfo.power != "null" '>소비전력이 {{ productInfo.power }}W이며, 온도범위/팬 구조 등을 고려하면 조리속도가 {{ state.cookWord.content }}으로 예상되는 제품입니다.</div>
                <div v-else>정보가 없습니다.</div>
              </div>
            </div>
            <div class="modal-button">
              <button data-bs-toggle="modal" data-bs-target="#exampleModal" @click="clickModal('cook')">소비전력과 성능 ⛏</button>
            </div>
            <product-detail-spec-air-fryer-modal desc="cook" id="exampleModal"></product-detail-spec-air-fryer-modal>
          </div>

          <!-- 세척 편의 -->
          <div class="cook spec">
            <div class="sub-title">
              <div class="circle circle-margin">4</div>
              <h3>세척편의 | {{ state.managementWord.word }}</h3>
              <hr>
            </div>
            <div class="chart">
              <div class="chart-back">&nbsp;</div>
              <div
                :class="state.managementWord.class"
                :style="{ width: state.managementWord.width + '%' }">&nbsp;</div>
               <ul class="chart-desc">
                <li class="desc">매우 불편</li>
                <li class="desc">불편</li>
                <li class="desc">보통</li>
                <li class="desc">편함</li>
                <li class="desc">매우 편함</li>
              </ul>
            </div>
            <div class="content">
              <!-- 조절방식 정보의 존재여부에 따른 설명란 -->
              <div class="content-func">
                <h4>{{ productInfo.type == "null" ? "-" : productInfo.type }}</h4>
                <div v-if="productInfo.type != 'null'">{{ answerOfType(productInfo.type) }}</div>
                <div v-else>정보가 없습니다.</div>
              </div>
              <!-- 세척편의 관련 스펙  -->
              <div class="content-func" v-for="(item, index) in state.managementList" v-bind:key="index">
                <h4>{{ item.key }}</h4>
                <div>{{ answerOfSpec(item.key, item.value) }}</div>
              </div>
            </div>
          </div>

           <!-- 편의 기능 -->
           <div class="cook spec">
            <div class="sub-title">
              <div class="circle circle-margin">5</div>
              <h3>편의기능 | {{ state.convenienceWord.word }} </h3>
              <hr>
            </div>
            <div class="chart">
              <div class="chart-back">&nbsp;</div>
              <div
                :class="state.convenienceWord.class"
                :style="{ width: state.convenienceWord.width + '%' }">&nbsp;</div>
               <ul class="chart-desc">
                <li class="desc">매우 불편</li>
                <li class="desc">불편</li>
                <li class="desc">보통</li>
                <li class="desc">편함</li>
                <li class="desc">매우 편함</li>
              </ul>
            </div>
            <div class="content">
              <!-- 편의기능 관련 스펙  -->
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

/* 1 ~ 2점 */
.chart-low {
  background-color: #FF8946 !important;
}

/* 2 ~ 4점 */
.chart-middle {
  background-color: #FFD480 !important;
}

/* 4 ~ 5점*/
.chart-high {
  background-color: #9EED9C !important;
}

/* 5점 */
.chart-highest {
  background-color: #7BD8FF !important;
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
  font-size: 14px;
}
</style>

<script>
import ProductDetailSpecAirFryerModal from './ProductDetailSpecAirFryerModal.vue';
import { reactive, computed, onMounted } from "vue";
import { useStore } from "vuex";

export default {
  name: 'ProductDetailSpecAirFryer',
  components: {
    ProductDetailSpecAirFryerModal,
  },
  props: {
    productInfo : Object,
  },
  setup(props){
    const store = useStore();
    const state = reactive({
      productInfo : computed(() => {
        return props.productInfo
      }),

      managementList: [], // 스펙 속 세척편의
      convenienceList: [], // 스펙 속 편의기능

      control: "null",
      temperatureControl: "null",
      timeControl: "null",
      showInside: "null",
      digitalDisplay: "null",

      priceWord : { word : "", content : "", class : "", score : 0, width : 0},
      volumeWord : { word : "", content : "", class : "", score : 0, width : 0},
      convenienceWord: { word : "", content : "", class : "", score : 0, width : 0},
      managementWord: { word : "", content : "", class : "", score : 0, width : 0},
      cookWord: { word : "", content : "", class : "", score : 0, width : 0},

      // 가격
      priceWord: computed(() => {
        let pricePoint = props.productInfo.pricePoint;
        if (pricePoint < 20) {
          return {
            word: "매우 비쌈",
            content: "에어프라이기 중 매우 비싼",
            class: "chart-low",
            score: Math.ceil(pricePoint* 10/20) / 10,
            width: 20,
          };
        } else if (pricePoint < 40) {
          return {
            word: "비쌈",
            content: "에어프라이기 중 비싼",
            class: "chart-low",
            score: Math.ceil(pricePoint* 10/20) / 10,
            width: pricePoint,
          };
        } else if (pricePoint < 60) {
          return {
            word: "보통",
            content: "보통",
            class: "chart-middle",
            score: Math.ceil(pricePoint* 10/20) / 10,
            width: pricePoint,
          };
        } else if (pricePoint < 80) {
          return {
            word: "저렴",
            content: "저렴한",
            class: "chart-middle",
            score: Math.ceil(pricePoint* 10/20) / 10,
            width: pricePoint,
          };
        } else if (pricePoint < 100) {
          return {
            word: "매우 저렴",
            content: "매우 저렴한",
            class: "chart-high",
            score: Math.ceil(pricePoint* 10/20) / 10,
            width: pricePoint,
          };
        } else if (pricePoint == 100){
          return {
            word: "매우 저렴",
            content: "에어프라이기 중 매우 저렴한😋",
            class: "chart-highest",
            score: Math.ceil(pricePoint* 10/20) / 10,
            width: pricePoint,
          };
        }
      }),
      // 용량
      volumeWord: computed (() => {
        let volume = props.productInfo.volume;
        let volumeScore = props.productInfo.volumePoint;
        if (volume < 2) {
          return {
            word: "매우 부족",
            content: "1인",
            class: "chart-low",
            score: Math.ceil(volumeScore* 10/20) / 10,
            width: 20,
          };
        } else if (volume < 5) {
          return {
            word: "부족",
            content: "1~2인",
            class: "chart-low",
            score: Math.ceil(volumeScore* 10/20) / 10,
            width: volumeScore,
          };
        } else if (volume < 7) {
          return {
            word: "보통",
            content: "2~3인",
            class: "chart-middle",
            score: Math.ceil(volumeScore* 10/20) / 10,
            width: volumeScore,
          };
        } else if (volume < 20) {
          return {
            word: "우수",
            content: "3~4인",
            class: "chart-middle",
            score: Math.ceil(volumeScore* 10/20) / 10,
            width: volumeScore,
          };
        } else {
          return {
            word: "우수",
            content: "4인 이상",
            class: "chart-high",
            score: Math.ceil(volumeScore* 10/20) / 10,
            width: volumeScore,
          };
        }
      }),
      // 조리성능
      cookWord: computed (() => {
        let cookScore = props.productInfo.cookPoint;
        console.log(cookScore)

        if (cookScore < 20) {
          return {
            word: "매우 미흡",
            content: "느릴 것",
            class: "chart-low",
            score: Math.ceil(cookScore* 10/20) / 10,
            width: cookScore,
          };
        } else if (cookScore < 40) {
          return {
            word: "미흡",
            content: "조금 느릴 것",
            class: "chart-low",
            score: Math.ceil(cookScore* 10/20) / 10,
            width: cookScore,
          };
        } else if (cookScore < 60) {
          return {
            word: "보통",
            content: "보통일 것",
            class: "chart-middle",
            score: Math.ceil(cookScore* 10/20) / 10,
            width: cookScore,
          };
        } else if (cookScore < 80) {
          return {
            word: "좋음",
            content: "조금 빠를 것",
            class: "chart-middle",
            score: Math.ceil(cookScore* 10/20) / 10,
            width: cookScore,
          };
        } else if (cookScore < 100) {
          return {
            word: "매우 좋음",
            content: "빠를 것",
            class: "chart-high",
            score: Math.ceil(cookScore* 10/20) / 10,
            width: cookScore,
          };
        } else if(cookScore == 100){
           return {
            word: "매우 좋음",
            content: "빠를 것",
            class: "chart-highest",
            score: Math.ceil(cookScore* 10/20) / 10,
            width: 100,
          };
        }
      }),
      // 세척편의
      managementWord: computed(() => {
        let managementScore = props.productInfo.managementPoint;

        if (managementScore < 20) {
          return {
            word: "매우 불편",
            content: "불편한 편",
            class: "chart-low",
            score: Math.ceil(managementScore* 10/20) / 10,
            width: managementScore,
          }
        } else if (managementScore < 40) {
          return {
            word: "불편",
            content: "조금 불편한 편",
            class: "chart-low",
            score: Math.ceil(managementScore* 10/20) / 10,
            width: managementScore,
          }
        } else if (managementScore < 60) {
          return {
            word: "보통",
            content: "보통인 편",
            class: "chart-middle",
            score: Math.ceil(managementScore* 10/20) / 10,
            width: managementScore,
          }
        } else if (managementScore < 80) {
          return {
            word: "편함",
            content: "편한 편",
            class: "chart-middle",
            score: Math.ceil(managementScore* 10/20) / 10,
            width: managementScore,
          }
        } else if (managementScore < 100) {
           return {
            word: "매우 편함",
            content: "매우 편한 편",
            class: "chart-high",
            score: Math.ceil(managementScore* 10/20) / 10,
            width: managementScore,
          }
        } else if(managementScore == 100){
           return {
            word: "매우 편함",
            content: "매우 편한 편",
            class: "chart-highest",
            score: Math.ceil(managementScore* 10/20) / 10,
            width: managementScore,
          };
        }
      }),
      // 편의기능
      convenienceWord: computed(() => {
        let controlScore = props.productInfo.controlPoint;
        let convenienceScore = props.productInfo.conveniencePoint;
        let totalScore = (controlScore + convenienceScore) / 2;

        if (totalScore < 20) {
          return {
            word: "매우 불편",
            content: "불편한 편",
            class: "chart-low",
            score: Math.ceil(totalScore* 10/20) / 10,
            width: totalScore,
          }
        } else if (totalScore < 40) {
          return {
            word: "불편",
            content: "조금 불편한 편",
            class: "chart-low",
            score: Math.ceil(totalScore* 10/20) / 10,
            width: totalScore,
          }
        } else if (totalScore < 60) {
          return {
            word: "보통",
            content: "보통인 편",
            class: "chart-middle",
            score: Math.ceil(totalScore* 10/20) / 10,
            width: totalScore,
          }
        } else if (totalScore < 80) {
          return {
            word: "편함",
            content: "편한 편",
            class: "chart-middle",
            score: Math.ceil(totalScore* 10/20) / 10,
            width: totalScore,
          }
        } else if (totalScore < 100) {
           return {
            word: "매우 편함",
            content: "매우 편한 편",
            class: "chart-high",
            score: Math.ceil(totalScore* 10/20) / 10,
            width: totalScore,
          }
        } else if(totalScore == 100){
           return {
            word: "매우 편함",
            content: "매우 편한 편",
            class: "chart-highest",
            score: Math.ceil(totalScore* 10/20) / 10,
            width: totalScore,
          };
       }
      })
   });

    // 모달 버튼 클릭 시, 추가 설명 모달 OPEN
    function clickModal(desc) {
      store.commit("root/setModal", desc)
    }

    // 형태에 따른 설명 출력
    function answerOfType(type) {
      if(type == "바스켓형")
        return "바스켓과 본체로 부터 분리가 가능한 방식으로 깨끗하고 조금 더 간편하게 세척할 수 있습니다"
      else if(type == "오븐형")
        return "오븐형 제품으로 바스켓형과 비교하면 세척이 번거로울 수 있습니다. 바닥, 벽면, 도어 등에 튄 기름이나 소스를 물세척을 할 수 없고, 행주나 타월 등으로 닦아주는 정도로만 세척할 수 있습니다."
    }

    // 스펙에 따른 설명 출력
    function answerOfSpec(key2, value) {
      var key = String(key2)

      if(key.startsWith("터치") || key.startsWith("버튼") || key.startsWith("다이얼"))
        return "조작방식은 크게 다이얼/터치/버튼식으로 나뉘는데, 일반적으로 정확한 온도/시간 설정을 할 수 있는 터치/버튼 방식의 선호가 높은 편입니다."

      else if(key.startsWith("디지털디스플레이"))
        return "LED 디스플레이 창이 있어 시간과 온도 등의 작동상태를 확인할 수 있어 편리합니다."

      else if(key.startsWith("시간조절"))
        return value + " 중 원하는 시간으로 설정할 수 있습니다."

      else if(key.startsWith("온도조절"))
        return value + " 중 원하는 온도로 설정할 수 있습니다."

      else if(key.startsWith("분리형용기"))
        return "바스켓과 내솥(내부팬)이 분리가 가능한 방식으로 깨끗하고 조금 더 간편하게 세척할 수 있습니다."

      else if(key.startsWith("분리형거름망"))
        return "내부용기 대신 거름망을 사용하여 세척의 부담을 줄인 방식입니다. 하지만 조리시 외부 용기의 내구성이 저하된다는 단점이 있습니다."

      else if(key.startsWith("자동메뉴"))
        return value + " 자동메뉴가 세팅되어 있습니다. 재료에 따라 온도와 시간이 설정되어 있어 사용자에 따라 편리하게 사용할 수 있습니다."

      else if(key.startsWith("내부조명") || key.startsWith("내부램프"))
        return "내부에 조명이 있어서 조리상태를 조금 더 쉽게 확인할 수 있어 편리합니다."

      else if(key.includes("건조"))
        return "온도를 낮추어 장시간 작동할 수 있는 기능으로 과일이나 채소 등을 건조할 때에 식품건조기 처럼 사용할 수 있습니다. 다만, 소비전력이 높기 때문에 장시간 사용한다면 전기요금이 많이 발생할 수 있습니다."

      else if(key.startsWith("로티세리"))
        return "로티세리 기능이 포함되어 있어 꼬치에 육류, 채소 등을 끼워서 작동하면 꼬치가 회전하면서 재료를 골고루 익힐 수 있으며, 기름을 빼면서 조리할 수 있습니다."

      else if(key.startsWith("통돌이"))
        return "육류 뿐 아니라 커피콩, 팝콘, 군밤 등을 통회전 하면서 조리할 수 있습니다."

      else if(key.includes("열선"))
        return "열선 커버가 포함되어 있어, 열선 쪽에 상대적으로 기름이 덜 튀게 되어 열선세척 빈도를 줄일 수 있습니다. 다만, 완전히 막혀있는 것이 아니기 때문에 오염을 완벽히 방지하기는 어려우며, 오히려 청소 시 덮개를 분해해야 하기 때문에 불편할 수 있습니다."

      else
        return key + " 기능을 갖춘 제품입니다."
    }

    const isNull = function(value) {
      if(value == undefined || value == "null")
        return "-"
      else
        return value;
    }

   onMounted(()=> {
    // 스펙에서 값을 뽑아낸다.
    let specList = [];
    console.log(state.productInfo)

    if(state.productInfo != undefined && state.productInfo != {} && state.productInfo.spec != ""){
      specList = JSON.parse(state.productInfo.spec)
    }

    for(var key in specList){
      for(var subkey in specList[key]){
        var json = new Object();
        json.key = subkey;
        json.value = specList[key][subkey];

        if(subkey == "분리형용기" || subkey == "분리형거름망")
          state.managementList.push(json) // 세척편의
        else
          state.convenienceList.push(json) // 편의기능

        // 주요 스펙 탐색
        if(subkey.startsWith("다이얼") || subkey.startsWith("버튼") || subkey.startsWith("터치"))
          state.control = subkey;
        else if(subkey.startsWith("시간조절"))
          state.timeControl = specList[key][subkey];
        else if(subkey.startsWith("온도조절"))
          state.temperatureControl = specList[key][subkey];
        else if(subkey.startsWith("내부투시"))
          state.showInside = specList[key][subkey];
         else if(subkey.startsWith("디지털디스플레이"))
          state.digitalDisplay = specList[key][subkey];
      }
    }

   })

    return { clickModal, state, answerOfType, answerOfSpec, isNull }
  }
}
</script>
