@@ -0,0 +1,354 @@
<template>
  <v-flex xs12 sm5>
    <v-card margin-top="150px">
      <v-toolbar color="grey" dark>
        <v-toolbar-title>ToDoList</v-toolbar-title>

        <v-spacer></v-spacer>

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
              <v-btn color="blue darken-1" text @click="update()">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>

      <v-row
        style="min-height:56px; border-bottom:2px solid rgba(0, 0, 0, 0.12); width:100%; margin-left:0px"
      >
        <v-col cols="10" sm="8" md="9" lg="10">
          <v-text-field v-model="title" @keyup.enter="addTodo"></v-text-field>
        </v-col>
        <v-col cols="2" sm="2" md="2" lg="2">
          <v-btn style="margin-left:-15px; margin-top:15px;" @click="addTodo()">
            Add
          </v-btn>
        </v-col>
      </v-row>
      <v-list style="padding-top:0px;">
        <template v-for="(item, index) in items">
          <v-list-item two-line :key="item.index">
            <v-list-item-content>
              <v-row style="padding-top:5px;">
                <v-icon
                  @click="isCompleteTodo()"
                  v-if="!isComplete"
                  style="margin:10px 10px 0 35px;"
                  >mdi-check-circle-outline
                </v-icon>
                <v-icon
                  @click="isCompleteTodo()"
                  v-else
                  style="margin:10px 10px 0 35px;"
                  color="primary"
                  >mdi-check-circle-outline
                </v-icon>
                <v-list-item-title
                  v-if="isComplete"
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
                  ><v-icon> mdi-delete </v-icon></v-btn
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
  data() {
    return {
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
    };
  },
  methods: {
    changeScheduleId(id) {
      this.scheduleId = id;
    },
    update() {
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
        user_id: this.userId,
      };

      axiosScript.updateToDo(
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
      let data = { title: this.title, type: type, user_id: userId };

      axiosScript.addToDo(
        data,
        (res) => {
          this.$emit("addEvent", res.data);
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
        },
        (error) => console.log(error)
      );
    },
    isCompleteTodo() {
      if (this.isComplete == true) this.isComplete = false;
      else this.isComplete = true;
    },
  },
};
</script>

<style>
.v-icon.v-icon.v-icon--link {
  width: 0px;
  height: 0px;
}
</style>
No newline at end of file
