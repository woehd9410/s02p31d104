<template>
  <!-- npm install vuelidate -->
  <v-layout class="justify-center">
    <v-flex>
      <v-row justify="space-around">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
          <v-hover elevation="0" v-slot:default="{ hover }">
            <v-card @click="person()" height="60vh">
              <div style="text-align:center; height:50vh">
                <v-img
                  style="height:100%"
                  src="https://vipinestates.com/wp-content/uploads/2016/02/3ffdb08aafb723e161722847da87a058-male-man-avatar.jpg"
                />
              </div>

              <v-flex class="text-center">
                <v-card-text class="display-1">개인</v-card-text>
              </v-flex>
              <v-expand-transition>
                <div
                  v-if="hover"
                  class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                  style="height: 100%;"
                >
                  개인입니다아아(문구 임시로 한거임)
                </div>
              </v-expand-transition>
            </v-card>
          </v-hover>
        </div>

        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
          <v-hover elevation="0" v-slot:default="{ hover }">
            <v-card @click="department()">
              <div style="text-align:center; height:50vh">
                <v-img
                  style="height:100%"
                  src="https://image.flaticon.com/icons/svg/1642/1642256.svg"
                />
              </div>

              <v-flex class="text-center">
                <v-card-text class="display-1">부서</v-card-text>
              </v-flex>
              <v-expand-transition>
                <div
                  v-if="hover"
                  class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                  style="height: 100%;"
                >
                  부서입니다아아(문구 임시로 한거임)
                </div>
              </v-expand-transition>
            </v-card>
          </v-hover>
        </div>
      </v-row>
    </v-flex>
  </v-layout>
</template>

<script>
import { mdiAccount, mdiArrowLeft } from "@mdi/js";
import { EventBus } from "../plugins/eventBus.js";
export default {
  data: () => ({
    hover: false,
    icon_person: mdiAccount,
    icon_back: mdiArrowLeft,
  }),
  components: {},
  created() {
    EventBus.$on("use-eventbus", (flag) => {
      this.flag = flag;
    });
  },
  methods: {
    person() {
      console.log("개인 로그인 폼 띄우기");
      this.$emit("selectJoinType", "Person");
    },

    department() {
      console.log("부서 로그인 폼 띄우기");
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
    },
  },
};
</script>
<style scoped>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}
</style>
