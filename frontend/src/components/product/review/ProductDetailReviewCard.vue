<template>
  <div>
    <div class="card-top">
      <div class="star-ratio">
        <div v-for="item in 5" :key="item.id">
          <img src="@/assets/star-yellow.png" alt="star-yellow" />
        </div>
      </div>
      <span>2021. 05.21</span>
      <span>11번가</span>
    </div>
    <div class="card-content">
      <div style="width: 90%; margin-right: 10px;">
        <p>약 한달전에 건조기를 구매했던 사람입니다. 구매한지 이틀만에 버튼이 부식되고 일주일이 지나자 건조기에서 연기가 나더니 지금은 비행기가 이륙하면 나는소리가 건조기에서 나고있네요. 없는 형편에 한푼이라도 아껴보려고 저렴한 제품을 구매했더니 한달만에 고장이 났습니다. 돈 더쓰더라도 좋은 제품 사세요 고객센터 대응도 엉망입니다 완전 최악이에요 </p>
      </div>
      <div style="width: 10%; margin-top:auto; margin-bottom: 16px"><button>펼쳐보기</button></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProductDetailReivewCard",
  setup() {

  },
}
</script>

<style scoped>
.card-top {
  /* display: inline-block; */
  /* width: 830px; */
  text-align: left;
  /* margin: auto 10px; */
  justify-content: left;
}

.card-top span {
  display: inline-block;
  margin: auto 10px;
  font-size: 15px;
  color: #969696;
}

.star-ratio {
  display: inline-flex;
  width: fit-content;
  margin-right: 10px;
}

.star-ratio img {
  display: inline-block;
  width: 13px;
  height: 13px;
  margin: auto 2px;
}

.card-content {
  display: flex;
  /* text-align: left; */
}

.card-content div {
  display: inline-block;
  text-align: left;
}

.card-content button {
  border: 0px;
  background-color: transparent;
  color: #969696;
  height: fit-content;
}

/* .card-content button {
  display: inline-block;
  width: 15%;
  text-align: left;
} */
</style>
