<template>
    <div>
        <tabs v-model="selectedTab">
            <tab
            class="tab"
            val="checkPick"
            label="체크픽"
            indicator="true"
            />
            <tab
            class="tab"
            val="filter"
            label="직접탐색"
            indicator="true"
            />
        </tabs>

        <tab-panels
            v-model="selectedTab"
            :animate="true"
        >
            <tab-panel
            val="checkPick"
            >
            체크리스트에서 분석한 내용을 바탕으로 상품을 추천받아 보세요

            </tab-panel>
            <tab-panel
            val="filter"
            >
            스펙을 원하는 대로 선택해서 상품을 찾을 수 있어요
            </tab-panel>
        </tab-panels>
    </div>
</template>

<script>
import {Tabs} from "./SearchTabs.vue";
import {Tab} from "./SearchTab.vue";

export default {
    name : "Product",
    props : ['product'],
    components: {
        Tabs,
        Tab,
    },
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
