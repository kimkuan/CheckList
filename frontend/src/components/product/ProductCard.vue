<template>
    <div class="productCard card p-2 m-2 mt-3 rounded-3" @click="clickProductDetail">
        <img :src="product.img" class="card-img-top p-3" alt="상품이미지">
        <div class="card-body p-2 mt-3 mb-4">
            <div class="card-text mb-1">
                <small class="text-muted">{{product.brand}}</small><br />
                <div class="text-name"><strong>{{product.name}}</strong></div><br />
                {{avgScore == undefined ? `리뷰가 없어요 😥` : "⭐️"+avgScore + "점"}}
            </div>
            <p class="card-text">
                <span class="lowest-price fw-bold">최저가  </span>
                <span class="price fw-bold"> {{  $filters.convertPriceVer2(product.price)}}만원</span>
            </p>
        </div>
    </div>
</template>

<script>
import { useRouter } from "vue-router";

export default {
    name : "ProductCard",
    props : ['product', 'avgScore', 'category'],
    setup(props){
        const router = useRouter();
        const clickProductDetail = function () {
            console.log("프로덕트 확인")
            console.log(props.category)
            router.push({ name: "Product" , params: {category : props.category, pcode : props.product.pcode}});
        };

        return { router, clickProductDetail };
    }
}
</script>

<style scoped>
img{
  display: block; margin: 0px auto;
  width:60%;
}
.productCard {
    display: inline;
    width: 300px;
    height: 350px;
    margin: auto;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
    border: none;
    display: inline-block;
    text-align: left;
}

.text-name {
  width:100%;
  text-overflow: ellipsis;
  white-space:nowrap;
  overflow:hidden;

}

.lowest-price {
    color: red;
    font-size: small;
}
.price {
    font-size: 18px;
}
.productCard:hover {
    box-shadow: rgba(0, 0, 0, 0.07) 0px 1px 2px, rgba(0, 0, 0, 0.07) 0px 2px 4px, rgba(0, 0, 0, 0.07) 0px 4px 8px, rgba(0, 0, 0, 0.07) 0px 8px 16px, rgba(0, 0, 0, 0.07) 0px 16px 32px, rgba(0, 0, 0, 0.07) 0px 32px 64px;
}
</style>
