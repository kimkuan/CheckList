<template>
    <div class="productCard card p-2 m-2 mt-3 rounded-3" @click="clickProductDetail">
        <img :src="product.img" class="card-img-top p-3" alt="상품이미지">
        <div class="card-body p-2 mt-3 mb-4">
            <p class="card-text mb-1">
                <small class="text-muted">{{product.brand}}</small><br />
                <strong>{{product.name}}</strong><br />
                ⭐️{{product.score}}점
            </p>
            <p class="card-text">
                <span class="lowest-price fw-bold">최저가 </span>
                <span class="price fw-bold">{{ $filters.convertPrice(product.price) }}원</span>
            </p>
        </div>
    </div>
</template>

<script>
import { reactive } from 'vue';
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
    name : "ProductCard",
    props : ['product'],
    setup(props){
        const store = useStore();
        const router = useRouter();
        const state = reactive({
            product : props.product,
        });
        const clickProductDetail = function () {
            store.commit("root/setProductId", state.product.pcode);
            router.push({ name: "Product", params: {category : 'coffeemachine', pcode : state.product.pcode} });
        };

        return { router, clickProductDetail };
    }
}
</script>

<style scoped>
.productCard {
    display: inline;
    width: 230px;
    height: 400px;
    margin: auto;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
    border: none;
    display: inline-block;
    text-align: left;
    vertical-align: middle;
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