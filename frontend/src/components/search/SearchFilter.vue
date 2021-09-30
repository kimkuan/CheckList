<template>
    <div>
        <tabs>
            <tab name="First tab">
                First tab content
            </tab>
            <tab name="Second tab">
                Second tab content
            </tab>
            <tab name="Third tab">
                Third tab content
            </tab>
        </tabs>
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
