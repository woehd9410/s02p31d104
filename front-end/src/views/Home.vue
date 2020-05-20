<template>
  <div class="home">
    <!-- <HelloWorld /> -->
    <TodoList :items="items"/>
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
      items:[
        { content:"todolist 만들기" },
        { content:"직무면접 준비하기" },
        { content:"여행계획 짜기" },
      ]
    }
  },
  components: {
    // HelloWorld,
    TodoList
  },
  mounted(){
    // this.getTodo();
  },
  methods:{
    getTodo: async function(){
      this.items = (await todoApi.getTodo());
      console.log(this.items.data[0].content)
    },
  }
};
</script>
