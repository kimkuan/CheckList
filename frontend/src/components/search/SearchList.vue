<template>
    <div class="searchList card mb-4 rounded-3" @click="clickProductDetail">
        <div class="title mt-3">
            <h3 class="card-title fw-bold">[{{product.brand}}] {{product.name}}</h3>
        </div>
        <div class="row g-0">
            <div class="col-md-3">
                <div class="card-body image">
                    <img :src="product.img" alt="상품 이미지" class="img rounded-3">
                </div>
            </div>
            <div class="col-md-6">
                <div class="card-body score">
                    <div class="score-chart">
                        <div class="score-group">

                        <h3 class="title">가격</h3>
                        <div class="chart">
                            <div class="circle" :class="state.priceWord.class" v-for="i in pricePoint" v-bind:key="i" />
                            <div class="circle" v-for="i in 5 - pricePoint" v-bind:key="i" />
                        </div>
                        <h3 class="content">{{product.pricePoint}}점 <span style="color: #C7C7C7;">|</span> {{product.price}} </h3>
                        </div>
                        <div class="score-group">

                        <h3 class="title">추출성능</h3>
                        <div class="chart">
                            <div class="circle" :class="state.brewingWord.class" v-for="i in Math.ceil(state.brewingWord.score)" v-bind:key="i" />
                            <div class="circle" v-for="i in 5 - Math.ceil(state.brewingWord.score)" v-bind:key="i" />
                        </div>
                        <h3 class="content"> {{product.brewingPoint}}점 <span style="color: #C7C7C7;">|</span> {{product.heatTime}}</h3>
                        </div>
                        <div class="score-group">

                        <h3 class="title">물통용량</h3>
                        <div class="chart">
                            <div class="circle" :class="state.waterWord.class" v-for="i in Math.ceil(state.waterWord.score)" v-bind:key="i" />
                            <div class="circle" v-for="i in 5 - Math.ceil(state.waterWord.score)" v-bind:key="i" />
                        </div>
                        <h3 class="content">{{product.waterVolumePoint}}점<span style="color: #C7C7C7;">|</span> {{product.waterVolume}}</h3>
                        </div>
                        <div class="score-group">

                        <h3 class="title">사용편의</h3>
                        <div class="chart">
                            <div class="circle" :class="state.conferWord.class" v-for="i in Math.ceil(state.conferWord.score)" v-bind:key="i" />
                            <div class="circle" v-for="i in 5 - Math.ceil(state.conferWord.score)" v-bind:key="i" />
                        </div>
                        <h3 class="content"> {{product.conveniencePoint}}점<span style="color: #C7C7C7;">|</span> {{ state.conferWord.content}}</h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-body price">
                    <h1 class="mt-5 mb-3 center">{{ $filters.convertPriceVer2(product.price) }}만원</h1>
                    <button @click="goToBuy()" class="btn btn-block goToBuy rounded-pill fw-bold">사러가기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { reactive, computed } from "vue";
import { useStore } from "vuex";

export default {
    name : "SearchList",
    props : ['product'],

    setup(props){
        let pricePoint = Math.ceil(props.product.pricePoint/20);
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

    return { pricePoint, store, state};
    }
}
</script>

<style scoped>
.searchList {
    width: 80%;
    height: 280px;
    margin: auto;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
    border: none;
}
.searchList:hover {
    box-shadow: rgba(0, 0, 0, 0.07) 0px 1px 2px, rgba(0, 0, 0, 0.07) 0px 2px 4px, rgba(0, 0, 0, 0.07) 0px 4px 8px, rgba(0, 0, 0, 0.07) 0px 8px 16px, rgba(0, 0, 0, 0.07) 0px 16px 32px, rgba(0, 0, 0, 0.07) 0px 32px 64px;
}
.goToBuy {
    background-color: #FFEDED;
    padding: 6px 20px 6px 20px;
}
.card-title {
    text-align: center;
    margin: auto;
    padding-top: 5px;
}

.img {
    max-height: 220px;
}

.image {
    padding-top: 0px;
}

.col-md-6 {
    max-height: 220px;
}

h3.title {
  text-align: right;
  /* width: 15%; */
  font-size: 16px;
}

h3.content {
  text-align: left;
  /* width: 40%; */
  font-size: 16px;
}

.score-chart {
  margin: 5px 0px;
}

.score-group {
  margin: 10px auto;
}

.score-chart h3 {
  display: inline-block;
  margin: 0 10px;
}

.score-chart .score-group h3 {
  display: inline-block;
  margin: 0 10px;
}


.score-chart .score-group .chart {
  display: inline-block;
  width: 40%;
}

 .score .circle {
  display: inline-block;
  margin-left: 6px;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #ECECEC;
}
/*
.score .circle-low {
  display: inline-block;
  margin-left: 6px;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #FF8946;
}

.score .circle-middle {
  display: inline-block;
  margin-left: 6px;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #FFD480;
}

.score .circle-high {
  display: inline-block;
  margin-left: 6px;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #9EED9C;
}

.score .circle-highest {
  display: inline-block;
  margin-left: 6px;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #7BD8FF;
} */


.circle {
  display: inline-block;
  margin-left: 4px;
  width: 18px;
  height: 18px;
  border-radius: 50%;
  background: #ECECEC;
}

.circle-low {
  background: #FF8946;
}

.circle-middle {
  background: #FFD480;
}

.circle-high {
  background: #9EED9C;
}

.circle-highest {
  background: #7BD8FF;
}

.price {
    text-align: center;
}

/* 1 ~ 3점 */
.chart-llow {
  background-color: #FF8946 !important;
}

/* 3 ~ 4점 */
.chart-low {
  background-color: #FFD480 !important;
}

/* 4 ~ 5점*/
.chart-middle {
  background-color: #9EED9C !important;
}

/* 5점 */
.chart-high {
  background-color: #7BD8FF !important;
}

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