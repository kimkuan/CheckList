<template>
    <div class="dropdown">
        <select v-model="selected">
            <option disabled value="category">카테고리</option>
            <option>A</option>
            <option>B</option>
            <option>C</option>
    </select>
    </div>
</template>

<script>
export default {
    name : "Category",
    props : ['categories'],
    components: {
    },
    setup(){
    },
    methods : {
    }
}
</script>
<style scoped>
.dropdown {
    text-align: center;
}
</style>
