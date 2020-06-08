import http from "@/api/v1/http-common.js";
export default {
  searchGroupUserListByGid(param, success, error, final) {
    http
      .get(`group-user/${param}`)
      .then((res) => success(res))
      .catch((err) => error(err))
      .finally(() => final());
  },
};
