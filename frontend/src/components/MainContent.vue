<template>
  <!-- carousel -->
  <div
    id="carouselExampleControls"
    class="carousel"
    data-bs-ride="carousel"
  >
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active first">
        <div class="container carousel-item-1">
          <div class="row align-items-center justify-content-center">
            <div class="col-4">
              <h3>체크리스트와 함께</h3>
              <h3>가전제품 스펙 확인하기🤓🤭</h3>
              <button class="clickMore" @click="clickMoreButton" type="button">알아보기</button>
            </div>
            <div class="col-4"></div>
          </div>
        </div>
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
      <div class="nav nav-tabs nav-fill nav-tabs-category" id="nav-category-tab" role="tablist">
        <button class="nav-link active category-button" id="nav-best-tab" data-bs-toggle="tab" data-bs-target="#nav-best" type="button" role="tab" aria-controls="nav-best" aria-selected="true">인기</button>
        <button class="nav-link category-button" id="nav-weather-tab" data-bs-toggle="tab" data-bs-target="#nav-weather" type="button" role="tab" aria-controls="nav-weather" aria-selected="false">계절가전</button>
        <button class="nav-link category-button" id="nav-digital-tab" data-bs-toggle="tab" data-bs-target="#nav-digital" type="button" role="tab" aria-controls="nav-digital" aria-selected="false">디지털IT</button>
        <button class="nav-link category-button" id="nav-life-tab" data-bs-toggle="tab" data-bs-target="#nav-life" type="button" role="tab" aria-controls="nav-life" aria-selected="false">생활가전</button>
        <button class="nav-link category-button" id="nav-kitchen-tab" data-bs-toggle="tab" data-bs-target="#nav-kitchen" type="button" role="tab" aria-controls="nav-kitchen" aria-selected="false">주방가전</button>
      </div>
    </nav>
  </div>
  <div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-best" role="tabpanel" aria-labelledby="nav-best-tab">
      <main-category :visible='[true,true,true,true,true,true]'/>
    </div>
    <div class="tab-pane fade" id="nav-weather" role="tabpanel" aria-labelledby="nav-weather-tab">
      <main-category :visible='[false,false,true,false,false,true]'/>
    </div>
    <div class="tab-pane fade" id="nav-digital" role="tabpanel" aria-labelledby="nav-digital-tab">
      <main-category :visible='[true,false,false,false,false,true]'/>
    </div>
    <div class="tab-pane fade" id="nav-life" role="tabpanel" aria-labelledby="nav-life-tab">
      <main-category :visible='[false,false,false,false,false,false]'/>
    </div>
    <div class="tab-pane fade" id="nav-kitchen" role="tabpanel" aria-labelledby="nav-kitchen-tab">
      <main-category :visible='[false,true,false,true,true,true]'/>
    </div>
  </div>

  <!-- 베스트 상품 -->
  <div class="best">
    <h4>🏅 베스트</h4>
    <!-- <product-card></product-card> -->
  </div>

  <!-- 체크픽 -->
  <div class="checkpick">
    <h4>
      <img class="checkpickLogo" src="../assets/images/logo2.png">
      체크픽
    </h4>
    <div class="check-category">
      <nav>
        <div class="nav nav-tabs justify-content-center checkp" id="nav-checkpick-tab" role="tablist">
          <button class="nav-link active checkpick-button" id="nav-airflyer-tab" data-bs-toggle="tab" data-bs-target="#nav-airflyer" type="button" role="tab" aria-controls="nav-airflyer" aria-selected="true">에어프라이어</button>
          <button class="nav-link checkpick-button" id="nav-foodprocessor-tab" data-bs-toggle="tab" data-bs-target="#nav-foodprocessor" type="button" role="tab" aria-controls="nav-foodprocessor" aria-selected="false">음식물처리기</button>
          <button class="nav-link checkpick-button" id="nav-monitor-tab" data-bs-toggle="tab" data-bs-target="#nav-monitor" type="button" role="tab" aria-controls="nav-monitor" aria-selected="false">모니터</button>
          <button class="nav-link checkpick-button" id="nav-aircleaner-tab" data-bs-toggle="tab" data-bs-target="#nav-aircleaner" type="button" role="tab" aria-controls="nav-aircleaner" aria-selected="false">공기청정기</button>
          <button class="nav-link checkpick-button" id="nav-coffeemachine-tab" data-bs-toggle="tab" data-bs-target="#nav-coffeemachine" type="button" role="tab" aria-controls="nav-coffeemachine" aria-selected="false">캡슐커피머신</button>
        </div>
      </nav>
    </div>
    <div class="tab-content" id="check-nav-tabContent">
      <div class="tab-pane fade show active" id="nav-airflyer" role="tabpanel" aria-labelledby="nav-airflyer-tab">에어에어</div>
      <div class="tab-pane fade" id="nav-foodprocessor" role="tabpanel" aria-labelledby="nav-foodprocessor-tab">계절가전</div>
      <div class="tab-pane fade" id="nav-monitor" role="tabpanel" aria-labelledby="nav-monitor-tab">...</div>
      <div class="tab-pane fade" id="nav-aircleaner" role="tabpanel" aria-labelledby="nav-aircleaner-tab">...</div>
      <div class="tab-pane fade" id="nav-coffeemachine" role="tabpanel" aria-labelledby="nav-coffeemachine-tab">...</div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import MainCategory from "./main/MainCategory.vue";
// import ProductCard from "./product/ProductCard.vue";

export default {
  name : "MainContent",
  components : {
    MainCategory,
    // ProductCard
  },
  setup() {
    const router = useRouter();
    const clickMoreButton = function () {
      router.push({ name: "AboutService" });
    };

    return { router, clickMoreButton };
  },
};
</script>

<style scoped>
button {
  border: none;
  background: none;
}

button:focus {
  outline: none;
}
.d-block {
  height: 200px;
}

.first {
  background-color: rgba(255, 196, 196, 0.29);
  height: 200px;
}

.best {
  height: 500px;
}
.check-category {
  font-size: 18px;
}
.tab-content {
  height: 25%;
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
.checkpickLogo {
  width: 20px;
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
  background: white;
}

.checkpick-button {
  background: white;
  border-color: #fff #fff #fff #fff;
}
.checkpick-button.active {
  background: white;
  border-color: #fff #fff #fff #fff;
}

.checkpick-button:hover,
.category-button:hover {
  border-color: white;
}

.nav-tabs {
  border-bottom: white;
}

.nav-tabs-category {
  border-bottom: 1px solid #dee2e6;
}
.container {
  height: 100%;
}

.row {
  height: 100%;
}
h3 {
  font-family: SpoqaHanSansNeo-Bold;
}

.clickMore {
  width: 120px;
  height: 35px;
  background-color: rgba(255, 87, 87, 0.24);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 40px;
  border: 0;
  font-weight: bold;
}

</style>
