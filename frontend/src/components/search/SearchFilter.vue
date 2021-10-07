<template>
    <div class="filter">
        <div class="row g-0">
            <div class="line" v-for="(title, i) in filterTitles" :key=i>
                <div class="name col-md-2">{{title}}</div>
                <div class="contents" v-for="(filter, j) in filters[i]" :key=j>
                    <input type="checkbox" :id="title+':'+filter" class="check" @click="getCheckboxValue">
                    <label :for="title+':'+filter" class="check-label"><span>{{filter}}</span></label>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { onMounted } from 'vue';
import { useStore } from 'vuex';


export default {
    name : "Filter",
    props : {filterTitles: Object, filters: Object},
    components: {
    },
    setup(){
        const store = useStore();
        let json = {};
        onMounted(() => {
            json = {};
            getCheckboxValue();
        })

        function getCheckboxValue()  {
            // 선택된 목록 가져오기
            const check = 'input[type="checkbox"]:checked';
            const selectedEls = document.querySelectorAll(check);

            // 선택된 목록에서 value 찾기
            let filterList = [];
            json = {};
            selectedEls.forEach((el) => {
                // 선택된 필터 목록 저장
                filterList.push(el.id);
                
                // 만원 붙은 경우 카테고리별로 검색이 안되는 경우가 있음..! => 만원 제거, key, value 분리
                let map = el.id.replace('만원', '').split(':');
                let key = map[0];
                let value = map[1];


                // 키가 있는 경우 기존 배열에 값 추가후 key에 넣어줌
                if (!json.hasOwnProperty(key)) {
                    json[key] = [];
                }
                json[key].push(value);
                console.log(json[key]);
                store.commit("root/setSelectedFilter", filterList);
            });
            console.log(filterList);
            // $emit('infiniteHandler', json);
        }
        return {
            getCheckboxValue, json
        }
    },
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
