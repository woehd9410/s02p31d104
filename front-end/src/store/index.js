import Vue from "vue";
import Vuex from "vuex";
import axios from "@/api/v1/http-common.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    ui: {
      themeColor: "grey lighten-4",
      drawer: true,
      progress: 0,
      snackbar: false,
      snackbarText: "",
      snackbarColor: "",
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
    schedule: {
      list: [],
      now: `${new Date().getFullYear()}-${
        (new Date().getMonth() + 1).toString().length < 2
          ? "0" + (new Date().getMonth() + 1)
          : new Date().getMonth() + 1
      }-${
        new Date().getDate().toString().length < 2
          ? "0" + new Date().getDate()
          : new Date().getDate()
      }`,
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
    scheduleInfo(state) {
      let scheduleList = [];
      for (let s of state.schedule.list) {
        if (s.start_time == null) continue;
        let tmpScheduleObj = {
          name: s.title,
          start: s.start_time.substr(0, 16),
          end: s.end_time.substr(0, 16),
          color: s.color,
        };
        scheduleList.push(tmpScheduleObj);
      }
      return scheduleList;
    },
    now(state) {
      return state.schedule.now;
    },
  },
  // method로 등록 (동기)
  mutations: {
    snackbar(state, payload) {
      state.ui.snackbar = true;
      state.ui.snackbarText = payload.text;
      state.ui.snackbarColor = payload.color;
    },
    taskCntUp(state) {
      return state.ui.progress++;
    },
    taskCntDown(state) {
      if (state.ui.progress > 0) return state.ui.progress--;
      else return state.ui.progress;
    },
    switchDrawer(state, payload = !state.ui.drawer) {
      return (state.ui.drawer = payload);
    },
    login(state, payload) {
      console.log("store mutations login");
      state.user.info = payload.data;
      sessionStorage.setItem("session", JSON.stringify(payload.data));
      axios.defaults.headers.common["Authorization"] = payload.token;
      sessionStorage.setItem("token", payload.token);
      state.user.jwt = payload.token;
      return (state.user.auth = true);
    },
    session(state, payload) {
      console.log("store mutations session");
      state.user.auth = true;
      return (state.user.info = JSON.parse(payload));
    },
    token(state, payload) {
      console.log("store mutations token");
      axios.defaults.headers.common["Authorization"] = payload;
      return (state.user.jwt = payload);
    },
    logout(state, payload = false) {
      sessionStorage.clear();
      state.ui.drawer = false;
      // state.user.info = null;
      return (state.user.auth = payload);
    },
    setScheduleInfo(state, payload) {
      return (state.schedule.list = payload);
    },
    pushScheduleInfo(state, payload) {
      return state.schedule.list.push(payload);
    },
    setScheduleNow(state, payload) {
      return (state.schedule.now = payload);
    },
  },
  actions: {},
  modules: {},
});
