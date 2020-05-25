import axios from "axios";
import http from '@/api/http-common.js';

// 테스트 rest api사용
const testAxios = axios.create({
  baseURL: "https://jsonplaceholder.typicode.com/users/",
});

// get store/{name}
const getTest = (success, error) => {
  testAxios
    .get()
    .then((res) => {
      success(res);
    })
    .catch((err) => {
      error(err);
    });
};

const getToDo = (param,success, error)=>{
  http
    .get(`/api/v1/todo/${param}`)
    .then((res) => {
      success(res);
    })
    .catch((err) => {
      error(err);
    });
}

const addToDo = (params, success , error) =>{
  http
  .post('/api/v1/todo',params)
  .then((res) => {
    console.log(res.data);
    success(res);
  })
  .catch((err) => {
    error(err);
  });
}

const deleteToDo = (param, success, error) =>{
  http
  .delete(`/api/v1/todo/${param}`)
  .then((res) => {
    success(res);
  })
  .catch((err) => {
    error(err);
  });
}

const updateToDo = (params,success, error) =>{
  http
  .put('/api/v1/todo', params)
  .then((res) => {
    success(res);
  })
  .catch((err) => {
    error(err);
  });
}

const axiosFunction = {
  getTest: (success, error) => getTest(success, error),
  getToDo: (param, success, error) => getToDo(param, success, error),
  addToDo: (params, success, error) => addToDo(params, success, error),
  deleteToDo: (param, success, error) => deleteToDo(param, success, error),
  updateToDo: (params, success, error) => updateToDo(params, success, error),
};

export default axiosFunction;
