<template>
  <div class="section-wrapper">
    <!-- <div style="display: flex; justify-content: center"> -->
    <div class="section" style="padding-top: 0px">
      <div class="productHead">
        <div class="p-img">
          <!-- 이미지 -->
          <img alt="product sample img" src="@/assets/product_sample.jpg" />
        </div>
        <div class="p-info">
          <!-- 제품 내용 -->
          건조기
          <br />
          <div class="title">
            <div style="width: 75%">
              <h4>삼성전자</h4>
              <h2>제품코드 DV14T8520BV</h2>
            </div>
          </div>
          <br />
          <div>
            <div style="display: inline-block; width: 75%">
              <h1>최저가 1,200,000원</h1>
            </div>
            <div style="display: inline-block; width: 25%; text-align: right">
              <button>사러가기</button>
            </div>
          </div>

          <div class="price-chart">
            <!-- 최저가 추이 그래프 -->
            <product-detail-chart></product-detail-chart>
          </div>
        </div>
      </div>
    </div>

    <!-- <hr class="division-line" /> -->

    <div class="category" style="margin-top: 50px;">
      <nav>
        <div class="nav nav-tabs nav-fill" id="detail-menu" role="tablist">
          <button class="nav-link active category-button" id="nav-best-tab" type="button" role="tab" onclick="location.href='#core-spec'">주요스펙</button>
          <button class="nav-link active category-button" id="nav-weather-tab" type="button" role="tab" onclick="location.href='#score'">상품점수</button>
          <button class="nav-link active category-button" id="nav-digital-tab" type="button" role="tab" onclick="location.href='#detail-spec'">스펙분석</button>
          <button class="nav-link active category-button" id="nav-life-tab" type="button" role="tab" onclick="location.href='#detail-review'">리뷰분석</button>
          <button class="nav-link active category-button" id="nav-kitchen-tab" type="button" role="tab" onclick="location.href='#low-price'">최저가사러가기</button>
        </div>
      </nav>
    </div>

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

    <hr class="division-line" />

    <div class="section" id="detail-spec">
      <!-- <product-detail-spec-air-fryer></product-detail-spec-air-fryer> -->
      <product-detail-spec-air-cleaner></product-detail-spec-air-cleaner>

    </div>

    <hr class="division-line" />

    <div class="section" id="detail-review">
      <product-detail-reivew style="width:100%"></product-detail-reivew>
    </div>

    <hr class="division-line" />

    <div class="section" style="" id="low-price">
      <product-detail-low-price></product-detail-low-price>
    </div>
    <product-all-spec-modal id="productSpecModal"></product-all-spec-modal>
  </div>
</template>

<script>
import { reactive, onMounted } from 'vue'

import ProductDetailChart from './detail/ProductDetailChart.vue';
import ProductDetailReivew from './detail/ProductDetailReview.vue';
import ProductDetailLowPrice from './detail/ProductDetailLowPrice.vue';
// import ProductDetailSpecAirFryer from './spec/ProductDetailSpecAirFryer.vue';
import ProductDetailSpecAirCleaner from './spec/ProductDetailSpecAirCleaner.vue';
import ProductAllSpecModal from "./ProductAllSpecModal.vue";

export default {
  name: "ProductDetail",
  components: {
    ProductDetailChart,
    ProductDetailReivew,
    ProductDetailLowPrice,
    // ProductDetailSpecAirFryer,
    ProductDetailSpecAirCleaner,
    ProductAllSpecModal,
  },
  setup() {

    const state = reactive({
      scrollValue: 0,
    })

    const onScroll = function() {

      if(0 > document.getElementById('detail-menu').getBoundingClientRect().top) {
        if(!document.getElementById('detail-menu').classList.contains('fixed-top'))
          document.getElementById('detail-menu').classList.add('fixed-top');
      } else {
        const tmp = document.getElementById('detail-menu').getBoundingClientRect().top;
        if(window.pageYOffset>700 && tmp==0)  console.log()
        else if( document.getElementById('detail-menu').classList.contains('fixed-top'))
          document.getElementById('detail-menu').classList.remove("fixed-top");
      }
    }

    onMounted(() => {
      window.addEventListener('scroll', onScroll);
    })

    return { state, onScroll, onMounted }
  },
};

</script>

<style scoped>
.section {
  margin: 0 auto;
  display: flex;
  justify-content: center;
  padding-top: 100px;
}

hr.division-line {
  /* width: 1205px; */
  /* width: 85%; */
  /* max-width: 1205px; */
  border: 1px solid #D8D8D8;;
  margin-top: 100px;
}

.nav-link.active {
  color: rgba(207, 0, 15, 1);
  font-weight: bold;
  background-color: #fff;
  border-color: #fff #fff #cf000f;
  border-width: 3px;
}

.nav-link {
  color: #6d6d6d;
}

.category {
  padding-top: 30px;
  border-bottom: 1px solid rgb(229, 229, 229);
  margin-left: auto;
  margin-right: auto;
  font-size: 15px;
}

.category-button {
  font-weight: bold;
}

.nav-tabs {
  border-bottom: white;
}

.fixed-top {
  background-color: white;
  width: 70%;
  padding-top: 30px;
  margin: auto;
}

.productHead {
  /* position: absolute; */
  width: 1047px;
  /* width: 55%; */
  height: 465px;
  /* left: 437px; */
  /* margin: 0px auto; */
  /* top: 178px; */
  /* background-color: lightgray; */
  text-align: center;
  display: flex;
  justify-content: center;
}

.productHead .p-img {
  /* background-image: url('../../assets/product_sample.jpg'); */
  /* background-repeat: no-repeat; */
  /* background-size: contain; */
  width: 40%;
  height: 100%;
  margin: auto 0px;
}

.productHead .p-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.productHead .p-info {
  width: 60%;
  height: 100%;
  text-align: left;
  margin: auto 0px;
}

.p-info .title {
  line-height: 250%;
}

.p-info .title div {
  display: inline-block;
  vertical-align: middle;
}

.p-info .title div button {
  width: 66px;
  height: 66px;
  border-radius: 50%;
  filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.25));
}

.p-info button {
  display: inline-block;
  /* width: 20%; */
  width: 90%;
  height: 42px;
  background: #cf000f;
  border-radius: 15px;
  color: #fff3f3;
  border: none;
  filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.25));
}

.price-chart {
  margin: 50px auto;
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
  /* margin-left: 3%; */
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
  height: 487px;
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

.circle {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #ECECEC;
}

.circle-low {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #FF8946;
}

.circle-middle {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #FFD480;
}

.circle-high {
  display: inline-block;
  margin-left: 10px;
  width: 23px;
  height: 23px;
  border-radius: 50%;
  background: #9EED9C;
}

.circle-highest {
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

</style>
