<template>
  <div style="text-align: center; display: flex; justify-content: center">
    <div class="review-analysis">
      <h2>리뷰 분석</h2>
      <div class="review-summary">
        <div id="review-score">
          <h3>사용자 총 평점</h3>
          <div id="star">
            <img src="@/assets/star-yellow.png" alt="star-yellow" />
            <img src="@/assets/star-yellow.png" alt="star-yellow" />
            <img src="@/assets/star-yellow.png" alt="star-yellow" />
            <img src="@/assets/star-yellow.png" alt="star-yellow" />
            <img src="@/assets/star-grey.png" alt="star-grey" />
          </div>
          <!-- 총 평점 계산해서 h4 태그 안에 넣기 -->
          <h4 style="margin-top: 5px">4 / 5</h4>
        </div>
        <div id="review-count">
          <h3>전체 리뷰 수</h3>
          <img src="@/assets/user.png" alt="user icon" />
          <!-- 전체 리뷰 수 카운트해서 h2 태그 안에 넣기 -->
          <h2>231</h2>
        </div>
        <div id="review-ratio">
          <h3>평점 비율</h3>
          <!-- 나중에 비율 알맞게 graph css 비율 조정 -->
          <div class="ratio-chart">
            <div class="star-ratio">
              <div v-for="item in 5" :key="item.id">
                <img src="@/assets/star-yellow.png" alt="star-yellow" />
              </div>
              <div id="graph1"></div>
            </div>
            <div class="star-ratio">
              <div v-for="item in 4" :key="item.id">
                <img src="@/assets/star-yellow.png" alt="star-yellow" />
              </div>
              <div id="graph2"></div>
            </div>
            <div class="star-ratio">
              <div v-for="item in 3" :key="item.id">
                <img src="@/assets/star-yellow.png" alt="star-yellow" />
              </div>
              <div id="graph3"></div>
            </div>
            <div class="star-ratio">
              <div v-for="item in 2" :key="item.id">
                <img src="@/assets/star-yellow.png" alt="star-yellow" />
              </div>
              <div id="graph1"></div>
            </div>
            <div class="star-ratio">
              <div v-for="item in 1" :key="item.id">
                <img src="@/assets/star-yellow.png" alt="star-yellow" />
              </div>
              <div id="graph1"></div>
            </div>
          </div>
        </div>
      </div>
      <div id="word-cloud">
        <div id="cloud" ref="cloud" style="width: 100%; height: 100%"></div>
      </div>
      <div id="review-data">
        <div v-for="item in 5" :key="item.id">
          <product-detail-reivew-card></product-detail-reivew-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Anychart from "anychart";
import ProductDetailReivewCard from "../review/ProductDetailReviewCard.vue";

export default {
  name: "ProductDetailReivew",
  components: {
    ProductDetailReivewCard,
  },
  setup() {
  },
  mounted() {

    // setup 안에서는 안됨. dom 만들어지기 전에 객체 로드하려고 해서 그런듯?
    const chartData = [
      {
        x: "IT",
        value: 590000000,
      },
      {
        x: "Python",
        value: 283000000,
      },
      {
        x: "JAVA",
        value: 527000000,
      },
      {
        x: "C++",
        value: 422000000,
      },
    ];

    const chart = Anychart.tagCloud(chartData);
    chart.angles([0]);
    // chartw.normal().fill("#1f66ad"); // 글자색 바꾸기
    chart.container(this.$refs.cloud);
    chart.draw();
  },
};
</script>

<style scoped>
.review-analysis {
  width: 100%;
  justify-content: center;
  text-align: center;
  display: inline-block;
}

.review-summary {
  margin: 50px auto;
  max-width: 1058px;
  width: 70%;
  height: 222px;
  background: #fff3f3;
  border-radius: 20px;
  text-align: center;
  display: flex;
  justify-content: center;
}

.review-summary h3 {
  margin-top: 0px;
}

#review-score {
  display: inline-block;
  margin: auto 18px;
  vertical-align: middle;
  width: 25%;
}

#star img {
  width: 35px;
  height: 35px;
  padding: 4px;
}

#review-count {
  display: inline-block;
  margin: auto 18px;
  width: 25%;
}

#review-count img {
  width: 50px;
  height: 50px;
}

#review-ratio {
  display: inline-block;
  margin: auto 18px;
  width: 25%;
}

.star-ratio {
  display: flex;
  justify-content: right;
}

.star-ratio img {
  display: inline-block;
  width: 15px;
  height: 15px;
  margin: auto 2.3px;
}

#graph1 {
  width: 160px;
  height: 12px;
  background: linear-gradient(90deg, #666666 80%, #c4c4c4 20%);
  margin: auto 3px;
}

#graph2 {
  width: 160px;
  height: 12px;
  background: linear-gradient(90deg, #666666 60%, #c4c4c4 40%);
  margin: auto 3px;
}

#graph3 {
  width: 160px;
  height: 12px;
  background: linear-gradient(90deg, #666666 50%, #c4c4c4 50%);
  margin: auto 3px;
}

#word-cloud {
  width: 50%;
  max-width: 750px;
  height: 350px;
  text-align: center;
  justify-content: center;
  margin: 20px auto;
}

#review-data {
  width: 50%;
  max-width: 900px;
  display: inline-block;
  margin: 30px auto;
  /* text-align: center; */
  /* justify-content: center; */
}

h1 {
  font-size: 35px;
  margin: 0px 0px;
  font-family: "SpoqaHanSansNeo";
  display: inline-block;
  width: 80%;
}

h2 {
  font-size: 30px;
  margin: 0px 0px;
  font-family: "SpoqaHanSansNeo";
}

h4 {
  font-size: 20px;
  margin: 0px 0px;
  font-family: "SpoqaHanSansNeo";
}

@font-face {
  font-family: "SpoqaHanSansNeo";
  font-weight: 600;
  src: url("../../../assets/fonts/SpoqaHanSansNeo-Bold.ttf");
}
</style>
