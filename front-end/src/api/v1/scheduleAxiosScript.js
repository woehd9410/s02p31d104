import http from '@/api/v1/http-common.js';

const getToDo = (param,success, error)=>{
  http
    .get(`user/${param}/to-do-list`)
    .then((res) => {
      console.log(res)
      success(res);
    })
    .catch((err) => {
      error(err);
    });
}

const addToDo = (params, success , error) =>{
  http
  .post('/to-do-list',params)
  .then((res) => {
    success(res);
  })
  .catch((err) => {
    error(err);
  });
}

const deleteToDo = (param, success, error) =>{
  http
  .delete(`/to-do-list/${param}`)
  .then((res) => {
    success(res);
  })
  .catch((err) => {
    error(err);
  });
}

const todoToSchedule = (params,success, error) =>{
  http
  .put('/to-do-list', params)
  .then((res) => {
    success(res);
  })
  .catch((err) => {
    error(err);
  });
}

const updateToDoState = (params, success, error) =>{
    console.log(params.isCompleted)
    http
    .put(`/to-do-list/${params.id}?is-completed=${params.isCompleted}`)
    .then((res)=>{
        success(res);
    })
    .catch((err) =>{
        error(err);
    });
}

const axiosFunction = {
  getToDo: (param, success, error) => getToDo(param, success, error),
  addToDo: (params, success, error) => addToDo(params, success, error),
  deleteToDo: (param, success, error) => deleteToDo(param, success, error),
  todoToSchedule: (params, success, error) => todoToSchedule(params, success, error),
  updateToDoState: (params, success, error) => updateToDoState(params, success, error),
};

export default axiosFunction;
