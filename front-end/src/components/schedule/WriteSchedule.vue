<template>
  <v-card>
    <v-card-title>
      <span class="headline" style="padding-left=20px;">+ Schedule</span>
    </v-card-title>
    <v-card-text>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field
              prepend-icon="mdi-alpha-t-box-outline"
              label="Title"
              v-model="scheduleInfo.title"
              required
            >
            </v-text-field>
          </v-col>
          <v-col cols="1">
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-icon
                  v-on="on"
                  style="cursor: pointer;"
                  :style="{
                    color: scheduleInfo.isPrivate ? 'red' : '#2196F3',
                  }"
                  @click="scheduleInfo.isPrivate = !scheduleInfo.isPrivate"
                >
                  {{
                    scheduleInfo.isPrivate
                      ? `mdi-lock-outline `
                      : `mdi-lock-open-variant-outline `
                  }}
                </v-icon>
              </template>
              <span>Private Check</span>
            </v-tooltip>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="8">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  prepend-icon="mdi-clock-start"
                  v-model="scheduleInfo.startDate"
                  label="Start Date"
                  readonly
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="scheduleInfo.startDate"
                no-title
                scrollable
              >
                <v-spacer></v-spacer>
                <v-btn text color="grey" @click="menu = false">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="4">
            <v-menu
              ref="startClock"
              v-model="startClock"
              :close-on-content-click="false"
              :nudge-right="40"
              :return-value.sync="scheduleInfo.startTime"
              transition="scale-transition"
              offset-y
              max-width="290px"
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="scheduleInfo.startTime"
                  label="Time"
                  readonly
                  v-on="on"
                ></v-text-field>
              </template>
              <v-time-picker
                v-if="startClock"
                v-model="scheduleInfo.startTime"
                full-width
                @click:minute="$refs.startClock.save(scheduleInfo.startTime)"
              ></v-time-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="8">
            <v-menu
              ref="menu"
              v-model="endCalendar"
              :close-on-content-click="false"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  prepend-icon="mdi-clock-end"
                  v-model="scheduleInfo.endDate"
                  label="End Date"
                  readonly
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="scheduleInfo.endDate" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn
                  text
                  color="grey"
                  @click="scheduleInfo.endCalendar = false"
                  >OK</v-btn
                >
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="4">
            <v-menu
              ref="endClock"
              v-model="endClock"
              :close-on-content-click="false"
              :nudge-right="40"
              :return-value.sync="scheduleInfo.endTime"
              transition="scale-transition"
              offset-y
              max-width="290px"
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="scheduleInfo.endTime"
                  label="Time"
                  readonly
                  v-on="on"
                ></v-text-field>
              </template>
              <v-time-picker
                v-if="endClock"
                v-model="scheduleInfo.endTime"
                full-width
                @click:minute="$refs.endClock.save(scheduleInfo.endTime)"
              ></v-time-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12">
            <v-text-field
              label="address"
              v-model="scheduleInfo.address"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-textarea
              v-model="scheduleInfo.content"
              label="Content"
              auto-grow
              outlined
              rows="1"
              row-height="15"
            ></v-textarea>
          </v-col>
        </v-row>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="green lighten-2" text @click="updateSchedule">Save</v-btn>
      <v-btn color="red darken-1" text @click="cancleScheduleModal"
        >Cancle</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
import axiosScript from "@/api/axiosScript.js";
export default {
  data() {
    return {
      scheduleInfo: {
        title: "",
        content: "",
        startDate: "",
        startTime: "",
        endDate: "",
        endTime: "",
        address: "",
        isPrivate: false,
        typeName: "",
      },
      menu: false,
      startCalendar: false,
      startClock: false,
      endCalendar: false,
      endClock: false,
    };
  },
  methods: {
    cancleScheduleModal() {
      console.log("WriteSchedule cancleScheduleModal method cancle event");
      this.$emit("cancleButtonEvent");
    },
    updateSchedule() {
      console.log(
        `AddScheduleButton updateSchedule methed : save button event`
      );
      console.log(this.scheduleInfo);
      let schedule = {
        user_id: this.userInfo.id,
        title: this.scheduleInfo.title,
        start_time: `${this.scheduleInfo.startDate} ${this.scheduleInfo.startTime}`,
        end_time: `${this.scheduleInfo.endDate} ${this.scheduleInfo.endTime}`,
        address: this.scheduleInfo.address,
        content: this.scheduleInfo.content,
        public_type: this.scheduleInfo.isPrivate ? 1 : 0,
        is_completed: 0,
        schedule_type: 0,
      };
      axiosScript.addSchedule(
        schedule,
        (res) => {
          if (res.status == 200) {
            console.log(res);
            alert("일정 등록 완료!");
            this.cancleScheduleModal();
          } else if (res.status == 204) {
            alert("일정 등록 실패");
          }
        },
        (err) => console.log(err)
      );
      // axios script 작성하고 안에 scheduleInfo 비우기
    },
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
};
</script>

<style></style>
