import http from "@/api/v1/http-common.js";
export default {
  createGroup(param, success, error) {
    http
      .post(`group`, param)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
  searchGroupUserListByGid(param, success, error) {
    http
      .get(`group/${param}`)
      .then((res) => success(res))
      .catch((err) => error(err));
  },
};
