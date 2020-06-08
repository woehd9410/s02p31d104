import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import "./plugins/bootstrap";
import ScrollAnimation from './directives/scrollanimation'

Vue.directive('scrollanimation', ScrollAnimation);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");

// vuetify .native bug ignore
const ignoredMessage = "The .native modifier for v-on is only valid on components but it was used on <div>.";

Vue.config.warnHandler = (message, vm, componentTrace) => {
    if (message !== ignoredMessage) {
        console.error(message + componentTrace);
    }
};