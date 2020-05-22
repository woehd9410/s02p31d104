<template>
  <div class="container">
      <h2>Things To Do</h2>
      <form>
        <input type="text" v-model="inputValue"/>
        <!-- <button class="btn-add">add</button> -->
        <v-btn class="btn-add" small color="primary" @click="addTodo(inputValue)">add</v-btn>
      </form>

      <v-list class="list">
        <v-card class="items" v-for="(item, index) in items.data" :key="index">
            <span class="todo">{{item.content}}</span>
            <div class="btns">
              <v-btn class="toschedule" small color="success" 
              v-b-modal.modal-prevent-closing @click="changeScheduleId(item.id)">To Schedule</v-btn>
              <v-btn class="finish" small color="error" @click="deleteTodo(item.id)">finish</v-btn>
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
          label="Title"
          label-for="name-input"
          invalid-feedback="Name is required"
        >
        <b-input v-model="title"></b-input>
        </b-form-group>
        <b-form-group
          label="Start"
          label-for="name-input"
          invalid-feedback="Name is required"
        >
        <datetime type="datetime" v-model="startTime" format="yyyy-MM-dd HH:mm:ss"></datetime>
        </b-form-group>
        <b-form-group
          label="End"
          label-for="name-input"
          invalid-feedback="Name is required"
        >
        <datetime type="datetime" v-model="endTime" format="yyyy-MM-dd HH:mm:ss"></datetime>
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
      title: '',
      startTime: 'Select Date',
      endTime: 'Select Date',
      scheduleId: 0,
      schedulemodal: false,
      userId : 5,
    }
  },
  methods:{
  
    changeScheduleId(id){
      this.scheduleId = id
    },
    update: async function(){
      let start = new Date(this.startTime);
      let end = new Date(this.endTime);
      start.setHours(start.getHours() + 9);
      end.setHours(end.getHours() + 9);
      let data = {
        id : this.scheduleId,
        title : this.title,
        start_time : start,
        end_time : end
      };
      await todoApi.updateTodo(data);
      this.schedulemodal = false
      this.$emit("addEvent")
      this.title = null; this.startTime = null; this.endTime=null
      
    },
    addTodo: async function(){
     let content = this.inputValue; 
     let type = 0;
     let userId = this.userId

    let data = 
      { content : content,
        type : type,
        user_id : userId}

     await todoApi.addTodo(data);
     this.$emit("addEvent")
     this.inputValue = null
    },
    deleteTodo: async function(id){
      await todoApi.deleteTodo(id);
      this.$emit("addEvent")
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