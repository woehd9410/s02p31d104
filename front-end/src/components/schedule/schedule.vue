<template>
  <v-row>
    <v-col>
      <v-sheet height="64">
        <v-toolbar flat color="white">
          <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
            Today
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="prev">
            <v-icon small>mdi-chevron-left</v-icon>
          </v-btn>
          <v-btn fab text small color="grey darken-2" @click="next">
            <v-icon small>mdi-chevron-right</v-icon>
          </v-btn>
          <v-toolbar-title>{{ focus }}</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-menu bottom right>
            <template v-slot:activator="{ on }">
              <v-btn outlined color="grey darken-2" v-on="on">
                <span>{{ typeToLabel[type] }}</span>
                <v-icon right>mdi-menu-down</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="type = 'day'">
                <v-list-item-title>Day</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'week'">
                <v-list-item-title>Week</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'month'">
                <v-list-item-title>Month</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = '4day'">
                <v-list-item-title>4 days</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="550">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="scheduleArr"
          :event-color="getEventColor"
          :now="today"
          event-overlap-mode="column"
          :type="type"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="viewDay"
        ></v-calendar>
        <v-menu v-model="selectedOpen" :close-on-content-click="false" :activator="selectedElement" offset-x
          ><DetailSchedule :selectedEvent="selectedEvent" @closeWindow="selectedOpen = false" />
        </v-menu>
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
import DetailSchedule from "@/components/schedule/DetailSchedule.vue";
export default {
  props: ["scheduleArr"],

  data: () => ({
    focus: `${new Date().getFullYear()}-${
      (new Date().getMonth() + 1).toString().length < 2 ? "0" + (new Date().getMonth() + 1) : new Date().getMonth() + 1
    }-${new Date().getDate().toString().length < 2 ? "0" + new Date().getDate() : new Date().getDate()}`,
    type: "month",
    typeToLabel: {
      month: "Month",
      week: "Week",
      day: "Day",
      "4day": "4 Days",
    },
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    colors: ["blue", "indigo", "deep-purple", "cyan", "orange", "grey darken-1", "red", "green", "yellow"],
  }),
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
    scheduleInfo() {
      return this.$store.getters.scheduleInfo;
    },
    today() {
      var d = new Date(),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;

      return [year, month, day].join("-");
    },
    now() {
      return this.$store.getters.now;
    },
  },
  watch: {
    $route(to) {
      if (to.params.id == this.userInfo.id) {
        console.log("Profile watch route eq to.params.id and userInfo.id");
        this.profileInfo = this.userInfo;
        return;
      }
      console.log(`Profile watch route to.params.id : ${to.params.id}`);
    },
    now(newValue) {
      this.focus = newValue;
    },
    focus(newValue) {
      this.$store.commit("setScheduleNow", newValue);
    },
  },
  mounted() {
    console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    console.log(this.scheduleArr);
    console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
  },
  methods: {
    viewDay({ date }) {
      this.focus = date;
      this.type = "day";
    },
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = this.today;
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => (this.selectedOpen = true), 10);
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },

    getUserColor(id) {
      return this.colors[id % this.colors.length];
    },
  },
  components: {
    DetailSchedule,
  },
};
</script>
