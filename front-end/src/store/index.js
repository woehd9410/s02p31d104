import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    ui: {
      themeColor: "blue accent-4",
      drawer: false,
    },
  },
  // computed로 등록
  getters: {
    getThemeColor(state) {
      return state.ui.themeColor;
    },
    getDrawer(state) {
      return state.ui.drawer;
    },
  },
  // method로 등록 (동기)
  mutations: {
    switchDrawer(state, payload = !state.ui.drawer) {
      return (state.ui.drawer = payload);
    },
  },
  actions: {},
  modules: {},
});
