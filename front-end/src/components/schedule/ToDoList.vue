<template>
  <div style="margin-top:150px">
     
      <v-card
    margin-top="150px"
    max-width="500"
    class="mx-auto"
  >
    <v-toolbar
      color="grey"
      dark
    >
      <v-toolbar-title>ToDoList</v-toolbar-title>

      <v-spacer></v-spacer>

    <v-dialog v-model="todoModal" persistent max-width="500px">
      <template v-slot:activator="{ on }">
        <v-btn icon dark v-on="on">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline" style="padding-left=20px;">To Do</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" >
                <v-text-field label="Title" v-model="title" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="content" v-model="content"  required></v-text-field>
              </v-col>
            </v-row>
            <v-row>
            <v-col cols="12">
              
              <v-menu
                ref="todoEnd"
                v-model="todoEnd"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-icon>mdi-calendar</v-icon>
                  <v-text-field
                    v-model="endTime"
                    label="Choose Daedline"
                    readonly
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker v-model="endTime" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="grey" @click="todoEnd = false">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="todoModal = false">Cancle</v-btn>
          <v-btn color="blue darken-1" text @click="addTodo()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>  

    <v-dialog v-model="toScheduleModal" persistent max-width="450px">
      <v-card>
        <v-card-title>
          <span class="headline" style="padding-left=20px;">To Schedule</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row >
              <v-col cols="8" style="40px !important;">
                <v-text-field label="Title" v-model="title" required></v-text-field>
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
                  <v-btn text color="grey" @click="endCalendar = false">OK</v-btn>
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
              <v-col cols=12>
                <v-text-field label="address" v-model="address" required></v-text-field>
              </v-col>
                <v-col cols="12" >
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
          <v-btn color="blue darken-1" text @click="toScheduleModal = false">Cancle</v-btn>
          <v-btn color="blue darken-1" text @click="update()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>  
    </v-toolbar>

    <v-list>

        <template v-for="(item, index) in items">
          <v-list-item two-line :key="item.index">
    
              <v-list-item-content>
                
                <v-list-item-title style="margin-top:20px; margin-bottom:10px;" v-text="item.content"></v-list-item-title>

                <v-list-item-subtitle v-text="'Deadline : '"></v-list-item-subtitle>
                <v-list-item-subtitle v-text='tmp'></v-list-item-subtitle>

              </v-list-item-content>
              <v-list-item-action>
                <v-row style="margin-bottom:5px; margin-right:-1px;">
                  <v-btn icon style="margin-top:10px;" @click="toScheduleModal = true; changeScheduleId(item.id);"><v-icon> mdi-tab </v-icon></v-btn>
                  <v-btn icon style="margin-top:9.5px;" @click="deleteTodo(item.id)"><v-icon > mdi-delete </v-icon></v-btn>
                </v-row>
                <!-- <v-icon
                  style="padding-right: 5px"
                  v-if="!active"
                  color="grey lighten-1"
                >
                  mdi-star
                </v-icon>
  
                <v-icon
                  style="padding-right: 5px"
                  v-else
                  color="error"
                >
                  mdi-star
                </v-icon> -->
                
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
  </div>
</template>

<script>
import Vue from 'vue'
import "@/assets/css/todolist.scss"
import Datetime from 'vue-datetime'
import 'vue-datetime/dist/vue-datetime.css'
import axiosScript from '@/api/axiosScript.js';

Vue.use(Datetime)
export default {
  props:{
    items:{
      type : Array,
      require: true,
    }
  },
  data() {
    return {
      todoModal : false,
      startCalendar: false,
      startClock : false,
      endCalendar : false,
      endClock : false,
      toScheduleModal : false,
      menu : false,
      todoEnd : false,
      selected: [2],
      inputValue: this.value,
      title: '',
      content: '',
      startDate: '',
      startTime: '',
      endDate : '',
      endTime: '',
      address:'',
      scheduleId: 0,
      schedulemodal: false,
      userId : 5,
      typeName:'',
      tmp : '2020:05:20'
    }
  },
  methods:{
  
    changeScheduleId(id){
      this.scheduleId = id
    },
    update(){
      this.toScheduleModal = false;
      let start = this.startDate + " " + this.startTime + ":00";
      let end = this.endDate + " " + this.endTime + ":00";
      let type;
      if(this.typeName ==='private') type = 0;
      else type = 1;
      let data = {
        id : this.scheduleId,
        content : this.content,
        title : this.title,
        start_time : start,
        end_time : end,
        address : this.address,
        type : type,
        user_id : this.userId
      };
    
      axiosScript.updateToDo(
        data,
        () => {
           this.schedulemodal = false
           this.$emit("deleteEvent", data.id)
           this.title = null; this.content = null; this.startTime = null; this.endTime=null;
           this.startDate = null; this.endDate = null; this.address = null;
        },
        (error) => {console.log(error)}
      )
     
      
    },
    addTodo(){
     let type = 0;
     let userId = this.userId;
     this.todoModal = false;
    let data = 
      { content : this.content,
        title : this.title,
        end_time : this.endTime,
        type : type,
        user_id : userId}

     axiosScript.addToDo(
       data,
       (res) => {
          this.$emit("addEvent",res.data)
       },
       (error) => {
          console.log(error)
       }
     )
     console.log("addToDo Finish")
     this.inputValue = null
    },
    deleteTodo(id){
      console.log("delete")
      axiosScript.deleteToDo(
        id,
        () =>{
           this.$emit("deleteEvent", id)
        },
        (error) => console.log(error)
      )
    }
    
  }
}
</script>

<style>
.vdatetime{
  width:220px;
  border: double;
}
.vdatetime input{
  width: 200px;
  margin-left: 10px;
} 
.theme--light.v-list-item .v-list-item__subtitle, 
.theme--light.v-list-item .v-list-item__action-text{
  display: contents;
}
</style>