<template>
  <div class="section" id="core-spec" tabindex="-1">
      <div class="core-spec">
        <div class="title">
          <div><h2 style="width: 50%">주요 스펙</h2></div>
          <div style="text-align: right"><button data-bs-toggle="modal" data-bs-target="#productSpecModal">스펙전체보기</button></div>
        </div>

        <div class="content">
          <div class="spec-item"><div><p style="color: #7E7E7E;">가격대</p><p>{{ productInfo.price }}</p></div></div>
          <div class="spec-item"><div><p style="color: #7E7E7E;">화면 크기</p><p>{{ productInfo.size }}</p></div></div>
          <div class="spec-item"><div><p style="color: #7E7E7E;">해상도</p><p>{{ productInfo.pixel }}</p></div></div>
          <div class="spec-item"><div><p style="color: #7E7E7E;">주사율</p><p>{{ productInfo.hz }} Hz</p></div></div>
          <div class="spec-item"><div><p style="color: #7E7E7E;">무게</p><p>{{ isWeight() }}</p></div></div>
          <div class="spec-item"><div><p style="color: #7E7E7E;">스피커</p><p>{{ isSpeaker() }}</p></div></div>
          <div class="spec-item"><div><p style="color: #7E7E7E;">색재현율</p><p>{{ isNTSC() }}</p></div></div>
          <div class="spec-item"><div><p style="color: #7E7E7E;">HDMI지원</p><p>{{ isHDMI() }}</p></div></div>
          <div class="spec-item"><div><p style="color: #7E7E7E;">FreeSync</p><p>{{ isFreeSync() }}</p></div></div>
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
            <div class="chart" id="price-score">
              <div class="circle" :class="priceSum.class" v-for="i in priceSum.score" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - priceSum.score" v-bind:key="i" />
            </div>
            <h3 class="content">{{ priceSum.score }}점 <span style="color: #C7C7C7;">|</span> {{ calPricePoint(productInfo.pricePoint) }} 편</h3>
          </div>
          <div class="score-group">
            <h3 class="title">화면 크기</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart" id="size-score">
              <div class="circle" :class="sizeSum.class" v-for="i in sizeSum.score" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - sizeSum.score" v-bind:key="i" />
            </div>
            <h3 class="content">{{ sizeSum.score }}점 <span style="color: #C7C7C7;">|</span> {{ calSizePoint(productInfo.pricePoint) }} 편</h3>
          </div>
          <div class="score-group">
            <h3 class="title">해상도</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart" id="pixel-score">
              <div class="circle" :class="pixelSum.class" v-for="i in pixelSum.score" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - pixelSum.score" v-bind:key="i" />
            </div>
            <h3 class="content">{{ pixelSum.score }}점 <span style="color: #C7C7C7;">|</span> {{ calPixelPoint(productInfo.pixelPoint) }} 편</h3>
          </div>
          <div class="score-group">
            <h3 class="title">주사율</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart" id="hz-score">
              <div class="circle" :class="hzSum.class" v-for="i in hzSum.score" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - hzSum.score" v-bind:key="i" />
            </div>
            <h3 class="content">{{ hzSum.score }}점 <span style="color: #C7C7C7;">|</span> {{ productInfo.hz }} Hz</h3>
          </div>
          <div class="score-group">
            <h3 class="title">부가 기능</h3>
            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
            <div class="chart" id="con-score">
              <div class="circle" :class="conSum.class" v-for="i in conSum.score" v-bind:key="i" />
              <div class="circle" v-for="i in 5 - conSum.score" v-bind:key="i" />
            </div>
            <h3 class="content">{{ conSum.score }}점 <span style="color: #C7C7C7;">|</span> {{ calConPoint(productInfo.conveniencePoint) }} 편</h3>
          </div>
        </div>
      </div>
  </div>

  <hr class="division-line" />

  <div class="section" id="detail-spec">
    <div class="spec-info">
    <div class="title">
      <h2>스펙 분석</h2>
    </div>
    <div class="body">
      <div class="price spec">
        <div class="sub-title">
          <div class="circle circle-margin">1</div>
          <h3>가격 | {{ calPricePoint(productInfo.pricePoint) }}</h3>
        </div>
        <hr>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div class="chart-low"
              :class="priceSum.class"
              :style="{ width: priceSum.score*20 + '%' }"
              >&nbsp;</div>
              <ul class="chart-desc">
                <li class="desc">비쌈</li>
                <li class="desc">조금 비쌈</li>
                <li class="desc">보통</li>
                <li class="desc">저렴</li>
                <li class="desc">매우 저렴</li>
              </ul>
        </div>
        <div class="content">
          <div class="content-func">
            <h4>{{ $filters.convertPriceVer2(productInfo.price) }} 만원</h4>
            <div>현재 온라인 최저가는 {{ productInfo.lowPriceInfos[0].price }} 원으로 모니터 전체에서는 {{ calSizePoint(productInfo.pricePoint) }} 편입니다.</div>
          </div>
        </div>
      </div>

      <div class="size spec">
        <div class="sub-title">
          <div class="circle circle-margin">2</div>
          <h3>화면 크기 | {{ calSizePoint(productInfo.sizePoint) }}</h3>
        </div>
        <hr>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div class="chart-low"
              :class="sizeSum.class"
              :style="{ width: sizeSum.score*20 + '%' }"
              >&nbsp;</div>
              <ul class="chart-desc">
                <li class="desc">아주 작음</li>
                <li class="desc">작음</li>
                <li class="desc">보통</li>
                <li class="desc">큼</li>
                <li class="desc">아주 큼</li>
              </ul>
        </div>
        <div class="content">
          <div class="content-func" v-if="inch != undefined && inch.length > 2">
            <h4>{{ inch[1].substring(0, inch[1].length-1) }}</h4>
            <div>화면 대각선 길이가 {{ inch[0] }}인 {{ inch[1].substring(0, inch[1].length-1) }} 제품입니다. <br> 현재 판매중인 모니터 중 {{ calPricePoint(productInfo.sizePoint) }} 편입니다.</div>
            <div><br>틸트와 피벗 기능이 있어 여러 용도로 사용이 편리합니다.</div>
          </div>

          <div class="content-func" v-if="isStand().includes(`틸트`)">
            <h4>틸트</h4>
            <div>모니터 틸트 기능이 제공되는 제품입니다.</div>
          </div>

          <div class="content-func" v-if="isStand().includes(`피벗`)">
            <h4>피벗</h4>
            <div>모니터 피벗 기능이 제공되는 제품입니다.</div>
          </div>

        </div>
        <div class="modal-button">
          <button data-bs-toggle="modal" data-bs-target="#exampleModal" @click="clickModal('size')">피벗? 틸트? 그게 뭐지? 🤔</button>
        </div>

        <product-detail-spec-monitor-modal id="exampleModal"></product-detail-spec-monitor-modal>
      </div>

      <div class="cook spec">
        <div class="sub-title">
          <div class="circle circle-margin">3</div>
          <h3>해상도 | {{ calPixelPoint(productInfo.pixelPoint) }}</h3>
          <hr>
        </div>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div class="chart-low"
              :class="pixelSum.class"
              :style="{ width: pixelSum.score*20 + '%' }"
              >&nbsp;</div>
              <ul class="chart-desc">
                <li class="desc">조금 부족함</li>
                <li class="desc">덜 선명함</li>
                <li class="desc">보통</li>
                <li class="desc">선명함</li>
                <li class="desc">아주 선명함</li>
              </ul>
        </div>
        <div class="content">
          <div class="content-func" v-if="pixelCal != undefined && pixelCal.length > 2">
            <h4>{{ pixelCal[1].substring(0, pixelCal[1].length-1) }}</h4>
            <div>화면 사이즈가 {{ pixelCal[0] }} 이며 전체 모니터 화질 중에서는 {{ calPixelPoint(productInfo.pixelPoint) }} 편인 제품입니다.</div>
          </div>
        </div>
        <div class="modal-button">
          <button data-bs-toggle="modal" data-bs-target="#exampleModal" @click="clickModal('pixel')">해상도? 어떻게 골라야 하는 걸까? 🤔</button>
        </div>
        <product-detail-spec-monitor-modal desc="pixel" id="exampleModal"></product-detail-spec-monitor-modal>
      </div>

      <div class="cook spec">
        <div class="sub-title">
          <div class="circle circle-margin">4</div>
          <h3>주사율 | {{ calHzPoint(productInfo.screenChangePoint) }}</h3>
          <hr>
        </div>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div class="chart-low"
              :class="hzSum.class"
              :style="{ width: hzSum.score*20 + '%' }"
              >&nbsp;</div>
              <ul class="chart-desc">
                <li class="desc">조금 부족함</li>
                <li class="desc">보통</li>
                <li class="desc">적당함</li>
                <li class="desc">좋음</li>
                <li class="desc">아주 좋음</li>
              </ul>
        </div>
        <div class="content">
          <div class="content-func">
            <h4>{{ productInfo.hz }} Hz</h4>
            <div>주사율이 {{ productInfo.hz }} Hz인 제품으로 전체 모니터 중 {{ calHzPoint(productInfo.screenChangePoint) }} 편입니다.</div>
            <div>최근 모니터는 기본적으로 60 Hz 이상의 주사율을 가지고 있으며, 게임용을 제외한 대부분의 용도로 충분합니다. </div>
          </div>
        </div>
      </div>

      <div class="cook spec">
        <div class="sub-title">
          <div class="circle circle-margin">5</div>
          <h3>부가 기능 | {{ calConPoint(productInfo.conveniencePoint) }}</h3>
          <hr>
        </div>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div class="chart-low"
              :class="conSum.class"
              :style="{ width: conSum.score*20 + '%' }"
              >&nbsp;</div>
              <ul class="chart-desc">
                <li class="desc">아주 적음</li>
                <li class="desc">적음</li>
                <li class="desc">보통</li>
                <li class="desc">많음</li>
                <li class="desc">아주 많음</li>
              </ul>
        </div>
        <div class="content">
          <div class="content-func" v-if="isNTSC().includes(`NTSC`)">
            <h4>색재현율 | NTSC</h4>
            <div>색재현율 중 NTSC가 {{ spec[`색재현율`][`NTSC`] }} 인 제품입니다.</div>
          </div>

          <div class="content-func" v-if="isNTSC().includes(`sRGB`)">
            <h4>색재현율 | sRGB</h4>
            <div>색재현율 중 sRGB가 {{ spec[`색재현율`][`sRGB`] }} 인 제품입니다.</div>
          </div>

          <div class="content-func" v-if="isSpeaker().includes(`○`)">
            <h4>스피커</h4>
            <div>내장 스피커가 제공되는 제품입니다.</div>
          </div>

          <div class="content-func" v-if="isStand().includes(`틸트`)">
            <h4>틸트</h4>
            <div>모니터 틸트 기능이 제공되는 제품입니다.</div>
          </div>

          <div class="content-func" v-if="isStand().includes(`피벗`)">
            <h4>피벗</h4>
            <div>모니터 피벗 기능이 제공되는 제품입니다.</div>
          </div>

          <div class="content-func" v-if="isStand().includes(`월마운트`)">
            <h4>월마운트</h4>
            <div>벽에 부착할 수 있는 {{ spec[`스탠드 기능`][`월마운트(VESA)`] }} 크기의 모니터 월마운트 기능이 제공되는 제품입니다.</div>
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
  padding-top: 100px;
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
import ProductDetailSpecMonitorModal from "./ProductDetailSpecMonitorModal.vue";
import { useStore } from "vuex";
import { reactive, computed, toRefs } from 'vue';

export default {
  name: 'ProductDetailSpecMonitor',
  components: {
      ProductDetailSpecMonitorModal,
  },
  setup(){
    const store = useStore();

    const state = reactive({
      productInfo : computed(() => {
        return store.getters["root/getProductInfo"];
      }),
      inch : computed(() => {
        if(state.productInfo == undefined)
          return 0;
        return state.productInfo.size.split('(');
      }),
      pixelCal : computed(() => {
        return state.productInfo.pixel.split('(');
      }),
      spec : computed(() => {
        return JSON.parse(state.productInfo.spec);
      }),
      circleColor : {
        class: "",
        score: "",
      },
      priceSum : computed(() => {
        const p = calScore(state.productInfo.pricePoint);
        if(p < 3) {
          return {
            class: "circle-low",
            score: p,
          }
        } else if(p<4) {
          return {
            class: "circle-middle",
            score: p,
          }
        } else if(p<5) {
          return {
            class: "circle-high",
            score: p,
          }
        } else {
          return {
            class: "circle-highest",
            score: p,
          }
        }
      }),
      sizeSum : computed(() => {
        const p = calScore(state.productInfo.sizePoint);
        if(p < 3) {
          return {
            class: "circle-low",
            score: p,
          }
        } else if(p<4) {
          return {
            class: "circle-middle",
            score: p,
          }
        } else if(p<5) {
          return {
            class: "circle-high",
            score: p,
          }
        } else {
          return {
            class: "circle-highest",
            score: p,
          }
        }
      }),
      pixelSum : computed(() => {
        const p = calScore(state.productInfo.pixelPoint);
        if(p < 3) {
          return {
            class: "circle-low",
            score: p,
          }
        } else if(p<4) {
          return {
            class: "circle-middle",
            score: p,
          }
        } else if(p<5) {
          return {
            class: "circle-high",
            score: p,
          }
        } else {
          return {
            class: "circle-highest",
            score: p,
          }
        }
      }),
      hzSum : computed(() => {
        const p = calScore(state.productInfo.screenChangePoint);
        if(p < 3) {
          return {
            class: "circle-low",
            score: p,
          }
        } else if(p<4) {
          return {
            class: "circle-middle",
            score: p,
          }
        } else if(p<5) {
          return {
            class: "circle-high",
            score: p,
          }
        } else {
          return {
            class: "circle-highest",
            score: p,
          }
        }
      }),
      conSum : computed(() => {
        const p = calScore(state.productInfo.conveniencePoint);
        if(p < 3) {
          return {
            class: "circle-low",
            score: p,
          }
        } else if(p<4) {
          return {
            class: "circle-middle",
            score: p,
          }
        } else if(p<5) {
          return {
            class: "circle-high",
            score: p,
          }
        } else {
          return {
            class: "circle-highest",
            score: p,
          }
        }
      }),
    })

    console.log("오긴하냐?")

    function clickModal(desc) {
      // console.log("클릭 모달" + desc)
      store.commit("root/setModal", desc)
      // console.log("모달 ㄱ밧 :" + store.getters["root/getModal"])
      console.log(state.spec)
      console.log(state.spec[`전력량`]==null ? true : false)
    }

    const calPricePoint = (point) => {
      if(point <= 10) return "비싼";
      else if(point <=40) return "조금 비싼"
      else if(point <=60) return "보통인"
      else if(point <= 80) return "저렴한"
      else return "아주 저렴한";
    }

    // 인치
    const calSizePoint = (point) => {
      if(point <= 10) return "아주 작은";
      else if(point <=40) return "작은"
      else if(point <=60) return "보통인"
      else if(point <= 80) return "큰"
      else return "아주 큰";
    }

    // 해상도 (HD~)
    const calPixelPoint = (point) => {
      if(point <= 10) return "조금 부족한";
      else if(point <=40) return "덜 선명한"
      else if(point <=60) return "보통인"
      else if(point <= 80) return "좋은"
      else return "아주 좋은";
    }

    const calHzPoint = (point) => {
      if(point <= 10) return "조금 부족한";
      else if(point <=40) return "보통"
      else if(point <=60) return "적당한"
      else if(point <= 80) return "좋은"
      else return "아주 좋은";
    }

    // 부가기능
    const calConPoint = (point) => {
      if(point <= 10) return "아주 적은";
      else if(point <=40) return "적은"
      else if(point <=60) return "보통인"
      else if(point <= 80) return "많은"
      else return "아주 많은";
    }

    const isWeight = () => {
      if(state.spec[`전력량`]==null) return "-";
      else {
        if(state.spec[`전력량`][`무게`] == null) return "-";
        return "○";
      }
    }

    const isSpeaker = () => {
      if(state.spec[`부가 기능`]==null) return "X";
      else {
        if(state.spec[`부가 기능`][`스피커`] == null) return "X";
        return "";
      }
    }

    const isNTSC = () => {
      if(state.spec[`색재현율`]==null) return "-";
      else {
        var tmp;
        if(state.spec[`색재현율`][`NTSC`] != null) tmp = "NTSC: " + state.spec[`색재현율`][`NTSC`];
        if(state.spec[`색재현율`][`sRGB`] != null) tmp = tmp + "\n" + "sRGB: " + state.spec[`색재현율`][`sRGB`];

        return tmp.size==0 ? "-" : tmp;
      }
    }

    const isHDMI = () => {
      if(state.spec[`영상입력 단자`]==null) return "-";
      else {
        if(state.spec[`영상입력 단자`][`HDMI`] == null) return "-";
        else return "○";
      }
    }

    const isFreeSync = () => {
      if(state.spec[`게임 화면 개선`]==null) return "-";
      else {
        if(state.spec[`게임 화면 개선`][`FreeSync`] == null) return "-";
        else return "○";
      }
    }

    const isStand = () => {
      var tmp;

      if(state.spec[`스탠드 기능`]==null) return "-";
      else {
        if(state.spec[`스탠드 기능`][`틸트(상하)`]!=null) tmp += "틸트";
        if(state.spec[`스탠드 기능`][`피벗(회전)`]!=null) tmp += "피벗";
        if(state.spec[`스탠드 기능`][`스위블(좌우)`]!=null) tmp += "스위블";
        if(state.spec[`스탠드 기능`][`월마운트(VESA)`]!=null) tmp += "월마운트";
      }
      return tmp;
    }

    const calScore = (score) => {
      if(score <= 20) return 1;
      else if(score <= 40 ) return 2;
      else if(score <= 60) return 3;
      else if(score <= 80) return 4;
      else return 5;
    }

    return { clickModal, ...toRefs(state), store,
     calPricePoint, calSizePoint, calPixelPoint, calHzPoint, calConPoint,
      isWeight, isSpeaker, isNTSC, isHDMI, isFreeSync, isStand,
      calScore }
  }
}
</script>
