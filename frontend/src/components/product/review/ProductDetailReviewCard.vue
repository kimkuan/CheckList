<template>
  <div>
    <div class="card-top">
      <div class="star-ratio" v-if="review">
        <div v-for="star in (review.score/20)" :key="star.id">
          <img src="@/assets/star-yellow.png" alt="star-yellow" />
        </div>
      </div>
      <span v-if="review">{{ review.title.substring(0, review.title.length < 20 ? review.title.length : 20)+"..."}}</span>
      <span v-if="review" style="margin-left: 20px">{{review.site }}</span>
    </div>
    <div class="card-content">
      <p class="line-limit" v-if="review" :id="review.id">{{ review.content.substring(0, review.content.length < 100 ? review.content.length : 100) }}</p>
      <div style="width: 12%; margin-top:auto; margin-bottom: 16px; font-size:14px"><button @click="toggleContent(review)">펼쳐보기 ▼</button></div>
    </div>
  </div>
</template>

<script>
// import { computed } from '@vue/reactivity';
import { reactive, toRefs, computed } from 'vue'
import { useStore } from "vuex";

export default {
  name: "ProductDetailReivewCard",
  props: {
    review: Object,
  },
  setup() {

    const state = reactive({
      open : false,
      isOpen : computed(() => {
        console.log(state.open)
      })
    })
    // const store = useStore()
    // const state = reactive({
    //   reviewInfo: computed(() => {
    //     return store.getters["root/getReviewInfo"].reviewList.content[props.review-1];
    //   }),
    //   title: computed(() => {
    //     return store.getters["root/getReviewInfo"].reviewList.content[props.review-1].title;
    //   }),
    // })

    // });

    const toggleContent = (review) => {
      document.getElementById(review.id).classList.toggle('line-limit');
      if(!state.open && review.content.length > 100){
        document.getElementById(review.id).innerText += review.content.substring(100, review.content.length)
        state.open = true;
      }
      else if(state.open){
         document.getElementById(review.id).innerText = document.getElementById(review.id).innerText.substring(0, 100)
         state.open = false;
      }
      // if(id.class) c.className += 'line-limit';
    }


    return { toggleContent }
  },
}
</script>

<style scoped>
.card-top {
  /* display: inline-block; */
  /* width: 830px; */
  text-align: left;
  /* margin: auto 10px; */
  justify-content: left;
}

.card-top span {
  display: inline-block;
  font-size: 15px;
  color: #969696;
}

.star-ratio {
  display: inline-flex;
  width: 90px;
  margin-right: 10px;
}

.star-ratio img {
  display: inline-block;
  width: 13px;
  height: 13px;
  margin: auto 2px;
}

.card-content {
  display: flex;
  text-align: left;
  width: 90%;
  margin-bottom: 10px;
}

.card-content p {
  width: 90%;
}

.card-content div {
  display: inline-block;
  text-align: left;
  width: 90%;
}

.card-content button {
  border: 0px;
  background-color: transparent;
  color: #969696;
  height: fit-content;
}

/* .card-content button {
  display: inline-block;
  width: 15%;
  text-align: left;
} */

.line-limit {
  display: -webkit-box;
  white-space: normal;
  line-height: 20px;
  word-wrap: break-word;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
  width: 90%;
}
</style>
