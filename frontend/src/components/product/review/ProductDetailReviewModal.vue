<template>
  <div
    class="modal fade"
    tabindex="-1"
    id="productDetailReviewModal"
    ref="productDetailReviewModal"
  >
    <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable" role="document">
      <div class="modal-content" style="height: 750px">
        <div class="modal-header">
          <div class="modal-title" style="width: 80%; text-align: left; margin-left: 30px; margin-top: 30px"><h1>'{{ word }}'에 대한 리뷰</h1></div>
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
          <div id="keyword-review">
            <div v-for="item in 20" :key="item.id">
              <product-detail-reivew-card></product-detail-reivew-card>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductDetailReivewCard from "../review/ProductDetailReviewCard.vue";

export default {
  name: "ProductDetailReviewModal",
  props: {
    word: String,
  },
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

    return { goParent };
  },
};
</script>

<style scoped>
</style>
