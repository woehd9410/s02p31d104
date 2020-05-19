<template>
  <div class="container">
      <schedule-modal
        :scheduleModal.sync="scheduleModal"
        >
      </schedule-modal>
      <h2>Things To Do</h2>
      <form>
        <input type="text" v-model="inputValue"/>
        <!-- <button class="btn-add">add</button> -->
        <v-btn class="btn-add" small color="primary">add</v-btn>
      </form>

      <v-list class="list">
        <v-card class="items" v-for="(item, index) in data" :key="index">
            <span class="todo">{{item}}</span>
            <div class="btns">
              <v-btn class="toschedule" small color="success" 
              v-b-modal.modal-prevent-closing @click="changeScheduleId()">To Schedule</v-btn>
              <v-btn class="finish" small color="error">finish</v-btn>
            </div>
        </v-card>
      </v-list>
       <b-modal
      id="modal-prevent-closing"
      ref="modal"
      title="Submit Your ScheduleDate"
      v-model="schedulemodal"
      
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
          label="Start"
          label-for="name-input"
          invalid-feedback="Name is required"
        >
        <datetime type="datetime" v-model="startdate" ></datetime>
        </b-form-group>
        <b-form-group
          label="End"
          label-for="name-input"
          invalid-feedback="Name is required"
        >
        <datetime type="datetime" v-model="enddate" ></datetime>
        </b-form-group>
        
      </form>
      <template v-slot:modal-footer="{ ok, cancel}">
      <!-- Emulate built in modal footer ok and cancel button actions -->
      <b-button variant="primary" @click="update()">
        OK
      </b-button>
      <b-button variant="secondary" @click="cancel()">
        Cancel
      </b-button>
     
    </template>
    </b-modal>
  </div>
</template>

<script>
import Vue from 'vue'
import Datetime from 'vue-datetime'
import 'vue-datetime/dist/vue-datetime.css'

Vue.use(Datetime)
export default {
  data() {
    return {
      inputValue: this.value,
      data : ["todolist 만들기",
              "직무면접 준비하기",
              "여행계획 짜기"
             ],
      scheduleModal: false,
      startdate: 'Select Date',
      enddate: 'Select Date',
      scheduleId: 0,
      schedulemodal: false,
    }
  },
  methods:{
    toSchedulePopup(){
      this.scheduleModal = !this.scheduleModal;
      
    },
    changeScheduleId(){
      this.scheduleId = 1
      console.log(this.scheduleId)
    },
    update(){
      console.log(this.startdate)
      this.schedulemodal = false
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
</style>