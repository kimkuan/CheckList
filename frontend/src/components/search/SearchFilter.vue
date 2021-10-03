<template>
    <div class="filter">
        <div class="row g-0">
            <div class="line" v-for="(filter, i) in filters" :key=i>
                <div class="name col-md-2">가격</div>
                <div class="contents" v-for="(content, j) in contents" :key=j>
                    <input type="checkbox" :id="'check'+i+j" class="check">
                    <label :for="'check'+i+j" class="check-label"><span>100</span></label>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name : "Filter",
    props : ['filters', 'contents'],
    components: {
    },
    setup(){
        // console.log(this.filters);
    },
    methods : {
    }
}
</script>
<style scoped>
.filter {
  box-shadow: rgba(0, 0, 0, 0.1) 0px 5px 6px -5px, rgba(0, 0, 0, 0.06) 0px 4px 6px -5px;
  margin-bottom: 50px;
}
.line {
    border-bottom: 0.5px solid rgb(196, 196, 196);
}
.name {
    background-color: #FFF5F5;
    display: inline-block;
    font-size: 18px;
    padding: 10px;
    padding-left: 30px;
}
.contents {
    display: inline-block;
    margin-left: 3px;
    text-align: center;
}

.check {
    display: none;
}

.check-label {
    display: inline-block;
    width: 55px;
    height: 30px;
    margin-left: 6px;
    border-radius: 50px;
    padding: 3px;
}

input:checked ~ .check-label {
    background-color: #CF000F;
    color: white;
}


</style>
