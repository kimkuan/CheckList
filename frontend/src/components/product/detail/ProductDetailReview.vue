<template>
  <div style="text-align: center; display: flex; justify-content: center">
    <div class="review-analysis">
      <h2>리뷰 분석</h2>
      <div class="review-summary">
        <div id="review-score">
          <h3>사용자 총 평점</h3>
          <div id="star">
            <img src="@/assets/star-yellow.png" alt="star-yellow" v-for="i in (reviewInfo.avgScore/20)" v-bind:key="i">
            <img src="@/assets/star-grey.png" alt="star-grey" v-for="i in 5-(reviewInfo.avgScore/20)" v-bind:key="i">
          </div>
          <!-- 총 평점 계산해서 h4 태그 안에 넣기 -->
          <h4 style="margin-top: 10px">{{ reviewInfo.avgScore/20 }} / 5</h4>
        </div>
        <div id="review-count">
          <h3>전체 리뷰 수</h3>
          <img src="@/assets/user.png" alt="user icon" />
          <!-- 전체 리뷰 수 카운트해서 h2 태그 안에 넣기 -->
          <h4 style="margin-top: 10px">231</h4>
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
        <div v-for="review in reviewInfo.reviewList.content" :key="review.id">
          <product-detail-reivew-card v-bind:review="review"></product-detail-reivew-card>
        </div>
        <div style="display:flex; justify-content: center;">
          <nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item">
                <a class="page-link" aria-label="Previous">
                  <span aria-hidden="true" @click="prePage()">&laquo;</span>
                </a>
              </li>
              <li class="page-item" v-for="item in cnt" :key="item.id">
                <a class="page-link" @click="getNextReview(item+pageIdx*10)">{{ item+pageIdx*10 }}</a>
              </li>
              <!-- <li class="page-item" v-for="item in reviewInfo.reviewList.totalPages" :key="item.id"><a class="page-link" @click="getNextReview(item)">{{ item }}</a></li> -->
              <li class="page-item">
                <a class="page-link" aria-label="Next">
                  <span aria-hidden="true" @click="nextPage()">&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
    <product-detail-review-modal v-bind:word="word" @call-parent="hideModal"></product-detail-review-modal>
  </div>
</template>

<script>
import { reactive, toRefs, onMounted, computed } from 'vue'
import { useStore } from "vuex";
import { useRoute } from 'vue-router';

import Anychart from "anychart";
import ProductDetailReivewCard from "../review/ProductDetailReviewCard.vue";
import ProductDetailReviewModal from "../review/ProductDetailReviewModal.vue";

export default {
  name: "ProductDetailReivew",
  components: {
    ProductDetailReivewCard,
    ProductDetailReviewModal,
  },
  setup() {
    const route = useRoute()
    const store = useStore()
    const state = reactive({
      displayModal: false,
      word: "",
      chartData: [
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
      ],
      reviewInfo: computed(() => {
        return store.getters["root/getReviewInfo"];
      }),
      cnt: computed(() => {
        if(store.getters["root/getReviewInfo"].reviewList.number == store.getters["root/getReviewInfo"].reviewList.totalPages-1)
          return state.reviewInfo.reviewList.totalPages%10;
        return 10;
      }),
      pageIdx: 0,
    });

    var pcode = route.params.pcode;

    // 리뷰 첫페이지 가져오기
    store.dispatch("root/requestProductReview", {
      pcode: pcode,
      page: 0,
    })
    .then(function(result){
      console.log(result.data)
      store.commit("root/setReviewInfo", result.data);
      console.log(state.reviewInfo);
    })
    .catch(function(err){
      console.log(err)
    })

    onMounted(() => {
      const chart = Anychart.tagCloud(state.chartData);
      chart.angles([0]);
      chart.selected().fill(chart.normal().fill()); // word 선택시 색상 변하지 않도록 설정
      chart.container(document.getElementById('cloud'));
      chart.tooltip(false); // data 설명창? 안뜨게 수정
      chart.draw();
      chart.listen("pointClick", function() {
        // console.log(chart.getSelectedPoints()[0].index);
        // console.log(chart.getPoint(chart.getSelectedPoints()[0].index).get('x'))
        state.word = chart.getPoint(chart.getSelectedPoints()[0].index).get('x');
        document.getElementById('productDetailReviewModal').classList.toggle('show');
        document.getElementById('productDetailReviewModal').style.display = 'block';
        document.getElementById('productDetailReviewModal').setAttribute('aria-modal', true);
        // document.getElementById('productDetailReviewModal').setAttribute('role', 'dialog');
        document.body.classList.toggle('modal-open');
        document.body.style.overflow = 'hidden';
      });
    })

    console.log("페이지수", state.reviewInfo.reviewList.totalPages)


    const hideModal = () => {
      console.log("암것두안혀")
    }

    const getNextReview = (idx) => {
      store.dispatch("root/requestProductReview", {
      pcode: pcode,
      page: idx-1,
    })
    .then(function(result){
      console.log(result.data)
      store.commit("root/setReviewInfo", result.data);
      console.log(state.reviewInfo);
    })
    .catch(function(err){
      console.log(err)
    })
    }

    const nextPage = () => {
      if(state.pageIdx < 1) {
      state.pageIdx = state.pageIdx+1;
      this.getNextRevie(state.pageIdx);
      route.push();
      }
    }

    const prePage = () => {
       if (state.pageIdx<=state.reviewInfo.reviewList.totalPages){
      state.pageIdx = state.pageIdx-1;
      this.getNextRevie(state.pageIdx);
      route.push("/");
      }
    }

    return { ...toRefs(state), hideModal, getNextReview, nextPage, prePage }
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
  /* max-width: 1058px; */
  width: 85%;
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
  margin-right: 5px;
  width: 263px;
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
  height: 400px;
  text-align: center;
  justify-content: center;
  margin: 20px auto;
}

#review-data {
  width: 65%;
  max-width: 900px;
  display: inline-block;
  margin: 30px auto;
  /* text-align: center; */
  /* justify-content: center; */
}

#review-data nav {
  margin-top: 30px;
}

.page-link {
  color: #000000;
  border: none;
}

h1 {
  margin: 0px 0px;
  font-family: "SpoqaHanSansNeo-Bold";
  display: inline-block;
  width: 80%;
}

h2 {
  margin: 0px 0px;
  font-family: "SpoqaHanSansNeo-Bold";
}

h4 {
  margin: 0px 0px;
  font-family: "SpoqaHanSansNeo-Bold";
}

</style>
