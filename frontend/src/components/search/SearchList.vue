<template>
    <div class="searchList card mb-4 rounded-3" @click="clickProductDetail">
        <div class="title mt-3">
            <h4 class="card-title fw-bold">[{{product.brand}}] {{product.name}}</h4>
        </div>
        <div class="row g-0">
            <div class="col-md-3">
                <div class="card-body image">
                    <img :src="product.img" alt="상품 이미지" class="img-fluid rounded-3">
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
                            <!-- <div class="circle" v-for="i in 5 - Math.ceil(product.brewingPoint)" v-bind:key="i" /> -->
                        </div>
                        <h3 class="content"> {{product.brewingPoint}}점 <span style="color: #C7C7C7;">|</span> {{product.heatTime}}</h3>
                        </div>
                        <div class="score-group">

                        <h3 class="title">물통용량</h3>
                        <div class="chart">
                            <div class="circle" :class="state.waterWord.class" v-for="i in Math.ceil(state.waterWord.score)" v-bind:key="i" />
                            <!-- <div class="circle" v-for="i in 5 - Math.ceil(product.waterVolumePoint)" v-bind:key="i" /> -->
                        </div>
                        <h3 class="content">{{product.waterVolumePoint}}점<span style="color: #C7C7C7;">|</span> {{product.waterVolume}}</h3>
                        </div>
                        <div class="score-group">

                        <h3 class="title">사용편의</h3>
                        <div class="chart">
                            <div class="circle" :class="state.conferWord.class" v-for="i in Math.ceil(state.conferWord.score)" v-bind:key="i" />
                            <!-- <div class="circle" v-for="i in 5 - Math.ceil(product.conveniencePoint)" v-bind:key="i" /> -->
                        </div>
                        <h3 class="content"> {{product.conveniencePoint}}점<span style="color: #C7C7C7;">|</span> {{ state.conferWord.content}}</h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-body price">
                    <h3 class="mt-5 mb-3 center">{{ $filters.convertPriceVer2(product.price) }}만원</h3>
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
        console.log(props.product.pricePoint);
        let pricePoint = Math.ceil(props.product.pricePoint/20);
        console.log("price; " + pricePoint);
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
}
.card-body {
    margin: 0px auto;
    margin-top: 20px;
    padding: 15px;
    padding-right: 2px;
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
  margin: 5px auto;
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
</style>