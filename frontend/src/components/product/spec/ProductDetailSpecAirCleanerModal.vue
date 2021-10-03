<template>
  <div
    class="modal fade"
    tabindex="-1"
    id="ProductDetailSpecAirCleanerModal"
    ref="ProductDetailSpecAirCleanerModal"
    style="background-color:rgba(0, 0, 0, 0.2);"
  >
    <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable" role="document">
      <div class="modal-content" style="height: fit-content;">
        <div class="modal-header">
          <div class="modal-title" style="width: 80%; text-align: left; margin-left: 30px;"></div>
          <div style="width: 20%; margin-right: 30px; text-align: right;"><button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button></div>
          <hr>
        </div>
        <div class="modal-body p-5 align-items-center">
          <div>
            <div style="text-align: center;"><h1>청정 면적</h1></div>
            <img alt="product sample img" style="width:90%" src="@/assets/images/aircleanerArea.png" />
            <div style="text-align: left">
                <br>
                표기된 공기청정기의 사용면적은 <strong>최대 세기</strong>로 작동시켰을 때의 기준입니다.<br>
                일반적으로 <strong>팬 소음때문에 항상 최대 세기로 작동시키기 어렵기 때문에</strong><br>
                충분한 공기청정 효과를 기대하기 위해서 <strong>최소한 '실 면적X1.5배' </strong>사용면적을 갖춘 제품을 추천합니다.<br><br>
                <strong>실 공간</strong>의 크기를 정확히 파악하는 것이 어렵기 때문에<br>
                <strong>위 사진을 참고</strong>하여 집의 크기와 사용할 공간을 고려하여 상품을 결정하세요.
            </div>
          </div>
        </div>
        <div class="modal-footer"></div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "ProductDetailSpecAirCleanerModal",
  props: {
    word: String,
  },
  data() {
    return {
      ProductDetailSpecAirCleanerModal: {},
    };
  },
  setup() {

    return {  };
  },
};
</script>

<style scoped>
h1 {
  font-family: "SpoqaHanSansNeo-Bold";
}

.modal-content {
  height: fit-content;
  max-height: 80%;
}

img {
  width: 40%;
  object-fit: cover;
  margin: 0px auto;
}

</style>
