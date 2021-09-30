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
        에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.
      </div>
    </div>

    <div class="size spec">
      <div class="sub-title">
        <div class="circle circle-margin">2</div>
        <h3>용량 | 보통</h3>
      </div>
      <div class="chart">
        <div class="chart-back">&nbsp;</div>
        <div class="chart-middle">&nbsp;</div>
      </div>
      <div class="content">
        에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다. 에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.  에어프라이기에 대한 설명입니다.
      </div>
      <div class="modal-button">
        <button>용량에 대한 설명</button>
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
  height: 200px;
  background-color: gray;

}
.modal-button > button {
  width: 20%;
  min-width: 250px;
  height: 80px;
  background-color: white;
  border: none ;
  filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.25));
}
</style>

<script>
// import { useStore } from "vuex";
export default {
  name: 'ProductDetailSpecAirFryer',
  components: {

  },
  setup(){

    return { }
  }
}
</script>
