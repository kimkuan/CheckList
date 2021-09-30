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
  convertPrice(price) {
    var result = price.toLocaleString();
    return result;
  }
}

app.mount("#app");
