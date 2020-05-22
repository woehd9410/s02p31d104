<template>
  <div class="home">
    <!-- <HelloWorld /> -->
    <TodoList :items="items" @addEvent="getTodo"/>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";
import TodoList from "@/components/schedule/TodoList.vue";
import todoApi from '../api/schedule/todoApi'

export default {
  name: "Home",
  data(){
    return{
      items:{}
    }
  },
  components: {
    // HelloWorld,
    TodoList
  },
  mounted(){
    this.getTodo();
  },
  methods:{
    getTodo: async function(){
      this.items = (await todoApi.getTodo());
    },
  }
};
</script>
