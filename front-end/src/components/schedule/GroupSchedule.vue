<template>
  <div>
    <div v-if="!($vuetify.breakpoint.xs || $vuetify.breakpoint.sm)">
      <v-layout row wrap align-center v-if="!fullScreenCalendar">
        <v-flex>
          <Schedule :groupScheduleInfo="groupScheduleInfo" />
        </v-flex>
        <v-flex>
          <ParticipateUserList :participateUserList="participateUser" />
          <Chat :key="rerender" @inChat="inChatEvent" />
        </v-flex>
      </v-layout>
      <Schedule v-else :groupScheduleInfo="groupScheduleInfo" />
    </div>
    <Schedule v-else :groupScheduleInfo="groupScheduleInfo" />
    <v-speed-dial
      v-model="actionButton.fab"
      absolute
      dark
      bottom
      right
      :direction="actionButton.direction"
      :open-on-hover="actionButton.hover"
      :transition="actionButton.transition"
    >
      <template v-slot:activator>
        <v-btn v-model="actionButton.fab" color="blue darken-2" dark fab>
          <v-icon v-if="actionButton.fab">mdi-close</v-icon>
          <v-icon v-else>mdi-account-multiple-outline</v-icon>
        </v-btn>
      </template>
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-btn
            fab
            dark
            small
            color="green"
            @click="fullScreenCalendar = !fullScreenCalendar"
            v-if="!($vuetify.breakpoint.xs || $vuetify.breakpoint.sm)"
            v-on="on"
          >
            <v-icon>{{
              !fullScreenCalendar ? "mdi-fullscreen" : "mdi-fullscreen-exit"
            }}</v-icon>
          </v-btn>
        </template>
        <span>{{
          !fullScreenCalendar
            ? "Full Screen Calendar"
            : "Show Together Chatting"
        }}</span>
      </v-tooltip>
      <v-dialog v-model="chatModalFlag" persistent max-width="290">
        <template v-slot:activator="{ on }">
          <v-btn
            fab
            dark
            small
            color="warning"
            v-on="on"
            v-if="$vuetify.breakpoint.xs || $vuetify.breakpoint.sm"
            @click="chatModalFlag = !chatModalFlag"
          >
            <v-icon>mdi-chat</v-icon>
          </v-btn>
        </template>
        <v-card>
          <Chat :key="rerender" />
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="chatModalFlag = false"
              >Disagree</v-btn
            >
            <v-btn color="green darken-1" text @click="chatModalFlag = false"
              >Agree</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-btn
            fab
            dark
            small
            color="indigo"
            v-on="on"
            @click="addGroupSchedule"
          >
            <v-icon>mdi-calendar-plus</v-icon>
          </v-btn>
        </template>
        <span>+ Group Schedule</span>
      </v-tooltip>
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-btn
            fab
            dark
            small
            color="error"
            v-on="on"
            @click="showOnlyGroupSchedule"
          >
            <v-icon
              >{{
                onlyGroupSchedule
                  ? "mdi-calendar-multiselect"
                  : "mdi-calendar-month"
              }}
            </v-icon>
          </v-btn>
        </template>
        <span>{{
          onlyGroupSchedule ? "Only Group Schedule" : "Show All Schedule"
        }}</span>
      </v-tooltip>
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-btn
            fab
            dark
            small
            color="grey"
            v-on="on"
            @click="showSettingGroupSchedule"
          >
            <v-icon>mdi-calendar-alert</v-icon>
          </v-btn>
        </template>
        <span>Setting</span>
      </v-tooltip>
    </v-speed-dial>
  </div>
</template>

<script>
import Schedule from "@/components/schedule/group/Schedule.vue";
import Chat from "@/components/schedule/group/Chat.vue";
import ParticipateUserList from "@/components/schedule/group/ParticipateUserList.vue";
import axiosScript from "@/api/axiosScript.js";

export default {
  components: {
    Schedule,
    Chat,
    ParticipateUserList,
  },
  data() {
    return {
      participateUser: [],
      groupScheduleInfo: [],
      online: [],
      colors: [
        "blue",
        "indigo",
        "deep-purple",
        "cyan",
        "orange",
        "grey darken-1",
        "red",
        "green",
        "yellow",
      ],
      fullScreenCalendar: false,
      onlyGroupSchedule: false,
      rerender: 0,
      chatModalFlag: false,
      actionButton: {
        direction: "top",
        fab: false,
        fling: false,
        hover: true,
        tabs: null,
        transition: "slide-y-reverse-transition",
      },
    };
  },
  watch: {
    $route(to) {
      this.participateUser = [];
      this.groupScheduleInfo = [];
      this.getGroupParticipateUserList();
      this.searchGroupScheduleByGroupId();
      this.rerender = to.params.id;
    },
    online(to) {
      console.log("GroupSchedule watch online");
      let tmpParticipateUser = [];
      for (let pu of this.participateUser) {
        for (let onUser of to) {
          console.log(pu);
          console.log(onUser);

          if (pu.email === onUser) {
            console.log("eq email ");

            pu.online = true;
          }
        }
        tmpParticipateUser.push(pu);
      }
      this.participateUser = tmpParticipateUser;
      console.log(this.participateUser);
    },
  },
  mounted() {
    this.getGroupParticipateUserList();
    this.searchGroupScheduleByGroupId();
    // this.getGroupInfo();
  },
  methods: {
    inChatEvent(payload) {
      this.online.push(payload);
    },
    getGroupParticipateUserList() {
      console.log("schedule GroupSchedule getGroupParticipateUserList");
      this.$store.commit("taskCntUp");
      axiosScript.searchGroupUserListByGid(
        this.$route.params.id,
        (res) => {
          for (let gsui of res.data) {
            let groupScheduleUserInfo = {
              email: gsui.user.email,
              name: gsui.user.name,
              color: this.getUserColor(gsui.user.id),
              online: false,
            };
            this.getGoogleUrl(gsui.user.id, this.getUserColor(gsui.user.id));
            this.participateUser.push(groupScheduleUserInfo);
          }
        },
        (err) => console.log(err),
        () => {
          this.$store.commit("taskCntDown");
        }
      );
    },
    searchGroupScheduleByGroupId() {
      console.log("schedule GroupSchedule searchGroupScheduleByGroupId");
      this.$store.commit("taskCntUp");
      axiosScript.searchGroupScheduleByGroupId(
        this.$route.params.id,
        (res) => {
          console.log(res.data);
          for (let userSchedule of res.data) {
            let s = new String(userSchedule.start_time).substr(0, 16);
            let e = new String(userSchedule.end_time).substr(0, 16);

            let scheduleInfo = {
              name: userSchedule.title,
              start: s,
              end: e,
              color: this.getUserColor(userSchedule.id),
            };
            this.groupScheduleInfo.push(scheduleInfo);
          }
        },
        (err) => console.log(err),
        () => {
          this.$store.commit("taskCntDown");
        }
      );
    },
    getGoogleUrl(userId, color) {
      console.log("GroupSchedule getGoogleUrl method ");
      this.$store.commit("taskCntUp");
      axiosScript.getGoogleUrl(
        userId,
        (res) => {
          if (res.status == 200) {
            console.log(res.data);
            let schedules = res.data;
            for (let s of schedules) {
              let scheduleInfo = {
                name: s.title,
                start: s.start_time,
                end: s.end_time,
                color: color,
              };
              this.groupScheduleInfo.push(scheduleInfo);
            }
          }
        },
        (err) => {
          console.log(err);
          this.$store.commit("snackbar", {
            text: `${userId}의 구글 캘린더 가져오기 실패.`,
            color: "error",
          });
        },
        () => {
          this.$store.commit("taskCntDown");
        }
      );
    },
    getUserColor(id) {
      return this.colors[id % this.colors.length];
    },
    addGroupSchedule() {
      console.log("schedule GroupSchedule addGroupSchedule method");
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
    },
    showOnlyGroupSchedule() {
      this.onlyGroupSchedule = !this.onlyGroupSchedule;
      console.log("schedule GroupSchedule onlyGroupSchedule method");
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
    },
    showSettingGroupSchedule() {
      console.log("schedule GroupSchedule showSettingGroupSchedule method");
      this.$store.commit("snackbar", {
        text: "서비스 준비중입니다..",
        color: "warning",
      });
    },
  },
};
</script>

<style></style>
