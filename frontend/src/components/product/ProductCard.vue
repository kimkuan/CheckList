<template>
    <div class="productCard card m-2 mb-4 rounded-3">
        <img :src="product.url" class="card-img-top" alt="상품이미지">
        <div class="card-body">
            <p class="card-text mb-1">
                <small class="text-muted">{{product.brand}}</small><br />
                <strong>{{product.name}}</strong><br />
                ⭐️{{product.score}}점
            </p>
            <p class="card-text">
                <span class="lowest-price fw-bold">최저가 </span>
                <span class="price fw-bold">{{product.price}}원</span>
            </p>
        </div>
    </div>
</template>

<script>
export default {
    name : "Product",
    props : ['product'],
    setup(){
    }
}
</script>

<style scoped>
.productCard {
    width: 230px;
    height: 230px;
    margin: auto;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
    border: none;
    display: inline-block;
    text-align: left;
}
.lowest-price {
    color: red;
    font-size: small;
}
.price {
    font-size: 18px;
}
</style>