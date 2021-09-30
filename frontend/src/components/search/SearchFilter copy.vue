<template>
    <div class="searchFilter">
        <!-- Tabs navs -->
        <ul class="nav nav-tabs nav-justified mb-3" id="tab" role="tablist" >
            <li class="nav-item" role="presentation">
                <a
                class="nav-link active"
                id="checkPick-tab"
                data-toggle="tab"
                href="#checkPick"
                role="tab"
                aria-selected="true"
                >체크 Pick</a
                >
            </li>
            <li class="nav-item" role="presentation">
                <a
                class="nav-link"
                id="filter-tab"
                data-toggle="tab"
                href="#filter"
                role="tab"
                aria-selected="false"
                >직접 탐색</a
                >
            </li>
        </ul>
        <!-- Tabs navs -->

        <!-- Tabs content -->
        <div class="tab-content" id="search-content">
            <div
                class="tab-pane fade show active"
                id="checkPick"
                role="tabpanel"
            >
                체크리스트에서 분석한 내용을 바탕으로
                상품을 추천받아 보세요
            </div>
            <div
                class="tab-pane fade"
                id="filter"
                role="tabpanel"
            >
                스펙을 원하는 대로 선택해서
                상품을 찾을 수 있어요
            </div>
        </div>
        <!-- Tabs content -->
    </div>
</template>

<script>
export default {
    name : "Product",
    props : ['product'],
    setup(){
    },
    methods : {
    }
    
}
</script>
<style scoped>
.searchFilter {
    margin-left: 80px;
    margin-right: 80px;
}
</style>