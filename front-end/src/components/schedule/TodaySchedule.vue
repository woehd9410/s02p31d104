<template>
  <v-flex xs12 sm12 md5>
    <v-card>
      <v-card-title height="56px">
        <h3>Today's Schedule</h3>
      </v-card-title>
      <v-sheet height="400">
        <v-calendar
          ref="calendar"
          type="day"
          event-overlap-mode="stack"
          :events="scheduleInfo"
          :start="now"
          @click:event="showEvent"
          :event-color="getEventColor"
        >
        </v-calendar>
        <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
          ><DetailSchedule
            :selectedEvent="selectedEvent"
            @closeWindow="selectedOpen = false"
          />
        </v-menu>
      </v-sheet>
    </v-card>
  </v-flex>
</template>

<script>
import axiosScript from "@//api/axiosScript.js";
import DetailSchedule from "@/components/schedule/DetailSchedule.vue";
export default {
  mounted() {
    this.getScheduleById();
  },
  data() {
    return {
      showTodaySchedule: [],
      selectedElement: null,
      selectedEvent: null,
      selectedOpen: false,
    };
  },
  components: {
    DetailSchedule,
  },
  computed: {
    scheduleInfo() {
      return this.$store.getters.scheduleInfo;
    },
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
    now() {
      return this.$store.getters.now;
    },
  },
  methods: {
    getEventColor(event) {
      return event.color;
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => (this.selectedOpen = true), 10);
      };
      console.log("TodaySchedule showEvent");
      console.log(nativeEvent);
      console.log(event);
      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
    getScheduleById() {
      console.log("Home page getScheduleById");
      this.$store.commit("taskCntUp");
      axiosScript.searchScheduleById(
        this.userInfo.id,
        (res) => {
          if (res.status == 200) {
            this.$store.commit("setScheduleInfo", res.data);
            this.getGoogleCalendar();
          }
        },
        (err) => console.log(err),
        () => this.$store.commit("taskCntDown")
      );
    },
    getGoogleCalendar() {
      this.$store.commit("taskCntUp");
      let url = localStorage.getItem("iCal");
      if (url == null) {
        this.$store.commit("taskCntDown");
        return;
      }
      console.log("TodaySchedule getGoogleCalendar");
      axiosScript.searchImportByIcsUrl(
        url,
        (res) => {
          if (res.status == 200) {
            console.log(res.data);
            let schedules = res.data;
            for (let s of schedules) {
              s.color = localStorage.getItem("iCalColor");
              s.user_id = this.userInfo.id;
              this.$store.commit("pushScheduleInfo", s);
            }
          }
        },
        (err) => {
          console.log(err);
          this.$store.commit("snackbar", {
            text: "올바르 않은 URL입니다..",
            color: "error",
          });
        },
        () => this.$store.commit("taskCntDown")
      );
    },
  },
};
</script>

<style></style>
