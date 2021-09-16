import { createStore } from "vuex";
import VuexPersistence from "vuex-persist";

const vuexLocal = new VuexPersistence({
  storage: window.localStorage,
  supportCircular: true,
});

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  modules: {},
  plugins: [ vuexLocal.plugin ],
});
