import axios from "axios";

export default {
    getTodo(){
        console.log("todoApi 내 getTodo 함수")
        return axios.get('http://localhost:8080/todo',{
        });
    },
    addTodo(params){
        console.log("todoApi 내 addTodo 함수")
        return axios.get('http://localhost:8080/todo',{
            params
        });
    }
}