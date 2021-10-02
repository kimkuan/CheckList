<template>
    <div class="dropdown">
        <select v-model="selected">
            <option disabled value="category">카테고리</option>
            <option v-for="category in categories" :key="category.id" :value="category.name"> {{ category.name }}</option>
        </select>
    </div>
</template>

<script>
export default {
    name : "Category",
    props : ['categories'],
    components: {
    },
    data() {
        return {selected:'category'};
    },
    setup(){
    },
    methods : {
    }
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
