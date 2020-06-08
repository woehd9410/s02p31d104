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
          <v-col cols="1">
            <v-menu>
              <template #activator="{ on: onMenu }">
                <v-tooltip top>
                  <template #activator="{on:onTooltip}">
                    <v-icon
                      :color="scheduleInfo.color"
                      v-on="{ ...onMenu, ...onTooltip }"
                      >mdi-circle</v-icon
                    >
                  </template>
                  <span>Schedule Color</span>
                </v-tooltip>
              </template>
              <v-card max-width="150">
                <v-container>
                  <v-card-text>
                    <v-row>
                      <v-col v-for="item in colors" :key="item.id" cols="4">
                        <v-icon :color="item" @click="chooseColor(item)"
                          >mdi-circle</v-icon
                        >
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-container>
              </v-card>
            </v-menu>
          </v-col>
        </v-row>

        <v-row
          style="width:100%; padding-left:30px;"
          align="center"
          justify="center"
        >
          <v-btn-toggle
            style="font-size:x-small; width:100%;"
            class="elevation-0"
            align="center"
            mandatory
          >
            <v-btn style="width:50%" @click="DateView(true)">
              내 맘대로
            </v-btn>
            <v-btn style="width:50%" @click="DateView(false)">
              하루종일
            </v-btn>
          </v-btn-toggle>
        </v-row>
        <template v-if="visible">
          <v-row>
            <v-col cols="6">
              <v-menu
                ref="startCalendar"
                v-model="startCalendar"
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
                  <v-btn text color="grey" @click="startCalendar = false"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-col cols="6">
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
                  @click:minute="inputStartTime()"
                ></v-time-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <v-menu
                ref="endCalendar"
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
                <v-date-picker
                  v-model="scheduleInfo.endDate"
                  no-title
                  scrollable
                >
                  <v-spacer></v-spacer>
                  <v-btn text color="grey" @click="endCalendar = false"
                    >OK</v-btn
                  >
                </v-date-picker>
              </v-menu>
            </v-col>
            <v-col cols="6">
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
        </template>
        <template v-else style="padding-top:50px;">
          <v-menu
            ref="startCalendar"
            v-model="startCalendar"
            :close-on-content-click="false"
            transition="scale-transition"
            offset-y
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                style="padding-top:20px;"
                prepend-icon="mdi-clock-start"
                v-model="scheduleInfo.startDate"
                label="Pick A Day"
                readonly
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker v-model="scheduleInfo.startDate" no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn text color="grey" @click="startCalendar = false">OK</v-btn>
            </v-date-picker>
          </v-menu>
        </template>
         
        <v-row>
          <v-col cols="10">
            <v-text-field
              prepend-icon="mdi-alpha-a-box-outline"
              label="address"
              readonly
              v-model="scheduleInfo.address"
              required
            ></v-text-field>
           
          </v-col>
           <v-col cols="2" style="margin:15px 0 0 -15px; padding-right:5px">
            <v-dialog v-model="MapView" dark persistent max-width="450px">
              <template v-slot:activator="{ on }">
                <v-btn dark v-on="on" style="min-width:30px;"
                  ><v-icon color="white">mdi-map</v-icon></v-btn
                >
              </template>
              <MapForSelectAddr 
              @cancleButtonEvent="MapView = false"
              @setLatLng="setLatLng"
              />
            </v-dialog>
            </v-col>
            <v-col 
            cols="12"
            v-if="this.showMap == true">
              <MapForView :items="latLng" ref="MapForView" />
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
import MapForSelectAddr from "@/components/map/MapForSelectAddr.vue"
import MapForView from "@/components/map/MapForView.vue"
export default {
  components: {
    MapForSelectAddr,
    MapForView,
  },
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
        color: "blue",
      },
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
      latLng :{
        latitude: "",
        longitude: "",    
      },
      startCalendar: false,
      startClock: false,
      endCalendar: false,
      endClock: false,
      visible: true,
      MapView: false,
      showMap: false,
    };
  },
  mounted () {
      if (navigator.geolocation) { // GPS를 지원하면
          navigator.geolocation.getCurrentPosition(function(position) {
          
          localStorage.setItem("myLat", position.coords.latitude);
          localStorage.setItem("myLng", position.coords.longitude);

          }, function(error) {
          console.error(error);
          }, {
          enableHighAccuracy: false,
          maximumAge: 0,
          timeout: Infinity
          });
      } else {
          alert('GPS를 지원하지 않습니다');
      }
  },
  methods: {
    cancleScheduleModal() {
      console.log("WriteSchedule cancleScheduleModal method cancle event");
      (this.scheduleInfo = {
        title: "",
        content: "",
        startDate: "",
        startTime: "",
        endDate: "",
        endTime: "",
        address: "",
        isPrivate: false,
        typeName: "",
        color: "blue",
      }),
      this.showMap = false;
      this.$emit("cancleButtonEvent");
    },
    updateSchedule() {
      console.log(
        `AddScheduleButton updateSchedule methed : save button event`
      );
      this.$store.commit("taskCntUp");
      let schedule = {
        user_id: this.userInfo.id,
        title: this.scheduleInfo.title,
        start_time: this.visible
          ? `${this.scheduleInfo.startDate} ${this.scheduleInfo.startTime}`
          : `${this.scheduleInfo.startDate} 00:00`,
        end_time: this.visible
          ? `${this.scheduleInfo.endDate} ${this.scheduleInfo.endTime}`
          : `${this.scheduleInfo.startDate} 23:59`,
        address: this.scheduleInfo.address,
        content: this.scheduleInfo.content,
        public_type: this.scheduleInfo.isPrivate ? 1 : 0,
        is_completed: 0,
        schedule_type: 0,
        color: this.scheduleInfo.color,
      };
      axiosScript.addSchedule(
        schedule,
        (res) => {
          if (res.status == 200) {
            console.log(res);
            this.$store.commit("pushScheduleInfo", res.data);
            this.$store.commit("snackbar", {
              text: "일정 등록 완료!!",
              color: "primary",
            });
            this.cancleScheduleModal();
          } else if (res.status == 204) {
            this.$store.commit("snackbar", {
              text: "일정 등록 실패!!",
              color: "error",
            });
          }
        },
        (err) => console.log(err),
        this.$store.commit("taskCntDown")
      );
      // axios script 작성하고 안에 scheduleInfo 비우기
    },
    DateView(value) {
      this.visible = value;
    },

    addToMembers() {
      this.members.push(this.inputValue);
      this.inputValue = null;
    },
    inputStartTime() {
      this.$refs.startClock.save(this.scheduleInfo.startTime);
      this.scheduleInfo.endDate = this.scheduleInfo.startDate;

      let tmp = new Date(
        this.scheduleInfo.startDate + " " + this.scheduleInfo.startTime
      );
      tmp.setHours(tmp.getHours() + 1);
      let endHour = "";
      let endMinute = "";
      if (tmp.getHours() / 10 > 1) endHour = tmp.getHours().toString();
      else endHour = "0" + tmp.getHours().toString();

      if (tmp.getMinutes() / 10 > 1) endMinute = tmp.getMinutes().toString();
      else endMinute = "0" + tmp.getMinutes().toString();
      this.scheduleInfo.endTime = endHour + ":" + endMinute;
    },
    chooseColor(item) {
      this.scheduleInfo.color = item;
    },
    setLatLng(data){
      this.latLng.latitude = data.lat;
      this.latLng.longitude = data.lng;
      if(!this.showMap) this.showMap = true;
      else this.$refs.MapForView.initMap();
      
      this.scheduleInfo.address = data.addr;
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
