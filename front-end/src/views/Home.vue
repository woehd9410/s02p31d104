<template>
  <div class="home">
    <v-content class="mt-12">
      <v-row>
        <ToDoList />
        <TodaySchedule/>
      </v-row>
    </v-content>
  </div>
</template>

<script>
import ToDoList from "@/components/schedule/ToDoList.vue";
import TodaySchedule from "@/components/schedule/TodaySchedule.vue";

export default {
  name: "Home",
  components: {
    ToDoList,
    TodaySchedule,
  },
};
</script>
