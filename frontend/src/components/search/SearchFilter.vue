<template>
    <div class="filter">
        <div class="row g-0">
            <div class="line" v-for="(title, i) in filterTitles" :key=i>
                <div class="name col-md-2">{{title}}</div>
                <div class="contents" v-for="(filter, j) in filters[i]" :key=j>
                    <input type="checkbox" :id="'check'+i+j" class="check">
                    <label :for="'check'+i+j" class="check-label"><span>{{filter}}</span></label>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name : "Filter",
    props : ['filterTitles', "filters"],
    components: {
    },
    setup(){
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
    min-width: 55px;
    height: 30px;
    margin-left: 10px;
    border-radius: 50px;
    padding: 3px;
}

input:checked ~ .check-label {
    background-color: #CF000F;
    color: white;
}


</style>
