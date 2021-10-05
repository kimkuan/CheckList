<template>
    <div class="dropdown">
        <select v-model="selected">
            <option disabled value="category">카테고리</option>
            <option v-for="category in categories" :key="category.id" :value="category.id" @click="changeCategory(category.id)"> {{ category.name }}</option>
        </select>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import { reactive } from 'vue';

export default {
    name : "Category",
    props : ['categories'],
    components: {
    },
    // data() {
    //     return {selected:'aircleaner'};
    // },
    setup(){
        const store = useStore();
        const state = reactive({
            selected: store.getters["root/getSelectCategoryName"]
        })

        const changeCategory = (id) => {
            store.commit("root/setSelectCategoryName", id);
        }

        return {
            state,
            changeCategory
        };
    },
}
</script>
<style scoped>
.dropdown {
    font-size: 18px;
    text-align: center;
    margin: 20px;
}
select {
    border: none;
}
</style>
