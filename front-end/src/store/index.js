import Vue from "vue";
import Vuex from "vuex";
import axios from "@/api/v1/http-common.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    ui: {
      themeColor: "grey lighten-4",
      drawer: false,
      progress: 0,
    },
    user: {
      jwt: "", // 세션 유지 기능
      auth: false, // 인증 변수로 사용할 예정
      info: {
        url: null,
        id: null,
        name: null,
        email: null,
        phoneNumber: null,
        birthday: null,
        type: null, // 개인 : Poerson account , 부서 : Department account
      },
    },
  },
  // computed로 등록
  getters: {
    getUserInfo(state) {
      return state.user.info;
    },
    getThemeColor(state) {
      return state.ui.themeColor;
    },
    getDrawer(state) {
      return state.ui.drawer;
    },
    userAuth(state) {
      return state.user.auth;
    },
    userJWT(state) {
      return state.user.jwt;
    },
  },
  // method로 등록 (동기)
  mutations: {
    taskCntUp(state) {
      return state.ui.progress++;
    },
    taskCntDown(state) {
      return state.ui.progress--;
    },
    switchDrawer(state, payload = !state.ui.drawer) {
      return (state.ui.drawer = payload);
    },
    login(state, payload) {
      state.user.info = payload.data;
      state.user.jwt = payload.token;
      axios.defaults.headers.common["Authorization"] = payload.token;
      sessionStorage.setItem("session", JSON.stringify(payload.data));
      return (state.user.auth = true);
    },
    session(state, payload) {
      state.user.auth = true;
      return (state.user.info = JSON.parse(payload));
    },
    logout(state, payload = false) {
      sessionStorage.clear();
      state.ui.drawer = false;
      // state.user.info = null;
      return (state.user.auth = payload);
    },
  },
  actions: {},
  modules: {},
});
