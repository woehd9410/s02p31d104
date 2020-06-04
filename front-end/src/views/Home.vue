<template>
  <div class="home">
    <v-content class="mt-12">
      <v-container grid-list-xl>
        <v-layout row wrap>
          <ToDoList
            
            :items="todolistItmes"
            @updateEvent="updateToDoList"
            @addEvent="addToDoList"
            @deleteEvent="deleteToDoList"
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
      todolistItmes: [],
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
            this.todolistItmes = res.data;
          }
        },
        (error) => {
          console.log(error);
        },
        () => {
          if (this.todolistItmes == null) this.todolistItmes = [];
          this.$store.commit("taskCntDown");
        }
      );
    },
    addToDoList(params) {
      this.todolistItmes.push(params);
    },
    deleteToDoList(id) {
      for (var i = 0; i < this.todolistItmes.length; i++) {
        if (id == this.todolistItmes[i].id) {
          this.todolistItmes.splice(i, 1);
          break;
        }
      }
    },
    updateToDoList(data) {
      for (var i = 0; i < this.todolistItmes.length; i++) {
        if (data.id == this.todolistItmes[i].id) {
          this.todolistItmes[i].is_completed = data.isCompleted;
          break;
        }
      }
    },
   
  },
};
</script>
