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

export default {
  name: 'ProductDetailSpecMonitorModal',
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
        desc : "size",
        title : "피벗? 틸트? 그게 뭐지? 🤔",
        content : ` <h3>피벗 모니터</h3> <br>
        모니터 화면을 세로로 90도 회전시킬 수 있는 모니터를 말합니다. <br>
        상하로  긴 화면을 많이 볼 때, 문서 작업이나 로그 확인에 편리합니다.
        <br><br><br><br>
        <h3>틸트 모니터</h3> <br>
        모니터 화면의 각도를 상하로 저절할 수 있는 모니터를 말합니다. <br>
        가장 기본적인 기능으로 거북목을 방지하는 적절한 화면의 각도를 조절하기에 편리합니다. <br><br>`
      },
      {
        desc : "pixel",
        title : "해상도? 어떻게 골라야 하는 걸까? 🤔",
        content : `<br>해상도란 (가로 방향 픽셀 수 X 세로 방향 픽셀 수)로 표현하며 이를 곱한 값이 화면에 동시 표시되는 픽셀의 수 입니다.<br><br><br><br>
        화면에 동시에 표시되는 픽셀의 수가 커질수록 더 세밀하고 촘촘하게 화면을 표현할 수 있으므로 화질이 선명하게 표현됩니다.<br><br><br>
        `
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
