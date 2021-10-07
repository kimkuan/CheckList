<template>
  <div
    class="modal fade"
    tabindex="-1"
    id="productDetailReviewModal"
    ref="productDetailReviewModal"
    style="background-color:rgba(0, 0, 0, 0.2);"
  >
    <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable" role="document">
      <div class="modal-content" style="height: 750px">
        <div class="modal-header">
          <div class="modal-title" style="width: 80%; text-align: left; margin-left: 30px; margin-top: 30px">
            <h1>'{{ state.wordCloudInfo.word }}'에 대한 리뷰</h1>
          </div>
          <div style="width: 20%; margin-right: 30px; text-align: right;"><button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="goParent"
          ></button></div>
          <hr>
        </div>
        <div class="modal-body p-5 align-items-center">
          <div id="keyword-review" style="margin-left: 15px;">
            <product-detail-reivew-card v-for="review in state.reviewList" :key="review.pcode" :review="review"></product-detail-reivew-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductDetailReivewCard from "../review/ProductDetailReviewCard.vue";
import { computed, reactive, watch } from 'vue'
import { useStore } from "vuex";

export default {
  name: "ProductDetailReviewModal",
  components: {
    ProductDetailReivewCard,
  },
  data() {
    return {
      ProductDetailReviewModal: {},
      // activeDisplay: "flex",
    };
  },
  setup() {
    const store = useStore()
    const state = reactive({
      wordCloudInfo: computed(()=> {
        return store.getters["root/getWordCloudInfo"];
      }),
      page : 0,
      reviewList : [],
    })
    const goParent = () => {
      document.getElementById('productDetailReviewModal').classList.toggle('show');
      document.getElementById('productDetailReviewModal').style.display = 'none';
      document.getElementById('productDetailReviewModal').removeAttribute('aria-modal');
      // document.getElementById('productDetailReviewModal').removeAttribute('role');
      document.getElementById('productDetailReviewModal').setAttribute('aria-hidden', true);
      document.body.classList.toggle('modal-open');
      document.body.removeAttribute('style');
      // emit("call-parent");
    };
    const getReviewList = function() {
      store.dispatch('root/requestReviewListByWord',{
        pcode: state.wordCloudInfo.pcode, 
        word:state.wordCloudInfo.word, 
        page : state.page
      })
      .then(function(result){
        console.log("리뷰 불러오기 성공-============================")
        console.log(result.data)
        state.reviewList = result.data;
      })
    }
    

    watch(() => state.wordCloudInfo,
      (after, before) => {
        state.wordCloudInfo.page = 0;
        getReviewList();
      }
    )

    return { store, state, goParent, getReviewList };
  },
};
</script>

<style scoped>
</style>
