<template>
  <div class="section-wrapper">
    <!-- <div style="display: flex; justify-content: center"> -->
    <div class="section" style="padding-top: 0px">
      <div class="productHead">
        <div class="p-img">
          <!-- 이미지 -->
          <img alt="product sample img" :src="state.productInfo.img" />
        </div>
        <div class="p-info">
          <!-- 제품 내용 -->
          <div class="title">
            <div style="width: 75%">
              <h5>{{ state.categoryName }}</h5>
              <h4>{{ state.productInfo.brand }}</h4>
              <h2>{{ state.productInfo.name }}</h2>
            </div>
          </div>
          <br />
          <div>
            <div style="display: inline-block; width: 75%;  padding-left: 30px; color: #cf000f">
              <h1>최저가 {{ $filters.convertPrice(state.productInfo.price) }}원</h1>
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

    <!-- 이 부분이 각자 칵테고리에 맞게 변경됨! -->
    <product-detail-spec-monitor v-if="state.category == 'monitor'"></product-detail-spec-monitor>
    <product-detail-spec-air-fryer v-else-if="state.category == 'airfryer'" :productInfo="state.productInfo"></product-detail-spec-air-fryer>
    <product-detail-spec-coffee-machine v-else-if="state.category == 'coffeemachine'"></product-detail-spec-coffee-machine>

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
import { reactive, computed, onMounted, watch, watchEffect } from 'vue'
import { useStore } from "vuex";
import { useRoute } from 'vue-router';
import ProductDetailChart from './detail/ProductDetailChart.vue';
import ProductDetailReivew from './detail/ProductDetailReview.vue';
import ProductDetailLowPrice from './detail/ProductDetailLowPrice.vue';
import ProductDetailSpecAirFryer from './spec/ProductDetailSpecAirFryer.vue';
import ProductDetailSpecAirCleaner from './spec/ProductDetailSpecAirCleaner.vue';
import ProductDetailSpecCoffeeMachine from './spec/ProductDetailSpecCoffeeMachine.vue';
import ProductDetailSpecMonitor from './spec/ProductDetailSpecMonitor.vue';
import ProductAllSpecModal from "./ProductAllSpecModal.vue";

export default {
  name: "ProductDetail",
  components: {
    ProductDetailChart,
    ProductDetailReivew,
    ProductDetailLowPrice,
    ProductDetailSpecAirFryer,
    ProductDetailSpecAirCleaner,
    ProductDetailSpecCoffeeMachine,
    ProductDetailSpecMonitor,
    ProductAllSpecModal,
  },
  setup() {
    const route = useRoute()
    const store = useStore()
    let state = reactive({
      productInfo : computed(()=>{
        return store.getters["root/getProductInfo"];
      }),
      scrollValue: 0,
      category : route.params.category,
      pcode : route.params.pcode,
      categoryName : '',

      categoryLoad : computed(()=> console.log(state.category))
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

    const getCategory = function(category) {
      if(category == "airfryer")
        return "에어프라이기"
      else if(category == "aircleaner")
        return "공기청정기"
      else if(category == "monitor")
        return "모니터"
      else if(category == "foodprocessor")
        return "음식물처리기"
      else if(category == "coffeemachine")
        return "커피머신"
    }

    state.categoryName = getCategory(state.category)

    // 상품 정보 가져오기
    store.dispatch("root/requestProductInfo", {
      category: route.params.category,
      pcode: route.params.pcode
    })
    .then((result) => {
      state.productInfo = result.data
      store.commit("root/setProductInfo", result.data);

      // 현재 가져온 상품을 최근 본 상품에 등록
      store.commit("root/setProductHistory", {
        category: route.params.category,
        productInfo: result.data,
      });
    })
    .catch((err) => {
      console.log(err)
    })

    watchEffect(() =>{
        console.log(state.productInfo);
        var lastHistory = store.getters["root/getProductHistory"][0]
        if(lastHistory == undefined){
          state.category = route.params.category
          state.pcode = route.params.pcode
        }
        else{
          state.category = store.getters["root/getProductHistory"][0].category;
          state.pcode = route.params.pcode;
        }
    })

    onMounted(() => {
      window.addEventListener('scroll', onScroll);
    })

    return { store, state, onScroll, onMounted }
  },
};

</script>

<style scoped>
.section {
  margin: 0 auto;
  display: flex;
  justify-content: center;
  padding-top: 50px;
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
  height: 380px;
  margin: auto 0px;
  object-fit: contain;
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
  padding-top: 60px;
  padding-left: 30px;
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
  margin: 30px auto;
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

h5 {
  font-family: "SpoqaHanSansNeo-Bold";
  margin: 0px 0px;
}


</style>
