import http from "@/api/v1/http-common.js";
export default {
  getToDo(param, success, error, final) {
    http
      .get(`user/${param}/to-do-list`)
      .then((res) => {
        success(res);
      })
      .catch((err) => {
        error(err);
      })
      .finally(() => final());
  },

  addToDo(params, success, error) {
    http
      .post("/to-do-list", params)
      .then((res) => {
        success(res);
      })
      .catch((err) => {
        error(err);
      });
  },

  deleteToDo(param, success, error) {
    http
      .delete(`/to-do-list/${param}`)
      .then((res) => {
        success(res);
      })
      .catch((err) => {
        error(err);
      });
  },

  deleteCompleteToDo(success, error){
    http
      .delete(`/to-do-list`)
      .then((res) => {
        success(res);
      })
      .catch((err) => {
        error(err);
      });
  },

  todoToSchedule(params, success, error) {
    http
      .put("/to-do-list", params)
      .then((res) => {
        success(res);
      })
      .catch((err) => {
        error(err);
      });
  },

  updateToDoState(params, success, error) {
    http
      .put(`/to-do-list/${params.id}?is-completed=${params.isCompleted}`)
      .then((res) => {
        success(res);
      })
      .catch((err) => {
        error(err);
      });
  },

  addSchedule(params, success, error, finall) {
    http
      .post(`user/personal-schedule`, params)
      .then((res) => success(res))
      .catch((err) => error(err))
      .finally(() => finall);
  },

  searchScheduleById(param, success, error, finall) {
    http
      .get(`user/${param}/personal-schedule`)
      .then((res) => success(res))
      .catch((err) => error(err))
      .finally(() => finall());
  },
  searchImportByIcsUrl(param, success, error, finall) {
    http
      .get(`ics`, { params: { url: param } })
      .then((res) => success(res))
      .catch((err) => error(err))
      .finally(() => finall());
  },
  searchGroupScheduleByGroupId(param, success, error, final) {
    http
      .get(`group/${param}/schedule`)
      .then((res) => success(res))
      .catch((err) => error(err))
      .finally(() => final());
  },
};
