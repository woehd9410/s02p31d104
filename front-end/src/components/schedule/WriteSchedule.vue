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
               <v-icon color="success" v-on="onMenu">mdi-circle</v-icon>
               <v-tooltip top>
                 <template #activator="{on:onTooltip}">
                   <v-icon color="lime darken-1" v-on="{ ...onMenu, ...onTooltip}">mdi-circle</v-icon>
                 </template>
                 <span>Schedule Color</span>
               </v-tooltip>
             </template>

              <v-list>
                <v-list-item
                  v-for="(item, index) in colors"
                  :key="index"
                  @click="chooseColor(item)"
                >
                  <v-list-item-title>{{item}}</v-list-item-title>
                </v-list-item>
              </v-list>
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
            mandatory>
            <v-btn style="width:50%" @click="DateView(true)">
              내 맘대로
            </v-btn>
            <v-btn  style="width:50%" @click="DateView(false)">
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
                  <v-btn text color="grey" @click="startCalendar = false">OK</v-btn>
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
                <v-date-picker v-model="scheduleInfo.endDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn
                    text
                    color="grey"
                    @click="endCalendar = false"
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
        <template
          v-else
          style="padding-top:50px;"
          >
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
                <v-date-picker
                  v-model="scheduleInfo.startDate"
                  no-title
                  scrollable
                >
                  <v-spacer></v-spacer>
                  <v-btn text color="grey" @click="startCalendar = false">OK</v-btn>
                </v-date-picker>
              </v-menu>
        </template>
        <v-row
          style="margin-left:1px; margin-right:1px;"  
        >
          <v-combobox
            v-model="members"
            :items="chips"
            chips
            multiple
            clearable
            label="Invite Members"
            prepend-icon="mdi-alpha-m-box-outline"
          >
            <template v-slot:selection="{ attrs, item, select, selected }">
                <v-chip
                  small
                  v-bind="attrs"
                  :input-value="selected"
                  @click="select"
                  close
                  @click:close="remove(item)"
                >
                <strong style="font-size:small">{{ item }}</strong>
                </v-chip>
            </template>
          </v-combobox>
        </v-row>
        
        <v-row>
          <v-col cols="12">
            <v-text-field
              prepend-icon="mdi-alpha-a-box-outline"
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
        color:"",
      },
      colors: ["primary", "success", "error"],
      members : [],
      chips: ['Streaming', 'Eating','Work',
        'Home Improvement',
        'Vacation',
        'Food',
        'Drawers',
        'Shopping',
        'Art',
        'Tech',
        'Creative Writing',],
      startCalendar: false,
      startClock: false,
      endCalendar: false,
      endClock: false,
      visible: true,
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
    DateView(value){
      this.visible=value;
    },
    remove (item) {
      this.members.splice(this.members.indexOf(item), 1)
      this.members = [...this.members]
    },
    addToMembers(){
      this.members.push(this.inputValue);
      this.inputValue = null;
    },
    inputStartTime(){
      this.$refs.startClock.save(this.scheduleInfo.startTime)
      this.scheduleInfo.endDate = this.scheduleInfo.startDate
     
      let tmp = new Date(this.scheduleInfo.startDate + " " + this.scheduleInfo.startTime);
      tmp.setHours(tmp.getHours() + 1)
      let endHour = ""; let endMinute = "";
      if((tmp.getHours() / 10) > 1) endHour = tmp.getHours().toString();
      else endHour = "0" + tmp.getHours().toString();
     
      if((tmp.getMinutes())/ 10 > 1) endMinute = tmp.getMinutes().toString();
      else endMinute = "0" + tmp.getMinutes().toString();
      this.scheduleInfo.endTime = endHour + ":" + endMinute;
    },
    chooseColor(item){
      this.scheduleInfo.color = item;
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
};
</script>

<style></style>
