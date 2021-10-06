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
        title : "피벗? 틸트? 🤔",
        content : ` 에어프라이기의 용량은 재료를 넣는 바스켓 혹은 내부의 부피이며, 2L부터 20L까지 다양합니다. 대부분의 제품이 바스켓 형은 2L~10L, 오븐형은 8~20L 정도입니다.<br><br>
        용량이 커질 수록 한번에 조리할 수 있는 재료의 양이 많아집니다. 뜨거운 공기의 순환이 잘 되어야 조리가 되기 때문에 공간의 여유 없이 재료를 가득 채우면 조리시간이 길어지고, 재료가 고르게 익혀지기가 어렵습니다.<br>
        <div class="img-wrapper">
          <img :src='@/assets/images/airfryer/modal-size.png' class="modal-image"/>
        </div>
        따라서, 2인 이상이 사용한다면 최소 5L 이상, 3~4인 이상이라면 최소 7L 이상의 제품을 추천드립니다.`
      },
      {
        desc : "cook",
        title : "에어프라이기의 조리성능 차이?",
        content : `에어프라이어는 원리자체가 간단하기 때문에 충분한 시간 조리해 주면 조리품질의 차이는 거의 없습니다.<br><br>
        다만, 소비전력, 열기순환정도, 유격, 온도센서 등의 영향으로 조리속도의 차이가 있습니다.<br>
        그 중 조리속도에 가장 많이 영향을 미치는 것이 소비전력입니다. 200℃ 가량의 높은 온도를 유지하기 위해 에어프라이어의 소비전력은 보통 1300W 이상으로 높은데, 일부 제품은 1000W 이하로 온도를 충분히 올리지 못하거나, 조리시간이 길어질 수 있습니다.`
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
