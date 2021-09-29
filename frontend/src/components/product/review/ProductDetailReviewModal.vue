<template>
  <div
    class="modal"
    tabindex="-1"
    id="productDetailReviewModal"
    ref="productDetailReviewModal"
  >
    <div class="modal-dialog modal-xl modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <div class="modal-title"><strong>'{{ word }}'에 대한 리뷰</strong></div>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="goParent"
          ></button>
          <hr>
        </div>
        <div class="row no-gutters">
          <div class="modal-body p-5 d-flex align-items-center">
            <div id="keyword-review">
              <div v-for="item in 5" :key="item.id">
                <product-detail-reivew-card></product-detail-reivew-card>
              </div>
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
      activeDisplay: "flex",
    };
  },
  setup(props, { emit }) {
    const goParent = () => {
      emit("call-parent");
    };

    return { goParent };
  },
};
</script>

<style scoped>
  .modal {
    display: block;
  }
</style>
