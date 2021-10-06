<template>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="#" v-for="(page, idx) in totalPages" :key="idx" @click="movePage(idx)">{{ page }}</a>
            </li>
        </ul>
    </nav>
</template>

<script>
export default {
    name: 'Paging',
    props: ["totalPages"],
    emits: ["movePage"],

    setup({ emit }) {
        const movePage = (page) => {
            page += 1;
            console.log(page);
            emit('movePage', {page});
        };

        return {
            movePage,
        }
    },
};
</script>
<style scoped>
.page-link {
    display: inline-block;
}
</style>