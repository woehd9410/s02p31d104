import http from "@/api/v1/http-common.js";
export default {
  postBoard(param, success, error) {
    http
      .post(`board`, param)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
  searchBoardById(param, success, error) {
    http
      .get(`board/${param}`)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
  updateBoard(param, success, error) {
    http
      .put(`board`, param)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
  deleteBoard(param, success, error) {
    http
      .delete(`board/${param}`)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
  searchBoardAll(success, error) {
    http
      .get(`board`)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
  searchBoardByEmail(param, success, error) {
    http
      .get(`board/email/${param}`)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
};
