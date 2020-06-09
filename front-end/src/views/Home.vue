<template>
  <div class="home">
    <v-content class="mt-12">
      <v-container grid-list-xl>
        <v-layout row wrap>
          <ToDoList
            v-if="items != null"
            :items="items"
            @updateEvent="update"
            @addEvent="addList"
            @deleteEvent="deleteList"
          />
        </v-layout>
      </v-container>
    </v-content>
  </div>
</template>

<script>
import ToDoList from "@/components/schedule/ToDoList.vue";
import axiosScript from "@//api/axiosScript.js";

export default {
  name: "Home",
  data() {
    return {
      items: null,
    };
  },
  computed: {
    userInfo() {
      return this.$store.getters.getUserInfo;
    },
  },
  components: {
    ToDoList,
  },
  mounted() {
    this.getToDo();
  },
  methods: {
    getToDo() {
      this.$store.commit("taskCntUp");
      console.log("Home page getToDo axios ");
      axiosScript.getToDo(
        this.userInfo.id,
        (res) => {
          if(res.status == 200){
            this.items = res.data;
          }
        },
        (error) => {
          console.log(error);
        },
        () => {
          if (this.items == null) this.items = [];
          this.$store.commit("taskCntDown");
        }
      );
    },
    addList(params) {
      this.items.push(params);
    },
    deleteList(id) {
      for (var i = 0; i < this.items.length; i++) {
        if (id == this.items[i].id) {
          this.items.splice(i, 1);
          break;
        }
      }
    },
    update(data) {
      for (var i = 0; i < this.items.length; i++) {
        if (data.id == this.items[i].id) {
          this.items[i].is_completed = data.isCompleted;
          break;
        }
      }
    },
  },
};
</script>
