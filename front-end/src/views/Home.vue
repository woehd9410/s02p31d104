<template>
  <div class="home">
    <ToDoList />
  </div>
</template>

<script>
import ToDoList from "@/components/schedule/ToDoList.vue";

export default {
  name: "Home",
  components: {
    ToDoList,
  },
};
</script>
