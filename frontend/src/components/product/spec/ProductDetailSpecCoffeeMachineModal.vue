<template>
<div class="modal fade" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <div class="modal-title" id="modal-title">{{ state.title }}</div>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="modal-body" v-html="state.body"></div>
    </div>
  </div>
</div>
</template>

<style scoped>
.modal-dialog-scrollable .modal-content {
  max-height: 600px;
}
.img-wrapper {
  width: 100%;
  text-align: center;
  margin: 5px 0 5px 0;
}
.img-wrapper > .modal-image {
  width: 450px;
  object-fit: contain; /* 이미지 비율 유지 */
}
.modal-header > .modal-title{
  font-size: 1.25rem;
}
.modal-body {
  padding: 20px;
}
</style>

<script>
import { reactive, watchEffect } from '@vue/runtime-core'
import { useStore } from 'vuex'
// import { useStore } from "vuex";

export default {
  name: 'ProductDetailSpecCoffeeMachineModal',
  props : {
    desc : {
      type: String,
      require: true,
      default:'',
    },
  },

  setup(){

    const store = useStore()
    const contents = [
      {
        desc : "price",
        title : "커피머신 가격 어느 정도일까? 🤔 ",
        content : ` 커피머신의 가격대는 6~60만원대로 형성되어 있습니다.
        일반적으로 물통 용량, 캡슐 수거함의 크기, 우유 스팀 등 다양한 기능이 추가될수록 가격이 비싸지는 편입니다.<br><br>`
      },
      {
        desc : "pressure",
        title : "펌프 압력이란?",
        content : `캡슐 커피머신은 대부분 펌프 압력을 이용해 커피를 추출합니다. <br><br>

                    작동원리는 간단하게 내부 장치로 물을 끓인 후 뜨거워진 물을 펌프가 고압으로 추출 장치로 밀어내 커피를 추출하는 방식입니다.<br><br>

                    이때, 펌프가 뜨거운 물을 밀어내는 압력을 펌프 압력이라 하고 단위는 bar를 사용합니다.<br><br>

                  가정용 커피머신의 펌프 압력은 대부분 15~21bar 정도이며, 보통 15bar 이상이 되면 추출 성능에는 크게 차이가 없는 수준입니다.<br><br>`
      }
    ]

    const state = reactive({
      title : '',
      body : '',
    })

    watchEffect(() => {
      changeContent(store.getters["root/getModal"])
    })

    function changeContent(desc) {
      for(var i in contents){
          if(desc == contents[i].desc) {
            state.title = contents[i].title
            state.body = contents[i].content
          }
        }
    }

    return { state, contents }
  }
}
</script>
