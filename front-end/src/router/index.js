import Vue from "vue";
import VueRouter from "vue-router";
// Routes
import paths from "./paths";
import store from '@/store/index.js';

function route(path, sidebar, view, name) {
  return {
    name: name || view,
    path,
    components: {
      default: (resolve) => import(`@/views/${view}.vue`).then(resolve),
      sidebar: (resolve) => import(`@/views/sidebar/${sidebar == null ? view : sidebar}.vue`).then(resolve),
    },
  };
}

Vue.use(VueRouter);

// Create a new router
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: paths
    .map((path) => route(path.path,path.sidebar, path.view, path.name))
    .concat([
      {
        path: "*",
        redirect: "/",
      },
    ]),
});

router.beforeEach((to, from, next) => {
  store.commit("taskCntUp")
  let session = sessionStorage.getItem("session");
  let token = sessionStorage.getItem("token");
  console.log("router beforEach session status check");
  console.log(session);
  console.log(token);
  if (session) {
    store.commit("session", session);
    store.commit("token", token);
  }
  if (to && session) {
    next();
  }
  store.commit("taskCntDown")
});

export default router;

