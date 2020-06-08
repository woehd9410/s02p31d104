<template>
  <div class="home">
    <v-content class="mt-12">
      <v-container grid-list-xl>
        <v-layout row wrap>
          <ToDoList
            
            :items="todolistItems"
            @updateEvent="updateToDoList"
            @addEvent="addToDoList"
            @deleteEvent="deleteToDoList"
            @deleteCompleteTodoEvent="deleteCompleteTodo"
          />
          <TodaySchedule />
        </v-layout>
      </v-container>
    </v-content>
  </div>
</template>

<script>
import TodaySchedule from "@/components/schedule/TodaySchedule.vue";
import ToDoList from "@/components/schedule/ToDoList.vue";
import axiosScript from "@//api/axiosScript.js";

export default {
  name: "Home",
  data() {
    return {
      todolistItems: [],
    };
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  components: {
    ToDoList,
    TodaySchedule,
  },
  mounted() {
    this.getToDoList();
  },
  methods: {
    getToDoList() {
      this.$store.commit("taskCntUp");
      console.log("Home page getToDo axios ");
      axiosScript.getToDo(
        this.userInfo.id,
        (res) => {
          if (res.status == 200) {
            this.todolistItems = res.data;
          }
        },
        (error) => {
          console.log(error);
        },
        () => {
          if (this.todolistItems == null) this.todolistItems = [];
          this.$store.commit("taskCntDown");
        }
      );
    },
    addToDoList(params) {
      this.todolistItems.push(params);
    },
    deleteToDoList(id) {
      for (var i = 0; i < this.todolistItems.length; i++) {
        if (id == this.todolistItems[i].id) {
          this.todolistItems.splice(i, 1);
          break;
        }
      }
    },
    deleteCompleteTodo(){
       for (var i = this.todolistItems.length - 1; i >= 0 ; i--) {
        if (this.todolistItems[i].is_completed == 1) {
           this.todolistItems.splice(i, 1);
        }
      }
    },
    updateToDoList(data) {
      for (var i = 0; i < this.todolistItems.length; i++) {
        if (data.id == this.todolistItems[i].id) {
          this.todolistItems[i].is_completed = data.isCompleted;
          break;
        }
      }
    },
   
  },
};
</script>
