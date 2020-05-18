<template>
  <div class="home">
    <HelloWorld />
    <TodoList />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import TodoList from "@/components/todo/TodoList.vue";

export default {
  name: "Home",
  components: {
    HelloWorld,
    TodoList
  },
};
</script>
