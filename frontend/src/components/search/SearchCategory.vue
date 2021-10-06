<template>
    <div class="dropdown">
        <select v-model="selected" @change="changeCategory(selected)">
            <option disabled value="category">카테고리</option>
            <option v-for="category in categories" :key="category.id" :value="category.id"> {{ category.name }}</option>
        </select>
    </div>
</template>

<script>
import { useStore } from 'vuex';

export default {
    name : "Category",
    props : ['categories'],
    components: {
    },
    setup(){
        const store = useStore();
        const selected= store.getters["root/getSelectCategoryName"];

        const changeCategory = (selected, event) => {
            store.commit("root/setSelectCategoryName", selected);
            console.log("selected category: " + selected);

            const val = event.target.parentNode;
            const keyword = ptag.querySelector("input")
            const inputText = keyword.value
            if (inputText){
                this.$emit('input-change', inputText)
                keyword.placeholder = keyword.value
                keyword.value = null
            }
        }

        return {
            selected,
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
