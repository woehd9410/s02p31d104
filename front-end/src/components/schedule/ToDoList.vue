@@ -0,0 +1,354 @@
<template>
  <v-flex xs12 md5 sm12>
    <v-text-field 
    label="Add TO DO..."
    solo
    dense
    v-model="title"
    @keyup.enter="addTodo"
    >
    </v-text-field>
    <v-card margin-top="150px">
        <v-dialog v-model="toScheduleModal" persistent max-width="450px">
          <v-card>
            <v-card-title>
              <span class="headline" style="padding-left=20px;"
                >To Schedule</span
              >
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="8" style="40px !important;">
                    <v-text-field
                      label="Title"
                      v-model="title"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="4">
                    <v-select
                      :items="['private', 'public']"
                      label="Can see all or only you"
                      v-model="typeName"
                      required
                    ></v-select>
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
                          v-model="startDate"
                          label="Start Date"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker v-model="startDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn text color="grey" @click="menu = false"
                          >OK</v-btn
                        >
                      </v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col cols="4">
                    <v-menu
                      ref="startClock"
                      v-model="startClock"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      :return-value.sync="startTime"
                      transition="scale-transition"
                      offset-y
                      max-width="290px"
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          v-model="startTime"
                          label="Time"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-time-picker
                        v-if="startClock"
                        v-model="startTime"
                        full-width
                        @click:minute="$refs.startClock.save(startTime)"
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
                          v-model="endDate"
                          label="End Date"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker v-model="endDate" no-title scrollable>
                        <v-spacer></v-spacer>
                        <v-btn text color="grey" @click="endCalendar = false"
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
                      :return-value.sync="endTime"
                      transition="scale-transition"
                      offset-y
                      max-width="290px"
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          v-model="endTime"
                          label="Time"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-time-picker
                        v-if="endClock"
                        v-model="endTime"
                        full-width
                        @click:minute="$refs.endClock.save(endTime)"
                      ></v-time-picker>
                    </v-menu>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      label="address"
                      v-model="address"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      v-model="content"
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
              <v-btn color="blue darken-1" text @click="toScheduleModal = false"
                >Cancle</v-btn
              >
              <v-btn color="blue darken-1" text @click="todoToSchedule()">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      <v-progress-linear class="my-0" color="success" v-model="progressPercentage"/>
        <v-card-actions class="px-3" v-show="items.length">
          <span class="success--text"  style="font-size:small;">
            {{ remaining }} {{ remaining | pluralize('item') }} left
          </span>
          <v-spacer></v-spacer>
          <v-btn-toggle
            style="font-size:x-small;"
            class="elevation-0"
            mandatory
            color="white"
            v-show="items.length"
          >
            <v-btn
              @click="changeList(1)"
              class="mx-0"
              color="success--text"
              small
            >all
            </v-btn>
            <v-btn
              @click="changeList(2)"
              class="mx-0"
              color="success--text"
              small
            >active
            </v-btn>
            <v-btn
              @click="changeList(3)"
              class="mx-0"
              color="success--text"
              small
            >complete
            </v-btn>
          </v-btn-toggle>
        </v-card-actions>
      <v-list style="padding-top:0px; overflow-y:hidden;" height=360 class="overflow-y-auto">
        
        <template v-for="(item, index) in list">
          <v-list-item two-line :key="item.index">
            <v-list-item-content>
              <v-row>
                <v-checkbox
                  style="margin:5px 0 0 20px;"
                  v-model="item.is_completed"
                  @click="isCompleteTodo(item)"
                  v-if="!item.is_completed"
                  color="success"
                  hide-details
                ></v-checkbox>
                <v-checkbox
                  style="margin:5px 0 0 20px;"
                  v-model="item.is_completed"
                  @click="isCompleteTodo(item)"
                  v-else
                  color="success"
                  hide-details
                ></v-checkbox>
               
                <v-list-item-title
                  v-if="item.is_completed"
                  class="success--text"
                  style="margin: 12px 0 10px 10px; flex:0; overflow:inherit; text-decoration:line-through;"
                  v-text="item.title"
                >
                </v-list-item-title>
                <v-list-item-title
                  v-else
                  style="margin: 12px 0 10px 10px; flex:0; overflow:inherit;"
                  v-text="item.title"
                >
                </v-list-item-title>
              </v-row>
            </v-list-item-content>
            <v-list-item-action>
              <v-row style="margin-bottom:5px; margin-right:-1px;">
                <v-btn
                  icon
                  style="margin-top:10px;"
                  @click="
                    toScheduleModal = true;
                    changeScheduleId(item.id);
                  "
                  ><v-icon> mdi-calendar </v-icon></v-btn
                >
                <v-btn
                  icon
                  style="margin-top:9.5px;"
                  @click="deleteTodo(item.id)"
                  ><v-icon color="red"> mdi-delete </v-icon></v-btn
                >
              </v-row>
            </v-list-item-action>
          </v-list-item>

          <v-divider
            v-if="index + 1 < items.length"
            :key="index"
            style="margin-top:0px; margin-bottom:0px;"
          ></v-divider>
        </template>
      </v-list>
    </v-card>
  </v-flex>
</template>

<script>
import axiosScript from "@/api/axiosScript.js";

export default {
  props: {
    items: {
      type: Array,
      require: true,
    },
  },

  created () {
    this.updateNum = 1;
  },
  updated () {
    if(this.updateNum == 1){
      this.changeList(1);
      this.updateNum++;
    }
  },
  computed: {
    remaining (){
      var completeNum = 0;
      for(var i = 0; i < this.items.length; i++){
        if(this.items[i].is_completed == 0) completeNum++;
      }
      return completeNum
    },
    progressPercentage() {
      const len = this.items.length;
      return ((len - this.remaining) * 100) / len
    },
  },
  data() {
    return {
      list : this.items,
      startCalendar: false,
      startClock: false,
      endCalendar: false,
      endClock: false,
      toScheduleModal: false,
      menu: false,
      todoEnd: false,
      selected: [2],
      inputValue: this.value,
      title: "",
      content: "",
      startDate: "",
      startTime: "",
      endDate: "",
      endTime: "",
      address: "",
      scheduleId: 0,
      schedulemodal: false,
      userId: 5,
      typeName: "",
      isComplete: false,
      btnNum: 0,
      btns : ["ALL", "ACTIVE", "COMPLETED"],
      updateNum : 0,
    };
  },
  methods: {
    changeScheduleId(id) {
      this.scheduleId = id;
    },
    todoToSchedule() {
      this.toScheduleModal = false;
      let start = this.startDate + " " + this.startTime + ":00";
      let end = this.endDate + " " + this.endTime + ":00";
      let type;
      if (this.typeName === "private") type = 0;
      else type = 1;
      
      let data = {
        id: this.scheduleId,
        content: this.content,
        title: this.title,
        start_time: start,
        end_time: end,
        address: this.address,
        public_type: type,
        schedule_type: 0,
        user_id: this.userId,
      };

      axiosScript.todoToSchedule(
        data,
        () => {
          this.schedulemodal = false;
          this.$emit("deleteEvent", data.id);
          this.title = null;
          this.content = null;
          this.startTime = null;
          this.endTime = null;
          this.startDate = null;
          this.endDate = null;
          this.address = null;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    addTodo() {
      let type = 0;
      let userId = this.userId;
      let data = { title: this.title, public_type: type, user_id: userId };

      axiosScript.addToDo(
        data,
        (res) => {
          this.$emit("addEvent", res.data);
          this.changeList(this.btnNum)
        },
        (error) => {
          console.log(error);
        }
      );
      this.title = null;
    },
    deleteTodo(id) {
      axiosScript.deleteToDo(
        id,
        () => {
          this.$emit("deleteEvent", id);
          this.changeList(this.btnNum)
        },
        (error) => console.log(error)
      );
      
    },
    isCompleteTodo(item) {
      let isCompleted = 0;
      if(item.is_completed == 0) isCompleted = 1;
      else isCompleted = 0;
      let data = {
        id: item.id,
        isCompleted: isCompleted
      }
      axiosScript.updateToDoState(
        data,
        () => {
          this.$emit("updateEvent", data);
          this.changeList(this.btnNum);
        },
        (error) => console.log(error)
      )
     
    },
    changeList(num){
      if(num == 1){
        this.btnNum = 1
        this.list = this.items
      }else if(num ==2){
        this.btnNum = 2
        let tmp = [];
        for(var i = 0; i < this.items.length; i++){
          if(this.items[i].is_completed == 0) tmp.push(this.items[i])
        }
        this.list = tmp;
      }else if(num == 3){
        this.btnNum = 3
        let tmp = [];
        for(var j = 0; j < this.items.length; j++){
          if(this.items[j].is_completed == 1) tmp.push(this.items[j])
        }
        this.list = tmp;
      }
    }
  },
  filters: {
    pluralize: (n, w) => n === 1 ? w : (w + 's'),
    capitalize: s => s.charAt(0).toUpperCase() + s.slice(1)
  }
};
</script>

<style>
.v-icon.v-icon.v-icon--link {
  width: 0px;
  height: 0px;
}
</style>
No newline at end of file
