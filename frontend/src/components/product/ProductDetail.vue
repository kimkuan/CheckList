<template>
  <div style="display: flex; justify-content: center">
    <div class="productHead">
      <div class="p-img">
        <!-- 이미지 -->
        <img alt="product sample img" src="@/assets/product_sample.jpg" />
      </div>
      <div class="p-info">
        <!-- 제품 내용 -->
        건조기
        <br />
        <div class="title">
          <div style="width: 75%">
            <h4>삼성전자</h4>
            <h2>제품코드 DV14T8520BV</h2>
          </div>
          <div style="width: 25%; text-align: right">
            <button>비교함<br />담기</button>
          </div>
        </div>
        <br />
        <div>
          <div style="display: inline-block; width: 75%">
            <h1>최저가 1,200,000원</h1>
          </div>
          <div style="display: inline-block; width: 25%; text-align: right">
            <button>사러가기</button>
          </div>
        </div>

        <div>
          <!-- 최저가 추이 그래프 -->
        </div>
      </div>
    </div>

    <div class="core-spec">
      <div class="title">
        <div><h2 style="width: 50%">주요 스펙</h2></div>
        <div style="text-align: right"><button>스펙전체보기</button></div>
      </div>

      <div style="text-align: left; ">
        <div class="spec-item"><p style="margin-bottom: 0px; color: #7E7E7E;">가격대</p><p style="margin-top: 0px">데이터</p></div>
        <div class="spec-item"><p>가격대 <br> 데이터</p></div>
        <div class="spec-item"><p>가격대 <br> 데이터</p></div>
        <div class="spec-item"><p>가격대 <br> 데이터</p></div>
        <div class="spec-item"><p>가격대 <br> 데이터</p></div>
        <div class="spec-item"><p>가격대 <br> 데이터</p></div>
        <div class="spec-item"><p>가격대 <br> 데이터</p></div>
      </div>
    </div>

    <div class="score">
      <h2>상품 점수</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProductDetail",
  setup() {},
};
</script>

<style scoped>

.productHead {
  position: absolute;
  width: 1047px;
  height: 465px;
  /* left: 437px; */
  /* margin: 0px auto; */
  top: 178px;
  /* background-color: lightgray; */
  text-align: center;
  display: flex;
  justify-content: center;
}

.productHead .p-img {
  /* background-image: url('../../assets/product_sample.jpg'); */
  /* background-repeat: no-repeat; */
  /* background-size: contain; */
  width: 40%;
  height: 100%;
  margin: auto 0px;
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

.core-spec {
  /* display: inline-block; */
  position: absolute;
  width: 1048px;
  height: 292px;
  /* left: 437px; */
  /* margin: 0px auto; */
  top: 733px;
  /* background-color: lightgray; */
  text-align: center;
  justify-content: left;
}

.core-spec .title {
  margin: 10px 10px;
  line-height: 15%;
}

.core-spec .title div {
  width: 50%;
  display: inline-block;
  text-align: left;
  vertical-align: middle;
}

.core-spec .title button {
  width: 25%;
  height: 40px;
  border-radius: 15px;
  /* margin-right: 5px; */
  background-color: #FFFFFF;
  border: 0.5px solid #000000;
}

.spec-item {
  display: inline-block;
  width: 18%;
  height: 89px;
  background-color: #fff3f3;
  border-radius: 15px;
  margin: 10px 10px;
  filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.25));
  text-align: center;
  vertical-align: middle;
  /* margin-left: 3%; */
}

.score {
  position: absolute;
  width: 646px;
  height: 487px;
  /* left: 637px; */
  top: 1115px;
  text-align: center;
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
  src: url("../../assets/fonts/SpoqaHanSansNeo-Bold.ttf");
}
</style>
