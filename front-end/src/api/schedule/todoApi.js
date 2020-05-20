import axios from "axios";

export default {
    getTodo(){
        console.log("todoApi 내 getTodo 함수")
        return axios.get('http://localhost:8080/todo/get',{
        });
    },
    addTodo(params){
        console.log("todoApi 내 addTodo 함수")
        return axios.post('http://localhost:8080/todo/add',{
            params
        });
    },
    deleteTodo(params){
        console.log("todoApi 내 deleteTodo 함수")
        return axios.delete('http://localhost:8080/todo/delete',{
            params
        });
    },
    updateTodo(params){
        console.log("todoApi 내 updateTodo 함수")
        return axios.put('http://localhost:8080/todo/update',{
            params
        });
    }
}