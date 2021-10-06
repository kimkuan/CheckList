<template>
  <div
    class="modal fade"
    tabindex="-1"
    id="productAllSpecModal"
    ref="productAllSpecModal"
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
            <div style="text-align: center;"><h1>전체 스펙</h1></div>
            <div style="justify-content: center; text-align: center">
              <img alt="product sample img" src="@/assets/product_sample.jpg" />
              <table class="table" style="width: 70%; margin: auto auto; vertical-align: middle;">
                <tbody style="text-align: center; vertical-align: middle">
                  <tr>
                    <th scope="row" rowspan="4">제품정보</th>
                    <th>브랜드</th>
                    <td>리빙코리아</td>
                  </tr>
                  <tr>
                    <th>제품명</th>
                    <td>리빙웰</td>
                  </tr>
                  <tr>
                    <th>제품명</th>
                    <td>리빙웰</td>
                  </tr>
                  <tr>
                    <th>제품명</th>
                    <td>리빙웰</td>
                  </tr>
                  <tr>
                    <th scope="row" rowspan="51">기본사양</th>
                    <th>형태</th>
                    <td>바스켓형</td>
                  </tr>
                  <tr v-for="item in 50" :key="item.id">
                    <th>형태</th>
                    <td>바스켓형</td>
                  </tr>
                  <tr>
                    <th scope="row">어쩌고</th>
                    <th scope="row">저쩌고</th>
                    <td>ㄷㄷㄷ</td>
                  </tr>
                </tbody>
              </table>
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
  name: "ProductAllSpecModal",
  props: {
    word: String,
  },
  data() {
    return {
      ProductAllSpecModal: {},
      // activeDisplay: "flex",
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

table th {
  width: 33.3%;
  background-color: #F5F6F9;
}

table td {
  width: 33.3%;
}
</style>
