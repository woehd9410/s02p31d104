<template>
  <div class="home">
    <TodoList :items="items" @addEvent="addList" @deleteEvent="deleteList"/>
  </div>
</template>

<script>
// @ is an alias to /src
import axiosScript from '@/api/axiosScript';
import TodoList from "@/components/schedule/TodoList.vue";

export default {
  name: "Home",
  data(){
    return{
      items:[],
      userId: 5,
    }
  },
  components: {
    TodoList
  },
  mounted(){
    this.getToDo();
  },
  methods:{
    getToDo(){
      axiosScript.getToDo(
        this.userId,
        (res)=>{
          console.log(res)
          this.items = res.data},
        (error) =>{console.log(error);
        }
      )
    },
    addList(params){
      console.log(params);
      this.items.push(params);
      console.log("addList Finish")
    },
    deleteList(id){
      for(var i = 0; i < this.items.length; i++){
        if(id == this.items[i].id){
           this.items.splice(i,1);
           break;
        }
      }
    }
    // getTodo: async function(){
    //   this.items = (await todoApi.getTodo());
    // },
  }
};
</script>
