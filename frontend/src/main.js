import { createApp, h } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueAxios from "./common/axios";
import axios from "./common/axios";

const app = createApp({
  render: () => h(App)
});

app.use(VueAxios, axios);
app.use(store);
app.use(router);

// filters
app.config.globalProperties.$filters = {
  // 100000 -> 100,000
  convertPrice(price) {
    var result = price.toLocaleString();
    return result;
  },

  // 만원 단위로 자르기
  convertPriceVer2(price) {
    var result = (price+0)
    if(result < 10000)
      return 1
    else
      return Math.round(result / 10000)
  },
}

app.mount("#app");
