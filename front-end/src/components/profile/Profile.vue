<template>
  <v-content>
    <v-container fluid wrap>
      <v-layout v-if="profileInfo" row my-10 class="justify-center">
        <v-flex lg6 md8 xs12 class="text-center">
          <v-avatar size="164"><v-img style="border-radius:70px" :src="profileInfo.url"></v-img> </v-avatar>
          <div v-if="flag == false">
            <v-list>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item class="justify-center">
                    <div class="headline font-weight-bold mb-3">
                      <div class="">
                        {{ profileInfo.name }}
                      </div>
                    </div>
                  </v-list-item>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    <v-icon>{{ email_icon }}</v-icon> 이메일
                  </v-list-item-title>
                </v-list-item-content>
                <v-list-item-content>
                  <v-list-item-title>
                    {{ profileInfo.email }}
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    <v-icon>{{ phone_icon }}</v-icon> 전화번호
                  </v-list-item-title>
                </v-list-item-content>
                <v-list-item-content>
                  <v-list-item-title>
                    {{ profileInfo.phone_number | phoneNumberFilter }}
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    <v-icon>{{ recommend_icon }}</v-icon> 찜 코드
                  </v-list-item-title>
                </v-list-item-content>
                <v-list-item-content>
                  <v-list-item-title> {{ profileInfo.name }}#{{ userInfo.id }} </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </div>

          <v-icon class="mt-5" large v-if="$route.params.id == userInfo.id && flag == false" @click="updateUser()">mdi-cog-outline</v-icon>
          <v-icon class="mt-5" large v-if="$route.params.id != userInfo.id && flag == false" @click="flag = !flag">mdi-calendar-check</v-icon>

          <div v-if="$route.params.id != userInfo.id && flag == true">
            <div class="text-right"><v-icon @click="flag = !flag" large>mdi-close</v-icon></div>
            <schedule :key="profileIdKey" :scheduleArr="scheduleArr"></schedule>
          </div>
        </v-flex>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
import { mdiEmailOutline, mdiKey, mdiPhone } from "@mdi/js";
import phoneNumberFilter from "@/utils/filters/phoneNumberFilter.js";
import axiosScript from "@/api/axiosScript.js";
import schedule from "@/components/schedule/schedule.vue";

export default {
  filters: {
    phoneNumberFilter: phoneNumberFilter,
  },
  components: {
    schedule,
  },
  mounted() {
    console.log(`Profile mounted route parms.id : ${this.$route.params.id}`);
    console.log(`Profile mounted userInfo.id : ${this.userInfo.id}`);

    if (this.$route.params.id == this.userInfo.id) {
      console.log(`My Profile page`);
      this.profileInfo = this.userInfo;
    }
  },
  watch: {
    $route(to) {
      this.flag = false;
      if (to.params.id == this.userInfo.id) {
        console.log("Profile watch route eq to.params.id and userInfo.id");
        this.profileInfo = this.userInfo;
        return;
      }
      console.log(`Profile watch route to.params.id : ${to.params.id}`);
      this.scheduleArr = [];
      this.bringProfile(to.params.id);
      this.profileIdKey = to.params.id;
      if (this.$route.params.id != this.userInfo.id) {
        axiosScript.searchScheduleById(
          this.$route.params.id,
          (res) => {
            console.log(this.$route.params.id + " :: 스케줄 불러오기 성공!!");
            console.log(res.data);

            for (let userSchedule of res.data) {
              let s = "";
              let e = "";

              if (userSchedule.start_time == null) {
                //비었다면 임의로 현재시간 넣어주기
                let date = new Date();
                s =
                  date.getFullYear() +
                  "-" +
                  (date.getMonth() + 1) +
                  "-" +
                  date.getDate() +
                  " " +
                  date.getHours() +
                  ":" +
                  date.getMinutes() +
                  ":" +
                  date.getSeconds();
              } else {
                s = new String(userSchedule.start_time).substr(0, 16);
              }
              console.log("원래 시작 시간::::  " + userSchedule.start_time);
              console.log("바꾼 시작 시간::::  " + s);

              if (userSchedule.end_time == null) {
                let date = new Date();
                e =
                  date.getFullYear() +
                  "-" +
                  (date.getMonth() + 1) +
                  "-" +
                  date.getDate() +
                  " " +
                  date.getHours() +
                  ":" +
                  date.getMinutes() +
                  ":" +
                  date.getSeconds();
              } else {
                e = new String(userSchedule.end_time).substr(0, 16);
              }

              let scheduleArr = {
                name: userSchedule.title,
                start: s,
                end: e,
                color: userSchedule.color,
              };
              this.scheduleArr.push(scheduleArr);
              console.log("-----------------------------");
              console.log("-----------------------------");
              console.log("-----------------------------");
              console.log(this.scheduleArr);
            }
          },
          (err) => {
            console.log("스케줄 불러오기 실패ㅜㅜ");
            console.log(err);
          },
          () => {}
        );
      }
    },
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  data: () => ({
    profileInfo: null,
    email_icon: mdiEmailOutline,
    recommend_icon: mdiKey,
    phone_icon: mdiPhone,
    scheduleArr: [],
    flag: false,
  }),
  methods: {
    updateUser() {
      console.log("회원정보 수정하기");
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
    },
    bringProfile(id) {
      axiosScript.searchUserByOptions(
        id,
        (res) => {
          this.profileInfo = res.data[0];
          console.log("Profile bringProfile method (bring favorite info) ");
          console.log(res.data[0]);
        },
        (err) => console.log(err)
      );
    },
  },
};
</script>
<style scoped>
.underlined {
  text-decoration: none;
  font-weight: bold;
  position: relative;
  z-index: 1;
  display: inline-flex;
  padding-left: 10px;
  padding-bottom: 5px;
  padding-right: 10px;
}
.underlined::before {
  content: "";
  width: 100%;
  height: 80%;
  background-image: linear-gradient(to top, #85b9f5 25%, rgba(0, 0, 0, 0) 40%);
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>
