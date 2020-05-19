import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    ui: {
      themeColor: "grey lighten-4",
      drawer: false,
    },
    user: {
      jwt: null,
      id: null,
      tmpAuth: false,
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
    userAuth(state) {
      return state.user.tmpAuth;
    },
  },
  // method로 등록 (동기)
  mutations: {
    switchDrawer(state, payload = !state.ui.drawer) {
      return (state.ui.drawer = payload);
    },
    login(state, payload = true) {
      return (state.user.tmpAuth = payload);
    },
    logout(state, payload = false) {
      return (state.user.tmpAuth = payload);
    },
  },
  actions: {},
  modules: {},
});
