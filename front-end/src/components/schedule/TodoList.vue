<template>
  <div class="container">
      <h2>Things To Do</h2>
      <form>
        <input type="text" v-model="inputValue"/>
        <!-- <button class="btn-add">add</button> -->
        <v-btn class="btn-add" small color="primary" @click="addTodo(inputValue)">add</v-btn>
      </form>

      <v-list class="list">
        <v-card class="items" v-for="(item, index) in items" :key="index">
            <span class="todo">{{item.content}}</span>
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
import "@/assets/css/todolist.scss"
import Datetime from 'vue-datetime'
import 'vue-datetime/dist/vue-datetime.css'
import todoApi from '../../api/schedule/todoApi'

Vue.use(Datetime)
export default {
  props:{
    items:{
      type : Object,
      require: true,
    }
  },
  data() {
    return {
      inputValue: this.value,
      startDate: 'Select Date',
      endDate: 'Select Date',
      scheduleId: 0,
      schedulemodal: false,
    }
  },
  methods:{
  
    changeScheduleId(){
      this.scheduleId = 1
      console.log(this.scheduleId)
    },
    update(){
      console.log(this.startdate)
      this.schedulemodal = false
    },
    addTodo(){
     
    },
    getTodo: async function(){
      this.items = (await todoApi.getTodo());
      console.log(this.items.data[0].content)
    },

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