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
                            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
                            <div class="chart">
                            <div class="circle circle-middle" />
                            <div class="circle circle-middle" />
                            <div class="circle circle-middle" />
                            <div class="circle" />
                            <div class="circle" />
                            </div>
                            <h3 class="content">3점 <span style="color: #C7C7C7;">|</span> 100~120만원</h3>
                        </div>
                        <div class="score-group">
                            <h3 class="title">용량</h3>
                            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
                            <div class="chart">
                            <div class="circle circle-high" />
                            <div class="circle circle-high" />
                            <div class="circle circle-high" />
                            <div class="circle circle-high" />
                            <div class="circle" />
                            </div>
                            <h3 class="content">4.5점 <span style="color: #C7C7C7;">|</span> 875리터</h3>
                        </div>
                        <div class="score-group">
                            <h3 class="title">가격</h3>
                            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
                            <div class="chart">
                            <div class="circle circle-middle" />
                            <div class="circle circle-middle" />
                            <div class="circle circle-middle" />
                            <div class="circle" />
                            <div class="circle" />
                            </div>
                            <h3 class="content">3점 <span style="color: #C7C7C7;">|</span> 100~120만원</h3>
                        </div>
                        <div class="score-group">
                            <h3 class="title">냉각성능</h3>
                            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
                            <div class="chart">
                            <div class="circle circle-highest" />
                            <div class="circle circle-highest" />
                            <div class="circle circle-highest" />
                            <div class="circle circle-highest" />
                            <div class="circle circle-highest" />
                            </div>
                            <h3 class="content">5점 <span style="color: #C7C7C7;">|</span> 다양한편</h3>
                        </div>
                        <div class="score-group">
                            <h3 class="title">가격</h3>
                            <!-- 실 구현할 때는 circle 색 때문에 script에서 데이터 받아오면서 직접 추가해줘야할듯 -->
                            <div class="chart">
                            <div class="circle circle-middle" />
                            <div class="circle circle-middle" />
                            <div class="circle circle-middle" />
                            <div class="circle" />
                            <div class="circle" />
                            </div>
                            <h3 class="content">3점 <span style="color: #C7C7C7;">|</span> 100~120만원</h3>
                        </div>
                    </div>
                    <!-- <p class="card-text"><small class="text-muted">{{product.score}}</small></p> -->
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-body price">
                    <h5 class="mt-5 mb-3 center">{{product.price}}</h5>
                    <button @click="goToBuy()" class="btn btn-block goToBuy rounded-pill fw-bold">사러가기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useRouter } from "vue-router";

export default {
    name : "Product",
    props : ['product'],

    setup(props){
        const router = useRouter();

        const clickProductDetail = function () {
            router.push({ name: 'Product', params: { category: catetory, pcode: pcode }});
        };

        return { router, clickProductDetail };
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
</style>