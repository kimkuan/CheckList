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
        <hr>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div class="chart-low">&nbsp;</div>
          <ul class="chart-desc">
            <li class="desc"></li>
          </ul>
        </div>
        <div class="content">
          <div class="content-func">
            <h4>10만원</h4>
            <div>현재 온라인 최저가는 10만원으로 모니터 전체에서는 저렴한 편입니다.</div>
          </div>
        </div>
      </div>

      <div class="size spec">
        <div class="sub-title">
          <div class="circle circle-margin">2</div>
          <h3>화면 크기 | 보통</h3>
        </div>
        <hr>
        <div class="chart">
          <div class="chart-back">&nbsp;</div>
          <div class="chart-middle">&nbsp;</div>
        </div>
        <div class="content">
          <div class="content-func">
            <h4>23 인치</h4>
            <div>화면 크기가 1920x720인 23인치 제품입니다. <br> 현재 판매중인 모니터 중 보통인 편입니다.</div>
            <div><br>틸트와 피벗 기능이 있어 여러 용도로 사용이 편리합니다.</div>
          </div>
        </div>
        <div class="modal-button">
          <button data-bs-toggle="modal" data-bs-target="#exampleModal" @click="clickModal('size')">피벗? 틸트?🤔</button>
        </div>

        <product-detail-spec-monitor-modal id="exampleModal"></product-detail-spec-monitor-modal>
      </div>

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
        <product-detail-spec-monitor-modal desc="cook" id="exampleModal"></product-detail-spec-monitor-modal>
      </div>

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
</template>

<style scoped>
/* 공통 CSS */
h2 {
  font-family: SpoqaHanSansNeo-Bold;
}
h3 {
  display: inline-block;
}
h4 {
  color: #000000;
}
.title {
  text-align: center;
  margin: 10px 0px 10px 0px;
}
.body {
  width: 60%;
  margin: 0 auto;
  margin-top: 30px;
}
.spec {
  position: relative;
  margin-bottom: 100px;
}

/* 스펙 타이틀 CSS */
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

/* 막대그래프 CSS */
.chart {
  height: 60px;
}
.chart > div {
  position: absolute;
  display: block;
  line-height: 18px;
  border-radius: 15px;
  margin: 10px 0;
}
.chart-back {
  background-color: #F5F5F5;
  width: 100%;
}
.chart-llow {
  background-color: #FF8946;
  width: 20%; ; /* width 값을 이용하여 그래프 조정 */
}
.chart-low {
  background-color: #FFD480;
  width: 50%;
}
.chart-middle {
  background-color: #9EED9C;
  width: 70%;
}
.chart-high {
  background-color: #7BD8FF;
  width: 100%
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

/* 성능 상세 설명 CSS */
.content > .content-func {
  margin-bottom: 30px;
}

/* 모달 버튼 CSS */
.modal-button {
  width: 100%;
  height: 50px;
  margin: auto;
}
.modal-button > button {
  display: block;
  min-width: 300px;
  height: 50px;
  margin: auto;

  background-color: white;
  border: none ;
  border-radius: 50px;
  filter: drop-shadow(0px 3px 3px rgba(0, 0, 0, 0.2));
  font-size: 14px;
}
</style>

<script>
import ProductDetailSpecMonitorModal from "./ProductDetailSpecMonitorModal.vue";
import { useStore } from "vuex";

export default {
  name: 'ProductDetailSpecMonitor',
  components: {
      ProductDetailSpecMonitorModal,
  },
  setup(){
    const store = useStore();

    function clickModal(desc) {
      console.log("클릭 모달" + desc)
      store.commit("root/setModal", desc)
      console.log("모달 ㄱ밧 :" + store.getters["root/getModal"])
    }

    return { clickModal }
  }
}
</script>
