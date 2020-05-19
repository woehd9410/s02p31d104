import axios from 'axios'

export default {
    addTodoList(params){
        return axios.get('',{
            params,
        })
    }
}