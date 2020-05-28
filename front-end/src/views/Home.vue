<template>
  <div class="home">
    <ToDoList :items="items" @updateEvent="update" @addEvent="addList" @deleteEvent="deleteList" />
  </div>
</template>

<script>
import ToDoList from "@/components/schedule/ToDoList.vue";
import axiosScript from "@//api/axiosScript.js"
export default {
  name: "Home",
  data() {
    return {
      items: [],
      userId: 5,
    }
  },
  components: {
    ToDoList,
  },
  mounted(){
    this.getToDo();
  },
  methods:{
    getToDo(){
      axiosScript.getToDo(
        this.userId,
        (res)=>{this.items = res.data},
        (error) =>{console.log(error);
        }
      )
    },
    addList(params){
      this.items.push(params);
    },
    deleteList(id){
      for(var i = 0; i < this.items.length; i++){
        if(id == this.items[i].id){
           this.items.splice(i,1);
           break;
        }
      }
    },
    update(data){
      
      for(var i = 0; i < this.items.length; i++){
        if(data.id == this.items[i].id){
           this.items[i].is_completed = data.isCompleted;
           break;
        }
      }
    }
  }
};
</script>
