<template>
<div class="spec-info">
  <div class="title">
    <h2>스펙 분석</h2>
  </div>
  <div class="body">
    <div class="price spec">
      <div class="sub-title">
        <div class="circle circle-margin">1</div>
        <h3>가격 | 보통</h3>
      </div>
      <div class="chart">
        <div class="chart-back">&nbsp;</div>
        <div class="chart-low">&nbsp;</div>
        <ul class="chart-desc">
          <li class="desc"></li>
        </ul>
      </div>
      <div class="content">
        <strong>해당 상품의 최저가는 41만원으로 보통 수준입니다.</strong><br>
        공기청정기는 청정 가능한 면적이 넓을 수록 가격대가 높아집니다.
      </div>
    </div>

    <div class="size spec">
      <div class="sub-title">
        <div class="circle circle-margin">2</div>
        <h3>청정면적 | 보통</h3>
      </div>
      <div class="chart">
        <div class="chart-back">&nbsp;</div>
        <div class="chart-middle">&nbsp;</div>
      </div>
      <div class="content">
        표기되어 있는 청정 면적은 최대 세기 작동 기준입니다.<br>
        팬 소음으로 인해 항상 최대 세기로 작동 시키기 어려우므로 표기된 면적의 70% 수준 정도 공간에 사용하기에 적합합니다.<br>
        따라서 사용할 공간의 실면적의 1.5배로 선택하는 것을 추천합니다.

      </div>
      <div class="modal-button">
          <button data-bs-toggle="modal" data-bs-target="#productDetailSpecAirCleanerModal">청정면적에 대한 설명</button>
          <product-detail-spec-air-cleaner-modal id="productDetailSpecAirCleanerModal"></product-detail-spec-air-cleaner-modal>
      </div>
    </div>
  </div>
</div>
</template>

<style scoped>
h3 {
  display: inline-block;
}
.title {
  text-align: center;
  margin: 10px 0px 10px 0px;
}
.body {
  width: 70%;
  margin: 0 auto;
  margin-top: 30px;
}
.spec {
  margin-bottom: 100px;
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
.chart-low {
  background-color: #FFD480;
  width: 50%; /* width 값을 이용하여 그래프 조정 */
}
.chart-middle {
  background-color: #FF8946;
  width: 70%;
}
.chart-high {
  background-color: #FF553E;
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
}
.modal-button {
  margin: 0 auto;
  width: 100%;
  text-align: center;
}
.modal-button > button {
  width: 20%;
  min-width: 300px;
  height: 60px;
  background-color: white;
  border: none ;
  filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.25));
  border-radius: 50px;
  margin-top: 40px;
  font-size: 18px;
}
</style>

<script>
import ProductDetailSpecAirCleanerModal from "./ProductDetailSpecAirCleanerModal.vue";
// import { useStore } from "vuex";
export default {
  name: 'ProductDetailSpecAirFryer',
  components: {
      ProductDetailSpecAirCleanerModal
  },
  setup(){

    return { }
  }
}
</script>
