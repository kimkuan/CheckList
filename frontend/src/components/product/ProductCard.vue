<template>
    <div class="productCard card mb-4 rounded-3">
        <div class="title mt-3">
            <h5 class="card-title fw-bold">[{{product.brand}}] {{product.name}}</h5>
        </div>
        <div class="row g-0">
            <div class="col-md-4">
                <div class="card-body">
                    <img :src="product.url" alt="상품 이미지" class="img-fluid rounded-3">
                </div>
            </div>
            <div class="col-md-4">
                <div class="card-body">
                    <p class="card-text"><small class="text-muted">{{product.score}}</small></p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card-body">
                    <h5 class="mt-5">{{product.price}}~</h5>
                </div>
            </div>
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
    width: 820px;
    height: 250px;
    margin: auto;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px, rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
    border: none;
}
</style>