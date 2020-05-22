import axios from "axios";

export default {
    getTodo(){
        return axios.get('http://localhost:9000/api/v1/todo',{
        });
    },
    addTodo(params){
        return axios.post('http://localhost:9000/api/v1/todo',
            params
        );
    },
    deleteTodo(params){
        return axios.delete('http://localhost:9000/api/v1/todo/' + params,{
        });
    },
    updateTodo(params){
        return axios.put('http://localhost:9000/api/v1/todo',
            params
        );
    }
}