import Vue from "vue";
import VueRouter from "vue-router";
// Routes
import paths from "./paths";
import store from '@/store/index.js';

function route(path, view, name) {
  return {
    name: name || view,
    path,
    components: {
      default: (resolve) => import(`@/views/${view}.vue`).then(resolve),
      sidebar: (resolve) => import(`@/views/sidebar/${view}.vue`).then(resolve),
    },
  };
}

Vue.use(VueRouter);

// Create a new router
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: paths
    .map((path) => route(path.path, path.view, path.name))
    .concat([
      {
        path: "*",
        redirect: "/",
      },
    ]),
});

router.beforeEach((to, from, next) => {
  let session = sessionStorage.getItem("session");
  store.commit("taskCntUp")
  console.log("router beforEach session status check");
  console.log(session);
  if (session) {
    store.commit("session", session);
  }
  if (to && session) {
    next();
  }
  store.commit("taskCntDown")
});

export default router;

