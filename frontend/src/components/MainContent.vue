<template>
  <!-- carousel -->
  <div
    id="carouselExampleControls"
    class="carousel slide"
    data-bs-ride="carousel"
  >
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active first">
      </div>
      <div class="carousel-item">
        <img src="../assets/images/tmpCarouselImage2.png" class="d-block w-100" alt="..." />
      </div>
      <div class="carousel-item">
        <img src="../assets/images/logo.png" class="d-block w-100" alt="..." />
      </div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carouselExampleControls"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carouselExampleControls"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>

  <!-- 카테고리 -->
  <div class="category">
    <nav>
      <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
        <button class="nav-link active" id="nav-best-tab" data-bs-toggle="tab" data-bs-target="#nav-best" type="button" role="tab" aria-controls="nav-best" aria-selected="true">인기</button>
        <button class="nav-link" id="nav-weather-tab" data-bs-toggle="tab" data-bs-target="#nav-weather" type="button" role="tab" aria-controls="nav-weather" aria-selected="false">계절가전</button>
        <button class="nav-link" id="nav-digital-tab" data-bs-toggle="tab" data-bs-target="#nav-digital" type="button" role="tab" aria-controls="nav-digital" aria-selected="false">디지털IT</button>
        <button class="nav-link" id="nav-life-tab" data-bs-toggle="tab" data-bs-target="#nav-life" type="button" role="tab" aria-controls="nav-life" aria-selected="false">생활가전</button>
        <button class="nav-link" id="nav-kitchen-tab" data-bs-toggle="tab" data-bs-target="#nav-kitchen" type="button" role="tab" aria-controls="nav-kitchen" aria-selected="false">주방가전</button>
      </div>
    </nav>
  </div>
  <div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-best" role="tabpanel" aria-labelledby="nav-best-tab">베스트상품</div>
    <div class="tab-pane fade" id="nav-weather" role="tabpanel" aria-labelledby="nav-weather-tab">계절가전</div>
    <div class="tab-pane fade" id="nav-digital" role="tabpanel" aria-labelledby="nav-digital-tab">...</div>
    <div class="tab-pane fade" id="nav-life" role="tabpanel" aria-labelledby="nav-life-tab">...</div>
    <div class="tab-pane fade" id="nav-kitchen" role="tabpanel" aria-labelledby="nav-kitchen-tab">...</div>
  </div>

  <!-- 베스트 상품 -->
  <div class="best">
    <h6>🏅 베스트</h6>
    <!-- <product-card></product-card> -->
  </div>

  <!-- 체크픽 -->
  <div class="checkpick">
    <h6>체크픽</h6>
    <div class="check-category">
      <nav>
        <div class="nav nav-tabs justify-content-center" id="nav-tab" role="tablist">
          <button class="nav-link active" id="nav-airflyer-tab" data-bs-toggle="tab" data-bs-target="#nav-airflyer" type="button" role="tab" aria-controls="nav-airflyer" aria-selected="true">에어프라이어</button>
          <button class="nav-link" id="nav-foodprocessor-tab" data-bs-toggle="tab" data-bs-target="#nav-foodprocessor" type="button" role="tab" aria-controls="nav-foodprocessor" aria-selected="false">음식물처리기</button>
          <button class="nav-link" id="nav-monitor-tab" data-bs-toggle="tab" data-bs-target="#nav-monitor" type="button" role="tab" aria-controls="nav-monitor" aria-selected="false">모니터</button>
          <button class="nav-link" id="nav-aircleaner-tab" data-bs-toggle="tab" data-bs-target="#nav-aircleaner" type="button" role="tab" aria-controls="nav-aircleaner" aria-selected="false">공기청정기</button>
          <button class="nav-link" id="nav-coffeemachine-tab" data-bs-toggle="tab" data-bs-target="#nav-coffeemachine" type="button" role="tab" aria-controls="nav-coffeemachine" aria-selected="false">캡슐커피머신</button>
        </div>
      </nav>
    </div>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-airflyer" role="tabpanel" aria-labelledby="nav-airflyer-tab">에어에어</div>
      <div class="tab-pane fade" id="nav-foodprocessor" role="tabpanel" aria-labelledby="nav-foodprocessor-tab">계절가전</div>
      <div class="tab-pane fade" id="nav-monitor" role="tabpanel" aria-labelledby="nav-monitor-tab">...</div>
      <div class="tab-pane fade" id="nav-aircleaner" role="tabpanel" aria-labelledby="nav-aircleaner-tab">...</div>
      <div class="tab-pane fade" id="nav-coffeemachine" role="tabpanel" aria-labelledby="nav-coffeemachine-tab">...</div>
    </div>
    </div>
</template>

<script>
// import ProductCard from "./product/ProductCard.vue";

export default {
  name : "MainContent",
  components : {
    // ProductCard
  },
  setup() {},
};
</script>

<style scoped>
.d-block {
  height: 200px;
}

.first {
  background-color: rgba(255, 196, 196, 0.29);
  height: 200px;
}

.category {
  padding-top: 30px;
  border-bottom: 1px solid rgb(229, 229, 229);
  margin-left: auto;
  margin-right: auto;
  font-size: 10px;
}
.best {
  height: 500px;
}
.check-category {
  font-size: 12px;
}
.tab-content {
  height: 200px;
}

h6 {
  font-family: SpoqaHanSansNeo-Bold;
}

tabs .nav-link.active {
  color: rgba(207, 0, 15, 1);
  background-color: #fff;
  border-color: #dee2e6 #dee2e6 #fff;
}

</style>
