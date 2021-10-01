<template>
<div class="modal fade" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">에어프라이기 용량은 어느정도가 적당할까? 🤔</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        에어프라이기의 용량은 재료를 넣는 바스켓 혹은 내부의 부피이며, 2L부터 20L까지 다양합니다. 대부분의 제품이 바스켓 형은 2L~10L, 오븐형은 8~20L 정도입니다.<br><br>
        용량이 커질 수록 한번에 조리할 수 있는 재료의 양이 많아집니다. 뜨거운 공기의 순환이 잘 되어야 조리가 되기 때문에 공간의 여유 없이 재료를 가득 채우면 조리시간이 길어지고, 재료가 고르게 익혀지기가 어렵습니다.<br>
        <div class="img-wrapper">
          <img :src="require('@/assets/images/airfryer/modal-size.png')" class="modal-image"/>
        </div>
        따라서, 2인 이상이 사용한다면 최소 5L 이상, 3~4인 이상이라면 최소 7L 이상의 제품을 추천드립니다.
      </div>
    </div>
  </div>
</div>
</template>

<style scoped>
.modal-dialog-scrollable .modal-content {
  max-height: 600px;
}
.img-wrapper {
  width: 100%;
  text-align: center;
  margin: 5px 0 5px 0;
}
.img-wrapper > .modal-image {
  width: 450px;
  object-fit: contain; /* 이미지 비율 유지 */
}
.modal-header > .modal-title{
  font-size: 1.25rem;
}
.modal-body {
  padding: 20px;
}
</style>

<script>
// import { useStore } from "vuex";

export default {
  name: 'ProductDetailSpecAirFryerModal',
  setup(){

    return { }
  }
}
</script>
